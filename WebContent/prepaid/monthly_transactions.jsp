<%@page import="prepaid.beans.TransactionsBean"%>
<%@page import="prepaid.dao.PrepaidRecharge"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>


<%
  String meter=request.getParameter("meter");
String selMonth=request.getParameter("selMonth");


  List<TransactionsBean> details=new PrepaidRecharge().getPrepaidTransactions(meter,selMonth); 
  request.setAttribute("details", details);
  
%>   

<div class="table-responsive" style="border:0;padding-top:10px;">
<c:if test="${!empty details}">
   
<table   class="table table-advance" cellspacing="0"  id="table1" width="100%" >
    <thead>
<tr>
<th>Recharge Date Time</th>
<th>From Date Time</th>
<th>To Date Time</th>
<th>From Readings</th>
<th>To Readings</th>
<th>Units Consumed</th>
<th>Charge for units consumed</th>
<th>Previous balance</th>
<th>Revised balance</th>
<th>Average unit price</th>
</tr>
</thead>
<tbody>
<c:forEach items="${details}" var="d">
  
<tr>
<td>${d.log_timestamp}</td>
<td>${d.from_timestamp}</td>
<td>${d.to_timestamp}</td>
<td>${d.from_readings}</td>
<td>${d.to_readings}</td>
<td>${d.units_consumed}</td>
<td>${d.charge_for_units_consumed}</td>
<td>${d.previous_balance}</td>
<td>${d.revised_balance}</td>
<td>${d.average_unit_price}</td>

</tr>
</c:forEach>
</tbody>
</table>
</c:if>
 </div>           


<c:if test="${empty details}">
No data available...
</c:if>
