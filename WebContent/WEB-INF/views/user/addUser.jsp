<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- BEGIN Breadcrumb -->
                <div id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">Home</a>
                            <span class="divider"><i class="fa fa-angle-right"></i></span>
                        </li>
                        <li class="active">User Master</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->

<div class="panel panel-bd">
                        <div class="panel-heading">                                                                                                          
    <div class="panel-title">                                                                                                          
        <h4 class="card-heading"> Add User </h4>                                                                          
                                                                
        </div>                                                                                                                       
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="addUser">                                                                                   
            <div class="row">                                                                                                        
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">User ID<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="userid" id="userid" placeholder="User Id" class="form-control">      
                        </div>                                                                                                       
                    </div>                                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">User Full Name</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  id="fullname" placeholder="User Full Name" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Password<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="password" name="password" id="password" placeholder="Password" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password2" class="col-xs-4 col-lg-5 control-label">Confirm Password<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="password" name="confirmPassword" id="password2" placeholder="confirm Password" class="form-control">
                        </div>                                                                                                       
                    </div> 
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Address</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <textarea name="textarea2" id="address" rows="3" class="form-control"></textarea>
                        </div>                                                                                                       
                    </div>                                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textarea1" class="col-xs-4 col-lg-5 control-label">Profile Image<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">
                     <div class="fileupload fileupload-new" data-provides="fileupload">                                                                           
       <div  class="fileupload-new img-thumbnail" style="width: 64px; height: 64px;">                                                       
          <img  src="img/users/user.png" alt="" />                                               
       </div>                                                                                                                                
       <div id="userimg" class="fileupload-preview fileupload-exists img-thumbnail" style="max-width: 64px; max-height: 64px; line-height: 20px;"></div>

       <div>                                                                                                                                 
          <span class="btn btn-default btn-file"><span class="fileupload-new">Select image</span>                                            
          <span class="fileupload-exists">Change</span>                                                                                      
          <input  type="file" name="file" class="file-input" /></span>                                                                                    
          <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>                                         
       </div>                                                                                                                                
    </div>                                                                                                                                   
                                                                                                                                             
 </div> </div>                                                                                                            
                                                                                                                               
                  <!-- END Left Side -->                                                                                             
               </div>                                                                                                                
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Right Side -->
                 <div class="form-group col-md-12">                                                                                         
                        <label for="password1" class="col-xs-3 col-lg-2 control-label">E-mail</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  id="email" placeholder="@" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group col-md-12">                                                                                         
                        <label for="password1" class="col-xs-3 col-lg-2 control-label">Mobile</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  id="mobile" placeholder="Mobile" class="form-control">
                        </div>                                                                                                       
                    </div>	
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
                        <label for="textfield2" class="col-xs-3 col-lg-2 control-label">User Role<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls" id="rolesDiv">
                            <select class="form-control chosen" id="role" name="role" onchange="getHierarchyByrole(this.value)" data-placeholder="Choose a Category" tabindex="1">
      <option value=""> </option> 
      
      <c:forEach items="${roles}" var="role">
        <option value="${role.role_id}">${role.role_title}</option>
      </c:forEach>                                                     
                                          
   </select>                                                                             
</div>                                                                                                        
                    </div>  
                                                                                                                            
       <div id="hierarchy" class="form-group col-md-12">
       </div>                                                                                                         
                                                                                                                              
                                                                                                                                    
                    <div class="form-group">                                                                                         
                        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
                           <button type="button" class="btn btn-primary" onclick="saveUesr()"><i class="fa fa-check"></i> Save</button>                   
                           <button type="button" class="btn" onclick="viewUsers(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>                                                                                                           
                  <!-- END Right Side -->                                                                                            
               </div> 
<!--                <div class="col-md-6 col-md-offset-6"> -->
<!--                <div class="form-group ">                                                                                          -->
<!--                         <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                              -->
<!--                            <button type="button" class="btn btn-primary" onclick="saveUesr()"><i class="fa fa-check"></i> Save</button>                    -->
<%--                            <button type="button" class="btn" onclick="viewUsers(${actionId})">Cancel</button>                                                          --%>
<!--                         </div>                                                                                                        -->
<!--                     </div> -->
<!--                </div>                                                                                                                -->
            </div>                                                                                                                   
         </form>                                                                                                                     
    </div>                                                                                                                           
</div> 
<script>  
//  $("#clientdiv").ready(
// 		 getUserRoles());  
 
 </script>                                                                                                                        
