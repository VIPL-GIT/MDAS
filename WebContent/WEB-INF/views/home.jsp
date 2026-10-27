<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.rtdas.common.models.*" %>
<%@page import="com.rtdas.common.beans.*" %>
<%@page import="java.text.*" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Solar</title>
        <link rel="shortcut icon" href="assets/dist/img/favicon.png" type="image/x-icon">
        <script src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.26/webfont.js"></script>
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
        <link href="assets/plugins/toastr/toastr.min.css" rel=stylesheet type="text/css"/>
        <link href="assets/plugins/emojionearea/emojionearea.min.css" rel=stylesheet type="text/css"/>
        <link href="assets/plugins/monthly/monthly.min.css" rel=stylesheet type="text/css"/>
        <link href="assets/plugins/amcharts/export.css" rel=stylesheet type="text/css"/>
        <link href="assets/dist/css/component_ui.css" rel=stylesheet type="text/css"/>
        <link href="assets/plugins/fullcalendar/fullcalendar.min.css" rel=stylesheet type="text/css"/>
        <link href="assets/plugins/bootstrap-social/bootstrap-social.css" rel=stylesheet type="text/css"/>
        <!-- START PAGE LABEL PLUGINS --> 
        <link href="assets/weather-icons/css/weather-icons.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/weather-icons/css/weather-icons-wind.min.css" rel="stylesheet" type="text/css"/>
        
        <link href="js/sweetalert/sweetalert.css" rel=stylesheet type="text/css"/>
        
        <link href="assets/plugins/datatables/dataTables.min.css" rel=stylesheet type="text/css"/>
        
        <!-- START THEME LAYOUT STYLE -->
        <link href="assets/dist/css/style.css" rel="stylesheet" type="text/css"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        
        <link href="css/table.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.simple-dtpicker.css" rel="stylesheet" type="text/css"/>
        <link href="css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <style>
        #table1_length {
        float:left !important;
        }
        #datapart{
        margin-left:-25px;
        margin-right:-25px;
        }
        .name{
         width:100px;
         
/*          padding-bottom: 15px; */
        }
        .value{
          padding:5px;
          
        }
        .value1{
        font-weight:bold;
        }
        .amcharts-chart-div a {display:none !important;}
        .panel{
        margin-bottom:5px !important;
        }
        .dbselect{
        float:right;width:130px;margin-top:5px;font-size:13px;
        }
        
        .alertImg{
        padding-left: 5px;
    border: 1px solid black;
    border-radius: 25%;
    margin-left: 5px;
        }
        
        .statistic-box{
        padding-top:12px !important;
        }
        </style>
    </head>
    <body class="hold-transition fixed sidebar-mini">
        
        <!-- Preloader -->
        <div class="preloader"></div>
       
        <!-- Site wrapper -->
        <div class="wrapper">
            <header class="main-header"> 
                <%@include file="header.jsp" %>
                <!-- Tab panes -->
                 <%@include file="side_menu.jsp" %>
            </header>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Main content -->
                <div class="content">
                <div id="dashboard">
                    <div class="row ">
                        <div class="col-md-5" style="padding:0px !important;">
							<div class="panel panel-bd row">
								<div class="panel-heading">
									<div class="panel-title">
										<h5>Site Summary</h5>
									</div>
									</div>
									<div id="panel-body">
									<img src="img/solar.png" class="col-md-6" width="50%" style="padding:10px;margin-top:12px;float:left;"/>
									<div  style="float:left;margin-top:12px;margin-bottom:45px;" class="col-md-6">
									<table >
									<tr id="trc"><td class="name">Site Name :</td>   <td class="value">PVH Demo setup</td></tr>
									<tr id="trc"><td class="name" style="vertical-align: top;
         padding-top:5px;">Site Address :</td><td class="value">Valencia,<br>Spain.</td></tr>
									<tr id="trc"><td class="name">Capacity :</td>    <td class="value">Demo Tracker</td></tr>
									<tr id="trc"><td class="name">Installed :</td>   <td class="value">30-08-2018</td></tr>
									<tr id="trc"><td class="name">Last Updated :</td><td class="value">10:10 30-08-2018</td></tr>
<!-- 									<tr id="trc"><td class="name">Peak Power :</td>  <td class="value">308.5 kwp</td></tr> -->
									</table>
									</div>
								</div>
							</div>
						</div>
						 <div class="col-md-3" style="padding:0px !important;padding-left:15px !important;">
							<div class="panel panel-bd  " style="margin-bottom:5px;">
								<div class="panel-heading">
									<div class="panel-title">
										<h5>Trackers Overview</h5>
									</div>
									</div>
									<div id="panel-body" style="height:215px;">
									<div id="trackerCommChart"></div>
									 
								</div>
								
							</div>
							</div>
							<div class="col-md-4" style="padding:0px !important;padding-left:5px !important;">
							<div class="panel panel-bd " >
								<div class="panel-heading">
									<div class="panel-title">
										<h5>Alerts & Notifications</h5>
										<select id="alertType" class="dbselect" onchange="getDashAlerts(this.value)">                                          
                                            <option value="today">Today Alerts</option>
                                            <option value="last10">Last 10 Alerts</option>
										  
										</select>
									</div>
									</div>
									<div id="panel-body " style="height:215px;overflow-y:scroll">
