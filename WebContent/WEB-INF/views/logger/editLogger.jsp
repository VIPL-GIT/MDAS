<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.rtdas.common.models.*" %>
<%@page import="com.rtdas.common.service.*" %>
<%@page import="java.util.*" %>

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
        <h4> Edit Panel</h4>
        </div>                                                                         
                                                                                                                              
    </div>                                                                                                                           
    <div class="panel-body"> 
    <c:if test="${! empty logger }" >                                                                                                  
        <form  class="form-horizontal" method="post" id="addLogger">                                                                                   
            <div class="row">                                                                                                        
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Panel Sl No<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="logger" id="logger" placeholder="logger sl no" class="form-control" value="${logger.logger_sl_no }">      
                        </div>                                                                                                       
                    </div>                                                                                                           
                    <div class="form-group">                                                                                     
   <label class="col-xs-4 col-lg-5 control-label">Hardware Version</label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" id="hw_version" placeholder="HW version" class="form-control" value="${logger.hw_version }" />                                                     
   </div>                                                                                                    
</div>                                                                   
<div class="form-group">                                                                                     
   <label class="col-xs-4 col-lg-5 control-label">Firmware Version</label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" id="fw_version" placeholder="FW version" class="form-control" value="${logger.fw_version }" />
    </div>                                                                                                    
</div>                                                                   
<div class="form-group">                                                                                     
   <label class="col-xs-4 col-lg-5 control-label">Mobile<sup>*</sup></label>                                    
   <div class="col-sm-4 col-lg-5 controls">                                                                 
      <input type="text" id="mobile" placeholder="mobile" class="form-control" data-rule-number="true" value="${logger.mobile }"
      data-rule-required="true" data-rule-minlength="10"/>                                        
                                                            
   </div>                                                                                                    
</div> 
<div class="form-group">                                                                                     
   <label class="col-xs-4 col-lg-5 control-label">Installation Date<sup>*</sup></label>                                    
<!--    <div class="col-sm-4 col-lg-5 controls"> -->
  <div class="input-group input-append date col-sm-4 col-lg-5" id="dateRangePicker" style="margin-left:5px;">
    <input type="text" class="form-control" name="date" id="date"  data-rule-required="true" style="margin:0px !important;" value="${logger.delivery_date }" />
  <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar fa fa-calendar"></span></span>
            </div>                                                                                                                  
<!-- </div>  -->
</div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Lattitude<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="lattitude" id="lattitude" placeholder="lattitude" class="form-control" value="${logger.lattitude }" >
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password2" class="col-xs-4 col-lg-5 control-label">Longitude<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="longitude"  id="longitude" placeholder="longitude" class="form-control" value="${logger.longitude }" >
                        </div>                                                                                                       
                    </div> 
