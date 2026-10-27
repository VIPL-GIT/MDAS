<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

<div class="col-md-12">
<h4 class="rTitle col-md-10">Reliability Index Report</h4>  
</div>
 <table class="table table-advance" cellspacing="0" width="100%" id="reliabilityIndex">
 <thead>
 <tr>
 <td>Circle</td>
 <td>Division</td>
 <td>Substation</td>
 <td>Feeder</td>
 <td>Meter</td>
 <td>Category</td>
 <td>Total No.of Consumer</td>
 <td>Total No.of Interruptions</td>
 <td>Duration</td>
<!--  <td>Total No.of Interruptions > 5 mins</td> -->
<!--  <td>Duration</td> -->
 <td>SAIFI</td>
 <td>SAIDI</td>
 <td>CAIDI</td>
 <td>MAIFI</td>
 
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${master}" var="m">
 <tr>
 <td>${fn:toUpperCase(m.circle) }</td>
 <td>${fn:toUpperCase(m.division) }</td>
 <td>${fn:toUpperCase(m.ss) }</td>
 <td>${fn:toUpperCase(m.location_name) }</td>
 <td>${fn:toUpperCase(m.meter_sl_no) }</td>
 <td>${fn:toUpperCase(m.location_category) }</td>
 
 <td>${hlbList[m.meter_sl_no].tot_consumers }</td>
 <td>${hlbList[m.meter_sl_no].tot_interrupts }</td>
 <td>${hlbList[m.meter_sl_no].tot_i_duration }</td>
<%--  <td>${hlbList[m.meter_sl_no].no_of_interrupts_gr_5 }</td> --%>
<%--  <td>${hlbList[m.meter_sl_no].duration }</td> --%>
 <td>${hlbList[m.meter_sl_no].saifi }</td>
 <td>${hlbList[m.meter_sl_no].saidi }</td>
 <td>${hlbList[m.meter_sl_no].caidi }</td>
 <td>${hlbList[m.meter_sl_no].maifi }</td>
 
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>