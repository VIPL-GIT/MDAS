<%@ page import="java.sql.*" %>
<%@ page import="java.text.*" %>
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
SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");

    String reportDate = request.getParameter("reportDate");
     reportDate=reportDate.replaceAll("/","-");
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
            <td class="thstyle">Request Type</td>
            <td class="thstyle">Request Parameter</td>           
            <td class="thstyle">Request Time</td>
            
        </tr>
    <tbody>
        <%  try {
                con = new DBUtil().getConnection();
                reportDate=sdf1.format(sdf.parse(reportDate));
                
                if (!reportDate.equals("")) {
                   
                	sql = "select * from smart_meter_od where  log_time_stamp like '" + reportDate + "%' order by log_time_stamp desc";
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
            <td class="head0"><%=rs.getString("meter_no")%></td>  
            <td class="head0"><%=rs.getString("action_type")%></td>  
            <td class="head0"><%=rs.getString("name")%></td>              
            <td class="head0"><%=rs.getDate("log_time_stamp") + " " + rs.getTime("log_time_stamp")%></td>
            
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