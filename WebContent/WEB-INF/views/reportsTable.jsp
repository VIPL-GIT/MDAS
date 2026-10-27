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
        
        <script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
        <script type="text/javascript" src="js/table2excel/jquery.table2excel.js"></script> 
	<!--<script type="text/javascript" src="js/jquery-3.3.1.js"></script>-->
        
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
            function clearall()
{
       // alert("in JS clearall");                        
        //$('.highcharts-container').css('visibility','hidden');
        //document.getElementsByName("highcharts-container").style.display="none";
        $(".highcharts-container").hide();


}

	function test()
	{
		$('#tamperdatatable').DataTable
                ( 
                    {
                                dom: 'Blfrtip',
                                //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                                //buttons: ['csv', 'excel', 'print'],
                                        
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
                                    }
                                ]


                    } 
                );
	}

    function getReportsInstantData(meter_id) {
//   alert("meter_id = "+meter_id);
//    getHeader(); // to get the header first 
//alert("in change");
        //$("#pageheader").load(location.href + " #pageheader>*", "");//KUMAR
        //$( "#pageheader" ).load(window.location.href + " #pageheader" );
        $('#kumar1').removeClass('current');
        $('#kumar2').removeClass('current');
        $('#kumar3').removeClass('current');
        $('#kumar4').removeClass('current');
        $('#kumar5').removeClass('current');
        $('#kumar').addClass('current');
        document.getElementById('cont-1-1').style.display = "block";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById('cont-3-1').style.display = "none";
        document.getElementById('cont-3-1-1').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-5-1').style.display = "none";
        document.getElementById('cont-6-1').style.display = "none";
        document.getElementById('ip_opt_div1').style.display = "none";//KUMAR
        document.getElementById("instant1").checked=true;
        document.getElementById("instant2").checked=false;
        if (meter_id != 0)
        {
           var radioButtons1 = document.getElementsByName("instant");
           if(radioButtons1.length=3)
           {
               if (document.getElementById("instant2").checked)
               {
                   //alert("checked2");
               }
               else if (document.getElementById("instant1").checked)
               {
                   //alert("checked1");
               }
           }
           document.getElementById("DataPart").innerHTML = document.getElementById("loading").innerHTML;//KUMAR LOADING DIV 
            //document.getElementsByName("instant")[0].checked = true;            // div of instant with values 1, 2 or 3 
            var chbEMF = document.getElementById("AEMF");                         // div of checkbox aemf

            xmlHttp = GetXmlHttpObject();
            if (xmlHttp == null)
            {
                alert("Browser does not support HTTP Request");
                return;
            }
            var url = "getInstantData.jsp";
            url = url + "?meter_id=" + meter_id + "&ApplyEMF=" + (chbEMF.checked ? "1" : "0") + "&InstType=instant";
//        alert("url = "+url);
            xmlHttp.onreadystatechange = getInstantaneousData1;
            xmlHttp.open("GET", url, true);
            xmlHttp.send(null);
        } else
        {
            document.getElementById("DataPart").innerHTML = "";
            dcuFunction();
        }
    }
    function getInstantaneousData1() {
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            if(xmlHttp.status == 302){
                window.location="/relogin.do";
            }
            if(xmlHttp.status == 200){
            var showdata = xmlHttp.responseText;
            document.getElementById("DataPart").innerHTML = showdata;
            var but_value;
            var radioButtons = document.getElementsByName("instant");
            for (var x = 0; x < radioButtons.length; x++) {
                if (radioButtons[x].checked) {
                    but_value = radioButtons[x].value;
                   
        }
        }
            
        }
            //document.getElementsByName("instant")[but_value - 1].checked = true;
        }
    }//getInstantaneousData
    function getInstant(value) {
    }
    function getInstantTabs() {     //  its called when the instantaneous tab is clicked
//        alert("in getEnergyoptions");
         $('#kumar1').removeClass('current');
        $('#kumar2').removeClass('current');
        $('#kumar3').removeClass('current');
        $('#kumar4').removeClass('current');
        $('#kumar5').removeClass('current');
        $('#kumar').addClass('current');
        document.getElementById('cont-1-1').style.display = "block";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById('cont-3-1-1').style.display = "none";
        document.getElementById('cont-3-1').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-5-1').style.display = "none";
        document.getElementById('cont-6-1').style.display = "none";
        document.getElementById('ip_opt_div1').style.display = "none";//KUMAR
        document.getElementById("instant2").checked=false;//KUMAR
        document.getElementById("instant3").checked=false;//KUMAR
        document.getElementById("instant1").checked=true;//KUMAR
        $("#cont-1-1").load(location.href + " #cont-1-1>*", "");//KUMAR
         var radioButtons1 = document.getElementsByName("instant");
           if(radioButtons1.length=3)
           {
               if (document.getElementById("instant2").checked)
               {
                   //alert("checked2");
               }
               else if (document.getElementById("instant1").checked)
               {
                   //alert("checked1");
               }
               else
               {
                   //alert("checked3");
               }
           }
        var but_value;
        
        var radioButtons = document.getElementsByName("instant");
        for (var x = 0; x < radioButtons.length; x++) {
            if (radioButtons[x].checked) {
                but_value = radioButtons[x].value;
       
            }
        }
       
        if (but_value == 3) {
           // document.getElementsByName("instant")[0].checked = true;
            
            
        }
        
        getInstantData2(but_value);
    }
    function getcompleteData()
    {
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('ip_opt_div1').style.display = "none";
      
        var month = document.getElementById("month").value;
        var getmonth=month.substring(month.lastIndexOf("-")+1);
        var getyear=month.substring(0,month.lastIndexOf("-"));
        //alert(getmonth+"-"+getyear);
        //var monthyear = getmonth+"-"+getyear;
        //alert(monthyear);
         var chbEMF = document.getElementById("AEMF");  

        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        //selected option to get the meter serial number
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].value;
       
        var url = "getCompleteData.jsp?meter_id=" + escape(SelectedOption) + "&month=" + escape(getmonth) + "&year=" + escape(getyear)+"&EMF="+(chbEMF.checked?"1":"0");
        //alert(url);
        xmlHttp.onreadystatechange = getInstantaneousData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }

    function getInstantData2(data_type) // for Instant & Complete data
    {

        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('ip_opt_div1').style.display = "none";

        sdate = document.getElementById("sDate").value;
        edate = document.getElementById("eDate").value;

        if (data_type == 2) 
        {   // getting dates for complete data
            if (sdate == "" && edate == "")
                getDates();

        }
        if (data_type == 1) {   // for instantaneous data
            document.getElementById('date_div').style.display = "none";
        }
        document.getElementById('gdate').style.display = "none";
        var chbEMF = document.getElementById("AEMF");
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }

        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].value;
        
        var url = "getInstantData.jsp?data_type=" + escape(data_type) + "&meter_id=" + escape(SelectedOption) + "&sdate=" + escape(sdate) + "&edate=" + escape(edate) + "&start=1" + "&ApplyEMF=" + (chbEMF.checked ? "1" : "0");
