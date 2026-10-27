function getDashBoard_1(actionId) {
	clear();
	hideDashboardDiv();
	$("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');  
	
	$.ajax( {
        url:'Dashboard1/dashHome.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
          // callDataTable('table1');
        }
     });
}
function getLiveStatusNew(actionId){
	clear();
	hideDashboardDiv();	
	$.ajax( {
        url:'live/livestatus.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
//            getLiveCommStatus_1();
//            getLiveInstant();
        }
     });
}
function getLiveInstant(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	$.ajax( {
        url:'getAllInstant_live.jsp?level='+level+'&levelID='+levelID,
        beforeSend:function(){
        	document.getElementById("liveStatus").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#liveStatus').html(data);
           callDataTableWithName('allinstant','LiveStatus');
           getLiveCommStatus_1();
        }
     });
}
function callAllfunctions(){
	//getLiveCommStatus();
	getDayCommStatus();
	get1week_commstatus();
	getSignalGraph();
	
	
	getLoadCurve();
    //getSupplyInfo();
    get11kvFeedersStatus();
    get33kvFeedersStatus();
    getPowerFails();
}
function getDayCommStatus(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	$.ajax( {
        url:'Dashboard1/getdayLiveCommStatus.epx?level='+level+'&levelID='+levelID+'&date='+$("#dashdate").val(),
        beforeSend:function(){
        	//document.getElementById("day_status").innerHTML=document.getElementById("loader").innerHTML;
        	document.getElementById("live_status").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	//document.getElementById("day_status").innerHTML=data;
        	document.getElementById("live_status").innerHTML=data;
        	Circlle('.round','#5cffae'); //11kv
        	Circlle('.33kv','#ffd15c'); //33kv
        	Circlle('.total','#5cceff'); //total
        	Circlle('.notcomm','#ff9d5c'); //not comm
        }
     });
}
function getLiveCommStatus(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	$.ajax( {
        url:'Dashboard1/getLiveCommStatus.epx?level='+level+'&levelID='+levelID+'&date='+$("#dashdate").val(),
        beforeSend:function(){
        	document.getElementById("live_status").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	document.getElementById("live_status").innerHTML=data;
        	Circlle('.round','#5cffae'); //11kv
        	Circlle('.33kv','#ffd15c'); //33kv
        	Circlle('.total','#5cceff'); //total
        	Circlle('.notcomm','#ff9d5c'); //not comm
        }
     });
}
function getLiveCommStatus_1(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	$.ajax( {
        url:'live/getLiveCommStatus.epx?level='+level+'&levelID='+levelID+'&date='+$("#dashdate").val(),
        beforeSend:function(){
        	document.getElementById("live_status").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	document.getElementById("live_status").innerHTML=data;
        	Circlle('.round','#5cffae'); //11kv
        	Circlle('.33kv','#ffd15c'); //33kv
        	Circlle('.total','#5cceff'); //total
        	Circlle('.notcomm','#ff9d5c'); //not comm
        }
     });
}
function getLoadCurve(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	$.ajax( {
        url:'Dashboard1/loadCurve.epx?level='+level+'&levelID='+levelID+'&date='+$("#dashdate").val(),
        beforeSend:function(){
        	document.getElementById("load_curve").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	console.log(data.split(";")[0]);
        	getLoadCurveAction(data.split(";")[0],data.split(";")[1],data.split(";")[2]);
        }
     });
}
function getLoadCurveAction(ag,nonag,datestr){
	// Themes begin
	am4core.useTheme(am4themes_animated);
	// Themes end

	// Create chart instance
	var chart = am4core.create("load_curve", am4charts.XYChart);
    
//	var title = chart.titles.create();
//	title.text = "Load Curve";
//	title.fontSize = 15;
	
//	chart.svgContainer.htmlElement.style.height = "250px";
//	chart.svgContainer.htmlElement.style.marginBottom="-50px";
	// Increase contrast by taking evey second color
	chart.colors.step = 2;

	// Add data
	chart.data = generateChartData();

	// Create axes
	var dateAxis = chart.xAxes.push(new am4charts.DateAxis());
	dateAxis.baseInterval = {
			  "timeUnit": "minute",
			  "count": 1
			};
	dateAxis.tooltipDateFormat = "HH:mm, d MMMM";
	dateAxis.renderer.minGridDistance = 50;

	// Create series
	function createAxisAndSeries(field, name, opposite, bullet) {
	  var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
	  if(chart.yAxes.indexOf(valueAxis) != 0){
	  	//valueAxis.syncWithAxis = chart.yAxes.getIndex(0);
	  }
	  
	  var series = chart.series.push(new am4charts.LineSeries());
	  series.dataFields.valueY = field;
	  series.dataFields.dateX = "date";
	  series.strokeWidth = 2;
	  if(name=="Total"){
	    series.yAxis = valueAxis;
	  }
	  
	  series.name = name;
	  series.tooltipText = "{name}: [bold]{valueY} MW[/]";
	  series.tensionX = 0.8;
	  series.showOnInit = true;
	  
	  var interfaceColors = new am4core.InterfaceColorSet();
	  
	  switch(bullet) {
	    case "triangle":
	      var bullet = series.bullets.push(new am4charts.Bullet());
	      bullet.width = 12;
	      bullet.height = 12;
	      bullet.horizontalCenter = "middle";
	      bullet.verticalCenter = "middle";
	      
	      var triangle = bullet.createChild(am4core.Triangle);
	      triangle.stroke = interfaceColors.getFor("background");
	      //triangle.stroke ="#a367dc";
	      triangle.strokeWidth = 2;
	      triangle.direction = "top";
	      triangle.width = 12;
	      triangle.height = 12;
	      break;
	    case "rectangle":
	      var bullet = series.bullets.push(new am4charts.Bullet());
	      bullet.width = 10;
	      bullet.height = 10;
	      bullet.horizontalCenter = "middle";
	      bullet.verticalCenter = "middle";
	      
	      var rectangle = bullet.createChild(am4core.Rectangle);
	      rectangle.stroke = interfaceColors.getFor("background");
	      //rectangle.stroke ="#67b7dc";
	      rectangle.strokeWidth = 2;
	      rectangle.width = 10;
	      rectangle.height = 10;
	      break;
	    default:
	      var bullet = series.bullets.push(new am4charts.CircleBullet());
	      bullet.width = 1;
	      bullet.height = 1;
	      
	      bullet.circle.stroke = interfaceColors.getFor("background");
	      //bullet.circle.stroke ="#6771dc";
	      bullet.circle.strokeWidth = 1;
	      bullet.circle.width = 1;
	      bullet.circle.height = 1;
	      break;
	  }
	  
	  valueAxis.renderer.line.strokeOpacity = 1;
	  valueAxis.renderer.line.strokeWidth = 2;
	  valueAxis.renderer.line.stroke = series.stroke;
	  valueAxis.renderer.labels.template.fill = series.stroke;
	  valueAxis.renderer.opposite = opposite;
	}

	createAxisAndSeries("tot", "Total", false, "rectangle");
	createAxisAndSeries("ag", "AG", true, "circle");
	createAxisAndSeries("nonag", "Non-AG", true, "triangle");
	

	// Add legend
	chart.legend = new am4charts.Legend();
//	chart.legend.maxHeight = 150;
//	chart.legend.scrollable = true;
	// Add cursor
	chart.cursor = new am4charts.XYCursor();

	// generate some random data, quite different range
	function generateChartData() {
	  var chartData = [];
	  //var firstDate = new Date();
	 
	  var firstDate=new Date(datestr.split("-")[2],datestr.split("-")[1]-1,datestr.split("-")[0]);
	  //firstDate.setDate(firstDate.getDate() - 1);
	  firstDate.setHours(0, 0, 0, 0);

	  var visits = 0;
	  var hits = 0;
	  var views = 0;

	  for (var i = 0; i < ag.split(",").length; i++) {
	    // we create date objects here. In your data, you can have date strings
	    // and then set format of your dates using chart.dataDateFormat property,
	    // however when possible, use date objects, as this will speed up chart rendering.
	    var newDate = new Date(firstDate);
	    newDate.setHours(newDate.getHours() + i);

	    chartData.push({
	      date: newDate,
	      ag: parseFloat(ag.split(",")[i]).toFixed(2),	      
	      nonag: parseFloat(nonag.split(",")[i]).toFixed(2),
	      tot: (parseFloat(ag.split(",")[i])+parseFloat(nonag.split(",")[i])).toFixed(2)
//	      ag: Math.round(ag.split(",")[i]),	      
//	      nonag: Math.round(nonag.split(",")[i]),
//	      tot: Math.round(ag.split(",")[i])+Math.round(nonag.split(",")[i])
	    });
	  }
	  return chartData;
	}
}

