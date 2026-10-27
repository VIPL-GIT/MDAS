<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.rtdas.common.beans.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<style>

</style>
<div class="row">
                        <div class="col-md-12">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs">
                                <li class="active" onclick="getAlertsHome()"><a href="#tab1" data-toggle="tab" style="cursor:pointer;">Latest</a></li>
                                <li><a href="#tab2" data-toggle="tab" style="cursor:pointer;">History</a></li>
                            </ul>
                            <!-- Tab panels -->
                            <div class="tab-content">
                                <div class="tab-pane fade in active" id="tab1">
                                    <div class="panel-body">
<!-- ----------------------------------------------------------------------------------------------------- -->
<div class="table-responsive">    
<table id = "table4" class="table table-striped table-hover " style="margin-bottom:0px;" role="grid">
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

<!-- ----------------------------------------------------------------------------------------------------- -->                                        
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="tab2">
                                    <div class="panel-body">
<!-- ------------------------------------------------------------------------------------------------------ -->
					<table cellpadding="5" cellspacing="5">
					<tr><td>
					<td ><div class="form-group row">
                                        <label for="example-text-input" class="col-sm-3 col-form-label">Select</label>
                                        <div class="col-sm-9">
                                            <select id="traid" class="form-control">
                                            <option value="0">select</option>
                                            <c:if test="${!empty tralist}">
                                            <c:forEach items="${tralist}" var="trak">
                                            <option value="${trak }">${trak eq "SMT02"?"Epsx_Pvh_Row1":trak }</option>
                                            </c:forEach>
                                            </c:if>
                                            </select>
                                        </div>
                                    </div></td>
					<td ><div class="form-group row">
                                        <label for="example-text-input" class="col-sm-3 col-form-label">From</label>
                                        <div class="col-sm-9">
                                            <input class="form-control" type="text"  id="alertsfrom">
                                        </div>
                                    </div></td>
                                    <td ><div class="form-group row">
                                        <label for="example-text-input" class="col-sm-3 col-form-label">to</label>
                                        <div class="col-sm-9">
                                            <input class="form-control" type="text"  id="alertsto">
                                        </div>
                                    </div></td>
					
					<td >
					 <div class="form-group row" style="margin-left:5px;">
					  <input type="button" class="btn-sm btn btn-base" value="Get Data" onclick="getAlertsHistory()"/>
					  
					  </div>
					</td>
					</tr>
					</table>
					
					 <div id="tabler"></div>
<!-- ------------------------------------------------------------------------------------------------------ -->   
                                    </div>
                                </div>
                               
                            </div>

                           

</div>
</div>