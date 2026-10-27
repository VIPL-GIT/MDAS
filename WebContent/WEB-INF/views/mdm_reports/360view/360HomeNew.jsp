<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
.panel-title{
    background: #becfcd;
    color: darkred;
    text-align: center;
}
.panel{
border: 1px solid #ddd !important;
}
..panel-heading{
padding:0px !important;
}
</style>
<link href="./assets/plugins/datatables/dataTables.min.css" rel=stylesheet type="text/css" />
<link href="./assets/plugins/datatables/select.dataTables.min.css" rel=stylesheet type="text/css" />
<link href="./css/table.css" rel="stylesheet" type="text/css" />
<link href="./assets/plugins/jquery-ui-1.12.1/jquery-ui.min.css" rel=stylesheet type="text/css" />
<link href="./assets/bootstrap/css/bootstrap.min.css" rel=stylesheet type="text/css" />

<script src="./assets/plugins/jQuery/jquery-1.12.4.min.js"></script>
<script src="./assets/bootstrap/js/bootstrap.min.js"></script>
<script src="./js/mdmreports.js"></script>
<script src="./assets/plugins/datatables/dataTables.min.js"></script>
<script src="./assets/plugins/datatables/dataTables.select.min.js"></script>
<!-- <script src="./js/highcharts/highcharts-more.js"></script>  -->
<script type="text/javascript" src="./js/highcharts/highcharts.js"></script> 
<script src="./js/highcharts/highcharts-more.js"></script>	
<script>

var meter=${meter};
viewMeter360DegreeInstant(meter);
viewMeter360DegreeBilling('graph',meter);
viewMeter360DegreeLoad('graph',meter);
viewMeter360Events(meter);
</script>

<div class="row table-responsive" style="min-height:30px;padding-top: -10px;display:contents;">
<div class="col-md-12" style="margin-top:5px;">
<div class="col-md-5" style="padding-left:0px;">
<div class="panel panel-bd">
<div class="panel-heading"><div class="panel-title">Instant Params</div></div>
<div class="panel-body no-padding" >
<div class="col-md-12">
<div id="voltages" class="col-md-4" style="padding:0px !important;"></div>
<div id="currents" class="col-md-4" style="padding:0px !important;"></div>
<div id="pfs" class="col-md-4" style="padding:0px !important;"></div>
</div>
<div class="col-md-12" id="instnatDiv">
</div>
</div>
</div>

</div>
<div class="col-md-7" style="padding-left:0px;padding-right:0px;">
<div class="panel panel-bd">
<div class="panel-heading"><div class="panel-title">Cumulative Energy Readings
<div style="float:right;">
<button class="btn-xs btn-info" onclick="viewMeter360DegreeBilling('graph',${meter})">Graphical</button>
<button class="btn-xs btn-info" onclick="viewMeter360DegreeBilling('tab',${meter})">Tabler</button>
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
<button class="btn-xs btn-info" onclick="viewMeter360DegreeLoad('graph',${meter})">Graphical</button>
<button class="btn-xs btn-info" onclick="viewMeter360DegreeLoad('tab',${meter})">Tabler</button>
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