function getSignalGraph(){
	$.ajax( {
        url:'Dashboard1/signalGraph.epx',
        beforeSend:function(){
        	document.getElementById("load_curve").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	
        	//getSignalGraphAction(data.split(";")[0],data.split(";")[1]);
        	getSignalGraphAction(data);
        }
     });
}
function getSignalGraphAction(data){
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_animated);
		// Themes end

		// Create chart instance
		var chart = am4core.create("signal_strength", am4charts.XYChart3D);
		chart.colors.list = [
		                     am4core.color("#5faa46"),
		                     am4core.color("#aec83d"),
		                     am4core.color("#e5dc36"),
		                     am4core.color("#FF9671"),
		                     am4core.color("#FFC75F"),
		                     am4core.color("#F9F871")
		                   ];
		// Add data
		chart.data = [{
			  "country": "Good",
			  "visits": data.split(",")[0],
			  
			}, {
			  "country": "Moderate",
			  "visits": data.split(",")[1]
			}, {
			  "country": "Low",
			  "visits": data.split(",")[2]
			}];

		// Create axes
		let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "country";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.minGridDistance = 30;
		//categoryAxis.renderer.labels.template.rotation = 270;
		//categoryAxis.renderer.labels.template.hideOversized = false;
		categoryAxis.renderer.inside = true;
		categoryAxis.renderer.labels.template.valign = "top";
		//categoryAxis.renderer.minGridDistance = 20;
		//categoryAxis.renderer.labels.template.horizontalCenter = "right";
		//categoryAxis.renderer.labels.template.verticalCenter = "middle";
		//categoryAxis.tooltip.label.rotation = 270;
		//categoryAxis.tooltip.label.horizontalCenter = "right";
		//categoryAxis.tooltip.label.verticalCenter = "middle";
		//categoryAxis.renderer.labels.template.disabled = true;
		
		let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = "No.of modems";
		//valueAxis.title.fontWeight = "bold";

		// Create series
		//var series = chart.series.push(new am4charts.ColumnSeries());
		var series = chart.series.push(new am4charts.ConeSeries());
		series.dataFields.valueY = "visits";
		series.dataFields.categoryX = "country";
		//series.name = "country";
		series.tooltipText = "{categoryX}: [bold]{valueY}[/]";
		series.columns.template.fillOpacity = .9;
		series.columns.template.events.on("hit", function(ev) {
			 console.log("clicked on ", ev.target.dataItem.dataContext.country);
			 downloadSignalGraphValues(ev.target.dataItem.dataContext.country);
			}, this);
		
		var columnTemplate = series.columns.template;
		columnTemplate.strokeWidth = 2;
		columnTemplate.strokeOpacity = 1;
		columnTemplate.stroke = am4core.color("#FFFFFF");

		columnTemplate.adapter.add("fill", function(fill, target) {
		  return chart.colors.getIndex(target.dataItem.index);
		})

		columnTemplate.adapter.add("stroke", function(stroke, target) {
		  return chart.colors.getIndex(target.dataItem.index);
		})

		
		chart.cursor = new am4charts.XYCursor();
		chart.cursor.lineX.strokeOpacity = 0;
		chart.cursor.lineY.strokeOpacity = 0;
