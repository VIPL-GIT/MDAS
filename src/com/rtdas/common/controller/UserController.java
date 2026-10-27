package com.rtdas.common.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.beans.UserBean;
import com.rtdas.common.beans.alert_types;
import com.rtdas.common.dao.UserDao;
import com.rtdas.common.models.*;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.UserService;

import resources.EncryptPassword;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private HierarchyTitleService titleService;
	@Autowired 
	private HierarchyService hService;
	
	public String actionId;

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUserPage(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		List<Client> clientsList = userService.listClients();
		model.put("clients", clientsList);
		 List<UserRole> userRoles=userService.listUserRoles(clientId);
		 model.put("roles", userRoles);
		model.put("actionId", getActionId());
		model.put("clientId",clientId );
		return new ModelAndView("user/addUser", model);
	}

	
	@RequestMapping(value = "/eventsnotifications", method = RequestMethod.GET)
	public ModelAndView eventsNotifications(@RequestParam("actionId") String actionId,@RequestParam("alertid") String alertid,@RequestParam("alerttype") String alerttype,@RequestParam("alertmode") String alertmode,@RequestParam("isenabled") String isenabled,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		String clientId=session.getAttribute("clientId").toString();
	/*	model.put("usersNotefication", prepareListofBean(userService.listUsers(clientId),clientId));*/
		model.put("alerttype",alerttype);
		model.put("alertmode",alertmode);
		model.put("isenabled",isenabled);
		model.put("usersNotefication",actionId);
		model.put("alertid", alertid);
		
	//	boolean result =userService.chkEmailSms(actionId,alertmode);
		
		
		List<alerttypes> alertType=userService.listAletTypes();
	  	model.put("alertType",alertType);
	  	List<alertMode> alertMode=userService.listAletMode();
	  	model.put("alertMode",alertMode);
		setActionId(actionId);
		model.put("actionId", actionId);
		return new ModelAndView("user/eventsnotifications", model);
	}
	

	
	@RequestMapping(value = "/AddUserEventNotification", method = RequestMethod.GET)
	public ModelAndView AddUserEventNotification(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		String clientId=session.getAttribute("clientId").toString();
		model.put("user", prepareListofBean(userService.listUsers(clientId),clientId));
		List<alerttypes> alertType=userService.listAletTypes();
	  	model.put("alertType",alertType);
	  	List<alertMode> alertMode=userService.listAletMode();
	  	model.put("alertMode",alertMode);
		//setActionId(actionId);
		//model.put("actionId", actionId);
		return new ModelAndView("user/AddUserEventNotification", model);
	}
	
	
	
	@RequestMapping(value = "/deleteEventNotification", method = RequestMethod.GET)
	public ModelAndView deleteEventNotification(@RequestParam("username") String username,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		boolean result =userService.deleteuseralertconfig(username);
		return new ModelAndView("redirect:/user/viewnotifications.epx");
	}
	

	
	@RequestMapping(value = "/saveEventNotification", method = RequestMethod.POST)
	public ModelAndView saveEventNotification(@RequestParam("username") String username,@RequestParam("alerttype") String alerttype,@RequestParam("alertmode") String alertmode,@RequestParam("isenabled") String isenabled,@RequestParam("alertid") int alertid,HttpSession session) throws Exception{
		System.out.println(" save and notification ");
		System.out.println(username);
		System.out.println(alerttype);
		System.out.println(alertmode);
		System.out.println(alertid);
		boolean b=userService.saveEventNotification(username,alerttype,alertmode,isenabled,alertid);
		return new ModelAndView("redirect:/user/viewnotifications.epx");
	}

	
	
	
	@RequestMapping(value = "/AddUser", method = RequestMethod.POST)
	public ModelAndView AddUser(@RequestParam("username") String username,@RequestParam("alerttype") String alerttype,@RequestParam("alertmode") String alertmode,@RequestParam("isenabled") String isenabled,HttpSession session) throws Exception{
		System.out.println("add user controller ");
		boolean b=userService.AddUserEventNotification(username,alerttype,alertmode,isenabled);
		return new ModelAndView("redirect:/user/viewnotifications.epx");
	}
	
	
	
	@RequestMapping(value = "/viewnotifications", method = RequestMethod.GET)
	public ModelAndView viewnotifications(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		List<useralertsconfig> userconfig=userService.listUserAlert();
	  	model.put("userconfig",userconfig);
		return new ModelAndView("user/viewnotifications", model);
	}
	
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView saveModem(@ModelAttribute("user") UserBean userBean, MultipartHttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception{
		try {
			System.out.println("------in adding bean");
			int gen_userSlno = 0;
			if (userBean.getSlno() == 0){
				userBean.setNew_password(userBean.getPassword());
				userBean.setPassword(new EncryptPassword().encrypt(userBean.getPassword()));
				gen_userSlno = userService.addUser(prepareModel(userBean));
				
			}
			if (userBean.getSlno() != 0 && request.getFile("userImageFile") == null) {
				String oldImageUrl = userService.getUserImageUrl(userBean.getSlno());
				userBean.setUserImage(oldImageUrl);
				gen_userSlno = userService.addUser(prepareModel(userBean));
			} if (userBean.getSlno() != 0 && request.getFile("userImageFile") != null) {
				gen_userSlno = userService.addUser(prepareModel(userBean));
			}

			String userId = new UtilityClass().addZeros(gen_userSlno);
			if (request.getFile("userImageFile") != null) {
				System.out.println("---------if file available------------------");
				MultipartFile multipartFile = request.getFile("userImageFile");
				String fileName = multipartFile.getOriginalFilename();
				String file_ext = fileName.substring(fileName.lastIndexOf("."));
				String sep=System.getProperty("file.separator");
				System.out.println("--->" + userId + "\t" + fileName+"\t"+request.getRealPath("/img"));

				File f = new File(request.getRealPath("/img") + sep+"users"+sep+"user_" + userId + file_ext);
				System.out.println("File is saved at ---->" + request.getRealPath("/img") + "/users/user_" + userId + file_ext);
				// if(!f.exists()) f.mkdirs();
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(multipartFile.getBytes());
				fos.flush();
				fos.close();
				userBean.setUserImage(f.getAbsolutePath());
				userBean.setSlno(gen_userSlno);
				userService.addUser(prepareModel(userBean));
			} // if user image is not null

		} catch (Exception e) {
			e.printStackTrace();
		}
//		Map<String, Object> model = new HashMap();
//		String clientId=session.getAttribute("clientId").toString();
//		model.put("users", prepareListofBean(userService.listUsers(clientId),clientId));
//		model.put("actionId", getActionId());
		return new ModelAndView("redirect:/user/viewUsers.epx?actionId="+getActionId());
	}

	@RequestMapping(value = "/deleteUser")
	public ModelAndView deleteUser(@RequestParam("userSlNo") String userSlNo,HttpSession session) throws Exception{
		UserBean cb = new UserBean();
		cb.setSlno(Integer.parseInt(userSlNo));
		// deleting the user image also--------------------------------------
		
		/*File f = new File(userService.getUserImageUrl(Integer.parseInt(userSlNo)));
		if (f.exists())
			f.delete();*/
		
		userService.deleteUser(prepareModel(cb));

		Map<String, Object> model = new HashMap();
		String clientId=session.getAttribute("clientId").toString();
		model.put("users", prepareListofBean(userService.listUsers(clientId),clientId));
		model.put("actionId", getActionId());
		return new ModelAndView("user/viewUsers", model);
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(@ModelAttribute("user") UserBean userBean, HttpServletResponse response,HttpSession session) throws Exception{
		response.setHeader("Cache-Control", "no-cache");
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		Map<String, Object> model = new HashMap<String, Object>();
		userBean=prepareUserBean(userService.getUser(userBean.getSlno()));
		model.put("user",userBean );
		List<Client> clientsList = userService.listClients();
		model.put("clients", clientsList);
		List<UserRole> userRoles = userService.listUserRoles(clientId);
		model.put("roles", userRoles);
		model.put("actionId", getActionId());
		model.put("clientId", clientId);
//------------------------------------------------------------------------------------------------
		if(userBean.getHierarchy()!=null){
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		model.put("titles", titleList);
		System.out.println("title size ----->"+titleList.size());
		//System.out.println("----->"+mbean.getLocation_id());
		Hierarchy h=hService.getHierarchy(Integer.parseInt(userBean.getHierarchy().split(",")[0]));
		int level=1;
		if(h!=null){
		for(HierarchyTitle ht:titleList){
			if(ht.getHierarchy_type_id()==h.getHierarchy_type_id()){
				level=titleList.indexOf(ht)+1;
				break;
			}else{				
			continue;
			}
			
		}
		}
		//System.out.println("----->"+level);
		model.put("level", level);
		List<Object[]> current_Hierarchy=new ArrayList();
		for(int i=0;i<userBean.getHierarchy().split(",").length;i++){
			current_Hierarchy.addAll(hService.listHierarchyById(level, Integer.parseInt(userBean.getHierarchy().split(",")[i])));
		}
		model.put("currentHierarchy", current_Hierarchy);
		}
//------------------------------------------------------------------------------------------------		
		return new ModelAndView("user/editUser", model);

	}

	@RequestMapping(value = "/viewUsers", method = RequestMethod.GET)
	public ModelAndView viewUserPage(@RequestParam("actionId") String actionId,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		String clientId=session.getAttribute("clientId").toString();
		model.put("users", prepareListofBean(userService.listUsers(clientId),clientId));
		setActionId(actionId);
		model.put("actionId", actionId);
		return new ModelAndView("user/viewUsers", model);

	}

	private Users prepareModel(UserBean userBean) {
		Users users = new Users();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");

			users.setSlno(userBean.getSlno());
			users.setUserId(userBean.getUserId());
			users.setUserFullName(userBean.getUserFullName());
			users.setAddress(userBean.getAddress());
			users.setEmail(userBean.getEmail());
			users.setMobileNumber(userBean.getMobileNumber());
			users.setUserImage(userBean.getUserImage());
			users.setClientId(userBean.getClientId());
			users.setPassword(userBean.getPassword());
			users.setRollId(userBean.getRollId());
			users.setActiveFlg(userBean.getActiveFlg());
			users.setHierarchy(userBean.getHierarchy());
			
			users.setNew_password(userBean.getNew_password());
		} catch (Exception e) {
		}

		return users;

	}

	private List<UserBean> prepareListofBean(List<Users> users,String clientId) {
		List<Client> clientsList = userService.listClients();
		List<UserRole> userRoles = userService.listUserRoles(Integer.parseInt(clientId));
		Map<Integer, String> clientMap = new HashMap();
		Map<Integer, String> roleMap = new HashMap();
		for (Client c : clientsList) {
			clientMap.put(c.getClient_id(), c.getClient_name());
		}
		for (UserRole u : userRoles) {
			roleMap.put(u.getRole_id(), u.getRole_title());
		}
		List<UserBean> beans = null;
		if (users != null && !users.isEmpty()) {
			beans = new ArrayList<UserBean>();
			UserBean bean = null;
			for (Users user : users) {
				bean = new UserBean();
				bean.setSlno(user.getSlno());
				bean.setUserId(user.getUserId());
				bean.setUserFullName(user.getUserFullName());
				bean.setClientId(user.getClientId());
				bean.setRollId(user.getRollId());
				bean.setAddress(user.getAddress());
				bean.setEmail(user.getEmail());
				bean.setMobileNumber(user.getMobileNumber());
				//bean.setPassword(new UtilityClass().decryptPass(user.getPassword()));
				bean.setPassword(user.getPassword());
				bean.setUserImage(user.getUserImage());
				bean.setActiveFlg(user.getActiveFlg());
                bean.setHierarchy(user.getHierarchy());
				
				bean.setRoll_name(roleMap.get(user.getRollId()));
				bean.setClient_name(clientMap.get(user.getClientId()));
				beans.add(bean);
			}
		}
		return beans;
	}

	private UserBean prepareUserBean(Users user) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		UserBean bean = new UserBean();
		bean.setSlno(user.getSlno());
		bean.setUserId(user.getUserId());
		bean.setUserFullName(user.getUserFullName());
		bean.setClientId(user.getClientId());
		bean.setRollId(user.getRollId());
		bean.setAddress(user.getAddress());
		bean.setEmail(user.getEmail());
		bean.setMobileNumber(user.getMobileNumber());
		 bean.setHierarchy(user.getHierarchy());
		 bean.setNew_password(user.getNew_password());
		//bean.setPassword(new UtilityClass().decryptPass(user.getPassword()));
		 bean.setPassword(user.getPassword());
		String img = user.getUserImage();
		bean.setUserImage(img == null ? "" : img.substring(img.indexOf("img")));
		bean.setActiveFlg(user.getActiveFlg());

		return bean;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

}
