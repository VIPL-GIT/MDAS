<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<%@page import="com.rtdas.meterdata.beans.*" %>

<c:choose>
<c:when test="${not empty error}">
<div class="col-md-12" >
<p style="min-height: 100px;vertical-align:center;line-height:150px;" class="error">Error : ${error }</p>
</div>
</c:when>
<c:otherwise>
<table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
  <th>Date Time</th>
  <th>KWh</th>
  <th>KVAh</th>
  <th>KVARH Lag</th>
  <th>KVARH Lead</th>
<!--   <th>MD KW</th> -->
<!--   <th>MD KVA</th> -->
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${result}" var="auto">
   <tr>
   <td>${auto.billing_timestamp}</td>
   <td>${auto.KWH }</td>
   <td>${auto.KVAH }</td>
   <td>${auto.KVARH_Lag }</td>
   <td>${auto.KVARH_Lead }</td>
<%--    <td>${auto.MDKW }</td> --%>
<%--    <td>${auto.MDKVA }</td> --%>
   </tr>
   </c:forEach>
  
  </tbody>
  </table>
  </c:otherwise>
  </c:choose>