<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports"> 
<c:if test="${not empty zc }">  
 <table class="table table-advance" cellspacing="0" width="100%" id="eventsSummary">
 <thead>
 <tr>
 <td>Month</td>
 <td>Total ${type=="kwh_tod"?"KWh":"KVAh" }</td>
 <td>TOD-1</td>
 <td>TOD-2</td>
 <td>TOD-3</td>
 <td>TOD-4</td>
 <td>TOD-5</td>
 <td>TOD-6</td>
 <td>TOD-7</td>
 <td>TOD-8</td>
 </tr> 
 </thead>
 <tbody>
 <c:forEach items="${zc}" var="m">
 <tr>
 <td>${fn:toUpperCase(m.key) }</td>
 <td>${type=="kwh_tod"?m.value.kwh_tot:m.value.kvah_tot }</td>
 <td>${m.value.tod1 }</td>
 <td>${m.value.tod2 }</td>
 <td>${m.value.tod3 }</td>
 <td>${m.value.tod4 }</td>
 <td>${m.value.tod5 }</td>
 <td>${m.value.tod6 }</td>
 <td>${m.value.tod7 }</td>
 <td>${m.value.tod8 }</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </c:if>
 
 <c:if test="${empty zc }">
  <p style="text-align:center;">Data is not available...</p>
 </c:if>
 </div>
 
 <div id="bi_modal" class="modal">  </div>
 
 