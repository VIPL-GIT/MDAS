
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.*"%>
<%
    Connection con1 = null;
    Statement st = null;
    ResultSet rs=null;
    String sql = null;
    String sql1 = null;
    String rph = "";
    String yph = "";
    String bph = "";
    String data = "";
    String time = "";
    Double vmf=1.0;
    String meterId = request.getParameter("meter_id").trim();
    String sdate = request.getParameter("sdate");
    System.out.println("sdate-"+sdate);
    String dm[]=sdate.trim().split("/");
    String month = dm[1].trim();
    String year = dm[2].trim().substring(2);
    String db="d2_"+month+year;
    System.out.println("DB - "+db);
    String emf= request.getParameter("ApplyEMF");
    NumberFormat oneDec= new DecimalFormat("#0.0");
    con1 = new DBUtil().getConnection();
    //getting voltage mf 
     rs=con1.prepareStatement("select * from meter where meterid="+meterId).executeQuery();
     if(rs.next())
        vmf=Double.parseDouble(oneDec.format(rs.getDouble("adopted_ptr")/rs.getDouble("meter_ptr")));
     if (emf.equals("0"))
         vmf=1.0;
     else
         vmf=vmf;
     
    
        sql = "select substr(g2,11),rph_voltage,yph_voltage,bph_voltage from d2 where meterid='" + meterId + "' and str_to_date(g2,'%d-%m-%Y')=str_to_date('" + sdate + "','%d/%m/%Y') order by g2";
        sql1 = "select substr(g2,11),rph_voltage,yph_voltage,bph_voltage from "+ db+" where meterid='" + meterId + "' and str_to_date(g2,'%d-%m-%Y')=str_to_date('" + sdate + "','%d/%m/%Y') order by g2";
        System.out.println(sql1);
    
   

    System.out.println(" " + sql);
    
    st = con1.createStatement();
    rs = st.executeQuery(sql);
    while (rs.next()) {
        time = time + rs.getString(1) + ",";
//        rph = rph + oneDec.format((rs.getDouble("rph_voltage")*vmf*1.732)/1000) + ",";
//        yph = yph + oneDec.format((rs.getDouble("yph_voltage")*vmf*1.732)/1000) + ",";
//        bph = bph + oneDec.format((rs.getDouble("bph_voltage")*vmf*1.732)/1000) + ",";
        rph = rph + oneDec.format((rs.getDouble("rph_voltage")*vmf*1)/1) + ",";
        yph = yph + oneDec.format((rs.getDouble("yph_voltage")*vmf*1)/1) + ",";
        bph = bph + oneDec.format((rs.getDouble("bph_voltage")*vmf*1)/1) + ",";
    }
    data = "time" + "," + time + "rph" + "," + rph + "yph" + "," + yph + "bph" + "," + bph;
    //System.out.println("data  "+data);
    data = data.substring(0, data.length() - 1);
    out.println(data);
    //System.out.println("data for graph  "+data);

%>




