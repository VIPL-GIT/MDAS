package com.rtdas.common.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.models.HTConsumer;
import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.service.ConsumerService;
import com.rtdas.common.service.FeederService;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import com.rtdas.meterdata.beans.MasterdataBean;
import com.rtdas.reports.dao.TotalMasterDAO;

import utils.MyCell;

@Controller
@RequestMapping(value = "/cc")
public class ConsumerController {
	
	public String actionId;

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	@Autowired
	private HierarchyTitleService titleService;
	@Autowired
	private ConsumerService cservice;
	@Autowired 
	private HierarchyService hService;
	@Autowired
	private FeederService fservice;
	@Autowired
    private ServletContext servletContext;
	@Autowired
	private TotalMasterDAO tmdao;

	@Resource(name = "excelPOIHelper")
    private ExcelPOIHelper excelPOIHelper;
	
	@RequestMapping(value = "/addCC", method = RequestMethod.GET)
	public ModelAndView addUserPage(HttpSession session) throws Exception{
		System.out.println("add consumer controler");
		Map<String, Object> model = new HashMap();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		//List titleList=titleService.listDeviceLocatedTitles(clientId);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		
		model.put("titles", titleList);	
		model.put("actionId", getActionId());
		model.put("clientId",clientId );
		return new ModelAndView("Consumer/addConsumer", model);
	}
	
	@RequestMapping(value = "/editCC", method = RequestMethod.GET)
	public ModelAndView editUser(@RequestParam("ccid") int consumerId, HttpServletResponse response,HttpSession session) throws Exception{
		System.out.println("edit cc");
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		Map<String, Object> model = new HashMap<String, Object>();
		HTConsumer cc=cservice.getConsumer(consumerId);
		model.put("cc",cc);
		model.put("actionId", getActionId());
		model.put("clientId", clientId);
//------------------------------------------------------------------------------------------------
		//------------------------------------------------------------------------------------------------
		System.out.println("feeder id --> "+cc.getFeeder_id());
				//Feeder f=fservice.getFeeder(cc.getFeeder_id());
				List<HierarchyTitle> titleList=titleService.listTitles(clientId);
				List<HierarchyTitle> locationtitleList=titleService.listDeviceLocatedTitles(clientId);
				
				model.put("titles", titleList);
				model.put("locationtitles", locationtitleList);
				
				
					Hierarchy h=hService.getHierarchy(cc.getFeeder_id());
					int level=1;
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
					
					model.put("currentHierarchy", hService.listHierarchyById(level, cc.getFeeder_id()));
					
		//------------------------------------------------------------------------------------------------
		
//------------------------------------------------------------------------------------------------		
		return new ModelAndView("Consumer/editConsumer", model);

	}
	
