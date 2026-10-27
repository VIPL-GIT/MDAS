<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="../assets/plugins/jQuery/jquery-1.12.4.min.js"></script>
<script src="../js/downloads.js"></script>


<c:if test="${!empty files}">
<input type="hidden" id="month" value="${month}"/>
<table cellspacing="10">
<c:forEach items="${files}" var="f">
 <tr><td><a href="javascript:downloadFiles('${f }')">${f}</a></td></tr>
</c:forEach>
</table>
</c:if>
<c:if test="${empty files}">
<h4>No Files are available...</h4>
</c:if>


