<%@page import="utils.DBUtil"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%@page import="java.io.*" %>

  <link rel="stylesheet" href="css/style.default.css" type="text/css" />
  <link rel="stylesheet" href="css/table.css" type="text/css" />
<div class="modal-content">
    <span id="biclose">&times;</span>
    <h4 style="text-align:center;">Total Billing Parameters</h4>
        
       
<div class="table-responsive" style="border:0;width:100%;" >
<%
String meter=request.getParameter("meter");
String datetime=request.getParameter("datetime");
datetime=datetime.replaceAll("-", "");
datetime=datetime.replaceAll(":", "");
datetime=datetime.replaceAll(" ", "");

String filePath="C:\\Users\\maruthi\\Desktop\\Avon POC";
String fileName=meter+"_"+datetime+".txt";

File f=new File(filePath+"/"+fileName);
if(f.exists()){
BufferedReader br=new BufferedReader(new FileReader(filePath+"/"+fileName));
try{
%>
<table class="table table-advance" cellspacing="0" width="100%" id="tbilling">
<thead>
<tr>
<%
 for(String s:br.readLine().split(",")){ %>
	<td><%=s %></td> 
 <%}
%>
</tr>
</thead>
<tbody>
<%
String data="";
while((data=br.readLine())!=null){
   %><tr><%
	for(String val:data.split(",")){
		%>
		<td><%=(val.equals("null")?"":val) %></td>
	<%} %></tr><%
}

}catch(Exception e){ }
}else{
	%>
	<table class="table table-advance" cellspacing="0" width="100%" id="tbilling">
<thead>
<tr>
<td>No Data available...</td>
</tr>
</thead>
<tbody>
<tr><td></td></tr>
<%
}
%>


</tbody>
</table>

</div>

    </div>