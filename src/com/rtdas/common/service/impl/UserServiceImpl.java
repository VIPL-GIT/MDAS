package com.rtdas.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.ClientDao;
import com.rtdas.common.dao.UserDao;
import com.rtdas.common.dao.UserRoleDao;
import com.rtdas.common.models.*;
import com.rtdas.common.service.UserService;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int addUser(Users user) {
		return userDao.addUser(user);

	}

	@Override
	public void deleteUser(Users user) {
		userDao.deleteUser(user);

	}

	@Override
	public Users getUser(int userId) {
		return userDao.getUser(userId); //userId is nothing but serial no(primary key)
	}

	@Override
	public List<Users> listUsers(String clientId) {
		return userDao.listUsers(clientId);
	}
	@Override
	public List<Client> listClients() {
		
		return clientDao.listClients();
	}
	
	
	@Override
	public List<UserRole> listUserRoles(int clientId){
		return userRoleDao.listUserRole(clientId);
	}

	
	@Override
	public String getUserImageUrl(int userId){
		return userDao.getUserImageUrl(userId);
	}

	@Override
	public List<alerttypes> listAletTypes() {
		return userDao.alertTypes();
	}

	@Override
	public List<alertMode> listAletMode() {
		return userDao.alertMode();
	}


	
	
	
	@Override
	public List<useralertsconfig> listUserAlert() {
		// TODO Auto-generated method stub
	 return userDao.listUserConfig();
	}

	@Override
	public boolean deleteuseralertconfig(String username) {
		
		 return userDao.deleteuseralertconfig(username);
	}

	@Override
	public boolean saveEventNotification(String username, String alerttype, String alertmode, String isenabled,int alertid) {
		return userDao.saveEventNotification(username,alerttype,alertmode,isenabled,alertid);
	
	}

	
	@Override
	public boolean AddUserEventNotification(String username, String alerttype, String alertmode, String isenabled) {
		return userDao.AddUserEventNotification(username,alerttype,alertmode,isenabled);
	
	}

	@Override
	public boolean chkEmailSms(String username, String alertmode) {
		 return userDao.chkEmailSms(username,alertmode);
	}

	

}
