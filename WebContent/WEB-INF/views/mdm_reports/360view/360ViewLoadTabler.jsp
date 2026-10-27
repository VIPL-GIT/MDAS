<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>

<c:choose>
<c:when test="${not empty error}">
<div class="col-md-12" >
<p style="min-height: 100px;vertical-align:center;line-height:150px;" class="error">Error : ${error }</p>
</div>
</c:when>
<c:otherwise>
<table class="table table-advance" cellspacing="0" width="100%" id="table2">
 <thead>
  <tr>
  <th>Date Time</th>
  <c:forTokens items="${result.header.load_columns_header }" delims="," var="mySplit">
   <th><c:out value="${mySplit}"/></th>
</c:forTokens>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${result.data}" var="d4">
   <tr>
   <td>${d4.d4_date}</td>
   <c:forTokens items="${d4.data_string }" delims="," var="mySplit">
   <td><c:out value="${mySplit}"/></td>
</c:forTokens>
   </tr>
   </c:forEach>
  
  </tbody>
  </table>
  </c:otherwise>
  </c:choose>