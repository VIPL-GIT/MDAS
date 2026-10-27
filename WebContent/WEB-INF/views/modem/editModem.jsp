<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty modem}">
<div class="panel panel-bd">
                            <div class="panel panel-bd">
                                <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Edit Modem</h4> 
                                </div>
                                </div>                               
                            </div>
                            <div class="panel-body">
<form  class="form-horizontal" method="post" id="editModem">
 <div class="form-group">                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Modem Serial No<sup>*</sup></label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" id="modemSlNo" placeholder="modem serial no" class="form-control" value="${modem.modemSlNo}" data-rule-required="true" data-rule-minlength="3"/>                                      
     </div>                                                                                                    
</div>                                                                   

<div class="form-group">                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Hardware Version</label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" id="hw_version" placeholder="HW version" class="form-control" value="${modem.hw_version}"/>                                                     
   </div>                                                                                                    
</div>                                                                   
<div class="form-group">                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Firmware Version</label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" id="fw_version" placeholder="FW version" class="form-control" value="${modem.fw_version}"/>
    </div>                                                                                                    
</div>                                                                   
<div class="form-group">                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Mobile<sup>*</sup></label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" id="mobile" placeholder="mobile" class="form-control" value="${modem.mobile}" data-rule-required="true" data-rule-number="true"  data-rule-minlength="10"/>                                        
                                                            
   </div>                                                                                                    
</div> 
<%-- <div class="form-group">                                                                                     
   <label class="col-sm-3 col-lg-2 control-label">Delivery Date<sup>*</sup></label>                                    
   <div class="col-sm-5 col-lg-3 controls">
  <div class="input-group input-append date" id="dateRangePicker">
                <input type="text" class="form-control" name="date" id="date" value="${modem.delivery_date}" data-rule-required="true" style="margin:0px !important;"/>
                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar fa fa-calendar"></span></span>
            </div>                                                                                                                  
</div>                                                                                                    
</div>   --%>                                                                 
  <div class="form-group">                                                                         
        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                           
           <button type="button" class="btn btn-primary" onclick="updateModem('${modem.modemId}')"><i class="fa fa-check"></i> Update</button> 
           <button type="button" class="btn" onclick="viewModem(${actionId})">Cancel</button>                                       
        </div>                                                                                     
    </div>                                                                                         
 </form>                                                                 
</div>
</div>
</c:if>