<!-- -------------------------------------------------- -->
                                    <div class=message_inner>
                                        <div class=message_widgets id="dashalerts">
                                            
                                        </div>
                                    </div>
<!-- ----------------------------------------------------- -->									
									</div>
								</div>
							
							</div>
						
                    </div>
                    <div class="row " style="margin-left:-30px;margin-right:-19px;">
                    <div class="col-md-8">
                        <div class="col-md-6" style="padding:0px !important;">
							<div class="panel panel-bd ">
								<div class="panel-heading">
									<div class="panel-title">
										<h5>SAT Graph</h5>
										<select id="tracker" class="dbselect" onchange="getDashGraphs();">
                                          <c:if test="${!empty tralist}">
                                            <c:forEach items="${tralist}" var="trak">
                                            <option value="${trak }">${trak eq "SMT02"?"Epsx_Pvh_Row1":trak }</option>
                                            </c:forEach>
                                            </c:if>
										  
										</select>
									</div>
									</div>
									<div id="panel-body ">
									<div id="satgraph" style="width:100%;height:150px"></div>
									</div>
								
							</div>
						</div>
						<div class="col-md-6" style="padding:0px !important;padding-left:5px !important;">
							<div class="panel panel-bd">
								<div class="panel-heading">
									<div class="panel-title">
										<h5>SOC Graph</h5>
										
									</div>
									</div>
									<div id="panel-body">
									<div id="socgraph" style="width:100%;height:150px"></div>
									</div>
								</div>
							
						</div>
						
<!--                     </div> -->
<!--                     <div class="row" style="margin-left:-19px;margin-right:-19px;"> -->
                    
						</div>
						<div class="col-md-4" style="padding:0px !important;">
						<div class="panel panel-bd   ">
								<div class="panel-heading">
									<div class="panel-title">
										<h5>Weather</h5>
									</div>
									</div>
									<div id="panel-body" style="height:0px;">
									<div id="weather">
									<div class="statistic-box col-md-12" style="margin:0px;">
									<h3 style="float:left;font-size:40px;"><i class="wi wi-day-lightning"></i></h3>
									<div style="float:left;margin-left:10px;text-align:left">
									    <div style="float:left;">Temp :<span class="value1"> 16 &#8451;</span> <br>
										Wind Speed:<span class="value1"> 4km/h</span><br>
										Humidity : <span class="value1">97%</span></div>
										<div style="float:left;margin-left:10px;">Sunrise at <span class="value1">06:30</span><br>
										Sunset at <span class="value1">18:45</span></div>
										</div>
										
									</div>
									<div class="statistic-box col-md-4">
										<div class="small">Saturday</div>
										<h3><i class="wi wi-day-lightning"></i></h3>19-8&#8451;
										
									</div>
									<div class="statistic-box col-md-4">
										<div class="small">Sunday</div>
										<h3><i class="wi wi-day-lightning"></i></h3>20-11&#8451;
										
									</div>
									<div class="statistic-box col-md-4">
										<div class="small">Monday</div>
										<h3><i class="wi wi-day-lightning"></i></h3>23-15&#8451;
										
									</div>
									</div>
									</div>
								
							</div>	
							
						
                     </div>
						
                    </div>
                    <div class="row " style="margin-left:-30px;margin-right:-19px;">
                    <div class="col-md-12">
							<div class="panel panel-bd ">
								<div class="panel-heading">
									<div class="panel-title">
										<h5>Trackers</h5>
									</div>
									</div>
									<div id="panel-body" class="table-responsive">
									<table id="trackerTable" class="table table-striped table-hover  dataTable no-footer" style="margin-bottom:0px;" role="grid" aria-describedby="trackerTable_info">
										<thead>
											<tr>
											<th >Tracker ID</th>											
											<th >Sun Angle</th>
											<th >Tracker Angle</th>
											<th >Tracker Mode</th>
											<th >Battery Status</th>
<!-- 											<th >Cells vol Min/Max</th> -->
											<th >Last Updated Time Stamp</th>
											</tr>
										</thead>
										<tbody>
