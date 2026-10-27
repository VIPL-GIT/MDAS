package com.rtdas.common.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.beans.ModemBean;
import com.rtdas.common.models.Modem;
import com.rtdas.common.service.ModemService;

@Controller
@RequestMapping(value="/modem")

public class ModemController {
	@Autowired	
	private ModemService modemService; 
    public String actionId;
	
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	@RequestMapping(value="/addModem" ,method=RequestMethod.GET)
	public ModelAndView addModem() throws Exception{
		Map<String,String> model=new HashMap();
		model.put("actionId", getActionId());
		return new ModelAndView("modem/addModem",model);
	}
	@RequestMapping(value="/addModem" ,method=RequestMethod.POST)
	public ModelAndView saveModem(@ModelAttribute("modem") ModemBean modemBean) throws Exception{
		try{
		  modemService.addModem(prepareModel(modemBean));
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String,String> model=new HashMap();
		model.put("actionId", getActionId());
		return new ModelAndView("redirect:/modem/viewModem.epx?actionId="+getActionId());
	}
	@RequestMapping(value="/editModem" ,method=RequestMethod.GET)
	public ModelAndView editModem(@ModelAttribute("modem") ModemBean modemBean) throws Exception{
		System.out.println("edit modem controller and modem id"+modemBean.getModemId());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("modem", prepareModemBean(modemService.getModem(modemBean.getModemId())));
		model.put("actionId", getActionId());
		return new ModelAndView("modem/editModem",model);
		
	}
	
	@RequestMapping(value="/viewModem" ,method=RequestMethod.GET)
	public ModelAndView viewModems(@RequestParam("actionId")String actionId) throws Exception{
		Map<String, Object> model = new HashMap();
		model.put("modems", prepareListofBean(modemService.listModems()));
		setActionId(actionId);
		model.put("actionId", actionId);
		System.out.println("action Id --> "+actionId);
		return new ModelAndView("modem/viewModem",model);
		
	}
	@RequestMapping(value="/deleteModem")
	public ModelAndView deleteModem(@RequestParam("modemId") String modemId) throws Exception{
		ModemBean cb=new ModemBean();
		cb.setModemId(Integer.parseInt(modemId));
		modemService.deleteModem(prepareModel(cb));
		Map<String, Object> model = new HashMap();
		model.put("modems", prepareListofBean(modemService.listModems()));
		model.put("actionId", getActionId());
		return new ModelAndView("modem/viewModem",model);
	}
	private Modem prepareModel(ModemBean modemBean){
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		Modem modem=new Modem();
		modem.setModem_id(modemBean.getModemId());
		modem.setModem_serial_no(modemBean.getModemSlNo());
		modem.setHw_version(modemBean.getHw_version());
		modem.setFw_version(modemBean.getFw_version());
		modem.setMobile_no(modemBean.getMobile());
		modem.setDelivery_date(modemBean.getDelivery_date());
		modem.setStatic_ip(modemBean.getIp());
		modem.setSim_no(modemBean.getSim_no());
		modem.setImei_no(modemBean.getImei_no());
		try{
			
		//modem.setDelivery_date(new java.sql.Date(sdf.parse(modemBean.getDelivery_date()).getTime()));
			modem.setDelivery_date(modemBean.getDelivery_date());
			
		}catch(Exception e){e.printStackTrace();}
		return modem;
		
	}
	
	private List<ModemBean> prepareListofBean(List<Modem> modems){
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		List<ModemBean> beans = null;
		if(modems != null && !modems.isEmpty()){
			beans = new ArrayList<ModemBean>();
			ModemBean bean = null;
			for(Modem modem : modems){
				bean = new ModemBean();
				bean.setModemId(modem.getModem_id());
				bean.setModemSlNo(modem.getModem_serial_no());
				bean.setFw_version(modem.getFw_version());
				bean.setHw_version(modem.getHw_version());
				bean.setMobile(modem.getMobile_no());
				bean.setImei_no(modem.getImei_no());
				bean.setSim_no(modem.getSim_no());
				bean.setIp(modem.getStatic_ip());
				try{
				bean.setDelivery_date(sdf.format(modem.getDelivery_date()));
			     }catch(Exception e){}
				beans.add(bean);
			}
		}
		return beans;
	}
	private ModemBean prepareModemBean(Modem modem){
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		ModemBean bean = new ModemBean();
			
				bean.setModemId(modem.getModem_id());
				bean.setModemSlNo(modem.getModem_serial_no());
				bean.setFw_version(modem.getFw_version());
				bean.setHw_version(modem.getHw_version());
				bean.setMobile(modem.getMobile_no());
				bean.setImei_no(modem.getImei_no());
				bean.setSim_no(modem.getSim_no());
				bean.setIp(modem.getStatic_ip());
				try{
				bean.setDelivery_date(sdf.format(modem.getDelivery_date()));
			     }catch(Exception e){}				
					
		return bean;
	}

}
