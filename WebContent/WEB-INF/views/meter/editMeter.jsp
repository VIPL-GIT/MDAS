<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.rtdas.common.models.*" %>
<%@page import="com.rtdas.common.service.*" %>
<%@page import="java.util.*" %>
<div class="panel panel-bd">
                            <div class="panel panel-bd">
                                <div class="panel-heading">
                            <div class="panel-title">
                                <h4>Edit Meter</h4> 
                                </div>
                                </div>                               
                            </div>
                            <div class="panel-body">                                                                                                        
        <form  class="form-horizontal" method="post" id="editMeter">                                                                                   
            <div class="row">                                                                                                        
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Left Side -->                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="textfield1" class="col-xs-4 col-lg-5 control-label">Meter Sl No<sup>*</sup></label>                           
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="hidden" name="meter_id" id="meter_id" placeholder="meter_id" class="form-control" value="${meter.meter_id }">
                            <input type="hidden" name="location_id" id="location_id" placeholder="location_id" class="form-control" value="${meter.location_id }">
                            <input type="hidden" name="installation_date" id="installation_date" placeholder="installation_date" class="form-control" value="${meter.installation_date }">
                                                                   
                            <input type="text" name="meter_sl_no" id="meter_sl_no" placeholder="meter sl no" class="form-control" value="${meter.meter_sl_no }" readonly>      
                        </div>                                                                                                       
                    </div>                                                                                                           
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Modem Sl No<sup>*</sup></label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text" name="modem_sl_no" id="modem_sl_no" placeholder="modem sl no" class="form-control" value="${meter.modem_sl_no }"> 
                        </div>                                                                                                       
                    </div>
                    
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Meter CT</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="me_ct" id="me_ct" placeholder="me ct" class="form-control" value="${meter.me_ct }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Meter PT</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="me_pt" id="me_pt" placeholder="me pt" class="form-control" value="${meter.me_pt }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Adopted CT</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="ad_ct" id="ad_ct" placeholder="ad ct" class="form-control" value="${meter.ad_ct }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Adopted PT</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="ad_pt" id="ad_pt" placeholder="ad pt" class="form-control" value="${meter.ad_pt }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">MF</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <input type="text"  name="mf" id="mf" placeholder="mf" class="form-control" value="${meter.mf }">
                        </div>                                                                                                       
                    </div>
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Meter Type</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
<%--                             <input type="text"  name="meter_type" id="meter_type" placeholder="type" class="form-control" value="${meter.meter_type }"> --%>
                            <select class="form-control chosen" name="meter_type" id="meter_type" data-placeholder="Choose a Category" tabindex="1">
						      <option value="0">--select-- </option>  
						      
						        <option value="DLMS" ${meter.meter_type eq "DLMS"?"selected":""  }>DLMS</option>
						        <option value="NDLMS" ${meter.meter_type eq "NDLMS"?"selected":""  }>NDLMS</option>
						                                                        
						                                          
						   </select> 
                        </div>                                                                                                       
                    </div> 
                    <div class="form-group">                                                                                         
                        <label for="password1" class="col-xs-4 col-lg-5 control-label">Meter Make</label>                              
                        <div class="col-sm-4 col-lg-5 controls">                                                                    
                            <select class="form-control chosen" name="metermake" id="metermake" data-placeholder="Choose a Category" tabindex="1">
      <option value="0">--select-- </option>  
      <c:forEach items="${metermakes}" var="mk" varStatus="loop">
        <option value="${mk.value}" ${mk.value eq meter.metermake?"selected":""  }>${mk.value}</option>
      </c:forEach>                                                     
                                          
   </select> 
                        </div>                                                                                                       
                    </div>

                                                                                                                              
                                                                                                                               
                                                                                                                               
                  <!-- END Left Side -->  