//        alert(url);
        xmlHttp.onreadystatechange = getInstantaneousData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }//getInstantanious Data

    function getInstantaneousData()
    {
        //alert("in getinstantaneous Data");
        document.getElementById('DataPart').style.display = "block";
        document.getElementById('ip_opt_div1').style.display = "none";//Kumar
        
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            var showdata = xmlHttp.responseText;
            
            document.getElementById("DataPart").innerHTML = showdata;
            //$('.highcharts-container').css('visibility','hidden');
            var but_value;
            var radioButtons = document.getElementsByName("instant");
            for (var x = 0; x < radioButtons.length; x++) {
                if (radioButtons[x].checked) {
                    but_value = radioButtons[x].value;
                }
            }
            document.getElementsByName("instant")[but_value - 1].checked = true;
             $('.highcharts-container').css('visibility','hidden');
             
        }
        implement4();
        
        
         
    }//getInstantaneousData

    function getDates() //Call from complete data radio button
    {
        //alert("In Getdates function");
        //$("#DataPart").load(location.href + " #DataPart>*", "");//KUMAR
        document.getElementById("DataPart").style.display = "none";
        document.getElementById("sDate").value = "";
        document.getElementById("eDate").value = "";
        document.getElementById('ip_opt_div1').style.display = "none";
        document.getElementById("gdate").style.display = "none";
        document.getElementById('date_div').style.display = "block";
//        loadDate(1);
    }

    function getInstantOptions() //call from graphs radio button
    {
        //alert("in get instant options function");
        document.getElementById("DataPart").innerHTML = "";
        //document.getElementById("graphPart").style.display = "none";
        document.getElementById('date_div').style.display = "none";
        document.getElementById("graphDate").value = "";
        document.getElementById('gdate').style.display = "block";
        loadDate(2);
        document.getElementById('ip_opt_div1').style.display = "block";
    }

    function getSelectedCompleteData(start)
    {
//        alert("start  in reports menu = "+start);
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
        var ApplyEMF = document.getElementById("AEMF");  // new code
        sdate = document.getElementById("sDate").value;
        edate = document.getElementById("eDate").value;
//        var sdate = "20/07/2017";
//        var edate = "28/07/2017";
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].value;
        var url = "getInstantData.jsp?data_type=2&start=" + escape(start) + "&meter_id=" + escape(SelectedOption) + "&sdate=" + escape(sdate) + "&edate=" + escape(edate) + "";
        url = url + "&ApplyEMF=" + (ApplyEMF.checked ? "1" : "0"); //new code
//        alert("url = "+url);
        xmlHttp.onreadystatechange = getInstantaneousData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }

    var rph = new Array();
    var yph = new Array();
    var bph = new Array();
    var graphTime = new Array();
    var kwh = new Array();

    function getVANDCGraphs(e_opt) {
        if (document.getElementById("graphDate").value == "")
        {
            //alert("please select date");
            document.getElementById("graphDate").focus();
        }
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
        sdate = document.getElementById("graphDate").value;
        //alert(sdate);
//        var sdate = "27/07/2017";
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].value;
        xmlHttp = GetXmlHttpObject();
        var chbEMF = document.getElementById("AEMF");

        if (e_opt == 1)
        {
            //alert("voltage");
            var url = "getVoltageGraph.jsp?sdate=" + escape(sdate) + "&meter_id=" + escape(SelectedOption) + "";
            url = url + "&ApplyEMF=" + (chbEMF.checked ? "1" : "0");
//            alert("url = "+url);
            xmlHttp.onreadystatechange = getVoltageGraphData;

        }
        if (e_opt == 2)
        {
            //alert("current");
            var url = "getCurrentGraph.jsp?sdate=" + escape(sdate) + "&meter_id=" + escape(SelectedOption) + "";
            url = url + "&ApplyEMF=" + (chbEMF.checked ? "1" : "0");
//            alert("url = "+url);
            xmlHttp.onreadystatechange = getCurrentGraphData;

        }
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }

    function getVoltageGraphData() {

        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            var showdata = xmlHttp.responseText;
            gtime = showdata.substring(showdata.indexOf("time") + 5, showdata.indexOf("rph") - 1);
            // alert("gtime "+gtime)
            gtime = gtime.replace(";", "");
            //alert("gtime  "+gtime)
            graphTime = gtime.split(",");

            rc = showdata.substring(showdata.indexOf("rph") + 4, showdata.indexOf("yph") - 1);
            //alert("rc  "+rc)
            rph = rc.split(",");
            for (i = 0; i < rph.length; i++)
            {
                rph[i] = +rph[i];
            }
            yc = showdata.substring(showdata.indexOf("yph") + 4, showdata.indexOf("bph") - 1);
            yph = yc.split(",");
            for (j = 0; j < yph.length; j++)
            {
                yph[j] = +yph[j];
            }
            bc = showdata.substring(showdata.indexOf("bph") + 4);
            bph = bc.split(",");
            for (k = 0; k < bph.length; k++)
            {
                bph[k] = +bph[k];
            }
            voltageGraph();
        }
    }

    function getCurrentGraphData() {
//        document.getElementById('submenu').style.display = "block";
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            var showdata = xmlHttp.responseText;

            gtime = showdata.substring(showdata.indexOf("time") + 5, showdata.indexOf("rph") - 1);
            gtime = gtime.replace(";", "");
            graphTime = gtime.split(",");

            rc = showdata.substring(showdata.indexOf("rph") + 4, showdata.indexOf("yph") - 1);

            rph = rc.split(",");
            for (i = 0; i < rph.length; i++)
            {
//                alert(rph[i]);
                rph[i] = +rph[i];
//                alert(rph[i]);
            }

            yc = showdata.substring(showdata.indexOf("yph") + 4, showdata.indexOf("bph") - 1);
            yph = yc.split(",");
            for (j = 0; j < yph.length; j++)
            {
                yph[j] = +yph[j];
            }

            bc = showdata.substring(showdata.indexOf("bph") + 4);
            bph = bc.split(",");
            for (k = 0; k < bph.length; k++)
            {
                bph[k] = +bph[k];
            }
            currentGraph();
        }
    }

