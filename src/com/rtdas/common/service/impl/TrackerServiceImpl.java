package com.rtdas.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.beans.TrackerDataBean;
import com.rtdas.common.dao.TrackerDao;
import com.rtdas.common.models.Adjustments;
import com.rtdas.common.service.TrackerService;

@Service("trackerService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TrackerServiceImpl implements TrackerService {
	
	@Autowired
	public TrackerDao dao;

	@Override
	public List<TrackerDataBean> getTrackersLatestData(String siteid) {
		return dao.getTrackersLatestData(siteid);
	}

	@Override
	public List<TrackerDataBean> getTrackersHistoryData(String siteid, String traid, String from, String to) {
		 return dao.getTrackersHistoryData(siteid, traid, from, to);
	}

	@Override
	public List<String> getAllTrackers(String siteid) {
		// TODO Auto-generated method stub
		return dao.getAllTrackers(siteid);
	}

	@Override
	public List<Adjustments> getAdjustments(String siteid) {
		// TODO Auto-generated method stub
		return dao.getAdjustments(siteid);
	}

}
