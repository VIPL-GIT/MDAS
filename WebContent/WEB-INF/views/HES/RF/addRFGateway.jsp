<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
$(document).ready(function(){
    $("#submit123").click(function(){
    	var rowCount = $('#mytable tbody tr').length;
        var value = $("#addNodes").val();
        
		for (var j = 0; j <value-rowCount; j++) {
			var row=rowCount+j;
        var markup = "<tr id='newtr'><td>"+(row+1)+"</td>"
                     +"<td><input name='endnode_mac_address"+(row+1)+"' value=''/></td></tr>"; 
        $("#mytable tbody").append(markup);
		}
		 
    });
    $("#clear").click(function(){
    	var contentToRemove = document.querySelectorAll("#newtr");
    	$(contentToRemove).remove(); 
    	document.getElementById("addNodes").value="";
    });
    
    
});  

</script>
<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
        <h4>Add RF Gateway</h4>                                                                          
                                                                                                                              
    </div>                                                                                                                           
<div class="panel-body">
   <form method="POST"  id="addRFGateway">                                                                                
   <div class="col-md-12 row">
    <div class="form-group col-md-4">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-4 control-label">Gateway Sl.No.</label>                           
     <div class="col-sm-8 col-lg-8 controls">                                                                    
         <input type="text" name="gateway_serial_number" id="gateway_serial_number" placeholder="name" class="form-control">      
     </div>                                                                                                       
 </div>
 <div class="form-group col-md-4">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-4 control-label">Manufacturer</label>                           
     <div class="col-sm-8 col-lg-8 controls">                                                                    
         <input type="text" name="gateway_manufacturer" id="gateway_manufacturer" placeholder="name" class="form-control">      
     </div>                                                                                                       
 </div>
 <div class="form-group col-md-4">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-4 control-label">No.of End Nodes</label>                           
     <div class="col-sm-4 col-lg-4 controls">                                                                    
         <input type="text" name="addNodes" id="addNodes" placeholder="enter" class="form-control">      
     </div>
     <div class="col-sm-4 col-lg-4 controls">                                                                    
         <input type="button" class="btn btn-xs btn-primary" id="submit123" value="Add"  />
         <input type="button" class="btn btn-xs btn-primary" id="clear" value="Reset" style="margin-left:5px;"  />
                         
     </div>                                                                                                       
 </div>
   </div>  

<div class="col-md-offset-3 col-offset-md-3 col-md-12 table-responsive" style="width:50%;">  

  <table id='mytable' class="table table-hover">
  	<thead>
    <tr>
      <th>S.no</th>
      <th>End Node MAC Address</th>
      </tr>
     </thead>
     <tbody>
     
     </tbody>
     </table>
     
     
                                                                                                     
    <div class="col-sm-5 col-sm-offset-7 col-lg-5 col-lg-offset-7">                                             
       <button type="button" class="btn btn-primary" onclick="saveOrUpdateRFGateway()"><i class="fa fa-check"></i> Save</button>                   
       <button type="button" class="btn" onclick="viewRFGateway(${actionId})">Cancel</button>                                                         
    </div>                                                                                                       
    </div>                  
            
     </form>
</div>       
                                                                                                                   
                

</div>

