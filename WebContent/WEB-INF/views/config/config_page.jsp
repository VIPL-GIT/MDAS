<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date" %>
<%@page import="java.sql.*" %>
<%@ page import ="javax.sql.DataSource" %>
<%@ page import ="javax.naming.InitialContext" %>
<style>
.btn-sm{
margin-top:25px !important;
}
</style>

<div class="panel panel-bd ">
	<div class="panel-heading">
		<div class="panel-title">
			<h5>Configuration for ${traid.equalsIgnoreCase("all")?"'All'":(traid eq "SMT02"?"Epsx_Pvh_Row1":traid) }</h5>
			<a href="javascript:getConfigHome();" style="float:right;"><i class="ti-back-left"></i></a>
		</div>
	</div>
	<div class="panel-body">
	<div class="col-md-7">
     <div id="latlon" class="col-md-12">
       <div class="form-group col-md-3">
         <label >Latitude</label>
         <input type="text" class="form-control" id="lat" value="${tracker.latitude }">
       </div>
       <div class="form-group col-md-3">
         <label >Longitude</label>
         <input type="text" class="form-control" id="lon" value="${tracker.longitude }">
       </div>
       <div class="form-group col-md-3">
         <label >Timezone</label>
         <input type="text" class="form-control" id="zone" value="${tracker.time_Zone }">
       </div>
       <div class="form-group col-md-3">
         <button class="btn-sm" onclick="sendLatLon('latlon','${traid}')">Send</button>
       </div>
     </div>
     <div id="datetime" class="col-md-12">
       <div class="form-group col-md-3">
         <label >Date</label>
         <input type="text" class="form-control" id="date" placeholder="dd:mm:yyyy">
       </div>
       <div class="form-group col-md-3">
         <label >Time</label>
         <input type="text" class="form-control" id="time" placeholder="hh:mm:ss">
       </div>
       
       <div class="form-group col-md-3">
         <button class="btn-sm" onclick="sendLatLon('datetime','${traid}')">Send</button>
       </div>
     </div>
     <div id="ewangle" class="col-md-12">
       <div class="form-group col-md-3">
         <label >East Limit</label>
         <input type="text" class="form-control" id="el" value="${tracker.rSL }">
       </div>
       <div class="form-group col-md-3">
         <label >West Limit</label>
         <input type="text" class="form-control" id="wl" value="${tracker.fSL }">
       </div>
       <div class="form-group col-md-3">
         <label >BT Angle</label>
         <input type="text" class="form-control" id="bta" value="${tracker.sSS_Angle }">
       </div>
       <div class="form-group col-md-3">
         <button class="btn-sm" onclick="sendLatLon('ewlimits','${traid}')">Send</button>
       </div>
     </div></div>
     <div class="col-md-5">
      <img src="img/spinningGlobe1.gif" height="250px;" style="margin-left:75px;"/>
     </div>	
	</div>
</div>