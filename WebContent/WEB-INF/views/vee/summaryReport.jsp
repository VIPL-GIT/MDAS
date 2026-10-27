<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>

<script src="js/admin.js"></script>

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




<div class="table-responsive" style="border:0;float:left;width: 50%;">
 <c:if test="${!empty bean}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
  <th>Check</th>
  <th>Success</th>
  <th>Failed</th>
  <th>Total</th>
  </tr>
  
  </thead>
  <tbody>
    <tr><td>RTC Check</td><td>${bean.rtc_check }</td> 
    <td><a href="javascript:getSummaryChecks('rtc','${bean.comm_date }')" >${bean.rtc_check_failed }</a></td>
    <td>${bean.total_meters }</td></tr>
    
	<tr><td>Spike Check</td><td>${bean.spike_check }</td> 
	<td><a href="javascript:getSummaryChecks('spike','${bean.comm_date }')" >${bean.spike_check_failed }</a></td>
	<td>${bean.total_meters }</td></tr>
	
	<tr><td>KVARh</td><td>${bean.kvarh_check}</td> 
	<td><a href="javascript:getSummaryChecks('kvarh','${bean.comm_date }')" >${bean.kvarh_check_failed }</a></td>
	<td>${bean.total_meters }</td></tr>
	
	<tr><td>Sum Check</td><td>${bean.sum_check }</td> 
	<td><a href="javascript:getSummaryChecks('sum','${bean.comm_date }')" >${bean.sum_check_failed }</a></td>
	<td>${bean.total_meters }</td></tr>
	
	<tr><td>High/Low Check</td><td>${bean.high_low_check }</td> 
	<td><a href="javascript:getSummaryChecks('highlow','${bean.comm_date }')" >${bean.high_low_check_failed }</a></td>
	<td>${bean.total_meters }</td></tr>
	
	<tr><td>Zero Check</td><td>${bean.zero_check }</td>
	<td><a href="javascript:getSummaryChecks('zero','${bean.comm_date }')" >${bean.zero_check_failed }</a></td>
	 <td>${bean.total_meters }</td></tr>
	 
	<tr><td>Negative Usage Check</td><td>${bean.negative_usage_check }</td> 
	<td><a href="javascript:getSummaryChecks('negative','${bean.comm_date }')" >${bean.negative_usage_check_failed }</a></td>
	<td>${bean.total_meters }</td></tr>
	
	<tr><td>TOD Summation Check</td><td>${bean.tod_summation_check }</td>
	<td><a href="javascript:getSummaryChecks('tod','${bean.comm_date }')" >${bean.tod_summation_check_failed }</a></td>
	 <td>${bean.total_meters }</td></tr>
	 
	<tr><td>Time Tolerance Check</td><td>${bean.time_tolerance_check }</td> 
	<td><a href="javascript:getSummaryChecks('time','${bean.comm_date }')" >${bean.time_tolerance_check_failed }</a></td>
	<td>${bean.total_meters }</td></tr>
	
  </tbody>
  </table>
  </c:if>
  <c:if test="${empty bean}">
   <h4 style="text-align:center;">Data Not Available...</h4>
  </c:if>
</div>

<div id="bi_modal" class="modal"></div>
<div id='veesummary_graph' style="float: left; width: 49%;height:345px;border:1px solid #CCC;border-radius:10px;margin-left:5px;"></div>
 