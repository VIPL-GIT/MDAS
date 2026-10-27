<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="com.rtdas.common.service.*" %>
<%@page import="com.rtdas.common.models.*" %>
<%@page import="com.rtdas.reports.dao.*" %>
<%@page import="java.util.*" %>


<%

int levelId=Integer.parseInt(request.getParameter("levelId"));
int level=(request.getParameter("level").contains("_")?Integer.parseInt(request.getParameter("level").split("_")[1]):Integer.parseInt(request.getParameter("level")));
int install_type=Integer.parseInt(request.getParameter("install_type"));
String search_by=request.getParameter("search_by");


System.out.println(levelId+"level -->  "+level+"  "+install_type);


WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
MeterService dtService = (MeterService) wac.getBean("meterService");
HierarchyService hservice = (HierarchyService) wac.getBean("hierarchyService");
HierarchyTitleService tservice = (HierarchyTitleService) wac.getBean("titleService");
TotalMasterDAO tmdao = (TotalMasterDAO) wac.getBean("totalMasterDao");

int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
List<HierarchyTitle> titleList=tservice.listTitles(clientId);
Map<String,String> knoMap=null;

/* System.out.println("-----------"+clientId);
System.out.println("-----------"+titleList); */


List<Integer> ssIds=new ArrayList();
if(levelId!=0 && (level==2 || level==5)){  //for SDO users and SS users user
	ssIds.add(levelId);
	//to get ssids
	List<Object[]> sslist=hservice.listAllChildsByParentId( level, titleList.size(), levelId);
	for(Object[] objArray:sslist) {
		ssIds.add(Integer.parseInt(objArray[0].toString()));
	}
	
}
else if (levelId!=0 && level==1 ){    //for company level user
	//to get ssids
	List<Object[]> sslist=hservice.listAllChildsByParentId( level, titleList.size(), levelId);
	//to get SDO ids
	List<Object[]> sdolist=hservice.listAllChildsByParentId( level+1, titleList.size(), levelId);
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

System.out.println("-----------   "+ssIds);
List<Meter> dtList=new ArrayList();
if(search_by.equals("unmapped")){
	 dtList=dtService.getUnmappedMeters();
}
else{
	 dtList=dtService.listMetersByLocation(ssIds, install_type);
}

System.out.println("======OD meters===================>>>>>>>>>>"+dtList.size());
try{
	if(!dtList.isEmpty() && search_by.equalsIgnoreCase("kno")){
	  knoMap=tmdao.getMetersKnoMap(dtList);
	  System.out.println(knoMap);
	}
}catch(Exception e){}

%>

   
   <table id="childID">
						<thead>
							<tr>
								<th></th>
								<th><%=search_by.equalsIgnoreCase("kno")?"KNO":"Meter Sl.No" %></th>
							</tr>
						</thead>
						<tbody>                                                                                     
            
     <%for(int i=0;i<dtList.size();i++){
    	 Meter data=(Meter)dtList.get(i);
    	 %>
    	 <tr><td></td>
    	 <%if(search_by.equalsIgnoreCase("kno")){
    		 %>
       	  
       	  <td>
       	  <%=knoMap.containsKey(data.getMeter_sl_no())?knoMap.get(data.getMeter_sl_no()):data.getMeter_sl_no()%> 
       	  </td>
       <% 
    	 }else{
    	 %>
    	
    	  <td><%=data.getMeter_sl_no()%> </td> 
    	  
    <%
    
     } %>
     </tr>
     <%}
     %>                                        
</tbody>
					</table>


