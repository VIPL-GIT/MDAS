<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
    <script src="assets/jquery-validation/jquery.validate.min.js"></script>
    <script src="assets/jquery-validation/additional-methods.min.js"></script>

<div class="table-responsive" style="border:0">
    <c:if test="${!empty list}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
   <th>Date</th>
   <th>KWH (Imp)</th>
   <th>Consumed</th>  
   <th>MD (KW)</th>
   <th>MD(KVA)</th>  
   
  </tr>
</thead>
<tbody>
  <c:forEach items="${list}" var="l">
   <tr>
    <td>${l.date }</td>
    <td>${l.kwh}</td>
    <td>${l.consumption }</td>
    <td>${l.md_kw}</td>
    <td>${l.md_kva }</td>    
   </tr>
  </c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty list}">
<center><h3>No Data Available</h3></center>
</c:if>
</div>