//    function currentGraph()
//    {
//        var chart;
////        alert('111---');
////        $(document).ready(function () {
//        chart = new Highcharts.Chart({
//            chart: {
//                renderTo: 'DataPart',
//                defaultSeriesType: 'line',
//                marginRight: 100,
//                marginBottom: 55,
//                width: window.size,
//                borderColor: "#4572A7",
//                borderRadius: 15,
//                borderWidth: 1,
//                margin: [40, 40, 40, 40]
//
//            },
//            title: {
//                text: 'Currents Graph',
//                x: -20 //center
//            },
//            subtitle: {
//                text: '',
//                x: -20
//            },
//            xAxis: {
//                gridLineWidth: 1,
//                lineColor: '#000',
//                tickColor: '#000',
//                tickInterval: 3,
//                labels: {
//                    style: {
//                        color: '#000',
//                        font: '11px Trebuchet MS, Verdana, sans-serif'
//                    }
//                },
//
//                title: {
//                    text: ' Time(hh:mm:ss)'
//
//                },
//
//                categories: graphTime,
//                min: 0,
//                max: 24
//
//            },
//            yAxis: {
//                min: 0,
//                minorTickInterval: 'auto',
//                lineColor: '#000',
//                lineWidth: 1,
//                tickWidth: 1,
//                tickColor: '#000',
//                labels: {
//                    style: {
//                        color: '#000',
//                        font: '11px Trebuchet MS, Verdana, sans-serif'
//                    }
//                },
//                title: {
//                    text: 'currents (A)'
//                },
//                plotLines: [{
//                        value: 0,
//                        width: 1,
//                        color: '#808080'
//                    }]
//            },
//            tooltip: {
//                formatter: function () {
//                    return '<b>' + this.series.name + '</b><br/>' +
//                            this.x + '(hh:mm:ss)' + '</b><br/> ' + this.y + '';
//                }
//            },
//            legend: {
//                layout: 'vertical',
//                align: 'right',
//                verticalAlign: 'top',
//                x: -10,
//                y: 100,
//                borderWidth: 0
//            },
//            scrollbar: {
//                enabled: true
//            },
//            series: [{
//                    name: 'BPhase',
//                    data: bph,
//                    color: 'blue'
//                }, {
//                    name: 'RPhase',
//                    data: rph,
//                    color: 'red'
//                }, {
//                    name: 'YPhase',
//                    data: yph,
//                    color: 'yellow'
//                }, ],
//            exporting: {
//                enabled: false
//            },
//            credits: {
//                enabled: false
//            }
//        });
////        });
//    }
function currentGraph()
{
    chart = new Highcharts.Chart({
            chart: {
                renderTo:'DataPart',
                zoomType: 'x',
                borderColor: "#4572A7",
                borderRadius: 15,
                borderWidth: 1,
                margin: [80, 0, 30, 80]
            },
            title: {
                text: 'Currents Graph'
            },
            subtitle: {
                text: document.ontouchstart === undefined ?
                        'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
            },
            xAxis: {
                gridLineWidth: 1,
                lineColor: '#000',
                tickColor: '#000',
                tickInterval:2,
                labels: {
                    enabled: false
//                    style: {
//                        color: '#000',
//                        font: '11px Trebuchet MS, Verdana, sans-serif'
//                    }
                },
                title: {
                    text: 'Time(hh:mm:ss)'

                },
                categories: graphTime
            },
            yAxis: {
                title: {
                    text: 'Currents (A)'
                }
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                            this.x +  '</b><br/> ' + this.y + '';
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth:0.5,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },
            legend: {
                layout: 'horizontal',
                backgroundColor: '#FFFFFF',
                align: 'left',
                verticalAlign: 'top',
                x: 1,
                y: 10,
                floating: true,
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: 'BPhase',
                data: bph,
                color:'#5fa2e2'
            }, {
                name: 'RPhase',
                data: rph,
                color:'#e25f5f'
            }, {
                name: 'YPhase',
                data: yph,
                color: '#e2d75f'
            },]
        });
}
//    function voltageGraph() {
////        alert("in voltage graph");
//        var chart;
////        $(document).ready(function () {
//        chart = new Highcharts.Chart({
//            chart: {
//                renderTo: 'DataPart',
//                defaultSeriesType: 'line',
//                marginRight: 100,
//                marginBottom: 95,
//                width: window.size,
//                borderColor: "#4572A7",
//                borderRadius: 15,
//                borderWidth: 1,
//                margin: [40, 40, 40, 40]
//            },
//            title: {
//                text: 'Voltage Graph',
//                x: -20 //center
//            },
//            subtitle: {
//                text: '',
//                x: -20
//            },
//            xAxis: {
//                gridLineWidth: 1,
//                lineColor: '#000',
//                tickColor: '#000',
//                tickInterval: 3,
//                labels: {
//                    style: {
//                        color: '#000',
//                        font: '11px Trebuchet MS, Verdana, sans-serif'
//                    }
//                },
//                title: {
//                    text: 'Time(hh:mm:ss)'
//
//                },
//                categories: graphTime,
//                min: 0,
//                max: 24
//            },
//            yAxis: {
//                min: 0,
//                minorTickInterval: 'auto',
//                lineColor: '#000',
//                lineWidth: 1,
//                tickWidth: 1,
//                tickColor: '#000',
//
//                labels: {
//                    style: {
//                        color: '#000',
//                        font: '11px Trebuchet MS, Verdana, sans-serif'
//                    }
//                },
//                title: {
//                    text: 'voltages (V)'
//                },
//                plotLines: [{
//                        value: 0,
//                        width: 1,
//                        color: '#808080'
//                    }]
//            },
//            tooltip: {
//                formatter: function () {
//                    return '<b>' + this.series.name + '</b><br/>' +
//                            this.x + '(hh:mm:ss)' + '</b><br/> ' + this.y + '';
//                }
//            },
//            legend: {
//                layout: 'vertical',
//                align: 'right',
//                verticalAlign: 'top',
//                x: -10,
//                y: 100,
//                borderWidth: 0
//            },
//            scrollbar: {
//                enabled: true
//            },
//
//            series: [{
//                    name: 'BPhase',
//                    data: bph,
//                    color: 'blue'
//                }, {
//                    name: 'RPhase',
//                    data: rph,
//                    color: 'red'
//                }, {
//                    name: 'YPhase',
//                    data: yph,
//                    color: 'yellow'
//                }, ],
//            exporting: {
//                enabled: false
//            },
//            credits: {
//                enabled: false
//            }
//        });
////        });
//    }
function voltageGraph()
{

    chart = new Highcharts.Chart({
            chart: {
                renderTo:'DataPart',
                zoomType: 'x',
                borderColor: "#4572A7",
                borderRadius: 15,
                borderWidth: 1,
                margin: [80, 0, 30, 80]
            },
            title: {
                text: 'Voltages Graph'
            },
            subtitle: {
                text: document.ontouchstart === undefined ?
                        'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
            },
            xAxis: {
                gridLineWidth: 1,
                lineColor: '#000',
                tickColor: '#000',
                tickInterval:3,
                labels: {
                    enabled: false
//                    style: {
//                        color: '#000',
//                        font: '11px Trebuchet MS, Verdana, sans-serif'
//                    }
                },
                title: {
                    text: 'Time(hh:mm:ss)'

                },
                categories: graphTime
                
            },
            yAxis: {
                title: {
                    text: 'Voltages (v)'
                }
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                            this.x +  '</b><br/> ' + this.y + '';
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth:0.5,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },
            legend: {
                layout: 'horizontal',
                backgroundColor: '#FFFFFF',
                align: 'left',
                verticalAlign: 'top',
                x: 1,
                y: 10,
                floating: true,
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: 'BPhase',
                data: bph,
                color:'#5fa2e2'
            }, {
                name: 'RPhase',
                data: rph,
                color:'#e25f5f'
            }, {
                name: 'YPhase',
                data: yph,
                color: '#e2d75f'
            },]
        });

}
//--------------------------------------- Energy Data -------------------------------------------
    function getEnergyOptions() {
//        alert("in getEnergyoptions");
        $('#kumar').removeClass('current');
        $('#kumar2').removeClass('current');
        $('#kumar3').removeClass('current');
        $('#kumar4').removeClass('current');
        $('#kumar5').removeClass('current');
        $('#kumar1').addClass('current');
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-3-1-1').style.display = "none";
        document.getElementById('cont-3-1').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-2-1').style.display = "block";
        document.getElementById('cont-5-1').style.display = "none";
        document.getElementById('cont-6-1').style.display = "none";

        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getEnergyScreen.jsp";
        xmlHttp.onreadystatechange = getEnergyPageAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function getEnergyPageAction() {
        var showData = xmlHttp.responseText;
//        document.getElementById("load_div2").innerHTML = "";
        document.getElementById("cont-2-1").style.visibility = "visible";
        document.getElementById("cont-2-1").innerHTML = showData;
//        loadDate(4);
    }
    function getEnergyReport() {
//       alert("in energy");
        var options = document.getElementsByName("e_opt1");
        var selectedEOption = 0;
        for (var i = 0; i < options.length; i++)
        {
            if (options[i].checked)
                selectedEOption = options[i].value;
        }
        getEnergy(selectedEOption);
    }
    function getEnergy(e_opt1) {
//        alert("e_opt1 = "+e_opt1);
//        document.getElementById('e_opt_div3').style.display = "none";
        document.getElementById("DataPart").innerHTML = "";
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "";
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].title;
//        alert("selectedoption  "+SelectedOption)
        var esdate = document.getElementById('energyFromDate').value;
        var eedate = document.getElementById('energyToDate').value;
//        alert("e_opt1 === "+e_opt1);
        if((e_opt1!=7) && (e_opt1!=9))
        if (esdate == "" && eedate == "") {
            alert("Please select dates...");
            return 0;
        }
//        var esdate = "01/07/2017";
//        var eedate = "02/08/2017";
//        alert("e_opt1  "+e_opt1);
        var energyMF =document.getElementById('energyMF');
//        var e_opt1 = 1;
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;

        if (e_opt1 == 1)
            url = "main_energy.jsp?meter_sl_no=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
        if (e_opt1 == 2)
            url = "tod_energy.jsp?meter_sl_no=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
        if (e_opt1 == 3)
            url = "energy_consumption.jsp?meter_sl_no=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
        if (e_opt1 == 5)
            url = "tod_consumption.jsp?meter_sl_no=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
        if (e_opt1 == 6)
            url = "max_demand.jsp?meter_sl_no=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
        if (e_opt1 == 7)
            url = "AutoResetBillings/ResetBillings.jsp?meter_sl_no=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
        if (e_opt1 == 8)
            url = "AutoResetBillings/DailyBillings.jsp?meter_sl_no=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
        if (e_opt1 == 9){
//            alert('selected = '+e_opt1);
            url = "AutoResetBillings/consumptionReport.jsp?meter_sl_no=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
        }
//        alert(url);
        xmlHttp.onreadystatechange = getEnergyData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function mainEnergy(start) {
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var energyMF = document.getElementById("energyMF");
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].title;
        var esdate = document.getElementById('energyFromDate').value;
        var eedate = document.getElementById('energyToDate').value;
        var url = "main_energy.jsp?meter_sl_no=" + escape(SelectedOption) + "&start=" + escape(start) + "&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
        xmlHttp.onreadystatechange = getEnergyData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function max_demand(start) {
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var energyMF = document.getElementById("energyMF");
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].title;
        var esdate = document.getElementById('energyFromDate').value;
        var eedate = document.getElementById('energyToDate').value;
        var url = "max_demand.jsp?meter_sl_no=" + SelectedOption + "&start=" + escape(start) + "&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
        // alert(url);
        xmlHttp.onreadystatechange = getEnergyData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function getEnergyData() {
//        document.getElementById('submenu').style.display = "block";
        document.getElementById('DataPart').style.display = "block";
//        document.getElementById('meter_details').style.display = "block";
//        document.getElementById('report_header').style.display = "none";
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            //alert("inside energydata");
            var showdata = xmlHttp.responseText;
            document.getElementById("DataPart").innerHTML = showdata;
            implement2();
        }
    }
