<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%@ page import ="javax.sql.DataSource" %>
<%@ page import ="javax.naming.InitialContext" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.rtdas.common.beans.*" %>
<style>
.btn-sm{
margin-top:25px !important;
}
.fc-event a{
color:#FFF;
}
.east{
background:url("img/east.png") no-repeat;
background-size:30px 31px;
}
.west{
background:url("img/west.png") no-repeat;
background-size:30px 31px;
}
.stow{
background:url("img/stow.png") no-repeat;
background-size:30px 31px;
}
.statistic-box h2{
margin-bottom:0px !important;
}
</style>
<c:if test='${traid!= "all"}'>
<%
TrackerDataBean tdb=(TrackerDataBean)request.getAttribute("tracker");
int Tracker_Mode,T1,T2,TST;
 int Motor_status;
 String status="",sun_image="",tra_image="",status_image="",tra_status="",tra_status_img="";
 double Sun_angle=0,Tra_angle=0;
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
	
 Sun_angle=Double.parseDouble(tdb.getSun_Position());
 Tra_angle=Double.parseDouble(tdb.getTracker_Position());
 
 
 status_image=Motor_status==1?"img/status/Motor_status-2.png":"img/status/Motor_status-1.png";
 if(tra_status.contains("stopped")) tra_status_img="img/status/20-Auto Stop.jpg";
 else if(tra_status.contains("west")) tra_status_img="img/status/21-Auto West.gif";
 else if(tra_status.contains("east")) tra_status_img="img/status/22-Auto East.gif";
 if(Sun_angle >= -90.00 && Sun_angle <= -45.00){
	 sun_image = "img/status/spa-1.gif";
	}else if(Sun_angle > -45.00 && Sun_angle <= -1.00){
		sun_image = "img/status/spa-2.gif";
	}else if(Sun_angle >= 1.00 && Sun_angle <= 45.00){
		sun_image = "img/status/spa-3.gif";
	}else if(Sun_angle > 45.00 && Sun_angle < 90.00){
		sun_image = "img/status/spa-4.gif";
	}else if(Sun_angle == 0){
		sun_image = "img/status/spa-night.gif";
	}
 
 if(Tra_angle < 02.00 && Tra_angle >= -45.0 && Sun_angle >=-89 && Sun_angle <= -45 ){
	 tra_image = "img/status/tracker-morning-backtrack.gif";
	}else if(Tra_angle > -45.00 && Tra_angle <= -1.00){
		tra_image = "img/status/tracker-morning-track.gif";
	}else if(Tra_angle >= 0.00 && Tra_angle <= 45.00 && Sun_angle >= 0 && Sun_angle < 40){
		tra_image = "img/status/tracker-afternoon-after-track.gif";
	}else if((Tra_angle < 45.00 && Tra_angle > 0.00) && (Sun_angle >= 40.00 && Sun_angle <= 89.00)){
		tra_image = "img/status/tracker-evening-backtracking.gif";
	}else if((Tra_angle < 5.00 && Tra_angle >= -5.00) && (Sun_angle == 0)){
		tra_image = "img/status/tracker-night.gif";
	}
 
%>	
<div class="row home1_analys" style="margin-bottom:10px;">
                        <div class="col-xs-6 col-sm-4 col-md-4 col-lg-3">
                            <div class="statistic-box">
                                <div class="small">Sun Angle </div>
                                <h2><span class="count-number"><%=tdb.getSun_Position() %></span>&deg; </h2>
                                <div class="">
                                    <img src="<%=sun_image %>" />
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4 col-md-4 col-lg-3">
                            <div class="statistic-box">
                                <div class="small">Tracker Angle</div>
                                <h2><span class="count-number"><%=tdb.getTracker_Position() %></span>&deg; </h2>
                                <div class="">
                                    <img src="<%=tra_image %>" />
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4 col-md-4 col-lg-3">
                            <div class="statistic-box">
                                <div class="small">Tracker Status</div>
                                <h2><span class="count-number"><%=tra_status %></span> </h2>
                                <div class="">
                                    <img src="<%=tra_status_img %>" />
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 hidden-sm hidden-md col-lg-3">
                            <div class="statistic-box">
                                <div class="small">Motor Status</div>
                                <h2><span class="count-number"><%=Motor_status==1?"Run":"Stop" %></span> </h2>
                                <div class="">
                                    <img src="<%=status_image %>" />
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:if>
<div class="panel panel-bd ">
	<div class="panel-heading">
		<div class="panel-title">
			<h5>Control Settings for ${traid.equalsIgnoreCase("all")?"'All'":(traid eq "SMT02"?"Epsx_Pvh_Row1":traid) }</h5>
			<a href="javascript:getControlHome();" style="float:right;"><i class="ti-back-left"></i></a>
		</div>
	</div>
	<div class="panel-body">
     <div id="latlon" class="col-md-5">
     <div class="col-md-6">
                                        <h5>Select Mode</h5>
                                         <button class="btn btn-danger w-md m-rb-5"  onclick="getDiv('auto')">Auto</button>
                                         <button class="btn btn-danger w-md m-rb-5"  onclick="getDiv('manual')">Manual</button>
      </div>
      <div class="col-md-6" id="manualDiv" style="display:none;margin-top:35px;">
      <button type="button" class="btn btn-block btn-social btn-openid m-rb-5" onclick="sendLatLon('manual_east','${traid}')">
              <span class="east"></span> East
      </button>
      <button type="button" class="btn btn-block btn-social btn-openid m-rb-5" onclick="sendLatLon('manual_west','${traid}')">
              <span class="west"></span> West
      </button>
      <button type="button" class="btn btn-block btn-social btn-openid m-rb-5" onclick="sendLatLon('stow','${traid}')">
              <span class="stow"></span> Stow
      </button>
      </div>
       
     </div>
     <c:if test='${traid!= "all"}'>
     <div class="col-md-7 ">
		<h5 class="st-ctext"> Tracker Status </h5>
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
											   <th>Date Time</th>
											</tr>
										</thead>
										<tbody>
<%
TrackerDataBean tdb=(TrackerDataBean)request.getAttribute("tracker");
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
    <td><c:out value="<%=trackerMode %>"/></td>
    <td><c:out value="<%=tra_status %>"/></td>
    <td><%=(Motor_status==1?"Run":"Stop") %></td>
    
    
    <td><c:out value="<%=tdb.getTracker_reading_date_time() %>"/></td>    
    
    </tr>
 

										</tbody>
									</table>
                   </div>						
	 </div>
	</c:if>	
	</div>
</div>
<script>
function getDiv(id){
	if(id=="manual"){
		document.getElementById("manualDiv").style.display="block";
		sendLatLon('manual','${traid}');
	}
	if(id=="auto"){
		document.getElementById("manualDiv").style.display="none";
		sendLatLon('auto','${traid}');	
	}
}

</script>