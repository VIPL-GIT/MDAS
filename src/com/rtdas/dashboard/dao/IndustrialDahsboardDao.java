package com.rtdas.dashboard.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.meterdata.models.FeederLiveStatus;

import utils.DBUtil;

@Repository("indlReportsDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class IndustrialDahsboardDao {

	@Autowired
	private SessionFactory sf;
	
	public String getDashLoadCurveValues_withMF(String date,String ids,int level){
		String values="",ag="",nonag="",tot="";
		Connection con=null;
		try{
			String sql="SELECT category,SUM(h0)/1000,SUM(h1)/1000,SUM(h2)/1000,SUM(h3)/1000,SUM(h4)/1000,SUM(h5)/1000,SUM(h6)/1000,SUM(h7)/1000,SUM(h8)/1000,SUM(h9)/1000,SUM(h10)/1000,SUM(h11)/1000,SUM(h12)/1000,SUM(h13)/1000,SUM(h14)/1000,SUM(h15)/1000,SUM(h16)/1000,SUM(h17)/1000,SUM(h18)/1000,SUM(h19)/1000,SUM(h20)/1000,SUM(h21)/1000,SUM(h22)/1000,SUM(h23)/1000 FROM"
					//+ "(SELECT (l.h0*t.mf*l.demand_integration_period) AS  h0,(l.h1*t.mf*l.demand_integration_period) AS  h1,(l.h2*t.mf*l.demand_integration_period) AS  h2,(l.h3*t.mf*l.demand_integration_period) AS  h3,(l.h4*t.mf*l.demand_integration_period) AS  h4,(l.h5*t.mf*l.demand_integration_period) AS  h5,(l.h6*t.mf*l.demand_integration_period) AS  h6,(l.h7*t.mf*l.demand_integration_period) AS  h7,(l.h8*t.mf*l.demand_integration_period) AS  h8,(l.h9*t.mf*l.demand_integration_period) AS  h9,(l.h10*t.mf*l.demand_integration_period) AS  h10,(l.h11*t.mf*l.demand_integration_period) AS  h11,(l.h12*t.mf*l.demand_integration_period) AS  h12,(l.h13*t.mf*l.demand_integration_period) AS  h13,(l.h14*t.mf*l.demand_integration_period) AS  h14,(l.h15*t.mf*l.demand_integration_period) AS  h15,(l.h16*t.mf*l.demand_integration_period) AS  h16,(l.h17*t.mf*l.demand_integration_period) AS  h17,(l.h18*t.mf*l.demand_integration_period) AS  h18,(l.h19*t.mf*l.demand_integration_period) AS  h19,(l.h20*t.mf*l.demand_integration_period) AS  h20,(l.h21*t.mf*l.demand_integration_period) AS  h21,(l.h22*t.mf*l.demand_integration_period) AS  h22,(l.h23*t.mf*l.demand_integration_period) AS  h23,t.location_category FROM analyitics_houly_loadprofile_kwh l,total_masterdata t "
					+ "(SELECT (l.h0*t.mf) AS  h0,(l.h1*t.mf) AS  h1,(l.h2*t.mf) AS  h2,(l.h3*t.mf) AS  h3,(l.h4*t.mf) AS  h4,(l.h5*t.mf) AS  h5,(l.h6*t.mf) AS  h6,(l.h7*t.mf) AS  h7,(l.h8*t.mf) AS  h8,(l.h9*t.mf) AS  h9,(l.h10*t.mf) AS  h10,(l.h11*t.mf) AS  h11,(l.h12*t.mf) AS  h12,(l.h13*t.mf) AS  h13,(l.h14*t.mf) AS  h14,(l.h15*t.mf) AS  h15,(l.h16*t.mf) AS  h16,(l.h17*t.mf) AS  h17,(l.h18*t.mf) AS  h18,(l.h19*t.mf) AS  h19,(l.h20*t.mf) AS  h20,(l.h21*t.mf) AS  h21,(l.h22*t.mf) AS  h22,(l.h23*t.mf) AS  h23,t.category FROM analyitics_houly_loadprofile_kwh l,total_masterdata t "
					+ "WHERE l.comm_date='"+date+"' AND l.meter_serial_no=t.meter_sl_no  ";
							
					if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
					if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
					if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
					if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
					if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
					if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
				 sql=sql+ ")AS t GROUP BY category";
				 
				 System.out.println("Query for Dash load curve : "+sql);
			 con=new DBUtil().getConnection();
			 ResultSet rs=con.prepareStatement(sql).executeQuery();
			 while(rs.next()){
				 if(rs.getString(1)!=null)
				 if(rs.getString(1).equalsIgnoreCase("INDUSTRIAL")) nonag=nonag+","+rs.getDouble(2)+","+rs.getDouble(3)+","+rs.getDouble(4)+","+rs.getDouble(5)+","+rs.getDouble(6)+","+rs.getDouble(7)+","+rs.getDouble(8)+","+rs.getDouble(9)+","+rs.getDouble(10)+","+rs.getDouble(11)+","+rs.getDouble(12)+","+rs.getDouble(13)+","+rs.getDouble(14)+","+rs.getDouble(15)+","+rs.getDouble(16)+","+rs.getDouble(17)+","+rs.getDouble(18)+","+rs.getDouble(19)+","+rs.getDouble(20)+","+rs.getDouble(21)+","+rs.getDouble(22)+","+rs.getDouble(23)+","+rs.getDouble(24)+","+rs.getDouble(25);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		if(nonag.startsWith(",")) nonag=nonag.substring(1);
		
		values=nonag;
		
		System.out.println("load values---------"+values);
		return values;
	}
	
	public String getDashSustainedPowerFailureValues(String curmonth,String ids,int level){
		String values="",ag="",nonag="";
		Connection con=null;
		try{
			//String sql="SELECT location_category,AVG(d1),AVG(d2),AVG(d3),AVG(d4),AVG(d5),AVG(d6),AVG(d7),AVG(d8),AVG(d9),AVG(d10),AVG(d11),AVG(d12),AVG(d13),AVG(d14),AVG(d15),AVG(d16),AVG(d17),AVG(d18),AVG(d19),AVG(d20),AVG(d21),AVG(d22),AVG(d23),AVG(d24),AVG(d25),AVG(d26),AVG(d27),AVG(d28),AVG(d29),AVG(d30),AVG(d31)"
			String sql="SELECT category,sum(d1),sum(d2),sum(d3),sum(d4),sum(d5),sum(d6),sum(d7),sum(d8),sum(d9),sum(d10),sum(d11),sum(d12),sum(d13),sum(d14),sum(d15),sum(d16),sum(d17),sum(d18),sum(d19),sum(d20),sum(d21),sum(d22),sum(d23),sum(d24),sum(d25),sum(d26),sum(d27),sum(d28),sum(d29),sum(d30),sum(d31)"
					+ " FROM (SELECT o.*,t.category FROM outage_power_fail_interrupts_sustained o,total_masterdata t "
					+ " WHERE o.month='"+curmonth+"' AND o.meter_serial_no=t.meter_sl_no ";
							
					if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
					if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
					if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
					if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
					if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
					if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
				 sql=sql+ ")AS t GROUP BY category";
				 
				 System.out.println("Query for Dash Sustained power failures : "+sql);
			 con=new DBUtil().getConnection();
			 ResultSet rs=con.prepareStatement(sql).executeQuery();
			 while(rs.next()){
				 if(rs.getString(1)!=null)
				  if(rs.getString(1).equalsIgnoreCase("INDUSTRIAL")) nonag=nonag+","+rs.getDouble(2)+","+rs.getDouble(3)+","+rs.getDouble(4)+","+rs.getDouble(5)+","+rs.getDouble(6)+","+rs.getDouble(7)+","+rs.getDouble(8)+","+rs.getDouble(9)+","+rs.getDouble(10)+","+rs.getDouble(11)+","+rs.getDouble(12)+","+rs.getDouble(13)+","+rs.getDouble(14)+","+rs.getDouble(15)+","+rs.getDouble(16)+","+rs.getDouble(17)+","+rs.getDouble(18)+","+rs.getDouble(19)+","+rs.getDouble(20)+","+rs.getDouble(21)+","+rs.getDouble(22)+","+rs.getDouble(23)+","+rs.getDouble(24)+","+rs.getDouble(25)+","+rs.getDouble(26)+","+rs.getDouble(27)+","+rs.getDouble(28)+","+rs.getDouble(29)+","+rs.getDouble(30)+","+rs.getDouble(31)+","+rs.getDouble(32);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		values=nonag.substring(1);
		return values;
	}
	public String getDashTransientPowerFailureValues(String curmonth,String ids,int level){
		String values="",ag="",nonag="";
		Connection con=null;
		try{
			//String sql="SELECT location_category,AVG(d1),AVG(d2),AVG(d3),AVG(d4),AVG(d5),AVG(d6),AVG(d7),AVG(d8),AVG(d9),AVG(d10),AVG(d11),AVG(d12),AVG(d13),AVG(d14),AVG(d15),AVG(d16),AVG(d17),AVG(d18),AVG(d19),AVG(d20),AVG(d21),AVG(d22),AVG(d23),AVG(d24),AVG(d25),AVG(d26),AVG(d27),AVG(d28),AVG(d29),AVG(d30),AVG(d31)"
			String sql="SELECT t.category,sum(d1),sum(d2),sum(d3),sum(d4),sum(d5),sum(d6),sum(d7),sum(d8),sum(d9),sum(d10),sum(d11),sum(d12),sum(d13),sum(d14),sum(d15),sum(d16),sum(d17),sum(d18),sum(d19),sum(d20),sum(d21),sum(d22),sum(d23),sum(d24),sum(d25),sum(d26),sum(d27),sum(d28),sum(d29),sum(d30),sum(d31)"
					+ " FROM (SELECT o.*,t.category FROM outage_power_fail_interrupts_transiant o,total_masterdata t "
					+ " WHERE o.month='"+curmonth+"'AND o.meter_serial_no=t.meter_sl_no ";
							
					if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
					if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
					if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
					if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
					if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
					if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
				 sql=sql+ ")AS t GROUP BY category";
				 
				 System.out.println("Query for Dash Transient power failures : "+sql);
			 con=new DBUtil().getConnection();
			 ResultSet rs=con.prepareStatement(sql).executeQuery();
			 while(rs.next()){
				 if(rs.getString(1)!=null)
				 if(rs.getString(1).equalsIgnoreCase("INDUSTRIAL")) nonag=nonag+","+rs.getDouble(2)+","+rs.getDouble(3)+","+rs.getDouble(4)+","+rs.getDouble(5)+","+rs.getDouble(6)+","+rs.getDouble(7)+","+rs.getDouble(8)+","+rs.getDouble(9)+","+rs.getDouble(10)+","+rs.getDouble(11)+","+rs.getDouble(12)+","+rs.getDouble(13)+","+rs.getDouble(14)+","+rs.getDouble(15)+","+rs.getDouble(16)+","+rs.getDouble(17)+","+rs.getDouble(18)+","+rs.getDouble(19)+","+rs.getDouble(20)+","+rs.getDouble(21)+","+rs.getDouble(22)+","+rs.getDouble(23)+","+rs.getDouble(24)+","+rs.getDouble(25)+","+rs.getDouble(26)+","+rs.getDouble(27)+","+rs.getDouble(28)+","+rs.getDouble(29)+","+rs.getDouble(30)+","+rs.getDouble(31)+","+rs.getDouble(32);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		values=nonag.substring(1);
		return values;
	}
	
	public String getDashOverLoad(String ids,int level,int capacity){
		int ol=0;
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			Map<String,String> conductorMap=new HashMap();
			Map<String,Integer> valsMap=new HashMap();
			valsMap.put("Rabbit", 148);
			valsMap.put("Weasel", 100);
			valsMap.put("Squirrel", 70);
			valsMap.put("Raccoon", 197);
			valsMap.put("Dog", 254);
			valsMap.put("Panther", 254);
			
			String sql="SELECT feederid,conductor FROM feeder";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getString(2)!=null)
				  conductorMap.put(rs.getString(1), rs.getString(2));
			}
			 sql="SELECT t.location_id,f.imf,f.lr,f.ly,f.lb FROM feeder_live_status f, total_masterdata t WHERE f.meter_serial_no=t.meter_sl_no ";
					
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
			sql=sql+ "AND t.category='INDUSTRIAL'";
			
			//System.out.println("--------------------"+sql);
			 rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				double curr=Math.max(Math.max(rs.getDouble("lr"),rs.getDouble("ly")),rs.getDouble("lb"));
				curr=curr*rs.getDouble("imf");
			    if(conductorMap.containsKey(rs.getString("location_id"))){
			    	String cType=conductorMap.get(rs.getString("location_id"));
			    	if(valsMap.containsKey(cType)){
			    		int cVal=valsMap.get(cType);
			    		
			    		if(curr>=cVal) ol++;
			    	}
			    }
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return ol+"";
	}
	
	public Map<String,FeederLiveStatus> getDashOverLoadDetails(String ids,int level,int capacity){
		int ol=0;
		Connection con=null;
		Map<String,FeederLiveStatus> map=new HashMap();
		try{
			con=new DBUtil().getConnection();
			DecimalFormat twodec=new DecimalFormat("#0.00");
			
			Map<String,String> conductorMap=new HashMap();
			Map<String,Integer> valsMap=new HashMap();
			valsMap.put("Rabbit", 148);
			valsMap.put("Weasel", 100);
			valsMap.put("Squirrel", 70);
			valsMap.put("Raccoon", 197);
			valsMap.put("Dog", 254);
			valsMap.put("Panther", 254);
			
			String sql="SELECT feederid,conductor FROM feeder";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getString(2)!=null)
				  conductorMap.put(rs.getString(1), rs.getString(2));
			}
			 sql="SELECT t.location_id,f.imf,f.lr,f.ly,f.lb,f.meter_serial_no FROM feeder_live_status f, total_masterdata t WHERE f.meter_serial_no=t.meter_sl_no ";
					
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
			sql=sql+ "AND t.category='INDUSTRIAL'";
			
			//System.out.println("--------------------"+sql);
			 rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				double curr=Math.max(Math.max(rs.getDouble("lr"),rs.getDouble("ly")),rs.getDouble("lb"));
				curr=curr*rs.getDouble("imf");
			    if(conductorMap.containsKey(rs.getString("location_id"))){
			    	String cType=conductorMap.get(rs.getString("location_id"));
			    	if(valsMap.containsKey(cType)){
			    		int cVal=valsMap.get(cType);
			    		
			    		if(curr>=cVal) {
			    			FeederLiveStatus fls=new FeederLiveStatus();
							fls.setMeter_serial_no(rs.getString("meter_serial_no"));
//							fls.setLr(rs.getDouble("lr"));
//							fls.setLy(rs.getDouble("ly"));
//							fls.setLb(rs.getDouble("lb"));	
							fls.setLr(Double.parseDouble(twodec.format(curr)));
							fls.setImf(rs.getString("imf"));
							fls.setStatus(cType); // to get the conductor type in view page
							map.put(rs.getString("meter_serial_no"), fls);
			    		}
			    	}
			    }
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return map;
	}
	public String getDashOverunderVoltages(String ids,int level,int capacity,String type){
		int voltage=0;
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			
			
			String sql="SELECT f.vmf,f.vr,f.vy,f.vb FROM feeder_live_status f, total_masterdata t WHERE f.meter_serial_no=t.meter_sl_no ";
					
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
			sql=sql+ "AND t.category='INDUSTRIAL'";

			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				double vol=Math.max(Math.max(rs.getDouble("vr"),rs.getDouble("vy")),rs.getDouble("vb"));
				vol=vol*1.732*rs.getDouble("vmf");
			
				double percent=(((capacity*1000)-vol)/(capacity*1000))*100;
				
				if(type.equals("over")){
					if(percent<0){
						percent=Math.abs(percent);
						if(percent>6 && percent<20) {
					        voltage++;
						}
				}
				}
                if(type.equals("under")){
                	if(percent>8 && percent <9) voltage++;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return voltage+"";
	}
	
	public Map<String,FeederLiveStatus> getDashLowPFDetails(String ids,int level,int capacity){
		int lowPF=0;
		Connection con=null;
		Map<String,FeederLiveStatus> map=new HashMap();
		try{
			con=new DBUtil().getConnection();
			
			
			String sql="SELECT f.meter_serial_no,f.pfavg,f.pfr,f.pfy,f.pfb FROM feeder_live_status f, total_masterdata t WHERE f.meter_serial_no=t.meter_sl_no ";
					
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
			sql=sql+ "AND t.category='INDUSTRIAL'";
			
			//System.out.println("--------------------"+sql);
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(Math.abs(rs.getDouble(2))<0.8){
					FeederLiveStatus fls=new FeederLiveStatus();
					fls.setMeter_serial_no(rs.getString("meter_serial_no"));
					fls.setPfr(rs.getDouble("pfr"));
					fls.setPfy(rs.getDouble("pfy"));
					fls.setPfb(rs.getDouble("pfb"));
					fls.setPfavg(rs.getDouble("pfavg"));
					map.put(rs.getString("meter_serial_no"), fls);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return map;
	}
	
	public Map<String,FeederLiveStatus> getDashOverunderVoltagesDetails(String ids,int level,int capacity,String type){
		int voltage=0;
		Connection con=null;
		Map<String,FeederLiveStatus> map=new HashMap();
		try{
			con=new DBUtil().getConnection();
			DecimalFormat twodec=new DecimalFormat("#.##");
			
			String sql="SELECT f.meter_serial_no,f.vmf,f.vr,f.vy,f.vb FROM feeder_live_status f, total_masterdata t WHERE f.meter_serial_no=t.meter_sl_no ";
					
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
			sql=sql+ "AND t.category='INDUSTRIAL'";
			
			//System.out.println("--------------------"+sql);
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				double vol=Math.max(Math.max(rs.getDouble("vr"),rs.getDouble("vy")),rs.getDouble("vb"));
				vol=vol*1.732*rs.getDouble("vmf");
			
				double percent=(((capacity*1000)-vol)/(capacity*1000))*100;
				
				if(type.equals("over")){
					if(percent<0){
					percent=Math.abs(percent);
					if(percent>6 && percent<20) {
						FeederLiveStatus fls=new FeederLiveStatus();
						fls.setMeter_serial_no(rs.getString("meter_serial_no"));
						fls.setVr(Double.parseDouble(twodec.format(vol/1000)));
//						fls.setVy(rs.getDouble("vy"));
//						fls.setVb(rs.getDouble("vb"));
						fls.setVmf(rs.getString("vmf"));
						map.put(rs.getString("meter_serial_no"), fls);
					
					}
					}
				}
                if(type.equals("under")){
                	if(percent>8 && percent < 9) {
                		FeederLiveStatus fls=new FeederLiveStatus();
						fls.setMeter_serial_no(rs.getString("meter_serial_no"));
						fls.setVr(Double.parseDouble(twodec.format(vol/1000)));
						fls.setVmf(rs.getString("vmf"));
						map.put(rs.getString("meter_serial_no"), fls);
                	}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return map;
	}
	
	public Map<String,String> getFeeerTrippingDetails(String meter){
		Map<String,String> map=new LinkedHashMap();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	        String curMonth=sdf.format(new java.util.Date());
	        int curDay=Integer.parseInt(curMonth.split("-")[0]);
	        
	        curMonth=curMonth.substring(curMonth.indexOf("-"));
	        
	        String sql="select * from outage_power_fail_interrupts_sustained where meter_serial_no='"+meter+"' and month='"+curMonth+"'";
	        ResultSet rs=con.prepareStatement(sql).executeQuery();
	        if(rs.next()){
	        	for(int i=1;i<curDay;i++){
	        		map.put(i+curMonth, rs.getString("d"+i));
	        	}
	        }
	        
	         sql="select * from outage_power_fail_interrupts_transiant where meter_serial_no='"+meter+"' and month='"+curMonth+"'";
	         rs=con.prepareStatement(sql).executeQuery();
	        if(rs.next()){
	        	for(int i=1;i<curDay;i++){
	        		if(map.containsKey(i+curMonth)){
	        			String ss=map.get(i+curMonth)+","+rs.getString("d"+i);
	        			map.put(i+curMonth, ss);
	        		}else{
	        		  map.put(i+curMonth, ","+rs.getString("d"+i));
	        		}
	        	}
	        }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return map;
	}
	
}
