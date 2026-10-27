function getLiveLoad(){
	
	$.ajax( {
        url:'Dashboard/LiveLoad.epx',
        beforeSend:function(){
        	document.getElementById("liveload").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#liveload').html(data);
           getLiveLoadData();
          // callDataTable('table1');
        }
     });
}
function getLiveLoadGraph(){
	$.ajax( {
        url:'Dashboard/LiveLoadGraph.epx',
        beforeSend:function(){
        	document.getElementById("liveloadGraph").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	getLiveLoadCurveAction(data.split(";")[0],data.split(";")[1]);
        }
     });
}
function getLiveLoadData(){
	$.ajax( {
        url:'Dashboard/LiveLoadData.epx',
        beforeSend:function(){
        	document.getElementById("liveloaddata").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#liveloaddata').html(data);
           
          // callDataTable('table1');
        }
     });
}
function getLiveLoadValues(type){
	if(type=="pres_load"){
		$('.load').show();
		$('.load_relief').hide();
	}else{
		$('.load').hide();
		$('.load_relief').show();
	}
}
function getLiveLoadCurveAction(ag,nonag){
	// Themes begin
	am4core.useTheme(am4themes_animated);
	// Themes end

	// Create chart instance
	var chart = am4core.create("liveloadGraph", am4charts.XYChart);
    
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
	  var firstDate = new Date();
	 
	  //var firstDate=new Date(datestr.split("-")[2],datestr.split("-")[1]-1,datestr.split("-")[0]);
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
	    newDate.setMinutes(newDate.getMinutes() + i*30);

	    chartData.push({
	      date: newDate,
	      ag: parseFloat(ag.split(",")[i]),	      
	      nonag: parseFloat(nonag.split(",")[i]),
	      tot: parseFloat(ag.split(",")[i])+parseFloat(nonag.split(",")[i])
	    });
	   // firstDate=newDate;
	  }
	  return chartData;
	}
}
function getLiveLoadSummary(group,circle){
	window.open('dashboard/getLiveLoadSummary.jsp?group='+group+'&circle='+circle, "_blank");
}
function getofflineFeeders(){
	$.ajax( {
        url:'Dashboard/offlineFeeders.epx',
        beforeSend:function(){
        	document.getElementById("offlineFeeders").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#offlineFeeders').html(data);
           callDataTableNoExport("offline");
          // callDataTable('table1');
        }
     });
}

function getpfNpowersStatus(){
	$.ajax( {
        url:'Dashboard/pfNpowersStatus.epx',
        beforeSend:function(){
        	document.getElementById("pfNpowersStatus").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#pfNpowersStatus').html(data);
           getpfNpowersStatusData();
          // callDataTable('table1');
        }
     });
}
function getpfNpowersStatusValues(type){
	$.ajax( {
        url:'dashboard/getpfNpowersStatusValues.jsp?type='+type,
        beforeSend:function(){
        	document.getElementById("pfNpowerValues").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#pfNpowerValues').html(data);
           getpfNpowersStatusData();
          // callDataTable('table1');
        }
     });
}
function getpfNpowersStatusData(){
	$.ajax( {
        url:'Dashboard/pfNpowersStatusData.epx?pfNpowerParam='+$("#pfNpowerParam").val()
                 +'&pfNpowerCondition='+$("#pfNpowerCondition").val()+'&pfNpowerValues='+$("#pfNpowerValues").val(),
        beforeSend:function(){
        	document.getElementById("pfNpowersStatusdata").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#pfNpowersStatusdata').html(data);
           callDataTableNoExport("pfNpowers");
          // callDataTable('pfNpowers');
        }
     });
}
function getnotCommFeeders(){
	$.ajax( {
        url:'Dashboard/notCommFeeders.epx',
        beforeSend:function(){
        	document.getElementById("notCommFeeders").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#notCommFeeders').html(data);
           callDataTableNoExport("notcomm");
          // callDataTable('table1');
        }
     });
}
function getgroupAFeeders(){
	
	$.ajax( {
        url:'Dashboard/groupAFeeders.epx?date='+$("#graphDate").val(),
        beforeSend:function(){
        	document.getElementById("groupA").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#groupA').html(data);
           //callDataTableNoExport("groupAFeeders");
           callDataTableWithName_5_forgroups('groupAFeeders','groupAFeeders');
        }
     });
}
function getgroupBFeeders(){
	$.ajax( {
        url:'Dashboard/groupBFeeders.epx?date='+$("#graphDate").val(),
        beforeSend:function(){
        	document.getElementById("groupB").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#groupB').html(data);
//           callDataTableNoExport("groupBFeeders");
           callDataTableWithName_5_forgroups('groupBFeeders','groupBFeeders');
        }
     });
}
function getgroupCFeeders(){
	$.ajax( {
        url:'Dashboard/groupCFeeders.epx?date='+$("#graphDate").val(),
        beforeSend:function(){
        	document.getElementById("groupC").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#groupC').html(data);
//           callDataTableNoExport("groupCFeeders");
           callDataTableWithName_5_forgroups('groupCFeeders','groupCFeeders');
        }
     });
}
function getSinglePhaseFeeders(){
	$.ajax( {
        url:'Dashboard/getSinglePhaseFeeders.epx?date='+$("#graphDate").val(),
        beforeSend:function(){
        	document.getElementById("singlePhase").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#singlePhase').html(data);
//           callDataTableNoExport("singlePhaseFeeders");
           callDataTableWithName_5_forgroups('singlePhaseFeeders','singlePhaseFeeders');
        }
     });
}
function getSinglePhaseDetails(meter){
	
	var modal = document.getElementById("bi_modal");
	   
	$.ajax({
		url:'Dashboard/getSinglePhaseDetails.epx?meter='+meter+'&date='+$("#graphDate").val(),
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
function getGroupLoads(){
	  getgroupAFeeders();
	   getgroupBFeeders();
	   getgroupCFeeders();
	   getSinglePhaseFeeders();
}

function callDataTableWithName_5_forgroups(tableId,fileName){
	
	 $('#'+tableId).DataTable( {
		 "order": [],
		 "dom": 'Bfrtip', 
		 "lengthMenu": [5,10, 25, 50, 75, 100],
		 "buttons": [{
   	   extend:    'excel',
 	   title: fileName,
        titleAttr: 'Excel'
    },                               
    {
        extend: 'csv',
        title: fileName
    },
    {
        extend: 'pdf',
        title: fileName,
        orientation: 'landscape'
    }
	        ]
		 
	 } );
}