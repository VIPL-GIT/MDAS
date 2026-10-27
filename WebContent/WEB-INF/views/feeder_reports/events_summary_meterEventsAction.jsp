<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-md-12">
<h4 class="rTitle col-md-10">Events Summary</h4> 
<!-- <button class="btn btn-sm col-md-2" onclick="exportDivtoExcel('Events_summary','eventssummary')">Export to Excel</button> -->
</div>
<div class="col-md-12 table-responsive" style="border:0" id="eventssummary">

<c:if test="${!empty meterevents}">
<table class="table table-advance" cellspacing="0" width="100%" id="events">
<thead>
<tr>
<th>Month</th>
<th>No# of occurrence with restoration</th>
<th>Duration(DD HH:MM:SS)</th>
<th>No# of occurrence without restoration</th>
<th>Duration(DD HH:MM:SS)</th>
</tr>
</thead>
<tbody>

<c:forEach items="${meterevents}" var="m">
<tr>
<td>${m.key }</td>
<td>${m.value.event_no_with_restore }</td>
<td>${m.value.with_duration }</td>
<td>${m.value.event_no_without_restore }</td>
<td>${m.value.without_duration }</td>

</tr>
</c:forEach>
</tbody>
</table>
</c:if>
<c:if test="${empty meterevents}">
<p style="text-align:center;font-size:15px;">No Data is available...</p>
</c:if>
</div>