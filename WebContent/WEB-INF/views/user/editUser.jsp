<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<META HTTP-EQUIV="Expires" CONTENT="-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Date.*" %>
<%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.rtdas.common.models.*" %>
<%@page import="com.rtdas.common.service.*" %>
<%@page import="com.rtdas.common.beans.*" %>
<%@page import="java.util.*" %>
 <%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
 %>
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
                
<c:if test="${!empty user }">
<div class="panel panel-bd">
                        <div class="panel-heading">                                                                                                          
    <div class="panel-title">                                                                                                          
        <h4> Edit User </h4>                                                                          
                                                               
        </div>                                                                                                                       
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form action="#" class="form-horizontal" id="editUser">                                                                                    
            <div class="row">                                                                                                        
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">User ID<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="userid" id="userid" placeholder="User Id" class="form-control" value="${user.userId }">
                            <input type="hidden" id="pass" value="${user.password }"/> 
                            <input type="hidden" id="new_password" value="${user.new_password }"/>      
                        </div>                                                                                                       
                    </div>                                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">User Full Name</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  id="fullname" placeholder="User Name" class="form-control" value="${user.userFullName }">   
                        </div>                                                                                                       
                    </div>
<!--                     <div class="form-group">                                                                                          -->
<!--                         <label for="password1" class="col-xs-4 col-lg-5 control-label">Password</label>                               -->
<!--                         <div class="col-sm-4 col-lg-5 controls">                                                                     -->
<!--                             <input type="password"  id="password" placeholder="Password" class="form-control"> -->
<!--                         </div>                                                                                                        -->
<!--                     </div> -->
<!--                     <div class="form-group">                                                                                          -->
<!--                         <label for="password2" class="col-xs-4 col-lg-5 control-label">Confirm Password</label>                               -->
<!--                         <div class="col-sm-4 col-lg-5 controls">                                                                     -->
<!--                             <input type="password"  id="password2" placeholder="Confirm Password" class="form-control"> -->
<!--                         </div>                                                                                                        -->
<!--                     </div>  -->
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Active Status</label>                              
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen"  name="status"  id="status"  tabindex="1">
                               
                                <option value="1" <c:if test="${user.activeFlg==1 }">selected</c:if>>Active </option>
                               
                                <option value="0" <c:if test="${user.activeFlg==0 }">selected</c:if> >Inactive </option> 
                                                             
                             </select>                  
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Address</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <textarea name="textarea2" id="address" rows="3" class="form-control" >${user.address}</textarea>
                        </div>                                                                                                       
                    </div>                                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textarea1" class="col-xs-4 col-lg-5 control-label">Profile Image<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">
                     <div class="fileupload fileupload-new" data-provides="fileupload">                                                                           
       <div class="fileupload-new img-thumbnail" style="width: 64px; height: 64px;">                                                       
          <img src='${user.userImage}?nocache=<%=new java.util.Date().getTime() %>' alt="" />                                               
       </div>                                                                                                                                
       <div class="fileupload-preview fileupload-exists img-thumbnail" style="max-width: 64px; max-height: 64px; line-height: 20px;"></div>
       <div>                                                                                                                                 
          <span class="btn btn-default btn-file"><span class="fileupload-new">Select image</span>                                            
          <span class="fileupload-exists">Change</span>                                                                                      
          <input type="file" name="file" class="file-input" /></span>                                                                                    
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
                            <input type="text"  id="email" placeholder="@" class="form-control" value="${user.email}">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group col-md-12">                                                                                         
                        <label for="password1" class="col-xs-3 col-lg-2 control-label">Mobile</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  id="mobile" placeholder="Mobile" class="form-control" value="${user.mobileNumber}">
                        </div>                                                                                                       
                    </div>	
                  <c:choose> 
                  <c:when test='${clientId eq 0}'>                                                                                         
                    <div class="form-group col-md-12">                                                                                         
                        <label for="textfield2" class="col-xs-3 col-lg-2 control-label">Client<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen" name="client" onchange="getUserRoles()" id="clientId" data-placeholder="Choose a Category" tabindex="1">
      <option value=""> </option>  
      <c:forEach items="${clients}" var="client">
      <c:choose>
      <c:when test="${user.clientId==client.client_id }">
      <option value="${client.client_id}" selected>${client.client_name}</option>
      </c:when>
      <c:otherwise>
        <option value="${client.client_id}">${client.client_name}</option>
        </c:otherwise>
        </c:choose>
      </c:forEach>                                                     
                                          
   </select>                                                                             
</div>                                                                                                        
  </div> 
  </c:when><c:otherwise>
