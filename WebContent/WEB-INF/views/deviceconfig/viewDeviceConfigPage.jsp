<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<%
List actionList=new ArrayList();
try{
String actionId=request.getAttribute("actionId").toString();
Map<String,List> actionMap=(Map<String,List>)session.getAttribute("mappedActions");
actionList=actionMap.get(actionId);
//System.out.println("------------------------>"+actionList);
}catch(Exception e){
	e.printStackTrace();
}
%>

<div id="main-content">
	<!-- BEGIN Breadcrumb -->
	<div id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="fa fa-home"></i> <a href="#">Home</a> <span
				class="divider"><i class="fa fa-angle-right"></i></span></li>
			<li class="active">Device Config Master</li>
		</ul>
	</div>
	<!-- END Breadcrumb -->

	<!-- BEGIN Main Content -->
	<div class="row">
		<div class="col-md-12">
			<c:if test="${!empty device}">
<!-- 			<fieldset class="scheduler-border"> -->
<!-- 						<legend class="scheduler-border">Devices</legend>						 -->
<!-- 							<div class="col-md-6"> -->
<!-- 							<label for="textfield2" class="control-label">Select Device :</label> -->
<!-- 								<select  name="device" id="device" tabindex="1" onchange="getDeviceMap(this.value)"> -->
<!-- 								<option value="0"></option> -->
<%-- 								<c:forEach items="${device}" var="device"> --%>
<%-- 								  <option value="${device.deviceId }">${device.deviceMakeName} - ${device.deviceTypeName}</option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
<!-- 							</div> -->
						
<!-- 					</fieldset> -->
				<div class="panel panel-bd">
                        <div class="panel-heading">
					<div class="panel-title">
						
                          <div class="col-md-6">
							<label for="textfield2" class="control-label">Select Device :</label>
								<select  name="device" id="device" tabindex="1" onchange="getDeviceMap(this.value)">
								<option value="0"></option>
								<c:forEach items="${device}" var="device">
								  <option value="${device.deviceId }">${device.deviceMakeName} - ${device.deviceTypeName}</option>
								</c:forEach>
							</select>
							</div>
						<div class="btn-group pull-right clearfix">
							<%if(!actionList.get(0).equals("0")) {%>
<button id="updateButton" type="button" class="btn btn-sm btn-primary" onclick="updateAllDeviceConfig()" style="display:none;margin-right:25px;">
<i class="fa fa-check"></i> Update All</button>
								<a class="btn btn-circle show-tooltip" title="Delete All"
								href="javascript:deleteAllDeviceMap()"><i class="zmdi zmdi-delete"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<%} %>
						</div>
</div>
					</div>

					<div class="panel-body">
						<div class="clearfix"></div>
						<div class="table-responsive" style="border: 0" id="deviceConfig">
						
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${empty device}">
				<center>
					<h3>No Data Available</h3>
				</center>
			</c:if>
		</div>
	</div>