/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

function sendDOCommand(status) {

	if (parseInt(type_id) == 3) {
		if (parseInt(pres_status) == 1) {
			pres_status = 0;
		} else if (parseInt(pres_status) == 2) {
			pres_status = 1;
		}
	}
	change_status = status;
	//alert(change_status+":"+pres_status);
	/* if(parseInt(change_status)==parseInt(pres_status)){
	    alert("present status is same");
	    $('#contextmenu').hide();
	 }else{*/
	xmlHttp = GetXmlHttpObject();
	if (xmlHttp == null) {
		alert("Browser does not support HTTP Request");
		return;
	}
	//alert("di_srlno :"+di_srlno);
	var status = true;
	if (parseInt(rtu_id) == 48) {
		if (parseInt(di_srlno) == 745) {
			var voltage_hidden2 = $("#voltage_hidden2").val();
			var voltage_hidden4 = $("#voltage_hidden4").val();
			if (parseInt(voltage_hidden2) == parseInt(voltage_hidden4)) {
				status = false;
				if (parseInt(voltage_hidden2) == 0) {
					alert("PT09 and PT11 both are Dead,So Not Possible Send DO Command");
				} else {
					alert("PT09 and PT11 both are Live,So Not Possible Send DO Command");
				}
			}
		}
		if (parseInt(di_srlno) == 746) {
			var voltage_hidden1 = $("#voltage_hidden1").val();
			var voltage_hidden3 = $("#voltage_hidden3").val();
			if (parseInt(voltage_hidden1) == parseInt(voltage_hidden3)) {
				status = false;
				if (parseInt(voltage_hidden1) == 0) {
					alert("PT08 and PT10 both are Dead,So Not Possible Send DO Command");
				} else {
					alert("PT08 and PT10 both are Live,So Not Possible Send DO Command");
				}
			}
		}
	}
	if (status) {
		var url = "SendDOCommand1.jsp";
		url = url + "?rtu_id=" + rtu_id;
		url = url + "&di_srlno=" + di_srlno + "&type_id=" + type_id
				+ "&pres_status=" + change_status;
		xmlHttp.onreadystatechange = displaysendDOCommand;
		// alert(3);
		xmlHttp.open("GET", url, true);
		xmlHttp.send(null);
		//}  
	}
}
function displaysendDOCommand() {
	if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
		var showdata = xmlHttp.responseText;
		//alert(showdata);
		if (showdata.trim() == "true") {
			alert("Command send successfully");
		} else {
			alert("Command sending  Failed");
		}
		//document.getElementById("view_part").innerHTML = showdata;
		$('#contextmenu').hide();
	}
}
function getFeederLiveDet(varFeederID) {
alert("feeder live data check -->  "+varFeederID);
	view_FeederInstDet(varFeederID);
}

function view_FeederInstDet(varFeeder_id) {
	
	var x1 = 300;
	var y1 = 300;

	$("#dialog").width(eval(x1));
	$("#dialog").height(eval(y1 + 30));
	$("#body_part").width(eval(x1));
	$("#body_part").height(eval(y1));
	$('#model_a_link').click();
	$("#close_part").click(function() {
		clk_flg = 0;
	});
	document.getElementById("body_part").innerHTML = document
			.getElementById("loading_div").innerHTML;
	xmlHttp1 = GetXmlHttpObject();
	if (xmlHttp1 == null) {
		alert("Browser does not support HTTP Request");
		return;
	}
	var url = "viewFeederInstDet.jsp";
	url = url + "?pFeeder_id=" + varFeeder_id;
	alert(url);
	xmlHttp1.onreadystatechange = displayFedderDet;
	xmlHttp1.open("GET", url, true);
	xmlHttp1.send(null);
}
function displayFedderDet() {
	if (xmlHttp1.readyState == 4 || xmlHttp1.readyState == "complete") {
		var showdata = xmlHttp1.responseText;
		document.getElementById("body_part").innerHTML = showdata;
		var x1 = 300;
		var y1 = 300;

	}
}

//   -----------    sld javscript starts   -----------

function getSld1(actionId) {

	$.ajax({
		url : 'sld/mapping1.epx?actionId=' + actionId,
		beforeSend : function() {
			//document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
		},
		success : function(data) {

			$('#viewTable1').html(data);
			// callDataTable('table1');
		}
	});
}

function getView() {
	clear();
	hideDashboardDiv();
	$.ajax({
		url : 'sld/sldview.epx',
		beforeSend : function() {
			document.getElementById("datapart").innerHTML = document
					.getElementById("loader").innerHTML;
		},
		success : function(data) {
			$('#datapart').html(data);
			// callDataTable('table1');
		}
	});
}


