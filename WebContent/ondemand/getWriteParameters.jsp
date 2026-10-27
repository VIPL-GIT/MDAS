<%@page import="utils.DBUtil"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%
String type="write";
String feature=request.getParameter("feature");

Connection con=null;
List<String> params=new ArrayList();
try{
	con=new DBUtil().getConnection();
	
	String sql="select distinct attribute_name from smart_meter_us_association_props where action_type='"+type+"' and name='"+feature+"'";
	ResultSet rs=con.prepareStatement(sql).executeQuery();
	while(rs.next()){
		params.add(rs.getString("attribute_name"));
	}
	con.close();
}catch(Exception e){}

%>
<option value="-1">--select--</option>
<%if(!params.isEmpty()){
	  for(String st:params){
	  %>
	  <option value="<%=st%>"><%=st%></option>
<% }
}
%>