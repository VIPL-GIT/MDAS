<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
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

<!-- <div class="panel panel-blue"> -->

<!--                            <div class="panel-heading"> -->
<!--                                      <h4><b>Consumers List</b></h4>     -->
<%--                                      <div class="btn-group pull-right clearfix"> <%if(!actionList.get(0).equals("0")) {%> --%>
<!--                                       <a class="btn btn-default btn-rounded addbtn" title="Add new record" href="javascript:addConsumer()"> -->
<!-- 	                                  <i class="fa fa-plus"></i>Add Consumer</a> -->
<%--                                         <%} %> --%>
<!--                                         </div> -->
<!--                             </div> -->
<div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h5>Consumers List</h5> 
                                
                                    <div class="btn-group pull-right clearfix">
                                     <%if(!actionList.get(0).equals("0")) {%>
                                        <a class="btn btn-circle show-tooltip" title="Add new record" href="javascript:addConsumer()"><i class="fa fa-plus"></i></a>
                                         <%} %>
                                        </div>
                                   </div>                           
                            </div>
<div class="panel-body" id="consumerBody">
<div class="table-responsive" style="border:0">
    <c:if test="${!empty hts}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
  <th>KNO</th>
  <th>DF</th>
<th>SDO</th>
<th>Name</th>
<th>Address</th>
<th>Sanction Load</th>
<th>Contract Demand</th>
<th>Category</th>
<th>Mobile</th>
<th>Supply Voltage</th>
<th>Action</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${hts}" var="f">
   <tr>
   <td>${f.consumer_code }</td>
   <td>${f.parent_name }</td>
<td>${f.feeder_name }</td>
<td>${f.consumer_name }</td>
<td>${f.address }</td>
<td>${f.contract_load }</td>
<td>${f.contract_demand }</td>
<td>${f.category }</td>
<td>${f.mobile }</td>
<td>${f.supply_voltage }</td>
<td>
   <%if(!actionList.get(2).equals("0")) {%>
      <a href="javascript:editConsumer('${f.consumer_id}')" title="edit"><div class="glyphicon glyphicon-edit"></div></a> &nbsp;      
      <% }if(!actionList.get(1).equals("0")) {%>
      <a href="javascript:deleteConsumer('${f.consumer_id}')" title="delete"> <div class="glyphicon glyphicon-trash"></div></a>
        <%} %>  
       </td>
    
   </tr>
  </c:forEach>
  </tbody>
 </table> 
</c:if>

</div>
</div>
</div>
</div>
</div>
