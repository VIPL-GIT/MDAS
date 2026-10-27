<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import="utils.DBUtil"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%

%>
<div class="modal-content">
    <span class="biclose">&times;</span>
    <h4 style="text-align:center;">SAIDI / SAIFI</h4>
        
       
<div class="table-responsive" style="border:0" >
<table class="table table-advance" cellspacing="0" width="100%" id="dashpopup">
<thead>
<tr>
<td>Circle</td>
<td>SAIDI (dd hh:mm)</td>
<td>SAIFI</td>
</tr>
</thead>
<tbody>

<c:forEach items="${ssMap}" var="m">
<tr>
<td><a href="javascript:downloadSaidiSaifi('${m.key }','${levelStr }','${levelID }')">${m.key }(${m.value.tot_consumers })</a></td>
<td>${m.value.saidi }</td>
<td>${m.value.saifi }</td>
</tr>
</c:forEach>

</tbody>
</table>
</div>

    </div>
    
<script>
callDataTableWithName_5('dashpopup','SAIDISAIFI');

</script>    