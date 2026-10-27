<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.rtdas.common.service.*" %>
<!-- in this page we are getting hierarchy data based on the selection of parent hierarchy -->
<%@page import="com.rtdas.common.models.*" %>
<%@page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
int titleId=Integer.parseInt(request.getParameter("titleId"));
int level=0,levelID=0;
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());

HierarchyTitleService tService = (HierarchyTitleService) wac.getBean("titleService");
HierarchyService hService = (HierarchyService) wac.getBean("hierarchyService");
String clientId=session.getAttribute("clientId").toString();


List<HierarchyTitle> tList=tService.listTitles(Integer.parseInt(clientId));
HierarchyTitle firstTitle=(HierarchyTitle)tList.get(0);
List<Hierarchy> firstHierarchy=hService.getHierarchyByType(firstTitle.getHierarchy_type_id());

for(int i=0;i<tList.size();i++){
	HierarchyTitle htitle=(HierarchyTitle)tList.get(i);
	if(htitle.getHierarchy_type_id()==titleId){
		level=i+1;
		System.out.println("level --> "+level);
}
}
if(titleId!=0){
%>

  <input type="hidden" id="level" value="<%=level%>"/>
  <input type="hidden" id="levelID" value="<%=levelID%>"/>
     <%
}
     for(int i=0;i<level;i++){
    	 HierarchyTitle title=(HierarchyTitle)tList.get(i);
    	 %>
    	 
    	<div class="col-md-2">                                                                                        
            <label  class="control-label"><%=title.getHierarchy_title() %></label>                           
            
            <%if(i+1==tList.size()) {%>
            <select class="form-control chosen ssID" 
                    id="level_<%=i+1%>" data-placeholder="Choose a Category" tabindex="1"
            onchange="getMeters3(this.value,'meterSel')" >
                
            <%}else{ %>
                <select class="form-control chosen-select" onchange="getHierarchyData(this.value,'level_<%=i+2%>')"  
                id="level_<%=i+1%>" data-placeholder="Choose a Category" tabindex="1" >
                <%} %>
                  <option value=""> </option> 
                  <%if(i==0){
                	  for(int l=0;l<firstHierarchy.size();l++){
                		  Hierarchy hie=(Hierarchy)firstHierarchy.get(l);
                	  %>
                	  <option value="<%=hie.getHierarchy_id()%>" ><%=hie.getHierarchy_name()%> </option>
               <%  } }
                  %> 
                 </select>                                                                             

        </div> 
        
        
    <% }
     %>   
     
<script>
$(function() {
   // $('#level_1').chosen();
//     $('.chosen-select-deselect').chosen({ allow_single_deselect: true });
  });
</script>      
                                      
      