package com.rtdas.common.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.dao.HierarchyTitleDao;
import com.rtdas.common.models.HierarchyTitle;

@Repository("titleDao")
public class HierarchyTitleDaoImpl implements HierarchyTitleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	
	public void addTitle(HierarchyTitle title) {
		try{	
			System.out.println("-------------"+title.getHierarchy_type_id());
		    sessionFactory.getCurrentSession().saveOrUpdate(title);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void deleteTitle(HierarchyTitle title) {
		try{
			String sql="delete from HierarchyTitle where hierarchy_type_id="+title.getHierarchy_type_id();
			Query q=sessionFactory.getCurrentSession().createQuery(sql);
			q.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}

	}

	@Override
	public HierarchyTitle getTitles(int titleId) {
		return (HierarchyTitle)sessionFactory.getCurrentSession().createQuery("from HierarchyTitle where hierarchy_type_id="+titleId).uniqueResult();
	}

	@Override
	public List<HierarchyTitle> listTitles(int clientId) {
		String sql="";
		if(clientId==0)
			sql="from HierarchyTitle ORDER BY parent_title_id ";
		else
			sql="from HierarchyTitle where client_id="+clientId+" ORDER BY parent_title_id" ;
		
		Query q=sessionFactory.getCurrentSession().createQuery(sql);
		List<HierarchyTitle> list=q.list();
		return list;
	}
	@Override
	public List<HierarchyTitle> listDeviceLocatedTitles(int clientId){
		String sql="";
		if(clientId==0)
			sql="from HierarchyTitle";
		else
			sql="from HierarchyTitle where client_id="+clientId+" and device_location=1";
			//sql="from HierarchyTitle where client_id="+clientId;
		System.out.println(" add dtr sql --> "+sql);
		Query q=sessionFactory.getCurrentSession().createQuery(sql);
		List<HierarchyTitle> list=q.list();
		return list;
	}

}
