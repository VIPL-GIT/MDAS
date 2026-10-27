package com.rtdas.common.dao.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.beans.TrackerDataBean;
import com.rtdas.common.dao.TrackerDao;
import com.rtdas.common.models.Adjustments;
import com.rtdas.common.models.DeviceData;

@Repository("trackerDao")
public class TrackerDaoImpl implements TrackerDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<TrackerDataBean> getTrackersLatestData(String siteid) {
		List<TrackerDataBean> list=new ArrayList();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try{
			String sql="select distinct device_id from device_data where client_id="+siteid;
			List trackers=sf.getCurrentSession().createSQLQuery(sql).list();
			if(!trackers.isEmpty()){
				sql=" FROM DeviceData WHERE slno IN(SELECT MAX(slno) FROM DeviceData WHERE device_id IN (:trackers) GROUP BY device_id)";
				Query q=sf.getCurrentSession().createQuery(sql);
				q.setParameterList("trackers", trackers);
				List<DeviceData> ddlist=q.list();
				
				if(!ddlist.isEmpty()){
					for(DeviceData dd:ddlist){
					TrackerDataBean tdb=new TrackerDataBean();
					
					tdb.setTracker_id(dd.getDevice_id());
					tdb.setSite_id(dd.getClient_id());
					tdb.setTracker_reading_date_time(sdf.format(dd.getReading_datetime()));
					
					tdb.setSun_Position(dd.getParam_1());
					tdb.setSet_Position(dd.getParam_2());
					tdb.setTracker_Position(dd.getParam_3());
					tdb.setStatus(dd.getParam_4());
					tdb.setPack_Voltage(dd.getParam_5());
					tdb.setMotor_current(dd.getParam_6());
					tdb.setTemparature(dd.getParam_7());
					tdb.setLatitude(dd.getParam_8());
					tdb.setLongitude(dd.getParam_9());
					tdb.setAltitude(dd.getParam_10());
					tdb.setTime_Zone(dd.getParam_11());
					tdb.setbT_ends(dd.getParam_12());
					tdb.setsSS_Angle(dd.getParam_13());
					tdb.setfEL(dd.getParam_14());
					tdb.setrEL(dd.getParam_15());
					tdb.setaZ(dd.getParam_16());
					tdb.setrSL(dd.getParam_17());
					tdb.setfSL(dd.getParam_18());
					tdb.setiNST_DATE(dd.getParam_19());
					tdb.setStop_days(dd.getParam_20());
					
					list.add(tdb);
					}
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<TrackerDataBean> getTrackersHistoryData(String siteid, String traid, String from, String to) {
		List<TrackerDataBean> list=new ArrayList();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			//System.out.println(siteid+"\t"+traid+"\t"+from+"\t"+to);
				String sql=" FROM DeviceData WHERE client_id='"+siteid+"' and device_id='"+traid+"' and reading_datetime between :from and :to";
				Query q=sf.getCurrentSession().createQuery(sql);
				q.setTimestamp("from", sdf.parse(from));
				q.setTimestamp("to", sdf.parse(to));
				List<DeviceData> ddlist=q.list();
				
				if(!ddlist.isEmpty()){
					for(DeviceData dd:ddlist){
					TrackerDataBean tdb=new TrackerDataBean();
					
					tdb.setTracker_id(dd.getDevice_id());
					tdb.setSite_id(dd.getClient_id());
					tdb.setTracker_reading_date_time(sdf.format(dd.getReading_datetime()));
					
					tdb.setSun_Position(dd.getParam_1());
					tdb.setSet_Position(dd.getParam_2());
					tdb.setTracker_Position(dd.getParam_3());
					tdb.setStatus(dd.getParam_4());
					tdb.setPack_Voltage(dd.getParam_5());
					tdb.setMotor_current(dd.getParam_6());
					tdb.setTemparature(dd.getParam_7());
					tdb.setLatitude(dd.getParam_8());
					tdb.setLongitude(dd.getParam_9());
					tdb.setAltitude(dd.getParam_10());
					tdb.setTime_Zone(dd.getParam_11());
					tdb.setbT_ends(dd.getParam_12());
					tdb.setsSS_Angle(dd.getParam_13());
					tdb.setfEL(dd.getParam_14());
					tdb.setrEL(dd.getParam_15());
					tdb.setaZ(dd.getParam_16());
					tdb.setrSL(dd.getParam_17());
					tdb.setfSL(dd.getParam_18());
					tdb.setiNST_DATE(dd.getParam_19());
					tdb.setStop_days(dd.getParam_20());
					
					list.add(tdb);
					}
				}
				
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> getAllTrackers(String siteid) {
		List<String> trackers=new ArrayList();
		try{
			String sql="select distinct device_id from device_data where client_id="+siteid;
			 trackers=sf.getCurrentSession().createSQLQuery(sql).list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return trackers;
	}

	@Override
	public List<Adjustments> getAdjustments(String siteid) {
		List<Adjustments> adjusts=new ArrayList();
		try{
			String sql="from Adjustments where site_id='"+siteid+"' ";
			adjusts=sf.getCurrentSession().createQuery(sql).list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return adjusts;
	}

}
