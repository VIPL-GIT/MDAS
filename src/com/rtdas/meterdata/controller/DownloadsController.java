package com.rtdas.meterdata.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.models.Meter;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.common.service.MeterService;
import com.rtdas.mdm.beans.D5_data;
import com.rtdas.mdm.beans.EnergyTODBean;
import com.rtdas.mdm.models.D3_b3;
import com.rtdas.mdm.models.D3_columns;
import com.rtdas.mdm.models.D4_columns;
import com.rtdas.mdm.models.D4_data;
import com.rtdas.mdm.models.D5_columns;
import com.rtdas.mdm.models.D6_columns;
import com.rtdas.mdm.models.D6_data;
import com.rtdas.meterdata.beans.Rawdata_availabilityBean;
import com.rtdas.meterdata.beans.TotalMasterdataBean;
import com.rtdas.meterdata.daoimpl.DownloadsDao;
import com.rtdas.meterdata.models.Billing_summary;
import com.rtdas.meterdata.models.Device_summary;
import com.rtdas.meterdata.models.MobileLogs;
import com.rtdas.meterdata.models.MobileUploads;
import com.rtdas.reports.dao.TotalMasterDAO;

@Controller
@RequestMapping(value = "/Downloads")
public class DownloadsController {

	@Autowired
	private DownloadsDao ddao;
	@Autowired
	private TotalMasterDAO masterdao;
	@Autowired
	private HierarchyTitleService tService;
	@Autowired
	private HierarchyService hservice;
	@Autowired
	private HierarchyTitleService titleService;
	@Autowired	
	private MeterService meterService;
	
	@Value("${rawdata_path}")
	private String rawdata_path;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHomepage(HttpSession session) throws Exception{
    	Map<String, Object> model = new HashMap();
    	
    	return new ModelAndView("downloads/download_home", model);
	}
	
	@RequestMapping(value = "/deviceSummary", method = RequestMethod.GET)
	public ModelAndView getdeviceSummary(HttpSession session) throws Exception{
    	Map<String, Object> model = new HashMap();
    	try{
    		List<TotalMasterdataBean> master=new ArrayList();			
			List<Integer> ids=new ArrayList();
			
    		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
    		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
    		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
    		System.out.println("clientId -->  "+clientId);
    		//System.out.println("userHierarchyid---------------------"+userHierarchyids[0]);
    		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
    		
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
			if (userHierarchyids!=null  ){    //for company level user
				
				for(String sid:userHierarchyid.split(",")){
					ids.add(Integer.parseInt(sid));
				}
				master=masterdao.getTotalMasterDataBysdID(ids,level);
				
			}
    		
    		System.out.println("list------------------"+master.size());
    		if(master.size()>0){
    			List<String> meters=new ArrayList();
    			Map<String,String> meter_types=new HashMap();
    			
    			for(TotalMasterdataBean tmb:master){
    				meters.add(tmb.getMeter_sl_no());
    				meter_types.put(tmb.getMeter_sl_no(), tmb.getCategory());
    			}
    		
    		  List<Device_summary> list=ddao.getDeviceSummary(meters);
    		  
    		  System.out.println("d summary---------------"+list.size());
    		  model.put("list", list);
    		  model.put("category", meter_types);
    		}
    	}catch(Exception e){
    		
    	}
    	return new ModelAndView("downloads/deviceSummary", model);
	}
	
    public ModelAndView getBillingSummary(HttpSession session) throws Exception{
    	Map<String, Object> model = new HashMap();
    	try{
    		List<TotalMasterdataBean> master=new ArrayList();			
			List<Integer> ids=new ArrayList();
			
    		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
    		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
    		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
    		System.out.println("clientId -->  "+clientId);
    		//System.out.println("userHierarchyid---------------------"+userHierarchyids[0]);
    		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
    		
    		model.put("installpoints",meterService.getInstallPoints());
    		model.put("titles", titleList);
    		model.put("userHierarchyid", userHierarchyid);
    		List<Integer> ssIds=new ArrayList();
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
    		List<Meter> metersList=meterService.listMetersByLocation(ssIds, 0);
    		
    		//System.out.println("list------------------"+master.size());
    		if(!metersList.isEmpty()){
    			List<String> meters=new ArrayList();
    			for(Meter m:metersList){
    				meters.add(m.getMeter_sl_no());
    			}
    			//System.out.println("meters------------------"+meters.size());
    		  List<Billing_summary> list=ddao.getBillingSummary(meters,ssIds);
    		  
    		  System.out.println("d summary---------------"+list.size());
    		  model.put("list", list);
    		}
    	}catch(Exception e){
    		
    	}
    	return new ModelAndView("downloads/billingSummary", model);
	}
	
