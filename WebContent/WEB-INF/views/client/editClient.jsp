<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<%@page import="com.rtdas.common.beans.*" %>
<c:if test="${!empty client }">
<div class="box">                                                                                                          
    <div class="box-title">                                                                                                          
        <h3><i class="fa fa-bars"></i>Edit Client Form</h3>                                                                          
        <div class="box-tool">                                                                                                       
            <a data-action="collapse" href="#"><i class="fa fa-chevron-up"></i></a>                                                  
            <a data-action="close" href="#"><i class="fa fa-times"></i></a>                                                          
        </div>                                                                                                                       
    </div>                                                                                                                           
    <div class="box-content">                                                                                                        
        <form action="#" class="form-horizontal" id="editClient">                                                                                    
            <div class="row">                                                                                                        
               <div class="col-md-5 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Client Name<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="client" id="client" placeholder="Client Name" class="form-control" value="${client.clientName }">      
                        </div>                                                                                                       
                    </div>                                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">About Client</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  id="client_desc" placeholder="About Client" class="form-control" value="${client.aboutClient }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group"> 
                    <label for="password1" class="col-xs-4 col-lg-5 control-label">E-mail</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  id="email" placeholder="@" class="form-control" value="${client.email }">
                        </div>
                        </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Active Status</label>                              
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen"  name="status"  id="status"  tabindex="1">
                               
                                <option value="1" <c:if test="${client.activeStatus==1 }">selected</c:if>>Active </option>
                                <option value="0" <c:if test="${client.activeStatus==0 }">selected</c:if> >Inactive </option> 
                                                             
                             </select>                  
                        </div>                                                                                                       
                    </div>    
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Address 1</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <textarea name="textarea2" id="address1" rows="2" class="form-control">${client.add1 }</textarea>
                        </div>                                                                                                       
                    </div>                                                                                                           
                                                                                                                               
                                                                                                                               
                  <!-- END Left Side -->                                                                                             
               </div>                                                                                                                
               <div class="col-md-5 ">                                                                                               
                  <!-- BEGIN Right Side -->                                                                                          
                                                                                                                          
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Contact Person<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="contactperson" id="contact_person" placeholder="Contact Person" class="form-control" value="${client.contactPerson }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Mobile<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="mobile" id="mobile" placeholder="Mobile" class="form-control" value="${client.mobile }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Address 2</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <textarea name="textarea2" id="address2" rows="2" class="form-control">${client.add2 }</textarea>
                        </div>                                                                                                       
                    </div> 	                                                                                                          
                    <div class="form-group">                                                                                         
                        <label for="textarea1" class="col-xs-4 col-lg-5 control-label">Client Logo<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">
                     <div class="fileupload fileupload-new" data-provides="fileupload">                                                                           
       <div class="fileupload-new img-thumbnail" style="width: 200px; height: 65px;">                                                       
          <img src="${client.clientImg}?nocache=<%=new java.util.Date().getTime() %>" alt="size : 200*65" />                                               
       </div>                                                                                                                                
       <div class="fileupload-preview fileupload-exists img-thumbnail" style="max-width: 200px; max-height:65px; line-height: 20px;"></div>
       <div>                                                                                                                                 
          <span class="btn btn-default btn-file"><span class="fileupload-new">Select image</span>                                            
          <span class="fileupload-exists">Change</span>                                                                                      
          <input type="file" name="clientimage" class="file-input" /></span>                                                                                    
          <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>                                         
       </div>                                                                                                                                
    </div>                                                                                                                                   
                                                                                                                                             
 </div> </div> 
 </div>
 <hr>
 <div class="col-md-12">
                        <div class="box">
                            <div class="box-title">
                                <h3><i class="fa fa-table"></i> Module Access</h3>
                                
                            </div>
                            <div class="box-content">
 <div class="table-big" style="height:200px;overflow-y:scroll;">
<table class="table table-striped table-hover fill-head">
 <thead>
 <tr>
<!--  <th>Module Sl No</th> -->
 <th>Module Name</th><th>Sub-Module Name</th><th>Access</th>
 </tr>
  </thead>
 <tbody>
 <% List<ModuleSubModuleBean> module=(List<ModuleSubModuleBean>)request.getAttribute("modules");
   for(int i=0;i<module.size();i++){
	   ModuleSubModuleBean mb=(ModuleSubModuleBean)module.get(i);
	   %>
	   <tr>
	   <td><%=mb.getModule_title() %></td>
       <td><%=mb.getSubmodule_title()%></td>
       <%
       List mappedIds=(List)request.getAttribute("mappedModules");
       if(mappedIds.contains(mb.getSubmodule_id())){ %>
    	   <td><input type="checkbox" name="submodules" value="'<%=mb.getSubmodule_id()%>'" checked="checked"/></td>
     <%  }else{ %>
    	 <td><input type="checkbox" name="submodules" value="'<%=mb.getSubmodule_id()%>'"/></td>
   <%  }
       %>
	   </tr>
  <% }
 %>
 
</tbody>
 </table>
 </div> 
 </div>
 </div>                                                                                                              
                    <div class="form-group">                                                                                         
                        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
                           <button type="button" class="btn btn-primary" onclick="updateClient('${client.clientId}')"><i class="fa fa-check"></i> Update</button>                   
                           <button type="button" class="btn" onclick="viewClient(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>                                                                                                           
                  <!-- END Right Side -->                                                                                            
                                                                                                                               
            </div>                                                                                                                   
         </form>                                                                                                                     
    </div>                                                                                                                           
</div>  
</c:if>                                                                                                                             
