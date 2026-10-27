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
System.out.println("------------------------>"+actionList);
}catch(Exception e){
	System.out.println("Exception in catch.....");
	e.printStackTrace();
}
%>
    
 <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h5>Smart Projects</h5> 
                                
                                    <div class="btn-group pull-right clearfix">
                                     <%if(!actionList.get(0).equals("0")) {%>
                                        <a class="btn btn-circle show-tooltip" title="Add new Project" href="javascript:addSmartProjects()"><i class="fa fa-plus"></i></a>
                                         <%} %>
                                        </div>
                                   </div>                           
                            </div>	
<div class="panel-body" id="feederBody">
<div class="table-responsive" style="border:0">
    <c:if test="${!empty list}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
<th>Project Name</th>
<th>Meter Manufacturer</th>
<th>Flag ID</th>
<th>LLS</th>
<th>Action</th>      
  </tr>
</thead>
<tbody>
<c:forEach items="${list}" var="l">
<tr>
<td>${l.project_name}</td>
<td>${l.meter_manufacturer}</td>
<td>${l.flag_id}</td>
<td>${l.lls}</td>
<td>
    <%if(!actionList.get(2).equals("0")) {%>
      <a href="javascript:editProject('${l.project_id}')" title="edit"><i class="ti ti-pencil"></i></a> &nbsp;      
       <% }if(!actionList.get(1).equals("0")) {%>
      <a href="javascript:deleteProject('${l.project_id}')" title="delete"> <i class="ti ti-trash"></i></a>
         <%} %>
       </td>
</tr>
</c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty list}">
<center><h5>No Projects available.	</h5></center>
</c:if>
</div>
</div>
</div>
</div>
</div>

