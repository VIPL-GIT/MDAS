<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="singlePhaseFeeders_savings">
<thead>
<tr>
<th>Circle</th>
<th >Division</th>
 <th >Zone</th>
<th>SS Name</th>
<th>F Name</th>
<th>Group</th>
<th>Meter</th>
<th>MF</th>
<!-- <th>Single Phase</th> -->
<!-- <th>Three Phase</th> -->
<!-- <th>Power Failure</th> -->
<!-- <th>Three phase consumption (avg)</th> -->
<th>Energy Savings (kWh)</th>
<th>Energy Utilised (kWh)</th>
<th>Supply Duration (HH:mm:ss)</th>
</tr>
</thead>
<tbody>
 <c:forEach items="${master}" var="m">
 
 <tr>
 <td>${m.circle }</td>
 <td>${m.division }</td>
 <td>${m.sd }</td>
 <td>${m.ss }</td>
 <td>${m.location_name }</td>
 <td>${m.location_group }</td>
 <td><a href="javascript:getEnergySavingsDetails('${m.meter_sl_no }')">${m.meter_sl_no }</a></td>
 <td>${m.mf }</td>
<%--  <td>${map[m.meter_sl_no].singlephase }</td> --%>
<%--  <td>${map[m.meter_sl_no].threephase }</td> --%>
<%--  <td>${map[m.meter_sl_no].powerfailure }</td> --%>
 
 
<%--  <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${map[m.meter_sl_no].avg_three_consumption }" /></td> --%>
 <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${map[m.meter_sl_no].energy_savings*m.mf }" /></td>
 <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${map[m.meter_sl_no].energy_utilised*m.mf }" /></td>
 <td>${map[m.meter_sl_no].supply_duration }</td>
 
 </tr>
 
 </c:forEach>

</tbody>
</table>
</div>  

<div id="bi_modal" class="modal">   