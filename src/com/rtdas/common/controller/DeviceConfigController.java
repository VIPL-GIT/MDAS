package com.rtdas.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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

import com.rtdas.common.beans.DeviceBean;
import com.rtdas.common.beans.DeviceConfigBean;
import com.rtdas.common.beans.ParameterMasterBean;
import com.rtdas.common.models.Device;
import com.rtdas.common.models.MemoryMap;
import com.rtdas.common.service.DeviceConfigService;
import com.rtdas.common.service.DeviceService;
import com.rtdas.common.service.ParameterService;

@Controller

@RequestMapping(value="/deviceconfig")
public class DeviceConfigController {
	
	@Autowired
	private DeviceConfigService configService;
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
	
	@RequestMapping(value="/viewDeviceConfigPage" ,method=RequestMethod.GET)
	public ModelAndView viewDeviceConfigPage(@RequestParam("actionId")String actionId,HttpSession session){	
		
		Map<String, Object> model = new HashMap<String, Object>();
		String clientId=session.getAttribute("clientId").toString();
		model.put("device", prepareListofBean(deviceService.listDevice(clientId)));
		setActionId(actionId);
		model.put("actionId", actionId);
		return new ModelAndView("deviceconfig/viewDeviceConfigPage",model);
	}
	
	@RequestMapping(value="/viewDeviceConfig" ,method=RequestMethod.GET)
	public ModelAndView viewDevice(@RequestParam("device_id") int device_id,HttpSession session){	
		
		Map<String, Object> model = new HashMap<String, Object>();
		String clientId=session.getAttribute("clientId").toString();
//------------getting support params for this device------------------------------------
		List<Integer> mappedParams=deviceService.getMappedParams(device_id);		
		if(!mappedParams.isEmpty())
		  model.put("params", pservice.getParams(mappedParams));
//-------------------------------------------------------------------------------------------		
		
		List<DeviceConfigBean> configList=prepareconfigList(configService.listDeviceConfig(clientId, device_id));
		Device device=deviceService.getDevice(device_id);
		if(device!=null){
		int paramsCount=device.getSupportedParamsCount();
		int diff=paramsCount-(configList==null?0:configList.size());
		if(configList!=null){
			for(int i=0;i<diff;i++){
				System.out.println(paramsCount+"=========="+configList.size());
				DeviceConfigBean dcb=new DeviceConfigBean();
				dcb.setMf(1);
				
				configList.add(dcb);
			}		
		 
		}else{
			configList=new ArrayList();
			for(int i=0;i<paramsCount;i++){
				DeviceConfigBean dcb=new DeviceConfigBean();
				dcb.setMf(1);
				
				configList.add(dcb);
			}
		}
		
		model.put("deviceconfig",configList);
		System.out.println("--------------->"+configList.size());
		}
		return new ModelAndView("deviceconfig/viewDeviceConfig",model);
	}
	@RequestMapping("deleteConfig")
	public ModelAndView deleteAllParams(@RequestParam("deviceid") int deviceid){
		try{
			configService.deleteDeviceConfig(deviceid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/deviceconfig/viewDeviceConfig.epx?device_id=1");
	}
	@RequestMapping("deleteConfigById")
	public ModelAndView deleteConfigById(@RequestParam("memoryMapid") int memoryMapid,@RequestParam("deviceId") int deviceId){
		try{
			configService.deleteDeviceConfigBymemoryMapId(memoryMapid,deviceId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/deviceconfig/viewDeviceConfig.epx?device_id="+deviceId);
	}
	@RequestMapping("updateAllParams")
	public ModelAndView updateAllParams(@RequestParam Map<String,String> map,HttpSession session){
		String deviceid="0";
		try{
			String ids=map.get("ids");
			String register_address=map.get("register_address");
			String register_size=map.get("register_size");
			String function_code=map.get("function_code");
			String names=map.get("names");
			String descs=map.get("descs");
			String mfs=map.get("mfs");
			String eFlgs=map.get("eFlgs");
			String default_add_flg=map.get("default_add_flg");
			 deviceid=map.get("device");
			 
			System.out.println("--->"+names);
			
			String clientId=session.getAttribute("clientId").toString();
			
			for(int i=0;i<register_address.split(",").length;i++){				
				MemoryMap mm=new MemoryMap();
				
				int id=Integer.parseInt(ids.split(",")[i]);
				if(id>0) mm.setMemory_map_id(id);
				
				mm.setRegister_address(register_address.split(",")[i]);
				mm.setDescription(descs.split(",")[i]);
				mm.setMf(Float.parseFloat(mfs.split(",")[i]));
				mm.setRegister_size(register_size.split(",")[i]);
				mm.setFunction_code(Integer.parseInt(function_code.split(",")[i]));
				mm.setEnergy_flg(Integer.parseInt(eFlgs.split(",")[i]));
				mm.setDefault_add_flg(Integer.parseInt(default_add_flg.split(",")[i]));				
				mm.setParameter_id(names.split(",")[i]);
				
				mm.setClient_id(Integer.parseInt(clientId));
				mm.setDevice_id(Integer.parseInt(deviceid));
				
				configService.addDeviceConfig(mm);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/deviceconfig/viewDeviceConfig.epx?device_id="+deviceid);
	}
	
	@RequestMapping("updateParam")
	public ModelAndView updateParams(@ModelAttribute("MemoryMap") MemoryMap memoryMap,HttpSession session){
       try{
			System.out.println("---->"+memoryMap.getMemory_map_id());
			System.out.println("---->"+memoryMap.getParameter_id());
			System.out.println("---->"+memoryMap.getDescription());
			System.out.println("---->"+memoryMap.getMf());
			System.out.println("---->"+memoryMap.getEnergy_flg());
			String clientId=session.getAttribute("clientId").toString();
			memoryMap.setClient_id(Integer.parseInt(clientId));
			configService.addDeviceConfig(memoryMap);
    	   
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/deviceconfig/viewDeviceConfig.epx?device_id="+memoryMap.getDevice_id());
		
	}
	private List<DeviceConfigBean> prepareconfigList(List<MemoryMap> modelList){
		
		List<DeviceConfigBean> dcList=null;
		try{
			if(modelList!=null && !modelList.isEmpty()){
				dcList=new ArrayList();
				for(MemoryMap mm:modelList){
					DeviceConfigBean dc=new DeviceConfigBean();
					
					dc.setClient_id(mm.getClient_id());
					dc.setDefault_add_flg(mm.getDefault_add_flg());
					dc.setDescription(mm.getDescription());
					dc.setDevice_id(mm.getDevice_id());
					dc.setEnergy_flg(mm.getEnergy_flg());
					dc.setFunction_code(mm.getFunction_code());
					dc.setMemory_map_id(mm.getMemory_map_id());
					dc.setMf(mm.getMf());
					
					dc.setParameter_name(mm.getParameter_id());
					dc.setRegister_address(mm.getRegister_address());
					dc.setRegister_size(mm.getRegister_size());			
										
					dcList.add(dc);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dcList;
		
	}
	private List<ParameterMasterBean> parameterListBean(List<Object[]> listParameter) {
		List<ParameterMasterBean> beans = null;
		if(listParameter != null && !listParameter.isEmpty()){
			beans = new ArrayList<ParameterMasterBean>();
			ParameterMasterBean bean = null;
			for(Object[] list : listParameter){
				bean = new ParameterMasterBean();
				
				bean.setParameterId(Integer.parseInt(list[0].toString()));
				bean.setParameterName(list[1].toString());
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
			System.out.println(obj[1].toString()+" object to test "+obj[2].toString());
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

	@ModelAttribute("mfs")
	   public List<String> getCheckBoxList()
	   {
	      List<String> checkBoxList = new ArrayList<String>();
			checkBoxList.add("1");
			checkBoxList.add("10");
			checkBoxList.add("100");
			checkBoxList.add("1000");
			checkBoxList.add("10000");
			checkBoxList.add("100000");			
			return checkBoxList;
			
		}
	@ModelAttribute("rsizes")		
public List<String> getRegisterSize(){
	List<String> list=new ArrayList();
	list.add("U8");
	list.add("S8");
	list.add("U16");
	list.add("S16");
	list.add("U24");
	list.add("U32");
	list.add("S32");
	
	return list;
}
@ModelAttribute("fCodes")
public Map<String,String> getFunctionalCodes(){
	Map<String,String> map=new LinkedHashMap<>();
	map.put("1","Read Coil Status(1)");
	map.put("2","Read Coil Input(2)");
	map.put("3","Read Holding Registers(3)");
	map.put("4","Read Input Registers(4)");

	return map;
}
}