//-------------------------------------------------------------------
//		var legend = new am4charts.Legend();
//		legend.parent = chart.chartContainer;
//		legend.itemContainers.template.togglable = false;
//		legend.marginTop = 20;
//
//		series.events.on("ready", function(ev) {
//		  var legenddata = [];
//		  series.columns.each(function(column) {
//		    legenddata.push({
//		      name: column.dataItem.categoryX,
//		      fill: column.fill
//		    })
//		  });
//		  legend.data = legenddata;
//		});
//-------------------------------------------------		

		}); // end am4core.ready()
}
function downloadSignalGraphValues(type){
	location.href="Dashboard1/downloadSignalGraphValues.epx?type="+type;
}
function getSignalGraphAction_bkp(xaxis,yaxis){
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_animated);
		// Themes end

		// Create chart instance
		var chart = am4core.create("signal_strength", am4charts.XYChart);
		//chart.paddingRight = 20;
		chart.padding(0, 0, 0, 0);

		chart.data = generateChartData();
		
		function generateChartData() {
			  var chartData = [];
			 
			  for (var i = 0; i < xaxis.split(",").length; i++) {
			    
			    chartData.push({
			    	"year": xaxis.split(",")[i],
					 "value": yaxis.split(",")[i]
			    });
			  }
			  return chartData;
			}
		// Add data
//		chart.data = [{
//		  "year": "1950",
//		  "value": -0.307
//		}];

		// Create axes
		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "year";
		categoryAxis.renderer.minGridDistance = 50;
		categoryAxis.renderer.grid.template.location = 0.5;
		categoryAxis.startLocation = 0.5;
		categoryAxis.endLocation = 0.5;

		// Create value axis
		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.baseValue = 0;

		// Create series
		var series = chart.series.push(new am4charts.LineSeries());
		series.dataFields.valueY = "value";
		series.dataFields.categoryX = "year";
		series.strokeWidth = 2;
		series.tensionX = 0.77;
		

		// bullet is added because we add tooltip to a bullet for it to change color
		var bullet = series.bullets.push(new am4charts.Bullet());
		bullet.tooltipText = "{valueY}";

		bullet.adapter.add("fill", function(fill, target){
		    if(target.dataItem.valueY < 10){
		        return am4core.color("#FF0000");
		    }
		    return fill;
		})
		var range = valueAxis.createSeriesRange(series);
		range.value = 0;
		range.endValue =10;
		range.contents.stroke = am4core.color("#FF0000");
		range.contents.fill = range.contents.stroke;
		
		bullet.adapter.add("fill", function(fill, target){
		    if(target.dataItem.valueY >10 && target.dataItem.valueY < 20){
		        return am4core.color("#f2ff00");
		    }
		    return fill;
		})
		var range = valueAxis.createSeriesRange(series);
		range.value = 10;
		range.endValue =20;
		range.contents.stroke = am4core.color("#f2ff00");
		range.contents.fill = range.contents.stroke;

		// Add scrollbar
//		var scrollbarX = new am4charts.XYChartScrollbar();
//		scrollbarX.series.push(series);
//		chart.scrollbarX = scrollbarX;

		chart.cursor = new am4charts.XYCursor();

		}); // end am4core.ready()
}


