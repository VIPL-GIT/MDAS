package com.rtdas.common.service;
import java.util.*;

import com.rtdas.common.models.Alerts;
public interface AlertsService {

	public List<Alerts> getLast10Alerts(String siteid,String type);
	public List<Alerts> getAllTrackerLatestAlerts(String siteid);
	public List<Alerts> getAlertsHistory(String siteid,String traid,String from,String to);
}
