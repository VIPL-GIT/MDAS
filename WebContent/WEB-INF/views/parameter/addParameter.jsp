<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
        <h3 class="panel-title"><i class="fa fa-bars"></i> Add Parameter</h3>                                                                          
                                                                                                                              
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="param">                                                                                   
            <div class="row">                                                                                                  
                                                                                                                               
               <div class="col-md-6 ">                                                                                               
                 	
                  <c:choose> 
                  <c:when test='${clientId eq 0}'>                                                                                      
                    <div class="form-group col-md-12">                                                                                         
                        <label for="textfield2" class="col-xs-3 col-lg-2 control-label">Client<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen" onchange="getUserRoles()" name="client"  id="clientId" data-placeholder="Choose a Category" tabindex="1">
      <option value=""> </option> 
     
      <c:forEach items="${clients}" var="client">      
        <option value="${client.client_id}">${client.client_name}</option>        
      </c:forEach>                                                     
                                         
   </select>                                                                             
</div> 
</div> 
</c:when><c:otherwise>
<div class="form-group" id="clientdiv"><input type="hidden" value="${clientId}" id="clientId"/></div>
</c:otherwise>
</c:choose>
  
  <div class="form-group col-md-12">                                                                                         
                        <label  class="col-xs-4 col-lg-5 control-label">Parameter Name</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  id="name" name="name"  placeholder="Parameter Name" class="form-control">
                        </div>                                                                                                       
                    </div>
                                                                                                                                 
                    <div class="form-group">                                                                                         
                        <div class="col-sm-8 col-sm-offset-4 col-lg-7 col-lg-offset-5">                                             
                           <button type="button" class="btn btn-primary" onclick="saveParameterName()"><i class="fa fa-check"></i> Save</button>                   
                           <button type="button" class="btn" onclick="viewParameters(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>                                                                                                           
                  <!-- END Right Side -->                                                                                            
               </div> 

            </div>                                                                                                                   
         </form>                                                                                                                     
    </div>                                                                                                                           
</div> 
                                                                                                                       
