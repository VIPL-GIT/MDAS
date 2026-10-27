<%@page import="java.util.*" %>
<%@page import="java.util.Map.Entry.*" %>
<%@page import="com.rtdas.common.beans.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String  clientImg=session.getAttribute("clientImg")==null?"":session.getAttribute("clientImg").toString();
String  clientId=session.getAttribute("clientId")==null?"":session.getAttribute("clientId").toString();
int client=clientId==null?0:Integer.parseInt(clientId);
if(clientImg.length()>0) 
	clientImg=clientImg.substring(clientImg.indexOf("img"));
else
	clientImg="img/hmws.png";
//System.out.println("----------------->"+clientImg);
%>

<aside class="main-sidebar">
                    <!-- sidebar -->
                    <div class="sidebar">
                        <!-- sidebar menu -->
                        <ul class="sidebar-menu">
                         <%if(client>0){ %>   <!-- if not master admin,display dashbord -->
<!--                             <li class="treeview active"> -->
<!--                                 <a href="javascript:viewMeter360Degree(56)"> -->
<!--                                     <i class="ti-home"></i><span>Dashboard</span> -->
                                    
                                    
<!--                                 </a> -->
<!--                                 </li> -->
<!--                                 <a href="#" > -->
<!-- 		                            <i class="ti-home"></i>  -->
<!-- 		                            <span>Dashboard</span> -->
<!-- 		                            <span class="pull-right-container"> -->
<!-- 		                                <i class="fa fa-angle-left pull-right"></i> -->
<!-- 		                             </span> -->
<!-- 		                        </a> -->
<!--                                 <ul class="treeview-menu"> -->
<!--                                     <li ><a href="javascript:getDashBoard(48)">Dashboard One</a></li> -->
<!--                                     <li class="active"><a href="javascript:getDashBoard_1(67)">Dashboard Two</a></li> -->
                                    
<!--                                 </ul> -->

                            
<!-- <li class="treeview"> -->
<!--                 <a href="javascript:getIdlDashboard(115)"> -->
<!--                     <i class="fa fa-edit"></i>  -->
<!--                     <span>IDL Status</span>                     -->
<!-- </a> -->
<!-- </li> -->
<!-- <li class="treeview"> -->
<!-- 	<a href="#"> -->
<!-- 	    <i class="fa fa-desktop"></i>  -->
<!-- 	    <span>VEE</span> -->
<!-- 	    <span class="pull-right-container"> -->
<!-- 		<i class="fa fa-angle-left pull-right"></i> -->
<!-- 	     </span> -->
<!-- 	</a> -->
	
<!-- 	<ul class="treeview-menu"> -->
<!-- 	<li><a href="javascript:veeSummary()">Summary</a></li> -->
<!-- 	<li><a href="javascript:veeDetailed()">Detailed</a></li>                                                   -->
<!-- 	<li><a href="javascript:viewRules()">Rules</a></li>                                                   -->

<!-- 	</ul> -->
	
<!-- </li>                             -->
<%
                 }
     List<ModuleSubModuleBean> modules=(List<ModuleSubModuleBean>)request.getAttribute("modules");
     Map<String,Map> mappedModules=(Map<String,Map>)request.getAttribute("mappedModules");
     System.out.println("------------>"+mappedModules);
     for(ModuleSubModuleBean sbm:modules){
    	 String module=sbm.getModule_title();    	 
    		//System.out.println(mappedModules+">>>>>======================>"+module); 
    	 String module_img=sbm.getModule_img();
    	 if(mappedModules.containsKey(module)){
    		 Map<String,String> subModules=mappedModules.get(module);
    		 if(Integer.parseInt(clientId)>0)
    			 subModules.remove("Client Master");
    		  if(subModules.size()>1){
    		 %>
    		 <li class="treeview">
                        <a href="#" >
                            <i class="fa <%=module_img%>"></i> 
                            <span><%=module %></span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                             </span>
                        </a>
                        <!-- BEGIN Submenu -->
                        <ul class="treeview-menu">  
                      <%for(Map.Entry em:subModules.entrySet()){
                    	  if(em.getValue()!=""){
                    	  %>                     
                                                  
                <li><a href="javascript:<%=em.getValue()%>"><%=em.getKey() %></a></li>               
                        
			    	<% } }%>
			    	</ul>
                        <!-- END Submenu -->
                    </li>
    	<%}else{ 
    	     
    	%>
    		<li class="treeview">
                <a href="javascript:<%=subModules.values().toArray()[0] %>" >
                    <i class="fa <%=module_img%>"></i> 
                    <span><%=module %></span>                    
             </a>
             </li>
    	<%}
    	
    	 }
    	 mappedModules.remove(module);
    	
     }
     %>                            

	                           
                        </ul>
                    </div> <!-- /.sidebar -->
                </aside>