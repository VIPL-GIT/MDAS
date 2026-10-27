package com.rtdas.common.service;

import java.util.List;

import com.rtdas.common.beans.TrackerDataBean;
import com.rtdas.common.models.Adjustments;

public interface TrackerService {

	public List<String> getAllTrackers(String siteid);
	public List<TrackerDataBean> getTrackersLatestData(String siteid);
	public List<TrackerDataBean> getTrackersHistoryData(String siteid,String traid,String from,String to);
	
	public List<Adjustments> getAdjustments(String siteid);
	
}
