package com.rtdas.common.dao;



import java.util.List;
import java.util.Map;

import com.rtdas.common.models.Feeder;


public interface FeederDao {

	public int addFeeder(Feeder feeder);
	public void deleteFeeder(int feeder_id);
	public Feeder getFeeder(int feederId);	
	public List<Feeder> listFeeders(String clientId);
	public Map<Integer,String> getFeeders(String clientId);
	public List<Feeder> listFeedersByssid(int ssid);
	public String processRowInsertion(List<String> header,List<String> values,int clientId) throws Exception;
}
