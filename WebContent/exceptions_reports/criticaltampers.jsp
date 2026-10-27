<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<link rel="stylesheet" href="css/table.css" type="text/css" />
<!-- <link rel="stylesheet" href="css/style.default.css" type="text/css" /> -->
<script src="js/month/jquery-ui.min.js"></script>
<link href="js/month/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/month/MonthPicker.min.js"></script>
<link  rel="stylesheet" type="text/css" href="js/month/MonthPicker.min.css"/>    
    <%@page import="org.springframework.web.context.WebApplicationContext" %> 
    <%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
     <%@page import="com.rtdas.dashboard.dao.*" %>
    <%@page import="com.rtdas.reports.dao.*" %>
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
$(document).ready(function() {
    $('#rph_missing').DataTable( {
    	dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Rph_missing'},
             {extend:'excelHtml5',
                 title:'Rph_missing'},
              {extend:'csvHtml5',
                 title:'Rph_missing'}
        ]
    } );
    
    $('#yph_missing').DataTable( {
    	dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Yph_missing'},
             {extend:'excelHtml5',
                 title:'Yph_missing'},
              {extend:'csvHtml5',
                 title:'Yph_missing'}
        ]
    } );
    
    $('#bph_missing').DataTable( {
    	dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'Bph_missing'},
             {extend:'excelHtml5',
                 title:'Bph_missing'},
              {extend:'csvHtml5',
                 title:'Bph_missing'}
        ]
    } );
    
    $('#ct_open').DataTable( {
    	dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'CT_open'},
             {extend:'excelHtml5',
                 title:'CT_open'},
              {extend:'csvHtml5',
                 title:'CT_open'}
        ]
    } );

    $('#ct_short').DataTable( {
    	dom: 'Bfrtip',
        "lengthMenu": [5,10, 25, 50, 75, 100],
        buttons: [
            {extend:'copyHtml5',
                title:'CT_short'},
             {extend:'excelHtml5',
                 title:'CT_short'},
              {extend:'csvHtml5',
                 title:'CT_short'}
        ]
    } );
    
   
} );

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="assets/plugins/modals/modal-component.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<%
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
String curMonth=request.getParameter("month");
String month=curMonth;
System.out.println("**************************************************"+curMonth);
if(curMonth==null){
	curMonth=sdf.format(new java.util.Date());
	curMonth=curMonth.substring(0, curMonth.lastIndexOf("-"));
	//month=curMonth.split("-")[1]+"/"+curMonth.split("-")[0];
	month=curMonth;
}else{
	//curMonth=curMonth.split("-")[1]+"-"+curMonth.split("-")[0];
}
System.out.println("**************************************************"+curMonth);
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
Map<String,List<DashCriticalTampersBean>> map=ddao.getDashCriticalTampersBean(meters,curMonth);

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


<table class="table table-advance" cellspacing="0" width="100%" id="table1" style="height:282px !important;">
<thead>
<!-- <tr> -->
<%-- <th>Select Month <input type="text"  name="dashMonth" id="dashMonth" onchange="callDashTampers()"  value="<%=month %>" style="width:75px;float:right;color:black;box-shadow:none;"/></th> --%>
<!-- <th><button onclick="callDashTampers()" style="color:black;">Get Details</button></th> -->
<!-- </tr> -->
 <tr>
    <th colspan="2">Critical Tempers  (Not restored) 
    <input type="month"  name="dashMonth" id="dashMonth" onchange="dashTampers()"  value="<%=month %>" style="width:145px;float:right;color:black;box-shadow:none;"/>
    </th>
    
  </tr>
  </thead>
  <tbody>   
    	<tr>
    	<td>R phase potential missing</td>
    	<td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ctmodal-rpt">
    	     <%=(map.containsKey("R phase potential missing")?map.get("R phase potential missing").size():0) %>
    	     </button></td>
    	</tr>
    	<tr>
    	<td>Y phase potential missing</td>
    	<td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ctmodal-ypt">
    	     <%=(map.containsKey("Y phase potential missing")?map.get("Y phase potential missing").size():0) %>
    	     </button></td>
    	</tr>
    	<tr>
    	<td>B phase potential missing</td>
    	<td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ctmodal-bpt">
    	     <%=(map.containsKey("B phase potential missing")?map.get("B phase potential missing").size():0) %>
    	     </button></td>
    	</tr>
    	
    	<tr>
    	<td>CT short</td>
    	<td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ctmodal-ctshort">
    	     <%=(map.containsKey("CT short")?map.get("CT short").size():0) %>
    	     </button></td>
    	</tr>
    	
    	<tr>
    	<td>CT Open</td>
    	<td><button class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ctmodal-ctopen">
    	     <%=(map.containsKey("CT open")?map.get("CT open").size():0) %>
    	     </button></td>
    	</tr>
    
<!--     <tr> -->
<!--     <td>Any Event (Restoration time > 10 mins)</td> -->
<!--     <td></td> -->
<!--   </tr> -->
</tbody>
 </table>

</div>
<!-- ----------------------------models---------------------------------------------- -->

 <div class="md-modal md-effect-10" id="ctmodal-rpt">
    <div class="md-content">
        <h3>R phase potential missing</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="rph_missing">
<thead>
<tr> 
     <td>KNO</td>
     <td>Meter Sl.No</td>
     <td>Install point</td>
     <td>Location Name</td>
     <td>Event Date</td>
     <td>Last Occurred DateTime</td> 
     <td>Last Restored DateTime</td>
