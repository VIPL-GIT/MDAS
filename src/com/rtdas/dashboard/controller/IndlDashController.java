package com.rtdas.dashboard.controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.MeterService;
import com.rtdas.dashboard.dao.DashboardDaoMP;
import com.rtdas.dashboard.dao.IndustrialDahsboardDao;
import com.rtdas.feeder_reports.beans.AverageSupplyHrsBean;
import com.rtdas.feeder_reports.beans.ReliabilityIndexBean;
import com.rtdas.meterdata.beans.TotalMasterdataBean;
import com.rtdas.meterdata.models.FeederLiveStatus;
import com.rtdas.reports.dao.FeederReportsDao;
import com.rtdas.reports.dao.TotalMasterDAO;

import utils.DateUtilities;
import utils.GetPieGraph;

@Controller
@RequestMapping(value = "/Idl")
public class IndlDashController {

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
	@Autowired
	private IndustrialDahsboardDao idao;
	
	@RequestMapping(value = "/IdlHome", method = RequestMethod.GET)
    public ModelAndView getdashHome(@RequestParam("actionId") String actionId,HttpSession session) throws Exception{

	Map<String, Object> model = new HashMap<String, Object>();	
 	return new ModelAndView("Industrial/IdlHome", model);
 	
    }
	@RequestMapping(value = "/getIndlLatestInstant", method = RequestMethod.GET)
    public ModelAndView getIndlLatestInstant(HttpSession session) throws Exception{

	Map<String, Object> model = new HashMap<String, Object>();	
 	return new ModelAndView("Industrial/getIndlLatestInstant", model);
 	
    }
	
	@RequestMapping(value = "/getIndlCommStatus", method = RequestMethod.GET)
    public ModelAndView getIndlCommStatus(@RequestParam("level") String levelStr,@RequestParam("levelID") String levelID,
    		HttpSession session) throws Exception{

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
			
			List<String> meters_11kv_nonag=new ArrayList();
			List<String> tot_11kv_nonag=new ArrayList();
			
			
			for(TotalMasterdataBean tmb:master){
				
					if(tmb.getCategory()!=null &&  tmb.getCategory().equalsIgnoreCase("INDUSTRIAL")) {
						meters_11kv_nonag.add(tmb.getMeter_sl_no());
					}
				
			}
			
			System.out.println("11 Non-AG----------------"+meters_11kv_nonag.size());
			
			tot_11kv_nonag.addAll(commList);
			
			tot_11kv_nonag.retainAll(meters_11kv_nonag);
			
			
			model.put("comm_11kv_nonag", tot_11kv_nonag.size());
			model.put("tot_11kv_nonag", meters_11kv_nonag.size());
			
			
			
			
			model.put("commcount_11kv_nonag", twoDec.format((Float.parseFloat(tot_11kv_nonag.size()+"")/meters_11kv_nonag.size())));
			


		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
 	return new ModelAndView("Industrial/getLiveCommStatus", model);
    }
	
	@RequestMapping(value = "/getIndlLoadCurve", method = RequestMethod.GET)
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
			

			model.put("values", idao.getDashLoadCurveValues_withMF(prevDate,userHierarchyid,level));
			model.put("date", prevDate);
		}
		else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;

			model.put("values", idao.getDashLoadCurveValues_withMF(prevDate,levelID,level));
			model.put("date", prevDate);
		}
	}catch(Exception e){}
	return new ModelAndView("Industrial/load_curve", model);
	
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
			
			model.put("sustained", idao.getDashSustainedPowerFailureValues(curMonth,userHierarchyid,level));
			model.put("transientValues", idao.getDashTransientPowerFailureValues(curMonth,userHierarchyid,level));
			
			
		}
		else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			model.put("sustained", ddao.getDashSustainedPowerFailureValues(curMonth,levelID,level));
			model.put("transientValues", ddao.getDashTransientPowerFailureValues(curMonth,levelID,level));
		}
		
		
	}catch(Exception e){}
	return new ModelAndView("Industrial/power_failures", model);
	
	}
	@RequestMapping(value = "/indlStatus", method = RequestMethod.GET)
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
			
			model.put("overload", idao.getDashOverLoad(levelID,level, 11));			
			model.put("overvoltage", idao.getDashOverunderVoltages(userHierarchyid,level, 11,"over"));
			model.put("undervoltage", idao.getDashOverunderVoltages(userHierarchyid,level, 11,"under"));
			
			
		}
		else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			ids.add(Integer.parseInt(levelID));
			master=masterdao.getTotalMasterDataBysdID(ids,level);
			
			model.put("overload", idao.getDashOverLoad(levelID,level, 11));			
			model.put("overvoltage", idao.getDashOverunderVoltages(levelID,level, 11,"over"));
			model.put("undervoltage", idao.getDashOverunderVoltages(levelID,level, 11,"under"));
						
		}
