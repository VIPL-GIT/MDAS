

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
 margin-top:80px !important;
  border: 2px solid #888;
  width: 80%;
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

#durationGR1omins{
margin-top:-10px;
}
.highcharts-container , .highcharts-root {
 width:100% !important;
}

</Style>
<script>
$(document).ready(function() {
 	//getBarGraph();
	//pieChart(); 
	
	meterDataAnySummary();
	energyComparision();
	criticalTampers();
	tampersGr10mins();
	tamperEvents();
	
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
                        <li class="active">Exceptional Reports Summary</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->

                <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>DashBoard</h4> 
                            </div>                                 
                            </div>
                            
                            
                             <div class="panel-body" style="padding:10px !important;">
                            
                            
                             
                         <div class="row">
    <div id="meterdata" class="col-sm-6" style="padding-right:0px !important;"></div>
    <div id="energy" class="col-sm-6"></div>
                            </div>
                          
                           
    <div class="row">
      <div  id="tampers" class="col-sm-4" style="padding-right:0px !important;">
       
      </div>
      <div class="col-sm-4" style="padding-right:0px !important;">
      <div class="table-responsive" style="border:0">
	      <table class="table table-advance" cellspacing="0" width="100%" id="table1">
			<thead>
			 <tr>
			    <th colspan="2">Events (Restoration time > 10 mins)</th>
			  </tr>
			  </thead></table>
       <div id="durationGR1omins"></div>
       </div>
      </div>
      <div id="events" class="col-sm-4"></div>
    </div>
                         
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
    $('#graphDate').datepicker({
    	  dateFormat: "dd-mm-yy"
    });
    $('#graphDate').datepicker('setDate', new Date());
    
    
   
	
    </script>       
                
                
                