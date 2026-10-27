/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function GetXmlHttpObject()
{
    var xmlHttp = null;
    if (window.XMLHttpRequest) {
        xmlHttp = new window.XMLHttpRequest;
    } else {
        try {
            xmlHttp = new ActiveXObject("MSXML2.XMLHTTP.3.0");
        } catch (ex) {
            return null;
        }
    }
    return xmlHttp;
}//GetXmlHttpObject
var graphTime = new Array();
var dateString = new Array();
var vaExp = new Array();
var vaImp = new Array();
var varExp = new Array();
var varImp = new Array();
var wExp = new Array();
var wImp = new Array();
var meterid = null;
var loadSurvayDate = null;
var paramNames = new Array();
var paramValues = new Array();
function getLoadGraph(butnValue)
{
    //alert("in getLoadGraph = "+butnValue);
    document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
    xmlHttp = GetXmlHttpObject();
    var Selected = document.getElementById('dtr').selectedIndex;
    var SelectedOption = document.getElementById('dtr').options[Selected].value;
    meterid = document.getElementById('dtr').options[Selected].title;

    loadSurvayDate = document.getElementById('loadgraphDate').value;

    var chbMF = document.getElementById("chbMF");

    var url = "getLoadSurvayGraph.jsp?sdate=" + escape(loadSurvayDate) +
            "&meterid=" + escape(SelectedOption)
            + "&EMF=" + (chbMF.checked ? "1" : "0");
    //alert(url);
    if (butnValue == 1)
    {
        xmlHttp.onreadystatechange = getDayLine;
    }
    if (butnValue == 2)
    {
        xmlHttp.onreadystatechange = getDayBarChart;
    }
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
}
/*function getMultipleLoadGraph(butnType)
{

    if (document.getElementById("multiplesDate").value == "" && document.getElementById("multipleeDate").value == "")
    {
        alert("please select fromdate and todate");
    }
    xmlHttp = GetXmlHttpObject();
    var Selected = document.getElementById('dtr').selectedIndex;
    var SelectedOption = document.getElementById('dtr').options[Selected].value;
    meterid = document.getElementById('dtr').options[Selected].title;

    multipleLoads = document.getElementById('multiplesDate').value;
    multipleLoade = document.getElementById('multipleeDate').value;
    if (multipleLoads == multipleLoade) {
        alert("From date and to dates could not be same.Select different dates");
        return 0;
    }
    var chbMF = document.getElementById("chbMF");

    if (butnType == 1)
    {
        var url = "loadMultipleStepGraph.jsp?sdate=" + escape(multipleLoads) +
                "&edate=" + escape(multipleLoade) + "&meterid=" + escape(SelectedOption) + "&EMF=" + (chbMF.checked ? "1" : "0");
        //alert(url);
        // xmlHttp.onreadystatechange=getMultipleLine;
        // xmlHttp.onreadystatechange=getMultipleStep;
        window.open(url, "mywindow", "location=1,status=1,scrollbars=1, width=1000,height=400");
        window.moveTo(100, 100);
    }
    if (butnType == 2)
    {
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
        var url = "loadMultipleGraph.jsp?sdate=" + escape(multipleLoads) + "&edate=" + escape(multipleLoade) + "&meterid=" + escape(SelectedOption) + "";
        //alert(url);
        xmlHttp.onreadystatechange = getMultipleBarChart;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }

}*/
function getDayLine()
{
   
    //document.getElementById('DataPart').innerHTML = "";
    if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
    {       
        var showdata = xmlHttp.responseText;       
        for( i=0;i<showdata.split(";").length;i++)
        {                
            paramNames=paramNames+","+showdata.split(";")[i].substring(0,showdata.split(";")[i].indexOf(","));                 
            paramValues=paramValues+";"+showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));                  
        // alert(showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(",")));
        }         
      
        if(paramValues.split(";")[1].length<2){           
             paramValues={};paramNames={};
            document.getElementById("DataPart").innerHTML= "<h3 align='center'>No data Available.</h3>";
            return;
        }
        if(paramValues.split(";")[1].length>2){                        
            dayStep();
        }
        
    }
}
var tickInt;
function getMultipleLine()
{
    document.getElementById('DataPart').innerHTML = "";
    paramNames = {};
    paramValues = {};
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        var showdata = xmlHttp.responseText;
        for (i = 0; i < showdata.split(";").length; i++)
        {
            paramNames = paramNames + "," + showdata.split(";")[i].substring(0, showdata.split(";")[i].indexOf(","));

            paramValues = paramValues + ";" + showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));
        }
        //alert("paramNames--"+paramNames.split(","));
        if (paramValues.split(";")[1].substring(1).split(",").length <= 768)
            tickInt = 48;
        if (paramValues.split(";")[1].substring(1).split(",").length > 768)
            tickInt = 48 * 2;
        //alert("tickInte id"+tickInt);

        multipleStep();
    }
}

