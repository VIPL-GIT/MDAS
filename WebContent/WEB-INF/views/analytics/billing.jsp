<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
    <script src="assets/jquery-validation/jquery.validate.min.js"></script>
    <script src="assets/jquery-validation/additional-methods.min.js"></script>

<div class="table-responsive" style="border:0">
    <c:if test="${!empty list}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
   <th>Billing Dates</th>
   <th>KWH (Imp) Cumulative</th>
   <th>KWH (Exp) Cumulative</th>
   <th>KVAH (Imp) Cumulative</th>
   <th>Demand (KW)</th>
   <th>Demand(KVA)</th>   
   <th>KVArH (Lead)</th>
   <th>KVArH (Lag)</th>
   <th>Avg PF</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${list}" var="l">
   <tr>
    <td>${l.datetime }</td>
    <td>${l.KWH_I eq null?l.KWH_ABS:l.KWH_I }</td>
    <td>${l.KWH_E }</td>
    <td>${l.KVAH_I eq null?KVAH_ABS:l.KVAH_I}</td>
    <td>${l.MDKW_I }</td>
    <td>${l.MDKVA_I }</td>
    <td>${l.KVARH_Lead }</td>
    <td>${l.KVARH_Lag }</td>
    <td>${l.PF }</td>
   </tr>
  </c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty list}">
<center><h3>No Data Available</h3></center>
</c:if>
</div>