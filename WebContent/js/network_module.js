//----------------------------feeder----------------------------------
function addFeeder(){
	$.ajax( {
		type: "GET",
        url:'feeder/addFeeder.epx',   
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);           
        }
     });
}
function editFeeder(feederid){
	$.ajax( {
		type: "GET",
        url:'feeder/editFeeder.epx?feederid='+feederid,   
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);           
        }
     });
}
function saveOrUpdateFeeder(){
	
	$("#substation_id" ).val($("#level_"+$("#level").val()).val());
	 var url=$( "#addFeeder" ).serialize();
		$.ajax( {
	        url:'feeder/addFeeder.epx',
	        type: "POST",
	        data:url,
	        beforeSend: function(msg){
	        	$('#networkdata').html($('#loading').html());
	          }, 
	        success:function(data) {
	           $('#networkdata').html(data);
	           callDataTable('table1');
	        }
	     });
}
function viewFeeder(actionId){
	$('#networkdata').empty();
	$.ajax( {
        url:'feeder/viewFeeder.epx?actionId='+actionId,
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           callDataTable('table1');
        }
     });
}
function deleteFeeder(feederId){
	//alert("in delete client"+feederId);
	$.ajax( {
        url:'feeder/deleteFeeder.epx?feederid='+feederId,
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           callDataTable('table1');
        }
     });
}
function getFeederBulkHome(){
	$.ajax( {
        url:'feeder/bulkHome.epx',
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           
        }
     });
}
function getFeeders(ssid,divid){
	$.ajax( {
        url:'getFeedersByssID.jsp?ssid='+ssid,
        success:function(data) {        	
           $('#'+divid).html(data);
           
        }
     });
}
function getChilds(feederid){
	
	var type=$("#install_type").val();
	var urlstr="";
	if(type=="6"){
		urlstr="getDtrsByfID.jsp?fid="+feederid;
	}
	if(type=="8"){
		urlstr="getCCsByfID.jsp?fid="+feederid;
	}
	if(type=="7"){
		urlstr="getFeedersByssID.jsp?ssid="+feederid;
	}	
	$.ajax( {
        url:urlstr,
        success:function(data) {   
        	if(type=="7")
        		$('#feederSel').html(data);
        	else
              $('#childsSel').html(data);
           
        }
     });
	
		
	
}
//----------------------------feeder----------------------------------
function addIPFeeder(){
	$.ajax( {
		type: "GET",
        url:'ipfeeder/addFeeder.epx',   
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);           
        }
     });
}
function editIPFeeder(feederid){
	$.ajax( {
		type: "GET",
        url:'ipfeeder/editFeeder.epx?feederid='+feederid,   
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);           
        }
     });
}
function saveOrUpdateIPFeeder(){
	
	$("#substation_id" ).val($("#level_"+$("#level").val()).val());
	 var url=$( "#addFeeder" ).serialize();
		$.ajax( {
	        url:'ipfeeder/addFeeder.epx',
	        type: "POST",
	        data:url,
	        beforeSend: function(msg){
	        	$('#networkdata').html($('#loading').html());
	          }, 
	        success:function(data) {
	           $('#networkdata').html(data);
	           callDataTable('table1');
	        }
	     });
}
function viewIPFeeder(actionId){
	
	$.ajax( {
        url:'ipfeeder/viewFeeder.epx?actionId='+actionId,
        beforeSend: function(msg){
        	//$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           callDataTable('table1');
        }
     });
}
function deleteIPFeeder(feederId){
	//alert("in delete client"+feederId);
	$.ajax( {
        url:'ipfeeder/deleteFeeder.epx?feederid='+feederId,
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           callDataTable('table1');
        }
     });
}
function getIPFeederBulkHome(){
	$.ajax( {
        url:'ipfeeder/bulkHome.epx',
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           
        }
     });
}
//----------------------------DTR----------------------------------
function addDTR(){
	$.ajax( {
		type: "GET",
        url:'dtr/addDTR.epx',   
        beforeSend: function(msg){
        	//$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);           
        }
     });
}
function editDTR(dtrid){
	$.ajax( {
		type: "GET",
        url:'dtr/editDTR.epx?dtrid='+dtrid,   
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);           
        }
     });
}
function saveOrUpdateDTR(){
	//alert("save dtr");
	$("#feeder_id" ).val($("#feederID").val());

	 var url=$( "#addFeeder" ).serialize();
		$.ajax( {
	        url:'dtr/addDTR.epx',
	        type: "POST",
	        data:url,
	        beforeSend: function(msg){
	        	$('#networkdata').html($('#loading').html());
	          }, 
	        success:function(data) {
	           $('#networkdata').html(data);
	           callDataTable('table1');
	        }
	     });
}
function viewDTRs(actionId){
	//alert("viewtr");
	//hideDashboardDiv();
	$.ajax( {
        url:'dtr/viewDTR.epx?actionId='+actionId,
        beforeSend: function(msg){
        //	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           callDataTable('table1');
        }
     });
}
function deleteDTR(feederId){
	//alert("in delete client");
	$.ajax( {
        url:'dtr/deleteDTR.epx?dtrid='+feederId,
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           callDataTable('table1');
        }
     });
}
function getDTRBulkHome(){
	$.ajax( {
        url:'dtr/bulkHome.epx',
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           
        }
     });
}
//----------------------------consumer----------------------------------
function addConsumer(){
	$.ajax( {
		type: "GET",
        url:'cc/addCC.epx',   
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);           
        }
     });
}
function editConsumer(ccid){
	$.ajax( {
		type: "GET",
        url:'cc/editCC.epx?ccid='+ccid,   
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);           
        }
     });
}
function saveOrUpdateConsumer(){
	
	$("#feeder_id" ).val($("#level_"+$("#level").val()).val());
	 var url=$( "#addFeeder" ).serialize();
		$.ajax( {
	        url:'cc/addCC.epx',
	        type: "POST",
	        data:url,
	        beforeSend: function(msg){
	        	$('#networkdata').html($('#loading').html());
	          }, 
	        success:function(data) {
	           $('#networkdata').html(data);
	           callDataTable('table1');
	        }
	     });
}
function viewConsumers(actionId){
	//alert("viewconsumer");
	//hideDashboardDiv();
	$.ajax( {
        url:'cc/viewCC.epx?actionId='+actionId,
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           callDataTable('table1');
        }
     });
}
function deleteConsumer(cid){
	//alert("in delete client");
	$.ajax( {
        url:'cc/deleteConsumer.epx?cid='+cid,
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           callDataTable('table1');
        }
     });
}
function getConsumerBulkHome(){
	$.ajax( {
        url:'cc/bulkHome.epx',
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           
        }
     });
}
//----------------------------PSL consumer----------------------------------
function addPSLConsumer(){
	$.ajax( {
		type: "GET",
        url:'psl/addCC.epx',   
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);           
        }
     });
}
function editPSLConsumer(ccid){
	$.ajax( {
		type: "GET",
        url:'psl/editCC.epx?ccid='+ccid,   
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);           
        }
     });
}
function saveOrUpdatePSLConsumer(){
	$("#feeder_id" ).val($("#feederID").val());
	 var url=$( "#addFeeder" ).serialize();
		$.ajax( {
	        url:'psl/addCC.epx',
	        type: "POST",
	        data:url,
	        beforeSend: function(msg){
	        	$('#networkdata').html($('#loading').html());
	          }, 
	        success:function(data) {
	           $('#networkdata').html(data);
	           callDataTable('table1');
	        }
	     });
}
function viewPSLConsumers(actionId){
	//alert("viewconsumer");
	//hideDashboardDiv();
	$.ajax( {
        url:'psl/viewCC.epx?actionId='+actionId,
        beforeSend: function(msg){
        	//$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           callDataTable('table1');
        }
     });
}
function deletePSLConsumer(cid){
	//alert("in delete client");
	$.ajax( {
        url:'psl/deleteConsumer.epx?cid='+cid,
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           callDataTable('table1');
        }
     });
}
function getPSLConsumerBulkHome(){
	$.ajax( {
        url:'psl/bulkHome.epx',
        beforeSend: function(msg){
        	$('#networkdata').html($('#loading').html());
          }, 
        success:function(data) {
           $('#networkdata').html(data);
           
        }
     });
}
//---------------------------bulk upload ing --------------------------------
function viewBulkHome(actionId){
	hideDashboardDiv();
	$.ajax( {
        url:'nhdata/bulkHome.epx?actionId='+actionId,
        beforeSend: function(msg){
        	$('#datapart').html($('#loading').html());
          }, 
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}
