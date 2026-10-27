<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.*"%>

<%
    try {
    	System.out.println("get load survey graph page 2222222222--------------");
        Connection con1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        PreparedStatement ps = null;
        PreparedStatement pstmt = null;
        Statement st = null;
        Statement stmt = null;
        String sql = null;
        String string_data = "";
        String[] data_string2 = new String[0];
        String params[] = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        String data = "";
        double mf = 1.0, imf = 1.0, cmf = 1.0, vmf = 1.0;
        String time = "";
        String meter_serial_no="";
        
        String meterId = request.getParameter("meterid").trim();
        String lsdatetoreturn = request.getParameter("lsdate").trim();
        String ledatetoreturn = request.getParameter("ledate").trim();
        
       
        
        
        System.out.println("meter LoadGraph2 " + meterId);
        
        

        //mf param
    
        String lsdate = request.getParameter("sdate");
        
       String lsdate1 =lsdate.substring(0,10).replaceAll("-","/");
       System.out.println("lsdate --> "+lsdate1);
        
        
        //String ledate = request.getParameter("edate");
        
        
        con1 = new DBUtil().getConnection();

        if (meterId.equals("00")) {
            String header = "Fwd.kVAh,Fwd.kWh,Fwd.kVArh Lag,Fwd.kVArh Lead,RV,YV,BV,RI,YI,VI";
            data_string2 = header.split(",");
        } else {
            pstmt = con1.prepareStatement("select * from d4_columns_header where meter_serial_no=(select meter_serial_no from meter where meterid=" + meterId + ")");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("---------------------------Load survay Header Got Dynamically-----------------------------------");
                data_string2 = rs.getString("load_columns_header").split(",");
            } else {
                System.out.println("---------------------------Load survay Header Got Manually-----------------------------------");
                String sql11="select no_of_params,d4_params from metermake where metermakeid=(select metermake from meter where meterid='" + meterId + "')";
                System.out.println("sql11 --> "+sql11);
                pstmt = con1.prepareStatement(sql11);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    data_string2 = rs.getString("d4_params").split(",");
                }//end if
            }//end else

        }//else
        //mf new logic start
        NumberFormat oneDec = new DecimalFormat("#0.0");
        String sql1 = "select * from meter where meterid='" + meterId + "'";
        System.out.println("sql  " + sql1);
        stmt = con1.createStatement();
        rs1 = stmt.executeQuery(sql1);
        try{
        if (rs1.next()) {
        	meter_serial_no=rs.getString("meter_serial_no");
            mf = rs1.getDouble("mf") == 0 ? 1 : rs1.getDouble("mf");
            imf = rs1.getDouble("imf") == 0 ? 1 : rs1.getDouble("imf");
            vmf = Double.parseDouble(oneDec.format(rs1.getDouble("adopted_ptr") / rs1.getDouble("meter_ptr")));
            cmf = Double.parseDouble(oneDec.format(rs1.getDouble("adopted_ctr") / rs1.getDouble("meter_ctr")));

        }
        }catch(Exception e){
            
        }
        //mf = 1.0;

        System.out.println("------------------------------- Single Day Graph Not Applying MF -------------------------------------");
        //not applying multiplication factor
        	 sql = "SELECT  d4_date,SUBSTR(d4_date,11), data_string FROM d4_data d4 WHERE d4.meter_serial_no='"+meter_serial_no+"' AND STR_TO_DATE(d4_date,'%d-%m-%Y') = STR_TO_DATE('"+lsdate1+"','%d/%m/%Y') and data_string IS NOT NULL ORDER BY d4_time_stamp";	
       
        System.out.println("sql shiva---" + sql);
        st = con1.createStatement();
        rs = st.executeQuery(sql);
        System.out.println("query executed======== "+data_string2.length);
        int headerLen = data_string2.length;
        int dataStringLen = 0;
        NumberFormat decFormat = new DecimalFormat("#0.00");
        
        
        while (rs.next()) {
            time = time + rs.getString(1) + ",";
            string_data = rs.getString(3);
            
            for (int i = 0; i < string_data.split(",").length; i++) {
                if (data_string2[i].toLowerCase().equals("avg_voltage")) {
                	
                    //params[i] = params[i] + "," + decFormat.format((Double.parseDouble(string_data.split(",")[i]) * vmf * 1.732) / 1000);
                    params[i] = params[i] + "," + decFormat.format(Double.parseDouble(string_data.split(",")[i]) * vmf );
                } else if (data_string2[i].toLowerCase().equals("avg_current")) {
                	
                    params[i] = params[i] + "," + decFormat.format((Double.parseDouble(string_data.split(",")[i]) * cmf));
                } else if (data_string2[i].toLowerCase().equals("vr") || data_string2[i].toLowerCase().equals("vy") || data_string2[i].toLowerCase().equals("vb")) {
                	
                    //params[i] = params[i] + "," + decFormat.format((Double.parseDouble(string_data.split(",")[i]) * vmf * 1.732) / 1000);
                    params[i] = params[i] + "," + decFormat.format((Double.parseDouble(string_data.split(",")[i]) * vmf ));
                } else if (data_string2[i].toLowerCase().equals("lr") || data_string2[i].toLowerCase().equals("ly") || data_string2[i].toLowerCase().equals("lb")) {
                
                    params[i] = params[i] + "," + decFormat.format((Double.parseDouble(string_data.split(",")[i]) * cmf));
                } else {
                	
                   // double val = Double.parseDouble(string_data.split(",")[i]) * mf;
                   double val = Double.parseDouble(string_data.split(",")[i]);
                    params[i] = params[i] + "," + decFormat.format(val);
                   
                }//end else
            }//end for

        }//end while
        	
        
        
        	
        System.out.println("After while===========");
        if (time.lastIndexOf(",") > 0) {
            time = time.substring(0, time.lastIndexOf(","));
        }
        data = "time" + "," + time;

        for (int j = 0; j <= string_data.split(",").length - 1; j++) {

            data = data + ";" + data_string2[j] + params[j];

        }//end for
        
        
        data=data+"#"+meterId+"#"+lsdatetoreturn+"#"+ledatetoreturn+"#";
        
        data = data.substring(0, data.length() - 1);
        out.println(data);
        System.out.println("22222222 load survay graph values are------>" + data);
    } catch (Exception e) {
        e.printStackTrace();
    }

%>


