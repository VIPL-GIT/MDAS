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

function getAllInstant(actionId){
	clear();
	hideDashboardDiv();
	
	$.ajax( {
        url:'getAllInstant.jsp',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           callDataTableWithName('allinstant','All_Instant');
          
        }
     });
}

function getMeterDataReports(actionId) {
	clear();
	hideDashboardDiv();
	
	$.ajax( {
        url:'MeterData/MeterDataTabs.epx?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
          // callDataTable('table1');
          
        }
     });
}
function getHierarchyData_reports(value,level){
	if(value=="all"){
		document.getElementById("levelID").value="0";
		  document.getElementById("level").value="0";
	}else{
	  document.getElementById("levelID").value=value;
	  document.getElementById("level").value=level;
	}
	
	var install_point=$("#install_type").val();
	if(install_point==8 && level=="level_3"){
		getMeters(value,'meterSel');
		return false;
	}else{
	//document.getElementById("parentId").value=value;
		 for(var i=1;i<=5;i++){
	    	 if(i>parseInt(level.split("_")[1]))
	    	   $('#level_'+i).children('option:not(:first)').remove();
	     }
	
	$.ajax( {
        url:'getHierarchyData.jsp?id='+value,
        success:function(data) { 
        	
           $('#'+level).html(data);           
           //$('#'+level).chosen();
        }
     });
	}//else
}

function getHierarchyData_analysis(value,level,report){
	document.getElementById("levelID").value=value;
	document.getElementById("level").value=level;
	
	var install_point=$("#install_type").val();
	if(install_point==8 && level=="level_3"){
		getMeters_analysis(value,'meterSel',report);
		return false;
	}else{
	//document.getElementById("parentId").value=value;
	
	
	$.ajax( {
        url:'getHierarchyData.jsp?id='+value,
        success:function(data) { 
        	
           $('#'+level).html(data);           
           //$('#'+level).chosen();
        }
     });
	}//else
}
function getHierarchyData_alerts(value,level){
	var install_point=$("#install_type").val();
	document.getElementById("levelID").value=value;
	document.getElementById("level").value=level;
	
	if(install_point==8 && level=="level_3"){
		getMeters1(value,'meterSel');
		return false;
	}else{
	//document.getElementById("parentId").value=value;
	
	
	$.ajax( {
        url:'getHierarchyData.jsp?id='+value,
        success:function(data) { 
        	
           $('#'+level).html(data);           
           //$('#'+level).chosen();
        }
     });
	}//else
}

function getHierarchyData_diag(value,level){
	var install_point=$("#install_type").val();
	document.getElementById("levelID").value=value;
	document.getElementById("level").value=level;
	
	if(install_point==8 && level=="level_3"){
		getMeters2(value,'meterSel');
		return false;
	}else{
	//document.getElementById("parentId").value=value;
	
	
	$.ajax( {
        url:'getHierarchyData.jsp?id='+value,
        success:function(data) { 
        	
           $('#'+level).html(data);           
           //$('#'+level).chosen();
        }
     });
	}//else
}

function getHierarchyData(value,level){
	
	//document.getElementById("parentId").value=value;
	document.getElementById("levelID").value=value;
	document.getElementById("level").value=level;
	
	$.ajax( {
        url:'getHierarchyData.jsp?id='+value,
        success:function(data) { 
        	
           $('#'+level).html(data);           
           //$('#'+level).chosen();
        }
     });
}



//function getMeters(value,divid){
//	alert(value+" getmETERS "+divid);
//	var levelId=document.getElementById("levelID").value=value;
//	//var level=document.getElementById("level").value=level;
//	var install_type=$("#install_type").val();
//	var level=8;
//	
//	$.ajax( {
//       // url:'getMetersByssID.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level,
//        url:'getMetersByssID.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level,
//        success:function(data) {  
//        	 $('#'+divid).empty();
//           $('#'+divid).html(data);
//           $('#childID').chosen();
//           
//        }
//     });
//}



function getInstantTabs() {     //  its called when the instantaneous tab is clicked
	
// alert("in getEnergyoptions");
   $('#kumar1').removeClass('current');
  $('#kumar2').removeClass('current');
  $('#kumar3').removeClass('current');
  $('#kumar4').removeClass('current');
  $('#kumar5').removeClass('current');
  $('#kumar').addClass('current');
  document.getElementById('cont-1-1').style.display = "block";
  document.getElementById("DataPart").innerHTML = "";
  document.getElementById('cont-2-1').style.display = "none";
  document.getElementById('cont-3-1-1').style.display = "none";
  document.getElementById('cont-3-1').style.display = "none";
  document.getElementById('cont-3-3').style.display = "none";
  document.getElementById('cont-4-1').style.display = "none";
  document.getElementById('cont-5-1').style.display = "none";
  document.getElementById('cont-6-1').style.display = "none";
  //document.getElementById('ip_opt_div1').style.display = "none";//KUMAR
  document.getElementById("instant2").checked=false;//KUMAR
  document.getElementById("instant3").checked=false;//KUMAR
  document.getElementById("instant1").checked=true;//KUMAR
  $("#cont-1-1").load(location.href + " #cont-1-1>*", "");//KUMAR
   var radioButtons1 = document.getElementsByName("instant");
     if(radioButtons1.length=3)
     {
         if (document.getElementById("instant2").checked)
         {
             //alert("checked2");
         }
         else if (document.getElementById("instant1").checked)
         {
             //alert("checked1");
         }
         else
         {
             //alert("checked3");
         }
     }
  var but_value;
  
  var radioButtons = document.getElementsByName("instant");
  for (var x = 0; x < radioButtons.length; x++) {
      if (radioButtons[x].checked) {
          but_value = radioButtons[x].value;
 
      }
  }
 
  if (but_value == 3) {
     // document.getElementsByName("instant")[0].checked = true;
      
      
  }
  getInstantData2(but_value); 
}


function getInstantData2(data_type) // for Instant & Complete data
{

    document.getElementById("DataPart").innerHTML = "";
   // document.getElementById('ip_opt_div1').style.display = "none";

    sdate = document.getElementById("sDate").value;
    edate = document.getElementById("eDate").value;

    if (data_type == 2) 
    {   // getting dates for complete data
        if (sdate == "" && edate == "")
            getDates();

    }
    if (data_type == 1) {   // for instantaneous data
        document.getElementById('date_div').style.display = "none";
    }
    document.getElementById('gdate').style.display = "none";
    var chbEMF = document.getElementById("AEMF");
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request");
        return;
    }

   var Selected = document.getElementById('childID').selectedIndex;
   var SelectedOption = document.getElementById('childID').options[Selected].value;
  //  var SelectedOption ="41853";
    
    var url = "getInstantData.jsp?data_type=" + escape(data_type) + "&meter_id=" + escape(SelectedOption) + "&sdate=" + escape(sdate) + "&edate=" + escape(edate) + "&start=1" + "&ApplyEMF=" + (chbEMF.checked ? "1" : "0");
//    alert(url);
    xmlHttp.onreadystatechange = getInstantaneousData;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
}//getInstantanious Data

function getInstantaneousData()
{
    //alert("in getinstantaneous Data");
    document.getElementById('DataPart').style.display = "block";
   // document.getElementById('ip_opt_div1').style.display = "none";//Kumar
    
    document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        var showdata = xmlHttp.responseText;
        
        document.getElementById("DataPart").innerHTML = showdata;
        //$('.highcharts-container').css('visibility','hidden');
        var but_value;
        var radioButtons = document.getElementsByName("instant");
        for (var x = 0; x < radioButtons.length; x++) {
            if (radioButtons[x].checked) {
                but_value = radioButtons[x].value;
            }
        }
     //   document.getElementsByName("instant")[but_value - 1].checked = true;
         $('.highcharts-container').css('visibility','hidden');
         
    }
    implement4();     
}//getInstantaneousData

function getDates() //Call from complete data radio button
{
    //alert("In Getdates function");
    //$("#DataPart").load(location.href + " #DataPart>*", "");//KUMAR
    document.getElementById("DataPart").style.display = "none";
    document.getElementById("sDate").value = "";
    document.getElementById("eDate").value = "";
    document.getElementById('ip_opt_div1').style.display = "none";
    document.getElementById("gdate").style.display = "none";
    document.getElementById('date_div').style.display = "block";
    var txtMonth = document.getElementById('month');
    var date = new Date();
    var month = "0" + (date.getMonth() + 1);
    txtMonth.value = (date.getFullYear() + "-" + (month.slice(-2)));
//    loadDate(1);
}

function getInstantOptions() //call from graphs radio button
{
    
    document.getElementById("DataPart").innerHTML = "";
    //document.getElementById("graphPart").style.display = "none";
    document.getElementById('date_div').style.display = "none";
    document.getElementById("graphDate").value = "";
    document.getElementById('gdate').style.display = "block";
    loadDate(2);
    document.getElementById('ip_opt_div1').style.display = "block";
}

function getSelectedCompleteData(start)
{
//    alert("start  in reports menu = "+start);
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request");
        return;
    }
    document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
    var ApplyEMF = document.getElementById("AEMF");  // new code
    sdate = document.getElementById("sDate").value;
    edate = document.getElementById("eDate").value;
//    var sdate = "20/07/2017";
//    var edate = "28/07/2017";
    var Selected = document.getElementById('dtr').selectedIndex;
    var SelectedOption = document.getElementById('dtr').options[Selected].value;
    var url = "getInstantData.jsp?data_type=2&start=" + escape(start) + "&meter_id=" + escape(SelectedOption) + "&sdate=" + escape(sdate) + "&edate=" + escape(edate) + "";
    url = url + "&ApplyEMF=" + (ApplyEMF.checked ? "1" : "0"); //new code
//    alert("url = "+url);
    xmlHttp.onreadystatechange = getInstantaneousData;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
}

var rph = new Array();
var yph = new Array();
var bph = new Array();
var graphTime = new Array();
var kwh = new Array();

function getVANDCGraphs(e_opt) {
    if (document.getElementById("graphDate").value == "")
    {
        //alert("please select date");
        document.getElementById("graphDate").focus();
    }
    document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
    sdate = document.getElementById("graphDate").value;
    //alert(sdate);
//    var sdate = "27/07/2017";
    
    
    
    var Selected = document.getElementById('childID').selectedIndex;
    var SelectedOption = document.getElementById('childID').options[Selected].value;
    
    
    
    xmlHttp = GetXmlHttpObject();
    var chbEMF = document.getElementById("AEMF");

    if (e_opt == 1)
    {
        //alert("voltage");
        var url = "getVoltageGraph.jsp?sdate=" + escape(sdate) + "&meter_id=" + escape(SelectedOption) + "";
        url = url + "&ApplyEMF=" + (chbEMF.checked ? "1" : "0");
//        alert("url = "+url);
        xmlHttp.onreadystatechange = getVoltageGraphData;

    }
    if (e_opt == 2)
    {
        //alert("current");
        var url = "getCurrentGraph.jsp?sdate=" + escape(sdate) + "&meter_id=" + escape(SelectedOption) + "";
        url = url + "&ApplyEMF=" + (chbEMF.checked ? "1" : "0");
//        alert("url = "+url);
        xmlHttp.onreadystatechange = getCurrentGraphData;

    }
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
}

function getVoltageGraphData() {

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        var showdata = xmlHttp.responseText;
        gtime = showdata.substring(showdata.indexOf("time") + 5, showdata.indexOf("rph") - 1);
        // alert("gtime "+gtime)
        gtime = gtime.replace(";", "");
        //alert("gtime  "+gtime)
        graphTime = gtime.split(",");

        rc = showdata.substring(showdata.indexOf("rph") + 4, showdata.indexOf("yph") - 1);
        //alert("rc  "+rc)
        rph = rc.split(",");
        for (i = 0; i < rph.length; i++)
        {
            rph[i] = +rph[i];
        }
        yc = showdata.substring(showdata.indexOf("yph") + 4, showdata.indexOf("bph") - 1);
        yph = yc.split(",");
        for (j = 0; j < yph.length; j++)
        {
            yph[j] = +yph[j];
        }
        bc = showdata.substring(showdata.indexOf("bph") + 4);
        bph = bc.split(",");
        for (k = 0; k < bph.length; k++)
        {
            bph[k] = +bph[k];
        }
        voltageGraph();
    }
}

//---------- Energy  ----------

function getEnergyOptions() {
  
  $('#kumar').removeClass('current');
  $('#kumar2').removeClass('current');
  $('#kumar3').removeClass('current');
  $('#kumar4').removeClass('current');
  $('#kumar5').removeClass('current');
  $('#kumar1').addClass('current');
  $('#kumar6').removeClass('current');
  $('#kumar7').removeClass('current');
  $('#kumar8').removeClass('current');
  document.getElementById('cont-1-1').style.display = "none";
  document.getElementById("DataPart").innerHTML = "";
  document.getElementById('cont-3-1-1').style.display = "none";
  document.getElementById('cont-3-1').style.display = "none";
  document.getElementById('cont-3-3').style.display = "none";
  document.getElementById('cont-4-1').style.display = "none";
  document.getElementById('cont-2-1').style.display = "block";
  document.getElementById('cont-5-1').style.display = "none";
  document.getElementById('cont-6-1').style.display = "none";

  xmlHttp = GetXmlHttpObject();
  if (xmlHttp == null)
  {
      alert("Browser does not support HTTP Request");
      return;
  }
  var meter=document.getElementById("meter").value;
 // alert("meter "+meter);
  var url = "getEnergyScreen.jsp?meter="+meter;
  xmlHttp.onreadystatechange = getEnergyPageAction1;
  xmlHttp.open("GET", url, true);
  xmlHttp.send(null);
}
function getEnergyPageAction1() {
	if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
    var showData = xmlHttp.responseText;
  
//  document.getElementById("load_div2").innerHTML = "";
  document.getElementById("cont-2-1").style.visibility = "visible";
  document.getElementById("cont-2-1").innerHTML = showData;
  loadDate(3);
  getEnergyReport();
    }
}
function getEnergyReport() {
// alert("in energy");
  var options = document.getElementsByName("e_opt1");
  var selectedEOption = 0;
  for (var i = 0; i < options.length; i++)
  {
      if (options[i].checked)
          selectedEOption = options[i].value;
  }
  getEnergy(selectedEOption);
}
function getEnergy(e_opt1) {
	
  var SelectedOption= document.getElementById('meter_energy').value;
  document.getElementById("DataPart").innerHTML = "";
  xmlHttp = GetXmlHttpObject();
  if (xmlHttp == null)
  {
      alert("Browser does not support HTTP Request");
      return;
  }
  var url = "";
    
  /*var Selected = document.getElementById('childID').selectedIndex;
  var SelectedOption = document.getElementById('childID').options[Selected].title;*/
  
 
 
  var esdate = document.getElementById('energyFromDate').value;
  var eedate = document.getElementById('energyToDate').value;
//  alert("e_opt1 === "+e_opt1);
  if((e_opt1!=7) && (e_opt1!=9))
  if (esdate == "" && eedate == "") {
      alert("Please select dates...");
      return 0;
  }
//  var esdate = "01/07/2017";
//  var eedate = "02/08/2017";
//  alert("e_opt1  "+e_opt1);
  var energyMF =document.getElementById('energyMF');
//  var e_opt1 = 1;
  document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;

  if (e_opt1 == 1)
      url = "main_energy.jsp?meter_id=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
  if (e_opt1 == 2)
      url = "tod_energy.jsp?meter_id=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
  if (e_opt1 == 3)
      url = "energy_consumption.jsp?meter_id=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
  if (e_opt1 == 5)
      url = "tod_consumption.jsp?meter_id=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
  if (e_opt1 == 6)
      url = "max_demand.jsp?meter_id=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
  if (e_opt1 == 7)
      url = "AutoResetBillings/ResetBillings.jsp?meter_id=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
  if (e_opt1 == 8)
      url = "AutoResetBillings/DailyBillings.jsp?meter_id=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
  if (e_opt1 == 9){
//      alert('selected = '+e_opt1);
      url = "AutoResetBillings/consumptionReport.jsp?meter_id=" + SelectedOption + "&start=1&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
  }
  //alert(url);
  xmlHttp.onreadystatechange = getEnergyData;
  xmlHttp.open("GET", url, true);
  xmlHttp.send(null);
}
function mainEnergy(start) {
  xmlHttp = GetXmlHttpObject();
  if (xmlHttp == null)
  {
      alert("Browser does not support HTTP Request");
      return;
  }
  var energyMF = document.getElementById("energyMF");
  var Selected = document.getElementById('dtr').selectedIndex;
  var SelectedOption = document.getElementById('dtr').options[Selected].title;
  var esdate = document.getElementById('energyFromDate').value;
  var eedate = document.getElementById('energyToDate').value;
  var url = "main_energy.jsp?meter_sl_no=" + escape(SelectedOption) + "&start=" + escape(start) + "&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
  xmlHttp.onreadystatechange = getEnergyData;
  xmlHttp.open("GET", url, true);
  xmlHttp.send(null);
}
function max_demand(start) {
  xmlHttp = GetXmlHttpObject();
  if (xmlHttp == null)
  {
      alert("Browser does not support HTTP Request");
      return;
  }
  var energyMF = document.getElementById("energyMF");
  var Selected = document.getElementById('dtr').selectedIndex;
  var SelectedOption = document.getElementById('dtr').options[Selected].title;
  var esdate = document.getElementById('energyFromDate').value;
  var eedate = document.getElementById('energyToDate').value;
  var url = "max_demand.jsp?meter_sl_no=" + SelectedOption + "&start=" + escape(start) + "&esdate=" + escape(esdate) + "&eedate=" + escape(eedate) + "&energyMF=" + (energyMF.checked ? "1" : "0");
  // alert(url);
  xmlHttp.onreadystatechange = getEnergyData;
  xmlHttp.open("GET", url, true);
  xmlHttp.send(null);
}
function getEnergyData() {
//  document.getElementById('submenu').style.display = "block";
  document.getElementById('DataPart').style.display = "block";
//  document.getElementById('meter_details').style.display = "block";
//  document.getElementById('report_header').style.display = "none";
  if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
  {
      //alert("inside energydata");
      var showdata = xmlHttp.responseText;
      document.getElementById("DataPart").innerHTML = showdata;
      implement2();
  }
}






       function getReportsInstantData(meter_id) {
    	   getMeterInfo(meter_id);
    	   
    	   meter_id=document.getElementById("childID").value;
     document.getElementById("meter").value=meter_id;
    // alert(document.getElementById("meter").value+" check meter");
       //alert("meter_id = "+meter_id);
       //getHeader(); // to get the header first 
       //alert("in change");
       //$("#pageheader").load(location.href + " #pageheader>*", "");//KUMAR
       //$( "#pageheader" ).load(window.location.href + " #pageheader" );
       $('#kumar1').removeClass('current');
       $('#kumar2').removeClass('current');
       $('#kumar3').removeClass('current');
       $('#kumar4').removeClass('current');
       $('#kumar5').removeClass('current');
       $('#kumar').addClass('current');
       $('#kumar6').removeClass('current');
       $('#kumar7').removeClass('current');
       $('#kumar8').removeClass('current');
       document.getElementById('cont-1-1').style.display = "block";
       document.getElementById("DataPart").innerHTML = "";
       document.getElementById('cont-2-1').style.display = "none";
       document.getElementById('cont-3-1').style.display = "none";
       document.getElementById('cont-3-3').style.display = "none";
       document.getElementById('cont-3-1-1').style.display = "none";
       document.getElementById('cont-4-1').style.display = "none";
       document.getElementById('cont-5-1').style.display = "none";
       document.getElementById('cont-6-1').style.display = "none";
       //document.getElementById('ip_opt_div1').style.display = "none";//KUMAR
       document.getElementById("instant1").checked=true;
       document.getElementById("instant2").checked=false;
       if (meter_id != 0)
       {
          var radioButtons1 = document.getElementsByName("instant");
          if(radioButtons1.length=3)
          {
              if (document.getElementById("instant2").checked)
              {
                  //alert("checked2");
              }
              else if (document.getElementById("instant1").checked)
              {
                  //alert("checked1");
              }
          }
          document.getElementById("DataPart").innerHTML = document.getElementById("loading").innerHTML;//KUMAR LOADING DIV 
           //document.getElementsByName("instant")[0].checked = true;            // div of instant with values 1, 2 or 3 
           var chbEMF = document.getElementById("AEMF");                         // div of checkbox aemf

           xmlHttp = GetXmlHttpObject();
           if (xmlHttp == null)
           {
               alert("Browser does not support HTTP Request");
               return;
           }
           var url = "getInstantData.jsp";
           url = url + "?meter_id=" + meter_id + "&ApplyEMF=" + (chbEMF.checked ? "1" : "0") + "&InstType=instant";
        //  alert("url = "+url);
           xmlHttp.onreadystatechange = getInstantaneousData1;
           xmlHttp.open("GET", url, true);
           xmlHttp.send(null);
       } else
       {
           document.getElementById("DataPart").innerHTML = "";
           dcuFunction();
       }
       
       
   }
