<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>MDMS</title>
<link rel="shortcut icon" href="assets/dist/img/favicon.png"
	type="image/x-icon">
<script
	src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.26/webfont.js"></script>
<script>
            WebFont.load({
                google: {families: ['Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i']},
                active: function () {
                    sessionStorage.fonts = true;
                }
            });
        </script>
    
        
<!-- START GLOBAL MANDATORY STYLE -->
<link href="assets/dist/css/base.css" rel="stylesheet" type="text/css">
<!-- START PAGE LABEL PLUGINS -->
<link href="assets/plugins/toastr/toastr.min.css" rel=stylesheet
	type="text/css" />
<link href="assets/plugins/emojionearea/emojionearea.min.css"
	rel=stylesheet type="text/css" />
<link href="assets/plugins/monthly/monthly.min.css" rel=stylesheet
	type="text/css" />
<link href="assets/plugins/amcharts/export.css" rel=stylesheet
	type="text/css" />
<!-- START PAGE LABEL PLUGINS -->
<link href="assets/weather-icons/css/weather-icons.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/weather-icons/css/weather-icons-wind.min.css"
	rel="stylesheet" type="text/css" />
	
<link href="assets/plugins/jquery-ui-1.12.1/jquery-ui.min.css" rel=stylesheet type="text/css" />
<link href="js/sweetalert/sweetalert.css" rel=stylesheet type="text/css" />

<link href="assets/plugins/datatables/dataTables.min.css" rel=stylesheet type="text/css" />
<link href="assets/plugins/datatables/select.dataTables.min.css" rel=stylesheet type="text/css" />
<link href="css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<!-- START THEME LAYOUT STYLE -->
<link href="assets/dist/css/style.css" rel="stylesheet" type="text/css" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<link href="css/table.css" rel="stylesheet" type="text/css" />
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/yearpicker.css" rel="stylesheet" type="text/css" />

<link href="assets/plugins/jquery.sumoselect/sumoselect.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/select2/select2.min.css" rel="stylesheet" type="text/css"/>
         <!-- Sumoselect Js -->
        <script src="assets/plugins/jquery.sumoselect/jquery.sumoselect.min.js"></script>
        <script src="assets/plugins/jquery.sumoselect/sumoselect-active.js"></script>
       

<style>
#table1_length {
	float: left !important;
}

#datapart {
	margin-left: -25px;
	margin-right: -25px;
}

.name {
	width: 100px;

	/*          padding-bottom: 15px; */
}

.value {
	padding: 5px;
}

.value1 {
	font-weight: bold;
}

.amcharts-chart-div a {
	display: none !important;
}

.panel {
	margin-bottom: 5px !important;
}

.dbselect {
	float: right;
	width: 70px;
	margin-top: 5px;
	font-size: 13px;
}

.table-responsive input, select {
	line-height: 15px !important;
}
.buttons-html5{
 padding: 2px 5px;
    margin: 3px;
    color: #7d7878;
    font-weight: 200;
}

.dt-buttons{
 margin-left:15px !important;
}
.content-wrapper{
background-color:#fff;
}

.graph{

}
</style>

        <script type="text/javascript" src="js/date-picker.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
        <script type="text/javascript" src="js/custom/general.js"></script>
        <script type="text/javascript" src="js/custom/tables.js"></script>
        <script type="text/javascript" src="js/exporting.js"></script>
<!--         <script src="js/highcharts/highcharts.js"></script> -->
<!--         <script src="js/highcharts/highcharts-more.js"> -->
        
        <script src="js/highcharts/highstock.js"></script>
<!--         <script src="js/highcharts/highcharts-more.js"></script> -->
        <script src="js/highcharts/data.js"></script>
        <script src="js/highcharts/exporting.js"></script>
        <script src="js/highcharts/export-data.js"></script>
        
        <script type="text/javascript" src="js/custom/general.js"></script>
        
        

</head>
<body class="hold-transition  sidebar-mini" id="hbody">
	<div class="preloader"></div>
	<div class="wrapper">
		<header class="main-header">
			<%@include file="header.jsp"%>
			<%@include file="side_menu.jsp"%>
		</header>
		<div class="content-wrapper">
			<!-- Main content -->
			<div class="content">
				<div id="dashboard">
					<div class="row ">
						<div class="col-md-12" style="padding: 0px !important;">
							<div class="panel panel-bd row">
								<div class="panel-heading">
									<div class="panel-title">
										<h5>Home</h5>
									</div>
								</div>
								<div id="panel-body"></div>
							</div>
						</div>
					</div>
				</div>
				<div id="datapart"></div>
			</div>

			<div class="row" id="loader" style="display: none;">
				<table width="100%">
					<tr>
						<td align="center"><img src="assets/dist/img/loader.gif"
							width="20" height="20" style="margin-top: 35px" /></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	
	
	<%@ include file="footer.jsp"%>	
	
	<script src="js/canvas/jquery-1.11.1.min.js"></script>
