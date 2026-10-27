<%@page import="java.util.*"%>
<%--Reports Home Pointer--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>MDAS - Meter Data Reports</title>
        <link rel="icon" type="image/png" href="images/favicon_1.png"/>
        <link rel="stylesheet" href="css/table.css" type="text/css" />
        <link rel="stylesheet" href="css/style.default.css" type="text/css" />
        <script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
        <script type="text/javascript" src="js/plugins/jquery-ui-1.12.1.custom.min.js"></script>
        <script type="text/javascript" src="js/plugins/colorpicker.js"></script>
        <script type="text/javascript" src="js/custom/elements.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/custom/tables.js"></script>    
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="js/login.js"></script>        
        <!--<script type="text/javascript" src="js/jquery-3.3.1.js"></script>-->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css">
        <script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.flot.min.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.flot.resize.min.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.slimscroll.js"></script>
        <script type="text/javascript" src="js/custom/dashboard.js"></script>
        <script type="text/javascript" src="js/custom/forms.js"></script>
        <script type="text/javascript" src="js/newmasterdata.js"></script>
        <script type="text/javascript" src="js/admin_hirarchy.js"></script>    
        <script type="text/javascript" src="js/date-picker.js"></script>
        <script type="text/javascript" src="js/load_survay_graphs.js"></script>
        <script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
        <script type="text/javascript" src="js/plugins/jquery-ui-1.12.1.custom.min.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
        <script type="text/javascript" src="js/custom/general.js"></script>
        <script type="text/javascript" src="js/custom/tables.js"></script>
        <link rel="stylesheet" href="css/style.default.css" type="text/css" />
        <script type="text/javascript" src="js/table2excel/jquery.table2excel.js"></script> 
        <script>
     $('#table1 tbody').on('click', 'td.phasor', function () {
    	    	
        var tr = $(this).parents('tr');
        var row =  $('#table1').DataTable().row(tr);
          alert("in ----------");
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
        
        	$.ajax( {
                url:'OutageSummaryBreakup?meter='+tr.find("td").eq(1).html()+'&date='+$("#outageDate").val(),
                //cache:false,
                success:function(resp, status, xhr) {  
                	 row.child($(
                             '<tr>' +
                             '<td colspan="7">'+resp+'</td>' +
                         '</tr>'
                     )).show();
                     tr.addClass('shown');
                     
                }
             });
        }
    } );
</script>
         
         
        <script>
                jQuery(".complete").on("click", function () {
                jQuery(".data_compledisplay").addClass("blocker");
                });
                jQuery(".latest").on("click", function () {
                jQuery(".data_compledisplay").removeClass("blocker");
                });
                 jQuery(".graphs").on("click", function () {
                 jQuery(".voltage").addClass("blocker");
             });
        </script>
        <style>
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
        
    </head>
   
    <!--<body onload='newShowZones(document.getElementById("discom").value)'>-->
    <body>
         <div class="bodywrapper">                         
                   
               
               
               
               
