function downloadsHome(actionID){
	clear();
	hideDashboardDiv();
	$.ajax({
	   	 url : 'Downloads/home.epx',	       
	       type : 'GET',
	       beforeSend:function(){
         	 jQuery("#datapart").html(jQuery("#loading").html());
         },
	       success : function(data) {	         
	           $("#datapart").html(data);
	           //callDataTableWithName('table1','Billing_meters');
	       },
	       error : function(err) {	           
	           $("#datapart").html(err);
	       }
	   });
}

function getDeviceSummary(){
	$.ajax({
	   	 url : 'Downloads/deviceSummary.epx',	       
	       type : 'GET',
	       beforeSend:function(){
        	 jQuery("#viewresult").html(jQuery("#loading").html());
        },
	       success : function(data) {	         
	           $("#viewresult").html(data);
	           callDataTableWithName('table1','DeviceSummary');
	       },
	       error : function(err) {	           
	           $("#viewresult").html(err);
	       }
	   });
}

function getMasterData(){
	$.ajax({
	   	 url : 'Downloads/masterdata.epx',	       
	       type : 'GET',
	       beforeSend:function(){
       	 jQuery("#viewresult").html(jQuery("#loading").html());
       },
	       success : function(data) {	         
	           $("#viewresult").html(data);
	           callDataTableWithName('table1','Masterdata');
	       },
	       error : function(err) {	           
	           $("#viewresult").html(err);
	       }
	   });
}
function getMobileUsersLogs(){
	$.ajax({
	   	 url : 'Downloads/mobileUsersLogs.epx?month='+$("#mlogs_month").val(),	       
	       type : 'GET',
	       beforeSend:function(){
      	 jQuery("#viewresult").html(jQuery("#loading").html());
      },
	       success : function(data) {	         
	           $("#viewresult").html(data);
	           callDataTableWithName('table1','MobileUsersLogs');
	       },
	       error : function(err) {	           
	           $("#viewresult").html(err);
	       }
	   });
}
function getMobileUploads(){
	$.ajax({
	   	 url : 'Downloads/mobileUploads.epx',	       
	       type : 'GET',
	       beforeSend:function(){
     	 jQuery("#viewresult").html(jQuery("#loading").html());
     },
	       success : function(data) {	         
	           $("#viewresult").html(data);
	           callDataTableWithName('table1','MobileUploads');
	       },
	       error : function(err) {	           
	           $("#viewresult").html(err);
	       }
	   });
}

function getRawdata(){
	$.ajax({
	   	 url : 'Downloads/rawdata.epx?month='+$("#month").val(),	       
	       type : 'GET',
	       beforeSend:function(){
      	 jQuery("#viewresult").html(jQuery("#loading").html());
      },
	       success : function(data) {	         
	           $("#viewresult").html(data);
	           callDataTableWithName('table1','Billing_meters');
	       },
	       error : function(err) {	           
	           $("#viewresult").html(err);
	       }
	   });
}

function showZipFiles(meter){
	if(meter!=''){
		//window.location="downloads/showFiles.jsp?path="+path
		var win = window.open("Downloads/showFiles.epx?meter="+meter+"&month="+$("#month").val(), '_blank');
	    win.focus();
	}
}
function downloadFiles(fileName){
	location.href="downloadFiles.epx?filename="+fileName+"&month="+$("#month").val();
}

function uploadsHome(actionID){
	clear();
	hideDashboardDiv();
	$.ajax({
	  	 url : 'Downloads/uploadType.epx',	       
	      type : 'GET',
	      beforeSend:function(){
	   	 jQuery("#datapart").html(jQuery("#loading").html());
	   },
	      success : function(data) {	         
	          $("#datapart").html(data);
	         // callDataTableWithName('table1','DeviceSummary');
	      },
	      error : function(err) {	           
	          $("#datapart").html(err);
	      }
	  });

}
function updateDiscomIssue(meter,issue){
	var modal = document.getElementById("bi_modal");
	$.ajax({
		url:'Downloads/updateDiscomIssue.epx?meter='+meter+'&issue='+issue,
	       beforeSend:function(){         	
         },
	       success : function(data) {	         
	    	   
	           $("#bi_modal").html(data);
	           modal.style.display = "block";
	           var span = document.getElementsByClassName("biclose")[0];
	    	   span.onclick = function() {		   
	    		  modal.style.display = "none";
	    		}
	    	   
	    	   var btn = document.getElementById("issueBtn");
	    	   btn.onclick = function() {	
	    		   updateDiscomIssueAction(); 
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
function updateDiscomIssueAction(){
	meter=$("#meter").val();
	issue=$("#issue").val();
	
	$.ajax({
	  	 url : 'Downloads/updateDiscomIssueAction.epx?meter='+meter+'&issue='+issue,
	      beforeSend:function(){
	   },
	      success : function(data) {	         
	         alert("data");
	      },
	      error : function(err) {	           
	          
	      }
	  });
}