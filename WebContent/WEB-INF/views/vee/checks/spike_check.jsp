<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*"%>
<div class="modal-content" style="margin-left:5%;margin-right:5%;margin-top: 100px;width:90%;">
    <span class="biclose">&times;</span>
    <h4 style="text-align:center;border-bottom: 2px solid #787078cc;padding-bottom: 10px">
    Spike Checks</h4>
<div class="table-responsive" style="border:0" id="mpreports">
<table class="table table-advance" cellspacing="0" width="100%" id="checks">
 <thead>
 <tr> 
 <td >Circle</td>
 <td >Division</td>
 <td >Zone</td>
 <td >Substation</td>
 <td >Feeder</td>
 <td >Meter</td>
 
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${summary}" var="m">
 <c:if test="${m.spike_check=='1' }">
 <tr>
 <td>${fn:toUpperCase(master[m.meter_serial_no].circle) }</td>
 <td>${fn:toUpperCase(master[m.meter_serial_no].division) }</td>
 <td>${fn:toUpperCase(master[m.meter_serial_no].sd) }</td>
 <td>${fn:toUpperCase(master[m.meter_serial_no].ss) }</td>
 <td>${fn:toUpperCase(master[m.meter_serial_no].location_name) }</td>
 <td>${m.meter_serial_no }</td>
 
 </tr>
 </c:if>
 </c:forEach>
 </tbody>
 </table>

</div>
</div>
<script>
callDataTableWithName_5('checks','spike_checks');

</script> 