function getMeterInfo(){
	var Selected = document.getElementById('childID').selectedIndex;
    var SelectedOption = document.getElementById('childID').options[Selected].value;
    $.ajax( {
        url:'getMeterInfo.jsp?meter='+SelectedOption,
        beforeSend:function(){
        	
        },
        success:function(data) {
           $('#meterinfo').html(data);
          
        }
     });
    
}       
   function getInstantaneousData1() {
       if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
       {
           if(xmlHttp.status == 302){
               window.location="/relogin.do";
           }
           if(xmlHttp.status == 200){
           var showdata = xmlHttp.responseText;
           document.getElementById("DataPart").innerHTML = showdata;
           var but_value;
           var radioButtons = document.getElementsByName("instant");
           for (var x = 0; x < radioButtons.length; x++) {
               if (radioButtons[x].checked) {
                   but_value = radioButtons[x].value;
                   
               }
           }
           
       }
           //document.getElementsByName("instant")[but_value - 1].checked = true;
          
       }
   }//getInstantaneousData
   
   
   
   function dcuFunction()
   {
       document.getElementById("submenu").style.display = "none";
       document.getElementById("report_header").style.display = "block";
       document.getElementById("DataPart").innerHTML = "";
       getDCUData();

   }
   
   
   
   function getDCUData()
   {
       
       if(document.getElementById("substation").value==-1)
       {
           alert('Select dcu ...');
       }
       else
       {
          
           document.getElementById("DataPart").innerHTML= document.getElementById("dcu_loading").innerHTML;
           xmlHttp=GetXmlHttpObject();
           if (xmlHttp==null)
           {
               alert ("Browser does not support HTTP Request");
               return;
           }
          // alert(document.getElementById("substation").value)
           var dcu_opt = get_radio_value();
           var myOpts = document.getElementById('dtr').options;
           //alert(myOpts.length);
           var meter_ids = "";
           for(var loop=1;loop<myOpts.length;loop++)
           {
               if(loop==myOpts.length-1)
                   meter_ids = meter_ids + myOpts[loop].value;
               else
                   meter_ids = meter_ids + myOpts[loop].value + ",";
           }
          // alert(meter_ids);
           //alert(dcu_opt);
           //this is for instantanious data
           //alert("dcu_opt "+dcu_opt)
           if(dcu_opt==1)
           {
               var url="getDCU_Instant_Data.jsp?dcu_id="+meter_ids;
               //alert(url);
               xmlHttp.onreadystatechange=getDCU_Instant_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }

           //this is for instantanious historical data
           if(dcu_opt==2)
           {
              // alert("123")
               var url="getDCU_Instant_Data_HistoryMenu.jsp?dcu_id="+meter_ids
               //alert(url);
               xmlHttp.onreadystatechange=getDCU_Instant_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }

           //this is for analog data
           if(dcu_opt==3)
           {
               var url="getDCU_AI_Data.jsp?dcu_id="+meter_ids
               xmlHttp.onreadystatechange=getDCU_AI_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }
           
           //this is for analog history data
           if(dcu_opt==4)
           {
               var url="getDCU_AI_Data_HistoryMenu.jsp?dcu_id="+meter_ids
               xmlHttp.onreadystatechange=getDCU_AI_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }

           //this is for digital data
           if(dcu_opt==5)
           {
               var url="getDCU_DI_Data.jsp?dcu_id="+meter_ids
               xmlHttp.onreadystatechange=getDCU_AI_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }

           //this is for digital historical data
           if(dcu_opt==6)
           {
               var url="getDCU_DI_Data_HistoryMenu.jsp?dcu_id="+meter_ids
               xmlHttp.onreadystatechange=getDCU_Instant_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }

           //this is for soe data
           if(dcu_opt==7)
           {
               var url="getDCU_SOE_Data.jsp?dcu_id="+meter_ids
               xmlHttp.onreadystatechange=getDCU_AI_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }

           //this is for ls data
           if(dcu_opt==8)
           {
               var url="getDCU_LS_Data.jsp?dcu_id="+meter_ids
               xmlHttp.onreadystatechange=getDCU_AI_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }

           //this is for LS historical data
           if(dcu_opt==9)
           {
               var url="getDCU_LS_Data_HistoryMenu.jsp?dcu_id="+meter_ids
               xmlHttp.onreadystatechange=getDCU_Instant_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }

           //this is for tamper data
           if(dcu_opt==10)
           {
               var url="getDCU_Tamper_Data.jsp?dcu_id="+meter_ids
               xmlHttp.onreadystatechange=getDCU_AI_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }

           //this is for Tamper historical data
           if(dcu_opt==11)
           {
               var url="getDCU_Tamper_Data_HistoryMenu.jsp?dcu_id="+meter_ids
               xmlHttp.onreadystatechange=getDCU_Instant_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }
           
           //this is for Billing data
           if(dcu_opt==12)
           {
               var url="getDCU_Billing_Data.jsp?dcu_id="+meter_ids;
               xmlHttp.onreadystatechange=getDCU_Instant_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }
           
           //this is for Transformation loss report
           if(dcu_opt==13)   //for Transformation loss report
           {
               var url="getDCU_Transfrm_Loss_Date_Selection.jsp?dcu_id="+meter_ids
               xmlHttp.onreadystatechange=getDCU_Instant_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }
           if(dcu_opt==14)  // for miscellaneous reports
           {
               var url="getDCU_Miscellaneous_Report.jsp?dcu_id="+meter_ids
               xmlHttp.onreadystatechange=getDCU_Instant_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }
           if(dcu_opt==15)   //to send ondemand to dCU
           {
               var ssId=document.getElementById("substation").value;            
               if(ssId==0)// alert("please select Substation");
               var url="DCU_ondemand.jsp?dcu_id="+ssId;
              // alert(url);
               xmlHttp.onreadystatechange=getDCU_Instant_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }
           if(dcu_opt==16)  // for OD Reports
           {
               var url="getDCU_OD_Report.jsp?dcu_id="+meter_ids
               xmlHttp.onreadystatechange=getDCU_Instant_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }
           if(dcu_opt==32)
           {
               var url="getDCU_Instant_Data_MF.jsp?dcu_id="+meter_ids;
               //alert(url);
               xmlHttp.onreadystatechange=getDCU_Instant_Data;
               xmlHttp.open("GET",url,true);
               xmlHttp.send(null);
           }        
       }
   }
   function getDCU_Instant_Data()
   {
       document.getElementById('DataPart').style.display = "block";
       //document.getElementById('report_header').style.display = "block";
       if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
       {
               var showdata = xmlHttp.responseText;
               document.getElementById("DataPart").innerHTML= showdata;
       }
   }
   
   
   
   
   
   function getInstant(value) {
   }
   
   function getcompleteData()
   {
	  // alert("complete data");
       document.getElementById("DataPart").innerHTML = "";
       //document.getElementById('ip_opt_div1').style.display = "none";
     
       var month = document.getElementById("month").value;
       var getmonth=month.substring(month.lastIndexOf("-")+1);
       var getyear=month.substring(0,month.lastIndexOf("-"));
       //alert(getmonth+"-"+getyear);
       //var monthyear = getmonth+"-"+getyear;
       //alert(monthyear);
        var chbEMF = document.getElementById("AEMF");  

       xmlHttp = GetXmlHttpObject();
       if (xmlHttp == null)
       {
           alert("Browser does not support HTTP Request");
           return;
       }
       //selected option to get the meter serial number
       
       
       var Selected = document.getElementById('childID').selectedIndex;
       var SelectedOption = document.getElementById('childID').options[Selected].value;
     // alert("meter no "+SelectedOption);
      // var SelectedOption ="41853";
       
       var url = "getCompleteData.jsp?meter_id=" + escape(SelectedOption) + "&month=" + escape(getmonth) + "&year=" + escape(getyear)+"&EMF="+(chbEMF.checked?"1":"0");
       //alert(url);
       xmlHttp.onreadystatechange = getInstantaneousData;
       xmlHttp.open("GET", url, true);
       xmlHttp.send(null);
   }

   
   function getCurrentGraphData() {
//       document.getElementById('submenu').style.display = "block";
       if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
       {
           var showdata = xmlHttp.responseText;

           gtime = showdata.substring(showdata.indexOf("time") + 5, showdata.indexOf("rph") - 1);
           gtime = gtime.replace(";", "");
           graphTime = gtime.split(",");

           rc = showdata.substring(showdata.indexOf("rph") + 4, showdata.indexOf("yph") - 1);

           rph = rc.split(",");
           for (i = 0; i < rph.length; i++)
           {
//               alert(rph[i]);
               rph[i] = +rph[i];
//               alert(rph[i]);
           }

           yc = showdata.substring(showdata.indexOf("yph") + 4, showdata.indexOf("bph") - 1);
           yph = yc.split(",");
           for (j = 0; j < yph.length; j++)
           {
               yph[j] = +yph[j];
           }

           bc = showdata.substring(showdata.indexOf("bph") + 4);
           bph = bc.split(",");
           for (k = 0; k < bph.length; k++)
           {
               bph[k] = +bph[k];
           }
           currentGraph();
       }
   }

