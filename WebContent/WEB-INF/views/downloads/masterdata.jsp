<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*" %>
    <script src="assets/jquery-validation/jquery.validate.min.js"></script>
    <script src="assets/jquery-validation/additional-methods.min.js"></script>
    <Style>

.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 70%; /* Full width */
  height: 70%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
   background-color: #fefefe;
/*   margin: auto; */
/*    padding: 150px;  */
/*   margin-left:200px;  */
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
  <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                      
                            <div class="panel-heading">
                                <h4>Master Data</h4>    </div>
                            
                 <div class="panel-body">
<div class="clearfix"></div>
<div class="table-responsive" style="border:0">
    <c:if test="${!empty master}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
   <th>Region</th>
   <th>Circle</th>
   <th>Division</th>
   <th>Zone</th>
   <th>Sub station</th>
   <th>PTR</th>   
   <th>Feeder </th>
   <th>Code</th>  
   <th>Category</th>
   <th>Type</th>
   <th>Group</th>
   <th>MF</th>
<!--    <th>Sanction Load</th> -->
<!--    <th>Contract Demand</th> -->
   <th>Supply Voltage</th> 
   <th>Meter</th>  
   <th>Meter Type</th>
   <th>Meter Make</th>
   <th>Modem</th>
   <th>Issue</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${master}" var="l">
   <tr>
    <td>${fn:toUpperCase(l.region)}</td>
    <td>${fn:toUpperCase(l.circle)}</td> 
    <td>${fn:toUpperCase(l.division)}</td> 
    <td>${fn:toUpperCase(l.sd)}</td> 
    <td>${fn:toUpperCase(l.ss)}</td> 
    <td>${fn:toUpperCase(l.ptr)}</td>    
    <td>${fn:toUpperCase(l.location_name)}</td>
    <td>${l.location_code}</td>
    <td>${l.location_category}</td>
    <td>${l.category}</td>
    <td>${l.location_group}</td>
    <td>${l.mf}</td>
<%--     <td>${l.sanction_load}</td> --%>
<%--     <td>${l.contract_demand}</td> --%>
    <td>${l.supply_voltage}</td>
    <td><a href="javascript:updateDiscomIssue('${fn:toUpperCase(l.meter_sl_no)}','${issues[l.meter_sl_no] }')">
         ${fn:toUpperCase(l.meter_sl_no)}</a></td>
    <td>${fn:toUpperCase(l.meter_type)}</td>
    <td>${fn:toUpperCase(l.meter_make)}</td>
    <td>${fn:toUpperCase(l.modem_sl_no)}</td>
    <td>${issues[l.meter_sl_no] }</td>   
   
   </tr>
  </c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty master}">
<center><h3>No Data Available</h3></center>
</c:if>
</div>
                            </div>      
                            
                            
                        </div>
                    </div>
                </div>
<div id="bi_modal" class="modal" style="margin-top:75px;margin-left:200px;"></div>                