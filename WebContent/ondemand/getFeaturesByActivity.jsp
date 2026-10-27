<%@page import="utils.DBUtil"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%
int activity=Integer.parseInt(request.getParameter("activity"));

Connection con=null;
List<String> features=new ArrayList();
try{
	con=new DBUtil().getConnection();
	String type="";
	if(activity==0) type="write";
	if(activity==1) type="execute";
	String sql="select distinct name from smart_meter_us_association_props where action_type='"+type+"'";
	ResultSet rs=con.prepareStatement(sql).executeQuery();
	while(rs.next()){
		features.add(rs.getString("name"));
	}
	con.close();
}catch(Exception e){}
if(activity==0){ //write
	%>
	<option value="-1">--select--</option>
	<%
	  if(!features.isEmpty()){
		  for(String st:features){
		  %>
		  <option value="<%=st%>"><%=st%></option>
	 <% }
	  }
	%>
	
<%}
if(activity==1){ //execute
	%>
	<option value="-1">--select--</option>
	<option value="connect">Connect</option>
	<option value="disconnect">Disconnect</option>
	<option value="ACTIVITY CALENDER (execute)">Activity Calender (execute)</option>
	<option value="MD Reset">MD Reset</option>
<%}
if(activity==2){ //OD reading
	%>
	<option value="-1">--select--</option>
	<option value="IP">Instantaneous Data</option>
	<option value="DP">Data Profiles</option>
    <option value="OL">Complete OBIS</option>
<!--     <option value="DPLS">Loadsurvey Data</option> -->
<!--     <option value="DPTD">Events Data</option> -->
    
<%}
%>