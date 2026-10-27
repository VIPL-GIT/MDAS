<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

<div class="col-md-12">
<h4 class="rTitle col-md-10">Energy Input Report</h4>  
</div>
 <table class="table table-advance" cellspacing="0" width="100%" id="energyInput">
 <thead>
 <tr>
 <c:if test="${level eq '0' }">
 <td rowspan="2">Region</td>
 </c:if>
 <td rowspan="2">Circle</td>
 <td rowspan="2">Division</td>
 <td rowspan="2">Zone</td>
 <td rowspan="2">Substation</td>
 <td rowspan="2">Feeder</td>
 <td rowspan="2">Code</td>
 <td rowspan="2">Meter</td>
 <td rowspan="2">Category</td>
 <td rowspan="2">MF</td> 
 <td colspan="3">KWH</td>
 <td colspan="3">KVAH</td>
 <td colspan="5">MD</td>
 
 </tr>
 <tr>
 
<!-- 	 <td>ME CT</td> -->
<!-- 	 <td>ME PT</td> -->
<!-- 	 <td>Meter CT</td> -->
<!-- 	 <td>Meter PT</td> -->
<!-- 	 <td>EMF</td>  -->
 
 <td>Initial</td>
 <td>Final</td>
 <td>Consumption</td>
 <td>Initial</td>
 <td>Final</td>
 <td>Consumption</td>
 <td>KVA</td>
 <td>Occ.Date Time</td>
 <td>KW</td>
 <td>Occ.Date Time</td>
<!--  <td>Reset Date</td> -->
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${master}" var="m">
 <tr>
 <c:if test="${level eq '0'}">
 <td>${fn:toUpperCase(m.region) }</td>
 </c:if>
 <td>${fn:toUpperCase(m.circle) }</td>
 <td>${fn:toUpperCase(m.division) }</td>
 <td>${fn:toUpperCase(m.sd) }</td>
 <td>${fn:toUpperCase(m.ss) }</td>
 <td>${fn:toUpperCase(m.location_name) }</td>
 <td>${fn:toUpperCase(m.location_code) }</td>
 <td>${fn:toUpperCase(m.meter_sl_no) }</td>
 <td>${fn:toUpperCase(m.location_category) }</td>
 <td>${m.mf }</td>
 
<%--  <td>${hlbList[m.meter_sl_no].me_ct }</td> --%>
<%--  <td>${hlbList[m.meter_sl_no].me_pt }</td> --%>
<%--  <td>${hlbList[m.meter_sl_no].meter_ct }</td> --%>
<%--  <td>${hlbList[m.meter_sl_no].meter_pt }</td> --%>
<%--  <td>${hlbList[m.meter_sl_no].emf }</td> --%>
 <td>${hlbList[m.meter_sl_no].kwh_initial }</td>
 <td>${hlbList[m.meter_sl_no].kwh_final }</td>
 <td>${hlbList[m.meter_sl_no].kwh_consumption }</td>
 <td>${hlbList[m.meter_sl_no].kvah_initial }</td>
 <td>${hlbList[m.meter_sl_no].kvah_final }</td>
 <td>${hlbList[m.meter_sl_no].kvah_consumption }</td>
 <td>${hlbList[m.meter_sl_no].md_kva }</td>
 <td>${hlbList[m.meter_sl_no].md_kva_occdate }</td>
 <td>${hlbList[m.meter_sl_no].md_kw }</td>
 <td>${hlbList[m.meter_sl_no].md_kw_occdate }</td>
<%--  <td>${hlbList[m.meter_sl_no].md_resetdate }</td> --%>
 
 
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>