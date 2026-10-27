package com.rtdas.dashboard.controller;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.MeterService;
import com.rtdas.dashboard.beans.CircleavgSupplyWeeklyBean;
import com.rtdas.dashboard.dao.DashboardDaoMP;
import com.rtdas.feeder_reports.beans.ReliabilityIndexBean;
import com.rtdas.meterdata.beans.TotalMasterdataBean;
import com.rtdas.meterdata.models.FeederLiveStatus;
import com.rtdas.reports.dao.FeederReportsDao;
import com.rtdas.reports.dao.TotalMasterDAO;

import utils.DateUtilities;
import utils.GetPieGraph;

@Controller
@RequestMapping(value = "/Dashboard_pominda")
public class Dashboard_pominda {

	@Autowired
	private HierarchyService hservice;
	@Autowired
	private HierarchyTitleService titleService;
	@Autowired	
	private MeterService meterService;
	@Autowired	
	private DashboardDaoMP ddao;
	@Autowired
	private TotalMasterDAO masterdao;
	@Autowired
	private FeederReportsDao fdao;
	
	
	@RequestMapping(value = "/dashHome", method = RequestMethod.GET)
    public ModelAndView getdashHome(@RequestParam("actionId") String actionId,HttpSession session) throws Exception{

	Map<String, Object> model = new HashMap<String, Object>();
	List<TotalMasterdataBean> master=new ArrayList();
//--------------------------getting the values for comm status------------------------------
	try{
		DecimalFormat twoDec=new DecimalFormat("#0.000");
		twoDec.setRoundingMode(RoundingMode.DOWN);
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String currentDate=sdf.format(new java.util.Date());
        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
 	return new ModelAndView("pominda/dashHome", model);
    }
	
	@RequestMapping(value = "/getLiveCommStatus", method = RequestMethod.GET)
    public ModelAndView getLiveCommStatus(@RequestParam("level") String levelStr,@RequestParam("levelID") String levelID,
    		@RequestParam("date") String date,HttpSession session) throws Exception{

	Map<String, Object> model = new HashMap<String, Object>();
	List<TotalMasterdataBean> master=new ArrayList();
//--------------------------getting the values for comm status------------------------------
	try{
		DecimalFormat twoDec=new DecimalFormat("#0.000");
		twoDec.setRoundingMode(RoundingMode.DOWN);
		List<Integer> ids=new ArrayList();
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String currentDate=sdf.format(new java.util.Date());
        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
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
				
				for(String sid:userHierarchyid.split(",")){
					ids.add(Integer.parseInt(sid));
				}
				master=masterdao.getTotalMasterDataBysdID(ids,level);
				
			}
		}else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			ids.add(Integer.parseInt(levelID));
			master=masterdao.getTotalMasterDataBysdID(ids,level);
		}
		
		GetPieGraph gpg=new GetPieGraph();
		List<String> commList=gpg.getLast1hrCommmetersStatus(currentDate);
		if(master.size()>0){
			List<String> meters_11kv_ag=new ArrayList();
			List<String> meters_11kv_nonag=new ArrayList();
			
			List<String> meters_33kv=new ArrayList();
			List<String> meters_comm_11kv_ag=new ArrayList();
			List<String> meters_comm_11kv_nonag=new ArrayList();
			
			List<String> meters_comm_33kv=new ArrayList();
			List<String> not_comm_meters=new ArrayList();
			
			for(TotalMasterdataBean tmb:master){
				if(tmb.getSupply_voltage().contains("11")){
					if(tmb.getLocation_category().equalsIgnoreCase("AG")) meters_11kv_ag.add(tmb.getMeter_sl_no());
					if(tmb.getLocation_category().equalsIgnoreCase("NON AG") || tmb.getLocation_category().equalsIgnoreCase("DL")) {
						meters_11kv_nonag.add(tmb.getMeter_sl_no());
					}
				}
				if(tmb.getSupply_voltage().contains("33")) meters_33kv.add(tmb.getMeter_sl_no());
			}
			System.out.println("11 AG----------------"+meters_11kv_ag.size());
			System.out.println("11 Non-AG----------------"+meters_11kv_nonag.size());
			System.out.println("33------------------"+meters_33kv.size());
			
			meters_comm_11kv_ag.addAll(meters_11kv_ag);
			meters_comm_11kv_nonag.addAll(meters_11kv_nonag);
			
			meters_comm_33kv.addAll(meters_33kv);
			not_comm_meters.addAll(meters_11kv_ag);
			not_comm_meters.addAll(meters_11kv_nonag);
			not_comm_meters.addAll(meters_33kv);
			
			meters_comm_11kv_ag.retainAll(commList);
			meters_comm_11kv_nonag.retainAll(commList);
			meters_comm_33kv.retainAll(commList);
			not_comm_meters.removeAll(commList);
			
			int tot_11kv_ag=meters_11kv_ag.size(),comm_11kv_ag=meters_comm_11kv_ag.size();
			int	tot_11kv_nonag=meters_11kv_nonag.size(),comm_11kv_nonag=meters_comm_11kv_nonag.size();
			int tot_33kv=meters_33kv.size(),comm_33kv=meters_comm_33kv.size();
			int not_comm=not_comm_meters.size();
			
			model.put("comm_11kv_ag", comm_11kv_ag);
			model.put("tot_11kv_ag", tot_11kv_ag);
			model.put("comm_11kv_nonag", comm_11kv_nonag);
			model.put("tot_11kv_nonag", tot_11kv_nonag);
			model.put("comm_33kv", comm_33kv);
			model.put("tot_33kv", tot_33kv);
			model.put("tot_comm", comm_11kv_ag+comm_11kv_nonag+comm_33kv);
			model.put("tot_notcomm", not_comm);
			model.put("tot_meters", tot_11kv_ag+tot_11kv_nonag+tot_33kv);
			
			
			model.put("commcount_11kv_ag", twoDec.format((Float.parseFloat(comm_11kv_ag+"")/tot_11kv_ag)));
			model.put("commcount_11kv_nonag", twoDec.format((Float.parseFloat(comm_11kv_nonag+"")/tot_11kv_nonag)));
			model.put("commcount_33kv", twoDec.format((Float.parseFloat(comm_33kv+"")/tot_33kv)));
			model.put("tot_commcount", twoDec.format((Float.parseFloat((comm_11kv_ag+comm_11kv_nonag+comm_33kv)+"")/(tot_11kv_ag+tot_11kv_nonag+tot_33kv))));


		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
 	return new ModelAndView("pominda/getLiveCommStatus", model);
    }
	
