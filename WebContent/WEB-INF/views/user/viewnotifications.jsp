<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>

<div id="main-content">
               <!-- BEGIN Breadcrumb -->
                <div id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">Home</a>
                            <span class="divider"><i class="fa fa-angle-right"></i></span>
                        </li>
                        <li class="active">View Events & Notifications</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->

                <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                            <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Events & Notifications List</h4> 
                            <button  style="float:right" class="btn btn-rounded btn-outline-primary" onclick="javascript:AddUserEventNotification()">Add new record </button>
                            </div>
                              </div>
                            <div class="panel-body">
                                <div class="clearfix"></div>
<div class="table-responsive" style="border:0">
    <c:if test="${!empty userconfig}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
   <th>User Name</th>
   <th>Alert Type</th>
   <th>Alert Mode</th>
   <th>Is Enable</th>
   <!-- <th>User Hierarchy </th> -->
   <th>Action</th>
   
  </tr>
</thead>
<tbody>
  <c:forEach items="${userconfig}" var="userconfig">
   <tr>    
    <td><c:out value="${userconfig.user_name}"/></td>
    <td><c:out value="${userconfig.type_of_alert}"/></td>
    <td><c:out value="${userconfig.mode_of_alert}"/></td>
      <c:choose>
  <c:when test="${userconfig.is_enabled==1}">
    <td>False</td>
  </c:when>
  <c:otherwise>
   <td>True</td>
  </c:otherwise>
</c:choose>
 
 
 <%-- 
   <td><c:out value="${userconfig.is_enabled}"/></td>
   <td><c:out value="${userconfig.user_hierarchy_id}"/></td> 

 <input type="text" id="mobile" value="${userconfig.user_alert_id}">
 <input type="text" id="email" value="${userconfig.user_alert_id}">
  --%>
    <td>
       <a href="javascript:editEventNotification('${userconfig.user_name}','${userconfig.user_alert_id}','${userconfig.type_of_alert}','${userconfig.mode_of_alert}','${userconfig.is_enabled}')" title="edit"><i class="ti ti-pencil"></i></a> &nbsp;      
       <a href="javascript:deleteEventNotification('${userconfig.user_alert_id}')" title="delete"> <i class="ti ti-trash"></i></a>
       </td>
   </tr>
    <%-- <input type="text"  id="username" name="username"  placeholder="User Name" class="form-control" value="${userconfig.user_alert_id}"> --%>
  </c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty userconfig}">
<center><h3>No Data Available</h3></center>
</c:if>
</div>
                            </div>
                    </div>
                </div>