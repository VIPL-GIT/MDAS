<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
        <h4>Add Meter</h4>                                                                          
                                                                                                                              
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="addMeter">                                                                                   
            <div class="row">                                                                                                        
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Meter Sl No<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="meter_sl_no" id="meter_sl_no" placeholder="meter sl no" class="form-control">      
                            <input type="hidden" id="location_id" name="location_id" value="0" class="form-control"/>
                        </div>                                                                                                       
                    </div>      
                                                             
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Modem Sl No<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="modem_sl_no" id="modem_sl_no" placeholder="modem sl no" class="form-control">
                        </div>                                                                                                       
                    </div>
                    
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Meter CT</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="me_ct" id="me_ct" placeholder="me ct" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Meter PT</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="me_pt" id="me_pt" placeholder="me pt" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Adopted CT</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="ad_ct" id="ad_ct" placeholder="ad ct" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Adopted PT</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="ad_pt" id="ad_pt" placeholder="ad pt" class="form-control">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">MF</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="mf" id="mf" placeholder="mf" class="form-control" >
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Meter Type</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
<!--                             <input type="text"  name="meter_type" id="meter_type" placeholder="type" class="form-control" > -->
<select class="form-control chosen" name="meter_type" id="meter_type" data-placeholder="Choose a Category" tabindex="1">
      <option value="DLMS">DLMS </option>  
      <option value="NDLMS">NDLMS </option>
   </select> 
                        </div>                                                                                                       
                    </div> 
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Meter Make</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <select class="form-control chosen" name="metermake" id="metermake" data-placeholder="Choose a Category" tabindex="1">
      <option value="0"> </option>  
      <c:forEach items="${metermakes}" var="mk" varStatus="loop">
        <option value="${mk.value}" >${mk.value}</option>
      </c:forEach>                                                     
                                          
   </select> 
                        </div>                                                                                                       
                    </div>
<!--                     <div class="form-group">                                                                                          -->
<!--                         <label for="password1" class="col-xs-4 col-lg-5 control-label">Installation Date<sup>*</sup></label>                               -->
<!--                         <div class="col-sm-4 col-lg-5 controls"> -->
<!--   <div class="input-group input-append date" id="dateRangePicker"> -->
<!--     <input type="text" class="form-control" name="installation_date" id="installation_date"/> -->
<!--   <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span> -->
<!--             </div>                                                                                                                   -->
<!-- </div>                                                                                                        -->
<!--                     </div>                                                                                                            -->
                                                                                                                               
                                                                                                                               
                  <!-- END Left Side -->                                                                                             
               </div>                                                                                                                
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Right Side -->                                                                                          
                    <div class="form-group">                                                                                         
                        <label for="textfield2" class="col-xs-3 col-lg-3 control-label">Installation Point</label>                           
                        <div class="col-sm-4 col-lg-5 controls">
                        <!-- ${titles[fn:length(titles)-1].hierarchy_type_id } previous value for below onchange -->
                       
                            <select class="form-control chosen" name="meterlocation" onchange="getHierarchy(this.value)"  id="install_type" data-placeholder="Choose a Category" tabindex="1">
      <option value="0"> </option>  
      <c:forEach items="${installpoints}" var="type" varStatus="loop">
        <option value="${type.id}">${type.meter_type}</option>
      </c:forEach>                                                     
                                          
   </select>                                                                             
</div> 
</div> 
       <div id="hierarchy"></div>
       <div id="feederSel" ></div>
       <div id="childsSel" ></div>
                                                                                                                         
                                                                                                                             
                  <!-- END Right Side -->                                                                                            
               </div>                                                                                                                
            </div> 
            <div class="row" >
            <div class="form-group">                                                                                         
                        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
                           <button type="button" class="btn btn-primary" onclick="saveMeter()"><i class="fa fa-check"></i> Save</button>                   
                           <button type="button" class="btn" onclick="viewMeter(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>  
            </div>                                                                                                                  
         </form>                                                                                                                     
    </div>                                                                                                                           
</div>                                                                                                                               
