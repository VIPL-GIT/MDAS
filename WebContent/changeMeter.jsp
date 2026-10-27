<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import="utils.DBUtil"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>

<%
try{
	String meter=request.getParameter("meter");

%>
<div class="modal-content" style="margin-left:5%;margin-top:6%;">
    <span class="biclose">&times;</span>
    <center><h4>Change Meter ( <%=meter %> )</h4></center>  
<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
                                                                              
                                                                                                                              
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="changeMeterForm">                                                                                   
            <div class="row">                                                                                                        
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Meter Sl No<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="meter_sl_no" id="meter_sl_no" placeholder="meter sl no" class="form-control">
                            <input type="hidden" name="oldMeter" id="oldMeter" value="<%=meter%>">      
                            
                        </div>                                                                                                       
                    </div>      
                                                             
                   
                    
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Meter CT</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="me_ct" id="me_ct" placeholder="me ct" class="form-control" value="1">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Meter PT</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="me_pt" id="me_pt" placeholder="me pt" class="form-control" value="1">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Adopted CT</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="ad_ct" id="ad_ct" placeholder="ad ct" class="form-control" value="1">
                        </div>                                                                                                       
                    </div>
                    </div>
                    <div class="col-md-6 "> 
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Adopted PT</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="ad_pt" id="ad_pt" placeholder="ad pt" class="form-control" value="1">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">MF</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="mf" id="mf" placeholder="mf" class="form-control" value="1">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Meter Type</label>                              
                        <div class="col-sm-4 col-lg-5 controls">
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
						        <option value="NA"> </option>
						        <option value="NA">NA</option>
						        <option value="SECURE Non-DLMS">SECURE Non-DLMS</option>
						        <option value="LnT Non-DLMS">LnT Non-DLMS</option>      
						        <option value="SECURE DLMS">SECURE DLMS</option>      
						        <option value="LnT DLMS">LnT DLMS</option>      
						        <option value="GENUS DLMS">GENUS DLMS</option>      
						        <option value="HPL DLMS">HPL DLMS</option>
						   </select>
                        </div>                                                                                                       
                    </div>                                                                                           
               </div>                                                                                                                
                                                                                                                               
            </div> 
            <div class="row" >
            <div class="form-group">                                                                                         
                        <div class="col-sm-6 col-sm-offset-6 col-lg-6 col-lg-offset-6">                                             
                           <button type="button" class="btn btn-primary" onclick="changeMeterAction()"><i class="fa fa-check"></i> Update</button>                  
                                                                                    
                        </div>                                                                                                       
                    </div>  
            </div>                                                                                                                  
         </form>                                                                                                                     
    </div>                                                                                                                           
</div>  

    </div>
  <%}catch(Exception e){
	  
	  }%>  
  
<script>


</script>    