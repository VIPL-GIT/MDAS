<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="com.rtdas.common.models.Alerts" %>
<style>
.inbox-item-author{
color:red !important;
}
</style>
<!-- <a href="#"> -->
<!--     <div class=inbox-item> -->
<!--         <div class=inbox-item-img><h3 class="alertImg"><i class="wi wi-lightning"></i></h3></div> -->
<!--         <strong class=inbox-item-author>Low Battery</strong> -->
<!--         <span class=inbox-item-date>-19-0-2018 13:40 PM</span> -->
<!--         <p class=inbox-item-text>Low Battery (15%)  occured for 'SMT01'</p> -->
<!--         <span class="profile-status available pull-right"></span> -->
<!--     </div> -->
<!-- </a> -->
<%
SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
try{
List<Alerts> list=(List)request.getAttribute("alerts");
if(!list.isEmpty()){        		
	for(Alerts al:list){
		
		String iclass="";
		if(al.getAlert_type().toLowerCase().contains("battery")) iclass="wi wi-lightning";
		if(al.getAlert_type().toLowerCase().contains("position")) iclass="wi wi-horizon";
		if(al.getAlert_type().toLowerCase().contains("comm")) iclass="wi wi-barometer";
		if(al.getAlert_type().toLowerCase().contains("load")) iclass="wi wi-lightning";
		if(al.getAlert_type().toLowerCase().contains("temperature")) iclass="wi wi-lightning";
		%>
<a href="#">
    <div class=inbox-item>
        <div class=inbox-item-img><h3 class="alertImg"><i class="<%=iclass%>"></i></h3></div>
        <strong class=inbox-item-author><%=al.getAlert_type() %></strong>
        <span class=inbox-item-date>-<%=sdf.format(al.getAlert_datetime()) %></span>
        <p class=inbox-item-text><%=al.getAlert_desc().replaceAll("SMT02","Epsx_Pvh_Row1") %></p>
        <span class="profile-status available pull-right"></span>
    </div>
</a>		
		<%
	}
	%>
	<a href="javascript:getAlertsHome();" style="float:right;margin-right:10px;">more...</a>
<%}else{ %>
	<p style="text-align:center;margin-top:50px;">No alerts to display</p>
<%}
}catch(Exception e){
	e.printStackTrace();
}
%>

                                            