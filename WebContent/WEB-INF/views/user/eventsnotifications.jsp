<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="fa fa-home"></i> <a href="#">Home</a> <span
				class="divider"><i class="fa fa-angle-right"></i></span></li>
			<li class="active">Events & Notifications</li>
		</ul>
	</div>


<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
                                                                                                                     
        <h3 class="panel-title"><i class="fa fa-bars"></i> Edit Event & Notification</h3>                                                                          
                                                                                                                               
    </div>                                                               
                                                                                                                               
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="param">                                                                                   
            <div class="row">                                                                                                  
                                                                                                                               
                                                                                                          
                  <div class="form-group col-md-8">                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Select User :</label>                                    
   <div class="col-sm-4 col-lg-5 controls">
   <input type="text"  id="username" name="username"  placeholder="User Name" class="form-control" value="${usersNotefication}"> 
</div>
</div>


  <div class="form-group col-md-8">
 <label class="col-sm-3 col-lg-2 control-label">Type of Alert :</label>                                    
   <div class="col-sm-4 col-lg-5 controls">
      <select class="form-control chosen" data-placeholder="Choose a Category" name="alerttype" id="alerttype" tabindex="1" onchange="">
      <option value="0"> </option>  
      <c:forEach items="${alertType}" var="alertType">
      <c:choose>
       <c:when test="${alertType.alert_type==alerttype}">
     
       <option value="${alertType.alert_type}" selected>${alertType.alert_type}</option>
       </c:when>
       <c:otherwise>
       <option value="${alertType.alert_type}">${alertType.alert_type}</option>
       </c:otherwise>
       </c:choose>
      </c:forEach>    
   </select>                                                                             
</div>
 </div>
         
                  
        <div class="form-group col-md-8">
 <label class="col-sm-3 col-lg-2 control-label">Alert Mode :</label>                                    
   <div class="col-sm-4 col-lg-5 controls">
      <select class="form-control chosen" data-placeholder="Choose a Category" name="alertmode" id="alertmode" tabindex="1" onchange="">
      <option value="0"> </option>  
      <c:forEach items="${alertMode}" var="alertMode">
      
        <c:choose>
       <c:when test="${alertMode.alert_mode_Type==alertmode}">
         <% System.out.println(" when condition "); %>
     <option value="${alertMode.alert_mode_Type}" selected>${alertMode.alert_mode_Type}</option>
       </c:when>
       <c:otherwise>
       <option value="${alertMode.alert_mode_Type}">${alertMode.alert_mode_Type}</option>
       </c:otherwise>
       </c:choose>
      </c:forEach>                                                     
   </select>                                                                             
</div>
 </div>            
                
                
     <div class="form-group col-md-8">
   <label class="col-sm-3 col-lg-2 control-label">Is Enabled :</label>                                    
   <div class="col-sm-4 col-lg-5 controls">
   
      <select class="form-control chosen" data-placeholder="Choose a Category" name="isenabled" id="isenabled" tabindex="1" onchange="">
       <option value="0" <c:if test="${isenabled==0 }">selected</c:if>>True</option>
       <option value="1" <c:if test="${isenabled==1 }">selected</c:if>>False</option>
      </select>                           
                                                     
</div>
 </div>       
 
 
                 <input type="hidden"  id="alertid" name="alertid"  placeholder="User Name" class="form-control" value="${alertid}">
                
                    <div class="form-group col-md-6"> 
                    <div class="form-group">                                                                                         
                        <div class="col-sm-8 col-sm-offset-4 col-lg-7 col-lg-offset-5">                                             
                           <button type="button" class="btn btn-primary" onclick="saveEventNotification('${usersNotefication}','${alertmode}')"><i class="fa fa-check"></i> Save/Update</button>                   
                           <button type="button" class="btn" onclick="getEventNotification('50')">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>   
                    </div>                                                                                                        
                  <!-- END Right Side -->                                                                                            
             
            </div>                                                                                                                   
         </form>                                                                                                                     
    </div>                                                                                                                           
</div> 
                                                                                                                       
