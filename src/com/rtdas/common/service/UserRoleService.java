package com.rtdas.common.service;

import java.util.List;

import com.rtdas.common.beans.SubmoduleActionBean;
import com.rtdas.common.models.*;
public interface UserRoleService {
	public void addUserRole(UserRole userRole,String actionFlgs);
	public void deleteUserRole(UserRole roleId);
	public UserRole getUserRole(int roleId);
	public List<UserRole> listUserRole(int clientId);
	public List<Client> listClients();
	public List<UserRole> getUserRolesByClient(int clientId);
	public List<SubmoduleActionBean> listsubmoduleActions();
	public List getActionFlgs(int roleId);
	public List<Integer> getsubmodulesByrole(int roleId);

}
