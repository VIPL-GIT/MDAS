function getConfigHome()
{
	hideDashboardDiv();
	
	$.ajax( {
        url:'config/configHome.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}

function changeConfig(traid){
	$.ajax({
		url:"config/config_page.epx?traid="+traid,
		beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        }
		
	}).done(
		function(data){				
			 $("#datapart").html(data);
			
		});
}
function getControlHome()
{
	hideDashboardDiv();
	
	$.ajax( {
        url:'control/controlHome.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}

function changeControl(traid){
	$.ajax({
		url:"control/control_page.epx?traid="+traid,
		beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        }
		
	}).done(
		function(data){				
			 $("#datapart").html(data);
			
		});
}
function sendLatLon(type,traid){
	
	var cmd="rdevCmd 3000 ";
	if(traid=="all") cmd=cmd+"A00";
	else cmd=cmd+"T"+traid.split("T")[1];
	
	if(type=="latlon"){
		cmd=cmd+"EN"+$("#lat").val()+":"+$("#lon").val()+":500:"+$("#zone").val();
	 }
    if(type=="datetime"){
		cmd=cmd+"RT"+$("#time").val()+":"+$("#date").val()
	 }
    if(type=="ewlimits"){
	   cmd=cmd+"SE"+$("#el").val()+":"+$("#wl").val()+":1:"+$("#bta").val()
     }
    if(type=="auto")    	cmd=cmd+"AU";
    if(type=="manual")    	cmd=cmd+"MN";
    if(type=="manual_east") cmd=cmd+"ES";
    if(type=="manual_west") cmd=cmd+"WE";
    if(type=="stow")        cmd=cmd+"SW";
	
	swal({
		title: "Are you sure you want to update configuration?", 
		text: "If you are sure, type in your password:", 
		type: "input",
		inputType: "password",
		showCancelButton: true,
		closeOnConfirm: false,
		closeOnCancel: true
	},function(typedPassword) {
      
	
if(typedPassword){
		if (typedPassword === "") {
			swal.showInputError("You need to type in your password in order to do this!");
	        return false;
		}

		$.ajax({
			url: "config/send_config.epx",
	        data: { password: typedPassword,
	        	    cmd:cmd},
	        type: "POST"
		})
		.done(function(data) {
	        swal("Response", data, "success");
		})
		.error(function(data) {			
	        swal.showInputError(data.responseText);
	        
		});
}
	
       
	});
        
}
