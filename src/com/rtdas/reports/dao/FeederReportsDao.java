package com.rtdas.reports.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.dashboard.beans.SinglePhaseFeedersBean;
import com.rtdas.feeder_reports.beans.AnalyticsDemandForecasting;
import com.rtdas.feeder_reports.beans.BusbarLossesBean;
import com.rtdas.feeder_reports.beans.CommSummaryBean;
import com.rtdas.feeder_reports.beans.DailySupplyHrsBean;
import com.rtdas.feeder_reports.beans.EnergyInputBean;
import com.rtdas.feeder_reports.beans.EnergySavingsDetailsBean;
import com.rtdas.feeder_reports.beans.EnergysavingsBean;
import com.rtdas.feeder_reports.beans.EventsSummary_meterBean;
import com.rtdas.feeder_reports.beans.HourlyLoadLiveBean;
import com.rtdas.feeder_reports.beans.LPDataBean;
import com.rtdas.feeder_reports.beans.LoadCurveBean;
import com.rtdas.feeder_reports.beans.Oluvovpf;
import com.rtdas.feeder_reports.beans.PowerFailuresBean;
import com.rtdas.feeder_reports.beans.ReliabilityIndexBean;
import com.rtdas.feeder_reports.beans.TODConsumptionBean;

import utils.DBUtil;
import utils.DateUtilities;

