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


 /* select, option {
  width: 120px;
  border:1px;
  font-size:12px;
  font-family:verdana;
  overflow:hidden;
}

input[type=text] {
  width: 20%;
  /* margin: 8px 0;
  box-sizing: border-box; */
} */
 
</style>

<script>

$(document).ready(function() {
 
});
//getHierarchyForOnDemand('${titles[fn:length(titles)-1].hierarchy_type_id }');
getHierarchyForOnDemand('${userHierarchyid }');
$('#childID').chosen();
function changeEvent(){
	$('.chosen-container').hide();
}
function filterByIP(ip_id){
	if(ip_id==8) //for consumer
	  $('#div_level_3').hide();
	else
		$('#div_level_3').show();
	
	 
// 	 $('select.chosen-select option:contains(.ip_'+ip_id+')').hide();
// 	 $('select.chosen-select').trigger("chosen:updated");
}
</script>
<script type="text/javascript" src="js/ondemand.js"></script>
<!-- BEGIN Breadcrumb -->
     <div id="breadcrumbs">
         <ul class="breadcrumb">
             <li>
                 <i class="fa fa-home"></i>
                 <a href="#">Home</a>
                 <span class="divider"><i class="fa fa-angle-right"></i></span>
             </li>
             <li class="active">On Demand</li>
         </ul>
     </div>
      
      
      <div class="row" style="padding-top: 0px">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>On Demand</h4> 
                      <div class="pull-right col-md-3" >
                           <h4>Search Criteria</h4> 
							<select id="search_by"
								onchange="getMetersBySearchCriteria(this.value,'ondemand')" data-placeholder="Choose a Category"
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
<div class="panel-body" style="padding:0px !important;">                                                                                             
              
       <div id="hierarchy"></div>
       <div id="meterSel" >
         <div class="col-md-3">                                                                                        
            <label  class="control-label">Meter Sl No</label>  
            <select class="form-control chosen"  id="childID" data-placeholder="--select--" tabindex="1">
      <option value=""> </option> 
     <c:if test="${!empty meters }"> 
       <c:forEach items="${meters}" var="meter">
         <option value="${meter.meter_id }" >${meter.meter_sl_no }</option>
        </c:forEach>
        </c:if>                                      
</select>
</div>
       </div>                                                                                                                  
    <input type="hidden" id="meter" value=""/>                                                                                  
</div>

 
</div>
</div>