//---------------------------------------------- Load Survey-------------------------------------------
    function getLoadPage() {
//        alert("inside getLoadPage method");
        $('#kumar1').removeClass('current');
        $('#kumar').removeClass('current');
        $('#kumar3').removeClass('current');
        $('#kumar4').removeClass('current');
        $('#kumar5').removeClass('current');
        $('#kumar2').addClass('current');
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-3-1-1').style.display = "block";
        document.getElementById('cont-5-1').style.display = "none";
        document.getElementById('cont-6-1').style.display = "none";

        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getLoadScreen1.jsp";
//        alert("url ====> "+url);
        xmlHttp.onreadystatechange = getLoadPageAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function getLoadPageAction() {
        var showData = xmlHttp.responseText;
//        document.getElementById("load_div2").innerHTML = "";
        document.getElementById("cont-3-1-1").style.visibility = "visible";
        document.getElementById("cont-3-1-1").innerHTML = showData;
//        loadDate(4);
    }
    function getLoadPage1() {
//        alert("inside getLoadPage method");
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-3-1-1').style.display = "block";
        document.getElementById('cont-3-1').style.display = "block";
        document.getElementById('cont-5-1').style.display = "none";
        document.getElementById('cont-6-1').style.display = "none";
        
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getLoadSurveyScreen.jsp";
//        alert("url ====> "+url);
        xmlHttp.onreadystatechange = getLoadPageAction1;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function getLoadPageAction1() {
        var showData = xmlHttp.responseText;
        document.getElementById("cont-3-1").style.visibility = "visible";
        document.getElementById("cont-3-1").innerHTML = showData;
//        loadDate(4);
    }

    function getLoadsurvayDatas() {
//        alert("from getLoadsurvey data method");
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
        var lsdate = document.getElementById("loadsDate").value;
//        alert("lsdate"+lsdate);
        var ledate = document.getElementById("loadeDate").value;
//        alert("ledate"+ledate);
        if (lsdate == "" && ledate == "") {
            alert("Please select dates...");
            return 0;
        }
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].value;
        
        var chbMF=document.getElementById("chbMF");
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getLoadsurvayData.jsp";
        url = url + "?lsdate=" + escape(lsdate) + "&ledate=" + escape(ledate) + "&meter_id=" + escape(SelectedOption) + "&start=1&EMF="+(chbMF.checked?"1":"0");        
//        alert("url = "+url);
        xmlHttp.onreadystatechange = insertLoadsurvayData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        // }

    }//getLoadsurvayData
    function getLoadData(start) {
//        alert("get load data = "+start);
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
        var lsdate = document.getElementById("loadsDate").value;
        var ledate = document.getElementById("loadeDate").value;
//        var lsdate = "06/07/2017";
//        var ledate = "31/07/2017";
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].value;
//        var chbMF = document.getElementById("chbMF");

        var url = "getLoadsurvayData.jsp?start=" + escape(start) + "&meter_id=" + escape(SelectedOption) + "&lsdate=" + escape(lsdate) + "&ledate=" + escape(ledate) + "&EMF=1";
//        alert("url = "+url);
        xmlHttp.onreadystatechange = insertLoadsurvayData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function insertLoadsurvayData() {
        document.getElementById('DataPart').style.display = "block";
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            var showdata = xmlHttp.responseText;
            document.getElementById("DataPart").innerHTML = showdata;
            implement1();
        }
    }
    function getLoadDate()
    {
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById("cont-3-1").innerHTML = "";
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "load_daywise_screen.jsp";
        xmlHttp.onreadystatechange = getLoadDateAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function getLoadDateAction()
    {
        var showData = xmlHttp.responseText;
        document.getElementById("cont-3-1").style.visibility = "visible";
        document.getElementById("cont-3-1").innerHTML = showData;
        loadDate(5);
    }
    function getMultipleLoadDate()
    {
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById("cont-3-1").innerHTML = "";
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "load_multiple_screen.jsp";
        xmlHttp.onreadystatechange = getMultipleLoadDateAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function getMultipleLoadDateAction()
    {
        var showData = xmlHttp.responseText;
        document.getElementById("cont-3-1").style.visibility = "visible";
        document.getElementById("cont-3-1").innerHTML = showData;
        loadDate(6);
    }
//------------------------------------- Tamper -------------------------------------------------------------
    function getEventsPage() {
//        alert("in event page");
         $('#kumar1').removeClass('current');
        $('#kumar2').removeClass('current');
        $('#kumar').removeClass('current');
        $('#kumar4').removeClass('current');
        $('#kumar5').removeClass('current');
        $('#kumar3').addClass('current');
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById('cont-3-1-1').style.display = "none";
        document.getElementById('cont-3-1').style.display = "none";
        document.getElementById('cont-4-1').style.display = "block";
        document.getElementById('cont-5-1').style.display = "none";
        document.getElementById('cont-6-1').style.display = "none";
//        alert("events")        
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getEventsScreen.jsp";
        xmlHttp.onreadystatechange = getEventsPageAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }

    function getEventsPageAction()
    {
        var showData = xmlHttp.responseText;
        document.getElementById("cont-4-1").style.visibility = "visible";
        document.getElementById("cont-4-1").innerHTML = showData;
//        loadDate(7);
    }

    function selectall(source) {
        if (source.id == "select") {
            if (source.checked == true) {
                chkboxes = document.getElementsByName('R-Phase');
                for (var i = 0; i < chkboxes.length; i++)
                {
                    chkboxes[i].checked = true;
                }
            }
            if (source.checked == false) {
                chkboxes = document.getElementsByName('R-Phase');
                for (var i = 0; i < chkboxes.length; i++)
                {
                    chkboxes[i].checked = false;
                }
            }
        }
    }
    function deselect(source)
    {
        checkbox = document.getElementsByName('allevent');
        for (var i in checkbox)
            checkbox[i].checked = source.unchecked;
    }
    function getEventsDatas(bal)
    {
//        alert("bal ---> " + bal);
        
        var values = new Array();
        checkboxes = document.getElementsByName('R-Phase');
        for (var i = 0; i < checkboxes.length; i++)
        {
            if (checkboxes[i].checked == true)
            {
                values = values + checkboxes[i].value + ",";
            }
        }
        if (values.length == 0)
        {
            alert("Please select atleast one checkbox");
            return;
        }
//        alert("values -------> " + values);
        document.getElementById('DataPart').style.display = "block";
//        document.getElementById('report_header').style.display = "none";        
        var esdate = document.getElementById("eventsDate").value;
        var eedate = document.getElementById("eventeDate").value;
//        var esdate = "01/05/2017";
//        var eedate = "01/08/2017";
        if(esdate=="" || eedate==""){
            alert("select from and to dates");
            return 0;
        }
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].title;
        var chbMF=document.getElementById("chbMF");
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }

//        if (data_type == 1)
        var url = "getEventsData.jsp";
//        if (data_type == 2)
//            var url = "getEventsCumulativeData.jsp";
        url = url + "?sdate=" + escape(esdate) + "&edate=" + escape(eedate) + "&meter_id=" + escape(SelectedOption) + 
                "&start=1&EMF="+(chbMF.checked?"1":"0")+ "&chkvalues=" + escape(values);
        //alert(url);
        xmlHttp.onreadystatechange = insertEventsData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }//getEventData

    function getEventsHistory(start)
    {
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var values = new Array();
        checkboxes = document.getElementsByName('R-Phase');
        for (var i = 0; i < checkboxes.length; i++)
        {
            if (checkboxes[i].checked)
            {
                values = values + checkboxes[i].value + ",";
            }
        }
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].title;
        var tampersFrom = document.getElementById("eventsDate").value;
        var tampersTo = document.getElementById("eventeDate").value;
