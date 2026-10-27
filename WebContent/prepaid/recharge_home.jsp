<%@page import="java.util.*"%>
<%--Reports Home Pointer--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>MDAS - Communication Summary</title>
        
        
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
        margin-left: 5px;
        margin-top:7px;
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
    }
    .tabcontent { border: 0px solid #ddd; border-top: 0;}
    .hide { display: none;}
       
    .myButton {
	-moz-box-shadow:inset 0px 1px 0px 0px #ffffff;
	-webkit-box-shadow:inset 0px 1px 0px 0px #ffffff;
	box-shadow:inset 0px 1px 0px 0px #ffffff;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f9f9f9), color-stop(1, #e9e9e9));
	background:-moz-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background:-webkit-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background:-o-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background:-ms-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background:linear-gradient(to bottom, #f9f9f9 5%, #e9e9e9 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#f9f9f9', endColorstr='#e9e9e9',GradientType=0);
	background-color:#f9f9f9;
	-moz-border-radius:6px;
	-webkit-border-radius:6px;
	border-radius:6px;
	border:1px solid #dcdcdc;
	display:inline-block;
	cursor:pointer;
	color:#666666;
	margin-left: 10px;
	font-size:13px;
	font-weight:bold;
	padding:3px 19px;
        
	text-decoration:none;
	text-shadow:0px 1px 0px #ffffff;
}
.myButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #e9e9e9), color-stop(1, #f9f9f9));
	background:-moz-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background:-webkit-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background:-o-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background:-ms-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background:linear-gradient(to bottom, #e9e9e9 5%, #f9f9f9 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#e9e9e9', endColorstr='#f9f9f9',GradientType=0);
	background-color:#e9e9e9;
}
.myButton:active {
	position:relative;
	top:1px;
}

.userinput
{
    border: 1px solid transparent;
    align-content: center;
}
/*th { font-size: 12px; }
td { font-size: 11px; }
/*table {table-layout:fixed}*/
/*td {overflow:hidden; white-space:nowrap}*/

.table > thead > tr > th,.table > tbody > tr > td {
     white-space:nowrap;
}
fieldset{
border:1px solid silver !important;
}
legend {
    padding: 0px 10px;
    border: 1px solid silver;
    vertical-align: center;
    text-align: center;
    width: auto;
    background: #eceff1;
    margin-left:20px;
    text-align:left;
    font-size: 14px;
    font-family: 'Alegreya Sans', sans-serif;
}
</style>    


    </head>
   
    <body onload="getRechargePage()">
                                
                   
                    
                   
<!--<div class="tab-box" id="example">
    <a href="javascript:;" class="tabLink activeLink " id="cont-1">Recharge </a>    
    <a href="javascript:;" class="tabLink " id="cont-2">Data view</a>
    
</div> -->
<div class="tabcontent paddingAll" id="cont-1-1" >
    
</div>
<div class="tabcontent paddingAll hide" id="cont-2-1">
    
</div>
<div class="tabcontent paddingAll hide" id="cont-3-1">
    
</div>                    
        <div id="prepaidContent">
            
              
        </div>
      
        
        

    </body>
    <script src="./js/prepaid.js"></script>
</html>

