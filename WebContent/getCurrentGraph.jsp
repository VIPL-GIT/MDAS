
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
        NumberFormat oneDec= new DecimalFormat("#0.0");
        String meterId = request.getParameter("meter_id").trim();
        String sdate = request.getParameter("sdate");
         String emf= request.getParameter("ApplyEMF");
         con1 = new DBUtil().getConnection();
         //getting cmf 
         rs=con1.prepareStatement("select * from meter where meterid="+meterId).executeQuery();
         if(rs.next())
            cmf=Double.parseDouble(oneDec.format(rs.getDouble("adopted_ctr")/rs.getDouble("meter_ctr")));
          if (emf.equals("0")) {
                  cmf = 1.0;
              } else {
                  cmf = cmf;
              }
         
       
            sql = "select substr(g2,11),rph_line_current,yph_line_current,bph_line_current from d2 where meterid='" + meterId + "' and str_to_date(g2,'%d-%m-%Y') = str_to_date('" + sdate + "','%d/%m/%Y')order by g2";
       
        
        st = con1.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()) {
            time = time + rs.getString(1) + ",";
            rph = rph + oneDec.format(rs.getDouble("rph_line_current")*cmf) + ",";
            yph = yph + oneDec.format(rs.getDouble("yph_line_current")*cmf) + ",";
            bph = bph + oneDec.format(rs.getDouble("bph_line_current")*cmf) + ",";
        }
        data = "time" + "," + time + "rph" + "," + rph + "yph" + "," + yph + "bph" + "," + bph;
        data = data.substring(0, data.length() - 1);
        out.println(data);
    } catch (Exception e) {
        e.printStackTrace();
    }

%>




