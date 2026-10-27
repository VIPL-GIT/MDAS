package com.rtdas.common.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.dao.UserDao;
import com.rtdas.common.models.UserRole;
import com.rtdas.common.models.Users;
import com.rtdas.common.models.alerttypes;
import com.rtdas.common.models.useralertsconfig;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int addUser(Users user) {
		Session session=sessionFactory.getCurrentSession();
		int userSlno=user.getSlno();
		
		try{
			//String encPwd=new UtilityClass().encryptPassword(user.getPassword());			
//		   sessionFactory.getCurrentSession().saveOrUpdate(user);
			//System.out.println("user sl no--------->"+userSlno);
			if(userSlno==0){
//				String encPwd=new EncryptPassword().encrypt(user.getPassword());
//				user.setPassword(encPwd);
//				System.out.println(encPwd+"--->"+user.getPassword());
				userSlno=(int)session.save(user);
			}else{				
				session.saveOrUpdate(user);
				userSlno=user.getSlno();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return userSlno;
	}

	@Override
	public void deleteUser(Users user) {
		try{
			String sql="delete from Users where slno="+user.getSlno();
			Query q=sessionFactory.getCurrentSession().createQuery(sql);
			q.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
	}


	
	
	@Override
	public Users getUser(int userId) {
		System.out.println("user id============"+userId);
		return (Users)sessionFactory.getCurrentSession().createQuery("from Users where slno="+userId).uniqueResult();
	}

	@Override
	public List<Users> listUsers(String clientId) {
		String sql="";
		if(clientId.equals("0"))
			sql="from Users";
		else
			sql="from Users where clientId="+clientId;
		Query q=sessionFactory.getCurrentSession().createQuery(sql);
		List<Users> list=q.list();
		return list;
	}
	@Override
	public String getUserImageUrl(int userId){
		Users user=(Users)sessionFactory.getCurrentSession().createQuery("from Users where slno="+userId).uniqueResult();
		return user.getUserImage();
	}

	@Override
	public List<alerttypes> alertTypes() {
		String sql="from alerttypes";
		Query q=sessionFactory.getCurrentSession().createQuery(sql);
		List<alerttypes> list=q.list();
		return list;
	}

	@Override
	public List<com.rtdas.common.models.alertMode> alertMode() {
	
		String sql="from alertMode";
		Query q=sessionFactory.getCurrentSession().createQuery(sql);
		List<com.rtdas.common.models.alertMode> list=q.list();
		return list;
		
	}

	@Override
	public boolean saveEventNotification(String username, String alerttype, String alertmode,String isenabled,int alertid) {
	    	String myenablevalue=isenabled;
		    int isenable=1;
	    	if(myenablevalue.equalsIgnoreCase("True")){
	    		isenable=0;
	    	}
	    	System.out.println(" --------- ");
	    	System.out.println(username);
	    	System.out.println(alerttype);
	    	System.out.println(alertmode);
	    	System.out.println(isenabled);
	    	System.out.println(alertid);
	    	
	    	try{
		    Session session = sessionFactory.getCurrentSession();
	        session.beginTransaction();
	        Query query = session.createQuery("UPDATE useralertsconfig SET type_of_alert = '"+alerttype+"' ,mode_of_alert = '"+alertmode+"' ,is_enabled='"+isenabled+"' WHERE user_alert_id = '"+alertid+"'");
	    	int result = query.executeUpdate();
	    	session.getTransaction().commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}

		return false;
	}

	
	@Override
	public boolean AddUserEventNotification(String username, String alerttype, String alertmode,String isenabled) {
		System.out.println(" add user dao impl ");
	    	int isenable=Integer.parseInt(isenabled);
		
	    	
	    	String hid="";
	    	try{
	    	    //Users user=sessionFactory.getCurrentSession().createQuery("from Users where USER_ID='"+username+"'");
	    		//user.getHierarchy();
				//System.out.println( " hid  --> "+hid );
				
				
				String sql="from Users where USER_ID='"+username+"'";
				Query q=sessionFactory.getCurrentSession().createQuery(sql);
				List<Users> list=q.list();
				
				for(int i = 0; i < list.size(); i++)
				{
				    System.out.println(list.get(i));
				    Users user=list.get(i);
			        hid=user.getHierarchy();
			        System.out.println( "hid --> "+hid);
				}
				
		    	}catch(Exception e){
		    		e.printStackTrace();
		    	}
	    	
	    	String hierarchyid=hid.toString();
	    	System.out.println("is enabled -->  "+isenabled);
	    	
		    Session session = sessionFactory.getCurrentSession();
	        session.beginTransaction();
	        useralertsconfig userconfig = new useralertsconfig();
	    
	        userconfig.setUser_name(username);
	        userconfig.setType_of_alert(alerttype);
	        userconfig.setMode_of_alert(alertmode);
	        userconfig.setIs_enabled(isenable);
	        userconfig.setUser_hierarchy_id(hierarchyid);
	       
	        
	        session.save(userconfig);
	        session.getTransaction().commit();
		    session.close();
	      
		return false;
	}

	
	
	
	
	@Override
	public List<useralertsconfig> listUserConfig() {
		String sql="from useralertsconfig";
		Query q=sessionFactory.getCurrentSession().createQuery(sql);
		List<useralertsconfig> list=q.list();
		return list;
	}

	@Override
	public boolean deleteuseralertconfig(String username) {
		try{
			String sql="delete from useralertsconfig where user_alert_id="+username;
			System.out.println("sql  ============"+sql);
			Query q=sessionFactory.getCurrentSession().createQuery(sql);
			q.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
		return false;
	}

	@Override
	public boolean chkEmailSms(String username, String alertmode) {
		// TODO Auto-generated method stub
		
		
//		String myquery="";
//		if(alertmode.equalsIgnoreCase("")){
//		}
//		else if(alertmode.equalsIgnoreCase("")){
//		}
//		else{
//		}
//		
		try{
		String sql="from Users where clientId='"+username+"'";
		Query q=sessionFactory.getCurrentSession().createQuery(sql);
		List<Users> list=q.list();
		
		for(int i = 0; i < list.size(); i++)
		{
		    System.out.println(list.get(i));
		    
		    Users user=list.get(i);

	        System.out.println("user mobiile --> "+user.getMobileNumber()+"  user email -->  "+user.getEmail());
		    
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	

}
