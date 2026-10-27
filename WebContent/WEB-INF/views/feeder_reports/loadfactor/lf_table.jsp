<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>
<script src="js/admin.js"></script>

<div class="table-responsive" style="border:0" id="mpreports"> 
<c:if test="${not empty lf }">  
 <table class="table table-advance" cellspacing="0" width="100%" id="eventsSummary">
 <thead>
 <tr>
 <td>Month</td>
 <td>No.of Occurrences</td>
 </tr> 
 </thead>
 <tbody>
 <c:forEach items="${lf}" var="m">
 <tr>
 <td>${fn:toUpperCase(m.key) }</td>
 <td><a href="javascript:loadFactorPopup('${m.key}')">${m.value }</a></td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </c:if>
 
 <c:if test="${empty lf }">
  <p style="text-align:center;">Data is not available...</p>
 </c:if>
 </div>
 
 <div id="bi_modal" class="modal">  </div>
 
 