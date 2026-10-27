<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

<div class="col-md-12">
<h4 class="rTitle col-md-10">Average Supply Hours</h4>  
<!-- <button class="btn btn-sm col-md-1" onclick="exportDivtoExcel('Hourly_load')">Excel</button> -->
<!-- <button class="btn btn-sm col-md-1" onclick="printDiv()">Print</button> -->
</div>
 <table class="table table-advance" cellspacing="0" width="100%" id="avgSupply">
 <thead>
 <tr>
 <td rowspan="2">Circle</td>
 <td rowspan="2">Division</td>
 <td rowspan="2">Substation</td>
 <td rowspan="2">Feeder</td>
 <td rowspan="2">Meter</td>
 <td rowspan="2">Category</td>
 <td rowspan="2">Group</td>
 <td colspan="31">Daily Supply Hrs</td>
 <td rowspan="2">Avg. Supply Hrs</td>
 </tr>
 <tr>
 <%
 for(int day=1;day<=31;day++){
%><td><%=day %></td>	 
 <%}
 %>
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
 <td>${m.location_group }</td>
 <td>${hlbList[m.meter_sl_no].dsh_1 }</td>
<td>${hlbList[m.meter_sl_no].dsh_2 }</td>
<td>${hlbList[m.meter_sl_no].dsh_3 }</td>
<td>${hlbList[m.meter_sl_no].dsh_4 }</td>
<td>${hlbList[m.meter_sl_no].dsh_5 }</td>
<td>${hlbList[m.meter_sl_no].dsh_6 }</td>
<td>${hlbList[m.meter_sl_no].dsh_7 }</td>
<td>${hlbList[m.meter_sl_no].dsh_8 }</td>
<td>${hlbList[m.meter_sl_no].dsh_9 }</td>
<td>${hlbList[m.meter_sl_no].dsh_10 }</td>
<td>${hlbList[m.meter_sl_no].dsh_11 }</td>
<td>${hlbList[m.meter_sl_no].dsh_12 }</td>
<td>${hlbList[m.meter_sl_no].dsh_13 }</td>
<td>${hlbList[m.meter_sl_no].dsh_14 }</td>
<td>${hlbList[m.meter_sl_no].dsh_15 }</td>
<td>${hlbList[m.meter_sl_no].dsh_16 }</td>
<td>${hlbList[m.meter_sl_no].dsh_17 }</td>
<td>${hlbList[m.meter_sl_no].dsh_18 }</td>
<td>${hlbList[m.meter_sl_no].dsh_19 }</td>
<td>${hlbList[m.meter_sl_no].dsh_20 }</td>
<td>${hlbList[m.meter_sl_no].dsh_21 }</td>
<td>${hlbList[m.meter_sl_no].dsh_22 }</td>
<td>${hlbList[m.meter_sl_no].dsh_23 }</td>
<td>${hlbList[m.meter_sl_no].dsh_24 }</td>
<td>${hlbList[m.meter_sl_no].dsh_25 }</td>
<td>${hlbList[m.meter_sl_no].dsh_26 }</td>
<td>${hlbList[m.meter_sl_no].dsh_27 }</td>
<td>${hlbList[m.meter_sl_no].dsh_28 }</td>
<td>${hlbList[m.meter_sl_no].dsh_29 }</td>
<td>${hlbList[m.meter_sl_no].dsh_30 }</td>
<td>${hlbList[m.meter_sl_no].dsh_31 }</td>
<td>${hlbList[m.meter_sl_no].avg_supply_hrs }</td> 
 
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>