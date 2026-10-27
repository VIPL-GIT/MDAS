package com.rtdas.dashboard.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rtdas.reports.beans.DashCriticalTampersBean;
import com.rtdas.reports.beans.DashEnergyComparisonBean;
import com.rtdas.reports.beans.DashMeterDataAnalysisBean;

import utils.DBUtil;

@Repository("dashDao")
public class ExceptionalReportsDAO {

	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		
	public DashMeterDataAnalysisBean getDashMeterDataAnalysisBean(List<String> meters){
		
		DashMeterDataAnalysisBean dmdab=new DashMeterDataAnalysisBean();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			Map<String,String> lowPf_meters=new HashMap();
			Map<String,String> mdViolation_meters=new HashMap();
			Map<String,String> lagPf_meters=new HashMap();
			Map<String,String> leadPf_meters=new HashMap();
			
			String currentDate=sdf.format(new java.util.Date());
			String year=currentDate.split("-")[2];
			String month="m"+Integer.parseInt(currentDate.split("-")[1]);
//-------------------------------------------------------------------------------------------------------
			String meterStr="";
			if(!meters.isEmpty()){
				for(String mt:meters){
				  meterStr=meterStr+",'"+mt+"'";
				}
				meterStr=meterStr.substring(1);
			}
//---------------------------------------------------------------------------------------------------------			
			String sql="select meter_serial_no,"+month+",low_pf,pf_value,md_voilation,md_value from dashboard_monthly_consumption";
			if(!meters.isEmpty()) sql=sql+" where meter_serial_no in ("+meterStr+") and year='"+year+"'";
		
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while (rs.next()){
//				if(rs.getInt("low_pf")>0 && rs.getInt("pf_value")>0 ){
//					lowPf_meters.put(rs.getString("meter_serial_no"),rs.getString("pf_value"));
//				}
				if(rs.getInt("md_voilation")==1){
					mdViolation_meters.put(rs.getString("meter_serial_no"),rs.getString("md_value"));
				}
			}//while
			
//-----------------------------------low pf -------------------------------------------------------------
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
			String bill_month=sdf.format(new java.util.Date());
			bill_month=bill_month.substring(0, bill_month.lastIndexOf("-"));
			
			 sql="SELECT * FROM analytics_monthly_readings ";
			if(!meters.isEmpty()) sql=sql+" where meter_serial_no in ("+meterStr+") and billing_timestamp like '%"+bill_month+"%'";
		
			//System.out.println("low pf ------------"+sql);
			 rs=con.prepareStatement(sql).executeQuery();
			while (rs.next()){
				try{
				if( rs.getString("systempf")!=null && !rs.getString("systempf").equals("?") && rs.getString("systempf").length()>0 ){
					if(rs.getFloat("systempf")>0.0 && rs.getFloat("systempf")<0.8)
					   lowPf_meters.put(rs.getString("meter_serial_no"),rs.getString("systempf"));
				}
				}catch(Exception e){}
				
			}//while
//----------------------for lag and lead pf --------------------------------------------
			 sql="SELECT meter_serial_no,phasesequence,pfavg FROM feeder_live_status";
			 if(!meters.isEmpty()) sql=sql+" where meter_serial_no in ("+meterStr+")";
			 
			 rs=con.prepareStatement(sql).executeQuery();
			while (rs.next()){
				if(rs.getString("phasesequence")!=null){
				if(rs.getString("phasesequence").toLowerCase().contains("lag")){
					lagPf_meters.put(rs.getString("meter_serial_no"),rs.getString("pfavg"));
				}
				if(rs.getString("phasesequence").toLowerCase().contains("lead")){
					leadPf_meters.put(rs.getString("meter_serial_no"),rs.getString("pfavg"));
				}
				}
			}//while
			
			dmdab.setLowPf_meters(lowPf_meters);
			dmdab.setMdViolation_meters(mdViolation_meters);
			dmdab.setPf_lag_meters(lagPf_meters);
			dmdab.setPf_lead_meters(leadPf_meters);
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
		
		return dmdab;
	}
	
