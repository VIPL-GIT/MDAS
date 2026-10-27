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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.dashboard.beans.LiveLoadBean;
import com.rtdas.meterdata.models.FeederLiveStatus;

import utils.DBUtil;
import utils.DateUtilities;

@Repository("dashDaoMP")
@Transactional
public class DashboardDaoMP {

	@Autowired
	private SessionFactory sf;
	
	String tab="\t";
	String newLine=System.lineSeparator();
	
	public Map<String,LiveLoadBean> getLoadMap(Map<String,String> circles){
		Map<String,LiveLoadBean> map=new LinkedHashMap<String, LiveLoadBean>();
		try{
			for(Map.Entry em:circles.entrySet()){
				map.put(em.getValue().toString(), new LiveLoadBean());
			}
			
		}catch(Exception e){
			
		}
		
		return map;
	}
	
	public String getDashLoadCurveValues(String date,String ids,int level){
		String values="",ag="",nonag="",tot="";
		Connection con=null;
		try{
			String sql="SELECT location_category,SUM(h0),SUM(h1),SUM(h2),SUM(h3),SUM(h4),SUM(h5),SUM(h6),SUM(h7),SUM(h8),SUM(h9),SUM(h10),SUM(h11),SUM(h12),SUM(h13),SUM(h14),SUM(h15),SUM(h16),SUM(h17),SUM(h18),SUM(h19),SUM(h20),SUM(h21),SUM(h22),SUM(h23) FROM "
					+ "(SELECT l.*,t.location_category FROM analyitics_houly_loadprofile_kwh l,total_masterdata t "
					+ "WHERE l.comm_date='"+date+"' AND l.meter_serial_no=t.meter_sl_no  ";
							
					if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
					if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
					if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
					if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
					if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
					if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
				 sql=sql+ ")AS t GROUP BY location_category";
				 
				 System.out.println("Query for Dash load curve : "+sql);
			 con=new DBUtil().getConnection();
			 ResultSet rs=con.prepareStatement(sql).executeQuery();
			 while(rs.next()){
				 if(rs.getString(1).equals("AG")) ag=ag+","+rs.getDouble(2)+","+rs.getDouble(3)+","+rs.getDouble(4)+","+rs.getDouble(5)+","+rs.getDouble(6)+","+rs.getDouble(7)+","+rs.getDouble(8)+","+rs.getDouble(9)+","+rs.getDouble(10)+","+rs.getDouble(11)+","+rs.getDouble(12)+","+rs.getDouble(13)+","+rs.getDouble(14)+","+rs.getDouble(15)+","+rs.getDouble(16)+","+rs.getDouble(17)+","+rs.getDouble(18)+","+rs.getDouble(19)+","+rs.getDouble(20)+","+rs.getDouble(21)+","+rs.getDouble(22)+","+rs.getDouble(23)+","+rs.getDouble(24)+","+rs.getDouble(25);
				 if(rs.getString(1).equals("NON AG")) nonag=nonag+","+rs.getDouble(2)+","+rs.getDouble(3)+","+rs.getDouble(4)+","+rs.getDouble(5)+","+rs.getDouble(6)+","+rs.getDouble(7)+","+rs.getDouble(8)+","+rs.getDouble(9)+","+rs.getDouble(10)+","+rs.getDouble(11)+","+rs.getDouble(12)+","+rs.getDouble(13)+","+rs.getDouble(14)+","+rs.getDouble(15)+","+rs.getDouble(16)+","+rs.getDouble(17)+","+rs.getDouble(18)+","+rs.getDouble(19)+","+rs.getDouble(20)+","+rs.getDouble(21)+","+rs.getDouble(22)+","+rs.getDouble(23)+","+rs.getDouble(24)+","+rs.getDouble(25);
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
		values=ag.substring(1)+";"+nonag.substring(1);
		return values;
	}
	
	public String getDashLoadCurveValues_withMF(String date,String ids,int level){
		String values="",ag="",nonag="",tot="";
		Connection con=null;
		try{
			String sql="SELECT location_category,SUM(h0)/1000,SUM(h1)/1000,SUM(h2)/1000,SUM(h3)/1000,SUM(h4)/1000,SUM(h5)/1000,SUM(h6)/1000,SUM(h7)/1000,SUM(h8)/1000,SUM(h9)/1000,SUM(h10)/1000,SUM(h11)/1000,SUM(h12)/1000,SUM(h13)/1000,SUM(h14)/1000,SUM(h15)/1000,SUM(h16)/1000,SUM(h17)/1000,SUM(h18)/1000,SUM(h19)/1000,SUM(h20)/1000,SUM(h21)/1000,SUM(h22)/1000,SUM(h23)/1000 FROM"
					//+ "(SELECT (l.h0*t.mf*l.demand_integration_period) AS  h0,(l.h1*t.mf*l.demand_integration_period) AS  h1,(l.h2*t.mf*l.demand_integration_period) AS  h2,(l.h3*t.mf*l.demand_integration_period) AS  h3,(l.h4*t.mf*l.demand_integration_period) AS  h4,(l.h5*t.mf*l.demand_integration_period) AS  h5,(l.h6*t.mf*l.demand_integration_period) AS  h6,(l.h7*t.mf*l.demand_integration_period) AS  h7,(l.h8*t.mf*l.demand_integration_period) AS  h8,(l.h9*t.mf*l.demand_integration_period) AS  h9,(l.h10*t.mf*l.demand_integration_period) AS  h10,(l.h11*t.mf*l.demand_integration_period) AS  h11,(l.h12*t.mf*l.demand_integration_period) AS  h12,(l.h13*t.mf*l.demand_integration_period) AS  h13,(l.h14*t.mf*l.demand_integration_period) AS  h14,(l.h15*t.mf*l.demand_integration_period) AS  h15,(l.h16*t.mf*l.demand_integration_period) AS  h16,(l.h17*t.mf*l.demand_integration_period) AS  h17,(l.h18*t.mf*l.demand_integration_period) AS  h18,(l.h19*t.mf*l.demand_integration_period) AS  h19,(l.h20*t.mf*l.demand_integration_period) AS  h20,(l.h21*t.mf*l.demand_integration_period) AS  h21,(l.h22*t.mf*l.demand_integration_period) AS  h22,(l.h23*t.mf*l.demand_integration_period) AS  h23,t.location_category FROM analyitics_houly_loadprofile_kwh l,total_masterdata t "
					+ "(SELECT (l.h0*t.mf) AS  h0,(l.h1*t.mf) AS  h1,(l.h2*t.mf) AS  h2,(l.h3*t.mf) AS  h3,(l.h4*t.mf) AS  h4,(l.h5*t.mf) AS  h5,(l.h6*t.mf) AS  h6,(l.h7*t.mf) AS  h7,(l.h8*t.mf) AS  h8,(l.h9*t.mf) AS  h9,(l.h10*t.mf) AS  h10,(l.h11*t.mf) AS  h11,(l.h12*t.mf) AS  h12,(l.h13*t.mf) AS  h13,(l.h14*t.mf) AS  h14,(l.h15*t.mf) AS  h15,(l.h16*t.mf) AS  h16,(l.h17*t.mf) AS  h17,(l.h18*t.mf) AS  h18,(l.h19*t.mf) AS  h19,(l.h20*t.mf) AS  h20,(l.h21*t.mf) AS  h21,(l.h22*t.mf) AS  h22,(l.h23*t.mf) AS  h23,t.location_category FROM analyitics_houly_loadprofile_kwh l,total_masterdata t "
					+ "WHERE l.comm_date='"+date+"' AND l.meter_serial_no=t.meter_sl_no  ";
				//	+ "WHERE l.comm_date='2024-08-19' AND l.meter_serial_no=t.meter_sl_no  ";
							
					if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
					if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
					if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
					if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
					if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
					if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
				 sql=sql+ ")AS t GROUP BY location_category";
				 
				 System.out.println("Query for Dash load curve : "+sql);
			 con=new DBUtil().getConnection();
			 ResultSet rs=con.prepareStatement(sql).executeQuery();
			 while(rs.next()){
				 if(rs.getString(1).equals("AG")) ag=ag+","+rs.getDouble(2)+","+rs.getDouble(3)+","+rs.getDouble(4)+","+rs.getDouble(5)+","+rs.getDouble(6)+","+rs.getDouble(7)+","+rs.getDouble(8)+","+rs.getDouble(9)+","+rs.getDouble(10)+","+rs.getDouble(11)+","+rs.getDouble(12)+","+rs.getDouble(13)+","+rs.getDouble(14)+","+rs.getDouble(15)+","+rs.getDouble(16)+","+rs.getDouble(17)+","+rs.getDouble(18)+","+rs.getDouble(19)+","+rs.getDouble(20)+","+rs.getDouble(21)+","+rs.getDouble(22)+","+rs.getDouble(23)+","+rs.getDouble(24)+","+rs.getDouble(25);
				 if(rs.getString(1).equals("NON AG")) nonag=nonag+","+rs.getDouble(2)+","+rs.getDouble(3)+","+rs.getDouble(4)+","+rs.getDouble(5)+","+rs.getDouble(6)+","+rs.getDouble(7)+","+rs.getDouble(8)+","+rs.getDouble(9)+","+rs.getDouble(10)+","+rs.getDouble(11)+","+rs.getDouble(12)+","+rs.getDouble(13)+","+rs.getDouble(14)+","+rs.getDouble(15)+","+rs.getDouble(16)+","+rs.getDouble(17)+","+rs.getDouble(18)+","+rs.getDouble(19)+","+rs.getDouble(20)+","+rs.getDouble(21)+","+rs.getDouble(22)+","+rs.getDouble(23)+","+rs.getDouble(24)+","+rs.getDouble(25);
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
		if(ag.startsWith(",")) ag=ag.substring(1);
		else
			ag="0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
		if(nonag.startsWith(",")) nonag=nonag.substring(1);
		
		values=ag+";"+nonag;
		
		System.out.println("load values---------"+values);
		return values;
	}
	
	public String getDashSustainedPowerFailureValues(String curmonth,String ids,int level){
		String values="",ag="",nonag="";
		Connection con=null;
		try{
			//String sql="SELECT location_category,AVG(d1),AVG(d2),AVG(d3),AVG(d4),AVG(d5),AVG(d6),AVG(d7),AVG(d8),AVG(d9),AVG(d10),AVG(d11),AVG(d12),AVG(d13),AVG(d14),AVG(d15),AVG(d16),AVG(d17),AVG(d18),AVG(d19),AVG(d20),AVG(d21),AVG(d22),AVG(d23),AVG(d24),AVG(d25),AVG(d26),AVG(d27),AVG(d28),AVG(d29),AVG(d30),AVG(d31)"
			String sql="SELECT location_category,sum(d1),sum(d2),sum(d3),sum(d4),sum(d5),sum(d6),sum(d7),sum(d8),sum(d9),sum(d10),sum(d11),sum(d12),sum(d13),sum(d14),sum(d15),sum(d16),sum(d17),sum(d18),sum(d19),sum(d20),sum(d21),sum(d22),sum(d23),sum(d24),sum(d25),sum(d26),sum(d27),sum(d28),sum(d29),sum(d30),sum(d31)"
					+ " FROM (SELECT o.*,t.location_category FROM outage_power_fail_interrupts_sustained o,total_masterdata t "
					+ " WHERE o.month='"+curmonth+"'AND o.meter_serial_no=t.meter_sl_no ";
							
					if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
					if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
					if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
					if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
					if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
					if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
				 sql=sql+ ")AS t GROUP BY location_category";
				 
				 System.out.println("Query for Dash Sustained power failures : "+sql);
			 con=new DBUtil().getConnection();
			 ResultSet rs=con.prepareStatement(sql).executeQuery();
			 while(rs.next()){
				 if(rs.getString(1).equals("AG")) ag=ag+","+rs.getDouble(2)+","+rs.getDouble(3)+","+rs.getDouble(4)+","+rs.getDouble(5)+","+rs.getDouble(6)+","+rs.getDouble(7)+","+rs.getDouble(8)+","+rs.getDouble(9)+","+rs.getDouble(10)+","+rs.getDouble(11)+","+rs.getDouble(12)+","+rs.getDouble(13)+","+rs.getDouble(14)+","+rs.getDouble(15)+","+rs.getDouble(16)+","+rs.getDouble(17)+","+rs.getDouble(18)+","+rs.getDouble(19)+","+rs.getDouble(20)+","+rs.getDouble(21)+","+rs.getDouble(22)+","+rs.getDouble(23)+","+rs.getDouble(24)+","+rs.getDouble(25)+","+rs.getDouble(26)+","+rs.getDouble(27)+","+rs.getDouble(28)+","+rs.getDouble(29)+","+rs.getDouble(30)+","+rs.getDouble(31)+","+rs.getDouble(32);
				 if(rs.getString(1).equals("NON AG")) nonag=nonag+","+rs.getDouble(2)+","+rs.getDouble(3)+","+rs.getDouble(4)+","+rs.getDouble(5)+","+rs.getDouble(6)+","+rs.getDouble(7)+","+rs.getDouble(8)+","+rs.getDouble(9)+","+rs.getDouble(10)+","+rs.getDouble(11)+","+rs.getDouble(12)+","+rs.getDouble(13)+","+rs.getDouble(14)+","+rs.getDouble(15)+","+rs.getDouble(16)+","+rs.getDouble(17)+","+rs.getDouble(18)+","+rs.getDouble(19)+","+rs.getDouble(20)+","+rs.getDouble(21)+","+rs.getDouble(22)+","+rs.getDouble(23)+","+rs.getDouble(24)+","+rs.getDouble(25)+","+rs.getDouble(26)+","+rs.getDouble(27)+","+rs.getDouble(28)+","+rs.getDouble(29)+","+rs.getDouble(30)+","+rs.getDouble(31)+","+rs.getDouble(32);
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

	public Map<String,String> getCirclewise_transientcounts(){
		Map<String,String> map=new LinkedHashMap();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			String previousday=sdf.format(new java.util.Date().getTime()-24*60*60*1000);
			String column="d"+Integer.parseInt(previousday.split("-")[0]);
			String month=previousday.substring(previousday.indexOf("-"));
			
			String sql="SELECT t.circle, SUM(o."+column+") FROM total_masterdata t, outage_power_fail_interrupts_transiant_mop o WHERE o."+column+"<=1 AND t.meter_sl_no=o.meter_serial_no AND o.month='"+month+"' GROUP BY t.circle";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				
				  map.put(rs.getString("circle"), rs.getString(2));
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
	
	public String getDashTransientPowerFailureValues(String curmonth,String ids,int level){
		String values="",ag="",nonag="";
		Connection con=null;
		try{
			//String sql="SELECT location_category,AVG(d1),AVG(d2),AVG(d3),AVG(d4),AVG(d5),AVG(d6),AVG(d7),AVG(d8),AVG(d9),AVG(d10),AVG(d11),AVG(d12),AVG(d13),AVG(d14),AVG(d15),AVG(d16),AVG(d17),AVG(d18),AVG(d19),AVG(d20),AVG(d21),AVG(d22),AVG(d23),AVG(d24),AVG(d25),AVG(d26),AVG(d27),AVG(d28),AVG(d29),AVG(d30),AVG(d31)"
			String sql="SELECT location_category,sum(d1),sum(d2),sum(d3),sum(d4),sum(d5),sum(d6),sum(d7),sum(d8),sum(d9),sum(d10),sum(d11),sum(d12),sum(d13),sum(d14),sum(d15),sum(d16),sum(d17),sum(d18),sum(d19),sum(d20),sum(d21),sum(d22),sum(d23),sum(d24),sum(d25),sum(d26),sum(d27),sum(d28),sum(d29),sum(d30),sum(d31)"
					+ " FROM (SELECT o.*,t.location_category FROM outage_power_fail_interrupts_transiant o,total_masterdata t "
					+ " WHERE o.month='"+curmonth+"'AND o.meter_serial_no=t.meter_sl_no ";
							
					if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
					if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
					if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
					if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
					if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
					if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
				 sql=sql+ ")AS t GROUP BY location_category";
				 
				 System.out.println("Query for Dash Transient power failures : "+sql);
			 con=new DBUtil().getConnection();
			 ResultSet rs=con.prepareStatement(sql).executeQuery();
			 while(rs.next()){
				 if(rs.getString(1).equals("AG")) ag=ag+","+rs.getDouble(2)+","+rs.getDouble(3)+","+rs.getDouble(4)+","+rs.getDouble(5)+","+rs.getDouble(6)+","+rs.getDouble(7)+","+rs.getDouble(8)+","+rs.getDouble(9)+","+rs.getDouble(10)+","+rs.getDouble(11)+","+rs.getDouble(12)+","+rs.getDouble(13)+","+rs.getDouble(14)+","+rs.getDouble(15)+","+rs.getDouble(16)+","+rs.getDouble(17)+","+rs.getDouble(18)+","+rs.getDouble(19)+","+rs.getDouble(20)+","+rs.getDouble(21)+","+rs.getDouble(22)+","+rs.getDouble(23)+","+rs.getDouble(24)+","+rs.getDouble(25)+","+rs.getDouble(26)+","+rs.getDouble(27)+","+rs.getDouble(28)+","+rs.getDouble(29)+","+rs.getDouble(30)+","+rs.getDouble(31)+","+rs.getDouble(32);
				 if(rs.getString(1).equals("NON AG")) nonag=nonag+","+rs.getDouble(2)+","+rs.getDouble(3)+","+rs.getDouble(4)+","+rs.getDouble(5)+","+rs.getDouble(6)+","+rs.getDouble(7)+","+rs.getDouble(8)+","+rs.getDouble(9)+","+rs.getDouble(10)+","+rs.getDouble(11)+","+rs.getDouble(12)+","+rs.getDouble(13)+","+rs.getDouble(14)+","+rs.getDouble(15)+","+rs.getDouble(16)+","+rs.getDouble(17)+","+rs.getDouble(18)+","+rs.getDouble(19)+","+rs.getDouble(20)+","+rs.getDouble(21)+","+rs.getDouble(22)+","+rs.getDouble(23)+","+rs.getDouble(24)+","+rs.getDouble(25)+","+rs.getDouble(26)+","+rs.getDouble(27)+","+rs.getDouble(28)+","+rs.getDouble(29)+","+rs.getDouble(30)+","+rs.getDouble(31)+","+rs.getDouble(32);
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
	
	public String getDashSignalGraphValues(String ids,int level){
		String values="";
		int low=0,moderate=0,good=0;
		Connection con=null;
		try{
			 con=new DBUtil().getConnection();
			//String sql="SELECT gsm_signal FROM device_diagnosis_summary WHERE battery NOT LIKE '%E%' AND modem_serial_no LIKE 'MP%' ";
			String sql="SELECT gsm_signal FROM device_diagnosis_summary WHERE battery NOT LIKE '%E%'";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getDouble(1)>=20) good++;
				if(rs.getDouble(1)<10) low++;
				if(rs.getDouble(1)>=10 && rs.getDouble(1)<20) moderate++;
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
		values=good+","+moderate+","+low;
		return values;
	}
	public String downloadSignalGraphValues(String ids,int level,String type){
		String values="Modem Sl.No"+tab+"Signal"+newLine;
		int low=0,moderate=0,good=0;
		Connection con=null;
		try{
			 con=new DBUtil().getConnection();
			String sql="SELECT * FROM device_diagnosis_summary WHERE battery NOT LIKE '%E%' AND modem_serial_no LIKE 'MP%' ";
			if(type.equalsIgnoreCase("good"))
				sql=sql+" and gsm_signal>=20";
			if(type.equalsIgnoreCase("moderate"))
				sql=sql+" and gsm_signal<20 and gsm_signal>=10";
			if(type.equalsIgnoreCase("low"))
				sql=sql+" and gsm_signal <10";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				values=values+rs.getString("modem_serial_no")+tab+rs.getString("gsm_signal")+newLine;
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
		
		return values;
	}
	public String getDashSignalGraphValues_bkp(String ids,int level){
		String values="",ss="",mc="";
		Connection con=null;
		try{
			 con=new DBUtil().getConnection();
			String sql="SELECT gsm_signal,COUNT(DISTINCT modem_serial_no) AS c FROM device_diagnosis_summary WHERE battery NOT LIKE '%E%' GROUP BY gsm_signal ORDER BY c";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				ss=ss+","+rs.getString(1);
				mc=mc+","+rs.getString(2);
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
		values=mc.substring(1)+";"+ss.substring(1);
		return values;
	}
	
	public String getDashWeekStatusValues(String ids,int level){
		String values="",dates="",cc="";
		Connection con=null;
		try{
			DecimalFormat twodec=new DecimalFormat("#0.00");
			List<String> names=new ArrayList();
			List<String> meters=new ArrayList();
			List<String> dateList=new ArrayList();
			
			 con=new DBUtil().getConnection();
			 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			 String currDate=sdf.format(new java.util.Date());
			 String datesStr="",regions="";
			 for(int i=0;i<=7;i++){
				 String temp=currDate;
				 datesStr=datesStr+",'"+temp+"'";
				 dateList.add(temp);
				 currDate=new DateUtilities().substract_1_Day(temp+" 00:00:00");
			 }
			 dateList.remove(0);
			 datesStr=datesStr.substring(1);
			 datesStr=datesStr.substring(datesStr.indexOf(",")+1);
			 
			 String sql="select meter_sl_no,";
				    if(level==1) sql=sql+ "region ";
					if(level==2) sql=sql+ "circle ";
					if(level==3) sql=sql+ "division ";
					if(level==4) sql=sql+ "sd";
					if(level==5) sql=sql+ "ss ";
					if(level==6) sql=sql+ "prt ";
				sql=sql+" from total_masterdata ";
				    if(level==1) sql=sql+ "where region_id IN ("+ids+") ";
					if(level==2) sql=sql+ "where circle_id IN ("+ids+") ";
					if(level==3) sql=sql+ "where division_id IN ("+ids+") ";
					if(level==4) sql=sql+ "where sd_id IN ("+ids+") ";
					if(level==5) sql=sql+ "where ss_id IN ("+ids+") ";
					if(level==6) sql=sql+ "where ptr_id IN ("+ids+") ";
				
			 ResultSet rs=con.prepareStatement(sql).executeQuery();
			 while(rs.next()){
				 if(!names.contains(rs.getString(2))) names.add(rs.getString(2));
				 if(!meters.contains(rs.getString(1))) meters.add(rs.getString(1));
			 }
			 for(String s:names){
				 regions=regions+",'"+s+"'";
			 }
			 regions=regions.substring(1);
			 
			 System.out.println("Meters--------"+meters.size());
			 System.out.println("regions--------"+regions);
			 
			 sql="SELECT comm_date,SUM(comm) FROM daysummary ";
			    if(level==1) sql=sql+ "where discom IN ("+regions+") ";
				if(level==2) sql=sql+ "where circle IN ("+regions+") ";
				if(level==3) sql=sql+ "where division IN ("+regions+") ";
				if(level==4) sql=sql+ "where sd_id IN ("+ids+") ";
				if(level==5) sql=sql+ "where ss_id IN ("+ids+") ";
				if(level==6) sql=sql+ "where ptr_id IN ("+ids+") ";
			 sql=sql+ " AND comm_date IN ("+datesStr+") GROUP BY comm_date";
		
			 System.out.println("Query for week status "+sql);
		
			 rs=con.prepareStatement(sql).executeQuery();
			 Map<String,String> valMap=new HashMap();
			 while(rs.next()){
//				 dates=dates+","+rs.getString(1);
//				 //cc=cc+","+rs.getString(2);
//				 cc=cc+","+twodec.format((rs.getDouble(2)/meters.size())*100);
				 valMap.put(rs.getString(1), twodec.format((rs.getDouble(2)/meters.size())*100));
			 }
			 Collections.reverse(dateList);
			 for(String d:dateList){
				 dates=dates+","+d; 
				 cc=cc+","+valMap.get(d);
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
		values=dates.substring(1)+";"+cc.substring(1);
		return values;
	}
	
	public String getDashAg_supply(String curmonth,String ids,int level){
		String value="",ag="",nonag="";
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			int day=0;
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
			String curDate=sdf.format(new java.util.Date());
			if(Integer.parseInt(curDate.split("-")[0])==1){
				curDate=new DateUtilities().substract_1_Day(curDate);
				day=Integer.parseInt(curDate.split("-")[0]);
				curmonth=curmonth.substring(curmonth.indexOf("-"));
			}else{
				day=Integer.parseInt(curDate.split("-")[0])-1;
			}
			String sql="SELECT t.location_category,(1440-AVG(d"+day+"))/60 FROM outage_power_fail_minutes_total o,total_masterdata t WHERE o.month='"+curmonth+"' "
					+ "AND o.meter_serial_no=t.meter_sl_no ";
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
					sql=sql+ " GROUP BY t.location_category";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getString(1).equals("AG")) ag=""+rs.getDouble(2);
				if(rs.getString(1).equals("NON AG")) nonag=""+rs.getDouble(2);
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
		System.out.println(ag+";"+nonag);
		return ag+";"+nonag;
	}
	public String getDashAg_supply_LS(String curDate,String ids,int level){
		String value="",ag="",nonag="";
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			
//			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
//			String curDate=sdf.format(new java.util.Date())+" 00:00:00" ;
//			curDate=new DateUtilities().substract_1_Day(curDate);
			
			String sql="SELECT t.location_category,(1440-AVG(noload))/60,COUNT(*) FROM power_fail_minutes o,total_masterdata t WHERE o.power_fail_date='"+curDate+"' and noload!=-1 "
					+ "AND o.meter_serial_no=t.meter_sl_no ";
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
					sql=sql+ " GROUP BY t.location_category";
		System.out.println("query for avg supply "+sql);			
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getString(1).equals("AG")) ag=""+rs.getDouble(2)+","+rs.getInt(3);
				if(rs.getString(1).equals("NON AG")) nonag=""+rs.getDouble(2)+","+rs.getInt(3);
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
		System.out.println(ag+";"+nonag);
		return ag+";"+nonag;
	}
	public String getDashAg_deviation(String curmonth,String ids,int level){
		int ls10=0,gr10=0;
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			int day=0;
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
			String curDate=sdf.format(new java.util.Date());
			if(Integer.parseInt(curDate.split("-")[0])==1){
				curDate=new DateUtilities().substract_1_Day(curDate);
				day=Integer.parseInt(curDate.split("-")[0]);
				curmonth=curmonth.substring(curmonth.indexOf("-"));
			}else{
				day=Integer.parseInt(curDate.split("-")[0])-1;
			}
			String sql="SELECT t.location_category,(1440-(d"+day+"))/60 FROM outage_power_fail_minutes_total o,total_masterdata t WHERE o.month='"+curmonth+"' "
					+ "AND o.meter_serial_no=t.meter_sl_no ";
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
					sql=sql+ " order BY t.location_category";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getString(1).equals("AG")){
					if(rs.getDouble(2)<=10) ls10++;
					if(rs.getDouble(2)>10) gr10++;
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
		System.out.println(ls10+","+gr10);
		return ls10+","+gr10;
	}
	public String getDashAg_deviation_LS(String curDate,String ids,int level){
		int ls10=0,gr10=0;
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			
//			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
//			String curDate=sdf.format(new java.util.Date())+" 00:00:00" ;
//			curDate=new DateUtilities().substract_1_Day(curDate);
			
			String sql="SELECT t.location_category,(1440-(noload))/60 FROM power_fail_minutes o,total_masterdata t WHERE o.power_fail_date='"+curDate+"' AND noload!=-1 "
					+ "AND o.meter_serial_no=t.meter_sl_no ";
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
					sql=sql+ " order BY t.location_category";
					
	System.out.println("deviation supply AG "+sql);
	
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getString(1).equals("AG")){
					if(rs.getDouble(2)<9) ls10++;
					if(rs.getDouble(2)>11) gr10++;
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
		System.out.println(ls10+","+gr10);
		return ls10+","+gr10;
	}
	
	public String getDashNonag_deviation(String curmonth,String ids,int level){
		int ls22=0,gr22=0;
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			int day=0;
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
			String curDate=sdf.format(new java.util.Date());
			if(Integer.parseInt(curDate.split("-")[0])==1){
				curDate=new DateUtilities().substract_1_Day(curDate);
				day=Integer.parseInt(curDate.split("-")[0]);
				curmonth=curmonth.substring(curmonth.indexOf("-"));
			}else{
				day=Integer.parseInt(curDate.split("-")[0])-1;
			}
			String sql="SELECT t.location_category,(1440-(d"+day+"))/60 FROM outage_power_fail_minutes_total o,total_masterdata t WHERE o.month='"+curmonth+"' "
					+ "AND o.meter_serial_no=t.meter_sl_no ";
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
					sql=sql+ " order BY t.location_category";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getString(1).equals("NON AG")){
					if(rs.getDouble(2)<=22) ls22++;
					if(rs.getDouble(2)>22) gr22++;
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
		System.out.println("non ag"+ls22+","+gr22);
		return ls22+","+gr22;
	}
	public String getDashNonag_deviation_LS(String curDate,String ids,int level){
		int ls22=0,gr22=0;
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			int day=0;
//			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
//			String curDate=sdf.format(new java.util.Date())+" 00:00:00";
//			curDate=new DateUtilities().substract_1_Day(curDate);
			
			
			String sql="SELECT t.location_category,(1440-(noload))/60 FROM power_fail_minutes o,total_masterdata t WHERE o.power_fail_date='"+curDate+"' AND noload!=-1 "
					+ "AND o.meter_serial_no=t.meter_sl_no ";
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
					sql=sql+ " order BY t.location_category";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getString(1).equals("NON AG")){
					if(rs.getDouble(2)<20) ls22++;
					if(rs.getDouble(2)>20 && rs.getDouble(2)<22) gr22++;
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
		System.out.println("non ag"+ls22+","+gr22);
		return ls22+","+gr22;
	}
	
	public String getDashLowPF(String ids,int level,int capacity){
		int lowPF=0;
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			
			
			String sql="SELECT f.meter_serial_no,f.pfavg FROM feeder_live_status f, total_masterdata t WHERE f.meter_serial_no=t.meter_sl_no ";
					
			
			if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
			if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
			if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
			if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
			if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
			if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
			sql=sql+ "AND t.supply_voltage LIKE '"+capacity+"%'";
			
			//System.out.println("--------------------"+sql);
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getDouble(2)<0.8){
					lowPF++;
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
		
		return lowPF+"";
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
					
			sql=sql+ "AND t.supply_voltage LIKE '"+capacity+"%'";
			
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
					
			sql=sql+ "AND t.supply_voltage LIKE '"+capacity+"%'";
			
			System.out.println("--------------------"+sql);
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
					
			sql=sql+ "AND t.supply_voltage LIKE '"+capacity+"%'";
			
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
					
			sql=sql+ "AND t.supply_voltage LIKE '"+capacity+"%'";
			
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
					
			sql=sql+ "AND t.supply_voltage LIKE '"+capacity+"%'";
			
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
	
}
