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

import com.rtdas.common.models.Feeder;
import com.rtdas.common.models.Hierarchy;
import com.rtdas.common.models.HierarchyTitle;
import com.rtdas.common.service.FeederService;
import com.rtdas.common.service.HierarchyService;
import com.rtdas.common.service.HierarchyTitleService;
import utils.MyCell;

@Controller
@RequestMapping(value = "/feeder")
public class FeederController {
	
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
	private FeederService fservice;
	@Autowired 
	private HierarchyService hService;
	@Autowired
    private ServletContext servletContext;
	
	@Resource(name = "excelPOIHelper")
    private ExcelPOIHelper excelPOIHelper;
	
	@RequestMapping(value = "/addFeeder", method = RequestMethod.GET)
	public ModelAndView addUserPage(HttpSession session) throws Exception{
		System.out.println("add feeder controller ");
		Map<String, Object> model = new HashMap();
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		List<HierarchyTitle> locationtitleList=titleService.listDeviceLocatedTitles(clientId);
		
		System.out.println(clientId+" "+titleList);
		model.put("titles", titleList);
		model.put("actionId", getActionId());
		model.put("clientId",clientId );
		System.out.println("add feeder");
		
		
		
		model.put("locationtitles", locationtitleList);
		return new ModelAndView("Feeder/addFeeder", model);
	}
	
	@RequestMapping(value = "/editFeeder", method = RequestMethod.GET)
	public ModelAndView editUser(@RequestParam("feederid") int feederid, HttpServletResponse response,HttpSession session) throws Exception{
		System.out.println("edit controller");
		int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
		Map<String, Object> model = new HashMap<String, Object>();
		Feeder f=fservice.getFeeder(feederid);
		System.out.println("feeder --> "+f);
		model.put("feeder",f);
		model.put("actionId", getActionId());
		model.put("clientId", clientId);
//------------------------------------------------------------------------------------------------
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		List<HierarchyTitle> locationtitleList=titleService.listDeviceLocatedTitles(clientId);
		model.put("titles", titleList);
		model.put("locationtitles", locationtitleList);
		
		System.out.println(titleList+"  titleList "+locationtitleList+" "+f.getSubstationid());
		if(f.getSubstationid()!=null){
			Hierarchy h=hService.getHierarchy(f.getSubstationid());
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
			System.out.println("level ----->"+level);
			model.put("level", level);
			model.put("locLevelId", locLevelId);
			System.out.println(level+" level "+locLevelId);
			model.put("currentHierarchy", hService.listHierarchyById(level, f.getSubstationid()));
			}
//------------------------------------------------------------------------------------------------		
		return new ModelAndView("Feeder/editFeeder", model);

	}
	
	@RequestMapping(value="/addFeeder" ,method=RequestMethod.POST)
	public ModelAndView saveData(@ModelAttribute("feeder") Feeder feeder) throws Exception{
		try{
			fservice.addFeeder(feeder);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/feeder/viewFeeder.epx?actionId="+getActionId());
	}
	@RequestMapping(value = "/viewFeeder", method = RequestMethod.GET)
	public ModelAndView viewFeeders(@RequestParam("actionId") String actionId,HttpSession session) throws Exception{
		
		Map<String, Object> model = new HashMap();
		
	    try {
	    	String clientId =session.getAttribute("clientId").toString();
	    	Map<Integer,String> hlist=hService.listHierarchyByClient(Integer.parseInt(clientId));
	    	List<Feeder> flist=fservice.listFeeders(clientId);
	    	//System.out.println("------------->"+hlist);
	    	for(Feeder f:flist) {
	    		//System.out.println("------------>"+f.getSubstation_id());
	    		if(f.getSubstationid()!=null) {
	    		 f.setSubstation_name(hlist.containsKey(f.getSubstationid())?hlist.get(f.getSubstationid()):"");
	    		}
	    	}
	    	model.put("flist",flist);
	    	setActionId(actionId);
			model.put("actionId", actionId);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		
		return new ModelAndView("Feeder/viewFeeders", model);

	}
	
	@RequestMapping(value = "/deleteFeeder")
	public ModelAndView deleteUser(@RequestParam("feederid") int feederid,HttpSession session) throws Exception{
		
        fservice.deleteFeeder(feederid);   
		
		return new ModelAndView("redirect:/feeder/viewFeeder.epx?actionId="+getActionId());
	}
	
//--------------------------------bulk uploading -------------------------------------------------------
	
	@RequestMapping(value="/bulkHome")
	public ModelAndView getBulkHome() throws Exception{
		
         return new ModelAndView("Feeder/FeederBulkUploadHome");
	}
	
	@RequestMapping(value="/download")
	public void downloadBulkHome(HttpServletRequest req,HttpServletResponse res,HttpSession session) throws Exception{

		try {
			int clientId =Integer.parseInt(session.getAttribute("clientId").toString());			
			List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		 String templateHeader="";
		  //feeder props
		  templateHeader=templateHeader+"PTR Capacity@Feeder Name@Voltage Level@Feeder Type@Feeder Code@Conductor";
		  		//+ "@STRUCTURE CODE@VOLTAGE LEVEL@ME_CT@ME_PT@AD_CT@AD_PT";
		  //meter props
		  templateHeader=templateHeader+"@Meter@METER TYPE@Meter Make@ME_CT@ME_PT@AD_CT@AD_PT@MF";
		  //modem props
		 // templateHeader=templateHeader+"@Modem@Vendor@SIM no@Mobile";
		  
		String fileName="feeder_hierarchy_template.xls";
		String dataDirectory = req.getServletContext().getRealPath("/WEB-INF/template/");
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
	        for(int i=0;i<titleList.size();i++) {
	        	System.out.println(i+ " --> "+titleList.get(i).getHierarchy_title().toUpperCase());
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
			System.out.println("dataDir --> "+dataDirectory+" "+file);
			
		InputStream in = file.getInputStream();
		System.out.println("in "+in);
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
		               // Map<Integer, List<MyCell>> data = excelPOIHelper.readExcel(fileLocation);
		            	 Map<Integer, List<MyCell>> data = excelPOIHelper.readHSSFWorkbook(new FileInputStream(fileLocation));
		                model.addAttribute("data", data);
		                
		                //-----------setting validation string -------------------
		                boolean valid=true;
		                for(Map.Entry em:data.entrySet()) {
		                	List<MyCell> clist=(List<MyCell>)em.getValue();
		                	for(MyCell cell:clist) {
		                		if(cell.getContent().length()==0) {
		                			valid=false;
		                			break;
		                		}
		                	}
		                }
		                
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
		return "Feeder/FeederExcelData";
	}	

	
@RequestMapping(value="/processRow",method=RequestMethod.POST)
@ResponseBody
	public String  processRow(@RequestParam Map<String,String> map,HttpSession session){
	System.out.println("process row controller");
	 String result="k";
	 try {
		 int clientId =Integer.parseInt(session.getAttribute("clientId").toString());
		 List<String> values=Arrays.asList(map.get("values").split("@"));
		 List<String> header=Arrays.asList(map.get("header").split(","));
		
		 //values.remove(values.size()-1); 
		 try {
		 result=fservice.processRowInsertion(header,values, clientId);
		 }catch(Exception e) {
			 result="<img src='images/wrong.png' height='25px'/>";
			 //e.printStackTrace();
			 System.out.println("----------->"+e.getMessage());
			 return result;
		 }
		 
	 }catch(Exception e) {
		 result="<img src='images/wrong.png' height='25px'/>";
		 e.printStackTrace();
		 return result;
	 }
	 System.out.println("result --> "+result);
	 return result;
	
    }
	

}
