<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="com.rtdas.reports.dao.*" %>
    <%@page import="com.rtdas.reports.beans.*" %>
    <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="assets/plugins/modals/modal-component.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<%
DashboardDAO ddao=new DashboardDAO();	
Map<String,List<DashCriticalTampersBean>> map=ddao.getDashCriticalTampersBean(new ArrayList());
%>

<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="table1">
<thead>
 <tr>
    <th colspan="2">Critical Tempers  (Not restored)</th>
  </tr>
  </thead>
  <tbody>
   <%
   int modelNo=1;
    for(Map.Entry em:map.entrySet()){
    	List<DashCriticalTampersBean> list=(List<DashCriticalTampersBean>)em.getValue();
    	%>
    	<tr>
    	<td><%=em.getKey() %></td>
    	<td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ctmodal-"+modelNo><%=list.size() %></button></td>
    	</tr>
    <%
    modelNo++;
    }
   %>
    <tr>
    <td>Any Event (Restoration time > 10 mins)</td>
    <td></td>
  </tr>
</tbody>
 </table>

</div>
<!-- ----------------------------models---------------------------------------------- -->
<%
    modelNo=1;
    for(Map.Entry em:map.entrySet()){
    	List<DashCriticalTampersBean> list=(List<DashCriticalTampersBean>)em.getValue();
    	%>
    	<div class="md-modal md-effect-10" id="ctmodal-"+modelNo>
    <div class="md-content">
        <h3><%=em.getKey() %></h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="table1">
<thead>
<tr> <td>Meter Sl.No</td>
     <td>Event Date</td>
     <td>Last Occurred DateTime</td> 
     <td>Last Restored DateTime</td>
</tr>
</thead>
<tbody>
 <%
 try{
   for(DashCriticalTampersBean dtb:list){
	  %>
	  <tr>
	  <td><%=dtb.getMeter_serial_no() %></td>
	  <td><%=dtb.getEvent_date()%></td>
	  <td><%=dtb.getLast_o_timestamp() %></td>
	  <td><%=dtb.getLast_r_timestamp() %></td>
	  </tr> 
  <% }
 }catch(Exception e){}
 %>
</tbody>
</table>
<button class="btn btn-base md-close">Close me!</button>
</div>
        </div>
    </div>
</div>
    <%
    modelNo++;
    }
   %>


<div class="md-overlay"></div>
</body>
</html>
<script src="assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js"></script>
 <!-- modals js -->
        <script src="assets/plugins/modals/classie.js"></script>
        <script src="assets/plugins/modals/modalEffects.js"></script>