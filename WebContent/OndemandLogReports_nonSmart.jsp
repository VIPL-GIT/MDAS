<%@ page import="java.sql.*" %>
<%@page import="utils.DBUtil"%>
<style>
    .thstyle{
        background-color: #8FA1B5; 
        color:#FFFFFF;
        font-size:14px;
        border-left:1px solid #FFFFFF;
        text-decoration:none;
        font-family: monospace;
        border-bottom: 1px solid #FFFFFF;
    }
    .trbg{
        background-color: #E8EBE2;
        border-bottom: 1px solid #FFFFFF;
    }
    .tdbg{
        border-bottom: 1px solid #FFFFFF;
        border-left: 1px solid #FFFFFF;
    }
    #district_11kv_load tr  td {
        background-color: #E8EBE2;
        border-bottom: 1px solid #FFFFFF;
        border-left: 1px solid #FFFFFF;
    }
</style> 
<%
    String reportDate = request.getParameter("reportDate");
    //String meters = request.getParameter("meters").trim();
    //System.out.println("meters = "+meters);
//    String meter[] = meters.split(",");
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    Connection con = null;
    String sql = "";
    String sql1 = "";
%>

<table cellpadding="0" cellspacing="0" border="0" id="" class="stdtable stdtablecb smallfont">        
    <tr class="customrow"> 
            <td class="thstyle">Meter No</td>       
            <td class="thstyle">TimeStamp</td>
            <td class="thstyle">Request Type</td>           
            <td class="thstyle">Response Data</td>
            <!--<td class="thstyle">On-Demand Status</td>-->
        </tr>
    <tbody>
        <%  try {
                con = new DBUtil().getConnection();
                //System.out.println(reportDate+"\t"+meters+" "+con);                
//                 sql1 = "select METERID,METER_SERIAL_NO from meter WHERE METERID="+meters;
// //                System.out.println("sql111111111 = "+sql1);
//                 pstmt = con.prepareStatement(sql1);
//                 rs1 = pstmt.executeQuery();
//                 while (rs1.next()) {
//                     System.out.println("in while loop");
//                     meters = rs1.getString("METER_SERIAL_NO");
//                     System.out.println("METER_SERIAL_NO ="+rs1.getString("METER_SERIAL_NO"));
//                 }

                if (!reportDate.equals("")) {
                    //sql = "select * from sms_config where meter_id in('" + meters + "') and DATE_FORMAT(log_time_stamp,'%d/%m/%Y') like '" + reportDate + "%' order by meter_id,log_time_stamp ";
                	sql = "select * from sms_config where  DATE_FORMAT(log_time_stamp,'%d/%m/%Y') like '" + reportDate + "%' order by meter_id,log_time_stamp ";
                } else {
                    sql = "";
                }

                 System.out.println("------------------------------------------------------->"+sql);
                rs = con.prepareStatement(sql).executeQuery();
                int flag = 0;
                while (rs.next()) {
                    flag = 1;
        %>
        <tr class="head0">
            <td class="head0"><%=rs.getString("meter_id")%></td>              
            <td class="head0"><%=rs.getDate("log_time_stamp") + " " + rs.getTime("log_time_stamp")%></td>
            <td class="head0"><%=rs.getString("req_type") == null ? " " : rs.getString("req_type")%> </td>            
            <td class="head0"><%=rs.getString("res_data")==null?"":rs.getString("res_data")%></td>            
           <%-- <td class="head0"><%=rs.getString("sms_status")%></td> --%>
        </tr>  
        <%}//while
            if (flag == 0) {
        %>
        <tr class="head0"><td colspan="5" align="center">Data not available</td></tr>
        <%}
            }//try
            catch (Exception e) {
                out.println("<h4>Error:</h4>" + e.getMessage());
            }
        %>
    </tbody>
</table>