<div class="centercontent tables mart10">
    <div class="pageheader notab" id="pageheader">
        <ul class="hornav">
         
            <li id="kumar"><a href="javascript:getReportsInstantData('41853')">Instantaneous</a></li>
         <!--     <li id="kumar"><a href="javascript:getInstantTabs()">Instantaneous</a></li>--> 
            
            <li id="kumar1"><a href="javascript:getEnergyOptions()">Energy</a></li>
            <li id="kumar2"><a href="javascript:getLoadPage()">Load Survey</a></li>
            <li id="kumar3"><a href="javascript:getEventsPage()">Tampers </a></li>
            <li id="kumar4"><a href="javascript:getSnapshotPage()">Snapshot Data</a></li>
            
           
            
            <!--<li id="kumar5"><a href="javascript:getOutageReportPage()">Outage Report</a></li>-->
            
        </ul>
    </div><!--pageheader-->

    <div id="contentwrapper" class="contentwrapper">
        <div class="tabcontent paddingAll" id="cont-1-1" style="display:none">
        
        
            <!-- <div class="button_style"> <input type="radio" name="instant" id="instant1" value="1" checked  onclick="getInstantData2(this.value)"> Latest Data &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div> -->
            
            <div class="button_style"> <input type="radio" name="instant" id="instant1" value="1" checked  onclick="getInstantData2(this.value)"> Latest Data &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
            
            
            <div class="button_style"><input type="radio" name="instant" id="instant2" value="2" onclick="getDates()"> Complete Data </div> 
            <div class="button_style"><input type="radio" name="instant" id="instant3" value="3" onclick="getInstantOptions()"> Graphs</div>
            <div class="button_style"><input type="radio" name="instant" id="instant4" value="4" onclick="getInstantAllParams()">All Params</div>
            <table>
                <tr>
                    <td>
                        <span class="selectamf" ><input type="checkbox" id="AEMF" checked= false >Apply EMF</span>
                    </td>

                    <td>
                        <form name="graphicalReports">
                            <table>
                                <tr>
                                    <td id="gdate" style="display:none;margin-top: -9px;" >
                                        <b>Select Date:</b>
                                        <input type="text" readonly="true" size="10" name="startingDate" id="graphDate"/>
                                        <a href="javascript:show_calendar('graphicalReports.startingDate')"> <img style="padding-top:8px;"src="images/cal1.gif" id="sdate1"  width=24 height=22 border=0/></a>
                                        &nbsp;
                                    </td>
                                </tr>
                            </table>
                        </form> 
                        <div id="date_div" style="float:left;clear:both;display:none">
                            <form name="iReports">
                                <table>
                                    <tr>
                                        <td id="ictd" style="display:none" >
                                            <!-- <b>From Date:</b>-->
                                            <input type="text" readonly="true" size="10" name="startingDate" id="sDate" placeholder="From Date"/>
                                            <a href="javascript:show_calendar('iReports.startingDate')"> <img src="images/cal1.gif" id="sdate"  width=24 height=22 border=0/></a>
                                            &nbsp;
                                        </td>
                                        <td id="ictd" style="display:none">
                                            <!-- <b>To Date:</b>-->
                                            <input type="text" readonly="true" size="10" name="endingDate" id="eDate" placeholder="To Date"/>
                                            <a href="javascript:show_calendar('iReports.endingDate')"> <img src="images/cal1.gif" id="edate"  width=24 height=22 border=0/></a>
                                        </td>
                                        <td>
                                            <input type="month" id="month" name="Month Picker"  > 
                                        </td>
                                        <td>
                                            <%--<input type="button" size="30" id="" value="Get Complete Data" onclick="getInstantData2(2)" class="color_button"> --%>
                                            <input type="button" size="30" id="" value="Get Complete Data" onclick="getcompleteData()" class="myButton">
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>            
                    </td>
                </tr>
            </table>

            <div id="ip_opt_div1" style="float:left;clear:both;display:none">
                <div class="button_style"><input type="radio" name="instant_1"  value="1" onclick="getVANDCGraphs(this.value)"> Voltage Graph</div>
                <div class="button_style"><input type="radio" name="instant_1"  value="2" onclick="getVANDCGraphs(this.value)"> Current Graph</div>
            </div>
        </div>

        <div class="tabcontent paddingAll hide" id="cont-2-1">
        </div>

        <div class="tabcontent paddingAll hide" id="cont-3-1-1">           
        </div>
        <div class="tabcontent paddingAll hide" id="cont-3-1">           
        </div>

        <div class="tabcontent paddingAll hide" id="cont-4-1">
        </div>
        
        <div class="tabcontent paddingAll hide" id="cont-5-1">
        </div>
        
        <div class="tabcontent paddingAll hide" id="cont-6-1" >
            <span style="display: none;" id="outelements">
                <p style="font-weight: bold"> Select Date :   <input type="date" id="outdate" class="outdate" placeholder="">
                                            <input type="button" size="30" id="" value="Get Outage Info" onclick="getOutageInfo()" class="myButton">
                </p>
            </span>
        </div>

        <div class="scrollale">                                                                                                                                        
            <div id='DataPart' style="float:left;width:100%">
                    
            </div>   
            <div id="loading" style="display: none; " class="loading part">
                    <img src="images/loaders/139.gif" style="padding-top: 10%;padding-left:10%; " />
            </div>
        
        </div>
    </div><!-- centercontent -->
</div><!--bodywrapper-->
  

 
       
        </div><!--bodywrapper-->
 
    <script src ="js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css">
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.flash.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js"></script>
        
