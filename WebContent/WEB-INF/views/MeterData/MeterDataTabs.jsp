<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
<script src="assets/plugins/jQuery/jquery-1.12.4.min.js"></script>
<script src="assets/chosen-bootstrap/chosen.jquery.min.js"></script>
<!-- <link rel="stylesheet" href="css/table.css" type="text/css" /> -->
<!-- <link rel="stylesheet" href="css/style.default.css" type="text/css" /> -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.4.2/chosen.css">
<link href="assets/plugins/modals/modal-component.css" rel="stylesheet" type="text/css"/>

<script src="assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js"></script>

 <!-- modals js -->
        <script src="assets/plugins/modals/classie.js"></script>
        <script src="assets/plugins/modals/modalEffects.js"></script>
<style>
#instantTable td{
padding:2px !important;
}
.control-label{
width:100%;
text-align:left !important;
}
.col-md-2{
width:11% !important;
}
.form-control{
font-size:11px;
padding :0px !important;
height:20px !important;
}
.breadcrumb{
 padding:0px !important;
 margin-bottom:5px;
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


//getHierarchyFor360('${titles[fn:length(titles)-1].hierarchy_type_id }');
getHierarchyFor360('${userHierarchyid }');
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

<!-- <script type="text/javascript" src="js/table2excel/jquery.table2excel.js"></script> -->
<!-- <script type="text/javascript" 	src="js/plugins/jquery-ui-1.12.1.custom.min.js"></script> -->
<!-- <script type="text/javascript" src="js/custom/elements.js"></script> -->
<!-- <script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script> -->
<!-- <script type="text/javascript" src="js/custom/tables.js"></script> -->
<script type="text/javascript" src="js/date-picker.js"></script>
<script type="text/javascript" src="js/load_survay_graphs.js"></script>
<!-- <script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script> -->
<!-- <script type="text/javascript" 	src="js/plugins/jquery-ui-1.12.1.custom.min.js"></script> -->
<!-- <script type="text/javascript" src="js/plugins/jquery.cookie.js"></script> -->

<!-- <script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script> -->
<!-- <script type="text/javascript" src="js/custom/general.js"></script> -->
<!-- <script type="text/javascript" src="js/custom/tables.js"></script> -->

<script src="js/highcharts/highcharts.js"></script> 
<script src="js/highcharts/highcharts-more.js"></script> 

<!-- <script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script> -->
<script type="text/javascript" src="js/meterdata.js"></script>

<script type="text/javascript">

 function implement()
 {
      
        $('#instantdatatable').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ]
    } );
 }
  function implement1()
 {
       //alert("in load datatable");
                $('#loaddatatable').DataTable
                ( 
                    {
                                dom: 'Blfrtip',
                                 "bSort": false,
                                //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                                //buttons: ['csv', 'excel', 'print'],
                                        
                                buttons: 
                                [
                                    {
                                        extend: 'copy',
                                        title: 'Load Survey Data'
                                    },
                                    
                                    {
                                        extend: 'excel',
                                        title: 'Load Survey Data'
                                    },                                
                                    {
                                        extend: 'csv',
                                        title: 'Load Survey Data'
                                    },
                                    {
                                        extend: 'pdf',
                                        title: 'Load Survey Data',
                                        orientation: 'landscape'
                                    }
                                ]


                    } 
                );
 }
   function implement2()
 {
      // alert("in energy datatable");
                $('#energydatatable').DataTable
                ( 
                    {
                                dom: 'Blfrtip',
                                //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                                //buttons: ['csv', 'excel', 'print'],
                                 "bSort": false,        
                                buttons: 
                                [
                                    {
                                        extend: 'copy',
                                        title: 'Energy Data'
                                    },
                                    
                                    {
                                        extend: 'excel',
                                        title: 'Energy Data'
                                    },
                                
                                    {
                                        extend: 'csv',
                                        title: 'Energy Data'
                                    },
                                    {
                                        extend: 'pdf',
                                        title: 'Energy Data',
                                        orientation: 'landscape'
                                    }
                                ]
                    } 
                );
 }
 function implement3()
 {
      // alert("in energy datatable");
                $('#tamperdatatable').DataTable
                ( 
                    {
                                dom: 'Blfrtip',
                                //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                                //buttons: ['csv', 'excel', 'print'],
                                 "bSort": false,       
                                buttons: 
                                [
                                    {
                                        extend: 'copy',
                                        title: 'Tamper Data'
                                    },
                                    
                                    {
                                        extend: 'excel',
                                        title: 'Tamper Data'
                                    },                                
                                    {
                                        extend: 'csv',
                                        title: 'Tamper Data'
                                    },
                                    {
                                        extend: 'pdf',
                                        title: 'Tamper Data',
                                        orientation: 'landscape'
                                    }
                                ]


                    } 
                );
 }