//   function currentGraph()
//   {
//       var chart;
////       alert('111---');
////       $(document).ready(function () {
//       chart = new Highcharts.Chart({
//           chart: {
//               renderTo: 'DataPart',
//               defaultSeriesType: 'line',
//               marginRight: 100,
//               marginBottom: 55,
//               width: window.size,
//               borderColor: "#4572A7",
//               borderRadius: 15,
//               borderWidth: 1,
//               margin: [40, 40, 40, 40]
//
//           },
//           title: {
//               text: 'Currents Graph',
//               x: -20 //center
//           },
//           subtitle: {
//               text: '',
//               x: -20
//           },
//           xAxis: {
//               gridLineWidth: 1,
//               lineColor: '#000',
//               tickColor: '#000',
//               tickInterval: 3,
//               labels: {
//                   style: {
//                       color: '#000',
//                       font: '11px Trebuchet MS, Verdana, sans-serif'
//                   }
//               },
//
//               title: {
//                   text: ' Time(hh:mm:ss)'
//
//               },
//
//               categories: graphTime,
//               min: 0,
//               max: 24
//
//           },
//           yAxis: {
//               min: 0,
//               minorTickInterval: 'auto',
//               lineColor: '#000',
//               lineWidth: 1,
//               tickWidth: 1,
//               tickColor: '#000',
//               labels: {
//                   style: {
//                       color: '#000',
//                       font: '11px Trebuchet MS, Verdana, sans-serif'
//                   }
//               },
//               title: {
//                   text: 'currents (A)'
//               },
//               plotLines: [{
//                       value: 0,
//                       width: 1,
//                       color: '#808080'
//                   }]
//           },
//           tooltip: {
//               formatter: function () {
//                   return '<b>' + this.series.name + '</b><br/>' +
//                           this.x + '(hh:mm:ss)' + '</b><br/> ' + this.y + '';
//               }
//           },
//           legend: {
//               layout: 'vertical',
//               align: 'right',
//               verticalAlign: 'top',
//               x: -10,
//               y: 100,
//               borderWidth: 0
//           },
//           scrollbar: {
//               enabled: true
//           },
//           series: [{
//                   name: 'BPhase',
//                   data: bph,
//                   color: 'blue'
//               }, {
//                   name: 'RPhase',
//                   data: rph,
//                   color: 'red'
//               }, {
//                   name: 'YPhase',
//                   data: yph,
//                   color: 'yellow'
//               }, ],
//           exporting: {
//               enabled: false
//           },
//           credits: {
//               enabled: false
//           }
//       });
////       });
//   }
function currentGraph()
{
   chart = new Highcharts.Chart({
           chart: {
               renderTo:'DataPart',
               zoomType: 'x',
               borderColor: "#4572A7",
               borderRadius: 15,
               borderWidth: 1,
               margin: [80, 0, 30, 80]
           },
           title: {
               text: 'Currents Graph'
           },
           subtitle: {
               text: document.ontouchstart === undefined ?
                       'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
           },
           xAxis: {
               gridLineWidth: 1,
               lineColor: '#000',
               tickColor: '#000',
               tickInterval:2,
               labels: {
                   enabled: false
//                   style: {
//                       color: '#000',
//                       font: '11px Trebuchet MS, Verdana, sans-serif'
//                   }
               },
               title: {
                   text: 'Time(hh:mm:ss)'

               },
               categories: graphTime
           },
           yAxis: {
               title: {
                   text: 'Currents (A)'
               }
           },
           tooltip: {
        	    shared: true,
	       	    split: false,
	       	    enabled: true
           },
           legend: {
               enabled: false
           },
           plotOptions: {
               area: {
                   fillColor: {
                       linearGradient: {
                           x1: 0,
                           y1: 0,
                           x2: 0,
                           y2: 1
                       },
                       stops: [
                           [0, Highcharts.getOptions().colors[0]],
                           [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                       ]
                   },
                   marker: {
                       radius: 2
                   },
                   lineWidth:0.5,
                   states: {
                       hover: {
                           lineWidth: 1
                       }
                   },
                   threshold: null
               }
           },
           legend: {
               layout: 'horizontal',
               backgroundColor: '#FFFFFF',
               align: 'left',
               verticalAlign: 'top',
               x: 1,
               y: 10,
               floating: true,
               shadow: true
           },
           credits: {
               enabled: false
           },
           series: [{
               name: 'BPhase',
               data: bph,
               color:'#5fa2e2'
           }, {
               name: 'RPhase',
               data: rph,
               color:'#e25f5f'
           }, {
               name: 'YPhase',
               data: yph,
               color: '#e2d75f'
           },]
       });
}


function voltageGraph()
{

   chart = new Highcharts.Chart({
           chart: {
               renderTo:'DataPart',
               zoomType: 'x',
               borderColor: "#4572A7",
               borderRadius: 15,
               borderWidth: 1,
               margin: [80, 0, 30, 80]
           },
           title: {
               text: 'Voltages Graph'
           },
           subtitle: {
               text: document.ontouchstart === undefined ?
                       'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
           },
           xAxis: {
               gridLineWidth: 1,
               lineColor: '#000',
               tickColor: '#000',
               tickInterval:3,
               labels: {
                   enabled: false
//                   style: {
//                       color: '#000',
//                       font: '11px Trebuchet MS, Verdana, sans-serif'
//                   }
               },
               title: {
                   text: 'Time(hh:mm:ss)'

               },
               categories: graphTime
               
           },
           yAxis: {
               title: {
                   text: 'Voltages (v)'
               }
           },
           tooltip: {
        	   shared: true,
        	    split: false,
        	    enabled: true
           },
           legend: {
               enabled: false
           },
           plotOptions: {
               area: {
                   fillColor: {
                       linearGradient: {
                           x1: 0,
                           y1: 0,
                           x2: 0,
                           y2: 1
                       },
                       stops: [
                           [0, Highcharts.getOptions().colors[0]],
                           [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                       ]
                   },
                   marker: {
                       radius: 2
                   },
                   lineWidth:0.5,
                   states: {
                       hover: {
                           lineWidth: 1
                       }
                   },
                   threshold: null
               }
           },
           legend: {
               layout: 'horizontal',
               backgroundColor: '#FFFFFF',
               align: 'left',
               verticalAlign: 'top',
               x: 1,
               y: 10,
               floating: true,
               shadow: true
           },
           credits: {
               enabled: false
           },
           series: [{
               name: 'BPhase',
               data: bph,
               color:'#5fa2e2'
           }, {
               name: 'RPhase',
               data: rph,
               color:'#e25f5f'
           }, {
               name: 'YPhase',
               data: yph,
               color: '#e2d75f'
           },]
       });

}


function loadDate(x){
  
  var today = new Date();
  var dd = today.getDate();

   
  var mm = today.getMonth() + 1;
  
  if (mm.toString().length == 1) {
      mm = "0" + mm;
  }
  if (dd.toString().length == 1) {
      dd = "0" + dd;
  }

  var yyyy = today.getFullYear();
  var date = dd + "/" + mm + "/" + yyyy;
  
  var today1 = new Date();
  today1.setDate(today1.getDate() - 10);
  
  var dd1 = today1.getDate();
  var mm1 = today1.getMonth() + 1;
  if (mm1.toString().length == 1) {
      mm1 = "0" + mm1;
  }
  if (dd1.toString().length == 1) {
      dd1 = "0" + dd1;
  }
  var yyyy1 = today1.getFullYear();
  var date1 = dd1 + "/" + mm1 + "/" + yyyy1;
  
  
  
  if (x == 1) {
      document.getElementById("datepickfrom").value = date1;
      document.getElementById("datepickto").value = date;
  }
  if (x == 2) {

      document.getElementById("graphDate").value = date;
  }
  if (x == 3) {
	  
      document.getElementById("energyFromDate").value = addRsubMonths(new Date(),-6);
      document.getElementById("energyToDate").value = date;
  }
  if (x == 4) {
      document.getElementById("loadsDate").value = date1;
      document.getElementById("loadeDate").value = date;
  }
  if (x == 5) {
      document.getElementById("loadgraphDate").value = date;
  }
  if (x == 6) {
      document.getElementById("multiplesDate").value = date1;
      document.getElementById("multipleeDate").value = date;
  }
  if (x == 7) {
      document.getElementById("eventsDate").value = addRsubMonths(new Date(),-6);
      document.getElementById("eventeDate").value = date;
  }
  if (x == 8) {
      document.getElementById("dsDate").value = date1;
      document.getElementById("deDate").value = date;
  }
  if(x==10){
	  document.getElementById("s1Date").value = date;
  }
}

function addRsubMonths(date,months){
	 date.setMonth(date.getMonth() + months);
	 var dd = date.getDate();
	   
	  var mm = date.getMonth() + 1;
	  
	  if (mm.toString().length == 1) {
	      mm = "0" + mm;
	  }
	  if (dd.toString().length == 1) {
	      dd = "0" + dd;
	  }

	  var yyyy = date.getFullYear();
	  var dateStr = dd + "/" + mm + "/" + yyyy;
	  return dateStr;
}

function getLoadPage() {
	
	var meter=document.getElementById("meter").value;
	 //alert(meter);
	
  //alert("inside getLoadPage method");
  $('#kumar1').removeClass('current');
  $('#kumar').removeClass('current');
  $('#kumar3').removeClass('current');
  $('#kumar4').removeClass('current');
  $('#kumar5').removeClass('current');
  $('#kumar2').addClass('current');
  $('#kumar6').removeClass('current');
  $('#kumar7').removeClass('current');
  $('#kumar8').removeClass('current');
  document.getElementById('cont-1-1').style.display = "none";
  document.getElementById("DataPart").innerHTML = "";
  document.getElementById('cont-2-1').style.display = "none";
  document.getElementById('cont-4-1').style.display = "none";
  document.getElementById('cont-3-1-1').style.display = "block";
  document.getElementById('cont-5-1').style.display = "none";
  document.getElementById('cont-6-1').style.display = "none";

  xmlHttp = GetXmlHttpObject();
  if (xmlHttp == null)
  {
      alert("Browser does not support HTTP Request");
      return;
  }
  var url = "getLoadScreen1.jsp?meter="+meter;
//  alert("url ====> "+url);
  xmlHttp.onreadystatechange = getLoadPageAction;
  xmlHttp.open("GET", url, true);
  xmlHttp.send(null);
}
function getLoadPageAction() {
	 if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
     {
		  var showData = xmlHttp.responseText;
		  
		//  document.getElementById("load_div2").innerHTML = "";
		  document.getElementById("cont-3-1-1").style.visibility = "visible";
		  document.getElementById("cont-3-1-1").innerHTML = showData;
		  loadDate(6);
		  getMultipleLoadDate();
     }
}




function clearall()
{
       // alert("in JS clearall");                        
        //$('.highcharts-container').css('visibility','hidden');
        //document.getElementsByName("highcharts-container").style.display="none";
        $(".highcharts-container").hide();


}

	function test()
	{
		$('#tamperdatatable').DataTable
                ( 
                    {
                                dom: 'Blfrtip',
                                //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                                //buttons: ['csv', 'excel', 'print'],
                                        
                                buttons: 
                                [
                                    {
                                        extend: 'copy',
                                        title: 'Tamper Data'
                                    },
                                    
                                    {
                                        extend: 'excel',
                                        title: 'Tamper Data'
                                    },
                                
                                    {
                                        extend: 'csv',
                                        title: 'Tamper Data'
                                    }
                                ]


                    } 
                );
	}

  //--------------------------------------- Energy Data -------------------------------------------

//---------------------------------------------- Load Survey-------------------------------------------
        function getLoadPage1() {
        	
       var SelectedOption= document.getElementById('meter_energy').value;
        	
       //alert("inside getLoadPage method "+SelectedOption);
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-3-1-1').style.display = "block";
        document.getElementById('cont-3-1').style.display = "block";
        document.getElementById('cont-3-3').style.display = "none";
        document.getElementById('cont-5-1').style.display = "none";
        document.getElementById('cont-6-1').style.display = "none";
        
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getLoadSurveyScreen.jsp?meter_id="+SelectedOption;
//        alert("url ====> "+url);
        xmlHttp.onreadystatechange = getLoadPageAction1;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function getLoadPageAction1() {
    	if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
        { 
	        var showData = xmlHttp.responseText;
	        document.getElementById("cont-3-1").style.visibility = "visible";
	        document.getElementById("cont-3-1").innerHTML = showData;
	        loadDate(4);
	        getLoadsurvayDatas();
        }
    }

    function getLoadsurvayDatas() {
    	
    	
    	var SelectedOption= document.getElementById('meter_energy_2').value;

        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
        var lsdate = document.getElementById("loadsDate").value;
        var ledate = document.getElementById("loadeDate").value;

        if (lsdate == "" && ledate == "") {
            alert("Please select dates...");
            return 0;
        }
        var Selected = document.getElementById('childID').selectedIndex;
        var SelectedOption = document.getElementById('childID').options[Selected].value;

      
        var chbMF=document.getElementById("chbMF");
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getLoadsurvayData.jsp";
        url = url + "?lsdate=" + escape(lsdate) + "&ledate=" + escape(ledate) + "&meter_id=" + escape(SelectedOption) + "&start=1&EMF="+(chbMF.checked?"1":"0");        
//        alert("url = "+url);
        xmlHttp.onreadystatechange = insertLoadsurvayData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        // }

    }//getLoadsurvayData
    function getLoadData(start) {

        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
        var lsdate = document.getElementById("loadsDate").value;
        var ledate = document.getElementById("loadeDate").value;
//        var lsdate = "06/07/2017";
//        var ledate = "31/07/2017";
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].value;
//        var chbMF = document.getElementById("chbMF");

        var url = "getLoadsurvayData.jsp?start=" + escape(start) + "&meter_id=" + escape(SelectedOption) + "&lsdate=" + escape(lsdate) + "&ledate=" + escape(ledate) + "&EMF=1";
//        alert("url = "+url);
        xmlHttp.onreadystatechange = insertLoadsurvayData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function insertLoadsurvayData() {
        document.getElementById('DataPart').style.display = "block";
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            var showdata = xmlHttp.responseText;
            document.getElementById("DataPart").innerHTML = showdata;
            implement1();
        }
    }
    function getLoadDate()
    {
    	var meter1= document.getElementById('meter_energy').value;
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById("cont-3-1").innerHTML = "";
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "load_daywise_screen.jsp?meter1="+meter1;
        xmlHttp.onreadystatechange = getLoadDateAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function getLoadDateAction()
    {
    	if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
        var showData = xmlHttp.responseText;
        document.getElementById("cont-3-1").style.visibility = "visible";
        document.getElementById("cont-3-1").innerHTML = showData;
        loadDate(5);
        }
    }
   /* function getMultipleLoadDate()
    {
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById("cont-3-1").innerHTML = "";
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "load_multiple_screen.jsp";
        xmlHttp.onreadystatechange = getMultipleLoadDateAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function getMultipleLoadDateAction()
    {
        var showData = xmlHttp.responseText;
        document.getElementById("cont-3-1").style.visibility = "visible";
        document.getElementById("cont-3-1").innerHTML = showData;
        loadDate(6);
    }*/
//------------------------------------- Tamper -------------------------------------------------------------
    function getEventsPage() {

        var meter= document.getElementById('meter').value;
           // alert("in event page "+meter);
         $('#kumar1').removeClass('current');
        $('#kumar2').removeClass('current');
        $('#kumar').removeClass('current');
        $('#kumar4').removeClass('current');
        $('#kumar5').removeClass('current');
        $('#kumar3').addClass('current');
        $('#kumar6').removeClass('current');
        $('#kumar7').removeClass('current');
        $('#kumar8').removeClass('current');
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById('cont-3-1-1').style.display = "none";
        document.getElementById('cont-3-1').style.display = "none";
        document.getElementById('cont-3-3').style.display = "none";
        document.getElementById('cont-4-1').style.display = "block";
        document.getElementById('cont-5-1').style.display = "none";
        document.getElementById('cont-6-1').style.display = "none";
//        alert("events")        
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getEventsScreen.jsp?meter="+meter;
        xmlHttp.onreadystatechange = getEventsPageAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }

    function getEventsPageAction()
    {
    	if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
	        var showData = xmlHttp.responseText;
	        document.getElementById("cont-4-1").style.visibility = "visible";
	        document.getElementById("cont-4-1").innerHTML = showData;
	        loadDate(7);
	        getEventsDatas("1");
        }
    }

    function selectall(source) {
        if (source.id == "select") {
            if (source.checked == true) {
                chkboxes = document.getElementsByName('R-Phase');
                for (var i = 0; i < chkboxes.length; i++)
                {
                    chkboxes[i].checked = true;
                }
            }
            if (source.checked == false) {
                chkboxes = document.getElementsByName('R-Phase');
                for (var i = 0; i < chkboxes.length; i++)
                {
                    chkboxes[i].checked = false;
                }
            }
        }
    }
    function deselect(source)
    {
        checkbox = document.getElementsByName('allevent');
        for (var i in checkbox)
            checkbox[i].checked = source.unchecked;
    }
    function getEventsDatas(bal)
    {
       // alert("bal ---> " + bal);
    	var SelectedOption= document.getElementById('childID').value;
    	//alert("selected otion "+SelectedOption);
        var values = new Array();
        checkboxes = document.getElementsByName('R-Phase');
        for (var i = 0; i < checkboxes.length; i++)
        {
            if (checkboxes[i].checked == true)
            {
                values = values + checkboxes[i].value + ",";
            }
        }
        if (values.length == 0)
        {
            alert("Please select atleast one checkbox");
            return;
        }
//        alert("values -------> " + values);
        document.getElementById('DataPart').style.display = "block";
//        document.getElementById('report_header').style.display = "none";        
        var esdate = document.getElementById("eventsDate").value;
        var eedate = document.getElementById("eventeDate").value;
//        var esdate = "01/05/2017";
//        var eedate = "01/08/2017";
        if(esdate=="" || eedate==""){
            alert("select from and to dates");
            return 0;
        }
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
        
        
        /*var Selected = document.getElementById('childID').selectedIndex;
        var SelectedOption = document.getElementById('childID').options[Selected].title;*/
       
        
        var chbMF=document.getElementById("chbMF");
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }

//        if (data_type == 1)
        var url = "getEventsData.jsp"; 
//        if (data_type == 2)
//            var url = "getEventsCumulativeData.jsp";
        url = url + "?sdate=" + escape(esdate) + "&edate=" + escape(eedate) + "&meter_id=" + escape(SelectedOption) + 
                "&start=1&EMF="+(chbMF.checked?"1":"0")+ "&chkvalues=" + escape(values);
        //alert(url);
        xmlHttp.onreadystatechange = insertEventsData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }//getEventData

    function getEventsHistory(start)
    {
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var values = new Array();
        checkboxes = document.getElementsByName('R-Phase');
        for (var i = 0; i < checkboxes.length; i++)
        {
            if (checkboxes[i].checked)
            {
                values = values + checkboxes[i].value + ",";
            }
        }
        var Selected = document.getElementById('dtr').selectedIndex;
        var SelectedOption = document.getElementById('dtr').options[Selected].title;
        var tampersFrom = document.getElementById("eventsDate").value;
        var tampersTo = document.getElementById("eventeDate").value;
//        var tampersFrom = "01/05/2017";
//        var tampersTo = "01/08/2017";
        var url = "getEventsData.jsp";
        url = url + "?sdate=" + escape(tampersFrom) + "&edate=" + escape(tampersTo) + "&meter_id=" + escape(SelectedOption) + "&start=" + escape(start) + "&chkvalues=" + escape(values);        
//        alert("url in getEventsHistory"+url);
        xmlHttp.onreadystatechange = insertEventsData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }

    function insertEventsData()
    {
        document.getElementById('DataPart').style.display = "block";
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            var showdata = xmlHttp.responseText;
            document.getElementById("DataPart").innerHTML = showdata;
            implement3();
        }
    }//insertEventsData
