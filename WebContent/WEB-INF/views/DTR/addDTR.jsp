<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
        <h4>Add DTR</h4>                                                                          
                                                                                                                              
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="addFeeder">                                                                                   
            <div class="row">                                                                                                        
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">DTR Name</label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="dtrname" id="dtr_name" placeholder="name" class="form-control">      
                        </div>                                                                                                       
                    </div>                                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Structure Code</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="structure_code" id="structure_code" placeholder="code" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Capacity</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="capacity" id="capacity" placeholder="capacity" class="form-control">
                        </div>                                                                                                       
                    </div>
                                                                                          
                  <!-- END Left Side -->                                                                                             
               </div>                                                                                                                
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Right Side -->                                                                                          
                    <div class="form-group">                                                                                         
                        <label for="textfield2" class="col-xs-3 col-lg-3 control-label">Hierarchy Level</label>                           
                        <div class="col-sm-4 col-lg-5 controls">
                        <input type="hidden" id="feeder_id" name="feederid" value=""/>
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
                           <button type="button" class="btn btn-primary" onclick="saveOrUpdateDTR()"><i class="fa fa-check"></i> Save</button>                   
                           <button type="button" class="btn" onclick="viewDTRs(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>  
            </div>                                                                                                                  
         </form>                                                                                                                     
    </div>                                                                                                                           
</div>                                                                                                                               
