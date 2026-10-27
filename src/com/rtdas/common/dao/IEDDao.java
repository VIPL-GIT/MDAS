package com.rtdas.common.dao;

import java.util.List;
import java.util.Map;

import com.rtdas.common.beans.IEDMasterBean;
import com.rtdas.common.models.IEDMaster;

public interface IEDDao {

	public void addIED(IEDMasterBean imb);
	public void deleteIED(int ied_id);
	public IEDMaster getIED(int ied_id);
	public List<IEDMaster> listIED(List<Integer> loggerIds);
	public Map<Integer,String> devicesMappedToIED();
}