//----------------------------------------block for saidi and saifi---------------------------------------------		
		if(master.size()>0 ){
			
			List<String> meters=new ArrayList();
			for(TotalMasterdataBean tmb:master){
				if(tmb.getCategory()!=null && tmb.getCategory().equalsIgnoreCase("INDUSTRIAL"))
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
	return new ModelAndView("Industrial/IndlFeederStatus", model);
	
	}
	@RequestMapping(value = "/getIndlAvgSupplyHrs")
	public ModelAndView getavg_supply_hrs(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> avail_master=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.00");
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=sdf.format(new java.util.Date());
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
			
			System.out.println(frmonth+"----------------------master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				String meters="";
				for(TotalMasterdataBean tmb:master){
					if(tmb.getCategory()!=null && tmb.getCategory().equalsIgnoreCase("INDUSTRIAL"))
					   meters=meters+",'"+tmb.getMeter_sl_no()+"'";
				}
				meters=meters.substring(1);
				Map<String,Map<String,String>> map=fdao.getAVGSupplyHrs(frmonth,meters);
		//System.out.println("----------------------------------"+map);		
				Map<String,AverageSupplyHrsBean> hlbList=new HashMap();
				
			for(TotalMasterdataBean tmb:master){
				AverageSupplyHrsBean eib=new AverageSupplyHrsBean();				
				eib.setHlevel_name(tmb.getLocation_name());
//----------------------logic to get the average supply
				if(map.containsKey(tmb.getMeter_sl_no())){
					eib.setMeterno(tmb.getMeter_sl_no());
					Map<String,String> valMap=map.get(tmb.getMeter_sl_no());
					
					for(Map.Entry em:valMap.entrySet()){
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==1) eib.setDsh_1(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==2) eib.setDsh_2(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==3) eib.setDsh_3(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==4) eib.setDsh_4(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==5) eib.setDsh_5(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==6) eib.setDsh_6(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==7) eib.setDsh_7(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==8) eib.setDsh_8(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==9) eib.setDsh_9(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==10) eib.setDsh_10(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==11) eib.setDsh_11(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==12) eib.setDsh_12(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==13) eib.setDsh_13(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==14) eib.setDsh_14(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==15) eib.setDsh_15(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==16) eib.setDsh_16(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==17) eib.setDsh_17(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==18) eib.setDsh_18(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==19) eib.setDsh_19(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==20) eib.setDsh_20(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==21) eib.setDsh_21(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==22) eib.setDsh_22(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==23) eib.setDsh_23(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==24) eib.setDsh_24(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==25) eib.setDsh_25(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==26) eib.setDsh_26(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==27) eib.setDsh_27(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==28) eib.setDsh_28(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==29) eib.setDsh_29(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==30) eib.setDsh_30(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==31) eib.setDsh_31(em.getValue().toString());
						
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==32) eib.setAvg_supply_hrs(em.getValue().toString());
					}
					
					
				   
					hlbList.put(tmb.getMeter_sl_no(),eib);
					avail_master.add(tmb);
				
				}//if contains
			}//for 
			
			
			model.put("hlbList", hlbList);
			model.put("master", avail_master);
			
			}//if not null;
			
			
		}catch(Exception e){			
		}
		
		return new ModelAndView("Industrial/avg_supply_hrs", model);
	}
//--------------------------------------------------------------------------------------
	@RequestMapping(value = "/indlFeederStatusPopup", method = RequestMethod.GET)
    public ModelAndView getDashFeederStatusPopup(@RequestParam("level") String levelStr,@RequestParam("levelID") String levelID,
    		@RequestParam("type") String type,HttpSession session) throws Exception{
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
			  map=idao.getDashLowPFDetails(userHierarchyid, level, 11);
			if(type.equals("overvoltage"))
			  map=idao.getDashOverunderVoltagesDetails(userHierarchyid, level, 11,"over");
			if(type.equals("undervoltage"))
			  map=idao.getDashOverunderVoltagesDetails(userHierarchyid, level, 11,"under");
			if(type.equals("overload"))
				map=idao.getDashOverLoadDetails(userHierarchyid, level, 11);
		}
		else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			ids.add(Integer.parseInt(levelID));
			master=masterdao.getTotalMasterDataBysdID(ids,level);
			
			if(type.equals("lowpf"))
		     map=idao.getDashLowPFDetails(levelID, level, 11);
			if(type.equals("overvoltage"))
			  map=idao.getDashOverunderVoltagesDetails(levelID, level, 11,"over");
			if(type.equals("undervoltage"))
			  map=idao.getDashOverunderVoltagesDetails(levelID, level, 11,"under");
			if(type.equals("overload"))
			  map=idao.getDashOverLoadDetails(levelID, level, 11);
		}
		if(map.size()>0){
			for(TotalMasterdataBean tmb:master){
				if(map.containsKey(tmb.getMeter_sl_no())) avail_master.add(tmb);
			}
		}
		model.put("master",avail_master);
		model.put("fls", map);
		
		String title="";
		if(type.equals("overload")) title="Industrial Overloaded Feeders";
		if(type.equals("lowpf")) title="Industrial Low PF Feeders";
		if(type.equals("overvoltage")) title="Industrial Over Voltage Feeders";
		if(type.equals("undervoltage")) title="Industrial Under Voltage Feeders";
		
		model.put("title", title );
	}catch(Exception e){
		e.printStackTrace();
	}
	return new ModelAndView("Industrial/indlFeederStatusPopup", model);
	
	}
	
	@RequestMapping(value = "/getFeederTrippingDetails", method = RequestMethod.GET)
    public ModelAndView getFeederTrippingDetails(@RequestParam("meter") String meter,HttpSession session) throws Exception{
	Map<String, Object> model = new HashMap<String, Object>();
	try{
		
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        String curMonth=sdf.format(new java.util.Date());
        curMonth=curMonth.substring(curMonth.indexOf("-"));
        Map<String,String> map=idao.getFeeerTrippingDetails(meter);
        model.put("map", map);
	}catch(Exception e){
		e.printStackTrace();
	}
	return new ModelAndView("Industrial/getFeederTrippingDetails", model);
	
	}	
}
