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
 <li> <a href="javascript:getCommSummaryReport('<%=hlevel %>','<%=obj.get(0)[(i*3)+1] %>','<%=obj.get(0)[i*3] %>')">
 <%=obj.get(0)[i*3] %></a> <span class="divider"></span></li>
 <%
 hlevel++; 
}//for

}//if%>
</ul>
<div class="col-md-12">
<h4 class="rTitle col-md-10"><c:if test="${titleSize==level }">Feeder </c:if>Communication Summary</h4> 
<button class="btn btn-sm col-md-2" onclick="exportDivtoExcel('Comm_summary','mpreports')">Export to Excel</button>
</div> 
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
 <tr>
 <c:if test="${titleSize ne level }">
    <td rowspan="2">${h_type_name }</td>
    <td rowspan="2">Total No of <br> Points</td>	
	 <td rowspan="2">Communicating <br> Points</td>
	 <td rowspan="2">Communication (%)</td>
	 <td colspan="5">Non Communicating Points</td>
	 <td rowspan="2">Non<br> Communicating (%)</td>
 </c:if>
 <c:if test="${titleSize eq level }">
	 <td rowspan="2">Feeder</td>
	 <td rowspan="2">Voltage</td>
	 <td rowspan="2">Meter Sl.No</td>
	 <td rowspan="2">Category</td>
	 <td rowspan="2">Communicating or Not</td>
	 <td>Communicating</td>
	 <td>Non- communicating</td>
 </c:if>
 
 </tr>
 <tr>
 <c:if test="${titleSize ne level }">
 <td>Due to<br> Cable </td>
 <td>Due to<br> Modem</td>
 <td>NOT COMMUNICATING<br> (RECTIFICATION REQUIRED)</td>
 <td>COMMISSIONING <br>  YET TO BE DONE</td>
 <td>I&C <br> YET TO BE DONE</td>
 </c:if>
 <c:if test="${titleSize eq level }">
 <td>Signal strength(from to)</td>
 <td>Last communication date</td>
 </c:if>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${csList}" var="cs">
 <tr>
 <c:if test="${titleSize ne level }">
 <c:choose>
 <c:when test="${level < titleSize-4 }">
  <td><a href="javascript:getCommSummaryReport('${cs.hlevel }','${cs.hlevel_id }','${cs.hlevel_name }')" >${cs.hlevel_name }</a></td>
  </c:when>
 <c:otherwise>
 <td>${cs.hlevel_name }</td>
 </c:otherwise>
 </c:choose>
  <td>${cs.tot_points }</td>  
  <td>${cs.comm_points }</td>
  <td>${cs.comm_percent }</td>
  <td>${cs.mcf }</td>
  <td>${cs.modem_fail }</td>
  <td>${cs.notComm }</td>
  <td>${cs.commissioning_to_be_done }</td>
  <td>${cs.iNc_to_be_done }</td>
  <td>${cs.not_comm_percent }</td>
 </c:if>
 <c:if test="${titleSize==level }">
 <td>${cs.feeder }</td>
 <td>${cs.voltage }</td>
 <td>${cs.meterno }</td>
 <td>${cs.category }</td>
 <td>${cs.comm_status }</td>
 <td>${cs.comm_signal_strength }</td>
 <td>${cs.not_comm_last_date }</td>
 </c:if>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>