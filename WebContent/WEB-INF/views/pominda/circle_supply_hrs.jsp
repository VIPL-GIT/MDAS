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
    <c:if test="${!empty ag_circles}">
 <table class="table table-advance" cellspacing="0"  id="table1" style="width:100% !important;">
 <thead>
 <tr><th>CIRCLE</th>
 <th>SUPPLY Hrs (AG)</th>
 <th>SUPPLY Hrs (NON-AG)</th>
 </tr>
 </thead>
 <tbody> 
<tr><td>${fn:toUpperCase("Anuppur")}</td><td>${ag_circles['Anuppur']}</td><td>${nonag_circles['Anuppur']}</td></tr>
<tr><td>${fn:toUpperCase("Balaghat")}</td><td>${ag_circles['Balaghat']}</td><td>${nonag_circles['Balaghat']}</td></tr>
<tr><td>${fn:toUpperCase("Chhatarpur")}</td><td>${ag_circles['Chhatarpur']}</td><td>${nonag_circles['Chhatarpur']}</td></tr>
<tr><td>${fn:toUpperCase("Chhindwara")}</td><td>${ag_circles['Chhindwara']}</td><td>${nonag_circles['Chhindwara']}</td></tr>
<tr><td>${fn:toUpperCase("Damoh")}</td><td>${ag_circles['Damoh']}</td><td>${nonag_circles['Damoh']}</td></tr>
<tr><td>${fn:toUpperCase("Dindori")}</td><td>${ag_circles['Dindori']}</td><td>${nonag_circles['Dindori']}</td></tr>
<tr><td>${fn:toUpperCase("Jabalpur City")}</td><td>${ag_circles['Jabalpur City']}</td><td>${nonag_circles['Jabalpur City']}</td></tr>
<tr><td>${fn:toUpperCase("Jabalpur O&M")}</td><td>${ag_circles['Jabalpur O&amp;M']}</td><td>${nonag_circles['Jabalpur O&amp;M']}</td></tr>
<tr><td>${fn:toUpperCase("Katni")}</td><td>${ag_circles['Katni']}</td><td>${nonag_circles['Katni']}</td></tr>
<tr><td>${fn:toUpperCase("Mandla")}</td><td>${ag_circles['Mandla']}</td><td>${nonag_circles['Mandla']}</td></tr>
<tr><td>${fn:toUpperCase("Narsinghpur")}</td><td>${ag_circles['Narsinghpur']}</td><td>${nonag_circles['Narsinghpur']}</td></tr>
 
<tr><td>${fn:toUpperCase("Panna")}</td><td>${ag_circles['Panna']}</td><td>${nonag_circles['Panna']}</td></tr>
<tr><td>${fn:toUpperCase("Rewa")}</td><td>${ag_circles['Rewa']}</td><td>${nonag_circles['Rewa']}</td></tr>
<tr><td>${fn:toUpperCase("Sagar")}</td><td>${ag_circles['Sagar']}</td><td>${nonag_circles['Sagar']}</td></tr>
<tr><td>${fn:toUpperCase("Satna")}</td><td>${ag_circles['Satna']}</td><td>${nonag_circles['Satna']}</td></tr>
<tr><td>${fn:toUpperCase("Seoni")}</td><td>${ag_circles['Seoni']}</td><td>${nonag_circles['Seoni']}</td></tr>
<tr><td>${fn:toUpperCase("Shahdol")}</td><td>${ag_circles['Shahdol']}</td><td>${nonag_circles['Shahdol']}</td></tr>
<tr><td>${fn:toUpperCase("Sidhi")}</td><td>${ag_circles['Sidhi']}</td><td>${nonag_circles['Sidhi']}</td></tr>
<tr><td>${fn:toUpperCase("Singrauli")}</td><td>${ag_circles['Singrauli']}</td><td>${nonag_circles['Singrauli']}</td></tr>
<tr><td>${fn:toUpperCase("Tikamgarh")}</td><td>${ag_circles['Tikamgarh']}</td><td>${nonag_circles['Tikamgarh']}</td></tr>
<tr><td>${fn:toUpperCase("Umariya")}</td><td>${ag_circles['Umariya']}</td><td>${nonag_circles['Umariya']}</td></tr>
<tr><td>${fn:toUpperCase("all")}</td><td>${ag_circles['all']}</td><td>${nonag_circles['all']}</td></tr>

 </tbody>
 </table>
 </c:if>
 </div>