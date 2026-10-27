<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="utils.DBUtil"%>
<%@page import="ActionBeans.Utilities"%>
<%@ page import="java.sql.*"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<head>
    <link rel="stylesheet" type="text/css" href="css/table.css">
    <link rel="stylesheet" type="text/css" href="css/pagination.css"> 
    <style>
        .kv{
            display:none;
        }
    </style>
</head>
<%!
    String selectedDateStr = "";
    String selectedDateStr1 = "";
    String endlimit="";
    java.util.Date startDate;
    java.util.Date endDate;
%>
<%
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs = null, rs1 = null;
    String lsdate = request.getParameter("lsdate");
    String ledate = request.getParameter("ledate");
    int start = Integer.parseInt(request.getParameter("start"));
    int limit = 1;
    int count = 0;
    int flag = 0;
    int noOfIntervals = 0;
    if (!lsdate.equals("") && !ledate.equals("")) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        startDate = sdf.parse(lsdate);
         endDate = sdf.parse(ledate);
        long diff2 = (endDate.getTime() - startDate.getTime());
        long diffDays1 = diff2 / (24 * 60 * 60 * 1000);
        int diffDays = (int) diffDays1;
        noOfIntervals = diffDays + 1;
        count = noOfIntervals + 1;
        start = Integer.parseInt(request.getParameter("start"));
        int start_tmp = start;
        Map<Integer, String> indexDateMap = new HashMap<Integer, String>();
        java.util.Date tempDate = startDate;
        int incrementVal = 0;
        for (int loop = 0; loop <= diffDays; loop++) {
            if (loop != 0) {
                incrementVal = 1;
            }
            tempDate.setDate(tempDate.getDate() + incrementVal);
            indexDateMap.put(loop + 1, sdf.format(tempDate));
            //System.out.println("tempDate==>" + tempDate);
        }
        int tempStart = 0;
        if (0 != start) {
            tempStart = start - 1;
        }
        java.util.Date selectedDate = sdf.parse(lsdate);
        java.util.Date selectedDate1 = sdf.parse(ledate);
        selectedDate.setDate(selectedDate.getDate() + tempStart);
        selectedDateStr = sdf1.format(selectedDate);
        selectedDateStr1 = sdf1.format(selectedDate1);
        System.out.println("start date-"+startDate);
        System.out.println("end date-"+endDate);
%>

