<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="ag_deviations">
<thead>
<tr>
<th>Circle</th>
<th >Division</th>
 <th >Zone</th>
<th>SS Name</th>
<th>F Name</th>
<th>Meter</th>
<th>Supply Hrs (HH:mm:ss)</th>
</tr>
</thead>
<tbody>
 <c:forEach items="${master}" var="m">
 <c:if test='${fn:split(map[m.meter_sl_no], ":")[0] le 15}'>
 <tr>
 <td>${m.circle }</td>
 <td>${m.division }</td>
 <td>${m.sd }</td>
 <td>${m.ss }</td>
 <td>${m.location_name }</td> 
 <td>${m.meter_sl_no }</td>
 <td>${map[m.meter_sl_no] }</td>
 
 </tr>
 </c:if>
 </c:forEach>

</tbody>
</table>
</div>  

<div id="bi_modal" class="modal">   