<div id="clientdiv"><input type="hidden" value="${clientId}" id="clientId"/></div>
</c:otherwise>
</c:choose>
                    <div class="form-group col-md-12">                                                                                         
                        <label for="textfield2" class="col-xs-3 col-lg-2 control-label">User Role<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls" id="rolesDiv">
                            <select class="form-control chosen"  id="role" onchange="getHierarchyByrole(this.value)" data-placeholder="Choose a Category" tabindex="1">
      <option value=""> </option>  
      <c:forEach items="${roles}" var="role">
      <c:choose>
      <c:when test="${user.rollId==role.role_id }">
      <option value="${role.role_id}" selected>${role.role_title}</option>
      </c:when>
      <c:otherwise>
        <option value="${role.role_id}">${role.role_title}</option>
        </c:otherwise>
        </c:choose>
      </c:forEach>                                                     
                                          
   </select>                                                                             
</div>                                                                                                        
     </div>                                                                                                           
                                                                                                              
        <div id="hierarchy" class="form-group col-md-12">
        <%
       if(request.getAttribute("titles")!=null){
    	   String levelType="";
       WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
       
       HierarchyService hService = (HierarchyService) wac.getBean("hierarchyService");       
       List<HierarchyTitle> tList=(List<HierarchyTitle>)request.getAttribute("titles");
       UserBean userBean=(UserBean)request.getAttribute("user");
       HierarchyTitle firstTitle=(HierarchyTitle)tList.get(0);
      
       List<Hierarchy> firstHierarchy=hService.getHierarchyByType(firstTitle.getHierarchy_type_id());
      
       int level=Integer.parseInt(request.getAttribute("level").toString());
       List<Object[]> currentHierarchy=(List<Object[]>)request.getAttribute("currentHierarchy");
       if(!currentHierarchy.isEmpty()){
       //Object[] obj=currentHierarchy.get(0);
       UserRoleService roleService = (UserRoleService) wac.getBean("roleService");
       UserRole userrole=roleService.getUserRole(userBean.getRollId());
       Integer hierarchyLevelId=userrole.getHierarchy_level();
       
      
       for(int i=0;i<tList.size();i++){
    		HierarchyTitle htitle=(HierarchyTitle)tList.get(i);
    		if(hierarchyLevelId==htitle.getHierarchy_type_id()){
    			levelType=htitle.getHierarchy_title();
    			
    		}
    	}
      
       %>   
       <input type="hidden" id="hlevelid" value="${user.hierarchy }"/>
       <input type="hidden" id="hlevel" value="<%=level%>"/>
       <input type="hidden" id="hleveltype" value="<%=levelType%>"/>             
        <%
       for(int i=0;i<level;i++){
    	 HierarchyTitle title=(HierarchyTitle)tList.get(i);
    	 %>
     <div class="form-group col-md-12">                                                                                         
            <label for="textfield2" class="col-xs-3 col-lg-2 control-label"><%=title.getHierarchy_title() %></label>                           
            <div class="col-sm-4 col-lg-5 controls">
                <select class="form-control chosen" onchange="getHierarchyData(this.value,'level_<%=i+2%>')"  
                id="level_<%=i+1%>" data-placeholder="Choose a Category" tabindex="1" <%=(i==(level-1)?"multiple":"") %>>
                  <% if(i==level-1){
                    for(Object[] obj:currentHierarchy){ %>
                    	<option value="<%=obj[i*3+1] %>"><%=obj[i*2+i] %> </option>  	
                    <%}
                  }
                  %>
                   
                  <%if(i==0){
                	  for(int l=0;l<firstHierarchy.size();l++){
                		  Hierarchy hie=(Hierarchy)firstHierarchy.get(l);
                	  %>
                	  <option value="<%=hie.getHierarchy_id()%>"><%=hie.getHierarchy_name()%> </option>
               <%  } }
                  %> 
                 </select>                                                                             
           </div> 
        </div> 
    <% }
       }//if current hierarchy is not empty
       }//if titles not null
     %>
       </div>                                                                                                                     
                                                                                                                                    
                    <div class="form-group">                                                                                         
                        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
                           <button type="button" class="btn btn-primary" onclick="updateUser('${user.slno}')"><i class="fa fa-check"></i> Update</button>                   
                           <button type="button" class="btn" onclick="viewUsers(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>                                                                                                           
                  <!-- END Right Side -->                                                                                            
               </div>                                                                                                                
            </div>                                                                                                                   
         </form>                                                                                                                     
    </div>                                                                                                                           
</div>   
</c:if>   
<script>  
//  $("#clientdiv").ready(
// 		 getUserRoles());  
 </script>                                                                                                                          