	public DashEnergyComparisonBean getDashEnergyComparisonBean(List<String> meters){
		DashEnergyComparisonBean decb=new DashEnergyComparisonBean();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			Map<String,String> incr_0_10_meters= new HashMap();
			Map<String,String> incr_10_25_meters= new HashMap();
			Map<String,String> incr_25_100_meters= new HashMap();
			Map<String,String> incr_gr_100_meters= new HashMap();
			Map<String,String> decr_0_10_meters= new HashMap();
			Map<String,String> decr_10_25_meters= new HashMap();
			Map<String,String> decr_25_100_meters= new HashMap();
			Map<String,String> decr_gr_100_meters= new HashMap();
//-------------------------------------------------------------------------------------------------------
			String meterStr="";
			if(!meters.isEmpty()){
				for(String mt:meters){
				  meterStr=meterStr+",'"+mt+"'";
				}
				meterStr=meterStr.substring(1);
			}
//---------------------------------------------------------------------------------------------------------				
			String currentDate=sdf.format(new java.util.Date());
			String year=currentDate.split("-")[2];
			String month="m"+Integer.parseInt(currentDate.split("-")[1]);
			
			String sql="select meter_serial_no,"+month+" from dashboard_monthly_consumption_precentage";
			if(!meters.isEmpty()) sql=sql+" where meter_serial_no in ("+meterStr+") and year='"+year+"'";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while (rs.next()){
				if(rs.getDouble(2)<0){
					if(Math.abs(rs.getDouble(2))>=0.0 && Math.abs(rs.getDouble(2))<=10.0)   decr_0_10_meters.put(rs.getString(1), rs.getString(2));
					if(Math.abs(rs.getDouble(2))>10.0 && Math.abs(rs.getDouble(2))<= 25.0) decr_10_25_meters.put(rs.getString(1), rs.getString(2));
					if(Math.abs(rs.getDouble(2))>25.0 && Math.abs(rs.getDouble(2))<= 100.0)decr_25_100_meters.put(rs.getString(1), rs.getString(2));
					if(Math.abs(rs.getDouble(2))>100.0 )                         decr_gr_100_meters.put(rs.getString(1), rs.getString(2));
				}else{
					if(rs.getDouble(2)>=0.0 && rs.getDouble(2)<=10.0) incr_0_10_meters.put(rs.getString(1), rs.getString(2));
					if(rs.getDouble(2)>10.0 && rs.getDouble(2)<= 25.0) incr_10_25_meters.put(rs.getString(1), rs.getString(2));
					if(rs.getDouble(2)>25.0 && rs.getDouble(2)<= 100.0) incr_25_100_meters.put(rs.getString(1), rs.getString(2));
					if(rs.getDouble(2)>100.0 ) incr_gr_100_meters.put(rs.getString(1), rs.getString(2));
				}
				
			}//while
			
			decb.setDecr_0_10_meters(decr_0_10_meters);
			decb.setDecr_10_25_meters(decr_10_25_meters);
			decb.setDecr_25_100_meters(decr_25_100_meters);
			decb.setDecr_gr_100_meters(decr_gr_100_meters);
			decb.setIncr_0_10_meters(incr_0_10_meters);
			decb.setIncr_10_25_meters(incr_10_25_meters);
			decb.setIncr_25_100_meters(incr_25_100_meters);
			decb.setIncr_gr_100_meters(incr_gr_100_meters);
			
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
		
		return decb;
	}
	
