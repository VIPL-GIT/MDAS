<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.*" %>
<%@page import="utils.DBUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
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
#div1 {
  width: 350px;
  height: 70px;
  padding: 10px;
  border: 1px solid #aaaaaa;
}

input[type='file'] {
    border: 3px dashed #999;
    padding: 140px 50px 10px 50px;
    cursor: move;
    position:relative;
}
input[type='file']:before {
    content: "drag & drop your files here";
    display: block;
    position: absolute;
    text-align: center;
    top: 50%;
    left: 50%;
    width: 200px;
    height: 40px;
    margin: -25px 0 0 -100px;
    font-size: 18px;
    font-weight: bold;
    color: #999;
}
 #result1 {
  color: green;
  background-color:white;
  text-indent: 20px;
  text-transform: uppercase;
}

#result2 {
  color: red;
   background-color:white;
  text-indent: 20px;
  text-transform: uppercase;
} 

.Row {
   
    width: 100%; /*Optional*/
    table-layout: fixed; /*Optional*/
    border-spacing: 10px; /*Optional*/
}
.Column {
    display: table-cell;
   
}

</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script>
function allowDrop(ev) {
  ev.preventDefault();
}

function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
  ev.preventDefault();
  var data = ev.dataTransfer.getData("text");
  ev.target.appendChild(document.getElementById(data));
}



function saveMedia() {


	 $("#result1").empty();
	 $("#result1").empty();
	 
    var formData = new FormData();
    formData.append('file', $('input[type=file]')[0].files[0]);


  /*   alert($('input[type=file]')[0].files[0]); */
    
   console.log("form data " + formData);
    $.ajax({
    	 url : 'Downloads/proceed.epx',
        data : formData,
        processData : false,
        contentType : false,
        type : 'POST',
        success : function(data) {
            alert(data);
            $("#result1").append(data);
           // $('#p').css({'background-color':'black'});?
        },
        error : function(err) {
            alert(err);
            $("#result2").append(err);
        }
    }); 
}


</script>
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
             <li class="active">Upload Raw data Files</li>
         </ul>
     </div>
     
      
      
      
      <div class="row" style="padding-top: 0px">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Upload Raw data Files</h4> 
                                
                            </div>                          
                            </div></div></div></div>
      
      
     
      
     
     <!-- END Breadcrumb -->

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
				
				
			 <input type="file" id="fileUpload" name="file" />


<hr />


 
 <div class="Row">
   <!--  <div class="Column"><input type="button" id="upload" value="upload"  onclick="view();"/></div> -->
     <div class="Column"><input type="button" id="proceed" value="Upload"  onclick="saveMedia();"/></div>
  <!--   <div class="Column"><button id="proceed" onclick="saveMedia();" style="display: none;">proceed</button></div> -->
    <div class="Column"><p style="font: bold;" id="result1"></p></div>
    <div class="Column"><p style="font: bold;" id="result2"></p></div>
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




						
					
						
				
				
				
				
				

