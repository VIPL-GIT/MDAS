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
    $('#ct_missing_table').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'CT_missing'},
             {extend:'excelHtml5',
                 title:'CT_missing'},
              {extend:'csvHtml5',
                 title:'CT_missing'}
        ]
    } );
    
    $('#sph_supply_table').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'sph_supply'},
             {extend:'excelHtml5',
                 title:'sph_supply'},
              {extend:'csvHtml5',
                 title:'sph_supply'}
        ]
    } );
    
    $('#tph_supply_table').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'tph_supply_table'},
             {extend:'excelHtml5',
                 title:'tph_supply_table'},
              {extend:'csvHtml5',
                 title:'tph_supply_table'}
        ]
    } );
    
    $('#imb_table').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'imb_table'},
             {extend:'excelHtml5',
                 title:'imb_table'},
              {extend:'csvHtml5',
                 title:'imb_table'}
        ]
    } );
    
    $('#lpf_table').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Low_pf'},
             {extend:'excelHtml5',
                 title:'Low_pf'},
              {extend:'csvHtml5',
                 title:'Low_pf'}
        ]
    } );
    
    $('#lv_table').DataTable( {
        dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Low_voltage'},
             {extend:'excelHtml5',
                 title:'Low_voltage'},
              {extend:'csvHtml5',
                 title:'Low_voltage'}
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
Map<String,List<DashCriticalTampersBean>> map=ddao.getDashTamperEventsBean(meters);

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
<table class="table table-advance" cellspacing="0" width="100%" id="table1">
<thead>
 <tr>
    <th colspan="2">Instant Based Events</th>
  </tr>
  </thead>
  <tbody>
   
    	<tr>
    	<td>CURRENT MISSING</td>
    	<td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ct_missing">
    	     <%=(map.containsKey("CURRENT MISSING")?map.get("CURRENT MISSING").size():0) %>
    	     </button></td>
    	</tr>
    	<tr>
    	<td>SINGLE PHASE PT SUPPLY</td>
    	<td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="sph_supply">
    	     <%=(map.containsKey("SINGLE PHASE PT SUPPLY")?map.get("SINGLE PHASE PT SUPPLY").size():0) %>
    	     </button></td>
    	</tr>
    	<tr>
    	<td>TWO PHASE PT SUPPLY</td>
    	<td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="tph_supply">
    	     <%=(map.containsKey("TWO PHASE PT SUPPLY")?map.get("TWO PHASE PT SUPPLY").size():0) %>
    	     </button></td>
    	</tr>
    	
    	<tr>
    	<td>PT IMBALANCE</td>
    	<td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="imb">
    	     <%=(map.containsKey("PT IMBALANCE")?map.get("PT IMBALANCE").size():0) %>
    	     </button></td>
    	</tr>
    	<tr>
    	<td>LOW PF</td>
    	<td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="lowpf">
    	     <%=(map.containsKey("LOW POWER FACTOR")?map.get("LOW POWER FACTOR").size():0) %>
    	     </button></td>
    	</tr>
    	<tr>
    	<td>LOW VOLTAGE</td>
    	<td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="lowvoltage">
    	     <%=(map.containsKey("LOW VOLTAGE")?map.get("LOW VOLTAGE").size():0) %>
    	     </button></td>
    	</tr>
    	
    	
</tbody>
 </table>

</div>
<!-- ----------------------------models---------------------------------------------- -->

 <div class="md-modal md-effect-10" id="ct_missing">
    <div class="md-content">
        <h4>CT Missing</h4>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="ct_missing_table">
<thead>
<tr> 
     <td>KNO</td>
     <td>Meter Sl.No</td>
     <td>Install point</td>
     <td>Location Name</td>
     <td>Event Date Time</td>
     
</tr>
</thead>
<tbody>
 <%
 try{
	 List<DashCriticalTampersBean> list=(map.containsKey("CURRENT MISSING")?map.get("CURRENT MISSING"):new ArrayList());
    if(!list.isEmpty()){
	 for(DashCriticalTampersBean dtb:list){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(dtb.getMeter_serial_no())?knoURL+masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(dtb.getMeter_serial_no())%>')"><%=dtb.getMeter_serial_no() %></a></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_name():"" %></td>
	  <td><%=dtb.getEvent_date()%></td>	  
	  </tr> 
  <% }
    }
 }catch(Exception e){}
 %>
</tbody>
</table>
<button class="btn btn-base md-close">Close me!</button>
</div>
        </div>
    </div>
</div>

<div class="md-modal md-effect-10" id="sph_supply">
    <div class="md-content">
        <h4>SINGLE PHASE PT SUPPLY</h4>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="sph_supply_table">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
<td>Install point</td>
     <td>Location Name</td>
     <td>Event Date Time</td>
    
</tr>
</thead>
<tbody>
 <%
 try{
	 List<DashCriticalTampersBean> list=(map.containsKey("SINGLE PHASE PT SUPPLY")?map.get("SINGLE PHASE PT SUPPLY"):new ArrayList());
    if(!list.isEmpty()){
	 for(DashCriticalTampersBean dtb:list){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(dtb.getMeter_serial_no())?knoURL+masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(dtb.getMeter_serial_no())%>')"><%=dtb.getMeter_serial_no() %></a></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_name():"" %></td>
	  <td><%=dtb.getEvent_date()%></td>
	  </tr> 
  <% }
    }
 }catch(Exception e){}
 %>
</tbody>
</table>
<button class="btn btn-base md-close">Close me!</button>
</div>
        </div>
    </div>
</div>
    
<div class="md-modal md-effect-10" id="tph_supply">
    <div class="md-content">
        <h4>TWO PHASE PT SUPPLY</h4>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="tph_supply_table">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
<td>Install point</td>
     <td>Location Name</td>
     <td>Event Date Time</td>
</tr>
</thead>
<tbody>
 <%
 try{
	 List<DashCriticalTampersBean> list=(map.containsKey("TWO PHASE PT SUPPLY")?map.get("TWO PHASE PT SUPPLY"):new ArrayList());
    if(!list.isEmpty()){
	 for(DashCriticalTampersBean dtb:list){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(dtb.getMeter_serial_no())?knoURL+masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(dtb.getMeter_serial_no())%>')"><%=dtb.getMeter_serial_no() %></a></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_name():"" %></td>
	  <td><%=dtb.getEvent_date()%></td>
	  </tr> 
  <% }
    }
 }catch(Exception e){}
 %>
</tbody>
</table>
<button class="btn btn-base md-close">Close me!</button>
</div>
        </div>
    </div>
</div>

<div class="md-modal md-effect-10" id="imb">
    <div class="md-content">
        <h4>PT IMBALANCE</h4>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="imb_table">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
<td>Install point</td>
     <td>Location Name</td>
     <td>Event Date Time</td>
</tr>
</thead>
<tbody>
 <%
 try{
	 List<DashCriticalTampersBean> list=(map.containsKey("PT IMBALANCE")?map.get("PT IMBALANCE"):new ArrayList());
    if(!list.isEmpty()){
	 for(DashCriticalTampersBean dtb:list){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(dtb.getMeter_serial_no())?knoURL+masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(dtb.getMeter_serial_no())%>')"><%=dtb.getMeter_serial_no() %></a></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_name():"" %></td>
	  <td><%=dtb.getEvent_date()%></td>
	  </tr> 
  <% }
    }
 }catch(Exception e){}
 %>
</tbody>
</table>
<button class="btn btn-base md-close">Close me!</button>
</div>
        </div>
    </div>
</div>

<!-- --------------------------------------------------- -->
<div class="md-modal md-effect-10" id="lowpf">
    <div class="md-content">
        <h4>LOW POWER FACTOR</h4>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="lpf_table">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
<td>Install point</td>
     <td>Location Name</td>
     <td>Event Date Time</td>
</tr>
</thead>
<tbody>
 <%
 try{
	 List<DashCriticalTampersBean> list=(map.containsKey("LOW POWER FACTOR")?map.get("LOW POWER FACTOR"):new ArrayList());
    if(!list.isEmpty()){
	 for(DashCriticalTampersBean dtb:list){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(dtb.getMeter_serial_no())?knoURL+masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(dtb.getMeter_serial_no())%>')"><%=dtb.getMeter_serial_no() %></a></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_name():"" %></td>
	  <td><%=dtb.getEvent_date()%></td>
	  </tr> 
  <% }
    }
 }catch(Exception e){}
 %>
</tbody>
</table>
<button class="btn btn-base md-close">Close me!</button>
</div>
        </div>
    </div>
</div>

<!-- ------------------------------------------------------- -->
<div class="md-modal md-effect-10" id="lowvoltage">
    <div class="md-content">
        <h4>LOW VOLTAGE</h4>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="lv_table">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
<td>Install point</td>
     <td>Location Name</td>
     <td>Event Date Time</td>
</tr>
</thead>
<tbody>
 <%
 try{
	 List<DashCriticalTampersBean> list=(map.containsKey("LOW VOLTAGE")?map.get("LOW VOLTAGE"):new ArrayList());
    if(!list.isEmpty()){
	 for(DashCriticalTampersBean dtb:list){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(dtb.getMeter_serial_no())?knoURL+masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(dtb.getMeter_serial_no())%>')"><%=dtb.getMeter_serial_no() %></a></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_name():"" %></td>
	  <td><%=dtb.getEvent_date()%></td>
	  </tr> 
  <% }
    }
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