function getMultipleStep()
{
    document.getElementById('DataPart').innerHTML = "";
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //alert("in response");
        var showdata = xmlHttp.responseText;
        document.getElementById('DataPart').innerHTML = showdata;


    }
}
function getDayBarChart()
{
    //document.getElementById('DataPart').innerHTML = "";
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        var showdata = xmlHttp.responseText;
        for (j = 0; j < showdata.split(";").length; j++)
        {
            paramNames = paramNames + "," + showdata.split(";")[j].substring(0, showdata.split(";")[j].indexOf(","));
            paramValues = paramValues + ";" + showdata.split(";")[j].substring(showdata.split(";")[j].indexOf(","));
        }

        if (paramValues.split(";")[1].length < 2) {
            paramValues = {};
            paramNames = {};
            document.getElementById("DataPart").innerHTML = "<h3 align='center'>No data Available.</h3>";
            return;
        }
        if (paramValues.split(";")[1].length > 2) {
            dayBar();
        }
    }
}

function getMultipleBarChart()
{
    //document.getElementById('DataPart').innerHTML = "";
    paramNames = {};
    paramValues = {};
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        var showdata = xmlHttp.responseText;
        for (i = 0; i < showdata.split(";").length; i++)
        {
            paramNames = paramNames + "," + showdata.split(";")[i].substring(0, showdata.split(";")[i].indexOf(","));
            paramValues = paramValues + ";" + showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));
        }
        if (paramValues.split(";")[1].length < 6) {
            paramValues = {};
            paramNames = {};
            document.getElementById("DataPart").innerHTML = "<h3 align='center'>No data Available.</h3>";
            return;
        }
        if (paramValues.split(";")[1].length > 6) {
            multipleBar();
        }

    }
}
function dayLine()
{
    var chart;
    $(document).ready(function () {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'DataPart',
                defaultSeriesType: 'line',
                marginRight: 100,
                marginBottom: 55,
                width: window.size,
                borderColor: "#4572A7",
                borderRadius: 15,
                borderWidth: 1,
                margin: [80, 80, 160, 160]
            },
            title: {
                text: 'Line Graph for <br/>' + meterid + ' on  ' + loadSurvayDate + '',
                x: -20 //center
            },
            subtitle: {
                text: '',
                x: -20
            },
            xAxis: {
                gridLineWidth: 1,
                lineColor: '#000',
                tickColor: '#000',
                tickWidth: 2,
                labels: {
                    enabled: false
                },
                title: {
                    text: 'Time(hh:mm:ss)'

                },
                categories: graphTime
            },
            yAxis: {
                minorTickInterval: 'auto',
                lineColor: '#000',
                lineWidth: 1,
                tickWidth: 1,
                tickColor: '#000',
                labels: {
                    style: {
                        color: '#000',
                        font: '11px Trebuchet MS, Verdana, sans-serif'
                    }
                },
                title: {
                    text: 'values'
                },
                plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                            this.x + '(hh:mm:ss)' + '</b><br/> ' + this.y + '';
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            series: [

                {
                    name: 'VA(E)',
                    data: vaExp
                }, {
                    name: 'VA(I)',
                    data: vaImp,
                    step: true
                }, {
                    name: 'VAr(E)',
                    data: varExp
                },
                {
                    name: 'VAr(I)',
                    data: varImp
                },
                {
                    name: 'W(E)',
                    data: wExp
                },
                {
                    name: 'W(I)',
                    data: wImp
                },
                , ],
            exporting: {
                enabled: true
            }
        });


    });

}
function dayStep()
{      
    //alert("paramNames = "+paramNames);
    //alert("paramValues = "+paramValues);
    var seriesData = [];  //constructor in series to add multiple series at a time
    for (var i = 2; i < paramNames.split(",").length; i++){       
        var dataSeries=new Array();
        for( j=0;j<paramValues.split(";")[i].substring(1).split(",").length;j++)
        {
            dataSeries[j]=+paramValues.split(";")[i].substring(1).split(",")[j];
        }
  
        seriesData.push({
            name: paramNames.split(",")[i],      // adding multiple series(params) values to graph
            data: dataSeries
            //step:true
        });
                                 
    }
    // Create the chart
    var chart;
    //alert("meterid "+meterid+" loadSurvayDate = "+loadSurvayDate);
    document.getElementById('DataPart').style.display = "";
    $(document).ready(function() {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'DataPart',
                width: window.size,
                marginRight: 100,
                marginBottom: 55,
                width:window.size,
                borderColor: "#4572A7",
                borderRadius: 15,
                borderWidth: 1,
                margin: [80, 0, 0,80],
                zoomType: 'x'
                                        
            },
            title: {
                text: 'Graph for '+meterid+' on  '+loadSurvayDate+'',
                x: -20 //center
            },
            plotOptions:{
                lineWidth: 80

            },
            credits:{
                enabled:false
            },
            scrollbar: {
                enabled: true
            },
                                     
            xAxis: {
                tickInterval:3,
				        
                title: {
                    text: 'Time(hh:mm)'

                },
                categories: paramValues.split(";")[1].substring(1).split(",")
//                min: 0,
//                max:24
                                               
            },
            yAxis: {
                title: {
                    text: 'Values'
                }
            },
            tooltip: {
                formatter: function() {
                    return '<b>'+this.x +'(hh:mm)'+'</b><br/> '+ this.y +'';
                }
            },
            legend: {
                layout: 'horizontal',
                backgroundColor: '#FFFFFF',
                align: 'left',
                verticalAlign: 'top',
                x: 1,
                y: 20,
                floating: true,
                shadow: true
            },

                                       
            series: seriesData
        });
    });
    paramNames={};
    paramValues={};
}
function multipleLine()
{
    var chart;
    $(document).ready(function () {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'DataPart',
                defaultSeriesType: 'line',
                marginRight: 100,
                marginBottom: 55,
                width: window.size,
                borderColor: "#4572A7",
                borderRadius: 15,
                borderWidth: 1,
                margin: [80, 80, 160, 160]
            },
            title: {
                text: 'Line Graph for <br/>' + meterid + ' from  ' + multipleLoads + ' to ' + multipleLoade,
                x: -20 //center
            },
            subtitle: {
                text: '',
                x: -20
            },
            xAxis: {
                gridLineWidth: 1,
                lineColor: '#000',
                tickColor: '#000',
                tickWidth: 2,
                labels: {
                    enabled: false
                },
                title: {
                    text: 'Time(hh:mm:ss)'

                },
                categories: dateString
            },
            yAxis: {
                minorTickInterval: 'auto',
                lineColor: '#000',
                lineWidth: 1,
                tickWidth: 1,
                tickColor: '#000',
                labels: {
                    style: {
                        color: '#000',
                        font: '11px Trebuchet MS, Verdana, sans-serif'
                    }
                },
                title: {
                    text: 'values'
                },
                plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                            this.x + '(dd-mm-yyyy)' + '</b><br/> ' + this.y + '';
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            series: [{
                    name: 'VA(E)',
                    data: vaExp
                }, {
                    name: 'VA(I)',
                    data: vaImp
                }, {
                    name: 'VAr(E)',
                    data: varExp
                },
                {
                    name: 'VAr(I)',
                    data: varImp
                },
                {
                    name: 'W(E)',
                    data: wExp
                },
                {
                    name: 'W(I)',
                    data: wImp
                },
                , ],
            exporting: {
                enabled: true
            }
        });


    });
}

