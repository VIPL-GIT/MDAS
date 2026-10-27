<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import="utils.DBUtil"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%

%>
<div class="modal-content" style="margin-left:5%;margin-top:3%;">
    <span class="biclose">&times;</span>
    <h4 style="text-align:center;">Energy Savings Details</h4>
        
       
<div class="table-responsive" style="border:0" >
<table class="table table-advance" cellspacing="0" width="100%" id="events">
<thead>
<tr>

<th>Date Time</th>
<th>Phase Supply</th>
<th>Energy Utilised</th>
<th>Energy Saved</th>
</tr>
</thead>

<tbody>
<c:forEach items="${list}" var="l">
 <tr>
 <td>${l.d4_date }</td>
  <td>${l.phase_supply }</td>
   <td>${l.energy_utilised }</td>
    <td>${l.energy_saved }</td>
 </tr>
 </c:forEach>
</tbody>
</table>

</div>

    </div>
    
<script>
callDataTableWithName_5('events','EnergySavings_details');

</script>    