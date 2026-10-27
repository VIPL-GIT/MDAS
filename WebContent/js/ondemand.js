/**
 * 
 */

function GetXmlHttpObject() {
	var xmlHttp = null;
	if (window.XMLHttpRequest) {
		xmlHttp = new window.XMLHttpRequest;
	} else {
		try {
			xmlHttp = new ActiveXObject("MSXML2.XMLHTTP.3.0");
		} catch (ex) {
			return null;
		}
	}
	return xmlHttp;
}


function getOnDemand(actionId) {
	clear();
	hideDashboardDiv();
	
	$.ajax( {
        url:'OnDemand/OnDemandView.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
          // callDataTable('table1');
          
        }
     });
}

function callDataTableForOD(tableID){
	var table=$('#'+tableID).DataTable( {
		 "dom": '<"pull-left"f><"pull-right"l>tp',
		"scrollY":        "200px",
	    "scrollCollapse": true,
	    "paging":         false,
	    columnDefs: [ {
	        orderable: false,
	        className: 'select-checkbox',
	        targets:   0
	    } ],
	    select: {
	        style:    'os',
	        selector: 'td:first-child'
	    },
	    order: [[ 1, 'asc' ]]
	} );
	
	table.on( 'select', function ( e, dt, type, indexes ) { 
		
        var data = table.rows( indexes ).data().toArray();  
        var meter=data[0].toString().replace(/,/g, "");
        $("#meterToProcess").append("<tr id='"+meter+"' ><td ondblclick='removeMeter(this.innerHTML)'>"+meter+"</td><td id='"+meter+"_status' align='center'></td></tr>");
          
} );
	
	return table;
}

function removeMeter(meter){                
    $("#"+meter).remove();
}

function getHierarchyForOnDemand(titleId){
	// alert("titleId --> "+titleId);
		//$("#feederSel").empty();
		//$("#childsSel").empty();
		$.ajax( {
	        url:'getHierarchyForOnDemand.jsp?userHid='+titleId,
	        success:function(data) {        	
	           $('#hierarchy').html(data);
	           
	        }
	     });
	}



function getHierarchyData_ondemand(value,level){
	var install_point=$("#install_type").val();
	document.getElementById("levelID").value=value;
	document.getElementById("level").value=level;
	
	if(install_point==8 && level=="level_3"){
		getMetersforOnDemand(value,'meterSel');
		return false;
	}else{
	
	//alert(document.getElementById("levelID").value+" "+document.getElementById("level").value);
	$.ajax( {
        url:'getHierarchyData.jsp?id='+value,
        success:function(data) { 
           $('#'+level).html(data);           
           //$('#'+level).chosen();
        }
     });
	}
}



function getMetersforOnDemand(value,divid){
 	//alert(value+" getmETERS "+divid);
 	var levelId=document.getElementById("levelID").value=value;
 	var level=document.getElementById("level").value;
 	var install_type=$("#install_type").val();
 	var search_by=$("#search_by").val();
 	
 	$.ajax( {
        // url:'getMetersByssID.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level,
         url:'getMetersForOnDemand.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level+"&search_by="+search_by,
         success:function(data) {  
         	 $('#meterSel').empty();
            $('#meterSel').html(data);
            callDataTableForOD("childID");
            
         }
      });
 }


function getODfeatures(activity){
	if(activity=="1" || activity=="2"){
		$("#paramsDiv").hide();
		$("#paramValuesDiv").hide();
	}else{
		$("#paramsDiv").show();
		$("#paramValuesDiv").show();
	}
	
	$.ajax( {        
         url:'ondemand/getFeaturesByActivity.jsp?activity='+activity,
         success:function(data) {  
         	 $('#features').empty();
            $('#features').html(data);
         }
      });
}
function getODParams(feature){
	var activity=$("#activity").val();
	if(activity=="1" || activity=="2"){
		return 0;
	}else{
		$.ajax( {        
	         url:'ondemand/getWriteParameters.jsp?activity='+activity+'&feature='+feature,
	         success:function(data) {  
	         	 $('#params').empty();
	            $('#params').html(data);
	         }
	      });
	}
}
function getODParamValues(param){
	$.ajax( {        
        url:'ondemand/getODParamValues.jsp?param='+param,
        success:function(data) {  
        	 $('#paramValuesDiv').empty();
           $('#paramValuesDiv').html(data);
        }
     });
}

