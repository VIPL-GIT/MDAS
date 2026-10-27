package com.rtdas.common.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.beans.DeviceBean;
import com.rtdas.common.beans.DeviceTypeBean;
import com.rtdas.common.models.Device;
import com.rtdas.common.service.DeviceService;
import com.rtdas.common.service.ParameterService;

@SuppressWarnings("deprecation")
@Controller

@RequestMapping(value="/device")
public class DeviceController {
	
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ParameterService pservice;
	public String actionId;

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	//---------------Device-------------------
	
	@RequestMapping(value="/viewDevice" ,method=RequestMethod.GET)
	public ModelAndView viewDevice(@RequestParam("actionId")String actionId,HttpSession session){
		
		Map<String, Object> model = new HashMap<String, Object>();
		String clientId=session.getAttribute("clientId").toString();
		model.put("device", prepareListofBean(deviceService.listDevice(clientId)));
		setActionId(actionId);
		model.put("actionId", actionId);
		return new ModelAndView("device/viewDevice",model);
	}
	
	@RequestMapping(value="/addDevice" ,method=RequestMethod.GET)
	public ModelAndView addDevice(HttpSession session){
		String clientId=session.getAttribute("clientId").toString();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("devicetype", deviceTypeListBean(deviceService.listDeviceType(clientId)));
		model.put("deviceMake", deviceMakeListBean(deviceService.listDeviceMake(clientId)));
		model.put("params", pservice.listParameter(clientId));
		model.put("actionId", getActionId());
		return new ModelAndView("device/addDevice",model);
	}
	@RequestMapping(value="/addDevice" ,method=RequestMethod.POST)
	public ModelAndView saveDevice(@ModelAttribute("command") DeviceBean deviceBean,
			BindingResult result){
		
		System.out.println("--->"+deviceBean.getDeviceMakeId());
		System.out.println("--->"+deviceBean.getDeviceTypeId());
		System.out.println("--->"+deviceBean.getSupportedBaudrate());
		deviceService.addDevice(prepareModel(deviceBean),deviceBean.getParamsId());
		
		return new ModelAndView("redirect:/device/viewDevice.epx?actionId="+getActionId());
	}
	
	@RequestMapping(value = "/editDevice", method = RequestMethod.GET)
	public ModelAndView editDevice(@ModelAttribute("command")  DeviceBean deviceBean,
			BindingResult result,HttpSession session){
		String clientId=session.getAttribute("clientId").toString();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("devicetype", deviceTypeListBean(deviceService.listDeviceType(clientId)));
		model.put("deviceMake", deviceMakeListBean(deviceService.listDeviceMake(clientId)));
		model.put("params", pservice.listParameter(clientId));
		model.put("actionId", getActionId());
		model.put("device", prepareDeviceBean(deviceService.getDevice(deviceBean.getDeviceId())));
		model.put("mappedParams", deviceService.getMappedParams(deviceBean.getDeviceId()));
		return new ModelAndView("device/editDevice",model);
	}
	@RequestMapping(value = "/deleteDevice", method = RequestMethod.GET)
	public ModelAndView deleteDevice(@ModelAttribute("command")  DeviceBean deviceBean,
			BindingResult result) {
		
		deviceService.deleteDevice(deviceBean.getDeviceId());		
		
		return new ModelAndView("redirect:/device/viewDevice.epx?actionId="+getActionId());
	}
private DeviceBean prepareDeviceBean(Device device) {
	DeviceBean bean = new DeviceBean();
	try{
		List<String> rates=null;
		String sBaudrates=device.getSupportedBaudrates();
		if(sBaudrates!=null && sBaudrates.contains(","))
			rates=Arrays.asList(sBaudrates.split(","));
		else{
			rates=new ArrayList();
			if(sBaudrates!=null)
		      rates.add(sBaudrates);
		}
		System.out.println("--->"+device.getActiveFlg());
		bean.setDeviceId(device.getDeviceId());
		bean.setDeviceTypeId(device.getDeviceTypeId());
		bean.setDeviceMakeId(device.getDeviceMakeId());
		bean.setSupportedParamsCount(device.getSupportedParamsCount());		
		 bean.setSupportedBaudrates(rates);		
		bean.setSupportedBaudrate(device.getSupportedBaudrates());
		bean.setActiveFlg(device.getActiveFlg());
		
	}catch(Exception e){
		e.printStackTrace();
	}
		return bean;
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
	private List<DeviceBean> prepareListofBean(List<Object[]> listdevice){
		List<DeviceBean> dbList=new ArrayList<DeviceBean>();
		for(Object[] obj:listdevice){
			DeviceBean db=new DeviceBean();
			db.setDeviceId(Integer.parseInt(obj[0].toString()));
			db.setDeviceTypeName(obj[1].toString());
			db.setDeviceMakeName(obj[2].toString());
			db.setSupportedParamsCount(Integer.parseInt(obj[3].toString()));
			if(obj[4]!=null)
			 db.setSupportedBaudrate(obj[4].toString());
			db.setActiveFlg(Integer.parseInt(obj[5].toString()));
			dbList.add(db);
		}
		
		return dbList;
	}
	
	

	private Device prepareModel(DeviceBean deviceBean) {
				Device device = new Device();
		try{
			device.setDeviceId(deviceBean.getDeviceId());
			device.setDeviceMakeId(deviceBean.getDeviceMakeId());
			device.setDeviceTypeId(deviceBean.getDeviceTypeId());
			device.setSupportedParamsCount(deviceBean.getSupportedParamsCount());
			device.setActiveFlg(deviceBean.getActiveFlg());
			device.setSupportedBaudrates(deviceBean.getSupportedBaudrate());
			
		}catch(Exception e){}
		return device;
	}
//----------------------------------------------------------------------------------------------------------	
	
	
 @ModelAttribute("baudrates")
   public List<String> getCheckBoxList()
   {
      List<String> checkBoxList = new ArrayList<String>();
		checkBoxList.add("300");
		checkBoxList.add("600");
		checkBoxList.add("1200");
		checkBoxList.add("2400");
		checkBoxList.add("4800");
		checkBoxList.add("9600");
		checkBoxList.add("19200");
		checkBoxList.add("14400");
		checkBoxList.add("38400");
		checkBoxList.add("57600");
		checkBoxList.add("115200");
		checkBoxList.add("128000");
		checkBoxList.add("256000");
		
		return checkBoxList;
		
	}
	
	
	
}
