<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
        <h4>Add PSL Consumer</h4>                                                                          
                                                                                                                              
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="addFeeder">                                                                                   
            <div class="row">                                                                                                        
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">PSL Name</label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="consumer_name" id="consumer_name" placeholder="name" class="form-control">      
                        </div>                                                                                                       
                    </div>                                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">PSL Code</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="consumer_code" id="consumer_code" placeholder="code" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Contract Load</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="contract_load" id="contract_load" placeholder="load" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Agrmt from date</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="aggrement_start_date" id="agrement_start_date" placeholder="Agrmt from date" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Agrmt end date</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="aggrement_end_date" id="agrement_end_date" placeholder="Agrmt end date" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Contact No</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="mobile" id="mobile" placeholder="mobile" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Address</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="address" id="address" placeholder="address" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">E-mail</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="email_id" id="email_id" placeholder="mail" class="form-control">
                        </div>                                                                                                       
                    </div>
                                                                                          
                  <!-- END Left Side -->                                                                                             
               </div>                                                                                                                
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Right Side -->                                                                                          
                    <div class="form-group">                                                                                         
                        <label for="textfield2" class="col-xs-3 col-lg-3 control-label">Hierarchy Level</label>                           
                        <div class="col-sm-4 col-lg-5 controls">
                        <input type="hidden" id="feeder_id" name="feeder_id" value=""/>
                            <select class="form-control chosen" onchange="getHierarchy(this.value)"  id="loc_type" data-placeholder="Choose a Category" tabindex="1">
      <option value="0"> </option>  
      <c:forEach items="${titles}" var="title" varStatus="loop">
        <option value="${title.hierarchy_type_id}">${title.hierarchy_title}</option>
      </c:forEach>                                                     
                                          
   </select>                                                                             
</div> 
</div> 
       <div id="hierarchy">
       </div>
        <div id="feederSel" ></div>                                                                                                                      
                                                                                                                             
                  <!-- END Right Side -->                                                                                            
               </div>                                                                                                                
            </div> 
            <div class="row" >
            <div class="form-group">                                                                                         
                        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
                           <button type="button" class="btn btn-primary" onclick="saveOrUpdatePSLConsumer()"><i class="fa fa-check"></i> Save</button>                   
                           <button type="button" class="btn" onclick="viewPSLConsumers(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>  
            </div>                                                                                                                  
         </form>                                                                                                                     
    </div>                                                                                                                           
</div>                                                                                                                               
