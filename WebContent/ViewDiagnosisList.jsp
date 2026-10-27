<%@page import="utils.DBUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.rtdas.meterdata.daoimpl.AlertsAndDiagnosisDao"%>
<%@page import="com.rtdas.meterdata.beans.ModemDiagnosisBean"%>
<%@page import="com.rtdas.meterdata.daoimpl.*"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>

<%
String meter =request.getParameter("meter");
String date1=request.getParameter("alertdate");
String y=date1.substring(6, 10);
String m=date1.substring(3, 5);
String d=date1.substring(0, 2);

String fdate=y+"-"+m+"-"+d;
fdate=d+"-"+m+"-"+y;
System.out.println(fdate+"  meter --> "+meter);
List<ModemDiagnosisBean> ModemList=AlertsAndDiagnosisDao.getModemDiagnosisList(meter, fdate);



%>

<div id="main-content">
            
           	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-bd">



				<div class="panel-body">

					<div class="clearfix"></div>
					<div class="table-responsive" style="border: 0">

					<table class="table table-advance" cellspacing="0" width="100%" id="table1">

							<thead>
								<tr>
	 <th> Sl No</th>
     <th>Modem Sl.No</th>
     <th>Timestamp</th>
     <th>Firmware Version</th>
     <th>battery</th>
     <th>GSM signal</th>
     <th>Temperature</th>
     <th>Cable status</th>
     <th>SIM No</th>
     <th>IMEI No</th>
     <th>IP Address</th>
     
    
								</tr>
							</thead>
							<tbody>
							<% 
Iterator<ModemDiagnosisBean> itr=ModemList.iterator();
							int count=1;
while(itr.hasNext()){
	ModemDiagnosisBean mab=itr.next();
	
%>
							
							
<tr>
<td><%=count%></td>
<td><%=mab.getModem_serial_no()==null?"":mab.getModem_serial_no()%></td>
<td><%=mab.getDiag_timestamp()==null?"":mab.getDiag_timestamp()%></td>
<td><%=mab.getFv()==null?"":mab.getFv()%></td>
<td><%=mab.getBattery()==null?"":mab.getBattery()%></td>
<%if(Integer.parseInt(mab.getGsm_signal())<=10){ %>
<td style="color:#ff4d4d;font-weight:bold;"><%=mab.getGsm_signal()==null?"":mab.getGsm_signal()%></td>
<% }%>

<%if(Integer.parseInt(mab.getGsm_signal())>10 && Integer.parseInt(mab.getGsm_signal())<=17){ %>
<td style="color:#7979bf;;font-weight:bold;"><%=mab.getGsm_signal()==null?"":mab.getGsm_signal()%></td>
<% }%>


<%if(Integer.parseInt(mab.getGsm_signal())>17){ %>
<td style="color:#0ee28a;font-weight:bold;"><%=mab.getGsm_signal()==null?"":mab.getGsm_signal()%></td>
<% }%>

<td><%=mab.getTemperature()==null?"":mab.getTemperature()%></td>
<td><%=mab.getCablestatus()==null?"":mab.getCablestatus()%></td>
<td><%=mab.getSimno()==null?"":mab.getSimno()%></td>
<td><%=mab.getImeino()==null?"":mab.getImeino()%></td>
<td><%=mab.getIpaddress()==null?"":mab.getIpaddress()%></td>

</tr>
							
							<%count++; }%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>