package com.rtdas.common.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.controller.UtilityClass;
import com.rtdas.common.dao.LoginDao;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.Users;

@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Users getUserIdLogin(String userId) {
		
		return (Users) sessionFactory.getCurrentSession().createQuery("FROM Users WHERE userId='"+userId+"'").uniqueResult();
	}
	@Override
	public Map<String,List> getMappedActions(int roleId){
		Map<String,List> mappedActions=new HashMap();
		List<Object[]> result=sessionFactory.getCurrentSession().createSQLQuery("SELECT action_id,add_flg,delete_flg,edit_flg,view_flg FROM role_moduleaction_map WHERE role_id="+roleId).list();
		for(int i=0;i<result.size();i++){
			Object[] obj=result.get(i);
			List<String> actionList=new ArrayList<String>();
			      actionList.add(obj[1].toString()); //add
			      actionList.add(obj[2].toString()); //delete
			      actionList.add(obj[3].toString()); //edit
			      actionList.add(obj[4].toString()); //view
			mappedActions.put(obj[0].toString(), actionList);
		}
		
		return mappedActions;
	}
//	@Override	
//	public DashboardSupplyBean getDashboardSupply(List<HierarchyTitle> titles,int levelId,List<Integer> childIds){
//		
//	}
//	public DashboardSupplyBean getDashboardSupply_old(List<HierarchyTitle> titles,int levelId){
//		
//		
//	}
	public Map<String,String> convertToMap(List<Object[]> list){
		Map<String,String> map=new HashMap();
		for(Object[] obj:list){
			if(!map.containsKey(obj[0]))
			    map.put(obj[0].toString(), obj[1].toString());
		}
		return map;
		
	}
	public Map<String,Integer> convertToMap1(List<Object[]> list){
		Map<String,Integer> map=new HashMap();
		for(Object[] obj:list){
			if(!map.containsKey(obj[0]))
			    map.put(obj[0].toString(), Integer.parseInt(obj[1].toString()));
		}
		return map;
		
	}
    
    public Map<String,String> getSubstations(){    	
    	Map<String,String> sslist=new HashMap();
    	try{
    		String sql="SELECT hierarchy_id,hierarchy_name FROM hierarchy WHERE hierarchy_type_id IN(SELECT hierarchy_type_id FROM "
    				+ "hierarchy_master WHERE hierarchy_title LIKE 'Substation%')";
    		Query q=sessionFactory.getCurrentSession().createSQLQuery(sql);
    		List<Object[]> list=q.list();
    		if(!list.isEmpty()){
    		  for(Object[] obj:list){
    			  sslist.put(obj[0].toString(), obj[1].toString());
    		  }
    		}
    		
    	}catch(Exception e){
    		
    	}
    	
    	return sslist;
    }
}
