<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="table-responsive">    
<table id = "table5" class="table table-striped table-hover " style="margin-bottom:0px;" role="grid">
										<thead>
											<tr role="row" class="st-heading">
											  <th>Tracker ID</th>
											  <th>Alert type</th>
											  <th>Alert desc</th>
											  <th>Alert Datetime</th>										   
											   
											</tr>
										</thead>
										<tbody>
<c:if test="${!empty alerts}">
 <c:forEach items="${alerts}" var="al">
   <tr>    
    <td>${al.traid eq "SMT02"?"Epsx_Pvh_Row1":al.traid }</td>
    <td>${al.alert_type }</td>
     <td>${al.alert_desc.replaceAll("SMT02","Epsx_Pvh_Row1") }</td>
    <td>${al.alert_datetime }</td> 
    
    </tr>
  
 </c:forEach>
</c:if>

</tbody>
</table>
                   </div>