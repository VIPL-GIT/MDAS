package com.rtdas.common.dao;

import java.util.List;

import com.rtdas.common.models.PSLConsumer;

public interface PSLConsumerDao {

	public int addConsumer(PSLConsumer ht);
	public void deleteConsumer(int consumerid);
	public PSLConsumer getConsumer(int consumerId);	
	public List<PSLConsumer> listConsumers(String clientId);
	public List<PSLConsumer> listConsumersFid(String fid);
	
	public String processRowInsertion(List<String> header,List<String> values,int clientId) throws Exception;

}
