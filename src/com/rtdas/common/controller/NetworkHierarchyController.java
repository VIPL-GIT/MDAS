package com.rtdas.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.models.HierarchyTitle;

@Controller
@RequestMapping(value="/nhdata")
public class NetworkHierarchyController {
	
	@Autowired
    private ServletContext servletContext;
	
    public String actionId;
	
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	@RequestMapping(value="/viewNHdata")
	public ModelAndView viewModems(@RequestParam("hlevel")String hlevel,@RequestParam("actionId")String actionId,HttpSession session) throws Exception{
		System.out.println("viewNHdata");
		Map<String, Object> model = new HashMap();
		int clientId =Integer.parseInt(session.getAttribute("clientId").toString());
		setActionId(actionId);
		model.put("actionId", actionId);
		model.put("hlevel",hlevel);
		
		return new ModelAndView("NetworkHierarchy/viewNHierarchyData",model);
	}
	
	@RequestMapping(value="/bulkHome")
	public ModelAndView viewBulkHome(@RequestParam("actionId")String actionId,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		int clientId =Integer.parseInt(session.getAttribute("clientId").toString());
		setActionId(actionId);
		model.put("actionId", actionId);
		return new ModelAndView("NetworkHierarchy/BulkuploadHome",model);
		
	}

}
