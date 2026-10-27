<%--<%@include file="report_header.jsp" %>--%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.text.*"%>
<%--<%@ include file="getConnection.jsp"%>--%>

<style>
.sorting_1{
white-space: nowrap;
}
</style>
<% 
    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt=null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    String data_string2[]=null;
    String sql="";
String meter_type="";
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
        
        pstmt = con.prepareStatement("SELECT * FROM smartmeter_instant where g1='"+meter_sl_no+"' ORDER BY log_time_stamp DESC LIMIT 1");
        rs = pstmt.executeQuery();
        if (rs.next()) {
        	meter_type =rs.getString("meter_type");
        }

        System.out.println(meter_sl_no+" "+meter_id);
        session.setAttribute("meter_sl_no", meter_sl_no);
        String stringToReturn = "";
        System.out.println("meterid  " + meter_sl_no);
      // meter_sl_no="Z00000072";
%>

<div class="table-responsive">
     <table cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont" id="instPush">   
         <thead>
            <tr> 
<% if(meter_type.equalsIgnoreCase("THREE PHASE")){ %>
<th>MeterRTC</th>
<th>RPH_VOLTAGE</th>
<th>YPH_VOLTAGE</th>
<th>BPH_VOLTAGE</th>
<th>RPH_LINE_CURRENT</th>
<th>YPH_LINE_CURRENT</th>
<th>BPH_LINE_CURRENT</th>
<th>Act power</th>
<th>React Power</th>
<th>Apparent Power</th>
<th>Rph PF</th>
<th>Yph PF</th>
<th>Bph PF</th>
<th>Avg. PF</th>
<th>Freq</th>
<th>KWH</th>
<th>KVAH</th>
<th>KVARH Lag</th>
<th>KVARH Lead</th>
<th>MD KW</th>
<th>MD KVA</th>
<th>PowerOn Duration</th>
<th>TAMPER COUNT</th>
<th>BILLING COUNT</th>
<th>PROGRAMMING COUNT</th>
<th>Connection Status</th>


<%}else{ %>
<th>MeterRTC</th>
<th>OBIS</th>
<th>Voltage</th>
<th>Current</th>
<th>KWH</th>
<th>MD KW</th>
<th>MD KVA</th>
<th>PowerOn Duration</th>
<th>TAMPER COUNT</th>
<th>BILLING COUNT</th>
<th>PROGRAMMING COUNT</th>
<th>Connection Status</th>
<%} %>
            </tr>
         </thead>    
         <tbody>
         
    <%
       session.setAttribute("meter_sl_no", meter_sl_no);
        
    String sql1="select * from smartmeter_instant where G1 like '%" + meter_sl_no+"%' order by log_time_stamp desc";

     pstmt = con.prepareStatement(sql1);
     System.out.println("sql --> "+sql1);
     rs = pstmt.executeQuery(); 
   

        String dataString = "";
        NumberFormat formatter = new DecimalFormat("#0.000");
        while (rs.next())
        { 
        	 if(meter_type.equalsIgnoreCase("THREE PHASE")){ 
        %>
          
         <tr>
         <td><%=rs.getString("g2")==null?"":rs.getString("g2")%></td>   
         <td><%=rs.getString("RPH_VOLTAGE")==null?"":rs.getString("RPH_VOLTAGE")%></td>  
         <td><%=rs.getString("YPH_VOLTAGE")==null?"":rs.getString("YPH_VOLTAGE")%></td>
         <td><%=rs.getString("BPH_VOLTAGE")==null?"":rs.getString("BPH_VOLTAGE")%></td>
		 
         <td><%=rs.getString("RPH_LINE_CURRENT")==null?"":rs.getString("RPH_LINE_CURRENT")%></td>  
         <td><%=rs.getString("YPH_LINE_CURRENT")==null?"":rs.getString("YPH_LINE_CURRENT")%></td>  
         <td><%=rs.getString("BPH_LINE_CURRENT")==null?"":rs.getString("BPH_LINE_CURRENT")%></td>
		 
         <td><%=rs.getString("act_power")==null?"":rs.getString("act_power")%></td>  
         <td><%=rs.getString("react_power")==null?"":rs.getString("react_power")%></td>  
         <td><%=rs.getString("apparent_power")==null?"":rs.getString("apparent_power")%></td>  
		 
         <td><%=rs.getString("rph_power_factor")==null?"":rs.getString("rph_power_factor")%></td>  
         <td><%=rs.getString("yph_power_factor")==null?"":rs.getString("yph_power_factor")%></td>  
         <td><%=rs.getString("bph_power_factor")==null?"":rs.getString("bph_power_factor")%></td>
         <td><%=rs.getString("avg_power_factor")==null?"":rs.getString("avg_power_factor")%></td>
         
         <td><%=rs.getString("frequency")==null?"":rs.getString("frequency")%></td>
         <td><%=rs.getString("kwh")==null?"":rs.getString("kwh")%></td>
         <td><%=rs.getString("kvah")==null?"":rs.getString("kvah")%></td>
         <td><%=rs.getString("kvarh_lag")==null?"":rs.getString("kvarh_lag")%></td>
         <td><%=rs.getString("kvarh_lead")==null?"":rs.getString("kvarh_lead")%></td>
         <td><%=rs.getString("mdkw")==null?"":rs.getString("mdkw")%></td>
         <td><%=rs.getString("mdkva")==null?"":rs.getString("mdkva")%></td>
         
         <td><%=rs.getString("power_on_duration")==null?"":rs.getString("power_on_duration")%></td>
         <td><%=rs.getString("tamper_count")==null?"":rs.getString("tamper_count")%></td>
         <td><%=rs.getString("billing_count")==null?"":rs.getString("billing_count")%></td>
         <td><%=rs.getString("programming_count")==null?"":rs.getString("programming_count")%></td>
         <td><%=rs.getString("disc_con")==null?"":rs.getString("disc_con")%></td>
        
            </tr>
                <%
        	 }else{ %>
        <tr>
         <td><%=rs.getString("g2")==null?"":rs.getString("g2")%></td>   
         <td><%=rs.getString("obis")==null?"":rs.getString("obis")%></td>  
         <td><%=rs.getString("1PH_VOLTAGE")==null?"":rs.getString("1PH_VOLTAGE")%></td>
         <td><%=rs.getString("1PH_current")==null?"":rs.getString("1PH_current")%></td>
		 
         
         <td><%=rs.getString("kwh")==null?"":rs.getString("kwh")%></td>        
         <td><%=rs.getString("mdkw")==null?"":rs.getString("mdkw")%></td>
         <td><%=rs.getString("mdkva")==null?"":rs.getString("mdkva")%></td>
         
         <td><%=rs.getString("power_on_duration")==null?"":rs.getString("power_on_duration")%></td>
         <td><%=rs.getString("tamper_count")==null?"":rs.getString("tamper_count")%></td>
         <td><%=rs.getString("billing_count")==null?"":rs.getString("billing_count")%></td>
         <td><%=rs.getString("programming_count")==null?"":rs.getString("programming_count")%></td>
         <td><%=rs.getString("disc_con")==null?"":rs.getString("disc_con")%></td>
        
            </tr>		
        		 
        	 <%}
            }//end of while
            
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

