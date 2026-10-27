function viewSmartDevices(actionid){
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'hes/deviceView.epx?actionid='+actionid,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTableWithName_25('table1','SmartDevices');
        }
     });
}
function addSmartDevices(){
	$.ajax( {
        url:'hes/addSmartDevices.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function getSmartDevices(id){
	$.ajax( {
        url:'hes/getSmartDevices.epx?id='+id,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function deleteSmartDevices(id){
	$.ajax( {
        url:'hes/deleteSmartDevices.epx?id='+id,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTableWithName_25('table1','SmartDevices');
        }
     });
}
function saveOrUpdateSmartDevice(id){
	var formdata=$( "#addSmartDevice" ).serialize();
	$.ajax( {
        url:'hes/saveOrUpdateSmartDevice.epx',
        type: "POST",
        data:formdata,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTableWithName_25('table1','SmartDevices');
        }
     });
}
function getMACAddress(rfid){
	$.ajax( {
        url:'hes/getMACAddress.epx?id='+rfid,
        type: "GET",        
        beforeSend:function(){
        	
        },
        success:function(data) {
           $('#mac_address').html(data);           
        }
     });
}
function getProjectDetails(projid){
	$.ajax( {
        url:'hes/getProjectDetails.epx?id='+projid,
        type: "GET",        
        beforeSend:function(){
        	
        },
        success:function(data) {
        	var meter=$("#meter_serial_no").val();
          $("#manufacturer").val(data.split("@")[0]);
          $("#flag_id").val(data.split("@")[1]);
          
          var system_title=$("#flag_id").val().substring(0,3)+meter.substring(meter.length-5);
          $("#system_title").val(system_title);
          
          
          $('#manufacturer').attr('readonly', true);
          $('#flag_id').prop('readonly', true);
          $('#system_title').prop('readonly', true);
        }
     });
}
//---------------------------------------------------------------------------------------------------------------
function viewRFGateway(actionid){
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'hes/rfView.epx?actionid='+actionid,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTableWithName_25('table1','RFGateway');
        }
     });
}
function addRFGateway(){
	$.ajax( {
        url:'hes/addRF.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}
function editRFGateway(id){
	$.ajax( {
        url:'hes/editRF.epx?id='+id,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}
function deleteRFGateway(id){
	$.ajax( {
        url:'hes/deleteRF.epx?id='+id,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTableWithName_25('table1','RFGateway'); 
        }
     });
}
function saveOrUpdateRFGateway(){
	var formdata=$( "#addRFGateway" ).serialize();
//	console.log("Form data...")
//	console.log("----------"+formdata);
	
	$.ajax( {
        url:'hes/addRFGateway.epx',
        type: "POST",
        data:formdata,
        beforeSend: function(msg){
        	$('#datapart').html($('#datapart').html());
          }, 
        success:function(data) {
           $('#datapart').html(data);
           callDataTableWithName_25('table1','RFGateway');
        }
     });
}
function updateRFGateway(){
	var formdata=$( "#addRFGateway" ).serialize();
	$.ajax( {
        url:'hes/updateRFGateway.epx',
        type: "POST",
        data:formdata,
        beforeSend: function(msg){
        	$('#datapart').html($('#datapart').html());
          }, 
        success:function(data) {
           $('#datapart').html(data);
           callDataTableWithName_25('table1','RFGateway');
        }
     });
}
//---------------------------------------------------------------------------------------------------------------
function viewSmartProjects(actionid){
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'hes/projectView.epx?actionid='+actionid,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTableWithName_25('table1','SmartProjects');
        }
     });
}
function addSmartProjects(){
	$.ajax( {
        url:'hes/addSmartProject.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}
function addSmartProjects(){
	$.ajax( {
        url:'hes/addSmartProject.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}
function editProject(projid){
	$.ajax( {
        url:'hes/editSmartProject.epx?project_id='+projid,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}
function deleteProject(projid){
	$.ajax( {
        url:'hes/deleteSmartProject.epx?project_id='+projid,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTableWithName_25('table1','SmartProjects');
        }
     });
}
function saveOrUpdatesmartProject(){
	var formdata=$( "#addSmartProject" ).serialize();
	$.ajax( {
        url:'hes/addSmartProject.epx',
        type: "POST",
        data:formdata,
        beforeSend: function(msg){
        	$('#datapart').html($('#datapart').html());
          }, 
        success:function(data) {
           $('#datapart').html(data);
           callDataTableWithName_25('table1','SmartProjects');
        }
     });
}