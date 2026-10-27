<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
        <h4>Add Modem</h4>                                                                          
                                                                                                                              
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="addModem">                                                                                   
            <div class="row">                                                                                                        
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Modem Sl No<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls"> 
                            <input type="text" name="modemSlNo" id="modemSlNo" placeholder="modem serial no" class="form-control"/>
                        </div>                                                                                                       
                    </div>      
                                                             
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Hardware Version<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="hw_version" id="hw_version" placeholder="Hardware Version" class="form-control">
                        </div>                                                                                                       
                    </div>
                    
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Firmware Version</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name=fw_version id=fw_version placeholder="Firmware Version" class="form-control">
                        </div>                                                                                                       
                    </div>
                    
                    
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Mobile</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="mobile" id="mobile" placeholder="Mobile" class="form-control">
                        </div>                                                                                                       
                    </div>
                    
                    
                
                    
                    
   <div class="form-group">                                                                                     
   <label class="col-sm-4 col-lg-5 control-label">Delivery Date<sup>*</sup></label>                                    
   <div class="col-sm-5 col-lg-5 controls">
   <div class="input-group input-append date" id="dateRangePicker">
   <input type="text" class="form-control" name="date" id="date"/>
   <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar fa fa-calendar"></span></span>
   </div>                                                                                                                  
</div>                                                                                                                     
</div>                                                                                                                  
        
        <div class="form-group">                                                                         
        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                           
           <button type="button" class="btn btn-primary" onclick="saveModem()"><i class="fa fa-check" ></i> Save</button> 
           <button type="button" class="btn" onclick="viewModem(${actionId})">Cancel</button>                                       
        </div>                                                                                     
        </div> 
            
                                             </div></div>                                 
         </form> 
                                                                                                                            
    </div>                                                                                                                           
</div>                                                                                                                               
