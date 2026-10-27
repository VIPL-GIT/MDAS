package com.rtdas.hes.controller;

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

import com.rtdas.hes.dao.impl.HESDao;
import com.rtdas.hes.models.Projects;
import com.rtdas.hes.models.RFGateway;
import com.rtdas.hes.models.SmartDevices;

@Controller
@RequestMapping(value = "/hes")
public class HESController {

	@Autowired
	public HESDao hesdao;
	
	public String actionId;

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	
	
	@RequestMapping(value = "/projectView", method = RequestMethod.GET)
	public ModelAndView getProjectView(@RequestParam("actionid") String actionId,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		setActionId(actionId);
		model.put("list", hesdao.getSmartProjects());
		model.put("actionId", actionId);
		return new ModelAndView("HES/Project/viewSmartProjects", model);
	}
	@RequestMapping(value = "/addSmartProject", method = RequestMethod.GET)
	public ModelAndView getAddProject(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();			
		model.put("actionId", getActionId());
		return new ModelAndView("HES/Project/addProject", model);
	}
	@RequestMapping(value = "/editSmartProject", method = RequestMethod.GET)
	public ModelAndView editSmartProject(@RequestParam("project_id")int project_id,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		model.put("proj", hesdao.editSmartProjects(project_id));
		model.put("actionId", getActionId());
		return new ModelAndView("HES/Project/editProject", model);
	}
	@RequestMapping(value = "/deleteSmartProject", method = RequestMethod.GET)
	public ModelAndView deleteSmartProject(@RequestParam("project_id")int project_id,HttpSession session) throws Exception{
		hesdao.deleteSmartProjects(project_id);		
		return new ModelAndView("redirect:/hes/projectView.epx?actionid="+getActionId());
	}
	@RequestMapping(value = "/addSmartProject", method = RequestMethod.POST)
	public ModelAndView saveProject(@ModelAttribute("proj")Projects proj,HttpSession session) throws Exception{
		proj.setCreated_by(session.getAttribute("userId").toString());
		proj.setUpdated_by(session.getAttribute("userId").toString());
		hesdao.saveSmartProjects(proj);		
		return new ModelAndView("redirect:/hes/projectView.epx?actionid="+getActionId());
	}
//-------------------------------------------------------------------------------------------------------------	
	@RequestMapping(value = "/deviceView", method = RequestMethod.GET)
	public ModelAndView getDeviceView(@RequestParam("actionid") String actionId,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		setActionId(actionId);
		model.put("list", hesdao.getSmartDevices());
		model.put("actionId", actionId);
		return new ModelAndView("HES/Devices/ViewSmartDevices", model);
	}
	@RequestMapping(value = "/addSmartDevices", method = RequestMethod.GET)
	public ModelAndView addSmartDevices(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		
		model.put("rflist", hesdao.getDistinctRFGateway());
		model.put("projects", hesdao.getSmartProjects());
		model.put("actionId", getActionId());
		return new ModelAndView("HES/Devices/addSmartDevice", model);
	}
	@RequestMapping(value = "/getSmartDevices", method = RequestMethod.GET)
	public ModelAndView getSmartDevices(@RequestParam("id")int id,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		
		model.put("sd", hesdao.getSmartDevice(id));
		model.put("rflist", hesdao.getDistinctRFGateway());
		model.put("projects", hesdao.getSmartProjects());
		model.put("actionId", getActionId());
		return new ModelAndView("HES/Devices/editSmartProject", model);
	}
	
