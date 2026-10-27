<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
    <script src="assets/jquery-validation/jquery.validate.min.js"></script>
    <script src="assets/jquery-validation/additional-methods.min.js"></script>

<div class="table-responsive" style="border:0">
    <c:if test="${!empty list}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
   <th>Power Failure Occurance Date</th>
   <th>Occurance Time</th>
   <th>Power Failure Restoration Date</th>
   <th>Restoration Time</th>
   <th>Duration (Hrs:Mints:Sec)</th>  
  </tr>
</thead>
<tbody>
  <c:forEach items="${list}" var="l">
   <tr>
    <td>${l.o_timestamp.split(" ")[0] }</td>
    <td>${l.o_timestamp.split(" ")[1]}</td>
    <td>${l.r_timestamp.split(" ")[0] }</td>
    <td>${l.r_timestamp.split(" ")[1]}</td>
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