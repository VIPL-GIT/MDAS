<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>

<link href="assets/plugins/datatables/dataTables.min.css" rel=stylesheet type="text/css" />
<script src="assets/plugins/datatables/dataTables.min.js"></script>
<%
List actionList=new ArrayList();
String clientId="";
try{
String actionId=request.getAttribute("actionId").toString();
clientId=session.getAttribute("clientId").toString();
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
                        <li class="active">User Master</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->
                <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Users List</h4> 
                                
                                    <div class="btn-group pull-right clearfix">
                                     <%if(!actionList.get(0).equals("0")) {%>
                                        <a class="btn btn-circle show-tooltip" title="Add new record" href="javascript:addUser()"><i class="fa fa-plus"></i></a>
                                        <%} %>
                                        </div>
                                    </div>                          
                            </div>
                            
                            <div class="panel-body">
<!--                                 <div class="btn-toolbar pull-right clearfix"> -->
<!--                                     <div class="btn-group"> -->
<!--                                         <a class="btn btn-circle show-tooltip" title="Add new record" href="#"><i class="fa fa-plus"></i></a> -->
<!--                                         <a class="btn btn-circle show-tooltip" title="Edit selected" href="#"><i class="fa fa-edit"></i></a> -->
<!--                                         <a class="btn btn-circle show-tooltip" title="Delete selected" href="#"><i class="fa fa-trash-o"></i></a> -->
<!--                                     </div> -->
<!--                                     <div class="btn-group"> -->
<!--                                         <a class="btn btn-circle show-tooltip" title="Print" href="#"><i class="fa fa-print"></i></a> -->
<!--                                         <a class="btn btn-circle show-tooltip" title="Export to PDF" href="#"><i class="fa fa-file-text-o"></i></a> -->
<!--                                         <a class="btn btn-circle show-tooltip" title="Export to Exel" href="#"><i class="fa fa-table"></i></a> -->
<!--                                     </div> -->
<!--                                     <div class="btn-group"> -->
<!--                                         <a class="btn btn-circle show-tooltip" title="Refresh" href="#"><i class="fa fa-repeat"></i></a> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                                 <br/><br/> -->
                                <div class="clearfix"></div>
<div class="table-responsive" style="border:0">
    <c:if test="${!empty users}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>

   <th>User Id</th>
   <th>User Full Name</th>
   <%if(clientId.equals("0")){ %>
   <th>Client</th>
   <%} %>
   <th>User Role</th>
   <th>Address</th>
   <th>E-mail</th>
   <th>Mobile</th>
   <th>Status</th>
   <th>Action</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${users}" var="user">
   <tr>

    <td><c:out value="${user.userId}"/></td>
    <td><c:out value="${user.userFullName}"/></td>
    <%if(clientId.equals("0")){ %>
    <td><c:out value="${user.client_name}"/></td>
      <%} %>
    <td><c:out value="${user.roll_name}"/></td>
    <td><c:out value="${user.address}"/></td>
    <td><c:out value="${user.email}"/></td>
    <td><c:out value="${user.mobileNumber}"/></td>
    <td><c:out value='${user.activeFlg==0?"Inactive":"Active"}'/></td>
    
    
    <td>
    <%if(!actionList.get(2).equals("0")) {%>
      <a href="javascript:editUser('${user.slno}')" title="edit"><i class="ti ti-pencil"></i></a> &nbsp;      
       <% }if(!actionList.get(1).equals("0")) {%>
      <a href="javascript:deleteUser('${user.slno}')" title="delete"> <i class="ti ti-trash"></i></a>
         <%} %>
       </td>
   </tr>
  </c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty users}">
<center><h3>No Data Available</h3></center>
</c:if>
</div>
                            </div>
                        </div>
                    </div>
                </div>