<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" >   

 <table cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont" id="avgSupply">
 <thead>
 <tr>
 <td rowspan="1">Circle</td>
 <td rowspan="1">Division</td>
 <td rowspan="1">Substation</td>
 <td rowspan="1">Feeder</td>
 <td rowspan="1">Meter</td>
 <td rowspan="1">Category</td>
<!--  <td colspan="31">Daily Supply Hrs</td> -->
 <td rowspan="1">Avg. Supply Hrs</td>
 </tr>
<!--  <tr> -->
<%--  <% --%>
<!--   for(int day=1;day<=31;day++){ -->
<%-- %><td><%=day %></td>	  --%>
<%--  <%} --%>
<%--  %> --%>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${master}" var="m">
 <tr>
 <td>${fn:toUpperCase(m.circle) }</td>
 <td>${fn:toUpperCase(m.division) }</td>
 <td>${fn:toUpperCase(m.ss) }</td>
 <td>${fn:toUpperCase(m.location_name) }</td>
 <td>${fn:toUpperCase(m.meter_sl_no) }</td>
 <td>${fn:toUpperCase(m.location_category) }</td>
<%--  <td>${hlbList[m.meter_sl_no].dsh_1 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_2 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_3 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_4 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_5 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_6 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_7 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_8 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_9 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_10 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_11 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_12 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_13 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_14 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_15 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_16 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_17 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_18 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_19 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_20 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_21 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_22 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_23 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_24 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_25 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_26 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_27 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_28 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_29 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_30 }</td> --%>
<%-- <td>${hlbList[m.meter_sl_no].dsh_31 }</td> --%>
<td>${hlbList[m.meter_sl_no].avg_supply_hrs }</td> 
 
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>