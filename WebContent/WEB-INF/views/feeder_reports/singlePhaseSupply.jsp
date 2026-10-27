<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="singlePhaseFeeders">
<thead>
<tr>
<th>Circle</th>
<th >Division</th>
 <th >Zone</th>
<th>SS Name</th>
<th>F Name</th>
<th>Group</th>
<th>Meter</th>
<th>1ph Supply (HH:mm:ss)</th>
<th>3ph Supply  (HH:mm:ss)</th>
<th>PowerFail (HH:mm:ss)</th>
<th>1ph Status</th>
<!-- <th>Total Supply (hrs)</th>  -->
</tr>
</thead>
<tbody>
 <c:forEach items="${master}" var="m">
 <c:if test="${m.location_group eq 'mix'}">
 <tr>
 <td>${m.circle }</td>
 <td>${m.division }</td>
 <td>${m.sd }</td>
 <td>${m.ss }</td>
 <td>${m.location_name }</td>
 <td>${m.location_group }</td>
 <td>${m.meter_sl_no }</td>
 <td>${load[m.meter_sl_no].ph1_hrs }</td>
 <td>${load[m.meter_sl_no].ph3_hrs }</td>
 <td>${load[m.meter_sl_no].pf_hrs }</td>
 <td>${load[m.meter_sl_no].tot_hrs }</td>
<%--  <td>${load[m.meter_sl_no].tot_hrs }</td> --%>
 </tr>
 </c:if>
 </c:forEach>

</tbody>
</table>
</div>  

<div id="bi_modal" class="modal">   