<div class="table-responsive" style="border:0">  
<h4 class="rTitle">Reactive Power & PF</h4>  
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
 <tr>
 
 <td rowspan="2">Circle</td>
 <td rowspan="2">Division</td>
 <td rowspan="2">Sub station</td>
 <td rowspan="2">Feeder</td>
 <td rowspan="2">Voltage</td>
 <td rowspan="2">Category</td>
 <td rowspan="2">Meter Sl.No</td>
 <td colspan="24">Hourly KVAH & PF</td> 
 </tr>
 <tr>
 <%
 for(int hr=1;hr<=24;hr++){
%><td>Hrs <%=hr %></td>	 
 <%}
 %>
 
 </tr>
 </thead>
 <tbody>
 </tbody>
 </table>
 </div>