<%}%> 
<%
    double mf = 1.0, imf = 1.0, vmf = 1.0, cmf = 1.0;

    try {
        int kwhLoc = 0, kvahLoc = 0;
        Double totKwh = 0.0, totKvah = 0.0;
        String meter_id = "";
        int emf = Integer.parseInt(request.getParameter("EMF").trim());
        // out.println("EMF:"+emf);
        String[] data_string2 = new String[0];
        String[] data_string = new String[0];
        meter_id = request.getParameter("meter_id").trim();
        System.out.println("meter_id -------------------------------> " + meter_id);
        String stringToReturn = "";
        Connection con = new DBUtil().getConnection();
%>

<!-- <div class="tableoptions bdrbtm mart10" style="padding:2px;"> -->
<!--         <div class="loaddatatable" style="width:100%;text-align: center;"> <b>LOAD DATA</b>  -->
<!--         <p style="margin:0px !important;float:right;"> -->
<!--               <p style="margin:0px !important;float:right;"> -->
<!--             <button class="btn btn-sm" onclick="exportExcelForLoadData('loaddatatable','loaddatatable.xls')">Export</button> -->
<!--             </p> -->
<!--             <img src="images/phasor.png" width="25px" height="25px" style="cursor: pointer;" ></img> -->
<!--         </p> -->
<!--         </div> -->
<!--         <div class="active_recvolt"> Show Active & Reactive Current <input name="check2" type="checkbox">  | Show voltagge in KV <input name="check2" type="checkbox"> </div> -->
<!--     </div>tableoptions -->


<div id="divdatatable" class="table-responsive">
<table cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont" id="loaddatatable" >    
    <thead>    
    <tr >  
        <th  > Load Survey Dates</th>  

        <%
            String meter_sl_no = "";
            NumberFormat oneDec = new DecimalFormat("#0.0000");
            String sql1="select * from meter where meterid='" + meter_id + "'";
            System.out.println("sql 1111 "+sql1);
            pstmt = con.prepareStatement(sql1);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                meter_sl_no = rs.getString("meter_serial_no");
                System.out.println("msl --> "+meter_sl_no);
                mf = rs.getDouble("mf");
                imf = rs.getDouble("imf");
                vmf = Double.parseDouble(oneDec.format(rs.getDouble("adopted_ptr") / rs.getDouble("meter_ptr")));
                cmf = Double.parseDouble(oneDec.format(rs.getDouble("adopted_ctr") / rs.getDouble("meter_ctr")));
            }
            pstmt = con.prepareStatement("select * from d4_columns_header where meter_serial_no='" + meter_sl_no + "'");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("---------------------------Load survay Header Got -----------------------------------");
                data_string2 = rs.getString("load_columns_header").split(",");
            } else {
                System.out.println("---------------------------Load survay Header Got Manually-----------------------------------");
                
                String sql="select no_of_params,d4_params from metermake where metermakeid=(select metermake from meter where meter_serial_no='" + meter_sl_no + "') and d4_params is not null";
                System.out.println("sql --> "+sql);                
                pstmt = con.prepareStatement(sql);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    data_string2 = rs.getString("d4_params").split(",");
                }//end if
            }//end else
            System.out.println("data_string2==>" + data_string2.length);

            if (data_string2 != null) {
                for (int i = 0; i < data_string2.length; i++) {
                   String units="";
                   if(data_string2[i].equalsIgnoreCase("lr") || data_string2[i].equalsIgnoreCase("ly") || data_string2[i].equalsIgnoreCase("lb")) units=" (amps)";
                   if(data_string2[i].equalsIgnoreCase("vr") || data_string2[i].equalsIgnoreCase("vy") || data_string2[i].equalsIgnoreCase("vb")) units=" (volts)";
        %>
        <th style="" > <%=data_string2[i]%> <%= units %></th>
        <%
            
                }//end for
            }//if
        %>
    </tr>
    </thead>
    <tbody>
        <%  if (emf==0) {
                mf = 1.0;
                cmf = 1.0;
                vmf = 1.0;
            } else {
               
            }
            String sql = null;

            if (!lsdate.equals("") && !ledate.equals("")) {
               
                    sql = "select *  from d4_data d4 where meter_serial_no='" + meter_sl_no + "'  and str_to_date(date_format(d4_time_stamp,'%d/%m/%Y'),'%d/%m/%Y')>= str_to_date('" + lsdate + "','%d/%m/%Y') and str_to_date(date_format(d4_time_stamp,'%d/%m/%Y'),'%d/%m/%Y') <= str_to_date('" + ledate + "','%d/%m/%Y')"
                            + " order by d4_time_stamp desc";
                   
                   // String temp="select *  from d4_data d4 where meter_serial_no='" + meter_sl_no + "'  and d4_date >='"+selectedDateStr  + "'  and d4_date <='"+selectedDateStr1 ;
                   // System.out.println("temp is -"+temp);
                    pstmt = con.prepareStatement(sql);
                
            } else {
            }//end else

            // System.out.println("load survey query is" + sql);
            rs = pstmt.executeQuery();
            String dataString = "";
            String d4Date = "";
            String[] d4DateArr = null;
            //int rowcount=0;
            while (rs.next())
            {
                %><tr><%
               
                flag = 1;
                d4Date = rs.getString("d4_date");
                d4DateArr = d4Date.split(" ");
                if (d4DateArr[1].contains("23:59:00"))
                {
                    d4Date = d4DateArr[0] + " " + "00:00:00";
                }//end if
                if (rs.getString("data_string") != null)
                {
                    data_string = rs.getString("data_string").split(",");
                    String val = null;
                    for (int i = 0; i <= data_string.length - 1; i++)
                    {
                        val = data_string[i];
                        //if (val.length() > 1) {
                        if (val.length() >= 1 && val != null) {
                            try {
                                double d;
                                if (data_string2[i].toLowerCase().equals("avg_voltage")) {
                                    d = Double.parseDouble(oneDec.format(Double.parseDouble(val) * vmf));
                                } else if (data_string2[i].toLowerCase().equals("avg_current")) {
                                    d = Double.parseDouble(val) * cmf;
                                } else if (data_string2[i].toLowerCase().equals("vr") || data_string2[i].toLowerCase().equals("vy") || data_string2[i].toLowerCase().equals("vb")) {
                                    
                                        d = Double.parseDouble(oneDec.format(Double.parseDouble(val) * vmf));
                                   
                                } else if (data_string2[i].toLowerCase().equals("lr") || data_string2[i].toLowerCase().equals("ly") || data_string2[i].toLowerCase().equals("lb") ||
                                		data_string2[i].toLowerCase().equals("ir") || data_string2[i].toLowerCase().equals("iy") || data_string2[i].toLowerCase().equals("ib")) {
                                    d = Double.parseDouble(val) * cmf;
                                } else if (data_string2[i].toLowerCase().contains("freq")) {
                                	d = Double.parseDouble(val) ;
                                }else{
                                   // d = (Double.parseDouble(oneDec.format((Double.parseDouble(val) * imf)))) * mf;
                                    d = Double.parseDouble(val)*mf ;                                                
                                }//end else
                                data_string[i] = String.valueOf(oneDec.format(d));
                            } catch (Exception e) {
                                e.printStackTrace();
                                //out.println("Plz Try Again....");
                            }//end catch
                        }//end if                      
                    }//end for
%>

       
            <td ><%=d4Date%></td>

            <%
                
                
            for (int i = 0; i <= data_string2.length - 1; i++) 
            {
                //System.out.println("in for block");
    try{
            %>
           
            <% 
                if (data_string2[i].toLowerCase().equals("vr") || data_string2[i].toLowerCase().equals("vy") || data_string2[i].toLowerCase().equals("vb")) 
                {
                    //System.out.println("in if");
                   
                    //System.out.print(oneDec.format(Double.parseDouble(data_string[i]) / 1000));
            %>
                             <td class="v" ><%=data_string[i]%></td>        
                             

            <% } 
                else 
                {
                        
            %>            
                            <td ><%=data_string[i]%></td>
            <%  }//else
        }
   catch(Exception e)
        {
            
            continue;
        }
                           
                        }//for
                    }//if
%>
        
        
            <%
                
                
                %>
                </tr>
                    <%
                }//while


%>
        

    </tbody>
</table>
</div>
<div style="display:none;" id='req_data_xl'>
    <table id="table2" border=1 width=600px>
    </table>
</div>

<%
    } 
catch (Exception e) 
{       
        System.out.println("in main catch");
        String s = "";
        if (e.getClass().toString().contains("CommunicationsException")) {
            out.println("Plz Try Again....<font color='red'>Database Connection lost</font>");
        }
        if (e.getClass().toString().contains("MySQLSyntaxErrorException")) {
            out.println("Plz Try Again....<font color='red'>Invalid Request</font>");
        }
        e.printStackTrace();
    }
%>