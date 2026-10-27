package com.rtdas.common.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.dao.DataLoggerDao;
import com.rtdas.common.models.DataLogger;

@Repository("loggerdao")
public class DataLoggerDaoImpl implements DataLoggerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addLogger(DataLogger dl) {
		try{			
		   sessionFactory.getCurrentSession().saveOrUpdate(dl);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void deleteLogger(int loggerId) {
		sessionFactory.getCurrentSession().createQuery("delete from DataLogger where logger_id="+loggerId).executeUpdate();

	}

	@Override
	public DataLogger getLogger(int loggerId) {
		return (DataLogger)sessionFactory.getCurrentSession().createQuery("from DataLogger where logger_id="+loggerId).uniqueResult();
	}

	@Override
	public List<DataLogger> listLoggers(List<Integer> ids) {
		String sql="from DataLogger ";
				if(!ids.isEmpty())
					sql=sql+" where location_id in(:ids)";
		Query q=sessionFactory.getCurrentSession().createQuery(sql);
		if(!ids.isEmpty())
		  q.setParameterList("ids", ids);
		
		List<DataLogger> list=q.list();
		return list;
	}

	@Override
	public DataLogger getLoggerBySlNo(String slno) {
		return (DataLogger)sessionFactory.getCurrentSession().createQuery("from DataLogger where logger_sl_no='"+slno+"'").uniqueResult();
	}

}
