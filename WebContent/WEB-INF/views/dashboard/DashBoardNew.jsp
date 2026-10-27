<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<style>
.error{
   margin:50px;
   color:red;
}

.btn-sm{
 padding:1px 8px !important;
 margin: 0px !important;
}

.table-responsive td{
/*  padding:2px !important; */
}
.table{
 margin-bottom:10px !important;
}
[aria-labelledby$=title] { 
    display: none;  
 }
 
 .groupTitle{
     text-align: center;
    margin-bottom: -20px;
 }
</style>

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
<script>
$(document).ready(function() {
	 $('#graphDate').datepicker({
   	  dateFormat: "dd-mm-yy"
   });
   $('#graphDate').datepicker('setDate', new Date());
   
   
   getLiveLoad();
   getLiveLoadGraph();
//    getofflineFeeders();
//    getpfNpowersStatus();
//    getnotCommFeeders();
   
   getgroupAFeeders();
   getgroupBFeeders();
   getgroupCFeeders();
   getSinglePhaseFeeders();
	});
</script>

 
<div id="main-content">
               <!-- BEGIN Breadcrumb -->
                <div id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">Home</a>
                            <span class="divider"><i class="fa fa-angle-right"></i></span>
                        </li>
                        <li class="active">DashBoard One</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->

                <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Live Load Status</h4> 
                            </div>                                 
                            </div>
                            
                            
                             <div class="panel-body" style="padding:10px !important;">
                            
<div class="row" style="margin-bottom:10px !important;">    
     
     <div id="liveload" class="col-sm-12"></div>
     
     <div id="liveloadGraph" class="col-sm-12" style="height:250px;"></div>   
      
</div>
    
<div class="row">
<div class="panel panel-bd">
  <div class="panel-heading">
      <div class="panel-title">
          <h4 >select Date </h4>
          <input type="text" id="graphDate"  onchange="getGroupLoads()" style="width:105px;font-size: 14px;font-weight: normal;"/> 
      </div>                                 
      </div>
<div class="panel-body" style="padding:1px !important;">
    <h4 class="groupTitle">Group-A Feeders</h4>
    <div id="groupA" class="col-sm-12"></div>
    
    <h4 class="groupTitle">Group-B Feeders</h4>
    <div id="groupB" class="col-sm-12"></div>  
    
    <h4 class="groupTitle">Group-C Feeders</h4>
    <div id="groupC" class="col-sm-12"></div>
    
    <h4 class="groupTitle">Single Phase Feeders</h4>
    <div id="singlePhase" class="col-sm-12"></div> 
    </div>
    </div>
       
</div>



<!-- <div class="row"> -->
<!--     <div id="offlineFeeders" class="col-sm-6"></div> -->
<!--     <div id="pfNpowersStatus" class="col-sm-6"></div>     -->
<!-- </div>                             -->
                           
                             
<!-- <div class="row"> -->
<!--     <div id="offlineFeeders" class="col-sm-6"></div> -->
<!--     <div id="pfNpowersStatus" class="col-sm-6"></div>     -->
<!-- </div> -->
                          
<!-- <div class="row">     -->
<!--     <div id="notCommFeeders" class="col-sm-12"></div> -->
<!-- </div>                            -->
                            
                         
                            </div>
          <div class="clearfix">
          </div>
                    </div>
                    </div>
                </div>
                               
                
                
                
                </div>
    
<!-- ----------------------------------------------------------------------- -->



<!-- ----------------------------------------------------------------------- -->                
 	               
    <script>
   
    
    </script>       
                
                
                