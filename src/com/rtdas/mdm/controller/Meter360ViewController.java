package com.rtdas.mdm.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.Meter;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.MeterService;
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
import com.rtdas.mdm.service.MeterDataService;
import com.rtdas.meterdata.beans.AutoResetEnergies;

import utils.Events;

@Controller
public class Meter360ViewController {
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM- yyyy");
	@Autowired
	private HierarchyTitleService titleService;
	@Autowired	
	private MeterService meterService;
	@Autowired
	private HierarchyService hservice;
	@Autowired
	public MeterDataService service;
	
	@RequestMapping(value="/360Home" ,method=RequestMethod.GET)
	public ModelAndView getHome(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try {
			int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
			String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
    		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
			List<HierarchyTitle> titleList=titleService.listTitles(clientId);
			model.put("installpoints",meterService.getInstallPoints());
			model.put("titles", titleList);
			model.put("userHierarchyid", userHierarchyid);	
			List<Integer> ssIds=new ArrayList();
			//------------------getting all child ids--------------------- 
    		
    		int level=0;
    		if(userHierarchyids!=null){
    			Hierarchy userHierarchy=hservice.getHierarchy(Integer.parseInt(userHierarchyids[0]));
    		 for(int i=0;i<titleList.size();i++){
    			HierarchyTitle htitle=(HierarchyTitle)titleList.get(i);
    			if(htitle.getHierarchy_type_id()==userHierarchy.getHierarchy_type_id()){
    				level=i+1;	    				
    		       }
    		     }//for
    		 }//if
    		if(userHierarchyids!=null && (level==2 || level==3)){  //for SDO users and SS users user
    			for(int i=0;i<userHierarchyids.length;i++){
    			  ssIds.add(Integer.parseInt(userHierarchyids[i]));
    			}
    			//to get ssids
    			List<Object[]> sslist=new ArrayList();
    			for(int i=0;i<userHierarchyids.length;i++){
    			  sslist.addAll(hservice.listAllChildsByParentId( level, titleList.size(), Integer.parseInt(userHierarchyids[i])));
    		    }
    			for(Object[] objArray:sslist) {
	    			ssIds.add(Integer.parseInt(objArray[0].toString()));
	    		}
    			
    		}
    		else if (userHierarchyids!=null && level==1 ){    //for company level user
    			//to get ssids
    			List<Object[]> sslist=new ArrayList();
    			for(int i=0;i<userHierarchyids.length;i++){
    			  sslist.addAll(hservice.listAllChildsByParentId( level, titleList.size(), Integer.parseInt(userHierarchyids[i])));
    		    }
    			//to get SDO ids
    			List<Object[]> sdolist=new ArrayList();
    			for(int i=0;i<userHierarchyids.length;i++){
    				sdolist.addAll(hservice.listAllChildsByParentId( level+1, titleList.size(), Integer.parseInt(userHierarchyids[i])));
    			}
    			for(Object[] objArray:sslist) {
	    			ssIds.add(Integer.parseInt(objArray[0].toString()));
	    		}
    			for(Object[] objArray:sdolist) {
	    			ssIds.add(Integer.parseInt(objArray[0].toString()));
	    		}
    		}
    		else{           //for main admin
    			//to add SDO ids
    			List<Hierarchy> hie=hservice.getHierarchyByType(titleList.get(0).getHierarchy_type_id());
    			for(Hierarchy h:hie){
    				//to get ssids
	    			List<Object[]> sslist=hservice.listAllChildsByParentId( 1, titleList.size(), h.getHierarchy_id());
	    			//to get SDO ids
	    			List<Object[]> sdolist=hservice.listAllChildsByParentId( 2, titleList.size(), h.getHierarchy_id());
	    			for(Object[] objArray:sslist) {
		    			ssIds.add(Integer.parseInt(objArray[0].toString()));
		    		}
	    			for(Object[] objArray:sdolist) {
		    			ssIds.add(Integer.parseInt(objArray[0].toString()));
		    		}
    			}//for
    		}
//------------------------------------
			
			List<Meter> metersList=meterService.listMetersByLocation(ssIds, 0); //0 to get all the meters
		    model.put("meters",metersList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("mdm_reports/360view/360Home1",model);
	}
	
	@RequestMapping(value="/360HomeNew" ,method=RequestMethod.GET)
	public ModelAndView getHomeNew(HttpServletRequest req,HttpServletResponse res,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		
		model.put("meter", req.getParameter("meter"));
		return new ModelAndView("mdm_reports/360view/360HomeNew",model);
	}
	
	@RequestMapping(value="/360Instant" ,method=RequestMethod.GET)
	public ModelAndView getInstant(@RequestParam("meter") String meter, HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try {
			
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
		    SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy"); 
		    DecimalFormat tdec=new DecimalFormat("#0.00");
		    Map<String, String> params = new HashMap<String, String>();
		    params.put("meter", meter);
		    params.put("date",sdf1.format(new java.util.Date()));
//		    params.put("meter", "CESO0492");
//		    params.put("date","03-02-2019");
		    String date= sdf1.format(new java.util.Date());
		    //date="29-04-2020";
		    D2_curr result = service.getLatestInstant(meter,date);
		    
		   if(result==null) {
			   model.put("error","No Data Found...");
		   }else {
		    if(result.getR_Y_VOLTAGE_ANGLE()!=null)
		     result.setR_Y_VOLTAGE_ANGLE(tdec.format(Double.parseDouble(result.getR_Y_VOLTAGE_ANGLE())));
		    else
		    	result.setR_Y_VOLTAGE_ANGLE("0");
		    if(result.getY_B_VOLTAGE_ANGLE()!=null)
		     result.setY_B_VOLTAGE_ANGLE(tdec.format(Double.parseDouble(result.getY_B_VOLTAGE_ANGLE())));
		    else
		    	result.setY_B_VOLTAGE_ANGLE("0");
		    if(result.getB_R_VOLTAGE_ANGLE()!=null)
		     result.setB_R_VOLTAGE_ANGLE(tdec.format(Double.parseDouble(result.getB_R_VOLTAGE_ANGLE())));
		    else
		    	result.setB_R_VOLTAGE_ANGLE("0");
		    //result.setRtd_diff(tdec.format((sdf.parse(result.getG2()).getTime()-sdf.parse(result.getG3()).getTime())/(1000*60f)));
		    model.put("result",result);
		   }
		    
		}catch(Exception e) {
			e.printStackTrace();
			model.put("error",e.getMessage());
		}
		return new ModelAndView("mdm_reports/360view/360ViewInstant",model);
	}
	
	@RequestMapping(value="/360Billing" ,method=RequestMethod.GET)
	public ModelAndView getBilling(@RequestParam("type") String type,@RequestParam("meter") String meter,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try {
			
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy"); 
		    Map<String, String> params = new HashMap<String, String>();
//		    params.put("meter", "CSO60347");
//		    params.put("date","07-02-2019");
		    params.put("meter", meter);
		    params.put("date",sdf1.format(new java.util.Date()));
		    
		    String date=sdf1.format(new java.util.Date()); 
		   // date="29-04-2020";
		    BillingBean bb = null;
		    
			try {
			D3_columns b3_header=service.getBillingHeaders("b3", meter);
			D3_columns b5_header=service.getBillingHeaders("b5", meter);
			List<D3_b3> b3_list=service.getBillingEnergies(meter, date);
			List<D3_b5> b5_list=service.getBillingMds(meter, date);
			
			bb=new BillingBean();
			if(b3_header!=null) bb.setB3_header(b3_header);
			if(b5_header!=null) bb.setB5_header(b5_header);
			if(!b3_list.isEmpty()) bb.setEnergies(b3_list);
			if(!b5_list.isEmpty()) bb.setMds(b5_list);
			
			}catch(Exception e) {}
		    model.put("result",bb);
		   
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(type.equals("tab"))
		 return new ModelAndView("mdm_reports/360view/360ViewBillingTabler",model);
		else
			 return new ModelAndView("mdm_reports/360view/360ViewBillingGraph",model);
	}
	
	@RequestMapping(value="/360AutoEnergies" ,method=RequestMethod.GET)
	public ModelAndView getAutoEnergies(@RequestParam("type") String type,@RequestParam("meter") String meter,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try {
			
			List<AutoResetEnergies> result=null;
		    Map<String, String> params = new HashMap<String, String>();
//		    params.put("meter", "CSO60347");
//		    params.put("date","07-02-2019");
		    params.put("meter", meter);
		    
		     
		    
		    
		    result=service.getAutoResetEnergies(meter);
		    System.out.println("--------->"+result.size());
		    if(result==null || result.isEmpty() || result.size()==0) {
		    	model.put("error","No Data Found...");
		    }else {
		    model.put("result",result);
		    }
		   
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(type.equals("tab"))
		   return new ModelAndView("mdm_reports/360view/360ViewAutoEnergiesTabler",model);
		else
			 return new ModelAndView("mdm_reports/360view/360ViewAutoEnergiesGraph",model);
	}
	
	@RequestMapping(value="/360Load" ,method=RequestMethod.GET)
	public ModelAndView getLoad(@RequestParam("type") String type,@RequestParam("meter") String meter,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try {
			
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy"); 
			String previousDay=sdf1.format(new java.util.Date().getTime()-24*60*60*1000);
			System.out.println(meter+"---------------->"+previousDay);
		    Map<String, String> params = new HashMap<String, String>();
		    params.put("meter", meter);
		    params.put("date",previousDay);
		    String date= previousDay;
		   // date="27-04-2020";
		   
		    LoadBean result =null;

    		try {
    			result=new LoadBean();
    			D4_columns header=service.getLoadHeaders(meter);
    			List<D4_data> list=service.getLoadProfile(meter, date);
    			
    			if(header!=null) result.setHeader(header);
    			if(!list.isEmpty()) result.setData(list);
    			
    		}catch(Exception e) {}		
		  System.out.println(result.getData());
		    if(result==null || result.getData()==null) {
		    	model.put("error","No Data Found...");
		    }else {
		    model.put("result",result);
		    }
		    
		}catch(Exception e) {
			e.printStackTrace();
			model.put("error",e.getMessage());
		}
		if(type.equals("tab"))
		   return new ModelAndView("mdm_reports/360view/360ViewLoadTabler",model);
		else
		   return new ModelAndView("mdm_reports/360view/360ViewLoadGraph",model);
	}
	
	@RequestMapping(value="/360Events" ,method=RequestMethod.GET)
	public ModelAndView getEvents(@RequestParam("meter") String meter,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try {
			
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");  
		    Map<String, String> params = new HashMap<String, String>();
		    params.put("meter", meter);
		    params.put("date",sdf1.format(new java.util.Date()));
		    String date=sdf1.format(new java.util.Date());
		  //  date="23-04-2020";
		    
		    RestTemplate restTemplate = new RestTemplate();
		    EventsBean result = null;
		    try {
		    	result=new EventsBean();
				D5_columns header=service.getEventsHeaders(meter);
				List<D5_data> list=service.getEvents(meter, date);
				
				if(header!=null) result.setHeader(header);
				if(!list.isEmpty()) result.setData(list);
				
			}catch(Exception e) {}
		    if(result==null || result.getData()==null) {
		    	model.put("error","No Data Found...");
		    }else {
		    List<D5_data> dataList=result.getData();
		    Map<Integer,String> eventsMap=new Events().evetnsList();
		    for(D5_data d:dataList) {
		    	d.setEvent_type(eventsMap.containsKey(Integer.parseInt(d.getEvent_type()))?eventsMap.get(Integer.parseInt(d.getEvent_type())):"");
		    }
		    
		    model.put("result",result);
		    }
		    
		}catch(Exception e) {
			model.put("error",e.getMessage());
			e.printStackTrace();
		}
		
		return new ModelAndView("mdm_reports/360view/360ViewEvents",model);
	}

}
