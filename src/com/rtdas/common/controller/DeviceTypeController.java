package com.rtdas.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.beans.DeviceTypeBean;
import com.rtdas.common.models.DeviceType;
import com.rtdas.common.service.ClientService;
import com.rtdas.common.service.DeviceService;

@Controller

@RequestMapping(value="/devicetype")
public class DeviceTypeController {

	@Autowired
	private ClientService clientService;
	@Autowired
	private DeviceService deviceService;
	public String actionId;

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	@RequestMapping(value="/addType" ,method=RequestMethod.GET)
	public ModelAndView addDeviceType(HttpSession session){
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		Map<String, Object> model = new HashMap<String, Object>();		
		model.put("client", clientService.listClients());
		model.put("actionId", getActionId());
		model.put("clientId",clientId );
		return new ModelAndView("devicetype/addType",model);
	}
	
	@RequestMapping(value="/addType" ,method=RequestMethod.POST)
	public ModelAndView saveDeviceType(@ModelAttribute("type") DeviceType typebean,HttpSession session){
		deviceService.addDeviceType(typebean);
		
		return new ModelAndView("redirect:/devicetype/viewType.sms?actionId="+getActionId());
	}
	
	@RequestMapping(value="/deleteType")
	public ModelAndView deleteDeviceType(@ModelAttribute("type") DeviceType typebean,HttpSession session){
		deviceService.deleteDeviceType(typebean.getDeviceTypeId());
		
		return new ModelAndView("redirect:/devicetype/viewType.sms?actionId="+getActionId());
	}
	@RequestMapping(value="/editType" ,method=RequestMethod.GET)
	public ModelAndView editDeviceType(@ModelAttribute("type") DeviceType typebean,HttpSession session){
		
		Map<String, Object> model = new HashMap<String, Object>();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());		
		model.put("device", deviceService.getDeviceType(typebean.getDeviceTypeId()));
		setActionId(actionId);
		model.put("actionId", actionId);
		 model.put("clientId", clientId);
		return new ModelAndView("devicetype/editType",model);
	}
	
	@RequestMapping(value="/viewType")
	public ModelAndView viewDeviceType(@RequestParam("actionId")String actionId,HttpSession session){
		//System.out.println("-----------in device type view page -------------------");
		Map<String, Object> model = new HashMap<String, Object>();
		String clientId=session.getAttribute("clientId").toString();
		model.put("device", deviceTypeListBean(deviceService.listDeviceType(clientId)));
		setActionId(actionId);
		model.put("actionId", actionId);
		return new ModelAndView("devicetype/viewType",model);
	}
	
	private List<DeviceTypeBean> deviceTypeListBean(List<Object[]> listDeviceType) {
		List<DeviceTypeBean> beans = null;
		if(listDeviceType != null && !listDeviceType.isEmpty()){
			beans = new ArrayList<DeviceTypeBean>();
			DeviceTypeBean bean = null;
			for(Object[] list : listDeviceType){
				bean = new DeviceTypeBean();
				
				bean.setDeviceTypeId(Integer.parseInt(list[0].toString()));
				bean.setDeviceTypeName(list[1].toString());
				bean.setClientName(list[2].toString());
				beans.add(bean);
			}
				
			}
		return beans;
	}

	
}