//----------------------------------------- Snapshot Dat -------------------------------------------

    function getSnapshotPage(){
         $('#kumar1').removeClass('current');
        $('#kumar2').removeClass('current');
        $('#kumar3').removeClass('current');
        $('#kumar').removeClass('current');
        $('#kumar5').removeClass('current');
        $('#kumar4').addClass('current');
        $('#kumar6').removeClass('current');
        $('#kumar7').removeClass('current');
        $('#kumar8').removeClass('current');
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-3-1-1').style.display = "none";
        document.getElementById('cont-3-1').style.display = "none";
        document.getElementById('cont-3-3').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-5-1').style.display = "block";
        document.getElementById('cont-6-1').style.display = "none";

        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getSnapShotDates.jsp";
        xmlHttp.onreadystatechange = getSnapePageAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    function getSnapePageAction() {
    	if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
        var showData = xmlHttp.responseText;
//        document.getElementById("load_div2").innerHTML = "";
        document.getElementById("cont-5-1").style.visibility = "visible";
        document.getElementById("cont-5-1").innerHTML = showData;
        loadDate(8);
        getSnapshotSelectedData();
        }
    }

    function viewMeter360(){
    	$('#kumar1').removeClass('current');
        $('#kumar2').removeClass('current');
        $('#kumar3').removeClass('current');
        $('#kumar').removeClass('current');
        $('#kumar5').removeClass('current');
        $('#kumar4').removeClass('current');
        $('#kumar6').addClass('current');
        $('#kumar7').removeClass('current');
        $('#kumar8').removeClass('current');
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-3-1-1').style.display = "none";
        document.getElementById('cont-3-1').style.display = "none";
        document.getElementById('cont-3-3').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-5-1').style.display = "none";
        //document.getElementById('cont-6-1').style.display = "block";
        var Selected = document.getElementById('childID').selectedIndex;
        var meter = document.getElementById('childID').options[Selected].text;
        
        //get360Report(meter);
        
        window.open("360HomeNew.epx?meter="+meter, '_blank');
        
    }
    function getDataProfiles(){
   	 $('#kumar1').removeClass('current');
        $('#kumar2').removeClass('current');
        $('#kumar3').removeClass('current');
        $('#kumar').removeClass('current');
        $('#kumar4').removeClass('current');
        $('#kumar5').addClass('current');
        $('#kumar6').removeClass('current');
        $('#kumar7').removeClass('current');
        $('#kumar8').removeClass('current');
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-3-1-1').style.display = "none";
        document.getElementById('cont-3-1').style.display = "none";
        document.getElementById('cont-3-3').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-5-1').style.display = "block";
        document.getElementById('cont-6-1').style.display = "none";
   	 $.ajax({
   	   	 url : 'getDatProfilesDates.jsp',	       
   	       type : 'GET',
   	       beforeSend:function(){
           	 jQuery("#cont-5-1").html(jQuery("#loading").html());
           },
   	       success : function(data) {	         
   	           $("#cont-5-1").html(data);
   	           loadDate(8);
   	       },
   	       error : function(err) {	           
   	           $("#cont-5-1").html(err);
   	       }
   	   });
    }
    function downloadAllProfilesData(){
   	 var Selected = document.getElementById('childID').selectedIndex;
        var meter = document.getElementById('childID').options[Selected].text;
   	 var sdate=$("#dsDate").val();
   	 var edate=$("#deDate").val();
   	 location.href="Downloads/downloadAllProfilesData.epx?meter="+meter+"&sdate="+sdate+"&edate="+edate;
    }
    
    function getSnapshotSelectedData() {
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById('cont-3-1-1').style.display = "none";
        document.getElementById('cont-3-1').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-5-1').style.display = "block";
        document.getElementById('cont-6-1').style.display = "none";
//        alert("snapshot data called");
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
//        var data_type111 = 12;
     
        var Selected = document.getElementById('childID').selectedIndex;
        var SelectedOption = document.getElementById('childID').options[Selected].value;
      
       
        
        
        var startindDate = document.getElementById("dsDate").value;
        var endingDate = document.getElementById("deDate").value;

        if (startindDate == "" && endingDate == "") {
            alert("Please select dates...");
//                return 0;
        }
        var chbMF = document.getElementById("chbMF");
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;        
        var url = "getSnapshotData.jsp?startindDate=" + escape(startindDate) + "&endingDate=" + escape(endingDate)
                + "&meter_id=" + escape(SelectedOption)+"&EMF=" + (chbMF.checked ? "1" : "0");
//        alert("url = "+url);
        xmlHttp.onreadystatechange = insertSnapshotData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }//getSnapshotData
    
  
    function insertSnapshotData()
    {
        document.getElementById('DataPart').style.display = "block";
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            var showdata = xmlHttp.responseText;
            document.getElementById("DataPart").innerHTML = showdata;
            $('#snapshotdata').DataTable
             ( 
                {
                        dom: 'Bfrtip',
                        bSort: false,
                        buttons: [{
                                        extend: 'excel',
                                        title: 'Snapshot Data'
                                    },
                                
                                    {
                                        extend: 'csv',
                                        title: 'Snapshot Data'
                                    },
                                    {
                                        extend: 'pdf',
                                        title: 'Snapshot Data',
                                        orientation: 'landscape'
                                    }
                                ],
                        //buttons: ['csv', 'excel', 'print'],
                        lengthMenu : [ 10, 25, 50, 75, 100 ]
                } 
             );
        }
    }//insertSnapshotData
    
    function getSnapShotGraph(){
    	
   	 
   	 var Selected = document.getElementById('childID').selectedIndex;
        var SelectedOption = document.getElementById('childID').options[Selected].value;
      
        var startindDate = document.getElementById("dsDate").value;
        var endingDate = document.getElementById("deDate").value;

        if (startindDate == "" && endingDate == "") {
            alert("Please select dates...");
//                return 0;
        }
        var chbMF = document.getElementById("chbMF");
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;        

                
        var url = "getSnapShotGraph.jsp?startindDate=" + escape(startindDate) + "&endingDate=" + escape(endingDate)
                + "&meter_id=" + escape(SelectedOption)+"&EMF=" + (chbMF.checked ? "1" : "0");
        
        xmlHttp.onreadystatechange = getSnapShotBarGraph;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
    
    function getSnapShotBarGraph(){
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            var showdata = xmlHttp.responseText;
            for (j = 0; j < showdata.split(";").length; j++)
            {
                paramNames = paramNames + "," + showdata.split(";")[j].substring(0, showdata.split(";")[j].indexOf(","));
                paramValues = paramValues + ";" + showdata.split(";")[j].substring(showdata.split(";")[j].indexOf(","));
            }

            if (paramValues.split(";")[1].length < 2) {
                paramValues = {};
                paramNames = {};
                document.getElementById("DataPart").innerHTML = "<h3 align='center'>No data Available.</h3>";
                return;
            }
            if (paramValues.split(";")[1].length > 2) {
           	 SnapShotBar();
            }
        }	 
    }
    function SnapShotBar()
    {
   	 
   	 var dates= []
   		// var dates=new Array();
   		 for( k=0;k<paramValues.split(";")[1].substring(1).split(",").length;k++)
   	     {
   			 //dates[k]=+paramValues.split(";")[k].substring(1).split(",")[k];
   			 
   			 dates.push(paramValues.split(";")[1].substring(1).split(",")[k]);
   	     }
   		
   	 
   	// alert(paramNames);
   	// alert(paramValues);
        console.log(paramNames);
        console.log(paramValues);
        console.log("times : "+paramValues.split(";")[1].substring(1).split(",")[0]);
        var seriesDataBar = [];  //constructor in series to add multiple series at a time
        for (var i = 2; i < paramNames.split(",").length; i++) {
            var dataSeries = new Array();
            for (j = 0; j < paramValues.split(";")[i].substring(1).split(",").length; j++)
            {
                dataSeries[j] = +paramValues.split(";")[i].substring(1).split(",")[j];
            }

            seriesDataBar.push({name: paramNames.split(",")[i], // adding multiple series(params) values to graph
                data: dataSeries,
                step: true

            });

        }
        var chart;
    
            chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'DataPart',
                    //defaultSeriesType: 'line',
                    marginRight: 100,
                    marginBottom: 55,
                    width: window.size,
                    borderColor: "#4572A7",
                    borderRadius: 15,
                    borderWidth: 1,
                    margin: [80, 0, 0, 80],
                    zoomType: 'x'

                },
                
               /* xAxis: [{
        		    categories: dates,
        		    crosshair: true
        		  }],
        		 */
                xAxis: {
                     
                     categories: dates,
                     
//                    min: 0,
//                    max: 10
                },
                credits:{
                  enabled:false  
                },
                yAxis: {
                    min: 0,
                    tickInterval:500,
                    title: {
                        text: 'Values'
                    }
                },
                legend: {
                    layout: 'horizontal',
                    backgroundColor: '#FFFFFF',
                    align: 'left',
                    verticalAlign: 'top',
                    x: 1,
                    y: 20,
                    floating: true,
                    shadow: true
                },
              
               
                
                scrollbar: {
                    enabled: true
                },

                tooltip: {
                    formatter: function () {
                        return '<b>' + this.x + '</b><br/> ' + this.y + '';
                    }
                },

                series: seriesDataBar
            });


        
        paramNames = {};
        paramValues = {};
    }

//-------------------------------Get Outage Reports----------------------------
function getOutageReportPage()
{
        //alert("in get outage report page");
        $('#kumar').removeClass('current');
        $('#kumar1').removeClass('current');
        $('#kumar2').removeClass('current');
        $('#kumar3').removeClass('current');
        $('#kumar4').removeClass('current');
        $('#kumar5').addClass('current');
        document.getElementById('cont-1-1').style.display = "none";
        document.getElementById('cont-2-1').style.display = "none";
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('cont-3-1-1').style.display = "none";
        document.getElementById('cont-3-1').style.display = "none";
        document.getElementById('cont-4-1').style.display = "none";
        document.getElementById('cont-5-1').style.display = "none";
        document.getElementById('cont-6-1').style.display = "block";
        document.getElementById('outelements').style.display = "";

    
}
function getOutageInfo()
{
        //alert("in outageinfo");
        var outdate = document.getElementById('outdate').value;
        var meterselect = document.getElementById('dtr').selectedIndex
        var meter_id = document.getElementById('dtr').options[meterselect].value;
     
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getOutageScreen.jsp?meter_id="+meter_id+"&date="+outdate;
        xmlHttp.onreadystatechange = getOutageAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        
}
function getOutageAction()
{
        document.getElementById('DataPart').style.display = "block";
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            var showdata = xmlHttp.responseText;
            document.getElementById("DataPart").innerHTML = showdata;  
        }
        implement5();
}

    var phasorDate;
    function getPhasor(meter,mdate,div){
        //window.open("getPhasor.jsp?meter="+meter+"&date="+mdate,"Phasor Diagram",'width=650,height=250,left=500,top=250,scrollbars=yes');
        
//     url="getInstantGuageValues.jsp?meter_no="+escape(meter)+"&searchDate="+escape(mdate);   
//        var win = window.open(url, '_blank');
//        win.focus();
     var sameDiv=0;
    $('.shown').each(function(index, obj){
       if($(this).attr("id")==div){
           $(this).removeClass("shown");
           $(this).empty();
           sameDiv=1 ;
       }
     });
     if(sameDiv==0){
    $('.tr_phasor').each(function(index, obj){    
       $(this).empty();
       $(this).removeClass("shown");
       if($(this).attr("id")==div)
           $(this).addClass("shown");
     });
      $.ajax( {
        url:"getInstantGuageValues.jsp?meter_no="+escape(meter)+"&searchDate="+escape(mdate),
        beforeSend: function(msg){
        	document.getElementById(div).innerHTML=$('#loading').html();
          }, 
        success:function(data) {
           document.getElementById(div).innerHTML="<td colspan='15' id='pahsor_"+div+"'></td>";
          
           
        rph_c=data.split(":")[1].split(",")[0];    
        yph_c=data.split(":")[1].split(",")[1];
        bph_c=data.split(":")[1].split(",")[2];
        rph_v=data.split(":")[0].split(",")[0];
        yph_v=data.split(":")[0].split(",")[1];
        bph_v=data.split(":")[0].split(",")[2];
        rph_pf=data.split(":")[2].split(",")[0];
        yph_pf=data.split(":")[2].split(",")[1];
        bph_pf=data.split(":")[2].split(",")[2];
        r_y_ang=data.split(":")[3].split(",")[0];
        r_b_ang=data.split(":")[3].split(",")[1];
        
       getPhaseGuage(rph_pf,yph_pf,bph_pf,rph_v,yph_v,bph_v,rph_c,yph_c,bph_c,r_y_ang,r_b_ang,'pahsor_'+div);
          }
     });
 }
       
    }
function toDegrees (angle) {
    
  return parseFloat((angle*(180/Math.PI)).toFixed(2));
}
    function getPhaseGuage(rphPF,yphPF,bphPF,Vr,Vy,Vb,Ir,Iy,Ib,RY,RB,div)
{ 
  // alert(Math.min(Vr,Vy,Vb)+"-------"+ parseFloat(yphPF));
 //  alert(RY+" "+RB);
    var ang1=0,ang2=0,ang3=0;   
        ang1 =  toDegrees(Math.acos(rphPF));  
        ang2 =  toDegrees(Math.acos(yphPF));   
        ang3 =  toDegrees(Math.acos(bphPF));
       
        if(parseFloat(rphPF)<0){
        	ang1=180-ang1;
        	ang1=360-ang1;
        }
        if(parseFloat(yphPF)<0){
        	ang2=180-ang2;
        	ang2=360-ang2;
        }
        if(parseFloat(bphPF)<0){
        	ang3=180-ang3;
        	ang3=360-ang3;
        }
    
     maxV=Math.max(Vr,Vy,Vb);
     maxC=Math.max(Math.abs(Ir),Math.abs(Iy),Math.abs(Ib));
     
     vrAmp=(Vr/maxV)*100; vyAmp=(Vy/maxV)*100; vbAmp=(Vb/maxV)*100;
     IrAmp=(Math.abs(Ir)/maxC)*100; IyAmp=(Math.abs(Iy)/maxC)*100; IbAmp=(Math.abs(Ib)/maxC)*100;
     
   // alert(IrAmp);
    if(Math.min(Vr,Vy,Vb)>50){
        
 
    chart = new Highcharts.Chart({
        chart: {
            renderTo: div,
            type: 'gauge',
            plotBackgroundColor: null,
            plotBackgroundImage: null,
            plotBorderWidth: 0,
            plotShadow: false,
            marginTop:-5,
           // marginLeft: 160,
           // width:500,
            height:250
                      
            
        },
        tooltip: {
            enabled: false
        },
        title: {
           useHTML:true,
            text: '<h5 style="color:#3B5998;padding-bottom:1px;padding-right:1px;">Phaser Diagram</h5> <br /> '+
                  '&theta;(<font color="#990000">Vr </font>-<font color="#FF9933">Vy</font>)'+RY+'<br />'+
                  '&theta;(<font color="#990000">Vr </font>-<font color="#6699FF">Vb</font>) '+RB+'<br />'+
                  '&theta;(<font color="#990000">Vr </font>-<font color="red">IR</font>)'+parseFloat(ang1)+'<br />'+
                  '&theta;(<font color="#FF9933">Vy </font>-<font color="#E6B800">IY</font>)'+parseFloat(ang2)+'<br />'+
                  '&theta;(<font color="#6699FF">Vb </font>-<font color="blue">IB</font>) '+parseFloat(ang3),
style:{
textAlign:'left',
fontSize:'9px'
},
align: 'left'
        },

        pane: {
            startAngle: 0,
            endAngle: 360,
                  
            background:[
                {
                   backgroundColor: '#FFF',
                   outerRadius: '0%',
                   innerRadius: '0%'
                }
                ] 
           
        },
plotOptions: {
            gauge: {
                dial: {
                    borderWidth:50,
                    baseWidth:3,
                    topWidth: 3,
                    baseLength: '70%', // of radius
                    rearLength: '0%',
                    dashStyle:'longdashdot'
                }
            }
        },
            
        // the value axis
        yAxis: {
            min: 0,
            max: 360,
            minorTickInterval: 120,
            minorTickLength:15,
            minorTickPosition:'outside',
            tickLength:80, 
            tickWidth:2,
            tickInterval:120,
            plotLines: [{ 
                color: 'red',
                width: 2,                
                value:0                
            },{ 
                color: '#E6B800',
                width: 2,
                value:120                
            },{ 
                color: 'blue',
                width: 2,
                value:240               
            }],
            labels: {
                distance: 30,
                formatter: function () {
                    if (this.value == 0) {                        
                        return 'v1=0';
                    } else if (this.value == 120) {
                        return 'v2=120';
                    } else if (this.value == 240) {
                        return 'v3=240';
                    }
                }
            }
        },

        series: [{
            name: 'Ir',
            data: [parseFloat(ang1)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'red'
            }
            
        },
        {
            name: 'Iy',
            data: [parseFloat(ang2.toFixed(2))+parseFloat(RY)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: '#E6B800'
            }
        },
        {
            name: 'Ib',
            data: [parseFloat(ang3.toFixed(2))+parseFloat(RB)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'blue'
            }
        },
        {
            name: 'Vr',
            data: [0],
           dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'red'
            }
            
        },
        {
            name: 'Vy',
            data: [parseFloat(RY)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: '#E6B800'
            }
        },
        {
            name: 'Vb',
            data: [parseFloat(RB)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'blue'
            }
        }
                 
                ],
        credits:{
                enabled:false
            },
             exporting: {
                enabled: true
            }

    },
   // Add some life
    function (chart) {
        setInterval(function () {
            chart.series[0].options.dial.radius =IrAmp;            
            chart.series[0].isDirty = true;
            chart.series[1].options.dial.radius =IyAmp;
            chart.series[1].isDirty = true;
            chart.series[2].options.dial.radius =IbAmp;
            chart.series[2].isDirty = true;
            chart.series[3].options.dial.radius =vrAmp;
            chart.series[3].isDirty = true;
            chart.series[4].options.dial.radius =vyAmp;
            chart.series[4].isDirty = true;
            chart.series[5].options.dial.radius =vbAmp;
            chart.series[5].isDirty = true;
            chart.redraw();
            
        }, 3000);
    });                                  


    }
     if(Math.min(Vr,Vy,Vb)==0 && parseFloat(yphPF)==0 && !((parseFloat(Vy)==1 || parseFloat(Vy)==0) && parseFloat(Vb)==0)){
    // if(!(Math.round(Vy)==0 && Math.round(Vb)==0)){
  $(function () {
    
    var chart = new Highcharts.Chart({
    
        chart: {
            renderTo: div,
            type: 'gauge',
            plotBackgroundColor: null,
            plotBackgroundImage: null,
            plotBorderWidth: 0,
            plotShadow: false,
            marginLeft: 60,
            width:300,
            height:250
             
        },
        
       tooltip: {
            enabled: false
        },
        title: {
           useHTML:true,
            text: '<h3 style="color:#3B5998;padding-bottom:1px;padding-right:1px;">Phaser Diagram</h3> <br /> '+
                  '&theta;(<font color="#990000">Vr </font>-<font color="#FF9933">Vy</font>)'+RY+'<br />'+
                  '&theta;(<font color="#990000">Vr </font>-<font color="#6699FF">Vb</font>) '+RB+'<br />'+
                  '&theta;(<font color="#990000">Vr </font>-<font color="red">IR</font>)'+parseInt(ang1)+'<br />'+
                  '&theta;(<font color="#FF9933">Vy </font>-<font color="#E6B800">IY</font>)'+parseInt(ang2)+'<br />'+
                  '&theta;(<font color="#6699FF">Vb </font>-<font color="blue">IB</font>) '+parseInt(ang3),
style:{
textAlign:'left',
fontSize:'9px'
},
align: 'left'
        },

        
         pane: {
            startAngle: 0,
            endAngle: 360,
                  
            background:[
                {
                   backgroundColor: '#FFF',
                   outerRadius: '0%',
                   innerRadius: '0%'
                }
                ] 
           
        },
plotOptions: {
            gauge: {
                dial: {
                    borderWidth:50,
                    baseWidth:3,
                    topWidth: 3,
                    baseLength: '70%', // of radius
                    rearLength: '0%',
                    dashStyle:'longdashdot'
                }
            }
        },
            
        // the value axis
        yAxis: {
            min: 0,
            max: 360,
            minorTickInterval: 120,
            minorTickLength:15,
            minorTickPosition:'outside',
            tickLength:80, 
            tickWidth:2,
            tickInterval:120,
            plotLines: [{ 
                color: 'red',
                width: 2,                
                value:0                
            },{ 
                color: '#E6B800',
                width: 2,
                value:120                
            },{ 
                color: 'blue',
                width: 2,
                value:240               
            }],
            labels: {
                distance: 30,
                formatter: function () {
                    if (this.value == 0) {                        
                        return 'v1=0';
                    } else if (this.value == 120) {
                        return 'v2=120';
                    } else if (this.value == 240) {
                        return 'v3=240';
                    }
                }
            }
        },

        series: [{
            name: 'Ir',
            data: [parseFloat(ang1)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'red'
            }
            
        },
        {
            name: 'Iy',
            data: [parseFloat(ang2.toFixed(2))+parseFloat(RY)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: '#E6B800'
            }
        },
        {
            name: 'Ib',
            data: [parseFloat(ang3.toFixed(2))+parseFloat(RB)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'blue'
            }
        },
        {
            name: 'Vr',
            data: [0],
           dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'red'
            }
            
        },
        {
            name: 'Vy',
            data: [parseFloat(RY)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: '#E6B800'
            }
        },
        {
            name: 'Vb',
            data: [parseFloat(RB)],
            dataLabels: {
                enabled: false
            },
            dial: {
                backgroundColor: 'blue'
            }
        }
                 
                ],
        credits:{
                enabled:false
            },
             exporting: {
                enabled: true
            }

    },
   // Add some life
    function (chart) {
        setInterval(function () {
            chart.series[0].options.dial.radius =IrAmp;            
            chart.series[0].isDirty = true;
            chart.series[1].options.dial.radius =IyAmp;
            chart.series[1].isDirty = true;
            chart.series[2].options.dial.radius =IbAmp;
            chart.series[2].isDirty = true;
            chart.series[3].options.dial.radius =vrAmp;
            chart.series[3].isDirty = true;
            chart.series[4].options.dial.radius =vyAmp;
            chart.series[4].isDirty = true;
            chart.series[5].options.dial.radius =vbAmp;
            chart.series[5].isDirty = true;
            chart.redraw();
            
        }, 3000);
    });                                  
});
    }
    
    else{
    //document.getElementById("phaseGuage").innerHTML="Phase voltage Missing";
    
    }
}        
 function exportExcel(tableId,name){ 
    
//          table=document.getElementById(''+tableId+'');
//          if (!table.nodeType) table = document.getElementById(table);
//          download(table.outerHTML, name, "application/vnd.ms-excel");
	 //          download(table.innerHTML, "Export.xls", "application/vnd.ms-excel");
	 
	 
       //  var meter=$( "#dtr option:selected" ).attr("title");   blocked by shiva as meter coming dynamically so that we getting undefined
	 
	      var meter="12092598";
          var table = $("#instantTable");
            if(table && table.length){                 
                   // var preserveColors = (table.hasClass('table2excel_with_colors') ? true : false);
                    $(table).table2excel({
                            exclude: ".noExl",
                            name: "Excel Document Name",
                            filename:"Instant_"+meter+"_" + new Date().toISOString().replace(/[\-\:\.]/g, "") + ".xls",
                            fileext: ".xls",
                            exclude_img: true,
                            exclude_links: true,
                            exclude_inputs: true
                            //preserveColors: preserveColors
                    });
            }
}
 function exportExcelCompleteData(tableId,name){ 
//  var meter=$( "#dtr option:selected" ).attr("title");   blocked by shiva as meter coming dynamically so that we getting undefined
   var meter="12092598";
   var table = $("#completedatatable");
     if(table && table.length){                 
            // var preserveColors = (table.hasClass('table2excel_with_colors') ? true : false);
             $(table).table2excel({
                     exclude: ".noExl",
                     name: "Excel Document Name",
                     filename:"Complete_"+meter+"_" + new Date().toISOString().replace(/[\-\:\.]/g, "") + ".xls",
                     fileext: ".xls",
                     exclude_img: true,
                     exclude_links: true,
                     exclude_inputs: true
                     //preserveColors: preserveColors
             });
     }
}
 
 
 
 function exportExcelForMainEnergy(tableId,name){ 
	//  var meter=$( "#dtr option:selected" ).attr("title");   blocked by shiva as meter coming dynamically so that we getting undefined
	   var meter="12092598";
	   var table = $("#energydatatable");
	     if(table && table.length){                 
	            // var preserveColors = (table.hasClass('table2excel_with_colors') ? true : false);
	             $(table).table2excel({
	                     exclude: ".noExl",
	                     name: "Excel Document Name",
	                     filename:"MainEnergy_"+meter+"_" + new Date().toISOString().replace(/[\-\:\.]/g, "") + ".xls",
	                     fileext: ".xls",
	                     exclude_img: true,
	                     exclude_links: true,
	                     exclude_inputs: true
	                     //preserveColors: preserveColors
	             });
	     }
	}
 
 
 function exportExcelForMaxDemand(tableId,name){ 
		//  var meter=$( "#dtr option:selected" ).attr("title");   blocked by shiva as meter coming dynamically so that we getting undefined
		   var meter="12092598";
		   var table = $("#energydatatable");
		     if(table && table.length){                 
		            // var preserveColors = (table.hasClass('table2excel_with_colors') ? true : false);
		             $(table).table2excel({
		                     exclude: ".noExl",
		                     name: "Excel Document Name",
		                     filename:"MaxDemand_"+meter+"_" + new Date().toISOString().replace(/[\-\:\.]/g, "") + ".xls",
		                     fileext: ".xls",
		                     exclude_img: true,
		                     exclude_links: true,
		                     exclude_inputs: true
		                     //preserveColors: preserveColors
		             });
		     }
		} 

 
 

 function exportExcelForLoadData(tableId,name){ 
		//  var meter=$( "#dtr option:selected" ).attr("title");   blocked by shiva as meter coming dynamically so that we getting undefined
		   var meter="12092598";
		   var table = $("#loaddatatable");
		     if(table && table.length){                 
		            // var preserveColors = (table.hasClass('table2excel_with_colors') ? true : false);
		             $(table).table2excel({
		                     exclude: ".noExl",
		                     name: "Excel Document Name",
		                     filename:"LoadData_"+meter+"_" + new Date().toISOString().replace(/[\-\:\.]/g, "") + ".xls",
		                     fileext: ".xls",
		                     exclude_img: true,
		                     exclude_links: true,
		                     exclude_inputs: true
		                     //preserveColors: preserveColors
		             });
		     }
		} 
 
 
 

 function exportExcelForSnapShotData(tableId,name){ 
		//  var meter=$( "#dtr option:selected" ).attr("title");   blocked by shiva as meter coming dynamically so that we getting undefined
		   var meter="12092598";
		   var table = $("#snapshotdata");
		     if(table && table.length){                 
		            // var preserveColors = (table.hasClass('table2excel_with_colors') ? true : false);
		             $(table).table2excel({
		                     exclude: ".noExl",
		                     name: "Excel Document Name",
		                     filename:"SnapShotData_"+meter+"_" + new Date().toISOString().replace(/[\-\:\.]/g, "") + ".xls",
		                     fileext: ".xls",
		                     exclude_img: true,
		                     exclude_links: true,
		                     exclude_inputs: true
		                     //preserveColors: preserveColors
		             });
		     }
		} 
 
 