	public Map<String,List<DashCriticalTampersBean>> getDashCriticalTampersBean(List<String> meters,String month){
		Map<String,List<DashCriticalTampersBean>> bean=new HashMap();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			month=month.split("-")[1]+"-"+month.split("-")[0];
//-------------------------------------------------------------------------------------------------------
//			String meterStr="";
//			if(!meters.isEmpty()){
//				for(String mt:meters){
//				  meterStr=meterStr+",'"+mt+"'";
//				}
//				meterStr=meterStr.substring(1);
//			}
//---------------------------------------------------------------------------------------------------------	
			String sql="SELECT * FROM events_summary WHERE final_status=1 and event_date like '%"+month+"'";
			//if(!meters.isEmpty()) sql=sql+" and meter_serial_no in ("+meterStr+")";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(meters.contains(rs.getString("meter_serial_no"))){
				String name=rs.getString("name");
				if(name.contains("CT open")) name="CT open";
				if(bean.containsKey(name)){
					List<DashCriticalTampersBean>  list=bean.get(name);
					DashCriticalTampersBean dct=new DashCriticalTampersBean();
					dct.setMeter_serial_no(rs.getString("meter_serial_no"));
					dct.setEvent_date(rs.getString("event_date"));
					dct.setLast_o_timestamp(rs.getString("last_o_timestamp"));
					dct.setLast_r_timestamp(rs.getString("last_r_timestamp"));
					
					if(name.contains("CT open")) dct.setEvent_name(rs.getString("name"));
					
					list.add(dct);
					bean.put(name, list);
				}else{
					List<DashCriticalTampersBean>  list=new ArrayList();
					DashCriticalTampersBean dct=new DashCriticalTampersBean();
					dct.setMeter_serial_no(rs.getString("meter_serial_no"));
					dct.setEvent_date(rs.getString("event_date"));
					dct.setLast_o_timestamp(rs.getString("last_o_timestamp"));
					dct.setLast_r_timestamp(rs.getString("last_r_timestamp"));
					
					if(name.contains("CT open")) dct.setEvent_name(rs.getString("name"));
					
					list.add(dct);
					bean.put(name, list);
				}
			}//if contains meter
			}//while
			
		}catch(Exception e){}
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return bean;
		
	}
	public Map<String,List<DashCriticalTampersBean>> getDashTampersGr10mins(List<String> meters,String month){
		Map<String,List<DashCriticalTampersBean>> bean=new HashMap();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
//			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
//			String curdate=sdf.format(new java.util.Date().getTime()-24*60*60*1000);
//			curdate=curdate.substring(curdate.indexOf("-")+1);
			
			month=month.split("-")[1]+"-"+month.split("-")[0];
//-------------------------------------------------------------------------------------------------------
//			String meterStr="";
//			if(!meters.isEmpty()){
//				for(String mt:meters){
//				  meterStr=meterStr+",'"+mt+"'";
//				}
//				meterStr=meterStr.substring(1);
//			}
//---------------------------------------------------------------------------------------------------------
			//curdate="24-02-2021";
			//String sql="SELECT * FROM events_summary WHERE event_date='"+curdate+"' and duration >10*60 ";
			String sql="SELECT * FROM events_summary WHERE  duration >10*60 and event_date like '%"+month+"%'";
			//if(!meters.isEmpty()) sql=sql+" and meter_serial_no in ("+meterStr+")";
			
			//System.out.println("------------------------------->"+sql);
			
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(meters.contains(rs.getString("meter_serial_no"))){
				String name=rs.getString("name");
				if(name.contains("CT open")) name="CT open";
				if(bean.containsKey(name)){
					List<DashCriticalTampersBean>  list=bean.get(name);
					DashCriticalTampersBean dct=new DashCriticalTampersBean();
					dct.setMeter_serial_no(rs.getString("meter_serial_no"));
					dct.setEvent_date(rs.getString("event_date"));
					dct.setLast_o_timestamp(rs.getString("last_o_timestamp"));
					dct.setLast_r_timestamp(rs.getString("last_r_timestamp"));
					
					if(name.contains("CT open")) dct.setEvent_name(rs.getString("name"));
					
					list.add(dct);
					bean.put(name, list);
				}else{
					List<DashCriticalTampersBean>  list=new ArrayList();
					DashCriticalTampersBean dct=new DashCriticalTampersBean();
					dct.setMeter_serial_no(rs.getString("meter_serial_no"));
					dct.setEvent_date(rs.getString("event_date"));
					dct.setLast_o_timestamp(rs.getString("last_o_timestamp"));
					dct.setLast_r_timestamp(rs.getString("last_r_timestamp"));
					
					if(name.contains("CT open")) dct.setEvent_name(rs.getString("name"));
					
					list.add(dct);
					bean.put(name, list);
				}
			}//if contains meter
			}//while
			
		}catch(Exception e){}
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return bean;
		
	}
	
	public Map<String,List<DashCriticalTampersBean>> getDashTamperEventsBean(List<String> meters){
		Map<String,List<DashCriticalTampersBean>> bean=new HashMap();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			//-------------------------------------------------------------------------------------------------------
						String meterStr="";
						if(!meters.isEmpty()){
							for(String mt:meters){
							  meterStr=meterStr+",'"+mt+"'";
							}
							meterStr=meterStr.substring(1);
						}
			//---------------------------------------------------------------------------------------------------------
						String sql="SELECT * FROM feeder_live_status where ct_status LIKE '%CURRENT MISSING%'" ;
						if(!meters.isEmpty()) sql=sql+" and meter_serial_no in ("+meterStr+")";
						ResultSet rs=con.prepareStatement(sql).executeQuery();
						List<DashCriticalTampersBean>  ctlist=new ArrayList();
						while(rs.next()){
							String name=rs.getString("ct_status");								
							DashCriticalTampersBean dct=new DashCriticalTampersBean();
							dct.setMeter_serial_no(rs.getString("meter_serial_no"));
							dct.setEvent_date(rs.getString("meter_rtc"));																
							dct.setEvent_name(name);								
							ctlist.add(dct);
						}//while
						bean.put("CURRENT MISSING", ctlist);
						rs.close();
						
//-----------------------------
						 sql="SELECT * FROM feeder_live_status where pt_status LIKE '%SINGLE PHASE PT SUPPLY%'" ;
						if(!meters.isEmpty()) sql=sql+" and meter_serial_no in ("+meterStr+")";
						 rs=con.prepareStatement(sql).executeQuery();
						List<DashCriticalTampersBean>  sphptlist=new ArrayList();
						while(rs.next()){
							String name=rs.getString("pt_status");								
							DashCriticalTampersBean dct=new DashCriticalTampersBean();
							dct.setMeter_serial_no(rs.getString("meter_serial_no"));
							dct.setEvent_date(rs.getString("meter_rtc"));																
							dct.setEvent_name(name);								
							sphptlist.add(dct);
						}//while
						bean.put("SINGLE PHASE PT SUPPLY", sphptlist);
						rs.close();
//------------------------------
						sql="SELECT * FROM feeder_live_status where pt_status='TWO PHASE PT SUPPLY'" ;
						if(!meters.isEmpty()) sql=sql+" and meter_serial_no in ("+meterStr+")";
						
						 rs=con.prepareStatement(sql).executeQuery();
						List<DashCriticalTampersBean>  tphptlist=new ArrayList();
						while(rs.next()){
							String name=rs.getString("pt_status");								
							DashCriticalTampersBean dct=new DashCriticalTampersBean();
							dct.setMeter_serial_no(rs.getString("meter_serial_no"));
							dct.setEvent_date(rs.getString("meter_rtc"));																
							dct.setEvent_name(name);								
							tphptlist.add(dct);
						}//while
						bean.put("TWO PHASE PT SUPPLY", tphptlist);
						rs.close();
						//System.out.println("2ph -------------------->"+tphptlist.size());
//------------------------------
						sql="SELECT * FROM feeder_live_status where pt_status='LOW VOLTAGE'" ;
						if(!meters.isEmpty()) sql=sql+" and meter_serial_no in ("+meterStr+")";
						
						 rs=con.prepareStatement(sql).executeQuery();
						List<DashCriticalTampersBean>  lvlist=new ArrayList();
						while(rs.next()){
							String name=rs.getString("pt_status");								
							DashCriticalTampersBean dct=new DashCriticalTampersBean();
							dct.setMeter_serial_no(rs.getString("meter_serial_no"));
							dct.setEvent_date(rs.getString("meter_rtc"));																
							dct.setEvent_name(name);								
							lvlist.add(dct);
						}//while
						bean.put("LOW VOLTAGE", lvlist);
						rs.close();						
//---------------------------------
						sql="SELECT * FROM feeder_live_status where pt_observation LIKE '%IMBALANCE%'" ;
						if(!meters.isEmpty()) sql=sql+" and meter_serial_no in ("+meterStr+")";
						 rs=con.prepareStatement(sql).executeQuery();
						List<DashCriticalTampersBean>  imblist=new ArrayList();
						while(rs.next()){
							String name=rs.getString("pt_observation");								
							DashCriticalTampersBean dct=new DashCriticalTampersBean();
							dct.setMeter_serial_no(rs.getString("meter_serial_no"));
							dct.setEvent_date(rs.getString("meter_rtc"));																
							dct.setEvent_name(name);								
							imblist.add(dct);
						}//while
						bean.put("PT IMBALANCE", imblist);
						rs.close();
					   //System.out.println("Tampers bean ------------"+bean);
//---------------------------------
						sql="SELECT * FROM feeder_live_status where pf_status LIKE '%LOW POWER FACTOR%'" ;
						if(!meters.isEmpty()) sql=sql+" and meter_serial_no in ("+meterStr+")";
						 rs=con.prepareStatement(sql).executeQuery();
						List<DashCriticalTampersBean>  lpflist=new ArrayList();
						while(rs.next()){
							String name=rs.getString("pf_status");								
							DashCriticalTampersBean dct=new DashCriticalTampersBean();
							dct.setMeter_serial_no(rs.getString("meter_serial_no"));
							dct.setEvent_date(rs.getString("meter_rtc"));																
							dct.setEvent_name(name);								
							lpflist.add(dct);
						}//while
						bean.put("LOW POWER FACTOR", lpflist);
						rs.close();						
		}catch(Exception e){
			
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
		return bean;
	}
}
