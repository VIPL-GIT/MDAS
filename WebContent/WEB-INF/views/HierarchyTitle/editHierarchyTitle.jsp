<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- BEGIN Breadcrumb -->
                <div id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">Home</a>
                            <span class="divider"><i class="fa fa-angle-right"></i></span>
                        </li>
                        <li class="active">Hierarchy Title Master</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->
                
<c:if test="${!empty title}">
<div class="panel panel-bd">
                        <div class="panel-heading">
<div class="panel-title">
    <h4 class="card-heading"> Edit Hierarchy Title</h4>                                
</div>
</div>
  <div class="panel-body">
<form class="form-horizontal" method="post" id="editTitle">
 <div class="form-group col-md-9">                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Hierarchy Title<sup>*</sup></label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" name="title" id="title" placeholder="Hierarchy Title" class="form-control" value='${title.hierarchy_title}'/>                                      
     </div>                                                                                                    
</div>                                                                   

<div class="form-group col-md-9">                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Parent Title</label>                                    
   <div class="col-sm-4 col-lg-5 controls">
      <select class="form-control chosen" id="parent_title" data-placeholder="Choose a Category" tabindex="1">
      <option value="0"> </option>  
      <c:forEach items="${titles}" var="parenttitle">
      <c:choose>
      <c:when test="${parenttitle.hierarchy_type_id==title.parent_title_id }">
      <option value="${parenttitle.hierarchy_type_id}" selected>${parenttitle.hierarchy_title}</option>
      </c:when>
      <c:otherwise>
        <option value="${parenttitle.hierarchy_type_id}">${parenttitle.hierarchy_title}</option>
        </c:otherwise>
        </c:choose>
      </c:forEach>                                                     
   </select>                                                                             
</div>                                                                                                    
</div>                                                                   
<div class="form-group col-md-9">                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Title Description</label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" id="title_desc" placeholder="Title Description" class="form-control" value='${title.title_desc}'/>
    </div>                                                                                                    
</div>

<div class="form-group col-md-9">                                                                                         
                        <label for="password1" class="col-sm-3 col-lg-2 control-label">Active Status</label>                              
                         <div class="col-sm-4 col-lg-5 controls"> 
                            <select class="form-control chosen"  name="status"  id="status"  tabindex="1">
                               
                                <option value="1" <c:if test="${title.active_flg==1 }">selected</c:if>>Active </option>
                                <option value="0" <c:if test="${title.active_flg==0 }">selected</c:if> >Inactive </option> 
                                                             
                             </select>                  
                        </div>                                                                                                       
</div>  
<div class="form-group col-md-9"> 
<div class="col-md-5 col-md-offset-2 controls">
   <label class="checkbox-inline">
      <input type="checkbox" id="location" value="1" <c:if test="${title.device_location==1 }">checked</c:if>> Device/Meter location
   </label>  
</div> 
</div>                                                                 
<div class="form-group col-md-9"> 
<c:choose> 
    <c:when test='${clientId eq 0}'>                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Client</label>                                    
   <div class="col-sm-4 col-lg-5 controls">
      <select class="form-control chosen" id="client" data-placeholder="Choose a Category"  tabindex="1">
      <option value=""> </option>  
      <c:forEach items="${clients}" var="client">
      <c:choose>
        <c:when test="${client.client_id==title.client_id}">
        <option value="${client.client_id}" selected>${client.client_name}</option>
        </c:when>
        <c:otherwise>
        <option value="${client.client_id}">${client.client_name}</option>
        </c:otherwise>
        </c:choose>
      </c:forEach>                                                     
                                          
   </select>                                                                             
</div>
 </c:when><c:otherwise>
<div id="clientdiv"><input type="hidden" value="${clientId}" id="client"/></div>
</c:otherwise>
</c:choose>                                                                                                             
</div> 
<!-- <div class="form-group">                                                                                      -->
<!--    <label class="col-sm-3 col-lg-2 control-label">Active Status</label>                                     -->
<!--    <div class="col-sm-4 col-lg-5 controls"> -->
<!--       <select class="form-control chosen" id="status" data-placeholder="Choose a Category" tabindex="1"> -->
<!--       <option value=""> </option> -->
<!--       <option value="0">Inactive</option>   -->
<!--       <option value="1">Active</option>             -->
<!--    </select>                                                                              -->
<!-- </div>                                                                                                    -->
<!-- </div>                                                                                                                      -->
                                                                 
  <div class="form-group col-md-9">                                                                         
        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                           
           <button type="button" class="btn btn-primary" onclick="updateTitle('${title.hierarchy_type_id}')"><i class="fa fa-check" ></i> Update</button> 
           <button type="button" class="btn" onclick="viewTitles(${actionId})">Cancel</button>                                       
        </div>                                                                                     
    </div>                                                                                         
 </form>                                                                   
</div>
</div>
</c:if>