	@RequestMapping(value = "/getdayLiveCommStatus", method = RequestMethod.GET)
    public ModelAndView getdayLiveCommStatus(@RequestParam("level") String levelStr,@RequestParam("levelID") String levelID,
    		@RequestParam("date") String date,HttpSession session) throws Exception{

	Map<String, Object> model = new HashMap<String, Object>();
	List<TotalMasterdataBean> master=new ArrayList();
//--------------------------getting the values for comm status------------------------------
	try{
		DecimalFormat twoDec=new DecimalFormat("#0.000");
		twoDec.setRoundingMode(RoundingMode.DOWN);
		List<Integer> ids=new ArrayList();
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String currentDate=sdf.format(new java.util.Date());
        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
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
				
				for(String sid:userHierarchyid.split(",")){
					ids.add(Integer.parseInt(sid));
				}
				master=masterdao.getTotalMasterDataBysdID(ids,level);
				
			}
		}else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			ids.add(Integer.parseInt(levelID));
			master=masterdao.getTotalMasterDataBysdID(ids,level);
		}
		
		GetPieGraph gpg=new GetPieGraph();
		//List<String> commList=gpg.getLast1hrCommmetersStatus(currentDate);
		List<String> commList=gpg.getOnlyCommmetersStatus(currentDate);
		if(master.size()>0){
			List<String> meters_11kv_ag=new ArrayList();
			List<String> meters_11kv_nonag=new ArrayList();
			
			List<String> meters_33kv=new ArrayList();
			List<String> meters_comm_11kv_ag=new ArrayList();
			List<String> meters_comm_11kv_nonag=new ArrayList();
			
			List<String> meters_comm_33kv=new ArrayList();
			List<String> not_comm_meters=new ArrayList();
			
			for(TotalMasterdataBean tmb:master){
				if(tmb.getSupply_voltage().contains("11")){
					if(tmb.getLocation_category().equalsIgnoreCase("AG")) meters_11kv_ag.add(tmb.getMeter_sl_no());
					if(tmb.getLocation_category().equalsIgnoreCase("NON AG") || tmb.getLocation_category().equalsIgnoreCase("DL")) {
						meters_11kv_nonag.add(tmb.getMeter_sl_no());
					}
				}
				if(tmb.getSupply_voltage().contains("33")) meters_33kv.add(tmb.getMeter_sl_no());
			}
			System.out.println("11 AG----------------"+meters_11kv_ag.size());
			System.out.println("11 Non-AG----------------"+meters_11kv_nonag.size());
			System.out.println("33------------------"+meters_33kv.size());
			
			meters_comm_11kv_ag.addAll(meters_11kv_ag);
			meters_comm_11kv_nonag.addAll(meters_11kv_nonag);
			
			meters_comm_33kv.addAll(meters_33kv);
			not_comm_meters.addAll(meters_11kv_ag);
			not_comm_meters.addAll(meters_11kv_nonag);
			not_comm_meters.addAll(meters_33kv);
			
			meters_comm_11kv_ag.retainAll(commList);
			meters_comm_11kv_nonag.retainAll(commList);
			meters_comm_33kv.retainAll(commList);
			not_comm_meters.removeAll(commList);
			
			int tot_11kv_ag=meters_11kv_ag.size(),comm_11kv_ag=meters_comm_11kv_ag.size();
			int	tot_11kv_nonag=meters_11kv_nonag.size(),comm_11kv_nonag=meters_comm_11kv_nonag.size();
			int tot_33kv=meters_33kv.size(),comm_33kv=meters_comm_33kv.size();
			int not_comm=not_comm_meters.size();
			
			model.put("comm_11kv_ag", comm_11kv_ag);
			model.put("tot_11kv_ag", tot_11kv_ag);
			model.put("comm_11kv_nonag", comm_11kv_nonag);
			model.put("tot_11kv_nonag", tot_11kv_nonag);
			model.put("comm_33kv", comm_33kv);
			model.put("tot_33kv", tot_33kv);
			model.put("tot_comm", comm_11kv_ag+comm_11kv_nonag+comm_33kv);
			model.put("tot_notcomm", not_comm);
			model.put("tot_meters", tot_11kv_ag+tot_11kv_nonag+tot_33kv);
			
			
			model.put("commcount_11kv_ag", twoDec.format((Float.parseFloat(comm_11kv_ag+"")/tot_11kv_ag)));
			model.put("commcount_11kv_nonag", twoDec.format((Float.parseFloat(comm_11kv_nonag+"")/tot_11kv_nonag)));
			model.put("commcount_33kv", twoDec.format((Float.parseFloat(comm_33kv+"")/tot_33kv)));
			model.put("tot_commcount", twoDec.format((Float.parseFloat((comm_11kv_ag+comm_11kv_nonag+comm_33kv)+"")/(tot_11kv_ag+tot_11kv_nonag+tot_33kv))));


		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
 	return new ModelAndView("pominda/getDayLiveCommStatus", model);
    }
	
	@RequestMapping(value = "/loadCurve", method = RequestMethod.GET)
    public ModelAndView getloadCurve(@RequestParam("level") String levelStr,@RequestParam("levelID") String levelID,
    		@RequestParam("date") String date,HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd");
		String prevDate=new DateUtilities().substract_1_Day(sdf.format(sdf1.parse(date))+" 00:00:00");
        //String prevDate=sdf.format(new java.util.Date().getTime()-2*24*60*60*1000);
        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
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
			
			//model.put("values", ddao.getDashLoadCurveValues(prevDate,userHierarchyid,level));
			model.put("values", ddao.getDashLoadCurveValues_withMF(prevDate,userHierarchyid,level));
			model.put("date", prevDate);
		}
		else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			//model.put("values", ddao.getDashLoadCurveValues(prevDate,levelID,level));
			model.put("values", ddao.getDashLoadCurveValues_withMF(prevDate,levelID,level));
			model.put("date", prevDate);
		}
	}catch(Exception e){}
	return new ModelAndView("pominda/load_curve", model);
	
	}
	
	@RequestMapping(value = "/powerfails", method = RequestMethod.GET)
    public ModelAndView getpowerfails(@RequestParam("level") String levelStr,@RequestParam("levelID") String levelID,
    		HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String curMonth=sdf.format(new java.util.Date());
        curMonth=curMonth.substring(curMonth.indexOf("-"));
        //curMonth="-02-2021";
        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
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
			
			model.put("sustained", ddao.getDashSustainedPowerFailureValues(curMonth,userHierarchyid,level));
			model.put("transientValues", ddao.getDashTransientPowerFailureValues(curMonth,userHierarchyid,level));
			
			System.out.println("sus------"+ddao.getDashSustainedPowerFailureValues(curMonth,userHierarchyid,level));
			System.out.println("trans------"+ddao.getDashTransientPowerFailureValues(curMonth,userHierarchyid,level));
		}
		else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			model.put("sustained", ddao.getDashSustainedPowerFailureValues(curMonth,levelID,level));
			model.put("transientValues", ddao.getDashTransientPowerFailureValues(curMonth,levelID,level));
		}
		
		
	}catch(Exception e){}
	return new ModelAndView("pominda/power_failures", model);
	
	}
	
	@RequestMapping(value = "/circle_supply_hrs", method = RequestMethod.GET)
    public ModelAndView getcircle_supply_hrs(@RequestParam("level") String levelStr,@RequestParam("levelID") String levelID,
    		HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		List<TotalMasterdataBean> master=new ArrayList();
		List<TotalMasterdataBean> avail_master=new ArrayList();
		List<Integer> ids=new ArrayList();
		
		DecimalFormat twodec=new DecimalFormat("#0.00");
		
		String frmonth="-09-2021";
		SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
		frmonth=sdf1.format(new java.util.Date());
		frmonth=frmonth.substring(frmonth.indexOf("-")+1);
		  
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
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
				
				for(String sid:userHierarchyid.split(",")){
					ids.add(Integer.parseInt(sid));
				}
				master=masterdao.getTotalMasterDataBysdID(ids,level);
				
			}
		}else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			ids.add(Integer.parseInt(levelID));
			master=masterdao.getTotalMasterDataBysdID(ids,level);
		}
		
		System.out.println("master size -------------------------"+master.size());
		if(master.size()>0 ){
			
			String meters="";
			for(TotalMasterdataBean tmb:master){
				if(tmb.getSupply_voltage().contains("11"))
				   meters=meters+",'"+tmb.getMeter_sl_no()+"'";
			}
			meters=meters.substring(1);
			Map<String,Map<String,String>> map=fdao.getAVGSupplyHrs(frmonth,meters);
			
			Map<String,List<String>> ag_hlbList=new HashMap();
			Map<String,String> ag_circleMap=new HashMap();
			
			Map<String,List<String>> nonag_hlbList=new HashMap();
			Map<String,String> nonag_circleMap=new HashMap();
			
			for(TotalMasterdataBean tmb:master){
				if(map.containsKey(tmb.getMeter_sl_no())){
					Map<String,String> ag_valMap=map.get(tmb.getMeter_sl_no());
					Map<String,String> nonag_valMap=map.get(tmb.getMeter_sl_no());
					
					if(tmb.getLocation_category().equalsIgnoreCase("ag")){
					if(!ag_valMap.isEmpty()){
						//System.out.println("-------------------->>>>>>>>>>>>>>>"+valMap);
					if(ag_hlbList.containsKey(tmb.getCircle())){
						List<String> list=ag_hlbList.get(tmb.getCircle());
						if(Integer.parseInt(ag_valMap.get("32-00-0000").split(":")[0])>7 )
						  list.add(ag_valMap.get("32-00-0000"));
						ag_hlbList.put(tmb.getCircle(), list);
					}else{
						List<String> list=new ArrayList();
						if(Integer.parseInt(ag_valMap.get("32-00-0000").split(":")[0])>7){
							list.add(ag_valMap.get("32-00-0000"));
							ag_hlbList.put(tmb.getCircle(), list);
						}
					}
					}
					}//if ag 
					if(tmb.getLocation_category().equalsIgnoreCase("non ag")){
					if(!nonag_valMap.isEmpty()){
						//System.out.println("-------------------->>>>>>>>>>>>>>>"+valMap);
					if(nonag_hlbList.containsKey(tmb.getCircle())){
						List<String> list=nonag_hlbList.get(tmb.getCircle());
						if(Integer.parseInt(nonag_valMap.get("32-00-0000").split(":")[0])>22)
						 list.add(nonag_valMap.get("32-00-0000"));
						nonag_hlbList.put(tmb.getCircle(), list);
					}else{
						List<String> list=new ArrayList();
						if(Integer.parseInt(nonag_valMap.get("32-00-0000").split(":")[0])>22)
						  list.add(nonag_valMap.get("32-00-0000"));
						nonag_hlbList.put(tmb.getCircle(), list);
					}
					}
					}//non ag
				}//if map contains
			}//for
			
			//System.out.println("------------------------------>>>>>>>>>>>>>>>>>"+hlbList);
			DateUtilities du=new DateUtilities();
			int cirNo=0; long total_duration=0;
			for(Map.Entry em:ag_hlbList.entrySet()){
				List<String> vals=(List<String>)em.getValue();
				long duration=0;
				for(String s:vals){
					duration=duration+(Integer.parseInt(s.split(":")[0])*60*60*1000)+
							 (Integer.parseInt(s.split(":")[1])*60*1000)+
							 (Integer.parseInt(s.split(":")[2])*1000);
					//duration=duration*1000;
				}
				cirNo++;
				total_duration=total_duration+(duration/vals.size());
				ag_circleMap.put(em.getKey().toString(), du.parseTimeH(duration/vals.size()));
				
			}//for
			ag_circleMap.put("all", du.parseTimeH(total_duration/cirNo));
			 cirNo=0;  total_duration=0;
			for(Map.Entry em:nonag_hlbList.entrySet()){
				List<String> vals=(List<String>)em.getValue();
				long duration=0;
				for(String s:vals){
					duration=duration+(Integer.parseInt(s.split(":")[0])*60*60*1000)+
							 (Integer.parseInt(s.split(":")[1])*60*1000)+
							 (Integer.parseInt(s.split(":")[2])*1000);
					//duration=duration*1000;
				}
				cirNo++;
				total_duration=total_duration+(duration/vals.size());
				nonag_circleMap.put(em.getKey().toString(), du.parseTimeH(duration/vals.size()));
				
			}//for
			nonag_circleMap.put("all", du.parseTimeH(total_duration/cirNo));
			//System.out.println("------------------------------>>>>>>>>>>>>>>>>>"+circleMap);
			model.put("ag_circles", ag_circleMap);
			model.put("nonag_circles", nonag_circleMap);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return new ModelAndView("pominda/circle_supply_hrs", model);
	
	}
	
	@RequestMapping(value = "/circle_supply_hrs_daily", method = RequestMethod.GET)
    public ModelAndView getcircle_supply_hrs_daily(@RequestParam("level") String levelStr,@RequestParam("levelID") String levelID,
    		HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		List<TotalMasterdataBean> master=new ArrayList();
		List<TotalMasterdataBean> avail_master=new ArrayList();
		List<Integer> ids=new ArrayList();
		
		DecimalFormat twodec=new DecimalFormat("#0.00");
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
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
				
				for(String sid:userHierarchyid.split(",")){
					ids.add(Integer.parseInt(sid));
				}
				master=masterdao.getTotalMasterDataBysdID(ids,level);
				
			}
		}else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			ids.add(Integer.parseInt(levelID));
			master=masterdao.getTotalMasterDataBysdID(ids,level);
		}
		
		System.out.println("master size -------------------------"+master.size());
		if(master.size()>0 ){
			
			String meters="";
			for(TotalMasterdataBean tmb:master){
				if(tmb.getSupply_voltage().contains("11"))
				   meters=meters+",'"+tmb.getMeter_sl_no()+"'";
			}
			meters=meters.substring(1);
			Map<String,CircleavgSupplyWeeklyBean> clist=new HashMap();
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			DateUtilities du=new DateUtilities();
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			
			for(int i=0;i<=0;i++){
				String fdate=sdf.format(du.firstDayOfLastWeek(c, i).getTime());
				String tdate=sdf.format(du.firstDayOfLastWeek(c, i+1).getTime());
				if(i==0){
					fdate=sdf.format(new Date().getTime()-7*24*60*60*1000);
					tdate=sdf.format(c.getTime());
				}
				System.out.println(fdate+"\t"+tdate);
			    Map<String,Map<String,String>> map=fdao.getAVGSupplyHrs_weekly(fdate,tdate,meters);
//------------------------------------------------------------------------------------
			    Map<String,List<String>> ag_hlbList=new HashMap();
				Map<String,String> ag_circleMap=new HashMap();
				
				Map<String,List<String>> nonag_hlbList=new HashMap();
				Map<String,String> nonag_circleMap=new HashMap();
				
				for(TotalMasterdataBean tmb:master){
					if(map.containsKey(tmb.getMeter_sl_no())){
						Map<String,String> ag_valMap=map.get(tmb.getMeter_sl_no());
						Map<String,String> nonag_valMap=map.get(tmb.getMeter_sl_no());
						
						if(tmb.getLocation_category().equalsIgnoreCase("ag")){
						if(!ag_valMap.isEmpty()){
							//System.out.println("-------------------->>>>>>>>>>>>>>>"+valMap);
						if(ag_hlbList.containsKey(tmb.getCircle())){
							List<String> list=ag_hlbList.get(tmb.getCircle());
							if(Integer.parseInt(ag_valMap.get("32-00-0000").split(":")[0])>7 )
							  list.add(ag_valMap.get("32-00-0000"));
							ag_hlbList.put(tmb.getCircle(), list);
						}else{
							List<String> list=new ArrayList();
							if(Integer.parseInt(ag_valMap.get("32-00-0000").split(":")[0])>7){
								list.add(ag_valMap.get("32-00-0000"));
								ag_hlbList.put(tmb.getCircle(), list);
							}
						}
						}
						}//if ag 
						if(tmb.getLocation_category().equalsIgnoreCase("non ag")){
						if(!nonag_valMap.isEmpty()){
							//System.out.println("-------------------->>>>>>>>>>>>>>>"+valMap);
						if(nonag_hlbList.containsKey(tmb.getCircle())){
							List<String> list=nonag_hlbList.get(tmb.getCircle());
							if(Integer.parseInt(nonag_valMap.get("32-00-0000").split(":")[0])>22)
							 list.add(nonag_valMap.get("32-00-0000"));
							nonag_hlbList.put(tmb.getCircle(), list);
						}else{
							List<String> list=new ArrayList();
							if(Integer.parseInt(nonag_valMap.get("32-00-0000").split(":")[0])>22)
							  list.add(nonag_valMap.get("32-00-0000"));
							nonag_hlbList.put(tmb.getCircle(), list);
						}
						}
						}//non ag
					}//if map contains
				}//for
				for(Map.Entry em:ag_hlbList.entrySet()){
					List<String> vals=(List<String>)em.getValue();
					long duration=0;
					for(String s:vals){
						duration=duration+(Integer.parseInt(s.split(":")[0])*60*60*1000)+
								 (Integer.parseInt(s.split(":")[1])*60*1000)+
								 (Integer.parseInt(s.split(":")[2])*1000);
						//duration=duration*1000;
					}
					
					ag_circleMap.put(em.getKey().toString(), du.parseTimeH(duration/vals.size()));
					
				}//for
				
				 
				for(Map.Entry em:nonag_hlbList.entrySet()){
					List<String> vals=(List<String>)em.getValue();
					long duration=0;
					for(String s:vals){
						duration=duration+(Integer.parseInt(s.split(":")[0])*60*60*1000)+
								 (Integer.parseInt(s.split(":")[1])*60*1000)+
								 (Integer.parseInt(s.split(":")[2])*1000);
						//duration=duration*1000;
					}
					
					nonag_circleMap.put(em.getKey().toString(), du.parseTimeH(duration/vals.size()));
					
				}//for	
				
				for(Map.Entry em: nonag_circleMap.entrySet()){
					CircleavgSupplyWeeklyBean cb=null;
					if(clist.containsKey(em.getKey())){
						 cb=clist.get(em.getKey());
					}else{
						 cb=new CircleavgSupplyWeeklyBean();
						
					}
					cb.setCircle(em.getKey().toString());
					if(i==-3){
						model.put("week4", fdate+" to "+tdate);
						if(ag_circleMap.containsKey(em.getKey()))
						 cb.setWeek4_ag(ag_circleMap.get(em.getKey()));
						if(nonag_circleMap.containsKey(em.getKey()))
							 cb.setWeek4_nonag(nonag_circleMap.get(em.getKey()));
					}
					if(i==-2){
						model.put("week3", fdate+" to "+tdate);
						if(ag_circleMap.containsKey(em.getKey()))
						 cb.setWeek3_ag(ag_circleMap.get(em.getKey()));
						if(nonag_circleMap.containsKey(em.getKey()))
							 cb.setWeek3_nonag(nonag_circleMap.get(em.getKey()));
					}
					if(i==-1){
						model.put("week2", fdate+" to "+tdate);
						if(ag_circleMap.containsKey(em.getKey()))
						 cb.setWeek2_ag(ag_circleMap.get(em.getKey()));
						if(nonag_circleMap.containsKey(em.getKey()))
							 cb.setWeek2_nonag(nonag_circleMap.get(em.getKey()));
					}
					if(i==0){
						model.put("week1", fdate+" to "+tdate);
						if(ag_circleMap.containsKey(em.getKey()))
						 cb.setWeek1_ag(ag_circleMap.get(em.getKey()));
						if(nonag_circleMap.containsKey(em.getKey()))
							 cb.setWeek1_nonag(nonag_circleMap.get(em.getKey()));
					}
					
					clist.put(em.getKey().toString(), cb);
				}
//------------------------------------------------------------------------------------			    
			    
			}
			
			model.put("circles", clist);
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return new ModelAndView("pominda/circle_supply_hrs_daily", model);
	
	}

	@RequestMapping(value = "/circle_power_fails", method = RequestMethod.GET)
    public ModelAndView getCirclepowerfails(HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		
		model.put("map", ddao.getCirclewise_transientcounts());  //previous day counts
		
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return new ModelAndView("pominda/circle_powerfails", model);
	
	}
	
	@RequestMapping(value = "/supplyInfo", method = RequestMethod.GET)
    public ModelAndView getsupplyInfo(@RequestParam("level") String levelStr,@RequestParam("levelID") String levelID,
    		@RequestParam("date") String date,HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd");
		String prevDate=new DateUtilities().substract_1_Day(sdf.format(sdf1.parse(date))+" 00:00:00");
		
        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
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
			
			model.put("supply", ddao.getDashAg_supply_LS(prevDate,userHierarchyid,level));		
			model.put("ag_deviation", ddao.getDashAg_deviation_LS(prevDate,userHierarchyid,level));
			model.put("nonag_deviation", ddao.getDashNonag_deviation_LS(prevDate,userHierarchyid,level));
		}
		else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			model.put("supply", ddao.getDashAg_supply_LS(prevDate,levelID,level));		
			model.put("ag_deviation", ddao.getDashAg_deviation_LS(prevDate,levelID,level));
			model.put("nonag_deviation", ddao.getDashNonag_deviation_LS(prevDate,levelID,level));
		}
		
	}catch(Exception e){}
	return new ModelAndView("pominda/supplyInfo", model);
	
	}
	
	@RequestMapping(value = "/weekStatus", method = RequestMethod.GET)
    public ModelAndView getweekStatus(@RequestParam("level") String levelStr,@RequestParam("levelID") String levelID,
    		@RequestParam("date") String date,HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String curMonth=sdf.format(new java.util.Date());
        curMonth=curMonth.substring(curMonth.indexOf("-"));
        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
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
			
			model.put("values", ddao.getDashWeekStatusValues(userHierarchyid,level));
		}else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;			
			model.put("values", ddao.getDashWeekStatusValues(levelID,level));
		}
		//model.put("transientValues", ddao.getDashTransientPowerFailureValues(curMonth,userHierarchyid,level));
	}catch(Exception e){}
	return new ModelAndView("pominda/weekStatus", model);
	
	}
	
	@RequestMapping(value = "/signalGraph", method = RequestMethod.GET)
    public ModelAndView getsignalGraph(HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String curMonth=sdf.format(new java.util.Date());
        curMonth=curMonth.substring(curMonth.indexOf("-"));
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
		
		model.put("values", ddao.getDashSignalGraphValues(userHierarchyid,level));
		
	}catch(Exception e){}
	return new ModelAndView("pominda/signalGraph", model);
	
	}
	
	@RequestMapping(value = "/downloadSignalGraphValues", method = RequestMethod.GET)
    public void downloadSignalGraphValues(@RequestParam("type") String type,
    		HttpServletRequest req,HttpServletResponse res,HttpSession session) throws Exception{
    	Map<String, Object> model = new HashMap();
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
    		String result=ddao.downloadSignalGraphValues(userHierarchyid,level,type);
    		
    		res.setContentType("APPLICATION/OCTET-STREAM");   
            res.addHeader("Content-Disposition", "attachment; filename="+type+"_signal.xls");
            try
            {
                //Files.copy(file, res.getOutputStream());
            	res.getOutputStream().write(result.getBytes());
                res.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            
            }catch(Exception e){
    		
    	}
    	
	}
	
	@RequestMapping(value = "/11kvStatus", method = RequestMethod.GET)
    public ModelAndView get11kvStatus(@RequestParam("level") String levelStr,@RequestParam("levelID") String levelID,HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String curMonth=sdf.format(new java.util.Date());
        curMonth=curMonth.substring(curMonth.indexOf("-"));
        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
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
			
			model.put("overload", ddao.getDashOverLoad(userHierarchyid,level, 11));
			model.put("lowpf", ddao.getDashLowPF(userHierarchyid,level, 11));
			model.put("overvoltage", ddao.getDashOverunderVoltages(userHierarchyid,level, 11,"over"));
			model.put("undervoltage", ddao.getDashOverunderVoltages(userHierarchyid,level, 11,"under"));
		}
		else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			model.put("overload", ddao.getDashOverLoad(levelID,level, 11));
			model.put("lowpf", ddao.getDashLowPF(levelID,level, 11));
			model.put("overvoltage", ddao.getDashOverunderVoltages(levelID,level, 11,"over"));
			model.put("undervoltage", ddao.getDashOverunderVoltages(levelID,level, 11,"under"));
		}
		
	}catch(Exception e){}
	return new ModelAndView("pominda/11kvFeederStatus", model);
	
	}
	
	@RequestMapping(value = "/33kvStatus", method = RequestMethod.GET)
    public ModelAndView get33kvStatus(@RequestParam("level") String levelStr,@RequestParam("levelID") String levelID,HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		List<TotalMasterdataBean> master=new ArrayList();		
		List<Integer> ids=new ArrayList();
		DecimalFormat twoDec=new DecimalFormat("#0.00");
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String curMonth=sdf.format(new java.util.Date());
        curMonth=curMonth.substring(curMonth.indexOf("-"));
        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
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
				
				for(String sid:userHierarchyid.split(",")){
					ids.add(Integer.parseInt(sid));
				}
				master=masterdao.getTotalMasterDataBysdID(ids,level);
				
			}
			
			model.put("overload", "0");
			model.put("lowpf", ddao.getDashLowPF(userHierarchyid,level, 33));
			model.put("overvoltage", ddao.getDashOverunderVoltages(userHierarchyid,level, 33,"over"));
			model.put("undervoltage", ddao.getDashOverunderVoltages(userHierarchyid,level, 33,"under"));
			
			
		}
		else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			ids.add(Integer.parseInt(levelID));
			master=masterdao.getTotalMasterDataBysdID(ids,level);
			
			model.put("overload", "0");
			model.put("lowpf", ddao.getDashLowPF(levelID,level, 33));
			model.put("overvoltage", ddao.getDashOverunderVoltages(levelID,level, 33,"over"));
			model.put("undervoltage", ddao.getDashOverunderVoltages(levelID,level, 33,"under"));
						
		}