<%
       WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
       
       HierarchyService hService = (HierarchyService) wac.getBean("hierarchyService");       
       List<HierarchyTitle> tList=(List<HierarchyTitle>)request.getAttribute("titles");
       List<HierarchyTitle> locationTList=(List<HierarchyTitle>)request.getAttribute("locationtitles");
       HierarchyTitle firstTitle=(HierarchyTitle)tList.get(0);
       List<Hierarchy> firstHierarchy=hService.getHierarchyByType(firstTitle.getHierarchy_type_id());
       int level=2,locLevelId=0;
       Object[] obj=null;
       System.out.println("-----------------------------1"+level);
       if(request.getAttribute("level")!=null){
    	   System.out.println("-----------------------------2");
        level=Integer.parseInt(request.getAttribute("level").toString());
        System.out.println("-----------------------------3");
        locLevelId=Integer.parseInt(request.getAttribute("locLevelId").toString());
       List<Object[]> currentHierarchy=(List<Object[]>)request.getAttribute("currentHierarchy");
       obj=currentHierarchy.get(0);
       }
       %>
          </div>                                                                                                                
               <div class="col-md-6 ">                                                                                               
                  <!-- BEGIN Right Side -->                                                                                          
                    <div class="form-group">                                                                                         
                        <label for="textfield2" class="col-xs-3 col-lg-3 control-label">Installation Point</label>                           
                        <div class="col-sm-4 col-lg-5 controls">
                       <!-- ${titles[level-1].hierarchy_type_id }  //previous one for the param for below onchange function -->
                            <select class="form-control chosen" name="meterlocation" onchange="getHierarchy(this.value)"    id="install_type" data-placeholder="Choose a Category" tabindex="1">
      <option value="0"> </option>  
      <c:forEach items="${installpoints}" var="type" varStatus="loop">
        <option value="${type.id}" ${type.id eq meter.meterlocation?"selected":""  }>${type.meter_type}</option>
      </c:forEach>                                                     
                                          
   </select>                                                                             
</div> 
</div> 
       <div id="hierarchy" class="col-md-12">
       
       <%
       if(request.getAttribute("level")!=null){
    	  %> <input type="hidden" id="level" value="<%=level%>"/>
    	   
      <% }
       if(obj!=null){
       for(int i=0;i<level;i++){
    	   
    	 HierarchyTitle title=(HierarchyTitle)tList.get(i);
    	%>
    	 <div class="form-group col-md-12">                                                                                         
            <label for="textfield2" class="col-xs-3 col-lg-3 control-label"><%=title.getHierarchy_title() %></label>                           
            <div class="col-sm-4 col-lg-5 controls">
           <% if(i+1==level) {%>
            <select class="form-control chosen" onchange="getChilds(this.value)"   
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
    <%
    System.out.println("-----------------------------4");
       }
       }
     %>   
       </div>
       <div id="feederSel" >
       <%if(request.getAttribute("feeder")!=null){ %>
         <div class="form-group">                                                                                         
            <label for="textfield2" class="col-xs-3 col-lg-3 control-label">Feeder</label>                           
            <div class="col-sm-4 col-lg-5 controls">
           
            <select class="form-control chosen" onchange="" id="childID" data-placeholder="Choose a Category" tabindex="1">
      <option value="${feeder.feederid }">${feeder.feedername } </option> 
                                           
</select>
</div>
</div>
<%} %>
       </div>
       <div id="childsSel" >
         <%if(request.getAttribute("consumer")!=null){ %>
         <div class="form-group">                                                                                         
            <label for="textfield2" class="col-xs-3 col-lg-3 control-label">Consumer</label>                           
            <div class="col-sm-4 col-lg-5 controls">
           
            <select class="form-control chosen" onchange="" id="childID" data-placeholder="Choose a Category" tabindex="1">
      <option value="${consumer.consumer_id }">${consumer.consumer_code } </option> 
                                           
</select>
</div>
</div>
<%} %>
       </div>
                                                                                                                       
                                                                                                                             
                  <!-- END Right Side -->                                                                                            
               </div>                                                                                                                 
            </div> 
            <div class="row" >
            <div class="form-group">                                                                                         
                        <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">                                             
                           <button type="button" class="btn btn-primary" onclick="updateMeter(${meter.meter_id})"><i class="fa fa-check"></i> Update</button>                   
                           <button type="button" class="btn" onclick="viewMeter(${actionId})">Cancel</button>                                                         
                        </div>                                                                                                       
                    </div>  
            </div>                                                                                                                  
         </form>                                                                                                                     
    </div>                                                                                                                           
</div>                                                                                                                               
