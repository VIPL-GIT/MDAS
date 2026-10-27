package com.rtdas.common.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.service.ConsumerService;
import com.rtdas.common.dao.ConsumerDao;
import com.rtdas.common.models.HTConsumer;

@Service("consumerService")
@Transactional
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	private ConsumerDao dao;
	
	@Override
	public int addConsumer(HTConsumer ht) {
		// TODO Auto-generated method stub
		return dao.addConsumer(ht);
	}

	@Override
	public void deleteConsumer(int consumerid) {
		// TODO Auto-generated method stub
         dao.deleteConsumer(consumerid);
	}

	@Override
	public HTConsumer getConsumer(int consumerId) {
		// TODO Auto-generated method stub
		return dao.getConsumer(consumerId);
	}

	@Override
	public List<HTConsumer> listConsumers(String clientId) {
		// TODO Auto-generated method stub
		return dao.listConsumers(clientId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String processRowInsertion(List<String> header, List<String> values, int clientId) throws Exception {
		// TODO Auto-generated method stub
		return dao.processRowInsertion(header, values, clientId);
	}

	@Override
	public List<HTConsumer> listConsumersFid(String fid) {
		// TODO Auto-generated method stub
		return dao.listConsumersFid(fid);
	}

	@Override
	public List<HTConsumer> listUnmappedConsumersFid(String fid) {
		// TODO Auto-generated method stub
		return dao.listUnmappedConsumersFid(fid);
	}

	

}
