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

import com.rtdas.common.dao.InputFeederDao;
import com.rtdas.common.models.Feeder;
import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.InputFeeder;
import com.rtdas.common.models.Meter;
import com.rtdas.common.models.Modem;

@Repository("ipfeederDao")
public class InputFeederDaoImpl implements InputFeederDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public int addFeeder(InputFeeder feeder) {
		Session session=sf.getCurrentSession();
		int feederid=feeder.getFeederid();		
		
		try{
			if(feederid==0){

				feederid=(int)session.save(feeder);
			}else{				
				session.saveOrUpdate(feeder);
				feederid=feeder.getFeederid();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return feederid;
	}
	

	@Override
	public void deleteFeeder(int feeder_id) {
		try{
			String sql="delete from InputFeeder where feederid="+feeder_id;
			System.out.println("delete feeder sql --> "+sql);
			Query q=sf.getCurrentSession().createQuery(sql);
			q.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}

	}

	@Override
	public InputFeeder getFeeder(int feederId) {
		InputFeeder f=(InputFeeder)sf.getCurrentSession().createQuery("from InputFeeder where feederid="+feederId).uniqueResult();
		return f;
	}

	@Override
	public List<InputFeeder> listFeeders(String clientId) {		 
		List<InputFeeder> list=sf.getCurrentSession().createQuery("from InputFeeder").list();
		
		return list;
	}

	@Override
	public String processRowInsertion(List<String> header,List<String> values, int clientId)throws Exception {
		System.out.println(clientId+" --> "+values+"proces row insertion "+header);
		String result="";
		try{
		int feeder_str_length=13;  //fixed  as per downloaded excel 
		Session ses=sf.getCurrentSession();
		Map<String,Integer> titlesMap=new HashMap();
		List<HierarchyTitle> titles=ses.createQuery("from HierarchyTitle").list();
		for(HierarchyTitle ti:titles) {
			System.out.println(ti.getHierarchy_title().toUpperCase()+"  <-->  "+ti.getHierarchy_type_id());
			titlesMap.put(ti.getHierarchy_title().toUpperCase(),ti.getHierarchy_type_id());
		}
		ses.flush();
		ses.clear();
		System.out.println(header);
		for(int i=0;i<values.size();i++) {
			if(i<values.size()-feeder_str_length) {
				String hname=values.get(i);
				String parentHname=(i==0)?values.get(i):values.get(i-1);
				int typeId=titlesMap.containsKey(header.get(i))?titlesMap.get(header.get(i)):0;
				int parentTypeId=(i==0)?0:(titlesMap.containsKey(header.get(i-1))?titlesMap.get(header.get(i-1)):0);
				System.out.println("------------"+typeId);
				//-----------insert into db only if it is not exits
				String sql="from Hierarchy where hierarchy_name='"+hname+"' and hierarchy_type_id="+typeId;
				System.out.println("sql --> "+sql);
				Object oldH=ses.createQuery(sql).uniqueResult();
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
							String sql1="select hierarchy_id from Hierarchy where hierarchy_name='"+parentHname+"' and hierarchy_type_id="+parentTypeId;
							System.out.println("sql1 --> "+sql1);
						Object parentIdObj=ses.createQuery(sql1).uniqueResult();
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
//------------------------getting feeder info -------------------------------------------
		InputFeeder f=new InputFeeder();
		f.setFeedername(values.get(values.size()-feeder_str_length));
		f.setStructure_code(values.get(values.size()-feeder_str_length+1));
		f.setVoltage_level(values.get(values.size()-feeder_str_length+2));
		
	//--------------getting substation id from hierarchy table -------------------
		int ssTtypeId=titlesMap.containsKey(header.get(header.size()-feeder_str_length-2))?titlesMap.get(header.get(header.size()-feeder_str_length-2)):0;
		
		
		if(ssTtypeId!=0) {
			String sql11="select hierarchy_id from Hierarchy where hierarchy_name='"+values.get(values.size()-feeder_str_length-1)+"' and hierarchy_type_id="+ssTtypeId;
			System.out.println("sql1-1 ==> "+sql11);
		Object ssIdObj=ses.createQuery(sql11).uniqueResult();
		if(ssIdObj!=null)
		  f.setSubstationid(Integer.parseInt(ssIdObj.toString()));
		}
		
		ses.saveOrUpdate(f);
		ses.flush();
		ses.clear();
//---------------------inserting the meter info -----------------------------------
		Meter m=new Meter();
		m.setMeter_sl_no(values.get(values.size()-6));
		m.setInstallation_date(values.get(values.size()-5));
		m.setModem_sl_no(values.get(values.size()-4));
		m.setMeterlocation("11");
		m.setMe_ct(values.get(values.size()-feeder_str_length+3));
		m.setMe_pt(values.get(values.size()-feeder_str_length+4));
		m.setAd_ct(values.get(values.size()-feeder_str_length+5));
		m.setAd_pt(values.get(values.size()-feeder_str_length+6));
		//m.setLocation_id();
		String sql2="select feederid from InputFeeder where feedername='"+values.get(values.size()-feeder_str_length)+"'";
		System.out.println("sql2 ==> "+sql2);
		Object fIdObj=ses.createQuery(sql2).uniqueResult();
		if(fIdObj!=null)
		  m.setLocation_id(fIdObj.toString());
		
		
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
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public Map<Integer, String> getFeeders(String clientId) {
		Map<Integer, String> feeders=new HashMap();
		try {
		List<InputFeeder> list=sf.getCurrentSession().createQuery("from InputFeeder").list();
		for(InputFeeder f:list) {
			feeders.put(f.getFeederid(),f.getFeedername());
		}
		}catch(Exception e) {
			
		}
		return feeders;
	}


	@Override
	public List<InputFeeder> listFeedersByssid(int ssid) {
		List<InputFeeder> list=new ArrayList();
		try {
			 list=sf.getCurrentSession().createQuery("from InputFeeder where substationid="+ssid).list();			
			}catch(Exception e) {
				
			}
		return list;
	}

}