<!--                     <div class="form-group">                                                                                          -->
<!--                         <label for="password1" class="col-xs-4 col-lg-5 control-label">No.Of Lights<sup>*</sup></label>                               -->
<!--                         <div class="col-sm-4 col-lg-5 controls">                                                                     -->
<%--                             <input type="text"  name="noOfLights" id="noOfLights" placeholder="No.Of Lights" class="form-control" value="${logger.no_of_lights }"> --%>
<!--                         </div>                                                                                                        -->
<!--                     </div> -->
<!--                     <div class="form-group">                                                                                          -->
<!--                         <label for="password2" class="col-xs-4 col-lg-5 control-label">Amp per Light<sup>*</sup></label>                               -->
<!--                         <div class="col-sm-4 col-lg-5 controls">                                                                     -->
<%--                             <input type="text" name="ampPerLight"  id="ampPerLight" placeholder="Amp per Light" class="form-control" value="${logger.amp_per_light }"> --%>
<!--                         </div>                                                                                                        -->
<!--                     </div>  -->
                    
                                                                                                                             
                                                                                                                               
                                                                                                                               
                  <!-- END Left Side -->                                                                                             
               <%
       WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
       
       HierarchyService hService = (HierarchyService) wac.getBean("hierarchyService");       
       List<HierarchyTitle> tList=(List<HierarchyTitle>)request.getAttribute("titles");
       List<HierarchyTitle> locationTList=(List<HierarchyTitle>)request.getAttribute("locationtitles");
       HierarchyTitle firstTitle=(HierarchyTitle)tList.get(0);
       List<Hierarchy> firstHierarchy=hService.getHierarchyByType(firstTitle.getHierarchy_type_id());
       int level=0,locLevelId=0;
       Object[] obj=null;
       if(request.getAttribute("level")!=null){
        level=Integer.parseInt(request.getAttribute("level").toString());
        locLevelId=Integer.parseInt(request.getAttribute("locLevelId").toString());
       List<Object[]> currentHierarchy=(List<Object[]>)request.getAttribute("currentHierarchy");
       obj=currentHierarchy.get(0);
       }
       %>
          </div>                                                                                                                
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Right Side -->                                                                                          
                    <div class="form-group">                                                                                         
                        <label for="textfield2" class="col-xs-3 col-lg-3 control-label">Location Type</label>                           
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen" onchange="getHierarchy(this.value)"  id="loc_type" data-placeholder="Choose a Category" tabindex="1">
      <option value="0"> </option>  
      <%for(int i=0;i<locationTList.size();i++){ 
       if(locationTList.get(i).getHierarchy_type_id()==locLevelId){
      %>
    	  <option value="<%=locationTList.get(i).getHierarchy_type_id()%>" selected><%=locationTList.get(i).getHierarchy_title() %> </option> 
    <%  }else{ %>
    <option value="<%=locationTList.get(i).getHierarchy_type_id()%>"><%=locationTList.get(i).getHierarchy_title() %> </option> 
    	
   <% }}
      %>                                                     
                                          
   </select>                                                                             
</div> 
</div> 
       <div id="hierarchy" class="col-md-12">
       
       <%
       if(request.getAttribute("level")!=null){
    	  %> <input type="hidden" id="level" value="<%=level%>"/>
    	   
      <% }
       for(int i=0;i<level;i++){
    	 HierarchyTitle title=(HierarchyTitle)tList.get(i);
    	%>
    	 <div class="form-group col-md-12">                                                                                         
            <label for="textfield2" class="col-xs-3 col-lg-3 control-label"><%=title.getHierarchy_title() %></label>                           
            <div class="col-sm-4 col-lg-5 controls">
                <select class="form-control chosen" onchange="getHierarchyData(this.value,'level_<%=i+2%>')"  
                id="level_<%=i+1%>" data-placeholder="Choose a Category" tabindex="1">
                  <option value="<%=obj[i*3+1] %>"><%=obj[i*2+i] %> </option> 
                  <%if(i==0){
                	  for(int l=0;l<firstHierarchy.size();l++){
                		  Hierarchy hie=(Hierarchy)firstHierarchy.get(l);
                	  %>
                	  <option value="<%=hie.getHierarchy_id()%>"><%=hie.getHierarchy_name()%> </option>
               <%  } }
                  %> 
                 </select>                                                                             
           </div> 
        </div> 
    <% }
     %>   
       </div>                                                                                                             
                                                                                                                             
                  <!-- END Right Side -->                                                                                            
               </div>                                                                                                                
           
<!--             <div class="row" > -->
            <div class="form-group col-md-12">                                                                                         
                        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
                           <button type="button" class="btn btn-primary" onclick="updateLogger('${logger.logger_id}')"><i class="fa fa-check"></i> Update</button>                   
                           <button type="button" class="btn" onclick="viewDataLogger(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>  
<!--             </div>                                                                                                                   -->
         </div> 
         </form>  
         </c:if>
         <c:if test="${empty logger }">
         <h4>No Data Available</h4>
         </c:if>                                                                                                                   
    </div>                                                                                                                           
</div>                                                                                                                               
