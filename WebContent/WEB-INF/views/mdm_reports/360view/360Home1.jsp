<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="assets/chosen-bootstrap/chosen.jquery.min.js"></script>
<!-- <link rel="stylesheet" href="css/table.css" type="text/css" /> -->
<!-- <link rel="stylesheet" href="css/style.default.css" type="text/css" /> -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.4.2/chosen.css">
<style>
#instantTable td{
padding:2px !important;
}
.control-label{
width:100%;
text-align:left !important;
}
.col-md-2{
width:10% !important;
}
.form-control{
font-size:11px;
padding :0px !important;
height:20px !important;
}
</style>
<style>
.myButton {
	-moz-box-shadow: inset 0px 1px 0px 0px #ffffff;
	-webkit-box-shadow: inset 0px 1px 0px 0px #ffffff;
	box-shadow: inset 0px 1px 0px 0px #ffffff;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f9f9f9
		), color-stop(1, #e9e9e9));
	background: -moz-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: -webkit-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: -o-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: -ms-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: linear-gradient(to bottom, #f9f9f9 5%, #e9e9e9 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f9f9f9',
		endColorstr='#e9e9e9', GradientType=0);
	background-color: #f9f9f9;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	border: 1px solid #dcdcdc;
	display: inline-block;
	cursor: pointer;
	color: #666666;
	margin-left: 10px;
	font-size: 13px;
	font-weight: bold;
	padding: 3px 19px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #ffffff;
}

.myButton:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #e9e9e9
		), color-stop(1, #f9f9f9));
	background: -moz-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background: -webkit-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background: -o-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background: -ms-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background: linear-gradient(to bottom, #e9e9e9 5%, #f9f9f9 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#e9e9e9',
		endColorstr='#f9f9f9', GradientType=0);
	background-color: #e9e9e9;
}

.myButton:active {
	position: relative;
	top: 1px;
}

.table_download_csv_link, .table_download_xls_link {
	margin: 10px;
	display: inline-block;
	padding: 10px;
	background-color: #1ABC9C;
	border-bottom: 3px solid #16A085;
	text-align: center;
	line-height: 30px;
	border-radius: 5px;
	color: #fff;
}

.no-padding{
 padding:0px !important;
}
.error{
 color:red;
 position:relateive;
}
</style>

<script>
$(document).ready(function() {
  
});
getHierarchyFor360_reports('${userHierarchyid }');
function changeEvent(){
	$('.chosen-container').hide();
}
$('#childID').chosen();
function filterByIP(ip_id){
	if(ip_id==8) //for consumer
	  $('#div_level_3').hide();
	else
		$('#div_level_3').show();
	
	 
// 	 $('select.chosen-select option:contains(.ip_'+ip_id+')').hide();
// 	 $('select.chosen-select').trigger("chosen:updated");
}
</script>

<!-- BEGIN Breadcrumb -->
     <div id="breadcrumbs">
         <ul class="breadcrumb">
             <li>
                 <i class="fa fa-home"></i>
                 <a href="#">Home</a>
                 <span class="divider"><i class="fa fa-angle-right"></i></span>
             </li>
             <li class="active">Meter 360-view</li>
         </ul>
     </div>
     
      
      
      
      <div class="row" style="padding-top: 0px">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>360 View</h4> 
                                
                             <div class="pull-right col-md-3" >
                           <h4>Search Criteria</h4> 
							<select id="search_by"
								onchange="getMetersBySearchCriteria(this.value,'360')" data-placeholder="Choose a Category"
								tabindex="1">
								<option value="meter">By Meter</option>
								<option value="kno">By KNO</option>
								<option value="unmapped">Un-mapped</option>
							</select>
						</div>   
                            <div class="pull-right col-md-4" >
                           <h4>Installation Point</h4> 
							<select id="install_type" onchange="filterByIP(this.value)" data-placeholder="Choose a Category"
								tabindex="1">
								<option value="0">-All-</option>
								<c:forEach items="${installpoints }" var="type"> 
								<option value="${type.id }">${type.meter_type }</option>
								</c:forEach>
							</select>
						</div>	                              
                                    </div>                          
                            </div></div></div></div>
      
      
      
      
     
     <!-- END Breadcrumb -->
<div class="row table-responsive" style="min-height:30px;padding-top: -10px;display:contents;">
<div class="com-md-12">
<div class="panel-body">                                                                                             
      <input type="hidden" id="install_type"  value="3"/>          
       <div id="hierarchy"></div>
       <form name="graphicalReports"> 
<!--            <div class="col-md-2">                     -->
<!--              <b>Date</b> -->
<!--              <input style="font-size:11px;padding :0px !important;height:20px !important;width:70px !important;" type="text" readonly="true" size="5" name="startingDate" id="graphDate"/> -->
<!--              <a href="javascript:show_calendar('graphicalReports.startingDate')"> <img style="float:right;margin:-2px;"src="images/cal1.gif" id="sdate1"   height=25 border=0/></a> -->
<!--             </div> -->
       </form>
       <div id="meterSel" >
         <div class="col-md-3">                                                                                        
            <label  class="control-label">Meter Sl No</label>  
            <select class="form-control chosen" onchange="get360Report(this.value)" id="childID" data-placeholder="Choose a Category" tabindex="1">
      <option value=""> </option> 
     <c:if test="${!empty meters }"> 
       <c:forEach items="${meters}" var="meter">
         <option value="${meter.meter_sl_no }" >${meter.meter_sl_no }</option>
        </c:forEach>
        </c:if>                                      
</select>
</div>
                                     
   <input type="hidden" id="meter" value=""/> 
       </div>                                                                                                                  
 <!-- ------------------------------------------------------------------- -->
 <div id="reportdata" style="display:none;">              
<div class="col-md-12" style="margin-top:5px;">
<div class="col-md-5" style="padding-left:0px;">
<div class="panel panel-bd">
<div class="panel-heading"><div class="panel-title">Instant Params</div></div>
<div class="panel-body no-padding" id="instnatDiv">
</div>
</div>

</div>
<div class="col-md-7" style="padding-left:0px;padding-right:0px;">
<div class="panel panel-bd">
<div class="panel-heading"><div class="panel-title">Cumulative Energy Readings
<div style="float:right;">
<button class="btn-xs btn-info" onclick="viewMeter360DegreeBilling('graph')">Graphical</button>
<button class="btn-xs btn-info" onclick="viewMeter360DegreeBilling('tab')">Tabler</button>
</div>
</div>
</div>
<div class="panel-body no-padding">
<div class="col-md-12" id="billing" style="min-height: 150px;vertical-align:center;line-height:150px;"></div>

</div>
</div>
</div>
</div>
<div class="col-md-12">
<div class="panel panel-bd">
<div class="panel-heading"><div class="panel-title">Load Profile Data
<div style="float:right;">
<button class="btn-xs btn-info" onclick="viewMeter360DegreeLoad('graph')">Graphical</button>
<button class="btn-xs btn-info" onclick="viewMeter360DegreeLoad('tab')">Tabler</button>
</div>
</div>
</div>
<div class="panel-body no-padding">
<div class="col-md-12" id="load"></div>

</div>
</div>
</div>
<div class="col-md-12">
<div class="panel panel-bd">
<div class="panel-heading"><div class="panel-title">Events</div> </div>
<div class="panel-body no-padding" id="events">


</div>
</div>
</div>
</div>
</div>
</div>
                                          
</div>

