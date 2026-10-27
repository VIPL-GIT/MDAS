<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
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
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">Home</a>
                            <span class="divider"><i class="fa fa-angle-right"></i></span>
                        </li>
                        <li class="active">Device Master</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->

                <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Device List</h4> 
                                
                                    <div class="btn-group pull-right clearfix">
                                     <%if(!actionList.get(0).equals("0")) {%>
                                        <a class="btn btn-circle show-tooltip" title="Add new record" href="javascript:addDevice()"><i class="fa fa-plus"></i></a>
                                         <%} %>
                                        </div>
                                   </div>                           
                            </div>
                            
                            <div class="panel-body">
                                <div class="clearfix"></div>
<div class="table-responsive" style="border:0">
    <c:if test="${!empty device}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
   <th>Device</th>
   <th>Device Type </th>
   <th>Device Make </th>
   <th>Max.Params </th>
   <th>Supported Baud rates </th>
   <th>Active Status</th>
   <th>Action</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${device}" var="device">
   <tr> 
    <td><c:out value="${device.deviceMakeName} - ${device.deviceTypeName}"/></td>   
    <td><c:out value="${device.deviceTypeName}"/></td>
    <td><c:out value="${device.deviceMakeName}"/></td>
    <td><c:out value="${device.supportedParamsCount}"/></td>
    <td><c:out value="${device.supportedBaudrate}"/></td>    
    <td><c:out value='${device.activeFlg==0?"Inactive":"Active"}'/></td>  
    <td>
    <%if(!actionList.get(2).equals("0")) {%>
    <a href="javascript:editDevice('${device.deviceId}')" title="edit"><i class="ti ti-pencil"></i></a> &nbsp;      
        <% }if(!actionList.get(1).equals("0")) {%>
       <a href="javascript:deleteDevice('${device.deviceId}')" title="delete"> <i class="ti ti-trash"></i></a>
         <%} %>
       </td>
   </tr>
  </c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty device}">
<center><h3>No Data Available</h3></center>
</c:if>
</div>
                            </div>
                        </div>
                    </div>
                </div>