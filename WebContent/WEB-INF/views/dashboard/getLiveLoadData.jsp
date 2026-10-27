<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*" %>
<%@page import="com.rtdas.dashboard.beans.*" %>

<style>
.load_relief{
 display:none;
}
.table-responsive th{
padding:3px !important;
}
</style>
<table class="table table-advance" cellspacing="0" width="100%" id="liveload">
<thead>
<tr>
<th rowspan="2">Category</th>
<c:forEach var="c" items="${circles}">
 <th> <c:out value="${fn:replace(c, '&amp;', '&')}"/></th>  
</c:forEach>

<!-- <th rowspan="2">Total Load</th> -->

</tr>

</thead>
<%
try{
	

%>
<tbody>
<tr>
<td>AG</td>
<c:forEach var="c" items="${circles}">
 <td>${agmap[c] } </td>  
</c:forEach>
</tr>

<tr>
<td>Non-AG</td>
<c:forEach var="c" items="${circles}">
 <td>${nonagmap[c] } </td>  
</c:forEach>
</tr>
</tbody>
<%}catch(Exception e){
	
} %>
</table>