function download(strData, strFileName, strMimeType) {
    var D = document,
        A = arguments,
        a = D.createElement("a"),
        d = A[0],
        n = A[1],
        t = A[2] || "text/plain";

    //build download link:
    a.href = "data:" + strMimeType + "," + escape(strData);


    if (window.MSBlobBuilder) {
        var bb = new MSBlobBuilder();
        bb.append(strData);
        return navigator.msSaveBlob(bb, strFileName);
    } /* end if(window.MSBlobBuilder) */



    if ('download' in a) {
        a.setAttribute("download", n);
        a.innerHTML = "downloading...";
        D.body.appendChild(a);
        setTimeout(function() {
            var e = D.createEvent("MouseEvents");
            e.initMouseEvent("click", true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
            a.dispatchEvent(e);
            D.body.removeChild(a);
        }, 66);
        return true;
    } /* end if('download' in a) */
    ; //end if a[download]?

    //do iframe dataURL download:
    var f = D.createElement("iframe");
    D.body.appendChild(f);
    f.src = "data:" + (A[2] ? A[2] : "application/octet-stream") + (window.btoa ? ";base64" : "") + "," + (window.btoa ? window.btoa : escape)(strData);
    setTimeout(function() {
        D.body.removeChild(f);
    }, 333);
    return true;
} /* end download library function () */  

function getInstantAllParams(){
        document.getElementById("DataPart").innerHTML = "";
        document.getElementById('ip_opt_div1').style.display = "none";

        document.getElementById('gdate').style.display = "none";
        
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }

       var Selected = document.getElementById('childID').selectedIndex;
        var SelectedOption = document.getElementById('childID').options[Selected].title;
        
      
        
        var url = "getInstantAllParamsData.jsp?&meter_id=" + escape(SelectedOption);

        xmlHttp.onreadystatechange = getInstantaneousData;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
}


