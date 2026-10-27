<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="pfNpowers">
<thead>
<tr>
<th>SS Name</th>
<th>F Name</th>
<th>Date</th>
<th>Time</th>
<c:if test="${pfNpowerParam.equals('pf')}">
<th>Avg PF</th>
</c:if>
<c:if test="${pfNpowerParam.equals('mw')}">
<th>KW</th>
</c:if>
<c:if test="${pfNpowerParam.equals('mva')}">
<th>KVA</th>
</c:if>
<c:if test="${pfNpowerParam.equals('mvar')}">
<th>KVAr</th>
</c:if>
</tr>
</thead>
<tbody>
 <c:forEach items="${fmap}" var="map">
<tr>
<td>${map.key.split("@")[0] }</td>
<td>${map.key.split("@")[1] }</td>
<td>${map.value.modem_rtc.split(" ")[0] }</td>
<td>${map.value.modem_rtc.split(" ")[1] }</td>

<c:if test="${pfNpowerParam.equals('pf')}">
<td>${map.value.pfavg }</td>
</c:if>
<c:if test="${pfNpowerParam.equals('mw')}">
<td>${map.value.kw }</td>
</c:if>
<c:if test="${pfNpowerParam.equals('mva')}">
<td>${map.value.kva }</td>
</c:if>
<c:if test="${pfNpowerParam.equals('mvar')}">
<td>${map.value.kvar }</td>
</c:if>
</tr>
</c:forEach>

</tbody>
</table>
</div>
     