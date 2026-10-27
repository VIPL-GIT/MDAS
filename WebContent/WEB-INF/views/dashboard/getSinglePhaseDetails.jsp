<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import="utils.DBUtil"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%

%>
<div class="modal-content" style="margin-left:5%;">
    <span class="biclose">&times;</span>
    <h4 style="text-align:center;">Single Phase Timestamps </h4>
        
       
<div class="table-responsive" style="border:0" >
<table class="table table-advance" cellspacing="0" width="100%" id="singleTimes">
<thead>
<tr>
<th>From</th>
<th>To</th>
</tr>
</thead>
<tbody>
<c:forEach items="${map}" var="m">
<tr>
<td>${m.key }</td>
<td>${m.value }</td>
</tr>
</c:forEach>
</tbody>
</table>

</div>

    </div>
    
<script>
callDataTableWithName_5('singleTimes','singlephase_times');

</script>    