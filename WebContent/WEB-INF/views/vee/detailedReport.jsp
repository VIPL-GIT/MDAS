<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<style>
.table > thead > tr > th{
      white-space: nowrap;
}
</style>

<Style>

.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
   background-color: #fefefe;
/*   margin: auto; */
/*    padding: 150px;  */
/*  margin-left:200px; */
 margin-top:25px;
  border: 2px solid #888;
  width: 90%;
}

/* The Close Button */
.close , .biclose {
  color: #ec0a0a;
  float: right;
  font-size: 28px;
  font-weight: bold;
  opacity:.5;
}

.close:hover, .biclose:hover,
.close:focus ,.biclose:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

</Style>

<div class="table-responsive" style="border:0">
 <c:if test="${!empty summary}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
  <th>Date</th>
  <th>RTC Check</th>
	<th>Spike Check</th>
	<th>KVARh</th>
	<th>Sum Check</th>
	<th>High/Low Check</th>
	<th>Zero Check</th>
	<th>Negative Usage Check</th>
<!-- 	<th>Consecutive Interval Check</th> -->
	<th>TOD Summation Check</th>
<!-- 	<th>Inactive Measurement Check</th> -->
	<th>Time Tolerance Check</th>
	  
  </tr>
  </thead>
  <tbody>
   <c:forEach items="${summary}" var="s">
   <tr>
   <td>${s.comm_date }</td>
   <td>${s.rtc_check=='1'?"Fail":(s.rtc_check=='0'?"Pass":"") }</td>
   <td>${s.spike_check=='1'?"Fail":(s.spike_check=='0'?"Pass":"") }</td>
   <td>${s.kvarh_check=='1'?"Fail":(s.kvarh_check=='0'?"Pass":"") }</td>
   <td>${s.sum_check=='1'?"Fail":(s.sum_check=='0'?"Pass":"") }</td>
   <td>${s.high_low_check=='1'?"Fail":(s.high_low_check=='0'?"Pass":"") }</td>
   <td>${s.zero_check=='1'?"Fail":(s.zero_check=='0'?"Pass":"") }</td>
   <td>${s.negative_usage_check=='1'?"Fail":(s.negative_usage_check=='0'?"Pass":"") }</td>
<%--    <td><a href="javascript:getDetailedChecks('consecutive','${s.comm_date }')" >${s.consecutive_interval_check }</a></td> --%>
   <td>${s.tod_summation_check=='1'?"Fail":(s.tod_summation_check=='0'?"Pass":"")}</td>
<%--    <td><a href="javascript:getDetailedChecks('inactive','${s.comm_date }')" >${s.inactive_measurement_check }</a></td> --%>
   <td>${s.time_tolerance_check=='1'?"Fail":(s.time_tolerance_check=='0'?"Pass":"") }</td>
   </tr>
   </c:forEach>
  </tbody>
  </table>
  </c:if>
  <c:if test="${empty summary}">
   <h4 style="text-align:center;">Data Not Available...</h4>
  </c:if>
</div>

<div id="bi_modal" class="modal"></div>