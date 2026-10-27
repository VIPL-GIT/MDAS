package com.rtdas.common.dao;

import java.util.List;

import com.rtdas.common.models.Alerts;

public interface AlertsDao {

	public List<Alerts> getLast10Alerts(String siteid,String type);
	public List<Alerts> getAllTrackerLatestAlerts(String siteid);
	public List<Alerts> getAlertsHistory(String siteid,String traid,String from,String to);
	
}
