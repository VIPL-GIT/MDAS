<%@page import="utils.DBUtil"%>
<%@page import="java.sql.*" %>
<%
String knoURL="http://10.40.16.26:8084/rdfcomm/crm_cons_profile_show.jsp?knopro=";
Connection con=null;
try{
	con=new DBUtil().getConnection();
	String meter=request.getParameter("meter");
	ResultSet rs=con.prepareStatement("select * from total_masterdata where meter_sl_no=(select meter_serial_no from meter where meterid='"+meter+"')").executeQuery();
	if(rs.next()){
%>
<div class="md-content">
         <h3>Meter Info</h3>
         <div class="n-modal-body" id="meterdetails">
         
<table class="table table-advance" cellspacing="0" width="100%">
<tr><th>Region</th><td><%=rs.getString("region") %></td>
    <th>Circle</th><td><%=rs.getString("circle") %></td></tr>
<tr><th>Division</th><td><%=rs.getString("division") %></td>
    <th>Sub-division</th><td><%=rs.getString("sd") %></td></tr>
        
<tr><th>Sub station</th><td><%=rs.getString("ss") %></td> 
<th>Location Code</th><td><a href="<%=rs.getString("location_code")==null?"":knoURL+rs.getString("location_code") %>" target="_blank"><%=rs.getString("location_code")==null?"":rs.getString("location_code") %></a></td></tr>
<tr><th>Meter Sl.No</th><td><%=rs.getString("meter_sl_no") %></td> <th>Modem Sl.No</th><td><%=rs.getString("modem_sl_no")==null?"":rs.getString("modem_sl_no") %></td></tr>
<tr><th>Meter Type</th><td><%=rs.getString("meter_type")==null?"":rs.getString("meter_type") %></td> <th>MF</th><td><%=rs.getString("mf")==null?"":rs.getString("mf") %></td></tr>
<tr><th>Location </th><td><%=rs.getString("location_name")==null?"":rs.getString("location_name") %></td> <th>Location category</th><td><%=rs.getString("location_category")==null?"":rs.getString("location_category") %></td></tr>

</table>

         </div>
     </div>
     
  <%
	}else{ %>
	<div class="md-content">
         <h3>Meter Info</h3>
         <div class="n-modal-body" id="meterdetails">
              <h4>Meter Info not available.</h4>
         </div>
     </div>	
		
<%	}
}catch(Exception e){
	e.printStackTrace();
}finally{
	try{
		if(con!=null) con.close();
	}catch(Exception e){}
}
  %>   