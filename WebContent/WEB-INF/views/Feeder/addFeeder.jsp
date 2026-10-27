<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
        <h4>Add Feeder</h4>                                                                          
                                                                                                                              
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="addFeeder">                                                                                   
            <div class="row">                                                                                                        
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Feeder Name</label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="feedername" id="feeder_name" placeholder="name" class="form-control">      
                        </div>                                                                                                       
                    </div>                                                                                                           
                     <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Voltage Level</label>                              
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen"  name="voltage_level"  id="voltage_level"  tabindex="1">
                                <option value="-">-</option>                               
                                <option value="11Kv" >11Kv </option>                               
                                <option value="33Kv" >33Kv </option>
                             </select> 
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Feeder Type</label>                              
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen"  name="feeder_type"  id="feeder_type"  tabindex="1">
                                <option value="-">-</option>                               
                                <option value="AG" >AG </option>                               
                                <option value="NON AG"  >NON AG </option>
                                <option value="IN" >IN </option>
                                <option value="OUT" >OUT </option>
                             </select> 
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Conductor</label>                              
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen"  name="conductor"  id="conductor"  tabindex="1"> 
                                <option value="-">-</option>                              
                                <option value="Dog" >Dog </option>                               
                                <option value="Panther" >Panther </option>
                                <option value="Rabbit"  >Rabbit </option>
                                <option value="Raccoon" >Raccoon </option>
                                <option value="Squirrel" >Squirrel </option>
                                <option value="Weasel" >Weasel </option>
                             </select> 
                        </div>                                                                                                       
                    </div>
                                                                                                                            
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Structure Code</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="structure_code" id="structure_code" placeholder="code" class="form-control" >
                        </div>                                                                                                       
                    </div>
                    
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">No.of Consumers</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="no_of_consumers" id="no_of_consumers" placeholder="" class="form-control" >

                        </div>                                                                                                       
                    </div>
                                                                                          
                  <!-- END Left Side -->                                                                                             
               </div>                                                                                                                
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Right Side -->                                                                                          
                    <div class="form-group">                                                                                         
                        <label for="textfield2" class="col-xs-3 col-lg-3 control-label">Hierarchy Level</label>                           
                        <div class="col-sm-4 col-lg-5 controls">
                        <input type="hidden" id="substation_id" name="substationid" value=""/>
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
                                                                                                                             
                  <!-- END Right Side -->                                                                                            
               </div>                                                                                                                
            </div> 
            <div class="row" >
            <div class="form-group">                                                                                         
                        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
                           <button type="button" class="btn btn-primary" onclick="saveOrUpdateFeeder()"><i class="fa fa-check"></i> Save</button>                   
                           <button type="button" class="btn" onclick="viewFeeder(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>  
            </div>                                                                                                                  
         </form>                                                                                                                     
    </div>                                                                                                                           
</div>                                                                                                                               
