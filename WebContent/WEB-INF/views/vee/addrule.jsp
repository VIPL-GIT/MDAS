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
<form action="#" class="form-horizontal" id="editUser">                                                                                    
  <div class="row">                                                                                                        
  <div class="col-md-6 "> 
  
  
  <div class="form-group">                                                                                         
      <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
         <button type="button" class="btn btn-primary" onclick="addRuleAction()"><i class="fa fa-check"></i> Add</button>                   
         <button type="button" class="btn" onclick="viewRules()">Cancel</button>                                                         
      </div>                                                                                                       
  </div>
  </div>
  </div>
  </form>

</div>
                            </div>
                        </div>
                    </div>
                </div>