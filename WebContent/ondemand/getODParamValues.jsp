<%@page import="utils.DBUtil"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%
String param=request.getParameter("param");

Map<String,String> list=new LinkedHashMap();
String tag="",tagType="";

if(param.equals("Threshold Normal") || param.equals("Min Over Threshold Duration") || param.equals("Min Under Threshold Duration")){
	tag="input";
	tagType="text";
}
if(param.equals("Real Time Clock") || param.equals("SINGLE ACTION SCHEDULE") || param.equals("ACTIVITY CALENDER (DateTime SET)")){
	tag="input";
	tagType="datetime-local";
}
if(param.equals("Recording Interval-1 for load profile")){
tagType="select";
    list.put("900","15 mins");
	list.put("1800","30 mins");
	list.put("3600","1 hr");
}
if(param.equals("Recording Interval-2 for load profile")){
	tagType="select";
	
	list.put("43200","12 hrs");
	list.put("86400","24 hrs");
}
if(param.equals("Demand Integration Period")){
	tagType="select";
	
	list.put("900","15 mins");
	list.put("1800","30 mins");
}
if(param.equals("Post Paid")){
	tagType="select";	
	list.put("00","Post Paid");	
}
if(param.equals("Pre Paid")){
	tagType="select";	
	list.put("01","Pre Paid");	
}
if(param.equals("Uni directional")){
	tagType="select";	
	list.put("00","Uni directional");	
}
if(param.equals("Bi directional")){
	tagType="select";	
	list.put("01","Bi directional");	
}


%>

<div class="form-group row">
<label class="col-sm-3 col-form-label">Parameter Values</label>
<%if(tagType.equals("select")){ %> 
<select class="my-control col-sm-7" id="paramValues"
	data-placeholder="--select--" tabindex="1" >
	<%
	   for(Map.Entry em:list.entrySet()){ %>
		   <option value="<%=em.getKey()%>"><%=em.getValue() %></option>
	<%   }
	%>
</select>
<%} if(tag.equals("input")){ %> 
 <input type="<%=tagType %>" id="paramValues"/>
<%}
if(param.equals("ACTIVITY CALENDER (write)")){
	%>
	<input type="time" id="paramValues"/>
	<input type="time" id="paramValues"/>
	<input type="time" id="paramValues"/>
	<input type="time" id="paramValues"/> <br><br>
	<div style="margin-left:145px;">
	<input type="time" id="paramValues"/>
	<input type="time" id="paramValues"/>
	<input type="time" id="paramValues"/>
	<input type="time" id="paramValues"/>
	</div>
	<%
}
%>
</div>