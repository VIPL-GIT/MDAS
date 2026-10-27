package com.rtdas.common.dao;

import java.util.List;
import java.util.Map;

import com.rtdas.common.beans.SubmoduleActionBean;
import com.rtdas.common.models.Client;
import com.rtdas.common.models.UserRole;
import com.rtdas.common.models.Users;

public interface UserRoleDao {

	public void addUserRole(UserRole userRole,String actionFlgs);
	public void deleteUserRole(UserRole roleId);
	public UserRole getUserRole(int roleId);
	public List<UserRole> listUserRole(int clientId);
	public List<UserRole> getRoles(int clientId);
	public List<SubmoduleActionBean> listsubmoduleActions();
	public List getActionFlgs(int roleId);
	public List<Integer> getsubmodulesByrole(int roleId);
}
