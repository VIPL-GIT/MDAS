<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.rtdas.meterdata.daoimpl.AlertsAndDiagnosisDao"%>
<%@page import="com.rtdas.meterdata.beans.ModemAlertsBean"%>
<%@page import="java.util.*" %>

<%
String meter =request.getParameter("meter");
String date1=request.getParameter("alertdate");
String y=date1.substring(6, 10);
String m=date1.substring(3, 5);
String d=date1.substring(0, 2);

String fdate=y+"-"+m+"-"+d;
fdate=d+"-"+m+"-"+y;

List<ModemAlertsBean> ModemList=AlertsAndDiagnosisDao.getDiagList(meter,fdate);


%>

<div id="main-content">
            
           	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-bd">



				<div class="panel-body">

					<div class="clearfix"></div>
					<div class="table-responsive" style="border: 0">

						<table id="example" cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont" style="width:100%">
							<thead>
								<tr>
                                    <th>Sl No</th>
									<th>Modem Sl.No</th>
									<th>Alert type</th>
									<th>Alert timestamp</th>
									<th>Firmware version</th>
									<th>IMEI No</th>
									<th>IP Address</th> 
									
								</tr>
							</thead>
							<% 
Iterator<ModemAlertsBean> itr=ModemList.iterator();
							int count=1;
while(itr.hasNext()){
	ModemAlertsBean mab=itr.next();
%>
							<tbody>
<tr>
<td><%=count%></td>
<td><%=mab.getModem_serial_no()==null?"":mab.getModem_serial_no()%></td>
<td><%=mab.getAlert_type()==null?"":mab.getAlert_type()%></td>
<td><%=mab.getAlert_timestamp()==null?"":mab.getAlert_timestamp()%></td>
<td><%=mab.getFv()==null?"":mab.getFv()%></td>
<td><%=mab.getImeino()==null?"":mab.getImeino()%></td>
<td><%=mab.getIpaddress()==null?"":mab.getIpaddress()%></td>

</tr>
							</tbody>
							<%count++; }%>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>