	@RequestMapping(value="/addCC" ,method=RequestMethod.POST)
	public ModelAndView saveData(@ModelAttribute("cc") HTConsumer ht) throws Exception{
		try{
			cservice.addConsumer(ht);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/cc/viewCC.epx?actionId="+getActionId());
	}
	
	@RequestMapping(value = "/viewCC", method = RequestMethod.GET)
	public ModelAndView viewClients(@RequestParam("actionId") String actionId,HttpSession session) throws Exception{
		System.out.println("viewCC");
		Map<String, Object> model = new HashMap();
		System.out.println("--------------------->>>>"+new java.util.Date());
			
			try {
				String clientId =session.getAttribute("clientId").toString();
				setActionId(actionId);
				model.put("actionId", actionId);
				//Map<Integer, String> feeders=fservice.getFeeders(clientId);
				List<HTConsumer> clist=cservice.listConsumers(clientId);
				Map<Integer,MasterdataBean> tmMap=tmdao.getMasterLocationMap();
				for(HTConsumer ht:clist) {
					
					try {
//						
//						Hierarchy h=hService.getHierarchy(ht.getFeeder_id());
//						Hierarchy parent_h=hService.getHierarchy(h.getParent_id());
//						ht.setFeeder_name(h.getHierarchy_name());
//						ht.setParent_name(parent_h.getHierarchy_name());
						if(tmMap.containsKey(ht.getFeeder_id())){
							ht.setFeeder_name(tmMap.get(ht.getFeeder_id()).getSd());
							ht.setParent_name(tmMap.get(ht.getFeeder_id()).getRegion());
						}else{
							ht.setFeeder_name("");
							ht.setParent_name("");
						}
						
						}catch(Exception e) {e.printStackTrace();}
				}
				model.put("hts",clist);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("--------------------->>>>"+new java.util.Date());
			
		return new ModelAndView("Consumer/viewConsumer", model);

	}
	

	@RequestMapping(value = "/deleteConsumer")
	public ModelAndView deleteUser(@RequestParam("cid") int cid,HttpSession session) throws Exception{
		
		cservice.deleteConsumer(cid);   
		
		return new ModelAndView("redirect:/cc/viewCC.epx?actionId="+getActionId());
	}
	
//----------------------------for bulk uploading -----------------------------------
	@RequestMapping(value="/bulkHome")
	public ModelAndView getBulkHome() throws Exception{
		System.out.println("bulk home");
         return new ModelAndView("Consumer/ConsumerBulkUploadHome");
	}
	@RequestMapping(value="/download")
	public void downloadBulkHome(HttpServletRequest req,HttpServletResponse res,HttpSession session) throws Exception{

		System.out.println("consumer download ");
		
		try {
			int clientId =Integer.parseInt(session.getAttribute("clientId").toString());			
			List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		 String templateHeader="";
		 //consumer props
		 templateHeader=templateHeader+"CONSUMER NAME@KNO@SANCTION LOAD@SUPPLY VOLTAGE@CONTRACT DEMAND@CONTACT NUMBER@ADDRESS@CATEGORY";
		 //meter props
		  templateHeader=templateHeader+"@Meter@METER TYPE@Meter Make@Installed date@ME_CT@ME_PT@AD_CT@AD_PT@MF";
		  //modem props
		 // templateHeader=templateHeader+"@Modem@Vendor@SIM no@Mobile";
		 
		 String fileName="consumer_template.xls";
		String dataDirectory = req.getServletContext().getRealPath("/WEB-INF/");
//		File f=new File(dataDirectory+fileName);
//		FileOutputStream fos=new FileOutputStream(f);
//		fos.write(templateHeader.getBytes());
	//--------------writing the headers into file-----------------------------------------	
		Workbook workbook = new HSSFWorkbook();
	    Sheet sheet = workbook.createSheet();
	  //--------------------------------------------------------------
	    DataFormat fmt = workbook.createDataFormat();
	    CellStyle textStyle = workbook.createCellStyle();
	    textStyle.setDataFormat(fmt.getFormat("@"));
	    for(int i=0;i<templateHeader.split("@").length;i++) {
	    sheet.setDefaultColumnStyle(i, textStyle);
	    }
//----------------------------------------------------------------		 
	        Row row = sheet.createRow(0);
	        for(int i=0;i<2;i++) {	        	
	        	row.createCell(i).setCellValue(titleList.get(i).getHierarchy_title().toUpperCase());
	        }
	        int lastRow=row.getLastCellNum();
	        System.out.println("--------------------------->>"+lastRow);
	        for(int i=0;i<templateHeader.split("@").length;i++) {
	        	Cell cell=row.createCell(i+lastRow);
	        	cell.setCellValue(templateHeader.split("@")[i]);
	        	cell.setCellStyle(textStyle);
	        }
	 
	           System.out.println("dire "+dataDirectory);
		   	   System.out.println("fileName  "+fileName);
	        
	    try (FileOutputStream outputStream = new FileOutputStream(dataDirectory+"/"+fileName)) {
	        workbook.write(outputStream);
	    }
//---------------------------------------------------------------------------------------------------------	    
        Path file = Paths.get(dataDirectory, fileName);
        
        if (Files.exists(file))
        {
        	
        	res.setContentType("application/vnd.ms-excel");   //for xls format(.xls)
            res.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
                Files.copy(file, res.getOutputStream()); 
                res.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
		
	    }
		}catch(Exception e) {
			e.printStackTrace();
		}

		

}
@RequestMapping(value="/bulkUpload",method=RequestMethod.POST)	
	
	public String  uploadFile(Model model, MultipartHttpServletRequest request,HttpSession session){
		 String fileLocation;

		try {
			MultipartFile file = request.getFile("uploadFile");
			String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/template/");
		InputStream in = file.getInputStream();
	    File currDir = new File(".");
	    String path = currDir.getAbsolutePath();
			    //fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
			    fileLocation=dataDirectory+"/temp/"+file.getOriginalFilename();
		        FileOutputStream f = new FileOutputStream(fileLocation);
		        int ch = 0;
		        while ((ch = in.read()) != -1) {
		            f.write(ch);
		        }
		        f.flush();
		        f.close();
		        
		        if (fileLocation != null) {
		            if (fileLocation.endsWith(".xls")) {
		            	Map<Integer, List<MyCell>> data = excelPOIHelper.readHSSFWorkbook(new FileInputStream(fileLocation));		                
		                model.addAttribute("data", data);
		                //-----------setting validation string -------------------
		                //System.out.println(data.size());
		                boolean valid=true;
		                for(Map.Entry em:data.entrySet()) {
		                	//System.out.println(""+em.getKey());
		                	List<MyCell> clist=(List<MyCell>)em.getValue();
		                	//System.out.println(clist);
		                	if(!clist.isEmpty()) {
		                	for(MyCell cell:clist) {
//		                		if(Integer.parseInt(em.getKey().toString())==399)
//		                			System.out.println(cell.getContent());
		                		if(cell.getContent().length()==0) {
		                			valid=false;
		                			break;
		                		}
		                	}
		                	}//if
		                }
		                
		                System.out.println("------------------------"+valid);
		                model.addAttribute("status",valid==true?"valid":"invalid");
		            } else {
		                model.addAttribute("message", "Not a valid excel file!");
		            }
		        } else {
		            model.addAttribute("message", "File missing! Please upload an excel file.");
		        }
		        
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "Consumer/ConsumerExcelData";
	}

@RequestMapping(value="/processRow",method=RequestMethod.POST)
@ResponseBody
	public String  processRow(@RequestParam Map<String,String> map,HttpSession session){
	
	 String result="k";
	 try {
		 int clientId =Integer.parseInt(session.getAttribute("clientId").toString());
		 
		 List<String> values=Arrays.asList(map.get("values").split("@"));
		 List<String> header=Arrays.asList(map.get("header").split(","));
		
		 //values.remove(values.size()-1); 
		 try {
		 result=cservice.processRowInsertion(header,values, clientId);
		 }catch(Exception e) {
			 result="<img src='images/wrong.png' height='25px'/>";
			 e.printStackTrace();
			// System.out.println("----------->"+e.getMessage());
			 return result;
		 }
		 
	 }catch(Exception e) {
		 result="<img src='images/wrong.png' height='25px'/>";
		 e.printStackTrace();
		 return result;
	 }
	 
	 return result;
	
    }

}
