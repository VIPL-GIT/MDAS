package com.rtdas.common.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.DTRDao;
import com.rtdas.common.models.DTR;
import com.rtdas.common.service.DTRService;


@Service("dtrService")
@Transactional
public class DTRServiceImpl implements DTRService {

	@Autowired
	private DTRDao dao;
	
	@Override
	public int addDTR(DTR dtr) {
		// TODO Auto-generated method stub
		return dao.addDTR(dtr);
	}

	@Override
	public void deleteDTR(int dtrid) {
		// TODO Auto-generated method stub
         dao.deleteDTR(dtrid); 

	}

	@Override
	public DTR getDTR(int dtrId) {
		// TODO Auto-generated method stub
		return dao.getDTR(dtrId);
	}

	@Override
	public List<DTR> listDTRs(String clientId) {
		// TODO Auto-generated method stub
		return dao.listDTRs(clientId);
	}

	@Override
	public String processRowInsertion(List<String> header, List<String> values, int clientId) throws Exception {
		// TODO Auto-generated method stub
		return dao.processRowInsertion(header, values, clientId);
	}

	@Override
	public List<DTR> listDTRsByfId(String fid) {
		// TODO Auto-generated method stub
		return dao.listDTRsByfId(fid);
	}

	

}
