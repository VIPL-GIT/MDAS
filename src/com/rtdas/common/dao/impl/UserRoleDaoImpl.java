package com.rtdas.common.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.beans.SubmoduleActionBean;
import com.rtdas.common.dao.UserRoleDao;
import com.rtdas.common.models.UserRole;

@Repository("userRoleDao")
public class UserRoleDaoImpl implements UserRoleDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addUserRole(UserRole userRole,String actionFlgs) {
		Session session=sessionFactory.getCurrentSession();
		//System.out.println("--------action flgs"+actionFlgs);
		try{
			int id;
			if(userRole.getRole_id()==0){
				userRole.setActive_flg(1);
				id=(int)session.save(userRole);
			}else{
				session.saveOrUpdate(userRole);
				id=userRole.getRole_id();
			}
			session.flush();
            session.clear();
            session.createSQLQuery("delete from role_moduleaction_map where role_id="+id).executeUpdate();
            
            List actionIds=Arrays.asList(actionFlgs.split(","));
            Collections.sort(actionIds);
            Map<String,List> actionMaps=new HashMap(); //actions that user selected
            List actionTypes;
            
            for(int i=0;i<actionIds.size();i++){
            	
            	String actionId=actionIds.get(i).toString().split("_")[0];
            	String action=actionIds.get(i).toString().split("_")[1];
            	
            	if(actionMaps.containsKey(actionId)){
            		actionMaps.get(actionId).add(action);
            	}else{
            		actionTypes=new ArrayList();
            		actionTypes.add(action);
            		actionMaps.put(actionId, actionTypes);
            	}
            	
            }
            System.out.println("action maps-------->"+actionMaps);
  //total actions
            List<SubmoduleActionBean> actions=listsubmoduleActions();
            for(SubmoduleActionBean bean:actions){
             System.out.println("--->"+bean.getAction_id());
            	String sql="insert into role_moduleaction_map(role_id,action_id,add_flg,delete_flg,edit_flg,view_flg) values("+id+","+bean.getAction_id();
            	
            	if(actionMaps.containsKey(""+bean.getAction_id())){
            //System.out.println("---action id is found and its actions :"+actionMaps.get(""+bean.getAction_id()));		
                	List types=(List)actionMaps.get(""+bean.getAction_id());
            	
            		if(types.contains("add")) sql=sql+",1";
            		else sql=sql+",0";
            		if(types.contains("delete")) sql=sql+",1";
            		else sql=sql+",0";
            		if(types.contains("edit")) sql=sql+",1";
            		else sql=sql+",0";
            		if(types.contains("view")) sql=sql+",1";
            		else sql=sql+",0";
            	
            	sql=sql+")";
            	session.createSQLQuery(sql).executeUpdate();
				session.flush();
	            session.clear();
            }//if action is selected
            	else{
            		sql=sql+",0,0,0,0)";
            		session.createSQLQuery(sql).executeUpdate();
    				session.flush();
    	            session.clear();
            	}
            }//for all actions
            
		}catch(Exception e){
			
		}

	}

	@Override
	public void deleteUserRole(UserRole roleId) {
		try{
			String sql="delete from UserRole where role_id="+roleId.getRole_id();
			Query q=sessionFactory.getCurrentSession().createQuery(sql);
			q.executeUpdate();
			//deleting user role related actions in role_moduleaction_map table also;
			sessionFactory.getCurrentSession().createSQLQuery("delete from role_moduleaction_map where role_id="+roleId).executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}

	}

	@Override
	public UserRole getUserRole(int roleId) {
		return (UserRole)sessionFactory.getCurrentSession().createQuery("from UserRole where role_id="+roleId).uniqueResult();
	}

	@Override
	public List<UserRole> listUserRole(int clientId) {
		String sql="";
		if(clientId==0)
			sql="from UserRole";
		else
			sql="from UserRole where client_id="+clientId;
		Query q=sessionFactory.getCurrentSession().createQuery(sql);
		List<UserRole> list=q.list();
		return list;
	}
	@Override
	public List<UserRole> getRoles(int clientId){
		Query q=sessionFactory.getCurrentSession().createQuery("from UserRole where client_id="+clientId);
		List<UserRole> list=q.list();
		return list;
	}
	@Override
	public List<SubmoduleActionBean> listsubmoduleActions(){
		List<SubmoduleActionBean> moduleList=new ArrayList();
		try{
		SQLQuery sq=sessionFactory.getCurrentSession().createSQLQuery("SELECT m.module_id,m.module_title,sm.submodule_id,sm.submodule_title,a.action_id,a.action_title FROM module m,submodule sm,module_actions a WHERE m.module_id=sm.module_id AND a.submodule_id=sm.submodule_id and m.active_flg=1 and sm.active_flg=1 ORDER BY module_id");
		System.out.println("Module ---------"+sq);
		List<Object[]> result=sq.list(); 
		
		for(Object[] bean:result){
			SubmoduleActionBean smb=new SubmoduleActionBean();
			smb.setModule_id(Integer.parseInt(bean[0].toString()));
			smb.setModule_title(bean[1].toString());
			smb.setSubmodule_id(Integer.parseInt(bean[2].toString()));
			smb.setSubmodule_title(bean[3].toString());
			smb.setAction_id(Integer.parseInt(bean[4].toString()));
			smb.setAction_title(bean[5].toString());
			moduleList.add(smb);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return moduleList;
	}
	
	@Override
	public List getActionFlgs(int roleId){
		List mappedActions=new ArrayList();
		List<Object[]> actions=sessionFactory.getCurrentSession().createSQLQuery("select action_id,add_flg,delete_flg,edit_flg,view_flg from role_moduleaction_map where role_id="+roleId).list();
		for(Object[] bean:actions){
			if(bean[1]!=null && bean[1].toString().equals("1"))
				mappedActions.add(bean[0]+"_add");
			if(bean[2]!=null && bean[2].toString().equals("1"))
				mappedActions.add(bean[0]+"_delete");
			if(bean[3]!=null && bean[3].toString().equals("1"))
				mappedActions.add(bean[0]+"_edit");
			if(bean[4]!=null && bean[4].toString().equals("1"))
				mappedActions.add(bean[0]+"_view");
		}
		//System.out.println("---->"+mappedActions);
		return mappedActions;
	}
	
	@Override
	public List<Integer> getsubmodulesByrole(int roleId){
		List<Integer> submodules=new ArrayList();
		try{
			String sql="SELECT submodule_id FROM module_actions WHERE action_id IN(SELECT action_id FROM role_moduleaction_map WHERE role_id="+roleId+" AND (add_flg!=0 OR delete_flg!=0 OR edit_flg!=0 OR view_flg!=0))";
          // System.out.println("my sql ==>  "+sql);
			submodules=sessionFactory.getCurrentSession().createSQLQuery(sql).list();
//			for(Object[] bean:subs){
//				submodules.add(Integer.parseInt(bean[0].toString()));
//			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return submodules;
	}

}