function get1week_commstatus(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	$.ajax( {
        url:'Dashboard1/weekStatus.epx?level='+level+'&levelID='+levelID+'&date='+$("#dashdate").val(),
        beforeSend:function(){
        	document.getElementById("1week_commstatus").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	
        	get1week_commstatusAction(data.split(";")[0],data.split(";")[1]);
        }
     });
}
function get1week_commstatusAction(dates,vals){
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_animated);
		// Themes end

		// Create chart instance
		var chart = am4core.create("1week_commstatus", am4charts.XYChart3D);
		chart.colors.list = [
		                     am4core.color("#5faa46"),
		                     am4core.color("#aec83d"),
		                     am4core.color("#e5dc36"),
		                     am4core.color("#FF9671"),
		                     am4core.color("#FFC75F"),
		                     am4core.color("#F9F871")
		                   ];
		// Add data
		var data = [];
		var value = 0;

		

		for (var i = 0; i < dates.split(",").length; i++) {
		  
		  data.push({ country: dates.split(",")[i], visits: parseFloat(vals.split(",")[i]) });
		}

		chart.data = data;
		// Create axes
		let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "country";
		
		//categoryAxis.renderer.labels.template.rotation = 270;
		categoryAxis.renderer.labels.template.hideOversized = false;
		//categoryAxis.renderer.minGridDistance = 20;
		//categoryAxis.renderer.labels.template.horizontalCenter = "right";
		//categoryAxis.renderer.labels.template.verticalCenter = "middle";
		//categoryAxis.tooltip.label.rotation = 270;
		//categoryAxis.tooltip.label.horizontalCenter = "right";
		//categoryAxis.tooltip.label.verticalCenter = "middle";
		categoryAxis.renderer.labels.template.disabled = true;
		
		let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = "";
		//valueAxis.title.fontWeight = "bold";

		// Create series
		//var series = chart.series.push(new am4charts.ColumnSeries());
		var series = chart.series.push(new am4charts.ConeSeries());
		series.dataFields.valueY = "visits";
		series.dataFields.categoryX = "country";
		series.name = "Visits";
		series.tooltipText = "{categoryX}: [bold]{valueY}%[/]";
		series.columns.template.fillOpacity = .9;

		var columnTemplate = series.columns.template;
		columnTemplate.strokeWidth = 2;
		columnTemplate.strokeOpacity = 1;
		columnTemplate.stroke = am4core.color("#FFFFFF");

		columnTemplate.adapter.add("fill", function(fill, target) {
		  return chart.colors.getIndex(target.dataItem.index);
		})

		columnTemplate.adapter.add("stroke", function(stroke, target) {
		  return chart.colors.getIndex(target.dataItem.index);
		})

		chart.cursor = new am4charts.XYCursor();
		chart.cursor.lineX.strokeOpacity = 0;
		chart.cursor.lineY.strokeOpacity = 0;

		}); // end am4core.ready()
}

function getSupplyInfo(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	$.ajax( {
        url:'Dashboard1/supplyInfo.epx?level='+level+'&levelID='+levelID+'&date='+$("#dashdate").val(),
        beforeSend:function(){
//        	document.getElementById("avg_agl_supply").innerHTML=document.getElementById("loader").innerHTML;
//        	document.getElementById("avg_nonagl_supply").innerHTML=document.getElementById("loader").innerHTML;
//        	document.getElementById("deviation_agl_supply").innerHTML=document.getElementById("loader").innerHTML;
//        	document.getElementById("deviation_nonagl_supply").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	console.log(data);
      //-----------------------------------------------------------------------------------------------------
        	document.getElementById("avg_ag_supply").innerHTML=parseFloat(data.split(";")[0].split(",")[0]).toFixed(2)+" / "+parseInt(data.split(";")[0].split(",")[1]);
        	document.getElementById("avg_nonag_supply").innerHTML=parseFloat(data.split(";")[1].split(",")[0]).toFixed(2)+" / "+parseInt(data.split(";")[1].split(",")[1]);
        	
        	document.getElementById("ag_11_supply").innerHTML=parseInt(data.split(";")[3].split(",")[1]);
        	document.getElementById("ag_9_supply").innerHTML=parseInt(data.split(";")[3].split(",")[0]);
        	document.getElementById("nonag_20_supply").innerHTML=parseInt(data.split(";")[2].split(",")[0]);
        	document.getElementById("nonag_gr20_supply").innerHTML=parseInt(data.split(";")[2].split(",")[1]);
      //------------------------------------------------------------------------------------------------------  	
//        	getAvgAglsupply(data.split(";")[0]);
//        	getAvgNonaglsupply(data.split(";")[1]);
//        	getDeviationNonaglsupply(data.split(";")[2]);
//        	getDeviationAglsupply(data.split(";")[3]);
        }
     });
}
function getAvgAglsupply(val){
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_animated);
		// Themes end

		// create chart
		var chart = am4core.create("avg_agl_supply", am4charts.GaugeChart);
		chart.hiddenState.properties.opacity = 0;

		var title = chart.titles.create();
		title.text = "AG avg supply Hrs";
		title.fontSize = 15;
		
		

		var axis2 = chart.xAxes.push(new am4charts.ValueAxis());
		axis2.min = 0;
		axis2.max = 24;
		axis2.strictMinMax = true;

		axis2.renderer.line.strokeOpacity = 1;
		axis2.renderer.line.strokeWidth = 5;
		axis2.renderer.line.stroke = chart.colors.getIndex(0);
		axis2.renderer.ticks.template.stroke = chart.colors.getIndex(0);

		axis2.renderer.ticks.template.disabled = false
		axis2.renderer.ticks.template.strokeOpacity = 1;
		axis2.renderer.grid.template.disabled = true;
		axis2.renderer.ticks.template.length = 10;
		axis2.hiddenState.properties.opacity = 1;
		axis2.hiddenState.properties.visible = true;
		axis2.setStateOnChildren = true;
		axis2.renderer.hiddenState.properties.endAngle = 180;

		

		var hand2 = chart.hands.push(new am4charts.ClockHand());
		hand2.fill = axis2.renderer.line.stroke;
		hand2.stroke = axis2.renderer.line.stroke;
		hand2.axis = axis2;
		hand2.pin.radius = 10;
		hand2.startWidth = 10;