//        var tampersFrom = "01/05/2017";
//        var tampersTo = "01/08/2017";
        var url = "getEventsData.jsp";
        url = url + "?sdate=" + escape(tampersFrom) + "&edate=" + escape(tampersTo) + "&meter_id=" + escape(SelectedOption) + "&start=" + escape(start) + "&chkvalues=" + escape(values);        
//        alert("url in getEventsHistory"+url);
        xmlHttp.onreadystatechange = insertEventsData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }

    function insertEventsData()
    {
        document.getElementById('DataPart').style.display = "block";
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            var showdata = xmlHttp.responseText;
            document.getElementById("DataPart").innerHTML = showdata;
            implement3();
        }
    }//insertEventsData
//----------------------------------------- Snapshot Dat -------------------------------------------

    function getSnapshotPage(){
         $('#kumar1').removeClass('current');
        $('#kumar2').removeClass('current');
        $('#kumar3').removeClass('current');
        $('#kumar').removeClass('current');
        $('#kumar5').removeClass('current');
        $('#kumar4').addClass('current');
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-3-1-1').style.display = "none";
        document.getElementById('cont-3-1').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-5-1').style.display = "block";
        document.getElementById('cont-6-1').style.display = "none";

        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getSnapShotDates.jsp";
        xmlHttp.onreadystatechange = getSnapePageAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function getSnapePageAction() {
        var showData = xmlHttp.responseText;
//        document.getElementById("load_div2").innerHTML = "";
        document.getElementById("cont-5-1").style.visibility = "visible";
        document.getElementById("cont-5-1").innerHTML = showData;
    }

    function getSnapshotSelectedData() {
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById('cont-3-1-1').style.display = "none";
        document.getElementById('cont-3-1').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-5-1').style.display = "block";
        document.getElementById('cont-6-1').style.display = "none";
//        alert("snapshot data called");
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
//        var data_type111 = 12;
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].value;
        
        var startindDate = document.getElementById("dsDate").value;
        var endingDate = document.getElementById("deDate").value;

        if (startindDate == "" && endingDate == "") {
            alert("Please select dates...");
//                return 0;
        }
        var chbMF = document.getElementById("chbMF");
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;        
        var url = "getSnapshotData.jsp?startindDate=" + escape(startindDate) + "&endingDate=" + escape(endingDate)
                + "&meter_id=" + escape(SelectedOption)+"&EMF=" + (chbMF.checked ? "1" : "0");
//        alert("url = "+url);
        xmlHttp.onreadystatechange = insertSnapshotData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }//getSnapshotData
    
