function viewFrtuMgmt(actionId){
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'frtu/home.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);           
        }
     });
}
/*------------------------------------From base one-----------------------------------------------------------------*/

var old_no = '0';
var srlno = '-1';
var field_name = 'SLNO';
var sort_order = 'asc';
var page_no = '1';
records_per_page = '5';
var temp_tab_no = 1;
var insertStatus;
var pres_tab_obj1 = "";
var device_tab_no = 9;
var device_tab_no = 0;
var tree_root_srlno = 0;
var ioa_data_type_main = 1;
var flip_type = 0;

var select_tab_val = 1;
var sel_td_val_rtu_data = 1;

var actionStatus = "Success";
function tree_fun(no) 
{
    //document.getElementById("hierarchy_slno").value=no;
    $("#panel" + no).slideToggle("slow");
    document.getElementById("flip" + no).style.color = "red";
    var obj = document.getElementById("flip_li" + no);
    var class_name = obj.className;
    if(class_name=="list_style_one")
        obj.className = "list_style_two";
    else
        obj.className = "list_style_one";
    
    var old_nm = "flip" + old_no;
    if(flip_type == 1)
        old_nm = "flipd" + old_no;
    
    if ((parseInt(old_no) == parseInt(no)) && flip_type==0) {
    }
    else {
        old_no = no;
        document.getElementById(old_nm).style.color = "black";
    }
    flip_type = 0;
}
var is_root_click = 0;
function tree_fun2(no) {
    is_root_click = 1;
    document.getElementById("flip" + no).style.color = "red";
    var old_nm = "flip" + old_no;
    if(flip_type == 1)
        old_nm = "flipd" + old_no;
    
    srlno = no;
    if ((parseInt(old_no) == parseInt(no)) && flip_type==0) {
    }
    else {
        old_no = no;
        document.getElementById(old_nm).style.color = "black";
    }
    flip_type = 0;
    page_no = 1;
    tree_root_srlno = no;//sreenivas
if(0!=parseInt(no)){
//if(temp_tab_no==1){
        //getData2(no);//sreenivas
        tab_click(1, document.getElementById("tab_obj1"));
//    }else{
//        document.getElementById("display_data2").innerHTML = "";
//        document.getElementById("display_data3").innerHTML = "This is ROOT Node";
//    }
}else{
    $("#view1").html("<center><b>This Is ROOT Node</b></center>");
}
}
function tree_fun3(no) 
{
    //alert("tree_fun3");
    //alert("no==>"+no+" old_no==>"+old_no);
    is_root_click = 0;
    if (document.getElementById("hierarchy_slno") != null) {
        document.getElementById("hierarchy_slno").value = no;
    }
    if (document.getElementById("hierarchy_slno1") != null) {
        document.getElementById("hierarchy_slno1").value = no;
    }
    if (document.getElementById("srlno") != null) {
        document.getElementById("srlno").value = no;
    }
   
    document.getElementById("flipd" + no).style.color = "red";
    var old_nm = "flip" + old_no;
    if(flip_type == 1)
        old_nm = "flipd" + old_no;
    
    srlno = no;
    //alert("old_nm==>"+old_nm);
    if ((parseInt(old_no) == parseInt(no)) && flip_type==1) {
    }
    else {
        old_no = no;
        document.getElementById(old_nm).style.color = "black";
    }
    flip_type = 1;
    page_no = 1;

    if (parseInt(temp_tab_no) == 1) {
        //getEditRTUDataPerID(srlno, temp_tab_no);
        var main_rtu_config = document.getElementById("main_rtu_config");
        if(main_rtu_config==null){
            getOptionForRTUCreation(1);
        }else{
           document.getElementById("td" + sel_td_val_rtu_data).className = "tab1";
           getRTU_Main_Config(select_tab_val); 
        }
    } else if (parseInt(temp_tab_no) == 2 &&  parseInt(device_tab_no) == 9) {
          getDeviceDataList();
        //getNewDeviceForm();
    } else if (parseInt(temp_tab_no) == 2 && parseInt(device_tab_no) == 10) {       
            getAnalogData(no);        
    }
    else if (parseInt(temp_tab_no) == 3) 
    {
        getDigitalInputDetails();
    }
    else if (parseInt(temp_tab_no) == 4) {
        getDigitalOutputDetails();
    }
    else if (parseInt(temp_tab_no) == 5){
        getPulseCounterTab();
        //getPulseCounterDetails();
    }
    else if (parseInt(temp_tab_no) == 6)
    {
        getServersDetails(0);
    }
    else if (parseInt(temp_tab_no) == 7)
    {
        getServersDetails(1);
    }
    else if (parseInt(temp_tab_no) == 11) 
    {
        getAnalogOutputDetails();
    }else if(parseInt(temp_tab_no) == 8){
        getMastersPriorities();
    }
}

