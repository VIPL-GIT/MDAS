<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
    <script src="assets/jquery-validation/jquery.validate.min.js"></script>
    <script src="assets/jquery-validation/additional-methods.min.js"></script>

<div class="table-responsive" style="border:0">
    <c:if test="${!empty list}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
   <th>Date Time</th>
   <th>V1</th>
   <th>V2</th>
   <th>V3</th>
   <th>L1</th>
   <th>L2</th>
   <th>L3</th>
   <th>KWH (Imp)</th>
   <th>KVAH(Imp)</th>
   <th>KVArH Lead</th>
   <th>KVArH Lag</th>
   
   
  </tr>
</thead>
<tbody>
  <c:forEach items="${list}" var="l">
   <tr>
    <td>${l.d4_date }</td>
    <td>${l.vr }</td>
    <td>${l.vy }</td>
    <td>${l.vb}</td>
    <td>${l.lr }</td>
    <td>${l.ly }</td>
    <td>${l.lb }</td>
    <td>${l.kwh }</td>
    <td>${l.kvah }</td>    
    <td>${l.kvarhlead }</td>
    <td>${l.kvarhlag }</td>
   </tr>
  </c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty list}">
<center><h3>No Data Available</h3></center>
</c:if>
</div>