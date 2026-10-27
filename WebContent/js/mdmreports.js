function viewMeter360Degree(actionId){
	hideDashboardDiv();
	reportType="360view";
	$.ajax( {
        url:'360Home.epx?actionId='+actionId,
        beforeSend: function(msg){
        	$('#datapart').html($('#loading').html());
          }, 
        success:function(data) {
           $('#datapart').html(data);
          
        }
     });
}

var paramNames = new Array();
var paramValues = new Array();

//function get360Report(meterno){
function get360Report(meter){	
		 
		viewMeter360DegreeInstant(meter);
		viewMeter360DegreeBilling('graph');
		viewMeter360DegreeLoad('graph');
		viewMeter360Events(meter);
	
}
function viewMeter360DegreeInstant(meter){
	$.ajax( {
        url:'360Instant.epx?meter='+meter,
        beforeSend: function(msg){
        	$('#instnatDiv').html($('#loading').html());
          }, 
        success:function(data) {
        	 $('#instnatDiv').html(data);
        	 var vals=$("#ip_values").val();
        	 console.log(vals);
        	 getPhaseGuage_360(vals.split(",")[0],vals.split(",")[1],vals.split(",")[2],vals.split(",")[3],vals.split(",")[4],vals.split(",")[5],vals.split(",")[6],vals.split(",")[7],vals.split(",")[8],vals.split(",")[9],vals.split(",")[10]);
        	
        },
          error: function(jqXHR, textStatus, errorThrown) {             

              $('#instnatDiv').html('<p>status code: '+jqXHR.status+'</p><p>errorThrown: ' + errorThrown + '</p><p>jqXHR.responseText:</p><div>'+jqXHR.responseText + '</div>');
              
          }
     });
}

