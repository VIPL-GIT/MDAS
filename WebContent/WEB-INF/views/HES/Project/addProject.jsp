<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
        <h4>Add Smart Project</h4>                                                                          
                                                                                                                              
    </div>                                                                                                                           
<div class="panel-body">
<form  class="form-horizontal" method="post" id="addSmartProject" autocomplete="off">                                                                                   
            <div class="row">                                                                                                        
 <div class="col-md-6 "> 
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Project Name</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="project_name" id="feeder_name" placeholder="name" class="form-control">      
     </div>                                                                                                       
 </div>
 
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Manufacturer</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="meter_manufacturer" id="feeder_name" placeholder="name" class="form-control">      
     </div>                                                                                                       
 </div>
 
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Flag ID</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="flag_id" id="feeder_name" placeholder="name" class="form-control">      
     </div>                                                                                                       
 </div>
 
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">LLS</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="lls" id="lls" placeholder="LLS" class="form-control">      
     </div>                                                                                                       
 </div>
 </div>
 <div class="col-md-6 ">
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-3 col-lg-3 control-label">HLS</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="hls" id="hls" placeholder="hls" class="form-control">      
     </div>                                                                                                       
 </div>
 
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-3 col-lg-3 control-label">Global Key</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="global_key" id="global_key" placeholder="Global key" class="form-control">      
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-3 col-lg-3 control-label">Auth. Key</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="authentication_key" id="authentication_key" placeholder="Auth. key" class="form-control">      
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-3 col-lg-3 control-label">Master Key</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="master_key" id="master_key" placeholder="Master key" class="form-control">      
     </div>                                                                                                       
 </div>
 
 </div>
               </div>
<div class="row" >
            <div class="form-group">                                                                                         
                        <div class="col-sm-4 col-sm-offset-8 col-lg-4 col-lg-offset-8">                                             
                           <button type="button" class="btn btn-primary" onclick="saveOrUpdatesmartProject()"><i class="fa fa-check"></i> Save</button>                   
                           <button type="button" class="btn" onclick="viewSmartProjects(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>  
            </div>               
</form>
</div>
</div>
