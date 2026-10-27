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
    
<!--      <div class="panel-heading"> -->
<!--                                 <h4><b>Feeders List</b></h4>     -->
<%--                                      <div class="btn-group pull-right clearfix"> <%if(!actionList.get(0).equals("0")) {%> --%>
<!--                                        <a class="btn btn-default btn-rounded addbtn" title="Add new record" href="javascript:addFeeder()"> -->
<!-- 	                               <i class="fa fa-plus"></i>Add Feeder</a> -->
<%--                                          <%} %> --%>
<!--                                         </div> -->
<!--                             </div> -->
    
 <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h5>Feeders List</h5> 
                                
                                    <div class="btn-group pull-right clearfix">
                                     <%if(!actionList.get(0).equals("0")) {%>
                                        <a class="btn btn-circle show-tooltip" title="Add new record" href="javascript:addFeeder()"><i class="fa fa-plus"></i></a>
                                         <%} %>
                                        </div>
                                   </div>                           
                            </div>	
<div class="panel-body" id="feederBody">
<div class="table-responsive" style="border:0">
    <c:if test="${!empty flist}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
  <th>Substation</th>
     <th>Feeder Name</th>
     <th>Voltage Level</th> 
     <th>Feeder Type</th>
     <th>Conductor</th>
     <th>Structure Code</th>  
     <th>No.of consumers</th>     
     <th>Action</th>
      
  </tr>
</thead>
<tbody>
  <c:forEach items="${flist}" var="f">
   <tr>
     <td>${f.substation_name }</td>
     <td>${f.feedername }</td>
     <td>${f.voltage_level }</td>
     <td>${f.feeder_type }</td>
     <td>${f.conductor }</td>
     <td>${f.structure_code }</td>
     <td>${f.no_of_consumers }</td>
     
      <td>
       <%if(!actionList.get(2).equals("0")) {%>
            <a href="javascript:editFeeder('${f.feederid}')" title="edit"><div class="glyphicon glyphicon-edit"></div></a> &nbsp;      
       <% }if(!actionList.get(1).equals("0")) {%>
            <a href="javascript:deleteFeeder('${f.feederid}')" title="delete"> <div class="glyphicon glyphicon-trash"></div></a>
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