	@RequestMapping(value = "/deleteSmartDevices", method = RequestMethod.GET)
	public ModelAndView deleteSmartDevices(@RequestParam("id")int id,HttpSession session) throws Exception{
		hesdao.deleteSmartDevice(id);		
		return new ModelAndView("redirect:/hes/deviceView.epx?actionid="+getActionId());
	}
	@RequestMapping(value = "/saveOrUpdateSmartDevice", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateSmartDevice(@ModelAttribute("sd")SmartDevices sd,HttpSession session) throws Exception{
		hesdao.saveSmartDevice(sd);		
		return new ModelAndView("redirect:/hes/deviceView.epx?actionid="+getActionId());
	}
	@RequestMapping(value = "/getMACAddress", method = RequestMethod.GET)
	public ModelAndView getMACAddress(@RequestParam("id")int id,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		
		model.put("rflist", hesdao.getMACAddress(id));
		return new ModelAndView("HES/RF/getMACAddress", model);
	}
	@RequestMapping(value = "/getProjectDetails", method = RequestMethod.GET)
	public ModelAndView getProjectDetails(@RequestParam("id")int id,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		
		model.put("proj", hesdao.editSmartProjects(id));
		return new ModelAndView("HES/Project/getProjectDetails", model);
	}
//-------------------------------------------------------------------------------------------------------------	
	@RequestMapping(value = "/rfView", method = RequestMethod.GET)
	public ModelAndView getRFView(@RequestParam("actionid") String actionId,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		System.out.println("in RF Gateway view............"+actionId);
		setActionId(actionId);
		model.put("list", hesdao.getRFGateway());
		model.put("actionId", actionId);
		return new ModelAndView("HES/RF/viewRFGateway", model);
	}
	@RequestMapping(value = "/addRF", method = RequestMethod.GET)
	public ModelAndView addRF(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		
		model.put("actionId", getActionId());
		return new ModelAndView("HES/RF/addRFGateway", model);
	}
	@RequestMapping(value = "/addRFGateway", method = RequestMethod.POST)
	public ModelAndView addRFGateway(@RequestParam("id")Map<String,String> map,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try{
		   String gateway_serial_number=map.get("gateway_serial_number");
		   String gateway_manufacturer=map.get("gateway_manufacturer");
		   int addNodes=Integer.parseInt(map.get("addNodes"));
		   List<String> address=new ArrayList();
		   for(int i=1;i<=addNodes;i++){
			   address.add(map.get("endnode_mac_address"+i));
		   }
		   if(!address.isEmpty()){
			   for(String mac:address){
				   RFGateway rf=new RFGateway();
				   rf.setEndnode_mac_address(mac);
				   rf.setGateway_manufacturer(gateway_manufacturer);
				   rf.setGateway_serial_number(gateway_serial_number);
				   
				   hesdao.updateRF(rf);
			   }
		   }
		   
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/hes/rfView.epx?actionid="+getActionId());
	}
	@RequestMapping(value = "/updateRFGateway", method = RequestMethod.POST)
	public ModelAndView updateRFGateway(@ModelAttribute("rf")RFGateway rf,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		
		hesdao.updateRF(rf);
		model.put("actionId", getActionId());
		return new ModelAndView("redirect:/hes/rfView.epx?actionid="+getActionId());
	}
	@RequestMapping(value = "/editRF", method = RequestMethod.GET)
	public ModelAndView editRF(@RequestParam("id") int rf_id,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		model.put("rf", hesdao.getRF(rf_id));
		model.put("actionId", getActionId());
		return new ModelAndView("HES/RF/editRFGateway", model);
	}
	@RequestMapping(value = "/deleteRF", method = RequestMethod.GET)
	public ModelAndView deleteRF(@RequestParam("id") int rf_id,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		
		hesdao.deleteRF(rf_id);
		return new ModelAndView("redirect:/hes/rfView.epx?actionid="+getActionId());
	}
	
	@ModelAttribute("metercategory")
	public List<String> getMeterCategory(){
		List<String> list=new ArrayList();
		list.add("1-Ph");
		list.add("3-Ph");
		list.add("LT-CT");
		
		return list;
	}
	@ModelAttribute("metertype")
	public List<String> getMeterType(){
		List<String> list=new ArrayList();
		list.add("Prepaid");
		list.add("Postpaid");
		return list;
	}
	@ModelAttribute("netmeter")
	public Map<String,String> netMeter(){
		Map<String,String> map=new HashMap();
		map.put("0","No");
		map.put("1","Yes");
		return map;
	}
	@ModelAttribute("iptype")
	public List<String> getIPType(){
		List<String> list=new ArrayList();
		list.add("IPV4");
		list.add("IPV6");
		return list;
	}
	@ModelAttribute("channeltype")
	public List<String> getChannelType(){
		List<String> list=new ArrayList();
		list.add("TCP");
		list.add("HDLC");
		return list;
	}
	@ModelAttribute("addressScheme")
	public Map<String,String> getAddressScheme(){
		Map<String,String> map=new HashMap();
		map.put("1", "1 byte");
		map.put("2", "2 byte");
		map.put("4", "4 byte");
		return map;
	}
}
