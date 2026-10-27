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
 <li> <a href="javascript:getMonthlyPeakLoadReport('<%=hlevel %>','<%=obj.get(0)[(i*3)+1] %>','<%=obj.get(0)[i*3] %>')">
 <%=obj.get(0)[i*3] %></a> <span class="divider"></span></li>
 <%
 hlevel++; 
}//for

}//if%>
</ul>
<div class="col-md-12">
<h4 class="rTitle col-md-10">Monthly Peak Load</h4> 
<button class="btn btn-sm col-md-1" onclick="exportDivtoExcel('Monthly_peakLoad')">Excel</button>
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
	 <td rowspan="2">Capacity</td>
 </c:if>
 <td colspan="31">Daily Peak Load</td>
 <td rowspan="2">Monthly Peak Load</td> 
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
 <c:forEach items="${hlbList}" var="mpl">
 <tr>
 <c:if test="${titleSize ne level }">
  <td><a href="javascript:getMonthlyPeakLoadReport('${mpl.hlevel }','${mpl.hlevel_id }','${mpl.hlevel_name }')" >${mpl.hlevel_name }</a></td>
 </c:if>
 <c:if test="${titleSize==level }">
 <td>${mpl.feeder }</td>
 <td>${mpl.voltage }</td>
 <td>${mpl.meterno }</td>
 <td>${mpl.category }</td>
 <td>${mpl.capacity }</td>
 </c:if>
 <td>${mpl.dpl_1 }</td>
 <td>${mpl.dpl_2 }</td>
 <td>${mpl.dpl_3 }</td>
 <td>${mpl.dpl_4 }</td>
 <td>${mpl.dpl_5 }</td>
 <td>${mpl.dpl_6 }</td>
 <td>${mpl.dpl_7 }</td>
 <td>${mpl.dpl_8 }</td>
 <td>${mpl.dpl_9 }</td>
 <td>${mpl.dpl_10 }</td>
 <td>${mpl.dpl_11 }</td>
 <td>${mpl.dpl_12 }</td>
 <td>${mpl.dpl_13 }</td>
 <td>${mpl.dpl_14 }</td>
 <td>${mpl.dpl_15 }</td>
 <td>${mpl.dpl_16 }</td>
 <td>${mpl.dpl_17 }</td>
 <td>${mpl.dpl_18 }</td>
 <td>${mpl.dpl_19 }</td>
 <td>${mpl.dpl_20 }</td>
 <td>${mpl.dpl_21 }</td>
 <td>${mpl.dpl_22 }</td>
 <td>${mpl.dpl_23 }</td>
 <td>${mpl.dpl_24 }</td>
 <td>${mpl.dpl_25 }</td>
 <td>${mpl.dpl_26 }</td>
 <td>${mpl.dpl_27 }</td>
 <td>${mpl.dpl_28 }</td>
 <td>${mpl.dpl_29 }</td>
 <td>${mpl.dpl_30 }</td>
 <td>${mpl.dpl_31 }</td>
 <td>${mpl.monthly_peakLoad }</td>
 </c:forEach>
 </tbody>
 </table>
 </div>