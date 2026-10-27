package com.rtdas.common.service;

import java.util.List;

import com.rtdas.common.models.ParameterMaster;

public interface ParameterService {
	public List<Object[]> listParameter(String clientId);

	public void addParameterMaster(ParameterMaster parameterModel);

	public void deleteParameter(int parameterId);

	public ParameterMaster getParameterMaster(int parameterId);
	
	public List<ParameterMaster> getParams(List<Integer> ids);

	
}
