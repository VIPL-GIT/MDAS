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



function changeEvent(){
	$('.chosen-container').hide();
}
</script>

<script>

function getBillingSummary(){
	
        document.getElementById("viewmodemalert").innerHTML =""; 
 
        var selectedMonth = document.getElementById("month").value;
        var type=document.getElementById("install_type").value;
        var cat = document.getElementById("install_type").value;
      
 /* var circleId = document.getElementById("level_1").value;     
    var level_1 = document.getElementById("level_1");
    var level_2 = document.getElementById("level_2");
    var level_3 = document.getElementById("level_3");
    var level_4 = document.getElementById("level_4");
    var level_5 = document.getElementById("level_5");
    var level_6 = document.getElementById("level_6");
    var level_7 = document.getElementById("level_7");
    var level_8 = document.getElementById("level_8");
    var discomVal = level_1.options[level_1.selectedIndex].value;
    var zoneVal = level_2.options[level_2.selectedIndex].value;
    var circleVal = level_3.options[level_3.selectedIndex].value;
    var divisionVal = level_4.options[level_4.selectedIndex].value;
    var sdVal = level_5.options[level_5.selectedIndex].value;
    var mandalVal = level_6.options[level_6.selectedIndex].value;
    var sectionVal = level_7.options[level_7.selectedIndex].value;
    
*/
                jQuery.ajax({
                    //url: "EnergyReadings/AllResetBillings.jsp?circleId="+circleId+"&selectedMonth="+selectedMonth+"&type="+type,
                      url : "AllResetBillings.jsp?meterLoc=" + escape(cat)+"&selectedMonth="+selectedMonth,
                    dataType: 'html',
                    beforeSend:function(){
                    	 jQuery("#viewmodemalert").html(jQuery("#loading").html());
                    },
                    success: function (data) {
                        jQuery("#viewmodemalert").html(data);
                        callDataTableWithName('example','All_Biling_params');      
                    }
                });
 }
            
function getBOptions(value){
	if(value=="bsummary"){
		$("#viewmodemalert").empty();
		document.getElementById("pbody").innerHTML=document.getElementById("bill_summary").innerHTML;
		$('#month').MonthPicker({SelectedMonth: 0, Button: false });
	}if(value=="buploadfile"){
		$("#viewmodemalert").empty();
		document.getElementById("pbody").innerHTML=document.getElementById("bill_upload").innerHTML;
	}
	if(value=="bdownloadfile"){
		$("#viewmodemalert").empty();
		$("#pbody").empty();		
		getBalanceMeters();
	}
	if(value=="istatus"){
		$("#viewmodemalert").empty();
		$("#pbody").empty();		
		getIStatus();
	}
}
function getBalanceMeters(){
	var balance_type=$("#balance_type").val();
	$.ajax({
	   	 url : 'MeterData/downloadBillData.epx?balance_type='+balance_type,	       
	       type : 'POST',
	       beforeSend:function(){
          	 jQuery("#viewmodemalert").html(jQuery("#loading").html());
          },
	       success : function(data) {	         
	           $("#viewmodemalert").html(data);
	           callDataTableWithName('table1','Billing_meters');
	       },
	       error : function(err) {	           
	           $("#viewmodemalert").html(err);
	       }
	   });
}
function getIStatus(){
	$.ajax({
	   	 url : 'MeterData/getBillingIStatus.epx',	       
	      // type : 'POST',
	       beforeSend:function(){
         	 jQuery("#viewmodemalert").html(jQuery("#loading").html());
         },
	       success : function(data) {	         
	           $("#viewmodemalert").html(data);
	           callDataTableWithName('table1','Billing_meters');
	       },
	       error : function(err) {	           
	           $("#viewmodemalert").html(err);
	       }
	   });
}
function saveMedia() {

	 $("#result1").empty();
	 $("#result1").empty();
	 
   var formData = new FormData();
   formData.append('file', $('input[type=file]')[0].files[0]);

   $.ajax({
   	 url : 'MeterData/uploadBillData.epx',
       data : formData,
       processData : false,
       contentType : false,
       type : 'POST',
       success : function(data) {
         //  alert(data);
           $("#result1").append(data);
           callDataTableWithName('table1','Billing_meters');
          // $('#p').css({'background-color':'black'});​
       },
       error : function(err) {
           
           $("#result2").append(err);
       }
   }); 
}
function processMedia() {

	 $("#result1").empty();
	 $("#result1").empty();
	 
  var formData = new FormData();
  formData.append('file', $('input[type=file]')[0].files[0]);

  $.ajax({
  	 url : 'MeterData/processBillData.epx',
      data : formData,
      processData : false,
      contentType : false,
      type : 'POST',
      success : function(data) {
        //  alert(data);
          $("#result1").append(data);
         // $('#p').css({'background-color':'black'});​
      },
      error : function(err) {
          
          $("#result2").append(err);
      }
  }); 
}

