<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Date"%>
<%@page import="utils.MeterStatusBean"%>
<%@page import="utils.GetPieGraph"%>
<%@ page import="java.sql.*"%>
<%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="com.rtdas.common.service.*" %>
<%@page import="com.rtdas.common.models.*" %>
<%@page import="com.rtdas.reports.dao.*" %>


<%
   // String selectedLevel = request.getParameter("selectedLevel").trim();
   // int selectedId = Integer.parseInt(request.getParameter("selectedId"));
    String selectedType = request.getParameter("meterType").trim();
    String selectedDate = request.getParameter("selectedDate").trim();
    List<String> meters = new ArrayList();
   // System.out.println("in pie chart "+selectedLevel + "----" + selectedId + "----" + selectedType + "----" + selectedDate);
    

    if (selectedDate.isEmpty()) {
        SimpleDateFormat formDate = new SimpleDateFormat("dd-MM-yyyy");
        selectedDate = formDate.format(new Date());
        
    }else{
     //selectedDate = selectedDate.substring(3,5) + "-" + selectedDate.substring(0,2) + "-" +selectedDate.substring(6,10) ;   
     
    }
//-------------------------getting hierarchy meters --------------------------------------------------------
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
MeterService meterService = (MeterService) wac.getBean("meterService");
HierarchyService hservice = (HierarchyService) wac.getBean("hierarchyService");
HierarchyTitleService tservice = (HierarchyTitleService) wac.getBean("titleService");
int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
List<HierarchyTitle> titleList=tservice.listTitles(clientId);

String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);  	
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
   //------------------------------------	  
       System.out.println("***********************"+selectedType);
	    		List<Meter> metersList=meterService.listMetersByLocation(ssIds, Integer.parseInt(selectedType)); //0 to get all the meters
                for(Meter mt:metersList){
                	meters.add(mt.getMeter_sl_no());
                }
//-------------------------end of getting hierarchy meters ---------------------------------------------------
    
//    List<String> meters = new GetPieGraph().gettotalMeters(selectedType);

    int totMetrs = meters.size();
    if(totMetrs>0){ 
    MeterStatusBean msb = new GetPieGraph().metersStatus(meters, selectedDate);
   try{ 
    
    out.println(totMetrs + "," + msb.getCommMeters().size() + "," + msb.getNotCommMeters().size() + "," + msb.getNeverCommMeters().size()+ ","+msb.getMcfMeters().size());    
        System.out.println(totMetrs + "," + msb.getCommMeters().size() + "," + msb.getNotCommMeters().size() + "," + msb.getNeverCommMeters().size()+ ","+msb.getMcfMeters().size());
    
    
   }catch(Exception ee){
       ee.printStackTrace();
   }
   

    HttpSession ses = request.getSession(false);
    ses.setAttribute("commMeters", msb.getCommMeters());
    ses.setAttribute("notCommMeters", msb.getNotCommMeters());
    ses.setAttribute("neverCommMeters", msb.getNeverCommMeters());
    ses.setAttribute("poweroffMeters", msb.getNeverCommMeters());
    ses.setAttribute("mcfMeters",msb.getMcfMeters());
    ses.setAttribute("unmappedCommMeters",msb.getUnmappedCommMeters());
    }else{
    	out.println("error:No Meters Available...");
    }
   
%>
