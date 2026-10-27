package com.rtdas.common.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.dao.ModemDao;
import com.rtdas.common.models.Modem;

@Repository("modemDao")
public class ModemDaoImpl implements ModemDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addModem(Modem modem) {
		try{
			System.out.println(modem.getModem_id()+"======================="+modem.getDelivery_date());
		sessionFactory.getCurrentSession().saveOrUpdate(modem);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void deleteModem(Modem modem) {
		try{
			String sql="delete from Modem where modem_id="+modem.getModem_id();
			Query q=sessionFactory.getCurrentSession().createQuery(sql);
			q.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}

	}

	@Override
	public Modem getModem(int modemId) {
		return (Modem)sessionFactory.getCurrentSession().createQuery("from Modem where modem_id="+modemId).uniqueResult();
	}

	@Override
	public List<Modem> listModems() {
		System.out.println("listmodems1111");
		List<Modem> list=new ArrayList();	
		Query q=sessionFactory.getCurrentSession().createQuery("from Modem");
		//Query q=sessionFactory.getCurrentSession().createSQLQuery("select * from modem");
		System.out.println("listmodems2222");
		 list=q.list();
		System.out.println("size --> "+list.size());
		return list;
	}

}
