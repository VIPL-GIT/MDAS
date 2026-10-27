<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<div class="table-responsive" style="border:0" id="mpreports">  
<ul class="breadcrumb" >
<%
List<Object[]> obj=(List<Object[]>)request.getAttribute("bcd");
if(obj!=null){
int level=Integer.parseInt(request.getAttribute("level").toString());
int hlevel=1;
for(int i=0;i<level;i++){
%>
 <li> <a href="javascript:getDailySupplyHrsReport('<%=hlevel %>','<%=obj.get(0)[(i*3)+1] %>','<%=obj.get(0)[i*3] %>')">
 <%=obj.get(0)[i*3] %></a> <span class="divider"></span></li>
 <%
 hlevel++; 
}//for

}//if%>
</ul> 
<div class="col-md-12">
<h4 class="rTitle col-md-10">Daily Supply Hrs</h4>
<button class="btn btn-sm col-md-1" onclick="exportDivtoExcel('Daily_supplyHrs')">Excel</button>
<button class="btn btn-sm col-md-1" onclick="printDiv()">Print</button>
</div>  
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
 <tr>
 
 <c:if test="${titleSize ne level }">
    <td rowspan="2">${h_type_name }</td>
 </c:if>
 <c:if test="${titleSize eq level }">
	 <td rowspan="2">Feeder</td>
	 <td rowspan="2">Voltage</td>
	 <td rowspan="2">Category</td>
	 <td rowspan="2">Meter Sl.No</td>	 
 </c:if>
 <td colspan="24">Supply Hrs</td>
 <td colspan="2">00:00-06:00</td>
 <td colspan="2">06:00-12:00</td>
 <td colspan="2">12:00-18:00</td>
 <td colspan="2">18:00-24:00</td>
 <td rowspan="2">Total Supply Hours ( out of 24Hr)</td>
 <td rowspan="2">Scheduled Supply Hours (10/24Hr)</td>
 <td rowspan="2">Total Deviation in Supply Hours</td>
 </tr>
 <tr>
 <%
 for(int hr=1;hr<=24;hr++){
%><td>Hrs <%=hr %></td>	 
 <%}
 %>
 <td>HH:MM</td>
 <td>Consumption</td>
  <td>HH:MM</td>
 <td>Consumption</td>
  <td>HH:MM</td>
 <td>Consumption</td>
  <td>HH:MM</td>
 <td>Consumption</td>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${dsbList}" var="dsb">
 <tr>
 <c:if test="${titleSize ne level }">
  <td><a href="javascript:getDailySupplyHrsReport('${dsb.hlevel }','${dsb.hlevel_id }','${dsb.hlevel_name }')" >${dsb.hlevel_name }</a></td>
 </c:if>
 <c:if test="${titleSize==level }">
 <td>${dsb.feeder }</td>
 <td>${dsb.voltage }</td>
 <td>${dsb.meterno }</td>
 <td>${dsb.category }</td>
 </c:if>
 <td>${dsb.hr1 }</td>
 <td>${dsb.hr2 }</td>
 <td>${dsb.hr3 }</td>
 <td>${dsb.hr4 }</td>
 <td>${dsb.hr5 }</td>
 <td>${dsb.hr6 }</td>
 <td>${dsb.hr7 }</td>
 <td>${dsb.hr8 }</td>
 <td>${dsb.hr9 }</td>
 <td>${dsb.hr10 }</td>
 <td>${dsb.hr11 }</td>
 <td>${dsb.hr12 }</td>
 <td>${dsb.hr13 }</td>
 <td>${dsb.hr14 }</td>
 <td>${dsb.hr15 }</td>
 <td>${dsb.hr16 }</td>
 <td>${dsb.hr17 }</td>
 <td>${dsb.hr18 }</td>
 <td>${dsb.hr19 }</td>
 <td>${dsb.hr20 }</td>
 <td>${dsb.hr21 }</td>
 <td>${dsb.hr22 }</td>
 <td>${dsb.hr23 }</td>
 <td>${dsb.hr24 }</td>
 <td>${dsb.slot1_duration }</td>
 <td>${dsb.slot1_consumption }</td>
 <td>${dsb.slot2_duration }</td>
 <td>${dsb.slot2_consumption }</td>
 <td>${dsb.slot3_duration }</td>
 <td>${dsb.slot3_consumption }</td>
 <td>${dsb.slot4_duration }</td>
 <td>${dsb.slot4_consumption }</td>
 <td>${dsb.total_supply_hrs }</td>
 <td>${dsb.scheduled_hrs }</td>
 <td>${dsb.deviated_hrs }</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>