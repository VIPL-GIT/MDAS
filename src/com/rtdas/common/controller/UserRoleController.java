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

import com.rtdas.common.beans.UserRoleBean;
import com.rtdas.common.models.*;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.UserRoleService;

@Controller
@RequestMapping(value="/userRole")
public class UserRoleController {

	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private HierarchyTitleService titleService;
//	@Autowired	
//	private ClientService clientService; 
    public String actionId;
	
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	

	@RequestMapping(value="/addUserRole" ,method=RequestMethod.GET)
	public ModelAndView addUserRolePage(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		List<Client> clientsList=userRoleService.listClients();
	    model.put("clients", clientsList);
	    model.put("roles", prepareListofBean(userRoleService.getUserRolesByClient(clientId)));
	    model.put("subModuleActions",userRoleService.listsubmoduleActions());
	    model.put("actionId", getActionId());
	    model.put("clientId",clientId );
	    model.put("titles", titleService.listTitles(clientId));
		return new ModelAndView("userRoles/addUserRole",model);
	}
	@RequestMapping(value="/addUserRole" ,method=RequestMethod.POST)
	public ModelAndView saveClient(@ModelAttribute("userRole") UserRoleBean userRoleBean,HttpSession session) throws Exception{
		userRoleService.addUserRole(prepareModel(userRoleBean),userRoleBean.getAction_flgs());
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		Map<String, Object> model = new HashMap();
		model.put("roles", prepareListofBean(userRoleService.listUserRole(clientId)));
		model.put("actionId", getActionId());
		return new ModelAndView("userRoles/viewUserRoles",model);
	}
	
	@RequestMapping(value="/deleteUserRole")
	public ModelAndView deleteUserRole(@RequestParam("role_id") String role_id,HttpSession session) throws Exception{
		UserRoleBean cb=new UserRoleBean();
		cb.setRole_id(Integer.parseInt(role_id));
		userRoleService.deleteUserRole(prepareModel(cb));
		Map<String, Object> model = new HashMap();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		model.put("roles", prepareListofBean(userRoleService.listUserRole(clientId)));
		model.put("actionId", getActionId());
		return new ModelAndView("userRoles/viewUserRoles",model);
	}
	
	@RequestMapping(value="/editUserRole" ,method=RequestMethod.GET)
	public ModelAndView editUser(@ModelAttribute("user") UserRoleBean userRoleBean,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		model.put("role", prepareUserRoleBean(userRoleService.getUserRole((userRoleBean.getRole_id()))));
		List<Client> clientsList=userRoleService.listClients();
	    model.put("clients", clientsList);
	    model.put("roles", prepareListofBean(userRoleService.getUserRolesByClient(clientId)));
	    model.put("subModuleActions",userRoleService.listsubmoduleActions());
	    model.put("mappedActions", userRoleService.getActionFlgs(userRoleBean.getRole_id()));
	    model.put("actionId", getActionId());
	    model.put("clientId", clientId);
	    model.put("titles", titleService.listTitles(clientId));
		return new ModelAndView("userRoles/editUserRole",model);
		
	}
	@RequestMapping(value="/viewUserRoles" ,method=RequestMethod.GET)
	public ModelAndView viewUserPage(@RequestParam("actionId")String actionId,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		model.put("roles", prepareListofBean(userRoleService.listUserRole(clientId)));
		setActionId(actionId);
		model.put("actionId", actionId);
		return new ModelAndView("userRoles/viewUserRoles",model);
		
	}
	
private UserRole prepareModel(UserRoleBean userRoleBean){
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-YYYY");
		UserRole userRole=new UserRole();
		userRole.setRole_id(userRoleBean.getRole_id());
		userRole.setRole_title(userRoleBean.getRole_title());
		userRole.setRole_desc(userRoleBean.getRole_desc());
		userRole.setClient_id(userRoleBean.getClient_id());
		userRole.setParent_role_id(userRoleBean.getParent_role_id());
        userRole.setActive_flg(userRoleBean.getActive_flg());
        userRole.setHierarchy_level(userRoleBean.getHierarchy_level());
		return userRole;
		
	}
	
	private List<UserRoleBean> prepareListofBean(List<UserRole> userRole){
		Map<Integer,String> clientmap=new HashMap();
		Map<Integer,String> parentrolemap=new HashMap();
		List<Client> clientsList=userRoleService.listClients();
		for(Client c:clientsList){
			clientmap.put(c.getClient_id(), c.getClient_name());
		}
		for(UserRole user : userRole){
			parentrolemap.put(user.getRole_id(), user.getRole_title());
		}
		List<UserRoleBean> beans = null;
		if( userRole!= null && !userRole.isEmpty()){
			beans = new ArrayList<UserRoleBean>();
			UserRoleBean bean = null;
			for(UserRole user : userRole){
				bean = new UserRoleBean();
				bean.setRole_id(user.getRole_id());
				bean.setRole_title(user.getRole_title());
				bean.setRole_desc(user.getRole_desc());
				bean.setClient_id(user.getClient_id());
				bean.setParent_role_id(user.getParent_role_id());
				bean.setActive_flg(user.getActive_flg());
				bean.setHierarchy_level(user.getHierarchy_level());
				bean.setClient_name(clientmap.get(user.getClient_id()));
				bean.setParent_role_name(parentrolemap.get(user.getParent_role_id()));
				beans.add(bean);
			}
		}
		return beans;
	}
	private UserRoleBean prepareUserRoleBean(UserRole user){
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-YYYY");
		UserRoleBean bean = new UserRoleBean();
		bean.setRole_id(user.getRole_id());
		bean.setRole_title(user.getRole_title());
		bean.setRole_desc(user.getRole_desc());
		bean.setClient_id(user.getClient_id());
		bean.setParent_role_id(user.getParent_role_id());
		bean.setActive_flg(user.getActive_flg());	
		bean.setHierarchy_level(user.getHierarchy_level());			
		return bean;
	}

}