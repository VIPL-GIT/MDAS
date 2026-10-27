package com.rtdas.reports.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.Meter;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.MeterService;
import com.rtdas.dashboard.beans.SinglePhaseFeedersBean;
import com.rtdas.feeder_reports.beans.AnalyticsDemandForecasting;
import com.rtdas.feeder_reports.beans.AverageSupplyHrsBean;
import com.rtdas.feeder_reports.beans.BusbarLossesBean;
import com.rtdas.feeder_reports.beans.CommSummaryBean;
import com.rtdas.feeder_reports.beans.DailySupplyHrsBean;
import com.rtdas.feeder_reports.beans.EnergyInputBean;
import com.rtdas.feeder_reports.beans.EnergysavingsBean;
import com.rtdas.feeder_reports.beans.HourlyLoadBean;
import com.rtdas.feeder_reports.beans.HourlyLoadLiveBean;
import com.rtdas.feeder_reports.beans.LPDataBean;
import com.rtdas.feeder_reports.beans.LoadCurveBean;
import com.rtdas.feeder_reports.beans.MonthlyPeakLoadBean;
import com.rtdas.feeder_reports.beans.Oluvovpf;
import com.rtdas.feeder_reports.beans.PowerFailuresBean;
import com.rtdas.feeder_reports.beans.ReliabilityIndexBean;
import com.rtdas.meterdata.beans.TotalMasterdataBean;
import com.rtdas.reports.dao.FeederReportsDao;
import com.rtdas.reports.dao.TotalMasterDAO;

import utils.DateUtilities;

@Controller
@RequestMapping(value = "/fdr")
public class FeederReportsController {
	
	@Autowired
	private HierarchyTitleService tService;
	@Autowired
	private HierarchyService hservice;
	@Autowired
	private HierarchyTitleService titleService;
	@Autowired	
	private MeterService meterService;
	@Autowired
	private TotalMasterDAO tmdao;
	@Autowired
	private FeederReportsDao fdao;
	@Autowired
	private TotalMasterDAO masterdao;
	
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView addUserPage(@RequestParam Map<String, String> reqParam,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try{
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
	
    		List<Meter> metersList=meterService.listMetersByLocation(ssIds, 0); //0 to get all the meters
    	    model.put("meters",metersList);
//---------------------------------------------    	    
			List<String> menus=Arrays.asList(reqParam.get("menus").split(","));
			
			System.out.println("----------------"+menus.get(0));
		  model.put("menus",menus );
		}catch(Exception e){
			
		}
		return new ModelAndView("feeder_reports/feeder_reports_home", model);
	}
	
	@RequestMapping(value = "/energy_input", method = RequestMethod.POST)
	public ModelAndView getenergy_input(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> avail_master=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.00");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=reqParam.get("frmonth");
			
			frmonth=frmonth.replace("/", "-");
			
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
					//if(tmb.getSupply_voltage().contains("11"))
					   meters=meters+",'"+tmb.getMeter_sl_no()+"'";
				}
				meters=meters.substring(1);
				Map<String,EnergyInputBean> map=fdao.getEnergyInputDetails(frmonth,meters);
	
				Map<String,EnergyInputBean> hlbList=new HashMap();
				
			for(TotalMasterdataBean tmb:master){
				EnergyInputBean eib=new EnergyInputBean();				
				eib.setHlevel_name(tmb.getLocation_name());
//----------------------logic to get the hourly load details
				
				if(map.containsKey(tmb.getMeter_sl_no())){
					double mf=tmb.getMf()==null?1.0:tmb.getMf();
					
					EnergyInputBean eb=(EnergyInputBean)map.get(tmb.getMeter_sl_no());
					
					eib.setMeterno(tmb.getMeter_sl_no());
					//kwh initial
					if(eb.getKwh_initial()==null || eb.getKwh_initial().isEmpty()){
						eib.setKwh_initial(eb.getKwh_initial());	
					}else{
						eib.setKwh_initial(eb.getKwh_initial()==null?"":twodec.format(Double.parseDouble(eb.getKwh_initial())*1));
					}
					//kvah initial
					if(eb.getKvah_initial()==null || eb.getKvah_initial().isEmpty()){
						eib.setKvah_initial(eb.getKvah_initial());	
					}else{
						eib.setKvah_initial(eb.getKvah_initial()==null?"":twodec.format(Double.parseDouble(eb.getKvah_initial())*1));
					}
					//kwh final
					if(eb.getKwh_final()==null || eb.getKwh_final().isEmpty()){
						eib.setKwh_final(eb.getKwh_final());	
					}else{
						eib.setKwh_final(eb.getKwh_final()==null?"":twodec.format(Double.parseDouble(eb.getKwh_final())*1));
					}
					//kvah final
					if(eb.getKvah_final()==null || eb.getKvah_final().isEmpty()){
						eib.setKvah_final(eb.getKvah_final());	
					}else{
						eib.setKvah_final(eb.getKvah_final()==null?"":twodec.format(Double.parseDouble(eb.getKvah_final())*1));
					}
				   
								    
				    
				    eib.setKwh_consumption(eb.getKwh_consumption()==null?eb.getKwh_consumption():twodec.format(Double.parseDouble(eb.getKwh_consumption())*mf));
				    eib.setKvah_consumption(eb.getKvah_consumption()==null?eb.getKvah_consumption():twodec.format(Double.parseDouble(eb.getKvah_consumption())*mf));
				    
				    //MD kw
				    if(eb.getMd_kw()==null || eb.getMd_kw().isEmpty()){
						eib.setMd_kw(eb.getMd_kw());	
					}else{
						eib.setMd_kw(eb.getMd_kw()==null?"":twodec.format(Double.parseDouble(eb.getMd_kw())*mf));
					}
				    //MD kva
				    if(eb.getMd_kva()==null || eb.getMd_kva().isEmpty()){
						eib.setMd_kva(eb.getMd_kva());	
					}else{
						eib.setMd_kva(eb.getMd_kva()==null?"":twodec.format(Double.parseDouble(eb.getMd_kva())*mf));
					}
				   
				   
				    eib.setMd_kw_occdate(eb.getMd_kw_occdate());
				    eib.setMd_kva_occdate(eb.getMd_kva_occdate());
					hlbList.put(tmb.getMeter_sl_no(),eib);
					avail_master.add(tmb);
				
				}//if contains
			}//for 
			

