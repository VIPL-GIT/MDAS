<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>

<div class="table-responsive" style="border:0" id="mpreports">   

 <table class="table table-advance" style="width:100%;"  id="hourlyLOad">
 <thead>
 <tr>
 <td>Division</td>
 <td>Comm.Feeders</td>
 <td>Modems %</td>
 <td>Manual %</td>
 <td>NA %</td>
 </tr>
 </thead>
 <tbody>
 <c:forEach items="${hlbList}" var="m">
 <tr>
 <td>${m.hname }</td>
 <td>${m.no_of_feeders }</td>
 <td>${m.amr_percent }</td>
 <td>${m.manual_percent }</td>
 <td>${m.null_percent }</td>
 </tr>
 </c:forEach>
 
 </tbody>
 </table>
 </div>