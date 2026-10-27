<%@page import="com.rtdas.meterdata.beans.*" %>
<%@page import="com.rtdas.feeder_reports.beans.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<div class="table-responsive"  id="mpreports">
<div class="col-md-12">
<div class="col-sm-3 col-lg-3 controls">
	<select class="" id="load_type" onchange="getBusBarLosses(this.value)" style="width:100%;height:25px;">
		
		<option value="2" ${level eq "2"?"selected":"" }>Circles </option>
		<option value="3" ${level eq "3"?"selected":"" }>Divisions</option>
		<option value="6" ${level eq "6"?"selected":"" }>Sub stations</option>
		
		
	</select>
						</div>
<h4 class="rTitle col-md-7">${title}</h4>
<button class="btn btn-sm col-md-2" onclick="exportDivtoExcel('BusBar_losses')">Export to Excel</button> 
</div>



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
%>
<div class="col-md-12" style="border:0;height:400px;overflow: scroll">
<table class="table table-advance" cellspacing="0" width="100%" id="reliabilityIndex" >
 <thead>
 <tr>
 <%if(level.contains("2") || level.contains("3")){ %> 
	 <th rowspan="2"><%=level.contains("2")?"Circle":"Division" %></th>
	 <th colspan="3">Consumption with MF in MWH</th>
	 <th rowspan="2">Loss (c-(a+b))</th>
 <%}else{%>
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
 <%} %>
 </tr>
 <tr>
 <%if(level.contains("2") || level.contains("3")){ %> 
	 <th>All 11KV (a)</th>
	 <th>All 33KV-OUT (b)</th>
	 <th>All 33KV-IN (c)</th>
 <%}else{%>
 <th>Initial</th>
 <th>Final</th>
 
 <%} %>
 </tr>
 </thead>
 <tbody>
<%

if(level.contains("2") || level.contains("3")){
	
	Map<String,BusbarLossesSummaryBean> map=new HashMap();
	for(TotalMasterdataBean tm:master){
		
		BusbarLossesBean blb;
		if(hlbList.containsKey(tm.getMeter_sl_no())){
			 blb=(BusbarLossesBean)hlbList.get(tm.getMeter_sl_no());
		}else{
			 blb=new BusbarLossesBean();
		}
		String hname=level.contains("2")?tm.getCircle():tm.getDivision();
		
		if(map.containsKey(hname)){
			BusbarLossesSummaryBean blsb=map.get(hname);
            if(tm.getSupply_voltage().contains("11")){
            	if(blb.getDiff_with_mf()!=null)
            	  blsb.setAll_11kv_cons(Double.parseDouble(blb.getDiff_with_mf())+blsb.getAll_11kv_cons());
            }
			
			if(tm.getSupply_voltage().contains("33") && tm.getLocation_category().equalsIgnoreCase("OUT")){
				if(blb.getDiff_with_mf()!=null)
				    blsb.setAll_33kv_out_cons(Double.parseDouble(blb.getDiff_with_mf())+blsb.getAll_33kv_out_cons());
			}
			
			if(tm.getSupply_voltage().contains("33") && tm.getLocation_category().equalsIgnoreCase("IN")){
				if(blb.getDiff_with_mf()!=null)
				blsb.setAll_33kv_in_cons(Double.parseDouble(blb.getDiff_with_mf())+blsb.getAll_33kv_in_cons());
			}
			map.put(hname,blsb);
		}else{
			BusbarLossesSummaryBean blsb=new BusbarLossesSummaryBean();
			blsb.setHierarchy(hname);
			
			if(tm.getSupply_voltage().contains("11")){
            	if(blb.getDiff_with_mf()!=null)
            	  blsb.setAll_11kv_cons(Double.parseDouble(blb.getDiff_with_mf()));
            }
			
			if(tm.getSupply_voltage().contains("33") && tm.getLocation_category().equalsIgnoreCase("OUT")){
				if(blb.getDiff_with_mf()!=null)
				    blsb.setAll_33kv_out_cons(Double.parseDouble(blb.getDiff_with_mf()));
			}
			
			if(tm.getSupply_voltage().contains("33") && tm.getLocation_category().equalsIgnoreCase("IN")){
				if(blb.getDiff_with_mf()!=null)
				blsb.setAll_33kv_in_cons(Double.parseDouble(blb.getDiff_with_mf()));
			}
			
			map.put(hname,blsb);
		}
		
	}//for total master
	
	for(Map.Entry em: map.entrySet()){
		double loss=0,loss_percent=0;
		BusbarLossesSummaryBean blsb=(BusbarLossesSummaryBean)em.getValue();
		
		System.out.println(em.getKey()+"\t"+blsb.getAll_11kv_cons()+"\t"+blsb.getAll_33kv_out_cons()+"\t"+blsb.getAll_33kv_in_cons());
		if(blsb.getAll_33kv_in_cons()>0){
			loss=blsb.getAll_33kv_in_cons()-(blsb.getAll_11kv_cons()+blsb.getAll_33kv_out_cons());
			loss_percent=(loss/blsb.getAll_33kv_in_cons())*100;
		}
		%>
		<tr>
		<td><%=em.getKey() %></td>
		<td><%= twodec.format(blsb.getAll_11kv_cons())%></td>
		<td><%= twodec.format(blsb.getAll_33kv_out_cons()) %></td>
		<td><%= twodec.format(blsb.getAll_33kv_in_cons()) %></td>
		<td><%=twodec.format(loss)  %> ( <%=twodec.format(loss_percent) %>%)</td>
		</tr>
	<%
	}
}//for level 2 and level 3

