<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.rtdas.dashboard.dao.*" %>
    <%@page import="com.rtdas.reports.dao.*" %>
    <%@page import="com.rtdas.reports.beans.*" %>
    <%@page import="com.rtdas.meterdata.beans.*" %>
    <%@page import="java.util.*" %>
    <%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="com.rtdas.common.service.*" %>
<%@page import="com.rtdas.common.models.*" %>
<%@page import="com.rtdas.reports.dao.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
$(document).ready(function() {
    $('#lowPF').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Low_PF'},
             {extend:'excelHtml5',
                 title:'Low_PF'},
              {extend:'csvHtml5',
                 title:'Low_PF'}
           
        ]
    } );
    
    $('#mdviolate').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'MD_violated'},
             {extend:'excelHtml5',
                 title:'MD_violated'},
              {extend:'csvHtml5',
                 title:'MD_violated'}
        ]
    } );
    
    $('#pfLag').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'PF_Lag'},
             {extend:'excelHtml5',
                 title:'PF_Lag'},
              {extend:'csvHtml5',
                 title:'PF_Lag'}
        ]
    } );
    
    $('#pfLead').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'PF_Lead'},
             {extend:'excelHtml5',
                 title:'PF_Lead'},
              {extend:'csvHtml5',
                 title:'PF_Lead'}
        ]
    } );
} );
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link href="assets/plugins/modals/modal-component.css" rel="stylesheet" type="text/css"/>
        <!-- START THEME LAYOUT STYLE -->
</head>
<body>
<%
// DashboardDAO ddao=new DashboardDAO();	
// DashMeterDataAnalysisBean mdaBean=ddao.getDashMeterDataAnalysisBean();

// //-----------getting total masterdaata-------------
// TotalMasterDAO mdao=new TotalMasterDAO();
// List<TotalMasterdataBean> master=mdao.getTotalMasterData();
// Map<String,TotalMasterdataBean> masterMap=new HashMap();
// if(!master.isEmpty()){
// 	for(TotalMasterdataBean tmb:master){
// 		masterMap.put(tmb.getMeter_sl_no(),tmb);
// 	}
// }
//------------------------------------------------------------------------------------
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


DashMeterDataAnalysisBean mdaBean=ddao.getDashMeterDataAnalysisBean(meters);

//-----------getting total masterdaata-------------

List<TotalMasterdataBean> master=mdao.getTotalMasterData();
Map<String,TotalMasterdataBean> masterMap=new HashMap();
if(!master.isEmpty()){
	for(TotalMasterdataBean tmb:master){
		masterMap.put(tmb.getMeter_sl_no(),tmb);
	}
}

%>

<div class="md-modal md-effect-10" id="modal-1">
    <div class="md-content">
        <h3>Low PF Meters</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="lowPF">
<thead>
<tr> 
     <td>KNO</td>
     <td>Meter Sl.No</td>
     <td>Install point</td>
     <td>Location Name</td>
     <td>PF Value</td> 
</tr>
</thead>
<tbody>
 <%
 try{
   for(Map.Entry em:mdaBean.getLowPf_meters().entrySet()){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(em.getKey())?knoURL+masterMap.get(em.getKey()).getLocation_code():"" %>" target="_blank">
	  <%=masterMap.containsKey(em.getKey())?masterMap.get(em.getKey()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(em.getKey())%>')"><%=em.getKey() %></a></td>
	  <td><%=masterMap.containsKey(em.getKey())?masterMap.get(em.getKey()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(em.getKey())?masterMap.get(em.getKey()).getLocation_name():"" %></td>
	  
	  <td><%=em.getValue() %></td>
	  
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

<div class="md-modal md-effect-10" id="modal-2">
    <div class="md-content">
        <h3>MD Violated Meters</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="mdviolate">
<thead>
<tr> 
<td>KNO</td>
<td>Meter Sl.No</td>
<td>Install point</td>
     <td>Location Name</td>
     <td>MD Value</td> 
</tr>
</thead>
<tbody>
 <%
 try{
   for(Map.Entry em:mdaBean.getMdViolation_meters().entrySet()){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(em.getKey())?knoURL+masterMap.get(em.getKey()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(em.getKey())?masterMap.get(em.getKey()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(em.getKey())%>')"><%=em.getKey() %></a></td>
	  
	  <td><%=masterMap.containsKey(em.getKey())?masterMap.get(em.getKey()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(em.getKey())?masterMap.get(em.getKey()).getLocation_name():"" %></td>
	  <td><%=em.getValue() %></td>
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

<div class="md-modal md-effect-10" id="mdamodal-3">
    <div class="md-content">
        <h3>PF Lag Meters</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="pfLag">
<thead>
<tr>
 <td>KNO</td>
 <td>Meter Sl.No</td>
<td>Install point</td>
     <td>Location Name</td>
     <td>PF</td> 
</tr>
</thead>
<tbody>
 <%
 try{
   for(Map.Entry em:mdaBean.getPf_lag_meters().entrySet()){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(em.getKey())?knoURL+masterMap.get(em.getKey()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(em.getKey())?masterMap.get(em.getKey()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(em.getKey())%>')"><%=em.getKey() %></a></td>
	  
	  <td><%=masterMap.containsKey(em.getKey())?masterMap.get(em.getKey()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(em.getKey())?masterMap.get(em.getKey()).getLocation_name():"" %></td>
	  <td><%=em.getValue() %></td>
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

<div class="md-modal md-effect-10" id="mdamodal-4">
    <div class="md-content">
        <h3>PF Lead Meters</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="pfLead">
<thead>
<tr> 
<td>KNO</td>
<td>Meter Sl.No</td>
<td>Install point</td>
     <td>Location Name</td>
     <td>PF</td> 
</tr>
</thead>
<tbody>
 <%
 try{
   for(Map.Entry em:mdaBean.getPf_lead_meters().entrySet()){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(em.getKey())?knoURL+masterMap.get(em.getKey()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(em.getKey())?masterMap.get(em.getKey()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(em.getKey())%>')"><%=em.getKey() %></a></td>
	  
	  <td><%=masterMap.containsKey(em.getKey())?masterMap.get(em.getKey()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(em.getKey())?masterMap.get(em.getKey()).getLocation_name():"" %></td>
	  <td><%=em.getValue() %></td>
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
                                                
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="table1">
<thead>
<tr>
<th colspan="2">Meter Data Analysis Summary</th>
</tr>
</thead>


 <tr>
    <td>PF Lag / Lead</td>
    <td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="mdamodal-3" title="PF Lag"><%=mdaBean.getPf_lag_meters().size() %></button> / 
        <button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="mdamodal-4" title="PF Lead"><%=mdaBean.getPf_lead_meters().size() %></button> </td>    
  </tr>
  <tr>
    <td>Low Power Factor</td>
    <td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="modal-1"><%=mdaBean.getLowPf_meters().size() %></button></td>
  </tr>
  <tr>
    <td>M Demand > C Demand</td>
    <!-- btn-base -->
    <td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="modal-2"><%=mdaBean.getMdViolation_meters().size() %></button></td>
  </tr>

</table>


</div>


<div class="md-overlay"></div>
                                                
</body>
</html>
<script src="assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js"></script>
 <!-- modals js -->
        <script src="assets/plugins/modals/classie.js"></script>
        <script src="assets/plugins/modals/modalEffects.js"></script>

