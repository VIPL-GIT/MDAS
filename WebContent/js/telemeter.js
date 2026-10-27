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


function getTeleMetry(actionId) {
	
	clear();
	hideDashboardDiv();
	$.ajax({
		url : 'Telemeter/View.epx?actionId=' + actionId,
		beforeSend : function() {
			document.getElementById("datapart").innerHTML = document
					.getElementById("loader").innerHTML;
		},
		success : function(data) {
			$('#datapart').html(data);
			//callDataTable('table1');
		}
	});
}




function getDigitalData() {

	$.ajax({
		url : 'Telemeter/getDigitalData.epx?',
//		beforeSend : function() {
//			document.getElementById("datapart").innerHTML = document
//					.getElementById("loader").innerHTML;
//		},
		success : function(data) {
			$('#DataPart').html(data);
			//callDataTable('table1');
		}
	});
}


function getAnalogData() {

	$.ajax({
		url : 'Telemeter/getDigitalData.epx?',
//		beforeSend : function() {
//			document.getElementById("datapart").innerHTML = document
//					.getElementById("loader").innerHTML;
//		},
		success : function(data) {
			$('#DataPart').html(data);
			//callDataTable('table1');
		}
	});
}



function SaveDigitalPoints(){
	
//	var e = document.getElementById("country");
//	var result = e.options[e.selectedIndex].value;
//	alert("result --> "+result);
	
	var elements = document.getElementsByClassName("test");
	
	
	var date = document.getElementById("date").value;
	var time = document.getElementById("time").value;
	alert(date+" --- "+time);
	var keyvalue="";
	var key="";
	var allkeys="";
	var allvalues="";
	for(var i=0; i<elements.length; i++) {
		
	  key= elements[i].id;
	  allkeys=key+","+allkeys;
	   
	   
	  var value= document.getElementById(key).value;
	  if(value.length>0){
		  allvalues="'"+value+"'"+","+allvalues;
	  }else{
		  allvalues=""+","+allvalues;
	  }
	}
	
alert(allvalues+"  kv  "+allkeys);
	   
		$.ajax({
			url : 'Telemeter/InsertDIS.epx?allkeys='+allkeys+'&allvalues='+allvalues+'&date='+date+'&time='+time,

			success : function(data) {
				$('#DataPart').html(data);
			}
		});
	
}

