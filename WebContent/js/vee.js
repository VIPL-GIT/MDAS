function viewRules(){
hideDashboardDiv();
	
	$.ajax( {
        url:'vee/rules.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTableWithName('table1','rules');
           
        }
     });
}
function editRule(id){
	$.ajax( {
        url:'vee/editrules.epx?id='+id,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
        }
     });
}
function updateRule(id){
	$.ajax( {
        url:'vee/updateRule.epx',
        data : $('#editUser').serialize(),
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTableWithName('table1','rules');
        }
     });
}
function addVeeRule(){
	$.ajax( {
        url:'vee/addrules.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
        }
     });
}
function addRuleAction(){
	
}
function veeSummary(){
hideDashboardDiv();
	
	$.ajax( {
        url:'vee/summaryHome.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           
           
        }
     });
}
function generateVEESummaryReport(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	var sdate=$("#sdate").val();
	var edate=$("#edate").val();
	
	$.ajax( {
        url:'vee/summaryReport.epx?level='+level+'&levelID='+levelID+'&sdate='+sdate+'&edate='+edate,
        beforeSend:function(){
        	document.getElementById("veesummary").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#veesummary').html(data);
           callDataTableWithName('table1','summary');
           
           
        }
     });
}
function generateVEESummaryGraph(){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	var frdate=$("#frdate").val();
	
	
	$.ajax( {
        url:'vee/summaryReport_graph.epx?level='+level+'&levelID='+levelID+'&frdate='+frdate,
        beforeSend:function(){
        	//sdocument.getElementById("veesummary_graph").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
          generateVEEBarchart(data.split(";")[0],data.split(";")[1]);
          
           
        }
     });
}
function generateVEEBarchart(dates,vals){
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_animated);
		// Themes end

		// Create chart instance
		var chart = am4core.create("veesummary_graph", am4charts.XYChart3D);
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
		
		
		categoryAxis.renderer.labels.template.hideOversized = false;		
		categoryAxis.renderer.labels.template.disabled = false;
		
		let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = "";
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
function generateVEEDetailedReport(){
	
	var Selected = document.getElementById('childID').selectedIndex;
    var SelectedOption = document.getElementById('childID').options[Selected].text;
	var sdate=$("#sdate").val();
	var edate=$("#edate").val();
	
	$.ajax( {
        url:'vee/detailedReport.epx?sdate='+sdate+'&edate='+edate+'&meter='+SelectedOption,
        beforeSend:function(){
        	document.getElementById("veesummary").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#veesummary').html(data);
           callDataTableWithName('table1','details');
           
        }
     });
}
function veeDetailed(){
hideDashboardDiv();
	
	$.ajax( {
        url:'vee/detailedHome.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
        }
     });
}

function getSummaryChecks(type,date){
	var level=$("#level").val();
	var levelID=$("#levelID").val();
	
	var modal = document.getElementById("bi_modal");
	
	$.ajax( {
        url:'vee/summaryReportChecks.epx?level='+level+'&levelID='+levelID+'&sdate='+date+'&type='+type,
        beforeSend:function(){
        	//document.getElementById("veesummary").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
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
           
        }
     });
}
function getDetailedChecks(type,date){
	
	var Selected = document.getElementById('childID').selectedIndex;
    var SelectedOption = document.getElementById('childID').options[Selected].text;
	var sdate=$("#sdate").val();
	var edate=$("#edate").val();
	
	var modal = document.getElementById("bi_modal");
	
	$.ajax( {
        url:'vee/detailedReportChecks.epx?meter='+SelectedOption+'&sdate='+sdate+'&edate='+edate+'&type='+type,
        beforeSend:function(){
        	//document.getElementById("veesummary").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
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
           
        }
     });
}