function initiateOD(){
	var url="ondemand/initiateOD.jsp";
	var activity=$("#activity").val();
	var feature=$("#features").val();
	url=url+"?activity="+activity+"&feature="+feature;
	
	if(activity=="0"){	   
	   var params=$("#params").val();
	   var paramValues=$("#paramValues").val();
	   url=url+"&params="+params+"&paramValues="+paramValues;
	}
	
	$('#odMeters tbody tr').each(function (i, row) {
        var meter=$(this).find("td:first").html();
        $.ajax({
        url: encodeURI(url+"&meter="+meter),
                  type: 'post',
                  data: '',
                  beforeSend: function (xhr) {
                      $('#'+meter+"_status").html("<img src='images/loader.gif' height='15px'/>");
                  },
                  success: function(data) {                        
                      $('#'+meter+"_status").html(data);
                      
                  },
                  error: function(e) {
                  }
              });
     });
	
}


function getConfigOptions(value) {
          
if (value == "PHIM") {
document.getElementById('phimCB').style.display = "";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none";
document.getElementById('instCB').style.display = "none";
document.getElementById('normCB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
}else if (value == "DPCD" || value == "DPLS") {
document.getElementById('lsDays').style.display = "";
document.getElementById('phimCB').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none";
document.getElementById('instCB').style.display = "none";    
document.getElementById('normCB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
}else if (value == "MN") {
document.getElementById('mnCB').style.display = "";
document.getElementById('lsDays').style.display = "none";
document.getElementById('phimCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none";
document.getElementById('instCB').style.display = "none"; 
document.getElementById('normCB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
} else if (value == "SL") {
document.getElementById('slnoCB').style.display = "";
document.getElementById('lsDays').style.display = "none";
document.getElementById('phimCB').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none";
document.getElementById('instCB').style.display = "none";  
document.getElementById('normCB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
} else if (value == "APNPortsChangeD") {
document.getElementById('apnCB').style.display = "";
document.getElementById('phimCB').style.display = "none";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none";
document.getElementById('instCB').style.display = "none";   
document.getElementById('normCB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
}else if (value == "FLGA") {
document.getElementById('apnSCB').style.display = "";
document.getElementById('phimCB').style.display = "none";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none";
document.getElementById('instCB').style.display = "none";   
document.getElementById('normCB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
}else if (value == "FLGT") {
document.getElementById('flgtCB').style.display = "";
document.getElementById('phimCB').style.display = "none";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none";
document.getElementById('instCB').style.display = "none";  
document.getElementById('normCB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
}else if (value == "FLGI") {
document.getElementById('flgiCB').style.display = "";
document.getElementById('phimCB').style.display = "none";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";
document.getElementById('instCB').style.display = "none"; 
document.getElementById('normCB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
}else if (value == "INST") {
document.getElementById('instCB').style.display = "";
document.getElementById('flgiCB').style.display = "none"
document.getElementById('phimCB').style.display = "none";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";
document.getElementById('normCB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
}else if (value == "VOLTSET") {
document.getElementById('volCB').style.display = "";
document.getElementById('instCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none"
document.getElementById('phimCB').style.display = "none";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";   
document.getElementById('apnSCB').style.display = "none";
document.getElementById('pfCB').style.display = "none";
document.getElementById('normCB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
}else if (value == "PFSET") {
document.getElementById('pfCB').style.display = "";
document.getElementById('volCB').style.display = "none";
document.getElementById('instCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none"
document.getElementById('phimCB').style.display = "none";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";   
document.getElementById('apnSCB').style.display = "none";
document.getElementById('normCB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
}else if (value == "TEMPSET") {
document.getElementById('tempCB').style.display = "";
document.getElementById('volCB').style.display = "none";
document.getElementById('pfCB').style.display = "none";
document.getElementById('instCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none"
document.getElementById('phimCB').style.display = "none";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";   
document.getElementById('apnSCB').style.display = "none";
document.getElementById('normCB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
}else if (value == "NORMTEMPSET") {
document.getElementById('normCB').style.display = "";
document.getElementById('tempCB').style.display = "none";
document.getElementById('volCB').style.display = "none";
document.getElementById('pfCB').style.display = "none";
document.getElementById('instCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none"
document.getElementById('phimCB').style.display = "none";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";   
document.getElementById('apnSCB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
}else if (value == "DO1") {
document.getElementById('do1CB').style.display = "";
document.getElementById('normCB').style.display = "none";
document.getElementById('tempCB').style.display = "none";
document.getElementById('volCB').style.display = "none";
document.getElementById('pfCB').style.display = "none";
document.getElementById('instCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none"
document.getElementById('phimCB').style.display = "none";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";   
document.getElementById('apnSCB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
}else if (value == "DO2") {
document.getElementById('do2CB').style.display = "";
document.getElementById('do1CB').style.display = "none";
document.getElementById('normCB').style.display = "none";
document.getElementById('tempCB').style.display = "none";
document.getElementById('volCB').style.display = "none";
document.getElementById('pfCB').style.display = "none";
document.getElementById('instCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none"
document.getElementById('phimCB').style.display = "none";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";   
document.getElementById('apnSCB').style.display = "none";
}
else if (value == "CNCT") {
document.getElementById('cnctCB').style.display = "block";
document.getElementById('do2CB').style.display = "none";
document.getElementById('do1CB').style.display = "none";
document.getElementById('normCB').style.display = "none";
document.getElementById('tempCB').style.display = "none";
document.getElementById('volCB').style.display = "none";
document.getElementById('pfCB').style.display = "none";
document.getElementById('instCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none"
document.getElementById('phimCB').style.display = "none";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";   
document.getElementById('apnSCB').style.display = "none";
}
else{
document.getElementById('do1CB').style.display = "none";
document.getElementById('normCB').style.display = "none";
document.getElementById('volCB').style.display = "none";
document.getElementById('pfCB').style.display = "none";
document.getElementById('instCB').style.display = "none";
document.getElementById('flgiCB').style.display = "none"
document.getElementById('phimCB').style.display = "none";
document.getElementById('lsDays').style.display = "none";
document.getElementById('mnCB').style.display = "none";
document.getElementById('slnoCB').style.display = "none";
document.getElementById('apnCB').style.display = "none";
document.getElementById('flgtCB').style.display = "none";   
document.getElementById('apnSCB').style.display = "none";
document.getElementById('do2CB').style.display = "none";
}
}




function sendSMSToGateway() {
   
       
       xmlHttp = GetXmlHttpObject();
       if (xmlHttp == null)
       {
           alert("Browser does not support HTTP Request");
           return;
       }
       var requiredParams="NA";
       var commandType = document.getElementById("CTM").value;
   
//   metersSel = document.getElementsByName("meters");
//   var checkedMeters = new Array();
       var Selected = document.getElementById('childID').selectedIndex;
       var SelectedOption = document.getElementById('childID').options[Selected].value;
     //  alert("Meter Selected = "+SelectedOption.trim());
       if (SelectedOption.length == 0){
           alert("Please select atleast 1 meter ");
           return 0;
       }
       document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
     //  alert('commandType = '+commandType);
       if (commandType == "EACB") {
          // requiredParams = document.getElementById("instFreq").value;
          requiredParams = "NA";
       }
       if (commandType == "DACB") {
          // requiredParams = document.getElementById("instFreq").value;
          requiredParams = "NA";
       }
       if (commandType == "PHIM") {
          // requiredParams = document.getElementById("instFreq").value;//phimBal
          requiredParams = document.getElementById("phimBal").value;
//          requiredParams = "NA";
       }
       if (commandType == "VOLTSET") {
          // requiredParams = document.getElementById("instFreq").value;//phimBal
          requiredParams = document.getElementById("volRef").value;
//          requiredParams = "NA";
       }
       if (commandType == "PFSET") {
          // requiredParams = document.getElementById("instFreq").value;//phimBal
          requiredParams = document.getElementById("pfRef").value;
//          requiredParams = "NA";
       }
       if (commandType == "TEMPSET") {
          // requiredParams = document.getElementById("instFreq").value;//phimBal
          requiredParams = document.getElementById("tempRef").value;
//          requiredParams = "NA";
       }
       if (commandType == "NORMTEMPSET") {
          // requiredParams = document.getElementById("instFreq").value;//phimBal
          requiredParams = document.getElementById("normRef").value;
//          requiredParams = "NA";
       }        
       if (commandType == "DO1") {
          // requiredParams = document.getElementById("instFreq").value;//phimBal
          requiredParams = document.getElementById("do_1").value;
//          requiredParams = "NA";
       }
       if (commandType == "DO2") {
          // requiredParams = document.getElementById("instFreq").value;//phimBal
          requiredParams = document.getElementById("do_2").value;
//          requiredParams = "NA";
       }
       if (commandType == "CNCT") {
          var phase=document.getElementById("phase").value;
          var action=document.getElementById("action").value;
          if(phase=="-1" || action=="-1"){
              alert("phase or action missing...");
              return 0;
          }
          if(action=="0") commandType="DS"+commandType;
          
          commandType=commandType+phase+action;
          
          requiredParams = "NA";
       }
       if (commandType == "RST" || commandType == "FNS" || commandType == "FN1") {
           requiredParams = "NA";
       }
       if (commandType == "INST") {
           requiredParams = document.getElementById("instFreq").value;
       }
       if (commandType == "SL") {
           requiredParams = document.getElementById("SLNoValue").value;
       }
       if (commandType == "MN") {
           requiredParams = document.getElementById("MNValue").value;
       }
       if (commandType == "FLGA") {
           requiredParams = document.getElementById("apnNameS").value + "," + document.getElementById("serverIPS").value + "," + document.getElementById("apnPass").value + "," + document.getElementById("dataPort").value;
       }
       if (commandType == "FLGI") {
           requiredParams = document.getElementById("instStatus").value + "ED" + document.getElementById("discomName").value + document.getElementById("loadDays").value;
       }
       if (commandType == "DPCD" || commandType == "DPLS") {
//       requiredParams= document.getElementById("lsDates").value;
           requiredParams = "NA";
       }
       if (commandType == "DPTD" || commandType == "DPIP") {
           requiredParams = "NA";
       }
       if (commandType == "TAM1") {
           requiredParams = "NA";
       }
       if (commandType == "TAM0") {
           requiredParams = "NA";
       }
       if (commandType == "POTA" || commandType == "CONFIG" || commandType == "RDCFG") {
           requiredParams = "NA";
       }
       if (commandType == "FLGT") {
           requiredParams = document.getElementById("readTime").value + document.getElementById("transmitTime").value;
       }
       var url = "sendSMSToModem.jsp?CTM=" + escape(commandType) + "&meters=" + escape(SelectedOption.trim()) + "&requiredParams=" + escape(requiredParams);
      // alert(url);
       xmlHttp.onreadystatechange = sendSMSToModemAction;
       xmlHttp.open("GET", url, true);
       xmlHttp.send(null);
   }
   
   
   function getOnDemandLogs() {
	   $('#send').removeClass('current');
	    $('#report').addClass('current');
       document.getElementById('cont-1-1').style.display = "none";
       document.getElementById("DataPart").innerHTML = "";
       document.getElementById('cont-2-1').style.display = "block";
       
       xmlHttp = GetXmlHttpObject();
       if (xmlHttp == null)
       {
           alert("Browser does not support HTTP Request");
           return;
       }
       var url = "ondemandLogsScreen.jsp";
       xmlHttp.onreadystatechange = getOndemandPageAction;
       xmlHttp.open("GET", url, true);
       xmlHttp.send(null);
   }
   
   function getOndemandPageAction() {
       var showData = xmlHttp.responseText;
//       document.getElementById("load_div2").innerHTML = "";
       document.getElementById("cont-2-1").style.visibility = "visible";
       document.getElementById("cont-2-1").innerHTML = showData;
       loadDate(10);
   }
   
   
   function demandSending(){
		$('#report').removeClass('current');
	    $('#send').addClass('current');
		 document.getElementById('cont-2-1').style.display = "none";
		 document.getElementById("DataPart").innerHTML = "";
		 document.getElementById('cont-1-1').style.display = "block";
		
	}

   
   
   
   function getOnDemandReport(){
//     alert("in getOnDemandReport js")
	  
    document.getElementById('cont-1-1').style.display = "none";
    document.getElementById('cont-2-1').style.display = "block";
   
     xmlHttp = GetXmlHttpObject();
     if (xmlHttp == null)
     {
         alert("Browser does not support HTTP Request");
         return;
     }
     var reportDate = document.getElementById("s1Date").value;
//     var reportDate = "13/07/2017";
     if (reportDate == "") {
         alert("please select date");
         return 0;
     }
//     var Selected = document.getElementById('childID').selectedIndex;
//     var SelectedOption = document.getElementById('childID').options[Selected].value;
//     if (SelectedOption.length == 0)
//     {
//         alert("Please select atleast one meter");
//         return 0;
//     }
     document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
     var url = "OndemandLogReports.jsp?reportDate=" + escape(reportDate);
     //+ "&meters=" + escape(SelectedOption);
    // alert(url);
     xmlHttp.onreadystatechange = sendSMSToModemAction;
     xmlHttp.open("GET", url, false);
     xmlHttp.send(null);
	   
	   
   }
   
   function sendSMSToModemAction() {
       if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
       {
           var showdata = xmlHttp.responseText;
           document.getElementById("DataPart").innerHTML = showdata;
       }
   }
