<%@page import="java.sql.*" %>
<%@page import="utils.DBUtil" %>
<link rel="stylesheet" href="../css/style.default.css" type="text/css" />
  <link rel="stylesheet" href="../css/table.css" type="text/css" />
<%
String group=request.getParameter("group");
String circle=request.getParameter("circle");

System.out.println(group+"\t"+circle);
Connection con=null;
try{
	con=DBUtil.getConnection();
	String sql="select * from total_masterdata where circle='"+circle+"'";
	ResultSet rs=con.prepareStatement(sql).executeQuery();
%>

<div class="table-responsive" style="margin-left: 10%;margin-right: 10%;margin-top: 1%">
<table class="table table-advance" cellspacing="0" width="100%" id="pfNpowers">
<thead>
<tr>
<th>Circle</th>
<th>Division</th>
<th>S/S Name</th>
<th>Feeder</th>
<th>Group</th>
<th>Fstatus</th>
<th>Date</th>
<th>Time</th>
<th>Present Load</th>
<th>Switching Date</th>
<th>Switching Time</th>
<th>Load Relief</th>

</tr>
</thead>
<tbody>
<%
while(rs.next()){
	%>
	<tr>
	<td><%=rs.getString("circle") %></td>
	<td><%=rs.getString("division") %></td>
	<td><%=rs.getString("ss") %></td>
	<td><%=rs.getString("location_name") %></td>
	<td><%=rs.getString("location_group") %></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	</tr>
<%}
%>
</tbody>
</table>
</div>

<%}
catch(Exception e){
	
}
%>