//    function getSnapshotData1(data_type)
//    {
////        alert("data_type in getSnapshotData= "+data_type);
//        xmlHttp = GetXmlHttpObject();
//        if (xmlHttp == null)
//        {
//            alert("Browser does not support HTTP Request");
//            return;
//        }
//        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
//        var url = "getSnapshotData.jsp";
//        url = url + "?data_type=" + data_type;
//
//        xmlHttp.onreadystatechange = insertSnapshotData;
//        xmlHttp.open("GET", url, true);
//        xmlHttp.send(null);
//    }//getSnapshotData
    
    function insertSnapshotData()
    {
        document.getElementById('DataPart').style.display = "block";
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            var showdata = xmlHttp.responseText;
            document.getElementById("DataPart").innerHTML = showdata;
            $('#snapshotdata').DataTable
             ( 
                {
                        dom: 'Blfrtip',
                        bSort: false,
                        buttons: [
                                    {
                                        extend: 'copy',
                                        title: 'Snapshot Data'
                                    },
                                    
                                    {
                                        extend: 'excel',
                                        title: 'Snapshot Data'
                                    },
                                
                                    {
                                        extend: 'csv',
                                        title: 'Snapshot Data'
                                    },
                                    {
                                        extend: 'pdf',
                                        title: 'Snapshot Data',
                                        orientation: 'landscape'
                                    }
                                ],
                        //buttons: ['csv', 'excel', 'print'],
                        lengthMenu : [ 10, 25, 50, 75, 100 ]
                } 
             );
        }
    }//insertSnapshotData
    
    
//-------------------------------Get Outage Reports----------------------------
function getOutageReportPage()
{
        //alert("in get outage report page");
        $('#kumar').removeClass('current');
        $('#kumar1').removeClass('current');
        $('#kumar2').removeClass('current');
        $('#kumar3').removeClass('current');
        $('#kumar4').removeClass('current');
        $('#kumar5').addClass('current');
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-3-1-1').style.display = "none";
        document.getElementById('cont-3-1').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-5-1').style.display = "none";
        document.getElementById('cont-6-1').style.display = "block";
        document.getElementById('outelements').style.display = "";

    
}
function getOutageInfo()
{
        //alert("in outageinfo");
        var outdate = document.getElementById('outdate').value;
        var meterselect = document.getElementById('dtr').selectedIndex
        var meter_id = document.getElementById('dtr').options[meterselect].value;
     
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getOutageScreen.jsp?meter_id="+meter_id+"&date="+outdate;
        xmlHttp.onreadystatechange = getOutageAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        
}
function getOutageAction()
{
        document.getElementById('DataPart').style.display = "block";
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            var showdata = xmlHttp.responseText;
            document.getElementById("DataPart").innerHTML = showdata;  
        }
        implement5();
}

var phasorDate;
    function getPhasor(meter,mdate,div){
        //window.open("getPhasor.jsp?meter="+meter+"&date="+mdate,"Phasor Diagram",'width=650,height=250,left=500,top=250,scrollbars=yes');
        
//     url="getInstantGuageValues.jsp?meter_no="+escape(meter)+"&searchDate="+escape(mdate);   
//        var win = window.open(url, '_blank');
//        win.focus();
     var sameDiv=0;
    $('.shown').each(function(index, obj){
       if($(this).attr("id")==div){
           $(this).removeClass("shown");
           $(this).empty();
           sameDiv=1 ;
       }
     });
     if(sameDiv==0){
    $('.tr_phasor').each(function(index, obj){    
       $(this).empty();
       $(this).removeClass("shown");
       if($(this).attr("id")==div)
           $(this).addClass("shown");
     });
      $.ajax( {
        url:"getInstantGuageValues.jsp?meter_no="+escape(meter)+"&searchDate="+escape(mdate),
        beforeSend: function(msg){
        	document.getElementById(div).innerHTML=$('#loading').html();
          }, 
        success:function(data) {
           document.getElementById(div).innerHTML="<td colspan='15' id='pahsor_"+div+"'></td>";
          
           
        rph_c=data.split(":")[1].split(",")[0];    
        yph_c=data.split(":")[1].split(",")[1];
        bph_c=data.split(":")[1].split(",")[2];
        rph_v=data.split(":")[0].split(",")[0];
        yph_v=data.split(":")[0].split(",")[1];
        bph_v=data.split(":")[0].split(",")[2];
        rph_pf=data.split(":")[2].split(",")[0];
        yph_pf=data.split(":")[2].split(",")[1];
        bph_pf=data.split(":")[2].split(",")[2];
        r_y_ang=data.split(":")[3].split(",")[0];
        r_b_ang=data.split(":")[3].split(",")[1];
        
        getPhaseGuage(rph_pf,yph_pf,bph_pf,rph_v,yph_v,bph_v,rph_c,yph_c,bph_c,r_y_ang,r_b_ang,'pahsor_'+div);
          }
     });
 }
       
    }
