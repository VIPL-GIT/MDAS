function getIdlDashboard(actionId){
	clear();
	hideDashboardDiv();
	$("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');  
	$.ajax( {
        url:'Idl/IdlHome.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
          // callDataTable('table1');
        }
     });
}

function callAllINDLfunctions(){
	getIndlCommStatus();
	getIndlLoadCurve();   
	getIndlPowerFails();
	getIndlFeedersStatus();
    getIndlLatestInstant();
    getIndlAvgSupplyHrs();
}
function getIndlCommStatus(){
	var level="0";
	var levelID="0";
	$.ajax( {
        url:'Idl/getIndlCommStatus.epx?level='+level+'&levelID='+levelID,
        beforeSend:function(){
        	document.getElementById("live_status").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	document.getElementById("live_status").innerHTML=data;
        	Circlle('.round','#5cffae'); 
        	
        }
     });
}
function getIndlLoadCurve(){
	var level="0";
	var levelID="0";
	$.ajax( {
        url:'Idl/getIndlLoadCurve.epx?level='+level+'&levelID='+levelID+'&date='+$("#dashdate").val(),
        beforeSend:function(){
        	document.getElementById("load_curve").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	//console.log(data.split(";")[0]);
        	getIndlLoadCurveAction(data.split(";")[0],data.split(";")[1]);
        }
     });
}
function getIndlLoadCurveAction(nonag,datestr){
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
	chart.colors.step = 1;

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
	  series.strokeWidth = 1;
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
	  valueAxis.renderer.line.strokeWidth = 1;
	  valueAxis.renderer.line.stroke = series.stroke;
	  valueAxis.renderer.labels.template.fill = series.stroke;
	  valueAxis.renderer.opposite = opposite;
	}

	//createAxisAndSeries("tot", "Total", false, "rectangle");
	//createAxisAndSeries("ag", "AG", true, "circle");
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

	  for (var i = 0; i < nonag.split(",").length; i++) {
	    // we create date objects here. In your data, you can have date strings
	    // and then set format of your dates using chart.dataDateFormat property,
	    // however when possible, use date objects, as this will speed up chart rendering.
	    var newDate = new Date(firstDate);
	    newDate.setHours(newDate.getHours() + i);

	    chartData.push({
	      date: newDate,
	      //ag: parseFloat(ag.split(",")[i]).toFixed(2),	      
	      nonag: parseFloat(nonag.split(",")[i]).toFixed(2),
	     // tot: (parseFloat(ag.split(",")[i])+parseFloat(nonag.split(",")[i])).toFixed(2)
//	      ag: Math.round(ag.split(",")[i]),	      
//	      nonag: Math.round(nonag.split(",")[i]),
//	      tot: Math.round(ag.split(",")[i])+Math.round(nonag.split(",")[i])
	    });
	  }
	  return chartData;
	}
}
function getIndlPowerFails(){
	var level="0";
	var levelID="0";
	$.ajax( {
        url:'Idl/powerfails.epx?level='+level+'&levelID='+levelID,
        beforeSend:function(){
        	document.getElementById("power_fails").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	console.log(data.split(";")[0]);
        	getPowerFailsAction(data.split(";")[0],data.split(";")[1]);
        }
     });
}
function getIndlFeedersStatus(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	$.ajax( {
        url:'Idl/indlStatus.epx?level='+level+'&levelID='+levelID,
        beforeSend:function(){
        	document.getElementById("33kv_staus").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	document.getElementById("33kv_staus").innerHTML=data;
        	
        }
     });
}
function getIndlLatestInstant(){
	$.ajax( {
        url:'Idl/getIndlLatestInstant.epx',
        beforeSend:function(){
        	document.getElementById("list").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	document.getElementById("list").innerHTML=data;
        	callDataTableWithName('allinstant','LiveStatus');
        }
     });
}
function getIndlAvgSupplyHrs(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	$.ajax( {
        url:'Idl/getIndlAvgSupplyHrs.epx?level='+level+'&levelID='+levelID,
        beforeSend:function(){
        	document.getElementById("avg_supply").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	document.getElementById("avg_supply").innerHTML=data;
        	callDataTableWithName('avgSupply','avg_supply');
        }
     });
}

function indlFeederStatusPopup(type){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	var modal = document.getElementById("bi_modal");
	var urlStr="";
	
	if(type=="ss"){
		return 0;
	}else{
		urlStr='Idl/indlFeederStatusPopup.epx?level='+level+'&levelID='+levelID+'&type='+type;
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
	       error : function(err) {	}
	   });
}
function getFeederTrippingDetails(meter){
	
	var modal = document.getElementById("bi_modal");
	var	urlStr='Idl/getFeederTrippingDetails.epx?meter='+meter;
	   
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
	       error : function(err) {	}
	   });
	
}