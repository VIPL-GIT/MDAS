<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<style>
th{
white-space: nowrap;
}
</style>
<%
List actionList=new ArrayList();
String clientId="";
try{
String actionId=request.getAttribute("actionId").toString();
clientId=session.getAttribute("clientId").toString();
Map<String,List> actionMap=(Map<String,List>)session.getAttribute("mappedActions");
actionList=actionMap.get(actionId);
//System.out.println("------------------------>"+actionList);
}catch(Exception e){
	System.out.println("Exception in catch.....");
	e.printStackTrace();
}
%>
    
 <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h5>Smart Meters/Devices</h5> 
                                
                                    <div class="btn-group pull-right clearfix">
                                     <%if(!actionList.get(0).equals("0")) {%>
                                        <a class="btn btn-circle show-tooltip" title="Add Meter" href="javascript:addSmartDevices()"><i class="fa fa-plus"></i></a>
                                         <%} %>
                                        </div>
                                   </div>                           
                            </div>	
<div class="panel-body" id="feederBody">
<div class="table-responsive" style="border:0">
    <c:if test="${!empty list}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
<th>Meter Sl.No.</th>
<th>Project</th>
<th>Manufacturer</th>
<th>Flag</th>
<th>System Title</th>
<th>Meter Version</th>
<th>Year of Manufacture</th>
<th>Meter Category</th>
<th>Meter Type</th>
<th>CTR</th>
<th>PTR</th>
<th>Net Meter</th>
<th>IP Address Type</th>
<th>Destination IP address</th>
<th>Listening Port</th>
<th>Channel Type</th>
<th>Addressing Scheme</th>
<th>Baudrate</th>
<th>Parity</th>
<th>Communication type</th>
<th>Gateway Sl.No</th>
<th>MAC address</th>
<th>Action</th>
      
  </tr>
</thead>
<tbody>
<c:forEach items="${list}" var="l">
<tr>
<td>${l.meter_serial_no}</td>
<td>${l.project}</td>
<td>${l.manufacturer}</td>
<td>${l.flag_id}</td>
<td>${l.system_title}</td>
<td>${l.meter_version}</td>
<td>${l.year_of_manufacture}</td>
<td>${l.meter_category}</td>
<td>${l.meter_type}</td>
<td>${l.ctr}</td>
<td>${l.ptr}</td>
<td>${l.netmeter}</td>
<td>${l.ipaddress_type}</td>
<td>${l.destination_ipaddress}</td>
<td>${l.listening_port}</td>
<td>${l.channel_type}</td>
<td>${l.addressing_scheme}</td>
<td>${l.baudrate}</td>
<td>${l.parity}</td>
<td>${l.communication_type}</td>
<td>${l.gateway_serial_no}</td>
<td>${l.mac_address}</td>
<td>
    <%if(!actionList.get(2).equals("0")) {%>
      <a href="javascript:getSmartDevices('${l.device_id}')" title="edit"><i class="ti ti-pencil"></i></a> &nbsp;      
       <% }if(!actionList.get(1).equals("0")) {%>
      <a href="javascript:deleteSmartDevices('${l.device_id}')" title="delete"> <i class="ti ti-trash"></i></a>
         <%} %>
       </td>
</tr>
</c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty list}">
<center><h5>No Smart Devices available.	</h5></center>
</c:if>
</div>
</div>
</div>
</div>
</div>