function viewMeter360DegreeBilling(type){
	
	var Selected = document.getElementById('childID').selectedIndex;
    var meter = document.getElementById('childID').options[Selected].text;
    
	$.ajax( {
		
        url:'360AutoEnergies.epx?type='+type+'&meter='+meter,
        beforeSend: function(msg){
        	$('#billing').html($('#loading').html());
          }, 
        success:function(data) {
        	if(type=="tab"){
             $('#billing').html(data);
             callDataTableNoExport('table1');
        	}
        	if(type=="graph"){
        		var showdata =data;
        		if(showdata.indexOf("error")>0){
        			$('#billing').html(showdata.split(":")[1]);
        		}else{
            	  for( i=0;i<showdata.split(";").length;i++)
      	        {                
      	            paramNames=paramNames+","+showdata.split(";")[i].substring(0,showdata.split(";")[i].indexOf(","));                 
      	            paramValues=paramValues+";"+showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));                  
      	         //alert(showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(",")));
      	        } 
             call360BillingGraph();
        		}
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {             

            $('#billing').html('<p>status code: '+jqXHR.status+'</p><p>errorThrown: ' + errorThrown + '</p><p>jqXHR.responseText:</p><div>'+jqXHR.responseText + '</div>');
            
        }
     });
}
function viewMeter360DegreeBilling(type,meter){
	
	$.ajax( {
		
        url:'360AutoEnergies.epx?type='+type+'&meter='+meter,
        beforeSend: function(msg){
        	$('#billing').html($('#loading').html());
          }, 
        success:function(data) {
        	if(type=="tab"){
             $('#billing').html(data);
             callDataTableNoExport('table1');
        	}
        	if(type=="graph"){
        		var showdata =data;
        		if(showdata.indexOf("error")>0){
        			$('#billing').html(showdata.split(":")[1]);
        		}else{
            	  for( i=0;i<showdata.split(";").length;i++)
      	        {                
      	            paramNames=paramNames+","+showdata.split(";")[i].substring(0,showdata.split(";")[i].indexOf(","));                 
      	            paramValues=paramValues+";"+showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));                  
      	         //alert(showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(",")));
      	        } 
             call360BillingGraph();
        		}
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {             

            $('#billing').html('<p>status code: '+jqXHR.status+'</p><p>errorThrown: ' + errorThrown + '</p><p>jqXHR.responseText:</p><div>'+jqXHR.responseText + '</div>');
            
        }
     });
}
function call360BillingGraph(){
	var xvalues=paramValues.toString().split(";")[1].substring(1);
	var seriesDataBar = [];  //constructor in series to add multiple series at a time
    for (var i = 2; i < paramNames.split(",").length; i++){
    	var dataSeries=new Array();
    	for( j=0;j<paramValues.split(";")[i].substring(1).split(",").length;j++)
        {
    		dataSeries[j]=+paramValues.split(";")[i].substring(1).split(",")[j];
    	}

    	if(paramNames.split(",")[i]=="MDKW" || paramNames.split(",")[i]=="MDKVA"){
    		seriesDataBar.push({name: paramNames.split(",")[i],
    			type: 'spline',
                data: dataSeries,
                yAxis: 1,
                step:true
            });
    	}else{    
    	seriesDataBar.push({name: paramNames.split(",")[i],      
                                      data: dataSeries,
                                      pointWidth: 13,
                                      step:true
                                      
                                  });
    	}
    	
    }// for param names
    var chart;
			$(document).ready(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo:  'billing',
						defaultSeriesType: 'column',
						zoomType : 'x',
                       // marginRight: 100,
						marginBottom: 55,
                        width:window.size,
                        height:300
//                        borderColor: "#4572A7",
//                        borderRadius: 15,
//                        borderWidth: 1
                       // margin: [80, 80, 160,160]
                                                 
					},
					title: {
						text: ''
					},
					subtitle: {
		                text: 'Click and drag in the plot area to zoom in',
		                x: -20 //center
		            },
					xAxis: {                                                   
                            title: {
							text: 'Time(hh:mm)'
						},
						categories: xvalues.split(",")                                               
					},
					yAxis: [{
						min: 0,
						title: {
							text: 'Energies'
						}
					},
					{
						min: 0,
						title: {
							text: 'MD Values'
						},
						opposite: true
					}
					],
					legend: {
						layout: 'horizontal',
						backgroundColor: '#FFFFFF',
						align: 'center',
						verticalAlign: 'top',
						x: 20,
						y: 20,
						floating: true,
						shadow: true
					},
                    plotOptions: {
						column: {
							pointPadding: 1,
                            pointWidth:5,
							borderWidth: 0
						}
					},
                   scrollbar: {
                       enabled: true
                           },

					tooltip: {
						formatter: function() {
				                return '<b>'+ this.x +'(hh:mm)'+'</b><br/> '+ this.y +'';
						}
					},
					
				        series:seriesDataBar,
				        credits : {
							enabled : false
						}
				});


			});
                  paramNames={};
                  paramValues={};
}
function viewMeter360DegreeLoad(type){
	var Selected = document.getElementById('childID').selectedIndex;
    var meter = document.getElementById('childID').options[Selected].text;
    
	$.ajax( {
        url:'360Load.epx?type='+type+'&meter='+meter,
        beforeSend: function(msg){
        	$('#load').html($('#loading').html());
          }, 
        success:function(data) {
        	if(type=="tab"){
             $('#load').html(data);
             callDataTableNoExport('table2');
        	}
        	if(type=="graph"){
        		
        		var showdata =data;
        		if(showdata.indexOf("error")>0){
        			$('#load').html(showdata.split(":")[1]);
        		}else{
          	  for( i=0;i<showdata.split(";").length;i++)
    	        {                
    	            paramNames=paramNames+","+showdata.split(";")[i].substring(0,showdata.split(";")[i].indexOf(","));                 
    	            paramValues=paramValues+";"+showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));                  
    	         //alert(showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(",")));
    	        } 
          	  
             call360LoadGraph();
        	}
        	}//else
        },
        error: function(jqXHR, textStatus, errorThrown) {             

            $('#load').html('<p>status code: '+jqXHR.status+'</p><p>errorThrown: ' + errorThrown + '</p><p>jqXHR.responseText:</p><div>'+jqXHR.responseText + '</div>');
            
        }
     });
}

