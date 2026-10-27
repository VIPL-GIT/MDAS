package com.rtdas.common.dao;

import java.util.List;
import java.util.Map;

import com.rtdas.common.models.InputFeeder;

public interface InputFeederDao {
	public int addFeeder(InputFeeder feeder);
	public void deleteFeeder(int feeder_id);
	public InputFeeder getFeeder(int feederId);	
	public List<InputFeeder> listFeeders(String clientId);
	public Map<Integer,String> getFeeders(String clientId);
	public List<InputFeeder> listFeedersByssid(int ssid);
	public String processRowInsertion(List<String> header,List<String> values,int clientId) throws Exception;
}

