<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.rtdas.common.service.*" %>
<!-- in this page we are getting hierarchy data based on the selection of parent hierarchy -->
<%@page import="com.rtdas.common.models.*" %>
<%@page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="assets/plugins/jquery.sumoselect/sumoselect.min.css" rel="stylesheet" type="text/css"/>

<%

int roleid=Integer.parseInt(request.getParameter("roleid"));
int level=0;
String levelType="";
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
HierarchyTitleService tService = (HierarchyTitleService) wac.getBean("titleService");
HierarchyService hService = (HierarchyService) wac.getBean("hierarchyService");
UserRoleService roleService = (UserRoleService) wac.getBean("roleService");
UserRole userrole=roleService.getUserRole(roleid);
Integer hierarchyLevelId=userrole.getHierarchy_level();

if(hierarchyLevelId!=null){
	%>
	<input type="hidden" id="hlevelid" value="<%=hierarchyLevelId%>"/>
	<%
String clientId=session.getAttribute("clientId").toString();
List<HierarchyTitle> tList=tService.listTitles(Integer.parseInt(clientId));
HierarchyTitle firstTitle=(HierarchyTitle)tList.get(0);
List<Hierarchy> firstHierarchy=hService.getHierarchyByType(firstTitle.getHierarchy_type_id());
for(int i=0;i<tList.size();i++){
	HierarchyTitle htitle=(HierarchyTitle)tList.get(i);
	if(hierarchyLevelId==htitle.getHierarchy_type_id()){
		levelType=htitle.getHierarchy_title();
		level=i+1;
	}
}
%>
   <input type="hidden" id="hlevel" value="<%=level%>"/>
    <input type="hidden" id="hleveltype" value="<%=levelType%>"/>
     <%for(int i=0;i<level;i++){
    	 HierarchyTitle title=(HierarchyTitle)tList.get(i);
    	 %>
    	 <div class="form-group col-md-12">                                                                                         
            <label for="textfield2" class="col-xs-3 col-lg-2 control-label"><%=title.getHierarchy_title() %></label>                           
            <div class="col-sm-4 col-lg-5 controls">
                <select class="form-control chosen level_<%=i+1%>" onchange="getHierarchyData(this.value,'level_<%=i+2%>')"  
                id="level_<%=i+1%>" data-placeholder="Choose a Category" tabindex="1" <%=(i==(level-1)?"multiple":"") %>>
                  <option value="0"> </option> 
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
    
     }
}
     %>                                        
 <script src="assets/plugins/jquery.sumoselect/jquery.sumoselect.min.js"></script>
    