<%--<%@include file="report_header.jsp" %>--%>

<link href="css/table.css" rel="stylesheet" type="text/css" />

<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.text.*"%>
<%--<%@ include file="getConnection.jsp"%>--%>

<% 
    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt=null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    String data_string2[]=null;
    String sql="";

    try {
        con = new DBUtil().getConnection(); 
        String meter_sl_no = "";
        NumberFormat oneDec = new DecimalFormat("#0.0");
      
        
        meter_sl_no = request.getParameter("meter");
        String time = request.getParameter("time");
        
        
        session.setAttribute("meter_sl_no", meter_sl_no);
        String stringToReturn = "";
     
        System.out.println("meterid  " + meter_sl_no);
       // meter_sl_no="Z00000072";
%>

<div class="table-responsive" style="margin-left:25%;">
     <table cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont" id="pushAlerts">   
       
    <%
    Map<String,String> eventMap = new HashMap<String,String>();
    eventMap.put("0","R Phase Voltage Missing [ Three Phase Meter ]");
    eventMap.put("1","Y Phase Voltage Missing [ Three Phase Meter ]");
    eventMap.put("2","B Phase Voltage Missing [ Three Phase Meter ]");
    eventMap.put("3","Over Voltage in any phase");
    eventMap.put("4","Low Voltage in any phase");
    eventMap.put("5","Voltage Unbalance");
    eventMap.put("6","R Phase Current Reverse (Import Type Only)");
    eventMap.put("7","Y Phase Current Reverse (Import Type Only)");
    eventMap.put("8","B Phase Current Reverse (Import Type Only)");
    eventMap.put("9","Current Unbalance");
    eventMap.put("10","Current Bypass/Short");
    eventMap.put("11","Over Current in any phase");
    eventMap.put("12","Very Low PF");
    eventMap.put("51","Earth Loading");
    eventMap.put("81","Influence of permanent magnet or ac/dc electromagnet");
    eventMap.put("82","Neutral Disturbance - IIF, dc or alternate method");
    eventMap.put("83","Meter Cover Opening");
    eventMap.put("84","Meter Load disconnected / Meter Load Connected");
    eventMap.put("85","Last Gap");
    eventMap.put("86","First Breath");
    eventMap.put("87","Increment in billing counter (Manual MRI Reset)");
    
       session.setAttribute("meter_sl_no", meter_sl_no);
       String sql1="SELECT * FROM  smart_meter_alerts_64 WHERE  meter_serial_no LIKE  '%" + meter_sl_no+"%' and meter_rtc='"+time+"' ";

     pstmt = con.prepareStatement(sql1);
     System.out.println("sql --> "+sql1);
     rs = pstmt.executeQuery(); 
   
        String dataString = "",meterSrNo="",meterRTC="",obis="";
        NumberFormat formatter = new DecimalFormat("#0.000");
        String bit="";
        while (rs.next())
        { 
        	bit=rs.getString(5);
        	meterSrNo=rs.getString("meter_serial_no");
        	meterRTC=rs.getString("meter_rtc");
        	obis=rs.getString("obis");
        }
         
   /* String events=displayEvents(bit);
   System.out.println(events.length()+"events --> "+events); */
   
   String eventName="",status="";
   System.out.println("=========================ALERT START===============================");
   String result = bit;
   %>
   
   <thead><tr>    <th>MeterSrNo = <%=meterSrNo %></th><th>Meter RTC = <%=meterRTC %></th><th>OBIS = <%=obis %></th>        </tr></thead>
   <thead><tr>    <th>Bit No</th><th>Event/Alert Name</th><th>Status</th>        </tr></thead>
   
    <tbody>
   <%
   for(int row=0;row<result.length();row++){
	   
       eventName="";
       if(result.substring(row,row+1).equals("0")){
           status = "OFF";
       }else if(result.substring(row,row+1).equals("1")){
           status = "ON";
       }
       if(eventMap.containsKey(row+"")){
           eventName = eventMap.get(row+"");
       }else{
           eventName = "-";
		   continue;
       }
       System.out.println(row+"\t"+eventName+"\t"+status);
       %>
       <tr><td><%=row%></td><td><%=eventName%></td>
       <%if(result.substring(row,row+1).equals("1")){ %>
       <td style="background-color:#90EE90"><%=result.substring(row,row+1)%> (<%=status%>)</td>
       <%}else{ %>
       <td><%=result.substring(row,row+1)%> (<%=status%>)</td>
       <%} %>
       </tr>
       
       <%
   }//for    
   System.out.println("=========================ALERT END===============================");   
        %>
    
    
    
         </tbody>
</table>
</div>

      
<%
    }  catch (Exception e) {
        String s = "";
        if (e.getClass().toString().contains("CommunicationsException")) {
            out.println("Plz Try Again....<font color='red'>Database Connection lost</font>");
        }
        if (e.getClass().toString().contains("MySQLSyntaxErrorException")) {
            out.println("Plz Try Again....<font color='red'>Invalid Request</font>");
        }
        if (e.getClass().toString().contains("SQLException")) {
            out.println("Plz Try Again....<font color='red'>Check the DB Query</font>");
        }
        e.printStackTrace();
    }
%>

