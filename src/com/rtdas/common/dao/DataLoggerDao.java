package com.rtdas.common.dao;

import java.util.List;

import com.rtdas.common.models.DataLogger;

public interface DataLoggerDao {

	public void addLogger(DataLogger dl);
	public void deleteLogger(int loggerId);
	public DataLogger getLogger(int loggerId);
	public DataLogger getLoggerBySlNo(String slno);
	public List<DataLogger> listLoggers(List<Integer> ids);
}
