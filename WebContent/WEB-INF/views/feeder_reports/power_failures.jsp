<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

<div class="col-md-12">
<h4 class="rTitle col-md-10">Power Failures Details</h4>  
<!-- <button class="btn btn-sm col-md-1" onclick="exportDivtoExcel('Hourly_load')">Excel</button> -->
<!-- <button class="btn btn-sm col-md-1" onclick="printDiv()">Print</button> -->
</div>
 <table class="table table-advance" cellspacing="0" width="100%" id="powerfailure">
 <thead>
 <tr>
 <td >Circle</td>
 <td >Division</td>
 <td >Substation</td>
 <td >Feeder</td>
 <td>Category</td>
 <td >Meter</td>
 <td >Sustained</td>
 <td >Transient</td>
 </tr>
 
 </thead>
 <tbody>
 <c:forEach items="${master}" var="m">
 <tr>
 <td>${fn:toUpperCase(m.circle) }</td>
 <td>${fn:toUpperCase(m.division) }</td>
 <td>${fn:toUpperCase(m.ss) }</td>
 <td>${fn:toUpperCase(m.location_name) }</td>
 <td>${fn:toUpperCase(m.location_category) }</td>
 <td>${fn:toUpperCase(m.meter_sl_no) }</td>
 <td>${map[m.meter_sl_no].sust_pf }</td>
 <td>${map[m.meter_sl_no].trans_pf }</td>

 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>