package com.rtdas.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.AlertsDao;
import com.rtdas.common.models.Alerts;
import com.rtdas.common.service.AlertsService;

@Service("alertsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AlertsServiceImpl implements AlertsService{
	
	@Autowired
	private AlertsDao dao;

	@Override
	public List<Alerts> getLast10Alerts(String siteid,String type) {
		// TODO Auto-generated method stub
		return dao.getLast10Alerts(siteid,type);
	}

	@Override
	public List<Alerts> getAlertsHistory(String siteid, String traid, String from, String to) {
		// TODO Auto-generated method stub
		return dao.getAlertsHistory(siteid, traid, from, to);
	}

	@Override
	public List<Alerts> getAllTrackerLatestAlerts(String siteid) {
		// TODO Auto-generated method stub
		return dao.getAllTrackerLatestAlerts(siteid);
	}

}
