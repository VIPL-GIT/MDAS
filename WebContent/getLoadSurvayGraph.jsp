<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.*"%>

<%
    try {
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
        String meter_sl_no="";
        String data = "";
        double mf = 1.0, imf = 1.0, cmf = 1.0, vmf = 1.0;
        String time = "";
        String meterId = request.getParameter("meterid").trim();
        System.out.println("meter id is" + meterId);
        
        
        String sdate = request.getParameter("sdate");
        System.out.println("sdate is " + sdate);

        //mf param
        int emf = Integer.parseInt(request.getParameter("EMF"));
        System.out.println("emf is" + emf);
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
                String sql11="select no_of_params,d4_params from metermake where metermakeid=(select metermake from meter where meter_id='" + meterId + "')";
                System.out.println("sql11 --> "+sql11);
                pstmt = con1.prepareStatement(sql11);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    data_string2 = rs.getString("d4_params").split(",");
                }//end if
            }//end else

        }//else
        String params[] = new String[data_string2.length];        	
        	
        //mf new logic start
        NumberFormat oneDec = new DecimalFormat("#0.0");
        String sql1 = "select * from meter where meterid='" + meterId + "'";
        System.out.println("sql  " + sql1);
        stmt = con1.createStatement();
        rs1 = stmt.executeQuery(sql1);
        try{
        if (rs1.next()) {
        	meter_sl_no=rs.getString("meter_serial_no");
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

           if (emf==0) {
                mf = 1.0;
                cmf = 1.0;
                vmf = 1.0;
            } else {
                mf = mf;
                cmf = cmf;
                vmf = vmf;
            }

        //mf new logic end
        
        sql = "select distinct d4_date,substr(d4_date,11), data_string from d4_data d4 where meter_serial_no='"+meter_sl_no+"' and str_to_date(d4_date,'%d-%m-%Y') = str_to_date('" + sdate + "','%d/%m/%Y') and data_string is not null order by d4_time_stamp ";
        
       // System.out.println("sql s---" + sql);
        st = con1.createStatement();
        rs = st.executeQuery(sql);
        
        int headerLen = data_string2.length;
        int dataStringLen = 0;
        NumberFormat decFormat = new DecimalFormat("#0.00");
        while (rs.next()) {

            time = time + rs.getString(2) + ",";
            string_data = rs.getString(3);
           // System.out.println(string_data);

            //logic for proper d4 string data length
//             dataStringLen = string_data.split(",").length;
//             if (headerLen == dataStringLen) {
//                 //nothing to do
//             } else if (headerLen > dataStringLen) {
//                 System.out.println("Data String Len is Less than to Columns Header :" + string_data);
//                 for (int i = dataStringLen; i < headerLen; i++) {
//                     string_data = string_data + "0" + ",";
//                 }//end for
//                 System.out.println("Final Data String :" + string_data);
//             } else {
//                 System.out.println("Data String Len is Greater than to Columns Header :" + string_data);
//                 String[] sdArr = string_data.split(",");
//                 string_data = "";
//                 for (int i = 0; i < headerLen; i++) {
//                     string_data = string_data + sdArr[i] + ",";
//                 }//end for
//                 System.out.println("Final Data String :" + string_data);
//             }//end else

            //old logic
            /*
                    for (int i = 0; i < string_data.split(",").length; i++) {
                        if (data_string2[i].toLowerCase().equals("vr") || data_string2[i].toLowerCase().equals("vy") || data_string2[i].toLowerCase().equals("vb")
                                || data_string2[i].toLowerCase().equals("lr") || data_string2[i].toLowerCase().equals("ly") || data_string2[i].toLowerCase().equals("lb")
                                || data_string2[i].toLowerCase().contains("avg") || data_string2[i].toLowerCase().contains("unknown")) {
                            //don't apply mf for these values
                            //params[i] = string_data.split(",")[i];
                            params[i] = params[i] + "," + decFormat.format((Double.parseDouble(string_data.split(",")[i]) * mf));
                        } else {
                            //params[i] = params[i] + "," + Double.parseDouble(string_data.split(",")[i]) * mf;
                            //params[i] = "" + decFormat.format( (Double.parseDouble(decFormat.format( Double.parseDouble( string_data.split(",")[i] ) ) ) * mf ) );
                            params[i] = params[i] + "," + decFormat.format((Double.parseDouble(string_data.split(",")[i]) * mf));
                        }//end else
                    }//end for
             */
            //new logic
           
            for (int i = 0; i < string_data.split(",").length; i++) {
            	try{
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
                    //double val = Double.parseDouble(string_data.split(",")[i]) * mf;
                    double val = Double.parseDouble(string_data.split(",")[i]);
                    params[i] = params[i] + "," + decFormat.format(val);
                }//end else
                	
               // System.out.println("--------"+params[i]);
            	}catch(Exception e){
            		e.printStackTrace();
            	}
            	
            	
            }//end for

        }//end while
       
       
        	
        if (time.lastIndexOf(",") > 0) {
            time = time.substring(0, time.lastIndexOf(","));
        }
        data = "time" + "," + time;

        for (int j = 0; j <= string_data.split(",").length - 1; j++) {
        	
        	//System.out.println("--------"+data_string2[j]+"  "+params[j]);

            data = data + ";" + data_string2[j] +","+ params[j];

        }//end for
        data = data.substring(0, data.length() - 1);
        out.println(data);
        System.out.println("???????? load survay graph values are------>" + data);
    } catch (Exception e) {
        e.printStackTrace();
    }

%>