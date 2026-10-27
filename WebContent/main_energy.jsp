<%--<%@include file="report_header.jsp" %>--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.text.*"%>
<%--<%@ include file="getConnection.jsp"%>--%>

<% 
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int energyMF = Integer.parseInt(request.getParameter("energyMF").toString());
    Double emf = 1.0;
    String data_string2[]=null;
    String sql="";

    try {
        con = new DBUtil().getConnection(); 
        String meter_sl_no = "";
        String meter_id = "";
        NumberFormat oneDec = new DecimalFormat("#0.0");
       /*  if (request.getParameter("meter_sl_no") == null || request.getParameter("meter_sl_no") == "") {
            meter_sl_no = (String) session.getAttribute("meter_sl_no");
        } else {
            meter_sl_no = request.getParameter("meter_sl_no");
        }
        if (request.getParameter("meter_id") == null || request.getParameter("meter_id") == "") {
            meter_id = (String) session.getAttribute("meter_id");
        } else {
            meter_id = request.getParameter("meter_id");
        } */
        
        meter_id = request.getParameter("meter_id");
        
        pstmt = con.prepareStatement("select meter_serial_no from meter where meterid='" + meter_id + "'");
        rs = pstmt.executeQuery();
        if (rs.next()) {
        	meter_sl_no =rs.getString("meter_serial_no");
        }

        System.out.println(meter_sl_no+" "+meter_id);
        
        session.setAttribute("meter_sl_no", meter_sl_no);
        String stringToReturn = "";
        String fromDate = request.getParameter("esdate").trim();
        String toDate = request.getParameter("eedate").trim();
        System.out.println("meterid  " + meter_sl_no);
        if (energyMF == 1) {
            pstmt = con.prepareStatement("select mf from meter where meter_serial_no='" + meter_sl_no + "'");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                emf =rs.getDouble("mf");
            }
        }
        System.out.println(energyMF+"\tmf for energy---->" + emf+"  meter serial no  --> "+meter_sl_no);
%>
<%
    pstmt = con.prepareStatement("select * from d3_columns_header where meter_serial_no='" + meter_sl_no + "' and billing_type='b3'");
    rs = pstmt.executeQuery();
    while (rs.next()) {
        data_string2 = rs.getString("billing_columns_header").split(",");
    }System.out.println("data string --> "+data_string2);
    System.out.println(" fromDate --> "+fromDate);
    
%>



<!-- <div class="tableoptions bdrbtm mart10" style="padding:2px;"> -->
<!--         <div class="mainenergy" style="width:100%;text-align: center;"> <b>MAIN ENERGY</b>  -->
<!--         <p style="margin:0px !important;float:right;"> -->
<!--               <p style="margin:0px !important;float:right;"> -->
<!--             <button class="btn btn-sm" onclick="exportExcelForMainEnergy('energydatatable','mainenergy.xls')">Export</button> -->
<!--             </p> -->
<!--             <img src="images/phasor.png" width="25px" height="25px" style="cursor: pointer;" ></img> -->
<!--         </p> -->
<!--         </div> -->
<!--         <div class="active_recvolt"> Show Active & Reactive Current <input name="check2" type="checkbox">  | Show voltagge in KV <input name="check2" type="checkbox"> </div> -->
<!--     </div>tableoptions -->
<div class="table-responsive">
     <table cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont" id="energydatatable">   
         <thead>
            <tr class="customrow">    
                        <th width="150px" align>Billing Dates</th>
            <%
                for (int i = 0; i < data_string2.length; i++) {
                    String head=data_string2[i];
                    System.out.println("head --> "+head);
                    if(head.equals("KWh(I)_tot"))
                        head="Tot_KWH(I)";
                    if(head.contains("TOT"))
                        head=head.replaceAll("TOT", "Tot");
            %>
                        <th align="right"><%=head%></th>
            <%}
            
            %>
            </tr>
         </thead>    
         <tbody>
    <%
       

        session.setAttribute("meter_sl_no", meter_sl_no);
        

           
           
            if (!fromDate.equals("") && !toDate.equals("")) {
                sql = "select date_format(d3_time_stamp,'%d-%m-%Y %H:%i:%s') billing_date,DATA_STRING from d3_b3 b3 where meter_serial_no='"+meter_sl_no+"' and  str_to_date(date_format(b3.d3_time_stamp,'%d/%m/%Y'),'%d/%m/%Y')>= str_to_date('" + fromDate + "','%d/%m/%Y') and str_to_date(date_format(b3.d3_time_stamp,'%d/%m/%Y'),'%d/%m/%Y') <= str_to_date('" + toDate + "','%d/%m/%Y') order by d3_time_stamp desc";
            } else {
                sql = "select date_format(d3_time_stamp,'%d-%m-%Y %H:%i:%s') billing_date,DATA_STRING from d3_b3 b3 where meter_serial_no='"+meter_sl_no+"' order by d3_time_stamp desc";
            }
System.out.println("------------->"+sql);
            pstmt = con.prepareStatement(sql);
           // pstmt.setString(1, meter_sl_no);

        //end else if

        rs = pstmt.executeQuery();

        String dataString = "";
        NumberFormat formatter = new DecimalFormat("#0.000");
        while (rs.next())
        {
            
            %>
            <tr>
             <%
            if (rs.getString("DATA_STRING") != null) {
                String data_string[] = rs.getString("DATA_STRING").split(",");
//                for(String dataStrings : data_string){
//                    System.out.println("data_string = "+dataStrings);
//                }
                String val = null;
                for (int i = 0; i < data_string.length; i++) {
                    
                    val = data_string[i];
                    if (val.length() > 1) {
                        try {
                            double d = Double.parseDouble(val);                            
                             data_string[i] = String.valueOf(formatter.format(d));   
                        } catch (Exception e) {
                        }//end catch
                    }//end if
                }//end for

    %>


                <td ><a href="javascript:getTotalBilling('<%=meter_sl_no %>','<%=rs.getString("billing_date")%>');" class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ctmodal-rpt"><%=rs.getString("billing_date")%></a></td>
        <%
            
            
                for (int i = 0; i < data_string2.length; i++) {
                    //System.out.println("data_string[i] = "+data_string[i]);
                   
        %>
                <td  align="right"><%= formatter.format(Double.parseDouble(data_string[i])*emf)%></td>		
        
        <%
            }
       
       
                }
                
                %>
            </tr>
                <%
                   
            }//end of while
            
        %>
    
         </tbody>
</table>
</div>

      
<%
    }  catch (Exception e) {
        String s = "";
        if (e.getClass().toString().contains("CommunicationsException")) {
            out.println("Plz Try Again....<font color='red'>Database Connection lost</font>");
        }
        if (e.getClass().toString().contains("MySQLSyntaxErrorException")) {
            out.println("Plz Try Again....<font color='red'>Invalid Request</font>");
        }
        if (e.getClass().toString().contains("SQLException")) {
            out.println("Plz Try Again....<font color='red'>Check the DB Query</font>");
        }
        e.printStackTrace();
    }
%>