function tab_click(tab_no, tab_obj) {
    if(flip_type==0 && tab_no!=1){
        jAlert('info', 'Select RTU / FRTU Device.', 'RTUM');
        return false;
    }
    insertStatus = document.getElementById("insertStatus").value;
    if (insertStatus == "success" && pres_tab_obj == null)
        pres_tab_obj = document.getElementById("tab_obj1");

    if (pres_tab_obj != null)
    {
        if(pres_tab_obj.className=="selected last")
            pres_tab_obj.className = "last";
        else
            pres_tab_obj.className = "";
    }

    pres_tab_obj = tab_obj;
    temp_tab_no = tab_no;
    if(tab_obj.className=="last")
        tab_obj.className = "selected last";
    else
        tab_obj.className = "selected";

    if (tab_no == 2){
        getNewDeviceForm();
        getDeviceDataList();
    }else if (tab_no == 1)    {
        if(flip_type==1){
            //getEditRTUDataPerID(srlno, temp_tab_no);
            //getRTU_Main_Config(select_tab_val);
            getOptionForRTUCreation(1);
            sel_td_val_rtu_data = 1;
        }else
            getData2(srlno);
    }
    else if(tab_no==3){
        getDigitalInputDetails();
    }
    else if(tab_no==4) {
        getDigitalOutputDetails();
    }
    else if (parseInt(tab_no) == 5){
        getPulseCounterTab();
        //getPulseCounterDetails();
    }
    else if (parseInt(tab_no) == 6)
    {
        getServersDetails(0);
    }
    else if (parseInt(tab_no) == 7)
    {
        getServersDetails(1);
    }
    else if(tab_no==11)
    {
        getAnalogOutputDetails();
    }else if(tab_no==8){
        getMastersPriorities();
    }
    else
    {
        document.getElementById("view1").innerHTML = "";
    }
}
var temp_tab_obj1 ;
function tab_click1(tab_no2, tab_obj2, is_click){
    if (tab_no2 == 10) {
        pres_tab_obj2 = document.getElementById("tab_obj9");
    }    
    if(tab_obj2==undefined ||tab_obj2==null){
        tab_obj2 = document.getElementById("tab_obj"+tab_no2);
        pres_tab_obj2 = tab_obj2;
    }    
    if(pres_tab_obj2 != null){
        pres_tab_obj2.className = "";
    }
    
    device_tab_no = tab_no2;
    tab_obj2.className = "selected";    
    pres_tab_obj2 = tab_obj2;
    
    if (device_tab_no == 10) {
       
        getAnalogData(srlno);
    }
    else if (device_tab_no == 9) {
        getDeviceDataList();
    }else if (15==device_tab_no) {
        getDigitalDeviceData(srlno,"DI");
    }else if (16==device_tab_no) {
        getDigital_Device_Map(srlno,"DI");
    }else if (17==device_tab_no) {
        getDevice_Data_Home(srlno,"DI")
    }else if (18==device_tab_no) {
        getDigitalDeviceData(srlno,"DO");
    }else if (19==device_tab_no) {
         getDigital_Device_Map(srlno,"DO");
    }else if (20==device_tab_no) {
         //getDigital_Device_Map(srlno,"DO");
         getDevice_Data_Home(srlno,"DO")
    }else if(21==device_tab_no){
        getPulseCounterDetails("all");
    }else if(22==device_tab_no){
        getPulseCounterDetails("checked_only");
    }
}
function getData2(hierarchy_srlno){
	  var dataString ='tree_root_srlno='+ hierarchy_srlno.trim();
	  tree_root_srlno = hierarchy_srlno.trim();
	  $.ajax({
	        type: "post",
	        url: "../.././get_RTU_List.do",
	        data: dataString,
	        cache: false,
	        success: function(html)
	        {
	           $("#view1").html(html);
	        }
	    });
	  $.ajax( {
	        url:'frtu/get_RTU_List.epx',
	        data: dataString,
	        cache: false,
	        beforeSend:function(){
	        	document.getElementById("view1").innerHTML=document.getElementById("loader").innerHTML;
	        },
	        success:function(data) {
	           $('#view1').html(data);           
	        }
	     }); 
	    return false;  
	}
