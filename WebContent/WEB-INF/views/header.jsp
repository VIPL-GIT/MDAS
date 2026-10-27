<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<a href="javascript:hideDashboardDiv_new()" class="logo"  > <!-- Logo -->
                    <span class="logo-mini">
                        <!--<b>A</b>H-admin-->
                        <img src="assets/dist/img/logo-mini_vipl.png" alt="img">
                    </span>
                    <span class="logo-lg">
                        <!--<b>Admin</b>H-admin-->
                        
                        <img src="assets/dist/img/logo.png" alt="img" height="50px" style="vertical-align: -webkit-baseline-middle;border-radius: 5px;">
                        
                    </span>
                </a>
<a href="#" class="sidebar-toggle hidden-xs" data-toggle="offcanvas" role="button">
                  <!-- Sidebar toggle button-->
                  <span class="sr-only">Toggle navigation</span>
                  <i class="fa fa-ellipsis-v"></i>
               </a>  
                             
                <!-- Header Navbar -->
                <nav class="navbar navbar-static-top">
                    <a href="#" class="sidebar-toggle hidden-sm hidden-md hidden-lg" data-toggle="offcanvas" role="button"> <!-- Sidebar toggle button-->
                        <span class="sr-only">Toggle navigation</span>
                        <span class="ti-menu-alt"></span>
                    </a>
                    <div style="float:left;color:white;margin-left:275px;margin-top:15px;">
                    <h2>Meter Data Management System <sub>(MDMS)</sub> </h2>
                    </div>
                    <div class="navbar-custom-menu">
                        
                        <ul class="nav navbar-nav">
                        <li>
<!--                          <h2 style="color: white;">Meter Data Acquisition System (MDAS)</h2> -->
                        </li>
<!--                             <li> -->
<!--                                 <form class="navbar-form hidden-xs" role="search"> -->
<!--                                     <div class="input-group add-on"> -->
<!--                                         <input class="form-control" placeholder="Search" name="srch-term" id="srch-term" type="text" style="border-radius: 10px;"> -->
<!--                                         <div class="input-group-btn"> -->
<!--                                             <button class="btn btn-default" type="submit" data-toggle="tooltip" data-placement="bottom" title="Search"><i class="ti-search"></i></button> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </form> -->
<!--                             </li> -->
<!--                             <li class="dropdown dropdown-settings"> -->
<!--                                 <a href="#" class="dropdown-toggle bubbly-button" data-toggle="dropdown"> <i class="fa fa-bell-o"></i><span class="badge fadeAnim">2</span></a> -->
<!--                                 <div class="notification-box dropdown-menu animated bounceIn"> -->
<!--                                     <div class="notification-header"> -->
<!--                                         <h4>2 new notifications</h4> -->
<!--                                         <a href="#">clear all</a> -->
<!--                                     </div> -->
<!--                                     <div class="notification-body"> -->
<!--                                         <ul class="notification_inner"> -->
<!--                                             <li> -->
<!--                                               <a href="#" class="single-notification"> -->
<!--                                                  <div class="notification-img"> -->
<!--                                                     <i class="fa fa-commenting"></i> -->
<!--                                                  </div> -->
<!--                                                  <div class="notification-txt"> -->
<!--                                                     <h4>3 new comments</h4> -->
<!--                                                     <span>40 seconds ago</span> -->
<!--                                                  </div> -->
<!--                                               </a> -->
<!--                                             </li> -->
<!--                                             <li> -->
<!--                                               <a href="#" class="single-notification"> -->
<!--                                                  <div class="notification-img"> -->
<!--                                                     <i class="fa fa-envelope-o"></i> -->
<!--                                                  </div> -->
<!--                                                  <div class="notification-txt"> -->
<!--                                                     <h4>You have received 1 email</h4> -->
<!--                                                     <span>5 minutes ago</span> -->
<!--                                                  </div> -->
<!--                                               </a> -->
<!--                                            </li> -->
<!--                                            <li> -->
<!--                                               <a href="#" class="single-notification"> -->
<!--                                                  <div class="notification-img"> -->
<!--                                                     <i class="fa fa-usd"></i> -->
<!--                                                  </div> -->
<!--                                                  <div class="notification-txt"> -->
<!--                                                     <h4>You have transferred $50</h4> -->
<!--                                                     <span>8 minutes ago</span> -->
<!--                                                  </div> -->
<!--                                               </a> -->
<!--                                            </li> -->
<!--                                            <li> -->
<!--                                               <a href="#" class="single-notification"> -->
<!--                                                  <div class="notification-img"> -->
<!--                                                     <i class="fa fa-thumbs-up"></i> -->
<!--                                                  </div> -->
<!--                                                  <div class="notification-txt"> -->
<!--                                                     <h4>Someone likes your post</h4> -->
<!--                                                     <span>13 minutes ago</span> -->
<!--                                                  </div> -->
<!--                                               </a> -->
<!--                                            </li> -->
<!--                                            <li> -->
<!--                                               <a href="#" class="single-notification"> -->
<!--                                                  <div class="notification-img"> -->
<!--                                                     <i class="fa fa-ban "></i> -->
<!--                                                  </div> -->
<!--                                                  <div class="notification-txt"> -->
<!--                                                     <h4>Someone banned your post</h4> -->
<!--                                                     <span>20 minutes ago</span> -->
<!--                                                  </div> -->
<!--                                               </a> -->
<!--                                            </li> -->
<!--                                            <li> -->
<!--                                               <a href="#" class="single-notification"> -->
<!--                                                  <div class="notification-img"> -->
<!--                                                     <i class="fa fa-trash"></i> -->
<!--                                                  </div> -->
<!--                                                  <div class="notification-txt"> -->
<!--                                                     <h4>Someone deleted your post</h4> -->
<!--                                                     <span>36 minutes ago</span> -->
<!--                                                  </div> -->
<!--                                               </a> -->
<!--                                            </li> -->
<!--                                         </ul> -->
<!--                                     </div> -->
<!--                                     <div class="notification-footer"> -->
<!--                                         <a href="#">see all notification<i class="fa fa-long-arrow-right"></i></a> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </li> -->
<%
String userImg="";
 try{
	  userImg="/img/user.jpeg";
	  if(session.getAttribute("userImg")!=null)
	     userImg=session.getAttribute("userImg").toString();
	   if(userImg.contains("img"))
	    userImg=userImg.substring(userImg.indexOf("img"));
 }catch(Exception e){
	 response.sendRedirect("login.epx");
 }
%>
                            <li class="dropdown dropdown-user">
		                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
		                            <img class="admin-pic img-circle" src="<%=userImg %>" alt="">Hi, <%=session.getAttribute("userId") %> <b class="caret"></b>
		                        </a>
		                        <ul  role="menu" class="dropdown-menu" style="margin-left:120px !important;">
		                            <li>
		                                <a href="http://103.154.75.85:8080/CS/" target="_blank">
		                                    <i class="ti-link"></i> &#160;&#160;CS App</a>
		                            </li>
		                            <li>
		                                <a href="http://183.82.111.83:8088/CS/sendSMS.jsp" target="_blank">
		                                    <i class="ti-link"></i> &#160;&#160;SMS App</a>
		                            </li>
		                            <li>
		                                <a href="logout.jsp">
		                                    <i class="ti-key"></i> &#160;&#160;Logout</a>
		                            </li>
		                        </ul>
                   			</li>
                            
                        </ul>
                    </div>
                </nav>
<!-- javascript to add class as active and to change the css for FRTU mgmt -->                
<script>

</script>                