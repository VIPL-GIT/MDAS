package com.rtdas.dashboard.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.dashboard.beans.CircleLatLons;
import com.rtdas.dashboard.beans.MeterLatLons;
import com.rtdas.dashboard.beans.SSLatlonsBean;

import utils.DBUtil;

@Repository("GmapDao")
@Transactional
public class GmapDao {

	public CircleLatLons getCircleLatLons(String circle){
		CircleLatLons cll=new CircleLatLons();
		Connection con=null;
		try{
			con=new DBUtil().getConnection();
			Map<String,String> lastCommDate=new HashMap();
			String sql="select * from feeder_live_status";
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				lastCommDate.put(rs.getString("meter_serial_no"), rs.getString("meter_rtc")+","+rs.getString("vr")+","+rs.getString("vy")+","+rs.getString("vb")+","+rs.getString("lr")+","+rs.getString("ly")+","+rs.getString("lb"));
			}
			 sql="SELECT circle,ss,meter_sl_no,lattitude,longitude,location_name FROM total_masterdata WHERE circle='"+circle+"' AND lattitude IS NOT NULL ORDER BY ss";
			 rs=con.prepareStatement(sql).executeQuery();
			List<SSLatlonsBean> sslist=new ArrayList();
			Map<String,List<MeterLatLons>> meters=new HashMap();
			while(rs.next()){
				if(meters.containsKey(rs.getString("ss"))){
					
					List<MeterLatLons> ml=meters.get(rs.getString("ss"));
					MeterLatLons mll=new MeterLatLons();
					mll.setMeter(rs.getString("meter_sl_no"));
					mll.setLat(rs.getString("lattitude"));
					mll.setLng(rs.getString("longitude"));
					mll.setLocation(rs.getString("location_name"));
					if(lastCommDate.containsKey(rs.getString("meter_sl_no"))){
						String val=lastCommDate.get(rs.getString("meter_sl_no"));
						mll.setLastCommDate(val.split(",")[0]);
						mll.setRph_v(val.split(",")[1]);
						mll.setYph_v(val.split(",")[2]);
						mll.setBph_v(val.split(",")[3]);
						mll.setRph_c(val.split(",")[4]);
						mll.setYph_c(val.split(",")[5]);
						mll.setBph_c(val.split(",")[6]);
					}
					else {
						mll.setLastCommDate("Not Started");
					}
					ml.add(mll);
					
					meters.put(rs.getString("ss"), ml);
				}else{
					List<MeterLatLons> ml=new ArrayList();
					MeterLatLons mll=new MeterLatLons();
					mll.setMeter(rs.getString("meter_sl_no"));
					mll.setLat(rs.getString("lattitude"));
					mll.setLng(rs.getString("longitude"));
					mll.setLocation(rs.getString("location_name"));
					if(lastCommDate.containsKey(rs.getString("meter_sl_no"))){
						String val=lastCommDate.get(rs.getString("meter_sl_no"));
						mll.setLastCommDate(val.split(",")[0]);
						mll.setRph_v(val.split(",")[1]);
						mll.setYph_v(val.split(",")[2]);
						mll.setBph_v(val.split(",")[3]);
						mll.setRph_c(val.split(",")[4]);
						mll.setYph_c(val.split(",")[5]);
						mll.setBph_c(val.split(",")[6]);
					}
					else {
						mll.setLastCommDate("Not Started");
					}
					ml.add(mll);
					
					meters.put(rs.getString("ss"), ml);
				}
			}//while
			for(Map.Entry em:meters.entrySet()){
				SSLatlonsBean ssb=new SSLatlonsBean();
				ssb.setSs_name(em.getKey().toString());
				ssb.setMeters((List<MeterLatLons>)em.getValue());
				
				sslist.add(ssb);
			}
			
			cll.setSslist(sslist);
			
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
		return cll;
	}
}
