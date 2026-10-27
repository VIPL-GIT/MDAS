<%@page import="prepaid.beans.PrepaidAlerts"%>
<%@page import="prepaid.dao.PrepaidRecharge"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>


<%
  String meter=request.getParameter("meter");
String sdate=request.getParameter("sdate");
String edate=request.getParameter("edate");

  List<PrepaidAlerts> details=new PrepaidRecharge().getPrepaidAlerts(meter,sdate,edate);  
  request.setAttribute("details", details);
  
%>   

<div class="table-responsive" style="border:0;padding-top:10px;">
<c:if test="${!empty details}">
   
<table   class="table table-advance" cellspacing="0"  id="table1" width="100%" >
    <thead>
<tr>
    
<!-- <th>Meter No</th> -->
<th style="width:160px !important;"> Date time</th>

<th>OBIS</th>
<th>BITs String</th>

</tr>
</thead>
<tbody>
<c:forEach items="${details}" var="d">
  
<tr>
<td style="width:160px !important;"><a href="javascript:getSmartAlertPopup('${d.meter }','${d.meter_rtc }')">${d.meter_rtc }</a></td>
<td>${d.obis }</td>
<td>${d.bit_string }</td>
</tr>
</c:forEach>
</tbody>
</table>
</c:if>
 </div>           


<c:if test="${empty details}">
No data available...
</c:if>
