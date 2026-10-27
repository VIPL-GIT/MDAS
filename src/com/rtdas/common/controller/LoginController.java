package com.rtdas.common.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.beans.ModuleSubModuleBean;
import com.rtdas.common.beans.UserBean;
import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.Users;
import com.rtdas.common.service.ClientService;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.LoginService;
import com.rtdas.common.service.MeterService;
import com.rtdas.common.service.TrackerService;
import com.rtdas.common.service.UserRoleService;
import com.rtdas.common.service.UserService;

import nl.captcha.Captcha;
import resources.EncryptPassword;
import resources.SendMail;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private HierarchyTitleService titleService;
	@Autowired
	private HierarchyService hservice;
	@Autowired
	private MeterService meterService;
	@Autowired
	private UserRoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private TrackerService service;
//	@Autowired
//	private ReportsDao rdao;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView welcome() {
		System.out.println("***********:Welcome Page.......");
		 return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/landing", method = RequestMethod.GET)
	public ModelAndView getLandingPage() {		
		 return new ModelAndView("landing");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("command") UserBean user, 
			BindingResult result,HttpSession session) throws Exception{
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		  
		if (!user.getUserId().isEmpty() && !user.getPassword().isEmpty()) {
//------------------------------------------------------------------------------------------------------
			 Captcha captcha = (Captcha)session.getAttribute(Captcha.NAME);
			 if(captcha.isCorrect(user.getCaptcha())){
//------------------------------------------------------------------------------------------------------
			Users users = loginService.getUserIdLogin(user.getUserId());
			 int i=0;
			boolean isValidUser = false;
			
			if(users!=null){
				i++;
			System.out.println(user.getUserId()!=null+" not null "+user.getPassword());
		    isValidUser = login(user.getUserId(),user.getPassword(),users);
			}
			
			 if (isValidUser) {			
				
//if valid user get the modules , submodules and submodules mapped to the client through which user is logged in
				 List<ModuleSubModuleBean> modules=(List<ModuleSubModuleBean>)clientService.listModules();
				 Map<String,Map> moduleMap=getModuleMap(user.getUserId(),modules,users);
				 model.put("modules", modules);
				 model.put("mappedModules", moduleMap);
				 SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
				 model.put("userDetails", user.getUserId()+","+sdf.format(new Date()));
				 List<HierarchyTitle> titles=( List<HierarchyTitle>)titleService.listTitles(users.getClientId());
				 model.put("titles",titles ); //to display the title headers in dashboard
//---------------getting all hierarchy ids based on the hierarchy id , the user is mapped with.
				 List<Integer> childIds=getChildIds(users,titles);
				 System.out.println("child idsssssssssss---------------------------------------------------->"+childIds);
				 //model.put("ss", loginService.getSubstations());
//				 model.put("meters", meterService.listMeters(childIds)); //to display meters graph
//				 model.put("hierarchy", hservice.listHierarchyByClient(users.getClientId())); //to display names in meter graph
//-------------------------getting device types for this client ------------------------------------------------
				 
//---------------getting total supply and total consumption ,highest,lowest values for dashboard ------------------
				 int levelId=roleService.getUserRole(users.getRollId()).getHierarchy_level();
//				 DashboardSupplyBean supply=loginService.getDashboardSupply(titles,levelId,childIds);
//				 session.setAttribute("supply", supply); // supply bean to display dashboard details
//place user role access related info in session , to access these rights throughout the application
				 session.setAttribute("userId", users.getUserId());
				 session.setAttribute("userRole", users.getRollId());
				 session.setAttribute("userType",roleService.getUserRole(users.getRollId()).getRole_title());
				 session.setAttribute("userImg", users.getUserImage());
				 session.setAttribute("clientId", users.getClientId());
				 
				 session.setAttribute("childIds", childIds);
				 session.setAttribute("hierarchyid", users.getHierarchy());
				 session.setAttribute("levelId", levelId); //to know the level id
				 session.setAttribute("clientImg", clientService.getClientImageUrl(users.getClientId()));
				 session.setAttribute("mappedActions", loginService.getMappedActions(users.getRollId()));

//----------------------------setting the dashboard divs details -----------------------------------------------------
				
//-----------------------------------------------------------------------------------------------------------				 
				 if(users.getClientId()==0)
					 return new ModelAndView("home1",model);
				 else if(users.getUserId().equalsIgnoreCase("mopuser"))
					 return new ModelAndView("home_pominda",model);
				 else
			       // return new ModelAndView("home1",model);
					 return new ModelAndView("landing",model);
//					 return new ModelAndView("dashboard",model);
			 }else{
				   // model.put("username",users.getUserId());
				 System.out.println("i --> "+i);
				   if(i==0){
					   model.put("errormessage",  "Please Enter Valid Details");
				   }else{
					   model.put("errormessage", "true");
					   model.put("user",users.getUserId());
					   session.setAttribute("username", users.getUserId());
				   }
				   
				    model.put("validlogin", "false");
		            return new ModelAndView("login",model);
			 }
			 }else{
				 model.put("errormessage", "Invalid captcha");
				 return new ModelAndView("login",model);
			 }
		}else {
			model.put("validlogin", "false");
            model.put("errormessage",  "Please enter Details");
            return new ModelAndView("login",model);
        }
		
	}

	private boolean login(String userId, String password,Users users) {
		boolean returnValue = false;
		//Users users = loginService.getUserIdLogin(userId);
		//String pwd=new UtilityClass().encryptPassword(password);
		//String pwd=password;
		String pwd=new EncryptPassword().encrypt(password);
		
				
		try {
			if(userId.equals(users.getUserId()) && pwd.equals(users.getPassword())){
				returnValue = true;
			}
			} catch (Exception e) {
					e.printStackTrace();
			}
			
		return returnValue;
	}
	private Map<String,Map> getModuleMap(String getUserId,List<ModuleSubModuleBean> modules,Users users) throws Exception{
		
		Map<String,Map> mapped_modules=new LinkedHashMap<String,Map>();
		try{
			// Users users = loginService.getUserIdLogin(getUserId);
			 
			 List mappedIds=(List)clientService.getMappedModules(users.getClientId());
			 List<Integer> subModules=roleService.getsubmodulesByrole(users.getRollId());
			 
			 
			 for(ModuleSubModuleBean msm:modules){
				 
				 if(mappedIds.contains(msm.getSubmodule_id()) && subModules.contains(msm.getSubmodule_id())){
				 String module=msm.getModule_title();
				 
				 if(mapped_modules.containsKey(module)){
					
					 mapped_modules.get(module).put(msm.getSubmodule_title(),msm.getSubmodule_url());
				 }else{
					 
					 Map<String,String> sub=new LinkedHashMap();
					 sub.put(msm.getSubmodule_title(), msm.getSubmodule_url());
					 mapped_modules.put(module,sub );
				 }
				 }//if submodule id is mapped
				
			 }//for
			 System.out.println("--->"+mapped_modules);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return mapped_modules;
		
	}
	public List<Integer> getChildIds_bkp(Users user){
		List<Integer> childIds=new ArrayList();
		try{
			System.out.println("============????????????"+user.getHierarchy());
			if(user.getHierarchy()!=null){
				int level=1;
			Hierarchy hierarchy=hservice.getHierarchy(Integer.parseInt(user.getHierarchy().split(",")[0]));
			if(hierarchy!=null){
			int typeid=hierarchy.getHierarchy_type_id();
			List<HierarchyTitle> titles=titleService.listTitles(user.getClientId());
			for( int i=0;i<titles.size();i++){
				if(typeid==titles.get(i).getHierarchy_type_id())
					level=i+1;
			}

			if(level==titles.size()){
				for(int i=0;i<user.getHierarchy().split(",").length;i++){
					childIds.add(Integer.parseInt(user.getHierarchy().split(",")[i]));
				}
				
			}else{
//				 List<Hierarchy> hie=hservice.getHierarchyByParentId(user.getHierarchy());
//				 for(Hierarchy h:hie){
//					 childIds.add(h.getHierarchy_id());
//				 }
				List<Object[]> h=new ArrayList();
				for(int i=0;i<user.getHierarchy().split(",").length;i++){
					h.addAll(hservice.listAllChildsByParentId(level,titles.size(), Integer.parseInt(user.getHierarchy().split(",")[i])));
				}
					
					for(Object[] obj:h){
						childIds.add(Integer.parseInt(obj[0].toString()));
					}
			}
			}//if hierarchy is not null
			}//if hierarchy is not null
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return childIds;
	}
	
	public List<Integer> getChildIds(Users user,List<HierarchyTitle> titleList){
		List<Integer> ssIds=new ArrayList();
		try{
			String[] userHierarchyids=user.getHierarchy()!=null?user.getHierarchy().split(","):null;
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
    		System.out.println("***********************************************"+level);
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
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return ssIds;
	}
	
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.GET)
	public ModelAndView forgetPassword(HttpSession session) {
		
		 String username=session.getAttribute("username").toString();
		 Map<String, String> model = new HashMap<String, String>();
		 //String clientId=session.getAttribute("userId").toString();
		// Users users = loginService.getUserIdLogin(user.getUserId());
		 System.out.println("***********:forget password......."+username+" ");
		 
		 SendMail sm=new SendMail();
	 	 String msgtopass= sm.sendtomail(username);	
		 System.out.println("msgtopass --> "+msgtopass);
		 model.put("user",username);
		 model.put("SentInfo",msgtopass);
		 return new ModelAndView("login",model);
	}
	

}
