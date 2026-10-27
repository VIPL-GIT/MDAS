package com.rtdas.common.dao;

import java.util.List;

import com.rtdas.common.models.Users;
import com.rtdas.common.models.alerttypes;
import com.rtdas.common.models.useralertsconfig;

public interface UserDao {
	public int addUser(Users user);
	public void deleteUser(Users user);
	public Users getUser(int userId);
	public String getUserImageUrl(int userId);
	public List<Users> listUsers(String clientId);
	public List<alerttypes> alertTypes();
	public List<com.rtdas.common.models.alertMode> alertMode();
	public List<useralertsconfig> listUserConfig();
	public boolean saveEventNotification(String username,String alerttype,String alertmode,String isenabled,int alertid);
	public boolean deleteuseralertconfig(String username);
	public boolean AddUserEventNotification(String username,String alerttype,String alertmode,String isenabled);
	public boolean chkEmailSms(String username,String alertmode);
	
}
