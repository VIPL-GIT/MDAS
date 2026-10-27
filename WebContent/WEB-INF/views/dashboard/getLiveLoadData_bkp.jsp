<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<%@page import="com.rtdas.dashboard.beans.*" %>

<style>
.load_relief{
 display:none;
}
</style>
<table class="table table-advance" cellspacing="0" width="100%" id="liveload">
<thead>
<tr>
<th rowspan="2">Category</th>
<c:forEach var="c" items="${circles}">
 <th> <c:out value="${c}"/></th>  
</c:forEach>

<!-- <th rowspan="2">Total Load</th> -->

</tr>

</thead>
<%
try{
	Map<String,LiveLoadBean> loadMap=(Map<String,LiveLoadBean>)request.getAttribute("load");

%>
<tbody>
<tr>
<td>I-IV</td>
<%
double load_val=0;
double relief_val=0;
for(Map.Entry em:loadMap.entrySet()){
	LiveLoadBean llb=(LiveLoadBean)em.getValue();
	load_val=load_val+(llb.getGroup_I_load()+llb.getGroup_II_load()+
			           llb.getGroup_III_load()+llb.getGroup_IV_load());
	relief_val=relief_val+(llb.getGroup_I_load_relief()+llb.getGroup_II_load_relief()+
	                        llb.getGroup_III_load_relief()+llb.getGroup_IV_load_relief());
	%>
	<td class="load"><a href="javascript:getLiveLoadSummary('I-IV','<%=em.getKey()%>')"><%=(llb.getGroup_I_load()+llb.getGroup_II_load()+
			llb.getGroup_III_load()+llb.getGroup_IV_load())%></a></td>
	<td class="load_relief"><%=(llb.getGroup_I_load_relief()+llb.getGroup_II_load_relief()+
			llb.getGroup_III_load_relief()+llb.getGroup_IV_load_relief())%></td>		
<%} %>
<td><%=load_val %></td><td><%=relief_val %></td><td><%=load_val+relief_val %></td>
</tr>
<tr>
<td>V-VIA</td>
<%
 load_val=0;
 relief_val=0;
for(Map.Entry em:loadMap.entrySet()){
	LiveLoadBean llb=(LiveLoadBean)em.getValue();
	load_val=load_val+(llb.getGroup_V_load()+llb.getGroup_V_A_load()+
			           llb.getGroup_VI_load()+llb.getGroup_VI_A_load());
	relief_val=relief_val+(llb.getGroup_V_load_relief()+llb.getGroup_V_A_load_relief()+
	                       llb.getGroup_VI_load_relief()+llb.getGroup_VI_A_load_relief());
	
	%>
	<td class="load"><a href="javascript:getLiveLoadSummary('V-VIA','<%=em.getKey()%>')"><%=(llb.getGroup_V_load()+llb.getGroup_V_A_load()+
			llb.getGroup_VI_load()+llb.getGroup_VI_A_load())%></a></td>
	<td class="load_relief"><%=(llb.getGroup_V_load_relief()+llb.getGroup_V_A_load_relief()+
			llb.getGroup_VI_load_relief()+llb.getGroup_VI_A_load_relief())%></td>		
<%} %>
<td><%=load_val %></td><td><%=relief_val %></td><td><%=load_val+relief_val %></td>
</tr>
<tr>
<td>VII</td>
<%
load_val=0;
relief_val=0;
for(Map.Entry em:loadMap.entrySet()){
	LiveLoadBean llb=(LiveLoadBean)em.getValue();
	load_val=load_val+llb.getGroup_VII_load();
	relief_val=relief_val+llb.getGroup_VII_load_relief();
	%>
	<td class="load"><a href="javascript:getLiveLoadSummary('VII','<%=em.getKey()%>')"><%=llb.getGroup_VII_load() %></a></td>
	<td class="load_relief"><%=llb.getGroup_VII_load_relief() %></td>
<%} %>
<td><%=load_val %></td><td><%=relief_val %></td><td><%=load_val+relief_val %></td>
</tr>
<tr>
<td>VIIA</td>
<%
load_val=0;
relief_val=0;
for(Map.Entry em:loadMap.entrySet()){
	LiveLoadBean llb=(LiveLoadBean)em.getValue();
	load_val=load_val+llb.getGroup_VII_A_load();
	relief_val=relief_val+llb.getGroup_VII_A_load_relief();
	%>
	<td class="load"><a href="javascript:getLiveLoadSummary('VIIA','<%=em.getKey()%>')"><%=llb.getGroup_VII_A_load() %></a></td>
	<td class="load_relief"><%=llb.getGroup_VII_A_load_relief() %></td>
<%} %>
<td><%=load_val %></td><td><%=relief_val %></td><td><%=load_val+relief_val %></td>
</tr>
<tr>
<td>VIII</td>
<%
load_val=0;
relief_val=0;
for(Map.Entry em:loadMap.entrySet()){
	LiveLoadBean llb=(LiveLoadBean)em.getValue();
	load_val=load_val+llb.getGroup_VIII_load();
	relief_val=relief_val+llb.getGroup_VIII_load_relief();
	%>
	<td class="load"><a href="javascript:getLiveLoadSummary('VIII','<%=em.getKey()%>')"><%=llb.getGroup_VIII_load() %></a></td>
	<td class="load_relief"><%=llb.getGroup_VIII_load_relief() %></td>
<%} %>
<td><%=load_val %></td><td><%=relief_val %></td><td><%=load_val+relief_val %></td>
</tr>
<tr>
<td>IX</td>
<%
load_val=0;
relief_val=0;
for(Map.Entry em:loadMap.entrySet()){
	LiveLoadBean llb=(LiveLoadBean)em.getValue();
	load_val=load_val+llb.getGroup_IX_load();
	relief_val=relief_val+llb.getGroup_IX_load_relief();
	%>
	<td class="load"><a href="javascript:getLiveLoadSummary('IX','<%=em.getKey()%>')"><%=llb.getGroup_IX_load() %></a></td>
	<td class="load_relief"><%=llb.getGroup_IX_load_relief() %></td>
<%} %>
<td><%=load_val %></td><td><%=relief_val %></td><td><%=load_val+relief_val %></td>
</tr>
<tr>
<td>XI</td>
<%
load_val=0;
relief_val=0;
for(Map.Entry em:loadMap.entrySet()){
	LiveLoadBean llb=(LiveLoadBean)em.getValue();
	load_val=load_val+llb.getGroup_XI_load();
	relief_val=relief_val+llb.getGroup_XI_load_relief();
	%>
	<td class="load"><a href="javascript:getLiveLoadSummary('XI','<%=em.getKey()%>')"><%=llb.getGroup_XI_load() %></a></td>
	<td class="load_relief"><%=llb.getGroup_XI_load_relief() %></td>
<%} %>
<td><%=load_val %></td><td><%=relief_val %></td><td><%=load_val+relief_val %></td>
</tr>
<tr>
<td>B/C</td>
<%
load_val=0;
relief_val=0;
for(Map.Entry em:loadMap.entrySet()){
	LiveLoadBean llb=(LiveLoadBean)em.getValue();
	load_val=load_val+llb.getGroup_BC_load();
	relief_val=relief_val+llb.getGroup_BC_load_relief();
	%>
	<td class="load"><a href="javascript:getLiveLoadSummary('BC','<%=em.getKey()%>')"><%=llb.getGroup_BC_load() %></a></td>
	<td class="load_relief"><%=llb.getGroup_BC_load_relief() %></td>
<%} %>
<td><%=load_val %></td><td><%=relief_val %></td><td><%=load_val+relief_val %></td>
</tr>
<tr>
<td>Total</td>
<%
load_val=0;
relief_val=0;
for(Map.Entry em:loadMap.entrySet()){
	LiveLoadBean llb=(LiveLoadBean)em.getValue();
	%>
	<td class="load"><%=llb.getTotal_load() %></td>
	<td class="load_relief"><%=llb.getTotal_load_relief() %></td>
<%} %>
<td><%=load_val %></td><td><%=relief_val %></td><td><%=load_val+relief_val %></td>
</tr>
</tbody>
<%}catch(Exception e){
	
} %>
</table>