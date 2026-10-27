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
<!--                                      <h4><b>DTR List</b></h4>     -->
<%--                                      <div class="btn-group pull-right clearfix"> <%if(!actionList.get(0).equals("0")) {%> --%>
<!--                                       <a class="btn btn-default btn-rounded addbtn" title="Add new record" href="javascript:addDTR()"> -->
<!-- 	                                  <i class="fa fa-plus"></i>Add DTR</a> -->
<%--                                         <%} %> --%>
<!--                                         </div> -->
<!--                             </div> -->
    


<div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h5>DTRs List</h5> 
                                
                                    <div class="btn-group pull-right clearfix">
                                     <%if(!actionList.get(0).equals("0")) {%>
                                        <a class="btn btn-circle show-tooltip" title="Add new record" href="javascript:addDTR()"><i class="fa fa-plus"></i></a>
                                         <%} %>
                                        </div>
                                   </div>                           
                            </div>
<div class="panel-body" id="dtrBody">
<div class="table-responsive" style="border:0">
    <c:if test="${!empty dtrs}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
     <th>Feeder</th>
     <th>DTR Name</th>
     <th>Structure Code</th>
     <th>Capacity</th>    
     <th>Action</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${dtrs}" var="f">
   <tr>
     <td>${f.feeder_name }</td>
     <td>${f.dtrname }</td>
     <td>${f.structure_code }</td>
     <td>${f.capacity }</td>
     
     <td>
      <%if(!actionList.get(2).equals("0")) {%>
         <a href="javascript:editDTR('${f.dtrid}')" title="edit"><div class="glyphicon glyphicon-edit"></div></a> &nbsp;      
      <% }if(!actionList.get(1).equals("0")) {%>
         <a href="javascript:deleteDTR('${f.dtrid}')" title="delete"> <div class="glyphicon glyphicon-trash"></div></a>
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
