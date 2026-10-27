<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="utils.utilities" %>

 
<%@page import="java.lang.*" %>
<%
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int energyMF = Integer.parseInt(request.getParameter("energyMF").toString());
    Double emf = 1.0;
    String data_string2[]=null;

    try {
        con = new DBUtil().getConnection(); 
        DecimalFormat tdec = new DecimalFormat("#0.000");

         
         /* String meter_sl_no = request.getParameter("meter_sl_no");
         System.out.println("meter_sl_no -------------------> "+meter_sl_no); */
         String meter_sl_no="";
         String meter_id = request.getParameter("meter_id");
         
         pstmt = con.prepareStatement("select meter_serial_no from meter where meterid='" + meter_id + "'");
         rs = pstmt.executeQuery();
         if (rs.next()) {
         	meter_sl_no =rs.getString("meter_serial_no");
         }

         
         
         
        String stringToReturn = "";
        String fromDate = request.getParameter("esdate").trim();
        String toDate = request.getParameter("eedate").trim();
        String sql = "";
        if (energyMF == 1) {
            pstmt = con.prepareStatement("select mf from meter where meter_serial_no='" + meter_sl_no + "'");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                emf =rs.getDouble("mf");
            }
        }
        System.out.println(energyMF+"\tmf for max-demand---->" + emf);
        
        pstmt = con.prepareStatement("select * from d3_columns_header where meter_serial_no='" + meter_sl_no + "' and billing_type='b5'");
    rs = pstmt.executeQuery();
    while (rs.next()) {
        data_string2 = rs.getString("billing_columns_header").split(",");
    }
%>

<div class="table-responsive">
<table cellpadding="0" cellspacing="0" border="0" id="energydatatable" class="stdtable stdtablecb smallfont">        
    <thead>
    <tr class="customrow">    
        <th  width="200px">Billing Date</th>
        <%for(String h:data_string2){ %>        
        <th  width="200px"><%=h%></th>
        <th  width="200px"> Occ. Date</th>
        <%} %>
        
    </tr>
    </thead>
    <tbody>
    <%
        
             if(!fromDate.equals("") && !toDate.equals("")){
            sql = "SELECT date_format(d3_time_stamp,'%d-%m-%Y %H:%i:%s')  billing_date,occ_date,data_string FROM d3_b5 where meter_serial_no='" + meter_sl_no + "' and data_string IS NOT NULL and data_string!='' and"
                    + " str_to_date(date_format(d3_time_stamp,'%d/%m/%Y'),'%d/%m/%Y') >= str_to_date('" + fromDate + "','%d/%m/%Y') and str_to_date(date_format(d3_time_stamp,'%d/%m/%Y'),'%d/%m/%Y') <= str_to_date('" + toDate + "','%d/%m/%Y') "
                    + "order by d3_time_stamp desc ";
             }
             else{
                 sql = "SELECT date_format(d3_time_stamp,'%d-%m-%Y %H:%i:%s')  billing_date,occ_date,data_string FROM d3_b5 where meter_serial_no='" + meter_sl_no + "' and data_string IS NOT NULL and data_string!='' order by d3_time_stamp desc";
             }
    System.out.println("sql  -------------------> "+sql);
            pstmt = con.prepareStatement(sql);

        
       

        //System.out.println("max demand--->"+sql);
        rs = pstmt.executeQuery();
        String dataString = "";

        while (rs.next()) {
            
            %>
            <tr>
    <%

            String occDate = rs.getString("occ_date");
            if (occDate.equals(",,")||occDate.equals(",")) {
                occDate = " , ,";
            }
            
            String dataStr = rs.getString("data_string");

            String occ_date[] = occDate.split(",");
            String data_string[] = dataStr.split(",");
            
          // System.out.println(occ_date.length+"\t"+data_string.length);

    %>




        <td ><%=rs.getString("billing_date")%></td>
        <%for(int i=0;i<data_string.length;i++){ %>         
          <td ><%=tdec.format(Double.parseDouble(data_string[i])*emf)%></td>
           <td ><%=i<occ_date.length?occ_date[i]:""%></td>
        <%} %>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</div>


<%

    } catch (Exception e) {
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