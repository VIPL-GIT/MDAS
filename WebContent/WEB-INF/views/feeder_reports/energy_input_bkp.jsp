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
 <li> <a href="javascript:getEnergyInput('<%=hlevel %>','<%=obj.get(0)[(i*3)+1] %>','<%=obj.get(0)[i*3] %>')">
 <%=obj.get(0)[i*3] %></a> <span class="divider"></span></li>
 <%
 hlevel++; 
}//for

}//if%>
</ul>
<div class="col-md-12">
<h4 class="rTitle col-md-10">Energy Input Report</h4> 
<button class="btn btn-sm col-md-1" onclick="exportDivtoExcel('Energy_input')">Excel</button>
<button class="btn btn-sm col-md-1" onclick="printDiv()">Print</button>
</div> 
 <table class="table table-advance" cellspacing="0" width="100%" id="frtable">
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
	 <td colspan="5">MF</td>
 </c:if>
 <td colspan="3">KWH</td>
 <td colspan="3">KVAH</td>
 <td colspan="5">MD</td>
 <td rowspan="2">Avg PF</td>
 </tr>
 <tr>
 <c:if test="${titleSize==level }">
	 <td>ME CT</td>
	 <td>ME PT</td>
	 <td>Meter CT</td>
	 <td>Meter PT</td>
	 <td>EMF</td> 
 </c:if>
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
 <td>Reset Date</td>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${eiblist}" var="eib">
 <tr>
 <c:if test="${titleSize ne level }">
  <td><a href="javascript:getEnergyInput('${eib.hlevel }','${eib.hlevel_id }','${eib.hlevel_name }')" >${eib.hlevel_name }</a></td>
 </c:if>
 <c:if test="${titleSize==level }">
 <td>${eib.feeder }</td>
 <td>${eib.voltage }</td>
 <td>${eib.category }</td>
 <td>${eib.meterno }</td>
 <td>${eib.me_ct }</td>
 <td>${eib.me_pt }</td>
 <td>${eib.meter_ct }</td>
 <td>${eib.meter_pt }</td>
 <td>${eib.emf }</td>
 </c:if>
 <td>${eib.kwh_initial }</td>
 <td>${eib.kwh_final }</td>
 <td>${eib.kwh_consumption }</td>
 <td>${eib.kvah_initial }</td>
 <td>${eib.kvah_final }</td>
 <td>${eib.kvah_consumption }</td>
 <td>${eib.md_kva }</td>
 <td>${eib.md_kva_occdate }</td>
 <td>${eib.md_kw }</td>
 <td>${eib.md_kw_occdate }</td>
 <td>${eib.md_resetdate }</td>
 <td>${eib.avgPf }</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>