function dayBar()
{
//    console.log(paramNames);
//    console.log(paramValues);
//    console.log("times : "+paramValues.split(";")[1].substring(1).split(",")[0]);
    var seriesDataBar = [];  //constructor in series to add multiple series at a time
    for (var i = 2; i < paramNames.split(",").length; i++) {
        var dataSeries = new Array();
        for (j = 0; j < paramValues.split(";")[i].substring(1).split(",").length; j++)
        {
            dataSeries[j] = +paramValues.split(";")[i].substring(1).split(",")[j];
        }

        seriesDataBar.push({name: paramNames.split(",")[i], // adding multiple series(params) values to graph
            data: dataSeries,
            step: true

        });

    }
    var chart;
    $(document).ready(function () {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'DataPart',
                defaultSeriesType: 'column',
                marginRight: 100,
                marginBottom: 55,
                width: window.size,
                borderColor: "#4572A7",
                borderRadius: 15,
                borderWidth: 1,
                margin: [80, 0, 0, 80],
                zoomType: 'x'

            },
            title: {
                text: 'Bar Chart for ' + meterid + ' on  ' + loadSurvayDate + ''
            },

            xAxis: {
                 tickInterval:3,
                title: {
                    text: 'Time(hh:mm)'


                },
                categories: paramValues.split(';')[1].substring(1).split(',')
//                min: 0,
//                max: 10
            },
            credits:{
              enabled:false  
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Values'
                }
            },
            legend: {
                layout: 'horizontal',
                backgroundColor: '#FFFFFF',
                align: 'left',
                verticalAlign: 'top',
                x: 1,
                y: 20,
                floating: true,
                shadow: true
            },
            plotOptions: {
                column: {
                    pointPadding: 1,
                    pointWidth: 5,
                    borderWidth: 0
                }
            },
            scrollbar: {
                enabled: true
            },

            tooltip: {
                formatter: function () {
                    return '<b>' + this.x + '(hh:mm)' + '</b><br/> ' + this.y + '';
                }
            },

            series: seriesDataBar
        });


    });
    paramNames = {};
    paramValues = {};
}
function multipleBar()
{
    //alert("tickInte id"+tickInt);
    var seriesData = [];  //constructor in series to add multiple series at a time
    for (var i = 2; i < paramNames.split(",").length; i++) {
        var dataSeries = new Array();
        for (j = 0; j < paramValues.split(";")[i].substring(1).split(",").length; j++)
        {
            dataSeries[j] = +paramValues.split(";")[i].substring(1).split(",")[j];
        }

        seriesData.push({
            name: paramNames.split(",")[i], // adding multiple series(params) values to graph
            data: dataSeries,
            step: true

        });

    }
    var chart;
    $(document).ready(function () {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'DataPart',
                defaultSeriesType: 'column',
                marginRight: 100,
                marginBottom: 55,
                width: window.size,
                borderColor: "#4572A7",
                borderRadius: 15,
                borderWidth: 1,
                margin: [80, 80, 160, 160]

            },
            title: {
                text: 'Graph for' + meterid + ' from  ' + multipleLoads + ' to ' + multipleLoade
            },

            xAxis: {

                title: {
                    text: 'Date(dd-mm-yyyy)'
                },
                categories: paramValues.split(";")[1].substring(1).split(","),
                min: 0,
                max: 10
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Values'
                }
            },
            legend: {
                layout: 'horizontal',
                backgroundColor: '#FFFFFF',
                align: 'left',
                verticalAlign: 'top',
                x: 1,
                y: 20,
                floating: true,
                shadow: true
            },
            plotOptions: {
                column: {
                    pointPadding: 1,
                    pointWidth: 5,
                    borderWidth: 0
                }
            },
            scrollbar: {
                enabled: true
            },

            tooltip: {
                formatter: function () {
                    return '<b>' + this.x + '(hh:mm:ss)' + '</b><br/> ' + this.y + '';
                }
            },

            series: seriesData
        });


    });
    paramNames = {};
    paramValues = {};

}
function multipleStep()
{

    var multiSeriesData = [];  //constructor in series to add multiple series at a time
    //alert(paramNames);
    for (var i = 2; i < paramNames.split(",").length; i++) {
        var dataSeries = new Array();
        for (j = 0; j < paramValues.split(";")[i].substring(1).split(",").length; j++)
        {
            dataSeries[j] = +paramValues.split(";")[i].substring(1).split(",")[j];
        }

//alert(dataSeries);
        multiSeriesData.push({name: paramNames.split(",")[i], // adding multiple series(params) values to graph
            data: dataSeries,
            step: true

        });

    }
    var chart;

    $(document).ready(function () {
        chart = new Highcharts.StockChart({
            chart: {
                renderTo: 'DataPart',
                //defaultSeriesType: 'line',
                width: window.size

            },

            title: {
                text: 'Graph for' + meterid + ' from  ' + multipleLoads + ' to ' + multipleLoade,
                x: -20 //center
            },

            xAxis: {
                tickInterval: tickInt,
                //tickInterval:1,

                categories: paramValues.split(";")[1].substring(1).split(","),
                max: 720,
                labels:
                        {
                            enabled: true
                        }


            },
            yAxis: {
                title: {
                    text: 'Values'
                },

            },
            plotOptions: {
                line: {
                    lineWidth: 0.5,
                    marker: {
                        enabled: false
                    }
                }
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.x + '</b><br/> ' + this.y + '';
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                x: 10,
                y: -10,
                shadow: true,
                borderWidth: 1
            },
            scrollbar: {enabled: true},
            series: multiSeriesData
        });
    });
    paramNames = {};
    paramValues = {};
}
function exportReports(type) {
    if (type == "LS") {
        var lsdate = document.getElementById("loadsDate").value;
        var ledate = document.getElementById("loadeDate").value;
        var chbMF = document.getElementById("chbMF");
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].title;
        var url = "exportLoadsurvayData.jsp";
        url = url + "?fromDate=" + escape(lsdate) + "&toDate=" + escape(ledate) + "&meter_no=" + escape(SelectedOption) + "&EMF=" + (chbMF.checked ? "1" : "0");
        // alert(url);
        window.location = url;
    } else if (type == "InstData") {
        var lsdate = document.getElementById("sDate").value;
        var ledate = document.getElementById("eDate").value;
        var chbEMF = document.getElementById("AEMF");
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].value;
        var url = "exportInstantData.jsp";
        url = url + "?fromDate=" + escape(lsdate) + "&toDate=" + escape(ledate) + "&meter_no=" + escape(SelectedOption) + "&EMF=" + (chbEMF.checked ? "1" : "0");
        //url=url+"?fromDate="+escape(lsdate)+"&toDate="+escape(ledate)+"&meter_no="+escape(SelectedOption)+"&EMF=1";
        // alert(url);
        window.location = url;
    }
}

