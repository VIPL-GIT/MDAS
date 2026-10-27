function GetXmlHttpObject() {
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
}

//

function getDashBoard(actionId) {
	clear();
	hideDashboardDiv();
	$("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');  
	$.ajax( {
        url:'Dashboard/DashBoardNew.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
          // callDataTable('table1');
        }
     });
}



function meterDataAnySummary(){
	$.ajax( {
        url:'meterdataanalysis.jsp',
        beforeSend:function(){
        	document.getElementById("meterdata").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#meterdata').html(data);
          // callDataTable('table1');
        }
     });	
}

function goToMeterDataReports(meter){
	clear();
	hideDashboardDiv();
	
	$.ajax( {
        url:'MeterData/MeterDataTabs.epx?actionId=23',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	
           $('#datapart').html(data);
           
           $('#childID').val(meter);
           
           $('#childID').trigger("chosen:updated");
           
           getReportsInstantData(meter);
          
        }
     });
}

function energyComparision(){
	$.ajax( {
        url:'energyComparision.jsp',
        beforeSend:function(){
        	document.getElementById("energy").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#energy').html(data);
          // callDataTable('table1');
        }
     });	
}



function criticalTampers(){
	$.ajax( {
        url:'criticaltampers.jsp',
        beforeSend:function(){
        	document.getElementById("tampers").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#tampers').html(data);
          // callDataTable('table1');
        }
     });	
}


function tamperEvents(){
	$.ajax( {
        url:'tamperevents.jsp',
        beforeSend:function(){
        	document.getElementById("events").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#events').html(data);
          // callDataTable('table1');
        }
     });	
}






function getBarGraph(){
	var datess = new Array();
    var commCounts = new Array();
    var notCommCounts = new Array();
    var everCommCounts = new Array();
    var mcfCounts = new Array();
    
    
    var sdatess =  "";
    var scommCounts = "";
	var snotCommCounts = "";
	var severCommCounts ="";
	var smcfCounts="";
    
    
//  alert("dates = "+dates);
    
	
	$.ajax( {
        url:'getHomeBarGraph.jsp',
        beforeSend:function(){
        	document.getElementById("bargraph").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        //   $('#events').html(data);
          
             sdatess =  data.toString().split(";")[0];
             scommCounts = data.toString().split(";")[1];
        	 snotCommCounts = data.toString().split(";")[2];
        	 severCommCounts = data.toString().split(";")[3];
        	 smcfCounts = data.toString().split(";")[4];
        	
        	datess = sdatess.toString().split(",");
    	    commCounts = scommCounts.toString().split(",");
    	    notCommCounts = snotCommCounts.toString().split(",");
    	    everCommCounts = severCommCounts.toString().split(",");
    	    mcfCounts = smcfCounts.toString().split(",");
    	    
    	    var date1=datess[0];
    	    var date2=datess[1];
    	    var date3=datess[2];
    	    var date4=datess[3];
    	    var date5=datess[4];
    	    var date6=datess[5];
    	    var date7=datess[6];
    	    
    	    var com1=parseInt(commCounts[0]);
    	    var com2=parseInt(commCounts[1]);
    	    var com3=parseInt(commCounts[2]);
    	    var com4=parseInt(commCounts[3]);
    	    var com5=parseInt(commCounts[4]);
    	    var com6=parseInt(commCounts[5]);
    	    var com7=parseInt(commCounts[6]);
     
    	    var notcom1=parseInt(notCommCounts[0]);
    	    var notcom2=parseInt(notCommCounts[1]);
    	    var notcom3=parseInt(notCommCounts[2]);
    	    var notcom4=parseInt(notCommCounts[3]);
    	    var notcom5=parseInt(notCommCounts[4]);
    	    var notcom6=parseInt(notCommCounts[5]);
    	    var notcom7=parseInt(notCommCounts[6]);
    	    
    	    var nevcom1=parseInt(everCommCounts[0]);
    	    var nevcom2=parseInt(everCommCounts[1]);
    	    var nevcom3=parseInt(everCommCounts[2]);
    	    var nevcom4=parseInt(everCommCounts[3]);
    	    var nevcom5=parseInt(everCommCounts[4]);
    	    var nevcom6=parseInt(everCommCounts[5]);
    	    var nevcom7=parseInt(everCommCounts[6]);
    	    
    	    var mcf1=parseInt(mcfCounts[0]);
    	    var mcf2=parseInt(mcfCounts[1]);
    	    var mcf3=parseInt(mcfCounts[2]);
    	    var mcf4=parseInt(mcfCounts[3]);
    	    var mcf5=parseInt(mcfCounts[4]);
    	    var mcf6=parseInt(mcfCounts[5]);
    	    var mcf7=parseInt(mcfCounts[6]);
    	
    	    Highcharts.chart('bargraph', {
    	    	colors: ['#00A653', '#FFA500' ,'#85335C','#FF3333'],
    	        chart: {
    	            type: 'column',
    	            zoomType: 'x',
                    borderColor: "#4572A7",
                    borderRadius: 15,
                    borderWidth: 1,
                    margin: [50, 0, 30, 0],
    	            options3d: {
    	                enabled: true,
    	                alpha: 15,
    	                beta: 15,
    	                viewDistance: 25,
    	                depth: 40
    	            }
    	        },

    	        title: {
    	            text: 'Communication Status'
    	        },
    	        exporting: {
    	            enabled: false
    	        },
    	        credits: {
    	        enabled: false
    	        },
    	        xAxis: {
    	            categories:datess,
    	            labels: {
    	                rotation: 0,
    	                x: 0,
    	                style: {fontSize: '8px',
    	                    color:'#000'
    	                }
    	            }
    	        },

    	        yAxis: {
    	            allowDecimals: false,
    	            min: 0,
    	            title: {
    	                text: '',
    	                skew3d: true
    	            }
    	        },

    	        tooltip: {
    	            headerFormat: '<b>{point.key}</b><br>',
    	            pointFormat: '<span style="color:{series.color}">\u25CF</span> {series.name}: {point.y} / {point.stackTotal}'
    	        },
    	        legend: {
//    	            align: 'right',
//    	            layout: 'vertical',
//    	            verticalAlign: 'middle'         
    	        align: 'center',
    	        verticalAlign: 'top',
    	        layout: 'horizontal',
    	        y:5
    	        },
    	        plotOptions: {
    	            column: {
    	                stacking: 'normal',
    	                depth: 40
    	            }
    	        },

    	        series: [{
    	            name: 'Read',
    	            data: [com1, com2, com3, com4, com5,com6,com7]
    	            
    	        }, {
    	            name: 'Non-Read',
    	            data: [notcom1, notcom2, notcom3, notcom4, notcom5,notcom6,notcom7]
    	           
    	        },
    	        {
    	            name: 'Never-Read',
    	            data: [nevcom1, nevcom2, nevcom3, nevcom4, nevcom5,nevcom6,nevcom7],
    	            visible:false
    	           
    	        },
    	        {
    	            name: 'Cable issue',
    	            data: [mcf1,mcf2,mcf3,mcf4,mcf5,mcf6,mcf7],
    	            
    	        }
    	        ]
    	    });
        }
     });	
    }


