
<%@page import="java.sql.*"%>
<%@page import="java.text.DecimalFormat"%> 
<%@page import="utils.DBUtil"%>

<%!    //import java.text.DecimalFormat;
    PreparedStatement pstmt, pstmt1, pstmt2 = null;
    ResultSet rs, rs1, rs2 = null;
    double ctr = 1.0, ptr = 1.0, Actr = 1.0, Aptr = 1.0;
    double vmf = 1.0, cmf = 1.0, pmf = 1.0;
    double mf = 1.0;
    DecimalFormat df1 = new DecimalFormat("#0.000");
    DecimalFormat df2 = new DecimalFormat("#0.000");

%>
<%
    try {
        Connection con = new DBUtil().getConnection();
        String meter_id = "";
        String searchDate = request.getParameter("searchDate").trim();
        //searchDate=searchDate.split(" ")[0];
        if (request.getParameter("meter_no") == null || request.getParameter("meter_no") == "") {
            meter_id = (String) session.getAttribute("meter_no");
        } else {
            meter_id = request.getParameter("meter_no");
        }
        session.setAttribute("meter_no", meter_id);
        String stringToReturn = "";

%>

<%

        double aemf = 1.0;
        if (request.getParameter("ApplyEMF") != null) {
            aemf = Double.parseDouble(request.getParameter("ApplyEMF"));
        } else {
            System.out.println("Apply EMF is not available ");
        }//end else
        System.out.println("EMF----------------->" + aemf);

        double ctr, ptr, Actr, Aptr;
        double vmf = 1.0, cmf = 1.0;
        System.out.println("meterid---->" + meter_id+"\t"+searchDate);
        pstmt2 = con.prepareStatement("select * from meter where meter_serial_no='" + meter_id + "'");
        rs2 = pstmt2.executeQuery();
        if (rs2.next()) {
            ctr = Double.parseDouble(rs2.getString("METER_CTR") == null ? "1.0" : rs2.getString("METER_CTR"));
            ptr = Double.parseDouble(rs2.getString("METER_PTR") == null ? "1.0" : rs2.getString("METER_PTR"));
            Actr = Double.parseDouble(rs2.getString("ADOPTED_CTR") == null ? "1.0" : rs2.getString("ADOPTED_CTR"));
            Aptr = Double.parseDouble(rs2.getString("ADOPTED_PTR") == null ? "1.0" : rs2.getString("ADOPTED_PTR"));
            cmf = (Actr / Aptr) * (ctr / ptr);
            vmf = Double.parseDouble(df1.format(Aptr / ptr));
            pmf = Double.parseDouble(df1.format(cmf / vmf));
            /* System.out.println("ctr:" + ctr);
            System.out.println("ptr:" + ptr);
            System.out.println("Actr:" + Actr);
            System.out.println("Aptr:" + Aptr);
            System.out.println("vmf:" + vmf);
            System.out.println("cmf:" + cmf);
            System.out.println("pmf:" + pmf); */
        }
        pstmt = con.prepareStatement("select * from d2_curr where meter_serial_no='" + meter_id + "' and  str_to_date(g2,'%d-%m-%Y %H:%i:%s') = str_to_date('" + searchDate + "','%d-%m-%Y') order by meter_time_stamp desc limit 1");
        rs = pstmt.executeQuery();
        String dataString = "";
        if (rs.next()) {
            if (aemf == 0) {
            } else {
                dataString = dataString
                        + (rs.getString("rph_voltage")== null ? 0 :Math.round(Double.parseDouble(rs.getString("rph_voltage")))) + ","
                        + (rs.getString("yph_voltage") ==null ? 0 :Math.round(Double.parseDouble(rs.getString("yph_voltage")))) + ","
                        + (rs.getString("bph_voltage") ==null ? 0 :Math.round(Double.parseDouble(rs.getString("bph_voltage")))) + ":"
                        + (rs.getString("rph_line_current")==null ? 0 :df1.format(Double.parseDouble(rs.getString("rph_line_current")))) + ","
                        + (rs.getString("yph_line_current") ==null ?0 :df1.format(Double.parseDouble(rs.getString("yph_line_current")))) + ","
                        + (rs.getString("bph_line_current") ==null ?0 :df1.format(Double.parseDouble(rs.getString("bph_line_current")))) + ":"
                        + (rs.getString("rph_power_factor") == null ?0: df2.format(Double.parseDouble(rs.getString("rph_power_factor")))) + ","
                        + (rs.getString("yph_power_factor")== null ?0 :df2.format(Double.parseDouble(rs.getString("yph_power_factor")))) + ","
                        + (rs.getString("bph_power_factor") == null ?0 :df2.format(Double.parseDouble(rs.getString("bph_power_factor"))))+":"
                        + (rs.getString("R_Y_voltage_angle") == null ?120 :df2.format(Double.parseDouble(rs.getString("R_Y_voltage_angle"))))+","
                       + (rs.getString("Y_B_voltage_angle") == null ?240 :df2.format(Double.parseDouble(rs.getString("Y_B_voltage_angle"))));
            }//end else
        }//if
               else{
                       pstmt = con.prepareStatement("select * from d2 where meter_serial_no='" + meter_id + "' and  str_to_date(g2,'%d-%m-%Y %H:%i:%s') = str_to_date('" + searchDate + "','%d-%m-%Y %H:%i:%s') order by meter_time_stamp desc limit 1");
                       rs = pstmt.executeQuery();

                       if (rs.next()) {
                           if (aemf == 0) {
                           } else {
                               dataString = dataString
                        + (rs.getString("rph_voltage")== null ? 0 :Math.round(Double.parseDouble(rs.getString("rph_voltage")))) + ","
                        + (rs.getString("yph_voltage") ==null ? 0 :Math.round(Double.parseDouble(rs.getString("yph_voltage")))) + ","
                        + (rs.getString("bph_voltage") ==null ? 0 :Math.round(Double.parseDouble(rs.getString("bph_voltage")))) + ":"
                        + (rs.getString("rph_line_current")==null ? 0 :df1.format(Double.parseDouble(rs.getString("rph_line_current")))) + ","
                        + (rs.getString("yph_line_current") ==null ?0 :df1.format(Double.parseDouble(rs.getString("yph_line_current")))) + ","
                        + (rs.getString("bph_line_current") ==null ?0 :df1.format(Double.parseDouble(rs.getString("bph_line_current")))) + ":"
                        + (rs.getString("rph_power_factor") == null ?0: df2.format(Double.parseDouble(rs.getString("rph_power_factor")))) + ","
                        + (rs.getString("yph_power_factor")== null ?0 :df2.format(Double.parseDouble(rs.getString("yph_power_factor")))) + ","
                        + (rs.getString("bph_power_factor") == null ?0 :df2.format(Double.parseDouble(rs.getString("bph_power_factor"))))+":"
                        + (rs.getString("R_Y_voltage_angle") == null ?120 :df2.format(Double.parseDouble(rs.getString("R_Y_voltage_angle"))))+","
                        + (rs.getString("Y_B_voltage_angle") == null ?240 :df2.format(Double.parseDouble(rs.getString("Y_B_voltage_angle"))));
                           }//end else

                       }
                   }//else

        System.out.println("instant guage values--->" + dataString);
        out.println(dataString);

    

} catch (Exception e) {
        e.printStackTrace();
        //response.sendRedirect("../errorPage.jsp?message=" + e.getMessage());
    }
%>