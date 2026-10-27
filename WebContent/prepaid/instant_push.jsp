<%@page import="prepaid.beans.PrepaidInstant"%>
<%@page import="prepaid.dao.PrepaidRecharge"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>


<%
  String meter=request.getParameter("meter");
String sdate=request.getParameter("sdate");
String edate=request.getParameter("edate");

  List<PrepaidInstant> details=new PrepaidRecharge().getPrepaidInstant(meter,sdate,edate);  
  request.setAttribute("details", details);
  String meter_type=details.size()>0?details.get(0).getMeter_type():"";
%>   

<div class="table-responsive" style="border:0;padding-top:10px;">
<c:if test="${!empty details}">
   
<table   class="table table-advance" cellspacing="0"  id="table1" width="100%" >
    <thead>
<tr>
    
<!-- <th>Meter No</th> -->
<th style="width:160px !important;">Reading Date time</th>
<%if(meter_type.contains("SINGLE")){ %>
<th>Voltage</th>
<th>Current</th>
<!-- <th>Neutral current</th> -->
<%}else{ %>
<th>Rph-v</th>
<th>Yph-v</th>
<th>Bph-v</th>

<th>LR</th>
<th>LY</th>
<th>LY</th>

<th>Rph-pf</th>
<th>Yph-pf</th>
<th>Bph-pf</th>

<th>PF (avg)</th>
<th>kw</th>
<!--<th>kva</th>-->
<th>kvar</th>
<th>Freq</th>
<%} %>
<th>KWH</th>
<!-- <th>KVAH</th> -->
<!--<th>KVARH Lag</th>
<th>KVARH Lead</th>-->
<th>MD KW</th>
<th>MD KVA</th>
<!-- <th>MD KW Occ date</th> -->
<!-- <th>MD Kva Occ date</th> -->
<!-- <th>Power on duration</th> -->
<th>tamper count</th>
<th>Billing count</th>
<!-- <th>Programming count</th> -->
<th>Disconnect control</th>
<th>Limiter</th>
<th>Base KWH</th>
<th>Present  KWH</th>
<th>Tariff</th>
<th>Prev.balance</th>
<th>Curr.balance</th>
</tr>
</thead>
<tbody>
<c:forEach items="${details}" var="d">
  
<tr>
<%-- <td>${d.g1 }</td> --%>
<td style="width:160px !important;">${d.METER_TIME_STAMP }</td>
<%if(meter_type.contains("SINGLE")){ %>
<td>${d.one_PH_VOLTAGE }</td>
<td>${d.one_PH_CURRENT }</td>
<%-- <td>${d.NEUTRAL_CURRENT }</td> --%>
<%}else{ %>
<td>${d.RPH_VOLTAGE }</td>
<td>${d.YPH_VOLTAGE }</td>
<td>${d.BPH_VOLTAGE }</td>

<td>${d.RPH_LINE_CURRENT }</td>
<td>${d.YPH_LINE_CURRENT }</td>
<td>${d.BPH_LINE_CURRENT }</td>

<td>${d.RPH_POWER_FACTOR }</td>
<td>${d.YPH_POWER_FACTOR }</td>
<td>${d.BPH_POWER_FACTOR }</td>


<td>${d.AVG_POWER_FACTOR }</td>
<td>${d.ACT_POWER }</td>
<!--<td>${d.REACT_POWER }</td>-->
<td>${d.APPARENT_POWER }</td>
<td>${d.FREQUENCY }</td>
<%} %>
<td>${d.KWH }</td>
<%-- <td>${d.KVAH }</td> --%>
<!--<td>${d.KVARH_LAG }</td>
<td>${d.KVARH_LEAD }</td>-->
<td>${d.MDKW }</td>
<td>${d.MDKVA }</td>
<%-- <td>${d.MDKWOCCDATE }</td> --%>
<%-- <td>${d.MDKVAOCCDATE }</td> --%>
<%-- <td>${d.POWER_ON_DURATION }</td> --%>
<td>${d.TAMPER_COUNT }</td>
<td>${d.BILLING_COUNT }</td>
<%-- <td>${d.PROGRAMMING_COUNT }</td> --%>
<td>${d.disc_con }</td>
<td>${d.limiter }</td>

<td>${d.base_kwh }</td>
<td>${d.consumed_units_kwh }</td>
<td>${d.charge_for_consumed_units }</td>
<td>${d.previous_balance }</td>
<td>${d.current_balance }</td>

</tr>
</c:forEach>
</tbody>
</table>
</c:if>
 </div>           


<c:if test="${empty details}">
No data available...
</c:if>
