<%@page import="java.sql.*"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="utils.DBUtil"%>
<%
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Connection con = null;
    String[] data_string2 = new String[0];
    int flag = 0;
     String meter_sl_no = "";
   double mf = 1.0, imf = 1.0, vmf = 1.0, cmf = 1.0;
    try {
        con = new DBUtil().getConnection();
        NumberFormat twoDecFormat = new DecimalFormat("#0.000");
        NumberFormat oneDec = new DecimalFormat("#0.0000");
        String meter_id = "";
        meter_id = request.getParameter("meter_id");
        
        
        
        
        
        pstmt = con.prepareStatement("select * from meter where meterid='" + meter_id + "'");
            rs = pstmt.executeQuery();
        if (rs.next()) {
                meter_sl_no = rs.getString("meter_serial_no");
                mf = rs.getDouble("mf");
                imf = rs.getDouble("imf");
                vmf = Double.parseDouble(oneDec.format(rs.getDouble("adopted_ptr") / rs.getDouble("meter_ptr")));
                cmf = Double.parseDouble(oneDec.format(rs.getDouble("adopted_ctr") / rs.getDouble("meter_ctr")));
            }
        int emf = Integer.parseInt(request.getParameter("EMF").trim());
        if (emf==0) {
                mf = 1.0;
                cmf = 1.0;
                vmf = 1.0;
            } else {
                mf = mf;
                cmf = cmf;
                vmf = vmf;
            }
        System.out.println(meter_id+"\t"+emf+"========"+vmf+"\t"+cmf+"\t"+mf);
%>


<div class="table-responsive">
<table  id="tamperdatatable" cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont" >        
    <thead>
        <tr >
                    <th style="text-align: left;"> Event Dates</th>
                    <th style="text-align: left">Event Type</th>
                    <th style="text-align: left">Event Status</th>
   

            <%
                try {
                    
                    pstmt = con.prepareStatement("select * from d5_columns_header where meter_serial_no=(select meter_serial_no from meter where meterid='" + meter_id + "')");
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        flag = 1;
                        System.out.println("---------------------------Tamper Header Got Dynamically-----------------------------------");
                        data_string2 = rs.getString("tamper_columns_header").split(",");
                    } else {
                        System.out.println("---------------------------Tamper Header Got Manually-----------------------------------");
                        pstmt = con.prepareStatement("select metermakeid,no_of_params,param_names from metermake where metermakeid=(select metermake from meter where meter_serial_no='" + meter_id + "')");
                        rs = pstmt.executeQuery();

                        if (rs.next()) {
                            flag = 1;
                            data_string2 = rs.getString("param_names").split(",");
                        }//end if
                    }//end else

                } catch (Exception e) {
                    if (flag == 0) {
                    }
                    //response.sendRedirect("../errorPage.jsp?message="+e.getMessage());
                }
                //System.out.println("Tamper header lenght is ----->"+data_string2.length);
                for (int i = 0; i < data_string2.length; i++) 
                {
                	 String units="";
                     if(data_string2[i].equalsIgnoreCase("lr") || data_string2[i].equalsIgnoreCase("ly") || data_string2[i].equalsIgnoreCase("lb")) units=" (amps)";
                     if(data_string2[i].equalsIgnoreCase("vr") || data_string2[i].equalsIgnoreCase("vy") || data_string2[i].equalsIgnoreCase("vb")) units=" (volts)";
            %>
                    <th style="text-align: left"><%=data_string2[i]%> <%=units %></th>
            <%  }//end for%>

        </tr>
    </thead> 
    <tbody>
        <%
            System.out.println("<--------------------------->");
          
            String sql = "";
          
            String sdate = request.getParameter("sdate").replaceAll("/", "-");
            String edate = request.getParameter("edate").replaceAll("/", "-");
            String chkvalue = request.getParameter("chkvalues");