function implement4()
 {
      // alert("in energy datatable");
                $('#completedatatable').DataTable
                ( 
                    {
                                dom: 'Blfrtip',
                                //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                                //buttons: ['csv', 'excel', 'print'],
//                                 "scrollX": true,
                                 "bSort": false,
                                buttons: 
                                [
                                    {
                                        extend: 'copy',
                                        title: 'Complete Data'
                                    },
                                    
                                    {
                                        extend: 'excel',
                                        title: 'Complete Data'
                                    },
                                
                                    {
                                        extend: 'csv',
                                        title: 'Complete Data'
                                    },
                                    {
                                        extend: 'pdf',
                                        title: 'Complete Data',
                                        orientation: 'landscape'
                                    }
                                ]
                    } 
                );
 }
  function implement5()
 {
      // alert("in energy datatable");
                $('#outagetable').DataTable
                ( 
                    {
                                dom: 'Blfrtip',
                                //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                                //buttons: ['csv', 'excel', 'print'],
                                        
                                buttons: 
                                [
                                    {
                                        extend: 'copy',
                                        title: 'Outage Data'
                                    },
                                    
                                    {
                                        extend: 'excel',
                                        title: 'Outage Data'
                                    },                                
                                    {
                                        extend: 'csv',
                                        title: 'Outage Data'
                                    },
                                    {
                                        extend: 'pdf',
                                        title: 'Outage Data',
                                        orientation: 'landscape'
                                    }
                                ]


                    } 
                );
 }

        </script>
</head>
<body>
<!-- BEGIN Breadcrumb -->
     <div id="breadcrumbs">
         <ul class="breadcrumb">
             <li>
                 <i class="fa fa-home"></i>
                 <a href="#">Home</a>
                 <span class="divider"><i class="fa fa-angle-right"></i></span>
             </li>
             <li class="active">Meter Data Reports</li>
         </ul>
     </div>
     
      
      
      
      <div class="row" style="padding-top: 0px">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Meter Data Reports</h4> 
                       
                      <div class="pull-right col-md-3" >
                           <h4>Search Criteria</h4> 
							<select id="search_by"
								onchange="getMetersBySearchCriteria(this.value,'meterdata')" data-placeholder="Choose a Category"
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
                                                                                            
<!--       <input type="hidden" id="install_type"  value="3"/>           -->
       <div id="hierarchy"></div>
       <div id="meterSel" >
         <div class="col-md-3">                                                                                        
            <label  class="control-label">Meter Sl No</label>  
            <select class="form-control chosen-select" onchange="getReportsInstantData(this.value)" id="childID" data-placeholder="--select--" tabindex="1">
      <option value=""> </option> 
     <c:if test="${!empty meters }"> 
       <c:forEach items="${meters}" var="meter">
         <option value="${meter.meter_id }" class="ip_${meter.meterlocation }">${meter.meter_sl_no }</option>
        </c:forEach>
        </c:if>                                      
</select>
</div>
       </div>                                                                                                                  
    <input type="hidden" id="meter" value=""/>                                                                                  


 
