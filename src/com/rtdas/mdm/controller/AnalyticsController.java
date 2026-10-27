package com.rtdas.mdm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.mdm.dao.impl.AnalyticsDao;

@Controller
@RequestMapping(value = "/analytics")
public class AnalyticsController {

	@Autowired
	private AnalyticsDao adao;
	
	@RequestMapping(value="/billing" ,method=RequestMethod.GET)
	public ModelAndView getbilling(@RequestParam("meter") String meter,HttpSession session) {
		Map<String, Object> model = new HashMap();
		String view="";
		try {			
			view="analytics/billing";
			model.put("list", adao.getBilling(meter));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(view,model);
	}
	
	@RequestMapping(value="/dailyenergy" ,method=RequestMethod.GET)
	public ModelAndView getdailyenergy(@RequestParam("meter") String meter,@RequestParam("month") String month,HttpSession session) {
		Map<String, Object> model = new HashMap();
		String view="";
		try {			
			view="analytics/dailyenergy";
			model.put("list", adao.getDailyEnergies(meter,month));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(view,model);
	}
	
	@RequestMapping(value="/poweronoff" ,method=RequestMethod.GET)
	public ModelAndView getpoweronoff(@RequestParam("meter") String meter,HttpSession session) {
		Map<String, Object> model = new HashMap();
		String view="";
		try {			
			view="analytics/outages";
			model.put("list", adao.getPoweronoff(meter));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(view,model);
	}
	
	@RequestMapping(value="/poweronoff1" ,method=RequestMethod.GET)
	public ModelAndView getevents(@RequestParam("meter") String meter,
			@RequestParam("fromD") String from,
			@RequestParam("toD") String to,HttpSession session) {
		Map<String, Object> model = new HashMap();
		String view="";
		try {			
			view="analytics/otherEvents";
			model.put("list", adao.getEvents(meter,from,to));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(view,model);
	}
	
	@RequestMapping(value="/load" ,method=RequestMethod.GET)
	public ModelAndView getLoadProfile(@RequestParam("meter") String meter,
			@RequestParam("month") String month,HttpSession session) {
		Map<String, Object> model = new HashMap();
		String view="";
		try {			
			view="analytics/loadProfile";
			model.put("list", adao.getLoadProfile(meter,month));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(view,model);
	}
	
}
