<%@page import="com.rtdas.meterdata.beans.*" %>
<%@page import="com.rtdas.feeder_reports.beans.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<div class="table-responsive" style="border:0;height:400px;" id="mpreports">
<table class="table table-advance" cellspacing="0" width="100%" id="reliabilityIndex">
 <thead>
 <tr>
 <th rowspan="2">Circle</th>
 <th rowspan="2">Division</th>
 <th rowspan="2">Substation</th>
 <th rowspan="2">Feeder</th>
 <th rowspan="2">Category</th>
 <th rowspan="2">Voltage Level</th>
 <th rowspan="2">Meter sl.no</th>
 <th rowspan="2">MF</th>
 <th colspan="2">KWh</th>
 <th rowspan="2">KWh Diff</th>
 <th rowspan="2">Consumption with MF in MWH</th>
 </tr>
 <tr>
 <th>Initial</th>
 <th>Final</th>
 </tr>
 </thead>
 <tbody>
<%
try{
	List<TotalMasterdataBean> master=(List<TotalMasterdataBean>)request.getAttribute("master");
	Map<String,BusbarLossesBean> hlbList=(Map<String,BusbarLossesBean>)request.getAttribute("hlbList");
	String level=(String)request.getAttribute("level");
	
	DecimalFormat twodec=new DecimalFormat("#0.0000");
	String ss="";
	double diff=0,diff_mf=0;
	double in_diff=0,in_diff_mf=0;
	double out_diff=0,out_diff_mf=0;
	
	for(TotalMasterdataBean tm:master){
		
		BusbarLossesBean blb;
		if(hlbList.containsKey(tm.getMeter_sl_no())){
			 blb=(BusbarLossesBean)hlbList.get(tm.getMeter_sl_no());
		}else{
			 blb=new BusbarLossesBean();
		}
		
		if(level.contains("6")){
			if(tm.getLocation_category().equals("IN")){
				in_diff=in_diff+(blb.getDiff()!=null?Double.parseDouble(blb.getDiff()):0);
				in_diff_mf=in_diff_mf+(blb.getDiff_with_mf()!=null?Double.parseDouble(blb.getDiff_with_mf()):0);
			}else{
				out_diff=out_diff+(blb.getDiff()!=null?Double.parseDouble(blb.getDiff()):0);
				out_diff_mf=out_diff_mf+(blb.getDiff_with_mf()!=null?Double.parseDouble(blb.getDiff_with_mf()):0);
			}
		}
		
		if(ss.equals(tm.getSs())){
			
		%>
		 <tr>
		<td><%=tm.getCircle() %></td>
		<td><%=tm.getDivision() %></td>
		<td><%=tm.getSs() %></td>
		<td><%=tm.getLocation_name() %></td>
		<td><%=tm.getLocation_category() %></td>
		<td><%=tm.getSupply_voltage() %></td>
		<td><%=tm.getMeter_sl_no() %></td>
		<td><%=tm.getMf() %></td>
		<td><%=blb.getInitial_kwh()==null?"":blb.getInitial_kwh() %></td>
		<td><%=blb.getFinal_kwh()==null?"":blb.getFinal_kwh() %></td>
		<td><%=blb.getDiff()==null?"":blb.getDiff() %></td>
		<td><%=blb.getDiff_with_mf()==null?"":blb.getDiff_with_mf() %></td>
		</tr>	
		<%}else{
		%>
		<tr><td colspan="12" style="background: bisque;"></td></tr>
		<tr>
		<td><%=tm.getCircle() %></td>
		<td><%=tm.getDivision() %></td>
		<td><%=tm.getSs() %></td>
		<td><%=tm.getLocation_name() %></td>
		<td><%=tm.getLocation_category() %></td>
		<td><%=tm.getSupply_voltage() %></td>
		<td><%=tm.getMeter_sl_no() %></td>
		<td><%=tm.getMf() %></td>
		<td><%=blb.getInitial_kwh()==null?"":blb.getInitial_kwh() %></td>
		<td><%=blb.getFinal_kwh()==null?"":blb.getFinal_kwh() %></td>
		<td><%=blb.getDiff()==null?"":blb.getDiff() %></td>
		<td><%=blb.getDiff_with_mf()==null?"":blb.getDiff_with_mf() %></td>
		</tr>
		<%	
		}
		ss=tm.getSs();
		
	}
	diff=in_diff-out_diff;
	if(in_diff_mf>0)
	  diff_mf=in_diff_mf-out_diff_mf;
	if(level.contains("6")){
	%>
	<tr>
	<td></td><td></td><td></td>
	<td></td><td></td><td></td>
	<td></td><td></td><td></td>
	<td></td>
	<td>Loss</td>	
	<td><%=twodec.format(diff_mf) %></td>
	</tr>
<%	
	}
}catch(Exception e){
	e.printStackTrace();
}

%>
</tbody>
 </table>
</div>