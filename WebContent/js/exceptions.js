function getExceptionsSummary(){
	
	clear();
	hideDashboardDiv();
	$("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');  
	$.ajax( {
        url:'exceptions_reports/exceptions_reports_home.jsp',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
        }
     });
	
	meterDataAnySummary();
	energyComparision();
	criticalTampers();
	tampersGr10mins();
	tamperEvents();
}

function meterDataAnySummary(){
	$.ajax( {
        url:'exceptions_reports/meterdataanalysis.jsp',
        beforeSend:function(){
        	document.getElementById("meterdata").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#meterdata').html(data);
          // callDataTable('table1');
        }
     });	
}
function energyComparision(){
	$.ajax( {
        url:'exceptions_reports/energyComparision.jsp',
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
        url:'exceptions_reports/criticaltampers.jsp',
        beforeSend:function(){
        	document.getElementById("tampers").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#tampers').html(data);
           
          // callDataTable('table1');
        }
     });	
}

function tampersGr10mins(){
	$.ajax( {
        url:'exceptions_reports/tampersGr10mins.jsp',
        beforeSend:function(){
        	document.getElementById("durationGR1omins").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#durationGR1omins').html(data);
           $("#durationGR1omins").slimScroll({
               size: "3px", 
               height: "250px",
               width:"100%"
               
           });
        }
     });
}

function tamperEvents(){
	$.ajax( {
        url:'exceptions_reports/tamperevents.jsp',
        beforeSend:function(){
        	document.getElementById("events").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#events').html(data);
          // callDataTable('table1');
        }
     });	
}

function tampersGr10mins_popup(type,month){
	var modal = document.getElementById("bi_modal");
	$.ajax({
		url:'exceptions_reports/tampersGr10mins_popup.jsp?type='+type+'&month='+month,
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

function dashTampers(){
	
	var month=$("#dashMonth").val();
	criticalTampers_bymonth(month);
	tampersGr10mins_bymonth(month);
}
function criticalTampers_bymonth(month){
	$.ajax( {
        url:'exceptions_reports/criticaltampers.jsp?month='+month,
        beforeSend:function(){
        	document.getElementById("tampers").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#tampers').html(data);
          // callDataTable('table1');
        }
     });	
}

function tampersGr10mins_bymonth(month){
	$.ajax( {
        url:'exceptions_reports/tampersGr10mins.jsp?month='+month,
        beforeSend:function(){
        	document.getElementById("durationGR1omins").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#durationGR1omins').html(data);
           $("#durationGR1omins").slimScroll({
               size: "3px", 
               height: "250px",
               width:"100%"
               
           });
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