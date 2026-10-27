<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>

<style>
.table>tbody>tr>td{
white-space: nowrap;
}
</style>
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
   <th>Region</th>
   <th>Circle</th>
   <th>Division</th>
   <th>Substation</th>
   <th>Feeder</th>
   <th>Category</th>
   <th>Type</th>
   <th>Meter Sl No</th>
   <th>Prog. Meter Sl No</th>
   <th>Modem</th>
   <th>Mapping Status</th>
   <th>Record Status</th>
   <th>Comm. Status</th>
   <th>IMEI</th>
   <th>SIM No</th>
   <th>IP address</th>
   <th>FW.version</th>
   <th>Signal Strength</th>
   <th>Last Comm. Date</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${list}" var="l">
   <tr>
    <td>${l.discom_name }</td>
    <td>${l.circlename }</td>
    <td>${l.divisionname }</td>
    <td>${l.substationname }</td>
    <td>${l.locationname }</td>
    <td>${l.installationpoint }</td>
    <td>${category[l.meter_serial_no] }</td>
    <td>${l.meter_serial_no }</td>
    <td>${l.prog_meter_serial_no }</td>
    <td>${l.modem_serial_no }</td>
    <td>${l.mapping }</td>
    <td>${l.recordStatus }</td>
    <td>${l.status }</td>
    <td>${l.imeino }</td>
    <td>${l.simno }</td>
    <td>${l.ipaddress }</td>
    <td>${l.fv }</td>
    <td>${l.signal_strength }</td>
    <td>${l.log_timestamp eq "NULL"?"":l.log_timestamp }</td>
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