function implement()
{
     // alert("in implement");
       $('#instantdatatable').DataTable( {
       dom: 'Bfrtip',
       buttons: [
           'copy', 'csv', 'excel', 'pdf', 'print'
       ]
   } );
}
 function implement1()
{
      //alert("in load datatable");
               $('#loaddatatable').DataTable
               ( 
                   {
                               dom: 'Bfrtip',
                                "bSort": false,
                               //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                               //buttons: ['csv', 'excel', 'print'],
                                       
                               buttons: 
                               [
                                   {
                                	   extend:    'excel',
                                	   title: 'LoadSurvey Data',
                                       titleAttr: 'Excel'
                                   },                               
                                   {
                                       extend: 'csv',
                                       title: 'LoadSurvey Data'
                                   },
                                   {
                                       extend: 'pdf',
                                       title: 'LoadSurvey Data',
                                       orientation: 'landscape'
                                   }
                               ]


                   } 
               );
}
  function implement2()
{
     // alert("in energy datatable");
               $('#energydatatable').DataTable
               ( 
                   {
                               dom: 'Bfrtip',
                               //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                               //buttons: ['csv', 'excel', 'print'],
                                "bSort": false,        
                               buttons: 
                               [
                                   {
                                	   extend:    'excel',
                                	   title: 'Energy Data',
                                       titleAttr: 'Excel'
                                   },                               
                                   {
                                       extend: 'csv',
                                       title: 'Energy Data'
                                   },
                                   {
                                       extend: 'pdf',
                                       title: 'Energy Data',
                                       orientation: 'landscape'
                                   }
                               ]


                   } 
               );
}
function implement3()
{
     // alert("in energy datatable");
               $('#tamperdatatable').DataTable
               ( 
                   {
                               dom: 'Bfrtip',
                               //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                               //buttons: ['csv', 'excel', 'print'],
                                "bSort": false,       
                               buttons: 
                               [
                                   {
                                	   extend:    'excel',
                                	   title: 'Tamper Data',
                                       titleAttr: 'Excel'
                                   },                               
                                   {
                                       extend: 'csv',
                                       title: 'Tamper Data'
                                   },
                                   {
                                       extend: 'pdf',
                                       title: 'Tamper Data',
                                       orientation: 'landscape'
                                   }
                               ]


                   } 
               );
}
function implement4()
{
    
               $('#completedatatable').DataTable
               ( 
                   {
                               dom: 'Bfrtp',
                               //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                               //buttons: ['csv', 'excel', 'print'],
//                                "scrollX": true,
                                "bSort": false,
                               buttons: 
                               [
                      
                                   {
                                	   extend:    'excel',
                                	   title: 'Complete Data',
                                       titleAttr: 'Excel'
                                   },                               
                                   {
                                       extend: 'csv',
                                       title: 'Complete Data'
                                   },
                                   {
                                       extend: 'pdf',
                                       title: 'Complete Data',
                                       orientation: 'landscape'
                                   }
                               ]


                   } 
               );
}
 function implement5()
{
     // alert("in energy datatable");
               $('#outagetable').DataTable
               ( 
                   {
                               dom: 'Bfrtip',
                               //buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                               //buttons: ['csv', 'excel', 'print'],
                                       
                               buttons: 
                               [
                                   {
                                	   extend:    'excel',
                                	   title: 'Outage Data',
                                       titleAttr: 'Excel'
                                   },                               
                                   {
                                       extend: 'csv',
                                       title: 'Outage Data'
                                   },
                                   {
                                       extend: 'pdf',
                                       title: 'Outage Data',
                                       orientation: 'landscape'
                                   }
                               ]


                   } 
               );
}
 
 
 var graphTime = new Array();
 var dateString = new Array();
 var vaExp = new Array();
 var vaImp = new Array();
 var varExp = new Array();
 var varImp = new Array();
 var wExp = new Array();
 var wImp = new Array();
 var meterid = null;
 var loadSurvayDate = null;
 var paramNames = new Array();
 var paramValues = new Array();
 
 
 function getLoadGraph(butnValue)
 {
	 var SelectedOption= document.getElementById('meter_energy_2').value;
     //alert("in getLoadGraph = "+butnValue);
     document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
     xmlHttp = GetXmlHttpObject();
     
     
  //   var Selected = document.getElementById('childID').selectedIndex;
  //var SelectedOption = document.getElementById('childID').options[Selected].value;
//     meterid = document.getElementById('dtr').options[Selected].title;     
   
       meterid =SelectedOption;
       
  

     loadSurvayDate = document.getElementById('loadgraphDate').value;

     var chbMF = document.getElementById("chbMF");
     var url = "getLoadSurvayGraph.jsp?sdate=" + escape(loadSurvayDate) +
             "&meterid=" + escape(SelectedOption)
             + "&EMF=" + (chbMF.checked ? "1" : "0");
    // alert(url);
     if (butnValue == 1)
     {
         xmlHttp.onreadystatechange = getDayLine;
     }
     if (butnValue == 2)
     {
         xmlHttp.onreadystatechange = getDayBarChart;
     }
     xmlHttp.open("GET", url, true);
     xmlHttp.send(null);
 }
 function getMultipleLoadGraph(butnType)
 {

     if (document.getElementById("multiplesDate").value == "" && document.getElementById("multipleeDate").value == "")
     {
         alert("please select fromdate and todate");
     }
     xmlHttp = GetXmlHttpObject();
     var Selected = document.getElementById('dtr').selectedIndex;
     var SelectedOption = document.getElementById('dtr').options[Selected].value;
     meterid = document.getElementById('dtr').options[Selected].title;

     multipleLoads = document.getElementById('multiplesDate').value;
     multipleLoade = document.getElementById('multipleeDate').value;
     if (multipleLoads == multipleLoade) {
         alert("From date and to dates could not be same.Select different dates");
         return 0;
     }
     var chbMF = document.getElementById("chbMF");

     if (butnType == 1)
     {
         var url = "loadMultipleStepGraph.jsp?sdate=" + escape(multipleLoads) +
                 "&edate=" + escape(multipleLoade) + "&meterid=" + escape(SelectedOption) + "&EMF=" + (chbMF.checked ? "1" : "0");
         //alert(url);
         // xmlHttp.onreadystatechange=getMultipleLine;
         // xmlHttp.onreadystatechange=getMultipleStep;
         window.open(url, "mywindow", "location=1,status=1,scrollbars=1, width=1000,height=400");
         window.moveTo(100, 100);
     }
     if (butnType == 2)
     {
         document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
         var url = "loadMultipleGraph.jsp?sdate=" + escape(multipleLoads) + "&edate=" + escape(multipleLoade) + "&meterid=" + escape(SelectedOption) + "";
         //alert(url);
         xmlHttp.onreadystatechange = getMultipleBarChart;
         xmlHttp.open("GET", url, true);
         xmlHttp.send(null);
     }

 }
 function getDayLine()
 {
    
     //document.getElementById('DataPart').innerHTML = "";
     if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
     {       
         var showdata = xmlHttp.responseText;       
         for( i=0;i<showdata.split(";").length;i++)
         {                
             paramNames=paramNames+","+showdata.split(";")[i].substring(0,showdata.split(";")[i].indexOf(","));                 
             paramValues=paramValues+";"+showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));                  
         // alert(showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(",")));
         }         
       
         if(paramValues.split(";")[1].length<2){           
              paramValues={};paramNames={};
             document.getElementById("DataPart").innerHTML= "<h3 align='center'>No data Available.</h3>";
             return;
         }
         if(paramValues.split(";")[1].length>2){                        
             dayStep();
         }
         
     }
 }
 var tickInt;
 function getMultipleLine()
 {
     document.getElementById('DataPart').innerHTML = "";
     paramNames = {};
     paramValues = {};
     if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
     {
         var showdata = xmlHttp.responseText;
         for (i = 0; i < showdata.split(";").length; i++)
         {
             paramNames = paramNames + "," + showdata.split(";")[i].substring(0, showdata.split(";")[i].indexOf(","));

             paramValues = paramValues + ";" + showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));
         }
         //alert("paramNames--"+paramNames.split(","));
         if (paramValues.split(";")[1].substring(1).split(",").length <= 768)
             tickInt = 48;
         if (paramValues.split(";")[1].substring(1).split(",").length > 768)
             tickInt = 48 * 2;
         //alert("tickInte id"+tickInt);

         multipleStep();
     }
 }

 function getMultipleStep()
 {
     document.getElementById('DataPart').innerHTML = "";
     if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
     {
         //alert("in response");
         var showdata = xmlHttp.responseText;
         document.getElementById('DataPart').innerHTML = showdata;


     }
 }
 function getDayBarChart()
 {
     //document.getElementById('DataPart').innerHTML = "";
     if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
     {
         var showdata = xmlHttp.responseText;
         for (j = 0; j < showdata.split(";").length; j++)
         {
             paramNames = paramNames + "," + showdata.split(";")[j].substring(0, showdata.split(";")[j].indexOf(","));
             paramValues = paramValues + ";" + showdata.split(";")[j].substring(showdata.split(";")[j].indexOf(","));
         }

         if (paramValues.split(";")[1].length < 2) {
             paramValues = {};
             paramNames = {};
             document.getElementById("DataPart").innerHTML = "<h3 align='center'>No data Available.</h3>";
             return;
         }
         if (paramValues.split(";")[1].length > 2) {
             dayBar();
         }
     }
 }

 function getMultipleBarChart()
 {
     //document.getElementById('DataPart').innerHTML = "";
     paramNames = {};
     paramValues = {};
     if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
     {
         var showdata = xmlHttp.responseText;
         for (i = 0; i < showdata.split(";").length; i++)
         {
             paramNames = paramNames + "," + showdata.split(";")[i].substring(0, showdata.split(";")[i].indexOf(","));
             paramValues = paramValues + ";" + showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));
         }
         if (paramValues.split(";")[1].length < 6) {
             paramValues = {};
             paramNames = {};
             document.getElementById("DataPart").innerHTML = "<h3 align='center'>No data Available.</h3>";
             return;
         }
         if (paramValues.split(";")[1].length > 6) {
             multipleBar();
         }

     }
 }
 function dayLine()
 {
     var chart;
     $(document).ready(function () {
         chart = new Highcharts.Chart({
             chart: {
                 renderTo: 'DataPart',
                 defaultSeriesType: 'line',
                 marginRight: 100,
                 marginBottom: 55,
                 width: window.size,
                 borderColor: "#4572A7",
                 borderRadius: 15,
                 borderWidth: 1,
                 margin: [80, 80, 160, 160]
             },
             title: {
                 text: 'Line Graph for <br/>' + meterid + ' on  ' + loadSurvayDate + '',
                 x: -20 //center
             },
             subtitle: {
                 text: '',
                 x: -20
             },
             xAxis: {
                 gridLineWidth: 1,
                 lineColor: '#000',
                 tickColor: '#000',
                 tickWidth: 2,
                 labels: {
                     enabled: false
                 },
                 title: {
                     text: 'Time(hh:mm:ss)'

                 },
                 categories: graphTime
             },
             yAxis: {
                 minorTickInterval: 'auto',
                 lineColor: '#000',
                 lineWidth: 1,
                 tickWidth: 1,
                 tickColor: '#000',
                 labels: {
                     style: {
                         color: '#000',
                         font: '11px Trebuchet MS, Verdana, sans-serif'
                     }
                 },
                 title: {
                     text: 'values'
                 },
                 plotLines: [{
                         value: 0,
                         width: 1,
                         color: '#808080'
                     }]
             },
             tooltip: {
                 formatter: function () {
                     return '<b>' + this.series.name + '</b><br/>' +
                             this.x + '(hh:mm:ss)' + '</b><br/> ' + this.y + '';
                 }
             },
             legend: {
                 layout: 'vertical',
                 align: 'right',
                 verticalAlign: 'top',
                 x: -10,
                 y: 100,
                 borderWidth: 0
             },
             series: [

                 {
                     name: 'VA(E)',
                     data: vaExp
                 }, {
                     name: 'VA(I)',
                     data: vaImp,
                     step: true
                 }, {
                     name: 'VAr(E)',
                     data: varExp
                 },
                 {
                     name: 'VAr(I)',
                     data: varImp
                 },
                 {
                     name: 'W(E)',
                     data: wExp
                 },
                 {
                     name: 'W(I)',
                     data: wImp
                 },
                 , ],
             exporting: {
                 enabled: true
             }
         });


     });

 }
 function dayStep()
 {      
     //alert("paramNames = "+paramNames);
     //alert("paramValues = "+paramValues);
     var seriesData = [];  //constructor in series to add multiple series at a time
     for (var i = 2; i < paramNames.split(",").length; i++){       
         var dataSeries=new Array();
         for( j=0;j<paramValues.split(";")[i].substring(1).split(",").length;j++)
         {
             dataSeries[j]=+paramValues.split(";")[i].substring(1).split(",")[j];
         }
   
         seriesData.push({
             name: paramNames.split(",")[i],      // adding multiple series(params) values to graph
             data: dataSeries
             //step:true
         });
                                  
     }
     // Create the chart
     var chart;
     //alert("meterid "+meterid+" loadSurvayDate = "+loadSurvayDate);
     document.getElementById('DataPart').style.display = "";
     
         chart = new Highcharts.Chart({
             chart: {
                 renderTo: 'DataPart',
                 width: window.size,
                 marginRight: 100,
                 marginBottom: 55,
                 width:window.size,
                 borderColor: "#4572A7",
                 borderRadius: 15,
                 borderWidth: 1,
                 margin: [80, 0, 0,80],
                 zoomType: 'x'
                                         
             },
             title: {
//                 text: 'Graph for '+meterid+' on  '+loadSurvayDate+'',
//                 x: -20 //center
             },
             plotOptions:{
                 lineWidth: 80

             },
             credits:{
                 enabled:false
             },
             scrollbar: {
                 enabled: true
             },
                                      
             xAxis: {
                 tickInterval:3,
 				        
                 title: {
                     text: 'Time(hh:mm)'

                 },
                 categories: paramValues.split(";")[1].substring(1).split(",")
//                 min: 0,
//                 max:24
                                                
             },
             yAxis: {
                 title: {
                     text: 'Values'
                 }
             },
             tooltip: {
            	    shared: true,
            	    split: false,
            	    enabled: true
             },
             legend: {
                 layout: 'horizontal',
                 backgroundColor: '#FFFFFF',
                 align: 'left',
                 verticalAlign: 'top',
                 x: 1,
                 y: 20,
                 floating: true,
                 shadow: true
             },

                                        
             series: seriesData
         });
     
     paramNames={};
     paramValues={};
 }
 function multipleLine()
 {
     var chart;
     $(document).ready(function () {
         chart = new Highcharts.Chart({
             chart: {
                 renderTo: 'DataPart',
                 defaultSeriesType: 'line',
                 marginRight: 100,
                 marginBottom: 55,
                 width: window.size,
                 borderColor: "#4572A7",
                 borderRadius: 15,
                 borderWidth: 1,
                 margin: [80, 80, 160, 160]
             },
             title: {
                 text: 'Line Graph for <br/>' + meterid + ' from  ' + multipleLoads + ' to ' + multipleLoade,
                 x: -20 //center
             },
             subtitle: {
                 text: '',
                 x: -20
             },
             xAxis: {
                 gridLineWidth: 1,
                 lineColor: '#000',
                 tickColor: '#000',
                 tickWidth: 2,
                 labels: {
                     enabled: false
                 },
                 title: {
                     text: 'Time(hh:mm:ss)'

                 },
                 categories: dateString
             },
             yAxis: {
                 minorTickInterval: 'auto',
                 lineColor: '#000',
                 lineWidth: 1,
                 tickWidth: 1,
                 tickColor: '#000',
                 labels: {
                     style: {
                         color: '#000',
                         font: '11px Trebuchet MS, Verdana, sans-serif'
                     }
                 },
                 title: {
                     text: 'values'
                 },
                 plotLines: [{
                         value: 0,
                         width: 1,
                         color: '#808080'
                     }]
             },
             tooltip: {
                 formatter: function () {
                     return '<b>' + this.series.name + '</b><br/>' +
                             this.x + '(dd-mm-yyyy)' + '</b><br/> ' + this.y + '';
                 }
             },
             legend: {
                 layout: 'vertical',
                 align: 'right',
                 verticalAlign: 'top',
                 x: -10,
                 y: 100,
                 borderWidth: 0
             },
             series: [{
                     name: 'VA(E)',
                     data: vaExp
                 }, {
                     name: 'VA(I)',
                     data: vaImp
                 }, {
                     name: 'VAr(E)',
                     data: varExp
                 },
                 {
                     name: 'VAr(I)',
                     data: varImp
                 },
                 {
                     name: 'W(E)',
                     data: wExp
                 },
                 {
                     name: 'W(I)',
                     data: wImp
                 },
                 , ],
             exporting: {
                 enabled: true
             }
         });


     });
 }

 function dayBar()
 {
     console.log(paramNames);
     console.log(paramValues);
     console.log("times : "+paramValues.split(";")[1].substring(1).split(",")[0]);
     var seriesDataBar = [];  //constructor in series to add multiple series at a time
     for (var i = 2; i < paramNames.split(",").length; i++) {
         var dataSeries = new Array();
         for (j = 0; j < paramValues.split(";")[i].substring(1).split(",").length; j++)
         {
             dataSeries[j] = +paramValues.split(";")[i].substring(1).split(",")[j];
         }

         seriesDataBar.push({name: paramNames.split(",")[i], // adding multiple series(params) values to graph
             data: dataSeries,
             step: true

         });

     }
     var chart;
     
         chart = new Highcharts.Chart({
             chart: {
                 renderTo: 'DataPart',
                 defaultSeriesType: 'column',
                 marginRight: 100,
                 marginBottom: 55,
                 width: window.size,
                 borderColor: "#4572A7",
                 borderRadius: 15,
                 borderWidth: 1,
                 margin: [80, 0, 0, 80],
                 zoomType: 'x'

             },
             title: {
                 text: 'Bar Chart for ' + meterid + ' on  ' + loadSurvayDate + ''
             },

             xAxis: {
                  tickInterval:3,
                 title: {
                     text: 'Time(hh:mm)'


                 },
                 categories: paramValues.split(';')[1].substring(1).split(',')
//                 min: 0,
//                 max: 10
             },
             credits:{
               enabled:false  
             },
             yAxis: {
                 min: 0,
                 title: {
                     text: 'Values'
                 }
             },
             legend: {
                 layout: 'horizontal',
                 backgroundColor: '#FFFFFF',
                 align: 'left',
                 verticalAlign: 'top',
                 x: 1,
                 y: 20,
                 floating: true,
                 shadow: true
             },
             plotOptions: {
                 column: {
                     pointPadding: 1,
                     pointWidth: 5,
                     borderWidth: 0
                 }
             },
             scrollbar: {
                 enabled: true
             },

             tooltip: {
            	 shared: true,
            	    split: false,
            	    enabled: true
             },

             series: seriesDataBar
         });


    
     paramNames = {};
     paramValues = {};
 }
 function multipleBar()
 {
     //alert("tickInte id"+tickInt);
     var seriesData = [];  //constructor in series to add multiple series at a time
     for (var i = 2; i < paramNames.split(",").length; i++) {
         var dataSeries = new Array();
         for (j = 0; j < paramValues.split(";")[i].substring(1).split(",").length; j++)
         {
             dataSeries[j] = +paramValues.split(";")[i].substring(1).split(",")[j];
         }

         seriesData.push({
             name: paramNames.split(",")[i], // adding multiple series(params) values to graph
             data: dataSeries,
             step: true

         });

     }
     var chart;
     $(document).ready(function () {
         chart = new Highcharts.Chart({
             chart: {
                 renderTo: 'DataPart',
                 defaultSeriesType: 'column',
                 marginRight: 100,
                 marginBottom: 55,
                 width: window.size,
                 borderColor: "#4572A7",
                 borderRadius: 15,
                 borderWidth: 1,
                 margin: [80, 80, 160, 160]

             },
             title: {
                 text: 'Graph for' + meterid + ' from  ' + multipleLoads + ' to ' + multipleLoade
             },

             xAxis: {

                 title: {
                     text: 'Date(dd-mm-yyyy)'
                 },
                 categories: paramValues.split(";")[1].substring(1).split(","),
                 min: 0,
                 max: 10
             },
             yAxis: {
                 min: 0,
                 title: {
                     text: 'Values'
                 }
             },
             legend: {
                 layout: 'horizontal',
                 backgroundColor: '#FFFFFF',
                 align: 'left',
                 verticalAlign: 'top',
                 x: 1,
                 y: 20,
                 floating: true,
                 shadow: true
             },
             plotOptions: {
                 column: {
                     pointPadding: 1,
                     pointWidth: 5,
                     borderWidth: 0
                 }
             },
             scrollbar: {
                 enabled: true
             },

             tooltip: {
                 formatter: function () {
                     return '<b>' + this.x + '(hh:mm:ss)' + '</b><br/> ' + this.y + '';
                 }
             },

             series: seriesData
         });


     });
     paramNames = {};
     paramValues = {};

 }
 function multipleStep()
 {

     var multiSeriesData = [];  //constructor in series to add multiple series at a time
     //alert(paramNames);
     for (var i = 2; i < paramNames.split(",").length; i++) {
         var dataSeries = new Array();
         for (j = 0; j < paramValues.split(";")[i].substring(1).split(",").length; j++)
         {
             dataSeries[j] = +paramValues.split(";")[i].substring(1).split(",")[j];
         }

 //alert(dataSeries);
         multiSeriesData.push({name: paramNames.split(",")[i], // adding multiple series(params) values to graph
             data: dataSeries,
             step: true

         });

     }
     var chart;

     $(document).ready(function () {
         chart = new Highcharts.StockChart({
             chart: {
                 renderTo: 'DataPart',
                 //defaultSeriesType: 'line',
                 width: window.size

             },

             title: {
                 text: 'Graph for' + meterid + ' from  ' + multipleLoads + ' to ' + multipleLoade,
                 x: -20 //center
             },

             xAxis: {
                 tickInterval: tickInt,
                 //tickInterval:1,

                 categories: paramValues.split(";")[1].substring(1).split(","),
                 max: 720,
                 labels:
                         {
                             enabled: true
                         }


             },
             yAxis: {
                 title: {
                     text: 'Values'
                 },

             },
             plotOptions: {
                 line: {
                     lineWidth: 0.5,
                     marker: {
                         enabled: false
                     }
                 }
             },
             tooltip: {
                 formatter: function () {
                     return '<b>' + this.x + '</b><br/> ' + this.y + '';
                 }
             },
             legend: {
                 layout: 'vertical',
                 align: 'right',
                 verticalAlign: 'middle',
                 x: 10,
                 y: -10,
                 shadow: true,
                 borderWidth: 1
             },
             scrollbar: {enabled: true},
             series: multiSeriesData
         });
     });
     paramNames = {};
     paramValues = {};
 }
 function exportReports(type) {
     if (type == "LS") {
         var lsdate = document.getElementById("loadsDate").value;
         var ledate = document.getElementById("loadeDate").value;
         var chbMF = document.getElementById("chbMF");
         var Selected = document.getElementById('dtr').selectedIndex;
         var SelectedOption = document.getElementById('dtr').options[Selected].title;
         var url = "exportLoadsurvayData.jsp";
         url = url + "?fromDate=" + escape(lsdate) + "&toDate=" + escape(ledate) + "&meter_no=" + escape(SelectedOption) + "&EMF=" + (chbMF.checked ? "1" : "0");
         // alert(url);
         window.location = url;
     } else if (type == "InstData") {
         var lsdate = document.getElementById("sDate").value;
         var ledate = document.getElementById("eDate").value;
         var chbEMF = document.getElementById("AEMF");
         var Selected = document.getElementById('dtr').selectedIndex;
         var SelectedOption = document.getElementById('dtr').options[Selected].value;
         var url = "exportInstantData.jsp";
         url = url + "?fromDate=" + escape(lsdate) + "&toDate=" + escape(ledate) + "&meter_no=" + escape(SelectedOption) + "&EMF=" + (chbEMF.checked ? "1" : "0");
         //url=url+"?fromDate="+escape(lsdate)+"&toDate="+escape(ledate)+"&meter_no="+escape(SelectedOption)+"&EMF=1";
         // alert(url);
         window.location = url;
     }
 }


 function getAlerts(actionId) {
		clear();
		hideDashboardDiv();
		
		$.ajax( {
	        url:'MeterData/ViewAlerts.epx?actionId='+actionId,
	        beforeSend:function(){
	        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
	        },
	        success:function(data) {
	           $('#datapart').html(data);
	           //callDataTable('table1');
	          
	        }
	     });
	}

 
 function getModemAlert(meter){
		var alertdate=document.getElementById("graphDate").value   
		$.ajax( {
	        url:'ViewAlertsList.jsp?meter='+meter+'&alertdate='+alertdate,
	        beforeSend:function(){
	        	document.getElementById("viewmodemalert").innerHTML=document.getElementById("loader").innerHTML;
	        },
	        success:function(data) {
	           $('#viewmodemalert').html(data);
	           //callDataTable('example');
	          
	        }
	     });
	}

 
 function getHierarchyFor360(titleId){
		 //alert("titleId --> "+titleId);
			//$("#feederSel").empty();
			//$("#childsSel").empty();
			$.ajax( {
		        url:'getHierarchyFor360.jsp?userHid='+titleId,
		        success:function(data) {        	
		           $('#hierarchy').html(data);
		           
		        }
		     });
		}
 function getHierarchyFor360_reports(titleId){
	 
		$.ajax( {
	        url:'getHierarchyFor360_reports.jsp?userHid='+titleId,
	        success:function(data) {        	
	           $('#hierarchy').html(data);
	           
	        }
	     });
	}	 
 function getHierarchyFor360_analysis(titleId,report){
	 
		$.ajax( {
	        url:'getHierarchyFor360_analysis.jsp?userHid='+titleId+'&report='+report,
	        success:function(data) {        	
	           $('#hierarchy').html(data);
	           
	        }
	     });
	}	 
	 
	 
	 
	 function getHierarchyFor361(titleId){
			// alert("titleId --> "+titleId);
				//$("#feederSel").empty();
				//$("#childsSel").empty();
				$.ajax( {
			        url:'getHierarchyFor361.jsp?userHid='+titleId,
			        success:function(data) {        	
			           $('#hierarchy').html(data);
			           
			        }
			     });
			}
	 
	 function getHierarchyFor362(titleId){
			// alert("titleId --> "+titleId);
				$("#feederSel").empty();
				$("#childsSel").empty();
				$.ajax( {
			        url:'getHierarchyFor362.jsp?userHid='+titleId,
			        success:function(data) {        	
			           $('#hierarchy').html(data);
			           
			        }
			     });
			}
	 
	 

	 function getHierarchyFor363(titleId){
			// alert("titleId --> "+titleId);
				//$("#feederSel").empty();
				//$("#childsSel").empty();
				$.ajax( {
			        url:'getHierarchyFor363.jsp?titleId='+titleId,
			        success:function(data) {        	
			           $('#hierarchy').html(data);
			           
			        }
			     });
			}
	 
	 
	 
	 

	 function getMeters(value,divid){
	 	//alert(value+" ------------ "+divid);
	 	var levelId=document.getElementById("levelID").value=value;
	 	var level=$("#level").val();
	 	var install_type=$("#install_type").val();
	 	var search_by=$("#search_by").val();
	 	$.ajax( {
	        // url:'getMetersByssID.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level,
	         url:'getMetersByssID.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level+"&search_by="+search_by,
	         success:function(res) {
	         	 $('#'+divid).empty();
	         	$('#'+divid).html(res);
	            $('#childID').chosen();
	            
	         }
	      });
	 }
	 function getMeters_360(value,divid){
		 	//alert(value+" ------------ "+divid);
		 	var levelId=document.getElementById("levelID").value=value;
		 	var level=$("#level").val();
		 	var install_type=$("#install_type").val();
		 	var search_by=$("#search_by").val();
		 	$.ajax( {
		        // url:'getMetersByssID.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level,
		         url:'getMetersByssID_360.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level+"&search_by="+search_by,
		         success:function(res) {
		         	 $('#'+divid).empty();
		         	$('#'+divid).html(res);
		            $('#childID').chosen();
		            
		         }
		      });
		 }
	 function getMeters_analysis(value,divid,report){
		 	//alert(value+" ------------ "+divid);
		 	var levelId=document.getElementById("levelID").value=value;
		 	var level=$("#level").val();
		 	var install_type=$("#install_type").val();
		 	var search_by=$("#search_by").val();
		 	$.ajax( {
		        // url:'getMetersByssID.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level,
		         url:'getMetersByssID_analysis.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level+"&search_by="+search_by+'&report='+report,
		         success:function(res) {
		         	 $('#'+divid).empty();
		         	$('#'+divid).html(res);
		            $('#childID').chosen();
		            
		         }
		      });
		 }	 
