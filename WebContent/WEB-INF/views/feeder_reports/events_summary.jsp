<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

<div class="col-md-12">
<h4 class="rTitle col-md-10">Events Summary</h4>  
<!-- <button class="btn btn-sm col-md-1" onclick="exportDivtoExcel('Hourly_load')">Excel</button> -->
<!-- <button class="btn btn-sm col-md-1" onclick="printDiv()">Print</button> -->
</div>
<div class="col-md-4">
 <table class="table table-advance" cellspacing="0" width="100%" id="eventsSummary">
 <thead>
 <tr>
 <td>Event Description</td>
 <td>No.of Occurrences</td>
 </tr> 
 </thead>
 <tbody>
 <c:forEach items="${map}" var="m">
 <tr>
 <td>${fn:toUpperCase(m.key) }</td>
 <td><a href="javascript:getEventsSummaryDetails('${fn:toUpperCase(m.key) }')">${m.value }</a></td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>
 <div class="col-md-8" id="events_graph" style="height:420px;margin-top:10px;border: 1px solid;border-radius: 33px;">
 </div>
 </div>
 
 <div id="bi_modal" class="modal">  </div>
 
 