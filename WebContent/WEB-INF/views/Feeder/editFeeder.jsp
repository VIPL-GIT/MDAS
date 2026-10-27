<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.rtdas.common.models.*" %>
<%@page import="com.rtdas.common.service.*" %>
<%@page import="java.util.*" %>
<div class="panel panel-bd">                                                                                                          
    <div class="panel-heading">                                                                                                          
        <h4>Edit Feeder</h4>                                                                          
                                                                                                                              
    </div>                                                                                                                           
    <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="addFeeder">                                                                                   
            <div class="row">                                                                                                        
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Feeder Name</label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="hidden" name="feederid" id="feeder_id" placeholder="feederid" class="form-control" value="${feeder.feederid }">
                            <input type="text" name="feedername" id="feeder_name" placeholder="feeder name" class="form-control" value="${feeder.feedername }">     
                        </div>                                                                                                       
                    </div>   
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Voltage Level</label>                              
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen"  name="voltage_level"  id="voltage_level"  tabindex="1">
                                <option value="-">-</option>                               
                                <option value="11Kv" <c:if test="${feeder.voltage_level.contains('11') }">selected</c:if>>11Kv </option>                               
                                <option value="33Kv" <c:if test="${feeder.voltage_level.contains('33') }">selected</c:if> >33Kv </option>
                             </select> 
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Feeder Type</label>                              
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen"  name="feeder_type"  id="feeder_type"  tabindex="1">
                                <option value="-">-</option>                               
                                <option value="AG" <c:if test="${feeder.feeder_type.equals('AG') }">selected</c:if>>AG </option>                               
                                <option value="NON AG" <c:if test="${feeder.feeder_type.equals('NON AG') }">selected</c:if> >NON AG </option>
                                <option value="IN" <c:if test="${feeder.feeder_type.equals('IN') }">selected</c:if>>IN </option>
                                <option value="OUT" <c:if test="${feeder.feeder_type.equals('OUT') }">selected</c:if>>OUT </option>
                             </select> 
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Conductor</label>                              
                        <div class="col-sm-4 col-lg-5 controls">
                            <select class="form-control chosen"  name="conductor"  id="conductor"  tabindex="1"> 
                                <option value="-">-</option>                              
                                <option value="Dog" <c:if test="${feeder.conductor.equals('Dog') }">selected</c:if>>Dog </option>                               
                                <option value="Panther" <c:if test="${feeder.conductor.equals('Panther') }">selected</c:if> >Panther </option>
                                <option value="Rabbit" <c:if test="${feeder.conductor.equals('Rabbit') }">selected</c:if> >Rabbit </option>
                                <option value="Raccoon" <c:if test="${feeder.conductor.equals('Raccoon') }">selected</c:if> >Raccoon </option>
                                <option value="Squirrel" <c:if test="${feeder.conductor.equals('Squirrel') }">selected</c:if> >Squirrel </option>
                                <option value="Weasel" <c:if test="${feeder.conductor.equals('Weasel') }">selected</c:if> >Weasel </option>
                             </select> 
                        </div>                                                                                                       
                    </div>
                                                                                                                            
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Structure Code</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="structure_code" id="structure_code" placeholder="code" class="form-control" value="${feeder.structure_code }">
                        </div>                                                                                                       
                    </div>
                    
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">No.of Consumers</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="no_of_consumers" id="no_of_consumers" placeholder="" class="form-control" value="${feeder.no_of_consumers }">
<%--                             <input type="hidden"  name="conductor" id="conductor" placeholder="voltage" class="form-control" value="${feeder.conductor }"> --%>
<%--                             <input type="hidden"  name="feeder_type" id="feeder_type" placeholder="voltage" class="form-control" value="${feeder.feeder_type }"> --%>
                        </div>                                                                                                       
                    </div>
                                                                                          
                  <!-- END Left Side -->                                                                                             
               </div> 
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
            </div> 
            <div class="row" >
            <div class="form-group">                                                                                         
                        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
                           <button type="button" class="btn btn-primary" onclick="saveOrUpdateFeeder()"><i class="fa fa-check"></i> Save</button>                   
                           <button type="button" class="btn" onclick="viewFeeder(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>  
            </div>                                                                                                                  
         </form>                                                                                                                     
    </div>                                                                                                                           
</div>                                                                                                                               
