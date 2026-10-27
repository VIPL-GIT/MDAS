<%@page import="utils.DBUtil"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%
Connection con=null;
try{
	List<String> months=new ArrayList();
	months.add("Jan"); months.add("Feb"); months.add("Mar"); months.add("Apr"); months.add("May"); months.add("Jun");
	months.add("Jul"); months.add("Aug"); months.add("Sep"); months.add("Oct"); months.add("Nov"); months.add("Dec");
	con=new DBUtil().getConnection();
	String meter=request.getParameter("meter");
	String year=request.getParameter("year");
	
	String sql="select * from dashboard_monthly_demand where meter_serial_no='"+meter+"' and year='"+year+"'";
	ResultSet rs=con.prepareStatement(sql).executeQuery();
	
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	int presentMonth=Integer.parseInt(sdf.format(new java.util.Date()).split("-")[1]);
	
	DecimalFormat fourDec=new DecimalFormat("#0.0000");

%>
<div class="table-responsive" style="border:0">    
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
 <tr>
 <th>Month</th>
 <th>Contract Demand</th>
 <th>MD KW</th>
 <th>Peak Load</th>
 <th>Status</th>
 </tr>
 </thead>
 <tbody>
 
 <%
 if(rs.next()){
	 for(int i=1;i<=presentMonth;i++){
		 String cd=rs.getString("m"+i)==null?"0.0000":fourDec.format(rs.getDouble("m"+i)/rs.getDouble("contract_demand"));
	 %>
	 <tr>
	 <td><%=months.get(i-1)+","+year %></td>
	 <td><%=rs.getString("contract_demand") %></td>
	 <td><%=rs.getString("m"+i) %></td>
	 <td><%= cd%></td>
	 <td><%=Double.parseDouble(cd)>1?"Violated":"---" %></td>
	 </tr>
 <%}
 }
}catch(Exception e){}
finally{
	try{
		if(con!=null) con.close();
	}catch(Exception e){}
}
 %>
 </tbody>
 </table>
 </div>