	@RequestMapping(value = "/masterdata", method = RequestMethod.GET)
    public ModelAndView getmasterdata(HttpSession session) throws Exception{
    	Map<String, Object> model = new HashMap();
    	List<TotalMasterdataBean> master=new ArrayList();
    	try{
    		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
    		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
    		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
    		System.out.println("clientId -->  "+clientId);
    		//System.out.println("userHierarchyid---------------------"+userHierarchyids[0]);
    		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
    		
    		model.put("installpoints",meterService.getInstallPoints());
    		model.put("titles", titleList);
    		model.put("userHierarchyid", userHierarchyid);
    		List<Integer> ssIds=new ArrayList();
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
    		if (userHierarchyids!=null){    //for company level user
    			List<Integer> ids=new ArrayList();
    			for(String sid:userHierarchyid.split(",")){
    				ids.add(Integer.parseInt(sid));
    			}
    			master=masterdao.getTotalMasterDataBysdID(ids,level);
    			
    		}
    		
    		model.put("issues", masterdao.getDiscomIssues());
    		
    		model.put("master", master);
    	}catch(Exception e){
    		
    	}
    	return new ModelAndView("downloads/masterdata", model);
	}
	
	@RequestMapping(value = "/mobileUsersLogs", method = RequestMethod.GET)
    public ModelAndView getmobileUsersLogs(@RequestParam("month")String month,HttpSession session) throws Exception{
    	Map<String, Object> model = new HashMap();
    	try{
    		System.out.println("-----------"+month);
    		List<MobileLogs> list=ddao.getMobileLogs(month);
    		model.put("list", list);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return new ModelAndView("downloads/mobileUsersLogs", model);
	}
	
	@RequestMapping(value = "/updateDiscomIssue", method = RequestMethod.GET)
    public ModelAndView updateDiscomIssue(@RequestParam("meter")String meter,
    		                              @RequestParam("issue")String issue,
    		                              HttpSession session) throws Exception{
    	Map<String, Object> model = new HashMap();
    	try{
    		System.out.println("-----------"+meter);
    		model.put("meter", meter);
    		model.put("issue", issue);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return new ModelAndView("downloads/updateDiscomIssue", model);
	}
	@RequestMapping(value = "/updateDiscomIssueAction", method = RequestMethod.GET)
    public String updateDiscomIssueAction(@RequestParam("meter")String meter,
    		                              @RequestParam("issue")String issue,
    		                              HttpSession session) throws Exception{
    	String result="";
    	try{
    		System.out.println("-----------"+meter);
    		result=masterdao.updateMeterIssue(meter,issue);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return result;
	}
	
	@RequestMapping(value = "/mobileUploads", method = RequestMethod.GET)
    public ModelAndView getmobileUploads(HttpSession session) throws Exception{
    	Map<String, Object> model = new HashMap();
    	try{
    		
    		List<MobileUploads> list=ddao.getMobileUploads();
    		model.put("list", list);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return new ModelAndView("downloads/mobileUploads", model);
	}
	
	@RequestMapping(value = "/rawdata", method = RequestMethod.GET)
    public ModelAndView getrawdata(@RequestParam("month") String month,HttpSession session) throws Exception{
    	Map<String, Object> model = new HashMap();
    	try{
    		List<Integer> ids=new ArrayList();
    		List<TotalMasterdataBean> master=new ArrayList();
    		
    		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
    		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
    		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
    		System.out.println("clientId -->  "+clientId);
    		//System.out.println("userHierarchyid---------------------"+userHierarchyids[0]);
    		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
    		
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
			if (userHierarchyids!=null  ){    //for company level user
				
				for(String sid:userHierarchyid.split(",")){
					ids.add(Integer.parseInt(sid));
				}
				master=masterdao.getTotalMasterDataBysdID(ids,level);
				
			}
    		
			List<Rawdata_availabilityBean> list=new ArrayList();
			month=month.replace("/", "-");
			String filesPath=rawdata_path+File.separator+month;
			List<String> meters=ddao.availableMeters(filesPath);
			
			System.out.println(filesPath+"--total availmeters------------"+meters.size());
			
			for(TotalMasterdataBean tmb:master){
				
				Rawdata_availabilityBean rb=new Rawdata_availabilityBean();
				if(meters.contains(tmb.getMeter_sl_no())){ 
					rb.setIsAvailable("YES");
					rb.setPath(filesPath+File.separator+tmb.getMeter_sl_no());
					}
				else{ 
					rb.setIsAvailable("NO");
				    rb.setPath("");
				}
				
				rb.setTmb(tmb);
				
				list.add(rb);
			}
    		
    		
    		model.put("list", list);
    	}catch(Exception e){
    		
    	}
    	return new ModelAndView("downloads/rawdata", model);
	}
	
	@RequestMapping(value = "/showFiles", method = RequestMethod.GET)
    public ModelAndView showFiles(@RequestParam("month") String month,@RequestParam("meter") String meter,HttpSession session) throws Exception{
    	Map<String, Object> model = new HashMap();
    	try{
    		month=month.replace("/", "-");
    		File dir=new File(rawdata_path+File.separator+month+File.separator+meter);
    		File[] files=dir.listFiles();
    		List<String> fileNames=new ArrayList();
    		for(File f:files){
    			fileNames.add(f.getName());
    		}
    		
    		model.put("files", fileNames);
    		model.put("month", month);
            }catch(Exception e){
    		
    	}
    	return new ModelAndView("downloads/showFiles", model);
	}
	
	@RequestMapping(value = "/downloadFiles", method = RequestMethod.GET)
    public void downloadFiles(@RequestParam("month") String month,@RequestParam("filename") String filename,
    		HttpServletRequest req,HttpServletResponse res,HttpSession session) throws Exception{
    	Map<String, Object> model = new HashMap();
    	try{
    		month=month.replace("/", "-");
    		String meter=filename.split("_")[0];
    		String dataDirectory=rawdata_path+File.separator+month+File.separator+meter;
    		File rawfile=new File(dataDirectory+File.separator+filename);
    		Path file = Paths.get(dataDirectory, filename);
    		
    		res.setContentType("APPLICATION/OCTET-STREAM");   
            res.addHeader("Content-Disposition", "attachment; filename="+filename);
            try
            {
                Files.copy(file, res.getOutputStream()); 
                res.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            
            }catch(Exception e){
    		
    	}
    	
	}
	
//--------------------------------for uploads----------------------------------------
	@RequestMapping(value = "/uploadType", method = RequestMethod.GET)
    public ModelAndView getUploadHomepage(HttpSession session) throws Exception{
		System.out.println("uploadType");
    	Map<String, Object> model = new HashMap();
    	
    	return new ModelAndView("downloads/uploadType", model);
	}
	
	
	
	
	
	@RequestMapping(value="/proceed",method = RequestMethod.POST)
    public @ResponseBody String doproceed(@RequestParam("file") MultipartFile file,HttpSession session) {    
		
		   Map<String, Object> model = new HashMap();
	        //String path=session.getServletContext().getRealPath("/");
	       // String path = servletContext.getInitParameter("file-upload");
		   
	        String filename=file.getOriginalFilename();  
	        String path =null;
	        
	  
	        
if (filename.contains(".emd")){
	   path = session.getServletContext().getInitParameter("file-upload-EMD-server");
}
else if(filename.contains(".gxt")){
	 path = session.getServletContext().getInitParameter("file-upload-GXT-server");
}else{
	 path = session.getServletContext().getInitParameter("file-upload-MIOS-server");
}
		    
		    System.out.println(path+" "+filename);
	        try{  
	        byte barr[]=file.getBytes();  
	        BufferedOutputStream bout=new BufferedOutputStream(  
	        new FileOutputStream(path+"/"+filename));  
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
	        /*XLSXReader xlsxreader=new XLSXReader();
			xlsxreader.readFile(path,filename,session);*/
	        }catch(Exception e){System.out.println(e);}  
	        return filename+" Uploaded Succesfully";
    }
	
	@RequestMapping(value="/downloadAllProfilesData")
	public void downloadAllProfilesData(@RequestParam Map<String, String> reqParam,HttpServletRequest req,HttpServletResponse res,HttpSession session) {
		try{
			String meter=reqParam.get("meter");
			String sdate=reqParam.get("sdate");
			String edate=reqParam.get("edate");
			
			String fileName=meter+"_Profiles.xls";
		  Workbook workbook = new HSSFWorkbook();
	      Sheet d1 = workbook.createSheet("Meter details");
	      Sheet d3 = workbook.createSheet("Energy data");
	      Sheet d3_b4 = workbook.createSheet("Energy TOD");
	      Sheet d4 = workbook.createSheet("Load data");
	      Sheet d5 = workbook.createSheet("Events data");
	      Sheet d6 = workbook.createSheet("Snapshot data");
//------------writing d1 data--------------------------------------------------	      
	      TotalMasterdataBean tb=ddao.getMeterDetails(meter);
	      Row r1=d1.createRow(0); r1.createCell(0).setCellValue("Meter Serial Number");
	                              r1.createCell(1).setCellValue(tb.getMeter_sl_no());
          Row r2=d1.createRow(1); r2.createCell(0).setCellValue("Region");
	                              r2.createCell(1).setCellValue(tb.getRegion());
	      Row r3=d1.createRow(2); r3.createCell(0).setCellValue("SubDivision");
	                              r3.createCell(1).setCellValue(tb.getSd());
	      Row r4=d1.createRow(3); r4.createCell(0).setCellValue("Location Name");
	                              r4.createCell(1).setCellValue(tb.getLocation_name());
	      Row r5=d1.createRow(4); r5.createCell(0).setCellValue("Location Code");
	                              r5.createCell(1).setCellValue(tb.getLocation_code());
	      Row r6=d1.createRow(5); r6.createCell(0).setCellValue("MF");
	                              r6.createCell(1).setCellValue(tb.getMf()==null?1:tb.getMf());
	      Row r7=d1.createRow(6); r7.createCell(0).setCellValue("From Date");
	                              r7.createCell(1).setCellValue(sdate);
	      Row r8=d1.createRow(7); r8.createCell(0).setCellValue("To Date");
	                              r8.createCell(1).setCellValue(edate);
	                    	      
//----------------writing energy data----------------------------------------------
	       D3_columns d3_header=ddao.getD3_header(meter,"b3"); 
	       List<D3_b3> d3_list=ddao.getD3_values(meter,sdate,edate);
	       Row row = d3.createRow(0);
	        row.createCell(0).setCellValue("Date Time");
	        for(int i=0;i<d3_header.getBilling_columns_header().split(",").length;i++) {	        	
	        	row.createCell(i+1).setCellValue(d3_header.getBilling_columns_header().split(",")[i].toUpperCase());
	        }
	        int rowNo=1;
	        for(D3_b3 b3:d3_list){
	        	Row nrow = d3.createRow(rowNo);
	        	nrow.createCell(0).setCellValue(b3.getBilling_day());
	        	for(int i=0;i<b3.getData_string().split(",").length;i++) {	        	
		        	nrow.createCell(i+1).setCellValue(b3.getData_string().split(",")[i]);
		        }
	        	rowNo++;
	        }
//----------------writing energy TOD data----------------------------------------------
	        D3_columns d3b4_header=ddao.getD3_header(meter,"b4"); 		       
	        List<String> b4_header=new ArrayList();
	        for(String str:d3b4_header.getBilling_columns_header().split(",")){
	        	if(!b4_header.contains(str)) b4_header.add(str);
	        }
	        List<EnergyTODBean> b4_list=ddao.getD3_b4_values(meter,sdate,edate,b4_header.size());
	        for(EnergyTODBean etb:b4_list){
	        	//System.out.println(etb.getBilling_date()+"\t"+etb.getTod()+"\t"+etb.getValueString());
	        }
		       Row b4row = d3_b4.createRow(0);
		        b4row.createCell(0).setCellValue("Date Time");
		        b4row.createCell(1).setCellValue("TOD");
		        for(int i=0;i<b4_header.size();i++) {	        	
		        	b4row.createCell(i+2).setCellValue(b4_header.get(i).toUpperCase());
		        }
		        rowNo=1;
		        for(EnergyTODBean etb:b4_list){
		        	//System.out.println(etb.getBilling_date()+"\t"+etb.getTod()+"\t"+etb.getValueString());
		        	Row lnrow = d3_b4.createRow(rowNo);
		        	lnrow.createCell(0).setCellValue(etb.getBilling_date());
		        	lnrow.createCell(1).setCellValue(etb.getTod());
		        	for(int i=0;i<etb.getValueString().split(",").length;i++){
		        		lnrow.createCell(i+2).setCellValue(etb.getValueString().split(",")[i]);
		        	}
		        	rowNo++;
		        }
//---------------writing load survey data------------------------------------------------	        
	        D4_columns d4_header=ddao.getD4_header(meter); 
		       List<D4_data> d4_list=ddao.getD4_values(meter,sdate,edate);
		       Row lrow = d4.createRow(0);
		        lrow.createCell(0).setCellValue("Date Time");
		        for(int i=0;i<d4_header.getLoad_columns_header().split(",").length;i++) {	        	
		        	lrow.createCell(i+1).setCellValue(d4_header.getLoad_columns_header().split(",")[i].toUpperCase());
		        }
		         rowNo=1;
		        for(D4_data d4d:d4_list){
		        	Row lnrow = d4.createRow(rowNo);
		        	lnrow.createCell(0).setCellValue(d4d.getD4_date());
		        	for(int i=0;i<d4d.getData_string().split(",").length;i++) {	        	
			        	lnrow.createCell(i+1).setCellValue(d4d.getData_string().split(",")[i]);
			        }
		        	rowNo++;
		        }
//---------------writing events data------------------------------------------------	        
	        D5_columns d5_header=ddao.getD5_header(meter); 
		       List<D5_data> d5_list=ddao.getD5_values(meter,sdate,edate);
		       Row erow = d5.createRow(0);
		        erow.createCell(0).setCellValue("Date Time");
		        erow.createCell(1).setCellValue("Event ");
		        erow.createCell(2).setCellValue("Event Status");
		        if(d5_header!=null){
			        for(int i=0;i<d5_header.getTamper_columns_header().split(",").length;i++) {	        	
			        	erow.createCell(i+3).setCellValue(d5_header.getTamper_columns_header().split(",")[i].toUpperCase());
			        }
		        }
		         rowNo=1;
		        for(D5_data d5d:d5_list){
		        	Row enrow = d5.createRow(rowNo);
		        	enrow.createCell(0).setCellValue(d5d.getEvent_date());
		        	enrow.createCell(1).setCellValue(d5d.getEvent_type());
		        	enrow.createCell(2).setCellValue(d5d.getEvent_status());
		        	if(d5d.getData_string()!=null){
		        	for(int i=0;i<d5d.getData_string().split(",").length;i++) {	        	
			        	enrow.createCell(i+3).setCellValue(d5d.getData_string().split(",")[i]);
			        }
		        	}
		        	rowNo++;
		        }	
//----------------writing snapshot data---------------------------------------------------------------		        
		        D6_columns d6_header=ddao.getD6_header(meter); 
			       List<D6_data> d6_list=ddao.getD6_values(meter,sdate,edate);
			       Row srow = d6.createRow(0);
			        srow.createCell(0).setCellValue("Date Time");
			        if(d6_header!=null){ 
			        for(int i=0;i<d6_header.getTamper_columns_header().split(",").length;i++) {	        	
			        	srow.createCell(i+1).setCellValue(d6_header.getTamper_columns_header().split(",")[i].toUpperCase());
			        }
			        }
			         rowNo=1;
			        for(D6_data d6d:d6_list){
			        	Row snrow = d6.createRow(rowNo);
			        	snrow.createCell(0).setCellValue(d6d.getSNAPSHOT_DATE());			        	
			        	for(int i=0;i<d6d.getDATA_STRING().split(",").length;i++) {	        	
				        	snrow.createCell(i+1).setCellValue(d6d.getDATA_STRING().split(",")[i]);
				        }
			        	rowNo++;
			        }
			        
			        
	      res.setContentType("application/vnd.ms-excel");   //for xls format(.xls)
          res.addHeader("Content-Disposition", "attachment; filename="+fileName);
          OutputStream os=res.getOutputStream();
          workbook.write(os);
          os.flush();
	    
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    	
}
