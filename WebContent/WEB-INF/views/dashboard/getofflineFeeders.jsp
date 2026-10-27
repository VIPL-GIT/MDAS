<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-bd">
  <div class="panel-heading">
      <div class="panel-title">
          <h5>Offline Feeders</h5> 
      </div>                                 
      </div>
<div class="panel-body" style="padding:1px !important;">
<div class="table-responsive" style="border:0">
<table class="table table-advance" cellspacing="0" width="100%" id="offline">
<thead>
<tr>
<th>SS Name</th>
<th>F Name</th>
<th>Date</th>
<th>Time</th>
<th>Load Rel.KW</th>
</tr>
</thead>
<tbody>
 <c:forEach items="${fmap}" var="map">
<tr>
<td>${map.key.split("@")[0] }</td>
<td>${map.key.split("@")[1] }</td>
<td>${map.value.modem_rtc.split(" ")[0] }</td>
<td>${map.value.modem_rtc.split(" ")[1] }</td>
<td>${map.value.kw }</td>
</tr>
</c:forEach>

</tbody>
</table>
</div>
</div>
</div>      