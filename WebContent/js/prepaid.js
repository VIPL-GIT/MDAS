function getPrepaidHome(actionId){
	clear();
	hideDashboardDiv();
	
	$.ajax({
//	   	 url : 'prepaid/recharge_home.jsp',	 
		url: "prepaid/home.epx",
	       type : 'GET',
	       beforeSend:function(){
	    	   document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
	       success : function(data) {	         
	           $("#datapart").html(data);
	           //getRechargePage();
	       },
	       error : function(err) {	           
	           $("#datapart").html(err);
	       }
	   });
}
function getRechargePage(){
	
    $.ajax({
                url: "prepaid/recharge.jsp",
    	 
                dataType: 'html',
                success: function (data) {
                    $("#prepaidContent").html(data);
                   // getRechargeHistory();
                    getInstantView();
                   // setTimeout("getRechargePage()", 60000);
                }
            });
} 
function getRechargeHistory(){
   $.ajax({
                url: "prepaid/rechargeHistory.jsp",
                dataType: 'html',
                success: function (data) {
                    $("#rechargeHistory").html(data);
                    
                }
            }); 
}
function getInstantPush(){
	
	var Selected = document.getElementById('childID').selectedIndex;
	var meter = document.getElementById('childID').options[Selected].text;
	var sdate=$("#isDate").val();
	var edate=$("#ieDate").val();
	
	if(meter===undefined){
		alert("Please select meter");
		return 0;
	}
	
    $.ajax({
            url: "prepaid/instant_push.jsp?meter="+meter+"&sdate="+sdate+"&edate="+edate,
            dataType: 'html',
            beforeSend:function(){
            	 jQuery("#prepaidContent").html(jQuery("#loading").html());
            },
            success: function (data) {
                $("#prepaidContent").html(data);
                //var table = $('#table1').DataTable(); 
                callDataTableWithName('table1','Instant_push');
            }
        });
}

function getAlertsPush(){
	
	var Selected = document.getElementById('childID').selectedIndex;
	var meter = document.getElementById('childID').options[Selected].text;
	var sdate=$("#asDate").val();
	var edate=$("#aeDate").val();
	
	if(meter===undefined){
		alert("Please select meter");
		return 0;
	}
	
    $.ajax({
            url: "prepaid/alerts_push.jsp?meter="+meter+"&sdate="+sdate+"&edate="+edate,
            dataType: 'html',
            beforeSend:function(){
           	 jQuery("#prepaidContent").html(jQuery("#loading").html());
           },
            success: function (data) {
                $("#prepaidContent").html(data);
                //var table = $('#table1').DataTable(); 
                callDataTableWithName('table1','Instant_push');
            }
        });
}
function getcdHistory(){
	var Selected = document.getElementById('childID').selectedIndex;
	var meter = document.getElementById('childID').options[Selected].text;
	var sdate=$("#cdsDate").val();
	var edate=$("#cdeDate").val();
	
	if(meter===undefined){
		alert("Please select meter");
		return 0;
	}
	
    $.ajax({
            url: "prepaid/connectDisconnectHistory.jsp?meter="+meter+"&sdate="+sdate+"&edate="+edate,
            dataType: 'html',
            beforeSend:function(){
           	 jQuery("#prepaidContent").html(jQuery("#loading").html());
           },
            success: function (data) {
                $("#prepaidContent").html(data);
                callDataTableWithName('table1','cd_history');
            }
        });
}
function getSlabRates(){
	 $.ajax({
         url: "prepaid/slab_rates.jsp",
         dataType: 'html',
         beforeSend:function(){
        	 jQuery("#prepaidContent").html(jQuery("#loading").html());
        },
         success: function (data) {
             $("#prepaidContent").html(data); 
             callDataTableWithName('table1','Slab_rates');
         }
     });
}
function getMonthlyTransasctions(){
	var Selected = document.getElementById('childID').selectedIndex;
	var meter = document.getElementById('childID').options[Selected].text;
	if(meter===undefined){
		alert("Please select meter");
		return 0;
	}
	$.ajax({
        url: "prepaid/monthly_transactions.jsp?selMonth="+$("#tmonth").val()+"&meter="+meter,
        dataType: 'html',
        beforeSend:function(){
       	 jQuery("#prepaidContent").html(jQuery("#loading").html());
       },
        success: function (data) {
            $("#prepaidContent").html(data); 
            callDataTableWithName('table1','Slab_rates');
        }
    });
}
function payAmount(){
	var Selected = document.getElementById('childID').selectedIndex;
	var meter = document.getElementById('childID').options[Selected].text;
    var amount=$("#amount").val();
    $.ajax({
        url: "prepaid/recharge_amount.jsp?meter="+meter+"&amount="+amount,
        dataType: 'html',
        beforeSend:function(){
       	 jQuery("#prepaidContent").html(jQuery("#loading").html());
       },
        success: function (data) {
        	$("#prepaidContent").html(data); 
        }
    });
}
function tamperConnect(meter){   
    alert(meter);
    $.ajax({
        url: "prepaid/tamperReconnect.jsp?meter="+meter,
        dataType: 'html',
        beforeSend: function (xhr) {
                    $("#rechargeHistory").html($("#loading").html());    
                    },
        success: function (data) {
            //$("#DataPart").html(data);
             getRechargePage();
        }
    });
}