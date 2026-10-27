<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<style>
.table{
width:50% !important;
float:left;
padding:5px !important;
}
.table>tbody>tr>td{
padding:3px !important;
}
.table>thead>tr>th{
text-align: center !important;
}
</style>
<div class="table-responsive" style="border:0;overflow:hidden;">
    <c:if test="${!empty map}">
 <table class="table table-advance" cellspacing="0"  id="table1" style="width:100% !important;">
 <thead>
 <tr>
 <td >CIRCLE</td>
 <td >No.of Interrupts </td>
 
 </tr>
 
 </thead>
 <tbody>
 <c:forEach items="${map}" var="c">
 <tr>
 <td>${fn:toUpperCase(c.key) }</td>
 <td>${c.value }</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </c:if>
 </div>