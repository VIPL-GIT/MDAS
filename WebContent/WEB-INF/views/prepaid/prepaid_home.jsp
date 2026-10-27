<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.text.*" %>
<head>
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
.table >thead > tr > th {
white-space: nowrap;
}
.control-label{
width:100%;
text-align:left !important;
}
.form-control{
font-size:11px;
padding :0px !important;
height:20px !important;
}
    .button_style
    {
        margin-top:1px;
        margin-right:1px;
        border-radius: 5px 5px 0px 0px;
        background-color: #D4DAE4;
        border:1px solid #DDDDDD;
        color:#000;
        padding:3px 15px;
        text-decoration:none;
        float:left;
    }
    .tab-box {
        border-bottom: 1px solid #DDD;
        padding-bottom:5px;
        padding-top: 5px;
        margin-left: 4px;
    }
    .tab-box a {
        border:1px solid #DDD;
        color:#fff;
        font-size:14px;
        padding: 5px 15px;
        text-decoration:none;
        background-color: #4d9ca2;
/*         margin-left: -4px; */
        border-radius: 5px 5px 0px 0px;
    }
    .tab-box a.activeLink {
        background-color: #FFF;
        border-bottom: 0;
        padding: 5px 15px;
        color:#FB9337;
    }
    .tabcontent { 
        border: 0px solid #ddd;
        border-top: 0;
    }
    .hide { display: none;}

</style>

<script>
$(document).ready(function() {
    $(".tabLink").each(function(){
        $(this).click(function(){
            tabeId = $(this).attr('id');
            $(".tabLink").removeClass("activeLink");
            $(this).addClass("activeLink");
            $(".tabcontent").addClass("hide");
            $("#"+tabeId+"-1").removeClass("hide")

if(tabeId=="cont-1"){
	$("#prepaidContent").empty();
}
if(tabeId=="cont-2"){
	$("#prepaidContent").empty();
            }
if(tabeId=="cont-3"){
	$("#prepaidContent").empty();
}
if(tabeId=="cont-4"){
	$("#prepaidContent").empty();
	getSlabRates();
}
if(tabeId=="cont-5"){
	$("#prepaidContent").empty();
}
if(tabeId=="cont-6"){
	$("#prepaidContent").empty();
}
                
        });
    });
    
    getHierarchyFor360('${userHierarchyid }');
    $('#childID').chosen();
    getInstantPush();
    
});


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
             <li class="active">Prepaid</li>
         </ul>
     </div>
     
      
      
      
      <div class="row" style="padding-top: 0px">
                    <div class="col-md-12">
                        <div class="panel panel-bd">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Prepaid</h4> 
                       
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
       <c:forEach items="${meters}" var="meter" varStatus="loop">
         <option value="${meter.meter_id }" class="ip_${meter.meterlocation }" ${loop.index==0?"selected":""}>${meter.meter_sl_no }</option>
        </c:forEach>
        </c:if>                                      
</select>
</div>
       </div>                                                                                                                  
    <input type="hidden" id="meter" value=""/>                                                                                  


 
</div>
</div>

<!-- --------------------------------------------------------------------------------------  -->

<div class="tab-box" id="example">
    <a style="margin-right: 4px;" href="javascript:;" class="tabLink activeLink " id="cont-1">Instant Push </a>
    <a href="javascript:;" class="tabLink " id="cont-2">Alerts Push</a>
    <a href="javascript:;" class="tabLink " id="cont-3">Monthly Transactions</a>
    <a href="javascript:;" class="tabLink " id="cont-6">Connect/Disconnect</a> 
    <a href="javascript:;" class="tabLink " id="cont-4">Slab Rates </a>
    <a href="javascript:;" class="tabLink " id="cont-5">Re-charge/Renewal</a>
    
   
</div>
<%
String fdate="",tdate="";
int mnyear=0,mxyear=0;
try{
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	 tdate=sdf.format(new java.util.Date());
	 fdate=sdf.format(new java.util.Date().getTime()-7*24*60*60*1000);
	 mxyear=Integer.parseInt(fdate.split("-")[0]);
	 mnyear=mxyear-5;
}catch(Exception e){
	
}
%>
<div class="tabcontent paddingAll" id="cont-1-1">

    <table>
        <tr>
            <td id="ictd">
                <b>From Date:</b>
                <input type="date"  size="10" name="startingDate" id="isDate" value="<%=fdate%>"/>
                
            </td>
            <td id="ictd" >
                <b>To Date:</b>
                <input type="date"  size="10" name="endingDate" id="ieDate" value="<%=tdate%>"/>
                </td>
            <td>
                <input type="button" size="30" id="" value="Get Data" onclick="getInstantPush()">
            </td>
        </tr>
    </table>

</div>
<div class="tabcontent paddingAll hide" id="cont-2-1">
<table>
        <tr>
            <td id="ictd">
                <b>From Date:</b>
                <input type="date"  size="10" name="startingDate" id="asDate" value="<%=fdate%>"/>
                
            </td>
            <td id="ictd" >
                <b>To Date:</b>
                <input type="date"  size="10" name="endingDate" id="aeDate" value="<%=tdate%>"/>
                </td>
            <td>
                <input type="button" size="30" id="" value="Get Data" onclick="getAlertsPush()">
            </td>
        </tr>
    </table>
</div>
<div class="tabcontent paddingAll hide" id="cont-3-1">
<table>
 <tr>
            <td id="ictd">
                <b>Select Year:</b>
<%--                 <input type="text"  size="10" name="startingDate" id="tmonth" /> --%>
                <input type="number" id="tmonth" min="<%=mnyear %>" max="<%=mxyear %>" step="1" value="<%=mxyear %>" />              
            </td>           
            <td>
                <input type="button" size="30" id="" value="Get Data" onclick="getMonthlyTransasctions()">
            </td>
  </tr>
</table>
</div>
<div class="tabcontent paddingAll hide" id="cont-4-1"></div>
<div class="tabcontent paddingAll hide" id="cont-5-1">
 <table>
 <tr>     
<td>Amount</td>
<td><input type="text"  class="edate" id="amount" style="width:110px !important;" ></td>
<td><input type="button" class="myButton" value="Renewal" onclick="payAmount()" style=""></td>
</tr>
      </table>
</div>
<div class="tabcontent paddingAll hide" id="cont-6-1">
<table>
        <tr>
            <td id="ictd">
                <b>From Date:</b>
                <input type="date"  size="10" name="startingDate" id="cdsDate" value="<%=fdate%>"/>
                
            </td>
            <td id="ictd" >
                <b>To Date:</b>
                <input type="date"  size="10" name="endingDate" id="cdeDate" value="<%=tdate%>"/>
                </td>
            <td>
                <input type="button" size="30" id="" value="Get Data" onclick="getcdHistory()">
            </td>
        </tr>
    </table>
</div>
<!-- --------------------------------------------------------------------------------------  -->
<div id="prepaidContent"></div>
<div id="loading" style="display: none">
	<table width="100%">
		<tr>
			<td align="center"><img src="images/loaders/139.gif"
				style="padding-top: 150px;" /></td>
		</tr>
	</table>
</div>
