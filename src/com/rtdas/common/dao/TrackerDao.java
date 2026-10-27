package com.rtdas.common.dao;

import java.util.List;

import com.rtdas.common.beans.TrackerDataBean;
import com.rtdas.common.models.Adjustments;

public interface TrackerDao {
	public List<String> getAllTrackers(String siteid);
	public List<TrackerDataBean> getTrackersLatestData(String siteid);
	public List<TrackerDataBean> getTrackersHistoryData(String siteid,String traid,String from,String to);
	
	public List<Adjustments> getAdjustments(String siteid);
}
