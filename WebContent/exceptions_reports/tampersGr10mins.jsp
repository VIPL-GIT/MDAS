<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="org.springframework.web.context.WebApplicationContext" %> 
    <%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
     <%@page import="com.rtdas.dashboard.dao.*" %>
     <%@page import="com.rtdas.reports.dao.TotalMasterDAO" %>
    <%@page import="com.rtdas.reports.beans.*" %>
    <%@page import="com.rtdas.meterdata.beans.*" %>
    <%@page import="com.rtdas.common.service.*" %>
    <%@page import="com.rtdas.common.models.*" %>
    <%@page import="java.util.*" %>
    <%@page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="assets/plugins/modals/modal-component.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<%

SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
String curMonth=request.getParameter("month");
if(curMonth==null){
	curMonth=sdf.format(new java.util.Date());
	curMonth=curMonth.substring(0, curMonth.lastIndexOf("-"));
}
else{
	//curMonth=curMonth.split("-")[1]+"-"+curMonth.split("-")[0];
}
String knoURL="http://10.40.16.26:8084/rdfcomm/crm_cons_profile_show.jsp?knopro=";

WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
ExceptionalReportsDAO ddao=(ExceptionalReportsDAO)wac.getBean("dashDao");
TotalMasterDAO mdao = (TotalMasterDAO) wac.getBean("totalMasterDao");
MeterService meterService = (MeterService) wac.getBean("meterService");

List<Integer> childIds=null;
List<String> meters=new ArrayList();
Map<String,Integer> meter_ids=new HashMap();
try{
	childIds=(List<Integer>)session.getAttribute("childIds");
	List<Meter> metersList=meterService.listMetersByLocation(childIds, 8); //0 to get all the meters
	for(Meter mt:metersList){
		meters.add(mt.getMeter_sl_no());
		meter_ids.put(mt.getMeter_sl_no(),mt.getMeter_id());
	}
}catch(Exception e){
	response.sendRedirect("/login.epx");
}	
Map<String,List<DashCriticalTampersBean>> map=ddao.getDashTampersGr10mins(meters,curMonth);


%>


<table class="table table-advance" cellspacing="0" width="100%" id="table1">

  <tbody>
   
    	
<!--     	<td>R phase potential missing</td> -->
<!--     	<td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ctmodal-rpt"> -->
<%--     	     <%=(map.containsKey("R phase potential missing")?map.get("R phase potential missing").size():0) %> --%>
<!--     	     </button></td> -->
         <%
          for(Map.Entry em:map.entrySet()){
        	  %>
        	  <tr>
        	  <td><%=em.getKey() %></td>
        	  <td><a href="javascript:tampersGr10mins_popup('<%=em.getKey()%>','<%=curMonth%>')"><%=((List<DashCriticalTampersBean>)em.getValue()).size() %></a></td>
        	  </tr>
          <%}
         %>
    	
    	
</tbody>
 </table>


<div id="bi_modal" class="modal">  
</body>
</html>
