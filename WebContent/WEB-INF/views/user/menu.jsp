<%@page import="java.util.*" %>
<%@page import="java.util.Map.Entry.*" %>
<%@page import="com.rtdas.common.beans.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- BEGIN Sidebar -->
<div id="sidebar" class="site-menubar-inner">
                <!-- BEGIN Navlist -->
                <ul class="site-menu">
                    <!-- BEGIN Search Form -->
<!--                     <li> -->
<!--                         <form target="#" method="GET" class="search-form"> -->
<!--                             <span class="search-pan"> -->
<!--                                 <button type="submit"> -->
<!--                                     <i class="fa fa-search"></i> -->
<!--                                 </button> -->
<!--                                 <input type="text" name="search" placeholder="Search ..." autocomplete="off" /> -->
<!--                             </span> -->
<!--                         </form> -->
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
<%-- <img src="<%=clientImg %>" width="200" height="70"/> --%>
<!--                     </li> -->
                    <!-- END Search Form -->
                 <%if(client>0){ %>   <!-- if not master admin,display dashbord -->
                    <li class="active">
                        <a href="javascript: location.reload();">
                            <i class="fa fa-dashboard"></i> &nbsp;&nbsp;
                            <span>Dashboard</span>
                        </a>
                    </li>                    
     <%
                 }
     List<ModuleSubModuleBean> modules=(List<ModuleSubModuleBean>)request.getAttribute("modules");
     Map<String,Map> mappedModules=(Map<String,Map>)request.getAttribute("mappedModules");
     //System.out.println("------------>"+mappedModules);
     for(ModuleSubModuleBean sbm:modules){
    	 String module=sbm.getModule_title();    	 
    		//System.out.println(mappedModules+">>>>>======================>"+module); 
    	 String module_img=sbm.getModule_img();
    	 if(mappedModules.containsKey(module)){
    		 Map<String,String> subModules=mappedModules.get(module);
    		 if(Integer.parseInt(clientId)>0)
    			 subModules.remove("Client Master");
    		 %>
    		 <li>
                        <a href="#" >
                            <i class="fa <%=module_img%>"></i> &nbsp;&nbsp;
                            <span><%=module %></span>
<!--                             <b class="arrow fa fa-angle-right"></b> -->
                        </a>
                        <!-- BEGIN Submenu -->
                        <ul class="submenu">  
                      <%for(Map.Entry em:subModules.entrySet()){
                    	  if(em.getValue()!=""){
                    	  %>                     
                                                  
                <li><a href="javascript:<%=em.getValue()%>"><%=em.getKey() %></a></li>               
                        
    	<% } }%>
    	</ul>
                        <!-- END Submenu -->
                    </li>
    	<% }
    	 mappedModules.remove(module);
    	
     }
     %>   <li style="display:none;"><a href="javascript:getSld(14)">SLD</a></li>      
                </ul>
                <!-- END Navlist -->

                <!-- BEGIN Sidebar Collapse Button -->
<!--                 <div id="sidebar-collapse" class="visible-lg"> -->
<!--                     <i class="fa fa-angle-double-left"></i> -->
<!--                 </div> -->
<!--                 END Sidebar Collapse Button -->
<!--                 <div id="sidebar-collapse"> -->
<!--                 <img src="img/smarttrak.jpg" width="200" height="70"/> -->
<!--                 </div> -->
            </div>
<!-- END Sidebar -->