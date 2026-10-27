<%@page import="prepaid.beans.RechargeBean"%>
<%@page import="prepaid.dao.PrepaidRecharge"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>


<%
   List<RechargeBean> details=new PrepaidRecharge().getrechargeHistory();
  
  request.setAttribute("details", details);
%>   
<div class="table-responsive" style="border:0">
<c:if test="${!empty details}">
   
<table   class="table table-advance" cellspacing="0"  id="table1" width="100%" style="padding-top:10px;padding-left: 5%;padding-right: 5%;">
    <thead>
<tr>
<th>Meter No</th>
<th>Present KWH (K)</th>
<th>Update DateTime</th>
<th>to KWH (K)</th>
<th>Last Recharge amt (&#x20b9;)</th>
<th>Last Recharge DateTime</th>
<th>Disconnected DateTime</th>
<th>Status</th>
<th>Source of disconnect</th>
</tr>
</thead>
<tbody>
<c:forEach items="${details}" var="d">
  
<tr>
<td>${d.meter_serial_no }</td>
<td>${d.present_kwh }</td>
<td>${d.updated_datetime==null?"-":d.updated_datetime}</td>
<td>${d.to_kwh==null?"-":d.to_kwh }</td>
<td>${d.recent_recharge_amount==null?"-":d.recent_recharge_amount }</td>
<td>${d.recharge_timestamp==null?"-":d.recharge_timestamp }</td>
<td>${d.disconnected_timestamp==null?"-":d.disconnected_timestamp }</td>
<td>${d.status==null?"-":d.status }</td>
<c:choose>
<c:when test='${d.source_of_disconnect eq "TAMPER OCCURRED"}' >
    <td>TAMPER OCCURRED <a href="javascript:tamperConnect('${d.meter_serial_no}')"><img src='images/connect.png' height='20'/></a></td>
    </c:when>
 <c:otherwise >
    <td>${d.source_of_disconnect}</td>
    </c:otherwise> 
    </c:choose>
</tr>
</c:forEach>
</tbody>
</table>
 </div>           

</c:if>
<c:if test="${empty details}">
No data available...
</c:if>