<c:if test="${!empty latesttrackers}">
<%
List<TrackerDataBean> list=(List)request.getAttribute("latesttrackers");
//-----------------------------------------------------------------------------------
List<Adjustments> adjust=(List)request.getAttribute("adjusts");
Map<String,String> adjustMap=new HashMap();
if(!adjust.isEmpty()){		
	for(Adjustments ad:adjust){
		adjustMap.put(ad.getDevice_id()+"_"+ad.getAdjust_param(),ad.getAdjust_value());
		
	}
}
System.out.println(adjustMap);
//-----------------------------------------------------------------------------------
for(TrackerDataBean tdb:list){
int Tracker_Mode,T1,T2,TST;
 int Motor_status;
 String status="",image="";
String mode=tdb.getStatus();
Map<Integer,String> trackerModes=new HashMap();
trackerModes.put(1,"Initialization");
trackerModes.put(2,"Tracking");
trackerModes.put(3,"Hold");
trackerModes.put(4,"Night Stow");
trackerModes.put(5,"Wind Stow");
trackerModes.put(6,"BackTracking");
String trackerMode=trackerModes.get(Character.getNumericValue(mode.charAt(0)));
Motor_status=Character.getNumericValue(mode.charAt(3));

DecimalFormat twodec=new DecimalFormat("#0.00");
String voltage_adjust=adjustMap.containsKey(tdb.getTracker_id()+"_soc")?twodec.format(Double.parseDouble(adjustMap.get((tdb.getTracker_id()+"_soc")))+Double.parseDouble(tdb.getPack_Voltage())):tdb.getPack_Voltage();
	double soc=((Double.parseDouble(voltage_adjust)-18.6)/6.6)*100;
	soc=soc<0?0:soc;
	soc=soc>=100?100:soc;
%>
<tr>
<td><c:out value='<%=tdb.getTracker_id().equals("SMT02")?"Epsx_Pvh_Row1":tdb.getTracker_id() %>'/></td>
    <td><c:out value="<%=tdb.getSun_Position() %>"/></td>
    <td><c:out value="<%=tdb.getTracker_Position() %>"/></td>
    <td><%=trackerMode%></td>
    <td><%=twodec.format(soc) %> %</td>
<!--     <td>3.81v/ 3.92v</td>     -->
    <td><c:out value="<%=tdb.getTracker_reading_date_time() %>"/></td>
</tr>
<%} %>
</c:if>

</tbody>
									</table>
									</div>
								</div>
							
						</div>
						</div>
                    </div>
                     <div id="datapart"></div>
                     <div class="row" id="loader" style="display:none;">
<table width="100%">
<tr><td align="center">
<img src="assets/dist/img/loader.gif" width="20" height="20" style="margin-top:35px"/>
</td></tr></table>
 </div>
                </div>
            </div>
            
        </div> <!-- ./wrapper -->
<%@ include file="footer.jsp" %>         
        <!-- START CORE PLUGINS -->
         <script src="assets/plugins/jQuery/jquery-1.12.4.min.js"></script>
        <script src="assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js"></script>
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
        
        
        
        
        <script src="js/highcharts/highcharts.js"></script>
        <script src="js/highcharts/highcharts-3d.js"></script>
       <script src="js/sweetalert/sweetalert.min.js"></script> 
       
       
        <!-- START THEME LABEL SCRIPT -->
<!--         <script src="assets/dist/js/page/dashboard.js"></script> -->
        <script src="assets/dist/js/theme.js"></script>
        
        <script src="js/meterdata.js"></script>
        <script src="js/admin.js"></script>
        <script src="js/device.js"></script>
        <script src="js/config.js"></script>
        <script src="js/reports.js"></script>
        <script src="js/downloads.js"></script>
        <script src="js/dashboard.js"></script>
        <script src="js/jquery.simple-dtpicker.js"></script>
        <script src="assets/plugins/datatables/dataTables.min.js"></script>
<script>
getDashboardDetails();
callDataTable_scroll("trackerTable");
Highcharts.chart('trackerCommChart', {
    chart: {
        type: 'pie',
        height:215,
        margin:0,
        options3d: {
            enabled: true,
            alpha: 45,
            beta: 0
        }
    },
    title: {
        text: ''
    },
    tooltip: {
        pointFormat: '{point.y}</b>'
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            depth: 35,
            showInLegend:true,
            dataLabels: {
                enabled: false,
                format: '{point.name}'
            }
        }
    },
    legend: {
    	x:0,
    	y:0,
        layout: 'horizontal',
        align: 'top',
        verticalAlign: 'top'
    },
    credits:{
    	enabled:false
    },
    series: [{
        type: 'pie',
       // name: 'Browser share',
        data: [
{
    name: 'functional',
    y: <%=request.getAttribute("comm")%>,
    color:"#48a552"
},
            {name:'not-functional',y:<%=request.getAttribute("notcomm")%>,color:"#f44153"}
           
            
        ]
    }]
});



</script>
    </body>
</html>