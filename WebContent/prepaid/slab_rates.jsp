<%@page import="prepaid.beans.SlabRatesBean"%>
<%@page import="prepaid.dao.PrepaidRecharge"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>


<%
 

  List<SlabRatesBean> details=new PrepaidRecharge().getSlabRates(); 
  request.setAttribute("details", details);
  
%>   

<div class="table-responsive" style="border:0;padding-top:10px;">
<c:if test="${!empty details}">
   
<table   class="table table-advance" cellspacing="0"  id="table1" width="100%" >
    <thead>
<tr>

<th>Category</th>
<th>Phase</th>
<th>Min.Units</th>
<th>Max.Units</th>
<th>Energy Charge</th>
<th>Min.Charge</th>
<th>Customer Charge</th>
<th>Electricity Charge</th>
<th>Tariff</th>
</tr>
</thead>
<tbody>
<c:forEach items="${details}" var="d">
  
<tr>
<td>${d.category }</td>
<td>${d.phase }</td>
<td>${d.min_units }</td>
<td>${d.max_units }</td>
<td>${d.energy_charge }</td>
<td>${d.min_charge }</td>
<td>${d.customer_charge }</td>
<td>${d.electricity_charge }</td>
<td>${d.tariff_script.replaceAll(";","<br>") }</td>
</tr>
</c:forEach>
</tbody>
</table>
</c:if>
 </div>           


<c:if test="${empty details}">
No data available...
</c:if>