//		setInterval(function() {
//		  
//		  hand2.showValue(Math.random() * 160, 1000, am4core.ease.cubicOut);
//		  label2.text = Math.round(hand2.value).toString();
//		}, 2000);

		hand2.showValue(parseFloat(val.split(",")[0]), 1000, am4core.ease.cubicOut);
		//label2.text = Math.round(hand2.value).toString();
		
		var legend = new am4charts.Legend();
		legend.isMeasured = false;
		legend.y = am4core.percent(100);
		legend.verticalCenter = "bottom";
		legend.parent = chart.chartContainer;
		legend.data = [{
		  "name": "Avg supply Hrs ("+parseFloat(val.split(",")[0]).toFixed(2)+"/"+parseInt(val.split(",")[1])+")",
		  "fill": chart.colors.getIndex(0)
		}];

		legend.itemContainers.template.events.on("hit", function(ev) {
		  var index = ev.target.dataItem.index;

		  if (!ev.target.isActive) {
		    chart.hands.getIndex(index).hide();
		    chart.xAxes.getIndex(index).hide();
//		    labelList.getIndex(index).hide();
		  }
		  else {
		    chart.hands.getIndex(index).show();
		    chart.xAxes.getIndex(index).show();
//		    labelList.getIndex(index).show();
		  }
		});



		}); // end am4core.ready()
}

function getAvgNonaglsupply(val){
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_animated);
		// Themes end

		// create chart
		var chart = am4core.create("avg_nonagl_supply", am4charts.GaugeChart);
		chart.hiddenState.properties.opacity = 0;

		var title = chart.titles.create();
		title.text = "Non-AG avg supply Hrs";
		title.fontSize = 15;
		
		

		var axis2 = chart.xAxes.push(new am4charts.ValueAxis());
		axis2.min = 0;
		axis2.max = 24;
		axis2.strictMinMax = true;

		axis2.renderer.line.strokeOpacity = 1;
		axis2.renderer.line.strokeWidth = 5;
		axis2.renderer.line.stroke = chart.colors.getIndex(3);
		axis2.renderer.ticks.template.stroke = chart.colors.getIndex(3);

		axis2.renderer.ticks.template.disabled = false
		axis2.renderer.ticks.template.strokeOpacity = 1;
		axis2.renderer.grid.template.disabled = true;
		axis2.renderer.ticks.template.length = 10;
		axis2.hiddenState.properties.opacity = 1;
		axis2.hiddenState.properties.visible = true;
		axis2.setStateOnChildren = true;
		axis2.renderer.hiddenState.properties.endAngle = 180;

		
		var hand2 = chart.hands.push(new am4charts.ClockHand());
		hand2.fill = axis2.renderer.line.stroke;
		hand2.stroke = axis2.renderer.line.stroke;
		hand2.axis = axis2;
		hand2.pin.radius = 10;
		hand2.startWidth = 10;

//		setInterval(function() {
//		 
//		  hand2.showValue(Math.random() * 160, 1000, am4core.ease.cubicOut);
//		  label2.text = Math.round(hand2.value).toString();
//		}, 2000);
		hand2.showValue(parseInt(val.split(",")[0]), 1000, am4core.ease.cubicOut);

		var legend = new am4charts.Legend();
		legend.isMeasured = false;
		legend.y = am4core.percent(100);
		legend.verticalCenter = "bottom";
		legend.parent = chart.chartContainer;
		legend.data = [ {
		  "name": "Avg supply Hrs  ("+parseFloat(val.split(",")[0]).toFixed(2)+"/"+parseInt(val.split(",")[1])+")",
		  "fill": chart.colors.getIndex(3)
		}];

		legend.itemContainers.template.events.on("hit", function(ev) {
		  var index = ev.target.dataItem.index;

		  if (!ev.target.isActive) {
		    chart.hands.getIndex(index).hide();
		    chart.xAxes.getIndex(index).hide();
//		    labelList.getIndex(index).hide();
		  }
		  else {
		    chart.hands.getIndex(index).show();
		    chart.xAxes.getIndex(index).show();
//		    labelList.getIndex(index).show();
		  }
		});



		}); // end am4core.ready()
}

