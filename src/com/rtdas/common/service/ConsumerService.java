package com.rtdas.common.service;



import java.util.List;


import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.HTConsumer;


public interface ConsumerService {
	
	public int addConsumer(HTConsumer ht);
	public void deleteConsumer(int consumerid);
	public HTConsumer getConsumer(int consumerId);	
	public List<HTConsumer> listConsumers(String clientId);
	public List<HTConsumer> listConsumersFid(String fid);
	
	public List<HTConsumer> listUnmappedConsumersFid(String fid);
	
	public String processRowInsertion(List<String> header,List<String> values,int clientId) throws Exception;

}
