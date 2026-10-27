<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

<div class="col-md-12">
<h4 class="rTitle col-md-10">Monthly Peak Load</h4>  
<!-- <button class="btn btn-sm col-md-1" onclick="exportDivtoExcel('Hourly_load')">Excel</button> -->
<!-- <button class="btn btn-sm col-md-1" onclick="printDiv()">Print</button> -->
</div>
 <table class="table table-advance" cellspacing="0" width="100%" id="peakload">
 <thead>
 <tr>
 <td rowspan="2">Circle</td>
 <td rowspan="2">Division</td>
 <td rowspan="2">Substation</td>
 <td rowspan="2">Feeder</td>
 <td rowspan="2">Meter</td>
 <td rowspan="2">Category</td>
 <td colspan="31">Daily Peak Load</td>
 <td rowspan="2">Monthly Peak Load</td>
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
<td>${hlbList[m.meter_sl_no].dpl_1 }</td>
<td>${hlbList[m.meter_sl_no].dpl_2 }</td>
<td>${hlbList[m.meter_sl_no].dpl_3 }</td>
<td>${hlbList[m.meter_sl_no].dpl_4 }</td>
<td>${hlbList[m.meter_sl_no].dpl_5 }</td>
<td>${hlbList[m.meter_sl_no].dpl_6 }</td>
<td>${hlbList[m.meter_sl_no].dpl_7 }</td>
<td>${hlbList[m.meter_sl_no].dpl_8 }</td>
<td>${hlbList[m.meter_sl_no].dpl_9 }</td>
<td>${hlbList[m.meter_sl_no].dpl_10 }</td>
<td>${hlbList[m.meter_sl_no].dpl_11 }</td>
<td>${hlbList[m.meter_sl_no].dpl_12 }</td>
<td>${hlbList[m.meter_sl_no].dpl_13 }</td>
<td>${hlbList[m.meter_sl_no].dpl_14 }</td>
<td>${hlbList[m.meter_sl_no].dpl_15 }</td>
<td>${hlbList[m.meter_sl_no].dpl_16 }</td>
<td>${hlbList[m.meter_sl_no].dpl_17 }</td>
<td>${hlbList[m.meter_sl_no].dpl_18 }</td>
<td>${hlbList[m.meter_sl_no].dpl_19 }</td>
<td>${hlbList[m.meter_sl_no].dpl_20 }</td>
<td>${hlbList[m.meter_sl_no].dpl_21 }</td>
<td>${hlbList[m.meter_sl_no].dpl_22 }</td>
<td>${hlbList[m.meter_sl_no].dpl_23 }</td>
<td>${hlbList[m.meter_sl_no].dpl_24 }</td>
<td>${hlbList[m.meter_sl_no].dpl_25 }</td>
<td>${hlbList[m.meter_sl_no].dpl_26 }</td>
<td>${hlbList[m.meter_sl_no].dpl_27 }</td>
<td>${hlbList[m.meter_sl_no].dpl_28 }</td>
<td>${hlbList[m.meter_sl_no].dpl_29 }</td>
<td>${hlbList[m.meter_sl_no].dpl_30 }</td>
<td>${hlbList[m.meter_sl_no].dpl_31 }</td>
<td>${hlbList[m.meter_sl_no].monthly_peakLoad }</td> 
 
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>