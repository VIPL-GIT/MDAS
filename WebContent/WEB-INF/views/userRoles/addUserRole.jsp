<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- BEGIN Breadcrumb -->
                <div id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">Home</a>
                            <span class="divider"><i class="fa fa-angle-right"></i></span>
                        </li>
                        <li class="active">User Role Master</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->
                
<div class="panel panel-bd">
                        <div class="panel-heading">                                                                                                          
    <div class="panel-title">                                                                                                          
        <h4 class="card-heading"> Add User Role </h4>                                                                          
                                                                 
        </div>                                                                                                                       
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form action="#" class="form-horizontal" id="addUserRole">                                                                                    
            <div class="row">                                                                                                        
               <div class="col-md-5 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">User Role<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="role"  id="role" placeholder="User Role" class="form-control">      
                        </div>                                                                                                       
                    </div>                                                                                                           
                    
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Role Description</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <textarea name="textarea2" id="role_desc" rows="2" class="form-control"></textarea>
                        </div>                                                                                                       
                    </div>                                                                                                           
                                                                                                                               
                                                                                                                               
                  <!-- END Left Side -->                                                                                             
               </div>                                                                                                                
               <div class="col-md-7 ">                                                                                               
                  <!-- BEGIN Right Side --> 
                  <c:choose> 
                  <c:when test='${clientId eq 0}'>                                                                                          
                    <div class="form-group">                                                                                         
                        <label for="textfield2" class="col-xs-3 col-lg-2 control-label">Client<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen" name="client" id="client" data-placeholder="Choose a Category" tabindex="1">
      <option value=""> </option>  
      <c:forEach items="${clients}" var="client">
        <option value="${client.client_id}">${client.client_name}</option>
      </c:forEach>                                                     
                                          
   </select>                                                                             
</div> </div> 
</c:when><c:otherwise>
<input type="hidden" value="${clientId}" id="client"/>
</c:otherwise>
</c:choose>
                    <div class="form-group">                                                                                         
                        <label for="textfield2" class="col-xs-4 col-lg-5 control-label">Parent Role</label>                           
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen" id="parent_role" data-placeholder="Choose a Category" tabindex="1">
      <option value="0"> </option>  
      <c:forEach items="${roles}" var="role">
        <option value="${role.role_id}">${role.role_title}</option>
      </c:forEach>                                                     
                                          
   </select>                                                                             
</div>                                                                                                        
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="textfield2" class="col-xs-4 col-lg-5 control-label">Hierarchy Level</label>                           
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen" id="level" data-placeholder="Choose a Category" tabindex="1">
      <option value="0"> </option>  
      <c:forEach items="${titles}" var="title">
        <option value="${title.hierarchy_type_id}">${title.hierarchy_title}</option>
      </c:forEach>                                                     
                                          
   </select>                                                                             
</div>                                                                                                        
                    </div>                                                                                                           
                                                                                                                                 
                                                                                                                              
                  <!-- END Right Side -->                                                                                            
               </div>                                                                                                                
            </div> 
<hr>
 <div class="col-md-12">
                        <div class="box">
                            <div class="box-title">
                                <h3><i class="fa fa-table"></i>Access Control</h3>
                                
                            </div>
                            <div class="box-content">
 <div class="table-big" style="height:200px;overflow-y:scroll;">
<table class="table table-striped table-hover fill-head">
 <thead>
 <tr>
<!--  <th>Module Sl No</th> -->
 <th rowspan="2">Module Name</th>
 <th rowspan="2">Sub-Module Name</th>
 <th rowspan="2">Action</th>
 <th colspan="4">Actions</th>
 </tr>
 <tr>
 <th>Add</th><th>Edit</th><th>Delete</th><th>View</th>
 </tr>
  </thead>
 <tbody>
 <c:forEach var="actions" items="${subModuleActions}">
 <tr>
<%--  <td>${module.module_id }</td> --%>
 <td>${actions.module_title }</td>
 <td>${actions.submodule_title }</td>
 <td>${actions.action_title }</td>
 <td><input type="checkbox" name="action" value="${actions.action_id }_add"/></td>
 <td><input type="checkbox" name="action" value="${actions.action_id }_edit"/></td>
 <td><input type="checkbox" name="action" value="${actions.action_id }_delete"/></td>
 <td><input type="checkbox" name="action" value="${actions.action_id }_view"/></td>
 </tr>
 </c:forEach> 
</tbody>
 </table>
 </div> 
 </div>
 </div>                                                                                                              
                    <div class="form-group">                                                                                         
                        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
                           <button type="button" class="btn btn-primary" onclick="saveUserRole()"><i class="fa fa-check"></i> Save</button>                   
                           <button type="button" class="btn" onclick="viewUserRoles(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>                                                                                                        
                  <!-- END Right Side -->                                                                                            
                                                                                                                               
            </div>                                                                                                                              
         </form>                                                                                                                     
    </div>                                                                                                                           
</div>                                                                                                                               
