<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>

<link href="assets/plugins/datatables/dataTables.min.css" rel=stylesheet type="text/css" />
<script src="assets/plugins/datatables/dataTables.min.js"></script>

<div id="main-content">
               <!-- BEGIN Breadcrumb -->
                <div id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">Home</a>
                            <span class="divider"><i class="fa fa-angle-right"></i></span>
                        </li>
                        <li class="active">VEE</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->
                <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>VEE Rules List</h4> 
                                
                                    <div class="btn-group pull-right clearfix">                                     
                                        <a class="btn btn-circle show-tooltip" title="Add new rule" href="javascript:addVeeRule()"><i class="fa fa-plus"></i></a>
                                     
                                        </div>
                                    </div>                          
                            </div>
                            
                            <div class="panel-body">
                                <div class="clearfix"></div>
<div class="table-responsive" style="border:0">
    <c:if test="${!empty rules}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>

   <th>Rule Name</th>
   <th>Params</th>   
   <th>Param Values</th>
   <th>Formula</th>
<!--    <th>Created By</th>    -->
   <th>Action</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${rules}" var="r">
   <tr>

    <td><c:out value="${r.rule_name}"/></td>
    <td><c:out value="${r.params}"/></td>   
    <td><c:out value="${r.param_values}"/></td>
    <td>${r.formula.replaceAll(',','</br>')}</td>
<%--     <td><c:out value="${r.created_by}"/></td> --%>
    
    <td>
    <c:if test="${r.is_editable=='1' }">
      <a href="javascript:editRule('${r.id}')" title="edit"><i class="ti ti-pencil"></i></a> &nbsp;
      </c:if>      
    
<%--       <a href="javascript:deleteUser('${r.id}')" title="delete"> <i class="ti ti-trash"></i></a> --%>
    
       </td>
   </tr>
  </c:forEach>
  </tbody>
 </table> 
</c:if>
<c:if test="${empty rules}">
<center><h3>No Data Available</h3></center>
</c:if>
</div>
                            </div>
                        </div>
                    </div>
                </div>