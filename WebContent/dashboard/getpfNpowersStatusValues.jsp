<%
String type=request.getParameter("type");
if(type.equalsIgnoreCase("pf")){
	%>
	<option value="0.95">0.95</option>
	<option value="0.9">0.9</option>
	<option value="0.8">0.8</option>
	<option value="0.7">0.7</option>
	<option value="0.6">0.6</option>
<%}else{ %>
	<option value="20">20</option>
	<option value="15">15</option>
	<option value="10">10</option>
	<option value="5">5</option>
<%}
%>