function getMetersBySearchCriteria(searchBy,reportType){
	var levelId=document.getElementById("levelID").value;
 	var level=$("#level").val();
 	var install_type=$("#install_type").val();
 	var search_by=$("#search_by").val();
 	var urlstr="";
 	if(reportType=="meterdata") urlstr='getMetersByssID.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level+"&search_by="+search_by;
 	if(reportType=="alerts") urlstr='getMetersByssID1.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level+"&search_by="+search_by;
 	if(reportType=="diag") urlstr='getMetersByssID2.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level+"&search_by="+search_by;
 	if(reportType=="ondemand") urlstr='getMetersForOnDemand.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level+"&search_by="+search_by;
 	if(reportType=="360") urlstr='getMetersByssID_360.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level+"&search_by="+search_by;
 	$.ajax( {       
         url:urlstr,
         success:function(res) {
         	 $('#meterSel').empty();
         	$('#meterSel').html(res);
         	if(reportType=="ondemand")
         		callDataTableForOD("childID");
         	else
              $('#childID').chosen();
            
         }
      });
}
	 function getMeters1(value,divid){
	 	//alert(value+" getmETERS "+divid);
	 	var levelId=document.getElementById("levelID").value=value;
	 	var level=document.getElementById("level").value;
	 	var install_type=$("#install_type").val();
	 	var search_by=$("#search_by").val();
	 	
	 	$.ajax( {
	        // url:'getMetersByssID.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level,
	         url:'getMetersByssID1.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level+"&search_by="+search_by,
	         success:function(data) {  
	         	 $('#'+divid).empty();
	            $('#'+divid).html(data);
	            $('#childID').chosen();
	            
	         }
	      });
	 }



	 function getMeters2(value,divid){
	 	//alert(value+" getmETERS "+divid);
	 	var levelId=document.getElementById("levelID").value=value;
	 	var level=document.getElementById("level").value;
	 	var install_type=$("#install_type").val();
	 	var search_by=$("#search_by").val();
	 	
	 	$.ajax( {
	        // url:'getMetersByssID.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level,
	         url:'getMetersByssID2.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level+"&search_by="+search_by,
	         success:function(data) {  
	         	 $('#'+divid).empty();
	            $('#'+divid).html(data);
	            $('#childID').chosen();
	            
	         }
	      });
	 }






	 function getMeters3(value,divid){
	 	//alert(value+" getmETERS "+divid);
	 	var levelId=document.getElementById("levelID").value=value;
	 	//var level=document.getElementById("level").value=level;
	 	var install_type=$("#install_type").val();
	 	var level=3;
	 	
	 	$.ajax( {
	        // url:'getMetersByssID.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level,
	         url:'getMetersByssID3.jsp?levelId='+levelId+'&install_type='+install_type+'&level='+level,
	         success:function(data) {  
	         	 $('#'+divid).empty();
	            $('#'+divid).html(data);
	            $('#childID').chosen();
	            
	         }
	      });
	 }
	 
	 function getModemDiag(actionId){
			
			clear();
			hideDashboardDiv();
			$.ajax( {
		        url:'MeterData/ViewDiagnosis.epx?actionId='+actionId,
		        beforeSend:function(){
		        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
		        },
		        success:function(data) {
		           $('#datapart').html(data);
		          // callDataTable('table1');
		          
		        }
		     });
		}


		function getBilling(actionId){
			//alert(actionId);
			clear();
			hideDashboardDiv();
			$.ajax( {
		        url:'MeterData/ResetEnergies.epx?actionId='+actionId,
		        beforeSend:function(){
		        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
		        },
		        success:function(data) {
		           $('#datapart').html(data);
		           
		        }
		     });
		}


		function getModemDiagnosisList1(SlectedMeter){
			document.getElementById("meter").value = SlectedMeter;
			//alert(document.getElementById("meter").value);
		}	
		function getModemDiagnosisList2(SlectedMeter){
			document.getElementById("meter").value = SlectedMeter;
			//alert(document.getElementById("meter").value);
		}
		function getEnergies(meter){
			var enrgymonth=document.getElementById("month").value   
			//alert(enrgymonth);
			$.ajax( {
		        url:'ViewEnergyList.jsp?meter='+meter+'&enrgymonth='+enrgymonth,
		        beforeSend:function(){
		        	document.getElementById("viewmodemalert").innerHTML=document.getElementById("loader").innerHTML;
		        },
		        success:function(data) {
		           $('#viewmodemalert').html(data);
		          // callDataTable('table1');
		        }
		     });
		}


		var rph = new Array();
		var yph = new Array();
		var bph = new Array();
		var graphTime = new Array();
		var kwh = new Array();

		function getModemDiagnosisList(e_opt) {
			xmlHttp = GetXmlHttpObject();
			var alertdate=document.getElementById("graphDate").value  
			var meter=document.getElementById("meter").value  
			//alert(meter+" "+alertdate);
			if(alertdate==null||alertdate.length<1||meter==null||meter.length<1){
				alert("Please select Meter & Date");
				return false;
			}

		    if (e_opt == 1)
		    {
		       
		        var url = "ViewDiagnosisList.jsp?meter="+meter+"&alertdate="+alertdate+"";
		        
		        xmlHttp.onreadystatechange = getDiagTable;

		    }
		    if (e_opt == 2)
		    {
		        var url = "ViewDiagnosisGraph.jsp?meter="+meter+"&alertdate="+alertdate+"";
		    	xmlHttp.onreadystatechange = getDiagGraph;

		    }
		    xmlHttp.open("GET", url, true);
		    xmlHttp.send(null);
		}

		function getDiagTable(){
		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
		{
		    var showdata = xmlHttp.responseText;
		  //  alert(showdata);
		    document.getElementById("viewmodemalert").innerHTML = showdata;
		    $('#table1').DataTable({ paging: true});
		}
		}


		function getDiagGraph(){
			
		    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
		    {
		        var showdata = xmlHttp.responseText;
		       /* gtime = showdata.substring(showdata.indexOf("time") + 5, showdata.indexOf("rph") - 1);
		         alert("gtime "+gtime)
		        gtime = gtime.replace(";", "");
		        alert("gtime  "+gtime)
		        graphTime = gtime.split(",");*/

		        rc = showdata.substring(showdata.indexOf("rph") + 4, showdata.indexOf("yph"));
		       // alert("rc  "+rc)
		        rph = rc.split(",");
		       // alert(rph);
		        for (i = 0; i < rph.length; i++)
		        {
		            rph[i] = +rph[i];
		        }
		        yc = showdata.substring(showdata.indexOf("yph") + 4, showdata.indexOf("bph"));
		       // alert("yc  "+yc);
		        yph = yc.split(",");
		        //alert(yph);
		        for (j = 0; j < yph.length; j++)
		        {
		            yph[j] = +yph[j];
		        }
		        /*bc = showdata.substring(showdata.indexOf("bph") + 4);
		        alert("bc  "+bc);
		        bph = bc.split(",");
		        for (k = 0; k < bph.length; k++)
		        {
		            bph[k] = +bph[k];
		        }*/
		       ModemGraph();
		    }	
		}

		function ModemGraph(){
			

			   chart = new Highcharts.Chart({
			           chart: {
			               renderTo:'viewmodemalert',
			               zoomType: 'x',
			               borderColor: "#4572A7",
			               borderRadius: 15,
			               borderWidth: 1,
			               margin: [80, 0, 30, 80]
			           },
			           title: {
			               text: 'Modem Diagnosis Graph'
			           },
			           subtitle: {
			               text: document.ontouchstart === undefined ?
			                       'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
			           },
			           xAxis: {
			               gridLineWidth: 1,
			               lineColor: '#000',
			               tickColor: '#000',
			               tickInterval:3,
			               labels: {
			                   enabled: false
//			                   style: {
//			                       color: '#000',
//			                       font: '11px Trebuchet MS, Verdana, sans-serif'
//			                   }
			               },
			               title: {
			                  // text: 'Time(hh:mm:ss)'

			               },
			               categories: graphTime
			               
			           },
			           yAxis: {
			               title: {
			                   text: 'Temperature (t)'
			               }
			           },
			           tooltip: {
			               formatter: function () {
			                   return '<b>' + this.series.name + '</b><br/>' +
			                           this.x +  '</b><br/> ' + this.y + '';
			               }
			           },
			           legend: {
			               enabled: false
			           },
			           plotOptions: {
			               area: {
			                   fillColor: {
			                       linearGradient: {
			                           x1: 0,
			                           y1: 0,
			                           x2: 0,
			                           y2: 1
			                       },
			                       stops: [
			                           [0, Highcharts.getOptions().colors[0]],
			                           [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
			                       ]
			                   },
			                   marker: {
			                       radius: 2
			                   },
			                   lineWidth:0.5,
			                   states: {
			                       hover: {
			                           lineWidth: 1
			                       }
			                   },
			                   threshold: null
			               }
			           },
			           legend: {
			               layout: 'horizontal',
			               backgroundColor: '#FFFFFF',
			               align: 'left',
			               verticalAlign: 'top',
			               x: 1,
			               y: 10,
			               floating: true,
			               shadow: true
			           },
			           credits: {
			               enabled: false
			           },
			           series: [{
			               name: 'Network Strength',
			               data: rph,
			               color:'#5fa2e2'
			           }, {
			               name: 'Temperature',
			               data: yph,
			               color: '#e2d75f'
			           },]
			       });

			
			
		}
	 
		//----------------------------------------------------new one for multiple days graph --------------------
		function getMultipleLoadDate()
		{
			
			loadDate(6);
		  document.getElementById("DataPart").innerHTML = "";
		  document.getElementById('cont-1-1').style.display = "none";   	
			  document.getElementById('cont-2-1').style.display = "none";
			  document.getElementById('cont-3-1').style.display = "none";   	
			  document.getElementById('cont-3-1-1').style.display = "block";
  	
			  document.getElementById('cont-4-1').style.display = "none";
			  document.getElementById('cont-5-1').style.display = "none";
			  document.getElementById('cont-6-1').style.display = "none";
		  document.getElementById('cont-3-3').style.display = "block";
		  var loadmeter1=document.getElementById("meter").value;
		  getMultipleLoadGraph(1);
		}
		function getMultipleLoadGraph(butnType)
			 {
		var SelectedOption= document.getElementById('meter').value;  
		 meterid =SelectedOption;      
		 xmlHttp = GetXmlHttpObject();
		 
		 var chbMF = document.getElementById("chbMF");
		 multipleLoads = document.getElementById('multiplesDate').value;
		 multipleLoade = document.getElementById('multipleeDate').value;
		 
		 var url = "loadMultipleStepGraph.jsp?sdate=" + escape(multipleLoads) +
		 "&edate=" + escape(multipleLoade) + "&meterid=" + escape(SelectedOption)+ "&EMF=" + (chbMF.checked ? "1" : "0");
		 
		 //alert(url);
		     xmlHttp.onreadystatechange = getMultiAxisGraph1;
		     xmlHttp.open("GET", url, true);
		     xmlHttp.send(null);
		 

		 document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;

		}

			
			
			
			function getMultiAxisGraph1()
			 {
			     //document.getElementById('DataPart').innerHTML = "";
			     if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
			     {
			    	 
			    	 
			    	 
			    	 var showdata = xmlHttp.responseText;       
			         for( i=0;i<showdata.split(";").length;i++)
			         {                
			             paramNames=paramNames+","+showdata.split(";")[i].substring(0,showdata.split(";")[i].indexOf(","));                 
			             paramValues=paramValues+";"+showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));                  
			         // alert(showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(",")));
			         }         
			        
			         if(paramValues.split(";")[1].length<2){           
			          paramValues={};paramNames={};
			             document.getElementById("DataPart").innerHTML= "<h3 align='center'>No data Available.</h3>";
			             return;
			         }
			         if(paramValues.split(";")[1].length>2){    
			        	 getMultiAxisGraph();
			          
			         }
			    	 
			    	 

			     }
			 } 

			 function getMultiAxisGraph()
			 {
				
				     var meterid=paramValues.split("#")[1];

				     var lsdate=paramValues.split("#")[2];

				     var ledate=paramValues.split("#")[3];

				     
				     
				var dates= []
				// var dates=new Array();
				 for( k=0;k<paramValues.split(";")[1].substring(1).split(",").length;k++)
			     {
					 //dates[k]=+paramValues.split(";")[k].substring(1).split(",")[k];
					 
					 dates.push(paramValues.split(";")[1].substring(1).split(",")[k]);
			     }
				 
				    var seriesData = [];  //constructor in series to add multiple series at a time
				    
				     for (var i = 2; i < paramNames.split(",").length; i++){       
				         var dataSeries=new Array();
				         for( j=0;j<paramValues.split(";")[i].substring(1).split(",").length;j++)
				         {
				             dataSeries[j]=+paramValues.split(";")[i].substring(1).split(",")[j];
				         }
				   
				     //    alert(dataSeries);
				         
				        
				         
				         var test=paramNames.split(",")[i].toUpperCase();
				         var count=1;
				         
				         
				         if(test==="VR" || test==="VY" || test==="VB" || test==="LR" || test==="LY" || test==="LB"){
				        	 seriesData.push({
					             name: paramNames.split(",")[i],   
					             type: 'spline',
					             data: dataSeries,
					             yAxis: 2,
					             step:true
					         });
				         }else{
				        	 if(count<=2){
				        	 seriesData.push({
					             name: paramNames.split(",")[i], 
					             yAxis: 1,
					             type: 'column',
					             data: dataSeries
					         });
				         }
				        	 count++;
				         }
				         
				         
				     }
				 
				 
				  Highcharts.chart('DataPart', {
					  title: {
						    text: 'Multi Axis Graph',
						    align: 'center'
						  },
						  credits: {
						        enabled: false
						    },
					  chart: {
					    zoomType: 'xy'
					  },
					 
					  
					    exporting: {
				            buttons: {
				                customButton: {
				                    x: -62,
				                    onclick: function () {
				                        alert('Clicked');
				                    },
				                    symbol: 'circle'
				                }
				            }
				        },
				        
					  xAxis: [{
					    categories: dates,
					    crosshair: true
					  }],
					  plotOptions: {
					        series: {
					            cursor: 'pointer',
					            point: {
					                events: {
					                    click: function () {
					                    	
					                       // alert('Category: ' + this.category + ', value: ' + this.y);
					                        
					                        $.ajax({url: "loadMultipleStepGraph2.jsp?sdate="+escape(this.category)+"&meterid="+escape(meterid)+"&lsdate="+escape(lsdate)+"&ledate="+escape(ledate), success: function(responseTxt, statusTxt, xhr){
					                            
					                        	if(statusTxt == "success"){

					                           	
					                           	  var showdata = responseTxt;       
					                                for( i=0;i<showdata.split(";").length;i++)
					                                {                
					                                    paramNames=paramNames+","+showdata.split(";")[i].substring(0,showdata.split(";")[i].indexOf(","));                 
					                                    paramValues=paramValues+";"+showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));                  
					                                // alert(showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(",")));
					                                }         
					                               
					                                if(paramValues.split(";")[1].length<2){           
					                                 paramValues={};paramNames={};
					                                    document.getElementById("DataPart").innerHTML= "<h3 align='center'>No data Available.</h3>";
					                                    return;
					                                }
					                                if(paramValues.split(";")[1].length>2){    
					                               	 getMultiAxisGraph2();
					                                 
					                                }

					                        	}

					                          }});
					                    }
					                }
					            }
					        }
					    },

					  

					  yAxis: [{ // Primary yAxis
					    labels: {
					     
					      style: {
					        color: Highcharts.getOptions().colors[2]
					      }
					    },
					    title: {
					    
					      style: {
					        color: Highcharts.getOptions().colors[2]
					      }
					    },
					   
					    opposite: true

					  }, { // Secondary yAxis
					    gridLineWidth: 0,
					    title: {
					     
					      style: {
					        color: Highcharts.getOptions().colors[0]
					      }
					    },
					    labels: {					    
					      style: {
					        color: Highcharts.getOptions().colors[0]
					      }
					    },
					    tickInterval: 20

					  }, { // Tertiary yAxis
					    gridLineWidth: 0,
					    title: {
					     
					      style: {
					        color: Highcharts.getOptions().colors[1]
					      }
					    },
					    labels: {
					    
					      style: {
					        color: Highcharts.getOptions().colors[1]
					      }
					    },
					    
					    opposite: true
					  }],
					  
					  
					  tooltip: {
					    shared: true
					  },
					/*  legend: {
					    layout: 'horizontal',
					    backgroundColor: '#FFFFFF',
					    align: 'left',
					    x: 1,
					    verticalAlign: 'top',
					    y: 12,
			            shadow: true,
					    floating: true,
					    backgroundColor:
					      Highcharts.defaultOptions.legend.backgroundColor || // theme
					      'rgba(255,255,255,0.25)'
					  },*/
					  
				legend: {
					        layout: 'vertical',
					        align: 'right',
					        verticalAlign: 'top',
					        y: 3,
					        padding: 3,
					        itemMarginTop: 5,
					        itemMarginBottom: 5,
					        itemStyle: {
					        lineHeight: '5px'
					        }
					    },
				
					  
					  series:seriesData,
					
					
					  responsive: {
					    rules: [{
					      condition: {
					        maxWidth: 500
					      },
					      chartOptions: {
					        legend: {
					          floating: false,
					          layout: 'horizontal',
					          align: 'center',
					          verticalAlign: 'bottom',
					          x: 0,
					          y: 0
					        },
					        yAxis: [{
					          labels: {
					            align: 'right',
					            x: 0,
					            y: -6
					          },
					          showLastLabel: false
					        }, {
					          labels: {
					            align: 'left',
					            x: 0,
					            y: -6
					          },
					          showLastLabel: false
					        }, {
					          visible: false
					        }]
					      }
					    }]
					  }
					});	
				  paramNames = {};
				  paramValues = {};
			}
			 
			 
			 
			 function getMultiAxisGraph2()
			 {
				     var meterid=paramValues.split("#")[1];
				     var lsdate=paramValues.split("#")[2];
				     var ledate=paramValues.split("#")[3];
				     
				     
				var dates= []
				// var dates=new Array();
				 for( k=0;k<paramValues.split(";")[1].substring(1).split(",").length;k++)
			     {
					 //dates[k]=+paramValues.split(";")[k].substring(1).split(",")[k];
					 
					 dates.push(paramValues.split(";")[1].substring(1).split(",")[k]);
			     }
				 
				    var seriesData = [];  //constructor in series to add multiple series at a time
				    
				     for (var i = 2; i < paramNames.split(",").length; i++){       
				         var dataSeries=new Array();
				         for( j=0;j<paramValues.split(";")[i].substring(1).split(",").length;j++)
				         {
				             dataSeries[j]=+paramValues.split(";")[i].substring(1).split(",")[j];
				         }
				   
				     //    alert(dataSeries);
				         
				        
				         
				         var test=paramNames.split(",")[i].toUpperCase();
				         var count=1;
				         
				         
				         if(test==="VR" || test==="VY" || test==="VB" || test==="LR" || test==="LY" || test==="LB"){
				        	 seriesData.push({
					             name: paramNames.split(",")[i],   
					             type: 'spline',
					             data: dataSeries,
					             step:true
					         });
				         }else{
				        	 if(count<=2){
				        	 seriesData.push({
					             name: paramNames.split(",")[i], 
					             yAxis: count,
					             type: 'column',
					             data: dataSeries
					         });
				         }
				        	 count++;
				         }
				     }
				 
				 
				  Highcharts.chart('DataPart', {
					  title: {
						    text: 'Multi Axis Graph',
						    align: 'center'
						  },
						  credits: {
						        enabled: false
						    },
					  chart: {
					    zoomType: 'xy'
					  },
					 
					  exporting: {
				            buttons: {
				                customButton: {
				                    x: -62,
				                    onclick: function () {
				                        alert('Clicked');
				                    },
				                    symbol: 'circle'
				                }
				            }
				        },
				        
					  xAxis: [{
					    categories: dates,
					    crosshair: true
					  }],
					 
					

					  yAxis: [{ // Primary yAxis
					    labels: {
					     
					      style: {
					        color: Highcharts.getOptions().colors[2]
					      }
					    },
					    title: {
					    
					      style: {
					        color: Highcharts.getOptions().colors[2]
					      }
					    },
					    opposite: true

					  }, { // Secondary yAxis
					    gridLineWidth: 0,
					    title: {
					     
					      style: {
					        color: Highcharts.getOptions().colors[0]
					      }
					    },
					    labels: {
					    
					      style: {
					        color: Highcharts.getOptions().colors[0]
					      }
					    }

					  }, { // Tertiary yAxis
					    gridLineWidth: 0,
					    title: {
					     
					      style: {
					        color: Highcharts.getOptions().colors[1]
					      }
					    },
					    labels: {
					    
					      style: {
					        color: Highcharts.getOptions().colors[1]
					      }
					    },
					    opposite: true
					  }],
					  
					  
					  tooltip: {
					    shared: true
					  },
				/*	  legend: {
					    layout: 'horizontal',
					    backgroundColor: '#FFFFFF',
					    align: 'left',
					    x: 1,
					    verticalAlign: 'top',
					    y: 12,
			            shadow: true,
					    floating: true,
					    backgroundColor:
					      Highcharts.defaultOptions.legend.backgroundColor || // theme
					      'rgba(255,255,255,0.25)'
					  },*/
					  
					  legend: {
					        layout: 'vertical',
					        align: 'right',
					        verticalAlign: 'top',
					        y: 35,
					        padding: 3,
					        itemMarginTop: 5,
					        itemMarginBottom: 5,
					        itemStyle: {
					            lineHeight: '10px'
					        }
					    },
					  
					  
					  series:seriesData,
					  responsive: {
					    rules: [{
					      condition: {
					        maxWidth: 500
					      },
					      chartOptions: {
					        legend: {
					          floating: false,
					          layout: 'horizontal',
					          align: 'center',
					          verticalAlign: 'bottom',
					          x: 0,
					          y: 0
					        },
					        yAxis: [{
					          labels: {
					            align: 'right',
					            x: 0,
					            y: -6
					          },
					          showLastLabel: false
					        }, {
					          labels: {
					            align: 'left',
					            x: 0,
					            y: -6
					          },
					          showLastLabel: false
					        }, {
					          visible: false
					        }]
					      }
					    }]
					  }},
					  
					  function (chart) { // on complete
					        var i = 0;

					        chart.renderer.button(' << Back ',0,0)
					        
					            .on('click', function () {

				                       // alert('Category: ' + this.category + ', value: ' + this.y);
					            	var chbMF = document.getElementById("chbMF");
				                        $.ajax({url: "loadMultipleStepGraph.jsp?sdate="+escape(lsdate)+"&meterid="+escape(meterid)+"&edate="+escape(ledate)+ "&EMF=" + (chbMF.checked ? "1" : "0"), success: function(responseTxt, statusTxt, xhr){
				                            
				                        	if(statusTxt == "success"){

				                           	
				                           	  var showdata = responseTxt;       
				                                for( i=0;i<showdata.split(";").length;i++)
				                                {                
				                                    paramNames=paramNames+","+showdata.split(";")[i].substring(0,showdata.split(";")[i].indexOf(","));                 
				                                    paramValues=paramValues+";"+showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(","));                  
				                                // alert(showdata.split(";")[i].substring(showdata.split(";")[i].indexOf(",")));
				                                }         
				                               
				                                if(paramValues.split(";")[1].length<2){           
				                                 paramValues={};paramNames={};
				                                    document.getElementById("DataPart").innerHTML= "<h3 align='center'>No data Available.</h3>";
				                                    return;
				                                }
				                                if(paramValues.split(";")[1].length>2){    
				                               	 getMultiAxisGraph();
				                                 
				                                }

				                        	}

				                          }});
				                    
					            	
					            	
					            })
					            .add();
					});	
				  
				  paramNames = {};
				  paramValues = {};
			}
			 