var newwindow;
var ss_id = "";
function view_wind() {
	//alert("view sld");
	name = 'window';
	ss_id = document.getElementById("ss_id").value;
	document.getElementById('ss_id').selectedIndex = 0;
	if (ss_id != "0") {
		url = 'sld_view_1.jsp?s_id=' + ss_id;
		//alert(url);
		newwindow = window.open(url, '_blank', 'scrollbar=yes');
		if (window.focus) {
			newwindow.focus();
		}
	}
}

function view_sld() {
	var ss_id = document.getElementById("ss_id").value;
	if (ss_id == "0") {
	} else {
		//document.getElementById("view_part").innerHTML = document.getElementById("loading_div").innerHTML;

		xmlHttp = GetXmlHttpObject();
		if (xmlHttp == null) {
			alert("Browser does not support HTTP Request");
			return;
		}
		
		//var url="viewImage1_SrcBKp.jsp";
		var url = "viewImage1.jsp";
		if (parseInt(ss_id) == 10) {
			//alert("ss_id==>"+ss_id);
			//url="viewImage1_makudi.jsp";                            commented need to remove once tested
		}
		url = url + "?ss_id=" + ss_id;
		// alert(2);
		xmlHttp.onreadystatechange = displaySLD;
		// alert(3);
		xmlHttp.open("GET", url, true);
		xmlHttp.send(null);
	}
}
var rtu_id;
var do_srlno;
var type_id;
var pres_status;
var change_status;
function displaySLD() {
	if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
		var showdata = xmlHttp.responseText;
		document.getElementById("view_part").innerHTML = showdata;
		var rightclick_flag = document.getElementById("rightclick_flag").value;
		//rightclick_flag = 1;
		//alert("rightclick_flag==>"+rightclick_flag);
		var role_id = document.getElementById("role_id").value;
		//alert("rightclick_flag :"+rightclick_flag+ "role_id==>"+role_id);
		if (parseInt(role_id) == 2) {
			if (parseInt(rightclick_flag) == 1) {
				(function() {
					$('img').bind('contextmenu', function(e) {
						var id = $(this).attr("id");
						var di_values = new Array();
						di_values = id.split("_");
						rtu_id = di_values[0];
						di_srlno = di_values[1];
						type_id = di_values[2];
						pres_status = di_values[3];
						if (parseInt(type_id) == 3) {
							if (parseInt(pres_status) == 1) {
								pres_status = 0;
							} else if (parseInt(pres_status) == 2) {
								pres_status = 1;
							}
						}
						if (parseInt(pres_status) == 1) {
							$('#open_id').show();
							$('#close_id').hide();
						} else {
							$('#open_id').hide();
							$('#close_id').show();
						}
						$('#contextmenu').css('left', e.pageX + 'px');
						$('#contextmenu').css('top', e.pageY + 'px');
						$('#contextmenu').show();
						e.preventDefault();
						return false;
					});

					$('html').click(function(e) {
						$('#contextmenu').hide();
					});
					$('#contextmenu').click(function(e) {
						e.stopPropagation();
					});

					$(window).resize(function() {
						$('#contextmenu').hide();
					});
				})();
			}
		}
	}
}

function getCoOrdinateValues(actionId) {

	clear();
	hideDashboardDiv();
	$.ajax({
		url : 'sld/mapping2.epx?actionId=' + actionId,
		beforeSend : function() {
			//document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
		},
		success : function(data) {
			$('#viewCoOrdinateTable').html(data);
			callDataTable('table1');
		}
	});
}

function getSld(actionId) {
	clear();
	hideDashboardDiv();
	$.ajax({
		url : 'sld/mapping.epx?actionId=' + actionId,
		beforeSend : function() {
			document.getElementById("datapart").innerHTML = document
					.getElementById("loader").innerHTML;
		},
		success : function(data) {
			$('#datapart').html(data);
			callDataTable('table1');
		}
	});
}

