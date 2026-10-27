<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.text.*"%>
<%  Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String[] data_string2 = new String[0];

    try {
        
        int flag = 0;
        NumberFormat threeDec = new DecimalFormat("#0.000");
        
        String meter_id = request.getParameter("meter_id").trim();
        String data_type = request.getParameter("data_type");
        
        String startDate = request.getParameter("startindDate");
        startDate = startDate.substring(6, 10) + "/" + startDate.substring(3, 5) + "/" + startDate.substring(0, 2);

        String endDate = request.getParameter("endingDate");
        endDate = endDate.substring(6, 10) + "/" + endDate.substring(3, 5) + "/" + endDate.substring(0, 2);
        
        int emf=Integer.parseInt(request.getParameter("EMF").toString());
%>


<!-- <div class="tableoptions bdrbtm mart10" style="padding:2px;"> -->
<!--         <div class="snapshotdata" style="width:100%;text-align: center;">  <b>SNAPSHOT DATA</b> -->
<!--         <p style="margin:0px !important;float:right;"> -->
<!--               <p style="margin:0px !important;float:right;"> -->
<!--             <button class="btn btn-sm" onclick="exportExcelForSnapShotData('snapshotdata','snapshotdata.xls')">Export</button> -->
<!--             </p> -->
            
<!--         </p> -->
<!--         </div> -->
<!--         <div class="active_recvolt"> Show Active & Reactive Current <input name="check2" type="checkbox">  | Show voltagge in KV <input name="check2" type="checkbox"> </div> -->
<!--     </div>tableoptions -->





<div class="table-responsive">
<table cellpadding="0" cellspacing="0" border="0" id="snapshotdata" class="stdtable stdtablecb smallfont" width="100%">        
    <thead class="customrow">  
        <tr>
        <th >SnapShot Dates</th>

        <% try {
                con = new DBUtil().getConnection();
                pstmt = con.prepareStatement("select * from d6_columns_header where meter_serial_no=(select meter_serial_no from meter where meterid=" + meter_id + ")");
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    flag = 1;
                    System.out.println("---------------------------Snapshotr Header Got Dynamically-----------------------------------");
                    data_string2 = rs.getString("SNAPSHOT_COLUMNS_HEADER").split(",");
                } else {
                    flag = 1;
                    pstmt = con.prepareStatement("select no_of_params,param_names from metermake where metermakeid=(select metermake from meter where meter_id=" + meter_id + ")");
                    rs = pstmt.executeQuery();

                    while (rs.next()) {
                        data_string2 = rs.getString("param_names").split(",");
                    }
                }
            } catch (Exception e) {
                
           e.printStackTrace();
            }

            for (int i = 0; i < data_string2.length; i++) {
                String head = data_string2[i];
                if (head.equals("KWH(I)_tot")) {
                    head = "Tot_KWh(I)";
                }
                if (head.equals("KVAH_tot")) {
                    head = "Tot_KVAh";
                }
                if (head.equals("KVARH_Lead")) {
                    head = "KVARh(Lead)";
                }
                if (head.contains("TOT")) {
                    head = head.replaceAll("TOT", "Tot");
                }
        %>
        <th ><%=head%></th>
        <%}
        %>
        </tr>
    </thead>

    <tbody>
        <% 
            String meter_sl_no = "";
            double mf=1.0;
            pstmt = con.prepareStatement("select meter_serial_no,mf from meter where meterid=" + meter_id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                meter_sl_no = rs.getString("meter_serial_no");
                mf=rs.getDouble("mf");
            }
            if(emf==1) mf=mf;
            else mf=1.0;
          System.out.println("snapshot meterid---->" + meter_id+"  mf == "+mf);
            String sql = "select snapshot_date,data_string from d6_data where meter_serial_no='" + meter_sl_no + "' and DATE(D6_TIME_STAMP) BETWEEN " + " ' " + startDate + " ' " + " and " + " '" + endDate + " ' order by D6_TIME_STAMP desc";
           System.out.println("------------"+sql);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String data_string[] = rs.getString("data_string").split(",");
        %>

        <tr>
            <td ><%=rs.getString("snapshot_date")%></td>

            <% for (int i = 0; i <= data_string.length - 1; i++) {
                try{
             %>
            <td ><%=threeDec.format(Double.parseDouble(data_string[i])*mf)%></td>
            <% }
               catch(Exception e){}
}%>
        </tr>
        <%  }
            } catch (Exception e) {
                out.println("Please Try Again....");
                e.printStackTrace();
            }
        %>
    </tbody>
</table>
 </div>     