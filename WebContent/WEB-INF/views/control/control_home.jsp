<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%@ page import ="javax.sql.DataSource" %>
<%@ page import ="javax.naming.InitialContext" %>
<%@page import="com.rtdas.common.beans.*" %>
                  

<div class="panel panel-bd ">
                                <div class="panel-heading">
                                    <div class="panel-title">
                                        <h5>Control Settings</h5>
                                        <button class="btn-sm" style="float:right;background-color:cadetblue;" onclick="changeControl('all')">Broadcast</button>
                                    </div>
                                    
                                </div>
                                <div class="panel-body">
<!-- <p class="m-b-15">All you need to do to use it with your own tables is to call the construction function: -->
<!--                                         </p> -->
                                    <div class="table-responsive">    
<table id = "table1" class="table table-striped table-hover " style="margin-bottom:0px;" role="grid">
										<thead>
											<tr role="row" class="st-heading">
											   <th>Tracker ID</th>											  
											   <th>Sun Angle</th>
											   <th>Tracker Angle</th>
											   <th>Tracker Mode</th>
											   <th>Tracker Status</th>											  
											   <th>Motor Status</th>
											    <th>Time</th>											   
											   <th>Control</th>
											</tr>
										</thead>
										<tbody>
<c:if test="${!empty trackers}">
<%
List<TrackerDataBean> list=(List)request.getAttribute("trackers");
 for(TrackerDataBean tdb:list){
	 int Tracker_Mode,T1,T2,TST;
 	 int Motor_status;
 	 String status="",image="",tra_status="";
 	String mode=tdb.getStatus();
 	Map<Integer,String> trackerModes=new HashMap();
 	trackerModes.put(1,"Initialization");
 	trackerModes.put(2,"Tracking");
 	trackerModes.put(3,"Hold");
 	trackerModes.put(4,"Night Stow");
 	trackerModes.put(5,"Wind Stow");
 	trackerModes.put(6,"BackTracking");
 	String trackerMode=trackerModes.get(Character.getNumericValue(mode.charAt(0)));
 	Motor_status=Character.getNumericValue(mode.charAt(3));
 	tra_status=Character.getNumericValue(mode.charAt(1))==1?"Manual ":"Auto ";
 	if(Character.getNumericValue(mode.charAt(1))==0) tra_status=tra_status+"stopped";
 	if(Character.getNumericValue(mode.charAt(1))==1) tra_status=tra_status+"west";
 	if(Character.getNumericValue(mode.charAt(1))==2) tra_status=tra_status+"east";
 	
 %>

   <tr>    
    <td><c:out value='<%=tdb.getTracker_id().equals("SMT02")?"Epsx_Pvh_Row1":tdb.getTracker_id() %>'/></td>
    <td><c:out value="<%=tdb.getSun_Position() %>"/></td>
    <td><c:out value="<%=tdb.getTracker_Position() %>"/></td>
    <td><%=trackerMode %></td>
    <td><%=tra_status %></td>
    <td><%=Motor_status==1?"Run":"Stop"%></td>
    
    <td><c:out value="<%=tdb.getTracker_reading_date_time() %>"/></td>   
    <td><button class="btn-sm" onclick="changeControl('<%=tdb.getTracker_id() %>')">change</button></td>
    </tr>
  
  <%
  }%>
</c:if>

										</tbody>
									</table>
                   </div>
                   </div>
                   </div>
       
 
    </body>
</html>