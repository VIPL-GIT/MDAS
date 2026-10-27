<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import="utils.DBUtil"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%

%>
<div class="modal-content">
    <span class="biclose">&times;</span>
    <h4 style="text-align:center;">Tripping Details</h4>
        
       
<div class="table-responsive" style="border:0" >
<table class="table table-advance" cellspacing="0" width="100%" id="dashpopup">
<thead>
<tr>
<td>Date</td>
<td>Sustained</td>
<td>Transient</td>
</tr>
</thead>
<tbody>
<c:forEach items="${map}" var="m">
   <tr>
   <td>${m.key }</td>
   <td>${m.value.split(",")[0] }</td>
   <td>${m.value.split(",")[1] }</td>
   
   </tr>
</c:forEach>
</tbody>
</table>

</div>

    </div>
    
<script>
callDataTableWithName_5('dashpopup','TrippingDetails');

</script>    