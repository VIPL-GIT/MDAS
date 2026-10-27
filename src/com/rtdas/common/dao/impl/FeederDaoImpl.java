package com.rtdas.common.dao.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.bag.SynchronizedBag;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.dao.FeederDao;
import com.rtdas.common.models.Feeder;
import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.Meter;
import com.rtdas.common.models.Modem;

@Repository("feederDao")
public class FeederDaoImpl implements FeederDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public int addFeeder(Feeder feeder) {
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
			String sql="delete from Feeder where feederid="+feeder_id;
			System.out.println("delete feeder sql --> "+sql);
			Query q=sf.getCurrentSession().createQuery(sql);
			q.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}

	}

	@Override
	public Feeder getFeeder(int feederId) {
		Feeder f=(Feeder)sf.getCurrentSession().createQuery("from Feeder where feederid="+feederId).uniqueResult();
		return f;
	}

	@Override
	public List<Feeder> listFeeders(String clientId) {		 
		List<Feeder> list=sf.getCurrentSession().createQuery("from Feeder").list();
		
		return list;
	}

	@Override
	public String processRowInsertion(List<String> header,List<String> values, int clientId)throws Exception {
		//System.out.println(clientId+" --> "+values+"proces row insertion "+header);
		String result="";
		synchronized (result) {
			
		Session ses=sf.getCurrentSession();
		try{
		int feeder_str_length=18;  //fixed  as per downloaded excel 
		
		Map<String,Integer> titlesMap=new HashMap();
		List<HierarchyTitle> titles=ses.createQuery("from HierarchyTitle").list();
		for(HierarchyTitle ti:titles) {
			//System.out.println(ti.getHierarchy_title().toUpperCase()+"  <-->  "+ti.getHierarchy_type_id());
			titlesMap.put(ti.getHierarchy_title().toUpperCase(),ti.getHierarchy_type_id());
		}
		ses.flush();
		ses.clear();
		System.out.println("-----------values size -------------"+values.size());
		for(int i=0;i<values.size();i++) {
			if(i<titles.size()) {
				String hname=values.get(i);
				String parentHname=(i==0)?values.get(i):values.get(i-1);
				int typeId=titlesMap.containsKey(header.get(i))?titlesMap.get(header.get(i)):0;
				int parentTypeId=(i==0)?0:(titlesMap.containsKey(header.get(i-1))?titlesMap.get(header.get(i-1)):0);
	System.out.println(parentTypeId+"------------"+typeId);
				//-----------insert into db only if it is not exits
				Object parentIdObj=(parentTypeId==0?"0":null);
				if(parentTypeId!=0) {
//-----------------------------getting parents parent obj ( 2 levels)
					if(i>1){
						int pparentTypeId=titlesMap.containsKey(header.get(i-2))?titlesMap.get(header.get(i-2)):0;
					 Object pparentIdObj=ses.createQuery("select hierarchy_id from Hierarchy where hierarchy_name='"+values.get(i-2)+"' and hierarchy_type_id="+pparentTypeId).list().get(0);
					ses.flush();
					ses.clear();
					 parentIdObj=ses.createQuery("select hierarchy_id from Hierarchy where hierarchy_name='"+parentHname+"' and parent_id="+Integer.parseInt(pparentIdObj.toString())+" and hierarchy_type_id="+parentTypeId).uniqueResult();
						ses.flush();
						ses.clear();
						
						System.out.println("select hierarchy_id from Hierarchy where hierarchy_name='"+values.get(i-2)+"' and hierarchy_type_id="+pparentTypeId);
						System.out.println("select hierarchy_id from Hierarchy where hierarchy_name='"+parentHname+"' and parent_id="+Integer.parseInt(pparentIdObj.toString())+" and hierarchy_type_id="+parentTypeId);
					
					}
					else{
						 parentIdObj=ses.createQuery("select hierarchy_id from Hierarchy where hierarchy_name='"+parentHname+"' and hierarchy_type_id="+parentTypeId).uniqueResult();
							ses.flush();
							ses.clear();
					}
				}
				
				System.out.println(hname+"\t"+typeId+"\t"+Integer.parseInt(parentIdObj.toString()));
				Object oldH=ses.createQuery("from Hierarchy where hierarchy_name='"+hname+"' and hierarchy_type_id="+typeId+" and parent_id="+Integer.parseInt(parentIdObj.toString())).uniqueResult();
				ses.flush();
				ses.clear();
				if(oldH==null) {
					
					
					Hierarchy h=new Hierarchy();
					h.setClient_id(clientId);
					h.setActive_flg(1);
					h.setHierarchy_name(hname);
//-------------------------------------------------------------------
                    if(i==titles.size()-1)
                    	h.setCapacity(values.get(i+1));
//-------------------------------------------------------------------					
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
//------------------------getting feeder info -------------------------------------------
		Feeder f=new Feeder();
		f.setFeedername(values.get(titles.size()+1));
		f.setVoltage_level(values.get(titles.size()+2));
		f.setFeeder_type(values.get(titles.size()+3));
		f.setStructure_code(values.get(titles.size()+4));
		f.setConductor(values.get(titles.size()+5));
		
		
	//--------------getting substation id from hierarchy table -------------------
		int ssTtypeId=titles.get(titles.size()-1).getHierarchy_type_id();
		
		Object ssIdObj=null;
		if(ssTtypeId!=0) {
//--------------------need to get the parent id for ss, and then have to get the ssid for feeder,
//			System.out.println(values.get(titles.size()-2)+"\t"+titles.get(titles.size()-2).getHierarchy_type_id());
//			Object parentIdObj=ses.createQuery("select hierarchy_id from Hierarchy where hierarchy_name='"+values.get(titles.size()-2)+"' and hierarchy_type_id="+titles.get(titles.size()-2).getHierarchy_type_id()).uniqueResult();
//			ses.flush();
//			ses.clear();
//------------------- same ss is under diff subdivisions---------------			
			//String sql11="select hierarchy_id from Hierarchy where hierarchy_name='"+values.get(titles.size()-1)+"' and hierarchy_type_id="+ssTtypeId+" and parent_id="+parentIdObj;
			String sql11="select hierarchy_id from Hierarchy where hierarchy_name='"+values.get(titles.size()-1)+"' and hierarchy_type_id="+ssTtypeId;
			System.out.println("sql1-1 ==> "+sql11);
		ssIdObj=ses.createQuery(sql11).uniqueResult();
		ses.flush();
		ses.clear();
		if(ssIdObj!=null)
		  f.setSubstationid(Integer.parseInt(ssIdObj.toString()));
		}
		
		ses.saveOrUpdate(f);
		ses.flush();
		ses.clear();
//---------------------inserting the meter info -----------------------------------
		Meter m=new Meter();
		m.setMeter_sl_no(values.get(values.size()-8));
		m.setMeter_type(values.get(values.size()-7));
		m.setMetermake(values.get(values.size()-6));		
		m.setMeterlocation("7");
		
		m.setMe_ct(values.get(values.size()-5));
		m.setMe_pt(values.get(values.size()-4));
		m.setAd_ct(values.get(values.size()-3));
		m.setAd_pt(values.get(values.size()-2));
		m.setMf(values.get(values.size()-1));
		//m.setLocation_id();
		String sql2="select feederid from Feeder where feedername='"+values.get(titles.size()+1)+"' and substationid="+Integer.parseInt(ssIdObj.toString());
		System.out.println("sql2 ==> "+sql2);
		Object fIdObj=ses.createQuery(sql2).uniqueResult();
		ses.flush();
		ses.clear();
		if(fIdObj!=null)
		  m.setLocation_id(fIdObj.toString());
		
		
		ses.saveOrUpdate(m);
		ses.flush();
		ses.clear();
//---------------------inserting modem info ----------------------------------
		/*Modem mo=new Modem();
		mo.setModem_serial_no(values.get(values.size()-4));
		mo.setVendor(values.get(values.size()-3));
		mo.setImei_no(values.get(values.size()-2));
		mo.setMobile_no(values.get(values.size()-1));
		
		ses.saveOrUpdate(mo);
		ses.flush();
		ses.clear();*/
		result="<img src='images/right.png' height='25px'/>";
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		return result;
	}


	@Override
	public Map<Integer, String> getFeeders(String clientId) {
		Map<Integer, String> feeders=new HashMap();
		try {
		List<Feeder> list=sf.getCurrentSession().createQuery("from Feeder").list();
		for(Feeder f:list) {
			feeders.put(f.getFeederid(),f.getFeedername());
		}
		}catch(Exception e) {
			
		}
		return feeders;
	}


	@Override
	public List<Feeder> listFeedersByssid(int ssid) {
		List<Feeder> list=new ArrayList();
		try {
			 list=sf.getCurrentSession().createQuery("from Feeder where substationid="+ssid).list();			
			}catch(Exception e) {
				
			}
		return list;
	}

}
