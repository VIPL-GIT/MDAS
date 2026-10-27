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
                        <li class="active">Client Master</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->

                <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="box">
                            <div class="box-title">
                                <h3><i class="fa fa-table"></i>Clients List</h3> 
                                
                                    <div class="btn-group pull-right clearfix">
                                    <%if(!actionList.get(0).equals("0")) {%>
                                        <a class="btn btn-circle show-tooltip" title="Add new record" href="javascript:addClient()"><i class="fa fa-plus"></i></a>
                                        <%} %>
                                        </div>
                                                              
                            </div>
                            
                            <div class="box-content">
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
    <c:if test="${!empty clients}">
 <table class="table table-advance " cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
<!--    <th>Client ID</th> -->
   <th>Client Name</th>
<!--    <th>Client Logo</th> -->
   <th>Contact Person</th>
   <th>Address</th>
   <th>E-mail</th>
   <th>Mobile</th>
   <th>Status</th>
   <th>Action</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${clients}" var="client">
   <tr>
<%--     <td><c:out value="${client.clientId}"/></td> --%>
    <td><c:out value="${client.clientName}"/></td>
<%--     <td><c:out value="${client.clientName}"/></td> --%>
    <td><c:out value="${client.contactPerson}"/></td>
    <td><c:out value="${client.add1}"/></td>
    <td><c:out value="${client.email}"/></td>
    <td><c:out value="${client.mobile}"/></td>
    <td><c:out value='${client.activeStatus==0?"Inactive":"Active"}'/></td>
    <td>
    <%if(!actionList.get(2).equals("0")) {%>
       <a href="javascript:editClient('${client.clientId}')" title="edit"><i class="ti ti-pencil"></i></a> &nbsp;      
       <% }if(!actionList.get(1).equals("0")) {%>
       <a href="javascript:deleteClient('${client.clientId}')" title="delete"><i class="ti ti-trash"></i></a>
      <%} %>
       </td>
   </tr>
  </c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty clients}">
<center><h3>No Data Available</h3></center>
</c:if>
</div>
                            </div>
                        </div>
                    </div>
                </div>