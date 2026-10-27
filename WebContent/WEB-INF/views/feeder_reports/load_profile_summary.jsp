<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

<div class="col-md-12">
<h4 class="rTitle col-md-10">Load Profile Summary</h4>  
<!-- <button class="btn btn-sm col-md-1" onclick="exportDivtoExcel('Hourly_load')">Excel</button> -->
<!-- <button class="btn btn-sm col-md-1" onclick="printDiv()">Print</button> -->
</div>
 <table class="table table-advance" cellspacing="0" width="100%" id="lsprofile">
 <thead>
 <tr>
 <td rowspan="2">Circle</td>
 <td rowspan="2">Division</td>
 <td rowspan="2">Substation</td>
 <td rowspan="2">Feeder</td>
 <td rowspan="2">Meter</td>
 <td rowspan="2">Category</td>
 <td colspan="31">Daily Load Profile Intervals</td>
 
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
	<td>${map[m.meter_sl_no].d1 }</td>
	<td>${map[m.meter_sl_no].d2 }</td>
	<td>${map[m.meter_sl_no].d3 }</td>
	<td>${map[m.meter_sl_no].d4 }</td>
	<td>${map[m.meter_sl_no].d5 }</td>
	<td>${map[m.meter_sl_no].d6 }</td>
	<td>${map[m.meter_sl_no].d7 }</td>
	<td>${map[m.meter_sl_no].d8 }</td>
	<td>${map[m.meter_sl_no].d9 }</td>
	<td>${map[m.meter_sl_no].d10 }</td>
	<td>${map[m.meter_sl_no].d11 }</td>
	<td>${map[m.meter_sl_no].d12 }</td>
	<td>${map[m.meter_sl_no].d13 }</td>
	<td>${map[m.meter_sl_no].d14 }</td>
	<td>${map[m.meter_sl_no].d15 }</td>
	<td>${map[m.meter_sl_no].d16 }</td>
	<td>${map[m.meter_sl_no].d17 }</td>
	<td>${map[m.meter_sl_no].d18 }</td>
	<td>${map[m.meter_sl_no].d19 }</td>
	<td>${map[m.meter_sl_no].d20 }</td>
	<td>${map[m.meter_sl_no].d21 }</td>
	<td>${map[m.meter_sl_no].d22 }</td>
	<td>${map[m.meter_sl_no].d23 }</td>
	<td>${map[m.meter_sl_no].d24 }</td>
	<td>${map[m.meter_sl_no].d25 }</td>
	<td>${map[m.meter_sl_no].d26 }</td>
	<td>${map[m.meter_sl_no].d27 }</td>
	<td>${map[m.meter_sl_no].d28 }</td>
	<td>${map[m.meter_sl_no].d29 }</td>
	<td>${map[m.meter_sl_no].d30 }</td>
	<td>${map[m.meter_sl_no].d31 }</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>