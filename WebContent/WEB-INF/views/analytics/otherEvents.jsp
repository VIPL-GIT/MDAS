<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
    <script src="assets/jquery-validation/jquery.validate.min.js"></script>
    <script src="assets/jquery-validation/additional-methods.min.js"></script>

<div class="table-responsive" style="border:0">
    <c:if test="${!empty list}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
  <th>Tamper Name</th>
   <th>Occurance Date</th>
   <th>Restoration Date</th>
   <th>KWH Reading Cumulative During Occurance</th>   
   <th>KWH Reading Cumulative During Restoration</th>
   <th>Tamper Duration ( Days:Hrs:Mints)</th>  
  </tr>
</thead>
<tbody>
  <c:forEach items="${list}" var="l">
   <tr>
    <td>${l.event_name }</td>
    <td>${l.o_timestamp}</td>
    <td>${l.r_timestamp}</td>
    <td>${l.o_kwh}</td>
    <td>${l.r_kwh }</td>
    <td>${l.duration }</td>
   
   </tr>
  </c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty list}">
<center><h3>No Data Available</h3></center>
</c:if>
</div>