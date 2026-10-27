<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

<div class="col-md-12">
<h4 class="rTitle col-md-10">Load Curve Details</h4>  
<!-- <button class="btn btn-sm col-md-1" onclick="exportDivtoExcel('Hourly_load')">Excel</button> -->
<!-- <button class="btn btn-sm col-md-1" onclick="printDiv()">Print</button> -->
</div>
 <table class="table table-advance" cellspacing="0" width="100%" id="load_curve">
 <thead>
 <tr>
 <td rowspan="2">Circle</td>
 <td rowspan="2">Division</td>
 <td rowspan="2">Substation</td>
 <td rowspan="2">Feeder</td>
 <td rowspan="2">Meter</td>
 <td rowspan="2">MF</td>
 </tr>
 <tr>
 <%
 for(int day=1;day<=24;day++){
%><td>H<%=day %></td>	 
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
 <td>${m.mf }</td>
 <td>${map[m.meter_sl_no].h1 }</td>
<td>${map[m.meter_sl_no].h2 }</td>
<td>${map[m.meter_sl_no].h3 }</td>
<td>${map[m.meter_sl_no].h4 }</td>
<td>${map[m.meter_sl_no].h5 }</td>
<td>${map[m.meter_sl_no].h6 }</td>
<td>${map[m.meter_sl_no].h7 }</td>
<td>${map[m.meter_sl_no].h8 }</td>
<td>${map[m.meter_sl_no].h9 }</td>
<td>${map[m.meter_sl_no].h10 }</td>
<td>${map[m.meter_sl_no].h11 }</td>
<td>${map[m.meter_sl_no].h12 }</td>
<td>${map[m.meter_sl_no].h13 }</td>
<td>${map[m.meter_sl_no].h14 }</td>
<td>${map[m.meter_sl_no].h15 }</td>
<td>${map[m.meter_sl_no].h16 }</td>
<td>${map[m.meter_sl_no].h17}</td>
<td>${map[m.meter_sl_no].h18 }</td>
<td>${map[m.meter_sl_no].h19 }</td>
<td>${map[m.meter_sl_no].h20 }</td>
<td>${map[m.meter_sl_no].h21 }</td>
<td>${map[m.meter_sl_no].h22 }</td>
<td>${map[m.meter_sl_no].h23 }</td>
<td>${map[m.meter_sl_no].h24 }</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>