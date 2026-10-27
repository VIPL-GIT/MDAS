<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

<div class="col-md-12">
<h4 class="rTitle col-md-10">Demand Forecasting Report</h4>  
</div>
 <table class="table table-advance" cellspacing="0" width="100%" id="forecasting">
 <thead>
 <tr> 
 <td >Circle</td>
 <td >Division</td>
 <td >Zone</td>
 <td >Substation</td>
 <td >Feeder</td>
 <td >Meter</td>
 <td >Category</td>
 <td >MF</td> 
 <td >By Year</td>
 <td>By Month</td>
 <td>Observation</td>
 </tr>
 
 </thead>
 <tbody>
 <c:forEach items="${master}" var="m">
 <tr>
 
 <td>${fn:toUpperCase(m.circle) }</td>
 <td>${fn:toUpperCase(m.division) }</td>
 <td>${fn:toUpperCase(m.sd) }</td>
 <td>${fn:toUpperCase(m.ss) }</td>
 <td>${fn:toUpperCase(m.location_name) }</td>
 <td>${fn:toUpperCase(m.meter_sl_no) }</td>
 <td>${fn:toUpperCase(m.location_category) }</td>
 <td>${m.mf }</td>
 <td>${hlbList[m.meter_sl_no].forcast_year_based_kw }</td>
 <td>${hlbList[m.meter_sl_no].forcast_month_based_kw }</td>
 <td>${hlbList[m.meter_sl_no].observation }</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>