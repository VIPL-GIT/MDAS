<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%  Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String[] data_string2 = new String[0];
    String string_data = "";
    String time = "";
    String data = "";
    String params[] = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    try {
        
        int flag = 0;
        String meter_id = request.getParameter("meter_id").trim();
        String data_type = request.getParameter("data_type");
        
        String startDate = request.getParameter("startindDate");
        startDate = startDate.substring(6, 10) + "/" + startDate.substring(3, 5) + "/" + startDate.substring(0, 2);

        String endDate = request.getParameter("endingDate");
        endDate = endDate.substring(6, 10) + "/" + endDate.substring(3, 5) + "/" + endDate.substring(0, 2);
        
        int emf=Integer.parseInt(request.getParameter("EMF").toString());

        try {
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
            String sql = "select SUBSTR(snapshot_date,1,10),data_string from d6_data where meter_serial_no='" + meter_sl_no + "' and DATE(D6_TIME_STAMP) BETWEEN " + " ' " + startDate + " ' " + " and " + " '" + endDate + " ' order by D6_TIME_STAMP asc";
           System.out.println("------------"+sql);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            
            NumberFormat decFormat = new DecimalFormat("#0.00");
            while (rs.next()) {
            	
            	  time = time + rs.getString(1) + ",";
                  string_data = rs.getString(2);
                  System.out.println("time -->  "+time);
              //  String data_string[] = rs.getString("data_string").split(",");
              
                for (int i = 0; i < string_data.split(",").length; i++) {
                	
                	params[i] = params[i] + "," + decFormat.format((Double.parseDouble(string_data.split(",")[i]))*mf);   	
                	
                }//for
            }//while
            
    
    
            if (time.lastIndexOf(",") > 0) {
                time = time.substring(0, time.lastIndexOf(","));
            }
            data = "time" + "," + time;

            for (int j = 0; j <= string_data.split(",").length - 1; j++) {

                data = data + ";" + data_string2[j] + params[j];

            }//end for
            data = data.substring(0, data.length() - 1);
            out.println(data);
            System.out.println("???????? load survay graph values are------>" + data);

    
    
    }catch(Exception e){
            	e.printStackTrace();
            }
        %>
    