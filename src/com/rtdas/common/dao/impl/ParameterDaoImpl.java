package com.rtdas.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.dao.ParameterDao;
import com.rtdas.common.models.ParameterMaster;

@Repository("paramDao")
public class ParameterDaoImpl implements ParameterDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Object[]> listParameter(String clientId) {
		String sql="";
		if(clientId.equals("0"))
			sql="SELECT p.parameter_id,p.parameter_name,c.client_name FROM analog_parameter p,client c WHERE p.client_id=c.client_id ORDER BY client_name";
		else
			sql="SELECT p.parameter_id,p.parameter_name,c.client_name FROM analog_parameter p,client c WHERE "
					+ " p.client_id=c.client_id and p.client_id="+clientId+" ORDER BY client_name";
		Query q=sessionFactory.getCurrentSession().createSQLQuery(sql);		
		return (List<Object[]>) q.list();
		
	}

	@Override
	public void addParameterMaster(ParameterMaster parameterModel) {
		System.out.println("id--------------->"+parameterModel.getParameterId());
		sessionFactory.getCurrentSession().saveOrUpdate(parameterModel);

	}

	@Override
	public void deleteParameter(int parameterId) {
		sessionFactory.getCurrentSession().createQuery("delete from ParameterMaster where parameterId="+parameterId).executeUpdate();
	}

	@Override
	public ParameterMaster getParameterMaster(int parameterId) {
		 return (ParameterMaster)sessionFactory.getCurrentSession().createQuery("from ParameterMaster where parameterId="+parameterId).uniqueResult();
	}

	@Override
	public List<ParameterMaster> getParams(List<Integer> ids) {
		List<ParameterMaster> list=new ArrayList();
		try{
			Query q=sessionFactory.getCurrentSession().createQuery("from ParameterMaster where parameterId in (:params)");
			q.setParameterList("params", ids);
			list=q.list();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	

}
