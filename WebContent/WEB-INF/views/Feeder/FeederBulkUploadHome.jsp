<div class="col-md-12">
<div class="row">
<div class="col-md-6">
<button class="btn btn-sm" onclick="downloadFile()">Download Template</button>
<button class="btn btn-sm" onclick="getUploadScreen()">Upload Template</button>
</div>

<div class="col-md-6" id="upload" style="display:none;">
<div class="col-md-5" >
<input type="file" name="file" accept=".xls">
</div>
<div class="col-md-7">
<div class="btn btn-group" >
<button class="btn btn-sm" onclick="excelValidate()">Validate</button>
<button class="btn btn-sm" onclick="resetTable()" style="margin-left:10px;">Reset</button>
<button class="btn btn-sm" id="process" style="margin-left:10px;" onclick="uploadAndProcess()">Process All</button>
</div>
</div>
</div>
</div>
<div class="row " id="uploadedData" style="width:100%;height:400px;overflow:scroll;"></div>
</div>
<script>
function downloadFile(){
	location.href="feeder/download.epx";
}
function getUploadScreen(){
	document.getElementById("upload").style.display="block";
}
function excelValidate(){
	var formData = new FormData();	
	if($('input[type=file]')[0].files[0]!=undefined){
	 formData.append('uploadFile',$('input[type=file]')[0].files[0]);
	}
	$.ajax( {
		type : 'POST',       
		 url:'feeder/bulkUpload.epx',
        data : formData,
        contentType: false,
        processData: false,       
        beforeSend: function(msg){
        	$('#uploadedData').html($('#loading').clone());
          }, 
        success:function(data) {
           $('#uploadedData').html(data);
           
           
        }
     });
}

function uploadAndProcess(){	
//document.getElementById("process").style.display="none";
	var td=1;
	var header_array = [];
	$('#exceldata thead tr').each(function() {
	    $.each(this.cells, function(){	    	
	    	header_array.push($(this).text().trim());
	    });
	});
	//$.ajaxSetup({async: false});
	$('#exceldata tbody tr').each(function() {
		var formData = new FormData();
		var array = [];
		
	    $.each(this.cells, function(){	    	
	    	  array.push($(this).html().trim());
	    });
		
	   // alert($(this).find('td:last').html());
	    var id=$(this).find('td:last').attr('id');
	    formData.append("values",array.join("@"));
	    formData.append("header",header_array);
	    $.ajax( {
			type : 'POST',       
			 url:'feeder/processRow.epx',
	        data : formData,
	        contentType: false,
	        processData: false,	        
	        beforeSend: function(msg){
	        	document.getElementById(id).innerHTML='<img src="images/loader.gif" height="25px">';
	        	// $("#td_"+td).html($('#loading').clone());
	          }, 
	        success:function(data) {
	        	document.getElementById(id).innerHTML=data;   
	        	td=parseInt(td)+1;
	        }
	     });
	    

	});
	//$.ajaxSetup({async: true});
	
	
}

function resetTable(){
	$('#exceldata tbody tr').each(function() {
	    $.each(this.cells, function(){	    	
	    	if($(this).html().trim().indexOf("right.png")>0){
	    		$(this).parents("tr").remove();
	    	}
	    	if($(this).html().trim().indexOf("wrong.png")>0){
	    		$(this).html("");
	    	}
	    });
	});
}
</script>