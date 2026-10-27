package com.rtdas.common.controller;

import java.text.SimpleDateFormat;
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

import com.rtdas.common.beans.HierarchyBean;
import com.rtdas.common.models.*;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;

@Controller
@RequestMapping(value="/hdata")
public class HierarchyController {
	@Autowired 
	private HierarchyService hService;
	@Autowired
	private HierarchyTitleService titleService;
    public String actionId;
	
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	@RequestMapping(value="/addHdata" ,method=RequestMethod.GET)
	public ModelAndView addTitle(@RequestParam("level")int level,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		int clientId =Integer.parseInt(session.getAttribute("clientId").toString());
		List titleList=titleService.listTitles(clientId);
		model.put("titles", titleList);
		List<Client> clientsList=titleService.listClients();
	    model.put("clients", clientsList);
		model.put("selectedLevel", level);
		HierarchyTitle title=(HierarchyTitle)titleList.get(level-1);		
		model.put("selLevelTitle", title.getHierarchy_title());
		model.put("selLevelType", title.getHierarchy_type_id());
		System.out.println("----->"+title.getHierarchy_title());
		HierarchyTitle firstTitle=(HierarchyTitle)titleList.get(0);
		model.put("firstLevelData", hService.getHierarchyByType(firstTitle.getHierarchy_type_id()));
		model.put("actionId", getActionId());
		model.put("clientId", clientId);
		return new ModelAndView("HierarchyData/addHierarchyData",model);
	}
	@RequestMapping(value="/addHdata" ,method=RequestMethod.POST)
	public ModelAndView saveData(@ModelAttribute("data") HierarchyBean hierarchyBean) throws Exception{
		System.out.println("save data");
		try{
			hService.addHierarchy(prepareModel(hierarchyBean));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/hdata/viewHdata.epx?hlevel=1&actionId="+getActionId());
	}
	
	@RequestMapping(value="/viewHdata")
	public ModelAndView viewModems(@RequestParam("hlevel")int hlevel,@RequestParam("actionId")String actionId,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		int clientId =Integer.parseInt(session.getAttribute("clientId").toString());
		
			List titleList=titleService.listTitles(clientId);
			System.out.println("titleList  --> "+titleList);
			model.put("titles", titleList);
			if(!titleList.isEmpty()){
	    HierarchyTitle selectedLevel=(HierarchyTitle)titleList.get(hlevel-1);
		HierarchyTitle title=(HierarchyTitle)titleList.get(hlevel-1);
		System.out.println(selectedLevel+" <--> "+title);
		model.put("levelData", hService.listHierarchy(hlevel,selectedLevel.getHierarchy_type_id(),clientId));	
		System.out.println("----->title  "+title.getHierarchy_title());
		model.put("selLevelTitle", title.getHierarchy_title());
			}
		model.put("selectedLevel", hlevel);
		setActionId(actionId);
		model.put("actionId", actionId);
		return new ModelAndView("HierarchyData/viewHierarchyData",model);
		
	}
	@RequestMapping(value="/editHdata" ,method=RequestMethod.GET)
	public ModelAndView editModem(@ModelAttribute("hbean") HierarchyBean hBean,@RequestParam("level") int level,HttpSession session) throws Exception{
	    System.out.println(level+" level");
		Map<String, Object> model = new HashMap<String, Object>();
		int clientId =Integer.parseInt(session.getAttribute("clientId").toString());
		model.put("hdata", prepareModemBean(hService.getHierarchy(hBean.getHierarchy_id())));
		List titleList=titleService.listTitles(clientId);
		model.put("titles", titleList);
		List<Client> clientsList=titleService.listClients();
	    model.put("clients", clientsList);
		model.put("selectedLevel", level);
		HierarchyTitle title=(HierarchyTitle)titleList.get(level-1);		
		model.put("selLevelTitle", title.getHierarchy_title());
		model.put("selLevelType", title.getHierarchy_type_id());
		System.out.println("----->"+title.getHierarchy_title());
		HierarchyTitle firstTitle=(HierarchyTitle)titleList.get(0);
		model.put("firstLevelData", hService.getHierarchyByType(firstTitle.getHierarchy_type_id()));
		System.out.println("---->"+hService.listHierarchyById(level, hBean.getHierarchy_id()));
		model.put("currentHierarchy", hService.listHierarchyById(level, hBean.getHierarchy_id()));
		model.put("actionId", getActionId());
		model.put("clientId", clientId);
		return new ModelAndView("HierarchyData/edtHierarchyData",model);
		
	}
	@RequestMapping(value="/deleteHdata")
	public ModelAndView deleteModem(@RequestParam("hId") String hId,@RequestParam("level") int level) throws Exception{
		System.out.println("delete hdata");
		HierarchyBean cb=new HierarchyBean();
		cb.setHierarchy_id(Integer.parseInt(hId));
		hService.deleteHierarchy(prepareModel(cb));
         return new ModelAndView("redirect:/hdata/viewHdata.epx?hlevel="+level+"&actionId="+getActionId());
	}
	
private Hierarchy prepareModel(HierarchyBean hBean){
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-YYYY");
		Hierarchy title=new Hierarchy();
		title.setActive_flg(hBean.getActive_flg());
		title.setClient_id(hBean.getClient_id());
		title.setHierarchy_desc(hBean.getHierarchy_desc());
		title.setHierarchy_id(hBean.getHierarchy_id());
		title.setHierarchy_name(hBean.getHierarchy_name());
		title.setHierarchy_type_id(hBean.getHierarchy_type_id());
		title.setParent_id(hBean.getParent_id());
		title.setCode(hBean.getCode());
		title.setCapacity(hBean.getCapacity());
		
		System.out.println(hBean.getParent_id()+" parentid");
		return title;
		
	}
	
	private List<HierarchyBean> prepareListofBean(List<Hierarchy> hData){
		Map<Integer,String> clientMap=new HashMap();
		List<Client> clientsList=titleService.listClients();
		
		for(Client cl:clientsList){
			clientMap.put(cl.getClient_id(), cl.getClient_name());
		}
		
		List<HierarchyBean> beans = null;
		if(hData != null && !hData.isEmpty()){
			beans = new ArrayList<HierarchyBean>();
			HierarchyBean bean = null;
			for(Hierarchy hierarchy : hData){
				bean = new HierarchyBean();
				bean.setActive_flg(hierarchy.getActive_flg());
				bean.setClient_id(hierarchy.getClient_id());
				bean.setHierarchy_desc(hierarchy.getHierarchy_desc());
				bean.setHierarchy_id(hierarchy.getHierarchy_id());
				bean.setHierarchy_name(hierarchy.getHierarchy_name());
				bean.setHierarchy_type_id(hierarchy.getHierarchy_type_id());
				bean.setParent_id(hierarchy.getParent_id());
				
				bean.setClient_name(clientMap.get(hierarchy.getClient_id()));
				beans.add(bean);
			}
		}
		return beans;
	}
	private HierarchyBean prepareModemBean(Hierarchy hierarchy){
		
		HierarchyBean bean = new HierarchyBean();
		bean.setActive_flg(hierarchy.getActive_flg());
		bean.setClient_id(hierarchy.getClient_id());
		bean.setHierarchy_desc(hierarchy.getHierarchy_desc());
		bean.setHierarchy_id(hierarchy.getHierarchy_id());
		bean.setHierarchy_name(hierarchy.getHierarchy_name());
		bean.setHierarchy_type_id(hierarchy.getHierarchy_type_id());
		bean.setParent_id(hierarchy.getParent_id());							
		bean.setCode(hierarchy.getCode());
		bean.setCapacity(hierarchy.getCapacity());
		return bean;
	}
	

}