if(level.contains("6")){
for(TotalMasterdataBean tm:master){
	
	BusbarLossesBean blb;
	if(hlbList.containsKey(tm.getMeter_sl_no())){
		 blb=(BusbarLossesBean)hlbList.get(tm.getMeter_sl_no());
	}else{
		 blb=new BusbarLossesBean();
	}
	
	
		
		
	if(ss.equals(tm.getSs())){
		if(tm.getLocation_category().equals("IN")){
			in_diff=in_diff+(blb.getDiff()!=null?Double.parseDouble(blb.getDiff()):0);
			in_diff_mf=in_diff_mf+(blb.getDiff_with_mf()!=null?Double.parseDouble(blb.getDiff_with_mf()):0);
		}else{
			out_diff=out_diff+(blb.getDiff()!=null?Double.parseDouble(blb.getDiff()):0);
			out_diff_mf=out_diff_mf+(blb.getDiff_with_mf()!=null?Double.parseDouble(blb.getDiff_with_mf()):0);
		}
		
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
	

	if(ss.length()>0){
	if(in_diff_mf>0)
	  diff_mf=in_diff_mf-out_diff_mf;
	
	%>
	<tr>
	<td></td><td></td><td></td>
	<td></td><td></td><td></td>
	<td></td><td></td><td></td>
	<td></td>
	<td>Loss </td>	
	<td><%=twodec.format(diff_mf) %> (<%=twodec.format((diff_mf/in_diff_mf)*100) %> %)</td>
	</tr>
	<%} %>
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
	ss=tm.getSs();
	in_diff_mf=0;
	out_diff_mf=0;
	diff_mf=0;
	if(tm.getLocation_category().equals("IN")){
		in_diff=in_diff+(blb.getDiff()!=null?Double.parseDouble(blb.getDiff()):0);
		in_diff_mf=in_diff_mf+(blb.getDiff_with_mf()!=null?Double.parseDouble(blb.getDiff_with_mf()):0);
	}else{
		out_diff=out_diff+(blb.getDiff()!=null?Double.parseDouble(blb.getDiff()):0);
		out_diff_mf=out_diff_mf+(blb.getDiff_with_mf()!=null?Double.parseDouble(blb.getDiff_with_mf()):0);
	}
	}//else
	
}

}//for level 6
}catch(Exception e){
	e.printStackTrace();
}


%>
</tbody>
 </table>
 </div>
</div>