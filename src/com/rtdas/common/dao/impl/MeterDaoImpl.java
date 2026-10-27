package com.rtdas.common.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.dao.MeterDao;
import com.rtdas.common.models.DTR;
import com.rtdas.common.models.Feeder;
import com.rtdas.common.models.HTConsumer;
import com.rtdas.common.models.InstallationPoints;
import com.rtdas.common.models.Meter;
import com.rtdas.common.models.MeterMake;
import com.rtdas.common.models.MeterType;


@Repository("meterDao")
public class MeterDaoImpl implements MeterDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Meter> listMetersByLocation(List<Integer> locationid, int type) {
		System.out.println(type+"  <---  "+locationid);
		List<Meter> meters=new ArrayList();
		try {
			List<Integer> fids=new ArrayList();
			List<Integer> dtids=new ArrayList();
			List<Integer> htids=new ArrayList();
			List<Integer> locids=new ArrayList();
			//feeder
				Criteria crit=sessionFactory.getCurrentSession().createCriteria(Feeder.class);
				crit.setProjection(Projections.property("feederid"));
				crit.add(Restrictions.in("substationid", locationid));
				fids=(List<Integer>)crit.list(); 
				System.out.println("fids --> "+fids);
				
			if(type==6 ||type==0) {
				System.out.println("cndition 2");
				Criteria dtrcrit=sessionFactory.getCurrentSession().createCriteria(DTR.class);
				dtrcrit.setProjection(Projections.property("dtrid"));
				if(!fids.isEmpty())
				 dtrcrit.add(Restrictions.in("feederid", fids));
				dtids=(List<Integer>)dtrcrit.list(); 				
			}
			
			if(type==8 ||type==0) {
				System.out.println("cndition 3");
				Criteria htcrit=sessionFactory.getCurrentSession().createCriteria(HTConsumer.class);
				htcrit.setProjection(Projections.property("consumer_id"));
				htcrit.add(Restrictions.in("feeder_id", locationid));
				htids=(List<Integer>)htcrit.list(); 
				System.out.println("HT ids ------------"+htids);
			}			
			
//	System.out.println(fids);
//	System.out.println(dtids);
//	System.out.println(htids);
			if(type==7) locids.addAll(fids);
			if(type==6) locids.addAll(dtids);
			if(type==8) locids.addAll(htids);
			if(type==0) {
				locids.addAll(fids);
				locids.addAll(dtids);
				locids.addAll(htids);
			}
			System.out.println(locids+"----------------"+"\t"+type+"----------->"+locids);
			
			if(!locids.isEmpty()) {
				
				List<String> locs=new ArrayList();
				for(int i:locids) {
					locs.add(""+i);
				}
			Criteria q=sessionFactory.getCurrentSession().createCriteria(Meter.class);
			if(type!=0)
			  q.add(Restrictions.eq("meterlocation", ""+type));
			  q.add(Restrictions.in("location_id", locs));
			meters=q.list();
			fids=null;
			dtids=null;
			htids=null;
			locids=null;
			System.out.println("Meters list size ---------->"+meters.size());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return meters;
	}

	
	
	@Override
	public void addMeter(Meter meter) {
		try{
			
	     Session ses=sessionFactory.getCurrentSession();		
		       	ses.saveOrUpdate(meter);
		        ses.flush();
		        ses.clear();
	//------------updating the total_masterdata table
		String sql="REPLACE INTO total_masterdata(region,region_id,region_pid,circle,circle_id,circle_pid,division,division_id,division_pid,sd,sd_id,sd_pid,ss,ss_id,ss_pid,ptr,ptr_id,ptr_pid,meter_sl_no,meter_make,meter_type,meter_location,mf,location_id,modem_sl_no,modem_mobile_no,location_name,location_code,location_category,location_group,supply_voltage)"+
					 " (SELECT m.region,m.region_id,m.region_pid,m.circle,m.circle_id,m.circle_pid,m.division,m.division_id,m.division_pid,m.sd,m.sd_id,m.sd_pid,m.ss,m.ss_id,m.ss_pid,m.ptr,m.ptr_id,m.ptr_pid,"+
					" mt.meter_serial_no,mt.metermake,mt.meter_type,mt.meterlocation,mt.mf,mt.location_id,md.modem_serial_no,md.mobile_no,f.feedername,f.structure_code,f.feeder_type,f.feeder_group,f.voltage_level"+
					" FROM masterdata m  JOIN feeder f ON m.ptr_id=f.substationid"+
					 " JOIN meter mt ON mt.meter_serial_no='"+meter.getMeter_sl_no()+"' AND mt.location_id=f.feederid LEFT JOIN modem md ON mt.modem_serial_no=md.modem_serial_no)";
		
		System.out.println("--------------------->"+sql);
		Query q=ses.createSQLQuery(sql);
		if(q.executeUpdate()>0) System.out.println("Total master record insertd...");
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMeter(Meter meter) {
		try{
			Session ses=sessionFactory.getCurrentSession();
			String sql="delete from total_masterdata where meter_sl_no=(select meter_serial_no from meter where meterid="+meter.getMeter_id()+")";
	         Query q=ses.createSQLQuery(sql);
	         q.executeUpdate();
	         ses.flush();
		     ses.clear();
		        
			 sql="delete from Meter where meter_id="+meter.getMeter_id();
			 q=ses.createQuery(sql);
			q.executeUpdate();
			ses.flush();
	        ses.clear();
	        
	         
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	@Override
	public Meter getMeter(int meterId) {
		return (Meter)sessionFactory.getCurrentSession().createQuery("from Meter where meter_id="+meterId).uniqueResult();
	}
	
	@Override
	public Meter getMeterByslno(String meter){
		return (Meter)sessionFactory.getCurrentSession().createQuery("from Meter where meter_sl_no="+meter).uniqueResult();
	}

	@Override
	public List<Meter> listMeters(List<Integer> iedids) {
		
		System.out.println("-------------->"+iedids);
		List<Meter> list=null;
		try{
		String sql="from Meter";
//		if(!iedids.isEmpty()){
//			sql=sql+" where location_id in (select feederid from Feeder where feederid in (:ids)) or location_id is null";
//		}
		Query q=sessionFactory.getCurrentSession().createQuery(sql);
//		if(!iedids.isEmpty()){
//		 q.setParameterList("ids", iedids);
//		}
		
		list=q.list();
		
//------------------------------------------------
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MeterType> listMeterTypes() {
		return sessionFactory.getCurrentSession().createQuery("from MeterType").list();
	}

	@Override
	public List<Meter> listAdminMeters(List<Integer> iedids) {
		List<Meter> list=null;
		try{
		String sql="from Meter";
		if(!iedids.isEmpty())
			sql=sql+" where location_id in(:ids) and is_adminmeter=1";
		Query q=sessionFactory.getCurrentSession().createQuery(sql);
		if(!iedids.isEmpty())
		 q.setParameterList("ids", iedids);		
		list=q.list();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<InstallationPoints> getInstallPoints() {
		List<InstallationPoints> points=new ArrayList();
		try {
			points=sessionFactory.getCurrentSession().createQuery("from InstallationPoints").list();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return points;
	}



	@Override
	public List<MeterMake> listMeterMakes() {
		// TODO Auto-generated method stub
		return  sessionFactory.getCurrentSession().createQuery("from MeterMake").list();
	}



	@Override
	public List<Meter> getUnmappedMeters() {
		List<Meter> meters=new ArrayList();
		try{
			meters=sessionFactory.getCurrentSession().createQuery("from Meter where location_id is null").list();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return meters;
	}



	@Override
	public void changeMeter(Meter meter, String oldMeter) {
		
		String sql="",location_id="",modem_serial_no="";
		try{	
			
			System.out.println("old meter -------------------"+oldMeter);
			Session ses=sessionFactory.getCurrentSession();	
			Connection con=ses.connection();
//-------------------getting location id from old meter--------------------------
			sql="select location_id,modem_serial_no from meter where meter_serial_no='"+oldMeter+"'";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			if(rs.next()){ 
				location_id=rs.getString(1);
				modem_serial_no=rs.getString(2);
			}
			
				
//----------------insert/update new meter details --------------------------------
			boolean isNewMeterExists=false;
			sql="select * from meter where meter_serial_no='"+meter.getMeter_sl_no()+"'";
			rs=con.prepareStatement(sql).executeQuery();
			if(rs.next()) isNewMeterExists=true;
			
			PreparedStatement ps=null;
			if(isNewMeterExists){ //update
				sql="UPDATE meter SET metermake=?,meterlocation=?,meter_type=?,modem_serial_no=?,meter_ctr=?,meter_ptr=?,adopted_ctr=?,adopted_ptr=?,mf=?,location_id=? WHERE meter_serial_no=? ";
				 ps=con.prepareStatement(sql);				
				ps.setString(1, meter.getMetermake());
				ps.setString(2, "7");
				ps.setString(3, meter.getMeter_type());
				ps.setString(4, modem_serial_no);
				ps.setString(5, meter.getMe_ct());
				ps.setString(6, meter.getMe_pt());
				ps.setString(7, meter.getAd_ct());
				ps.setString(8, meter.getAd_pt());
				ps.setString(9, meter.getMf());
				ps.setString(10, location_id);
				ps.setString(11, meter.getMeter_sl_no());
				
			}else{  // insert
				sql="INSERT INTO meter(meter_serial_no,metermake,meterlocation,meter_type,modem_serial_no,meter_ctr,meter_ptr,adopted_ctr,adopted_ptr,mf,location_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				 ps=con.prepareStatement(sql);
				ps.setString(1, meter.getMeter_sl_no());
				ps.setString(2, meter.getMetermake());
				ps.setString(3, "7");
				ps.setString(4, meter.getMeter_type());
				ps.setString(5, modem_serial_no);
				ps.setString(6, meter.getMe_ct());
				ps.setString(7, meter.getMe_pt());
				ps.setString(8, meter.getAd_ct());
				ps.setString(9, meter.getAd_pt());
				ps.setString(10, meter.getMf());
				ps.setString(11, location_id);
				
			}
			
			if(ps.executeUpdate()>0){  //deleting mapping from old meter
				sql="update meter set location_id=null,modem_serial_no=null where meter_serial_no='"+oldMeter+"'";
				con.prepareStatement(sql).executeUpdate();
			}
	       	
//------------updating the total_masterdata table
	  sql="delete from total_masterdata where meter_sl_no='"+oldMeter+"'";
	  Query q=ses.createSQLQuery(sql);
		if(q.executeUpdate()>0) System.out.println("Oldmeter is deleted from Total master ...");
	        
	 sql="REPLACE INTO total_masterdata(region,region_id,region_pid,circle,circle_id,circle_pid,division,division_id,division_pid,sd,sd_id,sd_pid,ss,ss_id,ss_pid,ptr,ptr_id,ptr_pid,meter_sl_no,meter_make,meter_type,meter_location,mf,location_id,modem_sl_no,modem_mobile_no,location_name,location_code,location_category,location_group,supply_voltage)"+
				 " (SELECT m.region,m.region_id,m.region_pid,m.circle,m.circle_id,m.circle_pid,m.division,m.division_id,m.division_pid,m.sd,m.sd_id,m.sd_pid,m.ss,m.ss_id,m.ss_pid,m.ptr,m.ptr_id,m.ptr_pid,"+
				" mt.meter_serial_no,mt.metermake,mt.meter_type,mt.meterlocation,mt.mf,mt.location_id,md.modem_serial_no,md.mobile_no,f.feedername,f.structure_code,f.feeder_type,f.feeder_group,f.voltage_level"+
				" FROM masterdata m  JOIN feeder f ON m.ptr_id=f.substationid"+
				 " JOIN meter mt ON mt.meter_serial_no='"+meter.getMeter_sl_no()+"' AND mt.location_id=f.feederid LEFT JOIN modem md ON mt.modem_serial_no=md.modem_serial_no)";
	
	System.out.println("--------------------->"+sql);
	 q=ses.createSQLQuery(sql);
	if(q.executeUpdate()>0) System.out.println("Total master record insertd...");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
