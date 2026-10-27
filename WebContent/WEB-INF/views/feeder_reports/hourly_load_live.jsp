<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

 <table class="table table-advance" cellspacing="0" width="100%" id="hourlyLOad">
 <thead>
 <tr>
 <td>Circle</td>
 <td>Division</td>
 <td>Substation</td>
 <td>Feeder</td>
 <td>Meter</td>
 <td>Category</td>
 <td>Availability (%)</td>
 
 <%
 for(int hr=0;hr<24;hr++){
%><td>Hrs <%=hr %></td>	 
 <%}
 %>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${master}" var="m">
 <tr>
 <td>${fn:toUpperCase(m.circle) }</td>
 <td>${fn:toUpperCase(m.division) }</td>
 <td>${fn:toUpperCase(m.ss) }</td>
 <td>${fn:toUpperCase(m.location_name) }</td>
 <td>${fn:toUpperCase(m.meter_sl_no) }</td>
 <td>${fn:toUpperCase(m.location_category) }</td>
 
 <td>${hlbList[m.meter_sl_no].availPercent }</td> 
<td>${hlbList[m.meter_sl_no].hr1 }</td>
<td>${hlbList[m.meter_sl_no].hr2 }</td>
<td>${hlbList[m.meter_sl_no].hr3 }</td>
<td>${hlbList[m.meter_sl_no].hr4 }</td>
<td>${hlbList[m.meter_sl_no].hr5 }</td>
<td>${hlbList[m.meter_sl_no].hr6 }</td>
<td>${hlbList[m.meter_sl_no].hr7 }</td>
<td>${hlbList[m.meter_sl_no].hr8 }</td>
<td>${hlbList[m.meter_sl_no].hr9 }</td>
<td>${hlbList[m.meter_sl_no].hr10 }</td>
<td>${hlbList[m.meter_sl_no].hr11 }</td>
<td>${hlbList[m.meter_sl_no].hr12 }</td>
<td>${hlbList[m.meter_sl_no].hr13 }</td>
<td>${hlbList[m.meter_sl_no].hr14 }</td>
<td>${hlbList[m.meter_sl_no].hr15 }</td>
<td>${hlbList[m.meter_sl_no].hr16 }</td>
<td>${hlbList[m.meter_sl_no].hr17 }</td>
<td>${hlbList[m.meter_sl_no].hr18 }</td>
<td>${hlbList[m.meter_sl_no].hr19 }</td>
<td>${hlbList[m.meter_sl_no].hr20 }</td>
<td>${hlbList[m.meter_sl_no].hr21 }</td>
<td>${hlbList[m.meter_sl_no].hr22 }</td>
<td>${hlbList[m.meter_sl_no].hr23 }</td>
<td>${hlbList[m.meter_sl_no].hr24 }</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>