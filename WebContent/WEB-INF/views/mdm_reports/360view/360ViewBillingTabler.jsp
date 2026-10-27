<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>

<c:choose>
<c:when test="${not empty error}">
<div class="col-md-12" >
<p style="min-height: 100px;vertical-align:center;line-height:150px;">Error : ${error }</p>
</div>
</c:when>
<c:otherwise>
<table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
  <th>Date Time</th>
  <c:forTokens items="${result.b3_header.billing_columns_header }" delims="," var="mySplit">
   <th><c:out value="${mySplit}"/></th>
</c:forTokens>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${result.energies}" var="d3">
   <tr>
   <td>${d3.d3_time_stamp}</td>
   <c:forTokens items="${d3.data_string }" delims="," var="mySplit">
   <td><c:out value="${mySplit}"/></td>
</c:forTokens>
   </tr>
   </c:forEach>
  
  </tbody>
  </table>
  </c:otherwise>
  </c:choose>