package com.rtdas.meterdata.controller;

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
@RequestMapping(value = "/OnDemand")
public class OnDemandController {

	@Autowired
	private HierarchyTitleService tService;
	@Autowired
	private HierarchyService hService;
	@Autowired
	private HierarchyTitleService titleService;
	@Autowired	
	private MeterService meterService;
	@Autowired
	private HierarchyService hservice;
	
	  @RequestMapping(value = "/OnDemandView", method = RequestMethod.GET)
	    public ModelAndView OnDemandView(@RequestParam("actionId") String actionId,HttpSession session) throws Exception{
	    	Map<String, Object> model = new HashMap();
	    	try {
	    		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
	    		System.out.println("clientId -->  "+clientId);
	    		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
	    		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
	    		
	    		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
	    		System.out.println("size -->  "+titleList.size());
	    		
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
System.out.println(metersList+"   END of method-------------------------"+metersList.size()+"   ----------      "+ssIds);	    	    
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	 	//return new ModelAndView("OnDemand/OnDemandView", model);
	    	return new ModelAndView("OnDemand/OnDemandView", model);
	    }
	
	
}