function getDeviationNonaglsupply(vals){
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_animated);
		// Themes end

		// create chart
		var chart = am4core.create("deviation_nonagl_supply", am4charts.GaugeChart);
		chart.hiddenState.properties.opacity = 0;

		var title = chart.titles.create();
		title.text = "Non-AG Deviation supply Hrs";
		title.fontSize = 15;
		
		var axis = chart.xAxes.push(new am4charts.ValueAxis());
		axis.min = 0;
		axis.max = parseInt(vals.split(",")[0])+50;
		axis.strictMinMax = true;
		axis.renderer.inside = true;
		//axis.renderer.ticks.template.inside = true;
		//axis.stroke = chart.colors.getIndex(3);
		axis.renderer.radius = am4core.percent(97);
		//axis.renderer.radius = 80;
		axis.renderer.line.strokeOpacity = 1;
		axis.renderer.line.strokeWidth = 5;
		axis.renderer.line.stroke = chart.colors.getIndex(0);
		axis.renderer.ticks.template.disabled = false
		axis.renderer.ticks.template.stroke = chart.colors.getIndex(0);
		axis.renderer.labels.template.radius = 35;
		axis.renderer.ticks.template.strokeOpacity = 1;
		axis.renderer.grid.template.disabled = true;
		axis.renderer.ticks.template.length = 10;
		axis.hiddenState.properties.opacity = 1;
		axis.hiddenState.properties.visible = true;
		axis.setStateOnChildren = true;
		axis.renderer.hiddenState.properties.endAngle = 180;

		var axis2 = chart.xAxes.push(new am4charts.ValueAxis());
		axis2.min = 0;
		axis2.max = parseInt(vals.split(",")[1])+50;
		axis2.strictMinMax = true;

		axis2.renderer.line.strokeOpacity = 1;
		axis2.renderer.line.strokeWidth = 5;
		axis2.renderer.line.stroke = chart.colors.getIndex(3);
		axis2.renderer.ticks.template.stroke = chart.colors.getIndex(3);

		axis2.renderer.ticks.template.disabled = false
		axis2.renderer.ticks.template.strokeOpacity = 1;
		axis2.renderer.grid.template.disabled = true;
		axis2.renderer.ticks.template.length = 10;
		axis2.hiddenState.properties.opacity = 1;
		axis2.hiddenState.properties.visible = true;
		axis2.setStateOnChildren = true;
		axis2.renderer.hiddenState.properties.endAngle = 180;

		var hand = chart.hands.push(new am4charts.ClockHand());
		hand.fill = axis.renderer.line.stroke;
		hand.stroke = axis.renderer.line.stroke;
		hand.axis = axis;
		hand.pin.radius = 14;
		hand.startWidth = 10;

		var hand2 = chart.hands.push(new am4charts.ClockHand());
		hand2.fill = axis2.renderer.line.stroke;
		hand2.stroke = axis2.renderer.line.stroke;
		hand2.axis = axis2;
		hand2.pin.radius = 10;
		hand2.startWidth = 10;

		setInterval(function() {
		  hand.showValue(parseInt(vals.split(",")[0]), 1000, am4core.ease.cubicOut);
		 // label.text = Math.round(hand.value).toString();
		  hand2.showValue(parseInt(vals.split(",")[1]), 1000, am4core.ease.cubicOut);
		//  label2.text = Math.round(hand2.value).toString();
		}, 2000);

		var legend = new am4charts.Legend();
		legend.isMeasured = false;
		legend.y = am4core.percent(100);
		legend.verticalCenter = "bottom";
		legend.parent = chart.chartContainer;
		legend.data = [{
		  "name": "<20 ("+parseInt(vals.split(",")[0])+")",
		  "fill": chart.colors.getIndex(0)
		}, {
		  "name": ">20 & <22 ("+parseInt(vals.split(",")[1])+")",
		  "fill": chart.colors.getIndex(3)
		}];

		legend.itemContainers.template.events.on("hit", function(ev) {
		  var index = ev.target.dataItem.index;

		  if (!ev.target.isActive) {
		    chart.hands.getIndex(index).hide();
		    chart.xAxes.getIndex(index).hide();
//		    labelList.getIndex(index).hide();
		  }
		  else {
		    chart.hands.getIndex(index).show();
		    chart.xAxes.getIndex(index).show();
//		    labelList.getIndex(index).show();
		  }
		});

		


		}); // end am4core.ready()
}

