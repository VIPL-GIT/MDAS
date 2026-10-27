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
		 <%@include file="getHierarchyFor_live.jsp" %>
<!-- 			 <input type="hidden" id="level" value="0"/> -->
<!-- 			 <input type="hidden" id="levelID" value="0"/> -->
			 <input type="hidden" id="date" value="0"/>
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
    <div  class="col-sm-12" style="padding-right:0px;">
    
    <fieldset>
    <legend style="margin:5px !important;">Live Communication Status </legend>
     <div id="live_status" class="col-md-offset-3 col-md-6 col-md-offset-3" style="height:165px;padding-right:0px;"></div>
    </fieldset>
    
    </div>
    <div  class="col-sm-12" style="padding-right:0px;" id="liveStatus">
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

    </script>       
               
                
                