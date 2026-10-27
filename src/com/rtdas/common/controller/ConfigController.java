package com.rtdas.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.beans.TrackerDataBean;
import com.rtdas.common.service.TrackerService;

import resources.sock;

@Controller
@RequestMapping(value="/config")
public class ConfigController {
	
	@Autowired
	private TrackerService service;
	
	@RequestMapping(value="/configHome" ,method=RequestMethod.GET)
	public ModelAndView getConfigHome(HttpSession session){
		
		Map<String, Object> model = new HashMap<String, Object>();
		//String siteid=session.getServletContext().getInitParameter("siteid");
		String siteid=session.getAttribute("site_id").toString();
		System.out.println("site id------------>"+siteid);
		
		List<TrackerDataBean> list=service.getTrackersLatestData(siteid);
		model.put("trackers", list);
		return new ModelAndView("config/config_home",model);
	}
	@RequestMapping(value="/config_page" ,method=RequestMethod.GET)
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
		
		return new ModelAndView("config/config_page",model);
	}
	@RequestMapping(value="/send_config" ,method=RequestMethod.POST)
	public @ResponseBody String sendConfig(@RequestParam("map")Map map,HttpServletRequest req,HttpServletResponse res,HttpSession session){
		String result="";
		try{
			String cmd=map.get("cmd").toString();
			String password=map.get("password").toString();
			
			if(password.equalsIgnoreCase("admin@123")){
			//System.out.println(password+"\t"+cmd);
			result=sock.sendmessage(cmd);
			}else{
				res.setStatus(400);
				result="Invalid password...";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return result;
	}

}
