package com.rtdas.mdm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.mdm.models.Analytics_load_profile;
import com.rtdas.mdm.models.Analytics_other_events_summary;
import com.rtdas.mdm.models.Analytics_outage_summary;
import com.rtdas.meterdata.beans.AutoResetEnergies;
import com.rtdas.meterdata.beans.DailyEnergiesBean;

import utils.DateUtilities;
import utils.Events;

@Repository("analyticsDao")
@Transactional
public class AnalyticsDao {

	@Autowired
	private SessionFactory sf;
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	
	public List<AutoResetEnergies> getBilling(String meter){
		List<AutoResetEnergies> list=new ArrayList();
		try{
			Query q=sf.getCurrentSession().createQuery("From AutoResetEnergies where meter_serial_no='"+meter+"' order by datetime desc ");
			list=q.list();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<DailyEnergiesBean> getDailyEnergies(String meter,String month){
		List<DailyEnergiesBean> list=new ArrayList();
		try{
			Map<String,String> readingsMap=new HashMap();
			Map<String,String> consumptionMap=new HashMap();
			Map<String,String> mdsMap=new HashMap();
			
			Connection con=sf.getCurrentSession().connection();
			month="-"+month.replace("/", "-");
			String mddate=month.split("-")[2]+"-"+month.split("-")[1]+"-";
			String sql="SELECT * FROM analytics_meter_daily_readings where meter_serial_no='"+meter+"' and month='"+month+"'";
			System.out.println("--->"+sql);
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				readingsMap.put("d1", rs.getString("d1"));
				readingsMap.put("d2", rs.getString("d2"));
				readingsMap.put("d3", rs.getString("d3"));
				readingsMap.put("d4", rs.getString("d4"));
				readingsMap.put("d5", rs.getString("d5"));
				readingsMap.put("d6", rs.getString("d6"));
				readingsMap.put("d7", rs.getString("d7"));
				readingsMap.put("d8", rs.getString("d8"));
				readingsMap.put("d9", rs.getString("d9"));
				readingsMap.put("d10", rs.getString("d10"));
				readingsMap.put("d11", rs.getString("d11"));
				readingsMap.put("d12", rs.getString("d12"));
				readingsMap.put("d13", rs.getString("d13"));
				readingsMap.put("d14", rs.getString("d14"));
				readingsMap.put("d15", rs.getString("d15"));
				readingsMap.put("d16", rs.getString("d16"));
				readingsMap.put("d17", rs.getString("d17"));
				readingsMap.put("d18", rs.getString("d18"));
				readingsMap.put("d19", rs.getString("d19"));
				readingsMap.put("d20", rs.getString("d20"));
				readingsMap.put("d21", rs.getString("d21"));
				readingsMap.put("d22", rs.getString("d22"));
				readingsMap.put("d23", rs.getString("d23"));
				readingsMap.put("d24", rs.getString("d24"));
				readingsMap.put("d25", rs.getString("d25"));
				readingsMap.put("d26", rs.getString("d26"));
				readingsMap.put("d27", rs.getString("d27"));
				readingsMap.put("d28", rs.getString("d28"));
				readingsMap.put("d29", rs.getString("d29"));
				readingsMap.put("d30", rs.getString("d30"));
				readingsMap.put("d31", rs.getString("d31"));
				
			}
			
			 sql="SELECT * FROM analytics_meter_daily_consumption where meter_serial_no='"+meter+"' and month='"+month+"'";
			 System.out.println("--->"+sql);
			 rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				consumptionMap.put("d1", rs.getString("d1"));
				consumptionMap.put("d2", rs.getString("d2"));
				consumptionMap.put("d3", rs.getString("d3"));
				consumptionMap.put("d4", rs.getString("d4"));
				consumptionMap.put("d5", rs.getString("d5"));
				consumptionMap.put("d6", rs.getString("d6"));
				consumptionMap.put("d7", rs.getString("d7"));
				consumptionMap.put("d8", rs.getString("d8"));
				consumptionMap.put("d9", rs.getString("d9"));
				consumptionMap.put("d10", rs.getString("d10"));
				consumptionMap.put("d11", rs.getString("d11"));
				consumptionMap.put("d12", rs.getString("d12"));
				consumptionMap.put("d13", rs.getString("d13"));
				consumptionMap.put("d14", rs.getString("d14"));
				consumptionMap.put("d15", rs.getString("d15"));
				consumptionMap.put("d16", rs.getString("d16"));
				consumptionMap.put("d17", rs.getString("d17"));
				consumptionMap.put("d18", rs.getString("d18"));
				consumptionMap.put("d19", rs.getString("d19"));
				consumptionMap.put("d20", rs.getString("d20"));
				consumptionMap.put("d21", rs.getString("d21"));
				consumptionMap.put("d22", rs.getString("d22"));
				consumptionMap.put("d23", rs.getString("d23"));
				consumptionMap.put("d24", rs.getString("d24"));
				consumptionMap.put("d25", rs.getString("d25"));
				consumptionMap.put("d26", rs.getString("d26"));
				consumptionMap.put("d27", rs.getString("d27"));
				consumptionMap.put("d28", rs.getString("d28"));
				consumptionMap.put("d29", rs.getString("d29"));
				consumptionMap.put("d30", rs.getString("d30"));
				consumptionMap.put("d31", rs.getString("d31"));
				
			}
			
			sql="SELECT * FROM analytics_meter_daily_demands where meter_serial_no='"+meter+"' and month='"+mddate+"'";
			System.out.println("--->"+sql); 
			rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()){
				mdsMap.put("d1", rs.getString("d1"));
				mdsMap.put("d2", rs.getString("d2"));
				mdsMap.put("d3", rs.getString("d3"));
				mdsMap.put("d4", rs.getString("d4"));
				mdsMap.put("d5", rs.getString("d5"));
				mdsMap.put("d6", rs.getString("d6"));
				mdsMap.put("d7", rs.getString("d7"));
				mdsMap.put("d8", rs.getString("d8"));
				mdsMap.put("d9", rs.getString("d9"));
				mdsMap.put("d10", rs.getString("d10"));
				mdsMap.put("d11", rs.getString("d11"));
				mdsMap.put("d12", rs.getString("d12"));
				mdsMap.put("d13", rs.getString("d13"));
				mdsMap.put("d14", rs.getString("d14"));
				mdsMap.put("d15", rs.getString("d15"));
				mdsMap.put("d16", rs.getString("d16"));
				mdsMap.put("d17", rs.getString("d17"));
				mdsMap.put("d18", rs.getString("d18"));
				mdsMap.put("d19", rs.getString("d19"));
				mdsMap.put("d20", rs.getString("d20"));
				mdsMap.put("d21", rs.getString("d21"));
				mdsMap.put("d22", rs.getString("d22"));
				mdsMap.put("d23", rs.getString("d23"));
				mdsMap.put("d24", rs.getString("d24"));
				mdsMap.put("d25", rs.getString("d25"));
				mdsMap.put("d26", rs.getString("d26"));
				mdsMap.put("d27", rs.getString("d27"));
				mdsMap.put("d28", rs.getString("d28"));
				mdsMap.put("d29", rs.getString("d29"));
				mdsMap.put("d30", rs.getString("d30"));
				mdsMap.put("d31", rs.getString("d31"));
				
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse("01"+month));
		    int res = cal.getActualMaximum(Calendar.DATE);
			
		    for(int i=1;i<=res;i++){
		    	DailyEnergiesBean deb=new DailyEnergiesBean();
		    	deb.setDate((i<10?"0"+i:i)+month);
		    	deb.setKwh(readingsMap.get("d"+i));
		    	deb.setConsumption(consumptionMap.get("d"+i));
		    	deb.setMd_kw(mdsMap.get("d"+i)!=null?mdsMap.get("d"+i).split(",")[0]:"");
		    	deb.setMd_kva(mdsMap.get("d"+i)!=null?mdsMap.get("d"+i).split(",")[2]:"");
		    	
		    	list.add(deb);
		    }
			
			System.out.println("----------"+list.size());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Analytics_outage_summary> getPoweronoff(String meter){
		List<Analytics_outage_summary> list=new ArrayList();
		try{
			Query q=sf.getCurrentSession().createQuery("From Analytics_outage_summary where meter_serial_no='"+meter+"' order by recordid desc");
			q.setMaxResults(30);
			list=q.list();
			
			System.out.println(meter+"\t"+list.size());
			
			for(Analytics_outage_summary aos:list){
				if(!aos.getDuration().contains(":"))
				  aos.setDuration(new DateUtilities().parseTime(Long.parseLong(aos.getDuration())*1000));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Analytics_other_events_summary> getEvents(String meter,String from,String to){
		List<Analytics_other_events_summary> list=new ArrayList();
		try{
			System.out.println(from+"\t"+to);
			String sql="From Analytics_other_events_summary where meter_serial_no='"+meter+"' "
					+ " and str_to_date(o_timestamp,'%d-%m-%Y')<=str_to_date('" + to + "','%Y-%m-%d')"
							+ " and str_to_date(o_timestamp,'%d-%m-%Y') >= str_to_date('" + from + "','%Y-%m-%d') order by recordid desc";
			
			System.out.println("----"+sql);
			Query q=sf.getCurrentSession().createQuery(sql);			
			list=q.list();
			
			System.out.println(meter+"\t"+list.size());
			Map<Integer,String> events=new Events().evetnsList();
			for(Analytics_other_events_summary aos:list){
				System.out.println(aos.getDuration());
				if(!aos.getDuration().contains(":"))
				  aos.setDuration(new DateUtilities().parseTimeD(Long.parseLong(aos.getDuration())*1000));
				
				if(events.containsKey(Integer.parseInt(aos.getEvent_type())))
					aos.setEvent_name(events.get(Integer.parseInt(aos.getEvent_type())));
			}
			//sf.getCurrentSession().flush();
			//sf.getCurrentSession().clear();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Analytics_load_profile> getLoadProfile(String meter,String month){
		List<Analytics_load_profile> list=new ArrayList();
		try{
			month=month.replace("/", "-");
			
			System.out.println("--------"+month);
			Query q=sf.getCurrentSession().createQuery("From Analytics_load_profile where meter_serial_no='"+meter+"' and d4_date like '%"+month+"%'");
			list=q.list();
			
			System.out.println("----------"+list.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
}