<!--<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>-->
<script src="js/highcharts/highcharts.js"></script>
<script src="js/highcharts/highcharts-more.js"></script>        
 
        <script type="text/javascript">

 function implement()
 {
       alert("in implement");
        $('#instantdatatable').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ]
    } );
 }
 
 

 
  function implement1()
 {
       //alert("in load datatable");
                $('#loaddatatable').DataTable
                ( 
                    {
                                dom: 'Blfrtip',
                                 "bSort": false,
                                //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                                //buttons: ['csv', 'excel', 'print'],
                                        
                                buttons: 
                                [
                                    {
                                        extend: 'copy',
                                        title: 'Load Survey Data'
                                    },
                                    
                                    {
                                        extend: 'excel',
                                        title: 'Load Survey Data'
                                    },                                
                                    {
                                        extend: 'csv',
                                        title: 'Load Survey Data'
                                    },
                                    {
                                        extend: 'pdf',
                                        title: 'Load Survey Data',
                                        orientation: 'landscape'
                                    }
                                ]


                    } 
                );
 }
  
  
  
  
  
   function implement2()
 {
      // alert("in energy datatable");
                $('#energydatatable').DataTable
                ( 
                    {
                                dom: 'Blfrtip',
                                //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                                //buttons: ['csv', 'excel', 'print'],
                                 "bSort": false,        
                                buttons: 
                                [
                                    {
                                        extend: 'copy',
                                        title: 'Energy Data'
                                    },
                                    
                                    {
                                        extend: 'excel',
                                        title: 'Energy Data'
                                    },
                                
                                    {
                                        extend: 'csv',
                                        title: 'Energy Data'
                                    },
                                    {
                                        extend: 'pdf',
                                        title: 'Energy Data',
                                        orientation: 'landscape'
                                    }
                                ]


                    } 
                );
 }
 function implement3()
 {
      // alert("in energy datatable");
                $('#tamperdatatable').DataTable
                ( 
                    {
                                dom: 'Blfrtip',
                                //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                                //buttons: ['csv', 'excel', 'print'],
                                 "bSort": false,       
                                buttons: 
                                [
                                    {
                                        extend: 'copy',
                                        title: 'Tamper Data'
                                    },
                                    
                                    {
                                        extend: 'excel',
                                        title: 'Tamper Data'
                                    },                                
                                    {
                                        extend: 'csv',
                                        title: 'Tamper Data'
                                    },
                                    {
                                        extend: 'pdf',
                                        title: 'Tamper Data',
                                        orientation: 'landscape'
                                    }
                                ]


                    } 
                );
 }
function implement4()
 {
      // alert("in energy datatable");
                $('#completedatatable').DataTable
                ( 
                    {
                                dom: 'Blfrtip',
                                //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                                //buttons: ['csv', 'excel', 'print'],
//                                 "scrollX": true,
                                 "bSort": false,
                                buttons: 
                                [
                                    {
                                        extend: 'copy',
                                        title: 'Complete Data'
                                    },
                                    
                                    {
                                        extend: 'excel',
                                        title: 'Complete Data'
                                    },
                                
                                    {
                                        extend: 'csv',
                                        title: 'Complete Data'
                                    },
                                    {
                                        extend: 'pdf',
                                        title: 'Complete Data',
                                        orientation: 'landscape'
                                    }
                                ]


                    } 
                );
 }
  function implement5()
 {
      // alert("in energy datatable");
                $('#outagetable').DataTable
                ( 
                    {
                                dom: 'Blfrtip',
                                //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                                //buttons: ['csv', 'excel', 'print'],
                                        
                                buttons: 
                                [
                                    {
                                        extend: 'copy',
                                        title: 'Outage Data'
                                    },
                                    
                                    {
                                        extend: 'excel',
                                        title: 'Outage Data'
                                    },                                
                                    {
                                        extend: 'csv',
                                        title: 'Outage Data'
                                    },
                                    {
                                        extend: 'pdf',
                                        title: 'Outage Data',
                                        orientation: 'landscape'
                                    }
                                ]
                    } 
                );
 }
        </script>
        
    
    </body>
</html>

 <div id="dcu_loading" style="display: none">
    <table width="100%">
        <tr>
            <td align="center">
                <img src="images/loaders/139.gif" style="padding-top:150px;"/>
            </td>
        </tr>
    </table>
</div>
