<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="com.rtdas.common.service.*" %>
<%@page import="com.rtdas.common.models.*" %>
<%@page import="java.util.*" %>


<%

int levelId=Integer.parseInt(request.getParameter("levelId"));
int level=Integer.parseInt(request.getParameter("level"));
int install_type=Integer.parseInt(request.getParameter("install_type"));

//int levelId=10;
//int level=8;


System.out.println(levelId+"level -->  "+level+"  "+install_type);


WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
MeterService dtService = (MeterService) wac.getBean("meterService");
HierarchyService hservice = (HierarchyService) wac.getBean("hierarchyService");
HierarchyTitleService tservice = (HierarchyTitleService) wac.getBean("titleService");
int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
List<HierarchyTitle> titleList=tservice.listDeviceLocatedTitles(clientId);


/* System.out.println("-----------"+clientId);
System.out.println("-----------"+titleList); */


List<Integer> ssIds=new ArrayList();
if(level!=titleList.size()){
Hierarchy hie=hservice.getHierarchy(levelId);
System.out.println(level+"\t"+levelId+"\t"+hie.getHierarchy_id());		
	List<Object[]> childlist=hservice.listAllChildsByParentId(level,titleList.size(),hie.getHierarchy_id());
for(Object[] objArray:childlist) {
	ssIds.add(Integer.parseInt(objArray[0].toString()));
}
}else{
	ssIds.add(levelId);
}

System.out.println("-----------   "+ssIds);
List<Meter> dtList=dtService.listMetersByLocation(ssIds, install_type);
%>

  <div class="col-md-2">                                                                                        
            <label  class="control-label">Meter Sl No</label>  
            <select class="form-control chosen-select" onchange="getEnergies(this.value)" id="childID" data-placeholder="Choose a Category" tabindex="1">
      <option value=""> </option> 
     <%for(int i=0;i<dtList.size();i++){
    	 Meter data=(Meter)dtList.get(i);
    	 System.out.println("data --> "+data);
    	 %>
    	  <option value="<%=data.getMeter_id()%>"><%=data.getMeter_sl_no()%> </option> 
    <% }
     %>                                        
</select>
</div>

