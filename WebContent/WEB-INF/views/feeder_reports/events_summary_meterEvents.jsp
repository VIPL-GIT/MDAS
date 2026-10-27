<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12">
<div class="col-sm-3 col-lg-3 controls">
<select class="" id="event_type" style="width:100%;height:25px;">
<c:forEach items="${meterevents}" var="m">
 <option value="${m.key }">${m.value }</option>
</c:forEach>
</select>
</div>

<div class="col-sm-3 col-lg-3 controls">
<select class="" id="year" style="width:100%;height:25px;">
<c:forEach items="${years}" var="y">
 <option value="${y}">${y }</option>
</c:forEach>
</select>
</div>
<div class="col-sm-3 col-lg-3 controls">
<button class="btn" onclick="getEventsSummaryByMeterAction()">Get Details</button>
</div>
</div>

<div id="events_summary_meter" class="col-md-12" style="margin-top:5px;"></div>