//----------------------------------------block for saidi and saifi---------------------------------------------		
		if(master.size()>0 ){
			
			List<String> meters=new ArrayList();
			for(TotalMasterdataBean tmb:master){
				if(tmb.getSupply_voltage().contains("11") && tmb.getLocation_category().contains("NON AG"))
					meters.add(tmb.getMeter_sl_no());
			}
			String frmonth=curMonth;
			Map<String,ReliabilityIndexBean> map=fdao.getReliabilityIndeces(frmonth,meters);
             
			System.out.println("saidisaifi------------------"+map.size());
			double saidi=0,saifi=0;
			int total_fdrs=0;
			for(Map.Entry em:map.entrySet()){
				ReliabilityIndexBean rib=(ReliabilityIndexBean)em.getValue();
				if(rib.getSaidi()!=null && rib.getSaifi()!=null){
					if(Double.parseDouble(rib.getSaidi())>0 && Double.parseDouble(rib.getSaifi())>0){
						total_fdrs=total_fdrs+1;
						saidi=saidi+Double.parseDouble(rib.getSaidi());
						saifi=saifi+Double.parseDouble(rib.getSaifi());
						
						//System.out.println(em.getKey()+"\t"+saidi+"\t"+saifi);
						//System.out.println(em.getKey()+"\t"+rib.getSaidi()+"\t"+rib.getSaifi());
					}else{
						//System.out.println(rib.getSaidi()+"\t"+rib.getSaifi());
					}
				}
			}
			
			System.out.println("total_fdrs------------------"+total_fdrs);

		model.put("saidi",new DateUtilities().parseTime_m(Long.parseLong(""+Math.round(saidi/total_fdrs))));
		model.put("saifi",twoDec.format(saifi/total_fdrs));
		
		}//if not null;
		
		//----------------------------------------block for saidi and saifi---------------------------------------------		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return new ModelAndView("pominda/33kvFeederStatus", model);
	
	}
	
	@RequestMapping(value = "/dashFeederStatusPopup", method = RequestMethod.GET)
    public ModelAndView getDashFeederStatusPopup(@RequestParam("level") String levelStr,@RequestParam("levelID") String levelID,
    		@RequestParam("voltageLevel") String voltageLevel,@RequestParam("type") String type,HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		List<TotalMasterdataBean> master=new ArrayList();
		List<TotalMasterdataBean> avail_master=new ArrayList();
		List<Integer> ids=new ArrayList();
		Map<String,FeederLiveStatus> map=new HashMap();
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String curMonth=sdf.format(new java.util.Date());
        curMonth=curMonth.substring(curMonth.indexOf("-"));
        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
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
			for(String s:userHierarchyids){
				ids.add(Integer.parseInt(s));	
			}
			master=masterdao.getTotalMasterDataBysdID(ids,level);
			
			if(type.equals("lowpf"))
			  map=ddao.getDashLowPFDetails(userHierarchyid, level, Integer.parseInt(voltageLevel));
			if(type.equals("overvoltage"))
			  map=ddao.getDashOverunderVoltagesDetails(userHierarchyid, level, Integer.parseInt(voltageLevel),"over");
			if(type.equals("undervoltage"))
			  map=ddao.getDashOverunderVoltagesDetails(userHierarchyid, level, Integer.parseInt(voltageLevel),"under");
			if(type.equals("overload"))
				map=ddao.getDashOverLoadDetails(userHierarchyid, level, Integer.parseInt(voltageLevel));
		}
		else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			ids.add(Integer.parseInt(levelID));
			master=masterdao.getTotalMasterDataBysdID(ids,level);
			
			if(type.equals("lowpf"))
		     map=ddao.getDashLowPFDetails(levelID, level, Integer.parseInt(voltageLevel));
			if(type.equals("overvoltage"))
			  map=ddao.getDashOverunderVoltagesDetails(levelID, level, Integer.parseInt(voltageLevel),"over");
			if(type.equals("undervoltage"))
			  map=ddao.getDashOverunderVoltagesDetails(levelID, level, Integer.parseInt(voltageLevel),"under");
			if(type.equals("overload"))
			  map=ddao.getDashOverLoadDetails(levelID, level, Integer.parseInt(voltageLevel));
		}
		if(map.size()>0){
			for(TotalMasterdataBean tmb:master){
				if(map.containsKey(tmb.getMeter_sl_no())) avail_master.add(tmb);
			}
		}
		model.put("master",avail_master);
		model.put("fls", map);
		
		String title="";
		if(type.equals("overload")) title=voltageLevel+"KV Overloaded Feeders";
		if(type.equals("lowpf")) title=voltageLevel+"KV Low PF Feeders";
		if(type.equals("overvoltage")) title=voltageLevel+"KV Over Voltage Feeders";
		if(type.equals("undervoltage")) title=voltageLevel+"KV Under Voltage Feeders";
		
		model.put("title", title );
	}catch(Exception e){
		e.printStackTrace();
	}
	return new ModelAndView("pominda/dashFeederStatusPopup", model);
	
	}
	
	@RequestMapping(value = "/dashSaidiSaifiPopup", method = RequestMethod.GET)
    public ModelAndView getdashSaidiSaifiPopup(@RequestParam("level") String levelStr,
    		@RequestParam("levelID") String levelID,HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		List<TotalMasterdataBean> master=new ArrayList();		
		List<Integer> ids=new ArrayList();		
        DecimalFormat twoDec=new DecimalFormat("#0.00");
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String curMonth=sdf.format(new java.util.Date());
        curMonth=curMonth.substring(curMonth.indexOf("-"));
        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
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
			for(String s:userHierarchyids){
				ids.add(Integer.parseInt(s));	
			}
			master=masterdao.getTotalMasterDataBysdID(ids,level);
			
			
		}
		else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			ids.add(Integer.parseInt(levelID));
			master=masterdao.getTotalMasterDataBysdID(ids,level);
			
		}
		//----------------------------------------block for saidi and saifi---------------------------------------------		
				if(master.size()>0 ){
					
					List<String> meters=new ArrayList();
					for(TotalMasterdataBean tmb:master){
						if(tmb.getSupply_voltage().contains("11") && tmb.getLocation_category().contains("NON AG"))
							meters.add(tmb.getMeter_sl_no());
					}
					String frmonth=curMonth;
					Map<String,ReliabilityIndexBean> map=fdao.getReliabilityIndeces(frmonth,meters);
		             
					System.out.println("saidisaifi pop up------------------"+map.size());
					/*double saidi=0,saifi=0;
					int total_fdrs=0;
					for(Map.Entry em:map.entrySet()){
						ReliabilityIndexBean rib=(ReliabilityIndexBean)em.getValue();
						if(rib.getSaidi()!=null && rib.getSaifi()!=null){
							if(Double.parseDouble(rib.getSaidi())>0 && Double.parseDouble(rib.getSaifi())>0){
								total_fdrs=total_fdrs+1;
								saidi=saidi+Double.parseDouble(rib.getSaidi());
								saifi=saifi+Double.parseDouble(rib.getSaifi());
								
								//System.out.println(em.getKey()+"\t"+saidi+"\t"+saifi);
								System.out.println(em.getKey()+"\t"+rib.getSaidi()+"\t"+rib.getSaifi());
							}else{
								//System.out.println(rib.getSaidi()+"\t"+rib.getSaifi());
							}
						}
					}//for
*/					
					Map<String,ReliabilityIndexBean> ssMap=new HashMap();
					Map<String,List<ReliabilityIndexBean>> ssMapList=new HashMap();
					
					for(TotalMasterdataBean tmb:master){
						if(map.containsKey(tmb.getMeter_sl_no())){
							ReliabilityIndexBean rib=(ReliabilityIndexBean)map.get(tmb.getMeter_sl_no());
							if(rib.getSaidi()!=null && rib.getSaifi()!=null){
							 if(Double.parseDouble(rib.getSaidi())>0 && Double.parseDouble(rib.getSaifi())>0){
								if(ssMapList.containsKey(tmb.getCircle())){
									List<ReliabilityIndexBean> list=ssMapList.get(tmb.getCircle());
									list.add(rib);
									ssMapList.put(tmb.getCircle(), list );
								}else{
									List<ReliabilityIndexBean> list=new ArrayList();
									list.add(rib);
									ssMapList.put(tmb.getCircle(), list);
								}
								//System.out.println(rib.getFeeder()+"\t"+rib.getSaidi()+"\t"+rib.getSaifi());
							}//if saidi>0 & saifi>0
							}//if saidi & saifi are not null
						}//if contains
					}//for
					
					for(Map.Entry em: ssMapList.entrySet()){
						double saidi=0,saifi=0;
						int total_fdrs=0;
						List<ReliabilityIndexBean> list=(List<ReliabilityIndexBean>)em.getValue();
						for(ReliabilityIndexBean rib:list){
							saidi=saidi+Double.parseDouble(rib.getSaidi());
							saifi=saifi+Double.parseDouble(rib.getSaifi());
						}
						ReliabilityIndexBean rb=new ReliabilityIndexBean();
						rb.setSaidi(new DateUtilities().parseTime_m(Long.parseLong(""+Math.round((saidi/list.size())))));
						rb.setSaifi(twoDec.format(saifi/list.size()));
						rb.setTot_consumers(""+list.size());
						ssMap.put(em.getKey().toString(), rb);
					}
				//System.out.println("ssMap---------------"+ssMap);
					model.put("ssMap", ssMap);
					model.put("levelStr", levelStr);
					model.put("levelID", levelID);
				}//if not null;
				
				//----------------------------------------block for saidi and saifi---------------------------------------------		
				
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return new ModelAndView("pominda/dashSaidiSaifiPopup", model);
	
	}
	
	@RequestMapping(value = "/downloadSaidiSaifi", method = RequestMethod.GET)
    public void downloadSaidiSaifi(@RequestParam("level") String levelStr,
    		@RequestParam("levelID") String levelID,
    		@RequestParam("location") String circle,
    		HttpServletResponse res,
    		HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		String datastr="";
		String tab="\t";
		String newline="\n";
		
		List<TotalMasterdataBean> master=new ArrayList();
		Map<String,TotalMasterdataBean> masterMap=new HashMap();
		List<Integer> ids=new ArrayList();		
        DecimalFormat twoDec=new DecimalFormat("#0.00");
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String curMonth=sdf.format(new java.util.Date());
        curMonth=curMonth.substring(curMonth.indexOf("-"));
        int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
        
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
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
			for(String s:userHierarchyids){
				ids.add(Integer.parseInt(s));	
			}
			master=masterdao.getTotalMasterDataBysdID(ids,level);
			
			
		}
		else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			ids.add(Integer.parseInt(levelID));
			master=masterdao.getTotalMasterDataBysdID(ids,level);
			
		}
		//----------------------------------------block for saidi and saifi---------------------------------------------		
				if(master.size()>0 ){
					
					List<String> meters=new ArrayList();
					for(TotalMasterdataBean tmb:master){
						if(tmb.getSupply_voltage().contains("11")){
							meters.add(tmb.getMeter_sl_no());
							masterMap.put(tmb.getMeter_sl_no(), tmb);
						}
						
					}
					String frmonth=curMonth;
					Map<String,ReliabilityIndexBean> map=fdao.getReliabilityIndeces(frmonth,meters);
		             
					System.out.println("saidisaifi------------------"+map.size());
					/*double saidi=0,saifi=0;
					int total_fdrs=0;
					for(Map.Entry em:map.entrySet()){
						ReliabilityIndexBean rib=(ReliabilityIndexBean)em.getValue();
						if(rib.getSaidi()!=null && rib.getSaifi()!=null){
							if(Double.parseDouble(rib.getSaidi())>0 && Double.parseDouble(rib.getSaifi())>0){
								total_fdrs=total_fdrs+1;
								saidi=saidi+Double.parseDouble(rib.getSaidi());
								saifi=saifi+Double.parseDouble(rib.getSaifi());
								
								//System.out.println(em.getKey()+"\t"+saidi+"\t"+saifi);
								System.out.println(em.getKey()+"\t"+rib.getSaidi()+"\t"+rib.getSaifi());
							}else{
								//System.out.println(rib.getSaidi()+"\t"+rib.getSaifi());
							}
						}
					}//for
*/					
					
					Map<String,List<ReliabilityIndexBean>> ssMapList=new HashMap();
					
					for(TotalMasterdataBean tmb:master){
						if(map.containsKey(tmb.getMeter_sl_no())){
							ReliabilityIndexBean rib=(ReliabilityIndexBean)map.get(tmb.getMeter_sl_no());
							if(rib.getSaidi()!=null && rib.getSaifi()!=null){
							 if(Double.parseDouble(rib.getSaidi())>0 && Double.parseDouble(rib.getSaifi())>0){
								if(ssMapList.containsKey(tmb.getCircle())){
									List<ReliabilityIndexBean> list=ssMapList.get(tmb.getCircle());
									list.add(rib);
									ssMapList.put(tmb.getCircle(), list );
								}else{
									List<ReliabilityIndexBean> list=new ArrayList();
									list.add(rib);
									ssMapList.put(tmb.getCircle(), list);
								}
							}//if saidi>0 & saifi>0
							}//if saidi & saifi are not null
						}//if contains
					}//for
					
					datastr="Circle"+tab+"Substation"+tab+"Feeder"+tab+"Meter"+tab+"Category"+tab+"SAIDI"+tab+"SAIFI"+newline;
					
					if(ssMapList.containsKey(circle)){
						List<ReliabilityIndexBean> list=(List<ReliabilityIndexBean>)ssMapList.get(circle);
						for(ReliabilityIndexBean rib:list){
							TotalMasterdataBean tmb=masterMap.get(rib.getFeeder());
							datastr=datastr+tmb.getCircle()+tab+tmb.getSs()+tab+tmb.getLocation_name()+tab+tmb.getMeter_sl_no()+tab+tmb.getLocation_category()+tab+rib.getSaidi()+tab+rib.getSaifi()+newline;
						}
					}
					
				
					
				}//if not null;
				
				//----------------------------------------block for saidi and saifi---------------------------------------------		
				try{
					String fileName="Saidi_saifi.xls";
					res.setContentType("application/vnd.ms-excel");   //for xls format(.xls)
		            res.addHeader("Content-Disposition", "attachment; filename="+fileName);
		            res.getOutputStream().write(datastr.getBytes());
		            res.getOutputStream().flush();
		           
				}catch(Exception e){
					
				}
		
	}catch(Exception e){
		e.printStackTrace();
	}	
	
	}
	
}
