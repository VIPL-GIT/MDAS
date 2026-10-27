<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
    <script src="assets/jquery-validation/jquery.validate.min.js"></script>
    <script src="assets/jquery-validation/additional-methods.min.js"></script>


              

                <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                      
                            <div class="panel-heading">
                                <h4>Billing Meters List</h4>    </div>
                            
                 <div class="panel-body">
<div class="clearfix"></div>
<div class="table-responsive" style="border:0">
    <c:if test="${!empty list}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
   <th>Office Code</th>
   <th>Token No</th>   
   <th>BCCD</th>   
   <th>KNO</th>
   <th>Name</th>
   <th>Address</th>
   <th>Meter</th>  
   <th>KWH IMP</th>
   <th>KVAH IMP</th>
   <th>KVA IMP</th>  
   <th>Last Comm. Date</th>
   <th>Status</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${list}" var="l">
   <tr>
    <td>${l.officeid}</td>
    <td>${l.tokenno}</td>    
    <td>${l.binderid}</td>   
    <td>${l.kno}</td>
    <td>${l.consumer_name}</td>
    <td>${l.consumer_address}</td>
    <td>${l.meter_serial_no}</td>
    <td>${l.kwh_i}</td>
    <td>${l.kvah_i}</td>
    <td>${l.mdkva_i}</td>
    <td>${l.last_comm}</td>
    <td >${l.istatus}</td>
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