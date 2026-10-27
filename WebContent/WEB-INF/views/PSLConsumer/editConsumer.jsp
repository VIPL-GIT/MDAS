<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.rtdas.common.models.*" %>
<%@page import="com.rtdas.common.service.*" %>
<%@page import="java.util.*" %>
<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
        <h4>Edit PSL Consumer</h4>                                                                          
                                                                                                                              
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="addFeeder">                                                                                   
            <div class="row">                                                                                                        
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">PSL Name</label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="hidden" name="consumer_id" id="consumer_id" placeholder="name" class="form-control" value="${cc.consumer_id }">
                            <input type="text" name="consumer_name" id="consumer_name" placeholder="name" class="form-control" value="${cc.consumer_name }">      
                        </div>                                                                                                       
                    </div>                                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">PSL Code</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="consumer_code" id="consumer_code" placeholder="code" class="form-control" value="${cc.consumer_code }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Contract Load</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="contract_load" id="contract_load" placeholder="load" class="form-control" value="${cc.contract_load }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Agrmt from date</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="aggrement_start_date" id="aggrement_start_date" placeholder="Agrmt from date" class="form-control" value="${cc.aggrement_start_date }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Agrmt end date</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="aggrement_end_date" id="aggrement_end_date" placeholder="Agrmt end date" class="form-control" value="${cc.aggrement_end_date }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Contact No</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="mobile" id="mobile" placeholder="mobile" class="form-control" value="${cc.mobile }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Address</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="address" id="address" placeholder="address" class="form-control" value="${cc.address }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">E-mail</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="email_id" id="email_id" placeholder="mail" class="form-control" value="${cc.email_id }">
                        </div>                                                                                                       
                    </div>
                                                                                          
                  <!-- END Left Side -->                                                                                             
               </div>                                                                                                                
              <!-- ----------------------------------------------------------------------- -->
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
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Right Side -->                                                                                          
                    <div class="form-group">                                                                                         
                        <label for="textfield2" class="col-xs-3 col-lg-3 control-label">Hierarchy Level</label>                           
                        <div class="col-sm-4 col-lg-5 controls">
                        <input type="hidden" id="substation_id" name="substationid" value=""/>
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
       <div id="hierarchy">
      <input type="hidden" id="feeder_id" name="feeder_id" value=""/> 
       <%
       if(request.getAttribute("level")!=null){
    	  %> <input type="hidden" id="level" value="<%=level%>"/>
    	   
      <% }
       for(int i=0;i<level;i++){
    	 HierarchyTitle title=(HierarchyTitle)tList.get(i);
    	%>
    	 <div class="form-group">                                                                                         
            <label for="textfield2" class="col-xs-3 col-lg-3 control-label"><%=title.getHierarchy_title() %></label>                           
            <div class="col-sm-4 col-lg-5 controls">
                <%if(i+1==tList.size()) {%>
            <select class="form-control chosen" onchange="getFeeders(this.value,'feederSel')"   
                id="level_<%=i+1%>" data-placeholder="Choose a Category" tabindex="1">
            <%}else{ %>
                <select class="form-control chosen" onchange="getHierarchyData(this.value,'level_<%=i+2%>')"  
                id="level_<%=i+1%>" data-placeholder="Choose a Category" tabindex="1">
                <%} %>
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
     <div id="feederSel" >
     <div class="form-group">                                                                                         
            <label for="textfield2" class="col-xs-3 col-lg-3 control-label">Feeders</label>                           
            <div class="col-sm-4 col-lg-5 controls">
           
            <select class="form-control chosen" onchange="" id="feederID" data-placeholder="Choose a Category" tabindex="1">
          
    	  <option value="${feeder.feederid }">${feeder.feedername} </option> 
                                           
</select>
</div>
</div>
</div> 
       </div>                                                                                                                
                                                                                                                             
                  <!-- END Right Side -->                                                                                            
               </div>
<!-- ----------------------------------------------------------------------- -->                                                                                                               
            </div> 
            <div class="row" >
            <div class="form-group">                                                                                         
                        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
                           <button type="button" class="btn btn-primary" onclick="saveOrUpdatePSLConsumer()"><i class="fa fa-check"></i> Save</button>                   
                           <button type="button" class="btn" onclick="viewPSLConsumers(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>  
            </div>                                                                                                                  
         </form>                                                                                                                     
    </div>                                                                                                                           
</div>                                                                                                                               
