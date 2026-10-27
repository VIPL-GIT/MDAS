<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>
<%@page import="java.lang.*"%>

<script src="assets/plugins/jQuery/jquery-1.12.4.min.js"></script>
<script src="assets/chosen-bootstrap/chosen.jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.4.2/chosen.css">

<link href="assets/plugins/datatables/dataTables.min.css" rel=stylesheet
	type="text/css" />
<script src="assets/plugins/datatables/dataTables.min.js"></script>
<link rel="stylesheet" href="css/table.css" type="text/css" />
<!-- <link rel="stylesheet" href="css/style.default.css" type="text/css" /> -->
<script src="js/month/jquery-ui.min.js"></script>
<link href="js/month/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/month/MonthPicker.min.js"></script>
<link  rel="stylesheet" type="text/css" href="js/month/MonthPicker.min.css"/>

<script type="text/javascript" src="js/table2excel/jquery.table2excel.js"></script>

<script src="js/highcharts/highstock.js"></script>
<script src="js/highcharts/data.js"></script>
<script src="js/highcharts/exporting.js"></script>
<script src="js/highcharts/export-data.js"></script>
        
<style>
[aria-labelledby^=id] {
  display: none;
}
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
label, select {
 float:left;
}
select{
width:150px;
margin-left:10px;
}
input{
width:80px;
margin-left:5px;
font-size:11px;
}
.rTitle{
text-align: center;
}
.frbtn{
margin-top:-5px;
margin-left:10px;
height:20px;
padding:2px;
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
//getHierarchyForFReports('${userHierarchyid }');
// getFROptions('2');
// getFReports();
$('#childID').chosen();
</script>


	<!-- BEGIN Breadcrumb -->
	<div id="breadcrumbs">
<!-- 		<ul class="breadcrumb"> -->
<!-- 			<li><i class="fa fa-home"></i> <a href="#">Home</a> <span -->
<!-- 				class="divider"><i class="fa fa-angle-right"></i></span></li> -->
<!-- 			<li class="active">Reports</li> -->
<!-- 		</ul> -->
</div>

<%@include file="getHierarchyFor_Feeder_reports.jsp" %>
	
	<div id="meterSel" >
         <div class="col-md-3">                                                                                        
            <label  class="control-label">Meter Sl No</label>  
            <select class="form-control chosen-select" onchange="" id="childID" data-placeholder="--select--" tabindex="1">
      <option value="0"></option> 
     <c:if test="${!empty meters }"> 
       <c:forEach items="${meters}" var="meter">
         <option value="${meter.meter_id }" class="ip_${meter.meterlocation }">${meter.meter_sl_no }</option>
        </c:forEach>
        </c:if>                                      
</select>
</div>
       </div>
	<!-- END Breadcrumb -->
	<!-- BEGIN Main Content -->
	<div class="row">
		<div class="col-md-12" style="margin-top:5px;">
			<div class="panel panel-bd">
				<div class="panel-heading">
					<div class="panel-title">

					<div class="col-md-12">
						<h4 style="float:left;">Select Report Type</h4>
						<div class="col-sm-4 col-lg-5 controls">
							<select class="" id="frtype" onchange="getFROptions(this.value)" style="width:100%;height:25px;">
								<option value="-1">---select---</option>
<c:if test="${menus.contains('1')}">  <option value="1"> Energy Input report</option>  </c:if>
<c:if test="${menus.contains('17')}">  <option value="17"> Energy Input report Daily</option>  </c:if>
<c:if test="${menus.contains('24')}">  <option value="24"> Energy Input report (Monthly - EA Report)</option>  </c:if>
<c:if test="${menus.contains('2')}">  <option value="2" > Hourly load report</option>  </c:if>
<c:if test="${menus.contains('22')}">  <option value="22" > Hourly load report (Live)</option>  </c:if>
<c:if test="${menus.contains('23')}">  <option value="23" > Division wise Hourly load</option>  </c:if>
<c:if test="${menus.contains('3')}">  <option value="3"> Monthly Peak load</option>  </c:if>
<c:if test="${menus.contains('4')}">  <option value="4"> Daily supply hrs</option>  </c:if>
<c:if test="${menus.contains('5')}">  <option value="5"> Avg supply hrs</option>  </c:if>
<c:if test="${menus.contains('6')}">  <option value="6" > communication summary</option>  </c:if>
<c:if test="${menus.contains('15')}">  <option value="15"> communication summary Date wise</option>  </c:if>
<c:if test="${menus.contains('8')}">  <option value="8"> Reliability Index</option>  </c:if>
<c:if test="${menus.contains('9')}">  <option value="9"> Bus Bar Losses</option>  </c:if>
<c:if test="${menus.contains('10')}">  <option value="10"> Load Curve</option>  </c:if>
<c:if test="${menus.contains('11')}">  <option value="11">OL,UV,OV and PF</option>  </c:if>
<c:if test="${menus.contains('12')}">  <option value="12">Power Failures</option>  </c:if>
<c:if test="${menus.contains('13')}">  <option value="13">Events Summary</option>  </c:if>
<c:if test="${menus.contains('25')}">  <option value="25">Summary</option>  </c:if>
<c:if test="${menus.contains('14')}">  <option value="14">Load Profile Summary</option>  </c:if>
<c:if test="${menus.contains('15')}">  <option value="16">Feeders with single phase supply (Daily)</option>  </c:if>
<c:if test="${menus.contains('20')}">  <option value="20">Feeders with single phase supply (Monthly)</option>  </c:if>
<c:if test="${menus.contains('18')}">  <option value="18">Energy Savings (during single phase supply)-Daily</option>  </c:if>
<c:if test="${menus.contains('21')}">  <option value="21">Energy Savings (during single phase supply)-Monthly</option>  </c:if>
<c:if test="${menus.contains('19')}">  <option value="19">Supply Deviations( AG )</option>  </c:if>
<c:if test="${menus.contains('26')}">  <option value="26"> Load Curve Graph</option>  </c:if>
<c:if test="${menus.contains('27')}">  <option value="27"> Load Factor</option>  </c:if>
<c:if test="${menus.contains('28')}">  <option value="28"> Summary Reports</option>  </c:if>
<c:if test="${menus.contains('29')}">  <option value="29"> Demand Forecasting</option>  </c:if>

							</select>
						</div>
<%
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
String curdate=sdf.format(new java.util.Date().getTime()-24*60*60*1000);
%>						
<div  id="frdateDiv" style="display:none;width:180px;float:left;">
<h4>Date</h4>
<input type="date"  id="frdate" value="<%=curdate %>" style="width:130px;"/>
</div>

<div  id="frdatesDiv" style="display:none;width:360px;float:left;">
<h4>From</h4>
<input type="date"  id="frfromdate" value="<%=curdate %>" style="width:130px;"/>
<h4>To</h4>
<input type="date"  id="frtodate" value="<%=curdate %>" style="width:130px;"/>
</div>

<div  id="frmonthDiv" style="display:none;width:150px;float:left;">
<h4>Month</h4>
<input type="text"  id="frmonth" value=""/>
</div>

<button class="btn btn-sm btn-info frbtn" id="fr_button" onclick="getFReports()" style="">Get Report</button>
						
					</div>

				</div>
				</div>
				<div class="panel-body">
<div  class="col-md-12">
<div id="fr_hierarchy"></div>

</div>
<div id="fr_result" class="col-md-12"></div>

</div>
			</div>
		</div>
	</div>
