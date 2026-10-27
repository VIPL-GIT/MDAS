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
System.out.println("--------------------1---"+titleId);
//-----------------added to get the hierarchy as per selected level-----------------
// if(titleId==23 || titleId==24) {}
// else if(titleId==8) titleId=23;
// else if(titleId!=8) titleId=24;

if(titleId==7 || titleId==8) titleId=30;   //added to get the hierarchy in add meter/edit meter

System.out.println("--------------------2---"+titleId);
int level=0;
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
HierarchyTitleService tService = (HierarchyTitleService) wac.getBean("titleService");
HierarchyService hService = (HierarchyService) wac.getBean("hierarchyService");
String clientId=session.getAttribute("clientId").toString();
List<HierarchyTitle> tList=tService.listTitles(Integer.parseInt(clientId));
HierarchyTitle firstTitle=(HierarchyTitle)tList.get(0);
List<Hierarchy> firstHierarchy=hService.getHierarchyByType(firstTitle.getHierarchy_type_id());
for(int i=0;i<tList.size();i++){
	HierarchyTitle htitle=(HierarchyTitle)tList.get(i);
	if(htitle.getHierarchy_type_id()==titleId)
		level=i+1;
	
}
if(titleId!=0){
%>
  <input type="hidden" id="level" value="<%=level%>"/>
     <%
}
     for(int i=0;i<level;i++){
    	 HierarchyTitle title=(HierarchyTitle)tList.get(i);
    	 %>
    	 <div class="form-group col-md-12">                                                                                         
            <label for="textfield2" class="col-xs-3 col-lg-3 control-label"><%=title.getHierarchy_title() %></label>                           
            <div class="col-sm-4 col-lg-5 controls">
               <%if(i+1==tList.size()) {%>
            <select class="form-control chosen" onchange="getFeeders(this.value,'feederSel')"   
                id="level_<%=i+1%>" data-placeholder="Choose a Category" tabindex="1">
            <%}
               if(i+1==level) {%>
            <select class="form-control chosen" onchange="getChilds(this.value)"   
            id="level_<%=i+1%>" data-placeholder="Choose a Category" tabindex="1">
              <%}
               else{ %>
                <select class="form-control chosen" onchange="getHierarchyData(this.value,'level_<%=i+2%>')"  
                id="level_<%=i+1%>" data-placeholder="Choose a Category" tabindex="1">
                <%} %>
                  <option value=""> </option> 
                  <%if(i==0){
                	  for(int l=0;l<firstHierarchy.size();l++){
                		  Hierarchy hie=(Hierarchy)firstHierarchy.get(l);
                	  %>
                	  <option value="<%=hie.getHierarchy_id()%>"><%=hie.getHierarchy_name()%> </option>
               <%  } }
                  %> 
                 </select>                                                                             
           </div> 
        </div> 
    <% }
     %>                                        
