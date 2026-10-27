<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.rtdas.meterdata.daoimpl.AlertsAndDiagnosisDao"%>
<%@page import="com.rtdas.meterdata.beans.ModemAlertsBean"%>
<%@page import="java.util.*" %>
<%@page import="utils.DBUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.rtdas.meterdata.daoimpl.AlertsAndDiagnosisDao"%>
<%@page import="com.rtdas.meterdata.beans.ModemDiagnosisBean"%>
<%@page import="com.rtdas.meterdata.daoimpl.*"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>

<link rel="stylesheet" href="css/table.css" type="text/css" />
<!-- <link rel="stylesheet" href="css/style.default.css" type="text/css" /> -->
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

<!-- <script>

$(document).ready(function() {
  
});
getHierarchyFor361('${titles[fn:length(titles)-1].hierarchy_type_id }');
function changeEvent(){
	$('.chosen-container').hide();
}
</script> -->

<!-- BEGIN Breadcrumb -->
     <div id="breadcrumbs">
         <ul class="breadcrumb">
             <li>
                 <i class="fa fa-home"></i>
                 <a href="#">Home</a>
                 <span class="divider"><i class="fa fa-angle-right"></i></span>
             </li>
             <li class="active">Latest Modem Diagnosis</li>
         </ul>
     </div>
     
      
      
      
      <div class="row" style="padding-top: 0px">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Latest Modem Diagnosis</h4> 
                                
                            </div>                          
                            </div></div></div></div>
      
      
     
      
     
     <!-- END Breadcrumb -->
<div class="row table-responsive" style="min-height:30px;padding-top: -10px">
<div class="com-md-12">
<div class="panel-body">                                                                                             
     <!--  <input type="hidden" id="install_type"  value="3"/>          
       <div id="hierarchy"></div>
       <form name="graphicalReports"> 
                                      <div class="col-md-2">                    
                                        <b>Date</b>
                                        <input style="font-size:11px;padding :0px !important;height:20px !important;width:65px !important;" type="text"  size="5" name="startingDate" id="graphDate"/>
                                        <a href="javascript:show_calendar('graphicalReports.startingDate')"> <img style="float:right;"src="images/cal1.gif" id="sdate1"  height=25 border=0/></a>
                                       </div>
                                       </form>
                                                                                                                       
      
                     <button onclick="getSummaryReport();">Get Alerts Summary</button>
                           <div id='viewmodemalert' style="float: left; width: 100%"></div>
 -->    
 
       <div id="main-content">
            
           	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-bd">



				<div class="panel-body">

					<div class="clearfix"></div>
				
				<div class="table-responsive" style="border: 0">

					<table class="table table-advance" cellspacing="0" width="100%" id="table1">

							<thead>
								<tr>
	 <th> Sl No</th>
     <th>Modem Sl.No</th>
     <th>Timestamp</th>
     <th>Firmware Version</th>
     <th>battery</th>
     <th>GSM signal</th>
     <th>Temperature</th>
     <th>Cable status</th>
     <th>SIM No</th>
     <th>IMEI No</th>
     <th>IP Address</th>
    
								</tr>
							</thead>
							<tbody>	
							<%
							 List<ModemDiagnosisBean> ModemList=AlertsAndDiagnosisDao.getModemDiagnosisLatest();							
Iterator<ModemDiagnosisBean> itr=ModemList.iterator();
int count=1;
while(itr.hasNext()){
	ModemDiagnosisBean mab=itr.next();
	
	if(mab.getModem_serial_no().startsWith("MP")){
%>
						
<tr>
<td><%=count%></td>
<td><%=mab.getModem_serial_no()==null?"":mab.getModem_serial_no()%></td>
<td><%=mab.getDiag_timestamp()==null?"":mab.getDiag_timestamp()%></td>
<td><%=mab.getFv()==null?"":mab.getFv()%></td>
<td><%=mab.getBattery()==null?"":mab.getBattery()%></td>
<%if(Double.parseDouble(mab.getGsm_signal())<=10){ %>
<td style="color:#ff4d4d;font-weight:bold;"><%=mab.getGsm_signal()==null?"":mab.getGsm_signal()%></td>
<% }%>

<%if(Double.parseDouble(mab.getGsm_signal())>10 && Integer.parseInt(mab.getGsm_signal())<=17){ %>
<td style="color:#7979bf;;font-weight:bold;"><%=mab.getGsm_signal()==null?"":mab.getGsm_signal()%></td>
<% }%>


<%if(Double.parseDouble(mab.getGsm_signal())>17){ %>
<td style="color:#0ee28a;font-weight:bold;"><%=mab.getGsm_signal()==null?"":mab.getGsm_signal()%></td>
<% }%>

<td><%=mab.getTemperature()==null?"":mab.getTemperature()%></td>
<td><%=mab.getCablestatus()==null?"":mab.getCablestatus()%></td>
<td><%=mab.getSimno()==null?"":mab.getSimno()%></td>
<td><%=mab.getImeino()==null?"":mab.getImeino()%></td>
<td><%=mab.getIpaddress()==null?"":mab.getIpaddress()%></td>
</tr>
							
							<%count++;
}							
}%>
							</tbody>
						</table>
					</div>
			
				
				
				
				
								</div>
			</div>
		</div>
	</div>               
                 
						<div id="loading" style="display: none;" class="loading part">
							<img src="images/loaders/139.gif"
								style="padding-top: 10%; padding-left: 10%;" />
						</div>
                                                                             
</div>
</div>
</div>




						
					
						
				
				
				
				
				

