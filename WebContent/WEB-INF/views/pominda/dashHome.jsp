<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>

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
.dash2{
 padding-left: 10px;
 color: #206065 !important;
}
.statistic-box h2 {
font-weight:300 !important;
}
.smalls{
font-size:9px !important;
}
/* .progress-radial{ */
/* width:70px; */
/* height:70px; */
/* } */

.dash2{
width:100% !important;
}
/*  [aria-labelledby^=id] {  */
/*     display: none;   */
/*  }  */

[aria-labelledby$=title] { 
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

legend{
padding: 0px 10px;
border: 1px solid silver;
vertical-align: center;
text-align: center;
width:60%;
background:#eceff1;
}
fieldset{
    border: 1px solid silver !important;
}
.form-control{
    padding: 0px !important;
    margin-bottom: 5px;
    height: 25px;
    border: 1px solid #adbabb;
}
.dashinput{
padding: 0px !important;
    margin-bottom: 5px;
    height: 25px;
    border: 1px solid #adbabb;
    border-radius: 3px;
    margin-top: 20px;
}
.table > tbody >tr>td {
text-align: center;
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
	//callAllfunctions();
	});
</script>

 
<div id="main-content">
<!--                BEGIN Breadcrumb -->
<!--                 <div id="breadcrumbs"> -->
<!--                     <ul class="breadcrumb"> -->
<!--                         <li> -->
<!--                             <i class="fa fa-home"></i> -->
<!--                             <a href="#">Home</a> -->
<!--                             <span class="divider"><i class="fa fa-angle-right"></i></span> -->
<!--                         </li> -->
<!--                         <li class="active">DashBoard Two</li> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--                 END Breadcrumb -->
		 <div id="breadcrumbs">
		 <%@include file="getHierarchyFor360_reports.jsp" %>
		 </div>

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
                                
<div class="row" style="margin-bottom:10px !important;">    
     
     <div  class="col-sm-12" style="margin-top:5px;"> 
      <fieldset> 
               <legend>Load Curve</legend>         
                <div  id="load_curve" style="height:280px;padding:10px !important;">
                </div>
    </fieldset>
     </div>
     
     <div id="supply_hrs" class="col-sm-12" style="width:100%;margin-top:5px;display:none;">
     <fieldset>
         <legend>Supply Hrs</legend>                 
        <div id="load_curve" style="padding:10px !important;">
        <div id="" class="col-md-6 table-responsive" >
       <table class="table table-advance" cellspacing="0" width="100%" >
       <thead>
        <tr>
        <td>Category</td>
        <td>Avg Supply Hours</td>
        <td>No.of deviating feeders (<9 Hours)</td>
        <td>No.of deviating feeders (>11 Hours)</td>        
        </tr>
        </thead>
        <tbody>
        <tr>
        <td>Ag</td>
        <td><div id="avg_ag_supply"></div></td>
        <td><div id="ag_9_supply"></div></td>
        <td><div id="ag_11_supply"></div></td>        
        </tr>
        </tbody>
        </table>
        </div>
        <div id="" class="col-md-6 table-responsive" >
         <table class="table table-advance" cellspacing="0" width="100%" >
         <thead>
        <tr>
        <td>Category</td>
        <td>Avg Supply Hours</td>
        <td>No.of deviating feeders (<20 Hrs)</td>
        <td>No.of deviating feeders (>20 & <22 Hrs)</td>        
        </tr>
        </thead>
        <tbody>
         <tr>
        <td>Non-Ag</td>
        <td><div id="avg_nonag_supply"></div></td>
        <td><div id="nonag_20_supply"></div></td>
        <td><div id="nonag_gr20_supply"></div></td>        
        </tr>
        </tbody>
        </table>
        </div>                     
       <div id="avg_agl_supply" class="col-md-3" style="height:0px;"></div>
       <div id="deviation_agl_supply" class="col-md-3" style="height:0px;"></div>
       <div id="avg_nonagl_supply" class="col-md-3" style="height:0px;"></div>      
       <div id="deviation_nonagl_supply" class="col-md-3" style="height:0px;"></div>
       
       </div>
       </fieldset>
     </div>   
      
</div>
    
 <%
 String curmonth="",from="",to="",previousDay="";
 try{
 SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
  curmonth=sdf1.format(new java.util.Date());
  curmonth=curmonth.substring(curmonth.indexOf("-")+1);
  
   previousDay=sdf1.format(new java.util.Date().getTime()-24*60*60*1000);
   from=sdf1.format(new java.util.Date().getTime()-7*24*60*60*1000);
   to=sdf1.format(new java.util.Date());
 }catch(Exception e){
	 
 }
 %>                           
                           
                             
<div class="row">
    <div  class="col-sm-2" style="padding-right:0px !important;">
     <div class="col-md-12" id="11kv_staus"></div>
     <div class="col-md-12" id="33kv_staus"></div> 
    </div>
    <div  class="col-sm-4" >  
    <fieldset>
     <legend>AVG. Supply Hrs Monthly <br/><p style="font-size: 11px;text-align: center;">(<%=curmonth %>)</p></legend>
     <div id="circle_supply_hrs" style="height:600px;"></div>
    </fieldset>
    </div>
    
    <div  class="col-sm-4" >  
    <fieldset>
     <legend>AVG. Supply Hrs Weekly <br/> <p style="font-size: 11px;text-align: center;">(<%=from %> to <%=to %>)</p> </legend>
     <div id="daily_supply" style="height:600px;"></div>
    </fieldset>
    </div>
    
    <div  class="col-sm-2" >  
    <fieldset>
     <legend>Interrupts <br/> <p style="font-size: 11px;text-align: center;">(<%=previousDay %>)</p></legend>
     <div id="trans_interrupts" style="height:600px;"></div>
    </fieldset>
    </div>
    
    
</div>
                          
                          
                            
                         
                            </div>
          <div class="clearfix">
          </div>
                    </div>
                    </div>
                </div>
                               
                
                
                
                </div>
    <!-- Modal -->
 <div id="bi_modal" class="modal">

  <!-- Modal content -->
  
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
    	    	          if(stepValue=="1.000")
    						$(this).find('strong').text('100%');
    	    	          else
    	    	        	$(this).find('strong').text(String(stepValue.toFixed(2)).substr(2)+'%');
    				});  
    	};
//     	Circlle('.round','#5cffae'); //1kv
//     	Circlle('.33kv','#ffd15c'); //33kv
//     	Circlle('.total','#5cceff'); //total
//     	Circlle('.notcomm','#ff9d5c'); //not comm
    </script>       
               
                
                