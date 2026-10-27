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
import com.rtdas.common.models.DeviceMake;
import com.rtdas.common.service.ClientService;
import com.rtdas.common.service.DeviceService;

@Controller

@RequestMapping(value="/devicemake")
public class DeviceMakeController {

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
	
	@RequestMapping(value="/addMake" ,method=RequestMethod.GET)
	public ModelAndView addDeviceType(HttpSession session){
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		Map<String, Object> model = new HashMap<String, Object>();		
		model.put("client", clientService.listClients());
		model.put("actionId", getActionId());
		model.put("clientId",clientId );
		return new ModelAndView("devicemake/addMake",model);
	}
	
	@RequestMapping(value="/addMake" ,method=RequestMethod.POST)
	public ModelAndView saveDeviceType(@ModelAttribute("type") DeviceMake makebean,HttpSession session){
		deviceService.addDeviceMake(makebean);
		
		return new ModelAndView("redirect:/devicemake/viewMake.sms?actionId="+getActionId());
	}
	
	@RequestMapping(value="/deleteMake")
	public ModelAndView deleteDeviceType(@ModelAttribute("type") DeviceMake makebean,HttpSession session){
		deviceService.deleteDeviceMake(makebean.getDeviceMakeId());
		
		return new ModelAndView("redirect:/devicemake/viewMake.sms?actionId="+getActionId());
	}
	@RequestMapping(value="/editMake" ,method=RequestMethod.GET)
	public ModelAndView editDeviceType(@ModelAttribute("type") DeviceMake makebean,HttpSession session){
		
		Map<String, Object> model = new HashMap<String, Object>();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());		
		model.put("device", deviceService.getDeviceMake(makebean.getDeviceMakeId()));
		setActionId(actionId);
		model.put("actionId", actionId);
		 model.put("clientId", clientId);
		return new ModelAndView("devicemake/editMake",model);
	}
	
	@RequestMapping(value="/viewMake")
	public ModelAndView viewDeviceType(@RequestParam("actionId")String actionId,HttpSession session){
		//System.out.println("-----------in device type view page -------------------");
		Map<String, Object> model = new HashMap<String, Object>();
		String clientId=session.getAttribute("clientId").toString();
		model.put("device", deviceMakeListBean(deviceService.listDeviceMake((clientId))));
		setActionId(actionId);
		model.put("actionId", actionId);
		return new ModelAndView("devicemake/viewMake",model);
	}
	
	private List<DeviceTypeBean> deviceMakeListBean(List<Object[]> listDeviceType) {
		List<DeviceTypeBean> beans = null;
		if(listDeviceType != null && !listDeviceType.isEmpty()){
			beans = new ArrayList<DeviceTypeBean>();
			DeviceTypeBean bean = null;
			for(Object[] list : listDeviceType){
				bean = new DeviceTypeBean();
				
				bean.setDeviceMakeId(Integer.parseInt(list[0].toString()));
				bean.setDeviceMakeName(list[1].toString());
				bean.setClientName(list[2].toString());
				beans.add(bean);
			}
				
			}
		return beans;
	}

	
}
