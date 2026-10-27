<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="modal-content" style="margin-left:6%;margin-top:6%;width:60%;">
    <span class="biclose">&times;</span>
    <h4 style="text-align:center;">Load Factor Details</h4>
    
<div class="table-responsive" style="border:0" >
<table class="table table-advance" cellspacing="0" width="100%" id="events">
<thead>
<tr>
<th>Meter</th>
<th>Load Factor</th>
</tr>
</thead>
<tbody>
<c:forEach items="${lf}" var="m">
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
callDataTableWithName_5('events','load_factor');

</script>      