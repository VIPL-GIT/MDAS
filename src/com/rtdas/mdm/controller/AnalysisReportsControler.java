package com.rtdas.mdm.controller;

import java.util.ArrayList;
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

import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.Meter;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.MeterService;

@Controller
public class AnalysisReportsControler {

	@Autowired
	private HierarchyTitleService titleService;
	@Autowired	
	private MeterService meterService;
	@Autowired
	private HierarchyService hservice;
	
	@RequestMapping(value="/analysisHome" ,method=RequestMethod.GET)
	public ModelAndView getHome(@RequestParam("report") String reportName,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		String view="";
		try {
			
			view="mdm_reports/AnalysisHome";
			int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
			String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
			List<HierarchyTitle> titleList=titleService.listTitles(clientId);
			model.put("installpoints",meterService.getInstallPoints());
			model.put("titles", titleList);
			model.put("userHierarchyid", userHierarchyid);	
			model.put("report", reportName);
			List<Meter> metersList=getMeterList(userHierarchyid,clientId);
		    model.put("meters",metersList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(view,model);
	}
	
	@RequestMapping(value="/PeakLoadViolation" ,method=RequestMethod.GET)
	public ModelAndView getPeakLoadViolation(@RequestParam("meter") String meter,
			                                 @RequestParam("year") String year,
			                                 HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		String view="";
		try {			
			view="mdm_reports/PeakLoadViolation";
			model.put("meter", meter);
			model.put("year", year);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(view,model);
	}
	
	@RequestMapping(value="/MeterAssetMgmtHome" ,method=RequestMethod.GET)
	public ModelAndView getMeterAssetMgmtHome(@RequestParam("meter") String meter,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		String view="";
		try {			
			view="mdm_reports/MeterAssetMgmtHome";
			model.put("meter", meter);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(view,model);
	}
	@RequestMapping(value="/AnalyticsHome" ,method=RequestMethod.GET)
	public ModelAndView getAnalyticsHome(@RequestParam("meter") String meter,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		String view="";
		try {			
			view="mdm_reports/AnalyticsHome";
			model.put("meter", meter);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(view,model);
	}
	@RequestMapping(value="/loadfactor" ,method=RequestMethod.GET)
	public ModelAndView getloadfactor(@RequestParam("meter") String meter,@RequestParam("year") String year,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		String view="";
		try {			
			view="mdm_reports/LoadFactor";
			model.put("meter", meter);
			model.put("year", year);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(view,model);
	}
	@RequestMapping(value="/demandfactor" ,method=RequestMethod.GET)
	public ModelAndView getdemandfactor(@RequestParam("meter") String meter,@RequestParam("year") String year,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		String view="";
		try {			
			view="mdm_reports/DemandFactor";
			model.put("meter", meter);
			model.put("year", year);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(view,model);
	}
	
	
	public List<Meter> getMeterList(String userHierarchyid,int clientId ){
		List<Meter> meters=new ArrayList();
		try{
			String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
			List<HierarchyTitle> titleList=titleService.listTitles(clientId);
			
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
    		meters=meterService.listMetersByLocation(ssIds, 0); //0 to get all the meters
		}catch(Exception e){}
		
		return meters;
	}
}
