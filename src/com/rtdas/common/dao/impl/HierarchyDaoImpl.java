package com.rtdas.common.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.rtdas.common.dao.HierarchyDao;
import com.rtdas.common.models.Hierarchy;

@Repository("hierarchyDao")
public class HierarchyDaoImpl implements HierarchyDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addHierarchy(Hierarchy hierarchy) {
		System.out.println("hierarcht =y save");
		try{			
		    sessionFactory.getCurrentSession().saveOrUpdate(hierarchy);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void deleteHierarchy(Hierarchy hierarchy) {
		try{
			String sql="delete from Hierarchy where hierarchy_id="+hierarchy.getHierarchy_id();
			System.out.println("delete sql "+sql);
			Query q=sessionFactory.getCurrentSession().createQuery(sql);
			q.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}

	}

	@Override
	public Hierarchy getHierarchy(int id) {
		return (Hierarchy)sessionFactory.getCurrentSession().createQuery("from Hierarchy where hierarchy_id="+id).uniqueResult();
	}
	
	@Override
	public Map<Integer,String> listHierarchyByClient(int clientId){
		System.out.println("client id  -->  "+clientId);
		Map<Integer,String> hmap=new HashMap();
		 List<Hierarchy> hi=sessionFactory.getCurrentSession().createQuery("from Hierarchy where client_id="+clientId).list();
		 for(Hierarchy h:hi){
			 hmap.put(h.getHierarchy_id(), h.getHierarchy_name());
		 }
		 return hmap;
	}
	
	@Override
	public List<Hierarchy> getHierarchyByType(int typeId){
		//System.out.println(typeId+" typeId ");
		return sessionFactory.getCurrentSession().createQuery("from Hierarchy where hierarchy_type_id="+typeId).list();
	}
    @Override
    public List<Hierarchy> getHierarchyByParentId(int parentId){
    	return sessionFactory.getCurrentSession().createQuery("from Hierarchy where parent_id="+parentId).list();
    }
	@Override
	public List<Object[]> listHierarchy(int level,int typeId,int clientId) {
		System.out.println();
		List<Object[]> objList=new ArrayList();
		try{
		String sql="";
		System.out.println(clientId+" --> "+typeId+"--->"+level);
		for(int i=0;i<level;i++){
			if(i==0)
				sql="SELECT hierarchy_name name_"+i+",hierarchy_id id_"+i+",parent_id pid_"+i+" FROM hierarchy WHERE hierarchy_type_id="+typeId+" and client_id="+clientId;
			else
			{
				String sql_tmp = "";
				sql_tmp="select a"+i+".hierarchy_name name_"+i+",a"+i+".hierarchy_id id_"+i+",a"+i+".parent_id pid_"+i;
				for(int l=i-1;l>=0;l--)
				{
					sql_tmp = sql_tmp + ",a"+(i-1)+".name_"+l+",a"+(i-1)+".id_"+l+",a"+(i-1)+".pid_"+l;
						
				}
				sql_tmp = sql_tmp +" from hierarchy a"+i+",("+sql+") a"+(i-1)+" where a"+i+".hierarchy_id=a"+(i-1)+".pid_"+(i-1);
				sql = sql_tmp;
				
			}
		}
		System.out.println("sss--->"+sql);
		objList=(List<Object[]>)sessionFactory.getCurrentSession().createSQLQuery(sql).list();
//		for(int i=0;i<objList.size();i++){
//			 Object[] obj=(Object[])objList.get(i);
//			System.out.println(obj[0]+" "+obj[1]+" "+obj[2]+" "+obj[3]);
//		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return objList;
	}
	
	
	public List<Object[]> listHierarchyById(int level,int id){
		List<Object[]> objList=new ArrayList();
		String sql="";
		System.out.println("--->"+level);
		for(int i=0;i<level;i++){
			if(i==0)
				sql="SELECT hierarchy_name name_"+i+",hierarchy_id id_"+i+",parent_id pid_"+i+" FROM hierarchy WHERE hierarchy_id="+id;
			else
			{
				String sql_tmp = "";
				sql_tmp="select a"+i+".hierarchy_name name_"+i+",a"+i+".hierarchy_id id_"+i+",a"+i+".parent_id pid_"+i;
				for(int l=i-1;l>=0;l--)
				{
					sql_tmp = sql_tmp + ",a"+(i-1)+".name_"+l+",a"+(i-1)+".id_"+l+",a"+(i-1)+".pid_"+l;
						
				}
				sql_tmp = sql_tmp +" from hierarchy a"+i+",("+sql+") a"+(i-1)+" where a"+i+".hierarchy_id=a"+(i-1)+".pid_"+(i-1);
				sql = sql_tmp;
				
			}
		}
		System.out.println("--->"+sql);
		objList=sessionFactory.getCurrentSession().createSQLQuery(sql).list();
        //System.out.println("objList  ==> "+objList);
		return objList;
	}
	
	  public List<Object[]> listAllChildsByParentId(int level,int titlesize,int parentId){
		System.out.println(level+" <---> "+titlesize+"  <---> "+parentId);
		List<Object[]> objList=new ArrayList();
		String sql="SELECT a6.hierarchy_id id_6,a6.hierarchy_name name_6,a6.parent_id pid_6,a5.id_5,a5.name_5,a5.pid_5,a5.id_4,a5.name_4,a5.pid_4,a5.id_3,a5.name_3,a5.pid_3,a5.id_2,a5.name_2,a5.pid_2,a5.id_1,a5.name_1,a5.pid_1,a5.id_0,a5.name_0,a5.pid_0 FROM hierarchy a6,(SELECT a5.hierarchy_id id_5,a5.hierarchy_name name_5,a5.parent_id pid_5,a4.id_4,a4.name_4,a4.pid_4,a4.id_3,a4.name_3,a4.pid_3,a4.id_2,a4.name_2,a4.pid_2,a4.id_1,a4.name_1,a4.pid_1,a4.id_0,a4.name_0,a4.pid_0 FROM hierarchy a5,(SELECT a4.hierarchy_id id_4,a4.hierarchy_name name_4,a4.parent_id pid_4,a3.id_3,a3.name_3,a3.pid_3,a3.id_2,a3.name_2,a3.pid_2,a3.id_1,a3.name_1,a3.pid_1,a3.id_0,a3.name_0,a3.pid_0 FROM hierarchy a4,(SELECT a3.hierarchy_id id_3,a3.hierarchy_name name_3,a3.parent_id pid_3,a2.id_2,a2.name_2,a2.pid_2,a2.id_1,a2.name_1,a2.pid_1,a2.id_0,a2.name_0,a2.pid_0 FROM hierarchy a3,(SELECT a2.hierarchy_id id_2,a2.hierarchy_name name_2,a2.parent_id pid_2,a1.id_1,a1.name_1,a1.pid_1,a1.id_0,a1.name_0,a1.pid_0 FROM hierarchy a2,(SELECT a1.hierarchy_id id_1,a1.hierarchy_name name_1,a1.parent_id pid_1,a0.id_0,a0.name_0,a0.pid_0 FROM hierarchy a1,(SELECT hierarchy_id id_0,hierarchy_name name_0,parent_id pid_0 FROM hierarchy WHERE parent_id=7236) a0 WHERE a1.parent_id=a0.id_0) a1 WHERE a2.parent_id=a1.id_1) a2 WHERE a3.parent_id=a2.id_2) a3 WHERE a4.parent_id=a3.id_3) a4 WHERE a5.parent_id=a4.id_4) a5 WHERE a6.parent_id=a5.id_5";
		if(level==titlesize-1)
			sql="SELECT hierarchy_id id_0,hierarchy_name nm_0,parent_id pid_0 FROM hierarchy WHERE parent_id='"+parentId+"'";
		for(int i=0;i<titlesize-level;i++){
			if(i==0)
				sql="SELECT hierarchy_id id_"+i+",hierarchy_name name_"+i+",parent_id pid_"+i+" FROM hierarchy WHERE parent_id='"+parentId+"'";
			else
			{
				String sql_tmp = "";
				sql_tmp="select a"+i+".hierarchy_id id_"+i+",a"+i+".hierarchy_name name_"+i+",a"+i+".parent_id pid_"+i;
				for(int l=i-1;l>=0;l--)
				{
					sql_tmp = sql_tmp + ",a"+(i-1)+".id_"+l+",a"+(i-1)+".name_"+l+",a"+(i-1)+".pid_"+l;
				}
				sql_tmp = sql_tmp +" from hierarchy a"+i+",("+sql+") a"+(i-1)+" where a"+i+".parent_id=a"+(i-1)+".id_"+(i-1);
				sql = sql_tmp;
			}
		}
		
		
		System.out.println("all childs query--->"+sql);
		objList=sessionFactory.getCurrentSession().createSQLQuery(sql).list();
       // System.out.println("objList --> "+objList.size());
		return objList;
	}

}
