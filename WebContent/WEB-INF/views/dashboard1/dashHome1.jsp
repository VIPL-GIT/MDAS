<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>


<style>
.error{
   margin:50px;
   color:red;
}
.small{
 font-size:9px !important;
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
/* .panel{ */
/*  margin-left:-15px; */
/*  margin-right:-15px; */
/* } */
.statistic-box{
float:left;
padding: 12px 4px 0 0px;
}
.smalls{
font-size:9px !important;
}
/* .progress-radial{ */
/* width:70px; */
/* height:70px; */
/* } */

.dash2{
width:230px !important;
}
[aria-labelledby^=id] {
  display: none;
}

.panel-body{
border: 1px solid #a9becacc;
}

/* Code By Webdevtrick ( https://webdevtrick.com ) */
#circleBar {
/*   margin-top: 200px; */
  text-align: center;
/*   font-family: 'Staatliches', cursive; */

}
#circleBar .round {
/*   min-height: 255px; */
/*   margin-top: 30px; */
  position: relative;
  margin-bottom: 20px;
}
#circleBar .round strong {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -25px;
/*   transform: translate(-50%); */
  font-size: 30px;
/*   color: #212121; */
  font-weight: 100;
  margin-left:15px;
}
#circleBar span {
  display: block;
  color: #999;
  font-size: 16px;
  margin-top: 10px;
}
#circleBar span i {
  color: #ff5c5c;
  font-size: 22px;
  margin-right: 6px;
}
section button:hover {
  background-color: #ff5c5c;
}

</style>

<script>
$(document).ready(function() {
	get1week_commstatus();
	getSignalGraph();
	getLoadCurve();	
    getPowerFails();
    
    getAvgAglsupply();
    getAvgNonaglsupply();
    getDeviationAglsupply();
    getDeviationNonaglsupply();
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
                        <li class="active">DashBoard Two</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->

                <!-- BEGIN Main Content -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Live Status</h4> 
                            </div>                                 
                            </div>
                            
                            
                             <div class="panel-body" style="padding:10px !important;">
                            
<div class="row">
    <div  class="col-sm-5" style="padding-right:0px;">
    <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Live Status</h4> 
                            </div>                                 
                            </div>
                            
                            
                             <div class="panel-body" style="padding:10px !important;height:175px;">
                             <section id="circleBar">
    <div class="statistic-box">
         <div class="small">11KV Comm.status </div>
         
         <div class="col-md-3">		
        <div class="round " 
          data-value="0.57"
          data-size="100"
          data-thickness="6">
          <strong></strong>
          
        </div>
      </div>
     </div>
     <div class="statistic-box">
         <div class="small">33KV Comm.status </div>
         
         <div class="col-md-3">		
        <div class="round 33kv" 
          data-value="0.77"
          data-size="100"
          data-thickness="6">
          <strong></strong>
          
        </div>
      </div>
     </div>
     <div class="statistic-box">
         <div class="small">Tot. Comm.status </div>
         
         <div class="col-md-3">		
        <div class="round total" 
          data-value="0.97"
          data-size="100"
          data-thickness="6">
          <strong></strong>
          
        </div>
      </div>
     </div>
     <div class="statistic-box">
         <div class="small">Not Comm.status </div>
         
         <div class="col-md-3">		
        <div class="round notcomm" 
          data-value="0.57"
          data-size="100"
          data-thickness="6">
          <strong></strong>
          
        </div>
      </div>
     </div>
     </section>
     </div>
    </div>
    
    </div>
    <div  class="col-sm-4" style="height:150px;padding-right:0px;">
    
     <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>1week comm status</h4> 
                            </div>                                 
                            </div>
                            
                            
                             <div class="panel-body" id="1week_commstatus" style="padding:10px !important;">
                             </div>
     </div>
     </div>
    <div  class="col-sm-3" style="height:150px;">
       <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Signal Strength</h4> 
                            </div>                                 
                            </div>
                             <div class="panel-body" id="signal_strength" style="padding:10px !important;">
                             </div>
    </div>
</div>                            
<div class="row" style="margin-bottom:10px !important;">    
     
     <div  class="col-sm-12" > 
      <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Load Curve</h4> 
                            </div>                                 
                            </div>
                             <div class="panel-body" id="load_curve" style="height:250px;padding:10px !important;">
                             </div>
    </div>
     </div>
     <div id="supply_hrs" class="col-sm-12" style="width:100%;">
     <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Supply Hrs</h4> 
                            </div>                                 
                            </div>
                             <div class="panel-body" id="load_curve" style="padding:10px !important;">
                             
       <div id="avg_agl_supply" class="col-md-3" style="height:250px;"></div>
       <div id="avg_nonagl_supply" class="col-md-3" style="height:250px;"></div>
       <div id="deviation_agl_supply" class="col-md-3" style="height:250px;"></div>
       <div id="deviation_nonagl_supply" class="col-md-3" style="height:250px;"></div>
       </div>
       </div>
     </div>   
      
</div>
    
                            
                           
                             
<div class="row">
    <div  class="col-sm-2">
    <div class="col-md-12">
          <div class="statistic-box dash2" style="background-color:#fdba04 !important;">
              <i class="ti-bolt statistic_icon"></i>
              <div class="small">Overloaded Feeders </div>
              <h2><span class="count-number">789</span>K 
              </h2>
              
          </div>
     </div>
     <div class="col-md-12" style="margin-top:5px;">
          <div class="statistic-box dash2" style="background-color:#83aac3 !important;">
              <i class="ti-bolt statistic_icon"></i>
              <div class="small">Low PF Feeders </div>
              <h2><span class="count-number">789</span>K 
              </h2>
              
          </div>
     </div>
    </div>
    <div id="power_fails" class="col-sm-8" style="height:250px;"> power failures</div>
    <div  class="col-sm-2">
    <div class="col-md-12" style="padding:0px !important;">
          <div class="statistic-box dash2" style="background-color:#1abc9c !important;">
              <i class="ti-bolt statistic_icon"></i>
              <div class="small">Over Voltage Feeders </div>
              <h2><span class="count-number">789</span>K 
              </h2>
              
          </div>
     </div>
     <div class="col-md-12" style="margin-top:5px;padding:0px !important;">
          <div class="statistic-box dash2" style="background-color:#ed687c !important;">
              <i class="ti-bolt statistic_icon"></i>
              <div class="small">Under Voltage Feeders </div>
              <h2><span class="count-number">789</span>K 
              </h2>
              
          </div>
     </div>
    </div>
</div>
                          
                           
                            
                         
                            </div>
          <div class="clearfix">
          </div>
                    </div>
                    </div>
                </div>
                               
                
                
                
                </div>
                   
 	<script src='js/circle-progress.min.js'></script> 
 	               
    <script>
    $('#graphDate').datepicker({
    	  dateFormat: "dd-mm-yy"
    });
    $('#graphDate').datepicker('setDate', new Date());
    
    function Circlle(el,clr){
    	  $(el).circleProgress({fill: {color: clr}})
    	    .on('circle-animation-progress', function(event, progress, stepValue){
    						$(this).find('strong').text(String(stepValue.toFixed(2)).substr(2)+'%');
    				});  
    	};
    	Circlle('.round','#5cffae'); //1kv
    	Circlle('.33kv','#ffd15c'); //33kv
    	Circlle('.total','#5cceff'); //total
    	Circlle('.notcomm','#ff9d5c'); //not comm
    </script>       
               
                
                