//----------------------------------------------------new one for multiple days graph --------------------
function getMultipleLoadDate()
{
	
//alert("1");
  document.getElementById("DataPart").innerHTML = "";
  document.getElementById('cont-1-1').style.display = "none";   	
	  document.getElementById('cont-2-1').style.display = "none";
	  document.getElementById('cont-3-1').style.display = "none";   	
	  document.getElementById('cont-3-1-1').style.display = "block";
//	  document.getElementById('cont-3-4').style.display = "none";   	
	  document.getElementById('cont-4-1').style.display = "none";
	  document.getElementById('cont-5-1').style.display = "none";
	  document.getElementById('cont-6-1').style.display = "none";
  document.getElementById('cont-3-3').style.display = "block";
  var loadmeter1=document.getElementById("meter").value;
  getMultipleLoadGraph(loadmeter1);
}
function getMultipleLoadGraph(butnType)
	 {
var SelectedOption= document.getElementById('meter').value;  
 meterid =SelectedOption;      
 xmlHttp = GetXmlHttpObject();
 
 if(butnType!=1){
	 
	 var today = new Date();
	  var dd = today.getDate();
	  var mm = today.getMonth() + 1;
	  if (mm.toString().length == 1) {
	      mm = "0" + mm;
	  }
	  if (dd.toString().length == 1) {
	      dd = "0" + dd;
	  }
	  var yyyy = today.getFullYear();
	  var date = dd + "/" + mm + "/" + yyyy;
	  
	  
	  
	  var today1 = new Date();
	  today1.setDate(today1.getDate() - 10);
	  
	  var dd1 = today1.getDate();
	  var mm1 = today1.getMonth() + 1;
	  if (mm1.toString().length == 1) {
	      mm1 = "0" + mm1;
	  }
	  if (dd1.toString().length == 1) {
	      dd1 = "0" + dd1;
	  }
	  var yyyy1 = today1.getFullYear();
	  var date1 = dd1 + "/" + mm1 + "/" + yyyy1;
	  document.getElementById("loadGraph").checked=true;
	  var chbMF = document.getElementById("chbMF");
	  
	  var url = "loadMultipleStepGraph.jsp?sdate=" + escape(date1) +
	     "&edate=" + escape(date) + "&meterid=" + escape(butnType)+ "&EMF=" + (chbMF.checked ? "1" : "0");
	         xmlHttp.onreadystatechange = getMultiAxisGraph1;
	         xmlHttp.open("GET", url, true);
	         xmlHttp.send(null);
	 
 }

 else{

	 var chbMF = document.getElementById("chbMF");
 multipleLoads = document.getElementById('multiplesDate').value;
 multipleLoade = document.getElementById('multipleeDate').value;
 var url = "loadMultipleStepGraph.jsp?sdate=" + escape(multipleLoads) +
 "&edate=" + escape(multipleLoade) + "&meterid=" + escape(SelectedOption)+ "&EMF=" + (chbMF.checked ? "1" : "0")
     xmlHttp.onreadystatechange = getMultiAxisGraph1;
     xmlHttp.open("GET", url, true);
     xmlHttp.send(null);
 }   

 document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;

}

	
	
	
	function getMultiAxisGraph1()
	 {
	     //document.getElementById('DataPart').innerHTML = "";
	     if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
	     {
	    	 
	    	 
	    	 
	    	 var showdata = xmlHttp.responseText;       
	         for( i=0;i<showdata.split(";").length;i++)
	         {                
	             paramNames=paramNames+","+showdata.split(";")[i].substring(0,showdata.split(";")[i].indexOf(","));                 
	             paramValues=paramValues+";"+showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));                  
	         // alert(showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(",")));
	         }         
	        
	         if(paramValues.split(";")[1].length<2){           
	          paramValues={};paramNames={};
	             document.getElementById("DataPart").innerHTML= "<h3 align='center'>No data Available.</h3>";
	             return;
	         }
	         if(paramValues.split(";")[1].length>2){    
	        	 getMultiAxisGraph();
	          
	         }
	    	 
	    	 

	     }
	 } 

	 function getMultiAxisGraph()
	 {
		
		     var meterid=paramValues.split("#")[1];

		     var lsdate=paramValues.split("#")[2];

		     var ledate=paramValues.split("#")[3];

		     
		     
		var dates= []
		// var dates=new Array();
		 for( k=0;k<paramValues.split(";")[1].substring(1).split(",").length;k++)
	     {
			 //dates[k]=+paramValues.split(";")[k].substring(1).split(",")[k];
			 
			 dates.push(paramValues.split(";")[1].substring(1).split(",")[k]);
	     }
		 
		    var seriesData = [];  //constructor in series to add multiple series at a time
		    
		     for (var i = 2; i < paramNames.split(",").length; i++){       
		         var dataSeries=new Array();
		         for( j=0;j<paramValues.split(";")[i].substring(1).split(",").length;j++)
		         {
		             dataSeries[j]=+paramValues.split(";")[i].substring(1).split(",")[j];
		         }
		   
		     //    alert(dataSeries);
		         
		        
		         
		         var test=paramNames.split(",")[i].toUpperCase();
		         var count=1;
		         
		         
		         if(test==="VR" || test==="VY" || test==="VB" || test==="LR" || test==="LY" || test==="LB"){
		        	 seriesData.push({
			             name: paramNames.split(",")[i],   
			             type: 'spline',
			             data: dataSeries
			             //step:true
			         });
		         }else{
		        	 if(count<=2){
		        	 seriesData.push({
			             name: paramNames.split(",")[i], 
			             yAxis: count,
			             type: 'column',
			             data: dataSeries
			         });
		         }
		        	 count++;
		         }
		         
		         
		     }
		 
		 
		  Highcharts.chart('DataPart', {
			  title: {
				    text: 'Multi Axis Graph',
				    align: 'center'
				  },
				  credits: {
				        enabled: false
				    },
			  chart: {
			    zoomType: 'xy'
			  },
			 
			  
			    exporting: {
		            buttons: {
		                customButton: {
		                    x: -62,
		                    onclick: function () {
		                        alert('Clicked');
		                    },
		                    symbol: 'circle'
		                }
		            }
		        },
		        
			  xAxis: [{
			    categories: dates,
			    crosshair: true
			  }],
			  plotOptions: {
			        series: {
			            cursor: 'pointer',
			            point: {
			                events: {
			                    click: function () {
			                    	
			                       // alert('Category: ' + this.category + ', value: ' + this.y);
			                        
			                        $.ajax({url: "loadMultipleStepGraph2.jsp?sdate="+escape(this.category)+"&meterid="+escape(meterid)+"&lsdate="+escape(lsdate)+"&ledate="+escape(ledate), success: function(responseTxt, statusTxt, xhr){
			                            
			                        	if(statusTxt == "success"){

			                           	
			                           	  var showdata = responseTxt;       
			                                for( i=0;i<showdata.split(";").length;i++)
			                                {                
			                                    paramNames=paramNames+","+showdata.split(";")[i].substring(0,showdata.split(";")[i].indexOf(","));                 
			                                    paramValues=paramValues+";"+showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));                  
			                                // alert(showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(",")));
			                                }         
			                               
			                                if(paramValues.split(";")[1].length<2){           
			                                 paramValues={};paramNames={};
			                                    document.getElementById("DataPart").innerHTML= "<h3 align='center'>No data Available.</h3>";
			                                    return;
			                                }
			                                if(paramValues.split(";")[1].length>2){    
			                               	 getMultiAxisGraph2();
			                                 
			                                }

			                        	}

			                          }});
			                    }
			                }
			            }
			        }
			    },

			  

			  yAxis: [{ // Primary yAxis
			    labels: {
			     
			      style: {
			        color: Highcharts.getOptions().colors[2]
			      }
			    },
			    title: {
			    
			      style: {
			        color: Highcharts.getOptions().colors[2]
			      }
			    },
			    opposite: true

			  }, { // Secondary yAxis
			    gridLineWidth: 0,
			    title: {
			     
			      style: {
			        color: Highcharts.getOptions().colors[0]
			      }
			    },
			    labels: {
			    
			      style: {
			        color: Highcharts.getOptions().colors[0]
			      }
			    }

			  }, { // Tertiary yAxis
			    gridLineWidth: 0,
			    title: {
			     
			      style: {
			        color: Highcharts.getOptions().colors[1]
			      }
			    },
			    labels: {
			    
			      style: {
			        color: Highcharts.getOptions().colors[1]
			      }
			    },
			    opposite: true
			  }],
			  
			  
			  tooltip: {
			    shared: true
			  },
			/*  legend: {
			    layout: 'horizontal',
			    backgroundColor: '#FFFFFF',
			    align: 'left',
			    x: 1,
			    verticalAlign: 'top',
			    y: 12,
	            shadow: true,
			    floating: true,
			    backgroundColor:
			      Highcharts.defaultOptions.legend.backgroundColor || // theme
			      'rgba(255,255,255,0.25)'
			  },*/
			  
		legend: {
			        layout: 'vertical',
			        align: 'right',
			        verticalAlign: 'top',
			        y: 3,
			        padding: 3,
			        itemMarginTop: 5,
			        itemMarginBottom: 5,
			        itemStyle: {
			        lineHeight: '5px'
			        }
			    },
		
			  
			  series:seriesData,
			
			
			  responsive: {
			    rules: [{
			      condition: {
			        maxWidth: 500
			      },
			      chartOptions: {
			        legend: {
			          floating: false,
			          layout: 'horizontal',
			          align: 'center',
			          verticalAlign: 'bottom',
			          x: 0,
			          y: 0
			        },
			        yAxis: [{
			          labels: {
			            align: 'right',
			            x: 0,
			            y: -6
			          },
			          showLastLabel: false
			        }, {
			          labels: {
			            align: 'left',
			            x: 0,
			            y: -6
			          },
			          showLastLabel: false
			        }, {
			          visible: false
			        }]
			      }
			    }]
			  }
			});	
		  paramNames = {};
		  paramValues = {};
	}
	 
	 
	 
	 function getMultiAxisGraph2()
	 {
		     var meterid=paramValues.split("#")[1];
		     var lsdate=paramValues.split("#")[2];
		     var ledate=paramValues.split("#")[3];
		     
		     
		var dates= []
		// var dates=new Array();
		 for( k=0;k<paramValues.split(";")[1].substring(1).split(",").length;k++)
	     {
			 //dates[k]=+paramValues.split(";")[k].substring(1).split(",")[k];
			 
			 dates.push(paramValues.split(";")[1].substring(1).split(",")[k]);
	     }
		 
		    var seriesData = [];  //constructor in series to add multiple series at a time
		    
		     for (var i = 2; i < paramNames.split(",").length; i++){       
		         var dataSeries=new Array();
		         for( j=0;j<paramValues.split(";")[i].substring(1).split(",").length;j++)
		         {
		             dataSeries[j]=+paramValues.split(";")[i].substring(1).split(",")[j];
		         }
		   
		     //    alert(dataSeries);
		         
		        
		         
		         var test=paramNames.split(",")[i].toUpperCase();
		         var count=1;
		         
		         
		        // if(test==="VR" || test==="VY" || test==="VB" || test==="LR" || test==="LY" || test==="LB"){
		         if(test==="VR" || test==="VY" || test==="VB" ){
		        	 seriesData.push({
			             name: paramNames.split(",")[i],   
			             type: 'spline',
			             data: dataSeries
			             //step:true
			         });
		         }else{
		        	 if(count<=2){
		        	 seriesData.push({
			             name: paramNames.split(",")[i], 
			             yAxis: count,
			             type: 'column',
			             data: dataSeries
			         });
		         }
		        	 count++;
		         }
		     }
		 
		 
		  Highcharts.chart('DataPart', {
			  title: {
				    text: 'Multi Axis Graph',
				    align: 'center'
				  },
				  credits: {
				        enabled: false
				    },
			  chart: {
			    zoomType: 'xy'
			  },
			 
			  exporting: {
		            buttons: {
		                customButton: {
		                    x: -62,
		                    onclick: function () {
		                        alert('Clicked');
		                    },
		                    symbol: 'circle'
		                }
		            }
		        },
		        
			  xAxis: [{
			    categories: dates,
			    crosshair: true
			  }],
			 
			

			  yAxis: [{ // Primary yAxis
			    labels: {
			     
			      style: {
			        color: Highcharts.getOptions().colors[2]
			      }
			    },
			    title: {
			    
			      style: {
			        color: Highcharts.getOptions().colors[2]
			      }
			    },
			    opposite: true

			  }, { // Secondary yAxis
			    gridLineWidth: 0,
			    title: {
			     
			      style: {
			        color: Highcharts.getOptions().colors[0]
			      }
			    },
			    labels: {
			    
			      style: {
			        color: Highcharts.getOptions().colors[0]
			      }
			    }

			  }, { // Tertiary yAxis
			    gridLineWidth: 0,
			    title: {
			     
			      style: {
			        color: Highcharts.getOptions().colors[1]
			      }
			    },
			    labels: {
			    
			      style: {
			        color: Highcharts.getOptions().colors[1]
			      }
			    },
			    opposite: true
			  }],
			  
			  
			  tooltip: {
			    shared: true
			  },
		/*	  legend: {
			    layout: 'horizontal',
			    backgroundColor: '#FFFFFF',
			    align: 'left',
			    x: 1,
			    verticalAlign: 'top',
			    y: 12,
	            shadow: true,
			    floating: true,
			    backgroundColor:
			      Highcharts.defaultOptions.legend.backgroundColor || // theme
			      'rgba(255,255,255,0.25)'
			  },*/
			  
			  legend: {
			        layout: 'vertical',
			        align: 'right',
			        verticalAlign: 'top',
			        y: 35,
			        padding: 3,
			        itemMarginTop: 5,
			        itemMarginBottom: 5,
			        itemStyle: {
			            lineHeight: '10px'
			        }
			    },
			  
			  
			  series:seriesData,
			  responsive: {
			    rules: [{
			      condition: {
			        maxWidth: 500
			      },
			      chartOptions: {
			        legend: {
			          floating: false,
			          layout: 'horizontal',
			          align: 'center',
			          verticalAlign: 'bottom',
			          x: 0,
			          y: 0
			        },
			        yAxis: [{
			          labels: {
			            align: 'right',
			            x: 0,
			            y: -6
			          },
			          showLastLabel: false
			        }, {
			          labels: {
			            align: 'left',
			            x: 0,
			            y: -6
			          },
			          showLastLabel: false
			        }, {
			          visible: false
			        }]
			      }
			    }]
			  }},
			  
			  function (chart) { // on complete
			        var i = 0;

			        chart.renderer.button(' << Back ',0,0)
			        
			            .on('click', function () {

		                       // alert('Category: ' + this.category + ', value: ' + this.y);
		                        
		                        $.ajax({url: "loadMultipleStepGraph.jsp?sdate="+escape(lsdate)+"&meterid="+escape(meterid)+"&edate="+escape(ledate), success: function(responseTxt, statusTxt, xhr){
		                            
		                        	if(statusTxt == "success"){

		                           	
		                           	  var showdata = responseTxt;       
		                                for( i=0;i<showdata.split(";").length;i++)
		                                {                
		                                    paramNames=paramNames+","+showdata.split(";")[i].substring(0,showdata.split(";")[i].indexOf(","));                 
		                                    paramValues=paramValues+";"+showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));                  
		                                // alert(showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(",")));
		                                }         
		                               
		                                if(paramValues.split(";")[1].length<2){           
		                                 paramValues={};paramNames={};
		                                    document.getElementById("DataPart").innerHTML= "<h3 align='center'>No data Available.</h3>";
		                                    return;
		                                }
		                                if(paramValues.split(";")[1].length>2){    
		                               	 getMultiAxisGraph();
		                                 
		                                }

		                        	}

		                          }});
		                    
			            	
			            	
			            })
			            .add();
			});	
		  
		  paramNames = {};
		  paramValues = {};
	}
