<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

 <table class="table table-advance" cellspacing="0" width="100%" id="singlePhaseFeeders">
 <thead>
 <tr>
 <th rowspan="2">Circle</th>
 <th rowspan="2">Division</th>
 <th rowspan="2">Zone</th>
<th rowspan="2">SS Name</th>
<th rowspan="2">F Name</th>
<!-- <th rowspan="2">Group</th> -->
<th rowspan="2">Meter</th>
 <th colspan="31">1ph Supply (HH:mm:ss)</th>
 
 </tr>
 <tr>
 <%
 int days=((List)request.getAttribute("days")).size();
 for(int day=1;day<=days;day++){
%><td><%=day %></td>	 
 <%}
 %>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${master}" var="m">
 <c:if test="${m.location_group eq 'mix'}">
 <tr>
 <td>${m.circle }</td>
 <td>${m.division }</td>
 <td>${m.sd }</td>
 <td>${m.ss }</td>
 <td>${m.location_name }</td>
<%--  <td>${m.location_group }</td> --%>
 <td>${m.meter_sl_no }</td>
 <c:forEach items="${days}" var="d">
 <td>${load[m.meter_sl_no][d] }</td>
 </c:forEach>
 


 </tr>
 </c:if>
 </c:forEach>
  
 </tbody>
 </table>
 </div>