package com.rtdas.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.PSLConsumerDao;
import com.rtdas.common.models.PSLConsumer;
import com.rtdas.common.service.PSLConsumerService;


@Service("pslconsumerService")
@Transactional
public class PSLConsumerServiceImpl implements PSLConsumerService {

	@Autowired
	private PSLConsumerDao dao;
	
	@Override
	public int addConsumer(PSLConsumer ht) {
		// TODO Auto-generated method stub
		return dao.addConsumer(ht);
	}

	@Override
	public void deleteConsumer(int consumerid) {
		// TODO Auto-generated method stub
              dao.deleteConsumer(consumerid);
	}

	@Override
	public PSLConsumer getConsumer(int consumerId) {
		// TODO Auto-generated method stub
		return dao.getConsumer(consumerId);
	}

	@Override
	public List<PSLConsumer> listConsumers(String clientId) {
		// TODO Auto-generated method stub
		return dao.listConsumers(clientId);
	}

	@Override
	public List<PSLConsumer> listConsumersFid(String fid) {
		// TODO Auto-generated method stub
		return dao.listConsumersFid(fid);
	}

	@Override
	public String processRowInsertion(List<String> header, List<String> values, int clientId) throws Exception {
		// TODO Auto-generated method stub
		return dao.processRowInsertion(header, values, clientId);
	}

}