function getLatestDiagnosis(actionId) {
	clear();
	hideDashboardDiv();				
	$.ajax( {
        url:'LatestDiagnosisView.jsp?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           
           callDataTableWithName('table1','diagnosis');
           
          
        }
     });
}




function getLatestAlerts(actionId) {
	clear();
	hideDashboardDiv();
	$.ajax( {
        url:'LatestAlertsView.jsp?actionId='+actionId,
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
           $('#example').DataTable
           ( 
               {
                           dom: 'Bfrtip',
                           "bSort": false,
                           "pagingType": "full_numbers",
                           "paging": true,
                           "lengthMenu": [10, 25, 50, 75, 100],
                           buttons: 
                           [
                               {
                            	   extend:    'excel',
                            	   title: 'Latst Alerts',
                                   titleAttr: 'Excel'
                               },                               
                               {
                                   extend: 'csv',
                                   title: 'Latst Alerts',
                               }
                           ]

               } 
           );
          
        }
     });
}	

function getLsParamData() {
    var SelectedOption= document.getElementById('meter_energy').value;
     //	alert(SelectedOption);
    //alert("inside getLoadPage method "+SelectedOption);
     document.getElementById('cont-1-1').style.display = "none";
     document.getElementById("DataPart").innerHTML = "";
     document.getElementById('cont-2-1').style.display = "none";
     document.getElementById('cont-4-1').style.display = "none";
     document.getElementById('cont-3-1-1').style.display ="block";
     document.getElementById('cont-3-1').style.display = "none";
     document.getElementById('cont-3-2').style.display = "block";
     document.getElementById('cont-3-3').style.display = "none";
     document.getElementById('cont-5-1').style.display = "none";
     document.getElementById('cont-6-1').style.display = "none";
     
     xmlHttp = GetXmlHttpObject();
     if (xmlHttp == null)
     {
         alert("Browser does not support HTTP Request");
         return;
     }
     var url = "getLsParamScreen.jsp?meter_id="+SelectedOption;
//     alert("url ====> "+url);
     xmlHttp.onreadystatechange = getLoadPageAction112;
     xmlHttp.open("GET", url, true);
     xmlHttp.send(null);
     }

    function getLoadPageAction112() {
 	if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
     { 
	        var showData = xmlHttp.responseText;
	        document.getElementById("cont-3-2").style.visibility = "visible";
	        document.getElementById("cont-3-2").innerHTML = showData;
	        loadDate(11);
        
     }
 }
function getLsParamDataTable() {
    	
    	var SelectedOption= document.getElementById('param_meter').value;
    	//alert(SelectedOption);
    	
        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
        var lsdate = document.getElementById("paramsdate").value;
        var ledate = document.getElementById("paramedate").value;

        if (lsdate == "" && ledate == "") {
            alert("Please select dates...");
            return 0;
        }
        
        var Selected = document.getElementById('lsparam').selectedIndex;
        var lsparam = document.getElementById('lsparam').options[Selected].value;
        
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getLSParamDataTable.jsp";
        url = url + "?lsdate=" + escape(lsdate) + "&ledate=" + escape(ledate) + "&meter_id=" + escape(SelectedOption) + "&lsparam="+lsparam;        
       // alert("url = "+url);
        xmlHttp.onreadystatechange = LsDataTable;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        // }
    }
 
 function LsDataTable() {
     document.getElementById('DataPart').style.display = "block";
     if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
     {
         var showdata = xmlHttp.responseText;
         document.getElementById("DataPart").innerHTML = showdata;
         implement1();
     }
 }
 
 function getReport1(){
	    $('#kumar').removeClass('current');
	    $('#kumar1').removeClass('current');
	    $('#kumar2').removeClass('current');
	    $('#kumar3').removeClass('current');
	    $('#kumar4').removeClass('current');
	    $('#kumar5').removeClass('current');
	    $('#kumar6').removeClass('current');
	    $('#kumar8').removeClass('current');
	    $('#kumar7').addClass('current');
	    document.getElementById('cont-1-1').style.display = "none";
	    document.getElementById('cont-2-1').style.display = "none";
	    document.getElementById("DataPart").innerHTML = "";
	    document.getElementById('cont-3-1-1').style.display = "none";
	    document.getElementById('cont-3-1').style.display = "none";
	    document.getElementById('cont-4-1').style.display = "none";
	    document.getElementById('cont-5-1').style.display = "none";
	    document.getElementById('cont-6-1').style.display = "none";
	   /* document.getElementById('outelements').style.display = "";*/
	    
	    var Selected = document.getElementById('childID').selectedIndex;
	    var SelectedOption = document.getElementById('childID').options[Selected].value;
	    //var SelectedOption ="Z00000072";
	    
	    xmlHttp = GetXmlHttpObject();
	    if (xmlHttp == null)
	    {
	        alert("Browser does not support HTTP Request");
	        return;
	    }
	    var url = "smartmeter_instant.jsp?meter_id="+SelectedOption;
	    xmlHttp.onreadystatechange = getReport1Action;
	    xmlHttp.open("GET", url, true);
	    xmlHttp.send(null);
	}

	function getReport1Action()
	{
	        document.getElementById('DataPart').style.display = "block";
	        document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
	        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
	        {
	            var showdata = xmlHttp.responseText;
	            document.getElementById("DataPart").innerHTML = showdata;
	            $('#instPush').DataTable
	            ( 
	               {
	                       dom: 'Bfrtip',
	                       bSort: false,
	                       buttons: [{
	                                       extend: 'excel',
	                                       title: 'Instant Data'
	                                   },
	                               
	                                   {
	                                       extend: 'csv',
	                                       title: 'Instant Data'
	                                   }
	                               ],
	                       //buttons: ['csv', 'excel', 'print'],
	                       lengthMenu : [ 10, 25, 50, 75, 100 ]
	               } 
	            );
	        }
	        
	}

	function getReport2(){
	$('#kumar').removeClass('current');
	$('#kumar1').removeClass('current');
	$('#kumar2').removeClass('current');
	$('#kumar3').removeClass('current');
	$('#kumar4').removeClass('current');
	$('#kumar5').removeClass('current');
	$('#kumar6').removeClass('current');
	$('#kumar7').removeClass('current');
	$('#kumar8').addClass('current');
	document.getElementById('cont-1-1').style.display = "none";
	document.getElementById('cont-2-1').style.display = "none";
	document.getElementById("DataPart").innerHTML = "";
	document.getElementById('cont-3-1-1').style.display = "none";
	document.getElementById('cont-3-1').style.display = "none";
	document.getElementById('cont-4-1').style.display = "none";
	document.getElementById('cont-5-1').style.display = "none";
	document.getElementById('cont-6-1').style.display = "none";
//	document.getElementById('cont-7-1').style.display = "block";
	/* document.getElementById('outelements').style.display = "";*/

	var Selected = document.getElementById('childID').selectedIndex;
	var SelectedOption = document.getElementById('childID').options[Selected].value;
	//var SelectedOption ="Z00000072";

	xmlHttp = GetXmlHttpObject();
	 if (xmlHttp == null)
	{
	    alert("Browser does not support HTTP Request");
	    return;
	}
	var url = "smartmeter_alerts_all.jsp?meter_id="+SelectedOption;
	xmlHttp.onreadystatechange = getReport2Action;
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
	}

	function getReport2Action()
	{
	    document.getElementById('DataPart').style.display = "block";
	    document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;
	    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
	    {
	        var showdata = xmlHttp.responseText;
	        document.getElementById("DataPart").innerHTML = showdata;
	        $('#pushAlerts').DataTable
            ( 
               {
                       dom: 'Bfrtip',
                       bSort: false,
                       buttons: [{
                                       extend: 'excel',
                                       title: 'Alerts Data'
                                   },                               
                                   {
                                       extend: 'csv',
                                       title: 'Alerts Data'
                                   }
                               ],
                       //buttons: ['csv', 'excel', 'print'],
                       lengthMenu : [ 10, 25, 50, 75, 100 ]
               } 
            );
	    }
	    
	}
	function getSmartAlertPopup(meter,timestamp){
		 window.open("smartmeter_alerts.jsp?meter="+meter+"&time="+timestamp, '_blank');
	}
