<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
    <script src="assets/jquery-validation/jquery.validate.min.js"></script>
    <script src="assets/jquery-validation/additional-methods.min.js"></script>
<Style>

.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
   background-color: #fefefe;
/*   margin: auto; */
/*    padding: 150px;  */
/*  margin-left:200px; */
 margin-top:25px;
  border: 2px solid #888;
  width: 90%;
}

/* The Close Button */
.close , .biclose {
  color: #ec0a0a;
  float: right;
  font-size: 28px;
  font-weight: bold;
  opacity:.5;
}

.close:hover, .biclose:hover,
.close:focus ,.biclose:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

</Style>

<%
List actionList=new ArrayList();
try{
String actionId=request.getAttribute("actionId").toString();
Map<String,List> actionMap=(Map<String,List>)session.getAttribute("mappedActions");
actionList=actionMap.get(actionId);

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
                        <li class="active">Meter Master</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->

                <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                      
                            <div class="panel-heading">
                                <h4>Meters List</h4>    
                                    
                                     <div class="btn-group pull-right clearfix"> <%if(!actionList.get(0).equals("0")) {%>
                                        <a class="btn btn-default btn-rounded addbtn" title="Add new record" href="javascript:addMeter()">
                                        <i class="fa fa-plus"></i>Add Meter</a>
                                         <%} %>
                                        </div>
                                                              
                            </div>
                            
                                                   <div class="panel-body">

                                <div class="clearfix"></div>
<div class="table-responsive" style="border:0">
    <c:if test="${!empty meters}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
   <th>Location </th>
   <th>Meter Sl No</th>   
   <th>Modem Sl No</th>
   
   <th>Meter CT</th>
   <th>Meter PT</th>
   <th>Adopted CT</th>
   <th>Adopted PT</th>  
   <th>MF</th> 
   <th>Installation Date</th>
   <th>Installation point</th>
   <th>Meter Type</th>
   <th>Meter Make</th>
   
   
   <th>Action</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${meters}" var="meter">
   <tr>
    <td>${meter.location_name}</td>
    <td>${meter.meter_sl_no}</td>
    
    <td>${meter.modem_sl_no}</td>
   
    <td>${meter.me_ct}</td>
    <td>${meter.me_pt}</td>
    <td>${meter.ad_ct}</td>
    <td>${meter.ad_pt}</td>
    <td>${meter.mf}</td>
    <td>${meter.installation_date}</td>
    <td>${meter.install_point}</td>
    <td>${meter.meter_type}</td>
    <td>${meter.metermake}</td>
    
    <td>
    <%if(!actionList.get(2).equals("0")) {%>
    <a href="javascript:editMeter('${meter.meter_id}')" title="edit"><div class="glyphicon glyphicon-edit"></div></a> &nbsp;
    <a href="javascript:changeMeter('${meter.meter_sl_no}')" title="meter change"><div class="fa fa-exchange"></div></a> &nbsp;      
        <% }if(!actionList.get(1).equals("0")) {%>
       <a href="javascript:deleteMeter('${meter.meter_id}')" title="delete"> <div class="glyphicon glyphicon-trash"></div></a>
         <%} %>
       </td>
   </tr>
  </c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty meters}">
<center><h3>No Data Available</h3></center>
</c:if>
</div>
                            </div>      
                            
                            
                        </div>
                    </div>
                </div>
<div id="bi_modal" class="modal">                  