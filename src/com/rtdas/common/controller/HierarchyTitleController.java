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

import com.rtdas.common.beans.HierarchyTitleBean;
import com.rtdas.common.models.*;
import com.rtdas.common.service.HierarchyTitleService;


@Controller
@RequestMapping(value="/htitle")
public class HierarchyTitleController {

	@Autowired
	private HierarchyTitleService titleService;
public String actionId;
	
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
//	private List<Client> clientsList=titleService.listClients();
	
	@RequestMapping(value="/addTitle" ,method=RequestMethod.GET)
	public ModelAndView addTitle(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		List<Client> clientsList=titleService.listClients();
	    model.put("clients", clientsList);
	    model.put("titles", prepareListofBean(titleService.listTitles(clientId)));
	    model.put("actionId", getActionId());
	    model.put("clientId", clientId);
		return new ModelAndView("HierarchyTitle/addHierarchyTitle",model);
	}
	
	@RequestMapping(value="/addTitle" ,method=RequestMethod.POST)
	public ModelAndView saveModem(@ModelAttribute("title") HierarchyTitleBean titleBean,HttpSession session) throws Exception{
		try{
			titleService.addTitle(prepareModel(titleBean));
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String, Object> model = new HashMap();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
//		List<Client> clientsList=titleService.listClients();
//	    model.put("clients", clientsList);
	    model.put("titles", prepareListofBean(titleService.listTitles(clientId)));
	    model.put("actionId", getActionId());
		return new ModelAndView("HierarchyTitle/viewHierarchyTitles",model);
	}
	
	@RequestMapping(value="/viewTitles" ,method=RequestMethod.GET)
	public ModelAndView viewTitles(@RequestParam("actionId")String actionId,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		model.put("titles", prepareListofBean(titleService.listTitles(clientId)));
		setActionId(actionId);
		model.put("actionId", actionId);
		model.put("clientId", clientId);
		return new ModelAndView("HierarchyTitle/viewHierarchyTitles",model);
		
	}
	@RequestMapping(value="/editTitle" ,method=RequestMethod.GET)
	public ModelAndView editTitle(@ModelAttribute("modem") HierarchyTitleBean titleBean,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		model.put("title", prepareModemBean(titleService.getTitles(titleBean.getHierarchy_type_id())));
		List<Client> clientsList=titleService.listClients();
	    model.put("clients", clientsList);
	    model.put("titles", prepareListofBean(titleService.listTitles(clientId)));
	    model.put("actionId", getActionId());
	    model.put("clientId", clientId);
		return new ModelAndView("HierarchyTitle/editHierarchyTitle",model);
		
	}
	@RequestMapping(value="/deleteTitle")
	public ModelAndView deleteModem(@RequestParam("titleId") String titleId,HttpSession session) throws Exception{
		HierarchyTitleBean cb=new HierarchyTitleBean();
		cb.setHierarchy_type_id(Integer.parseInt(titleId));
		titleService.deleteTitle(prepareModel(cb));
		Map<String, Object> model = new HashMap();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		model.put("titles", prepareListofBean(titleService.listTitles(clientId)));
		model.put("actionId", getActionId());
		return new ModelAndView("HierarchyTitle/viewHierarchyTitles",model);
	}
	
private HierarchyTitle prepareModel(HierarchyTitleBean titleBean){
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-YYYY");
		HierarchyTitle title=new HierarchyTitle();
		title.setHierarchy_type_id(titleBean.getHierarchy_type_id());
		title.setHierarchy_title(titleBean.getHierarchy_title());
		title.setActive_flg(titleBean.getActive_flg());
		title.setClient_id(titleBean.getClient_id());
		title.setParent_title_id(titleBean.getParent_title_id());
		title.setTitle_desc(titleBean.getTitle_desc());
		title.setDevice_location(titleBean.getDevice_location());
		return title;
		
	}
	
	private List<HierarchyTitleBean> prepareListofBean(List<HierarchyTitle> titles){
		Map<Integer,String> parentmap=new HashMap();
		Map<Integer,String> clientMap=new HashMap();
		List<Client> clientsList=titleService.listClients();
		for(HierarchyTitle title : titles){
			parentmap.put(title.getHierarchy_type_id(), title.getHierarchy_title());
		}
		for(Client cl:clientsList){
			clientMap.put(cl.getClient_id(), cl.getClient_name());
		}
		List<HierarchyTitleBean> beans = null;
		if(titles != null && !titles.isEmpty()){
			beans = new ArrayList<HierarchyTitleBean>();
			HierarchyTitleBean bean = null;
			for(HierarchyTitle title : titles){
				bean = new HierarchyTitleBean();
				bean.setActive_flg(title.getActive_flg());
				bean.setClient_id(title.getClient_id());
				bean.setHierarchy_title(title.getHierarchy_title());
				bean.setHierarchy_type_id(title.getHierarchy_type_id());
				bean.setParent_title_id(title.getParent_title_id());
				bean.setTitle_desc(title.getTitle_desc());
				bean.setDevice_location(title.getDevice_location());
				bean.setParent_title(parentmap.get(title.getParent_title_id()));
				bean.setClient_name(clientMap.get(title.getClient_id()));
				beans.add(bean);
			}
		}
		return beans;
	}
	private HierarchyTitleBean prepareModemBean(HierarchyTitle title){
		
		HierarchyTitleBean bean = new HierarchyTitleBean();
		bean.setActive_flg(title.getActive_flg());
		bean.setClient_id(title.getClient_id());
		bean.setHierarchy_title(title.getHierarchy_title());
		bean.setHierarchy_type_id(title.getHierarchy_type_id());
		bean.setParent_title_id(title.getParent_title_id());
		bean.setTitle_desc(title.getTitle_desc());
		bean.setDevice_location(title.getDevice_location());						
					
		return bean;
	}
	
	
}
