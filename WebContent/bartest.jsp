<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.rtdas.common.service.*"%>
<%@page import="com.rtdas.common.models.*"%>
<%@page import="java.util.*"%>




<link rel="stylesheet" href="css/table.css" type="text/css" />
<link rel="stylesheet" href="css/style.default.css" type="text/css" />


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

<script type="text/javascript"
	src="js/table2excel/jquery.table2excel.js"></script>
<script type="text/javascript"
	src="js/plugins/jquery-ui-1.12.1.custom.min.js"></script>
<script type="text/javascript" src="js/custom/elements.js"></script>
<script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/custom/tables.js"></script>
<script type="text/javascript" src="js/date-picker.js"></script>
<script type="text/javascript" src="js/load_survay_graphs.js"></script>
<script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="js/plugins/jquery-ui-1.12.1.custom.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>

<script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript" src="js/custom/tables.js"></script>
<script src="js/highcharts/highcharts.js"></script>
<script src="js/highcharts/highcharts-more.js"></script>

<script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="js/meterdata.js"></script>
<script type="text/javascript">

 function implement()
 {
       alert("in implement");
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



<div id="main-content">
	<!-- BEGIN Breadcrumb -->
	<div id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="fa fa-home"></i> <a href="#">Home</a> <span
				class="divider"><i class="fa fa-angle-right"></i></span></li>
			<li class="active">MeterDataReports</li>
		</ul>
	</div>
	<!-- END Breadcrumb -->
	<!-- BEGIN Main Content -->
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-bd">
				<div class="panel-heading">
					<div class="panel-title">
						<h4>MeterDataReports</h4>
						
						<div class="pull-right col-md-2">

							<select class="form-control chosen" id="install_type"
								onchange="changeEvent()" data-placeholder="Choose a Category"
								tabindex="1">
								<option value="0">-All-</option>
								<option value="1">Feeder</option>
								<option value="2">DTR</option>
								<option value="3">HT</option>
							</select>
						</div>
						
					</div>
				</div>

				<div class="panel-body">
					<div class="clearfix"></div>
					
					<div>
					
					
						<%
//int titleId=Integer.parseInt(request.getParameter("titleId"));
int titleId=17;
int level=0,levelID=0;
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());

HierarchyTitleService tService = (HierarchyTitleService) wac.getBean("titleService");
HierarchyService hService = (HierarchyService) wac.getBean("hierarchyService");
String clientId=session.getAttribute("clientId").toString();


List<HierarchyTitle> tList=tService.listTitles(Integer.parseInt(clientId));
HierarchyTitle firstTitle=(HierarchyTitle)tList.get(0);
List<Hierarchy> firstHierarchy=hService.getHierarchyByType(firstTitle.getHierarchy_type_id());



for(int i=0;i<tList.size();i++){
	System.out.println(i+" level "+level);
	HierarchyTitle htitle=(HierarchyTitle)tList.get(i);
	if(htitle.getHierarchy_type_id()==titleId){
		System.out.println(i+" inininininininininininin "+level);
		level=i+1;
	System.out.println("level itertion "+level);
}
}

if(titleId!=0){
%>
					<%-- 	<input type="text" id="level" value="<%=level%>" />  --%>
						<input type="text" id="level" value="<%=level%>" /> 
						<input type="text" id="levelID" value="<%=levelID%>" />
						<%
}
     for(int i=0;i<level;i++){
    	 HierarchyTitle title=(HierarchyTitle)tList.get(i);
    	 %>

						<div class="col-md-2">
							<label class="control-label"><%=title.getHierarchy_title() %></label>

							<%if(i+1==tList.size()) {%>
							<select class="form-control chosen ssID" id="level_<%=i+1%>"
								data-placeholder="Choose a Category" tabindex="1"
								onchange="getMeters(this.value,'meterSel')">

								<%}else{ %>
								<select class="form-control chosen-select"
								onchange="getHierarchyData(this.value,'level_<%=i+2%>')"
								id="level_<%=i+1%>" data-placeholder="Choose a Category"
								tabindex="1">
									<%} %>
									<option value=""></option>
									<%if(i==0){
                	  for(int l=0;l<firstHierarchy.size();l++){
                		  Hierarchy hie=(Hierarchy)firstHierarchy.get(l);
           	  %>
									<option value="<%=hie.getHierarchy_id()%>"><%=hie.getHierarchy_name()%>
									</option>
									<%  } }
              %>
							</select>
						</div>
						<% }
               %>

						
					</div>
					
						<input type="hidden" id="install_type" value="3" />
						<div id="meterSel">
							<div class="col-md-2">
								<label class="control-label">Meter Sl No</label> 
									<select class="form-control chosen" onchange="getReport(this.value)"
									id="childID" data-placeholder="Choose a Category" tabindex="1">
									<option value=""></option>
									<c:if test="${!empty meters }">
									<c:forEach items="${meters}" var="meter">
											<option value="${meter.meter_id }">${meter.meter_sl_no }</option>
										</c:forEach> 
									</c:if>
								</select>
							</div>
						</div>
						<input type="hidden" id="meter" value="" />
					</div>
				






				<div class="pageheader notab" id="pageheader">
					<ul class="hornav">

						<li id="kumar"><a
							href="javascript:getReportsInstantData('41853')">Instantaneous</a></li>
						<!--     <li id="kumar"><a href="javascript:getInstantTabs()">Instantaneous</a></li>-->

						<li id="kumar1"><a href="javascript:getEnergyOptions()">Energy</a></li>
						<li id="kumar2"><a href="javascript:getLoadPage()">Load
								Survey</a></li>
						<li id="kumar3"><a href="javascript:getEventsPage()">Tampers
						</a></li>
						<li id="kumar4"><a href="javascript:getSnapshotPage()">Snapshot
								Data</a></li>



						<!--<li id="kumar5"><a href="javascript:getOutageReportPage()">Outage Report</a></li>-->

					</ul>
				</div>
				<!--pageheader-->

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

					<div id="cont-4-1"></div>

					<div id="cont-5-1"></div>

					<div class="tabcontent paddingAll hide" id="cont-6-1">
						<span style="display: none;" id="outelements">
							<p style="font-weight: bold">
								Select Date : <input type="date" id="outdate" class="outdate"
									placeholder=""> <input type="button" size="30" id=""
									value="Get Outage Info" onclick="getOutageInfo()"
									class="myButton">
							</p>
						</span>
					</div>

					<div class="scrollale">
						<div id='DataPart' style="float: left; width: 100%"></div>
						<div id="loading" style="display: none;" class="loading part">
							<img src="images/loaders/139.gif"
								style="padding-top: 10%; padding-left: 10%;" />
						</div>

					</div>
				</div>
				<!-- centercontent -->
			</div>
			<!--bodywrapper-->


			<div id="dcu_loading" style="display: none">
				<table width="100%">
					<tr>
						<td align="center"><img src="images/loaders/139.gif"
							style="padding-top: 150px;" /></td>
					</tr>
				</table>
			</div>

		</div>
	</div>
</div>
</div>