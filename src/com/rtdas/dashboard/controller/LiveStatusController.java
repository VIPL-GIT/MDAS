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
import com.rtdas.meterdata.beans.TotalMasterdataBean;
import com.rtdas.reports.dao.FeederReportsDao;
import com.rtdas.reports.dao.TotalMasterDAO;

import utils.GetPieGraph;

@Controller
@RequestMapping(value = "/live")
public class LiveStatusController {

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
	
	@RequestMapping(value = "/livestatus", method = RequestMethod.GET)
    public ModelAndView getdashHome(HttpSession session) throws Exception{

	Map<String, Object> model = new HashMap<String, Object>();
	List<TotalMasterdataBean> master=new ArrayList();

	try{
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
 	return new ModelAndView("LiveStatus/home", model);
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
		List<String> commList=gpg.getLast1hrCommmetersStatus_new(currentDate);
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
	
	
 	return new ModelAndView("dashboard1/getLiveCommStatus", model);
    }
	
}