function viewMeter360DegreeLoad(type,meter){
	    
	$.ajax( {
        url:'360Load.epx?type='+type+'&meter='+meter,
        beforeSend: function(msg){
        	$('#load').html($('#loading').html());
          }, 
        success:function(data) {
        	if(type=="tab"){
             $('#load').html(data);
             callDataTableNoExport('table2');
        	}
        	if(type=="graph"){
        		
        		var showdata =data;
        		if(showdata.indexOf("error")>0){
        			$('#load').html(showdata.split(":")[1]);
        		}else{
          	  for( i=0;i<showdata.split(";").length;i++)
    	        {                
    	            paramNames=paramNames+","+showdata.split(";")[i].substring(0,showdata.split(";")[i].indexOf(","));                 
    	            paramValues=paramValues+";"+showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));                  
    	         //alert(showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(",")));
    	        } 
          	  
             call360LoadGraph();
        	}
        	}//else
        },
        error: function(jqXHR, textStatus, errorThrown) {             

            $('#load').html('<p>status code: '+jqXHR.status+'</p><p>errorThrown: ' + errorThrown + '</p><p>jqXHR.responseText:</p><div>'+jqXHR.responseText + '</div>');
            
        }
     });
}

function viewMeter360Events(meter){
	$.ajax( {
        url:'360Events.epx?meter='+meter,
        beforeSend: function(msg){
        	$('#events').html($('#loading').html());
          }, 
        success:function(data) {        	
             $('#events').html(data);
             callDataTableNoExport('table1');        	
        },
        error: function(jqXHR, textStatus, errorThrown) {             

            $('#events').html('<p>status code: '+jqXHR.status+'</p><p>errorThrown: ' + errorThrown + '</p><p>jqXHR.responseText:</p><div>'+jqXHR.responseText + '</div>');
            
        }
     });
}
function call360LoadGraph(){
	var xvalues=paramValues.toString().split(";")[1].substring(1);
	 var seriesData = [];  //constructor in series to add multiple series at a time
	    for (var i = 2; i < paramNames.split(",").length; i++){       
	        var dataSeries=new Array();
	        for( j=0;j<paramValues.split(";")[i].substring(1).split(",").length;j++)
	        {
	            dataSeries[j]=+paramValues.split(";")[i].substring(1).split(",")[j];
	        }
	 
	        seriesData.push({
	            name: paramNames.split(",")[i],      // adding multiple series(params) values to graph
	            data: dataSeries ,                                     
	            step:true
	        });
	                                 
	    }
	
	    $(document).ready(function() {
	       var chart =Highcharts.chart({
	            chart: {
	                renderTo: 'load',
	                width: window.size,
	                //marginRight: 100,
	                marginBottom: 55,
	                width:window.size,
//	                borderColor: "#4572A7",
//	                borderRadius: 15,
//	                borderWidth: 1,
	                //margin: [80, 80, 160,160],
	                zoomType : 'x'
	                                        
	            },
	            title:{
	            	text:''
	            },
	            subtitle: {
	                text: 'Click and drag in the plot area to zoom in',
	                x: -20 //center
	            },
	            plotOptions:{
	                lineWidth: 80

	            },
	            scrollbar: {
	                enabled: true
	            },
	                                     
	            xAxis: {
	            	reversed: false,
	                gridLineWidth: 1,
	                title: {
	                    text: 'Time(hh:mm)'

	                },
	                categories: xvalues.split(",")              
	                                               
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
	                align: 'center',
	                verticalAlign: 'top',
	                x: 1,
	                y: 20,
	                floating: true,
	                shadow: true
	            },

	                                       
	            series: seriesData,
	            credits : {
					enabled : false
				}
	        });
	    });
	    paramNames={};
	    paramValues={};
}
Highcharts.theme = {
	    colors: ['#f45b5b', '#8085e9', '#8d4654', '#7798BF', '#aaeeee',
	        '#ff0066', '#eeaaee', '#55BF3B', '#DF5353', '#7798BF', '#aaeeee'],
	    chart: {
	        backgroundColor: null,
	        style: {
	            fontFamily: 'Signika, serif'
	        }
	    },
	    title: {
	        style: {
	            color: 'black',
	            fontSize: '16px',
	            fontWeight: 'bold'
	        }
	    },
	    subtitle: {
	        style: {
	            color: 'black'
	        }
	    },
	    tooltip: {
	        borderWidth: 0
	    },
	    legend: {
	        itemStyle: {
	            fontWeight: 'bold',
	            fontSize: '13px'
	        }
	    },
	    xAxis: {
	        labels: {
	            style: {
	                color: '#6e6e70'
	            }
	        }
	    },
	    yAxis: {
	        labels: {
	            style: {
	                color: '#6e6e70'
	            }
	        }
	    },
	    plotOptions: {
	        series: {
	            shadow: true
	        },
	        candlestick: {
	            lineColor: '#404048'
	        },
	        map: {
	            shadow: false
	        }
	    },

	    // Highstock specific
	    navigator: {
	        xAxis: {
	            gridLineColor: '#D0D0D8'
	        }
	    },
	    rangeSelector: {
	        buttonTheme: {
	            fill: 'white',
	            stroke: '#C0C0C8',
	            'stroke-width': 1,
	            states: {
	                select: {
	                    fill: '#D0D0D8'
	                }
	            }
	        }
	    },
	    scrollbar: {
	        trackBorderColor: '#C0C0C8'
	    },

	    // General
	    background2: '#E0E0E8'

	};

	// Apply the theme
	Highcharts.setOptions(Highcharts.theme);
	
