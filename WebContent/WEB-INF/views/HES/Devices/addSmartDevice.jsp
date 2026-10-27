<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.form-control{
  height:25px !important;
  border:1px solid #a3a5ab;
}
.chosen{
padding:0px !important;
}
</style>
<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
        <h4>Add Smart Device</h4>                                                                          
                                                                                                                              
    </div>                                                                                                                           
<div class="panel-body">
<form  class="form-horizontal" method="post" id="addSmartDevice" autocomplete="off">                                                                                   
            <div class="row">                                                                                                        
 <div class="col-md-6 "> 
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Meter Sl.No</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="meter_serial_no" id="meter_serial_no" placeholder="meter" class="form-control">      
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Project</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
<!--          <input type="text" name="project" id="project" placeholder="name" class="form-control">   -->
<select class="form-control chosen" onchange="getProjectDetails(this.value)" name="project"  id="project" data-placeholder="Choose a Category" tabindex="1">
      <option value=""> </option>
      <c:forEach items="${projects}" var="proj">      
        <option value="${proj.project_id}">${proj.project_name}</option>        
      </c:forEach>                       
   </select>              
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Manufacturer</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="manufacturer" id="manufacturer" placeholder="name" class="form-control">      
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Flag</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="flag_id" id="flag_id" placeholder="flag" class="form-control">      
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">System Title</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="system_title" id="system_title" placeholder="title" class="form-control">      
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Meter Version</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="meter_version" id="meter_version" placeholder="version" class="form-control">      
     </div>                                                                                                       
 </div>
 </div>
 <div class="col-md-6">
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Manufacturing Year</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="year_of_manufacture" id="year_of_manufacture" placeholder="year" class="form-control">      
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Meter Category</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
<!--          <input type="text" name="meter_category" id="meter_category" placeholder="category" class="form-control"> -->
<select class="form-control chosen"  name="meter_category"  id="meter_category" data-placeholder="Choose a Category" tabindex="1">
<!--       <option value=""> </option> -->
      <c:forEach items="${metercategory}" var="cat">      
        <option value="${cat}">${cat}</option>        
      </c:forEach>                       
   </select>               
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Meter Type</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
<!--          <input type="text" name="meter_type" id="meter_type" placeholder="type" class="form-control"> -->
<select class="form-control chosen"  name="meter_type"  id="meter_type" data-placeholder="Choose a Category" tabindex="1">
<!--       <option value=""> </option> -->
      <c:forEach items="${metertype}" var="type">      
        <option value="${type}">${type}</option>        
      </c:forEach>                       
   </select>               
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">CTR</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="ctr" id="ctr" placeholder="ctr" class="form-control">      
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">PTR</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="ptr" id="ptr" placeholder="ptr" class="form-control">      
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">is Netmeter</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
<!--          <input type="text" name="netmeter" id="netmeter" placeholder="year" class="form-control"> -->
<select class="form-control chosen"  name="netmeter"  id="netmeter" data-placeholder="Choose a Category" tabindex="1">
<!--       <option value=""> </option> -->
      <c:forEach items="${netmeter}" var="net">      
        <option value="${net.key}">${net.value}</option>        
      </c:forEach>                       
   </select>                
     </div>                                                                                                       
 </div>
 </div>
 
               </div>
<hr>
<div class="row">
<div class="col-md-6">
<div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">IP Address Type</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
<!--          <input type="text" name="ipaddress_type" id="ipaddress_type" placeholder="year" class="form-control"> -->
<select class="form-control chosen"  name="ipaddress_type"  id="ipaddress_type" data-placeholder="Choose a Category" tabindex="1">
<!--       <option value=""> </option> -->
      <c:forEach items="${iptype}" var="ip">      
        <option value="${ip}">${ip}</option>        
      </c:forEach>                       
   </select>               
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Destination Address</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="destination_ipaddress" id="destination_ipaddress" placeholder="" class="form-control">
               
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Port</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="listening_port" id="listening_port" placeholder="port" class="form-control" value="4059">      
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Channel Type</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
<!--          <input type="text" name="channel_type" id="channel_type" placeholder="type" class="form-control"> -->
<select class="form-control chosen"  name="channel_type"  id="channel_type" data-placeholder="Choose a Category" tabindex="1">
<!--       <option value=""> </option> -->
      <c:forEach items="${channeltype}" var="ct">      
        <option value="${ct}">${ct}</option>        
      </c:forEach>                       
   </select>               
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Addressing Scheme</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
<!--          <input type="text" name="addressing_scheme" id="addressing_scheme" placeholder="year" class="form-control"> -->
<select class="form-control chosen"  name="addressing_scheme"  id="addressing_scheme" data-placeholder="Choose a Category" tabindex="1">
<!--       <option value=""> </option> -->
      <c:forEach items="${addressScheme}" var="ads">      
        <option value="${ads.key}">${ads.value}</option>        
      </c:forEach>                       
   </select>               
     </div>                                                                                                       
 </div>
</div>
<div class="col-md-6">
<div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">BaudRate</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="baudrate" id="baudrate" placeholder="" class="form-control" value="9600">      
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Parity</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
         <input type="text" name="parity" id="parity" placeholder="" class="form-control" value="None">      
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Communication Type</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
<!--          <input type="text" name="communication_type" id="communication_type" placeholder="year" class="form-control" > -->
<select class="form-control chosen"  name="communication_type"  id="communication_type" data-placeholder="Choose a Category" tabindex="1">
      <option value="Cellular">Cellular </option>
      <option value="RF">RF </option>                       
   </select>               
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Gateway Sl.No</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
<!--          <input type="text" name="gateway_serial_no" id="gateway_serial_no" placeholder="year" class="form-control" > -->
<select class="form-control chosen"  name="gateway_serial_no" onchange="getMACAddress(this.value)" id="gateway_serial_no" data-placeholder="Choose a Category" tabindex="1">
      <option value="-1"> </option>
      <c:forEach items="${rflist}" var="gw">      
        <option value="${gw}">${gw}</option>        
      </c:forEach>                       
   </select>                
     </div>                                                                                                       
 </div>
 <div class="form-group">                                                                                         
     <label for="textfield1" class="col-xs-4 col-lg-5 control-label">MAC Address</label>                           
     <div class="col-sm-4 col-lg-5 controls">                                                                    
<!--          <input type="text" name="mac_address" id="mac_address" placeholder="" class="form-control" readonly> -->
<select class="form-control chosen"  name="mac_address"  id="mac_address" data-placeholder="Choose a Category" tabindex="1">
      <option value="-1"> </option>
                            
   </select>               
     </div>                                                                                                       
 </div>
</div>
</div>               
<div class="row" >
 <div class="form-group">                                                                                         
     <div class="col-sm-4 col-sm-offset-8 col-lg-4 col-lg-offset-8">                                             
        <button type="button" class="btn btn-primary" onclick="saveOrUpdateSmartDevice()"><i class="fa fa-check"></i> Save</button>                   
        <button type="button" class="btn" onclick="viewSmartDevices(${actionId})">Cancel</button>                                                         
     </div>                                                                                                       
  </div>  
</div>               
</form>
</div>
</div>
