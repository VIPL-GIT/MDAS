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
String[] userHids=request.getParameter("userHid")!=null?request.getParameter("userHid").split(","):null;
String report=request.getParameter("report");

int level=0,levelID=0;
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());

HierarchyTitleService tService = (HierarchyTitleService) wac.getBean("titleService");
HierarchyService hService = (HierarchyService) wac.getBean("hierarchyService");
String clientId=session.getAttribute("clientId").toString();

System.out.println(userHids[0]+"========================================="+report);
List<HierarchyTitle> tList=tService.listTitles(Integer.parseInt(clientId));
HierarchyTitle firstTitle=(HierarchyTitle)tList.get(0);
List<Hierarchy> firstHierarchy=hService.getHierarchyByType(firstTitle.getHierarchy_type_id());
Hierarchy userHierarchy=null;
if(userHids!=null && userHids[0]!=""){
 userHierarchy=hService.getHierarchy(Integer.parseInt(userHids[0]));
HierarchyTitle userTitle=tService.getTitles(userHierarchy.getHierarchy_type_id());

for(int i=0;i<tList.size();i++){
	HierarchyTitle htitle=(HierarchyTitle)tList.get(i);
	if(htitle.getHierarchy_type_id()==userHierarchy.getHierarchy_type_id()){
		level=i;
		break;		
       }//if
}//for
}else{
	
}


%>

  <input type="hidden" id="level" value="<%=level%>"/>
  <input type="hidden" id="levelID" value="<%=levelID%>"/>
     <%

     for(int i=level;i<tList.size();i++){
    	 HierarchyTitle title=(HierarchyTitle)tList.get(i);
    	 %>
    	 
    	<div class="col-md-2" id="div_level_<%=i+1%>">                                                                                        
            <label  class="control-label"><%=title.getHierarchy_title() %></label>                           
            
            <%if(i+1==tList.size()) {%>
            <select class="form-control chosen ssID" 
                    id="level_<%=i+1%>" data-placeholder="Choose a Category" tabindex="1"
            onchange="getMeters_analysis(this.value,'meterSel','<%=report%>')" >
                
            <%}else{ %>
                <select class="form-control chosen-select" onchange="getHierarchyData_analysis(this.value,'level_<%=i+2%>','<%=report%>')"  
                id="level_<%=i+1%>" data-placeholder="Choose a Category" tabindex="1" >
                <%} %>
                  <option value=""> </option>
                  <% if(userHids!=null && userHids[0]!=""){
                	  for(int h=0;h<userHids.length;h++){
                		  Hierarchy hie=hService.getHierarchy(Integer.parseInt(userHids[h]));
                	  %>                 
                  <option value="<%=hie.getHierarchy_id()%>" ><%=hie.getHierarchy_name()%> </option>
                   <%}
                    }else{ 
                	   if(i==0){
                	  for(int l=0;l<firstHierarchy.size();l++){
                		  Hierarchy hie=(Hierarchy)firstHierarchy.get(l);
                	  %>
                	  <option value="<%=hie.getHierarchy_id()%>" ><%=hie.getHierarchy_name()%> </option>
               <%  } }
                  }%>
                 </select>                                                                             

        </div> 
        
       <%}%> 
      
     
<script>
$(function() {
   // $('#level_1').chosen();
//     $('.chosen-select-deselect').chosen({ allow_single_deselect: true });
  });
</script>      
                                      
      