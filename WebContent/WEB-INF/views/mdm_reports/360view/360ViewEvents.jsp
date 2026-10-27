<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<c:choose>
<c:when test="${not empty error}">
<div class="col-md-12" >
<p style="min-height: 300px;vertical-align:center;line-height:150px;" class="error">Error : ${error }</p>
</div>
</c:when>
<c:otherwise>
<div class="col-md-6" >

<table class="table table-advance" cellspacing="0" width="100%" id="table1" >
 <thead>
  <tr><th>Sl.No.</th><th>Event Desc</th><th>Event Status</th><th>Event DateTime</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${result.data}" var="d5" varStatus="loop">
   <tr>
   <td>${loop.index+1 }</td>
   <td>${d5.event_type}</td>
   <td>${d5.event_status}</td>
   <td>${d5.event_date}</td>
   
   
   </tr>
   </c:forEach>
  
  </tbody>
  </table>
  
  </div>
  
  <div class="col-md-6 table-responsive">
<table  cellspacing="5" cellpadding="5" width="100%" id="table1" style="margin-top:10px;">
 <thead>
  <tr><th>Sl.No.</th><th>Event Desc</th><th>No.Of Times Occurred</th><th>Total Duration</th>
  </tr>
  </thead>
  <tbody>
  <tr><td>1</td><td>Power Failed</td><td>20</td><td>350</td></tr>
  <tr><td>2</td><td>R-ph CT Open</td><td>30</td><td>350</td></tr>
  <tr><td>3</td><td>Power Failed</td><td>40</td><td>350</td></tr>
  <tr><td>4</td><td>R-ph Missing</td><td>50</td><td>350</td></tr>
  <tr><td>5</td><td>R-ph Missing</td><td>20</td><td>350</td></tr>
  <tr><td>6</td><td>B-ph Missing</td><td>30</td><td>350</td></tr>
  <tr><td>7</td><td>B-ph Missing</td><td>40</td><td>350</td></tr>
  <tr><td>8</td><td>Y-ph Missing</td><td>60</td><td>350</td></tr>
  </tbody>
  </table>
</div>
</c:otherwise>
  </c:choose>