function getMapped() {
	clear();
	hideDashboardDiv();
	$.ajax({
		url : 'sld/sldmapping.epx',
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

function close_popup() {
	$('#mask , .login-popup').fadeOut(300, function() {
		$('#mask').remove();
	});
	return false;
}

function getImage() {
	document.getElementById("image").innerHTML = "";
	document.getElementById("parameters").innerHTML = "";
	document.getElementById("main").style.visibility = "visible";
	document.getElementById("save").style.visibility = "visible";
	var ss_id = document.getElementById("ss_id").value;

	xmlHttp = GetXmlHttpObject();
	if (xmlHttp == null) {
		alert("Browser does not support HTTP Request");
		return;
	}
	var url = "sldImage.jsp";
	url = url + "?ss_id=" + ss_id;
	xmlHttp.onreadystatechange = displayImage;
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
}
var paramArr = new Array();
function displayImage() {
	if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
		var showdata = xmlHttp.responseText;
		var arr = new Array();
		var arr1 = new Array();
		var paramValArr = new Array();
		arr1 = showdata.split("?");
		arr = arr1[0].split("#");
		paramValArr = arr1[1].split(",");
		//alert("arr1[1]==>"+arr[1]);
		paramArr = arr[1].split(",");
		//document.getElementById("image").style.background =  "url("+arr[0]+")";
		document.getElementById("image").innerHTML = "<img src='" + arr[0]
				+ "' width='1200px' style='border:1px solid #666'  />";
		document.getElementById("save_button").style.display = "block";

	}
}

var feeder_sel = -1;
var point_type = 0;
function select_Feeder(point_type_tmp) {
	//alert("select_feeder  -->  "+point_type_tmp);
	point_type = point_type_tmp;
	var ss_id = document.getElementById("ss_id").value;
	// alert(ss_id);
	if (ss_id != 0) {
		xmlHttp = GetXmlHttpObject();
		if (xmlHttp == null) {
			alert("Browser does not support HTTP Request");
			return;
		}
		var url = "getFeeders_For_SLD.jsp";
		url = url + "?ss_id=" + ss_id;
		xmlHttp.onreadystatechange = displayFeeders_for_select;
		xmlHttp.open("GET", url, true);
		xmlHttp.send(null);
	}
}
function displayFeeders_for_select() {
	if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
		var showdata = xmlHttp.responseText;
		var feeder_id_div = document.getElementById("feeder_id");
		var feeder_point_obj = document.getElementById("feeder_point");
		clear_Options(feeder_id_div);
		clear_Options(feeder_point_obj);

		var all_feeders_arr = showdata.split(";");
		for (var i = 0; i < all_feeders_arr.length - 1; i++) {
			var single_feeder_arr = all_feeders_arr[i].split(",");
			add_Option(feeder_id_div, single_feeder_arr[1],
					single_feeder_arr[0]);
		}
		show_Feeder_Sel();
	}
}
function show_Feeder_Sel() {
	$('body').append('<div id="mask"></div>');
	$('#mask').fadeIn(300);
	$("#login-box").fadeIn(300);
	var popMargTop = ($("#login-box").height() + 50) / 2;
	var popMargLeft = ($("#login-box").width() + 50) / 2;
	$("#login-box").css({
		'margin-top' : -popMargTop,
		'margin-left' : -popMargLeft
	});
}

function clear_Options(sel_obj) {
	for (var i = sel_obj.options.length; i >= 0; i--) {
		sel_obj.remove(i);
	}
}

function add_Option(sel_obj, text, value) {
	var option = document.createElement("option");
	option.text = text;
	option.value = value;
	try {
		// for IE earlier than version 8
		sel_obj.add(option, sel_obj.options[null]);
	} catch (e) {
		sel_obj.add(option, null);
	}
}

function first_choice(ch, sel_tab) {
	if (prev_div != 1) {
		prev_div.className = "";
	} else {
		document.getElementById("one").className = "";
	}

	prev_div = sel_tab;
	//tab.class = "current";
	sel_tab.className = "current";
	var menu_div_name = "menu_ch" + ch;
	document.getElementById("sub_menu_reports").innerHTML = document
			.getElementById(menu_div_name).innerHTML;
	choice = document.getElementById("r1").value;
	get_Report();
}
function second_choice(ch2) {
	choice = ch2;
	get_Report();
}

function getFeederPoints() {
	var feeder_id = $("#feeder_id").val();
	var dataString = "feeder_id=" + feeder_id + "&point_type=" + point_type;
	var url_str = "getFeederPointsData.jsp";
	$.ajax({
		type : "post",
		url : url_str,
		data : dataString,
		cache : false,
		dataType : 'json',
		success : function(responseJson) {
			var feeder_point_obj = $('#feeder_point');
			feeder_point_obj.find('option').remove();
			$.each(responseJson, function(key, value) {
				$('<option>').val(key).text(value).appendTo(feeder_point_obj);
			});
		}

	});
	return false;
}




var imagecnt1 = 1;
var imagecnt2 = 1;
var imagecnt3 = 1;
var imagecnt4 = 1;
var imagecnt5 = 1;
var imagecnt6 = 1;
var imagecnt7 = 1;
arrpos = new Array();
xPos = new Array();
yPos = new Array();
var div_inc = 0;

var name_global = "";
var id_global = "";
var paramName_global = "";

function add2() {
	
	var feeder_id = $("#feeder_id").val();
	var paramList = new Array();
	var div = document.createElement("div");

	id_global = 1;
	paramName_global = $("#feeder_point option:selected").text();
	var pointDesc = $("#feeder_point option:selected").text();
	var pointSrlno = $("#feeder_point option:selected").val();
	
	if (id_global == 1) {
		div.textContent = pointDesc;
		//div.id="param1_"+imagecnt1+"_"+feeder_id.trim();
		div.id = "param1_" + imagecnt1 + "_" + feeder_id.trim() + "_"
				+ pointSrlno + "_" + point_type;
		imagecnt1++;
	}
	if (id_global == 2) {
		div.textContent = paramName_global;
		div.id = "param2_" + imagecnt2 + "_" + feeder_id.trim();
		imagecnt2++;
	}
	if (id_global == 3) {
		div.textContent = paramName_global;
		div.id = "param3_" + imagecnt3 + "_" + feeder_id.trim();
		imagecnt3++;
	}
	if (id_global == 4) {
		div.textContent = paramName_global;
		div.id = "param4_" + imagecnt4 + "_" + feeder_id.trim();
		imagecnt4++;
	}
	if (id_global == 5) {
		div.textContent = paramName_global;
		div.id = "param5_" + imagecnt5 + "_" + feeder_id.trim();
		imagecnt5++;
	}
	if (id_global == 6) {
		div.textContent = paramName_global;
		div.id = "param6_" + imagecnt6 + "_" + feeder_id.trim();
		imagecnt6++;
	}
	if (id_global == 7) {
		div.textContent = paramName_global;
		div.id = "param7_" + imagecnt7 + "_" + feeder_id.trim();
		imagecnt7++;
	}
	var param = document.getElementById("image");
	document.getElementById("parameters").appendChild(div);
	var xPosition = "";
	var yPosition = "";
	$("#" + div.id).draggable({
		stop : function(event, ui) {
			var id = div.id;
			var xpos = parseInt($("#" + div.id).offset().top);
			var ypos = parseInt($("#" + div.id).offset().left);
		//	alert("id :"+id+"  xpos:"+xpos+"ypos : "+ypos);

			//sreenu start
			xPosition = (this.offsetLeft - this.scrollLeft + this.clientLeft);
			yPosition = (this.offsetTop - this.scrollTop + this.clientTop);
			//sreenu end
			var current_pos = 0;
	      //	alert(arrpos.length+"  <----  array  position  length");
			for (var loop = 0; loop < arrpos.length; loop++) {
				if (div.id == arrpos[loop].id)
					current_pos = loop;
				    //alert(current_pos+" <===== current position ");
			}
			//xPos[current_pos] = xpos;
			//yPos[current_pos] = ypos;
			xPos[current_pos] = yPosition;
			yPos[current_pos] = xPosition;
			//alert(div.id);
		}
	});
	arrpos[div_inc] = div;
	xPos[div_inc] = 0;
	yPos[div_inc] = 0;
	div_inc++;
	close_popup();
}

function checkPos() {
//	clear();
//	hideDashboardDiv();
	
	document.getElementById("datapart").value="";
	var id = document.getElementById("ss_id").value;
    alert("arrpos  --> "+arrpos+"   length  "+arrpos.length);
	var i;
	var arrVal = "";
	//alert(arrpos.length+"    array length");
	for (i = 0; i < arrpos.length; i++) {
		if (arrpos[i] != undefined) {
			var loc_id = arrpos[i].id;
			loc_id = loc_id.replace("param", "");
			//alert(loc_id);
			var array = new Array();
			array = loc_id.split("_");
			var newnewArray = "";
			 arrVal = arrVal + array[0] + "_" + array[2] + ","
					+ xPos[i] + "," + yPos[i] + "," + array[3] + "," + array[4]
					+ "@";
		}        
		/*else{
		        	newArray = arrVal= arrVal+" "+","+" "+"@";
		  }*/
	}
	
	 alert(arrVal+"  check");
	$.ajax({
		url : 'sld/savepoints.epx?id=' + id + '&arrVal=' + arrVal,
		beforeSend : function() {
		},
		success : function(data) {
			newnewArray = "";
			$('#datapart').html(data);
		imagecnt1 = 1;
		imagecnt2 = 1;
		imagecnt3 = 1;
	    imagecnt4 = 1;
		imagecnt5 = 1;
		imagecnt6 = 1;
		imagecnt7 = 1;
			arrpos = new Array();
			xPos = new Array();
			yPos = new Array();
	    	div_inc = 0;
			name_global = "";
		   id_global = "";
		   paramName_global = "";
		}
	});
}
