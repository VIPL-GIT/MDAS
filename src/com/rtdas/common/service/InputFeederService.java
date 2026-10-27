package com.rtdas.common.service;

import java.util.List;
import java.util.Map;

import com.rtdas.common.models.InputFeeder;

public interface InputFeederService {
	public int addFeeder(InputFeeder feeder);
	public void deleteFeeder(int feeder_id);
	public InputFeeder getFeeder(int feederId);	
	public List<InputFeeder> listFeeders(String clientId);
	public List<InputFeeder> listFeedersByssid(int ssid);
	
	public Map<Integer,String> getFeeders(String clientId);
	
	public String processRowInsertion(List<String> header,List<String> values,int clientId) throws Exception;

}