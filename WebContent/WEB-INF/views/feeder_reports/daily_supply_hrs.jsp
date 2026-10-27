<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

<div class="col-md-12">
<h4 class="rTitle col-md-10">Daily Supply Hours</h4>  
<!-- <button class="btn btn-sm col-md-1" onclick="exportDivtoExcel('Hourly_load')">Excel</button> -->
<!-- <button class="btn btn-sm col-md-1" onclick="printDiv()">Print</button> -->
</div>
 <table class="table table-advance" cellspacing="0" width="100%" id="supplyHrs">
 <thead>
 <tr>
 <td rowspan="2">Circle</td>
 <td rowspan="2">Division</td>
 <td rowspan="2">Substation</td>
 <td rowspan="2">Feeder</td>
 <td rowspan="2">Meter</td>
 <td rowspan="2">Category</td>
 <td colspan="2">00:00-06:00</td>
 <td colspan="2">06:00-12:00</td>
 <td colspan="2">12:00-18:00</td>
 <td colspan="2">18:00-24:00</td>
 <td rowspan="2">Total Supply Hrs </br>(DD HH:mm:ss)</td> 
 </tr>
 <tr>
 <td>HH:MM</td>
 <td>Consumption</td>
  <td>HH:MM</td>
 <td>Consumption</td>
  <td>HH:MM</td>
 <td>Consumption</td>
  <td>HH:MM</td>
 <td>Consumption</td>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${master}" var="m">
 <tr>
 <td>${fn:toUpperCase(m.circle) }</td>
 <td>${fn:toUpperCase(m.division) }</td>
 <td>${fn:toUpperCase(m.ss) }</td>
 <td>${fn:toUpperCase(m.location_name) }</td>
 <td>${fn:toUpperCase(m.meter_sl_no) }</td>
 <td>${fn:toUpperCase(m.location_category) }</td>
 
 <td>${hlbList[m.meter_sl_no].slot1_duration }</td>
 <td>${hlbList[m.meter_sl_no].slot1_consumption }</td>
 
 <td>${hlbList[m.meter_sl_no].slot2_duration }</td>
 <td>${hlbList[m.meter_sl_no].slot2_consumption }</td>
 
 <td>${hlbList[m.meter_sl_no].slot3_duration }</td>
 <td>${hlbList[m.meter_sl_no].slot3_consumption }</td>
 
 <td>${hlbList[m.meter_sl_no].slot4_duration }</td>
 <td>${hlbList[m.meter_sl_no].slot4_consumption }</td>
 
 <td>${hlbList[m.meter_sl_no].total_supply_hrs }</td>
 
 
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>