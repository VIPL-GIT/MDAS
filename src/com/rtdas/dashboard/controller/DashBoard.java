package com.rtdas.dashboard.controller;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.MeterService;
import com.rtdas.dashboard.beans.GroupLoadBean;
import com.rtdas.dashboard.beans.SinglePhaseFeedersBean;
import com.rtdas.dashboard.dao.DashboardDao1;
import com.rtdas.meterdata.beans.TotalMasterdataBean;
import com.rtdas.reports.dao.TotalMasterDAO;

@Controller
@RequestMapping(value = "/Dashboard")
public class DashBoard {

	@Autowired	
	private MeterService meterService;
	@Autowired
	private HierarchyTitleService titleService;
	@Autowired
	private HierarchyService hservice;
	@Autowired	
	private DashboardDao1 ddao;
	@Autowired
	private TotalMasterDAO masterdao;
	
	DecimalFormat twoDec=new DecimalFormat("#0.00");

	    @RequestMapping(value = "/DashBoardNew", method = RequestMethod.GET)
	    public ModelAndView meterdatareportview(@RequestParam("actionId") String actionId,HttpSession session) throws Exception{
	
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("installpoints",meterService.getInstallPoints());
	 	return new ModelAndView("dashboard/DashBoardNew", model);
	    }
	    
	    @RequestMapping(value = "/LiveLoad", method = RequestMethod.GET)
	    public ModelAndView getLiveLoad(HttpSession session) throws Exception{
	
			Map<String, Object> model = new HashMap<String, Object>();
		 	return new ModelAndView("dashboard/getLiveLoad", model);
	    }
	    