//            String chkvalue = "1,24,4,23,27,28,58,2,25,5,11,7,19,33,3,26,6,13,14,12,";
            System.out.println("chkvalue =======> "+chkvalue);
            int start = Integer.parseInt(request.getParameter("start"));
            
            String values[] = chkvalue.split(",");
            System.out.println("checked values"+values.length);
       if (!sdate.equals("") && !edate.equals("")) 
        {
            

 
                sql = "select event_date,e.event_type,event_status,data_string,d.event_type from  d5_data d,events e where d.meter_serial_no='"+meter_sl_no+"' and d.event_type=e.event_code and "
                        + "str_to_date(substr(event_date,1,10),'%d-%m-%Y')>=str_to_date('" + sdate + "','%d-%m-%Y') "
                        + "and str_to_date(substr(event_date,1,10),'%d-%m-%Y')<=str_to_date('" + edate + "','%d-%m-%Y')and "
                        + " d.event_type in (";
                for (int i = 0; i < values.length; i++) {
                    sql = sql + Integer.parseInt(values[i]) + ",";
                }
                sql = sql + "0) order by d5_time_stamp desc ";
                System.out.println(""+sql);
                pstmt = con.prepareStatement(sql);
                
               // pstmt.setString(1, meter_id);
               
                rs = pstmt.executeQuery();
            
            //System.out.println("sql query is" + sql);

            String dataString = "";
            //System.out.println("executed--------------->");
            
            while (rs.next()) 
            {
            %>
            
            <%
                flag = 1;
                
                if (!rs.getString("d.event_type").equals("13")) 
                {
                    String data_string[] = rs.getString(4).split(",");
//                    if(data_string2.length==data_string.length){
        %>
                        <tr>
                            <td ><%=rs.getString(1)%></td>
                            <td ><%=rs.getString(2)%></td>
                            <td ><%=(rs.getString(3).trim().contains("restored")) ? "Restored" : "Occured"%></td>
                            <% for (int i = 0; i <= data_string2.length - 1; i++) {
                                try{
                                double d;
                                if(data_string[i].trim().length()!=0){
                               if (data_string2[i].toLowerCase().equals("vr") || data_string2[i].toLowerCase().equals("vy") || data_string2[i].toLowerCase().equals("vb")) {
                                    
                                        d = Double.parseDouble(oneDec.format(Double.parseDouble(data_string[i]) * vmf));
                                   
                                } else if (data_string2[i].toLowerCase().equals("lr") || data_string2[i].toLowerCase().equals("ly") || data_string2[i].toLowerCase().equals("lb") ||
                                		data_string2[i].toLowerCase().equals("ir") || data_string2[i].toLowerCase().equals("iy") || data_string2[i].toLowerCase().equals("ib")) {
                                    d = Double.parseDouble(data_string[i]) * cmf;
                                }
                                 else if (data_string2[i].toLowerCase().contains("pf")) {
                                    d = Double.parseDouble(data_string[i]);
                                }else{
                                    d = Double.parseDouble(data_string[i]) * mf;
                                }
                            %>
                            <td  ><%=twoDecFormat.format(d)%></td>
                            <%
                                }else{ %>
                                <td></td>
                                <% }
                               }catch(Exception e){
                                  e.printStackTrace();
                                  out.print("<td></td>");
                                  }
                              }
//                                            }//for
                                %>
                        </tr>
        <%
                }
                else 
                {
        %>
                            <tr>
                                <td ><%=rs.getString(1)%></td>
                                <td ><%=rs.getString(2)%></td>
                                <td ><%=(rs.getString(3).trim().contains("restored")) ? "Restored" : "Occured"%></td>

                                <% for (int i = 0; i <= data_string2.length - 1; i++) {%>
                                <td ><%=" "%></td>
                                <% }%>

                            </tr>
        <%
                }//else
            }//while
            
            }//elseif
        %>
    </tbody>
</table>
</div>


                <p style="font-variant: normal;text-transform: none;"><font color="red"><b>Note:</b> 
                Parameter values are not applicable for power fail occurred and restored</font>.</p>

<%
    } catch (Exception e) {
e.printStackTrace();
        //response.sendRedirect("../errorPage.jsp?message=" + e.getMessage());
    }
%>