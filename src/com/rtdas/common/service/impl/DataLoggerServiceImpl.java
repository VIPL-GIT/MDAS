package com.rtdas.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.DataLoggerDao;
import com.rtdas.common.models.DataLogger;
import com.rtdas.common.service.DataLoggerService;

@Service("loggerService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DataLoggerServiceImpl implements DataLoggerService {

	@Autowired
	private DataLoggerDao loggerdao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addLogger(DataLogger dl) {
		loggerdao.addLogger(dl);

	}

	@Override
	public void deleteLogger(int loggerId) {
		loggerdao.deleteLogger(loggerId);

	}

	@Override
	public DataLogger getLogger(int loggerId) {
		return loggerdao.getLogger(loggerId);
	}

	@Override
	public List<DataLogger> listLoggers(List<Integer> ids) {
		return loggerdao.listLoggers(ids);
	}

	@Override
	public DataLogger getLoggerBySlNo(String slno) {
		return loggerdao.getLoggerBySlNo(slno);
	}

}
