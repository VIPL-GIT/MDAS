<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
	 
 </c:if>
 <c:forEach items="${dates}" var="d">
 <td colspan="2">${d }</td>
 </c:forEach> 
 </tr>
 <tr>
 <c:forEach items="${dates}" var="d">
 <td>Comm count</td>
 <td>Comm %</td>
 </c:forEach>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${map}" var="m">
 <tr>
 <td>${m.key.split("@")[0] }</td>
 <td>${m.key.split("@")[1] }</td>
	  <c:forEach items="${dates}" var="d">
		 <td>${m.value[d] }</td>
		 <td><fmt:formatNumber type = "number" 
         maxFractionDigits = "2" value ="${(m.value[d]/m.key.split('@')[1])*100 }" /></td>
	 </c:forEach>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>