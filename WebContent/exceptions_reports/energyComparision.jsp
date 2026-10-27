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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
$(document).ready(function() {
    $('#Increase_0-10').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Increase_0-10'},
             {extend:'excelHtml5',
                 title:'Increase_0-10'},
              {extend:'csvHtml5',
                 title:'Increase_0-10'}
        ]
    } );
    
    $('#Increase_10-25').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Increase_10-25'},
             {extend:'excelHtml5',
                 title:'Increase_10-25'},
              {extend:'csvHtml5',
                 title:'Increase_10-25'}
        ]
    } );
    
    $('#Increase_25-100').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Increase_25-100'},
             {extend:'excelHtml5',
                 title:'Increase_25-100'},
              {extend:'csvHtml5',
                 title:'Increase_25-100'}
        ]
    } );
    
    $('#Increase_above100').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Increase_above100'},
             {extend:'excelHtml5',
                 title:'Increase_above100'},
              {extend:'csvHtml5',
                 title:'Increase_above100'}
        ]
    } );

    $('#Decrease_0-10').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Decrease_0-10'},
             {extend:'excelHtml5',
                 title:'Decrease_0-10'},
              {extend:'csvHtml5',
                 title:'Decrease_0-10'}
        ]
    } );
    
    $('#Decrease_10-25').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Decrease_10-25'},
             {extend:'excelHtml5',
                 title:'Decrease_10-25'},
              {extend:'csvHtml5',
                 title:'Decrease_10-25'}
        ]
    } );
    
    $('#Decrease_25-100').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Decrease_25-100'},
             {extend:'excelHtml5',
                 title:'Decrease_25-100'},
              {extend:'csvHtml5',
                 title:'Decrease_25-100'}
        ]
    } );
    
    $('#Decrease_above100').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Decrease_above100'},
             {extend:'excelHtml5',
                 title:'Decrease_above100'},
              {extend:'csvHtml5',
                 title:'Decrease_above100'}
        ]
    } );

    
} );

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="assets/plugins/modals/modal-component.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%
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
DashEnergyComparisonBean ecBean=ddao.getDashEnergyComparisonBean(meters);

//-----------getting total masterdaata-------------

List<TotalMasterdataBean> master=mdao.getTotalMasterData();
Map<String,TotalMasterdataBean> masterMap=new HashMap();
if(!master.isEmpty()){
	for(TotalMasterdataBean tmb:master){
		masterMap.put(tmb.getMeter_sl_no(),tmb);
	}
}
%>

<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="table1" style="height:159px !important;">
 <thead>
 <tr>
    <th>Energy Comaprision</th>
    <th colspan="4">Comparision of current kWh with Last month</th>
  </tr>
  </thead>
  <tbody>
  <tr>
    <td></td>
    <td>0 - 10%</td>
    <td>10-25%</td>
    <td>25-100%</td>
    <td>>100%</td>
  </tr>
 				
 				
 
 <tr>
    <td>Increasing (+)</td>
    <td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ecmodal-1"><%=ecBean.getIncr_0_10_meters().size() %></button></td>
    <td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ecmodal-2"><%=ecBean.getIncr_10_25_meters().size() %></button></td>
    <td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ecmodal-3"><%=ecBean.getIncr_25_100_meters().size() %></button></td>
    <td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ecmodal-4"><%=ecBean.getIncr_gr_100_meters().size() %></button></td>
  </tr>
 
 <tr>
    <td>Decreasing (-)</td>
    <td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ecmodal-5"><%=ecBean.getDecr_0_10_meters().size() %></button></td>
    <td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ecmodal-6"><%=ecBean.getDecr_10_25_meters().size() %></button></td>
    <td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ecmodal-7"><%=ecBean.getDecr_25_100_meters().size() %></button></td>
    <td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ecmodal-8"><%=ecBean.getDecr_gr_100_meters().size() %></button></td>
  </tr>
 </tbody>
 </table>
</div>

<div class="md-modal md-effect-10" id="ecmodal-1">
    <div class="md-content">
        <h3>Increasing (0-10%) Meters</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="Increase_0-10">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
 <td>Install point</td>
     <td>Location Name</td>
     <td>Value (%)</td> 
</tr>
</thead>
<tbody>
 <%
 try{
   for(Map.Entry em:ecBean.getIncr_0_10_meters().entrySet()){
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

<div class="md-modal md-effect-10" id="ecmodal-2">
    <div class="md-content">
        <h3>Increasing (10-25%) Meters</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="Increase_10-25">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
 <td>Install point</td>
     <td>Location Name</td>
     <td>Value (%)</td> 
</tr>
</thead>
<tbody>
 <%
 try{
   for(Map.Entry em:ecBean.getIncr_10_25_meters().entrySet()){
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

<div class="md-modal md-effect-10" id="ecmodal-3">
    <div class="md-content">
        <h3>Increasing (25-100%) Meters</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="Increase_25-100">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
 <td>Install point</td>
     <td>Location Name</td>
     <td>Value (%)</td> 
</tr>
</thead>
<tbody>
 <%
 try{
   for(Map.Entry em:ecBean.getIncr_25_100_meters().entrySet()){
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

<div class="md-modal md-effect-10" id="ecmodal-4">
    <div class="md-content">
        <h3>Increasing (>100%) Meters</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="Increase_above100">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
 <td>Install point</td>
     <td>Location Name</td>
     <td>Value (%)</td> 
</tr>
</thead>
<tbody>
 <%
 try{
   for(Map.Entry em:ecBean.getIncr_gr_100_meters().entrySet()){
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

<!-- -------------------------Decreasing %----------------------------------------------------- -->
<div class="md-modal md-effect-10" id="ecmodal-5">
    <div class="md-content">
        <h3>Decreasing (0-10%) Meters</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="Decrease_0-10">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
 <td>Install point</td>
     <td>Location Name</td>
     <td>Value (%)</td> 
</tr>
</thead>
<tbody>
 <%
 try{
   for(Map.Entry em:ecBean.getDecr_0_10_meters().entrySet()){
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

<div class="md-modal md-effect-10" id="ecmodal-6">
    <div class="md-content">
        <h3>Decreasing (10-25%) Meters</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="Decrease_10-25">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
 <td>Install point</td>
     <td>Location Name</td>
     <td>Value (%)</td> 
</tr>
</thead>
<tbody>
 <%
 try{
   for(Map.Entry em:ecBean.getDecr_10_25_meters().entrySet()){
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

<div class="md-modal md-effect-10" id="ecmodal-7">
    <div class="md-content">
        <h3>Decreasing (25-100%) Meters</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="Decrease_25-100">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
 <td>Install point</td>
     <td>Location Name</td>
     <td>Value (%)</td> 
</tr>
</thead>
<tbody>
 <%
 try{
   for(Map.Entry em:ecBean.getDecr_25_100_meters().entrySet()){
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

<div class="md-modal md-effect-10" id="ecmodal-8">
    <div class="md-content">
        <h3>Decreasing (>100%) Meters</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="Decrease_above100">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
 <td>Install point</td>
     <td>Location Name</td>
     <td>Value (%) </td> 
</tr>
</thead>
<tbody>
 <%
 try{
   for(Map.Entry em:ecBean.getDecr_gr_100_meters().entrySet()){
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



<div class="md-overlay"></div>

</body>
</html>

<script src="assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js"></script>
 <!-- modals js -->
        <script src="assets/plugins/modals/classie.js"></script>
        <script src="assets/plugins/modals/modalEffects.js"></script>