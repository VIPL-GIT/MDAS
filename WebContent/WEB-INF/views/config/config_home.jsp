<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%@ page import ="javax.sql.DataSource" %>
<%@ page import ="javax.naming.InitialContext" %>

                  

<div class="panel panel-bd ">
                                <div class="panel-heading">
                                    <div class="panel-title">
                                        <h5>Configuration</h5>
                                        <button class="btn-sm" style="float:right;background-color:cadetblue;" onclick="changeConfig('all')">Broadcast</button>
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
											   <th>Latitude</th>
											   <th>Longitude</th>
											   <th>TimeZone</th>											  
											   <th>East Limit</th>
											   <th>West Limit</th>
											   <th>BT Angle</th>
											    <th>Time</th>
											    <th>Config</th>
											</tr>
										</thead>
										<tbody>
<c:if test="${!empty trackers}">
<c:forEach items="${trackers}" var="trak">
   <tr>    
    <td><c:out value='${trak.tracker_id eq "SMT02"?"Epsx_Pvh_Row1":trak.tracker_id}'/></td>
    <td><c:out value="${trak.latitude}"/></td>
    <td><c:out value="${trak.longitude}"/></td>
    <td><c:out value="${trak.time_Zone}"/></td>
    <td><c:out value="${trak.rSL}"/></td>
    <td><c:out value="${trak.fSL}"/></td>
    <td><c:out value="${trak.sSS_Angle}"/></td>
    <td><c:out value="${trak.tracker_reading_date_time}"/></td>
    <td><button class="btn-sm" onclick="changeConfig('${trak.tracker_id}')">change</button></td>
    </tr>
  </c:forEach>
</c:if>

										</tbody>
									</table>
                   </div>
                   </div>
                   </div>
       
 
    </body>
</html>