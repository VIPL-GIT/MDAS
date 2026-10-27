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
 <li> <a href="javascript:getAvgSupplyHrsReport('<%=hlevel %>','<%=obj.get(0)[(i*3)+1] %>','<%=obj.get(0)[i*3] %>')">
 <%=obj.get(0)[i*3] %></a> <span class="divider"></span></li>
 <%
 hlevel++; 
}//for

}//if%>
</ul>
<div class="col-md-12">
<h4 class="rTitle col-md-10">Average Supply Hrs</h4>  
<button class="btn btn-sm col-md-1" onclick="exportDivtoExcel('Avg_supplyHrs')">Excel</button>
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
	 <td rowspan="2">Meter Sl.No</td>
	 <td rowspan="2">Category</td>	 
 </c:if>
 <td colspan="31">Daily Supply Hrs</td>
 <td rowspan="2">Avg. Supply Hrs</td> 
 </tr>
 <tr>
 <%
 for(int day=1;day<=31;day++){
%><td><%=day %></td>	 
 <%}
 %>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${ashList}" var="ash">
 <tr>
 <c:if test="${titleSize ne level }">
  <td><a href="javascript:getAvgSupplyHrsReport('${ash.hlevel }','${ash.hlevel_id }','${ash.hlevel_name }')" >${ash.hlevel_name }</a></td>
 </c:if>
 <c:if test="${titleSize==level }">
 <td>${ash.feeder }</td>
 <td>${ash.voltage }</td>
 <td>${ash.meterno }</td>
 <td>${ash.category }</td>
 <td>${ash.capacity }</td>
 </c:if>
 <td>${ash.dsh_1 }</td>
 <td>${ash.dsh_2 }</td>
 <td>${ash.dsh_3 }</td>
 <td>${ash.dsh_4 }</td>
 <td>${ash.dsh_5 }</td>
 <td>${ash.dsh_6 }</td>
 <td>${ash.dsh_7 }</td>
 <td>${ash.dsh_8 }</td>
 <td>${ash.dsh_9 }</td>
 <td>${ash.dsh_10 }</td>
 <td>${ash.dsh_11 }</td>
 <td>${ash.dsh_12 }</td>
 <td>${ash.dsh_13 }</td>
 <td>${ash.dsh_14 }</td>
 <td>${ash.dsh_15 }</td>
 <td>${ash.dsh_16 }</td>
 <td>${ash.dsh_17 }</td>
 <td>${ash.dsh_18 }</td>
 <td>${ash.dsh_19 }</td>
 <td>${ash.dsh_20 }</td>
 <td>${ash.dsh_21 }</td>
 <td>${ash.dsh_22 }</td>
 <td>${ash.dsh_23 }</td>
 <td>${ash.dsh_24 }</td>
 <td>${ash.dsh_25 }</td>
 <td>${ash.dsh_26 }</td>
 <td>${ash.dsh_27 }</td>
 <td>${ash.dsh_28 }</td>
 <td>${ash.dsh_29 }</td>
 <td>${ash.dsh_30 }</td>
 <td>${ash.dsh_31 }</td>
 <td>${mpl.avg_supply_hrs }</td>
 </c:forEach>
 </tbody>
 </table>
 </div>