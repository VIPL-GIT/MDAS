<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
            	alert("Tab 1 ");
            }
if(tabeId=="cont-2"){
	alert("Tab 2 ");
            }
if(tabeId=="cont-3"){
	alert("Tab 3 ");
}
if(tabeId=="cont-4"){
	alert("Tab 4 ");
}
if(tabeId=="cont-5"){
	alert("Tab 5 ");
}
                
        });
    });
    
    getHierarchyFor360('${userHierarchyid }');
    $('#childID').chosen();    
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

<!-- --------------------------------------------------------------------------------------  -->

<div class="tab-box" id="example">
    <a style="margin-right: 4px;" href="javascript:;" class="tabLink activeLink " id="cont-1">Instant Push </a>
    <a href="javascript:;" class="tabLink " id="cont-2">Alerts Push</a>
    <a href="javascript:;" class="tabLink " id="cont-3">Monthly Transactions</a>
    <a href="javascript:;" class="tabLink " id="cont-4">Slab Rates </a>
    <a href="javascript:;" class="tabLink " id="cont-5">Re-charge/Renewal</a> 
   
</div>
<div class="tabcontent paddingAll" id="cont-1-1"></div>
<div class="tabcontent paddingAll hide" id="cont-2-1"></div>
<div class="tabcontent paddingAll hide" id="cont-3-1"></div>
<div class="tabcontent paddingAll hide" id="cont-4-1"></div>
<div class="tabcontent paddingAll hide" id="cont-5-1"></div>
<!-- --------------------------------------------------------------------------------------  -->
<div id="dcu_loading" style="display: none">
	<table width="100%">
		<tr>
			<td align="center"><img src="images/loaders/139.gif"
				style="padding-top: 150px;" /></td>
		</tr>
	</table>
</div>
