<%@page import="prepaid.beans.PrepaidInstant"%>
<%@page import="prepaid.dao.PrepaidRecharge"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>


<%
  String meter=request.getParameter("meter");
String sdate=request.getParameter("sdate");
String edate=request.getParameter("edate");

  Map<String,String> details=new PrepaidRecharge().getConnectDisconnectHistory(meter,sdate,edate);  
  request.setAttribute("details", details);
  
%>   

<div class="table-responsive" style="border:0;padding-top:10px;">
<c:if test="${!empty details}">
   
<table   class="table table-advance" cellspacing="0"  id="table1" width="100%" >
    <thead>
<tr>
<th>Date time</th>
<th>Action Done</th>
</tr>
</thead>
<tbody>
<c:forEach items="${details}" var="d">  
<tr>
<td>${d.key }</td>
<td>${d.value }</td>
</tr>
</c:forEach>
</tbody>
</table>
</c:if>
 </div>           


<c:if test="${empty details}">
No data available...
</c:if>
