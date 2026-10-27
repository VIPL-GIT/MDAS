package com.rtdas.common.dao.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.models.DTR;
import com.rtdas.common.models.Feeder;
import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.Meter;
import com.rtdas.common.models.Modem;



@Repository("dtrDao")
public class DTRDaoImpl implements com.rtdas.common.dao.DTRDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public int addDTR(DTR dtr) {
		System.out.println("add dtr daoimpl");
		Session session=sf.getCurrentSession();
		int dtrid=dtr.getDtrid();		
		System.out.println("dtrid --> "+dtrid);
		try{
			if(dtrid==0){
				dtrid=(int)session.save(dtr);
			}else{				
				session.saveOrUpdate(dtr);
				dtrid=dtr.getDtrid();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return dtrid;
	}

	@Override
	public void deleteDTR(int dtrid) {
		try{
			String sql="delete from DTR where dtrid="+dtrid;
			org.hibernate.Query q=sf.getCurrentSession().createQuery(sql);
			q.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}

	}

	@Override
	public DTR getDTR(int dtrId) {
		DTR f=(DTR)sf.getCurrentSession().createQuery("from DTR where dtrid="+dtrId).uniqueResult();
		return f;
	}

	@Override
	public List<DTR> listDTRs(String clientId) {
          List<DTR> list=sf.getCurrentSession().createQuery("from DTR").list();		
    	  return list;
	}

	@Override
	public String processRowInsertion(List<String> header, List<String> values, int clientId) throws Exception {
		String result="";
		int feeder_str_length=14;  //fixed  as per downloaded excel 
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
//--------------------------------DTR info-----------------------------------------
		DTR dt=new DTR();
		
		dt.setDtrname(values.get(values.size()-feeder_str_length+1));
		dt.setStructure_code(values.get(values.size()-feeder_str_length+2));
		dt.setCapacity(values.get(values.size()-feeder_str_length+3));
		
		
		Object feIdObj=ses.createQuery("select feederid from Feeder where feedername='"+values.get(values.size()-feeder_str_length)+"'").uniqueResult();
		if(feIdObj!=null)
		  dt.setFeederid(Integer.parseInt(feIdObj.toString()));
		
		ses.saveOrUpdate(dt);
		ses.flush();
		ses.clear();
		
//---------------------inserting the meter info -----------------------------------
		Meter m=new Meter();
		m.setMeter_sl_no(values.get(values.size()-6));
		m.setInstallation_date(values.get(values.size()-5));
		m.setModem_sl_no(values.get(values.size()-4));
		m.setMeterlocation("6");
		
		m.setMe_ct(values.get(values.size()-feeder_str_length+4));
		m.setMe_pt(values.get(values.size()-feeder_str_length+5));
		m.setAd_ct(values.get(values.size()-feeder_str_length+6));
		m.setAd_pt(values.get(values.size()-feeder_str_length+7));
		//m.setLocation_id();
		Object dIdObj=ses.createQuery("select dtrid from DTR where dtrname='"+values.get(values.size()-feeder_str_length+1)+"'").uniqueResult();
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
	public List<DTR> listDTRsByfId(String fid) {
		List<DTR> list=new ArrayList();
		try {
		    list=sf.getCurrentSession().createQuery("from DTR where feederid="+fid).list();
		}catch(Exception e) {
			
		}
  	    return list;
	}	

}
