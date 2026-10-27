<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="stylesheet" href="css/table.css" type="text/css" />
<!-- <link rel="stylesheet" href="css/style.default.css" type="text/css" /> -->
<script src="js/month/jquery-ui.min.js"></script>
<link href="js/month/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/month/MonthPicker.min.js"></script>
<link  rel="stylesheet" type="text/css" href="js/month/MonthPicker.min.css"/>



<style>
    .button_style
    {
        background-color:#EEEEEE;
        border:1px solid #DDDDDD;
        color:#666666;
        padding:3px 15px;
        text-decoration:none;
        float:left;
    }
    .tab-box {
        border-bottom: 1px solid #DDD;
        padding-bottom:5px;
        padding-top:5px;
    }
    .tab-box a {
        border:1px solid #DDD;
        color:#666666;
        padding: 5px 15px;
        text-decoration:none;
        background-color: #eee;
    }
    .tab-box a.activeLink {
        background-color: #fff;
        border-bottom: 0;
        padding: 6px 15px;
        margin-right:5px;
    }
    .tabcontent { border: 0px solid #ddd; border-top: 0;}
    .hide { display: none;}

</style>
<style>
    .line{ border: 0px solid #ddd; border-top: 0;}
</style>
<script type="text/javascript">
   

    $(document).ready(function() {
    	getAnalyticsHome('${meter}','billing');
        $(".tabLink").each(function(){
            $(this).click(function(){
                tabeId = $(this).attr('id');
                $(".tabLink").removeClass("activeLink");
                $(this).addClass("activeLink");
                $(".tabcontent").addClass("hide");
                $("#"+tabeId+"-1").removeClass("hide")
               
               
                if(tabeId=="cont-1")
                {
                   
                	getAnalyticsHome('${meter}','billing');
                }

                if(tabeId=="cont-2")
                {
                	$('#month').MonthPicker({SelectedMonth: 0, Button: false });
                	getAnalyticsHome('${meter}','dailyenergy');
                }
                if(tabeId=="cont-3")
                {
                	$('#reports_div').empty();
                }
                if(tabeId=="cont-4")
                {
                	
                	getAnalyticsHome('${meter}','poweronoff');
                }
                if(tabeId=="cont-5")
                {
                	$('#lmonth').MonthPicker({SelectedMonth: 0, Button: false });
                	getAnalyticsHome('${meter}','load');
                }
                
                return false;
            });
        });
    });
    
    
</script>

<div class="tab-box" id="example">
    <a href="javascript:;" class="tabLink activeLink " id="cont-1">Billing </a>
    <a href="javascript:;" class="tabLink " id="cont-2">Daily Energy</a>
    <a href="javascript:;" class="tabLink " id="cont-3">Events</a>
    <a href="javascript:;" class="tabLink " id="cont-4">Power ON/OFF</a>
    <a href="javascript:;" class="tabLink " id="cont-5">Load Profile</a>   
</div>

<div class="tabcontent paddingAll hide" id="cont-2-1">
<div class="col-md-3">
       <label class="">Select Month</label>    
       <input type="text" id="month" style="width:100px;" autocomplete="off"/>
      </div>      
      
      <div class="col-md-2">
      <input type="button" value="Get Energies"  onclick="getAnalyticsHome('${meter}','dailyenergy');" class="myButton" />
      </div>
</div>

<div class="tabcontent paddingAll hide" id="cont-3-1">
     <div class="col-md-3">
       <label class="">From</label>    
       <input type="date" id="fromDate" style="width:140px;" autocomplete="off"/>
      </div>
      <div class="col-md-3">
       <label class="">To</label>    
       <input type="date" id="toDate" style="width:140px;" autocomplete="off"/>
      </div>      
      
      <div class="col-md-2">
      <input type="button" value="Get Energies"  onclick="getAnalyticsHome('${meter}','events');" class="myButton" />
      </div>
</div>

<div class="tabcontent paddingAll hide" id="cont-5-1">
<div class="col-md-3">
       <label class="">Select Month</label>    
       <input type="text" id="lmonth" style="width:100px;" autocomplete="off"/>
      </div>      
      
      <div class="col-md-2">
      <input type="button" value="Get LoadProfile"  onclick="getAnalyticsHome('${meter}','load');" class="myButton" />
      </div>
</div>

<div class="col-md-12" id="reports_div" style="margin-top:5px;"></div>