function getDeviationAglsupply(vals){
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_animated);
		// Themes end

		// create chart
		var chart = am4core.create("deviation_agl_supply", am4charts.GaugeChart);
		chart.hiddenState.properties.opacity = 0;

		var title = chart.titles.create();
		title.text = "AG Deviation supply Hrs";
		title.fontSize = 15;
		
		var axis = chart.xAxes.push(new am4charts.ValueAxis());
		axis.min = 0;
		axis.max = parseInt(vals.split(",")[0])+50;
		axis.strictMinMax = true;
		axis.renderer.inside = true;
		//axis.renderer.ticks.template.inside = true;
		//axis.stroke = chart.colors.getIndex(3);
		axis.renderer.radius = am4core.percent(97);
		//axis.renderer.radius = 80;
		axis.renderer.line.strokeOpacity = 1;
		axis.renderer.line.strokeWidth = 5;
		axis.renderer.line.stroke = chart.colors.getIndex(0);
		axis.renderer.ticks.template.disabled = false
		axis.renderer.ticks.template.stroke = chart.colors.getIndex(0);
		axis.renderer.labels.template.radius = 35;
		axis.renderer.ticks.template.strokeOpacity = 1;
		axis.renderer.grid.template.disabled = true;
		axis.renderer.ticks.template.length = 10;
		axis.hiddenState.properties.opacity = 1;
		axis.hiddenState.properties.visible = true;
		axis.setStateOnChildren = true;
		axis.renderer.hiddenState.properties.endAngle = 180;

		var axis2 = chart.xAxes.push(new am4charts.ValueAxis());
		axis2.min = 0;
		axis2.max = parseInt(vals.split(",")[1])+50;
		axis2.strictMinMax = true;

		axis2.renderer.line.strokeOpacity = 1;
		axis2.renderer.line.strokeWidth = 5;
		axis2.renderer.line.stroke = chart.colors.getIndex(3);
		axis2.renderer.ticks.template.stroke = chart.colors.getIndex(3);

		axis2.renderer.ticks.template.disabled = false
		axis2.renderer.ticks.template.strokeOpacity = 1;
		axis2.renderer.grid.template.disabled = true;
		axis2.renderer.ticks.template.length = 10;
		axis2.hiddenState.properties.opacity = 1;
		axis2.hiddenState.properties.visible = true;
		axis2.setStateOnChildren = true;
		axis2.renderer.hiddenState.properties.endAngle = 180;

		var hand = chart.hands.push(new am4charts.ClockHand());
		hand.fill = axis.renderer.line.stroke;
		hand.stroke = axis.renderer.line.stroke;
		hand.axis = axis;
		hand.pin.radius = 14;
		hand.startWidth = 10;

		var hand2 = chart.hands.push(new am4charts.ClockHand());
		hand2.fill = axis2.renderer.line.stroke;
		hand2.stroke = axis2.renderer.line.stroke;
		hand2.axis = axis2;
		hand2.pin.radius = 10;
		hand2.startWidth = 10;

		setInterval(function() {
		  hand.showValue(parseInt(vals.split(",")[0]), 1000, am4core.ease.cubicOut);
		  //label.text = Math.round(hand.value).toString();
		  hand2.showValue(parseInt(vals.split(",")[1]), 1000, am4core.ease.cubicOut);
		 // label2.text = Math.round(hand2.value).toString();
		}, 2000);

		var legend = new am4charts.Legend();
		legend.isMeasured = false;
		legend.y = am4core.percent(100);
		legend.verticalCenter = "bottom";
		legend.parent = chart.chartContainer;
		legend.data = [{
		  "name": "<9 Hrs("+parseInt(vals.split(",")[0])+")",
		  "fill": chart.colors.getIndex(0)
		}, {
		  "name": ">11 Hrs("+parseInt(vals.split(",")[1])+")",
		  "fill": chart.colors.getIndex(3)
		}];

		legend.itemContainers.template.events.on("hit", function(ev) {
		  var index = ev.target.dataItem.index;

		  if (!ev.target.isActive) {
		    chart.hands.getIndex(index).hide();
		    chart.xAxes.getIndex(index).hide();
//		    labelList.getIndex(index).hide();
		  }
		  else {
		    chart.hands.getIndex(index).show();
		    chart.xAxes.getIndex(index).show();
//		    labelList.getIndex(index).show();
		  }
		});

//		var labelList = new am4core.ListTemplate(new am4core.Label());
//		labelList.template.isMeasured = false;
//		labelList.template.background.strokeWidth = 2;
//		labelList.template.fontSize = 15;
//		labelList.template.padding(10, 20, 10, 20);
//		labelList.template.y = am4core.percent(50);
//		labelList.template.horizontalCenter = "middle";
//
//		var label = labelList.create();
//		label.parent = chart.chartContainer;
//		label.x = am4core.percent(40);
//		label.background.stroke = chart.colors.getIndex(0);
//		label.fill = chart.colors.getIndex(0);
//		label.text = "0";
//
//		var label2 = labelList.create();
//		label2.parent = chart.chartContainer;
//		label2.x = am4core.percent(60);
//		label2.background.stroke = chart.colors.getIndex(3);
//		label2.fill = chart.colors.getIndex(3);
//		label2.text = "0";


		}); // end am4core.ready()
}

