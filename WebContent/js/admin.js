var sldFunction;
var dash;
function clear(){
	//alert("in clear");
	clearInterval(sldFunction);
	clearInterval(dash);
}

function addClient(){
	$.ajax( {
		type: "GET",
        url:'client/addClient.epx',        
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}



function saveClient(){
	var validator=$("#addClient").validate({
		rules:{
			client:"required",
			contactperson:"required",
			mobile:"required",
			clientimage:"required"			
		}
	});
	if(validator.form()){
	var modules = new Array();
	$("input:checkbox[name=submodules]:checked").each(function() {
		modules.push($(this).val());
	  });
	
	var formData = new FormData();
	formData.append('clientName',$("#client").val());
	formData.append('aboutClient',$("#client_desc").val());
	formData.append('add1',$("#address1").val());
	formData.append('add2',$("#address2").val());
	formData.append('email',$("#email").val());
	formData.append('mobile',$("#mobile").val());
	formData.append('contactPerson',$("#contact_person").val());
	formData.append('activeStatus',1);
	formData.append('submodules',modules.join());
	if($('input[type=file]')[0].files[0]!=undefined)
	formData.append('client_img_file',$('input[type=file]')[0].files[0]);
	

	
	$.ajax( {
		type: "POST",
        url:'client/addClient.epx',
        data: formData,
        contentType: false,
        processData: false,
//        data:"clientName="+clientName+"&aboutClient="+aboutClient+"&add1="+add1+"&add2="+add2+"&email="+email+"&mobile="
//        +mobile+"&contactPerson="+contactPerson+"&submodules="+modules.join(),
        
        success:function(data) {
           $('#datapart').html(data);
           callForDates();
        }
     });
	}
}
function updateClient(clientId){
	var validator=$("#editClient").validate({
		rules:{
			client:"required",
			contactperson:"required",
			mobile:"required",
//			clientimage:"required"			
		}
	});
	if(validator.form()){
	var modules = new Array();
	$("input:checkbox[name=submodules]:checked").each(function() {
		modules.push($(this).val());
	  });
	
	var formData = new FormData();
	formData.append('clientId',clientId);
	formData.append('clientName',$("#client").val());
	formData.append('aboutClient',$("#client_desc").val());
	formData.append('add1',$("#address1").val());
	formData.append('add2',$("#address2").val());
	formData.append('email',$("#email").val());
	formData.append('mobile',$("#mobile").val());
	formData.append('contactPerson',$("#contact_person").val());
	formData.append('activeStatus',$("#status").val());
	formData.append('submodules',modules.join());
	if($('input[type=file]')[0].files[0]!=undefined)
	formData.append('client_img_file',$('input[type=file]')[0].files[0]);
	
//	var clientName=$("#client").val();
//	var aboutClient=$("#client_desc").val();
//	var add1=$("#address1").val();
//	var add2=$("#address2").val();
//	var email=$("#email").val();
//	var mobile=$("#mobile").val();
//	var contactPerson=$("#contact_person").val();
//	var modules = new Array();
//	$("input:checkbox[name=submodules]:checked").each(function() {
//		modules.push($(this).val());
//	  });
	
	$.ajax( {
		type: "POST",
        url:'client/addClient.epx',
        data: formData,
        contentType: false,
        processData: false,
//        data:"clientId="+clientId+"&clientName="+clientName+"&aboutClient="+aboutClient+"&add1="+add1+"&add2="+add2+"&email="+email+"&mobile="
//        +mobile+"&contactPerson="+contactPerson+"&submodules="+modules.join(),
        success:function(data) {
           $('#datapart').html(data);
           callForDates();
        }
     });
	}
}
function deleteClient(clientId){
	//alert("in delete client");
	$.ajax( {
        url:'client/deleteClient.epx?clientId='+clientId,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function editClient(ClientId){
	$.ajax( {
        url:'client/editClient.epx?ClientId='+ClientId,
        success:function(data) {
           $('#datapart').html(data);
           callForDates();
        }
     });
}
function viewClient(actionId){
	hideDashboardDiv();
	//alert("-->"+actionId);
	$.ajax( {
        url:'client/viewClient.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
//block for modem --------------------
function addModem(){
	
	$.ajax( {
		type: "GET",
        url:'modem/addModem.epx',        
        success:function(data) {
           $('#datapart').html(data);
           callForDates();
        }
     });
}
function saveModem(){	
	
//	var validator = $( "#addModem" ).validate({
//		messages:{
//			modemSlNo:"please enter modem serial no "			
//		}
//	});
	var validator = $( "#addModem" ).validate();
	if(validator.form()){
	
	var modemSlNo=$("#modemSlNo").val();
	var hw_version=$("#hw_version").val();
	var fw_version=$("#fw_version").val();
	var mobile=$("#mobile").val();
	var date=$("#date").val();
	
	$.ajax( {
		type: "POST",
        url:'modem/addModem.epx',
        data:"modemSlNo="+modemSlNo+"&hw_version="+hw_version+"&fw_version="+fw_version+"&mobile="+mobile+"&delivery_date="+date,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
	}//if valid form
}
function updateModem(modemId){
	var validator = $( "#editModem" ).validate();
	if(validator.form()){
	var modemSlNo=$("#modemSlNo").val();
	var hw_version=$("#hw_version").val();
	var fw_version=$("#fw_version").val();
	var mobile=$("#mobile").val();
	var date=$("#date").val();
	//alert("modemId="+modemId+"&modemSlNo="+modemSlNo+"&hw_version="+hw_version+"&fw_version="+fw_version+"&mobile="+mobile+"&delivery_date="+date);
	$.ajax( {
		type: "POST",
        url:'modem/addModem.epx',
        data:"modemId="+modemId+"&modemSlNo="+modemSlNo+"&hw_version="+hw_version+"&fw_version="+fw_version+"&mobile="+mobile+"&delivery_date="+date,
        
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
	}
}
function editModem(modemId){
	$.ajax( {
        url:'modem/editModem.epx?modemId='+modemId,
        success:function(data) {
           $('#datapart').html(data);
           callForDates();
        }
     });
}
function viewModem(actionId){
	//alert("viewmodem");
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'modem/viewModem.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function deleteModem(modemId){
	$.ajax( {
        url:'modem/deleteModem.epx?modemId='+modemId,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
//block for meter --------------------
function addMeter(){
	$.ajax( {
		type: "GET",
        url:'meter/addMeter.epx',  
        beforeSend: function(msg){
        	$('#datapart').html($('#loader').html());
          },
        success:function(data) {
           $('#datapart').html(data);
           //callForDates();
        }
     });
}
function deleteMeter(meterId){
	$.ajax( {
        url:'meter/deleteMeter.epx?meter_id='+meterId,
        beforeSend: function(msg){
        	$('#datapart').html($('#loader').html());
          },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}

/*function saveMeter(){
	var validator = $( "#addMeter" ).validate({
		rules:{
			meter:"required",
			device_id:"required",
			min_level:"required",
			max_level_1:"required",
			max_level_2:"required",
			max_level_3:"required",
			meter_type:"required",
//			lattitude:"required",
//			longitude:"required",
//			pulse:"required",
//			initialreading:"required",
			date:"required"
		}
	});
	if(validator.form()){
	var meter_sl_no=$("#meter").val();
	var device_id=$("#device_id").val();
	var installation_date=$("#date").val();
	var min_level=$("#min_level").val();
	var max_level_1=$("#max_level_1").val();
	var max_level_2=$("#max_level_2").val();
	var max_level_3=$("#max_level_3").val();
	var meter_type=$("#meter_type").val();
	var is_adminmeter=$("#isadmin").is(':checked') ? 1 : 0;
    if(is_adminmeter==0)
	  var adminmeter_id=$("#adminmeter").val();
	if($("#loc_type").val()==0){
		alert("Please map to any location");
		return 0;
	}
	if($("#level").length!=0)
	  var location_id=$("#level_"+$("#level").val()).val();
//	var initial_reading=$("#reading").val();
//	var lattitude=$("#lattitude").val();
//	var longitude=$("#longitude").val();
//	var pulse=$("#pulse").val();
	
	var url="meter_sl_no="+meter_sl_no+"&device_id="+device_id+"&installation_date="+installation_date
	+"&min_level="+min_level+"&max_level_1="+max_level_1+"&max_level_2="+max_level_2+"&max_level_3="+max_level_3
	+"&meter_type="+meter_type+"&is_adminmeter="+is_adminmeter;
//	+"&initial_reading="+initial_reading+"&lattitude="+lattitude+"&longitude="+longitude+"&pulse="+pulse;
	if(is_adminmeter==0)
		url=url+"&adminmeter_id="+adminmeter_id;
		if($("#level").length!=0)
			url=url+"&location_id="+location_id;
		//alert(url);
	$.ajax( {
        url:'meter/addMeter.epx',
        type: "POST",
        data:url,
        success:function(data) {
           $('#datapart').html(data);  
           callDataTable('table1');
        }
     });
	}
}*/


function saveMeter(){
	var validator = $( "#addMeter" ).validate({
		rules:{
			meter:"required",
			modem:"required",
			date:"required"
		}
	});
	if(validator.form()){
	  if($("#install_type").val()=="7")	
	     $("#location_id" ).val($("#feederID").val()); //for feeder
	  if($("#install_type").val()!="7")
		  $("#location_id" ).val($("#childID").val()); //for DTR and consumer
	  
	 // alert("-------------- "+$("#location_id" ).val());
	 var url=$( "#addMeter" ).serialize();
	 
	$.ajax( {
        url:'meter/addMeter.epx',
        type: "POST",
        data:url,
        beforeSend: function(msg){
        	$('#datapart').html($('#loader').html());
          }, 
        success:function(data) {
           $('#datapart').html(data); 
           callDataTable('table1');
        }
     });
	}
}

function viewDG(actionId){
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'nhdata/viewNHdata.epx?hlevel=Feeder&actionId='+actionId,
        beforeSend: function(msg){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
          }, 
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
           viewFeeder('26');
        }
     });
}


function updateMeter(meterId){
//alert(meterId);
	var validator = $( "#editMeter" ).validate({
		rules:{
			meter:"required",
			modem:"required",			
			date:"required"
		}
	});
	if(validator.form()){
		
		if($("#install_type").val()=="7")	
		     $("#location_id" ).val($("#feederID").val()); //for feeder
		  if($("#install_type").val()!="7")
			  $("#location_id" ).val($("#childID").val()); //for DTR and consumer
		
		  
	var url=$( "#editMeter" ).serialize();
	$.ajax( {
        url:'meter/addMeter.epx',
        type: "POST",
        data:url,
        beforeSend: function(msg){
        	$('#datapart').html($('#loader').html());
          }, 
        success:function(data) {
           $('#datapart').html(data); 
           callDataTable('table1');
        }
     });
	}
	
}


function editMeter(meterId){
	$.ajax( {
        url:'meter/editMeter.epx?meter_id='+meterId,
        success:function(data) {
           $('#datapart').html(data);
           callForDates();
        }
     });
}
function changeMeter(meter){
	var modal = document.getElementById("bi_modal");
		
	$.ajax({
		url:'changeMeter.jsp?meter='+meter,
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
	       error : function(err) {}
	   });
}
function changeMeterAction(){
	var url=$( "#changeMeterForm" ).serialize();
	$.ajax( {
        url:'meter/changeMeter.epx',
        type: "POST",
        data:url,
        beforeSend: function(msg){
        	document.getElementById("bi_modal").innerHTML=document.getElementById("loader").innerHTML;
          }, 
        success:function(data) {
           $('#datapart').html(data); 
           callDataTable('table1');
        }
     });
	
}
function viewMeter(actionId){
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'meter/viewMeter.epx?actionId='+actionId,	
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
//------------block for consumer ------------------------
function addConsumer(){
	$.ajax( {
		type: "GET",
        url:'consumer/addConsumer.epx',        
        success:function(data) {
           $('#datapart').html(data);          
        }
     });
}
function deleteConsumer(cId){
	hideDashboardDiv();
	$.ajax( {
        url:'consumer/deleteConsumer.epx?consumer_no='+cId,		
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function saveConsumer(){
	var validator=$("#addConsumer").validate({
		rules:{
			name:"required",
			primary:"required",
			email:"required",
			add1:"required",
			add2:"required"
		}
	});
	if(validator.form()){
//	var consumer_no;	
	var consumer_name=$("#name").val();	
	var permanent_address=$("#primary").val();	
	var billing_address=$("#billing").val();	
	var landline=$("#landline").val();	
	var mobile1=$("#mobile1").val();	
	var mobile2=$("#mobile2").val();	
	var email=$("#email").val();	
	var connection_status=1;	//default consumer is active 
	
	if($("#level").length!=0){
		 var hierarchy_level=$("#level").val();
		 var hierarchy_id=$("#level_"+$("#level").val()).val();
	}
	 
	var datastring="consumer_name="+consumer_name+"&permanent_address="+permanent_address+"&billing_address="+billing_address+
    "&landline="+landline+"&mobile1="+mobile1+"&mobile2="+mobile2+"&email="+email+"&connection_status="+connection_status;
	
	if($("#level").length!=0){
		datastring=datastring+"&hierarchy_type_id="+hierarchy_level+"&hierarchy_id="+hierarchy_id;
	}
	
	$.ajax( {
        url:'consumer/addConsumer.epx',
        type: "POST",
        data:datastring,
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
	}
}
function updateConsumer(cId){
	var validator=$("#editConsumer").validate({
		rules:{
			name:"required",
			primary:"required",
			email:"required",
			add1:"required",
			add2:"required"
		}
	});
	if(validator.form()){
	var consumer_name=$("#name").val();	
	var permanent_address=$("#primary").val();	
	var billing_address=$("#billing").val();	
	var landline=$("#landline").val();	
	var mobile1=$("#mobile1").val();	
	var mobile2=$("#mobile2").val();	
	var email=$("#email").val();	
	var connection_status=$("#connectionstatus").val();	
	
	if($("#level").length!=0){
		var hierarchy_level=$("#level").val();
		var hierarchy_id=$("#level_"+$("#level").val()).val();
	}
	  
	var datastring="consumer_no="+cId+"&consumer_name="+consumer_name+"&permanent_address="+permanent_address+"&billing_address="+billing_address+
    "&landline="+landline+"&mobile1="+mobile1+"&mobile2="+mobile2+"&email="+email+"&connection_status="+connection_status;
	
	if($("#level").length!=0){
		datastring=datastring+"&hierarchy_type_id="+hierarchy_level+"&hierarchy_id="+hierarchy_id;
	}
	
	$.ajax( {
        url:'consumer/addConsumer.epx',
        type: "POST",
        data:datastring,
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
	}
}
function editConsumer(cId){
	$.ajax( {
        url:'consumer/editConsumer.epx?consumer_no='+cId,
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function viewConsumer(actionId){
	hideDashboardDiv();
	$.ajax( {
        url:'consumer/viewConsumer.epx?actionId='+actionId,	
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
//block for users --------------------
function addUser(){
	$.ajax( {
		type: "GET",
        url:'user/addUser.epx',        
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function deleteUser(useId){
	$.ajax( {
        url:'user/deleteUser.epx?userSlNo='+useId,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function editUser(userId){
	$.ajax( {
        url:'user/editUser.epx?slno='+userId,
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function saveUesr(){
	var validator=$("#addUser").validate({
		rules:{
			userid:"required",
			password:"required",
			confirmPassword:{required: true,equalTo: "#password"},
			client:"required",
			role:"required",
			file:"required"
		}
	});
	if(validator.form()){
	var formData = new FormData();
	formData.append('userId',$("#userid").val());
	formData.append('clientId',$("#clientId").val());
	formData.append('userFullName',$("#fullname").val());
	formData.append('password',$("#password").val());
	formData.append('address',$("#address").val());
	formData.append('email',$("#email").val());
	formData.append('mobileNumber',$("#mobile").val());
	formData.append('rollId',$("#role").val());
	formData.append('activeFlg',1);
	
	if($('input[type=file]')[0].files[0]!=undefined)
	formData.append('userImageFile',$('input[type=file]')[0].files[0]);
//------getting hierarchy id----------------------
	if($("#hlevelid").length!=0){
		if($("#level_"+$("#hlevel").val()).val()==0){
			//alert("Selected user role is "+$("#hleveltype").val()+" level.Please select upto "+$("#hleveltype").val());
			return 0;
		}
		formData.append('hierarchy',$("#level_"+$("#hlevel").val()).val());
	}
	
//	var userId=$("#userid").val();
//	var clientId=$("#clientId").val();
//	var userFullName=$("#fullname").val();
//	var password=$("#password").val();
//	var address=$("#address").val();
//	var email=$("#email").val();
//	var mobileNumber=$("#mobile").val();	
//	var rollId=$("#role").val();	
//  var userImage=mysrc;
	
	$.ajax( {
        url:'user/addUser.epx',
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,
//        data:"userId="+userId+"&clientId="+clientId+"&userFullName="+userFullName+"&password="+password+"&address="
//        +address+"&email="+email+"&mobileNumber="+mobileNumber+"&rollId="+rollId+"&activeFlg=1",
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
	}
}

function updateUser(slno){
	var validator=$("#editUser").validate({
		rules:{
			userid:"required",
//			password:"required",
//			confirmPassword:{required: true,equalTo: "#password"},
			client:"required",
			role:"required",
//			file:"required"
		}
	});
	if(validator.form()){
	//alert("--->"+$("#role").val());
	var formData = new FormData();
	formData.append('slno',slno);
	formData.append('userId',$("#userid").val());
	formData.append('clientId',$("#clientId").val());
	formData.append('userFullName',$("#fullname").val());
	formData.append('password',$("#pass").val());
	formData.append('new_password',$("#new_password").val());
	formData.append('address',$("#address").val());
	formData.append('email',$("#email").val());
	formData.append('mobileNumber',$("#mobile").val());
	formData.append('rollId',$("#role").val());
	formData.append('activeFlg',$("#status").val());
	
	if($('input[type=file]')[0].files[0]!=undefined) 
	  formData.append('userImageFile',$('input[type=file]')[0].files[0]);
	
	//------getting hierarchy id----------------------
	
	
	if($("#hlevelid").length!=0){
		if($("#level_"+$("#hlevel").val()).val()==0){
			//alert("Selected user role is "+$("#hleveltype").val()+" level.Please select upto "+$("#hleveltype").val());
			return 0;
		}
		formData.append('hierarchy',$("#level_"+$("#hlevel").val()).val());
	}
	
//	var userId=$("#userid").val();
//	var clientId=$("#clientId").val();
//	var userFullName=$("#fullname").val();
//	var password=$("#pass").val();
//	var address=$("#address").val();
//	var email=$("#email").val();
//	var mobileNumber=$("#mobile").val();	
//	var rollId=$("#role").val();
//	var userImage;
	
	$.ajax( {
        url:'user/addUser.epx',
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,
//        data:"slno="+slno+"&userId="+userId+"&clientId="+clientId+"&userFullName="+userFullName+"&password="+password+"&address="
//        +address+"&email="+email+"&mobileNumber="+mobileNumber+"&rollId="+rollId,
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
	}
}
function viewUsers(actionId){
	
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'user/viewUsers.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
//-------block for user roles ------------------------



function getEventNotification(actionId){
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'user/viewnotifications.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });	
}




function deleteEventNotification(username){
	
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'user/deleteEventNotification.epx?username='+username,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });	
}



function editEventNotification(actionId,alertid,alerttype,alertmode,isenabled){
  $.ajax( {
  url:'user/eventsnotifications.epx?actionId='+actionId+'&alertid='+alertid+'&alerttype='+alerttype+'&alertmode='+alertmode+'&isenabled='+isenabled,
  beforeSend:function(){
  	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
  },
  success:function(data) {
     $('#datapart').html(data);
     callDataTable('table1');
  }
  });	
}


function AddUserEventNotification(){
	
	$.ajax( {
		  url:'user/AddUserEventNotification.epx',
		  beforeSend:function(){
		  	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
		  },
		  success:function(data) {
		     $('#datapart').html(data);
		     callDataTable('table1');
		  }
		  });	
	
}


function AddUser(){

	var username=$("#userid").val();	
	var alerttype=$("#alerttype").val();
	var alertmode=$("#alertmode").val();
	var isenabled=$("#isenabled").val();
	
	$.ajax( {
		type: "POST",
        url:'user/AddUser.epx',
        data:"username="+username+"&alerttype="+alerttype+"&alertmode="+alertmode+'&isenabled='+isenabled,
        success:function(data) {
           $('#datapart').html(data);  
           callDataTable('table1');
        }
     });
	
}


function saveEventNotification(){

	var username=$("#username").val();	
	var alerttype=$("#alerttype").val();
	var alertmode=$("#alertmode").val();
	var isenabled=$("#isenabled").val();
    var alertid=$("#alertid").val();
	//alert(username+" "+alerttype+"  "+alertmode+"  "+isenabled+"  "+alertid);
	
	$.ajax( {
		type: "POST",
        url:'user/saveEventNotification.epx',
        data:"username="+username+"&alerttype="+alerttype+"&alertmode="+alertmode+'&isenabled='+isenabled+'&alertid='+alertid,
        success:function(data) {
           $('#datapart').html(data);  
           callDataTable('table1');
        }
     });
	
}


function addUserRole(){
	$.ajax( {
		type: "GET",
        url:'userRole/addUserRole.epx',        
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function saveUserRole(){
	var validator=$("#addUserRole").validate({
		rules:{
			role:"required",
			client:"required"
		}
	});
	if(validator.form()){
	var role_title=$("#role").val();
	var client_id=$("#client").val();
	var role_desc=$("#role_desc").val();
	var parent_role_id=$("#parent_role").val();	
	var hierarchy_level=$("#level").val();
	var modules = new Array();
	$("input:checkbox[name=action]:checked").each(function() {
		modules.push($(this).val());
	  });
	$.ajax( {
		type: "POST",
        url:'userRole/addUserRole.epx',        
        data:"role_title="+role_title+"&client_id="+client_id+"&role_desc="+role_desc+"&parent_role_id="+parent_role_id+"&action_flgs="+modules.join()+"&active_flg=1&hierarchy_level="+hierarchy_level,
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
	}
}
function updateUserRole(role_id){
	var validator=$("#editUserRole").validate({
		rules:{
			role:"required",
			client:"required"
		}
	});
	if(validator.form()){
	var role_title=$("#role").val();
	var client_id=$("#client").val();
	var role_desc=$("#role_desc").val();
	var parent_role_id=$("#parent_role").val();
	var hierarchy_level=$("#level").val();
	var status=$("#status").val();
	var modules = new Array();
	$("input:checkbox[name=action]:checked").each(function() {
		modules.push($(this).val());
	  });
	$.ajax( {
		type: "POST",
        url:'userRole/addUserRole.epx',        
        data:"role_id="+role_id+"&role_title="+role_title+"&client_id="+client_id+"&role_desc="+role_desc+"&parent_role_id="+parent_role_id+"&action_flgs="+modules.join()+"&active_flg="+status+"&hierarchy_level="+hierarchy_level,
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
	}
}
function editUserRole(roleId){
	$.ajax( {
        url:'userRole/editUserRole.epx?role_id='+roleId,
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}

function deleteUserRole(roleId){
	$.ajax( {
        url:'userRole/deleteUserRole.epx?role_id='+roleId,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function viewUserRoles(actionId){
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'userRole/viewUserRoles.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
//----------------------------block for hierarchy titles ----------------------------
function addTitle(){
	$.ajax( {
		type: "GET",
        url:'htitle/addTitle.epx',        
        success:function(data) {
           $('#datapart').html(data); 
           $('#location').bootstrapSwitch('state', true);
        }
     });
}
function deleteTitle(titleId){
	$.ajax( {
        url:'htitle/deleteTitle.epx?titleId='+titleId,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function editTitle(titleId){
	$.ajax( {
        url:'htitle/editTitle.epx?hierarchy_type_id='+titleId,
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function saveTitle(){
	var validator=$("#addTitle").validate({
		rules:{
			title:"required"
		}
	});
	if(validator.form()){
	var hierarchy_title=$("#title").val();
	var parent_title_id=$("#parent_title").val();
	var title_desc=$("#title_desc").val();
	var client_id=$("#client").val();
	var device_location=$("#location").is(':checked') ? 1 : 0;
	
	$.ajax( {
		type: "POST",
        url:'htitle/addTitle.epx',
        data:"hierarchy_title="+hierarchy_title+"&parent_title_id="+parent_title_id+"&title_desc="+title_desc+"&client_id="+client_id+"&active_flg=1&device_location="+device_location,
        success:function(data) {
           $('#datapart').html(data);
           callForDates();
        }
     });
	}
}
function updateTitle(titleId){
	var validator=$("#editTitle").validate({
		rules:{
			title:"required"
		}
	});
	if(validator.form()){
	var hierarchy_title=$("#title").val();
	var parent_title_id=$("#parent_title").val();
	var title_desc=$("#title_desc").val();
	var client_id=$("#client").val();
	var status=$("#status").val();
	var device_location=$("#location").is(':checked') ? 1 : 0;
	$.ajax( {
		type: "POST",
        url:'htitle/addTitle.epx',
        data:"hierarchy_type_id="+titleId+"&hierarchy_title="+hierarchy_title+"&parent_title_id="+parent_title_id+"&title_desc="+title_desc+"&client_id="+client_id+"&active_flg="+status+"&device_location="+device_location,
        success:function(data) {
           $('#datapart').html(data);
           callForDates();
        }
     });
	}
	
}
function viewTitles(actionId){
    clear();
	hideDashboardDiv();
	$.ajax( {
        url:'htitle/viewTitles.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
//---------------block for hierarchy data ------------------------------------
function addHdata(level){
	$.ajax( {
		type: "GET",
        url:'hdata/addHdata.epx?level='+level,        
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function deleteHdata(hId,level){
	//alert(hId+" delete "+level);
	$.ajax( {
        url:"hdata/deleteHdata.epx?hId="+hId+"&level="+level,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function saveHdata(level){
	
	var validator=$("#addData").validate({
		rules:{
			title:"required"			
		}
		
	});
	
	if(validator.form()){
	var name=$("#title").val();
	var code=$("#code").val();
	var client=$("#client").val();
	var htype=$("#htype").val();
	var parentId=(level==0)?0:$("#level_"+level).val();
	if(level>0 && parentId==0){
		alert("Please select parent hierarchy");
	}
	
	$.ajax( {
		type: "POST",
        url:'hdata/addHdata.epx',
        data:"hierarchy_name="+name+"&code="+code+"&parent_id="+parentId+"&client_id="+client+"&active_flg=1&hierarchy_type_id="+htype,
        success:function(data) {
           $('#datapart').html(data);
           callForDates();
        }
     });
	
	}
}

function updateHdata(level,hid){
	//alert(level+" update "+hid);
	var validator=$("#editData").validate({
		rules:{
			title:"required"			
		}
		
	});	
	if(validator.form()){
		
	var name=$("#title").val();
	var code=$("#code").val();
	var client=$("#client").val();
	var htype=$("#htype").val();
	var parentId=(level==0)?0:$("#level_"+level).val();
	var active_flg=$("#status").val();
	//alert("name --> "+name+" desc  --> "+desc+" client --> "+client+" parentId --> "+parentId+"  active_flg --> "+active_flg+" htype --> "+htype);
	if(level>0 && parentId==0){
		alert("Please select parent hierarchy");
	}
	$.ajax( {
		type: "POST",
        url:'hdata/addHdata.epx',
        data:"hierarchy_id="+hid+"&hierarchy_name="+name+"&code="+code+"&parent_id="+parentId+"&client_id="+client+"&active_flg="+active_flg+"&hierarchy_type_id="+htype,
        success:function(data) {
           $('#datapart').html(data);
           callForDates();
        }
     });
	}
}
function editHdata(hId,level){
	//alert(hId+"  "+level);
	$.ajax( {
        url:'hdata/editHdata.epx?hierarchy_id='+hId+'&level='+level,
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function viewHdata(actionId,level){
	 clear();
	hideDashboardDiv();
	$.ajax( {
        url:'hdata/viewHdata.epx?hlevel='+level+'&actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
//-----------------------------------------block for device type -----------------------------
function addDeviceType(){
	$.ajax( {
		type: "GET",
        url:'devicetype/addType.epx',        
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function saveDeviceType(){
	var validator=$("#deviceType").validate({
		rules:{
			type:"required"			
		}
		
	});	
	if(validator.form()){
		
	var type=$("#type").val();	
	var client=$("#clientId").val();
	
	$.ajax( {
		type: "POST",
        url:'devicetype/addType.epx',
        data:"deviceTypeName="+type+"&clientId="+client+"&activeFlg=1",
        success:function(data) {
           $('#datapart').html(data);  
           callDataTable('table1');
        }
     });
	}
}
function updateDeviceType(deviceTypeId){
	var validator=$("#deviceType").validate({
		rules:{
			type:"required"			
		}
		
	});	
	if(validator.form()){
		
	var type=$("#type").val();	
	var client=$("#clientId").val();
	var activeFlg=$("#status").val();
	$.ajax( {
		type: "POST",
        url:'devicetype/addType.epx',
        data:"deviceTypeId="+deviceTypeId+"&deviceTypeName="+type+"&clientId="+client+"&activeFlg="+activeFlg,
        success:function(data) {
           $('#datapart').html(data);  
           callDataTable('table1');
        }
     });
	}
}
function editDeviceType(deviceTypeId){
	$.ajax( {
        url:'devicetype/editType.epx?deviceTypeId='+deviceTypeId,
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function deleteDeviceType(deviceTypeId){
	$.ajax( {
        url:"devicetype/deleteType.epx?deviceTypeId="+deviceTypeId,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function viewDeviceType(actionId){
	//alert("view device type");
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'devicetype/viewType.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
//-----------------------------------------block for device make -----------------------------
function addDeviceMake(){
	$.ajax( {
		type: "GET",
        url:'devicemake/addMake.epx',        
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function saveDeviceMake(){
	var validator=$("#deviceMake").validate({
		rules:{
			make:"required"			
		}
		
	});	
	if(validator.form()){
		
	var make=$("#make").val();	
	var client=$("#clientId").val();
	
	$.ajax( {
		type: "POST",
        url:'devicemake/addMake.epx',
        data:"deviceMakeName="+make+"&clientId="+client+"&activeFlg=1",
        success:function(data) {
           $('#datapart').html(data);  
           callDataTable('table1');
        }
     });
	}
}
function updateDeviceMake(deviceMakeId){
	var validator=$("#deviceMake").validate({
		rules:{
			make:"required"			
		}
		
	});	
	if(validator.form()){
		
	var make=$("#make").val();	
	var client=$("#clientId").val();
	var activeflg=$("#status").val();
	$.ajax( {
		type: "POST",
        url:'devicemake/addMake.epx',
        data:"deviceMakeId="+deviceMakeId+"&deviceMakeName="+make+"&clientId="+client+"&activeFlg="+activeflg,
        success:function(data) {
           $('#datapart').html(data);  
           callDataTable('table1');
        }
     });
	}
}
function editDeviceMake(deviceMakeId){
	$.ajax( {
        url:'devicemake/editMake.epx?deviceMakeId='+deviceMakeId,
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function deleteDeviceMake(deviceMakeId){
	$.ajax( {
        url:"devicemake/deleteMake.epx?deviceMakeId="+deviceMakeId,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function viewDeviceMake(actionId){
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'devicemake/viewMake.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
//-------------------------------------parameter block ----------------------------------------
function addParameter(){
	$.ajax( {
		type: "GET",
        url:'parameter/addParam.epx',        
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
function deleteParameter(parameterId){
	$.ajax( {
		type: "GET",
        url:'parameter/deleteParam.epx?parameterId='+parameterId,        
        success:function(data) {
           $('#datapart').html(data); 
           callDataTable('table1');
        }
     });
}
function editParameter(parameterId){
	$.ajax( {
        url:'parameter/editParam.epx?parameterId='+parameterId,
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}	
function updateParameter(parameterId){
	var validator=$("#param").validate({
		rules:{
			name:"required"			
		}
		
	});	
	if(validator.form()){
		
	var name=$("#name").val();	
	var client=$("#clientId").val();
	var activeflg=$("#status").val();
	$.ajax( {
		type: "POST",
        url:'parameter/addParam.epx',
        data:"parameterId="+parameterId+"&parameterName="+name+"&clientId="+client+"&activeFlg="+activeflg,
        success:function(data) {
           $('#datapart').html(data);  
           callDataTable('table1');
        }
     });
	}
}
function saveParameterName(){
	var validator=$("#param").validate({
		rules:{
			name:"required"			
		}
		
	});	
	if(validator.form()){
		
	var name=$("#name").val();	
	var client=$("#clientId").val();	
	$.ajax( {
		type: "POST",
        url:'parameter/addParam.epx',
        data:"parameterName="+name+"&clientId="+client+"&activeFlg=1",
        success:function(data) {
           $('#datapart').html(data);  
           callDataTable('table1');
        }
     });
	}
}
function viewParameters(actionId){
	hideDashboardDiv();
	$.ajax( {
        url:'parameter/viewParam.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
//---------------------------------Tarrif block ------------------------------------------
function viewTariff(){
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'tariff/viewTariff.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
//       // 	alert(JSON.stringify(data).toString())
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}

function addTariff(){
//	//alert("**");
	$.ajax( {
		type: "GET",
        url:'tariff/addTariff.epx',        
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}

	function saveTariff(){
	var tariffName=$("#tariffName").val();
	var applyTax=$("#applyTax").val();
	var slno=$("#slNo").val();
	var tax=$("#tax").val();
	var s1="";
	$(".startValue1").each(function() {
		s1=s1+","+$(this).val();
	});	
	var e1="";
	$(".endValue1").each(function() {
		e1=e1+","+$(this).val();
	});	
	
	var units="";
	$(".units").each(function() {
		units=units+","+$(this).val();
	});
	var rate="";
	$(".rate").each(function() {
		rate=rate+","+$(this).val();
	});
	s1=s1.substring(1);
	e1=e1.substring(1);
	units=units.substring(1);
	rate=rate.substring(1);
	$.ajax( {
		type: "POST",
        url:'tariff/saveTariff.epx',        
       data:"tariffName="+tariffName+"&applyTax="+applyTax+"&slno="+slno+"&tax="+tax+"&startValue="+s1+"&endValue="
       		+e1+"&units="+units+"&rate="+rate,
        success:function(data) {
    //  	alert("get"+data);
           $('#datapart').html(data);
           
        }
     });
}

function addTariffRow(){
	     $("#tariffTable").each(function () {
	         var tds = '<tr>';
	         jQuery.each($('tr:last td', this), function () {	        	
	             tds += '<td>' + $(this).html() + '</td>';	             
	             
	         });
	         
	         tds += '</tr>';
	         if ($('tbody', this).length > 0) {
	             $('tbody', this).append(tds);
	         } else {
	             $(this).append(tds);
	         }
	     });
	     	
} 
function deleteTariffRow(id){
	
	if(id!=0){	
		 $("#tariffTable tr").each(function () {			 
			 if(id==$(this).index())
				 $(this).remove();
			 
		 });
//        	 $('#tariffTable').on('click', "#buttonRemove", function(){        		 
//            	 $(this).closest ('tr').remove ();        			
//    	   
//    	});
	}
		
	     
}  
	$("#insert-more").click(function () {
	    $("#mytable").each(function () {
	        var tds = '<tr>';
	        jQuery.each($('tr:last td', this), function () {
	            tds += '<td>' + $(this).html() + '</td>';
	        });
	        tds += '</tr>';
	        if ($('tbody', this).length > 0) {
	            $('tbody', this).append(tds);
	        } else {
	            $(this).append(tds);
	        }
	    });
	});

function editTariff(slNo){
//	//alert("edit tariff");
	$.ajax( {
        url:'tariff/editTariff.epx?slNo='+slNo,
        success:function(data) {
           $('#datapart').html(data);
           callForDates();
        }
     });
}

function deleteTariff(slNo){
//	//alert("in delete tariff");
	$.ajax( {
        url:'tariff/deleteTariff.epx?slNo='+slNo,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}


function updateTariff(slNo){
	
	var tariffName=$("#tariffName").val();
	var applyTax=$("#applyTax").val();
	var slno=$("#slNo").val();
	var tax=$("#tax").val();
	var s1="";
	$(".startValue1").each(function() {
		s1=s1+","+$(this).val();
	});	
	var e1="";
	$(".endValue1").each(function() {
		e1=e1+","+$(this).val();
	});	
	
	var units="";
	$(".units").each(function() {
		units=units+","+$(this).val();
	});
	var rate="";
	$(".rate").each(function() {
		rate=rate+","+$(this).val();
	});
	s1=s1.substring(1);
	e1=e1.substring(1);
	units=units.substring(1);
	rate=rate.substring(1);
	
	$.ajax( {
		type: "POST",
        url:'tariff/saveTariff.epx',        
        data:"tariffName="+tariffName+"&applyTax="+applyTax+"&slNo="+slNo+"&tax="+tax+"&startValue="+s1+"&endValue="
   		+e1+"&units="+units+"&rate="+rate,
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}
//--------------------------------------------------------
function hideDashboardDiv(){		
	$("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu'); 
	// $("#dashboard").toggle();
	document.getElementById("dashboard").style.display="none";
	document.getElementById("sidemenu_2").style.display="block";
	
}
function hideDashboardDiv_new(){
	//document.getElementById("dashboard").style.display="block";
	  $("#dashboard").toggle();
	  $('#datapart').empty();	
	  
//	alert("in alert");
//	alert($('#dashboard').hasClass('in'))
//	document.getElementById("dashboard").style.display="block";
//	document.getElementById("datapart").style.display="none";
	
//	if($('#dashboard').hasClass('in')){
//		$('#dashboard').collapse('hide');
//		
//	}else{
//		
//		$('#dashboard').collapse('show');
//		
//	}
}
function callDataTable(tableId){
	
	 $('#'+tableId).DataTable( {
		 "order": [],
		 "dom": 'frtBp', 
		 "buttons": [{
      	   extend:    'excel',
    	   title: ' Data',
           titleAttr: 'Excel'
       },                               
       {
           extend: 'csv',
           title: ' Data'
       },
       {
           extend: 'pdf',
           title: ' Data',
           orientation: 'landscape'
       }
	        ]
		 
	 } );
}
function callDataTableWithName(tableId,fileName){
	
	 $('#'+tableId).DataTable( {
		 "order": [],
		// "dom": 'Bfrtip', 
		 "dom": 'frtBp',
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

function callDataTableWithName_5(tableId,fileName){
	
	 $('#'+tableId).DataTable( {
		 "order": [],
		 //"dom": 'Bfrtip',
		 "dom": 'frtBp',
		// "lengthMenu": [5,10, 25, 50, 75, 100],
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
function callDataTableWithName_25(tableId,fileName){
	
	 $('#'+tableId).DataTable( {
		 "order": [],
		 //"dom": 'Bfrtip', 
		 "dom": 'frtBp',
		 "lengthMenu": [10,25, 50, 75, 100],
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

function callDataTableWithName_255(tableId,fileName){
	
	 $('#'+tableId).DataTable( {
		 "order": [],
		 //"dom": 'rtip', 
		 "dom": 'rtBp',
		 "lengthMenu": [25, 50, 75, 100],
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

function callDataTable_scroll(id){
	 $('#'+id).DataTable( {
	    	"sDom": 'lrtipBf',
	        "scrollY":        "200px",
	        "scrollCollapse": true,
	        "paging":         false,
	        "bSort":false,
	        "buttons" : [ {
				extend : 'excel',
				text : '<i class="fa fa-file-excel-o fa-lg"></i>',
				titleAttr : 'Excel'
			}, {
				extend : 'pdf',
				text : '<i class="fa fa-file-pdf-o fa-lg"></i>',
				titleAttr : 'Pdf'
			}, {
				extend : 'print',
				text : '<i class="fa fa-print fa-lg"></i>',
				titleAttr : 'Print'
			}
	 ]
	    } );
}
function callForDates(){
	$('#dateRangePicker')
    .datepicker({
 	   autoclose: true,
        format: 'dd-mm-yyyy hh:mm'
        	});

}
function callForDatesWithId(id){
	$('#'+id).appendDtpicker({
		"closeOnSelected": true,
		"dateFormat": "DD-MM-YYYY hh:mm"
	});

}
function getUserRoles(){
	
	var client=$("#clientId").val();	
	if(client>0){
		//alert("in getting roles")
	$.ajax( {
        url:'getUserRoles.jsp?clientId='+client,
        success:function(data) {        	
           $('#rolesDiv').html(data);
           
        }
     });
	}
}
function getHierarchyData(value,level){
	//document.getElementById("parentId").value=value;
	$.ajax( {
        url:'getHierarchyData.jsp?id='+value,
        success:function(data) {        	
           $('#'+level).html(data);
           
        }
     });
}
function getHierarchy(titleId){
	$.ajax( {
        url:'getHierarchy.jsp?titleId='+titleId,
        success:function(data) {        	
           $('#hierarchy').html(data);
           
        }
     });
}
function getHierarchyByrole(roleid){
	$.ajax( {
        url:'getHierarchyByRole.jsp?roleid='+roleid,
        success:function(data) {        	
           $('#hierarchy').html(data);
           
        }
     });
}
//---------------------------------------- block for complaints --------------------------------------------
function viewComplaints(actionid){
	
	hideDashboardDiv();
	$.ajax( {
        url:'complaints/viewComplaints.epx?actionId='+actionid,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });	
	
}
function addComplaint(){
	
}
function editComplaint(){
	
}
function deleteComplaint(id){
	
}
function saveComplaint(){
	
}
function updateComplaint(id){
	
}
//------------------------------pole info -----------------------------------
function addPole(){
	$.ajax( {
        url:'pole/addPole.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}
function editPole(slno){
	$.ajax( {
        url:'pole/editPole.epx?slno='+slno,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           
        }
     });
}
function viewPoleinfo(actionid){	
	hideDashboardDiv();
	$.ajax( {
        url:'pole/viewPoles.epx?actionId='+actionid,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });	
}

function deletePole(slNo){

	$.ajax( {
        url:'pole/deletePole.epx?slNo='+slNo,
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}

function savePole(){
	if($('#addPole').valid()){
		$.ajax( {
			type : 'POST',
	        data : $('#addPole').serialize(),
	        url:'pole/addPole.epx',
	        beforeSend:function(){
	        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
	        },
	        success:function(data) {
	           $('#datapart').html(data);
	           callDataTable('table1');
	        }
	     });
	}
		
}
function downloadTemplate(){
	location.href="pole/downloadTemplate.epx";
}
function uploadPoleInfo(){
	var formData = new FormData();	
	if($('input[type=file]')[0].files[0]!=undefined){
	 formData.append('uploadFile',$('input[type=file]')[0].files[0]);
	}
	
	$.ajax( {
		type : 'POST',       
        url:'pole/upload.epx',
        data : formData,
        contentType: false,
        processData: false,
        beforeSend:function(){
        	document.getElementById("uploadResponse").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#uploadResponse').html(data);
           
        }
     });
}
//-------------------panel and pole mappings ----------------------------------
function viewPoleMappingPage(actionid){
	hideDashboardDiv();
	$.ajax( {
        url:'poleMapping/viewPoleMappingPage.epx?actionId='+actionid,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTable('table1');
        }
     });
}
function getPolesMapping(panelid){
	hideDashboardDiv();
	$.ajax( {
        url:'poleMapping/viewPoleMapping.epx?panelid='+panelid,
        beforeSend:function(){
        	document.getElementById("polesMappig").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#polesMappig').html(data);
           callDataTable_scroll_dashboard('table1');
           callDataTable_scroll_dashboard('table2');
        }
     });
}
 
function deleteAllPolesMapping(){
	$.ajax( {
        url:'poleMapping/deleteAllPoleMapping.epx?panelid='+$("#panel").val(),
        beforeSend:function(){
        	document.getElementById("polesMappig").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#polesMappig').html(data);
           callDataTable_scroll_dashboard('table1');
           callDataTable_scroll_dashboard('table2');
        }
     });	
}





function updateAllPolesMapping(){
	var poles = new Array();
	$("input:checkbox[name=pole]:checked").each(function() {
		poles.push($(this).val());
	  });
	$.ajax( {
        url:'poleMapping/updateAllPoleMapping.epx?panelid='+$("#panel").val()+"&poles="+poles.join(),
        beforeSend:function(){
        	document.getElementById("polesMappig").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#polesMappig').html(data);
           callDataTable_scroll_dashboard('table1');
           callDataTable_scroll_dashboard('table2');
        }
     });
}