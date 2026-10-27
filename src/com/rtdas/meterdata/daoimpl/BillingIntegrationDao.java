package com.rtdas.meterdata.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtdas.meterdata.beans.WSIntegrationSummaryBean;
import com.rtdas.meterdata.beans.WSMasterDataResponseBean;
import com.rtdas.meterdata.beans.WSSendBillingReadingsBean;
import com.rtdas.meterdata.models.Billing_ws_integration_log;
import com.rtdas.meterdata.models.DeviceIntegrationSummary;

@Repository("BillingDao")
@Transactional
public class BillingIntegrationDao {

	@Autowired
	private SessionFactory sf;
	
	
	public List<DeviceIntegrationSummary> getBillingdata(){
		List<DeviceIntegrationSummary> list=new ArrayList();
		try{
			list=sf.getCurrentSession().createQuery("From DeviceIntegrationSummary where istatus is not null").list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;	
	}
	public List<WSIntegrationSummaryBean> getBillingIStatus(){
		
		List<Billing_ws_integration_log> list=new ArrayList();
		List<Billing_ws_integration_log> total_list=new ArrayList();
		List<String> successTokens=new ArrayList();
		
		List<WSIntegrationSummaryBean> beanList=new ArrayList();
		HashMap<String,String> flagsMap=new HashMap();
		flagsMap.put("0", "Request received");
		flagsMap.put("1", "Master received");
		flagsMap.put("2", "Success");
		flagsMap.put("3", "Error response");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try{	
			String billcycle=sdf.format(new java.util.Date());
			billcycle=billcycle.split("-")[0]+billcycle.split("-")[1];
			
			//list=sf.getCurrentSession().createQuery("From Billing_ws_integration_log where bill_cycle='"+billcycle+"'").list();
			Criteria cr = sf.getCurrentSession().createCriteria(Billing_ws_integration_log.class)
				    .setProjection(Projections.projectionList()
				      .add(Projections.property("binder_no"), "binder_no")
				      .add(Projections.property("token_no"), "token_no")
				    .add(Projections.property("master_data"), "master_data")
					.add(Projections.property("post_readings_data"), "post_readings_data")					
					.add(Projections.property("flag"), "flag"))
				    .add(Restrictions.eq("bill_cycle", billcycle))
				    .add(Restrictions.like("ack_response_status", "%success%"))
				    .setResultTransformer(Transformers.aliasToBean(Billing_ws_integration_log.class));
			list=cr.list();
//--------------------------------total list ---------------------------------------------------
			Criteria crt = sf.getCurrentSession().createCriteria(Billing_ws_integration_log.class)
				    .setProjection(Projections.projectionList()
				      .add(Projections.property("binder_no"), "binder_no")
				      .add(Projections.property("token_no"), "token_no")
				    .add(Projections.property("master_data"), "master_data")
					.add(Projections.property("post_readings_data"), "post_readings_data")					
					.add(Projections.property("flag"), "flag"))
				    .add(Restrictions.eq("bill_cycle", billcycle))				    
				    .setResultTransformer(Transformers.aliasToBean(Billing_ws_integration_log.class));
			total_list=crt.list();
			
			
//------------------------------------------------------------------------------------------------			
			if(!list.isEmpty()){
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(
					    JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), 
					    true
					);
				
				for(Billing_ws_integration_log bwil:list){
					WSIntegrationSummaryBean wsb=new WSIntegrationSummaryBean();
					wsb.setBccd(bwil.getBinder_no());
					wsb.setToken_no(bwil.getToken_no());
					successTokens.add(bwil.getToken_no());
					
					if(bwil.getMaster_data()!=null){
					 String masterResData=new String(bwil.getMaster_data().getBytes(1, (int)bwil.getMaster_data().length()));					
					 WSMasterDataResponseBean mdrb= mapper.readValue(masterResData, WSMasterDataResponseBean.class);
					 
					 
		             wsb.setOffice_code(mdrb.getResponse().getSdo());
		             wsb.setKno(mdrb.getResponse().getMrmaster().get(0).getKno());
		             wsb.setName(mdrb.getResponse().getMrmaster().get(0).getName());
		             wsb.setAddress(mdrb.getResponse().getMrmaster().get(0).getAddress());
		             wsb.setMeter(mdrb.getResponse().getMrmaster().get(0).getMeter_no());
		           if(bwil.getPost_readings_data()!=null){  
		             String postReadingsData=new String(bwil.getPost_readings_data().getBytes(1, (int)bwil.getPost_readings_data().length())); 
		             WSSendBillingReadingsBean rb=mapper.readValue(postReadingsData, WSSendBillingReadingsBean.class);
		             
		             wsb.setKwh_i(rb.getMeterreadings().get(0).getKwh());
		             wsb.setKvah_i(rb.getMeterreadings().get(0).getKvah());
		             wsb.setMdkva_i(rb.getMeterreadings().get(0).getMd());
		             wsb.setPf(rb.getMeterreadings().get(0).getPf());
		           }
					}
		             wsb.setRemarks(flagsMap.get(bwil.getFlag()));
		             
		             
					beanList.add(wsb);
				}//for success list
//-------------------------------------------------------
				for(Billing_ws_integration_log bwil:total_list){
					WSIntegrationSummaryBean wsb=new WSIntegrationSummaryBean();
					wsb.setBccd(bwil.getBinder_no());
					wsb.setToken_no(bwil.getToken_no());
					if(successTokens.contains(bwil.getToken_no())){
					  continue;	
					}
					
					if(bwil.getMaster_data()!=null){
					 String masterResData=new String(bwil.getMaster_data().getBytes(1, (int)bwil.getMaster_data().length()));					
					 WSMasterDataResponseBean mdrb= mapper.readValue(masterResData, WSMasterDataResponseBean.class);
					 
					 
		             wsb.setOffice_code(mdrb.getResponse().getSdo());
		             wsb.setKno(mdrb.getResponse().getMrmaster().get(0).getKno());
		             wsb.setName(mdrb.getResponse().getMrmaster().get(0).getName());
		             wsb.setAddress(mdrb.getResponse().getMrmaster().get(0).getAddress());
		             wsb.setMeter(mdrb.getResponse().getMrmaster().get(0).getMeter_no());
		           if(bwil.getPost_readings_data()!=null){  
		             String postReadingsData=new String(bwil.getPost_readings_data().getBytes(1, (int)bwil.getPost_readings_data().length())); 
		             WSSendBillingReadingsBean rb=mapper.readValue(postReadingsData, WSSendBillingReadingsBean.class);
		             
		             wsb.setKwh_i(rb.getMeterreadings().get(0).getKwh());
		             wsb.setKvah_i(rb.getMeterreadings().get(0).getKvah());
		             wsb.setMdkva_i(rb.getMeterreadings().get(0).getMd());
		             wsb.setPf(rb.getMeterreadings().get(0).getPf());
		           }
					}
		             wsb.setRemarks(flagsMap.get(bwil.getFlag()));
		             
		             
					beanList.add(wsb);
				}//for total list
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return beanList;
	}
}