function pieChart(){
	
	var selectedDate=document.getElementById('graphDate').value; 
	var meterType=document.getElementById('meterType').value; 
	
	$.ajax( {
        url:'getpiedata.jsp?selectedDate='+selectedDate+'&meterType='+meterType,
        beforeSend:function(){
        	document.getElementById("piechart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	if(data.indexOf("error")>0){
        		document.getElementById("piechart").innerHTML="<div class='error'>"+data.split(":")[1]+"</div>";
        	}else{
        	getPiChart(data.toString().split(",")[0], 
        			data.toString().split(",")[1], 
        			data.toString().split(",")[2], 
        			data.toString().split(",")[3], 
        			data.toString().split(",")[4]
                    );
        	
        	getDashMapUnmapCount();
        	}
           
        }
     });
	
	}
function getPiChart(totalMeters, commCount, notCommCount, everCommCount, mcfCount)
{   
   // alert('powerOffCount = ')
//    alert("-------> "+totalMeters+commCount+notCommCount+everCommCount+powerOffCount);
    var titleName;
    
    var dataArray = [];
    var headerArray = new Array();
    headerArray = ['Read', 'Non-Read', 'Never-Read', 'Cable Issue'];
//    alert("commCount "+commCount);
//    alert("notCommCount "+notCommCount);
//    alert("everCommCount "+everCommCount);
    dataArray=[  [headerArray[0],parseInt(commCount)],
    [headerArray[1],  parseInt(notCommCount)],                
   // [headerArray[2], parseInt(everCommCount)],
    {name:headerArray[2],y:parseInt(everCommCount),visible:false},
    [headerArray[3], parseInt(mcfCount)]];

    Highcharts.chart('piechart', {
        colors: ['#00A653', '#FFA500' ,'#85335C','#FF3333'],
        chart: {
            type: 'pie',
            backgroundColor:'#f3f3f3',
//            height:170 ,
            height: 150,
            width: 300,
//            margin:-2,
            marginTop:10,
//            marginLeft:5,
            marginRight:155,
            useHTML: true,
            options3d: {
                enabled: true,
                alpha: 35,
                beta: 0
            }
        },
        title: {
            text:'(Total=' + totalMeters + ')',
            y:3,
            x:-50
            
        },
        tooltip: {
            pointFormat: '{' + this.name + '}{point.percentage:.1f}%'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 25,
                dataLabels: {
                    enabled: false,
                    format: '{point.name} <br /> {point.y}'
                },
                showInLegend: true
            },
            series: {
                cursor: 'pointer',
                point: {
                    events: {
                        click: function () {
                            getHomePieChartMeters(this.name);
                        }
                    }
                }
            }
        },
        legend: {
            itemStyle: {
                 fontSize:'10px',color: '#000'
              },
              itemHoverStyle: {
                 color: '#FFF'
              },
              itemHiddenStyle: {
                 color: '#444'
              },
            align: 'right',
            layout: 'vertical',
            verticalAlign: 'top',
            labelFormatter: function () {
                return  this.name+'(' + this.y+')';
            },
            
            x: -30,
            y: 30,
            borderWidth: 0
        },
        exporting: {
            enabled: false
        },
        credits: {
            enabled: false
        },
        series: [{
                type: 'pie',
                //            name: 'Meters Status',
                data: dataArray
            }]
    });

}
function getDashMapUnmapCount(){
	$.ajax( {
        url:'getDashMapUnmapCount.jsp',
        beforeSend:function(){
        	document.getElementById("totalComm").innerHTML=document.getElementById("loader").innerHTML;
        	document.getElementById("commUnmapped").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	
        	document.getElementById("totalComm").innerHTML=data.split(",")[0];
        	document.getElementById("commUnmapped").innerHTML=data.split(",")[1];
        	$(".count-number").counterUp( {
                delay: 10, time: 5e3
            });
        }
     });
}
function getHomePieChartMeters(type) {
    var meterType = document.getElementById("meterType").value;
    var url = "getHomePieChartMeters.jsp?type=" + escape(type) + "&meterType=" + escape(meterType);
//    alert(url);
    window.location = url;
}
	