	    @RequestMapping(value = "/LiveLoadData", method = RequestMethod.GET)
	    public ModelAndView getLiveLoadData(HttpSession session) throws Exception{
	
			Map<String, Object> model = new HashMap<String, Object>();
			try{
				Map<String,String> circles= getCircles();
				//Map<String,LiveLoadBean>  loadMap=ddao.getLoadMap(circles);
				//model.put("load", loadMap);
//--------------------------------------------------------------------------------------------------
                int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		        
				String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
				String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
				List<HierarchyTitle> titleList=titleService.listTitles(clientId);
				
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
					if (userHierarchyids!=null && level==1 ){    //for company level user
						
						model.put("circles",  ddao.getCircles(userHierarchyid, level));
						model.put("agmap", ddao.getLiveloadDetails_AG(userHierarchyid, level));
						model.put("nonagmap", ddao.getLiveloadDetails_NonAG(userHierarchyid, level));
						
					}			
//--------------------------------------------------------------------------------------------------				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		 	return new ModelAndView("dashboard/getLiveLoadData", model);
	    }
	    
	    @RequestMapping(value = "/LiveLoadGraph", method = RequestMethod.GET)
	    public ModelAndView getLiveLoadGraph(HttpSession session) throws Exception{
	
			Map<String, Object> model = new HashMap<String, Object>();
			try{
				Map<String,String> circles= getCircles();
				//Map<String,LiveLoadBean>  loadMap=ddao.getLoadMap(circles);
				//model.put("load", loadMap);
//--------------------------------------------------------------------------------------------------
                int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		        
				String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
				String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
				List<HierarchyTitle> titleList=titleService.listTitles(clientId);
				
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
					if (userHierarchyids!=null && level==1 ){    //for company level user
						
						model.put("values", ddao.getLiveloadGraphDetails(userHierarchyid, level));
						
						System.out.println("live load values --------"+ddao.getLiveloadGraphDetails(userHierarchyid, level));
					}			
//--------------------------------------------------------------------------------------------------				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		 	return new ModelAndView("dashboard/getLiveLoadGraph", model);
	    }
	    
	    @RequestMapping(value = "/offlineFeeders", method = RequestMethod.GET)
	    public ModelAndView getofflineFeeders(HttpSession session) throws Exception{
	
			Map<String, Object> model = new HashMap<String, Object>();
			List<Integer> ids=new ArrayList();
			List<TotalMasterdataBean> master=new ArrayList();
			try{
				
		        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		        
				String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
				String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
				List<HierarchyTitle> titleList=titleService.listTitles(clientId);
				
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
					if (userHierarchyids!=null && level==1 ){    //for company level user
						
						model.put("fmap", ddao.getOfflineFeedersDetails(userHierarchyid, level));
						
					}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		 	return new ModelAndView("dashboard/getofflineFeeders", model);
	    }
	    
	    @RequestMapping(value = "/pfNpowersStatus", method = RequestMethod.GET)
	    public ModelAndView getpfNpowersStatus(HttpSession session) throws Exception{
	
			Map<String, Object> model = new HashMap<String, Object>();
		 	return new ModelAndView("dashboard/getpfNpowersStatus", model);
	    }
	    
	    @RequestMapping(value = "/pfNpowersStatusData", method = RequestMethod.GET)
	    public ModelAndView getpfNpowersStatusData(@RequestParam("pfNpowerParam") String pfNpowerParam,@RequestParam("pfNpowerCondition") String pfNpowerCondition,
	    		@RequestParam("pfNpowerValues") String pfNpowerValues,HttpSession session) throws Exception{
	
	    	Map<String, Object> model = new HashMap<String, Object>();
			List<Integer> ids=new ArrayList();
			List<TotalMasterdataBean> master=new ArrayList();
			try{
				
		        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		        
				String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
				String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
				List<HierarchyTitle> titleList=titleService.listTitles(clientId);
				
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
					if (userHierarchyids!=null && level==1 ){    //for company level user
						
						model.put("fmap", ddao.getpfNpowersDetails(userHierarchyid, level,pfNpowerParam,pfNpowerCondition,pfNpowerValues));
						
					}
				
				model.put("pfNpowerParam", pfNpowerParam);
			}catch(Exception e){
				e.printStackTrace();
			}
		 	return new ModelAndView("dashboard/getpfNpowersStatusData", model);
	    }
	    
	    @RequestMapping(value = "/notCommFeeders", method = RequestMethod.GET)
	    public ModelAndView getnotCommFeeders(HttpSession session) throws Exception{
	
	    	Map<String, Object> model = new HashMap<String, Object>();
			List<Integer> ids=new ArrayList();
			List<TotalMasterdataBean> master=new ArrayList();
			try{
				
		        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		        
				String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
				String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
				List<HierarchyTitle> titleList=titleService.listTitles(clientId);
				
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
					if (userHierarchyids!=null && level==1 ){    //for company level user
						
						model.put("fmap", ddao.getNotCommFeedersDetails(userHierarchyid, level));
						
					}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		 	return new ModelAndView("dashboard/getnotCommFeeders", model);
	    }
	    
	    @RequestMapping(value = "/groupAFeeders", method = RequestMethod.GET)
	    public ModelAndView getgroupAFeeders(@RequestParam("date")String date,HttpSession session) throws Exception{
	
	    	Map<String, Object> model = new HashMap<String, Object>();
			List<Integer> ids=new ArrayList();
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> availmaster=new ArrayList();
			List<String> meters=new ArrayList();
			
			try{
				 int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
			        
					String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
					String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
					List<HierarchyTitle> titleList=titleService.listTitles(clientId);
					
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
						if (userHierarchyids!=null ){    //for company level user
							
							for(String sid:userHierarchyid.split(",")){
								ids.add(Integer.parseInt(sid));
							}
							master=masterdao.getTotalMasterDataBysdID(ids,level);
							
						}
					if(!master.isEmpty()){
						for(TotalMasterdataBean tmb:master){
							if(tmb.getLocation_group()!=null && tmb.getLocation_group().equalsIgnoreCase("A")){
								meters.add(tmb.getMeter_sl_no());
								
							}
						}
						
						System.out.println(date+"\t"+meters.size()+"\t"+availmaster.size());
						
						Map<String,GroupLoadBean> loadMap=ddao.getGroup_load(meters, date);
						
						for(TotalMasterdataBean tmb:master){
							if(loadMap.containsKey(tmb.getMeter_sl_no())){
								availmaster.add(tmb);
								
							}
						}
					double totalLoad=0;
					    for(TotalMasterdataBean tmb:availmaster){
					    	GroupLoadBean glb=(GroupLoadBean)loadMap.get(tmb.getMeter_sl_no());
					    	totalLoad=totalLoad+Double.parseDouble(glb.getLoad());
					    	//System.out.println(tmb.getMeter_sl_no()+"\t"+Double.parseDouble(glb.getLoad()));
					    }
					     
						
						model.put("totalLoad", twoDec.format(totalLoad));
						model.put("master", availmaster);
						model.put("load", loadMap);
					}
					
					
					
					
				
			}catch(Exception e){
				e.printStackTrace();
			}
		 	return new ModelAndView("dashboard/getgroupAFeeders", model);
	    }
	    @RequestMapping(value = "/groupBFeeders", method = RequestMethod.GET)
	    public ModelAndView getgroupBFeeders(@RequestParam("date")String date,HttpSession session) throws Exception{
	
	    	Map<String, Object> model = new HashMap<String, Object>();
	    	List<Integer> ids=new ArrayList();
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> availmaster=new ArrayList();
			List<String> meters=new ArrayList();
			try{
				int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		        
				String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
				String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
				List<HierarchyTitle> titleList=titleService.listTitles(clientId);
				
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
					if (userHierarchyids!=null ){    //for company level user
						
						for(String sid:userHierarchyid.split(",")){
							ids.add(Integer.parseInt(sid));
						}
						master=masterdao.getTotalMasterDataBysdID(ids,level);
						
					}
				if(!master.isEmpty()){
					for(TotalMasterdataBean tmb:master){
						if(tmb.getLocation_group()!=null && tmb.getLocation_group().equalsIgnoreCase("B")){
							meters.add(tmb.getMeter_sl_no());
							
						}
					}
					
					System.out.println(date+"\t"+meters.size()+"\t"+availmaster.size());
					
					Map<String,GroupLoadBean> loadMap=ddao.getGroup_load(meters, date);
					
					for(TotalMasterdataBean tmb:master){
						if(loadMap.containsKey(tmb.getMeter_sl_no()))
							availmaster.add(tmb);
					}
					
					double totalLoad=0;
				    for(TotalMasterdataBean tmb:availmaster){
				    	GroupLoadBean glb=(GroupLoadBean)loadMap.get(tmb.getMeter_sl_no());
				    	totalLoad=totalLoad+Double.parseDouble(glb.getLoad());
				    	//System.out.println(tmb.getMeter_sl_no()+"\t"+Double.parseDouble(glb.getLoad()));
				    }
				     
					
					model.put("totalLoad", twoDec.format(totalLoad));
					model.put("master", availmaster);
					model.put("load", loadMap);
				}
				
				
				
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		 	return new ModelAndView("dashboard/getgroupBFeeders", model);
	    }
	    @RequestMapping(value = "/groupCFeeders", method = RequestMethod.GET)
	    public ModelAndView getgroupCFeeders(@RequestParam("date")String date,HttpSession session) throws Exception{
	
	    	Map<String, Object> model = new HashMap<String, Object>();
	    	List<Integer> ids=new ArrayList();
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> availmaster=new ArrayList();
			List<String> meters=new ArrayList();
			try{
				int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		        
				String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
				String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
				List<HierarchyTitle> titleList=titleService.listTitles(clientId);
				
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
					if (userHierarchyids!=null ){    //for company level user
						
						for(String sid:userHierarchyid.split(",")){
							ids.add(Integer.parseInt(sid));
						}
						master=masterdao.getTotalMasterDataBysdID(ids,level);
						
					}
				if(!master.isEmpty()){
					for(TotalMasterdataBean tmb:master){
						if(tmb.getLocation_group()!=null && tmb.getLocation_group().equalsIgnoreCase("C")){
							meters.add(tmb.getMeter_sl_no());
							
						}
					}
					
					System.out.println(date+"\t"+meters.size()+"\t"+availmaster.size());
					
					Map<String,GroupLoadBean> loadMap=ddao.getGroup_load(meters, date);
					
					for(TotalMasterdataBean tmb:master){
						if(loadMap.containsKey(tmb.getMeter_sl_no()))
							availmaster.add(tmb);
					}
					
					double totalLoad=0;
				    for(TotalMasterdataBean tmb:availmaster){
				    	GroupLoadBean glb=(GroupLoadBean)loadMap.get(tmb.getMeter_sl_no());
				    	totalLoad=totalLoad+Double.parseDouble(glb.getLoad());
				    	//System.out.println(tmb.getMeter_sl_no()+"\t"+Double.parseDouble(glb.getLoad()));
				    }
				     
					
					model.put("totalLoad", twoDec.format(totalLoad));
					model.put("master", availmaster);
					model.put("load", loadMap);
				}
				
				
				
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		 	return new ModelAndView("dashboard/getgroupCFeeders", model);
	    }
	    
	    @RequestMapping(value = "/getSinglePhaseFeeders", method = RequestMethod.GET)
	    public ModelAndView getSinglePhaseFeeders(@RequestParam("date")String date,HttpSession session) throws Exception{
	
	    	Map<String, Object> model = new HashMap<String, Object>();
	    	List<Integer> ids=new ArrayList();
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> availmaster=new ArrayList();
			List<String> meters=new ArrayList();
			try{
			
                   int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		        
				String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
				String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
				List<HierarchyTitle> titleList=titleService.listTitles(clientId);
				
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
					if (userHierarchyids!=null ){    //for company level user
						
						for(String sid:userHierarchyid.split(",")){
							ids.add(Integer.parseInt(sid));
						}
						master=masterdao.getTotalMasterDataBysdID(ids,level);
						
					}
				if(!master.isEmpty()){
					for(TotalMasterdataBean tmb:master){
						if(tmb.getLocation_category().equals("NON AG") || tmb.getLocation_category().equals("DL")){
							meters.add(tmb.getMeter_sl_no());
							
						}
					}
					
                    Map<String,SinglePhaseFeedersBean> loadMap=ddao.getSinglePhaseSupplyFeeders(meters, date);
					
					for(TotalMasterdataBean tmb:master){
						if(loadMap.containsKey(tmb.getMeter_sl_no()))
							availmaster.add(tmb);
					}
					
					model.put("master", availmaster);
					model.put("load", loadMap);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		 	return new ModelAndView("dashboard/getSinglePhaseFeeders", model);
	    }
	    
	    @RequestMapping(value = "/getSinglePhaseDetails", method = RequestMethod.GET)
	    public ModelAndView getSinglePhaseDetails(@RequestParam("date")String date,@RequestParam("meter")String meter,HttpSession session) throws Exception{
	
	    	Map<String, Object> model = new HashMap<String, Object>();
	    	
			try{
			
               Map<String,String> map=ddao.getSinglePhaseDetails(meter,date); 				
			   model.put("map", map);	
			   
			}catch(Exception e){
				e.printStackTrace();
			}
		 	return new ModelAndView("dashboard/getSinglePhaseDetails", model);
	    }
	    
	    @ModelAttribute("circles")
	    public Map<String,String> getCircles(){
	    	Map<String,String> map=new LinkedHashMap<String, String>();
	    	try{
	    		map.put("D1", "JABAL PUR");
	    		map.put("D2", "KATNI");
	    		map.put("D3", "NARSINGH PUR");
	    		map.put("D4", "CHHIND WARA");
	    		map.put("D5", "SEONI");
	    		map.put("D6", "BALA GHAT");
	    		map.put("D7", "MANDLA");
	    		map.put("D8", "DINDORI");
	    		map.put("D9", "SAGAR");
	    		map.put("D10", "DAMOH");
	    		map.put("D11", "TIKAM GARH");
	    		map.put("D12", "CHHATAR PUR");
	    		map.put("D13", "PANNA");
	    		map.put("D14", "REWA");
	    		map.put("D15", "SATNA");
	    		map.put("D16", "SIDHI");
	    		map.put("D17", "SING RAULI");
	    		map.put("D18", "SHAHDOL");
	    		map.put("D19", "ANUP PUR");
	    		map.put("D20", "UMARIA");
	    	}catch(Exception e){
	    		
	    	}
	    	return  map;
	    }
	
}
