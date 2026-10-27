package com.rtdas.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.beans.IEDMasterBean;
import com.rtdas.common.models.DataLogger;
import com.rtdas.common.models.IEDMaster;
import com.rtdas.common.service.DataLoggerService;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.IEDService;

@Controller

@RequestMapping(value="/ied")
public class IEDController {
	@Autowired
	private DataLoggerService loggerService;
	@Autowired
	private IEDService iedservice;
	@Autowired
	private HierarchyService hservice;
	
	public String actionId;

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	@RequestMapping(value="/viewied" ,method=RequestMethod.GET)
	public ModelAndView viewModems(@RequestParam("actionId")String actionId,HttpSession session) throws Exception{
		
		System.out.println("---------------->in action");
		
		List<IEDMasterBean> iedlist=new ArrayList();
		Map<String, Object> model = new HashMap();
		List<Integer> childIds=(List<Integer>)session.getAttribute("childIds");
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		List<DataLogger> loggerBeanList=new ArrayList();
				//loggerService.listLoggers(childIds);
		if(!loggerBeanList.isEmpty()){
			List<Integer> loggerids=new ArrayList();
			Map<Integer,String> loggerMap=new HashMap();
			Map<Integer,Integer> locationMap=new HashMap();
			for(DataLogger dl:loggerBeanList){
				loggerids.add(dl.getLogger_id());
				loggerMap.put(dl.getLogger_id(), dl.getLogger_sl_no());
				locationMap.put(dl.getLogger_id(), dl.getLocation_id());
			}
		Map<Integer,String> hMap=hservice.listHierarchyByClient(clientId);
		Map<Integer,String> mappedDevices=iedservice.devicesMappedToIED();
		iedlist=prepareListBean(iedservice.listIED(loggerids));
		if(!iedlist.isEmpty()){
			for(IEDMasterBean imb:iedlist){
				imb.setLogger_sl_no(loggerMap.get(imb.getLogger_id()));
				imb.setLocation_name(hMap.get(locationMap.get(imb.getLogger_id())));
				imb.setDevice_names(mappedDevices.get(imb.getIed_id()));
			}
		  }// if IED list is not empty
		}// if logger bean is not empty
		model.put("iedlist", iedlist);
		
		setActionId(actionId);
		model.put("actionId", actionId);
		return new ModelAndView("IED/viewIED",model);
		
	}
	
	public List<IEDMasterBean> prepareListBean(List<IEDMaster> iedm){
		List<IEDMasterBean> iedbeanlist=iedbeanlist=new ArrayList();
		if(iedm!=null && !iedm.isEmpty()){
			
           for(IEDMaster im:iedm){
        	   IEDMasterBean imb=new IEDMasterBean();
        	   imb.setFw_version(im.getFw_version());
        	   imb.setHw_version(im.getHw_version());
        	   imb.setIed_id(im.getIed_id());
        	   imb.setIed_sl_no(im.getIed_sl_no());
        	   imb.setLogger_id(im.getLogger_id());
        	   
        	   iedbeanlist.add(imb);
           }
			
			
		}
		
		
		return iedbeanlist;
		
	}

}