//------------phasor diagram -------------------------
	function toDegrees (angle) {
	    
		  return (angle*(180/Math.PI));
		}
	function getPhaseGuage_360(rphPF,yphPF,bphPF,Vr,Vy,Vb,Ir,Iy,Ib,RY,RB)
	{ 
		
	   //alert(Math.min(Vr,Vy,Vb)+"-------"+ parseFloat(yphPF));
	  // alert(RY+" "+RB);
	    var ang1=0,ang2=0,ang3=0;   
	        ang1 =  toDegrees(Math.acos(Math.abs(rphPF)));  
	        ang2 =  toDegrees(Math.acos(Math.abs(yphPF)));   
	        ang3 =  toDegrees(Math.acos(Math.abs(bphPF)));
	    
	     maxV=Math.max(Vr,Vy,Vb);
	     maxC=Math.max(Ir,Iy,Ib);
	     vrAmp=(Vr/maxV)*100; vyAmp=(Vy/maxV)*100; vbAmp=(Vb/maxV)*100;
	     IrAmp=(Ir/maxC)*100; IyAmp=(Iy/maxC)*100; IbAmp=(Ib/maxC)*100;
	     $("#phaserValues").html('&theta;(<font color="#990000">Vr </font>-<font color="#FF9933">Vy</font>)='+RY+' <br/>'+
                 '&theta;(<font color="#990000">Vr </font>-<font color="#6699FF">Vb</font>) ='+RB+'<br/>'+
                 '&theta;(<font color="#990000">Vr </font>-<font color="red">IR</font>)='+parseInt(ang1)+'<br/>'+
                 '&theta;(<font color="#FF9933">Vy </font>-<font color="#E6B800">IY</font>)='+parseInt(ang2)+'<br/>'+
                 '&theta;(<font color="#6699FF">Vb </font>-<font color="blue">IB</font>) ='+parseInt(ang3));
	    
	     Highcharts.chart('voltages', {

	    	    chart: {
	    	        type: 'gauge',
	    	        plotBackgroundColor: null,
	    	        plotBackgroundImage: null,
	    	        plotBorderWidth: 0,
	    	        plotShadow: false,
	    	        margin:[-5,-5,0,-5],
	    	        marginTop: 25,
	    	        width:140,
	    	        height:155
	    	    },

	    	    title: {
	    	        text: '<h5 style="color:#3B5998;">Voltages </h5>',
	    	        style: {
	    	            fontSize: '14px',
	    	            fontColor:'#3B5998'
	    	        }
	    	    },
	    	    credits:{
	    	    	enabled:false
	    	    },
	    	    pane: {
	    	        startAngle: -150,
	    	        endAngle: 150,
	    	        background: [{
	    	            backgroundColor: {
	    	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	    	                stops: [
	    	                    [0, '#FFF'],
	    	                    [1, '#333']
	    	                ]
	    	            },
	    	            borderWidth: 0,
	    	            outerRadius: '109%'
	    	        }, {
	    	            backgroundColor: {
	    	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	    	                stops: [
	    	                    [0, '#333'],
	    	                    [1, '#FFF']
	    	                ]
	    	            },
	    	            borderWidth: 1,
	    	            outerRadius: '107%'
	    	        }, {
	    	            // default background
	    	        }, {
	    	            backgroundColor: '#DDD',
	    	            borderWidth: 0,
	    	            outerRadius: '105%',
	    	            innerRadius: '103%'
	    	        }]
	    	    },

	    	    // the value axis
	    	    yAxis: {
	    	        min: Math.min(parseFloat(Vr), parseFloat(Vy), parseFloat(Vb))-1,
	    	        max: Math.max(parseFloat(Vr), parseFloat(Vy), parseFloat(Vb))+1,

	    	        minorTickInterval: 'auto',
	    	        minorTickWidth: 1,
	    	        minorTickLength: 10,
	    	        minorTickPosition: 'inside',
	    	        minorTickColor: '#666',

	    	        tickPixelInterval: 30,
	    	        tickWidth: 2,
	    	        tickPosition: 'inside',
	    	        tickLength: 10,
	    	        tickColor: '#666',
	    	        labels: {
	    	            step: 2,
	    	            rotation: 'auto'
	    	        },
	    	        title: {
	    	            text: ''
	    	        }
	    	    },

	    	    series: [{
	    	        name: 'Speed',
	    	        color:'red',
	    	        data: [parseFloat(Vr)],
	    	        dataLabels: {
	    	            enabled: false
	    	        },
	    	        dial: {
	    	            backgroundColor: 'red'
	    	        },
	    	        tooltip: {
	    	            valueSuffix: ' volts'
	    	        }},{
	    	        name: 'y',
	    	        color:'yellow',
	    	        data: [parseFloat(Vy)],
	    	        dataLabels: {
	    	            enabled: false
	    	        },
	    	        dial: {
	    	            backgroundColor: 'yellow'
	    	        },
	    	        tooltip: {
	    	            valueSuffix: ' volts'
	    	        }},
	    	        {
	    	        name: 'b',
	    	        color:'blue',
	    	        dataLabels: {
	    	            enabled: false
	    	        },
	    	        data: [parseFloat(Vb)],
	    	        dial: {
	    	            backgroundColor: 'blue'
	    	        },
	    	        tooltip: {
	    	            valueSuffix: ' volts'
	    	        }
	    	    }]


	    	});	     
	     
//-----------------------------currents------------------------------------------------
	     Highcharts.chart('currents', {

	    	    chart: {
	    	        type: 'gauge',
	    	        plotBackgroundColor: null,
	    	        plotBackgroundImage: null,
	    	        plotBorderWidth: 0,
	    	        plotShadow: false,
	    	        margin:[-5,-5,0,-5],
	    	        marginTop: 25,
	    	        width:140,
	    	        height:155
	    	    },

	    	    title: {
	    	        text: '<h5 style="color:#3B5998;">Currents </h5>',
	    	      
	    	        style: {
	    	            fontSize: '14px',
	    	            fontColor:'#3B5998'
	    	        }
	    	    },
	    	    
	    	    credits:{
	    	    	enabled:false
	    	    },
	    	    pane: {
	    	        startAngle: -150,
	    	        endAngle: 150,
	    	        background: [{
	    	            backgroundColor: {
	    	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	    	                stops: [
	    	                    [0, '#FFF'],
	    	                    [1, '#333']
	    	                ]
	    	            },
	    	            borderWidth: 0,
	    	            outerRadius: '109%'
	    	        }, {
	    	            backgroundColor: {
	    	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	    	                stops: [
	    	                    [0, '#333'],
	    	                    [1, '#FFF']
	    	                ]
	    	            },
	    	            borderWidth: 1,
	    	            outerRadius: '107%'
	    	        }, {
	    	            // default background
	    	        }, {
	    	            backgroundColor: '#DDD',
	    	            borderWidth: 0,
	    	            outerRadius: '105%',
	    	            innerRadius: '103%'
	    	        }]
	    	    },

	    	    // the value axis
	    	    yAxis: {
	    	    	min: Math.min(parseFloat(Ir), parseFloat(Iy), parseFloat(Ib))-1,
	    	        max: Math.max(parseFloat(Ir), parseFloat(Iy), parseFloat(Ib))+1,

	    	        minorTickInterval: 'auto',
	    	        minorTickWidth: 1,
	    	        minorTickLength: 10,
	    	        minorTickPosition: 'inside',
	    	        minorTickColor: '#666',

	    	        tickPixelInterval: 30,
	    	        tickWidth: 2,
	    	        tickPosition: 'inside',
	    	        tickLength: 10,
	    	        tickColor: '#666',
	    	        labels: {
	    	            step: 2,
	    	            rotation: 'auto'
	    	        },
	    	        title: {
	    	            text: ''
	    	        },
	    	        
	    	    },

	    	    series: [{
	    	        name: 'Speed',
	    	        color:'red',
	    	        data: [parseFloat(Ir)],
	    	        dataLabels: {
	    	            enabled: false
	    	        },
	    	        dial: {
	    	            backgroundColor: 'red'
	    	        },
	    	        tooltip: {
	    	            valueSuffix: ' amps'
	    	        }},{
	    	        name: 'y',
	    	        color:'yellow',
	    	        data: [parseFloat(Iy)],
	    	        dataLabels: {
	    	            enabled: false
	    	        },
	    	        dial: {
	    	            backgroundColor: 'yellow'
	    	        },
	    	        tooltip: {
	    	            valueSuffix: ' amps'
	    	        }},
	    	        {
	    	        name: 'b',
	    	        color:'blue',
	    	        data: [parseFloat(Ib)],
	    	        dataLabels: {
	    	            enabled: false
	    	        },
	    	        dial: {
	    	            backgroundColor: 'blue'
	    	        },
	    	        tooltip: {
	    	            valueSuffix: ' amps'
	    	        }
	    	    }]


	    	});
	     
	    if(Math.min(Vr,Vy,Vb)>50){
	        
	  

	    chart = new Highcharts.Chart({

	        chart: {
	            renderTo: 'pfs',
	            type: 'gauge',
	            plotBackgroundColor: null,
	            plotBackgroundImage: null,
	            plotBorderWidth: 0,
	            plotShadow: false,
	            margin:[-5,-5,-5,-5],
	            marginTop: 25,
	            width:160,
	            height:155
	                      
	            
	        },
	        tooltip: {
	            enabled: false
	        },
	        title: {
	           
	            text: '<h5 style="color:#3B5998;">Phaser </h5>',
//	                  '&theta;(<font color="#990000">Vr </font>-<font color="#FF9933">Vy</font>)'+RY+'<br />'+
//	                  '&theta;(<font color="#990000">Vr </font>-<font color="#6699FF">Vb</font>) '+RB+'<br />'+
//	                  '&theta;(<font color="#990000">Vr </font>-<font color="red">IR</font>)'+parseInt(ang1)+'<br />'+
//	                  '&theta;(<font color="#FF9933">Vy </font>-<font color="#E6B800">IY</font>)'+parseInt(ang2)+'<br />'+
//	                  '&theta;(<font color="#6699FF">Vb </font>-<font color="blue">IB</font>) '+parseInt(ang3),
	style:{
	textAlign:'left',
	fontSize:'14px',
	fontColor:'#3B5998'
	},
	align: 'left'
	        },

	        pane: {
	            startAngle: 0,
	            endAngle: 360,
	                  
	            background: [{
	                backgroundColor: {
	                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	                    stops: [
	                        [0, '#FFF'],
	                        [1, '#333']
	                    ]
	                },
	                borderWidth: 0,
	                outerRadius: '109%'
	            }, {
	                backgroundColor: {
	                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	                    stops: [
	                        [0, '#333'],
	                        [1, '#FFF']
	                    ]
	                },
	                borderWidth: 1,
	                outerRadius: '107%'
	            }, {
	                // default background
	            }, {
	                backgroundColor: '#DDD',
	                borderWidth: 0,
	                outerRadius: '105%',
	                innerRadius: '103%'
	            }]
	           
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
	                distance: 15,
	                rotation: 'auto',
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
	



	    }
	     if(Math.min(Vr,Vy,Vb)==0 && parseFloat(yphPF)==0 && !((parseFloat(Vy)==1 || parseFloat(Vy)==0) && parseFloat(Vb)==0)){
	    // if(!(Math.round(Vy)==0 && Math.round(Vb)==0)){
	  
	    
	    var chart = new Highcharts.Chart({
	    
	        chart: {
	            renderTo: 'pfs',
	            type: 'gauge',
	            plotBackgroundColor: null,
	            plotBackgroundImage: null,
	            plotBorderWidth: 0,
	            plotShadow: false,
	            marginLeft: -5,
	            marginTop: 25,
	            width:140,
	            height:170
	             
	        },
	        
	       tooltip: {
	            enabled: false
	        },
	        title: {
	           
	            text: '<h5 style="color:#3B5998;">Phaser </h5>',
//	                  '&theta;(<font color="#990000">Vr </font>-<font color="#FF9933">Vy</font>)'+RY+'<br />'+
//	                  '&theta;(<font color="#990000">Vr </font>-<font color="#6699FF">Vb</font>) '+RB+'<br />'+
//	                  '&theta;(<font color="#990000">Vr </font>-<font color="red">IR</font>)'+parseInt(ang1)+'<br />'+
//	                  '&theta;(<font color="#FF9933">Vy </font>-<font color="#E6B800">IY</font>)'+parseInt(ang2)+'<br />'+
//	                  '&theta;(<font color="#6699FF">Vb </font>-<font color="blue">IB</font>) '+parseInt(ang3),
	style:{
	textAlign:'left',
	fontSize:'14px',
	fontColor:'#3B5998'
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
	
	    }
	    
	    else{
	    //document.getElementById("phaseGuage").innerHTML="Phase voltage Missing";
	    
	    }
	}        
//---------------------end of phasor -----------
	
	function callDataTableNoExport(tableId){
		 $('#'+tableId).DataTable( {
			    "searching":false,
			    "scrollY":        "250px",
		        "scrollCollapse": true,
		        "paging":         false,
		        "info":     false
		    } );
	}
function getAnalysisHome(actionId,report){
	hideDashboardDiv();	
	$.ajax( {
        url:'analysisHome.epx?report='+report,
        beforeSend: function(msg){
        	$('#datapart').html($('#loading').html());
          }, 
        success:function(data) {
           $('#datapart').html(data);
          
        }
     });
}	
function getAnalysisReport(meter,report){
	var urlStr="";
	if(report=="Consumer Performance")
		urlStr='PeakLoadViolation.epx?meter='+meter+'&year='+$("#year").val()
	if(report=="Meter Asset Mgmt")
		urlStr="MeterAssetMgmtHome.epx?meter="+meter;
	if(report=="Analytics")
		urlStr="AnalyticsHome.epx?meter="+meter;
	
	$.ajax( {
        url:urlStr,
        beforeSend: function(msg){
        	$('#result_div').html($('#loading').html());
          }, 
        success:function(data) {
           $('#result_div').html(data);
          
        }
     });
}
function getMeterAssetMgmtHome(meter,report){
	if(report=="loadfactor")
		urlStr="loadfactor.epx";
	if(report=="demandfactor")
		urlStr="demandfactor.epx";
	
	$.ajax( {
        url:urlStr+'?meter='+meter+'&year='+$("#year").val(),
        beforeSend: function(msg){
        	$('#reports_div').html($('#loading').html());
          }, 
        success:function(data) {
           $('#reports_div').html(data);
          
        }
     });
}

function getAnalyticsHome(meter,report){
	var filename="";
	if(report=="billing"){
		urlStr="analytics/billing.epx?meter="+meter
		filename="billing";
	}
	if(report=="dailyenergy"){
		urlStr="analytics/dailyenergy.epx?month="+$("#month").val()+"&meter="+meter;
		filename="daily_energies"
	}
	if(report=="events"){
		var from=$("#fromDate").val();
		var to=$("#toDate").val();
//		from=from.replace('-','/').replace('-','/');
//		to=to.replace('-','/').replace('-','/');
		
		urlStr="analytics/poweronoff1.epx?meter="+meter+"&fromD="+from+"&toD="+to;
		filename="events"
	}
	if(report=="poweronoff"){
		urlStr="analytics/poweronoff.epx?meter="+meter;
		filename="poweronoff"
	}
	if(report=="load"){
		urlStr="analytics/load.epx?month="+$("#lmonth").val()+"&meter="+meter;
		filename="load_profile"
	}
	//alert(urlStr);
	$.ajax( {
        url:urlStr,
        beforeSend: function(msg){
        	$('#reports_div').html($('#loading').html());
          }, 
        success:function(data) {
           $('#reports_div').html(data);
           callDataTableWithName('table1',filename);
        }
     });
}

function getTotalBilling(meter,datetime){
	window.open('getAvonTotalBilling.jsp?meter='+meter+'&datetime='+datetime, "_blank");
	
}
function getTotalInstant(meter,datetime){
	window.open('getAvonTotalInstant.jsp?meter='+meter+'&datetime='+datetime, "_blank");
	
}