package com.rtdas.mdm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.mdm.beans.AllEventsBean;
import com.rtdas.mdm.beans.BillingBean;
import com.rtdas.mdm.beans.EventsBean;
import com.rtdas.mdm.beans.LoadBean;
import com.rtdas.mdm.models.D2_curr;
import com.rtdas.mdm.models.D3_b3;
import com.rtdas.mdm.models.D3_b5;
import com.rtdas.mdm.models.D3_columns;
import com.rtdas.mdm.models.D4_columns;
import com.rtdas.mdm.models.D4_data;
import com.rtdas.mdm.models.D5_columns;
import com.rtdas.mdm.models.D5_data;
import com.rtdas.mdm.models.D5_data_all;
import com.rtdas.mdm.service.MeterDataService;
import com.rtdas.meterdata.beans.AutoResetEnergies;





@RestController
public class Meter360ViewRestController {
	
	@Autowired
	public MeterDataService service;
	
	@RequestMapping(value="/360Instant/{meter}/{date}" ,method=RequestMethod.GET)
	public D2_curr getInstant(@PathVariable("meter")   String meterno,@PathVariable("date")  String date,HttpSession session) throws Exception{
		 return service.getLatestInstant(meterno,date);
	}
	
	@RequestMapping(value="/360Billing/{meter}/{date}" ,method=RequestMethod.GET)
	public BillingBean getBilling(@PathVariable("meter")   String meterno,@PathVariable("date")  String date,HttpSession session) throws Exception{
		BillingBean bb=null;
		try {
		D3_columns b3_header=service.getBillingHeaders("b3", meterno);
		D3_columns b5_header=service.getBillingHeaders("b5", meterno);
		List<D3_b3> b3_list=service.getBillingEnergies(meterno, date);
		List<D3_b5> b5_list=service.getBillingMds(meterno, date);
		
		bb=new BillingBean();
		if(b3_header!=null) bb.setB3_header(b3_header);
		if(b5_header!=null) bb.setB5_header(b5_header);
		if(!b3_list.isEmpty()) bb.setEnergies(b3_list);
		if(!b5_list.isEmpty()) bb.setMds(b5_list);
		
		}catch(Exception e) {}
		return bb;
		
	}
	@RequestMapping(value="/360AutoEnergies/{meter}" ,method=RequestMethod.GET)
	public List<AutoResetEnergies> getAutoResetEnergies(@PathVariable("meter")   String meterno,HttpSession session) throws Exception{
		List<AutoResetEnergies> bb=null;
		try {
		  bb=service.getAutoResetEnergies(meterno);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bb;
		
	}
	
	@RequestMapping(value="/360Load/{meter}/{date}" ,method=RequestMethod.GET)
	public LoadBean getLoad(@PathVariable("meter")   String meterno,@PathVariable("date")  String date,HttpSession session) throws Exception{
		LoadBean lb=null;
		try {
			lb=new LoadBean();
			D4_columns header=service.getLoadHeaders(meterno);
			List<D4_data> list=service.getLoadProfile(meterno, date);
			
			if(header!=null) lb.setHeader(header);
			if(!list.isEmpty()) lb.setData(list);
			
		}catch(Exception e) {}
		return lb;
	}
	
	@RequestMapping(value="/360Events/{meter}/{date}" ,method=RequestMethod.GET)
	public EventsBean getEvents(@PathVariable("meter")   String meterno,@PathVariable("date")  String date,HttpSession session) throws Exception{
		EventsBean eb=null;
       try {
			eb=new EventsBean();
			D5_columns header=service.getEventsHeaders(meterno);
			List<D5_data> list=service.getEvents(meterno, date);
			
			if(header!=null) eb.setHeader(header);
			if(!list.isEmpty()) eb.setData(list);
			
		}catch(Exception e) {}
		return eb;
	}
	@RequestMapping(value="/Events/{meter}" ,method=RequestMethod.GET)
	public AllEventsBean getAllEvents(@PathVariable("meter")   String meterno,HttpSession session) throws Exception{
		AllEventsBean eb=null;
       try {
			eb=new AllEventsBean();
			D5_columns header=service.getEventsHeaders(meterno);
			List<D5_data_all> list=service.getAllEvents(meterno);
			
			if(header!=null) eb.setHeader(header);
			if(!list.isEmpty()) eb.setData(list);
			
		}catch(Exception e) {}
		return eb;
	}
	@RequestMapping(value="/360EventsSummary" ,method=RequestMethod.GET)
	public ModelAndView getEventsSummary(@PathVariable("meter")   String meterno,@PathVariable("date")  String date,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
try {
			
		}catch(Exception e) {}
		return new ModelAndView("Reports/360ViewLoadTabler");
	}

}
