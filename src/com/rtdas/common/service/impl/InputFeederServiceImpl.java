package com.rtdas.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.InputFeederDao;
import com.rtdas.common.models.InputFeeder;
import com.rtdas.common.service.InputFeederService;

@Service("ipfeederService")
@Transactional
public class InputFeederServiceImpl implements InputFeederService {

	@Autowired
	private InputFeederDao fdao;
	
	@Override
	public int addFeeder(InputFeeder feeder) {
		// TODO Auto-generated method stub
		return fdao.addFeeder(feeder);
	}

	@Override
	public void deleteFeeder(int feeder_id) {
		// TODO Auto-generated method stub
          fdao.deleteFeeder(feeder_id);
	}

	@Override
	public InputFeeder getFeeder(int feederId) {
		// TODO Auto-generated method stub
		return fdao.getFeeder(feederId);
	}

	@Override
	public List<InputFeeder> listFeeders(String clientId) {
		// TODO Auto-generated method stub
		return fdao.listFeeders(clientId);
	}

	@Override
	public List<InputFeeder> listFeedersByssid(int ssid) {
		// TODO Auto-generated method stub
		return fdao.listFeedersByssid(ssid);
	}

	@Override
	public Map<Integer, String> getFeeders(String clientId) {
		// TODO Auto-generated method stub
		return fdao.getFeeders(clientId);
	}

	@Override
	public String processRowInsertion(List<String> header, List<String> values, int clientId) throws Exception {
		// TODO Auto-generated method stub
		return fdao.processRowInsertion(header, values, clientId);
	}

}