<div class="pageheader notab" id="pageheader" style="padding-top: -50px">
					<ul class="hornav">
						 <li id="send"><a href="javascript:demandSending()">ON DEMAND SENDING</a></li>
						 <li id="report"><a href="javascript:getOnDemandLogs()">ON DEMAND REPORTS</a></li>
					</ul>
				</div>
				
				
				
					<div id="contentwrapper" class="contentwrapper">
					
					 <div id="cont-1-1" style="display:none">
            <form name="form1" action="#">
                <table border="0" bgcolor="#FFE4E1" style="margin-top: 1%;margin-left: 2%;margin-right: 2%;width:96%";cellspacing="10px">

                    <%
                        String user = "Guest"; //by default user type
                        if (session.getAttribute("userid") != null) {
                            user = session.getAttribute("user_type").toString();
                        }
                    %>
                    <tr id="commandSelection" >
                        <td width="18%" style="white-space:nowrap;">Type of Request
                            <select name="CTM" id="CTM" onChange="getConfigOptions(this.value)">
                               <option value="DPIP">--select--</option>
                                <option value="DPCD">ONDEMAND COMPLETE DATA</option>
                                <option value="DPLS">ONDEMAND LOADSURVEY DATA</option>
                                <option value="DPTD">ONDEMAND TAMPER DATA</option>
                                <option value="DPIP">ONDEMAND INSTANTANEOUS</option>
                               <!--  <option value="RST">MODEM RESTART</option>  
                                <option value="FNS">STOP THE MODEM</option>      
                                <option value="FN1">START</option> 
                                <option value="INST">CHANGE INSTANTANEOUS FREQENCY</option>
                                <option value="MN">CHANGE MASTER NUMBER</option>
                                <option value="FLGI">CHANGE DISCOM NAME</option>    
                                <option value="FLGT">CHANGE SCHEDULE TIMES</option>
                                <option value="SL">CHANGE MODEM_SERIAL_NUMBER</option>
                                <option value="FLGA">APN & Ports Change</option>
                                <option value="POTA">POTA [Over the Air Programming]</option>
                                <option value="CONFIG">SEND CONFIGURATION TO MODEM</option>
                                <option value="RDCFG">READ CONFIGURATION FROM MODEM</option>                                                     
                                <option value="TAM1">TAMPER ENABLE</option>
                                <option value="TAM0">TAMPER DISABLE</option> -->
                                
                                
                             
                            </select>    
                        </td>

                        <td colspan="3" width="10%" style="white-space:nowrap;">
                            <div id="lsDays" style="display:none">
                                No. of Load Survey Dates:
                                <select name="lsDays" id="lsDates">
                                    <option value="02">02</option>
                                    <option value="05">05</option>
                                    <option value="10">10</option>
                                    <option value="15">15</option>
                                    <option value="20">20</option>
                                    <option value="25">25</option>
                                    <option value="30">30</option>
                                    <option value="35">35</option>
                                </select>  
                            </div>
                            <div id="mnCB" style="display:none"> Enter Master Number: <input type="text" value="" name="MNValue" id="MNValue"/></div>
                            <div id="slnoCB" style="display:none"> Enter Modem Serial No: <input type="text" value="" name="SLNoValue" id="SLNoValue"/></div>
                            <div id="apnCB" style="display:none"> Enter Server IP: <input type="text" value="122.175.53.31" name="serverIPD"/> &nbsp;&nbsp;&nbsp;Enter APN Name: <input type="text" value="AIRTELGPRS.COM" name="apnNameD"/></div>
                            <!--<div id="apnSCB" style="display:none">Server IP: <input type="text" value="122.175.53.31" name="serverIPS" id="serverIPS"/> APN Name: <input type="text" value="jkapdrp.com" name="apnNameS" id="apnNameS"/> Password: <input type="text" value="111" name="apnPass" id="apnPass"/></div>-->
                            <div id="apnSCB" style="display:none">Server IP: <input type="text" value="122.175.53.31" name="serverIPS" id="serverIPS"/> APN Name: <input type="text" value="AIRTELGPRS.COM" name="apnNameS" id="apnNameS"/> <br>Alerts Port: <input type="text" value="" name="apnPass" id="apnPass"/>Data Port: <input type="text" value="" name="dataPort" id="dataPort"/></div>
                            <div id="flgtCB" style="display:none"> Read Time: <input type="text" value="0100" name="readTime" id="readTime"/> 
                                                                   Transmit Time: <input type="text" value="" name="transmitTime" id="transmitTime"/>
                            </div>
                            <div id="flgiCB" style="display:none"> Interval Period: <input type="text" value="" name="instStatus" id="instStatus"/> DiscomName: <input type="text" value="" name="discomName" id="discomName"/> Load Days: <input type="text" value="" name="loadDays" id="loadDays"/></div>
                            <div id="instCB" style="display:none"> Instant Frequency: <input type="text" value="15" name="instFreq" id="instFreq"/></div>
                            <div id="phimCB" style="display:none"> Phase Imbalance Reference:<input type="text" value="30" name="phimBal" id="phimBal"/></div>
                            <div id="volCB" style="display:none"> Voltage Reference:<input type="text" value="120" name="volRef" id="volRef"/></div>
                            <div id="pfCB" style="display:none"> Power Factor Reference:<input type="text" value="80" name="pfRef" id="pfRef"/></div>
                            <div id="tempCB" style="display:none"> Over Temperature Reference:<input type="text" value="50" name="tempRef" id="tempRef"/></div>
                            <div id="normCB" style="display:none"> Normal Temperature Reference:<input type="text" value="48" name="normRef" id="normRef"/></div>
                            <div id="do1CB" style="display:none"> 
                                Digital Output 1:<select name="do_1" id="do_1">
                                    <option value="ON">ON</option>
                                    <option value="OFF">OFF</option>
                                </select>
                            </div>
                            <div id="do2CB" style="display:none">
                                Digital Output 2:<select name="do_2" id="do_2">
                                    <option value="ON">ON</option>
                                    <option value="OFF">OFF</option>
                                </select>
                            </div>
                            <div id="cnctCB" style="display:none">
                                <table><tr>
                                        <td> <select name="phase" id="phase">
                                    <option value="-1">-select-</option>
                                    <option value="0">R-phase</option>
                                    <option value="1">Y-phase</option>
                                    <option value="2">B-phase</option>
                                    <option value="3">-All-</option>
                                </select>
                                        </td><td>
                                <select name="action" id="action">
                                    <option value="-1">-select-</option>
                                    <option value="1">Connect</option>
                                    <option value="0">Disconnect</option>
                                    
                                </select>
                                        </td></tr></table>
                            </div>                            
                        </td>  
                        <td colspan="4" id="metersPart" style="padding-left: 10px;">
                            <input type="button" value="Send Command" onclick="sendSMSToGateway();" class="myButton"/>
                        </td>
                    </tr>                         
                </table>
            </form>
        </div>
    
					<div id="cont-2-1"></div>	
						
						
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

