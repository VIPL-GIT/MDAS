package com.rtdas.dashboard.controller;

import java.math.RoundingMode;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.MeterService;
import com.rtdas.dashboard.beans.CircleLatLons;
import com.rtdas.dashboard.dao.DashboardDaoMP;
import com.rtdas.dashboard.dao.GmapDao;
import com.rtdas.meterdata.beans.TotalMasterdataBean;
import com.rtdas.reports.dao.FeederReportsDao;
import com.rtdas.reports.dao.TotalMasterDAO;

@Controller
@RequestMapping(value = "/gmap")
public class GmapController {

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
	private GmapDao gdao;
	
	@RequestMapping(value = "/mapHome", method = RequestMethod.GET)
    public ModelAndView getdashHome(HttpSession session) throws Exception{

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
	
	
 	return new ModelAndView("gmap/mapHome", model);
    }
	
	/*
	 * @RequestMapping(value = "/ssLatLons", produces="application/json")
	 * 
	 * @ResponseBody public String getCircleLatLons(HttpServletRequest
	 * req,HttpServletResponse res,HttpSession session){ CircleLatLons clatlons=new
	 * CircleLatLons(); String jsonres=""; try{ String
	 * circle=req.getParameter("circlename");
	 * 
	 * System.out.println("---------------------------------"+circle);
	 * clatlons=gdao.getCircleLatLons(circle); ObjectMapper o=new ObjectMapper();
	 * jsonres=o.writeValueAsString(clatlons.getSslist());
	 * 
	 * System.out.println("--------------------------"+jsonres);
	 * 
	 * }catch(Exception e){ e.printStackTrace(); }
	 * 
	 * return jsonres;
	 * 
	 * }
	 */
	
	
	
	
	
	@RequestMapping(value = "/ssLatLons", method = RequestMethod.GET)
	@ResponseBody
	public String getCircleLatLons(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
	    CircleLatLons clatlons = new CircleLatLons();
	    String jsonres = "";
	    try {
	        String circle = req.getParameter("circlename");

	        System.out.println("---------------------------------" + circle);
	        clatlons = gdao.getCircleLatLons(circle);
	        ObjectMapper o = new ObjectMapper();
	        jsonres = o.writeValueAsString(clatlons.getSslist());

	        System.out.println("--------------------------" + jsonres);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return jsonres;
	}

	
	
	
	
	
	
	
}
