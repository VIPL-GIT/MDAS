<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- BEGIN Breadcrumb -->
                <div id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">Home</a>
                            <span class="divider"><i class="fa fa-angle-right"></i></span>
                        </li>
                        <li class="active">Device Type Master</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->
                
<div class="panel panel-bd">
                        <div class="panel-heading">                                                                                                         
    <div class="panel-title">                                                                                                          
        <h4 class="card-heading"> Edit Device Type</h4>                                                                          
                                                                                                                              
    </div> 
    </div>                                                                                                                          
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="deviceType">                                                                                   
            <div class="row">                                                                                                  
                                                                                                                               
               <div class="col-md-6 ">                                                                                               
                 <c:choose> 
    <c:when test='${clientId eq 0}'>                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Client</label>                                    
   <div class="col-sm-4 col-lg-5 controls">
      <select class="form-control chosen" id="clientId" data-placeholder="Choose a Category"  tabindex="1">
      <option value=""> </option>  
      <c:forEach items="${clients}" var="client">
      <c:choose>
        <c:when test="${client.client_id==device.clientId}">
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
<div id="clientdiv"><input type="hidden" value="${clientId}" id="clientId"/></div>
</c:otherwise>
</c:choose> 
  
  <div class="form-group col-md-12">                                                                                         
                        <label  class="col-xs-4 col-lg-5 control-label">DeviceType Name</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  id="type" name="type"  placeholder="DeviceType Name" class="form-control" value="${device.deviceTypeName}">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group col-md-12">                                                                                         
                        <label  class="col-xs-4 col-lg-5 control-label">Active Status</label>                              
                         <div class="col-sm-4 col-lg-5 controls"> 
                            <select class="form-control chosen"  name="status"  id="status"  tabindex="1">
                               
                                <option value="1" <c:if test="${device.activeFlg==1 }">selected</c:if>>Active </option>
                               
                                <option value="0" <c:if test="${device.activeFlg==0 }">selected</c:if> >Inactive </option> 
                                                             
                             </select>                  
                        </div>                                                                                                       
</div>
                                                                                                                                 
                    <div class="form-group">                                                                                         
                        <div class="col-sm-8 col-sm-offset-4 col-lg-7 col-lg-offset-5">                                             
                           <button type="button" class="btn btn-primary" onclick="updateDeviceType(${device.deviceTypeId})"><i class="fa fa-check"></i> Update</button>                   
                           <button type="button" class="btn" onclick="viewDeviceType(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>                                                                                                           
                  <!-- END Right Side -->                                                                                            
               </div> 

            </div>                                                                                                                   
         </form>                                                                                                                     
    </div>                                                                                                                           
</div> 
                                                                                                                       
