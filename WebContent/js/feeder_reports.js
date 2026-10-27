function getFeederReportsHome(actionId,menus){
	clear();
	hideDashboardDiv();
	 //Enable sidebar push menu
//    if ($(window).width() > (screenSizes.sm - 1)) {
//        if ($("body").hasClass('sidebar-collapse')) {
//            $("body").removeClass('sidebar-collapse').trigger('expanded.pushMenu');
//        } else {
//            $("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');
//        }
//    }
    $("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');   
	   
	$.ajax( {
        url:'fdr/home.epx?actionId='+actionId+'&menus='+menus,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}
function getFROptions(type){
	
	if(type=="1" || type=="8" || type=="5" || type=="3" || type=="9" || type=="11" || type=="13" 
		|| type=="14" || type=="17" || type=="20" || type=="21" || type=="24"){
		$("#frdateDiv").hide();
		$("#frdatesDiv").hide();
		$("#frmonthDiv").show();
		$("#fr_button").show();
		$('#frmonth').MonthPicker({SelectedMonth: 0, Button: false });
	}
	else if (type=="29"){
		$("#frdateDiv").hide();
		$("#frdatesDiv").hide();
		$("#frmonthDiv").show();
		$("#fr_button").show();
		$('#frmonth').MonthPicker({SelectedMonth: 1, Button: false });
	}
	else if(type=="15" ||type=="12" ){
		$("#frmonthDiv").hide();
		$("#frdateDiv").hide();
		$("#frdatesDiv").show();
		$("#fr_button").show();
	}
	else if(type=="26" ){
		$("#frmonthDiv").hide();
		$("#frdateDiv").hide();
		$("#frdatesDiv").hide();
		$("#fr_button").show();
	}
	else if(type=="27" || type=="28"){
		$("#frmonthDiv").hide();
		$("#frdateDiv").hide();
		$("#frdatesDiv").hide();
		$("#fr_button").hide();
		if(type=="27") getLoadfactorScreen();
		if(type=="28") getConsumptionScreen();
	}
	else{
		$("#frdateDiv").show();
		$("#frmonthDiv").hide();
		$("#frdatesDiv").hide();
		$("#fr_button").show();
		
	}
}
function getHierarchyForFReports(userHid){
	$.ajax( {
        url:'getHierarchyFor_FReports.jsp?userHid='+userHid,
        success:function(data) {        	
           $('#fr_hierarchy').html(data);
           
        }
     });
}
function getFReports(){
	var type=$("#frtype").val();
	$("#fr_hierarchy").empty();
	
	var frdate=$("#frdate").val();
	
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	
	var Selected = document.getElementById('childID').selectedIndex;
	var SelectedOption = document.getElementById('childID').options[Selected].text;
	var SelectedValue = document.getElementById('childID').options[Selected].value;
	if(SelectedValue!=0){
		$("#frmonthDiv").hide();
		$("#frdateDiv").hide();
		$("#frdatesDiv").hide();
		
		if(type=="13") getEventsSummaryByMeter();
		
	}else{
	
	var urlStr="";  //urlStr="'fdr/getReportByType.epx?type='+type+'&hlevel='+hlevel+'&hid='+hId";
	 //if(type=="1") urlStr='fdr/energy_input.epx?level='+level+'&levelID='+levelID+'&frdate='+frdate;

	 if(type=="1") urlStr='fdr/energy_input.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val();
	 if(type=="17") urlStr='fdr/energy_input_daily.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val();
	 if(type=="24") urlStr='fdr/energy_input_manual.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val();
	
	 //if(type=="2") urlStr='fdr/hourly_load.epx?type='+type+'&hlevel='+1+'&frdate='+frdate;
	 if(type=="2") urlStr='fdr/hourly_load.epx?level='+level+'&levelID='+levelID+'&frdate='+frdate+'&type=1';
	 if(type=="22") urlStr='fdr/hourly_load_live.epx?level='+level+'&levelID='+levelID+'&frdate='+frdate;
	 if(type=="23") urlStr='fdr/divisions_hourly_load_live.epx?frdate='+frdate;
	 
	 if(type=="3") urlStr='fdr/monthly_peak_load.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val();
	 
	 if(type=="4") urlStr='fdr/daily_supply_hrs.epx?level='+level+'&levelID='+levelID+'&frdate='+frdate;
	 
	 if(type=="5") urlStr='fdr/avg_supply_hrs.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val();
	 
	 if(type=="6") urlStr='fdr/comm_summary.epx?type='+type+'&hlevel='+1+'&frdate='+frdate;
	 if(type=="15") urlStr='fdr/comm_summary_datewise.epx?type='+type+'&hlevel='+1+'&frfromdate='+$("#frfromdate").val()+'&frtodate='+$("#frtodate").val();
	 
	 if(type=="7") urlStr='fdr/feeder_wise_comm.epx?type='+type+'&hlevel='+1;
	 
	 if(type=="8") urlStr='fdr/reliability_index.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val();
	 
	 if(type=="9") urlStr='fdr/bus_bar_losses.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val()+'&type=2';
	 
	 if(type=="10") urlStr='fdr/load_curve.epx?level='+level+'&levelID='+levelID+'&frdate='+frdate;
	 
	 if(type=="11") urlStr='fdr/oluvovpf.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val()+'&type=ol';
	 
	 if(type=="12") urlStr='fdr/power_failures.epx?level='+level+'&levelID='+levelID+'&frfromdate='+$("#frfromdate").val()+'&frtodate='+$("#frtodate").val();
	 
	 if(type=="13") urlStr='fdr/events_summary.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val();
	 
	 if(type=="14") urlStr='fdr/loadprofile_summary.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val();
	 if(type=="25") urlStr='vee/summaryReport.epx?level='+level+'&levelID='+levelID+'&frdate='+frdate;
	 
	 if(type=="16") urlStr='fdr/singlePhaseSupply.epx?level='+level+'&levelID='+levelID+'&frdate='+frdate;
	 if(type=="20") urlStr='fdr/singlePhaseSupply_monthly.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val();
	 
	 if(type=="18") urlStr='fdr/energysavings_singlePhaseSupply.epx?level='+level+'&levelID='+levelID+'&frdate='+frdate;
	 if(type=="21") urlStr='fdr/energysavings_singlePhaseSupply_monthly.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val();
	 
	 if(type=="19") urlStr='fdr/ag_deviations.epx?level='+level+'&levelID='+levelID+'&frdate='+frdate;
	 
	 if(type=="29") urlStr='fdr/demand_forecasting.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val();
	 
	 if(type=="26") {
		 callMeterLoadGraph();
		 return;
	 }
	 //alert(urlStr);
	$.ajax( {
        url:urlStr,
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#fr_result').html(data);
           if(type=="1" || type=="17" || type=="24")
             callDataTableWithName_25('energyInput','Energy_Input_'+frdate);
           if(type=="2" || type=="22" || type=="23")
               callDataTableWithName_25('hourlyLOad','Hourly_load_amps');
           if(type=="4")
               callDataTableWithName_25('supplyHrs','Daily_supply_hrs_'+frdate);
           if(type=="5")
               callDataTableWithName_25('avgSupply','Avg_supply_hrs');
           if(type=="6" || type=="15")
               callDataTableWithName_255('table1','Comm_summary');
           if(type=="3")
               callDataTableWithName_25('peakload','Monthly_peak_load');
           if(type=="8")
               callDataTableWithName_25('reliabilityIndex','ReliabilityIndex');
           if(type=="10")
               callDataTableWithName_25('load_curve','LoadCurve');
           if(type=="11")
               callDataTableWithName_25('oluvovpf','oluvovpf');
           if(type=="12")
               callDataTableWithName_25('powerfailure','powerfailures');
           if(type=="13"){
               callDataTableWithName_25('eventsSummary','eventsSummary');
               getEventsGraph();
           }
           if(type=="14")
               callDataTableWithName_25('lsprofile','ls_summary');
           if(type=="16" || type=="20")
               callDataTableWithName_25('singlePhaseFeeders','SinglePhaseSupply');
           if(type=="18" ||type=="21")
               callDataTableWithName_25('singlePhaseFeeders_savings','SinglePhaseSupply_savings');
           if(type=="19")
               callDataTableWithName_25('ag_deviations','ag_deviations');
           if(type=="29")
               callDataTableWithName_25('forecasting','forecasting');
           
           if(type=="25")
        	   generateVEESummaryGraph();
        }
     });
	}//if selected meter value=0;
}

function getLoadfactorScreen(){
	$.ajax( {
        url:'fdr/load_factor_screen.epx',
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	$('#fr_result').html(data);
        }
     });
}
function getConsumptionScreen(){
	$.ajax( {
        url:'fdr/consumption_screen.epx',
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	$('#fr_result').html(data);
        }
     });
}
function getLoadFactorAction(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	var Selected = document.getElementById('childID').selectedIndex;
	var meter = document.getElementById('childID').options[Selected].text;
	var SelectedValue = document.getElementById('childID').options[Selected].value;
	if(SelectedValue=="0") meter="0";
	
	$.ajax( {
        url:'fdr/load_factor_action.epx?type='+$("#lf_type").val()+'&year='+$("#year").val()+"&rtype=table&level="+level+"&levelID="+levelID+"&meter="+meter,
        type: "POST",
        beforeSend:function(){
        	document.getElementById("lf_table").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	$('#lf_table').html(data);
        	getLFGraph();
        }
     });
}
function getLFGraph(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	var Selected = document.getElementById('childID').selectedIndex;
	var meter = document.getElementById('childID').options[Selected].text;
	var SelectedValue = document.getElementById('childID').options[Selected].value;
	if(SelectedValue=="0") meter="0";
	
	$.ajax( {
        url:'fdr/load_factor_action.epx?type='+$("#lf_type").val()+'&year='+$("#year").val()+"&rtype=graph&level="+level+"&levelID="+levelID+"&meter="+meter,
        type: "POST",
        beforeSend:function(){
        	//document.getElementById("lf_graph").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	if(data.indexOf("error")>0){
        		document.getElementById("lf_graph").innerHTML=data.split("@")[1];
        	}else{
        		var gtitle="Load Factor";
        		if($("#lf_type").val()=="le5p") gtitle="LF < 5%";
        		if($("#lf_type").val()=="le5p3m") gtitle="LF < 5% from last 3 months";
        		if($("#lf_type").val()=="gr100p") gtitle="LF > 100%";
        		if($("#lf_type").val()=="gr100p3m") gtitle="LF > 100% from last 3 months";
        	  generateFreportsBarGraph("lf_graph",data.split(";")[0],data.split(";")[1],gtitle);
        	}
        }
     });
}
function loadFactorPopup(month){
	var modal = document.getElementById("bi_modal");
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	var Selected = document.getElementById('childID').selectedIndex;
	var meter = document.getElementById('childID').options[Selected].text;
	var SelectedValue = document.getElementById('childID').options[Selected].value;
	if(SelectedValue=="0") meter="0";
	
	$.ajax({
		url:'fdr/loadFactorPopup.epx?type='+$("#lf_type").val()+'&year='+$("#year").val()+'&level='+level+'&levelID='+levelID+'&meter='+meter+'&month='+month,
	       beforeSend:function(){},
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
function getZeroConsumptionAction(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	var Selected = document.getElementById('childID').selectedIndex;
	var meter = document.getElementById('childID').options[Selected].text;
	var SelectedValue = document.getElementById('childID').options[Selected].value;
	if(SelectedValue=="0") meter="0";
	
	$.ajax( {
        url:'fdr/zero_consumption_action.epx?type='+$("#zc_type").val()+'&year='+$("#year").val()+"&rtype=table&level="+level+"&levelID="+levelID+"&meter="+meter,
        type: "POST",
        beforeSend:function(){
        	document.getElementById("zc_table").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	$('#zc_table').html(data);
        	getZCGraph();
        }
     });
}
function getZCGraph(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	var Selected = document.getElementById('childID').selectedIndex;
	var meter = document.getElementById('childID').options[Selected].text;
	var SelectedValue = document.getElementById('childID').options[Selected].value;
	if(SelectedValue=="0") meter="0";
	
	$.ajax( {
        url:'fdr/zero_consumption_action.epx?type='+$("#zc_type").val()+'&year='+$("#year").val()+"&rtype=graph&level="+level+"&levelID="+levelID+"&meter="+meter,
        type: "POST",
        beforeSend:function(){
        	//document.getElementById("zc_graph").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) { 
        	if(data.indexOf("error")>0){
        		document.getElementById("zc_graph").innerHTML=data.split("@")[1];
        	}else{
        		var gtitle="";
        		if($("#zc_type").val()=="zc") gtitle="Zero Consumption";
        		if($("#zc_type").val()=="zc3m") gtitle="Zero Consumption in last 3 months";
        		if($("#zc_type").val()=="zc6m") gtitle="Zero Consumption in last 6 months";
        		if($("#zc_type").val()=="zc9m") gtitle="Zero Consumption in last 9 months";
        		if($("#zc_type").val()=="zc12m") gtitle="Zero Consumption in last 12 months";
        		if($("#zc_type").val()=="cle1006m") gtitle="Consumption <100 ( last 6 months)";
        		if($("#zc_type").val()=="nc") gtitle="Negative Consumption";
        		if($("#zc_type").val()=="hc") gtitle="High Consumption";
        		if($("#zc_type").val()=="lc") gtitle="Low Consumption";
        		if($("#zc_type").val()=="pf") gtitle="PF >-0.8 to <0.8";
        		
        		if($("#zc_type").val()=="kwh_tod" || $("#zc_type").val()=="kvah_tod"){
        	 	  if($("#zc_type").val()=="kwh_tod") gtitle="TOD Consumption (KWh)";
        		  if($("#zc_type").val()=="kvah_tod") gtitle="TOD Consumption (KVAh)";
        		  generateFreportsLineGraph("zc_graph",data,gtitle);
        		}else{
        	      generateFreportsBarGraph("zc_graph",data.split(";")[0],data.split(";")[1],gtitle);
        		}
        	}
        }
     });
}
function zeroConsumptionPopup(month){
	var modal = document.getElementById("bi_modal");
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	var Selected = document.getElementById('childID').selectedIndex;
	var meter = document.getElementById('childID').options[Selected].text;
	var SelectedValue = document.getElementById('childID').options[Selected].value;
	if(SelectedValue=="0") meter="0";
	
	$.ajax({
		url:'fdr/zeroConsuumptionPopup.epx?type='+$("#zc_type").val()+'&year='+$("#year").val()+'&level='+level+'&levelID='+levelID+'&meter='+meter+'&month='+month,
	       beforeSend:function(){},
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
function getEventsSummaryByMeter(){
	$.ajax( {
        url:'fdr/events_summary_meterEvents.epx',
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	$('#fr_result').html(data);
        }
     });
}
function getEventsSummaryByMeterAction(){
	var Selected = document.getElementById('childID').selectedIndex;
	var meter = document.getElementById('childID').options[Selected].text;
	
	$.ajax( {
        url:'fdr/events_summary_meterEventsAction.epx?meter='+meter+'&year='+$("#year").val()+'&event='+$("#event_type").val(),
        type: "POST",
        beforeSend:function(){
        	document.getElementById("events_summary_meter").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
        	$('#events_summary_meter').html(data);
        	callDataTableWithName_25('events','eventsSummary');
        }
     });
}
function getEventsGraph(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	$.ajax( {
        url:'fdr/events_summary_graph.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val(),
        type: "POST",
        beforeSend:function(){
        	document.getElementById("events_graph").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           generateFreportsBarGraph("events_graph",data.split(";")[0],data.split(";")[1],"Events Summary");
        }
     });
}
function generateFreportsBarGraph(id,names,vals,titleStr){
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_animated);
		var chart = am4core.create(id, am4charts.XYChart3D);
		var title = chart.titles.create();
		title.text = titleStr;
		title.fontSize = 20;
		title.marginBottom = 30;
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

		for (var i = 0; i < names.split(",").length; i++) {
		  
		  data.push({ country: names.split(",")[i], visits: parseFloat(vals.split(",")[i]) });
		}

		chart.data = data;
		// Create axes
		let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "country";
		
		
		categoryAxis.renderer.labels.template.hideOversized = false;		
		categoryAxis.renderer.labels.template.disabled = true;
		
		let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = "";
		//var series = chart.series.push(new am4charts.ColumnSeries());
		var series = chart.series.push(new am4charts.ConeSeries());
		series.dataFields.valueY = "visits";
		series.dataFields.categoryX = "country";
		series.name = "Visits";
		series.tooltipText = "{categoryX}: [bold]{valueY}[/]";
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
function generateFreportsLineGraph(div,data,gtitle){
	// Themes begin
	am4core.useTheme(am4themes_animated);
	// Themes end

	// Create chart instance
	var chart = am4core.create(div, am4charts.XYChart);
    
	var title = chart.titles.create();
	title.text = gtitle;
	title.fontSize = 15;
	
	// Increase contrast by taking evey second color
	chart.colors.step = 2;

	// Add data
	chart.data = generateChartData();

	// Create axes
	var categoryAxis  = chart.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis.dataFields.category = "month";

	// Create series
	function createAxisAndSeries(field, name, opposite, bullet) {
	  var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
	  if(chart.yAxes.indexOf(valueAxis) != 0){
	  	//valueAxis.syncWithAxis = chart.yAxes.getIndex(0);
	  }
	  
	  var series = chart.series.push(new am4charts.LineSeries());
	  series.dataFields.valueY = field;
	  series.dataFields.categoryX  = "month";
	  series.strokeWidth = 1;
	  
	  series.name = name;
	  series.tooltipText = "{name}: [bold]{valueY}";
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

	
	createAxisAndSeries("tod1", "TOD-1", false, "circle");
	createAxisAndSeries("tod2", "TOD-2", false, "circle");
	createAxisAndSeries("tod3", "TOD-3", false, "circle");
	createAxisAndSeries("tod4", "TOD-4", false, "circle");
	createAxisAndSeries("tod5", "TOD-5", false, "circle");
	createAxisAndSeries("tod6", "TOD-6", false, "circle");
	createAxisAndSeries("tod7", "TOD-7", false, "circle");
	createAxisAndSeries("tod8", "TOD-8", false, "circle");

	// Add legend
	chart.legend = new am4charts.Legend();
//	chart.legend.maxHeight = 150;
//	chart.legend.scrollable = true;
	// Add cursor
	chart.cursor = new am4charts.XYCursor();

	// generate some random data, quite different range
	function generateChartData() {
	  var chartData = [];
	  
	  var visits = 0;
	  var hits = 0;
	  var views = 0;

	  for (var i = 0; i < data.split(";")[0].split(",").length; i++) {
	    // we create date objects here. In your data, you can have date strings
	    // and then set format of your dates using chart.dataDateFormat property,
	    // however when possible, use date objects, as this will speed up chart rendering.
	    

	    chartData.push({
	      month: data.split(";")[0].split(",")[i],
	      tod1: parseFloat(data.split(";")[1].split(",")[i]).toFixed(2),	      
	      tod2: parseFloat(data.split(";")[2].split(",")[i]).toFixed(2),
	      tod3: parseFloat(data.split(";")[3].split(",")[i]).toFixed(2),
	      tod4: parseFloat(data.split(";")[4].split(",")[i]).toFixed(2),
	      tod5: parseFloat(data.split(";")[5].split(",")[i]).toFixed(2),
	      tod6: parseFloat(data.split(";")[6].split(",")[i]).toFixed(2),
	      tod7: parseFloat(data.split(";")[7].split(",")[i]).toFixed(2)
	     // tod8: parseFloat(data.split(";")[8].split(",")[i]).toFixed(2)

	    });
	  }
	  return chartData;
	}
}
function callMeterLoadGraph(){
	var Selected = document.getElementById('childID').selectedIndex;
	var SelectedOption = document.getElementById('childID').options[Selected].text;
	var SelectedValue = document.getElementById('childID').options[Selected].value;
	//alert(SelectedValue);
	if(SelectedValue==0){
		var level=$("#level").val();
		var levelID=$("#levelID").val();
		$.ajax( {
	        url:"fdr/load_curve_graph.epx?meter="+SelectedValue+"&level="+level+"&levelID="+levelID,
	        type: "POST",
	        beforeSend:function(){
	        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
	        },
	        success:function(data) {
	        	callMeterLoadGraphAction_graph(data);          
	        }
	     });
	}else{
		$.ajax( {
	        url:"fdr/load_curve_graph.epx?meter="+SelectedOption,
	        type: "POST",
	        beforeSend:function(){
	        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
	        },
	        success:function(data) {
	           callMeterLoadGraphAction(data);          
	        }
	     });
	}
}
function callMeterLoadGraphAction_graph(data){

//alert(data);
	var seriesData = []; 
	
	var sname;
	for(i=1;i<data.split("@").length;i++){
		var dataSeries=[];
		var d=new Date();
    for( j=0;j<data.split("@")[0].split(",").length;j++)
    {
    	d.setTime(data.split("@")[0].split(",")[j]);   	
        dataSeries.push([d.getTime(),parseFloat(data.split("@")[i].split(",")[j])]);
    }
     if(i==1) sname="AG";
     if(i==2) sname="Non AG";
     
	    seriesData.push({
	        name: sname,
	        color:(i==1?"#5fa9b8":"#ebab34"),
	        data: dataSeries
	    });
	}//for i 
    Highcharts.stockChart('fr_result', {

        rangeSelector: {
            selected: 4
        },

        yAxis: {
            labels: {
                formatter: function () {
                    return  this.value + 'Mw';
                }
            },
            plotLines: [{
                value: 0,
                width: 2,
                color: 'silver'
            }]
        },

//        plotOptions: {
//            series: {
//                compare: 'percent',
//                showInNavigator: true
//            }
//        },
        legend: {
            enabled: true,         
            layout: 'horizontal',
            verticalAlign: 'bottom',
            floating: true,
            y: -65
        }, 
        tooltip: {
            pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> <br/>',
            valueDecimals: 2,
            split: true
        },
        credits:{
        	enabled:false
        },
        series: seriesData
    });

	
}

function callMeterLoadGraphAction(data){

	//alert(data);
		var seriesData = []; 
		
		var sname;
		for(i=1;i<data.split("@").length;i++){
			var dataSeries=[];
			var d=new Date();
	    for( j=0;j<data.split("@")[0].split(",").length;j++)
	    {
	    	d.setTime(data.split("@")[0].split(",")[j]);   	
	        dataSeries.push([d.getTime(),parseFloat(data.split("@")[i].split(",")[j])]);
	    }
	     seriesData.push({
		        name: "Load",   
		        type: 'area', 
		        color:"#5fa9b8",
		        data: dataSeries,
		        tooltip: {        	
		            valueDecimals: 2,
		            pointFormatter: function() {
		                return this.y +' Mw';
		            }
		        },
		        fillColor: {
		            linearGradient: {
		                x1: 0,
		                y1: 0,
		                x2: 0,
		                y2: 1
		            },
		            stops: [
		                [0, "#5fa9b8"],
		                [1, Highcharts.color("#5fa9b8").setOpacity(0).get('rgba')]
		            ]
		        },
		        threshold: null
		
		    });
		}//for i 
		    // Create the chart
		    Highcharts.stockChart('fr_result', {
		    	chart: {
		            //backgroundColor: '#FCFFC5',
		            type: 'line',
		            zoomType: 'x',
		               borderColor: "#4572A7",
		               borderRadius: 15,
		               borderWidth: 1,
		               margin: [80, 30, 30, 30]
		        },
		    	rangeSelector: {
		            selected: 4
		        },

		        title: {
		            text: 'Load Curve'
		        },
		        xAxis: {
		            gapGridLineWidth: 0
		        },
	            credits:{
	            	enabled:false
	            },
		        series: seriesData
		    });
		

		
	}
function getEnergySavingsDetails(meter){
	var modal = document.getElementById("bi_modal");
	   var date=$("#frdate").val();
	   date=date.split("-")[2]+"-"+date.split("-")[1]+"-"+date.split("-")[0]
	$.ajax({
		url:'fdr/energySavingsDetails.epx?meter='+meter+'&date='+date,
	       beforeSend:function(){

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
function getSinglePhaseDetails_freports(meter){
	
	var modal = document.getElementById("bi_modal");
	   var date=$("#frdate").val();
	   date=date.split("-")[2]+"-"+date.split("-")[1]+"-"+date.split("-")[0]
	$.ajax({
		url:'Dashboard/getSinglePhaseDetails.epx?meter='+meter+'&date='+date,
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
function getBusBarLosses(type){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	
	var urlStr='fdr/bus_bar_losses.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val()+'&type='+type;
	$.ajax( {
        url:urlStr,
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#fr_result').html(data);           
          
           
        }
     });
}
function getOluvovpf(type){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	
	var urlStr='fdr/oluvovpf.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val()+'&type='+type;
	$.ajax( {
        url:urlStr,
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#fr_result').html(data);           
           callDataTableWithName_25('oluvovpf','oluvovpf');
           
        }
     });
}

function getHourlyLoadWith_type(type){
    var frdate=$("#frdate").val();	
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	var load_type=$("#load_type").val();
	
	
	var urlStr='fdr/hourly_load.epx?level='+level+'&levelID='+levelID+'&frdate='+frdate+'&type='+type;
	$.ajax( {
        url:urlStr,
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#fr_result').html(data);           
           callDataTableWithName_25('hourlyLOad','Hourly_load_amps');
        }
     });
}
function getEnergyInput(hlevel,hlevelid,hlevelname){
	
	//$("#fr_hierarchy").append('<li> <a href="javascript:getEnergyInput('+hlevel+','+hlevelid+','+hlevelname+')">'+hlevelname+"</a> <span class='divider'></span></li>");
	var urlStr='fdr/energy_input.epx?hlevel='+hlevel+'&hlevelid='+hlevelid;
	$.ajax( {
        url:urlStr,
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#fr_result').html(data);
           
        }
     });
}

function getHourlyLoadReport(hlevel,hlevelid,hlevelname){

	var frdate=$("#frdate").val();
	var urlStr='fdr/hourly_load.epx?hlevel='+hlevel+'&hlevelid='+hlevelid+'&frdate='+frdate;
	$.ajax( {
        url:urlStr,
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#fr_result').html(data);
           
        }
     });
}

function getMonthlyPeakLoadReport(hlevel,hlevelid,hlevelname){

	var urlStr='fdr/monthly_peak_load.epx?hlevel='+hlevel+'&hlevelid='+hlevelid;
	$.ajax( {
        url:urlStr,
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#fr_result').html(data);
           
        }
     });
}

function getDailySupplyHrsReport(hlevel,hlevelid,hlevelname){

	var urlStr='fdr/daily_supply_hrs.epx?hlevel='+hlevel+'&hlevelid='+hlevelid;
	$.ajax( {
        url:urlStr,
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#fr_result').html(data);
           
        }
     });
}

function getAvgSupplyHrsReport(hlevel,hlevelid,hlevelname){

	var urlStr='fdr/avg_supply_hrs.epx?hlevel='+hlevel+'&hlevelid='+hlevelid;
	$.ajax( {
        url:urlStr,
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#fr_result').html(data);
           
        }
     });
}

function getCommSummaryReport(hlevel,hlevelid,hlevelname){

	var frdate=$("#frdate").val();
	var urlStr='fdr/comm_summary.epx?hlevel='+hlevel+'&hlevelid='+hlevelid+'&frdate='+frdate;
	$.ajax( {
        url:urlStr,
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#fr_result').html(data);
           
        }
     });
}

function getReliabilityIndexReport(hlevel,hlevelid,hlevelname){

	var urlStr='fdr/reliability_index.epx?hlevel='+hlevel+'&hlevelid='+hlevelid;
	$.ajax( {
        url:urlStr,
        type: "POST",
        beforeSend:function(){
        	document.getElementById("fr_result").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#fr_result').html(data);
           
        }
     });
}

function exportDivtoExcel(fileName,id){
//	let file = new Blob([$('#mpreports').html()], {type:"application/vnd.ms-excel"});
//	let url = URL.createObjectURL(file);
//	let a = $("<a />", {
//	  href: url,
//	  download: fileName+".xls"}).appendTo("body").get(0).click();
//	  e.preventDefault();
	  
	  $("#"+id).table2excel({
		  exclude: ".noExl",
		  name: "Worksheet Name",
		  filename: fileName,
		  fileext: ".xls"
		  
		}); 
}
function printDiv() { 
    var divContents = document.getElementById("mpreports").innerHTML; 
    var a = window.open('', '', 'height=500, width=500');     
    a.document.write(divContents);
    a.document.close(); 
    a.print(); 
} 
function getEventsSummaryDetails(type){
	
	var modal = document.getElementById("bi_modal");
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	
	$.ajax({
		url:'fdr/events_summaryDetails.epx?level='+level+'&levelID='+levelID+'&frmonth='+$("#frmonth").val()+'&type='+type,
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

function monthlyEAPopup(meter){
	var modal = document.getElementById("bi_modal");
	$.ajax({
		url:"fdr/monthlyEAPopup.epx?meter="+meter+"&month="+$("#frmonth").val(),
	       beforeSend:function(){         	
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
function monthlyEAPopupAction(meter,month){
	var modal = document.getElementById("bi_modal");
	var ip=($("#final").val()-$("#initial").val())*$("#mf").val();
	ip=parseFloat(ip)+parseFloat($("#asu").val());
	if(ip>10000){
	swal({
		  title: "Are you sure?",
		  text: "Input (in KWH) > 10,000 ",		 
		  showCancelButton: true,
		  showConfirmButton: true,
		  confirmButtonText: 'Yes, I am sure!',
		  cancelButtonText: 'No, cancel it!',		 
		  dangerMode: false,
		}, function () {
        	var urlStr='fdr/monthlyEAPopupAction.epx?meter='+meter+'&month='+month+'&initial='+$("#initial").val()+'&mf='+$("#mf").val();
    	    urlStr=urlStr+'&final='+$("#final").val()+'&asu='+$("#asu").val();
    	$.ajax( {
            url:urlStr,
            type: "POST",
            beforeSend:function(){            	
            },
            success:function(data) {
            	modal.style.display = "none";
            	alert(data);
               
            }
         });
        
      });
	}else{
//----------------------------	
	var urlStr='fdr/monthlyEAPopupAction.epx?meter='+meter+'&month='+month+'&initial='+$("#initial").val()+'&mf='+$("#mf").val();
	    urlStr=urlStr+'&final='+$("#final").val()+'&asu='+$("#asu").val();
	$.ajax( {
        url:urlStr,
        type: "POST",
        beforeSend:function(){
        	
        },
        success:function(data) {
        	modal.style.display = "none";
        	alert(data);
           
        }
     });
	}
}
