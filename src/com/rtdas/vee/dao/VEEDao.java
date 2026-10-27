package com.rtdas.vee.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.vee.models.VEERules;
import com.rtdas.vee.models.Vee_summary_daily;

import utils.DateUtilities;

@Repository("veeDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class VEEDao {

	@Autowired
	private SessionFactory sf;
	
	
	public List<VEERules> getRules(){
		List<VEERules> list=new ArrayList();	
		try{
			list=sf.getCurrentSession().createQuery("from VEERules order by rule_name").list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public VEERules getRule(int id){
		VEERules rule=null;
		try{
			rule=(VEERules)sf.getCurrentSession().createQuery("from VEERules where id="+id).uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return rule;
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void updateRule(VEERules rule){
		try{
			VEERules rule1=(VEERules)sf.getCurrentSession().createQuery("from VEERules where id="+rule.getId()).uniqueResult();
			rule1.setParam_values(rule.getParam_values());
			sf.getCurrentSession().saveOrUpdate(rule1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Vee_summary_daily> getSummaryReport(List<String> meters,String sdate){
		List<Vee_summary_daily> list=new ArrayList();
		try{
			DateUtilities du=new DateUtilities();
			
			
			//System.out.println("-------------------------------"+dates);
//------------------------------------------------------------------------------			
			Query q=sf.getCurrentSession().createQuery("from Vee_summary_daily where comm_date='"+sdate+"' and meter_serial_no in (:meters)");			
			q.setParameterList("meters", meters);
			
			list=q.list();
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	public List<Vee_summary_daily> getSummaryReport_dates(List<String> meters,String sdate,String edate){
		List<Vee_summary_daily> list=new ArrayList();
		try{
			DateUtilities du=new DateUtilities();
			List<String> dates=new ArrayList();
			System.out.println(sdate+"-------------------"+edate);
			dates.add(sdate);
			while(!edate.equals(sdate)){
				sdate=du.add_Oneday(sdate+" 00:00:00");
				dates.add(sdate);
			}
//------------------------------------------------------------------------------			
			Query q=sf.getCurrentSession().createQuery("from Vee_summary_daily where comm_date in (:dates) and meter_serial_no in (:meters)");			
			q.setParameterList("dates", dates);
			q.setParameterList("meters", meters);
			
			list=q.list();
		}catch(Exception e){
			
		}
		
		return list;
	}
	
	
}
