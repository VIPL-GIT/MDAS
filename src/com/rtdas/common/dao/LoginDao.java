package com.rtdas.common.dao;

import java.util.List;
import java.util.Map;

import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.Users;

public interface LoginDao {
	
	public Users getUserIdLogin(String userId);
	public Map<String,List> getMappedActions(int roleId);
//	public DashboardSupplyBean getDashboardSupply(List<HierarchyTitle> titles,int levelId,List<Integer> childIds);
	public Map<String,String> getSubstations();
}