@Repository("feederReportsDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FeederReportsDao {

	@Autowired
	private SessionFactory sf;
	
	public Map<String,List<String>> getHourlyLoadValues_withMF_kwh(String date,String ids,int level){
		// not completed , need to do later
		String values="",ag="",nonag="",tot="";
		Map<String,List<String>> map=new HashMap();
		Connection con=null;
		try{
			String sql="SELECT location_category,SUM(h0)/1000,SUM(h1)/1000,SUM(h2)/1000,SUM(h3)/1000,SUM(h4)/1000,SUM(h5)/1000,SUM(h6)/1000,SUM(h7)/1000,SUM(h8)/1000,SUM(h9)/1000,SUM(h10)/1000,SUM(h11)/1000,SUM(h12)/1000,SUM(h13)/1000,SUM(h14)/1000,SUM(h15)/1000,SUM(h16)/1000,SUM(h17)/1000,SUM(h18)/1000,SUM(h19)/1000,SUM(h20)/1000,SUM(h21)/1000,SUM(h22)/1000,SUM(h23)/1000 FROM"
					+ "(SELECT (l.h0*t.mf) AS  h0,(l.h1*t.mf) AS  h1,(l.h2*t.mf) AS  h2,(l.h3*t.mf) AS  h3,(l.h4*t.mf) AS  h4,(l.h5*t.mf) AS  h5,(l.h6*t.mf) AS  h6,(l.h7*t.mf) AS  h7,(l.h8*t.mf) AS  h8,(l.h9*t.mf) AS  h9,(l.h10*t.mf) AS  h10,(l.h11*t.mf) AS  h11,(l.h12*t.mf) AS  h12,(l.h13*t.mf) AS  h13,(l.h14*t.mf) AS  h14,(l.h15*t.mf) AS  h15,(l.h16*t.mf) AS  h16,(l.h17*t.mf) AS  h17,(l.h18*t.mf) AS  h18,(l.h19*t.mf) AS  h19,(l.h20*t.mf) AS  h20,(l.h21*t.mf) AS  h21,(l.h22*t.mf) AS  h22,(l.h23*t.mf) AS  h23,t.location_category FROM analyitics_houly_loadprofile_kwh l,total_masterdata t "
					+ "WHERE l.comm_date='"+date+"' AND l.meter_serial_no=t.meter_sl_no  ";
							
					if(level==1) sql=sql+ "and t.region_id IN ("+ids+") ";
					if(level==2) sql=sql+ "and t.circle_id IN ("+ids+") ";
					if(level==3) sql=sql+ "and t.division_id IN ("+ids+") ";
					if(level==4) sql=sql+ "and t.sd_id IN ("+ids+") ";
					if(level==5) sql=sql+ "and t.ss_id IN ("+ids+") ";
					if(level==6) sql=sql+ "and t.ptr_id IN ("+ids+") ";
					
				 sql=sql+ ")AS t GROUP BY location_category";
				 
				// System.out.println("Query for Hourly load  : "+sql);
			 con=new DBUtil().getConnection();
			 ResultSet rs=con.prepareStatement(sql).executeQuery();
			 while(rs.next()){
				 //if(rs.getString(1).equals("AG")) ag=ag+","+rs.getDouble(2)+","+rs.getDouble(3)+","+rs.getDouble(4)+","+rs.getDouble(5)+","+rs.getDouble(6)+","+rs.getDouble(7)+","+rs.getDouble(8)+","+rs.getDouble(9)+","+rs.getDouble(10)+","+rs.getDouble(11)+","+rs.getDouble(12)+","+rs.getDouble(13)+","+rs.getDouble(14)+","+rs.getDouble(15)+","+rs.getDouble(16)+","+rs.getDouble(17)+","+rs.getDouble(18)+","+rs.getDouble(19)+","+rs.getDouble(20)+","+rs.getDouble(21)+","+rs.getDouble(22)+","+rs.getDouble(23)+","+rs.getDouble(24)+","+rs.getDouble(25);
				// if(rs.getString(1).equals("NON AG")) nonag=nonag+","+rs.getDouble(2)+","+rs.getDouble(3)+","+rs.getDouble(4)+","+rs.getDouble(5)+","+rs.getDouble(6)+","+rs.getDouble(7)+","+rs.getDouble(8)+","+rs.getDouble(9)+","+rs.getDouble(10)+","+rs.getDouble(11)+","+rs.getDouble(12)+","+rs.getDouble(13)+","+rs.getDouble(14)+","+rs.getDouble(15)+","+rs.getDouble(16)+","+rs.getDouble(17)+","+rs.getDouble(18)+","+rs.getDouble(19)+","+rs.getDouble(20)+","+rs.getDouble(21)+","+rs.getDouble(22)+","+rs.getDouble(23)+","+rs.getDouble(24)+","+rs.getDouble(25);
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
	
public Map<String,HourlyLoadLiveBean> getHourlyLoadValues_live(List<String> meters,String presDate){
	Map<String,HourlyLoadLiveBean> map=new HashMap();
	Connection con=null;
	try{
		DecimalFormat odec=new DecimalFormat("#0.00");
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//		String presDate=sdf.format(new java.util.Date()).split(" ")[0];
//		int pre_hr=Integer.parseInt(sdf.format(new java.util.Date()).split(" ")[1].split(":")[0]);
//		pre_hr=pre_hr-1;
		
		con=new DBUtil().getConnection();
		Map<String,Double> imfMap=new HashMap();
		
//		String sql="select * from feeder_live_status";
//		ResultSet rs=con.prepareStatement(sql).executeQuery();
//		while(rs.next()){			
//			  imfMap.put(rs.getString("meter_serial_no"),rs.getDouble("imf"));			
//		}
		
		String sql="select * from analyitics_houly_load_mobile_source WHERE comm_date='"+presDate+"'";
		System.out.println("---------------------------->>>>>>>>>>"+sql);
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		
		while(rs.next()){
			String meter=rs.getString("meter_serial_no");
			if(meters.contains(meter)){
				
				double imf=1;
				int na_count=0;
					HourlyLoadLiveBean hlb=new HourlyLoadLiveBean();
					
//					if(rs.getString("h0")==null){}else{ hlb.setHr1(rs.getInt("h0s")==1?(rs.getString("h0")+" (M)"):rs.getString("h0")); }
//					if(rs.getString("h1")==null){}else{ hlb.setHr2(rs.getInt("h1s")==1?(rs.getString("h1")+" (M)"):rs.getString("h1")); }
//					if(rs.getString("h2")==null){}else{ hlb.setHr3(rs.getInt("h2s")==1?(rs.getString("h2")+" (M)"):rs.getString("h2")); }
//					if(rs.getString("h3")==null){}else{ hlb.setHr4(rs.getInt("h3s")==1?(rs.getString("h3")+" (M)"):rs.getString("h3")); }
//					if(rs.getString("h4")==null){}else{ hlb.setHr5(rs.getInt("h4s")==1?(rs.getString("h4")+" (M)"):rs.getString("h4")); }
//					if(rs.getString("h5")==null){}else{ hlb.setHr6(rs.getInt("h5s")==1?(rs.getString("h5")+" (M)"):rs.getString("h5")); }
//					if(rs.getString("h6")==null){}else{ hlb.setHr7(rs.getInt("h6s")==1?(rs.getString("h6")+" (M)"):rs.getString("h6")); }
//					if(rs.getString("h7")==null){}else{ hlb.setHr8(rs.getInt("h7s")==1?(rs.getString("h7")+" (M)"):rs.getString("h7")); }
//					if(rs.getString("h8")==null){}else{ hlb.setHr9(rs.getInt("h8s")==1?(rs.getString("h8")+" (M)"):rs.getString("h8")); }
//					if(rs.getString("h9")==null){}else{ hlb.setHr10(rs.getInt("h9s")==1?(rs.getString("h9")+" (M)"):rs.getString("h9")); }
//					if(rs.getString("h10")==null){}else{ hlb.setHr11(rs.getInt("h10s")==1?(rs.getString("h10")+" (M)"):rs.getString("h10")); }
//					if(rs.getString("h11")==null){}else{ hlb.setHr12(rs.getInt("h11s")==1?(rs.getString("h11")+" (M)"):rs.getString("h11")); }
//					if(rs.getString("h12")==null){}else{ hlb.setHr13(rs.getInt("h12s")==1?(rs.getString("h12")+" (M)"):rs.getString("h12")); }
//					if(rs.getString("h13")==null){}else{ hlb.setHr14(rs.getInt("h13s")==1?(rs.getString("h13")+" (M)"):rs.getString("h13")); }
//					if(rs.getString("h14")==null){}else{ hlb.setHr15(rs.getInt("h14s")==1?(rs.getString("h14")+" (M)"):rs.getString("h14")); }
//					if(rs.getString("h15")==null){}else{ hlb.setHr16(rs.getInt("h15s")==1?(rs.getString("h15")+" (M)"):rs.getString("h15")); }
//					if(rs.getString("h16")==null){}else{ hlb.setHr17(rs.getInt("h16s")==1?(rs.getString("h16")+" (M)"):rs.getString("h16")); }
//					if(rs.getString("h17")==null){}else{ hlb.setHr18(rs.getInt("h17s")==1?(rs.getString("h17")+" (M)"):rs.getString("h17")); }
//					if(rs.getString("h18")==null){}else{ hlb.setHr19(rs.getInt("h18s")==1?(rs.getString("h18")+" (M)"):rs.getString("h18")); }
//					if(rs.getString("h19")==null){}else{ hlb.setHr20(rs.getInt("h19s")==1?(rs.getString("h19")+" (M)"):rs.getString("h19")); }
//					if(rs.getString("h20")==null){}else{ hlb.setHr21(rs.getInt("h20s")==1?(rs.getString("h20")+" (M)"):rs.getString("h20")); }
//					if(rs.getString("h21")==null){}else{ hlb.setHr22(rs.getInt("h21s")==1?(rs.getString("h21")+" (M)"):rs.getString("h21")); }
//					if(rs.getString("h22")==null){}else{ hlb.setHr23(rs.getInt("h22s")==1?(rs.getString("h22")+" (M)"):rs.getString("h22")); }
//					if(rs.getString("h23")==null){}else{ hlb.setHr24(rs.getInt("h23s")==1?(rs.getString("h23")+" (M)"):rs.getString("h23")); }
//										
					if(rs.getString("h0")==null){na_count++;}else{ hlb.setHr1(rs.getInt("h0s")==1?(rs.getString("h0")+" (M)"):""+Math.abs(rs.getDouble("h0"))); }
					if(rs.getString("h1")==null){na_count++;}else{ hlb.setHr2(rs.getInt("h1s")==1?(rs.getString("h1")+" (M)"):""+Math.abs(rs.getDouble("h1"))); }
					if(rs.getString("h2")==null){na_count++;}else{ hlb.setHr3(rs.getInt("h2s")==1?(rs.getString("h2")+" (M)"):""+Math.abs(rs.getDouble("h2"))); }
					if(rs.getString("h3")==null){na_count++;}else{ hlb.setHr4(rs.getInt("h3s")==1?(rs.getString("h3")+" (M)"):""+Math.abs(rs.getDouble("h3"))); }
					if(rs.getString("h4")==null){na_count++;}else{ hlb.setHr5(rs.getInt("h4s")==1?(rs.getString("h4")+" (M)"):""+Math.abs(rs.getDouble("h4"))); }
					if(rs.getString("h5")==null){na_count++;}else{ hlb.setHr6(rs.getInt("h5s")==1?(rs.getString("h5")+" (M)"):""+Math.abs(rs.getDouble("h5"))); }
					if(rs.getString("h6")==null){na_count++;}else{ hlb.setHr7(rs.getInt("h6s")==1?(rs.getString("h6")+" (M)"):""+Math.abs(rs.getDouble("h6"))); }
					if(rs.getString("h7")==null){na_count++;}else{ hlb.setHr8(rs.getInt("h7s")==1?(rs.getString("h7")+" (M)"):""+Math.abs(rs.getDouble("h7"))); }
					if(rs.getString("h8")==null){na_count++;}else{ hlb.setHr9(rs.getInt("h8s")==1?(rs.getString("h8")+" (M)"):""+Math.abs(rs.getDouble("h8"))); }
					if(rs.getString("h9")==null){na_count++;}else{ hlb.setHr10(rs.getInt("h9s")==1?(rs.getString("h9")+" (M)"):""+Math.abs(rs.getDouble("h9"))); }
					if(rs.getString("h10")==null){na_count++;}else{ hlb.setHr11(rs.getInt("h10s")==1?(rs.getString("h10")+" (M)"):""+Math.abs(rs.getDouble("h10"))); }
					if(rs.getString("h11")==null){na_count++;}else{ hlb.setHr12(rs.getInt("h11s")==1?(rs.getString("h11")+" (M)"):""+Math.abs(rs.getDouble("h11"))); }
					if(rs.getString("h12")==null){na_count++;}else{ hlb.setHr13(rs.getInt("h12s")==1?(rs.getString("h12")+" (M)"):""+Math.abs(rs.getDouble("h12"))); }
					if(rs.getString("h13")==null){na_count++;}else{ hlb.setHr14(rs.getInt("h13s")==1?(rs.getString("h13")+" (M)"):""+Math.abs(rs.getDouble("h13"))); }
					if(rs.getString("h14")==null){na_count++;}else{ hlb.setHr15(rs.getInt("h14s")==1?(rs.getString("h14")+" (M)"):""+Math.abs(rs.getDouble("h14"))); }
					if(rs.getString("h15")==null){na_count++;}else{ hlb.setHr16(rs.getInt("h15s")==1?(rs.getString("h15")+" (M)"):""+Math.abs(rs.getDouble("h15"))); }
					if(rs.getString("h16")==null){na_count++;}else{ hlb.setHr17(rs.getInt("h16s")==1?(rs.getString("h16")+" (M)"):""+Math.abs(rs.getDouble("h16"))); }
					if(rs.getString("h17")==null){na_count++;}else{ hlb.setHr18(rs.getInt("h17s")==1?(rs.getString("h17")+" (M)"):""+Math.abs(rs.getDouble("h17"))); }
					if(rs.getString("h18")==null){na_count++;}else{ hlb.setHr19(rs.getInt("h18s")==1?(rs.getString("h18")+" (M)"):""+Math.abs(rs.getDouble("h18"))); }
					if(rs.getString("h19")==null){na_count++;}else{ hlb.setHr20(rs.getInt("h19s")==1?(rs.getString("h19")+" (M)"):""+Math.abs(rs.getDouble("h19"))); }
					if(rs.getString("h20")==null){na_count++;}else{ hlb.setHr21(rs.getInt("h20s")==1?(rs.getString("h20")+" (M)"):""+Math.abs(rs.getDouble("h20"))); }
					if(rs.getString("h21")==null){na_count++;}else{ hlb.setHr22(rs.getInt("h21s")==1?(rs.getString("h21")+" (M)"):""+Math.abs(rs.getDouble("h21"))); }
					if(rs.getString("h22")==null){na_count++;}else{ hlb.setHr23(rs.getInt("h22s")==1?(rs.getString("h22")+" (M)"):""+Math.abs(rs.getDouble("h22"))); }
					if(rs.getString("h23")==null){na_count++;}else{ hlb.setHr24(rs.getInt("h23s")==1?(rs.getString("h23")+" (M)"):""+Math.abs(rs.getDouble("h23"))); }
					
					String availPercent=odec.format(((24f-na_count)/24)*100);
					hlb.setAvailPercent(availPercent);
					map.put(meter, hlb);
				
			}//if contains meter
		}//while
		
		for(String meter:meters){
			if(map.containsKey(meter)){}
			else{
				HourlyLoadLiveBean hlb=new HourlyLoadLiveBean();
				map.put(meter, hlb);
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

public List<HourlyLoadLiveBean> getDivisionsHourlyLoadValues_live(String date){
	List<HourlyLoadLiveBean> list=new ArrayList();
	Connection con=null;
	try{
		DecimalFormat twodec=new DecimalFormat("#0.00");
		Map<String,String> meters=new HashMap();
		con=new DBUtil().getConnection();
		String sql="SELECT meter_serial_no,"
				+ "(ISNULL(h0)+ISNULL(h1)+ISNULL(h2)+ISNULL(h3)+ISNULL(h4)+ISNULL(h5)+ISNULL(h6)+ISNULL(h7)+ISNULL(h8)+ISNULL(h9)+ISNULL(h10)+ISNULL(h11)+ISNULL(h12)+ISNULL(h13)+ISNULL(h14)+ISNULL(h15)+ISNULL(h16)+ISNULL(h17)+ISNULL(h18)+ISNULL(h19)+ISNULL(h20)+ISNULL(h21)+ISNULL(h22)+ISNULL(h23)) AS nval,"
				+ "(h0s+h1s+h2s+h3s+h4s+h5s+h6s+h7s+h8s+h9s+h10s+h11s+h12s+h13s+h14s+h15s+h16s+h17s+h18s+h19s+h20s+h21s+h22s+h23s) AS mval,"
				+ "(24-(h0s+h1s+h2s+h3s+h4s+h5s+h6s+h7s+h8s+h9s+h10s+h11s+h12s+h13s+h14s+h15s+h16s+h17s+h18s+h19s+h20s+h21s+h22s+h23s)-(ISNULL(h0)+ISNULL(h1)+ISNULL(h2)+ISNULL(h3)+ISNULL(h4)+ISNULL(h5)+ISNULL(h6)+ISNULL(h7)+ISNULL(h8)+ISNULL(h9)+ISNULL(h10)+ISNULL(h11)+ISNULL(h12)+ISNULL(h13)+ISNULL(h14)+ISNULL(h15)+ISNULL(h16)+ISNULL(h17)+ISNULL(h18)+ISNULL(h19)+ISNULL(h20)+ISNULL(h21)+ISNULL(h22)+ISNULL(h23))) AS aval "
				+ "FROM analyitics_houly_load_mobile_source WHERE comm_date='"+date+"'";
		
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			meters.put(rs.getString("meter_serial_no"), rs.getString("aval")+","+rs.getString("mval")+","+rs.getString("nval"));
		}
		
		sql="SELECT division,GROUP_CONCAT(meter_sl_no) FROM total_masterdata GROUP BY division ORDER BY division";
		rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			HourlyLoadLiveBean hlb=new HourlyLoadLiveBean();
			hlb.setHname(rs.getString("division"));
//--------------------------------------------------------------------------------
            double acount=0,mcount=0,ncount=0;
            int fcount=0;
            for(String m:rs.getString(2).split(",")){
            	if(meters.containsKey(m)){
            		fcount++;
            		acount=acount+Integer.parseInt(meters.get(m).split(",")[0]);
            		mcount=mcount+Integer.parseInt(meters.get(m).split(",")[1]);
            		ncount=ncount+Integer.parseInt(meters.get(m).split(",")[2]);
            	}
            }//for
            
            hlb.setAmr_percent(twodec.format((acount/(fcount*24))*100));
            hlb.setManual_percent(twodec.format((mcount/(fcount*24))*100));
            hlb.setNull_percent(twodec.format((ncount/(fcount*24))*100));
            hlb.setNo_of_feeders(""+fcount);
//--------------------------------------------------------------------------------			
			list.add(hlb);
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
	
	return list;	
}
public Map<String,Map<String,Double>> getHourlyLoadValues_byType(String date,String metersStr,String type){
		
		Connection con=null;
		Map<String,Map<String,Double>> lsMap=new HashMap();
		try{
			
			Map<String,Double> imfMap=new HashMap();
			
			con=new DBUtil().getConnection();
			
			
			String sql="select * from feeder_live_status";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(type.equals("1"))
				  imfMap.put(rs.getString("meter_serial_no"),rs.getDouble("imf"));
				else
				  imfMap.put(rs.getString("meter_serial_no"),rs.getDouble("mf"));
			}
			
			
			
			if(type.equals("1")){
			  sql="SELECT meter_serial_no,SUBSTR(d4_date,12,2) AS hr, GREATEST(lr,ly,lb) AS c FROM ls_data_stbl "
					+ "WHERE d4_date LIKE '"+date+"%'" ;
			      //+ "WHERE d4_date LIKE '"+date+"%' AND meter_serial_no in ("+metersStr+")";
			}
			if(type.equals("2")){
				  sql="SELECT meter_serial_no,SUBSTR(d4_date,12,2) AS hr, kvah_i AS c FROM ls_data_stbl "
						+ "WHERE d4_date LIKE '"+date+"%'";
				       // + "WHERE d4_date LIKE '"+date+"%' AND meter_serial_no in ("+metersStr+")";				  	
				}
			if(type.equals("3")){
				  sql="SELECT meter_serial_no,SUBSTR(d4_date,12,2) AS hr, kwh_i/kvah_i AS c FROM ls_data_stbl "
						  + "WHERE d4_date LIKE '"+date+"%' ";
						  //+ "WHERE d4_date LIKE '"+date+"%' AND meter_serial_no in ("+metersStr+")";
				}
			
			//System.out.println("--------- "+sql);
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				double mimf=1;
				if(imfMap.containsKey(rs.getString("meter_serial_no")) && !type.equals("3"))
					mimf=imfMap.get(rs.getString("meter_serial_no"));
				if(lsMap.containsKey(rs.getString("meter_serial_no"))){
					Map<String,Double> hrmap=lsMap.get(rs.getString("meter_serial_no"));
					//System.out.println("---------if----hrmap--------------"+lsMap);
					if(hrmap.containsKey(rs.getString(2))){
					 if(hrmap.get(rs.getString(2))<(rs.getDouble(3)*mimf)){
						hrmap.put(rs.getString(2), rs.getDouble(3)*mimf);
							
					 }
					}else{
						
						hrmap.put(rs.getString(2), rs.getDouble(3)*mimf);
						lsMap.put(rs.getString("meter_serial_no"), hrmap);	
					}
				}else{
					Map<String,Double> hrmap=new HashMap();
					hrmap.put(rs.getString(2), rs.getDouble(3)*mimf);
					lsMap.put(rs.getString("meter_serial_no"), hrmap);
					//System.out.println("------else-------hrmap--------------"+lsMap);
				}
			}
			
//			System.out.println(lsMap);
//			System.out.println("\n");
			
			
				
			
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
		
		return lsMap;
	}
	public Map<String,Double> getHourlyLoadValues_currents_bkp(String date,int ids,int level){
		
		
		Map<String,Double> map=new HashMap();
		Connection con=null;
		String metersStr="";
		try{
			//System.out.println(ids+"\t"+level);
			
			Map<String,Double> imfMap=new HashMap();
			List<String> meters=new ArrayList();
			con=new DBUtil().getConnection();
			
			String sql="select t.meter_sl_no from total_masterdata t ";
				if(level==1) sql=sql+ "where t.region_id IN ("+ids+") ";
				if(level==2) sql=sql+ "where t.circle_id IN ("+ids+") ";
				if(level==3) sql=sql+ "where t.division_id IN ("+ids+") ";
				if(level==4) sql=sql+ "where t.sd_id IN ("+ids+") ";
				if(level==5) sql=sql+ "where t.ss_id IN ("+ids+") ";
				if(level==6) sql=sql+ "where t.ptr_id IN ("+ids+") ";
				
				sql=sql+" and t.supply_voltage like '11%'";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				meters.add(rs.getString("meter_sl_no"));
				metersStr=metersStr+",'"+rs.getString("meter_sl_no")+"'";
			}
			metersStr=metersStr.substring(1);
			//metersStr="'X1000711'";
			sql="select * from feeder_live_status";
			 rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				imfMap.put(rs.getString("meter_serial_no"),rs.getDouble("imf"));
			}
			//System.out.println("Total meters "+meters.size());
			
			Map<String,Map<String,Double>> lsMap=new HashMap();
			
			sql="SELECT meter_serial_no,SUBSTR(d4_date,12,2) AS hr, GREATEST(lr,ly,lb) AS c FROM ls_data_stbl "
					+ "WHERE d4_date LIKE '"+date+"%' AND meter_serial_no in ("+metersStr+")";
			
			//System.out.println("--------- "+sql);
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				double mimf=1;
				if(imfMap.containsKey(rs.getString("meter_serial_no")))
					mimf=imfMap.get(rs.getString("meter_serial_no"));
				if(lsMap.containsKey(rs.getString("meter_serial_no"))){
					Map<String,Double> hrmap=lsMap.get(rs.getString("meter_serial_no"));
					//System.out.println("---------if----hrmap--------------"+lsMap);
					if(hrmap.containsKey(rs.getString(2))){
					 if(hrmap.get(rs.getString(2))<(rs.getDouble(3)*mimf)){
						hrmap.put(rs.getString(2), rs.getDouble(3)*mimf);
							
					 }
					}else{
						
						hrmap.put(rs.getString(2), rs.getDouble(3)*mimf);
						lsMap.put(rs.getString("meter_serial_no"), hrmap);	
					}
				}else{
					Map<String,Double> hrmap=new HashMap();
					hrmap.put(rs.getString(2), rs.getDouble(3)*mimf);
					lsMap.put(rs.getString("meter_serial_no"), hrmap);
					//System.out.println("------else-------hrmap--------------"+lsMap);
				}
			}
			
//			System.out.println(lsMap);
//			System.out.println("\n");
			
			
				for(Map.Entry em:lsMap.entrySet()){
					Map<String,Double> hrmap=(Map<String,Double>)em.getValue();
					for(Map.Entry hem:hrmap.entrySet()){
						if(map.containsKey(hem.getKey())){
							map.put(hem.getKey().toString(), map.get(hem.getKey())+Double.parseDouble(hem.getValue().toString()));
						}else{
							map.put(hem.getKey().toString(), Double.parseDouble(hem.getValue().toString()));
						}
					}
				}
			
				//System.out.println(map);
				//System.out.println("\n");
			
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
	
	public CommSummaryBean getCommSummary(String hname,int level,String commdate){
		CommSummaryBean cb=new CommSummaryBean();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			String sql="";
			if(level==1) sql=sql+"SELECT circlename,";
			if(level==2) sql=sql+"SELECT divisionname,";
			if(level==3) sql=sql+"SELECT subdivname,";
			if(level==4) sql=sql+"SELECT substationname,";
				   sql=sql+ "STATUS,COUNT(*) FROM device_summary WHERE comm_date='"+commdate+"'";
				   
				    if(level==1) sql=sql+ " AND circlename='"+hname+"' ";
					if(level==2) sql=sql+ " AND divisionname='"+hname+"' ";
					if(level==3) sql=sql+ " AND subdivname='"+hname+"' ";
					if(level==4) sql=sql+ " AND substationname='"+hname+"' ";
					
				    if(level==1) sql=sql+ "GROUP BY circlename,STATUS";
					if(level==2) sql=sql+ "GROUP BY divisionname,STATUS";
					if(level==3) sql=sql+ "GROUP BY subdivname,STATUS";
					if(level==4) sql=sql+ "GROUP BY substationname,STATUS";
			
					System.out.println("-------"+sql);
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			int total=0;
			while(rs.next()){
				if(rs.getString(2).equalsIgnoreCase("COMMUNICATING"))
					cb.setComm_status(rs.getString(3));
				if(rs.getString(2).equalsIgnoreCase("COMMISSIONING IS YET TO BE DONE"))
					cb.setCommissioning_to_be_done(rs.getString(3));
				if(rs.getString(2).equalsIgnoreCase("I&C YET TO BE DONE"))
					cb.setiNc_to_be_done(rs.getString(3));
				if(rs.getString(2).equalsIgnoreCase("MODEM TO METER CABLE DISTURBED OR INCOMPATIBLE"))
					cb.setMcf(rs.getString(3));
				if(rs.getString(2).equalsIgnoreCase("RD AVAIALABLE(PARTIAL DATA)"))
					cb.setModem_fail(rs.getString(3));
				if(rs.getString(2).equalsIgnoreCase("NOT COMMUNICATING (RECTIFICATION REQUIRED)"))
					cb.setNotComm(rs.getString(3));
				
				total=total+rs.getInt(3);
			}
			cb.setTot_points(""+total);
			
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
		return cb;
	}
	public Map<String,String> getCommSummaryDateWise(String hname,String dates){
		Map<String,String> map=new HashMap();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			String sql="SELECT circlename,comm_date,COUNT(*) FROM device_summary WHERE comm_date IN ("+dates+") AND circlename='"+hname+"' AND STATUS='COMMUNICATING' GROUP BY circlename,comm_date";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				map.put(rs.getString(2), rs.getString(3));
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
	public Map<String,String> getCircleWiseTotalMeters_DeviceSummary(String frtodate){
		Map<String,String> map=new HashMap();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			String sql="SELECT circlename,COUNT(*) FROM device_summary WHERE comm_date IN ('"+frtodate+"') GROUP BY circlename";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getString(1)!=null){
					map.put(rs.getString(1), rs.getString(2));
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
	public Map<String,String> getSupplyHrs(String frdate,String meters){
		Map<String,String> map=new HashMap();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			String sql="SELECT meter_serial_no,singlephase,twophase,threephase,noload FROM power_fail_minutes WHERE "
					+ "power_fail_date='"+frdate+"' AND meter_serial_no IN ("+meters+") ";
		
			//System.out.println(" Daily supply hrs supply "+sql);
			
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			DateUtilities du=new DateUtilities();
			while(rs.next()){
				int s1phase=(rs.getInt(2)==-1?0:rs.getInt(2))+(rs.getInt(3)==-1?0:rs.getInt(3))+(rs.getInt(4)==-1?0:rs.getInt(4));
//				int t3phase=(rs.getInt(4)==-1?0:rs.getInt(4));
//				int noload=(rs.getInt(5)==-1?0:rs.getInt(5));
				//map.put(rs.getString(1), s1phase+";"+t3phase+";"+noload);
				if(s1phase!=0)
				  map.put(rs.getString(1), du.parseTimeD(s1phase*60*1000));
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
	public Map<String,DailySupplyHrsBean> getSupplyHrs_bean(String frdate,String meters){
		Map<String,DailySupplyHrsBean> map=new HashMap();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			String sql="SELECT meter_serial_no,singlephase,twophase,threephase,noload,quarter_1,quarter_2,quarter_3,quarter_4,quarter_1_consumption,quarter_2_consumption,quarter_3_consumption,quarter_4_consumption"
					+ " FROM power_fail_minutes WHERE "
					+ "power_fail_date='"+frdate+"' AND meter_serial_no IN ("+meters+") ";
		
			System.out.println(" Daily supply hrs supply "+sql);
			
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			DateUtilities du=new DateUtilities();
			while(rs.next()){
				DailySupplyHrsBean dsb=new DailySupplyHrsBean();
				int s1phase=(rs.getInt(2)==-1?0:rs.getInt(2))+(rs.getInt(3)==-1?0:rs.getInt(3))+(rs.getInt(4)==-1?0:rs.getInt(4));
//				int t3phase=(rs.getInt(4)==-1?0:rs.getInt(4));
//				int noload=(rs.getInt(5)==-1?0:rs.getInt(5));
				//map.put(rs.getString(1), s1phase+";"+t3phase+";"+noload);
				if(s1phase!=0)
				  dsb.setTotal_supply_hrs(du.parseTimeD(s1phase*60*1000));
				dsb.setSlot1_duration(du.parseTimeD((360-rs.getInt("quarter_1"))*60*1000));
				dsb.setSlot2_duration(du.parseTimeD((360-rs.getInt("quarter_2"))*60*1000));
				dsb.setSlot3_duration(du.parseTimeD((360-rs.getInt("quarter_3"))*60*1000));
				dsb.setSlot4_duration(du.parseTimeD((360-rs.getInt("quarter_4"))*60*1000));
				
				dsb.setSlot1_consumption(rs.getString("quarter_1_consumption"));
				dsb.setSlot2_consumption(rs.getString("quarter_2_consumption"));
				dsb.setSlot3_consumption(rs.getString("quarter_3_consumption"));
				dsb.setSlot4_consumption(rs.getString("quarter_4_consumption"));
				
				map.put(rs.getString(1), dsb);
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
	public Map<String,Map<String,String>> getAVGSupplyHrs(String frdate,String meters){
		Map<String,Map<String,String>> map=new HashMap();
		Map<String,Integer> avgMap=new HashMap();
		Connection con=null;
		try{
			//meters="'8180149'";
			frdate=frdate.replace("/", "-");
			con=new DBUtil().getConnection();
			String sql="SELECT meter_serial_no,singlephase,twophase,threephase,noload,power_fail_date FROM power_fail_minutes WHERE "
					+ "power_fail_date like '%"+frdate+"' AND meter_serial_no IN ("+meters+") ";
		
			
			//System.out.println(sql);
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			DateUtilities du=new DateUtilities();
			while(rs.next()){
				int s1phase=(rs.getInt(2)==-1?0:rs.getInt(2))+(rs.getInt(3)==-1?0:rs.getInt(3))+(rs.getInt(4)==-1?0:rs.getInt(4));
				String meter=rs.getString(1);
				
				//System.out.println(s1phase+"\t"+avgMap);
				if(s1phase!=0){
				  String supply= du.parseTimeH(s1phase*60*1000);
				  if(map.containsKey(meter)){
					  map.get(meter).put(rs.getString(6), supply);
					  
					  avgMap.put(meter, avgMap.get(meter)+(s1phase));
				  }else{
					  HashMap valMap=new HashMap();
					  valMap.put(rs.getString(6), supply);
					  map.put(meter, valMap);
					  
					  avgMap.put(meter, s1phase);
				  }
				}
			}//while
			
			for(Map.Entry em:avgMap.entrySet()){
				//System.out.println(em.getKey()+"\t"+em.getValue());
				map.get(em.getKey()).put("32-00-0000", du.parseTimeH((Long.parseLong(em.getValue().toString())*60*1000)/map.get(em.getKey()).size()));
			}
			//System.out.println(map);
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
	
	public Map<String,Map<String,String>> getAVGSupplyHrs_weekly(String fdate,String tdate,String meters){
		Map<String,Map<String,String>> map=new HashMap();
		Map<String,Integer> avgMap=new HashMap();
		Connection con=null;
		try{
			
			con=new DBUtil().getConnection();
			String sql="SELECT meter_serial_no,singlephase,twophase,threephase,noload,power_fail_date FROM power_fail_minutes WHERE "
					+" str_to_date(power_fail_date,'%d-%m-%Y')>= str_to_date('" + fdate + "','%d-%m-%Y') and "
					+" str_to_date(power_fail_date,'%d-%m-%Y')<= str_to_date('" + tdate + "','%d-%m-%Y')"
					+ " AND meter_serial_no IN ("+meters+") ";
		
			
			//System.out.println(sql);
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			DateUtilities du=new DateUtilities();
			while(rs.next()){
				int s1phase=(rs.getInt(2)==-1?0:rs.getInt(2))+(rs.getInt(3)==-1?0:rs.getInt(3))+(rs.getInt(4)==-1?0:rs.getInt(4));
				String meter=rs.getString(1);
				
				//System.out.println(s1phase+"\t"+avgMap);
				if(s1phase!=0){
				  String supply= du.parseTimeH(s1phase*60*1000);
				  if(map.containsKey(meter)){
					  map.get(meter).put(rs.getString(6), supply);
					  
					  avgMap.put(meter, avgMap.get(meter)+(s1phase));
				  }else{
					  HashMap valMap=new HashMap();
					  valMap.put(rs.getString(6), supply);
					  map.put(meter, valMap);
					  
					  avgMap.put(meter, s1phase);
				  }
				}
			}//while
			for(Map.Entry em:avgMap.entrySet()){
				//System.out.println(em.getKey()+"\t"+em.getValue());
				map.get(em.getKey()).put("32-00-0000", du.parseTimeH((Long.parseLong(em.getValue().toString())*60*1000)/map.get(em.getKey()).size()));
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
	public Map<String,Map<String,String>> getMonthlyPeakLoad(String frdate,List<String> meters){
		Map<String,Map<String,String>> map=new HashMap();		
		Connection con=null;
		try{
			
			frdate=frdate.replace("/", "-");
			con=new DBUtil().getConnection();
			DecimalFormat twodec=new DecimalFormat("#0.00");
			Map<String,String> mfMap=new HashMap();
			
			String sql="select * from feeder_live_status";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getString("imf")!=null)
				  mfMap.put(rs.getString("meter_serial_no"), rs.getString("imf"));
			}
//			 sql="SELECT meter_serial_no,SUBSTR(d4_date,1,10),MAX(GREATEST(lr,ly,lb)) FROM ls_data_stbl "
//					+ "WHERE meter_serial_no in ("+meters+") AND d4_date LIKE '%"+frdate+"%' GROUP BY meter_serial_no,SUBSTR(d4_date,1,10)";
			 sql="SELECT meter_serial_no,SUBSTR(d4_date,1,10),MAX(GREATEST(lr,ly,lb)) FROM ls_data_stbl "
						+ "WHERE d4_date LIKE '%"+frdate+"%' GROUP BY meter_serial_no,SUBSTR(d4_date,1,10)";
			
			
			System.out.println(sql);
			 rs=con.prepareStatement(sql).executeQuery();
			
			while(rs.next()){
				if(meters.contains(rs.getString(1))){
				String meter=rs.getString(1);
				double mf=mfMap.containsKey(meter)?Double.parseDouble(mfMap.get(meter)):1;
				
				  if(map.containsKey(meter)){
					  map.get(meter).put(rs.getString(2), twodec.format(rs.getDouble(3)*mf));					  
					 
				  }else{
					  HashMap valMap=new HashMap();
					  valMap.put(rs.getString(2), twodec.format(rs.getDouble(3)*mf));
					  map.put(meter, valMap);
					  
				  }
				}
				
			}//while
			
			for(Map.Entry em:map.entrySet()){
				Map<String,Double> valMap=(Map<String,Double>)em.getValue();
				map.get(em.getKey()).put("32-00-0000", ""+Collections.max(valMap.values()));
			}
			//System.out.println("peak----------"+map);
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
	
	public Map<String,EnergyInputBean> getEnergyInputDetails(String month,String meters){
		Map<String,EnergyInputBean> map=new HashMap();
		Connection con=null;
		String sql="";
		ResultSet rs=null;
		try{
			con=new DBUtil().getConnection();
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");			
			String currentDate=sdf.format(new java.util.Date());
			DecimalFormat fourDec=new DecimalFormat("#0.0000");
			
			Calendar calendar = Calendar.getInstance();  
			calendar.setTime(sdf.parse("01-"+month));  

			calendar.add(Calendar.MONTH, 1);  
			calendar.set(Calendar.DAY_OF_MONTH, 1);  
			//calendar.add(Calendar.DATE, -1); 
			
			month=month.split("-")[1]+"-"+month.split("-")[0];
			String nextMonth=sdf.format(calendar.getTime());
			nextMonth=nextMonth.split("-")[2]+"-"+nextMonth.split("-")[1];
			
//			System.out.println("pres month :"+month);
//			System.out.println("next month : "+nextMonth);
			
			sql="SELECT * FROM analytics_monthly_readings WHERE billing_timestamp LIKE '%"+month+"%' and meter_serial_no in ("+meters+")";
			
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				EnergyInputBean eb=new EnergyInputBean();
				if(rs.getString("integrate_by").equalsIgnoreCase("import")){
					eb.setKwh_initial(rs.getString("kwh_i"));
					eb.setKvah_initial(rs.getString("kvah_i"));
//					eb.setMd_kw(rs.getString("md_kw_i"));
//					eb.setMd_kva(rs.getString("md_kva_i"));
//					eb.setMd_kw_occdate(rs.getString("md_kw_i_timestamp"));
//					eb.setMd_kva_occdate(rs.getString("md_kva_i_timestamp"));
				}
                if(rs.getString("integrate_by").equalsIgnoreCase("abs")){
                	eb.setKwh_initial(rs.getString("kwh_abs"));
					eb.setKvah_initial(rs.getString("kvah_abs"));
//					eb.setMd_kw(rs.getString("md_kw_abs"));
//					eb.setMd_kva(rs.getString("md_kva_abs"));
//					eb.setMd_kw_occdate(rs.getString("md_kw_abs_timestamp"));
//					eb.setMd_kva_occdate(rs.getString("md_kva_abs_timestamp"));
				}                
                map.put(rs.getString("meter_serial_no"), eb);                
			}//initial
			
          sql="SELECT * FROM analytics_monthly_readings WHERE billing_timestamp LIKE '%"+nextMonth+"%' and meter_serial_no in ("+meters+")";
			
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(map.containsKey(rs.getString("meter_serial_no"))){
					EnergyInputBean eb=map.get(rs.getString("meter_serial_no"));
					if(rs.getString("integrate_by").equalsIgnoreCase("import")){
						eb.setKwh_final(rs.getString("kwh_i"));
						eb.setKvah_final(rs.getString("kvah_i"));
						eb.setMd_kw(rs.getString("md_kw_i"));
						eb.setMd_kva(rs.getString("md_kva_i"));
						eb.setMd_kw_occdate(rs.getString("md_kw_i_timestamp"));
						eb.setMd_kva_occdate(rs.getString("md_kva_i_timestamp"));
						
					}
	                if(rs.getString("integrate_by").equalsIgnoreCase("abs")){
	                	eb.setKwh_final(rs.getString("kwh_abs"));
						eb.setKvah_final(rs.getString("kvah_abs"));
						eb.setMd_kw(rs.getString("md_kw_abs"));
						eb.setMd_kva(rs.getString("md_kva_abs"));
						eb.setMd_kw_occdate(rs.getString("md_kw_abs_timestamp"));
						eb.setMd_kva_occdate(rs.getString("md_kva_abs_timestamp"));
						
					} 
	                map.put(rs.getString("meter_serial_no"), eb);
				}else{
					EnergyInputBean eb=new EnergyInputBean();
					if(rs.getString("integrate_by").equalsIgnoreCase("import")){
						eb.setKwh_final(rs.getString("kwh_i"));
						eb.setKvah_final(rs.getString("kvah_i"));
						eb.setMd_kw(rs.getString("md_kw_i"));
						eb.setMd_kva(rs.getString("md_kva_i"));
						eb.setMd_kw_occdate(rs.getString("md_kw_i_timestamp"));
						eb.setMd_kva_occdate(rs.getString("md_kva_i_timestamp"));
						
					}
	                if(rs.getString("integrate_by").equalsIgnoreCase("abs")){
	                	eb.setKwh_final(rs.getString("kwh_abs"));
						eb.setKvah_final(rs.getString("kvah_abs"));
						eb.setMd_kw(rs.getString("md_kw_abs"));
						eb.setMd_kva(rs.getString("md_kva_abs"));
						eb.setMd_kw_occdate(rs.getString("md_kw_abs_timestamp"));
						eb.setMd_kva_occdate(rs.getString("md_kva_abs_timestamp"));
						
					} 
	                map.put(rs.getString("meter_serial_no"), eb);
				}
                                
			}//final
			
			for(Map.Entry em:map.entrySet()){
				EnergyInputBean eb=(EnergyInputBean)em.getValue();
				if(eb.getKwh_initial()!=null && eb.getKwh_final()!=null)
					eb.setKwh_consumption(fourDec.format(Double.parseDouble(eb.getKwh_final())-Double.parseDouble(eb.getKwh_initial())));
				
				if(eb.getKvah_initial()!=null && eb.getKvah_final()!=null)
					eb.setKvah_consumption(fourDec.format(Double.parseDouble(eb.getKvah_final())-Double.parseDouble(eb.getKvah_initial())));
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
	
	public Map<String,EnergyInputBean> getEnergyInputDetails_manual(String month,List<String> meters){
		Map<String,EnergyInputBean> map=new HashMap();
		Connection con=null;
		String sql="";
		ResultSet rs=null;
		try{
			con=new DBUtil().getConnection();
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");			
			String currentDate=sdf.format(new java.util.Date());
			DecimalFormat fourDec=new DecimalFormat("#0.00");
			
			Calendar calendar = Calendar.getInstance();  
			calendar.setTime(sdf.parse("01-"+month));  

			calendar.add(Calendar.MONTH, 1);  
			calendar.set(Calendar.DAY_OF_MONTH, 1);  
			//calendar.add(Calendar.DATE, -1); 
			
			month=month.split("-")[0]+"-"+month.split("-")[1];
			
			sql="SELECT * FROM analytics_energy_input_report_manual WHERE month LIKE '%"+month+"%' ";
			
			System.out.println("-------------------"+sql);
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(meters.contains(rs.getString("meter_serial_no"))){
					EnergyInputBean eb=new EnergyInputBean();			
	                eb.setMeterno(rs.getString("meter_serial_no"));
	                eb.setEmf(rs.getString("mf"));
	                eb.setDatasource(rs.getString("datasource"));
	                if(rs.getString("assessed_units")!=null)
	                  eb.setAssessed_units(fourDec.format(rs.getDouble("assessed_units")));
	                eb.setKwh_initial(rs.getString("initial_reading"));
	                eb.setKwh_final(rs.getString("final_reading"));
	                
	                double cons=0;
	                if(rs.getString("final_reading")!=null && rs.getString("initial_reading")!=null)
	                	 cons=rs.getDouble("final_reading")-rs.getDouble("initial_reading");
	                	
	                	eb.setConsumption(fourDec.format(cons));
	                	cons=cons*rs.getDouble("mf");
	                if(rs.getString("assessed_units")!=null)
	                	cons=cons+rs.getDouble("assessed_units");
	                 eb.setKwh_consumption(fourDec.format((cons)));
	                
	                map.put(rs.getString("meter_serial_no"), eb);        
				}
			}//initial
			
          
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
	
	public String updateMonthlyEAValues(String meter,String month,String initial,String finalVal,String asu,String mf){
		String result="";
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			String sql="update analytics_energy_input_report_manual set mf=?,initial_reading=?,final_reading=?,assessed_units=?,datasource=? where meter_serial_no=? and month=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, mf);
			ps.setString(2, initial);
			ps.setString(3, finalVal);
			ps.setString(4, asu);
			ps.setString(5, "MANUAL");
			ps.setString(6, meter);
			ps.setString(7, month);
			
			if(ps.executeUpdate()>0)
				result="Updated...";
			else
				result="Failed...";
			
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
		return result;
	}
	public Map<String,List<String>> getEnergyInputDetails_month(String month,String meters){
		Map<String,List<String>> map=new HashMap();
		Connection con=null;
		String sql="";
		ResultSet rs=null;
		try{
			con=new DBUtil().getConnection();
			
			
//			sql="SELECT * FROM analytics_meter_daily_readings WHERE meter_serial_no IN ("+meters+")"
//					+ " AND MONTH='-"+month+"' and parameter='KWH' order by meter_serial_no";
			
			sql="SELECT * FROM analytics_meter_daily_consumption WHERE meter_serial_no IN ("+meters+")"
					+ " AND MONTH='-"+month+"' and parameter='KWH' order by meter_serial_no";
			
			//System.out.println("-----------"+sql);
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){	
				List<String> dates=new ArrayList();
				dates.add(rs.getString("d1"));
				dates.add(rs.getString("d2"));
				dates.add(rs.getString("d3"));
				dates.add(rs.getString("d4"));
				dates.add(rs.getString("d5"));
				dates.add(rs.getString("d6"));
				dates.add(rs.getString("d7"));
				dates.add(rs.getString("d8"));
				dates.add(rs.getString("d9"));
				dates.add(rs.getString("d10"));
				dates.add(rs.getString("d11"));
				dates.add(rs.getString("d12"));
				dates.add(rs.getString("d13"));
				dates.add(rs.getString("d14"));
				dates.add(rs.getString("d15"));
				dates.add(rs.getString("d16"));
				dates.add(rs.getString("d17"));
				dates.add(rs.getString("d18"));
				dates.add(rs.getString("d19"));
				dates.add(rs.getString("d20"));
				dates.add(rs.getString("d21"));
				dates.add(rs.getString("d22"));
				dates.add(rs.getString("d23"));
				dates.add(rs.getString("d24"));
				dates.add(rs.getString("d25"));
				dates.add(rs.getString("d26"));
				dates.add(rs.getString("d27"));
				dates.add(rs.getString("d28"));
				dates.add(rs.getString("d29"));
				dates.add(rs.getString("d30"));
				dates.add(rs.getString("d31"));
				
				map.put(rs.getString("meter_serial_no"), dates);
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
	
	public Map<String,EnergyInputBean> getEnergyInputDetails_daily(String date,String meters){
		Map<String,EnergyInputBean> map=new HashMap();
		Connection con=null;
		String sql="";
		ResultSet rs=null;
		try{
			con=new DBUtil().getConnection();
			String month=date.substring(date.indexOf("-"));
			int day=Integer.parseInt(date.split("-")[0]);
			
			//System.out.println(date+"\t"+new DateUtilities().getLastDayOfTheMonth("01"+month));
//			sql="SELECT meter_serial_no,adopted_ptr,meter_ptr,adopted_ctr,meter_ctr,mf FROM meter WHERE mf IS NOT NULL "
//					+ " and meter_serial_no in ("+meters+")";
//			rs=con.prepareStatement(sql).executeQuery();
//			while(rs.next()){
//				
//			}
			sql="SELECT meter_serial_no,parameter,d"+day+" FROM analytics_meter_daily_readings WHERE meter_serial_no IN ("+meters+")"
					+ " AND MONTH='"+month+"' order by meter_serial_no";
			
			//System.out.println("-----------"+sql);
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(map.containsKey(rs.getString(1))){
					if(rs.getString(2).equalsIgnoreCase("kwh")) map.get(rs.getString(1)).setKwh_initial(rs.getString(3));
					if(rs.getString(2).equalsIgnoreCase("kvah")) map.get(rs.getString(1)).setKvah_initial(rs.getString(3));
				}else{
					EnergyInputBean eb=new EnergyInputBean();
					if(rs.getString(2).equalsIgnoreCase("kwh")) eb.setKwh_initial(rs.getString(3));
					if(rs.getString(2).equalsIgnoreCase("kvah")) eb.setKvah_initial(rs.getString(3));
					
					map.put(rs.getString(1), eb);
				}				
			}//initial
			
			String final_month=month;
			int final_day=day;
			if(date.equals(new DateUtilities().getLastDayOfTheMonth("01"+month))){
				date=new DateUtilities().add_Oneday(date);
				final_month=date.substring(date.indexOf("-"));
				final_day=Integer.parseInt(date.split("-")[0]);
			}else{
				final_day=day+1;
			}
			sql="SELECT meter_serial_no,parameter,d"+final_day+" FROM analytics_meter_daily_readings WHERE meter_serial_no IN ("+meters+")"
					+ " AND MONTH='"+final_month+"' order by meter_serial_no";
			
			//System.out.println("-----------"+sql);
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(map.containsKey(rs.getString(1))){
					if(rs.getString(2).equalsIgnoreCase("kwh")) map.get(rs.getString(1)).setKwh_final(rs.getString(3));
					if(rs.getString(2).equalsIgnoreCase("kvah")) map.get(rs.getString(1)).setKvah_final(rs.getString(3));
				}else{
					EnergyInputBean eb=new EnergyInputBean();
					if(rs.getString(2).equalsIgnoreCase("kwh")) eb.setKwh_final(rs.getString(3));
					if(rs.getString(2).equalsIgnoreCase("kvah")) eb.setKvah_final(rs.getString(3));
					
					map.put(rs.getString(1), eb);
				}				
			}//final
			sql="SELECT meter_serial_no,parameter,d"+day+" FROM analytics_meter_daily_consumption WHERE meter_serial_no IN ("+meters+")"
					+ " AND MONTH='"+month+"' order by meter_serial_no";
			
			//System.out.println("-----------"+sql);
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(map.containsKey(rs.getString(1))){
					if(rs.getString(2).equalsIgnoreCase("kwh")) map.get(rs.getString(1)).setKwh_consumption(rs.getString(3));
					if(rs.getString(2).equalsIgnoreCase("kvah")) map.get(rs.getString(1)).setKvah_consumption(rs.getString(3));
				}else{
					EnergyInputBean eb=new EnergyInputBean();
					if(rs.getString(2).equalsIgnoreCase("kwh")) eb.setKwh_consumption(rs.getString(3));
					if(rs.getString(2).equalsIgnoreCase("kvah")) eb.setKvah_consumption(rs.getString(3));
					
					map.put(rs.getString(1), eb);
				}				
			}//consumption
			
			
			month=month.split("-")[2]+"-"+month.split("-")[1]+"-";
			sql="SELECT meter_serial_no,d"+day+" FROM analytics_meter_daily_demands WHERE meter_serial_no IN ("+meters+")"
					+ " AND MONTH='"+month+"' and d"+day+" is not null order by meter_serial_no";
			
			//System.out.println("-----------"+sql);
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(map.containsKey(rs.getString(1))){
					map.get(rs.getString(1)).setMd_kw(rs.getString(2).split(",")[0]);
					map.get(rs.getString(1)).setMd_kw_occdate(rs.getString(2).split(",")[1]);
					map.get(rs.getString(1)).setMd_kva(rs.getString(2).split(",")[2]);
					map.get(rs.getString(1)).setMd_kva_occdate(rs.getString(2).split(",")[3]);
				}else{
					EnergyInputBean eb=new EnergyInputBean();
					eb.setMd_kw(rs.getString(2).split(",")[0]);
					eb.setMd_kw_occdate(rs.getString(2).split(",")[1]);
					eb.setMd_kva(rs.getString(2).split(",")[2]);
					eb.setMd_kva_occdate(rs.getString(2).split(",")[3]);
					map.put(rs.getString(1), eb);
				}				
			}//md values
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
	
	public Map<String,ReliabilityIndexBean> getReliabilityIndeces(String month,List<String> meters){
		Map<String,ReliabilityIndexBean> map=new HashMap();
		Connection con=null;
		String sql="";
		ResultSet rs=null;
		DecimalFormat twodec=new DecimalFormat("#0.00");
		try{
			con=new DBUtil().getConnection();
			month=month.replace("/", "-");
			
			Map<String,Integer> cons_map=new HashMap();
			Map<String,Integer> sus_intrruts=new HashMap();
			Map<String,Integer> sus_duration=new HashMap();
			Map<String,Integer> trans_intrruts=new HashMap();
			
			sql="SELECT m.meter_serial_no,f.no_of_consumers FROM meter m, feeder f WHERE m.location_id=f.feederid";
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getString(2)==null) cons_map.put(rs.getString(1), 1);
				else cons_map.put(rs.getString(1), rs.getInt(2));
			}
			sql="SELECT meter_serial_no,totalinterrupts FROM outage_power_fail_interrupts_sustained WHERE MONTH LIKE '%"+month+"'";
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				sus_intrruts.put(rs.getString(1), rs.getInt(2));
			}
			sql="SELECT meter_serial_no,totaloutage FROM outage_power_fail_minutes_sustained WHERE MONTH LIKE '%"+month+"'";
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				sus_duration.put(rs.getString(1), rs.getInt(2));
			}
			
			sql="SELECT meter_serial_no,totalinterrupts FROM outage_power_fail_interrupts_transiant WHERE MONTH LIKE '%"+month+"'";
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				trans_intrruts.put(rs.getString(1), rs.getInt(2));
			}
			
			for(String mt:meters){
				ReliabilityIndexBean ri=new ReliabilityIndexBean();
				if(cons_map.containsKey(mt)){
				  int no_of_cons=cons_map.get(mt);
				  ri.setTot_consumers(""+no_of_cons);
				  ri.setFeeder(mt);
				  ri.setTot_interrupts(sus_intrruts.containsKey(mt)?""+sus_intrruts.get(mt):"");
				  ri.setTot_i_duration(sus_duration.containsKey(mt)?""+sus_duration.get(mt):"");
				  try{
					  ri.setSaifi(twodec.format(Double.parseDouble(ri.getTot_interrupts())/no_of_cons));
				  }catch(Exception e){}
				  try{
					  ri.setSaidi(twodec.format(Double.parseDouble(ri.getTot_i_duration())/no_of_cons));
				  }catch(Exception e){}
				  try{
					  ri.setCaidi(twodec.format(Double.parseDouble(ri.getTot_i_duration())/Double.parseDouble(ri.getTot_interrupts())));
				  }catch(Exception e){}
				  try{
					  ri.setMaifi(twodec.format(trans_intrruts.get(mt)/no_of_cons));
				   }catch(Exception e){}
				  
				}
				map.put(mt,ri);
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
	
	public Map<String,BusbarLossesBean> getBusbarLossesBean(String month,List<String> meters){
		Map<String,BusbarLossesBean> map=new HashMap();
		Connection con=null;
		String sql="";
		ResultSet rs=null;
		DecimalFormat twodec=new DecimalFormat("#0.00");
		try{
			con=new DBUtil().getConnection();
			month=month.replace("/", "-");
			
			sql="SELECT * FROM analytics_meter_daily_readings WHERE MONTH='-"+month+"' and parameter='KWH'";
			//System.out.println("Busbar losses : "+sql);
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(meters.contains(rs.getString("meter_serial_no"))){
					BusbarLossesBean blb=new BusbarLossesBean();
					blb.setInitial_kwh(rs.getString("d1"));
					map.put(rs.getString("meter_serial_no"), blb);
				}
			}
			
			//-----------getting the next month details ----------------
			month=(Integer.parseInt(month.split("-")[0])+1)+"-"+month.split("-")[1];
			sql="SELECT * FROM analytics_meter_daily_readings WHERE MONTH like '%"+month+"' and parameter='KWH'";
			//System.out.println("Busbar losses : "+sql);
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(meters.contains(rs.getString("meter_serial_no"))){
					if(map.containsKey(rs.getString("meter_serial_no"))){
						BusbarLossesBean blb=map.get(rs.getString("meter_serial_no"));
						blb.setFinal_kwh(rs.getString("d1"));
					}else{
						BusbarLossesBean blb=new BusbarLossesBean();
						blb.setFinal_kwh(rs.getString("d1"));
						map.put(rs.getString("meter_serial_no"), blb);
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
	
public Map<String,LoadCurveBean> getLoadCurveValues(String date,List<String> meters){
	Map<String,LoadCurveBean> map=new HashMap();
	Connection con=null;
	try{
		con=new DBUtil().getConnection();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		date=sdf.format(sdf1.parse(date));
		DecimalFormat twodec=new DecimalFormat("#0.00");
		
		
		String sql="SELECT t.meter_sl_no,(l.h0*t.mf) AS  h0,(l.h1*t.mf) AS  h1,(l.h2*t.mf) AS  h2,(l.h3*t.mf) AS  h3,(l.h4*t.mf) AS  h4,(l.h5*t.mf) AS  h5,(l.h6*t.mf) AS  h6,(l.h7*t.mf) AS  h7,(l.h8*t.mf) AS  h8,(l.h9*t.mf) AS  h9,(l.h10*t.mf) AS  h10,(l.h11*t.mf) AS  h11,(l.h12*t.mf) AS  h12,(l.h13*t.mf) AS  h13,(l.h14*t.mf) AS  h14,(l.h15*t.mf) AS  h15,(l.h16*t.mf) AS  h16,(l.h17*t.mf) AS  h17,(l.h18*t.mf) AS  h18,(l.h19*t.mf) AS  h19,(l.h20*t.mf) AS  h20,(l.h21*t.mf) AS  h21,(l.h22*t.mf) AS  h22,(l.h23*t.mf) AS  h23,t.location_category FROM analyitics_houly_loadprofile_kwh l,total_masterdata t "
				+ "WHERE l.comm_date='"+date+"' AND l.meter_serial_no=t.meter_sl_no  ";
		
		System.out.println("load curve in freports : "+sql);
		
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
		  if(meters.contains(rs.getString(1))){
			LoadCurveBean lcb=new LoadCurveBean();
			lcb.setH1(twodec.format(rs.getDouble(2)));
			lcb.setH2(twodec.format(rs.getDouble(3)));
			lcb.setH3(twodec.format(rs.getDouble(4)));
			lcb.setH4(twodec.format(rs.getDouble(5)));
			lcb.setH5(twodec.format(rs.getDouble(6)));
			lcb.setH6(twodec.format(rs.getDouble(7)));
			lcb.setH7(twodec.format(rs.getDouble(8)));
			lcb.setH8(twodec.format(rs.getDouble(9)));
			lcb.setH9(twodec.format(rs.getDouble(10)));
			lcb.setH10(twodec.format(rs.getDouble(11)));
			lcb.setH11(twodec.format(rs.getDouble(12)));
			lcb.setH12(twodec.format(rs.getDouble(13)));
			lcb.setH13(twodec.format(rs.getDouble(14)));
			lcb.setH14(twodec.format(rs.getDouble(15)));
			lcb.setH15(twodec.format(rs.getDouble(16)));
			lcb.setH16(twodec.format(rs.getDouble(17)));
			lcb.setH17(twodec.format(rs.getDouble(18)));
			lcb.setH18(twodec.format(rs.getDouble(19)));
			lcb.setH19(twodec.format(rs.getDouble(20)));
			lcb.setH20(twodec.format(rs.getDouble(21)));
			lcb.setH21(twodec.format(rs.getDouble(22)));
			lcb.setH22(twodec.format(rs.getDouble(23)));
			lcb.setH23(twodec.format(rs.getDouble(24)));
			lcb.setH24(twodec.format(rs.getDouble(25)));
			
			map.put(rs.getString(1), lcb);
			}//if
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

public Map<String,LoadCurveBean> getLoadCurveGraphValues(String meter){
	Map<String,LoadCurveBean> map=new LinkedHashMap();
	Connection con=null;
	try{
		
		con=new DBUtil().getConnection();
		DecimalFormat twodec=new DecimalFormat("#0.00");
		
		
		String sql="SELECT l.comm_date,(l.h0*t.mf) AS  h0,(l.h1*t.mf) AS  h1,(l.h2*t.mf) AS  h2,(l.h3*t.mf) AS  h3,(l.h4*t.mf) AS  h4,(l.h5*t.mf) AS  h5,(l.h6*t.mf) AS  h6,(l.h7*t.mf) AS  h7,(l.h8*t.mf) AS  h8,(l.h9*t.mf) AS  h9,(l.h10*t.mf) AS  h10,(l.h11*t.mf) AS  h11,(l.h12*t.mf) AS  h12,(l.h13*t.mf) AS  h13,(l.h14*t.mf) AS  h14,(l.h15*t.mf) AS  h15,(l.h16*t.mf) AS  h16,(l.h17*t.mf) AS  h17,(l.h18*t.mf) AS  h18,(l.h19*t.mf) AS  h19,(l.h20*t.mf) AS  h20,(l.h21*t.mf) AS  h21,(l.h22*t.mf) AS  h22,(l.h23*t.mf) AS  h23,t.location_category FROM analyitics_houly_loadprofile_kwh l,total_masterdata t "
				+ "WHERE l.meter_serial_no=t.meter_sl_no and t.meter_sl_no='"+meter+"' ";
		
		//System.out.println("load curve in freports : "+sql);
		
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
		  
			LoadCurveBean lcb=new LoadCurveBean();
			lcb.setH1(twodec.format(rs.getDouble(2)));
			lcb.setH2(twodec.format(rs.getDouble(3)));
			lcb.setH3(twodec.format(rs.getDouble(4)));
			lcb.setH4(twodec.format(rs.getDouble(5)));
			lcb.setH5(twodec.format(rs.getDouble(6)));
			lcb.setH6(twodec.format(rs.getDouble(7)));
			lcb.setH7(twodec.format(rs.getDouble(8)));
			lcb.setH8(twodec.format(rs.getDouble(9)));
			lcb.setH9(twodec.format(rs.getDouble(10)));
			lcb.setH10(twodec.format(rs.getDouble(11)));
			lcb.setH11(twodec.format(rs.getDouble(12)));
			lcb.setH12(twodec.format(rs.getDouble(13)));
			lcb.setH13(twodec.format(rs.getDouble(14)));
			lcb.setH14(twodec.format(rs.getDouble(15)));
			lcb.setH15(twodec.format(rs.getDouble(16)));
			lcb.setH16(twodec.format(rs.getDouble(17)));
			lcb.setH17(twodec.format(rs.getDouble(18)));
			lcb.setH18(twodec.format(rs.getDouble(19)));
			lcb.setH19(twodec.format(rs.getDouble(20)));
			lcb.setH20(twodec.format(rs.getDouble(21)));
			lcb.setH21(twodec.format(rs.getDouble(22)));
			lcb.setH22(twodec.format(rs.getDouble(23)));
			lcb.setH23(twodec.format(rs.getDouble(24)));
			lcb.setH24(twodec.format(rs.getDouble(25)));
			
			map.put(rs.getString(1), lcb);
			
			
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
public Map<String,LoadCurveBean> getLoadCurveGraphValues_hierarchy(String level,String ids){
	Connection con=null;
	Map<String,LoadCurveBean> map=new LinkedHashMap();
	try{
		DecimalFormat twodec=new DecimalFormat("#0.00");
		System.out.println("Level : "+level+" Level ID : "+ids);
		level=level.split("_")[1];
		
		String sql="SELECT * FROM (SELECT comm_date,location_category,(SUM(h0)/1000+SUM(h1)/1000+SUM(h2)/1000+SUM(h3)/1000+SUM(h4)/1000+SUM(h5)/1000+SUM(h6)/1000+SUM(h7)/1000+SUM(h8)/1000+SUM(h9)/1000+SUM(h10)/1000+SUM(h11)/1000+SUM(h12)/1000+SUM(h13)/1000+SUM(h14)/1000+SUM(h15)/1000+SUM(h16)/1000+SUM(h17)/1000+SUM(h18)/1000+SUM(h19)/1000+SUM(h20)/1000+SUM(h21)/1000+SUM(h22)/1000+SUM(h23)/1000) AS SUM FROM "
				+ "(SELECT l.comm_date AS comm_date,(l.h0*t.mf) AS  h0,(l.h1*t.mf) AS  h1,(l.h2*t.mf) AS  h2,(l.h3*t.mf) AS  h3,(l.h4*t.mf) AS  h4,(l.h5*t.mf) AS  h5,(l.h6*t.mf) AS  h6,(l.h7*t.mf) AS  h7,(l.h8*t.mf) AS  h8,(l.h9*t.mf) AS  h9,(l.h10*t.mf) AS  h10,(l.h11*t.mf) AS  h11,(l.h12*t.mf) AS  h12,(l.h13*t.mf) AS  h13,(l.h14*t.mf) AS  h14,(l.h15*t.mf) AS  h15,(l.h16*t.mf) AS  h16,(l.h17*t.mf) AS  h17,(l.h18*t.mf) AS  h18,(l.h19*t.mf) AS  h19,(l.h20*t.mf) AS  h20,(l.h21*t.mf) AS  h21,(l.h22*t.mf) AS  h22,(l.h23*t.mf) AS  h23,t.location_category FROM analyitics_houly_loadprofile_kwh l,total_masterdata t "
				+ "WHERE  l.meter_serial_no=t.meter_sl_no  ";
						
				if(level=="1") sql=sql+ "and t.region_id IN ("+ids+") ";
				if(level=="2") sql=sql+ "and t.circle_id IN ("+ids+") ";
				if(level=="3") sql=sql+ "and t.division_id IN ("+ids+") ";
				if(level=="4") sql=sql+ "and t.sd_id IN ("+ids+") ";
				if(level=="5") sql=sql+ "and t.ss_id IN ("+ids+") ";
				if(level=="6") sql=sql+ "and t.ptr_id IN ("+ids+") ";
				
			 sql=sql+ ")AS t GROUP BY comm_date,location_category )AS tab ORDER BY STR_TO_DATE(comm_date,'%d-%m-%Y')";
			 
			 System.out.println("Query for Load curve : "+sql);
		 con=new DBUtil().getConnection();
		 ResultSet rs=con.prepareStatement(sql).executeQuery();
		 while(rs.next()){
			 if(map.containsKey(rs.getString(1))){
				 LoadCurveBean lb=map.get(rs.getString(1));
				 if(rs.getString(2).equals("AG")) lb.setAg(twodec.format(rs.getDouble(3)));
				 if(rs.getString(2).equals("NON AG")) lb.setNonag(twodec.format(rs.getDouble(3)));
					 map.put(rs.getString(1),lb );
				 
			 }else{
				 LoadCurveBean lb=new LoadCurveBean();
				 if(rs.getString(2).equals("AG")) lb.setAg(twodec.format(rs.getDouble(3)));
				 if(rs.getString(2).equals("NON AG")) lb.setNonag(twodec.format(rs.getDouble(3)));
					 map.put(rs.getString(1),lb );
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
public List<Oluvovpf> getOluvovpfValues(String month,List<String> meters,String type){
	List<Oluvovpf> map=new ArrayList();
	Connection con=null;
	ResultSet rs=null;
	String sql="";
	try{
		con=new DBUtil().getConnection();
		month=month.replace("/", "-");
		DecimalFormat twodec=new DecimalFormat("#0.00");
		Map<String,Double> mfmap=new HashMap();
		
		sql="select meter_serial_no,imf,vmf,mf from feeder_live_status";
		rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			if(type.equalsIgnoreCase("ol"))
				mfmap.put(rs.getString(1), rs.getString(2)==null?1:rs.getDouble(2));
			if(type.equalsIgnoreCase("uv") || type.equalsIgnoreCase("ov"))
				mfmap.put(rs.getString(1), rs.getString(3)==null?1:rs.getDouble(3));
		}
		
		if(type.equalsIgnoreCase("ol")){
			Map<String,String> conductorMap=new HashMap();
			Map<String,Integer> valsMap=new HashMap();
			valsMap.put("Rabbit", 148);
			valsMap.put("Weasel", 100);
			valsMap.put("Squirrel", 70);
			valsMap.put("Raccoon", 197);
			valsMap.put("Dog", 254);
			valsMap.put("Panther", 254);
			
			 sql="SELECT t.meter_sl_no,f.conductor FROM total_masterdata t, feeder f WHERE f.feederid=t.location_id";
			 rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(rs.getString(2)!=null)
				  conductorMap.put(rs.getString(1), rs.getString(2));
			}
			
			sql="SELECT meter_serial_no,GREATEST(lr,ly,lb),adjusted_rtc FROM feeder_daily_live_status WHERE adjusted_rtc LIKE '%"+month+"%'";
			//System.out.println("--------->"+sql);
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
			 if(meters.contains(rs.getString(1))){
				double mf=mfmap.containsKey(rs.getString(1))?mfmap.get(rs.getString(1)):1;
				double curr=rs.getDouble(2)*mf;
			    if(conductorMap.containsKey(rs.getString(1))){
			    	String cType=conductorMap.get(rs.getString(1));
			    	if(valsMap.containsKey(cType)){
			    		int cVal=valsMap.get(cType);
			    		
			    		if(curr>=cVal) {
			    			Oluvovpf of=new Oluvovpf();
			    			of.setMeter_serial_no(rs.getString(1));
			    			of.setDate(rs.getString(3).split(" ")[0]);
			    			of.setImf(""+mf);
			    			of.setValue(twodec.format(curr));
			    			
							map.add(of);
			    		}
			    	}
			    }
				}//if
			}//while
		}
		if(type.equalsIgnoreCase("uv") || type.equalsIgnoreCase("ov")){
			Map<String,Integer> capacityMap=new HashMap();
			sql="select meter_sl_no,supply_voltage from total_masterdata";
			 rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				capacityMap.put(rs.getString(1), rs.getString(2).contains("11")?11000:33000);
			}
			
			sql="SELECT meter_serial_no,GREATEST(vr,vy,vb),adjusted_rtc FROM feeder_daily_live_status WHERE adjusted_rtc LIKE '%"+month+"%'";
			//System.out.println("--------->"+sql);
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
			 if(meters.contains(rs.getString(1))){
				double mf=mfmap.containsKey(rs.getString(1))?mfmap.get(rs.getString(1)):1;
				int capacity=capacityMap.containsKey(rs.getString(1))?capacityMap.get(rs.getString(1)):1;
				double vol=rs.getDouble(2)*1.732*mf;
			
				double percent=(((capacity)-vol)/(capacity))*100;
				
				if(type.equals("ov")){
					if(percent<0){
					percent=Math.abs(percent);
					if(percent>6 && percent<20) {
						Oluvovpf of=new Oluvovpf();
		    			of.setMeter_serial_no(rs.getString(1));
		    			of.setDate(rs.getString(3).split(" ")[0]);
		    			of.setVmf(""+mf);
		    			of.setValue(twodec.format(vol/1000));
		    			
		    			map.add(of);
						
					
					}
					}
					
				}
                if(type.equals("uv")){
                	if(percent>8 && percent < 9) {
                		Oluvovpf of=new Oluvovpf();
		    			of.setMeter_serial_no(rs.getString(1));
		    			of.setDate(rs.getString(3).split(" ")[0]);
		    			of.setVmf(""+mf);
		    			of.setValue(twodec.format(vol/1000));
		    			
		    			map.add(of);
                	}
				}
			}//if
			}//while
		}
		if(type.equalsIgnoreCase("pf")){
			sql="SELECT meter_serial_no,pfavg,adjusted_rtc FROM feeder_daily_live_status WHERE adjusted_rtc LIKE '%"+month+"%'";
			System.out.println("--------->"+sql);
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(meters.contains(rs.getString(1))){
				if(Math.abs(rs.getDouble(2))<0.8){
					Oluvovpf of=new Oluvovpf();
	    			of.setMeter_serial_no(rs.getString(1));	 
	    			of.setDate(rs.getString(3).split(" ")[0]);
	    			of.setValue(rs.getString(2));	
	    			
	    			map.add(of);
				}
				}//if
			}//while
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

public Map<String,PowerFailuresBean> getPowerFailures(String date,List<String> meters){
	Map<String,PowerFailuresBean> map=new HashMap();
	Connection con=null;
	try{
		con=new DBUtil().getConnection();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		date=sdf.format(sdf1.parse(date));
		DecimalFormat twodec=new DecimalFormat("#0.00");
		
		String month=date.substring(date.indexOf("-"));
		
		int day=Integer.parseInt(date.split("-")[0]);
		
		String sql="SELECT *  FROM outage_power_fail_interrupts_sustained WHERE MONTH='"+month+"'";
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		
		while(rs.next()){
			if(meters.contains(rs.getString("meter_serial_no"))){
			PowerFailuresBean pf=new PowerFailuresBean();
			pf.setMeter_serial_no(rs.getString("meter_serial_no"));
			pf.setSust_pf(rs.getString("d"+day));
			
			map.put(rs.getString("meter_serial_no"), pf);
			}
			
		}
		
		 sql="SELECT *  FROM outage_power_fail_interrupts_transiant WHERE MONTH='"+month+"'";
		 rs=con.prepareStatement(sql).executeQuery();
		
		while(rs.next()){
			if(meters.contains(rs.getString("meter_serial_no"))){
			if(map.containsKey(rs.getString("meter_serial_no"))){
				PowerFailuresBean pf=map.get(rs.getString("meter_serial_no"));
				pf.setTrans_pf(rs.getString("d"+day));
				map.put(rs.getString(1), pf);
			}else{
				PowerFailuresBean pf=new PowerFailuresBean();
				pf.setMeter_serial_no(rs.getString("meter_serial_no"));
				pf.setTrans_pf(rs.getString("d"+day));
				map.put(rs.getString(1), pf);
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
public Map<String,PowerFailuresBean> getPowerFailures(String fromdate,String todate,List<String> meters){
	Map<String,PowerFailuresBean> map=new HashMap();
	Connection con=null;
	try{
		con=new DBUtil().getConnection();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		fromdate=sdf.format(sdf1.parse(fromdate));
		todate=sdf.format(sdf1.parse(todate));
		DecimalFormat twodec=new DecimalFormat("#0.00");
		
		String frmonth=fromdate.substring(fromdate.indexOf("-"));
		String tomonth=todate.substring(todate.indexOf("-"));
		
		int frday=Integer.parseInt(fromdate.split("-")[0]);
		int today=Integer.parseInt(todate.split("-")[0]);
		
		String sql="SELECT *  FROM outage_power_fail_interrupts_sustained WHERE MONTH in ('"+frmonth+"','"+tomonth+"')";
		System.out.println(sql);
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		
		while(rs.next()){
			if(meters.contains(rs.getString("meter_serial_no"))){
				int pfs=0;
				if(frmonth.equals(tomonth)){
					for(int i=frday;i<=today;i++){
						pfs=pfs+rs.getInt("d"+i);
					}
				}else{
					if(rs.getString("month").equals(frmonth)){
						for(int i=frday;i<=31;i++){
							pfs=pfs+rs.getInt("d"+i);
						}
					}
					if(rs.getString("month").equals(tomonth)){
						for(int i=1;i<=today;i++){
							pfs=pfs+rs.getInt("d"+i);
						}
					}
				}
				if(map.containsKey(rs.getString("meter_serial_no"))){
					PowerFailuresBean pf=map.get(rs.getString("meter_serial_no"));
					pf.setSust_pf(""+(pfs+Integer.parseInt(pf.getSust_pf())));
					map.put(rs.getString(1), pf);
					map.put(rs.getString("meter_serial_no"), pf);
				}else{	
					PowerFailuresBean pf=new PowerFailuresBean();
					pf.setMeter_serial_no(rs.getString("meter_serial_no"));
					pf.setSust_pf(""+pfs);
					map.put(rs.getString("meter_serial_no"), pf);
				}
			
			}
			
		}
		
		 sql="SELECT *  FROM outage_power_fail_interrupts_transiant WHERE MONTH in ('"+frmonth+"','"+tomonth+"')";
		 rs=con.prepareStatement(sql).executeQuery();
		
		while(rs.next()){
			if(meters.contains(rs.getString("meter_serial_no"))){
				int pfs=0;
				if(frmonth.equals(tomonth)){
					for(int i=frday;i<=today;i++){
						pfs=pfs+rs.getInt("d"+i);
					}
				}else{
					if(rs.getString("month").equals(frmonth)){
						for(int i=frday;i<=31;i++){
							pfs=pfs+rs.getInt("d"+i);
						}
					}
					if(rs.getString("month").equals(tomonth)){
						for(int i=1;i<=today;i++){
							pfs=pfs+rs.getInt("d"+i);
						}
					}
				}
			if(map.containsKey(rs.getString("meter_serial_no"))){
				PowerFailuresBean pf=map.get(rs.getString("meter_serial_no"));
				pf.setTrans_pf(""+pfs);
				map.put(rs.getString(1), pf);
			}else{
				PowerFailuresBean pf=new PowerFailuresBean();
				pf.setMeter_serial_no(rs.getString("meter_serial_no"));
				pf.setTrans_pf(""+pfs);
				map.put(rs.getString(1), pf);
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

public Map<String,Integer> getEventsSummary(String month,List<String> meters){
	Map<String,Integer> map=new HashMap();
	Connection con=null;
	String sql="";
	try{
		con=new DBUtil().getConnection();
		
		month=month.replace("/", "-");
		
		sql="SELECT * FROM events_summary WHERE event_date LIKE '%"+month+"%' order by name";
		
		System.out.println("-----------------------"+sql);
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			if(meters.contains(rs.getString("meter_serial_no"))){
				if(map.containsKey(rs.getString("name"))){
					map.put(rs.getString("name"), map.get(rs.getString("name"))+rs.getInt("interrupts"));
				}else{
					map.put(rs.getString("name"), rs.getInt("interrupts"));
				}
			}
		}//while
		
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

public List<String> getEventsSummaryDetails(String month,List<String> meters,String type){
	List<String> list=new ArrayList();
	Connection con=null;
	String sql="";
	try{
		con=new DBUtil().getConnection();
		String sep="@";
		month=month.replace("/", "-");
		
		sql="SELECT * FROM events_summary WHERE event_date LIKE '%"+month+"%' and name='"+type+"' and interrupts>0 order by event_date";
		
		System.out.println("-----------------------"+sql);
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			if(meters.contains(rs.getString("meter_serial_no"))){
			  list.add(rs.getString("meter_serial_no")+sep+rs.getString("event_date")+sep+rs.getString("last_o_timestamp")+sep+rs.getString("last_r_timestamp")+sep+rs.getString("interrupts"));
			}
		}//while
		
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
	
	return list;
}
public Map<String,String> getMonthlyMeterEvents(){
	Map<String,String> list=new HashMap();
	Connection con=null;
	try{
		con=new DBUtil().getConnection();
		String sql="SELECT * from events ORDER BY event_type";
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			list.put(rs.getString("event_code"),rs.getString("event_type"));
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
	return list;
}
public Map<String,EventsSummary_meterBean> getMonthlyMeterEventsAction(String meter,String year,String event){
	Map<String,EventsSummary_meterBean> list=new HashMap();
	Connection con=null;
	try{
		con=new DBUtil().getConnection();
		String sql="SELECT * FROM events_summary WHERE code='"+event+"' and event_date LIKE '%"+year+"%' and meter_serial_no='"+meter+"' order by log_timestamp";
		System.out.println("---------------->>"+sql);
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			EventsSummary_meterBean ev=null;
			if(list.containsKey(rs.getString("event_date").substring(3))){
				ev=list.get(rs.getString("event_date").substring(3));
				if(rs.getInt("final_status")==1){
					ev.setEvent_no_without_restore(ev.getEvent_no_without_restore()+1);
					ev.setEvent_no_without_restore_duration(ev.getEvent_no_without_restore_duration()+rs.getInt("duration"));
				}
				else{
					ev.setEvent_no_with_restore(ev.getEvent_no_with_restore()+1);
					ev.setEvent_no_with_restore_duration(ev.getEvent_no_with_restore_duration()+rs.getInt("duration"));
				}
			}else{
				 ev=new EventsSummary_meterBean();
				if(rs.getInt("final_status")==1){
					ev.setEvent_no_without_restore(ev.getEvent_no_without_restore()+1);
					ev.setEvent_no_without_restore_duration(ev.getEvent_no_without_restore_duration()+rs.getInt("duration"));
				}
				else{
					ev.setEvent_no_with_restore(ev.getEvent_no_with_restore()+1);
					ev.setEvent_no_with_restore_duration(ev.getEvent_no_with_restore_duration()+rs.getInt("duration"));
				}
			}
			list.put(rs.getString("event_date").substring(3), ev);
		}
		
		System.out.println("------------"+list);
		DateUtilities du=new DateUtilities();
		for(Map.Entry em:list.entrySet()){
			EventsSummary_meterBean ev=(EventsSummary_meterBean)em.getValue();
			ev.setWith_duration(du.parseTimeD(Long.parseLong(ev.getEvent_no_with_restore_duration()*60*1000+"")));
			ev.setWithout_duration(du.parseTimeD(Long.parseLong(ev.getEvent_no_without_restore_duration()*60*1000+"")));
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
	return list;
}
public Map<String,SinglePhaseFeedersBean> getSinglePhaseSupplyFeeders_freports(List<String> meters,String date){
	int voltage=0;
	Connection con=null;
	Map<String,SinglePhaseFeedersBean> map=new HashMap();
	try{
		String sql="";
		con=new DBUtil().getConnection();
		DecimalFormat twodec=new DecimalFormat("#0.00");
		DateUtilities du=new DateUtilities();
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd");
		
        String currentDate=sdf.format(new java.util.Date());
         date=sdf.format(sdf1.parse(date));
        if(date.equals(currentDate))
           date=du.substract_1_Day(date+" 00:00:00");
		
        sql="select * from analytics_energy_savings_single_phase where d4_date='"+date+"'";
		System.out.println("----------------"+sql);
		
        ResultSet rs=con.prepareStatement(sql).executeQuery();
        while(rs.next()){
        	String meter=rs.getString("meter_serial_no");
        	if(meters.contains(meter)){
        		SinglePhaseFeedersBean glb=new SinglePhaseFeedersBean();
        		long ph_1=rs.getLong("singlephase");
        		long ph_3=rs.getLong("threephase");
        		long pf=rs.getLong("powerfailure");
        		double ph3_cons=rs.getDouble("avg_three_consumption");
        		
        		if(ph_1+ph_3+pf>=96){
        			if(ph_1==96){ ph_3=0; pf=0; }
            		if(ph_3==96){ ph_1=0; pf=0; }
            		if(pf==96){ ph_3=0; ph_1=0; }
            		
            		if((ph_1+ph_3)==96 ) pf=0;
            		if((ph_1+pf)==96 ) ph_3=0;
            		if((pf+ph_3)==96 ) ph_1=0;
        		}
        		else if(ph_1+ph_3+pf>=48){
        			if(ph_1==48){ ph_3=0; pf=0; }
            		if(ph_3==48){ ph_1=0; pf=0; }
            		if(pf==48){ ph_3=0; ph_1=0; }
            		
            		if((ph_1+ph_3)==48) pf=0;
            		if((ph_1+pf)==48) ph_3=0;
            		if((pf+ph_3)==48 ) ph_1=0;
        		}
        		
//        		if(ph_1==48 || ph_1==96){ ph_3=0; pf=0; }
//        		if(ph_3==48 || ph_3==96){ ph_1=0; pf=0; }
//        		if(pf==48 || pf==96){ ph_3=0; ph_1=0; }
        		
        		if(rs.getDouble("avg_three_consumption")==0 && ph_3==1) ph_3=0;
        		if(rs.getDouble("energy_savings")==0 && ph_1==1) ph_1=0;
        		
//        		if((ph_1+ph_3)==48 || (ph_1+ph_3)==96 ) pf=0;
//        		if((ph_1+pf)==48 || (ph_1+pf)==96 ) ph_3=0;
//        		if((pf+ph_3)==48 || (pf+ph_3)==96 ) ph_1=0;
        		
        		
        		
        		if((ph_1+ph_3+pf)==48 || (ph_1+ph_3+pf)==96){
        			
        			long time=((ph_1+ph_3+pf)==48)?30:15;
        			
        			glb.setPh1_hrs(du.parseTimeH(ph_1*time*60*1000));
        			glb.setPh3_hrs(du.parseTimeH(ph_3*time*60*1000));
        			glb.setPf_hrs(du.parseTimeH(pf*time*60*1000));
        			
        			if((ph_1*time)==0) glb.setTot_hrs("No Single Phase Supply");
        			else if((ph_1*time)<=10*60) glb.setTot_hrs("<=10 hrs");
        			else if((ph_1*time)>10*60) glb.setTot_hrs(">10 hrs");
        			
        			map.put(meter, glb);
        		}
        		
        		
        		
            	
        	}
        }//while
		
		
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

public Map<String,Map<String,String>> getSinglePhaseSupplyFeeders_freports_monthly(List<String> meters,String month){
	int voltage=0;
	Connection con=null;
	Map<String,Map<String,String>> map=new HashMap();
	try{
		String sql="";
		con=new DBUtil().getConnection();
		DecimalFormat twodec=new DecimalFormat("#0.00");
		DateUtilities du=new DateUtilities();
		
		month=month.replace("/", "-");
		
        
		
        sql="select * from analytics_energy_savings_single_phase where d4_date like '%"+month+"'";
		System.out.println("----------------"+sql);
		
        ResultSet rs=con.prepareStatement(sql).executeQuery();
        while(rs.next()){
        	String meter=rs.getString("meter_serial_no");
        	if(meters.contains(meter)){
        		
        		long ph_1=rs.getLong("singlephase");
        		long ph_3=rs.getLong("threephase");
        		long pf=rs.getLong("powerfailure");
        		double ph3_cons=rs.getDouble("avg_three_consumption");
        		
        		if(ph_1+ph_3+pf>=96){
        			if(ph_1==96){ ph_3=0; pf=0; }
            		if(ph_3==96){ ph_1=0; pf=0; }
            		if(pf==96){ ph_3=0; ph_1=0; }
            		
            		if((ph_1+ph_3)==96 ) pf=0;
            		if((ph_1+pf)==96 ) ph_3=0;
            		if((pf+ph_3)==96 ) ph_1=0;
        		}
        		else if(ph_1+ph_3+pf>=48){
        			if(ph_1==48){ ph_3=0; pf=0; }
            		if(ph_3==48){ ph_1=0; pf=0; }
            		if(pf==48){ ph_3=0; ph_1=0; }
            		
            		if((ph_1+ph_3)==48) pf=0;
            		if((ph_1+pf)==48) ph_3=0;
            		if((pf+ph_3)==48 ) ph_1=0;
        		}
        		
//        		if(ph_1==48 || ph_1==96){ ph_3=0; pf=0; }
//        		if(ph_3==48 || ph_3==96){ ph_1=0; pf=0; }
//        		if(pf==48 || pf==96){ ph_3=0; ph_1=0; }
        		
        		if(rs.getDouble("avg_three_consumption")==0 && ph_3==1) ph_3=0;
        		if(rs.getDouble("energy_savings")==0 && ph_1==1) ph_1=0;
        		
//        		if((ph_1+ph_3)==48 || (ph_1+ph_3)==96 ) pf=0;
//        		if((ph_1+pf)==48 || (ph_1+pf)==96 ) ph_3=0;
//        		if((pf+ph_3)==48 || (pf+ph_3)==96 ) ph_1=0;
        		
        		
        		
        		if((ph_1+ph_3+pf)==48 || (ph_1+ph_3+pf)==96){
        			long time=((ph_1+ph_3+pf)==48)?30:15; 
        			
        			if(map.containsKey(meter)){
        				Map<String,String> vals=map.get(meter);
        				vals.put(rs.getString("d4_date"), du.parseTimeH(ph_1*time*60*1000));
        				map.put(meter, vals);
        			}else{
        				Map<String,String> vals=new HashMap();
        				vals.put(rs.getString("d4_date"), du.parseTimeH(ph_1*time*60*1000));
        				map.put(meter, vals);
        			}
        			
        			
        		}
        		
            	
        	}//if contains meter
        }//while
		
		
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
public Map<String,SinglePhaseFeedersBean> getSinglePhaseSupplyFeeders_freports_bkp(List<String> meters,String date){
	int voltage=0;
	Connection con=null;
	Map<String,SinglePhaseFeedersBean> map=new HashMap();
	try{
		String sql="";
		con=new DBUtil().getConnection();
		DecimalFormat twodec=new DecimalFormat("#0.00");
		DateUtilities du=new DateUtilities();
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd");
		
        String currentDate=sdf.format(new java.util.Date());
         date=sdf.format(sdf1.parse(date));
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

public List<LPDataBean> getLoadProfileSummaryDetails(String month){
	List<LPDataBean> list=new ArrayList();
	try{
		month=month.replaceAll("/", "-");
		System.out.println(" ls profile month "+month);
		Query q=sf.getCurrentSession().createQuery("from LPDataBean where month like '%"+month+"%'");
		list=q.list();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return list;
}


public Map<String,EnergysavingsBean> getSinglePhaseSupply_energysavings_bkp(List meters,String frdate){
	Map<String,EnergysavingsBean> map=new HashMap();
	try{
		Query q=sf.getCurrentSession().createQuery("from EnergysavingsBean where d4_date='"+frdate+"' and meter_serial_no in (:meters) and energy_savings>=0");
		q.setParameterList("meters", meters);
		List<EnergysavingsBean> list=q.list();
		
		for(EnergysavingsBean esb:list){
			map.put(esb.getMeter_serial_no(), esb);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return map;
}
public Map<String,EnergysavingsBean> getSinglePhaseSupply_energysavings(List meters,String frdate){
	Map<String,EnergysavingsBean> map=new HashMap();
	Connection con=null;
	try{
		con=new DBUtil().getConnection();
		Map<String,String> singlePHMap=new HashMap();
		String sql="select meter_serial_no,singlephase from analytics_energy_savings_single_phase where d4_date='"+frdate+"'";
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			if(meters.contains(rs.getString("meter_serial_no")))
				singlePHMap.put(rs.getString("meter_serial_no"), rs.getString("singlephase"));
		}
		
		sql="SELECT meter_serial_no,SUM(energy_utilised),SUM(energy_saved),COUNT(*) FROM ls_data_stbl_energy_savings WHERE d4_date LIKE '"+frdate+"%' GROUP BY meter_serial_no";
		rs=con.prepareStatement(sql).executeQuery();
		DateUtilities du=new DateUtilities();
		while(rs.next()){
//			if(rs.getDouble(3)>=0.0){
			EnergysavingsBean eb=new EnergysavingsBean();
			eb.setMeter_serial_no(rs.getString("meter_serial_no"));
			eb.setEnergy_utilised(rs.getString(2));
			eb.setEnergy_savings(rs.getDouble(3)<0?"0":rs.getString(3));
			
			int duration=rs.getInt(4)>48?15:30;
			if(singlePHMap.containsKey(rs.getString("meter_serial_no"))){
				long time=Long.parseLong(singlePHMap.get(rs.getString("meter_serial_no")))*duration*60*1000;
				eb.setSupply_duration(du.parseTimeH(time));
			}
			map.put(rs.getString("meter_serial_no"), eb);
//			}
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

public Map<String,Map<String,String>> getSinglePhaseSupply_energysavings_monthly(List meters,String frmonth){
	Map<String,Map<String,String>> map=new HashMap();
	Connection con=null;
	try{
		con=new DBUtil().getConnection();
		
		
		String sql="SELECT meter_serial_no,SUBSTR(d4_date,1,10) AS ddate,SUM(energy_saved) FROM ls_data_stbl_energy_savings WHERE d4_date LIKE '%"+frmonth+"%' GROUP BY meter_serial_no,ddate";
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		DateUtilities du=new DateUtilities();
		while(rs.next()){
			String meter=rs.getString("meter_serial_no");
//			if(rs.getDouble(3)>=0.0){
				if(map.containsKey(meter)){
					Map<String,String> vals=map.get(meter);
					vals.put(rs.getString("ddate"), (rs.getDouble(3)<0?"0":rs.getString(3)));
					map.put(meter, vals);
				}else{
					Map<String,String> vals=new HashMap();
					vals.put(rs.getString("ddate"), (rs.getDouble(3)<0?"0":rs.getString(3)));
					map.put(meter, vals);
				}
			
			
//			}//if savings>0
		}//while
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

public List<EnergySavingsDetailsBean> getSinglePhaseSupply_energysavingsDetails(String meter,String date){
	List<EnergySavingsDetailsBean> list=new ArrayList();
	List<EnergySavingsDetailsBean> mflist=new ArrayList();
	Connection con=null;
	try{
		DecimalFormat threedec=new DecimalFormat("#0.000");
		System.out.println(meter+"\t"+date);
		Query q=sf.getCurrentSession().createQuery("from EnergySavingsDetailsBean where meter_serial_no='"+meter+"' and d4_date like '"+date+"%'");
		list=q.list();
		
		Map<String,Double> mfmap=new HashMap();
		con=new DBUtil().getConnection();
		String sql="select meter_sl_no,mf from total_masterdata";
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			mfmap.put(rs.getString("meter_sl_no"), rs.getString("mf")!=null?rs.getDouble("mf"):1);
		}
		for(EnergySavingsDetailsBean esb:list){
			esb.setEnergy_saved(threedec.format(Double.parseDouble(esb.getEnergy_saved())*mfmap.get(esb.getMeter_serial_no())));
			esb.setEnergy_utilised(threedec.format(Double.parseDouble(esb.getEnergy_utilised())*mfmap.get(esb.getMeter_serial_no())));
			mflist.add(esb);
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
	
	return mflist;
}

public Map<String,String> getAG_deviationSupply(List<String> meters,String frdate){
	Map<String,String> list=new HashMap();
	Connection con=null;
	try{
		con=new DBUtil().getConnection();
		int day=0;
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		String curDate=sdf.format(new java.util.Date());		
			day=Integer.parseInt(frdate.split("-")[0]);
		String month=frdate.substring(frdate.indexOf("-"));	
		
		String sql="SELECT * FROM power_fail_minutes WHERE power_fail_date='"+frdate+"'";
		
		System.out.println(sql);
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		DateUtilities du=new DateUtilities();
		while(rs.next()){
	       if(meters.contains(rs.getString("meter_serial_no"))){
	    	   long supply=(rs.getLong("singlephase")!=-1?rs.getLong("singlephase"):0)+(rs.getLong("twophase")!=-1?rs.getLong("twophase"):0)+(rs.getLong("threephase")!=-1?rs.getLong("threephase"):0);
	    	   if(supply>10*60)
	    	     list.put(rs.getString("meter_serial_no"), du.parseTimeH(supply*60*1000));
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
	return list;
	
}
public Map<String,Integer> getLoadFactorValues(String type,String year,List<String> meters){
	Map<String,Integer> map=new LinkedHashMap();
	Connection con=null;
	try{
		con=new DBUtil().getConnection();
		//System.out.println(meters);
		String sql="SELECT * FROM analytics_monthly_load_factor WHERE YEAR="+year;
		
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			if(meters.contains(rs.getString("meter_serial_no"))){
				if(type.equals("le50p")){
					
					for(int i=1;i<=12;i++){
						
					 if(rs.getString("m"+i)!=null && rs.getInt("m"+i)<50){						 
						 if(map.get("m"+i)==null) map.put("m"+i, 1);
						 else map.put("m"+i, map.get("m"+i)+1);
					 }//if
					}
				 }
				if(type.equals("le5p3m")){
						 
			     }
				if(type.equals("gr100p")){
					for(int i=1;i<=12;i++){
						 if(rs.getString("m"+i)!=null && rs.getInt("m"+i)>100){
							 if(map.get("m"+i)==null) map.put("m"+i, 1);
							 else map.put("m"+i, map.get("m"+i)+1);
						 }//if
						}
				}
				if(type.equals("gr100p3m")){
					 
				}
			}//if contains meter
		}
		
		System.out.println("-------"+map);
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
public Map<String,Integer> getLoadFactorPopupValues(String type,String month,String year,List<String> meters){
	Map<String,Integer> map=new HashMap();
	Connection con=null;
	try{
		con=new DBUtil().getConnection();
		//System.out.println(meters);
		String sql="SELECT meter_serial_no,"+month+" FROM analytics_monthly_load_factor WHERE YEAR="+year;
		
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			if(meters.contains(rs.getString("meter_serial_no"))){
				if(type.equals("le50p")){
					map.put(rs.getString("meter_serial_no"), rs.getString(2)!=null?rs.getInt(2):0);
					
				 }
				if(type.equals("le5p3m")){
						 
			     }
				if(type.equals("gr100p")){
					map.put(rs.getString("meter_serial_no"), rs.getString(2)!=null?rs.getInt(2):0);
				}
				if(type.equals("gr100p3m")){
					 
				}
			}//if contains meter
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
public Map<String,Integer> getZeroConsumptionValues(String type,String year,List<String> meters){
	Map<String,Integer> map=new LinkedHashMap();
	Connection con=null;
	try{
		int th_value=1;
		if(type.equals("zc")) th_value=1;
		if(type.equals("zc3m")) th_value=3;
		if(type.equals("zc6m")) th_value=6;
		if(type.equals("zc9m")) th_value=9;
		if(type.equals("zc12m")) th_value=12;
		if(type.equals("nc") || type.equals("hc") || 
				type.equals("lc") || type.equals("pf")|| type.equals("cle1006m")) th_value=1;
	
		con=new DBUtil().getConnection();
		String sql="SELECT * FROM analytics_consumption_summary WHERE MONTH LIKE '%"+year+"%' order by month";
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			if(meters.contains(rs.getString("meter_serial_no"))){
				if(type.equals("nc")){
					if(rs.getString("negative_consumption")!=null && rs.getInt("negative_consumption")==th_value){						 
						 if(map.get(rs.getString("month"))==null) map.put(rs.getString("month"), 1);
						 else map.put(rs.getString("month"), map.get(rs.getString("month"))+1);
					 }//if
				}else if(type.contains("zc")){ //zero consumption
					if(rs.getString("zero_consumption")!=null && rs.getInt("zero_consumption")==th_value){						 
						 if(map.get(rs.getString("month"))==null) map.put(rs.getString("month"), 1);
						 else map.put(rs.getString("month"), map.get(rs.getString("month"))+1);
					 }//if
				}
				else if(type.contains("hc")){ //high consumption
					if(rs.getString("high_consumption")!=null && rs.getInt("high_consumption")==th_value){						 
						 if(map.get(rs.getString("month"))==null) map.put(rs.getString("month"), 1);
						 else map.put(rs.getString("month"), map.get(rs.getString("month"))+1);
					 }//if
				}
				else if(type.contains("lc")){ //low consumption
					if(rs.getString("low_consumption")!=null && rs.getInt("low_consumption")==th_value){						 
						 if(map.get(rs.getString("month"))==null) map.put(rs.getString("month"), 1);
						 else map.put(rs.getString("month"), map.get(rs.getString("month"))+1);
					 }//if
				}
				else if(type.contains("pf")){ //pf
					if(rs.getString("power_factor_status")!=null && rs.getInt("power_factor_status")==th_value){						 
						 if(map.get(rs.getString("month"))==null) map.put(rs.getString("month"), 1);
						 else map.put(rs.getString("month"), map.get(rs.getString("month"))+1);
					 }//if
				}
				else if(type.contains("cle1006m")){ //cle1006m
					if(rs.getString("six_months_consumption")!=null && rs.getInt("six_months_consumption")==th_value){						 
						 if(map.get(rs.getString("month"))==null) map.put(rs.getString("month"), 1);
						 else map.put(rs.getString("month"), map.get(rs.getString("month"))+1);
					 }//if
				}
				
			}//if contains meter
		}//while
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

public Map<String,TODConsumptionBean> getTODConsumptionValues(String type,String year,List<String> meters){
	Map<String,TODConsumptionBean> map=new LinkedHashMap();
	Connection con=null;
	try{			
		con=new DBUtil().getConnection();
		List<TODConsumptionBean> list=new ArrayList();
		String sql="SELECT * FROM auto_reset_energies WHERE billing_timestamp LIKE '"+year+"%' ORDER BY billing_timestamp asc";
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			if(meters.contains(rs.getString("meter_serial_no"))){
				TODConsumptionBean tb=new TODConsumptionBean();
				tb.setKvah_tot(rs.getString("kvah"));
				tb.setKwh_tot(rs.getString("kwh"));
				tb.setMonth(rs.getString("billing_timestamp").split(" ")[0]);
				if(type.equals("kwh_tod")){
					tb.setTod1(rs.getString("kwh_tod_1"));
					tb.setTod2(rs.getString("kwh_tod_2"));
					tb.setTod3(rs.getString("kwh_tod_3"));
					tb.setTod4(rs.getString("kwh_tod_4"));
					tb.setTod5(rs.getString("kwh_tod_5"));
					tb.setTod6(rs.getString("kwh_tod_6"));
					tb.setTod7(rs.getString("kwh_tod_7"));
					tb.setTod8(rs.getString("kwh_tod_8"));
				}
	            if(type.equals("kvah_tod")){
	            	tb.setTod1(rs.getString("kvah_tod_1"));
					tb.setTod2(rs.getString("kvah_tod_2"));
					tb.setTod3(rs.getString("kvah_tod_3"));
					tb.setTod4(rs.getString("kvah_tod_4"));
					tb.setTod5(rs.getString("kvah_tod_5"));
					tb.setTod6(rs.getString("kvah_tod_6"));
					tb.setTod7(rs.getString("kvah_tod_7"));
					tb.setTod8(rs.getString("kvah_tod_8"));
				}
	            list.add(tb);
			}//if contains meter
		}//while
		DecimalFormat twoDec=new DecimalFormat("#0.00");
		List<TODConsumptionBean> list_n=new ArrayList();
		for(int i=0;i<list.size()-1;i++){
			TODConsumptionBean tb=list.get(i);
			TODConsumptionBean tb_n=list.get(i+1);	
			
			//System.out.println(tb_n.getTod1()+"\t"+tb.getTod1());
			
			tb.setTod1(twoDec.format(Double.parseDouble(tb_n.getTod1())-Double.parseDouble(tb.getTod1())));
			tb.setTod2(twoDec.format(Double.parseDouble(tb_n.getTod2())-Double.parseDouble(tb.getTod2())));
			tb.setTod3(twoDec.format(Double.parseDouble(tb_n.getTod3())-Double.parseDouble(tb.getTod3())));
			tb.setTod4(twoDec.format(Double.parseDouble(tb_n.getTod4())-Double.parseDouble(tb.getTod4())));
			tb.setTod5(twoDec.format(Double.parseDouble(tb_n.getTod5())-Double.parseDouble(tb.getTod5())));
			tb.setTod6(twoDec.format(Double.parseDouble(tb_n.getTod6())-Double.parseDouble(tb.getTod6())));
			tb.setTod7(twoDec.format(Double.parseDouble(tb_n.getTod7())-Double.parseDouble(tb.getTod7())));
			tb.setTod8(twoDec.format(Double.parseDouble(tb_n.getTod8())-Double.parseDouble(tb.getTod8())));
		
			list_n.add(tb);
		}
		for(TODConsumptionBean tb:list_n){
			map.put(tb.getMonth(), tb);
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

public Map<String,String> getZeroConsumptionPopupValues(String type,String month,String year,List<String> meters){
	Map<String,String> map=new HashMap();
	Connection con=null;
	try{
		System.out.println(type+"\t"+month+"\t"+year);
		
		int th_value=1;
		if(type.equals("zc")) th_value=1;
		if(type.equals("zc3m")) th_value=3;
		if(type.equals("zc6m")) th_value=6;
		if(type.equals("zc9m")) th_value=9;
		if(type.equals("zc12m")) th_value=12;
		if(type.equals("nc") || type.equals("hc") || 
				type.equals("lc") || type.equals("pf")|| type.equals("cle1006m")) th_value=1;
		
		con=new DBUtil().getConnection();
		if(!type.contains("nc")){
			String sql="SELECT * FROM analytics_consumption_summary WHERE MONTH='"+month+"'";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(meters.contains(rs.getString("meter_serial_no"))){
					 if(type.contains("zc")){
						if(rs.getString("zero_consumption")!=null && rs.getInt("zero_consumption")==th_value){						 
							 map.put(rs.getString("meter_serial_no"), rs.getString("zero_consumption"));
						 }//if
					 }
						else if(type.contains("hc")){ //high consumption
							if(rs.getString("high_consumption")!=null && rs.getInt("high_consumption")==th_value){						 
								map.put(rs.getString("meter_serial_no"), rs.getString("high_consumption"));
							 }//if
						}
						else if(type.contains("lc")){ //low consumption
							if(rs.getString("low_consumption")!=null && rs.getInt("low_consumption")==th_value){						 
								map.put(rs.getString("meter_serial_no"), rs.getString("low_consumption"));
							 }//if
						}
						else if(type.contains("pf")){ //pf
							if(rs.getString("power_factor_status")!=null && rs.getInt("power_factor_status")==th_value){						 
								map.put(rs.getString("meter_serial_no"), rs.getString("power_factor_status"));
							 }//if
						}
						else if(type.contains("cle1006m")){ //cle1006m
							if(rs.getString("six_months_consumption")!=null && rs.getInt("six_months_consumption")==th_value){						 
								map.put(rs.getString("meter_serial_no"), rs.getString("six_months_consumption"));
							 }//if
						}	
					
				}//if contains meter
				
			}//while
		}		
		else if(type.equals("nc")){
			String col="m"+Integer.parseInt(month.split("-")[0]);
			String sql="SELECT * FROM analytics_monthly_consumption WHERE year='"+year+"'";
			System.out.println("---------------->"+sql);
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				if(meters.contains(rs.getString("meter_serial_no"))){
					
						if(rs.getString(col)!=null){						 
							 map.put(rs.getString("meter_serial_no"), rs.getString(col));
						 }//if
					
				}//if contains meter
			}//while
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

public Map<String,AnalyticsDemandForecasting> getDemandForecasting(List<String> meters,String month){
	Map<String,AnalyticsDemandForecasting> map=new HashMap();
	Connection con=null;
	try{
		month=month.replaceAll("/", "-");
		System.out.println("-------------"+month);
		con=DBUtil.getConnection();
		ResultSet rs=con.prepareStatement("select * from analytics_demand_forecasting where month='"+month+"'").executeQuery();
		while(rs.next()){
			AnalyticsDemandForecasting ad=new AnalyticsDemandForecasting();
			ad.setForcast_month_based_kw(rs.getString("forecast_month_based_kw"));
			ad.setForcast_year_based_kw(rs.getString("forecast_year_based_kw"));
			ad.setObservation(rs.getString("observation"));
			map.put(rs.getString("meter_serial_no"), ad);
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
