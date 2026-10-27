<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import="utils.DBUtil"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%

%>
<div class="modal-content" style="margin-left:5%;margin-top:3%;">
    <span class="biclose">&times;</span>
    <h4 style="text-align:center;">Events Summary</h4>
        
       
<div class="table-responsive" style="border:0" >
<table class="table table-advance" cellspacing="0" width="100%" id="events">
<thead>
<tr>
<th>Meter</th>
<th>Event Date</th>
<th>Occurred Time</th>
<th>Restored Time</th>
<th>No.of Interrupts</th>
</tr>
</thead>

<tbody>
<c:forEach items="${map}" var="m">
 <tr>
 <td>${m.split("@")[0] }</td>
 <td>${m.split("@")[1] }</td>
 <td>${m.split("@")[2] }</td>
 <td>${m.split("@")[3] }</td>
 <td>${m.split("@")[4] }</td>
 </tr>
 </c:forEach>
</tbody>
</table>

</div>

    </div>
    
<script>
callDataTableWithName_5('events','events_summary');

</script>    