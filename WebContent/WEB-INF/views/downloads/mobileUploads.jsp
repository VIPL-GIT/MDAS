<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*" %>
    <script src="assets/jquery-validation/jquery.validate.min.js"></script>
    <script src="assets/jquery-validation/additional-methods.min.js"></script>
  <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                      
                            <div class="panel-heading">
                                <h4>Data Uploads (Mobile App)</h4>    </div>
                            
                 <div class="panel-body">
<div class="clearfix"></div>
<div class="table-responsive" style="border:0">
    
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
  <th>Meter Sl.No</th>
  <th>Month</th>
  <th>File Name</th>
  <th>File Size (bytes)</th>  
  </tr>
</thead>
<tbody>
  <c:forEach items="${list}" var="l">
   <tr>
    <td>${l.meter_serial_no }</td>
    <td>${l.month }</td>
    <td>${l.originalname }</td>   
    <td>${l.filesize }</td>
    
   </tr>
  </c:forEach>
  </tbody>
 </table> 

</div>
                            </div>      
                            
                            
                        </div>
                    </div>
                </div>