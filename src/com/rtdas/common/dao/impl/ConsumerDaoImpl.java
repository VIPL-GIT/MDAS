package com.rtdas.common.dao.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.models.HTConsumer;
import com.rtdas.common.dao.ConsumerDao;
import com.rtdas.common.models.Feeder;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.Meter;
import com.rtdas.common.models.Modem;



/*
  import org.hibernate.query.Query;
  import com.vipl.mdm.daos.ConsumerDao;
import com.vipl.mdm.models.Feeder;
import com.vipl.mdm.models.HTConsumer;
import com.vipl.mdm.models.Hierarchy;
import com.vipl.mdm.models.HierarchyTitle;
import com.vipl.mdm.models.Meter;
import com.vipl.mdm.models.Modem;
*/



@Repository("consumerDao")
public class ConsumerDaoImpl implements ConsumerDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public int addConsumer(HTConsumer ht) {
		Session session=sf.getCurrentSession();
		int ccid=ht.getConsumer_id();		
		
		try{
			if(ccid==0){

				ccid=(int)session.save(ht);
			}else{				
				session.saveOrUpdate(ht);
				ccid=ht.getConsumer_id();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ccid;
	}

	@Override
	public void deleteConsumer(int consumerid) {
		try{
			String sql="delete from HTConsumer where consumer_id="+consumerid;
			Query q=sf.getCurrentSession().createQuery(sql);
			q.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	@Override
	public HTConsumer getConsumer(int consumerId) {
		HTConsumer f=(HTConsumer)sf.getCurrentSession().createQuery("from HTConsumer where consumer_id="+consumerId).uniqueResult();
		return f;
	}

	@Override
	public List<HTConsumer> listConsumers(String clientId) {
		 List<HTConsumer> list=sf.getCurrentSession().createQuery("from HTConsumer ").list();		
   	     return list;
	}

	@Override
	public String processRowInsertion(List<String> header, List<String> values, int clientId) throws Exception {
		String result="";
		int feeder_str_length=19;  //fixed  as per downloaded excel 
		Session ses=sf.getCurrentSession();
		Map<String,Integer> titlesMap=new HashMap();
		List<HierarchyTitle> titles=ses.createQuery("from HierarchyTitle").list();
		for(HierarchyTitle ti:titles) {
			titlesMap.put(ti.getHierarchy_title().toUpperCase(),ti.getHierarchy_type_id());
		}
		ses.flush();
		ses.clear();
		//System.out.println(header);
		for(int i=0;i<values.size();i++) {
			if(i<2) {
				String hname=values.get(i);
				String parentHname=(i==0)?values.get(i):values.get(i-1);
				int typeId=titlesMap.containsKey(header.get(i))?titlesMap.get(header.get(i)):0;
				int parentTypeId=(i==0)?0:(titlesMap.containsKey(header.get(i-1))?titlesMap.get(header.get(i-1)):0);
				//System.out.println("------------"+typeId);
				//-----------insert into db only if it is not exits
				Object parentIdObj=(parentTypeId==0?"0":null);
				if(parentTypeId!=0) {
					 parentIdObj=ses.createQuery("select hierarchy_id from Hierarchy where hierarchy_name='"+parentHname+"' and hierarchy_type_id="+parentTypeId).uniqueResult();
					ses.flush();
					ses.clear();
				}
				Object oldH=ses.createQuery("from Hierarchy where hierarchy_name='"+hname+"' and hierarchy_type_id="+typeId+" and parent_id="+Integer.parseInt(parentIdObj.toString())).uniqueResult();
				if(oldH==null) {
					ses.flush();
					ses.clear();
					
					Hierarchy h=new Hierarchy();
					h.setClient_id(clientId);
					h.setActive_flg(1);
					h.setHierarchy_name(hname);
					if(i==0)
						h.setParent_id(0);
					else {
						
						if(parentIdObj!=null)
						 h.setParent_id(Integer.parseInt(parentIdObj.toString()));
						}
					
					h.setHierarchy_type_id(typeId);
					
					ses.saveOrUpdate(h);
					ses.flush();
					ses.clear();
				}//if not exist in db
			
				
				}//upto titles only	
			
		}//for

//--------------------------------consumer info-----------------------------------------
		HTConsumer ht=new HTConsumer();
		
		ht.setConsumer_name(values.get(2));
		ht.setConsumer_code(values.get(3));
		ht.setContract_load(values.get(4));
		ht.setSupply_voltage(values.get(5));
		ht.setContract_demand(values.get(6));
		ht.setMobile(values.get(7));
		ht.setAddress(values.get(8));
		ht.setCategory(values.get(9));
		
		Object feIdObj=ses.createSQLQuery("SELECT hierarchy_id FROM hierarchy WHERE hierarchy_name='"+values.get(1)+"' AND parent_id IN (SELECT hierarchy_id FROM hierarchy WHERE hierarchy_name='"+values.get(0)+"')").uniqueResult();
		ses.flush();
		ses.clear();
		if(feIdObj!=null)
		  ht.setFeeder_id(Integer.parseInt(feIdObj.toString()));
		
		ses.saveOrUpdate(ht);
		ses.flush();
		ses.clear();
		
//---------------------inserting the meter info -----------------------------------
		Meter m=new Meter();
		m.setMeter_sl_no(values.get(values.size()-9));
		m.setMeter_type(values.get(values.size()-8));
		m.setMetermake(values.get(values.size()-7));
		m.setInstallation_date(values.get(values.size()-6));
		//m.setModem_sl_no(values.get(values.size()-4));
		m.setMeterlocation("8");
		
		m.setMe_ct(values.get(values.size()-5));
		m.setMe_pt(values.get(values.size()-4));
		m.setAd_ct(values.get(values.size()-3));
		m.setAd_pt(values.get(values.size()-2));
		m.setMf(values.get(values.size()-1));
		//m.setLocation_id();
		Object dIdObj=ses.createQuery("select consumer_id from HTConsumer where consumer_code='"+values.get(3)+"'").list().get(0);
		ses.flush();
		ses.clear();
		if(dIdObj!=null)
		  m.setLocation_id(dIdObj.toString());
		
		
		ses.saveOrUpdate(m);
		ses.flush();
		ses.clear();

		result="<img src='images/right.png' height='25px'/>";
		return result;
	
	}
	
	public String processRowInsertion_bkp(List<String> header, List<String> values, int clientId) throws Exception {
		String result="";
		int feeder_str_length=19;  //fixed  as per downloaded excel 
		Session ses=sf.getCurrentSession();
		Map<String,Integer> titlesMap=new HashMap();
		List<HierarchyTitle> titles=ses.createQuery("from HierarchyTitle").list();
		for(HierarchyTitle ti:titles) {
			titlesMap.put(ti.getHierarchy_title().toUpperCase(),ti.getHierarchy_type_id());
		}
		ses.flush();
		ses.clear();
		//System.out.println(header);
		for(int i=0;i<values.size();i++) {
			if(i<values.size()-feeder_str_length) {
				String hname=values.get(i);
				String parentHname=(i==0)?values.get(i):values.get(i-1);
				int typeId=titlesMap.containsKey(header.get(i))?titlesMap.get(header.get(i)):0;
				int parentTypeId=(i==0)?0:(titlesMap.containsKey(header.get(i-1))?titlesMap.get(header.get(i-1)):0);
				//System.out.println("------------"+typeId);
				//-----------insert into db only if it is not exits
				
				Object oldH=ses.createQuery("from Hierarchy where hierarchy_name='"+hname+"' and hierarchy_type_id="+typeId).uniqueResult();
				if(oldH==null) {
					ses.flush();
					ses.clear();
					
					Hierarchy h=new Hierarchy();
					h.setClient_id(clientId);
					h.setActive_flg(1);
					h.setHierarchy_name(hname);
					if(i==0)
						h.setParent_id(0);
					else {
						if(parentTypeId!=0) {
						Object parentIdObj=ses.createQuery("select hierarchy_id from Hierarchy where hierarchy_name='"+parentHname+"' and hierarchy_type_id="+parentTypeId).uniqueResult();
	 					if(parentIdObj!=null)
						 h.setParent_id(Integer.parseInt(parentIdObj.toString()));
						}
					}
					h.setHierarchy_type_id(typeId);
					
					ses.saveOrUpdate(h);
					ses.flush();
					ses.clear();
				}//if not exist in db
			
				
				}//upto titles only	
			
		}//for
//------------------------getting Feeder info -------------------------------------------
		Object fIdObj=ses.createQuery("select feederid from Feeder where feedername='"+values.get(values.size()-feeder_str_length)+"'").uniqueResult();
		if(fIdObj==null) {
		Feeder f=new Feeder();
		f.setFeedername(values.get(values.size()-feeder_str_length));

	//--------------getting substation id from hierarchy table -------------------
		int ssTtypeId=titlesMap.containsKey(header.get(header.size()-feeder_str_length-2))?titlesMap.get(header.get(header.size()-feeder_str_length-2)):0;
		
		
		if(ssTtypeId!=0) {
		Object ssIdObj=ses.createQuery("select hierarchy_id from Hierarchy where hierarchy_name='"+values.get(values.size()-feeder_str_length-1)+"' and hierarchy_type_id="+ssTtypeId).uniqueResult();
		if(ssIdObj!=null)
		  f.setSubstationid(Integer.parseInt(ssIdObj.toString()));
		}
		
		ses.saveOrUpdate(f);
		ses.flush();
		ses.clear();
		}
//--------------------------------consumer info-----------------------------------------
		HTConsumer ht=new HTConsumer();
		
		ht.setConsumer_name(values.get(values.size()-feeder_str_length+1));
		ht.setConsumer_code(values.get(values.size()-feeder_str_length+2));
		ht.setContract_load(values.get(values.size()-feeder_str_length+3));
		
		ht.setAggrement_start_date(values.get(values.size()-feeder_str_length+8));
		ht.setAggrement_end_date(values.get(values.size()-feeder_str_length+9));
		ht.setMobile(values.get(values.size()-feeder_str_length+10));
		ht.setAddress(values.get(values.size()-feeder_str_length+11));
		ht.setEmail_id(values.get(values.size()-feeder_str_length+12));
		
		Object feIdObj=ses.createQuery("select feederid from Feeder where feedername='"+values.get(values.size()-feeder_str_length)+"'").uniqueResult();
		if(feIdObj!=null)
		  ht.setFeeder_id(Integer.parseInt(feIdObj.toString()));
		
		ses.saveOrUpdate(ht);
		ses.flush();
		ses.clear();
		
//---------------------inserting the meter info -----------------------------------
		Meter m=new Meter();
		m.setMeter_sl_no(values.get(values.size()-6));
		m.setInstallation_date(values.get(values.size()-5));
		m.setModem_sl_no(values.get(values.size()-4));
		m.setMeterlocation("8");
		
		m.setMe_ct(values.get(values.size()-feeder_str_length+4));
		m.setMe_pt(values.get(values.size()-feeder_str_length+5));
		m.setAd_ct(values.get(values.size()-feeder_str_length+6));
		m.setAd_pt(values.get(values.size()-feeder_str_length+7));
		//m.setLocation_id();
		Object dIdObj=ses.createQuery("select consumer_id from HTConsumer where consumer_name='"+values.get(values.size()-feeder_str_length+1)+"'").uniqueResult();
		if(dIdObj!=null)
		  m.setLocation_id(dIdObj.toString());
		
		
		ses.saveOrUpdate(m);
		ses.flush();
		ses.clear();
//---------------------inserting modem info ----------------------------------
		Modem mo=new Modem();
		mo.setModem_serial_no(values.get(values.size()-4));
		mo.setVendor(values.get(values.size()-3));
		mo.setImei_no(values.get(values.size()-2));
		mo.setMobile_no(values.get(values.size()-1));
		
		ses.saveOrUpdate(mo);
		ses.flush();
		ses.clear();
		result="<img src='images/right.png' height='25px'/>";
		return result;
	
	}

	@Override
	public List<HTConsumer> listConsumersFid(String fid) {
		List<HTConsumer> list=new ArrayList();
		try {
		     list=sf.getCurrentSession().createQuery("from HTConsumer where feeder_id="+fid).list();
		}catch(Exception e) {}
  	     return list;
	}

	@Override
	public List<HTConsumer> listUnmappedConsumersFid(String fid) {
		List<HTConsumer> list=new ArrayList();
		try {
		     list=sf.getCurrentSession().createQuery("from HTConsumer where consumer_id NOT IN (select location_id from Meter where location_id is not null)").list();
		}catch(Exception e) {}
  	     return list;
	}	

	

}
