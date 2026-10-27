<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.text.*" %>

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
</style>

<script>
$(document).ready(function() {
  
});
getHierarchyFor360('${userHierarchyid }');
function changeEvent(){
	$('.chosen-container').hide();
}
$('#childID').chosen();

</script>

<!-- BEGIN Breadcrumb -->
     <div id="breadcrumbs">
         <ul class="breadcrumb">
             <li>
                 <i class="fa fa-home"></i>
                 <a href="#">Home</a>
                 <span class="divider"><i class="fa fa-angle-right"></i></span>
             </li>
             <li class="active">VEE Details</li>
         </ul>
     </div>
     
      
      
      
      <div class="row" style="padding-top: 0px">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>VEE Details</h4> 
                                                       
                                    </div>                          
                            </div></div></div></div>
      
      
      
      
     
     <!-- END Breadcrumb -->
<div class="row table-responsive" style="min-height:30px;padding-top: -10px;display:contents;">
<div class="com-md-12">
<div class="panel-body">                                                                                             
      <input type="hidden" id="install_type"  value="7"/>          
       <div id="hierarchy"></div>
<div id="meterSel" >
         <div class="col-md-3">                                                                                        
            <label  class="control-label">Meter Sl No</label>  
            <select class="form-control chosen" onchange="getModemDiagnosisList1(this.value)" id="childID" data-placeholder="select meter" tabindex="1">
      <option value=""> </option> 
     <c:if test="${!empty meters }"> 
       <c:forEach items="${meters}" var="meter">
         <option value="${meter.meter_id }" >${meter.meter_sl_no }</option>
        </c:forEach>
        </c:if>                                      
</select>
</div>

<%
String sdate="",edate="";
try{
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	sdate=sdf.format(new java.util.Date().getTime()-7*24*60*60*1000);
	edate=sdf.format(new java.util.Date());
}catch(Exception e){
	
}
%>                                     
  
       </div> 
     <div class="col-md-12" style="padding-top:10px;border-bottom: 2px solid #ccc;">                    
       <b>From Date</b>
       <input type="date"  size="5" id="sdate" value="<%=sdate%>"/>
       <b>To Date</b>
       <input type="date"  size="5" id="edate" value="<%=edate%>"/>
       <button class="btn btn-sm" onclick="generateVEEDetailedReport()">details</button>
      </div>

         
 
 <div id='veesummary' style="float: left; width: 100%"></div>
<div id="loading" style="display: none;" class="loading part">
  <img src="images/loaders/139.gif" style="padding-top: 10%; padding-left: 10%;" />
</div>
                                                                             
</div>

</div>
</div>

<script>
//     $('#graphDate').datepicker({
//     	  dateFormat: "dd/mm/yy"
//     });
//     $('#graphDate').datepicker('setDate', new Date());
    </script> 
