function addDevice(){
	$.ajax( {
		type: "GET",
        url:'device/addDevice.epx',        
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}
function saveDevice(){
	var validator=$("#device").validate({
		rules:{
			make:"required",
			type:"required",
			paramscount:"required",
			supportparams:"required"
					
		}
	});
	if(validator.form()){
		
		var baudrate = new Array();
		$("input:checkbox[name=baudrate]:checked").each(function() {
			baudrate.push($(this).val());
		  });
		var supportparams = new Array();
		$("input:checkbox[name=supportparams]:checked").each(function() {
			supportparams.push($(this).val());
		  });
		var formData = new FormData();
		formData.append('deviceMakeId',$("#make").val());
		formData.append('deviceTypeId',$("#type").val());
		formData.append('supportedParamsCount',$("#paramscount").val());
		formData.append('supportedBaudrate',baudrate.join());
		formData.append('paramsId',supportparams.join());
		formData.append("activeFlg","1");
		$.ajax( {
			type: "POST",
	        url:'device/addDevice.epx',
//	        data:"deviceMakeId="+$("#make").val()+"&deviceTypeId="+$("#type").val()+"&supportedParamsCount="+$("#paramscount").val()+"&activeFlg=1",
	        data: formData,	
	        contentType: false,
	        processData: false,
	        traditional: true,
	        success:function(data) {
	           $('#datapart').html(data);
	           callDataTable('table1');
	        }
	     });
	}
}
function updateDevice(deviceId){
	var validator=$("#device").validate({
		rules:{
			make:"required",
			type:"required",
			paramscount:"required",
			supportparams:"required"
					
		}
	});
	if(validator.form()){
//
		var baudrate = new Array();
		$("input:checkbox[name=baudrate]:checked").each(function() {
			baudrate.push($(this).val());
		  });
		var supportparams = new Array();
		$("input:checkbox[name=supportparams]:checked").each(function() {
			supportparams.push($(this).val());
		  });
		var formData = new FormData();
		formData.append('deviceId',deviceId);
		formData.append('deviceMakeId',$("#make").val());
		formData.append('deviceTypeId',$("#type").val());
		formData.append('supportedParamsCount',$("#paramscount").val());
		formData.append('supportedBaudrate',baudrate.join());
		formData.append('paramsId',supportparams.join());
		formData.append("activeFlg",$("#status").val());
		//alert($("#make").val());
		//alert($("#type").val())
		$.ajax( {
			type: "POST",
	        url:'device/addDevice.epx',
	        data: formData,
//	        data:"deviceId="+deviceId+"&deviceMakeId="+$("#make").val()+"&deviceTypeId="+$("#type").val()+"&supportedParamsCount="+$("#paramscount").val()+"&activeFlg="+$("#status").val(),
	        contentType: false,
	        processData: false,
	        traditional: true,
	        success:function(data) {
	           $('#datapart').html(data);
	           callDataTable('table1');
	        }
	     });
	}
}
function editDevice(deviceId){
	$.ajax( {
		type: "GET",
        url:'device/editDevice.epx?deviceId='+deviceId,        
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}
function deleteDevice(deviceId){
	$.ajax( {
        url:'device/deleteDevice.epx?deviceId='+deviceId,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function viewDevice(actionId){
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'device/viewDevice.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
//----------------------------------  device memory map --------------------------------------------
function viewMemoryMap(actionId){
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'deviceconfig/viewDeviceConfigPage.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function getDeviceMap(deviceId){
	$.ajax( {
        url:'deviceconfig/viewDeviceConfig.epx?device_id='+deviceId,
        success:function(data) {
           $('#deviceConfig').html(data);
           callDataTable('table1');
           document.getElementById("updateButton").style.display="block";
        }
     });
}
function deleteAllDeviceMap(){
	var deviceId=$("#device").val();
	$.ajax( {
        url:'deviceconfig/deleteConfig.epx?deviceid='+deviceId,
        success:function(data) {
           $('#deviceConfig').html(data);
           callDataTable('table1');
           document.getElementById("updateButton").style.display="block";
        }
     });
}
function deleteDeviceConfigById(memoryMapid){
	var deviceId=$("#device").val();
	$.ajax( {
        url:'deviceconfig/deleteConfigById.epx?memoryMapid='+memoryMapid+'&deviceId='+deviceId,
        success:function(data) {
        	 $('#deviceConfig').html(data);
             callDataTable('table1');
        }
     });
}
function updateAllDeviceConfig(){	
	var ids = new Array();
	var register_address=new Array();
	var descs = new Array();
	var mfs = new Array();
	var register_size=new Array();
	var function_code=new Array();
	var eFlgs = new Array();
	var default_add_flg = new Array();
	var names = new Array();
	
	
	
	$("input:hidden[name=ids]").each(function() {
		ids.push($(this).val());
	  });
	$("input:text[name=register_address]").each(function() {
		register_address.push($(this).val());
	  });
	
	$("input:text[name=desc]").each(function() {
		descs.push($(this).val());
	  });
	$("select[name=mf]").each(function() {
		mfs.push($(this).val());
	  });
	$("select[name=register_size]").each(function() {
		register_size.push($(this).val());
	  });
	$("select[name=function_code]").each(function() {
		function_code.push($(this).val());
	  });
	$("input:checkbox[name=energyParam]").each(function() {
		eFlgs.push($(this).is(':checked')?1:0);
	  });
	$("input:checkbox[name=default_add_flg]").each(function() {
		default_add_flg.push($(this).is(':checked')?1:0);
	  });
	$("select[name=names]").each(function() {
		names.push($(this).val());
	  });
	var device=$("#device").val();
	
	$.ajax( {
        url:'deviceconfig/updateAllParams.epx?ids='+ids.join()+'&names='+names.join()+'&descs='+descs.join()+
            '&register_address='+register_address.join()+'&register_size='+register_size.join()+'&function_code='+function_code.join()+
            '&mfs='+mfs.join()+'&eFlgs='+eFlgs.join()+'&default_add_flg='+default_add_flg+'&device='+device,
        success:function(data) {
        	 $('#deviceConfig').html(data);
             callDataTable('table1');
        }
     });
		
}
function updateDeviceConfig(id,index){
	var name=$("#name_"+index).val();
	var desc=$("#desc_"+index).val();
	var mf=$("#mf_"+index).val();
	var eflg=$("#eflg_"+index).is(':checked')?1:0;
	var diflg=$("#diflg_"+index).is(':checked')?1:0;
	var device=$("#device").val();
	$.ajax( {
        url:'deviceconfig/updateParam.epx?device_id='+device+'&memory_map_id='+id+'&parameter_name='+name+
             '&description='+desc+'&mf='+mf+'&energy_flg='+eflg+'&di_flg='+diflg,
        success:function(data) {
        	 $('#deviceConfig').html(data);
             callDataTable('table1');
        }
     });
}
//------------------------------data logger ---------------------------------
function viewDataLogger(actionId){
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'logger/viewLogger.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function addLogger(){
	
	$.ajax( {
        url:'logger/addLogger.epx',
        success:function(data) {
           $('#datapart').html(data);
           callForDates();
        }
     });

}
function saveLogger(){
	var validator = $( "#addLogger" ).validate({
		rules:{
			logger:"required",
			mobile:"required",
			lattitude:"required",
			longitude:"required",			
			date:"required"
//			noOfLights:"required",
//			ampPerLight:"required"
		}
	});
	if(validator.form()){
	var logger_sl_no=$("#logger").val();
	var hw_version=$("#hw_version").val();
	var fw_version=$("#fw_version").val();
	var mobile=$("#mobile").val();
	var delivery_date=$("#date").val();	
	var lattitude=$("#lattitude").val();
	var longitude=$("#longitude").val();
//	var noOfLights=$("#noOfLights").val();
//	var ampPerLight=$("#ampPerLight").val();
	if($("#level").length!=0)
	  var location_id=$("#level_"+$("#level").val()).val();
	
	
	var url="logger_sl_no="+logger_sl_no+"&hw_version="+hw_version+"&fw_version="+fw_version+"&mobile="+mobile+
	"&delivery_date="+delivery_date+"&lattitude="+lattitude+"&longitude="+longitude;
//	+"&no_of_lights="+noOfLights+"&amp_per_light="+ampPerLight;
		
	if($("#level").length!=0)
			url=url+"&location_id="+location_id;
	$.ajax( {
        url:'logger/addLogger.epx',
        type: "POST",
        data:url,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
	}
}
function updateLogger(logger_id){
	var validator = $( "#addLogger" ).validate({
		rules:{
			logger:"required",
			mobile:"required",
			lattitude:"required",
			longitude:"required",			
			date:"required"
//			noOfLights:"required",
//			ampPerLight:"required"
		}
	});
	if(validator.form()){
	var logger_sl_no=$("#logger").val();
	var hw_version=$("#hw_version").val();
	var fw_version=$("#fw_version").val();
	var mobile=$("#mobile").val();
	var delivery_date=$("#date").val();	
	var lattitude=$("#lattitude").val();
	var longitude=$("#longitude").val();
//	var noOfLights=$("#noOfLights").val();
//	var ampPerLight=$("#ampPerLight").val();
	
	if($("#level").length!=0)
	  var location_id=$("#level_"+$("#level").val()).val();
	
	
	var url="logger_id="+logger_id+"&logger_sl_no="+logger_sl_no+"&hw_version="+hw_version+"&fw_version="+fw_version+
	"&mobile="+mobile+"&delivery_date="+delivery_date+"&lattitude="+lattitude;
//	+"&longitude="+longitude+"&no_of_lights="+noOfLights+"&amp_per_light="+ampPerLight;
		
	if($("#level").length!=0)
			url=url+"&location_id="+location_id;
	$.ajax( {
        url:'logger/addLogger.epx',
        type: "POST",
        data:url,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
	}
}
function editLogger(logger_id){
	$.ajax( {
        url:'logger/editLogger.epx?logger_id='+logger_id,
        success:function(data) {
           $('#datapart').html(data);
           callForDates();
        }
     });
}
function deleteLogger(logger_id){
	$.ajax( {
        url:'logger/deleteLogger.epx?logger_id='+logger_id,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
//----------------------------------- IEDs ------------------------------------------
function viewIed(actionId){
	hideDashboardDiv();
	$.ajax( {
        url:'ied/viewied.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function addIED(){
	
}
function deleteIED(){
	
}
function editIED(){
	
}