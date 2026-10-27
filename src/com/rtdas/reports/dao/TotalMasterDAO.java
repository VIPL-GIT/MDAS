package com.rtdas.reports.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.models.InstallationPoints;
import com.rtdas.common.models.Meter;
import com.rtdas.common.models.MeterMake;
import com.rtdas.meterdata.beans.MasterdataBean;
import com.rtdas.meterdata.beans.TotalMasterdataBean;

@Repository("totalMasterDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TotalMasterDAO {

	@Autowired
	private SessionFactory sf;
	
	public List<TotalMasterdataBean> getTotalMasterData(){ 
		List<TotalMasterdataBean> master=new ArrayList();
			try{
				
				Query q=sf.getCurrentSession().createQuery("from TotalMasterdataBean where location_id is not null");
				master=q.list();
			
			Map<String,String> locationMap=new HashMap();
			Map<String,String> makesMap=new HashMap();
			 q=sf.getCurrentSession().createQuery("from InstallationPoints");
			
			 List<InstallationPoints> locs=q.list();
			 for(InstallationPoints mk:locs){
				 locationMap.put(""+mk.getId(), mk.getMeter_type());
			 }
			 //----------
			 q=sf.getCurrentSession().createQuery("from MeterMake");
				
			 List<MeterMake> makes=q.list();
			 for(MeterMake mk:makes){
				 makesMap.put(""+mk.getMETERMAKEID(), mk.getMETERMAKENAME());
			 }
			 for(TotalMasterdataBean tdb:master){
				 if(locationMap.containsKey(tdb.getMeter_location()))
					 tdb.setMeter_location(locationMap.get(tdb.getMeter_location()));
				 if(makesMap.containsKey(tdb.getMeter_make()))
					 tdb.setMeter_make(makesMap.get(tdb.getMeter_make()));
			 }
            }catch(Exception e){
				e.printStackTrace();
			}
			return master;
	}
	
	public List<TotalMasterdataBean> getTotalMasterDataBysdID(List<Integer> ids){ 
		List<TotalMasterdataBean> master=new ArrayList();
			try{
				
				Query q=sf.getCurrentSession().createQuery("from TotalMasterdataBean where ptr_id in (:ids) and meter_sl_no is not null");
				q.setParameterList("ids", ids);
				master=q.list();
			
			Map<String,String> locationMap=new HashMap();
			Map<String,String> makesMap=new HashMap();
			 q=sf.getCurrentSession().createQuery("from InstallationPoints");
			
			 List<InstallationPoints> locs=q.list();
			 for(InstallationPoints mk:locs){
				 locationMap.put(""+mk.getId(), mk.getMeter_type());
			 }
			 //----------
			 q=sf.getCurrentSession().createQuery("from MeterMake");
				
			 List<MeterMake> makes=q.list();
			 for(MeterMake mk:makes){
				 makesMap.put(""+mk.getMETERMAKEID(), mk.getMETERMAKENAME());
			 }
			 for(TotalMasterdataBean tdb:master){
				 if(locationMap.containsKey(tdb.getMeter_location()))
					 tdb.setMeter_location(locationMap.get(tdb.getMeter_location()));
				 if(makesMap.containsKey(tdb.getMeter_make()))
					 tdb.setMeter_make(makesMap.get(tdb.getMeter_make()));
			 }
            }catch(Exception e){
				e.printStackTrace();
			}
			return master;
	}
	
	public List<TotalMasterdataBean> getTotalMasterDataBysdID(List<Integer> ids,int level){ 
		List<TotalMasterdataBean> master=new ArrayList();
			try{
				String sql="from TotalMasterdataBean ";
				if(level==1) sql=sql+" where region_id in (:ids) and meter_sl_no is not null";
				if(level==2) sql=sql+" where circle_id in (:ids) and meter_sl_no is not null";
				if(level==3) sql=sql+" where division_id in (:ids) and meter_sl_no is not null";
				if(level==4) sql=sql+" where sd_id in (:ids) and meter_sl_no is not null";
				if(level==5) sql=sql+" where ss_id in (:ids) and meter_sl_no is not null";
				if(level==6) sql=sql+" where ptr_id in (:ids) and meter_sl_no is not null";
				
				
				
				Query q=sf.getCurrentSession().createQuery(sql);
				q.setParameterList("ids", ids);
				master=q.list();
			
			Map<String,String> locationMap=new HashMap();
			Map<String,String> makesMap=new HashMap();
			 q=sf.getCurrentSession().createQuery("from InstallationPoints");
			
			 List<InstallationPoints> locs=q.list();
			 for(InstallationPoints mk:locs){
				 locationMap.put(""+mk.getId(), mk.getMeter_type());
			 }
			 //----------
			 q=sf.getCurrentSession().createQuery("from MeterMake");
				
			 List<MeterMake> makes=q.list();
			 for(MeterMake mk:makes){
				 makesMap.put(""+mk.getMETERMAKEID(), mk.getMETERMAKENAME());
			 }
			 for(TotalMasterdataBean tdb:master){
				 if(locationMap.containsKey(tdb.getMeter_location()))
					 tdb.setMeter_location(locationMap.get(tdb.getMeter_location()));
				 if(makesMap.containsKey(tdb.getMeter_make()))
					 tdb.setMeter_make(makesMap.get(tdb.getMeter_make()));
			 }
            }catch(Exception e){
				e.printStackTrace();
			}
			return master;
	}
	
	public Map<String,String> getDiscomIssues(){
		Map<String,String> map=new HashMap();
		try{
			Connection con=sf.getCurrentSession().connection();
			String sql="select meter_serial_no,issue from meter";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				map.put(rs.getString("meter_serial_no"),rs.getString("issue"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	public String updateMeterIssue(String meter,String issue){
		String result="";
		try{
			Connection con=sf.getCurrentSession().connection();
			String sql="update meter set issue='"+issue+"' where meter_serial_no='"+meter+"'";
			if(issue.length()<=2)
				sql="update meter set issue=null where meter_serial_no='"+meter+"'";
			if(con.prepareStatement(sql).executeUpdate()>0)
				result="Updated Successfully..";
			else 
				result="Error...";
		}catch(Exception e){
			result="Error...";
		}
		return result;
	}
	public List<TotalMasterdataBean> getTotalMasterDataBysdID_orderbyss(List<Integer> ids,int level){ 
		List<TotalMasterdataBean> master=new ArrayList();
			try{
				String sql="from TotalMasterdataBean ";
				if(level==1) sql=sql+" where region_id in (:ids) and meter_sl_no is not null";
				if(level==2) sql=sql+" where circle_id in (:ids) and meter_sl_no is not null";
				if(level==3) sql=sql+" where division_id in (:ids) and meter_sl_no is not null";
				if(level==4) sql=sql+" where sd_id in (:ids) and meter_sl_no is not null";
				if(level==5) sql=sql+" where ss_id in (:ids) and meter_sl_no is not null";
				if(level==6) sql=sql+" where ptr_id in (:ids) and meter_sl_no is not null";
				
				sql=sql+" order by ss,supply_voltage";
				
				Query q=sf.getCurrentSession().createQuery(sql);
				q.setParameterList("ids", ids);
				master=q.list();
			
			Map<String,String> locationMap=new HashMap();
			Map<String,String> makesMap=new HashMap();
			 q=sf.getCurrentSession().createQuery("from InstallationPoints");
			
			 List<InstallationPoints> locs=q.list();
			 for(InstallationPoints mk:locs){
				 locationMap.put(""+mk.getId(), mk.getMeter_type());
			 }
			 //----------
			 q=sf.getCurrentSession().createQuery("from MeterMake");
				
			 List<MeterMake> makes=q.list();
			 for(MeterMake mk:makes){
				 makesMap.put(""+mk.getMETERMAKEID(), mk.getMETERMAKENAME());
			 }
			 for(TotalMasterdataBean tdb:master){
				 if(locationMap.containsKey(tdb.getMeter_location()))
					 tdb.setMeter_location(locationMap.get(tdb.getMeter_location()));
				 if(makesMap.containsKey(tdb.getMeter_make()))
					 tdb.setMeter_make(makesMap.get(tdb.getMeter_make()));
			 }
            }catch(Exception e){
				e.printStackTrace();
			}
			return master;
	}
	
	public List<TotalMasterdataBean> getTotalMasterDataByssID(int ssid){ 
		List<TotalMasterdataBean> master=new ArrayList();
			try{
				
				Query q=sf.getCurrentSession().createQuery("from TotalMasterdataBean where ss_id="+ssid+" and meter_sl_no is not null");
				
				master=q.list();
			}catch(Exception e){
				
			}			
			return master;
	}
	
	public Map<String,String> getMetersKnoMap(List<Meter> mList){
		Map<String,String> map=new HashMap();
		try{
			if(!mList.isEmpty()){
				List<String> meters=new ArrayList();
				for(Meter mt:mList){
					meters.add(mt.getMeter_sl_no());
				}
				
				Query q=sf.getCurrentSession().createSQLQuery("SELECT meter_sl_no,location_code FROM  total_masterdata where meter_sl_no in (:meters)");
				q.setParameterList("meters", meters);
				
				List<Object[]> result=q.list();
				for(Object[] obj:result){
					map.put(obj[0].toString(), obj[1].toString());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return map;
		
	}
	
public Map<Integer,TotalMasterdataBean> getTotalMasterLocationMap(){
	Map<Integer,TotalMasterdataBean> map=new HashMap();
	List<TotalMasterdataBean> master=new ArrayList();
	try{
		
		Query q=sf.getCurrentSession().createQuery("from TotalMasterdataBean");
		master=q.list();
		
		for(TotalMasterdataBean tmb:master){
			map.put(tmb.getLocation_id(), tmb);
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return map;
}

public Map<Integer,MasterdataBean> getMasterLocationMap(){
	Map<Integer,MasterdataBean> map=new HashMap();
	List<MasterdataBean> master=new ArrayList();
	try{
		
		Query q=sf.getCurrentSession().createQuery("from MasterdataBean");
		master=q.list();
		
		for(MasterdataBean tmb:master){
			map.put(tmb.getSd_id(), tmb);
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return map;
}
public Map<String,Meter> getMeterMFMap(List<TotalMasterdataBean> meters){
	Map<String,Meter> mfmap=new HashMap();
	try{
		List<String> metersList=new ArrayList();
		for(TotalMasterdataBean tmb:meters){
			metersList.add(tmb.getMeter_sl_no());
		}
		
		Query q=sf.getCurrentSession().createQuery("From Meter where meter_sl_no in (:meters)");
		q.setParameterList("meters", metersList);
		List<Meter> mts=q.list();
		if(!mts.isEmpty()){
			for(Meter mt:mts){
				mfmap.put(mt.getMeter_sl_no(), mt);
			}
		}
		
	}catch(Exception e){
		
	}
	
	return mfmap;
}

}