function getPowerFails(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	$.ajax( {
        url:'Dashboard1/powerfails.epx?level='+level+'&levelID='+levelID,
        beforeSend:function(){
        	document.getElementById("power_fails").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	console.log(data.split(";")[0]);
        	getPowerFailsAction(data.split(";")[0],data.split(";")[1]);
        }
     });	
}
function getPowerFailsAction(sus,trans){
	// Themes begin
//	am4core.useTheme(am4themes_dataviz);
//	am4core.useTheme(am4themes_animated);
	// Themes end

	// Create chart instance
	var chart = am4core.create("power_fails", am4charts.XYChart);

//	var title = chart.titles.create();
//	title.text = "Power Failures";
//	title.fontSize = 15;

	
	// Increase contrast by taking evey second color
	chart.colors.step = 2;

	// Add data
	chart.data = generateChartData();

	// Create axes
	var dateAxis = chart.xAxes.push(new am4charts.DateAxis());
	dateAxis.renderer.minGridDistance = 50;

	// Create series
	function createAxisAndSeries(field, name, opposite, bullet) {
	  var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
	  if(chart.yAxes.indexOf(valueAxis) != 0){
	  	valueAxis.syncWithAxis = chart.yAxes.getIndex(0);
	  }
	  
	  var series = chart.series.push(new am4charts.LineSeries());
	  series.dataFields.valueY = field;
	  series.dataFields.dateX = "date";
	  series.strokeWidth = 2;
	 // series.yAxis = valueAxis;
	  if(name=="Transient" ){
		    series.yAxis = valueAxis;
		  }
	  series.name = name;
	  series.tooltipText = "{name}: [bold]{valueY}[/]";
	  series.tensionX = 0.8;
	  series.showOnInit = true;
	  
	  var interfaceColors = new am4core.InterfaceColorSet();
	  
	  switch(bullet) {
	    case "triangle":
	      var bullet = series.bullets.push(new am4charts.Bullet());
	      bullet.width = 12;
	      bullet.height = 12;
	      bullet.horizontalCenter = "middle";
	      bullet.verticalCenter = "middle";
	      
	      var triangle = bullet.createChild(am4core.Triangle);
	      triangle.stroke = interfaceColors.getFor("background");
	      triangle.strokeWidth = 2;
	      triangle.direction = "top";
	      triangle.width = 12;
	      triangle.height = 12;
	      break;
	    case "rectangle":
	      var bullet = series.bullets.push(new am4charts.Bullet());
	      bullet.width = 10;
	      bullet.height = 10;
	      bullet.horizontalCenter = "middle";
	      bullet.verticalCenter = "middle";
	      
	      var rectangle = bullet.createChild(am4core.Rectangle);
	      rectangle.stroke = interfaceColors.getFor("background");
	      rectangle.strokeWidth = 2;
	      rectangle.width = 10;
	      rectangle.height = 10;
	      break;
	    default:
	      var bullet = series.bullets.push(new am4charts.CircleBullet());
	      bullet.circle.stroke = interfaceColors.getFor("background");
	      bullet.circle.strokeWidth = 2;
	      break;
	  }
	  
	  valueAxis.renderer.line.strokeOpacity = 1;
	  valueAxis.renderer.line.strokeWidth = 2;
	  valueAxis.renderer.line.stroke = series.stroke;
	  valueAxis.renderer.labels.template.fill = series.stroke;
	  valueAxis.renderer.opposite = opposite;
	}

	createAxisAndSeries("visits", "Transient", false, "circle");
	createAxisAndSeries("views", "Sustained", true, "triangle");
	

	// Add legend
	 chart.legend = new am4charts.Legend();
     chart.legend.position = "bottom";

	// Add cursor
	chart.cursor = new am4charts.XYCursor();

	// generate some random data, quite different range
	function generateChartData() {
	  var chartData = [];
	  var firstDate = new Date();
	  var firstDay = new Date(firstDate.getFullYear(), firstDate.getMonth(), 1);
	  var lastDay = new Date(firstDate.getFullYear(), firstDate.getMonth() + 1, 0);
	  
//	  var firstDay = new Date(firstDate.getFullYear(), firstDate.getMonth()-1, 1);
//	  var lastDay = new Date(firstDate.getFullYear(), firstDate.getMonth(), 0);

	  console.log(firstDay);
	  console.log(lastDay);
	  console.log(lastDay.getDate());
	  
	  var visits = 0;	  
	  var views = 0;

	  for (var i = 0; i < lastDay.getDate(); i++) {
	    // we create date objects here. In your data, you can have date strings
	    // and then set format of your dates using chart.dataDateFormat property,
	    // however when possible, use date objects, as this will speed up chart rendering.
	    var newDate = new Date(firstDay);
	    newDate.setDate(newDate.getDate() + i);

	    chartData.push({
	      date: newDate,
	      visits: parseFloat(sus.split(",")[i]),	      
	      views: parseFloat(trans.split(",")[i])
	    });
	  }
	  return chartData;
	}
}

function get11kvFeedersStatus(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	$.ajax( {
        url:'Dashboard1/11kvStatus.epx?level='+level+'&levelID='+levelID,
        beforeSend:function(){
        	document.getElementById("11kv_staus").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	document.getElementById("11kv_staus").innerHTML=data;
        	
        }
     });	
}
function get33kvFeedersStatus(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	$.ajax( {
        url:'Dashboard1/33kvStatus.epx?level='+level+'&levelID='+levelID,
        beforeSend:function(){
        	document.getElementById("33kv_staus").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	document.getElementById("33kv_staus").innerHTML=data;
        	
        }
     });	
}
function dashFeederStatusPopup(voltageLevel,type){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	var modal = document.getElementById("bi_modal");
	var urlStr="";
	
	if(type=="ss"){
		urlStr='Dashboard1/dashSaidiSaifiPopup.epx?level='+level+'&levelID='+levelID;
	}else{
		urlStr='Dashboard1/dashFeederStatusPopup.epx?level='+level+'&levelID='+levelID+'&voltageLevel='+voltageLevel+'&type='+type;
	}
	   
	$.ajax({
		url:urlStr,
	       beforeSend:function(){
         	// jQuery("#getCodeModal").html(jQuery("#loading").html());
         },
	       success : function(data) {	         
	    	   
	           $("#bi_modal").html(data);
	           modal.style.display = "block";
	           var span = document.getElementsByClassName("biclose")[0];
	    	   span.onclick = function() {		   
	    		  modal.style.display = "none";
	    		}

	    		//When the user clicks anywhere outside of the modal, close it
	    		window.onclick = function(event) {
	    		if (event.target == modal) {
	    		 modal.style.display = "none";
	    		}
	    		}
	    		
	    		
	       },
	       error : function(err) {	           
	           
	           
	       }
	   });
}
function downloadSaidiSaifi(circle,level,levelID){
	location.href='Dashboard1/downloadSaidiSaifi.epx?level='+level+'&levelID='+levelID+'&location='+circle;
}