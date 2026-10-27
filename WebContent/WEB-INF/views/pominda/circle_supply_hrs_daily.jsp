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
<div class="table-responsive" style="border:0">
    <c:if test="${!empty circles}">
<%--     <p style="text-align: center;">${week1 }</p> --%>
 <table class="table table-advance" cellspacing="0"  id="table1" style="width:100% !important;">
 <thead>
 <tr>
 <td>CIRCLE</td> 
 <th>SUPPLY Hrs (AG)</th>
 <th>SUPPLY Hrs (NON-AG)</th>
 </tr>
 
 </thead>
 <tbody>
<tr><td>${fn:toUpperCase("Anuppur")}</td><td>${circles['Anuppur'].week1_ag}</td><td>${circles['Anuppur'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Balaghat")}</td><td>${circles['Balaghat'].week1_ag}</td><td>${circles['Balaghat'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Chhatarpur")}</td><td>${circles['Chhatarpur'].week1_ag}</td><td>${circles['Chhatarpur'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Chhindwara")}</td><td>${circles['Chhindwara'].week1_ag}</td><td>${circles['Chhindwara'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Damoh")}</td><td>${circles['Damoh'].week1_ag}</td><td>${circles['Damoh'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Dindori")}</td><td>${circles['Dindori'].week1_ag}</td><td>${circles['Dindori'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Jabalpur City")}</td><td>${circles['Jabalpur City'].week1_ag}</td><td>${circles['Jabalpur City'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Jabalpur O&M")}</td><td>${circles['Jabalpur O&amp;M'].week1_ag}</td><td>${circles['Jabalpur O&amp;M'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Katni")}</td><td>${circles['Katni'].week1_ag}</td><td>${circles['Katni'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Mandla")}</td><td>${circles['Mandla'].week1_ag}</td><td>${circles['Mandla'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Narsinghpur")}</td><td>${circles['Narsinghpur'].week1_ag}</td><td>${circles['Narsinghpur'].week1_nonag}</td></tr>
 
<tr><td>${fn:toUpperCase("Panna")}</td><td>${circles['Panna'].week1_ag}</td><td>${circles['Panna'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Rewa")}</td><td>${circles['Rewa'].week1_ag}</td><td>${circles['Rewa'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Sagar")}</td><td>${circles['Sagar'].week1_ag}</td><td>${circles['Sagar'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Satna")}</td><td>${circles['Satna'].week1_ag}</td><td>${circles['Satna'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Seoni")}</td><td>${circles['Seoni'].week1_ag}</td><td>${circles['Seoni'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Shahdol")}</td><td>${circles['Shahdol'].week1_ag}</td><td>${circles['Shahdol'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Sidhi")}</td><td>${circles['Sidhi'].week1_ag}</td><td>${circles['Sidhi'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Singrauli")}</td><td>${circles['Singrauli'].week1_ag}</td><td>${circles['Singrauli'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Tikamgarh")}</td><td>${circles['Tikamgarh'].week1_ag}</td><td>${circles['Tikamgarh'].week1_nonag}</td></tr>
<tr><td>${fn:toUpperCase("Umariya")}</td><td>${circles['Umariya'].week1_ag}</td><td>${circles['Umariya'].week1_nonag}</td></tr>


 </tbody>
 </table>
 </c:if>
 </div>