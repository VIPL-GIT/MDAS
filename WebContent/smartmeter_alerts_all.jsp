<%--<%@include file="report_header.jsp" %>--%>
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
        String meter_id = "";
        NumberFormat oneDec = new DecimalFormat("#0.0");
      
        
        meter_id = request.getParameter("meter_id");
        System.out.println("Meter------------------"+meter_id);
        
        pstmt = con.prepareStatement("select meter_serial_no from meter where meterid='" + meter_id + "'");
        rs = pstmt.executeQuery();
        if (rs.next()) {
        	meter_sl_no =rs.getString("meter_serial_no");
        }

        System.out.println(meter_sl_no+" "+meter_id);
        
        session.setAttribute("meter_sl_no", meter_sl_no);
        String stringToReturn = "";
     
        System.out.println("meterid  " + meter_sl_no);
       // meter_sl_no="Z00000072";
%>

<div class="table-responsive">
     <table cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont" id="pushAlerts">   
          
     <thead>
            <tr> 
            <th>Meter RTC</th>
            <th>OBIS</th>
            <th>Bits</th>
            </tr>
            </thead>
            <tbody> 
         
    <%
    
       session.setAttribute("meter_sl_no", meter_sl_no);
       String sql1="SELECT * FROM  smart_meter_alerts_64 WHERE  meter_serial_no LIKE  '%" + meter_sl_no+"%' order by log_timestamp desc ";

     pstmt = con.prepareStatement(sql1);
     System.out.println("sql --> "+sql1);
     rs = pstmt.executeQuery(); 
   
        String dataString = "",meterSrNo="",meterRTC="",obis="";
        NumberFormat formatter = new DecimalFormat("#0.000");
        String bit="";
        while (rs.next())
        { 
        	%>
<tr>
<td><a href="javascript:getSmartAlertPopup('<%=rs.getString("meter_serial_no") %>','<%=rs.getString("meter_rtc") %>')"><%=rs.getString("meter_rtc") %></a></td>
<td><%=rs.getString("obis") %></td>
<td><%=rs.getString("bit_string") %></td>
</tr>        	
        	<%
        }
         
   
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

