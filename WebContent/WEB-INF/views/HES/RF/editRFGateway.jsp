<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
        <h4>Update RF Gateway</h4>                                                                          
                                                                                                                              
    </div>                                                                                                                           
<div class="panel-body">
<form  class="form-horizontal" method="post" id="addRFGateway" autocomplete="off">                                                                                   
            <div class="row">                                                                                                        
 <div class="col-md-6 "> 
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Gateway Sl.No.</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="gateway_serial_number" id="gateway_serial_number" placeholder="name" class="form-control" value="${rf.gateway_serial_number }" readonly>
         <input type="hidden" name="gateway_id" id="gateway_id" placeholder="name" class="form-control" value="${rf.gateway_id }">      
     </div>                                                                                                       
 </div>
 
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Manufacturer</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="gateway_manufacturer" id="gateway_manufacturer" placeholder="name" class="form-control" value="${rf.gateway_manufacturer }" readonly>      
     </div>                                                                                                       
 </div>
 
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">endnode_mac_address</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="endnode_mac_address" id="endnode_mac_address" placeholder="address" class="form-control" value="${rf.endnode_mac_address }">      
     </div>                                                                                                       
 </div>
 
 
 </div>
 
               </div>
<div class="row" >
            <div class="form-group">                                                                                         
                        <div class="col-sm-8 col-sm-offset-4 col-lg-8 col-lg-offset-4">                                             
                           <button type="button" class="btn btn-primary" onclick="updateRFGateway()"><i class="fa fa-check"></i> Update</button>                   
                           <button type="button" class="btn" onclick="viewRFGateway(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>  
            </div>               
</form>
</div>
</div>
