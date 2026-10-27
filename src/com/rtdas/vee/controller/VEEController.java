package com.rtdas.vee.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.meterdata.beans.TotalMasterdataBean;
import com.rtdas.reports.dao.TotalMasterDAO;
import com.rtdas.vee.beans.SummaryDailyBean;
import com.rtdas.vee.dao.VEEDao;
import com.rtdas.vee.models.VEERules;
import com.rtdas.vee.models.Vee_summary_daily;

@Controller
@RequestMapping(value = "/vee")
public class VEEController {

	@Autowired
	private VEEDao vdao;
	@Autowired
	private HierarchyService hservice;
	@Autowired
	private HierarchyTitleService titleService;	
	@Autowired
	private TotalMasterDAO masterdao;
	
	
	@RequestMapping(value = "/rules", method = RequestMethod.GET)
	public ModelAndView getRules(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try{
			model.put("rules", vdao.getRules());
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("vee/rules", model);
	}
	@RequestMapping(value = "/editrules", method = RequestMethod.GET)
	public ModelAndView editVEERules(@RequestParam("id")int id,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try{
			model.put("rule", vdao.getRule(id));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("vee/editrules", model);
	}
	@RequestMapping(value = "/updateRule", method = RequestMethod.GET)
	public ModelAndView updateRule(@ModelAttribute("rule")VEERules rule,HttpSession session) throws Exception{
		
		try{
			System.out.println("values : "+rule.getParam_values());
			vdao.updateRule(rule);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/vee/rules.epx");
	}
	@RequestMapping(value = "/addrules", method = RequestMethod.GET)
	public ModelAndView addVEERules(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try{
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("vee/addrule", model);
	}
	
	@RequestMapping(value = "/summaryHome", method = RequestMethod.GET)
	public ModelAndView getSummary(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try{
			String hierarchyid=(String)session.getAttribute("hierarchyid");
			model.put("userHierarchyid", hierarchyid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("vee/summaryHome", model);
	}
	
	@RequestMapping(value = "/summaryReport")
	public ModelAndView getSummaryReport(@RequestParam Map<String, String> reqParam,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try{	
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frdate=reqParam.get("frdate");
			
			frdate=sdf.format(sdf1.parse(frdate));
			System.out.println("sdate------------"+frdate);
//------------------------------------------------------------------------------------------------------
			List<TotalMasterdataBean> master=new ArrayList();
			
			List<Integer> ids=new ArrayList();
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
//------------------------------------------------------------------------------------------------------
           if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){
					meters.add(tmb.getMeter_sl_no());
				}
			List<Vee_summary_daily> list=vdao.getSummaryReport(meters,frdate);
//-----------------------------------------------------------------------------------------------------			
           
           int rtc_check=0,spike_check=0,kvarh_check=0,sum_check=0,high_low_check=0,zero_check=0,negative_usage_check=0;
           int consecutive_interval_check=0,tod_summation_check=0,inactive_measurement_check=0,time_tolerance_check=0;
            for(Vee_summary_daily vsd:list){
            	if(vsd.getRtc_check()!=null && vsd.getRtc_check().equals("1")) rtc_check++;
            	if(vsd.getSpike_check()!=null && vsd.getSpike_check().equals("1")) spike_check++;
            	if(vsd.getKvarh_check()!=null && vsd.getKvarh_check().equals("1")) kvarh_check++;
            	if(vsd.getSum_check()!=null && vsd.getSum_check().equals("1")) sum_check++;
            	if(vsd.getHigh_low_check()!=null && vsd.getHigh_low_check().equals("1")) high_low_check++;
            	if(vsd.getZero_check()!=null && vsd.getZero_check().equals("1")) zero_check++;
            	if(vsd.getNegative_usage_check()!=null && vsd.getNegative_usage_check().equals("1")) negative_usage_check++;
            	if(vsd.getConsecutive_interval_check()!=null && vsd.getConsecutive_interval_check().equals("1")) consecutive_interval_check++;
            	if(vsd.getTod_summation_check()!=null && vsd.getTod_summation_check().equals("1")) tod_summation_check++;
            	if(vsd.getInactive_measurement_check()!=null && vsd.getInactive_measurement_check().equals("1")) inactive_measurement_check++;
            	if(vsd.getTime_tolerance_check()!=null && vsd.getTime_tolerance_check().equals("1")) time_tolerance_check++;
            }
            SummaryDailyBean bean=new SummaryDailyBean(); 
            bean.setRtc_check_failed(rtc_check+"");
            bean.setRtc_check((meters.size()-rtc_check)+"");
            bean.setSpike_check_failed(spike_check+"");
            bean.setSpike_check((meters.size()-spike_check)+"");
            bean.setKvarh_check_failed(kvarh_check+"");
            bean.setKvarh_check((meters.size()-kvarh_check)+"");
            bean.setSum_check_failed(sum_check+"");
            bean.setSum_check((meters.size()-sum_check)+"");
            bean.setHigh_low_check_failed(high_low_check+"");
            bean.setHigh_low_check((meters.size()-high_low_check)+"");
            bean.setZero_check_failed(zero_check+"");
            bean.setZero_check((meters.size()-zero_check)+"");
            bean.setNegative_usage_check_failed(negative_usage_check+"");
            bean.setNegative_usage_check((meters.size()-negative_usage_check)+"");
            bean.setConsecutive_interval_check_failed(consecutive_interval_check+"");
            bean.setConsecutive_interval_check((meters.size()-consecutive_interval_check)+"");
            bean.setTod_summation_check_failed(tod_summation_check+"");
            bean.setTod_summation_check((meters.size()-tod_summation_check)+"");
            bean.setInactive_measurement_check_failed(inactive_measurement_check+"");
            bean.setInactive_measurement_check((meters.size()-inactive_measurement_check)+"");
            bean.setTime_tolerance_check_failed(time_tolerance_check+"");
            bean.setTime_tolerance_check((meters.size()-time_tolerance_check)+"");
            bean.setComm_date(frdate);
            bean.setTotal_meters(meters.size()+"");
//-----------------------------------------------------------------------------------------------------			
			model.put("bean", bean);
          }//if meters exists
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("vee/summaryReport", model);
	}
	@RequestMapping(value = "/summaryReport_graph")
	public ModelAndView getSummaryReportGraph(@RequestParam Map<String, String> reqParam,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try{	
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
			
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String frdate=reqParam.get("frdate");
			
			frdate=sdf.format(sdf1.parse(frdate));
			System.out.println("sdate------------"+frdate);
//------------------------------------------------------------------------------------------------------
			List<TotalMasterdataBean> master=new ArrayList();
			
			List<Integer> ids=new ArrayList();
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
//------------------------------------------------------------------------------------------------------
           if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){
					meters.add(tmb.getMeter_sl_no());
				}
			List<Vee_summary_daily> list=vdao.getSummaryReport(meters,frdate);
//-----------------------------------------------------------------------------------------------------			
           
           int rtc_check=0,spike_check=0,kvarh_check=0,sum_check=0,high_low_check=0,zero_check=0,negative_usage_check=0;
           int consecutive_interval_check=0,tod_summation_check=0,inactive_measurement_check=0,time_tolerance_check=0;
            for(Vee_summary_daily vsd:list){
            	if(vsd.getRtc_check()!=null && vsd.getRtc_check().equals("1")) rtc_check++;
            	if(vsd.getSpike_check()!=null && vsd.getSpike_check().equals("1")) spike_check++;
            	if(vsd.getKvarh_check()!=null && vsd.getKvarh_check().equals("1")) kvarh_check++;
            	if(vsd.getSum_check()!=null && vsd.getSum_check().equals("1")) sum_check++;
            	if(vsd.getHigh_low_check()!=null && vsd.getHigh_low_check().equals("1")) high_low_check++;
            	if(vsd.getZero_check()!=null && vsd.getZero_check().equals("1")) zero_check++;
            	if(vsd.getNegative_usage_check()!=null && vsd.getNegative_usage_check().equals("1")) negative_usage_check++;
            	if(vsd.getConsecutive_interval_check()!=null && vsd.getConsecutive_interval_check().equals("1")) consecutive_interval_check++;
            	if(vsd.getTod_summation_check()!=null && vsd.getTod_summation_check().equals("1")) tod_summation_check++;
            	if(vsd.getInactive_measurement_check()!=null && vsd.getInactive_measurement_check().equals("1")) inactive_measurement_check++;
            	if(vsd.getTime_tolerance_check()!=null && vsd.getTime_tolerance_check().equals("1")) time_tolerance_check++;
            }
            SummaryDailyBean bean=new SummaryDailyBean(); 
            bean.setRtc_check_failed(rtc_check+"");
            bean.setRtc_check((meters.size()-rtc_check)+"");
            bean.setSpike_check_failed(spike_check+"");
            bean.setSpike_check((meters.size()-spike_check)+"");
            bean.setKvarh_check_failed(kvarh_check+"");
            bean.setKvarh_check((meters.size()-kvarh_check)+"");
            bean.setSum_check_failed(sum_check+"");
            bean.setSum_check((meters.size()-sum_check)+"");
            bean.setHigh_low_check_failed(high_low_check+"");
            bean.setHigh_low_check((meters.size()-high_low_check)+"");
            bean.setZero_check_failed(zero_check+"");
            bean.setZero_check((meters.size()-zero_check)+"");
            bean.setNegative_usage_check_failed(negative_usage_check+"");
            bean.setNegative_usage_check((meters.size()-negative_usage_check)+"");
            bean.setConsecutive_interval_check_failed(consecutive_interval_check+"");
            bean.setConsecutive_interval_check((meters.size()-consecutive_interval_check)+"");
            bean.setTod_summation_check_failed(tod_summation_check+"");
            bean.setTod_summation_check((meters.size()-tod_summation_check)+"");
            bean.setInactive_measurement_check_failed(inactive_measurement_check+"");
            bean.setInactive_measurement_check((meters.size()-inactive_measurement_check)+"");
            bean.setTime_tolerance_check_failed(time_tolerance_check+"");
            bean.setTime_tolerance_check((meters.size()-time_tolerance_check)+"");
            bean.setComm_date(frdate);
            bean.setTotal_meters(meters.size()+"");
//-----------------------------------------------------------------------------------------------------			
			model.put("bean", bean);
          }//if meters exists
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("vee/summaryReport_graph", model);
	}
	@RequestMapping(value = "/summaryReportChecks")
	public ModelAndView getSummaryReportChecks(@RequestParam Map<String, String> reqParam,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		String viewpage="";
		try{
			String levelStr=reqParam.get("level");
			String levelID=reqParam.get("levelID");
			String sdate=reqParam.get("sdate");			
			String type=reqParam.get("type");
	
//------------------------------------------------------------------------------------------------------
			List<TotalMasterdataBean> master=new ArrayList();
			Map<String,TotalMasterdataBean> availmaster=new HashMap();
			
			List<Integer> ids=new ArrayList();
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
//------------------------------------------------------------------------------------------------------
           if(master.size()>0 ){
				
				List<String> meters=new ArrayList();
				for(TotalMasterdataBean tmb:master){
					meters.add(tmb.getMeter_sl_no());
					availmaster.put(tmb.getMeter_sl_no(),tmb);
				}
			List<Vee_summary_daily> list=vdao.getSummaryReport(meters,sdate);
			model.put("summary", list);
			model.put("master", availmaster);
			
//------------------------------------------------------------------------------
			if(type.equals("rtc")) viewpage="vee/checks/rtc_check";
			if(type.equals("spike")) viewpage="vee/checks/spike_check";
			if(type.equals("kvarh")) viewpage="vee/checks/kvarh_check";
			if(type.equals("sum")) viewpage="vee/checks/sum_check";
			if(type.equals("highlow")) viewpage="vee/checks/high_low_check";
			if(type.equals("zero")) viewpage="vee/checks/zero_check";
			if(type.equals("negative")) viewpage="vee/checks/negative_usage_check";
			if(type.equals("consecutive")) viewpage="vee/checks/consecutive_interval_check";
			if(type.equals("tod")) viewpage="vee/checks/tod_summation_check";
			if(type.equals("inactive")) viewpage="vee/checks/inactive_measurement_check";
			if(type.equals("time")) viewpage="vee/checks/time_tolerance_check";
			
          }//if meters exists
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView(viewpage, model);
	}
	
	@RequestMapping(value = "/detailedHome", method = RequestMethod.GET)
	public ModelAndView getDetails(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try{
			String hierarchyid=(String)session.getAttribute("hierarchyid");
			model.put("userHierarchyid", hierarchyid);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("vee/detailedHome", model);
	}
	@RequestMapping(value = "/detailedReport", method = RequestMethod.GET)
	public ModelAndView getdetailedReport(@RequestParam Map<String, String> reqParam,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
			
			String meter=reqParam.get("meter");
			String sdate=reqParam.get("sdate");
			String edate=reqParam.get("edate");
			sdate=sdf.format(sdf1.parse(sdate));
			edate=sdf.format(sdf1.parse(edate));
			
			List<String> meters=new ArrayList();
			meters.add(meter);
			
			System.out.println("--------------------------------"+meter);
			List<Vee_summary_daily> list=vdao.getSummaryReport_dates(meters,sdate,edate);
			model.put("summary", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("vee/detailedReport", model);
	}
	
	@RequestMapping(value = "/detailedReportChecks", method = RequestMethod.GET)
	public ModelAndView getdetailedReportChecks(@RequestParam Map<String, String> reqParam,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		String viewpage="";
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
			
			String meter=reqParam.get("meter");
			String sdate=reqParam.get("sdate");
			String edate=reqParam.get("edate");
			String type=reqParam.get("type");
			
			sdate=sdf.format(sdf1.parse(sdate));
			edate=sdf.format(sdf1.parse(edate));
			
			List<String> meters=new ArrayList();
			meters.add(meter);
			
			System.out.println("--------------------------------"+meter);
//			List<SummaryDailyBean> list=vdao.getSummaryReport(meters,sdate,edate);
//			model.put("summary", list);
			
//------------------------------------------------------------------------------
			if(type.equals("rtc")) viewpage="vee/checks/rtc_check";
			if(type.equals("spike")) viewpage="vee/checks/spike_check";
			if(type.equals("kvarh")) viewpage="vee/checks/kvarh_check";
			if(type.equals("sum")) viewpage="vee/checks/sum_check";
			if(type.equals("highlow")) viewpage="vee/checks/high_low_check";
			if(type.equals("zero")) viewpage="vee/checks/zero_check";
			if(type.equals("negative")) viewpage="vee/checks/negative_usage_check";
			if(type.equals("consecutive")) viewpage="vee/checks/consecutive_interval_check";
			if(type.equals("tod")) viewpage="vee/checks/tod_summation_check";
			if(type.equals("inactive")) viewpage="vee/checks/inactive_measurement_check";
			if(type.equals("time")) viewpage="vee/checks/time_tolerance_check";
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView(viewpage, model);
	}
}
