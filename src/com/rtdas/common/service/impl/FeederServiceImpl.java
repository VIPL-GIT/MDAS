package com.rtdas.common.service.impl;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.FeederDao;
import com.rtdas.common.models.Feeder;
import com.rtdas.common.service.FeederService;




@Service("feederService")
@Transactional
public class FeederServiceImpl implements FeederService {
	
	@Autowired
	private FeederDao fdao;

	@Override
	public int addFeeder(Feeder feeder) {
		return fdao.addFeeder(feeder);
	}

	@Override
	public void deleteFeeder(int feeder_id) {
		fdao.deleteFeeder(feeder_id);

	}

	@Override
	public Feeder getFeeder(int feederId) {
		return fdao.getFeeder(feederId);
	}

	@Override
	public List<Feeder> listFeeders(String clientId) {
		return fdao.listFeeders(clientId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String processRowInsertion(List<String> header,List<String> values,  int clientId) throws Exception {
		// TODO Auto-generated method stub
		return fdao.processRowInsertion(header,values,  clientId);
	}

	@Override
	public Map<Integer, String> getFeeders(String clientId) {
		// TODO Auto-generated method stub
		return fdao.getFeeders(clientId);
	}

	@Override
	public List<Feeder> listFeedersByssid(int ssid) {
		// TODO Auto-generated method stub
		return fdao.listFeedersByssid(ssid);
	}

}
