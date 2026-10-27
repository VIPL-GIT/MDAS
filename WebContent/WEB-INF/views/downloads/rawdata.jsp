<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
    <script src="assets/jquery-validation/jquery.validate.min.js"></script>
    <script src="assets/jquery-validation/additional-methods.min.js"></script>
  <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                      
                            <div class="panel-heading">
                                <h4>Data Availability</h4>    </div>
                            
                 <div class="panel-body">
<div class="clearfix"></div>
<div class="table-responsive" style="border:0">
    <c:if test="${!empty list}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
   <th>Region</th>
   <th>Circle</th>
   <th>Division</th>
   <th>Substation</th>
   <th>Feeder Name</th>

   <th>Meter</th>  
   <th>Is Available</th>
  
  </tr>
</thead>
<tbody>
  <c:forEach items="${list}" var="l">
   <tr>
    <td>${l.tmb.region}</td>
    <td>${l.tmb.circle}</td>
    <td>${l.tmb.division}</td>    
    <td>${l.tmb.ss}</td>
    <td>${l.tmb.location_name}</td>

    <td><a href="javascript:showZipFiles('${l.path eq ''?'':l.tmb.meter_sl_no}')" >${l.tmb.meter_sl_no}</a></td>
    <td>${l.isAvailable}</td>   
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