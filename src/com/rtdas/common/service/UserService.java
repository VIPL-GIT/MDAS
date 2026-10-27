package com.rtdas.common.service;
import java.util.List;


import com.rtdas.common.models.*;

public interface UserService {

	public int addUser(Users user);
	public void deleteUser(Users user);
	public Users getUser(int userId);
	public String getUserImageUrl(int userId);
	public List<Users> listUsers(String clientId);
	public List<Client> listClients();
	public List<UserRole> listUserRoles(int clientId);
 	public List<alerttypes> listAletTypes();
 	public List<alertMode> listAletMode();
 	public boolean saveEventNotification(String username,String alerttype,String alertmode,String isenabled,int alertid);
 	public List<useralertsconfig> listUserAlert();
 	public boolean deleteuseralertconfig(String username);
 	public boolean AddUserEventNotification(String username,String alerttype,String alertmode,String isenabled);
 	public boolean chkEmailSms(String username,String alertmode);
 	
}
