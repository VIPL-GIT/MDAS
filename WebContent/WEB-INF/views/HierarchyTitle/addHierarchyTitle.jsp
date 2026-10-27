<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page errorPage="../error.jsp" %>

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
                
<div class="panel panel-bd">
  <div class="panel-heading">
<div class="panel-title">
    <h4 class="card-heading"> Add Hierarchy Title</h4>                                
</div>
</div>
  <div class="panel-body">

<form class="form-horizontal" method="post" id="addTitle">
 <div class="form-group col-md-9">                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Hierarchy Title<sup>*</sup></label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" name="title" id="title" placeholder="Hierarchy Title" class="form-control" />                                      
     </div>                                                                                                    
</div>                                                                   

<div class="form-group col-md-9">                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Parent Title</label>                                    
   <div class="col-sm-4 col-lg-5 controls">
      <select class="form-control chosen" id="parent_title" data-placeholder="Choose a Category" tabindex="1">
      <option value="0"> </option>  
      <c:forEach items="${titles}" var="title">
        <option value="${title.hierarchy_type_id}">${title.hierarchy_title}</option>
      </c:forEach>                                                     
                                          
   </select>                                                                             
</div>                                                                                                    
</div>                                                                   
<div class="form-group col-md-9">                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Title Description</label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" id="title_desc" placeholder="Title Description" class="form-control" />
    </div>                                                                                                    
</div> 
<div class="form-group col-md-9"> 
<div class="col-md-5 col-md-offset-2 controls">
   <label class="checkbox-inline">

<!--    <div class="make-switch switch-mini"> -->
      <input type="checkbox" name="location" id="location" value="1"> Device/Meter location
<!--       </div> -->
   </label>  

</div> 
</div>                                                                 
<div class="form-group col-md-9"> 
 <c:choose> 
    <c:when test='${clientId eq 0}'>                                                                                    
   <label class="col-sm-3 col-lg-2 control-label">Client</label>                                    
   <div class="col-sm-4 col-lg-5 controls">
      <select class="form-control chosen" id="client" data-placeholder="Choose a Category" tabindex="1">
      <option value=""> </option>  
      <c:forEach items="${clients}" var="client">
        <option value="${client.client_id}">${client.client_name}</option>
      </c:forEach>                                                     
                                          
   </select>                                                                             
</div> 
 </c:when><c:otherwise>
<div id="clientdiv"><input type="hidden" value="${clientId}" id="client"/></div>
</c:otherwise>
</c:choose>                                                                                                          
</div>                                                                                                                      
                                                                 
  <div class="form-group col-md-9">                                                                         
        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                           
           <button type="button" class="btn btn-primary" onclick="saveTitle()"><i class="fa fa-check" ></i> Save</button> 
           <button type="button" class="btn" onclick="viewTitles(${actionId})">Cancel</button>                                       
        </div>                                                                                     
    </div>                                                                                         
 </form>                                                                                           
                                                             
   
</div>
</div>
