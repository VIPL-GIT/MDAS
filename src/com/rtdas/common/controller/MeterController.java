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

import com.rtdas.common.beans.DeviceBean;
import com.rtdas.common.beans.IEDMasterBean;
import com.rtdas.common.beans.MeterBean;
import com.rtdas.common.models.Feeder;
import com.rtdas.common.models.HTConsumer;
import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.IEDMaster;
import com.rtdas.common.models.Meter;
import com.rtdas.common.models.MeterMake;
import com.rtdas.common.service.ConsumerService;
import com.rtdas.common.service.DTRService;
import com.rtdas.common.service.DataLoggerService;
import com.rtdas.common.service.DeviceService;
import com.rtdas.common.service.FeederService;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.IEDService;
import com.rtdas.common.service.MeterService;


@Controller
@RequestMapping(value="/meter")
public class MeterController {
	@Autowired	
	private MeterService meterService;
	@Autowired
	private HierarchyTitleService titleService;
	@Autowired 
	private HierarchyService hService;
	@Autowired
	private DataLoggerService loggerService;
	@Autowired
	private IEDService iedservice;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private FeederService fservice;
	@Autowired
	private DTRService dtservice;
	@Autowired
	private ConsumerService cservice;
	
	
public String actionId;
	
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	@RequestMapping(value="/addMeter" ,method=RequestMethod.GET)
	public ModelAndView addMeter(HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		List titleList=titleService.listDeviceLocatedTitles(clientId);
		model.put("installpoints",meterService.getInstallPoints());
		model.put("titles", titleList);
		model.put("actionId", getActionId());
		return new ModelAndView("meter/addMeter",model);
	}
	
	
	@RequestMapping(value="/addMeter" ,method=RequestMethod.POST)
	public ModelAndView saveData(@ModelAttribute("meter") MeterBean meter) throws Exception{
		//System.out.println("meter controller "+meter.getLocation_id());
		try{
			meterService.addMeter(prepareModel(meter));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/meter/viewMeter.epx?actionId="+getActionId());
	}
	
	@RequestMapping(value="/changeMeter" ,method=RequestMethod.POST)
	public ModelAndView changeMeter(@RequestParam Map<String,String> reqmap) throws Exception{
		//System.out.println("meter controller "+meter.getLocation_id());
		try{
			Meter m=new Meter();
			m.setMeter_sl_no(reqmap.get("meter_sl_no"));
			m.setMe_ct(reqmap.get("me_ct"));
			m.setMe_pt(reqmap.get("me_pt"));
			m.setAd_ct(reqmap.get("ad_ct"));
			m.setAd_pt(reqmap.get("ad_pt"));
			m.setMf(reqmap.get("mf"));
			m.setMeter_type(reqmap.get("meter_type"));
			m.setMetermake(reqmap.get("metermake"));
			
			String oldMeter=reqmap.get("oldMeter");
			
			meterService.changeMeter(m, oldMeter);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/meter/viewMeter.epx?actionId="+getActionId());
	}
	
	
	/*@RequestMapping(value="/deleteMeter")
	public ModelAndView deleteMeter(@RequestParam("meter_id") String meterId,HttpSession session) throws Exception{
		Meter cb=new Meter();
		cb.setMeter_id(Integer.parseInt(meterId));
		meterService.deleteMeter(cb);
		
		return new ModelAndView("redirect:/meter/viewMeter.sms?actionId="+getActionId());
	}
	*/
	
	@RequestMapping(value="/deleteMeter")
	public ModelAndView deleteMeter(@RequestParam("meter_id") String meterId,HttpSession session) throws Exception{
		MeterBean cb=new MeterBean();
		cb.setMeter_id(Integer.parseInt(meterId));
		meterService.deleteMeter(prepareModel(cb));
		Map<String, Object> model = new HashMap();
		List<Integer> childIds=(List<Integer>)session.getAttribute("childIds");
		model.put("meters", prepareListofBean(meterService.listMeters(childIds)));
	
		model.put("actionId", getActionId());
		return new ModelAndView("meter/viewMeter",model);
	}
	
	
	@RequestMapping(value="/editMeter" ,method=RequestMethod.GET)
	public ModelAndView editModem(@ModelAttribute("meter") MeterBean meterBean,HttpSession session) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		MeterBean mbean=prepareModemBean(meterService.getMeter((meterBean.getMeter_id())));
		model.put("meter",mbean );
//-------------------------------------------------------------------------------		
		model.put("installpoints",meterService.getInstallPoints());
		
		  List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		  List<HierarchyTitle>
		  locationtitleList=titleService.listTitles(clientId);
		  model.put("titles", titleList); 
		  model.put("locationtitles",locationtitleList); 
		  System.out.println("----->"+mbean.getLocation_id());
		  if(mbean.getLocation_id()!=null){
			  try{
			  Hierarchy h=null;
			  if(mbean.getMeterlocation().equals("8"))  //HT
					h=  hService.getHierarchy(cservice.getConsumer(Integer.parseInt(mbean.getLocation_id())).getFeeder_id());
			  if(mbean.getMeterlocation().equals("7"))  //Feeder
					h=  hService.getHierarchy(fservice.getFeeder(Integer.parseInt(mbean.getLocation_id())).getSubstationid());
			 
			  //System.out.println("-------"+h.getHierarchy_name());
		  if(h!=null) {
			  int level=1; int locLevelId=h.getHierarchy_type_id();
		  for(HierarchyTitle ht:titleList){
		  if(ht.getHierarchy_type_id()==h.getHierarchy_type_id()){
		  level=titleList.indexOf(ht)+1; break; }else{ continue; }
		  
		  } 
		  System.out.println("sssssss----->"+level);
		  model.put("level", level);
		  model.put("locLevelId", locLevelId);
		 // HTConsumer cons=cservice.getConsumer(Integer.parseInt(mbean.getLocation_id()));
		  Feeder fed=fservice.getFeeder(Integer.parseInt(mbean.getLocation_id()));
		  model.put("feeder", fed);
		  model.put("currentHierarchy", hService.listHierarchyById(level,fed.getSubstationid()));
		  }
			  }catch(Exception e){
				  e.printStackTrace();
			  }
		  }//if location id is not null
		 
//-------------------------------------------------------------------------------		
		model.put("actionId", getActionId());
		return new ModelAndView("meter/editMeter",model);
		
	}
	
	
	
	@RequestMapping(value="/viewMeter" ,method=RequestMethod.GET)
	public ModelAndView viewMeters(@RequestParam("actionId")String actionId,HttpSession session) throws Exception{
		System.out.println("start time : "+new java.util.Date());
		Map<String, Object> model = new HashMap();
		List<Integer> childIds=(List<Integer>)session.getAttribute("childIds");
		//System.out.println("chils --> "+childIds);
		model.put("meters", prepareListofBean(meterService.listMeters(childIds)));
		setActionId(actionId);
		model.put("actionId", actionId);
		System.out.println("end time : "+new java.util.Date());
		return new ModelAndView("meter/viewMeter",model);
	}
	
	
		public List<IEDMasterBean> prepareIEDListBean(List<IEDMaster> iedm){
		List<IEDMasterBean> iedbeanlist=new ArrayList();;
		if(iedm!=null && !iedm.isEmpty()){			
           for(IEDMaster im:iedm){
        	   IEDMasterBean imb=new IEDMasterBean();
        	   imb.setFw_version(im.getFw_version());
        	   imb.setHw_version(im.getHw_version());
        	   imb.setIed_id(im.getIed_id());
        	   imb.setIed_sl_no(im.getIed_sl_no());
        	   imb.setLogger_id(im.getLogger_id());        	  
        	   iedbeanlist.add(imb);
           }		
			
		}	
		return iedbeanlist;		
	}
		
		
		private Meter prepareModel(MeterBean meterBean){
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			Meter meter=new Meter();
			meter.setMeter_id(meterBean.getMeter_id());
			meter.setMeter_sl_no(meterBean.getMeter_sl_no());
			meter.setModem_sl_no(meterBean.getModem_sl_no());
			meter.setLocation_id(meterBean.getLocation_id());
			
			meter.setMeterlocation(meterBean.getMeterlocation());
			meter.setMetermake(meterBean.getMetermake());
			meter.setMeter_type(meterBean.getMeter_type());
			meter.setMe_ct(meterBean.getMe_ct());
			meter.setMe_pt(meterBean.getMe_pt());
			meter.setAd_ct(meterBean.getAd_ct());
			meter.setAd_pt(meterBean.getAd_pt());
			meter.setMf(meterBean.getMf());
			
			
			try{			
				meter.setInstallation_date(meterBean.getInstallation_date());
			}catch(Exception e){e.printStackTrace();}
			return meter;
			
		}

		
		
    private List<DeviceBean> prepareListofDeviceBean(List<Object[]> listdevice){
	List<DeviceBean> dbList=new ArrayList<DeviceBean>();
	for(Object[] obj:listdevice){
		DeviceBean db=new DeviceBean();
		db.setDeviceId(Integer.parseInt(obj[0].toString()));
		db.setDeviceTypeName(obj[1].toString());
		db.setDeviceMakeName(obj[2].toString());
		db.setSupportedParamsCount(Integer.parseInt(obj[3].toString()));
		if(obj[4]!=null)
		 db.setSupportedBaudrate(obj[4].toString());
		db.setActiveFlg(Integer.parseInt(obj[5].toString()));
		dbList.add(db);
	}
	
	return dbList;
}

private List<MeterBean> prepareListofBean(List<Meter> meters){
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	List<MeterBean> beans = null;
	if(meters != null && !meters.isEmpty()){
		beans = new ArrayList<MeterBean>();
		MeterBean bean = null;
		Map<String,String> makes=listMakes();
		//Map<String,String> knos=getKnos();
		 Map<String,String> fmap=getFeederDetails();
		for(Meter meter : meters){
			bean = new MeterBean();
			bean.setMeter_id(meter.getMeter_id());
			bean.setMeter_sl_no(meter.getMeter_sl_no());
			
			bean.setMe_ct(meter.getMe_ct());
			bean.setMe_pt(meter.getMe_pt());
			bean.setAd_ct(meter.getAd_ct());
			bean.setAd_pt(meter.getAd_pt());
			bean.setMf(meter.getMf());
			bean.setLocation_id(meter.getLocation_id());
			bean.setMeter_type(meter.getMeter_type());
			bean.setModem_sl_no(meter.getModem_sl_no());
			
			try{
				bean.setMetermake(makes.containsKey(meter.getMetermake())?makes.get(meter.getMetermake()):"");
				
			if(meter.getMeterlocation()!=null){	
				bean.setInstall_point(meter.getMeterlocation().equals("7")?"Feeder":(meter.getMeterlocation().equals("6")?"DTR":"Consumer"));
			 bean.setInstallation_date(meter.getInstallation_date());
			 
			 if(meter.getMeterlocation().equals("7")){
			  //bean.setLocation_name(fservice.getFeeder(Integer.parseInt(meter.getLocation_id())).getFeedername());
				 bean.setLocation_name(fmap.containsKey(meter.getLocation_id())?fmap.get(meter.getLocation_id()):"");
			 }
			 if(meter.getMeterlocation().equals("6"))
				  bean.setLocation_name(dtservice.getDTR(Integer.parseInt(meter.getLocation_id())).getDtrname());
			 if(meter.getMeterlocation().equals("8")){
				 
//				 HTConsumer cons=cservice.getConsumer(Integer.parseInt(meter.getLocation_id()));				 
//				 bean.setLocation_name(cons.getConsumer_code());
				 
				 //bean.setLocation_name(knos.containsKey(meter.getLocation_id())?knos.get(meter.getLocation_id()):"");
			 }
			}//if location is not null
			}catch(Exception e){
				e.printStackTrace();
			}
			beans.add(bean);
		}
	}
	//System.out.println("beans "+beans);
	return beans;
}


private MeterBean prepareModemBean(Meter meter){
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	MeterBean bean = new MeterBean();
	Map<String,String> makes=listMakes();	
	bean = new MeterBean();
	bean.setMeter_id(meter.getMeter_id());
	bean.setMeter_sl_no(meter.getMeter_sl_no());
	
	bean.setMe_ct(meter.getMe_ct());
	bean.setMe_pt(meter.getMe_pt());
	bean.setAd_ct(meter.getAd_ct());
	bean.setAd_pt(meter.getAd_pt());
	bean.setMf(meter.getMf());
	bean.setMeterlocation(meter.getMeterlocation());
	bean.setLocation_id(meter.getLocation_id());
	bean.setMeter_type(meter.getMeter_type());
	bean.setModem_sl_no(meter.getModem_sl_no());
	bean.setMetermake(meter.getMetermake());		
	
	try{
	bean.setInstallation_date((meter.getInstallation_date()));
     }catch(Exception e){}				
				
	return bean;
}


	
	private List<DeviceBean> listofBean(List<Object[]> listdevice){
		List<DeviceBean> dbList=new ArrayList<DeviceBean>();
		for(Object[] obj:listdevice){
			DeviceBean db=new DeviceBean();
			db.setDeviceId(Integer.parseInt(obj[0].toString()));
			db.setDeviceTypeName(obj[1].toString());
			db.setDeviceMakeName(obj[2].toString());
			db.setSupportedParamsCount(Integer.parseInt(obj[3].toString()));
			if(obj[4]!=null)
			 db.setSupportedBaudrate(obj[4].toString());
			db.setActiveFlg(Integer.parseInt(obj[5].toString()));
			dbList.add(db);
		}
		
		return dbList;
	}
	
@ModelAttribute("metermakes")
public Map<String,String> listMakes(){
	Map<String,String> meterMakes=new HashMap();
	try{
	List<MeterMake> makes=meterService.listMeterMakes();
	for(MeterMake mk:makes){
		meterMakes.put(""+mk.getMETERMAKEID(), mk.getMETERMAKENAME());
	}
	}catch(Exception e){}
	return meterMakes;
}

public Map<String,String> getKnos(){
	Map<String,String> knos=new HashMap();
	try{
	List<HTConsumer> cons=cservice.listConsumers("1");
	for(HTConsumer ht:cons){
		knos.put(""+ht.getConsumer_id(), ht.getConsumer_code());
	}
	}catch(Exception e){}
	return knos;
}
public Map<String,String> getFeederDetails(){
	Map<String,String> fmap=new HashMap();
	try{
		List<Feeder> feeders=fservice.listFeeders("1");
		for(Feeder f:feeders){
			fmap.put(f.getFeederid()+"", f.getFeedername().toUpperCase());
		}
	}catch(Exception e){
		
	}
	return fmap;
}
}