$(document).ready(function() {
	 // getHierarchyFor363('${titles[fn:length(titles)-1].hierarchy_type_id }');
	 getBOptions("istatus");
	 //getBillingSummary();
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
             <li class="active">Billing Summary</li>
         </ul>
     </div>
     
      
      
      
      <div class="row" style="padding-top: 0px">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <div class="form-group no-margin" style="margin:0px;">
									
											<input type="radio" id="bsummary" name="nwtype"  value="bsummary" onclick="getBOptions(this.value)">
											<h5 class="nwh5">Billing Summary</h5>
											
											<input type="radio" id="bdownloadfile" name="nwtype" value="bdownloadfile" onclick="getBOptions(this.value)">
											<h5 class="nwh5">Download Billing data</h5>
											
											<input type="radio" id="buploadfile" name="nwtype" value="buploadfile" onclick="getBOptions(this.value)">
											<h5 class="nwh5">Upload Billing data</h5>
											
											<input type="radio" id="buploadfile" name="nwtype" checked="checked" value="istatus" onclick="getBOptions(this.value)">
											<h5 class="nwh5">Integration Status</h5>
											
									
                            </div> 
                                
                    	                              
                                    </div>                          
                            </div></div></div></div>
      
      
      
      
     
     <!-- END Breadcrumb -->
<div class="row table-responsive" style="min-height:30px;padding-top: -10px">
<div class="com-md-12">
<div class="panel-body" > 
      <div id="pbody"></div>
    
                        <div id='viewmodemalert' style="float: left; width: 100%"></div>
						<div id="loading" style="display: none;" class="loading part">
							<img src="images/loader.gif"
								style="padding-top: 10%; padding-left: 30%;" />
						</div>
                                                                             
</div>

</div>
</div>

<div id="bill_summary" style="display:none;">
<div class="col-md-3">
       <label class="">Select Month</label>    
       <input type="text" id="month" style="width:100px;" autocomplete="off"/>
      </div>
      
      
      
      
      <div class="col-md-3">
                        <label class="">Installation Point</label>
							<select id="install_type" onchange="changeEvent()" data-placeholder="Choose a Category"
								tabindex="1">
								<option value="0">-All-</option>
								<c:forEach items="${installpoints }" var="type"> 
								  <option value="${type.id }">${type.meter_type }</option>
								</c:forEach>
							</select>
						</div>	
      
      
      <div class="col-md-2">
      <input type="button" value="Get Report"  onclick="getBillingSummary()" class="myButton" />
      </div>
</div>
<div id="bill_upload" style="display:none;">
<div class="col-md-3">
<input type="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" name="file" />
 </div>   
 <div class="col-md-2"><button onclick="saveMedia();">Upload</button></div>
 <div class="col-md-2"><button onclick="processMedia();">Process</button></div>
 
 <div class="Row">
    
    <div class="Column"><p style="font: bold;" id="result1"></p></div>
    <div class="Column"><p style="font: bold;" id="result2"></p></div>
</div>
</div>
<div id="bill_download" style="display:none;">
<div class="col-md-3">
   <label class="">Select</label>
	<select id="balance_type"  data-placeholder="Choose a Category"
		tabindex="1">
		<option value="0">-All-</option>
		<option value="success">Integrated Meters</option>
		<option value="ready">Ready to Integrate Meters</option>
		<option value="fail">Integration failed Meters</option>
	</select>
</div>
  <div class="col-md-2">
  <input type="button" value="Download"  onclick="getBalanceMeters()" class="myButton" />
  </div>
</div>



						
					
						
				
				
				
				
				

