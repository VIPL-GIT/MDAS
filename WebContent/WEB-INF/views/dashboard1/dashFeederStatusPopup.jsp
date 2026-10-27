<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import="utils.DBUtil"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%

%>
<div class="modal-content">
    <span class="biclose">&times;</span>
    <h4 style="text-align:center;">${title }</h4>
        
       
<div class="table-responsive" style="border:0" >
<table class="table table-advance" cellspacing="0" width="100%" id="dashpopup">
<thead>
<tr>
<td>Region</td>
<td>Circle</td>
<td>Division</td>
<td>Zone</td>
<td>Substation</td>
<td>Feeder</td>
<td>Category</td>
<td>Meter</td>

<c:if test="${fn:contains(title, 'PF')}">  
<!-- <td>RPF</td> -->
<!-- <td>YPF</td> -->
<!-- <td>BPF</td> -->
<td>Avg PF</td>
</c:if>

<c:if test="${fn:contains(title, 'Voltage')}">
 <td>VMF</td>
 <td>Max.Voltage with MF (KV)</td>
 
</c:if>

<c:if test="${fn:contains(title, 'Overloaded')}">
 <td>Conductor</td>
 <td>IMF</td>
 <td>Max.Current with MF</td>
 
</c:if>

</thead>
<tbody>
<c:forEach items="${master}" var="m">
   <tr>
   <td>${m.region }</td>
   <td>${m.circle }</td>
   <td>${m.division }</td>
   <td>${m.sd }</td>
   <td>${m.ss }</td>
   <td>${m.location_name }</td>
   <td>${m.location_category }</td>
   <td>${m.meter_sl_no }</td>
   
   <c:if test="${fn:contains(title, 'PF')}">
   <c:if test="${not empty fls[m.meter_sl_no]}">
<%--     <td>${fls[m.meter_sl_no].pfr }</td> --%>
<%--     <td>${fls[m.meter_sl_no].pfy }</td> --%>
<%--     <td>${fls[m.meter_sl_no].pfb }</td> --%>
    <td>${fls[m.meter_sl_no].pfavg }</td>
   </c:if>
   </c:if>
   
   <c:if test="${fn:contains(title, 'Voltage')}">
 <c:if test="${not empty fls[m.meter_sl_no]}">
    <td>${fls[m.meter_sl_no].vmf }</td>
    <td>${fls[m.meter_sl_no].vr }</td>
   
    
   </c:if>
</c:if>

 <c:if test="${fn:contains(title, 'Overloaded')}">
 <c:if test="${not empty fls[m.meter_sl_no]}">
    <td>${fls[m.meter_sl_no].status }</td>
    <td>${fls[m.meter_sl_no].imf }</td>
    <td>${fls[m.meter_sl_no].lr }</td>
    
    
   </c:if>
</c:if>
   
   </tr>
</c:forEach>
</tbody>
</table>

</div>

    </div>
    
<script>
callDataTableWithName_5('dashpopup','${title}');

</script>    