</div>
</div>


<div class="pageheader notab" id="pageheader" style="padding-top: -50px">
					<ul class="hornav">

					
						 <li id="kumar"><a href="javascript:getReportsInstantData(this.value)">Instantaneous</a></li>
					 <!-- <li id="kumar"><a href="javascript:getInstantTabs()">Instantaneous</a></li> -->

						<li id="kumar1"><a href="javascript:getEnergyOptions()">Energy</a></li>
						<li id="kumar2"><a href="javascript:getLoadPage()">Load	Survey</a></li>
						<li id="kumar3"><a href="javascript:getEventsPage()">Tampers</a></li>
						<li id="kumar4"><a href="javascript:getSnapshotPage()">Midnight Snapshot Data</a></li>
						
						<li id="kumar5"><a href="javascript:getDataProfiles()">Download Data Profiles</a></li>
						<li id="kumar6"><a href="javascript:viewMeter360()">360<sup>0</sup> View</sup></a></li>

<!--                         <li id="kumar7"><a href="javascript:getReport1()">Instant(Push)</a></li> -->
<!-- 						<li id="kumar8"><a href="javascript:getReport2()">Alerts(Push)</a></li> -->

						<!--<li id="kumar5"><a href="javascript:getOutageReportPage()">Outage Report</a></li>-->

					</ul>
					                                                
					<button type="button" style="float:right;margin-top:-30px;" class="btn btn-primary btn-circle m-rb-3 md-trigger m-b-5 m-r-2"
					data-modal="meterinfo">
					<i class="glyphicon glyphicon-info-sign" title="meter info"></i></button>
					
				</div>
				
				
					<div id="contentwrapper" class="contentwrapper">
					<div class="tabcontent paddingAll" id="cont-1-1"
						style="display: none">


						<!-- <div class="button_style"> <input type="radio" name="instant" id="instant1" value="1" checked  onclick="getInstantData2(this.value)"> Latest Data &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div> -->

						<div class="button_style">
							<input type="radio" name="instant" id="instant1" value="1"
								checked onclick="getInstantData2(this.value)"> Latest
							Data &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<div class="button_style">
							<input type="radio" name="instant" id="instant2" value="2"
								onclick="getDates()"> Complete Data
						</div>
						<div class="button_style">
							<input type="radio" name="instant" id="instant3" value="3"
								onclick="getInstantOptions()"> Graphs
						</div>
						<!-- <div class="button_style"><input type="radio" name="instant" id="instant4" value="4" onclick="getInstantAllParams()">All Params</div> -->
						<table>
							<tr>
								<td><span class="selectamf"><input type="checkbox"
										id="AEMF" checked=false>Apply EMF</span></td>

								<td>
									<form name="graphicalReports">
										<table>
											<tr>
												<td id="gdate" style="display: none; margin-top: -9px;">
													<b>Select Date:</b> <input type="text" readonly="true"
													size="10" name="startingDate" id="graphDate" /> <a
													href="javascript:show_calendar('graphicalReports.startingDate')">
														<img style="padding-top: 8px;" src="images/cal1.gif"
														id="sdate1" width=24 height=22 border=0 />
												</a> &nbsp;
												</td>
											</tr>
										</table>
									</form>
									<div id="date_div"
										style="float: left; clear: both; display: none">
										<form name="iReports">
											<table>
												<tr>
													<td id="ictd" style="display: none">
														<!-- <b>From Date:</b>--> <input type="text"
														readonly="true" size="10" name="startingDate" id="sDate"
														placeholder="From Date" /> <a
														href="javascript:show_calendar('iReports.startingDate')">
															<img src="images/cal1.gif" id="sdate" width=24 height=22
															border=0 />
													</a> &nbsp;
													</td>
													<td id="ictd" style="display: none">
														<!-- <b>To Date:</b>--> <input type="text" readonly="true"
														size="10" name="endingDate" id="eDate"
														placeholder="To Date" /> <a
														href="javascript:show_calendar('iReports.endingDate')">
															<img src="images/cal1.gif" id="edate" width=24 height=22
															border=0 />
													</a>
													</td>
													<td><input type="month" id="month" name="Month Picker">
													</td>
													<td>
														<%--<input type="button" size="30" id="" value="Get Complete Data" onclick="getInstantData2(2)" class="color_button"> --%>
														<input type="button" size="30" id=""
														value="Get Complete Data" onclick="getcompleteData()"
														class="myButton">
													</td>
												</tr>
											</table>
										</form>
									</div>
								</td>
							</tr>
						</table>

						<div id="ip_opt_div1"
							style="float: left; clear: both; display: none">
							<div class="button_style">
								<input type="radio" name="instant_1" value="1"
									onclick="getVANDCGraphs(this.value)"> Voltage Graph
							</div>
							<div class="button_style">
								<input type="radio" name="instant_1" value="2"
									onclick="getVANDCGraphs(this.value)"> Current Graph
							</div>
						</div>
					</div>


					<div id="cont-2-1"></div>
					<div id="cont-3-1-1"></div>
					<div id="cont-3-1"></div>
					<div id="cont-3-2"></div>
					<div id="cont-4-1"></div>
					<div id="cont-5-1"></div>
