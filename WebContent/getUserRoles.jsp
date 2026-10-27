<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.rtdas.common.service.*" %>
<%@page import="com.rtdas.common.models.*" %>
<%@page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%

String clientId=request.getParameter("clientId");
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
UserRoleService roleObject = (UserRoleService) wac.getBean("roleService");
List<UserRole> rolesList=roleObject.getUserRolesByClient(Integer.parseInt(clientId));
//System.out.println("list size------------->"+rolesList.size());
%>
  <select class="form-control chosen" id="role" data-placeholder="Choose a Category" tabindex="1">
      <option value=""> </option> 
     <%for(int i=0;i<rolesList.size();i++){
    	 UserRole role=(UserRole)rolesList.get(i);
    	 %>
    	  <option value="<%=role.getRole_id()%>"><%=role.getRole_title() %> </option> 
    <% }
     %>                                        
                                          
   </select>                                                                             
