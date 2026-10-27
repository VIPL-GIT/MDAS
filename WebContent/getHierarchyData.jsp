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

String parentId=request.getParameter("id");
System.out.println("hierarcgt id --> "+parentId);
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
HierarchyService hService = (HierarchyService) wac.getBean("hierarchyService");
List<Hierarchy> hList=hService.getHierarchyByParentId(Integer.parseInt(parentId));
%>
  
      <option value=""> </option> 
     <%for(int i=0;i<hList.size();i++){
    	 Hierarchy data=(Hierarchy)hList.get(i);
    	 %>
    	  <option value="<%=data.getHierarchy_id()%>"><%=data.getHierarchy_name()%> </option> 
    <% }
     %>                                        
