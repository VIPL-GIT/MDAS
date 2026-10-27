package com.rtdas.common.controller;


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

import com.rtdas.common.beans.DataLoggerBean;
import com.rtdas.common.models.*;
import com.rtdas.common.service.DataLoggerService;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.LoginService;

@Controller
@RequestMapping(value="/logger")
public class DataLoggerController {
	@Autowired	
	private DataLoggerService loggerService; 
	@Autowired
	private HierarchyTitleService titleService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private HierarchyService hservice;
	
    public String actionId;
	
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	@RequestMapping(value="/addLogger" ,method=RequestMethod.GET)
	public ModelAndView addLogger(HttpSession session){
		
		Map<String, Object> model = new HashMap<String, Object>();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		List titleList=titleService.listDeviceLocatedTitles(clientId);
		model.put("titles", titleList);
		model.put("actionId", getActionId());
		return new ModelAndView("logger/addLogger",model);
	}
	
	@RequestMapping(value="/editLogger" ,method=RequestMethod.GET)
	public ModelAndView editLogger(@ModelAttribute("logger") DataLoggerBean loggerBean,HttpSession session){
		
		Map<String, Object> model = new HashMap<String, Object>();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
	    loggerBean=prepareBean(loggerService.getLogger(loggerBean.getLogger_id()));
		model.put("logger",loggerBean);
		
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		List<HierarchyTitle> locationtitleList=titleService.listDeviceLocatedTitles(clientId);
		model.put("titles", titleList);
		model.put("locationtitles", locationtitleList);
		//System.out.println("----->"+mbean.getLocation_id());
		if(loggerBean.getLocation_id()!=null){
		Hierarchy h=hservice.getHierarchy(loggerBean.getLocation_id());
		
		int level=1;
		if(h!=null){
		
		int locLevelId=h.getHierarchy_type_id();
		for(HierarchyTitle ht:titleList){
			if(ht.getHierarchy_type_id()==h.getHierarchy_type_id()){
				level=titleList.indexOf(ht)+1;
				break;
			}else{				
			continue;
			}
			
		}
		
		System.out.println("----->"+level);
		model.put("level", level);
		model.put("locLevelId", locLevelId);
		}//if any hierarchy is assigned to user
		model.put("currentHierarchy", hservice.listHierarchyById(level, loggerBean.getLocation_id()));
		}
		model.put("actionId", getActionId());
		return new ModelAndView("logger/editLogger",model);
	}
	
	@RequestMapping(value="/addLogger" ,method=RequestMethod.POST)
	public ModelAndView saveData(@ModelAttribute("logger") DataLoggerBean loggerBean,HttpSession session) throws Exception{
		try{
			loggerService.addLogger(prepareModel(loggerBean));
		}catch(Exception e){
			e.printStackTrace();
		}
//--------------------updating child ids in session -------------------------
		Users users = loginService.getUserIdLogin(session.getAttribute("userId").toString());
		System.out.println("------------>"+users.getHierarchy());
		 List<Integer> childIds=getChildIds(users);
		 session.removeAttribute("childIds");
		 session.setAttribute("childIds", childIds);
//-------------------------------end --------------------------------------		 
		return new ModelAndView("redirect:/logger/viewLogger.sms?actionId="+getActionId());
	}
	
	@RequestMapping(value="/deleteLogger")
	public ModelAndView deleteMeter(@RequestParam("logger_id") Integer logger_id,HttpSession session) throws Exception{
		
		loggerService.deleteLogger(logger_id);
		
		return new ModelAndView("redirect:/logger/viewLogger.sms?actionId="+getActionId());
	}
	
	@RequestMapping(value="/viewLogger" ,method=RequestMethod.GET)
	public ModelAndView viewModems(@RequestParam("actionId")String actionId,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		System.out.println("------------------1");
		String hid=session.getAttribute("hierarchyid").toString();
		System.out.println("----------------->"+hid);
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		int levelId=Integer.parseInt(session.getAttribute("levelId").toString());
		List<Integer> childs=(List<Integer>)session.getAttribute("childIds");
		System.out.println("------------------2");
		Map<Integer,String> hMap=hservice.listHierarchyByClient(clientId);
		List<DataLoggerBean> dlList=prepareListofBean(loggerService.listLoggers(childs));
		System.out.println("------------------3---------"+dlList);
		if(!dlList.isEmpty()){
		for(DataLoggerBean dlb:dlList){
			if(hMap.containsKey(dlb.getLocation_id()))
			 dlb.setLocation_name(hMap.get(dlb.getLocation_id()));
		}
		}
		System.out.println("------------------4");
		model.put("loggers",dlList );
		setActionId(actionId);
		model.put("actionId", actionId);
		return new ModelAndView("logger/viewLogger",model);
		
	}
	