<div style="display:none" id="cont-3-3">
	<form name="loadmultiplegraphs">
    <table id="multipledate" style="vertical-align: top;">
        <tr>
        <td > From Date:
        <input type="text" readonly="true" size="10" name="startingDate" id="multiplesDate" style="border:1px solid #666666;"/></td>
        <td style="padding-top:0px;padding-right:10px">
    <a href="javascript:show_calendar('loadmultiplegraphs.startingDate')"> <img src="images/cal1.gif" id="sdate"  width=24 height=22 border=0/></a></td>
        <td colspan="2">To Date:
    <input type="text" readonly="true" size="10" name="endingDate" id="multipleeDate" style="border:1px solid #666666;"/></td>
        <td style="padding-top:1px;" >
    <a href="javascript:show_calendar('loadmultiplegraphs.endingDate')"> <img src="images/cal1.gif" id="edate"  width=24 height=22 border=0/></a></td>
    
    <td>
	 <!--<input type="checkbox" name="chbMF" id="chbMF" />Apply MF -->
	 &nbsp;
    <input type="radio" value="1" id="loadGraph" name="loadGraph"  onclick="getMultipleLoadGraph(this.value);"/>Get Graph &nbsp;&nbsp;&nbsp;
<!--    <input type="radio"  value="2" name="loadGraph"onclick="getMultipleLoadGraph(this.value);"/>Bar Chart-->
            </td>
        </tr>
        <tr><td></td></tr>
        <tr><td colspan="7" style="height:1px;background-color:#ddd;" ></td></tr>
    </table></form>
				</div>
					<div  id="cont-6-1" style="display:none">
            
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

					<div class="scrollale">
						<div id='DataPart' style="float: left; width: 100%"></div>
						<div id="loading" style="display: none;" class="loading part">
							<img src="images/loaders/139.gif"
								style="padding-top: 10%; padding-left: 10%;" />
						</div>
					</div>
				</div>
				
				
				
				


<div id="dcu_loading" style="display: none">
				<table width="100%">
					<tr>
						<td align="center"><img src="images/loaders/139.gif"
							style="padding-top: 150px;" /></td>
					</tr>
				</table>
			</div>
<!-- Modal side fall effects -->
<!-- the overlay element -->
                                                
 <div class="md-modal md-effect-6" id="meterinfo">
     <div class="md-content">
         <h3>Meter Info</h3>
         <div class="n-modal-body" id="meterdetails">
             This div contains meter info
             <button class="btn btn-base md-close">Close me!</button>
         </div>
     </div>
 </div>
 
  <div class="md-overlay"></div>
 </body>