			model.put("hlbList", hlbList);
			model.put("master", avail_master);
			model.put("level", levelStr);
			
			}//if not null;
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/energy_input", model);
	}
	
	@RequestMapping(value = "/energy_input_daily", method = RequestMethod.POST)
	public ModelAndView getenergy_input_daily(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> avail_master=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.00");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
            String frmonth=reqParam.get("frmonth");
			
			frmonth=frmonth.replace("/", "-");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			
			
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
					//if(tmb.getSupply_voltage().contains("11"))
					   meters=meters+",'"+tmb.getMeter_sl_no()+"'";
				}
				meters=meters.substring(1);
				Map<String,List<String>> map=fdao.getEnergyInputDetails_month(frmonth,meters);
	
				Map<String,EnergyInputBean> hlbList=new HashMap();
				
			for(TotalMasterdataBean tmb:master){
				
				if(map.containsKey(tmb.getMeter_sl_no())){
					
					avail_master.add(tmb);
				
				}//if contains
			}//for 
			

			model.put("map", map);
			model.put("master", avail_master);
			
			}//if not null;
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/energy_input_daily", model);
	}
	@RequestMapping(value = "/energy_input_manual", method = RequestMethod.POST)
	public ModelAndView getenergy_input_manual(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> avail_master=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.00");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
            String frmonth=reqParam.get("frmonth");
			
			frmonth=frmonth.replace("/", "-");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			
			
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
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){
					//if(tmb.getSupply_voltage().contains("11"))
					   meters.add(tmb.getMeter_sl_no());
				}
				
				Map<String,EnergyInputBean> map=fdao.getEnergyInputDetails_manual(frmonth,meters);
	
				Map<String,EnergyInputBean> hlbList=new HashMap();
				
			for(TotalMasterdataBean tmb:master){
				
				if(map.containsKey(tmb.getMeter_sl_no())){
					
					avail_master.add(tmb);
				
				}//if contains
			}//for 
			

			model.put("map", map);
			model.put("master", avail_master);
			
			}//if not null;
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/energy_input_manual", model);
	}
	@RequestMapping(value = "/monthlyEAPopup")
	public ModelAndView getmonthlyEAPopup(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();		
		try{
			String meter=reqParam.get("meter");
			String frmonth=reqParam.get("month");
			frmonth=frmonth.replaceAll("/", "-");
			model.put("meter", meter);
			model.put("month", frmonth);
			List<String> meters=new ArrayList();
			meters.add(meter);
			Map<String,EnergyInputBean> map=fdao.getEnergyInputDetails_manual(frmonth,meters);
			
			model.put("vals", map);
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/monthlyEAPopup", model);
	}
	
	@RequestMapping(value = "/monthlyEAPopupAction")
	@ResponseBody
	public String getmonthlyEAPopupAction(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		String result="";
		try{
			String meter=reqParam.get("meter");
			String frmonth=reqParam.get("month");
			String initial=reqParam.get("initial");
			String finalVal=reqParam.get("final");
			String asu=reqParam.get("asu");
			String mf=reqParam.get("mf");
			
			frmonth=frmonth.replaceAll("/", "-");
			result=fdao.updateMonthlyEAValues(meter,frmonth,initial,finalVal,asu,mf);
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value = "/hourly_load", method = RequestMethod.POST)
	public ModelAndView gethourly_load(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> avail_master=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.00");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frdate=reqParam.get("frdate");
			String type=reqParam.get("type");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			frdate=sdf1.format(sdf.parse(frdate));
			
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
			
			//System.out.println("master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				String meters="";
				
				for(TotalMasterdataBean tmb:master){
					if(tmb.getSupply_voltage().contains("11"))
					   meters=meters+",'"+tmb.getMeter_sl_no()+"'";
				
				}
				meters=meters.substring(1);
				Map<String,Map<String,Double>> map=fdao.getHourlyLoadValues_byType(frdate,meters,type);
		//System.out.println("----------------------------------"+map);		
				Map<String,HourlyLoadBean> hlbList=new HashMap();
				
			for(TotalMasterdataBean tmb:master){
				HourlyLoadBean eib=new HourlyLoadBean();				
				eib.setHlevel_name(tmb.getLocation_name());
//----------------------logic to get the hourly load details
				if(map.containsKey(tmb.getMeter_sl_no())){
					
				Map<String,Double> hrmap=map.get(tmb.getMeter_sl_no());
				double maxVal=Collections.max(hrmap.values());
				//System.out.println(tmb.getMeter_sl_no()+"\t"+maxVal);
				if(maxVal>0.000){
					avail_master.add(tmb);
					if(hrmap.containsKey("00")) eib.setHr1(twodec.format(hrmap.get("00")));
					if(hrmap.containsKey("01")) eib.setHr2(twodec.format(hrmap.get("01")));
					if(hrmap.containsKey("02")) eib.setHr3(twodec.format(hrmap.get("02")));
					if(hrmap.containsKey("03")) eib.setHr4(twodec.format(hrmap.get("03")));
					if(hrmap.containsKey("04")) eib.setHr5(twodec.format(hrmap.get("04")));
					if(hrmap.containsKey("05")) eib.setHr6(twodec.format(hrmap.get("05")));
					if(hrmap.containsKey("06")) eib.setHr7(twodec.format(hrmap.get("06")));
					if(hrmap.containsKey("07")) eib.setHr8(twodec.format(hrmap.get("07")));
					if(hrmap.containsKey("08")) eib.setHr9(twodec.format(hrmap.get("08")));
					if(hrmap.containsKey("09")) eib.setHr10(twodec.format(hrmap.get("09")));
					if(hrmap.containsKey("01")) eib.setHr11(twodec.format(hrmap.get("01")));
					if(hrmap.containsKey("11")) eib.setHr12(twodec.format(hrmap.get("11")));
					if(hrmap.containsKey("12")) eib.setHr13(twodec.format(hrmap.get("12")));
					if(hrmap.containsKey("13")) eib.setHr14(twodec.format(hrmap.get("13")));
					if(hrmap.containsKey("14")) eib.setHr15(twodec.format(hrmap.get("14")));
					if(hrmap.containsKey("15")) eib.setHr16(twodec.format(hrmap.get("15")));
					if(hrmap.containsKey("16")) eib.setHr17(twodec.format(hrmap.get("16")));
					if(hrmap.containsKey("17")) eib.setHr18(twodec.format(hrmap.get("17")));
					if(hrmap.containsKey("18")) eib.setHr19(twodec.format(hrmap.get("18")));
					if(hrmap.containsKey("19")) eib.setHr20(twodec.format(hrmap.get("19")));
					if(hrmap.containsKey("20")) eib.setHr21(twodec.format(hrmap.get("20")));
					if(hrmap.containsKey("21")) eib.setHr22(twodec.format(hrmap.get("21")));
					if(hrmap.containsKey("22")) eib.setHr23(twodec.format(hrmap.get("22")));
					if(hrmap.containsKey("23")) eib.setHr24(twodec.format(hrmap.get("23")));
	
	//------------------------------------------------------				
					
					hlbList.put(tmb.getMeter_sl_no(),eib);
				}
				}//if contains
			}//for 
			
			//System.out.println("-------------"+hlbList.get("8180149").getHr1());
			model.put("hlbList", hlbList);
			model.put("master", avail_master);
			model.put("type", type);
			
			String title="Hourly Load (In Amperes)";
			if(type.equals("2")) title="Hourly Load (KVAh)";
			if(type.equals("3")) title="Hourly Load (PF)";
			model.put("title", title);
			
			}//if not null;
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/hourly_load", model);
	}
	
	@RequestMapping(value = "/hourly_load_live", method = RequestMethod.POST)
	public ModelAndView gethourly_load_live(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> avail_master=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.00");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frdate=reqParam.get("frdate");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			frdate=sdf1.format(sdf.parse(frdate));
			
			
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
			
			//System.out.println("master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){
					if(tmb.getSupply_voltage().contains("11"))
						meters.add(tmb.getMeter_sl_no());
				}
				
				Map<String,HourlyLoadLiveBean> map=fdao.getHourlyLoadValues_live(meters,frdate);
		//System.out.println("----------------------------------"+map);		
				Map<String,HourlyLoadBean> hlbList=new HashMap();
				
			for(TotalMasterdataBean tmb:master){
				if(map.containsKey(tmb.getMeter_sl_no()))
					avail_master.add(tmb);

			}//for 
			
			//System.out.println("-------------"+hlbList.get("8180149").getHr1());
			model.put("hlbList", map);
			model.put("master", avail_master);
			
			
			
			
			}//if not null;
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/hourly_load_live", model);
	}
	
	@RequestMapping(value = "/divisions_hourly_load_live", method = RequestMethod.POST)
	public ModelAndView getDivisionshourly_load_live(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();		
		try{
			
			DecimalFormat twodec=new DecimalFormat("#0.00");
			String frdate=reqParam.get("frdate");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			frdate=sdf1.format(sdf.parse(frdate));
			
			List<HourlyLoadLiveBean> map=fdao.getDivisionsHourlyLoadValues_live(frdate);
		    model.put("hlbList", map);
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/disivions_hourly_load_live", model);
	}
	
	public ModelAndView gethourly_load_bkp(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();		
		try{
			DecimalFormat twodec=new DecimalFormat("#0.00");
			int hlevel=Integer.parseInt(reqParam.get("hlevel")); // starts from 0 , 1=circle
			String frdate=reqParam.get("frdate");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			frdate=sdf1.format(sdf.parse(frdate));
			
			List<HourlyLoadBean> hlbList=new ArrayList();
			int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
			List<HierarchyTitle> titleList=getTitleList(clientId);
			
			if(hlevel!=titleList.size()){
			   String h_type_name=titleList.get(hlevel).getHierarchy_title();
			   model.put("h_type_name", h_type_name);
			}
			List<Hierarchy> hlist=null;
			if(hlevel==1){
				int h_type_id=titleList.get(hlevel).getHierarchy_type_id();
				hlist=hservice.getHierarchyByType(h_type_id);
			}else{
				int hlevelid=Integer.parseInt(reqParam.get("hlevelid"));
				hlist=hservice.getHierarchyByParentId(hlevelid);
				
				List<Object[]> bcd=hservice.listHierarchyById(hlevel, hlevelid);
				System.out.println("----------->>"+bcd);
				model.put("bcd", bcd);  //bread crumb details
				
			}
			if(hlist!=null && hlevel!=titleList.size()){
				
				//Map<String,Double> map=fdao.getHourlyLoadValues_currents(frdate, 1, 1);
			for(Hierarchy h:hlist){
				HourlyLoadBean eib=new HourlyLoadBean();
				eib.setHlevel(""+(hlevel+1));
				eib.setHlevel_id(""+h.getHierarchy_id());
				eib.setHlevel_name(h.getHierarchy_name());
//----------------------logic to get the hourly load details 
				Map<String,Double> map=fdao.getHourlyLoadValues_currents_bkp(frdate, h.getHierarchy_id(), hlevel+1);
				if(map.containsKey("00")) eib.setHr1(twodec.format(map.get("00")));
				if(map.containsKey("01")) eib.setHr2(twodec.format(map.get("01")));
				if(map.containsKey("02")) eib.setHr3(twodec.format(map.get("02")));
				if(map.containsKey("03")) eib.setHr4(twodec.format(map.get("03")));
				if(map.containsKey("04")) eib.setHr5(twodec.format(map.get("04")));
				if(map.containsKey("05")) eib.setHr6(twodec.format(map.get("05")));
				if(map.containsKey("06")) eib.setHr7(twodec.format(map.get("06")));
				if(map.containsKey("07")) eib.setHr8(twodec.format(map.get("07")));
				if(map.containsKey("08")) eib.setHr9(twodec.format(map.get("08")));
				if(map.containsKey("09")) eib.setHr10(twodec.format(map.get("09")));
				if(map.containsKey("01")) eib.setHr11(twodec.format(map.get("01")));
				if(map.containsKey("11")) eib.setHr12(twodec.format(map.get("11")));
				if(map.containsKey("12")) eib.setHr13(twodec.format(map.get("12")));
				if(map.containsKey("13")) eib.setHr14(twodec.format(map.get("13")));
				if(map.containsKey("14")) eib.setHr15(twodec.format(map.get("14")));
				if(map.containsKey("15")) eib.setHr16(twodec.format(map.get("15")));
				if(map.containsKey("16")) eib.setHr17(twodec.format(map.get("16")));
				if(map.containsKey("17")) eib.setHr18(twodec.format(map.get("17")));
				if(map.containsKey("18")) eib.setHr19(twodec.format(map.get("18")));
				if(map.containsKey("19")) eib.setHr20(twodec.format(map.get("19")));
				if(map.containsKey("20")) eib.setHr21(twodec.format(map.get("20")));
				if(map.containsKey("21")) eib.setHr22(twodec.format(map.get("21")));
				if(map.containsKey("22")) eib.setHr23(twodec.format(map.get("22")));
				if(map.containsKey("23")) eib.setHr24(twodec.format(map.get("23")));

//------------------------------------------------------				
				
				hlbList.add(eib);
			}//for
			}//if not null;
			if(hlevel==titleList.size()){      //while clicked on sub station
				int hlevelid=Integer.parseInt(reqParam.get("hlevelid"));
				List<TotalMasterdataBean> meters=tmdao.getTotalMasterDataByssID(hlevelid);
				
				if(!meters.isEmpty()){
				Map<String,Meter> mfmap=tmdao.getMeterMFMap(meters);
				
				for(TotalMasterdataBean tmb:meters){
					Meter mt=mfmap.containsKey(tmb.getMeter_sl_no())?mfmap.get(tmb.getMeter_sl_no()):null;
					HourlyLoadBean eib=new HourlyLoadBean();
					eib.setFeeder(tmb.getLocation_name());
					eib.setVoltage(tmb.getSupply_voltage());
					eib.setCategory(tmb.getLocation_category());
					eib.setMeterno(tmb.getMeter_sl_no());
					
					//----------------------logic to get the details 
					
					//------------------------------------------------------					
					
					hlbList.add(eib);
				}
				}//if
			}
			
			//System.out.println("EIB list "+hlbList);
			model.put("hlbList", hlbList);
			
			model.put("level", hlevel);
			model.put("titleSize", titleList.size());
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/hourly_load", model);
	}
	
	@RequestMapping(value = "/monthly_peak_load", method = RequestMethod.POST)
	public ModelAndView getmonthly_peak_load(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> avail_master=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.00");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=reqParam.get("frmonth");
			
			
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
				List<String> metersList=new ArrayList();
				for(TotalMasterdataBean tmb:master){
					if(tmb.getSupply_voltage().contains("11")){
					   meters=meters+",'"+tmb.getMeter_sl_no()+"'";
					   metersList.add(tmb.getMeter_sl_no());
					}
				}
				meters=meters.substring(1);
				Map<String,Map<String,String>> map=fdao.getMonthlyPeakLoad(frmonth,metersList);
		//System.out.println("----------------------------------"+map);		
				Map<String,MonthlyPeakLoadBean> hlbList=new HashMap();
				
			for(TotalMasterdataBean tmb:master){
				MonthlyPeakLoadBean eib=new MonthlyPeakLoadBean();				
				eib.setHlevel_name(tmb.getLocation_name());
//----------------------logic to get the average supply
				if(map.containsKey(tmb.getMeter_sl_no())){
					eib.setMeterno(tmb.getMeter_sl_no());
					Map<String,String> valMap=map.get(tmb.getMeter_sl_no());
					
					for(Map.Entry em:valMap.entrySet()){
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==1) eib.setDpl_1(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==2) eib.setDpl_2(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==3) eib.setDpl_3(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==4) eib.setDpl_4(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==5) eib.setDpl_5(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==6) eib.setDpl_6(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==7) eib.setDpl_7(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==8) eib.setDpl_8(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==9) eib.setDpl_9(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==10) eib.setDpl_10(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==11) eib.setDpl_11(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==12) eib.setDpl_12(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==13) eib.setDpl_13(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==14) eib.setDpl_14(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==15) eib.setDpl_15(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==16) eib.setDpl_16(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==17) eib.setDpl_17(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==18) eib.setDpl_18(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==19) eib.setDpl_19(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==20) eib.setDpl_20(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==21) eib.setDpl_21(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==22) eib.setDpl_22(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==23) eib.setDpl_23(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==24) eib.setDpl_24(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==25) eib.setDpl_25(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==26) eib.setDpl_26(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==27) eib.setDpl_27(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==28) eib.setDpl_28(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==29) eib.setDpl_29(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==30) eib.setDpl_30(em.getValue().toString());
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==31) eib.setDpl_31(em.getValue().toString());						
						if(Integer.parseInt(em.getKey().toString().split("-")[0])==32) eib.setMonthly_peakLoad(em.getValue().toString());
					}
					
					
				   
					hlbList.put(tmb.getMeter_sl_no(),eib);
					avail_master.add(tmb);
				
				}//if contains
			}//for 
			
			
			model.put("hlbList", hlbList);
			model.put("master", avail_master);
			
			}//if not null;
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/monthly_peak_load", model);
	}
	@RequestMapping(value = "/daily_supply_hrs", method = RequestMethod.POST)
	public ModelAndView getdaily_supploy_hrs(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> avail_master=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.00");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			System.out.println(levelStr+" levelID  "+levelID);
			String frdate=reqParam.get("frdate");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			frdate=sdf1.format(sdf.parse(frdate));
			
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
				Map<String,DailySupplyHrsBean> map=fdao.getSupplyHrs_bean(frdate,meters);
		//System.out.println("----------------------------------"+map);		
				Map<String,DailySupplyHrsBean> hlbList=new HashMap();
				
			for(TotalMasterdataBean tmb:master){
				if(map.containsKey(tmb.getMeter_sl_no())){				
				   
					hlbList.put(tmb.getMeter_sl_no(),map.get(tmb.getMeter_sl_no()));
					avail_master.add(tmb);
				
				}//if contains
			}//for 
			
			//System.out.println("-------------"+hlbList.get("8180149").getHr1());
			model.put("hlbList", hlbList);
			model.put("master", avail_master);
			
			}//if not null;
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/daily_supply_hrs", model);
	}
	@RequestMapping(value = "/avg_supply_hrs", method = RequestMethod.POST)
	public ModelAndView getavg_supply_hrs(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> avail_master=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.00");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=reqParam.get("frmonth");
			
			
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
		
		return new ModelAndView("feeder_reports/avg_supply_hrs", model);
	}
	
	@RequestMapping(value = "/comm_summary", method = RequestMethod.POST)
	public ModelAndView getcomm_summary(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<CommSummaryBean> csList=new ArrayList();
			String frdate=reqParam.get("frdate");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			frdate=sdf1.format(sdf.parse(frdate));
			DecimalFormat twodec=new DecimalFormat("#0.00");
			
			System.out.println(reqParam.get("hlevel")+"\t"+reqParam.get("hlevelid"));
			
			int hlevel=Integer.parseInt(reqParam.get("hlevel")); // starts from 0 , 1=circle
			
			int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
			List<HierarchyTitle> titleList=getTitleList(clientId);
			
			if(hlevel!=titleList.size()){
			   String h_type_name=titleList.get(hlevel).getHierarchy_title();
			   model.put("h_type_name", h_type_name);
			}
			List<Hierarchy> hlist=null;
			if(hlevel==1){
				int h_type_id=titleList.get(hlevel).getHierarchy_type_id();
				hlist=hservice.getHierarchyByType(h_type_id);
			}else{
				int hlevelid=Integer.parseInt(reqParam.get("hlevelid"));
				hlist=hservice.getHierarchyByParentId(hlevelid);
				
				List<Object[]> bcd=hservice.listHierarchyById(hlevel, hlevelid);
				System.out.println("----------->>"+bcd);
				model.put("bcd", bcd);  //bread crumb details
				
			}
			if(hlist!=null && hlevel!=titleList.size()){
			for(Hierarchy h:hlist){
				CommSummaryBean eib=new CommSummaryBean();
				eib.setHlevel(""+(hlevel+1));
				eib.setHlevel_id(""+h.getHierarchy_id());
				eib.setHlevel_name(h.getHierarchy_name());
//----------------------logic to get the details 
				CommSummaryBean vcb=fdao.getCommSummary(h.getHierarchy_name(),hlevel,frdate);
//------------------------------------------------------				
				eib.setComm_points(vcb.getComm_status());
				eib.setMcf(vcb.getMcf());
				eib.setModem_fail(vcb.getModem_fail());
				eib.setNotComm(vcb.getNotComm());
				eib.setCommissioning_to_be_done(vcb.getCommissioning_to_be_done());
				eib.setiNc_to_be_done(vcb.getiNc_to_be_done());
				eib.setTot_points(vcb.getTot_points());
				if(vcb.getComm_status()!=null && Integer.parseInt(vcb.getComm_status())>0){
					eib.setComm_percent(twodec.format((Double.parseDouble(vcb.getComm_status())/Double.parseDouble(vcb.getTot_points())*100)));
					eib.setNot_comm_percent(twodec.format(100-Double.parseDouble(eib.getComm_percent())));
				}
				
				csList.add(eib);
			}//for
			}//if not null;
			if(hlevel==titleList.size()){      //while clicked on sub station
				int hlevelid=Integer.parseInt(reqParam.get("hlevelid"));
				List<TotalMasterdataBean> meters=tmdao.getTotalMasterDataByssID(hlevelid);
				
				if(!meters.isEmpty()){
				Map<String,Meter> mfmap=tmdao.getMeterMFMap(meters);
				
				for(TotalMasterdataBean tmb:meters){
					Meter mt=mfmap.containsKey(tmb.getMeter_sl_no())?mfmap.get(tmb.getMeter_sl_no()):null;
					CommSummaryBean eib=new CommSummaryBean();
					eib.setFeeder(tmb.getLocation_name());
					eib.setVoltage(tmb.getSupply_voltage());
					eib.setCategory(tmb.getLocation_category());
					eib.setMeterno(tmb.getMeter_sl_no());
					
					//----------------------logic to get the details 
					
					//------------------------------------------------------					
					
					csList.add(eib);
				}
				}//if
			}
			
			//System.out.println("EIB list "+hlbList);
			model.put("csList", csList);
			
			model.put("level", hlevel);
			model.put("titleSize", titleList.size());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/comm_summary", model);
	}
	
	@RequestMapping(value = "/comm_summary_datewise", method = RequestMethod.POST)
	public ModelAndView getcomm_summary_datewise(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			Map<String,Map<String,String>> csMap=new HashMap();
			List<String> dates=new ArrayList();
			String frfromdate=reqParam.get("frfromdate");
			String frtodate=reqParam.get("frtodate");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			
			frfromdate=sdf1.format(sdf.parse(frfromdate));
			frtodate=sdf1.format(sdf.parse(frtodate));
			while(!frfromdate.equals(frtodate)){
				dates.add(frfromdate);
				frfromdate=new DateUtilities().add_Oneday(frfromdate+" 00:00:00");
			}
			dates.add(frtodate);
			Collections.reverse(dates);
			//System.out.println("dates----------------"+dates);
			DecimalFormat twodec=new DecimalFormat("#0.00");
			
			System.out.println(reqParam.get("hlevel")+"\t"+reqParam.get("hlevelid"));
			
			int hlevel=Integer.parseInt(reqParam.get("hlevel")); // starts from 0 , 1=circle
			
			int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
			List<HierarchyTitle> titleList=getTitleList(clientId);
			
			if(hlevel!=titleList.size()){
			   String h_type_name=titleList.get(hlevel).getHierarchy_title();
			   model.put("h_type_name", h_type_name);
			}
			List<Hierarchy> hlist=null;
			if(hlevel==1){
				int h_type_id=titleList.get(hlevel).getHierarchy_type_id();
				hlist=hservice.getHierarchyByType(h_type_id);
			}else{
				int hlevelid=Integer.parseInt(reqParam.get("hlevelid"));
				hlist=hservice.getHierarchyByParentId(hlevelid);
				
				List<Object[]> bcd=hservice.listHierarchyById(hlevel, hlevelid);
				System.out.println("----------->>"+bcd);
				model.put("bcd", bcd);  //bread crumb details
				
			}
			String datesStr="";
			for(String s:dates){
				datesStr=datesStr+",'"+s+"'";
			}
			datesStr=datesStr.substring(1);
			Map<String,String> circlewiseTotal=fdao.getCircleWiseTotalMeters_DeviceSummary(frtodate);
			System.out.println("--------circlewiseTotal---------------"+circlewiseTotal.size());
			
			if(hlist!=null && hlevel!=titleList.size()){
				for(Hierarchy h:hlist){
					String total=circlewiseTotal.containsKey(h.getHierarchy_name())?circlewiseTotal.get(h.getHierarchy_name()):"";
					csMap.put(h.getHierarchy_name()+"@"+total, fdao.getCommSummaryDateWise(h.getHierarchy_name(),datesStr));
					
				}//for
				}//if not null;
			System.out.println("--------map---------------"+csMap.size());
			model.put("map", csMap);
			model.put("dates", dates);
			model.put("level", hlevel);
			model.put("titleSize", titleList.size());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/comm_summary_datewise", model);
	}
	
	@RequestMapping(value = "/feeder_wise_comm", method = RequestMethod.POST)
	public ModelAndView getfeeder_wise_comm(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			
			
		}catch(Exception e){			
		}
		
		return new ModelAndView("feeder_reports/feeder_wise_comm", model);
	}
	
	@RequestMapping(value = "/reliability_index", method = RequestMethod.POST)
	public ModelAndView getreliability_index(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> avail_master=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.00");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=reqParam.get("frmonth");
			
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
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){
					if(tmb.getSupply_voltage().contains("11") && tmb.getLocation_category().contains("NON AG"))
						meters.add(tmb.getMeter_sl_no());
				}
				
				Map<String,ReliabilityIndexBean> map=fdao.getReliabilityIndeces(frmonth,meters);
	
				Map<String,ReliabilityIndexBean> hlbList=new HashMap();
				
			for(TotalMasterdataBean tmb:master){
				ReliabilityIndexBean eib=new ReliabilityIndexBean();				
				eib.setHlevel_name(tmb.getLocation_name());
//----------------------logic to get the hourly load details
				
				if(map.containsKey(tmb.getMeter_sl_no())){
					ReliabilityIndexBean ri=map.get(tmb.getMeter_sl_no());
					eib.setTot_consumers(ri.getTot_consumers());
					eib.setTot_interrupts(ri.getTot_interrupts());
					eib.setTot_i_duration(ri.getTot_i_duration());
					eib.setSaidi(ri.getSaidi());
					eib.setSaifi(ri.getSaifi());
					eib.setCaidi(ri.getCaidi());
					eib.setMaifi(ri.getMaifi());
					
				   
					hlbList.put(tmb.getMeter_sl_no(),eib);
					avail_master.add(tmb);
				
				}//if contains
			}//for 
			

			model.put("hlbList", hlbList);
			model.put("master", avail_master);
			
			}//if not null;
			
			
		}catch(Exception e){			
		}
		
		return new ModelAndView("feeder_reports/reliability_index", model);
	}
	
	@RequestMapping(value = "/bus_bar_losses", method = RequestMethod.POST)
	public ModelAndView getBusbarLosses(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=reqParam.get("frmonth");
			String type=reqParam.get("type");
			String title="";
			if(type.contains("2")) title="Circle wise Losses";
			if(type.contains("3")) title="Division wise Losses";
			if(type.contains("6")) title="Substation wise Losses";
			
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
					master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
					
				}
			}else{
				int level=Integer.parseInt(levelStr.split("_")[1])-1;
				ids.add(Integer.parseInt(levelID));
				master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
			}
			
			System.out.println("master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){					
						meters.add(tmb.getMeter_sl_no());
				}
				Map<String,BusbarLossesBean> map=fdao.getBusbarLossesBean(frmonth,meters);
				
				Map<String,BusbarLossesBean> hlbList=new HashMap();	
				
				for(TotalMasterdataBean tmb:master){
					
					if(map.containsKey(tmb.getMeter_sl_no())){
						BusbarLossesBean ri=map.get(tmb.getMeter_sl_no());
						if(ri.getInitial_kwh()!=null && ri.getFinal_kwh()!=null){
							if(ri.getInitial_kwh().length()>1 && ri.getFinal_kwh().length()>1){
							  ri.setDiff(twodec.format(Double.parseDouble(ri.getFinal_kwh())-Double.parseDouble(ri.getInitial_kwh())));
							  ri.setDiff_with_mf((twodec.format(((Double.parseDouble(ri.getFinal_kwh())-Double.parseDouble(ri.getInitial_kwh()))*tmb.getMf())/1000)));
							}
						}
						
					   
						hlbList.put(tmb.getMeter_sl_no(),ri);
						
					
					}//if contains
				}//for 	
			model.put("hlbList", hlbList);
			model.put("master", master);
			//model.put("level", levelStr);
			model.put("level", type);
			model.put("title", title);
			}//if not null;
			
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/bus_bar_losses", model);
	}
	
	@RequestMapping(value = "/load_curve", method = RequestMethod.POST)
	public ModelAndView getLoadCurveValues(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> availmaster=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frdate=reqParam.get("frdate");
			
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
					master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
					
				}
			}else{
				int level=Integer.parseInt(levelStr.split("_")[1])-1;
				ids.add(Integer.parseInt(levelID));
				master=masterdao.getTotalMasterDataBysdID(ids,level);
			}
			
			//System.out.println("master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){	
					 if(tmb.getSupply_voltage().contains("11")){
						  meters.add(tmb.getMeter_sl_no());
						  availmaster.add(tmb);
					 }
				}
				Map<String,LoadCurveBean> map=fdao.getLoadCurveValues(frdate,meters);
				
				Map<String,LoadCurveBean> hlbList=new HashMap();	
				
				for(TotalMasterdataBean tmb:master){
					
					if(map.containsKey(tmb.getMeter_sl_no())){
						
						LoadCurveBean ri=map.get(tmb.getMeter_sl_no());
						hlbList.put(tmb.getMeter_sl_no(),ri);
					
					}//if contains
				}//for 	
			model.put("map", hlbList);
			model.put("master", availmaster);
			
			}//if not null;
			
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/load_curve", model);
	}
	
	@RequestMapping(value = "/load_curve_graph", method = RequestMethod.POST)	
	public ModelAndView getLoadCurveGraphValues(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> availmaster=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String meter=reqParam.get("meter");
			String level=reqParam.get("level");
			String levelID=reqParam.get("levelID");
System.out.println("Meter "+meter+"\t"+level+"\t"+levelID);			
			if(!meter.equals("0")){
			  Map<String,LoadCurveBean> map=fdao.getLoadCurveGraphValues(meter);
			  model.put("vals", map);
			  model.put("type", "meter");
			}else{
				model.put("type", "group"); //hierarchy
				Map<String,LoadCurveBean> map=fdao.getLoadCurveGraphValues_hierarchy(level,levelID);
				 model.put("vals", map);
			}
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/load_curve_graph", model);
	}
	
	@RequestMapping(value = "/oluvovpf", method = RequestMethod.POST)
	public ModelAndView getoluvovpf(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			Map<String,TotalMasterdataBean> availmaster=new HashMap();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=reqParam.get("frmonth");
			String type=reqParam.get("type");
			
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
					master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
					
				}
			}else{
				int level=Integer.parseInt(levelStr.split("_")[1])-1;
				ids.add(Integer.parseInt(levelID));
				master=masterdao.getTotalMasterDataBysdID(ids,level);
			}
			
			//System.out.println("master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){	
					
					availmaster.put(tmb.getMeter_sl_no(), tmb);
					meters.add(tmb.getMeter_sl_no());
						 
				}
				List<Oluvovpf> map=fdao.getOluvovpfValues(frmonth,meters,type);
				
					
			
			String title="";
			if(type.equalsIgnoreCase("ol")) title="Overloaded Feeders";
			if(type.equalsIgnoreCase("uv")) title="Under Voltage Feeders";
			if(type.equalsIgnoreCase("ov")) title="Over Voltage Feeders";
			if(type.equalsIgnoreCase("pf")) title="Low PF Feeders";
			
			model.put("title", title);
			model.put("type", type);
			model.put("map", map);
			model.put("master", availmaster);
			
			}//if not null;
			
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/oluvovpf", model);
	}
	
	@RequestMapping(value = "/power_failures", method = RequestMethod.POST)
	public ModelAndView getpower_failures(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> availmaster=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frfromdate=reqParam.get("frfromdate");
			String frtodate=reqParam.get("frtodate");
			
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
					master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
					
				}
			}else{
				int level=Integer.parseInt(levelStr.split("_")[1])-1;
				ids.add(Integer.parseInt(levelID));
				master=masterdao.getTotalMasterDataBysdID(ids,level);
			}
			
			//System.out.println("master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){	
					 if(tmb.getSupply_voltage().contains("11") && tmb.getLocation_category().contains("NON AG")){
						  meters.add(tmb.getMeter_sl_no());
						  availmaster.add(tmb);
					 }
				}
				Map<String,PowerFailuresBean> map=fdao.getPowerFailures(frfromdate,frtodate,meters);
				
				Map<String,PowerFailuresBean> hlbList=new HashMap();	
				
				for(TotalMasterdataBean tmb:master){
					
					if(map.containsKey(tmb.getMeter_sl_no())){
						
						PowerFailuresBean ri=map.get(tmb.getMeter_sl_no());
						hlbList.put(tmb.getMeter_sl_no(),ri);
					
					}//if contains
				}//for 	
			model.put("map", hlbList);
			model.put("master", availmaster);
			
			}//if not null;
			
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/power_failures", model);
	}
	
	@RequestMapping(value = "/events_summary", method = RequestMethod.POST)
	public ModelAndView getEvents_summary(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			Map<String,TotalMasterdataBean> availmaster=new HashMap();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=reqParam.get("frmonth");
			
			
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
				if (userHierarchyids!=null  ){    //for company level user
					
					for(String sid:userHierarchyid.split(",")){
						ids.add(Integer.parseInt(sid));
					}
					master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
					
				}
			}else{
				int level=Integer.parseInt(levelStr.split("_")[1])-1;
				ids.add(Integer.parseInt(levelID));
				master=masterdao.getTotalMasterDataBysdID(ids,level);
			}
			
			//System.out.println("master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){	
					
					
					meters.add(tmb.getMeter_sl_no());
						 
				}
				Map<String,Integer> map=fdao.getEventsSummary(frmonth,meters);
				
					
			
			
			model.put("map", map);
			
			
			}//if not null;
			
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/events_summary", model);
	}
	
	@RequestMapping(value = "/events_summary_meterEvents", method = RequestMethod.POST)
	public ModelAndView getEvents_summary_meterEvents(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
						
			model.put("meterevents", fdao.getMonthlyMeterEvents());
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/events_summary_meterEvents", model);
	}
	@RequestMapping(value = "/load_factor_screen", method = RequestMethod.POST)
	public ModelAndView getLoad_factor_screen(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		return new ModelAndView("feeder_reports/loadfactor/load_factor_screen", model);
	}
	@RequestMapping(value = "/load_factor_action", method = RequestMethod.POST)
	public ModelAndView getLoad_factor_action(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		String viewpage="";
		try{
			String type=reqParam.get("type");
			String rtype=reqParam.get("rtype");
			String year=reqParam.get("year");
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String meter=reqParam.get("meter");
			
			//System.out.println("----->>>>>>>>>>>>>>>>>"+type+"\t"+rtype+"\t"+year+"\t"+levelStr+"\t"+levelID+"\t"+meter);
			
			if(rtype.equals("table")) viewpage="feeder_reports/loadfactor/lf_table";
			if(rtype.equals("graph")) viewpage="feeder_reports/loadfactor/lf_graph";
			List<String> meters=new ArrayList();
			
		if(meter==null || meter.equals("0")){	
//----------------------------------------------------------------------------------------------------------
			int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
			String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
			List<TotalMasterdataBean> master=getMeters(levelStr,levelID,userHierarchyid,clientId );
//----------------------------------------------------------------------------------------------------------			
			if(master.size()!=0){
				for(TotalMasterdataBean tmb:master){
					meters.add(tmb.getMeter_sl_no());
				}
			}
		}//if not meter
		else
			meters.add(meter);
			model.put("lf", fdao.getLoadFactorValues(type,year,meters));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView(viewpage, model);
	}
	@RequestMapping(value = "/loadFactorPopup")
	public ModelAndView getloadFactorPopup(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		String viewpage="";
		try{
			String month=reqParam.get("month");
			String type=reqParam.get("type");
			String year=reqParam.get("year");
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String meter=reqParam.get("meter");
			
			List<String> meters=new ArrayList();
			
		if(meter==null || meter.equals("0")){	
//----------------------------------------------------------------------------------------------------------
			int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
			String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
			List<TotalMasterdataBean> master=getMeters(levelStr,levelID,userHierarchyid,clientId );
//----------------------------------------------------------------------------------------------------------			
			if(master.size()!=0){
				for(TotalMasterdataBean tmb:master){
					meters.add(tmb.getMeter_sl_no());
				}
			}
		}//if not meter
		else
			meters.add(meter);
			model.put("lf", fdao.getLoadFactorPopupValues(type,month,year,meters));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/loadfactor/lf_popup", model);
	}	
	
	@RequestMapping(value = "/consumption_screen", method = RequestMethod.POST)
	public ModelAndView getConsumption_screen(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		return new ModelAndView("feeder_reports/zeroconsumption/consumption_screen", model);
	}
	@RequestMapping(value = "/zero_consumption_action", method = RequestMethod.POST)
	public ModelAndView getZero_consumption_action(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		String viewpage="";
		try{
			String type=reqParam.get("type");
			String rtype=reqParam.get("rtype");
			String year=reqParam.get("year");
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String meter=reqParam.get("meter");
			
			if(rtype.equals("table")) {
				viewpage="feeder_reports/zeroconsumption/zc_table";
				if(type.contains("tod"))
					viewpage="feeder_reports/zeroconsumption/tod_table";	
			}
			if(rtype.equals("graph")) {
				viewpage="feeder_reports/zeroconsumption/zc_graph";
				if(type.contains("tod"))
					viewpage="feeder_reports/zeroconsumption/tod_graph";
			}
			List<String> meters=new ArrayList();
			
           if(meter==null || meter.equals("0")){
//----------------------------------------------------------------------------------------------------------
			
			int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
			String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
			List<TotalMasterdataBean> master=getMeters(levelStr,levelID,userHierarchyid,clientId );
//----------------------------------------------------------------------------------------------------------			
			if(master.size()!=0){
				for(TotalMasterdataBean tmb:master){
					meters.add(tmb.getMeter_sl_no());
				}
			}
           }//if not meter
           else
   			meters.add(meter);
           
           if(type.contains("tod")){
        	   model.put("zc", fdao.getTODConsumptionValues(type,year,meters));
        	   model.put("type", type);
           }
           else        	   
			   model.put("zc", fdao.getZeroConsumptionValues(type,year,meters));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView(viewpage, model);
	}
	@RequestMapping(value = "/zeroConsuumptionPopup")
	public ModelAndView getZeroConsuumptionPopup(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		String viewpage="";
		try{
			String month=reqParam.get("month");
			String type=reqParam.get("type");
			String year=reqParam.get("year");
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String meter=reqParam.get("meter");
			
			List<String> meters=new ArrayList();
			String title="Zero Consumption";
			if(type.equals("nc")) title="Negative Consumption";
			if(type.equals("hc")) title="High Consumption";
			if(type.equals("lc")) title="Low Consumption";
			if(type.equals("pf")) title="Power Factor";
			if(type.equals("cle1006m")) title="Consumption <100 ( last 6 months)";
			
		if(meter==null || meter.equals("0")){	
//----------------------------------------------------------------------------------------------------------
			int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
			String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
			List<TotalMasterdataBean> master=getMeters(levelStr,levelID,userHierarchyid,clientId );
//----------------------------------------------------------------------------------------------------------			
			if(master.size()!=0){
				for(TotalMasterdataBean tmb:master){
					meters.add(tmb.getMeter_sl_no());
				}
			}
		}//if not meter
		else
			meters.add(meter);
			model.put("zc", fdao.getZeroConsumptionPopupValues(type,month,year,meters));
			model.put("title", title);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/zeroconsumption/zc_popup", model);
	}
	@RequestMapping(value = "/events_summary_meterEventsAction", method = RequestMethod.POST)
	public ModelAndView getEvents_summary_meterEventsAction(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			String meter=reqParam.get("meter");
			String year=reqParam.get("year");
			String event=reqParam.get("event");
			
			model.put("meterevents", fdao.getMonthlyMeterEventsAction(meter,year,event));
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/events_summary_meterEventsAction", model);
	}
	
	@RequestMapping(value = "/events_summary_graph", method = RequestMethod.POST)
	public ModelAndView getEvents_summary_graph(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			Map<String,TotalMasterdataBean> availmaster=new HashMap();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=reqParam.get("frmonth");
			
			
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
				if (userHierarchyids!=null  ){    //for company level user
					
					for(String sid:userHierarchyid.split(",")){
						ids.add(Integer.parseInt(sid));
					}
					master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
					
				}
			}else{
				int level=Integer.parseInt(levelStr.split("_")[1])-1;
				ids.add(Integer.parseInt(levelID));
				master=masterdao.getTotalMasterDataBysdID(ids,level);
			}
			
			//System.out.println("master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){	
					
					
					meters.add(tmb.getMeter_sl_no());
						 
				}
				Map<String,Integer> map=fdao.getEventsSummary(frmonth,meters);
			model.put("map", map);
			}//if not null;
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/events_summary_graph", model);
	}
	@RequestMapping(value = "/events_summaryDetails")
	public ModelAndView getEvents_summaryDetails(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			Map<String,TotalMasterdataBean> availmaster=new HashMap();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=reqParam.get("frmonth");
			String etype=reqParam.get("type");
			
			
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
				if (userHierarchyids!=null  ){    //for company level user
					
					for(String sid:userHierarchyid.split(",")){
						ids.add(Integer.parseInt(sid));
					}
					master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
					
				}
			}else{
				int level=Integer.parseInt(levelStr.split("_")[1])-1;
				ids.add(Integer.parseInt(levelID));
				master=masterdao.getTotalMasterDataBysdID(ids,level);
			}
			
			//System.out.println("master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				
				for(TotalMasterdataBean tmb:master){
					meters.add(tmb.getMeter_sl_no());						 
				}
				List<String> list=fdao.getEventsSummaryDetails(frmonth,meters,etype);
				
					
			
			
			model.put("map", list);
			
			
			}//if not null;
			
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/events_summary_details", model);
	}
	
	@RequestMapping(value = "/loadprofile_summary")
	public ModelAndView getloadprofile_summary(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> availmaster=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=reqParam.get("frmonth");
			
			
			
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
				if (userHierarchyids!=null  ){    //for company level user
					
					for(String sid:userHierarchyid.split(",")){
						ids.add(Integer.parseInt(sid));
					}
					master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
					
				}
			}else{
				int level=Integer.parseInt(levelStr.split("_")[1])-1;
				ids.add(Integer.parseInt(levelID));
				master=masterdao.getTotalMasterDataBysdID(ids,level);
			}
			
			//System.out.println("master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				
				List<LPDataBean> list=fdao.getLoadProfileSummaryDetails(frmonth);
				Map<String,LPDataBean> map=new HashMap();
				for(LPDataBean lp:list){
					map.put(lp.getMeter_serial_no(), lp);
				}
				
				for(TotalMasterdataBean tmb:master){
					if(map.containsKey(tmb.getMeter_sl_no()))
						availmaster.add(tmb);
				}
					
			
			
				model.put("map", map);
				model.put("master", availmaster);
			
			
			}//if not null;
			
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/load_profile_summary", model);
	}
	
	@RequestMapping(value = "/singlePhaseSupply", method = RequestMethod.POST)
	public ModelAndView getsinglePhaseSupply(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> availmaster=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frdate=reqParam.get("frdate");
			
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
					master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
					
				}
			}else{
				int level=Integer.parseInt(levelStr.split("_")[1])-1;
				ids.add(Integer.parseInt(levelID));
				master=masterdao.getTotalMasterDataBysdID(ids,level);
			}
			
			System.out.println("master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){	
					 if(tmb.getLocation_group()!=null && tmb.getLocation_group().equals("mix")){
						  meters.add(tmb.getMeter_sl_no());
						  
					 }
				}
				Map<String,SinglePhaseFeedersBean> loadMap=fdao.getSinglePhaseSupplyFeeders_freports(meters, frdate);
				
				for(TotalMasterdataBean tmb:master){
					if(loadMap.containsKey(tmb.getMeter_sl_no()))
						availmaster.add(tmb);
				}
				
				model.put("master", availmaster);
				model.put("load", loadMap);
			
			}//if not null;
			
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/singlePhaseSupply", model);
	}
	
	@RequestMapping(value = "/singlePhaseSupply_monthly", method = RequestMethod.POST)
	public ModelAndView getsinglePhaseSupply_monthly(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> availmaster=new ArrayList();
			List<Integer> ids=new ArrayList();
			List<String> dates=new ArrayList();
			DateUtilities du=new DateUtilities();
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=reqParam.get("frmonth");
			frmonth=frmonth.replace("/", "-");			
			String fdate="01-"+frmonth;
			String ldate=du.getLastDayOfTheMonth("01-"+frmonth);
			while(!fdate.equals(ldate)){
				dates.add(fdate);
				fdate=du.add_Oneday(fdate+" 00:00:00");
			}
			System.out.println("-----------------"+dates);
			
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
					master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
					
				}
			}else{
				int level=Integer.parseInt(levelStr.split("_")[1])-1;
				ids.add(Integer.parseInt(levelID));
				master=masterdao.getTotalMasterDataBysdID(ids,level);
			}
			
			System.out.println("master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){	
					 if(tmb.getLocation_group()!=null && tmb.getLocation_group().equals("mix")){
						  meters.add(tmb.getMeter_sl_no());
						  
					 }
				}
				Map<String,Map<String,String>> loadMap=fdao.getSinglePhaseSupplyFeeders_freports_monthly(meters, frmonth);
				
				System.out.println("--------------"+loadMap);
				for(TotalMasterdataBean tmb:master){
					if(loadMap.containsKey(tmb.getMeter_sl_no()))
						availmaster.add(tmb);
				}
				
				model.put("master", availmaster);
				model.put("load", loadMap);
				model.put("days", dates);
				
			
			}//if not null;
			
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/singlePhaseSupply_monthly", model);
	}
	
	@RequestMapping(value = "/energysavings_singlePhaseSupply_monthly", method = RequestMethod.POST)
	public ModelAndView getenergysavings_singlePhaseSupply_monthly(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> availmaster=new ArrayList();
			List<Integer> ids=new ArrayList();
			List<String> dates=new ArrayList();
			DateUtilities du=new DateUtilities();
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=reqParam.get("frmonth");
			frmonth=frmonth.replace("/", "-");			
			String fdate="01-"+frmonth;
			String ldate=du.getLastDayOfTheMonth("01-"+frmonth);
			while(!fdate.equals(ldate)){
				dates.add(fdate);
				fdate=du.add_Oneday(fdate+" 00:00:00");
			}
			
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
					master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
					
				}
			}else{
				int level=Integer.parseInt(levelStr.split("_")[1])-1;
				ids.add(Integer.parseInt(levelID));
				master=masterdao.getTotalMasterDataBysdID(ids,level);
			}
			
			
			if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){	
					 if(tmb.getLocation_group()!=null && tmb.getLocation_group().equals("mix")){
						  meters.add(tmb.getMeter_sl_no());
						  
					 }
				}
				Map<String,Map<String,String>> loadMap=fdao.getSinglePhaseSupply_energysavings_monthly(meters, frmonth);
				
				for(TotalMasterdataBean tmb:master){
					if(loadMap.containsKey(tmb.getMeter_sl_no()))
						availmaster.add(tmb);
				}
				
				model.put("master", availmaster);
				model.put("map", loadMap);
				model.put("days", dates);
			}//if not null;
			
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/singlePhaseSupply_energysavings_monthly", model);
	}
	
	@RequestMapping(value = "/energysavings_singlePhaseSupply", method = RequestMethod.POST)
	public ModelAndView getenergysavings_singlePhaseSupply(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> availmaster=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frdate=reqParam.get("frdate");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			
			frdate=sdf1.format(sdf.parse(frdate));
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
					master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
					
				}
			}else{
				int level=Integer.parseInt(levelStr.split("_")[1])-1;
				ids.add(Integer.parseInt(levelID));
				master=masterdao.getTotalMasterDataBysdID(ids,level);
			}
			
			System.out.println(frdate+"----- master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){	
					 if(tmb.getLocation_group()!=null && tmb.getLocation_group().equals("mix")){
						  meters.add(tmb.getMeter_sl_no());
						  
					 }
				}
				Map<String,EnergysavingsBean> loadMap=fdao.getSinglePhaseSupply_energysavings(meters, frdate);
				
				for(TotalMasterdataBean tmb:master){
					if(loadMap.containsKey(tmb.getMeter_sl_no()))
						availmaster.add(tmb);
				}
				
				model.put("master", availmaster);
				model.put("map", loadMap);
			
			}//if not null;
			
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/singlePhaseSupply_energysavings", model);
	}
	
	@RequestMapping(value = "/energySavingsDetails", method = RequestMethod.GET)
	public ModelAndView getEnergySavingsDetails(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		try{
			String meter=reqParam.get("meter");
			String date=reqParam.get("date");
			
			model.put("list", fdao.getSinglePhaseSupply_energysavingsDetails(meter, date));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/singlePhaseSupply_energysavingsDetails", model);
	}
	
	@RequestMapping(value = "/ag_deviations", method = RequestMethod.POST)
	public ModelAndView getag_deviations(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			System.out.println("In Feeder Reports ----------------->");
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> availmaster=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.0000");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frdate=reqParam.get("frdate");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			
			frdate=sdf1.format(sdf.parse(frdate));
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
					master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
					
				}
			}else{
				int level=Integer.parseInt(levelStr.split("_")[1])-1;
				ids.add(Integer.parseInt(levelID));
				master=masterdao.getTotalMasterDataBysdID(ids,level);
			}
			
			System.out.println(frdate+"----- master size -------------------------"+master.size());
			if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){	
					 if(tmb.getLocation_category().equals("AG")){
						  meters.add(tmb.getMeter_sl_no());
						  
					 }
				}
				Map<String,String> agmap=fdao.getAG_deviationSupply(meters, frdate);
				
				for(TotalMasterdataBean tmb:master){
					if(agmap.containsKey(tmb.getMeter_sl_no()))
						availmaster.add(tmb);
				}
				
				model.put("master", availmaster);
				model.put("map", agmap);
			
			}//if not null;
			
			
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		
		return new ModelAndView("feeder_reports/ag_deviations", model);
	}
	
	@RequestMapping(value = "/demand_forecasting", method = RequestMethod.POST)
	public ModelAndView getDemand_forecasting(@RequestParam Map<String, String> reqParam, HttpSession session){
		Map<String, Object> model = new HashMap();
		
		try{
			List<TotalMasterdataBean> master=new ArrayList();
			List<TotalMasterdataBean> avail_master=new ArrayList();
			List<Integer> ids=new ArrayList();
			
			DecimalFormat twodec=new DecimalFormat("#0.00");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frmonth=reqParam.get("frmonth");
			
			
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
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){
					meters.add(tmb.getMeter_sl_no());
				}
				Map<String,AnalyticsDemandForecasting> agmap=fdao.getDemandForecasting(meters, frmonth);

				for(TotalMasterdataBean tmb:master){
					if(agmap.containsKey(tmb.getMeter_sl_no()))
						avail_master.add(tmb);
				}
				
			model.put("hlbList", agmap);
			model.put("master", avail_master);
			
			}//if not null;
			
			
		}catch(Exception e){			
		}
		
		return new ModelAndView("feeder_reports/demand_forecasting", model);
	}
	
	public List<HierarchyTitle> getTitleList(int clientId){
		List<HierarchyTitle> titleList=null;
		try{			
			 titleList=titleService.listTitles(clientId);
		}catch(Exception e){
			
		}
		return titleList;
	}
	@ModelAttribute("years")
	public List<Integer> getYears(){
		List<Integer> years=new ArrayList();
		try{			
			 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			 int cur_year=Integer.parseInt(sdf.format(new java.util.Date()).split("-")[2]);
			 for( int i=0;i<4;i++){
				 years.add(cur_year-i);
			 }
		}catch(Exception e){
			
		}
		return years;
	}
	public List<TotalMasterdataBean> getMeters(String levelStr,String levelID,String userHierarchyid,int clientId ){
		List<TotalMasterdataBean> master=new ArrayList();
		Map<String,TotalMasterdataBean> availmaster=new HashMap();
		List<Integer> ids=new ArrayList();
		try{
			//System.out.println(userHierarchyid+"--------------------"+clientId);
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
			if (userHierarchyids!=null  ){    //for company level user
				
				for(String sid:userHierarchyid.split(",")){
					ids.add(Integer.parseInt(sid));
				}
				master=masterdao.getTotalMasterDataBysdID_orderbyss(ids,level);
				
			}
		}else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			ids.add(Integer.parseInt(levelID));
			master=masterdao.getTotalMasterDataBysdID(ids,level);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return master;
	}
}
