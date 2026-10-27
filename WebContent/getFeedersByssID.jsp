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

String ssid=request.getParameter("ssid");

WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
FeederService hService = (FeederService) wac.getBean("feederService");
List<Feeder> hList=hService.listFeedersByssid(Integer.parseInt(ssid));
%>

  <div class="form-group">                                                                                         
            <label for="textfield2" class="col-xs-3 col-lg-3 control-label">Feeders</label>                           
            <div class="col-sm-4 col-lg-5 controls">
           
            <select class="form-control chosen" onchange="getChilds(this.value)" id="feederID" data-placeholder="Choose a Category" tabindex="1">
<!--  <select class="form-control chosen"  id="feederID" data-placeholder="Choose a Category" tabindex="1"> -->
      <option value=""> </option> 
     <%for(int i=0;i<hList.size();i++){
    	 Feeder data=(Feeder)hList.get(i);
    	 %>
    	  <option value="<%=data.getFeederid()%>"><%=data.getFeedername()%> </option> 
    <% }
     %>                                        
</select>
</div>
</div>