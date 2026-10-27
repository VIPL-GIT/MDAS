<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="org.springframework.web.context.WebApplicationContext" %> 
    <%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
     <%@page import="com.rtdas.dashboard.dao.*" %>
     <%@page import="com.rtdas.reports.dao.*" %>
    <%@page import="com.rtdas.reports.beans.*" %>
    <%@page import="com.rtdas.meterdata.beans.*" %>
    <%@page import="com.rtdas.common.service.*" %>
    <%@page import="com.rtdas.common.models.*" %>
    <%@page import="java.util.*" %>

<%

String curMonth=request.getParameter("month");
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

String type=request.getParameter("type");
List<DashCriticalTampersBean> list=map.containsKey(type)?map.get(type):new ArrayList();
%>
<div class="modal-content" style="margin-left:14%;margin-top:30%;">
    <span class="biclose">&times;</span>
    <h4 style="text-align:center;"><%=type%></h4>
        
       
<div class="table-responsive" style="border:0" >
<table class="table table-advance" cellspacing="0" width="100%" id="gr10Events">
<thead>
<tr> 
    
     <td>Meter Sl.No</td>    
     <td>Event Date</td>
     <td>Last Occurred DateTime</td> 
     <td>Last Restored DateTime</td>
</tr>
</thead>
<tbody>
<%
if(!list.isEmpty()){
	for(DashCriticalTampersBean db:list){
		%>
		<tr>
		<td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(db.getMeter_serial_no())%>')"><%=db.getMeter_serial_no() %></a></td>
		<td><%=db.getEvent_date() %></td>
		<td><%=db.getLast_o_timestamp() %></td>
		<td><%=db.getLast_r_timestamp() %></td>
		</tr>
	<%}
}
%>
</tbody>
</table>
</div>

    </div>
    
<script>
callDataTable('gr10Events');


</script>    