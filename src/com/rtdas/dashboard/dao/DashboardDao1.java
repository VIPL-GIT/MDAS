package com.rtdas.dashboard.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.dashboard.beans.GroupLoadBean;
import com.rtdas.dashboard.beans.SinglePhaseFeedersBean;
import com.rtdas.meterdata.models.FeederLiveStatus;

import utils.DBUtil;
import utils.DateUtilities;

@Repository("dashDao1")
@Transactional
public class DashboardDao1 {

	
	public Map<String,FeederLiveStatus> getOfflineFeedersDetails(String ids,int level){
		int voltage=0;
		Connection con=null;
		Map<String,FeederLiveStatus> map=new HashMap();
		try{
			con=new DBUtil().getConnection();
			DecimalFormat twodec=new DecimalFormat("#.##");
			DateUtilities du=new DateUtilities();
			
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	        String currentDate=sdf.format(new java.util.Date());
			
			String sql="SELECT f.*,t.ss,t.location_name FROM feeder_live_status f, total_masterdata t WHERE f.meter_serial_no=t.meter_sl_no ";
					
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
			//sql=sql+ "AND t.supply_voltage LIKE '"+capacity+"%'";
			
			
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				
				if(rs.getString("modem_rtc").contains(currentDate)){
					
					if(du.getTimeDiff(rs.getString("modem_rtc"))>30*60*1000){
						
						FeederLiveStatus fls=new FeederLiveStatus();
						fls.setMeter_serial_no(rs.getString("meter_serial_no"));
						fls.setModem_rtc(rs.getString("modem_rtc"));
						fls.setKw(rs.getDouble("kw"));
						map.put(rs.getString("ss")+"@"+rs.getString("location_name"), fls);
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
	public Map<String,FeederLiveStatus> getNotCommFeedersDetails(String ids,int level){
		int voltage=0;
		Connection con=null;
		Map<String,FeederLiveStatus> map=new HashMap();
		try{
			con=new DBUtil().getConnection();
			DecimalFormat twodec=new DecimalFormat("#.##");
			DateUtilities du=new DateUtilities();
			
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	        String currentDate=sdf.format(new java.util.Date());
			
			String sql="SELECT f.*,t.ss,t.location_name FROM feeder_live_status f, total_masterdata t WHERE f.meter_serial_no=t.meter_sl_no ";
					
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
			//sql=sql+ "AND t.supply_voltage LIKE '"+capacity+"%'";
			
			
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				
				if(!rs.getString("modem_rtc").contains(currentDate)){
					
						FeederLiveStatus fls=new FeederLiveStatus();
						fls.setMeter_serial_no(rs.getString("meter_serial_no"));
						fls.setModem_rtc(rs.getString("modem_rtc"));
						
						map.put(rs.getString("ss")+"@"+rs.getString("location_name"), fls);
					
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
	
	public Map<String,FeederLiveStatus> getpfNpowersDetails(String ids,int level,String pfNpowerParam,String pfNpowerCondition,String pfNpowerValues){
		int voltage=0;
		Connection con=null;
		Map<String,FeederLiveStatus> map=new HashMap();
		try{
			con=new DBUtil().getConnection();
			DecimalFormat twodec=new DecimalFormat("#.##");
			DateUtilities du=new DateUtilities();
			
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	        String currentDate=sdf.format(new java.util.Date());
			
			String sql="SELECT f.*,t.ss,t.location_name FROM feeder_live_status f, total_masterdata t WHERE f.meter_serial_no=t.meter_sl_no ";
					
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
			//sql=sql+ "AND t.supply_voltage LIKE '"+capacity+"%'";
			
System.out.println(pfNpowerParam+"\t"+pfNpowerCondition+"\t"+pfNpowerValues);

			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				
				if(pfNpowerParam.equals("pf")){
					double pf=rs.getDouble("pfavg");
					 if((pfNpowerCondition.equals("gt") && pf>Double.parseDouble(pfNpowerValues)) || 
							 (pfNpowerCondition.equals("lt") && pf<Double.parseDouble(pfNpowerValues))){
					
						FeederLiveStatus fls=new FeederLiveStatus();
						fls.setMeter_serial_no(rs.getString("meter_serial_no"));
						fls.setModem_rtc(rs.getString("modem_rtc"));
						fls.setPfavg(pf);
						map.put(rs.getString("ss")+"@"+rs.getString("location_name"), fls);
					 }					
				}
				if(pfNpowerParam.equals("mw")){
					double pf=rs.getDouble("kw");
					 if((pfNpowerCondition.equals("gt") && pf>Integer.parseInt(pfNpowerValues)) || 
							 (pfNpowerCondition.equals("lt") && pf<Integer.parseInt(pfNpowerValues))){
					
						FeederLiveStatus fls=new FeederLiveStatus();
						fls.setMeter_serial_no(rs.getString("meter_serial_no"));
						fls.setModem_rtc(rs.getString("modem_rtc"));
						fls.setKw(pf);
						map.put(rs.getString("ss")+"@"+rs.getString("location_name"), fls);
					 }					
				}
				if(pfNpowerParam.equals("mva")){
					double pf=rs.getDouble("kva");
					 if((pfNpowerCondition.equals("gt") && pf>Integer.parseInt(pfNpowerValues)) || 
							 (pfNpowerCondition.equals("lt") && pf<Integer.parseInt(pfNpowerValues))){
					
						FeederLiveStatus fls=new FeederLiveStatus();
						fls.setMeter_serial_no(rs.getString("meter_serial_no"));
						fls.setModem_rtc(rs.getString("modem_rtc"));
						fls.setKva(pf);
						map.put(rs.getString("ss")+"@"+rs.getString("location_name"), fls);
					 }					
				}
				if(pfNpowerParam.equals("mvar")){
					double pf=rs.getDouble("kvar");
					 if((pfNpowerCondition.equals("gt") && pf>Integer.parseInt(pfNpowerValues)) || 
							 (pfNpowerCondition.equals("lt") && pf<Integer.parseInt(pfNpowerValues))){
					
						FeederLiveStatus fls=new FeederLiveStatus();
						fls.setMeter_serial_no(rs.getString("meter_serial_no"));
						fls.setModem_rtc(rs.getString("modem_rtc"));
						fls.setKvar(pf);
						map.put(rs.getString("ss")+"@"+rs.getString("location_name"), fls);
					 }					
				}
				
			}
                
			//System.out.println("----------------->"+map.size());
			
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
	
	public List<String> getCircles(String ids,int level){
		List<String> list=new ArrayList();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			
			String sql="SELECT distinct(circle) from total_masterdata  ";
					
					if(level==1) sql=sql+ "where region_id IN ("+ids+") ";
					if(level==2) sql=sql+ "where circle_id IN ("+ids+") ";
					if(level==3) sql=sql+ "where division_id IN ("+ids+") ";
					if(level==4) sql=sql+ "where sd_id IN ("+ids+") ";
					if(level==5) sql=sql+ "where ss_id IN ("+ids+") ";
					if(level==6) sql=sql+ "where ptr_id IN ("+ids+") ";
					
					sql=sql+" order by circle";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){								
				list.add(rs.getString("circle"));
			}
                
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public Map<String,String> getLiveloadDetails_AG(String ids,int level){
		int voltage=0;
		Connection con=null;
		Map<String,String> map=new HashMap();
		try{
			con=new DBUtil().getConnection();
			DecimalFormat twodec=new DecimalFormat("#.##");
			DateUtilities du=new DateUtilities();
			
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	        String currentDate=sdf.format(new java.util.Date());
	        String prevHr=""+(Integer.parseInt(currentDate.split(" ")[1].split(":")[0])-1);
	        
	        currentDate=currentDate.split(" ")[0];	        
	        prevHr=prevHr+":00";
	        
	        //((lr+ly+lb)/3)*imf
			String sql="SELECT circle,location_category,SUM(ls) FROM "
					+ "(SELECT t.circle_id AS hid,t.circle,t.meter_sl_no,t.location_category, abs(f.kw*f.mf) AS ls FROM feeder_hourly_live_status f,total_masterdata t "
					+ "WHERE f.adjusted_rtc LIKE '"+currentDate+"%' and hour like '%"+prevHr+"%' AND t.meter_sl_no=f.meter_serial_no  AND t.supply_voltage LIKE '11%' AND location_category='AG'";
			
					if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
					if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
					if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
					if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
					if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
					if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
			sql=sql+ " ORDER BY circle) AS t GROUP BY circle,location_category";
			
			
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
								
				map.put(rs.getString("circle"), twodec.format(rs.getDouble(3)/1000));
			}
                
			//System.out.println("----------------->"+sql);
			
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
	public Map<String,String> getLiveloadDetails_NonAG(String ids,int level){
		int voltage=0;
		Connection con=null;
		Map<String,String> map=new HashMap();
		try{
			con=new DBUtil().getConnection();
			DecimalFormat twodec=new DecimalFormat("#.##");
			DateUtilities du=new DateUtilities();
			
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String currentDate=sdf.format(new java.util.Date());
	        String prevHr=""+(Integer.parseInt(currentDate.split(" ")[1].split(":")[0])-1);
	        
	        currentDate=currentDate.split(" ")[0];	        
	        prevHr=prevHr+":00";
				
	        //((lr+ly+lb)/3)*imf
			String sql="SELECT circle,location_category,SUM(ls) FROM "
					+ "(SELECT t.circle_id AS hid,t.circle,t.meter_sl_no,t.location_category, abs(f.kw*f.mf) AS ls FROM feeder_hourly_live_status f,total_masterdata t "
					+ "WHERE f.adjusted_rtc LIKE '"+currentDate+"%' and hour like '%"+prevHr+"%' AND t.meter_sl_no=f.meter_serial_no  AND t.supply_voltage LIKE '11%' AND location_category in ('NON AG','DL')";
			
					if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
					if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
					if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
					if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
					if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
					if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
			sql=sql+ " ORDER BY circle) AS t GROUP BY circle,location_category";
			
			
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
								
				map.put(rs.getString("circle"), twodec.format(rs.getDouble(3)/1000));
			}
                
			//System.out.println("----------------->"+map.size());
			
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
	
	public String getLiveloadGraphDetails(String ids,int level){
		String ag="",nonag="";
		Connection con=null;
		Map<String,Double> agmap=new HashMap();
		Map<String,Double> nonagmap=new HashMap();
		try{
			con=new DBUtil().getConnection();
			DecimalFormat twodec=new DecimalFormat("#.##");
			DateUtilities du=new DateUtilities();
			
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	        String currentDate=sdf.format(new java.util.Date());
	        
			List<String> times=new ArrayList();
	        times.add("00:00");
	        times.add("00:30");
	        times.add("01:00");
	        times.add("01:30");
	        times.add("02:00");
	        times.add("02:30");
	        times.add("03:00");
	        times.add("03:30");
	        times.add("04:00");
	        times.add("04:30");
	        times.add("05:00");
	        times.add("05:30");
	        times.add("06:00");
	        times.add("06:30");
	        times.add("07:00");
	        times.add("07:30");
	        times.add("08:00");
	        times.add("08:30");
	        times.add("09:00");
	        times.add("09:30");
	        times.add("10:00");
	        times.add("10:30");
	        times.add("11:00");
	        times.add("11:30");
	        times.add("12:00");
	        times.add("12:30");
	        times.add("13:00");
	        times.add("13:30");
	        times.add("14:00");
	        times.add("14:30");
	        times.add("15:00");
	        times.add("15:30");
	        times.add("16:00");
	        times.add("16:30");
	        times.add("17:00");
	        times.add("17:30");
	        times.add("18:00");
	        times.add("18:30");
	        times.add("19:00");
	        times.add("19:30");
	        times.add("20:00");
	        times.add("20:30");
	        times.add("21:00");
	        times.add("21:30");
	        times.add("22:00");
	        times.add("22:30");
	        times.add("23:00");
	        times.add("23:30");
	        //((lr+ly+lb)/3)*imf
			
			String sql="SELECT t.location_category,HOUR,SUM(ls) FROM"
					+ "(SELECT t.location_category,HOUR,abs(f.kw*f.mf) AS ls from feeder_hourly_live_status f,total_masterdata t "
					+ "WHERE f.adjusted_rtc LIKE '"+currentDate+"%' AND t.meter_sl_no=f.meter_serial_no AND "
							+ "t.supply_voltage LIKE '11%' ";
			
					if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
					if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
					if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
					if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
					if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
					if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
					sql=sql+")as t GROUP BY location_category,HOUR ";
			
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
					if(rs.getString("location_category").equalsIgnoreCase("AG"))
						agmap.put(rs.getString("hour"), rs.getDouble(3));
					
					if(rs.getString("location_category").equalsIgnoreCase("NON AG") || 
							rs.getString("location_category").equalsIgnoreCase("DL")){
						if(nonagmap.containsKey(rs.getString("hour")))
							nonagmap.put(rs.getString("hour"), nonagmap.get(rs.getString("hour"))+rs.getDouble(3));
						else
							nonagmap.put(rs.getString("hour"), rs.getDouble(3));
					}
				
			}//while
                
			for(String time:times){
				if(agmap.containsKey(time)) ag=ag+","+twodec.format(agmap.get(time)/1000);
				else ag=ag+",0";
				
				if(nonagmap.containsKey(time)) nonag=nonag+","+twodec.format(nonagmap.get(time)/1000);
				else nonag=nonag+",0";
			}
			
			ag=ag.substring(1);
			nonag=nonag.substring(1);
			
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
		
		return ag+";"+nonag;
	}
	
	public Map<String,GroupLoadBean> getGroup_load(List<String> meters,String date){
		int voltage=0;
		Connection con=null;
		Map<String,GroupLoadBean> map=new HashMap();
		try{
			String sql="";
			con=new DBUtil().getConnection();
			DecimalFormat twodec=new DecimalFormat("#0.00");
			DateUtilities du=new DateUtilities();
			
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	        String currentDate=sdf.format(new java.util.Date());
	        
            Map<String,String> mfMap=new HashMap();
			
			 sql="select * from meter";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getString("mf")!=null)
				  mfMap.put(rs.getString("meter_serial_no"), rs.getString("mf"));
			}
			
	        if(date.equals(currentDate)){
	        	sql="SELECT meter_serial_no,kw,adjusted_rtc FROM feeder_hourly_live_status WHERE adjusted_rtc LIKE '"+date+"%' order by adjusted_rtc desc";
	        }
	        else{
	        	sql="select meter_serial_no,sum(kwh_i) from ls_data_stbl where d4_date LIKE '%"+date+"%' group by meter_serial_no";
	        }
	        
             rs=con.prepareStatement(sql).executeQuery();
            while(rs.next()){
            	String meter=rs.getString("meter_serial_no");
            	if(map.containsKey(meter)){
            		
            	}else{
            	if(meters.contains(meter)){
            		double mf=mfMap.containsKey(meter)?Double.parseDouble(mfMap.get(meter)):1;
            		
	            	GroupLoadBean glb=new GroupLoadBean();
	            	glb.setMeter(rs.getString("meter_serial_no"));
	            	glb.setLoad(twodec.format((Math.abs(rs.getDouble(2))*mf)/1000));
	            	
	            	if(date.equals(currentDate))
	            	  glb.setServerTime(rs.getString(3));
	            	else
	            	  glb.setServerTime(date);	  
	            	
	            	map.put(rs.getString(1), glb);
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
	
	public Map<String,SinglePhaseFeedersBean> getSinglePhaseSupplyFeeders(List<String> meters,String date){
		int voltage=0;
		Connection con=null;
		Map<String,SinglePhaseFeedersBean> map=new HashMap();
		try{
			String sql="";
			con=new DBUtil().getConnection();
			DecimalFormat twodec=new DecimalFormat("#0.00");
			DateUtilities du=new DateUtilities();
			
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	        String currentDate=sdf.format(new java.util.Date());
	        
            if(date.equals(currentDate))
               date=du.substract_1_Day(date+" 00:00:00");
			
            sql="select * from power_fail_minutes where power_fail_date='"+date+"' and twophase!='-1'";
			System.out.println("----------------"+sql);
			
            ResultSet rs=con.prepareStatement(sql).executeQuery();
            while(rs.next()){
            	String meter=rs.getString("meter_serial_no");
            	if(meters.contains(meter)){
            		long phase1=rs.getString("singlephase")==null?0:rs.getLong("singlephase");
            		long phase2=rs.getString("twophase")==null?0:rs.getLong("twophase");
            		long phase3=rs.getString("threephase")==null?0:rs.getLong("threephase");

            		phase1=(phase1==-1)?0:phase1;
            		phase2=(phase2==-1)?0:phase2;
            		phase3=(phase3==-1)?0:phase3;
            		
            		SinglePhaseFeedersBean glb=new SinglePhaseFeedersBean();
	            	glb.setMeter_seril_no(rs.getString("meter_serial_no"));
	            	glb.setPh2_hrs(phase2==0?"":du.parseTime(phase2*60*1000));	  
	            	glb.setPh3_hrs(phase3==0?"":du.parseTime(phase3*60*1000));
	            	glb.setTot_hrs(du.parseTime((phase1+phase2+phase3)*60*1000));
	            	
	            	map.put(meter, glb);
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
	
	public Map<String,String> getSinglePhaseDetails(String meter,String date){
		Map<String,String> map=new LinkedHashMap();
		Connection con=null;
		try{
			String sql="";
			con=new DBUtil().getConnection();
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	        String currentDate=sdf.format(new java.util.Date());
	        DateUtilities du=new DateUtilities();
	        
            if(date.equals(currentDate))
               date=du.substract_1_Day(date+" 00:00:00");
            
			sql="SELECT * FROM ls_data_stbl WHERE meter_serial_no='"+meter+"' AND d4_date LIKE '"+date+"%' ORDER BY d4_time_stamp ";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
		System.out.println(sql);
		
			List<Float> vals=new ArrayList();
			int mins_interval=0;
			if(rs.next()){
				if(rs.getString("d4_date").contains("00:15:00"))
					mins_interval=15;
				else
					mins_interval=30;
			}
			rs.beforeFirst();
			while(rs.next()){
				
				if(rs.getFloat("lr")==0 && rs.getFloat("ly")==0 && rs.getFloat("lb")==0){
				}else{
					vals.add(rs.getFloat("lr"));
					vals.add(rs.getFloat("ly"));
					vals.add(rs.getFloat("lb"));
					
					//System.out.println(no_of_occur(vals));
					
					if(  no_of_occur(vals)==1){ 
						System.out.println(vals);
						map.put(du.substract_30_mins(rs.getString("d4_date"),mins_interval), rs.getString("d4_date"));
					}
					
					vals.clear();
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

	public int no_of_occur(List<Float> list){
		int count=0;
		try{
			for(Float f:list){
				if(f==0) count++;
			}
		}catch(Exception e){
			
		}
		return count;
	}
	
}
