

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

<script>
$(document).ready(function() {
 	getBarGraph();
	pieChart(); 
	
	meterDataAnySummary();
	energyComparision();
	criticalTampers();
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
                        <li class="active">DashBoard</li>
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
                            
                            <div class="row" style="margin-bottom:10px !important;">
    <div id="bargraph" class="col-sm-6 bargraph" style="height: 200px"></div>
     
    <div   class="col-sm-6"  >
<!-- ----------------------------------------------------- -->
       <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                            <div class="pull-left">
                                <h5>Select Date</h5>
                                <input type="text" size="5" name="startingDate" id="graphDate" autocomplete="off" onchange="pieChart()" 
                                       style="height:20px;width:100px;font-size:11px;"/>
                                </div>
                                <div class="pull-right">
                                <h5>Select Type</h5>
					            <select name="meterType" id ="meterType" onChange="pieChart()">
					             	<option value="0">-All-</option>
									<c:forEach items="${installpoints }" var="type"> 
									<option value="${type.id }">${type.meter_type }</option>
									</c:forEach>
					            </select>
					            </div>
                                </div>                           
                            </div>	
		<div class="panel-body" style="border: 1px solid #4572a7;padding:15px;">
		 <div id="piechart" class="col-md-7" style="height:140px;"></div>
		 <div id="commSummary" class="col-md-5" style="height:140px;margin-top:-2px;">
		   <div class="col-md-12">
		   <div class="panel panel-bd" style="border:1px solid #cecfd5">
                        <div class="panel-heading" style="background: linear-gradient(#6199a0, #22909e);">
                            <div class="panel-title"><h4 style="color:#FFF;">Total Comm (mapped)</h4></div></div>
                            <div class="panel-body" style="padding:4px;">
		       <h3 style="text-align: center;cursor: pointer;"><span class=count-number id="totalComm" onclick="getHomePieChartMeters('Read')">0</span></h3>
		       </div>
		       </div>
		       </div>
		   <div class="col-md-12">
		   <div class="panel panel-bd" style="border:1px solid #cecfd5">
                        <div class="panel-heading" style="padding:0px;background: linear-gradient(#6199a0, #22909e);">
                            <div class="panel-title"><h5 style="color:#FFF;">Total Comm (unmapped)</h5></div></div>
                            <div class="panel-body" style="padding:4px;">
		       <h3 style="text-align: center;cursor: pointer;"><span class=count-number id="commUnmapped" onclick="getHomePieChartMeters('unmappedRead')">0</span></h3>
		       </div>
		       </div>
		       </div>
		 </div>
		</div>
		</div>

<!-- ------------------------------------------------------------- -->       
    </div>
      
                            </div>
    
                            
                           
                             
                         <div class="row">
    <div id="meterdata" class="col-sm-6"></div>
    <div id="energy" class="col-sm-6"></div>
                            </div>
                          
                           
                            <div class="row">
    <div id="tampers" class="col-sm-6"></div>
    <div id="events" class="col-sm-6"></div>
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
                
                
                