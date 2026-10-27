<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="groupAFeeders">
<thead>
<tr>
<th>Circle</th>
<th>SS Name</th>
<th>F Name</th>
<th>Meter</th>
<th>Server Time</th>
<th>Load (${totalLoad} MW)</th>
</tr>
</thead>
<tbody>
 <c:forEach items="${master}" var="m">
 <tr>
 <td>${m.circle }</td>
 <td>${m.ss }</td>
 <td>${m.location_name }</td>
 <td>${m.meter_sl_no }</td>
 <td>${load[m.meter_sl_no].serverTime }</td>
 <td>${load[m.meter_sl_no].load }</td>
 </tr>
 </c:forEach>

</tbody>
</table>
</div>     