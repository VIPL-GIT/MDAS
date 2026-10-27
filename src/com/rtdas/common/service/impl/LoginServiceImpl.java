package com.rtdas.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.LoginDao;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.Users;
import com.rtdas.common.service.LoginService;

@Service("loginService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public Users getUserIdLogin(String userId) {
		
		return loginDao.getUserIdLogin(userId);
	}
	@Override
	public Map<String,List> getMappedActions(int roleId){
		return loginDao.getMappedActions(roleId);
	}
//	@Override
//	public DashboardSupplyBean getDashboardSupply(List<HierarchyTitle> titles,int levelId,List<Integer> childIds){
//		return loginDao.getDashboardSupply(titles,levelId,childIds);
//	}
	@Override
	public Map<String, String> getSubstations() {
		return loginDao.getSubstations();
	}

}
