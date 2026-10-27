package com.rtdas.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.beans.IEDMasterBean;
import com.rtdas.common.dao.IEDDao;
import com.rtdas.common.models.IEDMaster;
import com.rtdas.common.service.IEDService;

@Service("ideservice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class IEDServiceImpl implements IEDService {

	@Autowired
	private IEDDao ieddao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addIED(IEDMasterBean imb) {
		ieddao.addIED(imb);

	}

	@Override
	public void deleteIED(int ied_id) {
		ieddao.deleteIED(ied_id);

	}

	@Override
	public IEDMaster getIED(int ied_id) {
		 return ieddao.getIED(ied_id);
	}

	@Override
	public List<IEDMaster> listIED(List<Integer> loggerIds) {
		return ieddao.listIED(loggerIds);
	}
	@Override
	public Map<Integer,String> devicesMappedToIED(){
		return ieddao.devicesMappedToIED();
	}

}