function toDegrees (angle) {
    
  return parseFloat((angle*(180/Math.PI)).toFixed(2));
}
    function getPhaseGuage(rphPF,yphPF,bphPF,Vr,Vy,Vb,Ir,Iy,Ib,RY,RB,div)
{ 
   //alert(Math.min(Vr,Vy,Vb)+"-------"+ parseFloat(yphPF));
   //alert(RY+" "+RB);
    var ang1=0,ang2=0,ang3=0;   
        ang1 =  toDegrees(Math.acos(rphPF));  
        ang2 =  toDegrees(Math.acos(yphPF));   
        ang3 =  toDegrees(Math.acos(bphPF));
        
    
     maxV=Math.max(Vr,Vy,Vb);
     maxC=Math.max(Math.abs(Ir),Math.abs(Iy),Math.abs(Ib));
     
     vrAmp=(Vr/maxV)*100; vyAmp=(Vy/maxV)*100; vbAmp=(Vb/maxV)*100;
     IrAmp=(Math.abs(Ir)/maxC)*100; IyAmp=(Math.abs(Iy)/maxC)*100; IbAmp=(Math.abs(Ib)/maxC)*100;
     
    //alert(IrAmp);
    if(Math.min(Vr,Vy,Vb)>50){
        
  $(function () {

    chart = new Highcharts.Chart({

        chart: {
            renderTo: div,
            type: 'gauge',
            plotBackgroundColor: null,
            plotBackgroundImage: null,
            plotBorderWidth: 0,
            plotShadow: false,
            marginTop:-5,
           // marginLeft: 160,
           // width:500,
            height:250
                      
            
        },
        tooltip: {
            enabled: false
        },
        title: {
           useHTML:true,
            text: '<h5 style="color:#3B5998;padding-bottom:1px;padding-right:1px;">Phaser Diagram</h5> <br /> '+
                  '&theta;(<font color="#990000">Vr </font>-<font color="#FF9933">Vy</font>)'+RY+'<br />'+
                  '&theta;(<font color="#990000">Vr </font>-<font color="#6699FF">Vb</font>) '+RB+'<br />'+
                  '&theta;(<font color="#990000">Vr </font>-<font color="red">IR</font>)'+parseFloat(ang1)+'<br />'+
                  '&theta;(<font color="#FF9933">Vy </font>-<font color="#E6B800">IY</font>)'+parseFloat(ang2)+'<br />'+
                  '&theta;(<font color="#6699FF">Vb </font>-<font color="blue">IB</font>) '+parseFloat(ang3),
style:{
textAlign:'left',
fontSize:'9px'
},
align: 'left'
        },

        pane: {
            startAngle: 0,
            endAngle: 360,
                  
            background:[
                {
                   backgroundColor: '#FFF',
                   outerRadius: '0%',
                   innerRadius: '0%'
                }
                ] 
           
        },
plotOptions: {
            gauge: {
                dial: {
                    borderWidth:50,
                    baseWidth:3,
                    topWidth: 3,
                    baseLength: '70%', // of radius
                    rearLength: '0%',
                    dashStyle:'longdashdot'
                }
            }
        },
            
        // the value axis
        yAxis: {
            min: 0,
            max: 360,
            minorTickInterval: 120,
            minorTickLength:15,
            minorTickPosition:'outside',
            tickLength:80, 
            tickWidth:2,
            tickInterval:120,
            plotLines: [{ 
                color: 'red',
                width: 2,                
                value:0                
            },{ 
                color: '#E6B800',
                width: 2,
                value:120                
            },{ 
                color: 'blue',
                width: 2,
                value:240               
            }],
            labels: {
                distance: 30,
                formatter: function () {
                    if (this.value == 0) {                        
                        return 'v1=0';
                    } else if (this.value == 120) {
                        return 'v2=120';
                    } else if (this.value == 240) {
                        return 'v3=240';
                    }
                }
            }
        },

        series: [{
            name: 'Ir',
            data: [parseFloat(ang1)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'red'
            }
            
        },
        {
            name: 'Iy',
            data: [parseFloat(ang2.toFixed(2))+parseFloat(RY)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: '#E6B800'
            }
        },
        {
            name: 'Ib',
            data: [parseFloat(ang3.toFixed(2))+parseFloat(RB)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'blue'
            }
        },
        {
            name: 'Vr',
            data: [0],
           dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'red'
            }
            
        },
        {
            name: 'Vy',
            data: [parseFloat(RY)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: '#E6B800'
            }
        },
        {
            name: 'Vb',
            data: [parseFloat(RB)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'blue'
            }
        }
                 
                ],
        credits:{
                enabled:false
            },
             exporting: {
                enabled: true
            }

    },
   // Add some life
    function (chart) {
        setInterval(function () {
            chart.series[0].options.dial.radius =IrAmp;            
            chart.series[0].isDirty = true;
            chart.series[1].options.dial.radius =IyAmp;
            chart.series[1].isDirty = true;
            chart.series[2].options.dial.radius =IbAmp;
            chart.series[2].isDirty = true;
            chart.series[3].options.dial.radius =vrAmp;
            chart.series[3].isDirty = true;
            chart.series[4].options.dial.radius =vyAmp;
            chart.series[4].isDirty = true;
            chart.series[5].options.dial.radius =vbAmp;
            chart.series[5].isDirty = true;
            chart.redraw();
            
        }, 3000);
    });                                  
});



    }
     if(Math.min(Vr,Vy,Vb)==0 && parseFloat(yphPF)==0 && !((parseFloat(Vy)==1 || parseFloat(Vy)==0) && parseFloat(Vb)==0)){
    // if(!(Math.round(Vy)==0 && Math.round(Vb)==0)){
  $(function () {
    
    var chart = new Highcharts.Chart({
    
        chart: {
            renderTo: div,
            type: 'gauge',
            plotBackgroundColor: null,
            plotBackgroundImage: null,
            plotBorderWidth: 0,
            plotShadow: false,
            marginLeft: 60,
            width:300,
            height:250
             
        },
        
       tooltip: {
            enabled: false
        },
        title: {
           useHTML:true,
            text: '<h3 style="color:#3B5998;padding-bottom:1px;padding-right:1px;">Phaser Diagram</h3> <br /> '+
                  '&theta;(<font color="#990000">Vr </font>-<font color="#FF9933">Vy</font>)'+RY+'<br />'+
                  '&theta;(<font color="#990000">Vr </font>-<font color="#6699FF">Vb</font>) '+RB+'<br />'+
                  '&theta;(<font color="#990000">Vr </font>-<font color="red">IR</font>)'+parseInt(ang1)+'<br />'+
                  '&theta;(<font color="#FF9933">Vy </font>-<font color="#E6B800">IY</font>)'+parseInt(ang2)+'<br />'+
                  '&theta;(<font color="#6699FF">Vb </font>-<font color="blue">IB</font>) '+parseInt(ang3),
style:{
textAlign:'left',
fontSize:'9px'
},
align: 'left'
        },

        
         pane: {
            startAngle: 0,
            endAngle: 360,
                  
            background:[
                {
                   backgroundColor: '#FFF',
                   outerRadius: '0%',
                   innerRadius: '0%'
                }
                ] 
           
        },
plotOptions: {
            gauge: {
                dial: {
                    borderWidth:50,
                    baseWidth:3,
                    topWidth: 3,
                    baseLength: '70%', // of radius
                    rearLength: '0%',
                    dashStyle:'longdashdot'
                }
            }
        },
            
        // the value axis
        yAxis: {
            min: 0,
            max: 360,
            minorTickInterval: 120,
            minorTickLength:15,
            minorTickPosition:'outside',
            tickLength:80, 
            tickWidth:2,
            tickInterval:120,
            plotLines: [{ 
                color: 'red',
                width: 2,                
                value:0                
            },{ 
                color: '#E6B800',
                width: 2,
                value:120                
            },{ 
                color: 'blue',
                width: 2,
                value:240               
            }],
            labels: {
                distance: 30,
                formatter: function () {
                    if (this.value == 0) {                        
                        return 'v1=0';
                    } else if (this.value == 120) {
                        return 'v2=120';
                    } else if (this.value == 240) {
                        return 'v3=240';
                    }
                }
            }
        },

        series: [{
            name: 'Ir',
            data: [parseFloat(ang1)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'red'
            }
            
        },
        {
            name: 'Iy',
            data: [parseFloat(ang2.toFixed(2))+parseFloat(RY)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: '#E6B800'
            }
        },
        {
            name: 'Ib',
            data: [parseFloat(ang3.toFixed(2))+parseFloat(RB)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'blue'
            }
        },
        {
            name: 'Vr',
            data: [0],
           dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'red'
            }
            
        },
        {
            name: 'Vy',
            data: [parseFloat(RY)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: '#E6B800'
            }
        },
        {
            name: 'Vb',
            data: [parseFloat(RB)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'blue'
            }
        }
                 
                ],
        credits:{
                enabled:false
            },
             exporting: {
                enabled: true
            }

    },
   // Add some life
    function (chart) {
        setInterval(function () {
            chart.series[0].options.dial.radius =IrAmp;            
            chart.series[0].isDirty = true;
            chart.series[1].options.dial.radius =IyAmp;
            chart.series[1].isDirty = true;
            chart.series[2].options.dial.radius =IbAmp;
            chart.series[2].isDirty = true;
            chart.series[3].options.dial.radius =vrAmp;
            chart.series[3].isDirty = true;
            chart.series[4].options.dial.radius =vyAmp;
            chart.series[4].isDirty = true;
            chart.series[5].options.dial.radius =vbAmp;
            chart.series[5].isDirty = true;
            chart.redraw();
            
        }, 3000);
    });                                  
});
    }
    
    else{
    //document.getElementById("phaseGuage").innerHTML="Phase voltage Missing";
    
    }
}        
 function exportExcel(tableId,name){ 
            
//          table=document.getElementById(''+tableId+'');
//          if (!table.nodeType) table = document.getElementById(table);
//          download(table.outerHTML, name, "application/vnd.ms-excel");
          //download(table.innerHTML, "Export.xls", "application/vnd.ms-excel");
        var meter=$( "#dtr option:selected" ).attr("title");
          var table = $("#instantTable");
            if(table && table.length){                 
                   // var preserveColors = (table.hasClass('table2excel_with_colors') ? true : false);
                    $(table).table2excel({
                            exclude: ".noExl",
                            name: "Excel Document Name",
                            filename:"Instant_"+meter+"_" + new Date().toISOString().replace(/[\-\:\.]/g, "") + ".xls",
                            fileext: ".xls",
                            exclude_img: true,
                            exclude_links: true,
                            exclude_inputs: true
                            //preserveColors: preserveColors
                    });
            }
}
function download(strData, strFileName, strMimeType) {
    var D = document,
        A = arguments,
        a = D.createElement("a"),
        d = A[0],
        n = A[1],
        t = A[2] || "text/plain";

    //build download link:
    a.href = "data:" + strMimeType + "," + escape(strData);


    if (window.MSBlobBuilder) {
        var bb = new MSBlobBuilder();
        bb.append(strData);
        return navigator.msSaveBlob(bb, strFileName);
    } /* end if(window.MSBlobBuilder) */



    if ('download' in a) {
        a.setAttribute("download", n);
        a.innerHTML = "downloading...";
        D.body.appendChild(a);
        setTimeout(function() {
            var e = D.createEvent("MouseEvents");
            e.initMouseEvent("click", true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
            a.dispatchEvent(e);
            D.body.removeChild(a);
        }, 66);
        return true;
    } /* end if('download' in a) */
    ; //end if a[download]?

    //do iframe dataURL download:
    var f = D.createElement("iframe");
    D.body.appendChild(f);
    f.src = "data:" + (A[2] ? A[2] : "application/octet-stream") + (window.btoa ? ";base64" : "") + "," + (window.btoa ? window.btoa : escape)(strData);
    setTimeout(function() {
        D.body.removeChild(f);
    }, 333);
    return true;
} /* end download library function () */  

function getInstantAllParams(){
       document.getElementById("DataPart").innerHTML = "";
        document.getElementById('ip_opt_div1').style.display = "none";

        document.getElementById('gdate').style.display = "none";
        
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }

        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].title;
        
        var url = "getInstantAllParamsData.jsp?&meter_id=" + escape(SelectedOption);

        xmlHttp.onreadystatechange = getInstantaneousData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
}
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
<div class="centercontent tables mart10">
    <div class="pageheader notab" id="pageheader">
        <ul class="hornav">
            <li id="kumar"><a href="javascript:getInstantTabs()">Instantaneous</a></li>
            <li id="kumar1"><a href="javascript:getEnergyOptions()">Energy</a></li>
            <li id="kumar2"><a href="javascript:getLoadPage()">Load Survey</a></li>
            <li id="kumar3"><a href="javascript:getEventsPage()">Tampers </a></li>
            <li id="kumar4"><a href="javascript:getSnapshotPage()">Snapshot Data</a></li>
            <!--<li id="kumar5"><a href="javascript:getOutageReportPage()">Outage Report</a></li>-->
            
        </ul>
    </div><!--pageheader-->

    <div id="contentwrapper" class="contentwrapper">
        <div class="tabcontent paddingAll" id="cont-1-1" style="display:none">
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
  