function getMastersPriorities(){
    var dataString = "device_id="+srlno;
    
    $.ajax( {
        url:'frtu/master_priority.epx?device_id='+srlno+ "&mode=view",
        beforeSend:function(){
        	document.getElementById("view1").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#view1').html(data);           
        }
     });
}
function saveMasterPriority(){   
    var dataString = "device_id=" + srlno + "&mode=save";
    var table = document.getElementById("data_tbody");
    var row_cnt = table.rows.length;
    for (var loop = 1; loop < row_cnt; loop++){
            var selectedVal = $("#priority"+loop).val();
            for (var loop2 = loop + 1; loop2 <= row_cnt; loop2++){
                var presentVal = $("#priority"+loop2).val();
                   if (selectedVal === presentVal) {
                        var msg = 'Duplicate Priorty Not Accepted';
                        showAlert('error', msg, 'RTUM');
                        
                        return false;
                    }
            }
    }
    for (var loop = 1; loop <=row_cnt; loop++){
        var time_period = $("#time_period"+loop).val();
        
        var msg = "";
        if(""==time_period){
           msg = "Time Period Empty Not Allowed";
        }else if(parseInt(time_period)<1 || parseInt(time_period)>65535){
           msg = "Time Period Value Between 1 to 65535";
        }
      
        if(msg!=""){            
        	showAlert('error',msg,'RTUM');
            return false;
           
        }
    }
    for (var i1 = 1; i1 <= row_cnt; i1++) {
        dataString = dataString + "&server_srlno_arr=" + $("#server_srlno"+i1).val();
        dataString = dataString + "&priority_arr=" + $("#priority"+i1).val();
        dataString = dataString + "&time_period_arr=" + $("#time_period"+i1).val();
    }    
    document.getElementById("view1").innerHTML = document.getElementById("processing_div").innerHTML;
    
    $.ajax( {
        url:'frtu/master_priority.epx',
        data:dataString,
        beforeSend:function(){
        	document.getElementById("view1").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#view1').html(data);           
        }
     });
    return false; 
}
function showAlert(status,msg,tt){
	swal({
  	  title: tt,
  	  text: msg,
  	  icon: status,
  	});
}

function getOptionForRTUCreation(option_val){
	   var mode = "upload_form";
	   if(parseInt(option_val)==1){
	       mode = "config_menu";
	   }else if(parseInt(option_val)==3){
	       mode = "upload_form_rtu";
	   }else if(parseInt(option_val)==2){
	       mode = "upload_form_new";
	   }
	   var dataString = "mode="+mode+"&hierarchy_srlno="+tree_root_srlno;
	   var url_str = "../.././common_rtu_action.do";
	   $.ajax({
	        type: "post",
	        url: url_str,
	        data: dataString,
	        cache: false,
	        success: function(html){
	            $("#view1").html(html);
	            getRTU_Main_Config(select_tab_val);
	        }
	    });
	    return false; 
	}
function getRTU_Main_Config(tab_val) {    
    document.getElementById("td" + tab_val).className = "selected_td";
    for(var loop=1;loop<=3;loop++){
        if (parseInt(tab_val) != parseInt(loop)){
            document.getElementById("td" + loop).className = "tab1";
        }
    }
    sel_td_val_rtu_data = tab_val;
    select_tab_val = tab_val;
    var mode = "manual_form";    
    if (parseInt(tab_val) == 1) {
        if (is_root_click == 1) {
            mode = "manual_form";
            var dataString = "mode=" + mode + "&hierarchy_srlno=" + tree_root_srlno;
            var url_str = "../.././common_rtu_action.do";
            $.ajax({
                type: "post",
                url: url_str,
                data: dataString,
                cache: false,
                success: function(html) {
                    $("#main_rtu_config").html(html);
                }
            });
            return false;
        } else {
            getEditRTUDataPerID(srlno, temp_tab_no);
        }
    } else if (parseInt(tab_val) == 2) {
        getComportSettings();
    } else if (parseInt(tab_val) == 3) {
        getOtherSettings();
    }
}
function getComportSettings() {
    var mode = "get_comport_data"
    var dataString = "mode=" + mode + "&device_id=" + srlno;
    var url_str = "../.././add_RTU_Action.do";
    //alert("dataString==>" + dataString);
    $.ajax({
        type: "post",
        url: url_str,
        data: dataString,
        cache: false,
        success: function(html) {
            $("#main_rtu_config").html(html);
            var srlno_tmp = $("#srlno").val();
            //sreenivas
            //alert("srlno_tmp==>"+srlno_tmp);
            if(srlno_tmp!==null || 'null'!==srlno_tmp){
                
                srlno = srlno_tmp;
                old_no = srlno;
                flip_type = 1;
                //alert("srlno12==>"+srlno);
            }
        }
    });
    return false;
}
var is_other_settings = "";
function getOtherSettings(){
    is_other_settings = "other_settings";
    getEditRTUDataPerID(srlno, temp_tab_no);
    is_other_settings = "";
}
function getEditRTUDataPerID(hierarchy_no, temp_tab_no) {
    //var url_str = "../.././editAndUpdate_RTU_Data_Action.do";
    var mode="getperID"
    if(is_other_settings=="other_settings"){
        mode="other_settings"
        is_other_settings = "";
    }
    var url_str =  "../.././add_RTU_Action.do";
    var dataString = 'srlno=' + hierarchy_no + '&temp_tab_no=' + temp_tab_no + '&mode='+mode+'&insertStatus=' + insertStatus;
    document.getElementById("main_rtu_config").innerHTML = document.getElementById("processing_div").innerHTML;
    //alert("dataString==>"+dataString);
    $.ajax({
        type: "post",
        url: url_str,
        data: dataString,
        cache: false,
        success: function(html)
        {
            $("#main_rtu_config").html(html);
        }
    });
    return false;
}