<!-- 	<script src="js/canvas/jquery.canvasjs.min.js"></script> -->
	
	<script src="assets/plugins/jQuery/jquery-1.12.4.min.js"></script>
	
  <script src="assets/plugins/jquery-ui-1.12.1/jquery-ui.js"></script>
   
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/plugins/metisMenu/metisMenu.min.js"></script>
	<script src="assets/plugins/lobipanel/lobipanel.min.js"></script>
	<script src="assets/plugins/fastclick/fastclick.min.js"></script>
	<script src="assets/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- sparkline Js -->
	<script src="assets/plugins/sparkline/sparkline.min.js"></script>
	<!-- counterup Js -->
	<script src="assets/plugins/counterup/jquery.counterup.min.js"></script>
	<script src="assets/plugins/counterup/waypoints.js"></script>
	<!-- emojionearea Js -->
	<script src="assets/plugins/emojionearea/emojionearea.min.js"></script>
	<script src="assets/plugins/monthly/monthly.min.js"></script>
	<!-- amcharts Js -->
	<script src="assets/plugins/amcharts/amcharts.js"></script>
	<script src="assets/plugins/amcharts/ammap.js"></script>
	<script src="assets/plugins/amcharts/worldLow.js"></script>
	<script src="assets/plugins/amcharts/serial.js"></script>
	<script src="assets/plugins/amcharts/export.min.js"></script>
	<script src="assets/plugins/amcharts/light.js"></script>
	<script src="assets/plugins/amcharts/pie.js"></script>
	<script src="js/sweetalert/sweetalert.min.js"></script>
	
<script src="assets/plugins/amcharts4/core.js"></script>
<script src="assets/plugins/amcharts4/charts.js"></script>
<script src="assets/plugins/amcharts4/themes/animated.js"></script>
<script src="assets/plugins/amcharts4/themes/dataviz.js"></script>



<!-- modals js -->
        <script src="assets/plugins/modals/classie.js"></script>
        <script src="assets/plugins/modals/modalEffects.js"></script>



	


	<!-- START THEME LABEL SCRIPT -->
	<script src="assets/dist/js/page/dashboard.js"></script>
	
	<script src="assets/dist/js/theme.js"></script>
    
    <script src="js/dashboard.js"></script>
    <script src="js/newdashboard.js"></script>
    <script src="js/newdashboardMP.js"></script>
    
    <script src="js/meterdata.js"></script>
    <script src="js/ondemand.js"></script>
	<script src="js/admin.js"></script>
	<script src="js/sld_js.js"></script>
	<script src="js/telemeter.js"></script>
	<script src="js/device.js"></script>
	<script src="js/frtu.js"></script>
	<script src="js/network_module.js"></script>
	<script src="js/mdmreports.js"></script>
	<script src="js/type_checking.js"></script>
	<script src="js/yearpicker.js"></script>
	<script src="js/downloads.js"></script>
	<script src="js/analytics.js"></script>
	<script src="js/feeder_reports.js"></script>
	<script src="js/indl.js"></script>
	<script src="js/vee.js"></script>
    <script src="js/exceptions.js"></script>
    	
	<script src="assets/plugins/datatables/dataTables.min.js"></script>
	<script src="assets/plugins/datatables/dataTables.select.min.js"></script>
	<script src="assets/jquery-validation/jquery.validate.min.js"></script>
    <script src="assets/jquery-validation/additional-methods.min.js"></script>
	<script>
$(document).ready(function() {
   // getDashBoard('48');
    getDashBoard_1('48');
	});

</script>
	
	
</body>
</html>

<!-- <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script> -->
<!-- <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script> -->
<!--<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
         <script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
         <script type="text/javascript" src="js/plugins/jquery-ui-1.12.1.custom.min.js"></script> -->
         <!-- <script src="assets/plugins/jQuery/jquery-1.12.4.min.js"></script>
	     <script src="assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js"></script> -->
          <!-- <script src="js/highcharts/highcharts-more.js"></script> 
        <script type="text/javascript" src="js/highstock.js"></script> 
        <script type="text/javascript" src="js/highcharts-3d.js"></script>-->
        <!-- 	<script	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script> -->