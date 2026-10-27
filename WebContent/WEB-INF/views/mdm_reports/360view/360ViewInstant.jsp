<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- <script src="./js/highcharts/highstock.js"></script> -->

<c:choose>
<c:when test="${not empty error}">
<div class="col-md-12" >
<p style="min-height: 100px;vertical-align:center;line-height:150px;" class="error">Error : ${error }</p>
</div>
</c:when>
<c:otherwise>
<!-- <div class="col-md-12"> -->
<!-- <div id="voltages" class="col-md-4" style="padding:0px !important;"></div> -->
<!-- <div id="currents" class="col-md-4" style="padding:0px !important;"></div> -->
<!-- <div id="pfs" class="col-md-4" style="padding:0px !important;"></div> -->
<!-- </div> -->
<div class="col-md-12">
<table style="border:1px solid #CCC" width="100%" id="instantTable">
 <thead><tr><th></th><th>Voltages</th><th>Currents</th><th>PFs</th><th>Phaser</th></tr></thead>
 <tbody>
 <tr><td>R-ph</td><td>${result.RPH_VOLTAGE } V</td><td>${result.RPH_LINE_CURRENT } A</td><td>${result.RPH_POWER_FACTOR }</td><td id="phaserValues" rowspan="5"></td></tr>
 <tr><td>Y-ph</td><td>${result.YPH_VOLTAGE } V</td><td>${result.YPH_LINE_CURRENT } A</td><td>${result.YPH_POWER_FACTOR }</td></tr>
 <tr><td>B-ph</td><td>${result.BPH_VOLTAGE } V</td><td>${result.BPH_LINE_CURRENT } A</td><td>${result.BPH_POWER_FACTOR }</td></tr>
 <tr><td  rowspan="2">RTC Status</td><td colspan="3">Meter&nbsp;&nbsp;&nbsp;&nbsp;:${result.g2 }<sub></sub></td></tr>
 <tr><td colspan="3">Modem :${result.g3 }</td></tr>
<!--  <tr><td>Phaser </td><td id="phaserValues" colspan="3"></td></tr> -->
 </tbody>
 </table>
</div>
</c:otherwise>
</c:choose>


<input type="hidden" id="ip_values" value="${result.RPH_POWER_FACTOR },${result.YPH_POWER_FACTOR },${result.BPH_POWER_FACTOR },${result.RPH_VOLTAGE },${result.YPH_VOLTAGE },${result.BPH_VOLTAGE },${result.RPH_LINE_CURRENT },${result.YPH_LINE_CURRENT },${result.BPH_LINE_CURRENT },0,0"/>


<script>
$(document).ready(function() {
//getPhaseGuage('${result.RPH_POWER_FACTOR }','${result.YPH_POWER_FACTOR }','${result.BPH_POWER_FACTOR }','${result.RPH_VOLTAGE }','${result.YPH_VOLTAGE }','${result.BPH_VOLTAGE }','${result.RPH_LINE_CURRENT }','${result.YPH_LINE_CURRENT }','${result.BPH_LINE_CURRENT }','0','0');

});
</script>