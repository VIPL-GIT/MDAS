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
                                                                                                                     
        <h3 class="panel-title"><i class="fa fa-bars"></i> ADD  Event & Notification</h3>                                                                          
                                                                                                                               
    </div>                                                               
                                                                                                                               
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="param">                                                                                   
            <div class="row">                                                                                                  
                                                                                                                               
                                                                                           
                  <div class="form-group col-md-8">                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Select User :</label>                                    
   <div class="col-sm-4 col-lg-5 controls">
    <select class="form-control chosen" data-placeholder="Choose a Category" name="userid" id="userid" tabindex="1" onchange="">
      <option value=""> </option>  
      <c:forEach items="${user}" var="user">
       <option value="${user.userId}">${user.userId}</option>
      </c:forEach>                                                     
   </select>   
</div>
</div>


  <div class="form-group col-md-8">
 <label class="col-sm-3 col-lg-2 control-label">Type of Alert :</label>                                    
   <div class="col-sm-4 col-lg-5 controls">
      <select class="form-control chosen" data-placeholder="Choose a Category" name="alerttype" id="alerttype" tabindex="1" onchange="">
      <option value=""> </option>  
      <c:forEach items="${alertType}" var="alertType">
       <option value="${alertType.alert_type}">${alertType.alert_type}</option>
      </c:forEach>                                                     
   </select>                                                                             
</div>
 </div>
                  
                  
        <div class="form-group col-md-8">
 <label class="col-sm-3 col-lg-2 control-label">Alert Mode :</label>                                    
   <div class="col-sm-4 col-lg-5 controls">
      <select class="form-control chosen" data-placeholder="Choose a Category" name="alertmode" id="alertmode" tabindex="1" onchange="">
      <option value=""> </option>  
      <c:forEach items="${alertMode}" var="alertMode">
       <option value="${alertMode.alert_mode_Type}">${alertMode.alert_mode_Type}</option>
      </c:forEach>                                                     
   </select>                                                                             
</div>
 </div>            
                
                
     <div class="form-group col-md-8">
   <label class="col-sm-3 col-lg-2 control-label">Is Enabled :</label>                                    
   <div class="col-sm-4 col-lg-5 controls">
      <select class="form-control chosen" data-placeholder="Choose a Category" name="isenabled" id="isenabled" tabindex="1" onchange="">
      <option value="0" selected>True</option>
       <option value="1">False</option>
                                                     
   </select>                                                                             
</div>
 </div>       
                  <input type="hidden"  id="alertid" name="alertid"  placeholder="User Name" class="form-control" value="${alertid}">
             
                    <div class="form-group col-md-6"> 
                    <div class="form-group">                                                                                         
                        <div class="col-sm-8 col-sm-offset-4 col-lg-7 col-lg-offset-5">                                             
                           <button type="button" class="btn btn-primary" onclick="AddUser()"><i class="fa fa-check"></i>Add User</button>                   
                           <button type="button" class="btn" onclick="getEventNotification('50')">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>   
                    </div>                                                                                                        
                  <!-- END Right Side -->                                                                                            
             
            </div>                                                                                                                   
         </form>                                                                                                                     
    </div>                                                                                                                           
</div> 
                                                                                                                       
