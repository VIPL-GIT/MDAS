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
                        <li class="active">Panels</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->

                <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Panels List</h4> 
                                
                                    <div class="btn-group pull-right clearfix">
                                     <%if(!actionList.get(0).equals("0")) {%>
                                        <a class="btn btn-circle show-tooltip" title="Add new record" href="javascript:addLogger()"><i class="fa fa-plus"></i></a>
                                        <%} %>
                                        </div>
                                  </div>                            
                            </div>
                            
                            <div class="panel-body">

                                <div class="clearfix"></div>
<div class="table-responsive" style="border:0">
    <c:if test="${!empty loggers}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
  
   <th>Panel Sl No</th>
   <th>Location</th>  
   <th>Hardware Version</th>
   <th>Firmware Version</th>
   <th>Mobile</th>
   <th>Installation Date</th>   
<!--    <th>No.of Lights</th> -->
<!--    <th>Amp per Light</th> -->
   <th>Action</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${loggers}" var="log">
   <tr>
   
    <td><c:out value="${log.logger_sl_no}"/></td>
    <td><c:out value="${log.location_name}"/></td>   
    <td><c:out value="${log.hw_version}"/></td>
    <td><c:out value="${log.fw_version}"/></td>
    <td><c:out value="${log.mobile}"/></td>
    <td><c:out value="${log.delivery_date}"/></td>   
<%--     <td><c:out value="${log.no_of_lights}"/></td> --%>
<%--     <td><c:out value="${log.amp_per_light}"/></td> --%>
    
    <td>
    <%if(!actionList.get(2).equals("0")) {%>
       <a href="javascript:editLogger('${log.logger_id}')" title="edit"><i class="ti ti-pencil"></i></a> &nbsp;      
       <% }if(!actionList.get(1).equals("0")) {%>
       <a href="javascript:deleteLogger('${log.logger_id}')" title="delete"> <i class="ti ti-trash"></i></a>
          <%} %>
       </td>
   </tr>
  </c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty loggers}">
<center><h3>No Data Available</h3></center>
</c:if>
</div>
                            </div>
                        </div>
                    </div>
                </div>