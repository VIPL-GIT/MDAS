<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   


<div class="col-md-12">
<div class="col-sm-3 col-lg-3 controls">
	<select class="" id="load_type" onchange="getOluvovpf(this.value)" style="width:100%;height:25px;">
		
		<option value="ol" ${type eq "ol"?"selected":"" }>Overloaded Feeders </option>
		<option value="uv" ${type eq "uv"?"selected":"" }>Under voltage Feeders</option>
		<option value="ov" ${type eq "ov"?"selected":"" }>Over Voltage Feeders</option>
		<option value="pf" ${type eq "pf"?"selected":"" }>Low PF Feeders</option>
		
	</select>
						</div>
<h4 class="rTitle col-md-7">${title}</h4>  
<!-- <button class="btn btn-sm col-md-1" onclick="exportDivtoExcel('Hourly_load')">Excel</button> -->
<!-- <button class="btn btn-sm col-md-1" onclick="printDiv()">Print</button> -->
</div>
 <table class="table table-advance" cellspacing="0" width="100%" id="oluvovpf">
 <thead>
 <tr>
 <td>Circle</td>
 <td>Division</td>
 <td>Substation</td>
 <td>Feeder</td>
 <td>Meter</td>
 <td>Category</td>
 <td>Date</td>
 <c:if test="${type ne 'pf' }">
 <td>${type eq "ol"?"IMF":"VMF" }</td>
 </c:if>
 
 <td>Value</td>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${map}" var="m">
 <tr>
 <td>${fn:toUpperCase(master[m.meter_serial_no].circle) }</td>
 <td>${fn:toUpperCase(master[m.meter_serial_no].division) }</td>
 <td>${fn:toUpperCase(master[m.meter_serial_no].ss) }</td>
 <td>${fn:toUpperCase(master[m.meter_serial_no].location_name) }</td>
 <td>${fn:toUpperCase(master[m.meter_serial_no].meter_sl_no) }</td>
 <td>${fn:toUpperCase(master[m.meter_serial_no].location_category) }</td>
 <td>${m.date }</td>
 <c:if test="${type ne 'pf' }">
 <c:if test="${type eq 'ol' }">
    <td>${m.imf }</td>
</c:if>
<c:if test="${type eq 'uv' }">
    <td>${m.vmf }</td>
</c:if>
<c:if test="${type eq 'ov' }">
    <td>${m.vmf }</td>
</c:if>
</c:if>
<td>${m.value }</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>