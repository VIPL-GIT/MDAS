package com.rtdas.meterdata.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.Meter;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.MeterService;
import com.rtdas.mdm.models.AutoResetEnergies_Cesc;
import com.rtdas.meterdata.beans.WSIntegrationSummaryBean;
import com.rtdas.meterdata.daoimpl.BillingIntegrationDao;
import com.rtdas.meterdata.models.DeviceIntegrationSummary;

import utils.ProcessUploadedBilling;


@Controller
@RequestMapping(value = "/MeterData")
public class MeterDataController {
	
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
	@Autowired
	private BillingIntegrationDao dao;	

	
	    @RequestMapping(value = "/MeterDataTabs", method = RequestMethod.GET)
	    public ModelAndView meterdatareportview(@RequestParam("actionId") String actionId,HttpSession session) throws Exception{
	    	Map<String, Object> model = new HashMap();
	    	try {
	    		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
	    		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
	    		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
	    		System.out.println("clientId -->  "+clientId);
	    		System.out.println("userHierarchyid---------------------"+userHierarchyids[0]);
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
//System.out.println("END of method-------------------------"+metersList);	    	    
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	 	return new ModelAndView("MeterData/MeterDataTabs", model);
	    }
	    
	    
	    @RequestMapping(value = "/ViewAlerts", method = RequestMethod.GET)
	    public ModelAndView getAlerts(@RequestParam("actionId") String actionId,HttpSession session) throws Exception{
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
	    		    System.out.println("SS IDs-----------"+ssIds);
	    		List<Meter> metersList=meterService.listMetersByLocation(ssIds, 0); //0 to get all the meters
	    	    model.put("meters",metersList);
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	 	return new ModelAndView("MeterData/ViewAlerts", model);
	    }

	    
	    @RequestMapping(value = "/ResetEnergies", method = RequestMethod.GET)
	    public ModelAndView getResetEnergies(@RequestParam("actionId") String actionId,HttpSession session) throws Exception{
	    	Map<String, Object> model = new HashMap();
	    	System.out.println("reset ----------");
	    	
	    	try {
	    		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
	    		System.out.println("clientId -->  "+clientId);
	    		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
	    		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
	    		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
	    		System.out.println("size -->  "+titleList.size());
	    		model.put("installpoints",meterService.getInstallPoints());
	    		model.put("titles", titleList);
	    		
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
	    		List<String> meters=new ArrayList();
	    		if(!metersList.isEmpty()){
	    			for(Meter mt:metersList){
	    				meters.add(mt.getMeter_sl_no());
	    			}
	    		}
	    	    model.put("meters",meters);
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	 	return new ModelAndView("MeterData/ResetEnergies", model);
	    }
	    
	    
	    @RequestMapping(value="/uploadBillData",method = RequestMethod.POST)
	    public ModelAndView  doUpload(@RequestParam("file") MultipartFile file,HttpSession session) {  
			
				
	        Map<String, Object> model = new HashMap();
	        
		    String path = session.getServletContext().getInitParameter("file-upload");
	        String filename=file.getOriginalFilename();  
	        System.out.println(path+" "+filename);  
	        try{  
	        byte barr[]=file.getBytes();  
	        BufferedOutputStream bout=new BufferedOutputStream(  
	        new FileOutputStream(path+"/"+filename)); 
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
	        ProcessUploadedBilling xlsxreader=new ProcessUploadedBilling();
	        List<AutoResetEnergies_Cesc> list=xlsxreader.readBillingFile(path,filename,session);
			model.put("list",list);
	        }catch(Exception e){System.out.println(e);}  
	        return new ModelAndView("MeterData/ViewUploadedFile", model);
	    }
	    @RequestMapping(value="/processBillData",method = RequestMethod.POST)
	    public @ResponseBody String  processBillData(@RequestParam("file") MultipartFile file,HttpSession session) {  
			
				
	        Map<String, Object> model = new HashMap();
	        
		    String path = session.getServletContext().getInitParameter("file-upload");
	        String filename=file.getOriginalFilename();  
	        System.out.println(path+" "+filename);  
	        try{  
	        byte barr[]=file.getBytes();  
	        BufferedOutputStream bout=new BufferedOutputStream(  
	        new FileOutputStream(path+"/"+filename));  
	       
			
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
	        ProcessUploadedBilling xlsxreader=new ProcessUploadedBilling();
			xlsxreader.processBillingFile(path,filename,session);
			
	        }catch(Exception e){System.out.println(e);}  
	        return filename+" processed successfully...";
	    }	    
	    
@RequestMapping(value="/downloadBillData",method = RequestMethod.POST)
	    public ModelAndView  doUpload(@RequestParam("balance_type") String balance_type,HttpSession session) {  
			
	    	Map<String, Object> model = new HashMap();
	        try{
	        	List<DeviceIntegrationSummary> list=dao.getBillingdata();
	        	model.put("list", list);
	        }catch(Exception e){
	        	e.printStackTrace();
	        }	        
	        
	        return new ModelAndView("MeterData/ViewBillingBalanceMeters", model);
	    }

@RequestMapping(value="/getBillingIStatus")
public ModelAndView  getBillingIStatus(HttpSession session) {  
	
	Map<String, Object> model = new HashMap();
    try{
    	System.out.println("in getting I status ");
    	List<WSIntegrationSummaryBean> list=dao.getBillingIStatus();
    	model.put("list", list);
    }catch(Exception e){
    	e.printStackTrace();
    }	        
    
    return new ModelAndView("MeterData/ViewBillingIstatus", model);
}
	    
	    
	    @RequestMapping(value = "/ViewDiagnosis", method = RequestMethod.GET)
	    public ModelAndView getDiagnosis(@RequestParam("actionId") String actionId,HttpSession session) throws Exception{
	    	Map<String, Object> model = new HashMap();
	    	try {
	    		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
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
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	 	return new ModelAndView("MeterData/ViewDiagnosis", model);
	    }

	    
	    
 
	    
}


