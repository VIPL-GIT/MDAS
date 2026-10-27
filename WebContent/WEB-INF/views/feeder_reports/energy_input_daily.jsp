<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

<div class="col-md-12">
<h4 class="rTitle col-md-10">Energy Input Report</h4>  
</div>
 <table class="table table-advance" cellspacing="0" width="100%" id="energyInput">
 <thead>
 <tr> 
 <td rowspan="2">Circle</td>
 <td rowspan="2">Division</td>
 <td rowspan="2">Zone</td>
 <td rowspan="2">Substation</td>
 <td rowspan="2">Feeder</td>
 <td rowspan="2">Meter</td>
 <td rowspan="2">Category</td>
 <td rowspan="2">MF</td> 
 <td colspan="31">KWH</td>
 
 
 </tr>
 <tr>
 <%for(int i=1;i<=31;i++){
	 %>
	 <td>D<%=i %></td>
 <%} %>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${master}" var="m">
 <tr>
 
 <td>${fn:toUpperCase(m.circle) }</td>
 <td>${fn:toUpperCase(m.division) }</td>
 <td>${fn:toUpperCase(m.sd) }</td>
 <td>${fn:toUpperCase(m.ss) }</td>
 <td>${fn:toUpperCase(m.location_name) }</td>
 <td>${fn:toUpperCase(m.meter_sl_no) }</td>
 <td>${fn:toUpperCase(m.location_category) }</td>
 <td>${m.mf }</td>
 <c:forEach items="${map[m.meter_sl_no]}" var="d"> 
 <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${d*m.mf }" /></td>
 </c:forEach>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>