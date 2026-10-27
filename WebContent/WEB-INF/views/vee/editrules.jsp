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
                                
                                    
                                    </div>                          
                            </div>
                            
                            <div class="panel-body">
                                <div class="clearfix"></div>
<div class="table-responsive" style="border:0;overflow: hidden;">
    <c:if test="${!empty rule}">
                                                                                                            
                                                                                  
    
 <form action="#" class="form-horizontal" id="editUser">                                                                                    
  <div class="row">                                                                                                        
  <div class="col-md-6 "> 
     <h4 style="padding-left: 160px;font-size: 18px;">${rule.rule_name } </h4>
     <input type="hidden"  name="id" value="${rule.id }"/>
  <!-- ------------------------------------------------------------------------------------------------- -->
  <c:if test="${rule.rule_name.equals('spike check') }">
  
  <c:forEach items="${rule.params.split(',')}" var="r" varStatus="loop">
  		<div class="form-group col-md-12">                                                                                         
            <label for="params" class="col-xs-3 col-lg-2 control-label">${r }</label>                              
            <div class="col-sm-4 col-lg-5 controls">                                                                    
                <input type="text"  name="param_values" id="param_values"  class="form-control" value="${rule.param_values.split(',')[loop.index]}">
            </div>                                                                                                       
        </div>
  </c:forEach>
  </c:if>
  
  <!-- ------------------------------------------------------------------------------------------------- -->
  
  <div class="form-group">                                                                                         
      <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
         <button type="button" class="btn btn-primary" onclick="updateRule('${r.id}')"><i class="fa fa-check"></i> Update</button>                   
         <button type="button" class="btn" onclick="viewRules()">Cancel</button>                                                         
      </div>                                                                                                       
  </div>
  </div>
  </div>
  </form>
 
</c:if>
</div>
                            </div>
                        </div>
                    </div>
                </div>