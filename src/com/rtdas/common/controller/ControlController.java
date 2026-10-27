package com.rtdas.common.controller;

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

import com.rtdas.common.beans.TrackerDataBean;
import com.rtdas.common.service.TrackerService;

@Controller
@RequestMapping(value="/control")
public class ControlController {
	
	@Autowired
	private TrackerService service;
	
	@RequestMapping(value="/controlHome" ,method=RequestMethod.GET)
	public ModelAndView getConfigHome(HttpSession session){
		
		Map<String, Object> model = new HashMap<String, Object>();
		//String siteid=session.getServletContext().getInitParameter("siteid");
		String siteid=session.getAttribute("site_id").toString();
		System.out.println("site id------------>"+siteid);
		
		List<TrackerDataBean> list=service.getTrackersLatestData(siteid);
		model.put("trackers", list);
		return new ModelAndView("control/control_home",model);
	}
	@RequestMapping(value="/control_page" ,method=RequestMethod.GET)
	public ModelAndView getConfigPage(@RequestParam("traid")String traid,HttpSession session){
		
		Map<String, Object> model = new HashMap<String, Object>();
		//String siteid=session.getServletContext().getInitParameter("siteid");
		String siteid=session.getAttribute("site_id").toString();
		System.out.println("tra id------------>"+traid);
		List<TrackerDataBean> list=service.getTrackersLatestData(siteid);
		TrackerDataBean tdbean=new TrackerDataBean();
        if(!list.isEmpty()){
        	for(TrackerDataBean tdb:list){
        		if(tdb.getTracker_id().equals(traid))
        			tdbean=tdb;
        	}
        }
		model.put("tracker", tdbean);
		model.put("traid", traid);
		
		return new ModelAndView("control/control_page",model);
	}

}
