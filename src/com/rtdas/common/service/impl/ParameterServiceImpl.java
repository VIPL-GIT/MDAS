package com.rtdas.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.ParameterDao;
import com.rtdas.common.models.ParameterMaster;
import com.rtdas.common.service.ParameterService;

@Service("paramService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ParameterServiceImpl implements ParameterService {

	@Autowired
	private ParameterDao parameterdao;
	
	@Override
	public List<Object[]> listParameter(String clientId) {
		 return parameterdao.listParameter(clientId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addParameterMaster(ParameterMaster parameterModel) {
		parameterdao.addParameterMaster(parameterModel);

	}

	@Override
	public void deleteParameter(int parameterId) {
		parameterdao.deleteParameter(parameterId);

	}

	@Override
	public ParameterMaster getParameterMaster(int parameterId) {
		 return parameterdao.getParameterMaster(parameterId);
	}

	@Override
	public List<ParameterMaster> getParams(List<Integer> ids) {
		// TODO Auto-generated method stub
		return parameterdao.getParams(ids);
	}


}
