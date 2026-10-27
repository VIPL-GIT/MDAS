<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

<div class="col-md-12">
<h4 class="rTitle col-md-10">Energy Input Report</h4>  
</div>
 <table class="table table-advance" cellspacing="0" width="100%" id="energyInput">
 <thead>
 <tr> 
 <td >Circle</td>
 <td >Division</td>
 <td >Zone</td>
 <td >Substation</td>
 <td >Feeder</td>
 <td >Meter</td>
 <td> Feeder Code</td>
 <td >Category</td>
 <td >MF</td> 
 <td >Initial</td>
 <td >Final</td>
 <td>Difference</td>
 <td> ASU</td> 
 <td>Input (in KWH)</td>
 </tr>
 
 </thead>
 <tbody>
 <c:forEach items="${master}" var="m">
 <c:if test="${fn:contains(m.supply_voltage,'11') }">
 <tr>
 
 <td>${fn:toUpperCase(m.circle) }</td>
 <td>${fn:toUpperCase(m.division) }</td>
 <td>${fn:toUpperCase(m.sd) }</td>
 <td>${fn:toUpperCase(m.ss) }</td>
 <td>${fn:toUpperCase(m.location_name) }</td>
 <td><a href="javascript:monthlyEAPopup('${fn:toUpperCase(m.meter_sl_no) }')">${fn:toUpperCase(m.meter_sl_no) }</a></td>
 <td>${fn:toUpperCase(m.location_code) }</td>
 <td>${fn:toUpperCase(m.location_category) }</td>
 <td>${m.mf }</td>
<td>${map[m.meter_sl_no].kwh_initial }</td>
 <td>${map[m.meter_sl_no].kwh_final }</td>
<td>${map[m.meter_sl_no].consumption }</td>
<td>${map[m.meter_sl_no].assessed_units }</td>
<td>${map[m.meter_sl_no].kwh_consumption }</td>
 </tr>
 </c:if>
 </c:forEach>
 </tbody>
 </table>
 </div>
 
 <div id="bi_modal" class="modal"></div>