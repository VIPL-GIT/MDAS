package com.rtdas.common.dao.impl;

import java.util.*;
import java.text.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.dao.AlertsDao;
import com.rtdas.common.models.Alerts;

@Repository("alertsdao")
public class AlertsDaoImpl implements AlertsDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public List<Alerts> getLast10Alerts(String siteid,String type) {
		List<Alerts> alerts=new ArrayList();
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String sql="";
			if(type.equals("last10"))
				sql="from Alerts where siteid='"+siteid+"'order by slno desc ";
			if(type.equals("today"))
				sql="from Alerts where siteid='"+siteid+"' and alert_datetime like '"+sdf.format(new java.util.Date())+"%' order by slno desc ";
			Query q=sf.getCurrentSession().createQuery(sql);
			
			q.setMaxResults(10);
			alerts=q.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return alerts;
	}

	@Override
	public List<Alerts> getAlertsHistory(String siteid, String traid, String from, String to) {
		List<Alerts> alerts=new ArrayList();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try{
			String sql="from Alerts where siteid='"+siteid+"' and traid='"+traid+"' and alert_datetime between :from and :to";
			Query q=sf.getCurrentSession().createQuery(sql);
			q.setTimestamp("from", sdf.parse(from));
			q.setTimestamp("to", sdf.parse(to));
			alerts=q.list();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return alerts;
	}

	@Override
	public List<Alerts> getAllTrackerLatestAlerts(String siteid) {
		List<Alerts> alerts=new ArrayList();
		try{
			String sql="select distinct device_id from device_data where client_id="+siteid;
			List trackers=sf.getCurrentSession().createSQLQuery(sql).list();
			if(!trackers.isEmpty()){
				
			Query q=sf.getCurrentSession().createQuery("from Alerts where slno IN(SELECT MAX(slno) FROM Alerts WHERE traid IN (:trackers) GROUP BY traid)");
			q.setParameterList("trackers", trackers);
			alerts=q.list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return alerts;
	}

}