</tr>
</thead>
<tbody>
 <%
 try{
	 List<DashCriticalTampersBean> list=(map.containsKey("R phase potential missing")?map.get("R phase potential missing"):new ArrayList());
    if(!list.isEmpty()){
	 for(DashCriticalTampersBean dtb:list){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(dtb.getMeter_serial_no())?knoURL+masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(dtb.getMeter_serial_no())%>')"><%=dtb.getMeter_serial_no() %></a></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_name():"" %></td>
	  <td><%=dtb.getEvent_date()%></td>
	  <td><%=dtb.getLast_o_timestamp() %></td>
	  <td><%=dtb.getLast_r_timestamp() %></td>
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

<div class="md-modal md-effect-10" id="ctmodal-ypt">
    <div class="md-content">
        <h3>Y phase potential missing</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="yph_missing">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
<td>Install point</td>
     <td>Location Name</td>
     <td>Event Date</td>
     <td>Last Occurred DateTime</td> 
     <td>Last Restored DateTime</td>
</tr>
</thead>
<tbody>
 <%
 try{
	 List<DashCriticalTampersBean> list=(map.containsKey("Y phase potential missing")?map.get("Y phase potential missing"):new ArrayList());
    if(!list.isEmpty()){
	 for(DashCriticalTampersBean dtb:list){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(dtb.getMeter_serial_no())?knoURL+masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(dtb.getMeter_serial_no())%>')"><%=dtb.getMeter_serial_no() %></a></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_name():"" %></td>
	  <td><%=dtb.getEvent_date()%></td>
	  <td><%=dtb.getLast_o_timestamp() %></td>
	  <td><%=dtb.getLast_r_timestamp() %></td>
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
    
<div class="md-modal md-effect-10" id="ctmodal-bpt">
    <div class="md-content">
        <h3>B phase potential missing</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="bph_missing">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
<td>Install point</td>
     <td>Location Name</td>
     <td>Event Date</td>
     <td>Last Occurred DateTime</td> 
     <td>Last Restored DateTime</td>
</tr>
</thead>
<tbody>
 <%
 try{
	 List<DashCriticalTampersBean> list=(map.containsKey("B phase potential missing")?map.get("B phase potential missing"):new ArrayList());
    if(!list.isEmpty()){
	 for(DashCriticalTampersBean dtb:list){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(dtb.getMeter_serial_no())?knoURL+masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(dtb.getMeter_serial_no())%>')"><%=dtb.getMeter_serial_no() %></a></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_name():"" %></td>
	  <td><%=dtb.getEvent_date()%></td>
	  <td><%=dtb.getLast_o_timestamp() %></td>
	  <td><%=dtb.getLast_r_timestamp() %></td>
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

<div class="md-modal md-effect-10" id="ctmodal-ctshort">
    <div class="md-content">
        <h3>CT short</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="ct_short">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
<td>Install point</td>
     <td>Location Name</td>
     <td>Event Date</td>
     <td>Last Occurred DateTime</td> 
     <td>Last Restored DateTime</td>
</tr>
</thead>
<tbody>
 <%
 try{
	 List<DashCriticalTampersBean> list=(map.containsKey("CT short")?map.get("CT short"):new ArrayList());
    if(!list.isEmpty()){
	 for(DashCriticalTampersBean dtb:list){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(dtb.getMeter_serial_no())?knoURL+masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(dtb.getMeter_serial_no())%>')"><%=dtb.getMeter_serial_no() %></a></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_name():"" %></td>
	  <td><%=dtb.getEvent_date()%></td>
	  <td><%=dtb.getLast_o_timestamp() %></td>
	  <td><%=dtb.getLast_r_timestamp() %></td>
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

<div class="md-modal md-effect-10" id="ctmodal-ctopen">
    <div class="md-content">
        <h3>CT open</h3>
        <div class="n-modal-body">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="ct_open">
<thead>
<tr> <td>KNO</td><td>Meter Sl.No</td>
<td>Install point</td>
     <td>Location Name</td>
     <td>Event Name</td>
     <td>Event Date</td>
     <td>Last Occurred DateTime</td> 
     <td>Last Restored DateTime</td>
</tr>
</thead>
<tbody>
 <%
 try{
	 List<DashCriticalTampersBean> list=(map.containsKey("CT open")?map.get("CT open"):new ArrayList());
    if(!list.isEmpty()){
	 for(DashCriticalTampersBean dtb:list){
	  %>
	  <tr>
	  <td><a href="<%=masterMap.containsKey(dtb.getMeter_serial_no())?knoURL+masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %>" target="_blank"><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_code():"" %></a></td>
	  <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(dtb.getMeter_serial_no())%>')"><%=dtb.getMeter_serial_no() %></a></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getMeter_location():"" %></td>
	  <td><%=masterMap.containsKey(dtb.getMeter_serial_no())?masterMap.get(dtb.getMeter_serial_no()).getLocation_name():"" %></td>
	  <td><%=dtb.getEvent_name() %></td>
	  <td><%=dtb.getEvent_date()%></td>
	  <td><%=dtb.getLast_o_timestamp() %></td>
	  <td><%=dtb.getLast_r_timestamp() %></td>
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
<script>
//$('#dashMonth').MonthPicker({ Button: false });
//$('#dashMonth').MonthPicker({SelectedMonth: 0, Button: false });



</script>
<script src="assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js"></script>
 <!-- modals js -->
        <script src="assets/plugins/modals/classie.js"></script>
        <script src="assets/plugins/modals/modalEffects.js"></script>