	public List<DataLoggerBean> prepareListofBean(List<DataLogger> loggerList){
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		List<DataLoggerBean> dlList=dlList=new ArrayList();;
		if(loggerList!=null && !loggerList.isEmpty()){
			
			for(DataLogger dl:loggerList){
				DataLoggerBean dlb=new DataLoggerBean();
				try{
					dlb.setDelivery_date(sdf.format(dl.getDelivery_date()));
				     }catch(Exception e){}
				dlb.setFw_version(dl.getFw_version());
				dlb.setHw_version(dl.getHw_version());
				dlb.setLattitude(dl.getLattitude());
				dlb.setLongitude(dl.getLongitude());
				dlb.setLocation_id(dl.getLocation_id());
				dlb.setLogger_id(dl.getLogger_id());
				dlb.setLogger_sl_no(dl.getLogger_sl_no());
				dlb.setMobile(dl.getMobile());
				dlb.setNo_of_lights(dl.getNo_of_lights());
				dlb.setAmp_per_light(dl.getAmp_per_light());
				dlList.add(dlb);
			}
		}
		
		
		return dlList;
	}
	
	public DataLoggerBean prepareBean(DataLogger dl){
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		DataLoggerBean dlb=new DataLoggerBean();
				try{
					dlb.setDelivery_date(sdf.format(dl.getDelivery_date()));
				     }catch(Exception e){}
				dlb.setFw_version(dl.getFw_version());
				dlb.setHw_version(dl.getHw_version());
				dlb.setLattitude(dl.getLattitude());
				dlb.setLongitude(dl.getLongitude());
				dlb.setLocation_id(dl.getLocation_id());
				dlb.setLogger_id(dl.getLogger_id());
				dlb.setLogger_sl_no(dl.getLogger_sl_no());
				dlb.setMobile(dl.getMobile());
				dlb.setNo_of_lights(dl.getNo_of_lights());
				dlb.setAmp_per_light(dl.getAmp_per_light());
				return dlb;
	}
	
public DataLogger prepareModel(DataLoggerBean dlb){
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	DataLogger dl=new DataLogger();
	try{
		
		dl.setDelivery_date((new java.sql.Date(sdf.parse(dlb.getDelivery_date()).getTime())));
	}catch(Exception e){e.printStackTrace();}
	  dl.setFw_version(dlb.getFw_version());
	  dl.setHw_version(dlb.getHw_version());
	  dl.setLattitude(dlb.getLattitude());
	  dl.setLongitude(dlb.getLongitude());
	  if(dlb.getLocation_id()!=null)
	   dl.setLocation_id(dlb.getLocation_id());
	  dl.setLogger_sl_no(dlb.getLogger_sl_no());
	  dl.setLogger_id(dlb.getLogger_id());
	  dl.setMobile(dlb.getMobile());
	  dl.setNo_of_lights(dlb.getNo_of_lights());
	  dl.setAmp_per_light(dlb.getAmp_per_light());
	
	return dl;
}

public List<Integer> getChildIds(Users user){
	List<Integer> childIds=new ArrayList();
	try{
		//System.out.println("============????????????"+user.getHierarchy());
		if(user.getHierarchy()!=null){
			int level=1;
		Hierarchy hierarchy=hservice.getHierarchy(Integer.parseInt(user.getHierarchy().split(",")[0]));
		int typeid=hierarchy.getHierarchy_type_id();
		List<HierarchyTitle> titles=titleService.listTitles(user.getClientId());
		for( int i=0;i<titles.size();i++){
			if(typeid==titles.get(i).getHierarchy_type_id())
				level=i+1;
		}

		if(level==titles.size()){
			childIds.add(Integer.parseInt(user.getHierarchy().split(",")[0]));
		}else{
//			 List<Hierarchy> hie=hservice.getHierarchyByParentId(user.getHierarchy());
//			 for(Hierarchy h:hie){
//				 childIds.add(h.getHierarchy_id());
//			 }
				List<Object[]> h=hservice.listAllChildsByParentId(level,titles.size(), Integer.parseInt(user.getHierarchy().split(",")[0]));
				for(Object[] obj:h){
					childIds.add(Integer.parseInt(obj[0].toString()));
				}
		}
		}//if hierarchy is not null
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return childIds;
}

}
