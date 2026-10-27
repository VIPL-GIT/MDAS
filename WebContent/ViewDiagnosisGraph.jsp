
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.*"%>
<%
    try {
        Connection con1 = null;
        Statement st = null;
        String sql = null;
        ResultSet rs=null;
        String rph = "";
        String yph = "";
        String bph = "";
        String data = "";
        String time = "";
        Double cmf=1.0;
   //     NumberFormat oneDec= new DecimalFormat("#0.0");
        String meter = request.getParameter("meter").trim();
        String alertdate = request.getParameter("alertdate");
        String y=alertdate.substring(6, 10);
        String m=alertdate.substring(3, 5);
        String d=alertdate.substring(0, 2);

        String fdate=y+"-"+m+"-"+d;

        fdate=d+"-"+m+"-"+y;
         con1 = new DBUtil().getConnection();
         sql="select gsm_signal,temperature from device_diagnosis where meter_serial_no=(select meter_serial_no from meter where meterid='" + meter + "') and diag_timestamp like '%"+fdate+"%'";
        System.out.println("sql -> "+sql);
        st = con1.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()) {
        	time="1";
        	rph=rph+rs.getString(1)+ ",";
        	yph=yph+rs.getString(2).replace("*C","")+ ",";
        	bph=bph+rs.getString(1)+ ",";
            /* time = time + rs.getString(1) + ",";
            rph = rph + oneDec.format(rs.getDouble("rph_line_current")*cmf) + ",";
            yph = yph + oneDec.format(rs.getDouble("yph_line_current")*cmf) + ",";
            bph = bph + oneDec.format(rs.getDouble("bph_line_current")*cmf) + ","; */
        }
        data = "time" + "," + time + "rph" + "," + rph + "yph" + "," + yph + "bph" + "," + bph;
        data = data.substring(0, data.length() - 1);
        System.out.println(data);
        out.println(data);
    } catch (Exception e) {
        e.printStackTrace();
    }

%>




