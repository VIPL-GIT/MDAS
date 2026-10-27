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

import java.text.SimpleDateFormat;
import java.util.*;

import com.rtdas.common.beans.TrackerDataBean;
import com.rtdas.common.service.TrackerService;

@Controller
@RequestMapping(value="/tracker")
public class TrackerController {
	@Autowired
	private TrackerService service;
	
	@RequestMapping(value="/trackerhome" ,method=RequestMethod.GET)
	public ModelAndView getHomePage(HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
		
		//String siteid=session.getServletContext().getInitParameter("siteid");
			String siteid=session.getAttribute("site_id").toString();
		//System.out.println("site id in tracker------------>"+siteid);
		
		List<TrackerDataBean> list=service.getTrackersLatestData(siteid);
		//System.out.println("Trackers size ---->"+list.size());
		if(!list.isEmpty()){
		List<String> traList=new ArrayList();
		  for(TrackerDataBean tdb:list){
			 traList.add(tdb.getTracker_id());
		  }
		  model.put("tralist", traList);
		}
		model.put("trackers", list);
		model.put("adjusts", service.getAdjustments(siteid));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("reports/tracker/tracker_home",model);
		
	}
	@RequestMapping(value="/trackerHistoryData" ,method=RequestMethod.GET)
	public ModelAndView gettrackerHistoryData(@RequestParam("map")Map map,HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			System.out.println(map);
		//String siteid=session.getServletContext().getInitParameter("siteid");
			String siteid=session.getAttribute("site_id").toString();
		String device_id=map.get("deviceid").toString();
		String from=map.get("from").toString();
		String to=map.get("to").toString();
		
		System.out.println(device_id+"\t"+from+"\t"+to);
		
		List<TrackerDataBean> list=service.getTrackersHistoryData(siteid, device_id, from, to);
		//System.out.println("Trackers size ---->"+list.size());
		
		model.put("trackers", list);
		model.put("adjusts", service.getAdjustments(siteid));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("reports/tracker/tracker_historydata",model);
		
	}
	@RequestMapping(value="/trackerHistoryGraph" ,method=RequestMethod.GET)
	public ModelAndView gettrackerHistoryGraph(@RequestParam("map")Map map,HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			System.out.println(map);
		//String siteid=session.getServletContext().getInitParameter("siteid");
			String siteid=session.getAttribute("site_id").toString();
		String device_id=map.get("deviceid").toString();
		String from=map.get("from").toString();
		String to=map.get("to").toString();
		
		System.out.println(device_id+"\t"+from+"\t"+to);
		
		List<TrackerDataBean> list=service.getTrackersHistoryData(siteid, device_id, from, to);
		//System.out.println("Trackers size ---->"+list.size());
		
		model.put("trackers", list);
		model.put("adjusts", service.getAdjustments(siteid));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("reports/tracker/tracker_historygraph",model);
		
	}
	
	@RequestMapping(value="/batteryHistoryData" ,method=RequestMethod.GET)
	public ModelAndView getbatteryHistoryData(@RequestParam("map")Map map,HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			System.out.println(map);
		//String siteid=session.getServletContext().getInitParameter("siteid");
			String siteid=session.getAttribute("site_id").toString();
		String device_id=map.get("deviceid").toString();
		String from=map.get("from").toString();
		String to=map.get("to").toString();
		
		System.out.println(device_id+"\t"+from+"\t"+to);
		
		List<TrackerDataBean> list=service.getTrackersHistoryData(siteid, device_id, from, to);
		//System.out.println("Trackers size ---->"+list.size());
		
		model.put("trackers", list);
		model.put("adjusts", service.getAdjustments(siteid));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("reports/battery/battery_historydata",model);
		
	}
	@RequestMapping(value="/batteryHistoryGraph" ,method=RequestMethod.GET)
	public ModelAndView getbatteryHistoryGraph(@RequestParam("map")Map map,HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			System.out.println(map);
		//String siteid=session.getServletContext().getInitParameter("siteid");
			String siteid=session.getAttribute("site_id").toString();
		String device_id=map.get("deviceid").toString();
		String from=map.get("from").toString();
		String to=map.get("to").toString();
		
		System.out.println(device_id+"\t"+from+"\t"+to);
		
		List<TrackerDataBean> list=service.getTrackersHistoryData(siteid, device_id, from, to);
		//System.out.println("Trackers size ---->"+list.size());
		
		model.put("trackers", list);
		model.put("adjusts", service.getAdjustments(siteid));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("reports/battery/battery_historygraph",model);
		
	}
	
	@RequestMapping(value="/batteryhome" ,method=RequestMethod.GET)
	public ModelAndView getBatteryHomePage(HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
		
		//String siteid=session.getServletContext().getInitParameter("siteid");
			String siteid=session.getAttribute("site_id").toString();
		//System.out.println("site id in tracker------------>"+siteid);
		
		List<TrackerDataBean> list=service.getTrackersLatestData(siteid);
		//System.out.println("Trackers size ---->"+list.size());
		if(!list.isEmpty()){
		List<String> traList=new ArrayList();
		  for(TrackerDataBean tdb:list){
			 traList.add(tdb.getTracker_id());
		  }
		  model.put("tralist", traList);
		}
		model.put("trackers", list);
		model.put("adjusts", service.getAdjustments(siteid));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("reports/battery/battery_home",model);
		
	}
	
	@RequestMapping(value="/trackerSATdata" ,method=RequestMethod.GET)
	public ModelAndView getTrackerSATdata(@RequestParam("traid")String traid,HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
		
		//String siteid=session.getServletContext().getInitParameter("siteid");
			String siteid=session.getAttribute("site_id").toString();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		String from=sdf.format(new java.util.Date())+" 00:00";
		String to=sdf.format(new java.util.Date())+" 23:59";
		//System.out.println("tacker id==============>"+traid);
		List<TrackerDataBean> list=service.getTrackersHistoryData(siteid,traid,from,to);
		System.out.println("sat size ---->"+list.size());
		if(!list.isEmpty()){		
		  model.put("satdata", list);
		}		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("dashboard/getSATgraphDetails",model);
		
	}
	@RequestMapping(value="/trackerSOCdata" ,method=RequestMethod.GET)
	public ModelAndView getTrackerSOCdata(@RequestParam("traid")String traid,HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
		
		//String siteid=session.getServletContext().getInitParameter("siteid");
			String siteid=session.getAttribute("site_id").toString();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		String from=sdf.format(new java.util.Date())+" 00:00";
		String to=sdf.format(new java.util.Date())+" 23:59";
		List<TrackerDataBean> list=service.getTrackersHistoryData(siteid,traid,from,to);
		System.out.println("soc size ---->"+list.size());
		if(!list.isEmpty()){		
		  model.put("socdata", list);
		}		
		model.put("adjusts", service.getAdjustments(siteid));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("dashboard/getSOCgraphDetails",model);
		
	}

}
