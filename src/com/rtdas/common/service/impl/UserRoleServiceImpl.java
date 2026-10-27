package com.rtdas.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.beans.SubmoduleActionBean;
import com.rtdas.common.dao.ClientDao;
import com.rtdas.common.dao.UserRoleDao;
import com.rtdas.common.models.*;
import com.rtdas.common.service.UserRoleService;

@Service("roleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private ClientDao clientDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUserRole(UserRole userRole,String actionFlgs) {
		 userRoleDao.addUserRole(userRole,actionFlgs);

	}

	@Override
	public void deleteUserRole(UserRole roleId) {
		userRoleDao.deleteUserRole(roleId);

	}

	@Override
	public UserRole getUserRole(int roleId) {
		return userRoleDao.getUserRole(roleId);
	}

	@Override
	public List<UserRole> listUserRole(int clientId) {
		return userRoleDao.listUserRole(clientId);
	}

	@Override
	public List<Client> listClients() {
		return clientDao.listClients();
	}
	@Override
	public List<UserRole> getUserRolesByClient(int clientId){
		return userRoleDao.getRoles(clientId);
	}
	@Override
	public List<SubmoduleActionBean> listsubmoduleActions(){
		return userRoleDao.listsubmoduleActions();
	}
	@Override
	public List getActionFlgs(int roleId){
		return userRoleDao.getActionFlgs(roleId);
	}
	
	@Override
	public List<Integer> getsubmodulesByrole(int roleId){
		return userRoleDao.getsubmodulesByrole(roleId);
	}

}
