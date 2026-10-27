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
 <li> <a href="javascript:getReliabilityIndexReport('<%=hlevel %>','<%=obj.get(0)[(i*3)+1] %>','<%=obj.get(0)[i*3] %>')">
 <%=obj.get(0)[i*3] %></a> <span class="divider"></span></li>
 <%
 hlevel++; 
}//for

}//if%>
</ul> 
<div class="col-md-12">
<h4 class="rTitle col-md-10">Reliability Index</h4> 
<button class="btn btn-sm col-md-2" onclick="exportDivtoExcel('Reliability_index')">Export to Excel</button>
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
 </c:if>
 <td>Total No of Consumer</td>
 <td>Total no of interuption</td>
 <td>Duration</td>
 <td>Total number of interuption > 5 Min</td>
 <td>Duration</td>
 <td>SAIFI</td>
 <td>SAIDI</td>
 <td>CAIDI</td>
 <td>MAIFI</td>
 
 </tr>
 </thead>
 <tbody>
  <c:forEach items="${riList}" var="ri">
 <tr>
 <c:if test="${titleSize ne level }">
  <td><a href="javascript:getReliabilityIndexReport('${ri.hlevel }','${ri.hlevel_id }','${ri.hlevel_name }')" >${ri.hlevel_name }</a></td>
 </c:if>
 <c:if test="${titleSize==level }">
 <td>${ri.feeder }</td>
 <td>${ri.voltage }</td>
 <td>${ri.meterno }</td>
 <td>${ri.category }</td>
 </c:if>
 <td>${ri.tot_consumers }</td>
 <td>${ri.tot_interrupts }</td>
 <td>${ri.tot_i_duration }</td>
 <td>${ri.no_of_interrupts_gr_5 }</td>
 <td>${ri.duration }</td>
 <td>${ri.saifi }</td>
 <td>${ri.saidi }</td>
 <td>${ri.caidi }</td>
 <td>${ri.maifi }</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>