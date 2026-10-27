<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="stylesheet" href="css/table.css" type="text/css" />
<!-- <link rel="stylesheet" href="css/style.default.css" type="text/css" /> -->
<script src="js/month/jquery-ui.min.js"></script>
<link href="js/month/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/month/MonthPicker.min.js"></script>
<link  rel="stylesheet" type="text/css" href="js/month/MonthPicker.min.css"/>
        
       
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
</style>


<script>
function getDOptions(value){
	if(value=="dsummary"){
		$("#viewresult").empty();
		$("#pbody").empty();
		//document.getElementById("pbody").innerHTML=document.getElementById("bill_summary").innerHTML;
		//$('#month').MonthPicker({SelectedMonth: 0, Button: false });
		getDeviceSummary();
	}
	if(value=="masterdata"){
		$("#viewresult").empty();
		$("#pbody").empty();		
		getMasterData();
	}
	if(value=="rawdata"){
		$("#viewresult").empty();
		$("#pbody").empty();
		document.getElementById("pbody").innerHTML=document.getElementById("data").innerHTML;
		$('#month').MonthPicker({SelectedMonth: 0, Button: false });
		
	}
	if(value=="mobileusers"){
		$("#viewresult").empty();
		$("#pbody").empty();		
		document.getElementById("pbody").innerHTML=document.getElementById("mlogs").innerHTML;
		$('#mlogs_month').MonthPicker({SelectedMonth: 0, Button: false });
	}
	if(value=="mobileuploads"){
		$("#viewresult").empty();
		$("#pbody").empty();
		getMobileUploads();
	}
}

$(document).ready(function() {
	getDeviceSummary();
	});
</script>

<!-- BEGIN Breadcrumb -->
     <div id="breadcrumbs">
         <ul class="breadcrumb">
             <li>
                 <i class="fa fa-home"></i>
                 <a href="#">Home</a>
                 <span class="divider"><i class="fa fa-angle-right"></i></span>
             </li>
             <li class="active">Downloads</li>
         </ul>
     </div>
     
      
      
      
      <div class="row" style="padding-top: 0px">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                           <div class="form-group no-margin" style="margin:0px;">
									
											<input type="radio" id="dsummary" name="nwtype"  checked="checked" value="dsummary" onclick="getDOptions(this.value)">
											<h5 class="nwh5">Device Summary</h5>
											
											<input type="radio" id="masterdata" name="nwtype" value="masterdata" onclick="getDOptions(this.value)">
											<h5 class="nwh5">Master Data</h5>
											
											<input type="radio" id="rawdata" name="nwtype" value="rawdata" onclick="getDOptions(this.value)">
											<h5 class="nwh5">MIOS/emd/Gxt</h5>
											
											<input type="radio" id="mobileusers" name="nwtype" value="mobileusers" onclick="getDOptions(this.value)">
											<h5 class="nwh5">Mobile Users Logs</h5>
											
											<input type="radio" id="mobileuploads" name="nwtype" value="mobileuploads" onclick="getDOptions(this.value)">
											<h5 class="nwh5">Data Uploads(Mobile App)</h5>
											
									
                            </div> 
							
                                 </div>                          
                            </div></div></div></div>
      
      
      
      
     
     <!-- END Breadcrumb -->
<div class="row table-responsive" style="min-height:30px;padding-top: -10px">
<div class="com-md-12">
<div class="panel-body" > 
      <div id="pbody"></div>
    
                        <div id='viewresult' style="float: left; width: 100%"></div>
						<div id="loading" style="display: none;" class="loading part">
							<img src="images/loader.gif"
								style="padding-top: 10%; padding-left: 30%;" />
						</div>
                                                                             
</div>

</div>
</div>
<div id="data" style="display:none;">
<div class="col-md-3">
       <label class="">Select Month</label>    
       <input type="text" id="month" style="width:100px;" autocomplete="off"/>
      </div>      
      
      <div class="col-md-2">
      <input type="button" value="Get data availability"  onclick="getRawdata()" class="myButton" />
      </div>
</div>

<div id="mlogs" style="display:none;">
<div class="col-md-3">
       <label class="">Select Month</label>    
       <input type="text" id="mlogs_month" style="width:100px;" autocomplete="off"/>
      </div>      
      
      <div class="col-md-2">
      <input type="button" value="Get Logs"  onclick="getMobileUsersLogs()" class="myButton" />
      </div>
</div>


