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
 <li> <a href="javascript:getHourlyLoadReport('<%=hlevel %>','<%=obj.get(0)[(i*3)+1] %>','<%=obj.get(0)[i*3] %>')">
 <%=obj.get(0)[i*3] %></a> <span class="divider"></span></li>
 <%
 hlevel++; 
}//for

}//if%>
</ul>
<div class="col-md-12">
<h4 class="rTitle col-md-10">Hourly Load (In Amps)</h4>  
<button class="btn btn-sm col-md-1" onclick="exportDivtoExcel('Hourly_load')">Excel</button>
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
 <%
 for(int hr=1;hr<=24;hr++){
%><td>Hrs <%=hr %></td>	 
 <%}
 %>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${hlbList}" var="hlb">
 <tr>
 <c:if test="${titleSize ne level }">
 <c:choose>
 <c:when test="${level < titleSize-3 }">
  <td><a href="javascript:getHourlyLoadReport('${hlb.hlevel }','${hlb.hlevel_id }','${hlb.hlevel_name }')" >${hlb.hlevel_name }</a></td>
 </c:when>
 <c:otherwise>
 <td>${hlb.hlevel_name }</td>
 </c:otherwise>
 </c:choose>
 </c:if>
 <c:if test="${titleSize==level }">
 <td>${hlb.feeder }</td>
 <td>${hlb.voltage }</td>
 <td>${hlb.meterno }</td>
 <td>${hlb.category }</td>
 </c:if>
 <td>${hlb.hr1 }</td>
 <td>${hlb.hr2 }</td>
 <td>${hlb.hr3 }</td>
 <td>${hlb.hr4 }</td>
 <td>${hlb.hr5 }</td>
 <td>${hlb.hr6 }</td>
 <td>${hlb.hr7 }</td>
 <td>${hlb.hr8 }</td>
 <td>${hlb.hr9 }</td>
 <td>${hlb.hr10 }</td>
 <td>${hlb.hr11 }</td>
 <td>${hlb.hr12 }</td>
 <td>${hlb.hr13 }</td>
 <td>${hlb.hr14 }</td>
 <td>${hlb.hr15 }</td>
 <td>${hlb.hr16 }</td>
 <td>${hlb.hr17 }</td>
 <td>${hlb.hr18 }</td>
 <td>${hlb.hr19 }</td>
 <td>${hlb.hr20 }</td>
 <td>${hlb.hr21 }</td>
 <td>${hlb.hr22 }</td>
 <td>${hlb.hr23 }</td>
 <td>${hlb.hr24 }</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>