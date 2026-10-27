function getTrackerHome(){
hideDashboardDiv();
	
	$.ajax( {
        url:'tracker/trackerhome.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
           callForDatesWithId("trahistfrom");
           callForDatesWithId("trahistto");
        }
     });
}
function getTrackerHistory(type){
	if(type=="1") url="tracker/trackerHistoryData.epx";
	if(type=="2") url="tracker/trackerHistoryGraph.epx";
	
	$.ajax( {
        url:url+"?deviceid="+$("#traid").val()+"&from="+$("#trahistfrom").val()+"&to="+$("#trahistto").val(),
        beforeSend:function(){
        	document.getElementById("tabler").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	if(type=="1"){
        	 $('#tabler').html(data);
        	 callDataTable('table2');
        	}
        	if(type=="2"){
        		$('#tabler').html(data);	
        	}
        	
           
        }
     });
}
function getBatteryHome(){
	hideDashboardDiv();
		
		$.ajax( {
	        url:'tracker/batteryhome.epx',
	        beforeSend:function(){
	        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
	        },
	        success:function(data) {
	           $('#datapart').html(data);
	           callDataTable('table1');
	           callForDatesWithId("batfrom");
	           callForDatesWithId("batto");
	        }
	     });
	}
function getBatteryHistory(type){
	if(type=="1") url="tracker/batteryHistoryData.epx";
	if(type=="2") url="tracker/batteryHistoryGraph.epx";
	
	$.ajax( {
        url:url+"?deviceid="+$("#traid").val()+"&from="+$("#batfrom").val()+"&to="+$("#batto").val(),
        beforeSend:function(){
        	document.getElementById("tabler").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	if(type=="1"){
        	 $('#tabler').html(data);
        	 callDataTable('table3');
        	}
        	if(type=="2"){
        		generateSocHistoryGraph(data);
        	}
           
        }
     });
}
function generateSocHistoryGraph(data){
	var paramNames=data.split("@")[0];
	
	 var multiSeriesData = [];  //constructor in series to add multiple series at a time
	 //alert(paramNames);
  for (var i = 0; i < paramNames.split(",").length; i++){
  var dataSeries=new Array();
  for( j=0;j<data.split("@")[i+2].split(",").length;j++)
      {
  	dataSeries[j]=+data.split("@")[i+2].split(",")[j];}


 multiSeriesData.push({name: paramNames.split(",")[i],
                                    data: dataSeries,
                                    type: 'spline'
                                    //step: true

                                });


}
	Highcharts.chart('tabler', {
	    chart: {
	        type: 'spline',
	        margin:40,
	        marginRight:0,
	        marginBottom:100,
	        zoomType: 'x'
	        
	    },
	    title: {
	        text: ''
	    },
	    subtitle: {
            text: ''
        },
	    xAxis: {
	        categories:data.split("@")[1].split(",")
	    },
	    yAxis: {
	        title: {
	            text: ''
	        },
	        labels: {
	            formatter: function () {
	                return this.value ;
	            }
	        }
	    },
	    legend: {
	    	x:0,
	    	y:0,
	        layout: 'horizontal',
	        align: 'top',
	        verticalAlign: 'top',
	        enabled: true
	    },
	    
	    tooltip: {
	        crosshairs: true,
	        shared: true
	    },
	    credits:{
	    	enabled:false
	    },
	    plotOptions: {
	        spline: {
	            marker: {
	                radius: 4,
	                lineColor: '#666666',
	                lineWidth: 1
	            }
	        }
	    },
	    series: multiSeriesData
	});
}
function getAlertsHome(){
	hideDashboardDiv();
	
	$.ajax( {
        url:'alerts/alertsHome.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table4');
           callForDatesWithId("alertsfrom");
           callForDatesWithId("alertsto");
        }
     });
}
function getAlertsHistory(){
	url="alerts/alertsHistory.epx";
	
	$.ajax( {
        url:url+"?traid="+$("#traid").val()+"&from="+$("#alertsfrom").val()+"&to="+$("#alertsto").val(),
        beforeSend:function(){
        	document.getElementById("tabler").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	 $('#tabler').html(data);
        	 callDataTable('table5');
           
        }
     });
}