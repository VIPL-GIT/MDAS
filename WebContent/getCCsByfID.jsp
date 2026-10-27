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

String fid=request.getParameter("fid");

WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
ConsumerService ccService = (ConsumerService) wac.getBean("consumerService");
//List<HTConsumer> ccList=ccService.listConsumersFid(fid);
List<HTConsumer> ccList=ccService.listUnmappedConsumersFid(fid);
%>

  <div class="form-group">                                                                                         
            <label for="textfield2" class="col-xs-3 col-lg-3 control-label">Consumer</label>                           
            <div class="col-sm-4 col-lg-5 controls">
           
            <select class="form-control chosen" onchange="" id="childID" data-placeholder="Choose a Category" tabindex="1">
      <option value=""> </option> 
     <%for(int i=0;i<ccList.size();i++){
    	 HTConsumer data=(HTConsumer)ccList.get(i);
    	 %>
    	  <option value="<%=data.getConsumer_id()%>"><%=data.getConsumer_code()%> </option> 
    <% }
     %>                                        
</select>
</div>
</div>