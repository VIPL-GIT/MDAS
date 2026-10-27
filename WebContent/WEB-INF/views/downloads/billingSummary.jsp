<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
    <script src="assets/jquery-validation/jquery.validate.min.js"></script>
    <script src="assets/jquery-validation/additional-methods.min.js"></script>
  <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                      
                            <div class="panel-heading">
                                <h4>All Devices Summary</h4>    </div>
                            
                 <div class="panel-body">
<div class="clearfix"></div>
<div class="table-responsive" style="border:0">
    <c:if test="${!empty list}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
   <th>Company</th>
   <th>SDO</th>   
   <th>Consumer Name</th>
   <th>KNO</th>
   <th>Meter</th> 
   <th>Meter Make</th>
   <th>Inst.data</th>
   <th>Energy data</th>
   <th>LoadSurvey</th>
   <th>Events.data</th>
   <th>Snapshot</th>
   <th>Recent Auto reset </th>
   <th>Present Month billing</th>
   <th>CMD availability</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${list}" var="l">
   <tr>
    <td>${l.master.company }</td>
    <td>${l.master.sd }</td>    
    <td>${l.master.location_name }</td>
    <td>${l.master.location_code }</td>
    <td>${l.meter_serial_no}</td>
    <td>${l.metermake}</td>
    <td>${l.d2}</td>
    <td>${l.d3}</td>
    <td>${l.d4}</td>
    <td>${l.d5}</td>
    <td>${l.d6}</td>
    <td>${l.recentAutoReset}</td>    
   <td>${l.pres_month_billing}</td> 
   <td>${l.cmd_availability}</td> 
   </tr>
  </c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty list}">
<center><h3>No Data Available</h3></center>
</c:if>
</div>
                            </div>      
                            
                            
                        </div>
                    </div>
                </div>