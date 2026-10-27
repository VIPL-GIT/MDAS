package com.rtdas.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.beans.ParameterMasterBean;
import com.rtdas.common.models.ParameterMaster;
import com.rtdas.common.service.ClientService;
import com.rtdas.common.service.ParameterService;

@Controller

@RequestMapping(value="/parameter")
public class ParameterController {
	@Autowired
	private ClientService clientService;
	@Autowired
	private ParameterService pService;
	public String actionId;

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	@RequestMapping(value="/viewParam" ,method=RequestMethod.GET)
	public ModelAndView parameterView(@RequestParam("actionId")String actionId,HttpSession session){
		String clientId=session.getAttribute("clientId").toString();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("parameter",parameterListBean(pService.listParameter(clientId)));
		setActionId(actionId);
		model.put("actionId", actionId);
		return new ModelAndView("parameter/viewParameter",model);
	}


	private List<ParameterMasterBean> parameterListBean(List<Object[]> listParameter) {
		List<ParameterMasterBean> beans = null;
		if(listParameter != null && !listParameter.isEmpty()){
			beans = new ArrayList<ParameterMasterBean>();
			ParameterMasterBean bean = null;
			for(Object[] list : listParameter){
				bean = new ParameterMasterBean();
				
				bean.setParameterId(Integer.parseInt(list[0].toString()));
				bean.setParameterName(list[1].toString());
				bean.setClientName(list[2].toString());
				beans.add(bean);
			}
				
			}
		return beans;
	}
	
	@RequestMapping(value="/addParam" ,method=RequestMethod.GET)
	public ModelAndView addParameter(HttpSession session){
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		Map<String, Object> model = new HashMap<String, Object>();		
		model.put("client", clientService.listClients());
		model.put("actionId", getActionId());
		model.put("clientId",clientId );
		return new ModelAndView("parameter/addParameter",model);
	}
	
	
	
	@RequestMapping(value="/addParam" ,method=RequestMethod.POST)
	public ModelAndView saveParameter(@ModelAttribute("command") ParameterMasterBean parameterMasterBean,
			BindingResult result){
		//System.out.println("---------------->kkkkkkkkkkkkkk");
		  pService.addParameterMaster(parameterModel(parameterMasterBean));
		
		
		return new ModelAndView("redirect:/parameter/viewParam.sms?actionId="+getActionId());
	}


	private ParameterMaster parameterModel(ParameterMasterBean parameterMasterBean) {
		
		ParameterMaster parameterMaster = new ParameterMaster();
		parameterMaster.setParameterId(parameterMasterBean.getParameterId());
		parameterMaster.setClientId(parameterMasterBean.getClientId());
		parameterMaster.setParameterName(parameterMasterBean.getParameterName());
		parameterMaster.setActiveFlg(parameterMasterBean.getActiveFlg());
		return parameterMaster;
	}
	
	@RequestMapping(value = "/deleteParam", method = RequestMethod.GET)
	public ModelAndView deleteParameter(@ModelAttribute("command")  ParameterMasterBean parameterMasterBean,
			BindingResult result) {
		pService.deleteParameter(parameterMasterBean.getParameterId());		
		
		return new ModelAndView("redirect:/parameter/viewParam.sms?actionId="+getActionId());
	}
	
	@RequestMapping(value = "/editParam", method = RequestMethod.GET)
	public ModelAndView editParameter(@ModelAttribute("command")  ParameterMasterBean parameterMasterBean,
			BindingResult result,HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		ParameterMaster ParameterMaster = pService.getParameterMaster(parameterMasterBean.getParameterId());
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());	
		 model.put("actionId", getActionId());
		 model.put("clientId", clientId);
		model.put("parameter",prepareParameterMaster(ParameterMaster));
		
		
		return new ModelAndView("parameter/editParameter", model);
	}
	
	
	private ParameterMasterBean prepareParameterMaster(ParameterMaster parameterMaster) {
		
		ParameterMasterBean bean = new ParameterMasterBean();
		bean.setClientId(parameterMaster.getClientId());
		bean.setParameterId(parameterMaster.getParameterId());
		bean.setParameterName(parameterMaster.getParameterName());
		bean.setActiveFlg(parameterMaster.getActiveFlg());
		
		return bean;
	}
	
	
	

}
