package com.rtdas.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.service.AlertsService;
import com.rtdas.common.service.TrackerService;

@Controller
@RequestMapping("alerts")
public class AlertsController {
	
	@Autowired
	private AlertsService service;
	@Autowired
	private TrackerService tservice;
	
	@RequestMapping("latestAlerts")
	public ModelAndView getLatestAlerts(@RequestParam("type")String type,HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			//String siteid=session.getServletContext().getInitParameter("siteid");
			String siteid=session.getAttribute("site_id").toString();
			model.put("alerts", service.getLast10Alerts(siteid,type));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("alerts/latest_alerts",model);
	}
	@RequestMapping("alertsHome")
	public ModelAndView getAlertsHome(HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			//String siteid=session.getServletContext().getInitParameter("siteid");
			String siteid=session.getAttribute("site_id").toString();
			model.put("alerts", service.getLast10Alerts(siteid,"last10"));
			model.put("tralist", tservice.getAllTrackers(siteid));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("alerts/alerts_home",model);
	}
	@RequestMapping("alertsHistory")
	public ModelAndView getAlertsHistory(@RequestParam("map")Map map,HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			//String siteid=session.getServletContext().getInitParameter("siteid");
			String siteid=session.getAttribute("site_id").toString();
			String traid=map.get("traid").toString();
			String from=map.get("from").toString();
			String to=map.get("to").toString();
			
			model.put("alerts", service.getAlertsHistory(siteid, traid, from, to));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("alerts/alerts_history",model);
	}
	@RequestMapping("weather")
	public ModelAndView getWeather(HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		
		return new ModelAndView("dashboard/getWeather",model);
	}

}
