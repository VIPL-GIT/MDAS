<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <!-- BEGIN Breadcrumb -->
                <div id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">Home</a>
                            <span class="divider"><i class="fa fa-angle-right"></i></span>
                        </li>
                        <li class="active">Panels</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->
                
<div class="panel panel-bd">
                        <div class="panel-heading">                                                                                                         
    <div class="panel-title">                                                                                                          
        <h4> Add Panel</h4>                                                                          
                                                                 
        </div>                                                                                                                       
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="addLogger">                                                                                   
            <div class="row">                                                                                                        
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Panel Sl No<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="logger" id="logger" placeholder="Panel sl no" class="form-control">      
                        </div>                                                                                                       
                    </div>                                                                                                           
                    <div class="form-group">                                                                                     
   <label class="col-xs-4 col-lg-5 control-label">Hardware Version</label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" id="hw_version" placeholder="HW version" class="form-control" />                                                     
   </div>                                                                                                    
</div>                                                                   
<div class="form-group">                                                                                     
   <label class="col-xs-4 col-lg-5 control-label">Firmware Version</label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" id="fw_version" placeholder="FW version" class="form-control" />
    </div>                                                                                                    
</div>                                                                   
<div class="form-group">                                                                                     
   <label class="col-xs-4 col-lg-5 control-label">Mobile<sup>*</sup></label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" id="mobile" placeholder="mobile" class="form-control" data-rule-number="true" data-rule-required="true" data-rule-minlength="10"/>                                        
                                                            
   </div>                                                                                                    
</div> 
<div class="form-group">                                                                                     
   <label class="col-xs-4 col-lg-5 control-label">Installation Date<sup>*</sup></label>                                    
<!--    <div class="col-sm-4 col-lg-5 controls"> -->
  <div class="input-group input-append date col-sm-4 col-lg-5" id="dateRangePicker" style="margin-left:5px;">
    <input type="text" class="form-control" name="date" id="date"  data-rule-required="true" style="margin:0px !important;"/>
  <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar fa fa-calendar"></span></span>
            </div>                                                                                                                  
<!-- </div>  -->
</div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Lattitude<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="lattitude" id="lattitude" placeholder="lattitude" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password2" class="col-xs-4 col-lg-5 control-label">Longitude<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="longitude"  id="longitude" placeholder="longitude" class="form-control">
                        </div>                                                                                                       
                    </div> 
<!--                     <div class="form-group">                                                                                          -->
<!--                         <label for="password1" class="col-xs-4 col-lg-5 control-label">No.Of Lights<sup>*</sup></label>                               -->
<!--                         <div class="col-sm-4 col-lg-5 controls">                                                                     -->
<!--                             <input type="text"  name="noOfLights" id="noOfLights" placeholder="No.Of Lights" class="form-control"> -->
<!--                         </div>                                                                                                        -->
<!--                     </div> -->
<!--                     <div class="form-group">                                                                                          -->
<!--                         <label for="password2" class="col-xs-4 col-lg-5 control-label">Amp per Light<sup>*</sup></label>                               -->
<!--                         <div class="col-sm-4 col-lg-5 controls">                                                                     -->
<!--                             <input type="text" name="ampPerLight"  id="ampPerLight" placeholder="Amp per Light" class="form-control"> -->
<!--                         </div>                                                                                                        -->
<!--                     </div>  -->
                    
                    
                                                                                                                             
                                                                                                                               
                                                                                                                               
                  <!-- END Left Side -->                                                                                             
               </div>                                                                                                                
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Right Side -->                                                                                          
                    <div class="form-group">                                                                                         
                        <label for="textfield2" class="col-xs-3 col-lg-3 control-label">Location Type</label>                           
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen" onchange="getHierarchy(this.value)"  id="loc_type" data-placeholder="Choose a Category" tabindex="1">
      <option value="0"> </option>  
      <c:forEach items="${titles}" var="title" varStatus="loop">
        <option value="${title.hierarchy_type_id}">${title.hierarchy_title}</option>
      </c:forEach>                                                     
                                          
   </select>                                                                             
</div> 
</div> 
       <div id="hierarchy" class="col-md-12">
       </div>                                                                                                                
                                                                                                                             
                  <!-- END Right Side -->                                                                                            
               </div>                                                                                                                
           
<!--             <div class="row" > -->
            <div class="form-group col-md-12">                                                                                         
                        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
                           <button type="button" class="btn btn-primary" onclick="saveLogger()"><i class="fa fa-check"></i> Save</button>                   
                           <button type="button" class="btn" onclick="viewDataLogger(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>  
<!--             </div>                                                                                                                   -->
         </div> 
         </form>                                                                                                                     
    </div>                                                                                                                           
</div>                                                                                                                               
