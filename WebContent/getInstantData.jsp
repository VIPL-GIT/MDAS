<%@page import="DataBeans.D2_Data_Form"%>
<%@page import="utils.DBUtil"%>
<%@ page import="java.sql.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%-- <%@page import="com.mysql.jdbc.JDBC4PreparedStatement"%> --%>
<%@page import="utils.D2_DAO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="ActionBeans.Utilities"%>
<%--<%@include file="report_header.jsp" %>--%>
<%@page import="java.text.DecimalFormat"%>
<head>
<link rel="stylesheet" type="text/css" href="css/pagination.css">

    <style>
        #hidden_fields{
            display:none;
        }   #hidden_fields{
            display:none;
        }
        #note{
            display:none;
        }
        .kv{
            display:none;
        }
    </style>

  
</head>
<body>
   
    <div id="page">
        <%
            Connection con = new DBUtil().getConnection();
            int data_type = 1; //2 - completed data   1 - Latest data
            if (request.getParameter("data_type") == null || request.getParameter("data_type") == "") 
            {
                
            } 
            else 
            {
                data_type = Integer.parseInt(request.getParameter("data_type"));
            }
            System.out.println("data type ========>" + data_type);
            int count = 0;
            String sdate = request.getParameter("sdate");
            String edate = request.getParameter("edate");
            String month = request.getParameter("month");
            int noOfIntervals = 0;
            int start = 0;
            int limit = 1;
            if (2 == data_type) 
            {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date startDate = sdf.parse(sdate);
                java.util.Date endDate = sdf.parse(edate);
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
                    if (loop != 0) 
                    {
                        incrementVal = 1;
                    }
                    tempDate.setDate(tempDate.getDate() + incrementVal);
                    indexDateMap.put(loop + 1, sdf1.format(tempDate));
                }
        %>
<table bordercolor="white" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr bgcolor="#004B8E">
        <td colspan="6" align="center" class="head1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td width="90%" align="center">
                    <ul class="paginate pag2 clearfix">
    <table>
                            <tr>
                                <%
                                    /*10 replace with this variable and assigned 5 value */
                                    int limitTemp = 5;
                                    if ((start_tmp % limitTemp) == 0) 
                                    {
                                        start_tmp = start_tmp - 2;
                                        System.out.println("start_tmp==>" + start_tmp);
                                    }
                                    if (((Integer.parseInt(request.getParameter("start")) - 1) / limitTemp) + 1 > limit) {
                                %>
                                <td id="pageNo"><li><a href="#"  onclick="javascript:getSelectedCompleteData(<%=(((start_tmp / (limit * limitTemp)) * (limit * limitTemp)))%>)" sgetInstantDatatyle="text-decoration:none;">Prev <%=limitTemp%></a></li></td>
                                <%
                                    }
                                    int page_no_start = (start_tmp / (limit * limitTemp)) * (limit * limitTemp);
                                    int page_no_end = ((start_tmp / (limit * limitTemp)) * (limit * limitTemp)) + (limit * (limitTemp - 1));
                                    page_no_start = (page_no_start / limit) + 1;
                                    page_no_end = (page_no_end / limit) + 1;
                                    System.out.println("start_tmp==>" + start_tmp);
                                    System.out.println("page_no_start==>" + page_no_start);
                                    System.out.println("page_no_end1==>" + page_no_end);
                                    start = 1;
                                    if (page_no_end >= Math.ceil((double) count / (double) limit)) {
                                        page_no_end = (int) Math.ceil((double) count / (double) limit);
                                    }
                                    System.out.println("page_no_end2==>" + page_no_end);
                                    for (int i = 1; i < Math.ceil((double) count / (double) limit); i++) {
                                        if (i >= page_no_start && i <= page_no_end) {
                                            if (i == ((Integer.parseInt(request.getParameter("start")) - 1) / limit) + 1) {
                                %>

                            <td id="pageNo"><li class="current"><a href="#"  onclick="javascript:getSelectedCompleteData(<%=start%>)" style="color: red !important"><%=indexDateMap.get(i)%></a><li></td>

                                <%
                                } else {
                                %>

                            <td id="pageNo"><li><a href="#"  onclick="javascript:getSelectedCompleteData(<%=start%>)"><%=indexDateMap.get(i)%></a><li></td>

                                <%
                                            }
                                        }
                                        start = start + limit;
                                    }
                                    // code for next
                                    //if((((Integer.parseInt(request.getParameter("start"))-1)/10)+1)>=1)
                                    start = start_tmp;
                                    System.out.println("sree==>" + Math.ceil((double) count / (double) limit));
                                    if (page_no_end >= Math.ceil((double) count / (double) limit) - 1) {
                                    } else {
                                        // start = Integer.parseInt(request.getParameter("start")) +(limit*10);

                                        start = (((start_tmp / (limit * limitTemp)) * (limit * limitTemp)) + (limit * limitTemp)) + limit + 1;
                                %>
                            <td id="pageNo"><li><a href="#"  onclick="javascript:getSelectedCompleteData(<%=start - 1%>)">Next <%=limitTemp%></a><li></td>

                                <%
                                    }
                                %>
                                </tr>
                        </table>
                    </ul>
                    <!--        </td><td width=10%" align="center"><a href="#" onclick='exportReports("InstData")'>Export All Data</a></td>-->
            </tr>
        </table>
    </div>
    <%} %>


<!--     <div class="tableoptions bdrbtm mart10" style="padding:2px;"> -->
<!--         <div class="instantaneous" style="width:100%;text-align: center;"> Instantaneous DATA -->
        <!-- <p style="margin:0px !important;float:right;">
            For Phasor diagram click on any row
            <button class="btn btn-sm" onclick="exportExcel('instantTable','Instant.xls')">Export</button> -->
            <!--<img src="images/phasor.png" width="25px" height="25px" style="cursor: pointer;" ></img>-->
        </p>
<!--         </div> -->
        <!--<div class="active_recvolt"> Show Active & Reactive Current <input name="check2" type="checkbox">  | Show voltagge in KV <input name="check2" type="checkbox"> </div>-->
<!--     </div>tableoptions	 -->
<p style="margin:0px !important;">
            For Phasor diagram click on any row
            </p>
    <div class="table-responsive">
<table cellpadding="0" cellspacing="0" border="0"  class="table2excel stdtable stdtablecb smallfont" id="instantTable"> 
    <thead>
    <tr  style="color: white; font-weight: bold;" valign="bottom">            
            <td align="center" rowspan="2" width="130px" style="min-width:160px;" >Reading Date Time</td>            
            <td align="center" rowspan="2" width="130px" style="min-width:160px;" >Server Date Time</td>            
            <td align="center" colspan="3" rowspan="1">Phase Voltages&nbsp;&nbsp;(volts)</td>
            <td align="center" colspan="3" rowspan="1">Line Currents&nbsp;&nbsp;(amps)</td>
            <td align="center" colspan="4" rowspan="1">Power Factors</td>
            <td align="center" colspan="3" rowspan="1">Powers</td>
            <td align="center" rowspan="1" style="color: white; font-weight: bold;min-width: 40px;">Freq (Hz)</td>            
        </tr>
        
        <tr  align="center">
            <td width="7.3%">R</td>
            <td width="7.3%">Y</td>
            <td width="7.3%">B</td>
            <td width="7.3%">R</td>
            <td width="7.3%">Y</td>
            <td width="7.3%">B</td>            
            <td width="7.3%">R</td>
            <td width="7.3%">Y</td>
            <td width="7.3%">B</td>
            <td width="7.3%">Avg</td>
            <td width="8%">&nbsp;Act(kW)&nbsp;</td>
            <td width="10%">&nbsp;App(kVA)&nbsp;</td>
            <td width="10%">&nbsp;React(kVAr)&nbsp;</td>
            <td width="10%">&nbsp;&nbsp;</td>
            
        </tr>
        </thead>
        <tbody>
        <%! PreparedStatement pstmt, pstmt1, pstmt2 = null;
            ResultSet rs, rs1, rs2 = null;
            double ctr = 1.0, ptr = 1.0, Actr = 1.0, Aptr = 1.0;
            double vmf = 1.0, cmf = 1.0, pmf = 1.0;
            double mf = 1.0;
            DecimalFormat df1 = new DecimalFormat("#0.000");
            String locationName = "";

        %>
        <%        try {
                String meter_id = "";
                if (request.getParameter("meter_id") == null || request.getParameter("meter_id") == "") 
                {
                    meter_id = (String) session.getAttribute("meter_id");
                }
                else 
                {
                    meter_id = request.getParameter("meter_id");
                }
                session.setAttribute("meter_id", meter_id);
                String stringToReturn = "";
                System.out.println(" meter_id  : " + meter_id);

                double aemf = 1.0;
                if (request.getParameter("ApplyEMF") != null) 
                {
                    aemf = Double.parseDouble(request.getParameter("ApplyEMF"));

                } 
                else 
                {
                    System.out.println("Apply EMF is not available ");
                }//end else
                System.out.println("EMF-----------------> $$ =" + aemf);
                String meter_sl_no = "";
               // String qqq1 = "select meter_serial_no,ml.locationname from meter,meterlocation ml where meterid=" + meter_id.trim() + " and meterlocation=ml.locationid";
                //System.out.println("qqq1 = "+qqq1);
               // pstmt = con.prepareStatement("select meter_serial_no,ml.locationname from meter,meterlocation ml where meterid=" + meter_id.trim() + " and meterlocation=ml.locationid");
                pstmt = con.prepareStatement("select meter_serial_no from meter where meterid='" + meter_id.trim() + "'");
                rs = pstmt.executeQuery();
                if (rs.next())
                {
                    meter_sl_no = rs.getString("meter_serial_no");
                   // locationName = rs.getString(2);
                }
                session.setAttribute("meter_sl_no", meter_sl_no);
                //System.out.println("meter serial no " + meter_sl_no);
                System.out.println("data_type==>" + data_type);
                
//START OF COMPLETE DATA                
if (data_type == 2)// complete data
{
        %>

    
            <%
                String sql1 = "select * from meter where meterid='"+meter_id+"'";
                pstmt2 = con.prepareStatement(sql1);
                rs2 = pstmt2.executeQuery();
                if (rs2.next()) 
                {
                    ctr = Double.parseDouble(rs2.getString("meter_ctr"));
                    ptr = Double.parseDouble(rs2.getString("meter_ptr"));
                    Actr = Double.parseDouble(rs2.getString("adopted_ctr"));//comm ctr
                    Aptr = Double.parseDouble(rs2.getString("adopted_ptr"));//comm ptr
                    mf = Double.parseDouble(rs2.getString("mf"));
                    //cmf =ctr/Actr;
                    vmf = Double.parseDouble(df1.format(Aptr / ptr)) * 1;
                    cmf = Double.parseDouble(df1.format(Actr / ctr));
                    pmf = Double.parseDouble(df1.format(cmf / vmf));
                    //cmf = (Actr / Aptr) * (ctr / ptr);

                }
                count = 0;
                String sql = null;
                if (sdate.equals("") && edate.equals(""))//Complete data without date selection
                {
                                            System.out.println("Date is Empty");
                                            Map<Integer, Object> d2_data_map = new HashMap<Integer, Object>();
                                            d2_data_map = new D2_DAO().getD2Data(con, meter_id, "", "");
                                            noOfIntervals = d2_data_map.size();
                                            //System.out.println(" history size  :  "+noOfIntervals);
                                            if (noOfIntervals > 0) 
                                            {
                                                count = noOfIntervals + 1;
                                                int limit1 = limit;
                                                if (count > 0 && start + limit - 1 > count) 
                                                {
                                                    int diff = (start + limit - 1) - count;
                                                    limit1 = limit - (diff + 1);
                                                }
                                                //System.out.println(" history size  :  "+count);
                                                //System.out.println(" history  g3 ind start : "+start);
                                                //System.out.println(" history  g3 ind limit : "+limit);
                                                //System.out.println(" history  g3 ind limit : "+limit1);
                                                for (int ind = start - 1; ind < start + limit1 - 1; ind++) 
                                                {
                                                    //System.out.println(" history  g3 ind : "+ind);

                                                    D2_Data_Form history = new D2_Data_Form();
                                                    history = (D2_Data_Form) d2_data_map.get(ind);

                                                    //System.out.println(" history  g3 : "+history.getStrG3());
                                                    //System.out.println(" history PF : "+history.getStrBphPF());
                                                    //System.out.println(" history PF  1: "+history.getStrBphPF());
                                                    if (history != null)
                                                    {
                                                        if (aemf == 0)//without MF
                                                        {
                                    %>

                                                            <tr align="center">
                                                                <%   System.out.println("  555 : ");%>
                                                                <td ><%=history.getStrG3() == null ? "" : history.getStrG3()%></td>
                                                                <td align="right" class="v" ><%=history.getStrRphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrRphVol().trim())))%></td>
                                                                <td align="right" class="v" ><%=history.getStrYphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrYphVol().trim())))%></td>
                                                                <td align="right" class="v" ><%=history.getStrBphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrBphVol().trim())))%></td>
                                                                <td align="right" class="kv" ><%=history.getStrRphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrRphVol().trim())) / 1000)%></td>
                                                                <td align="right" class="kv" ><%=history.getStrYphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrYphVol().trim())) / 1000)%></td>
                                                                <td align="right" class="kv" ><%=history.getStrBphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrBphVol().trim())) / 1000)%></td>

                                                                <td align="right"><%=history.getStrRphLineCurr().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrRphLineCurr().trim()))%></td>
                                                                <td align="right"><%=history.getStrYphLineCurr().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrYphLineCurr().trim()))%></td>
                                                                <td align="right"><%=history.getStrBphLineCurr().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrBphLineCurr().trim()))%></td>
                                                                <td align="right"><%=history.getStrRph_Act_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrRph_Act_Current().trim()))%></td>
                                                                <td align="right"><%=history.getStrYph_Act_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrYph_Act_Current().trim()))%></td>
                                                                <td align="right"><%=history.getStrBph_Act_Current().trim().equals("NA") ? "" : df1.format(Double.parseDouble(history.getStrBph_Act_Current().trim()))%></td>
                                                                <td align="right"><%=history.getStrRph_React_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrRph_React_Current().trim()))%></td>
                                                                <td align="right"><%=history.getStrYph_React_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrYph_React_Current().trim()))%></td>
                                                                <td align="right"><%=history.getStrBph_React_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrBph_React_Current().trim()))%></td>
                                                                <td align="right"><%=history.getStrRphPF().trim().equals("0") ? "0.00" : history.getStrRphPF().trim()%></td>
                                                                <td align="right"><%=history.getStrYphPF().trim().equals("0") ? "0.00" : history.getStrYphPF().trim()%></td>
                                                                <td align="right"><%=history.getStrBphPF().trim().equals("0") ? "0.00" : history.getStrBphPF().trim()%></td>
                                                                <td align="right"><%=history.getStrAvg_Power_Fact().trim().equals("") ? "" : history.getStrAvg_Power_Fact().trim()%></td>
                                                                <td align="right"><%=history.getStrFund_Act_Power().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrFund_Act_Power().trim()))%></td>
                                                                <td align="right"><%=history.getStrApp_Power().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrApp_Power().trim()))%></td>
                                                                <td align="right"><%=history.getStrReact_Power().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrReact_Power().trim()))%></td>
                                                                <td align="right"><%=history.getStrFreq().trim().equals("") ? "" : df1.format(Double.parseDouble(history.getStrFreq().trim()))%></td>
                                                            </tr>
                                    <%
                                                        } 
                                                        else 
                                                        {
                                    %>
                                                            <tr align="center">
                                                                <td ><%=history.getStrG3() == null ? "" : history.getStrG3()%></td>
                                                                <td align="right" class="v" ><%=history.getStrRphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrRphVol().trim()) * vmf))%></td>
                                                                <td align="right" class="v" ><%=history.getStrYphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrYphVol().trim()) * vmf))%></td>
                                                                <td align="right" class="v" ><%=history.getStrBphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrBphVol().trim()) * vmf))%></td>
                                                                <td align="right" class="kv" ><%=history.getStrRphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrRphVol().trim()) * vmf) / 1000)%></td>
                                                                <td align="right" class="kv" ><%=history.getStrYphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrYphVol().trim()) * vmf) / 1000)%></td>
                                                                <td align="right" class="kv" ><%=history.getStrBphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrBphVol().trim()) * vmf) / 1000)%></td>

                                                                <td align="right"><%=history.getStrRphLineCurr().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrRphLineCurr().trim()) * cmf)%></td>
                                                                <td align="right"><%=history.getStrYphLineCurr().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrYphLineCurr().trim()) * cmf)%></td>
                                                                <td align="right"><%=history.getStrBphLineCurr().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrBphLineCurr().trim()) * cmf)%></td>
                                                                <td align="right"><%=history.getStrRph_Act_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrRph_Act_Current().trim()) * cmf)%></td>
                                                                <td align="right"><%=history.getStrYph_Act_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrYph_Act_Current().trim()) * cmf)%></td>
                                                                <td align="right"><%=history.getStrBph_Act_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrBph_Act_Current().trim()) * cmf)%></td>
                                                                <td align="right"><%=history.getStrRph_React_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrRph_React_Current().trim()) * cmf)%></td>
                                                                <td align="right"><%=history.getStrYph_React_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrYph_React_Current().trim()) * cmf)%></td>
                                                                <td align="right"><%=history.getStrBph_React_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrBph_React_Current().trim()) * cmf)%></td>
                                                                <td align="right"><%=history.getStrRphPF().trim().equals("0") ? "0.00" : history.getStrRphPF().trim()%></td>
                                                                <td align="right"><%=history.getStrYphPF().trim().equals("0") ? "0.00" : history.getStrYphPF().trim()%></td>
                                                                <td align="right"><%=history.getStrBphPF().trim().equals("0") ? "0.00" : history.getStrBphPF().trim()%></td>
                                                                <td align="right"><%=history.getStrAvg_Power_Fact().trim().equals("") ? "" : df1.format(Math.abs(Double.parseDouble(history.getStrAvg_Power_Fact().trim())))%></td>
                                                                <td align="right"><%=history.getStrFund_Act_Power().trim().equals("0.00") ? "" : df1.format(Double.parseDouble(history.getStrFund_Act_Power().trim()) * mf)%></td>
                                                                <td align="right"><%=history.getStrApp_Power().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrApp_Power().trim()) * mf)%></td>
                                                                <td align="right"><%=history.getStrReact_Power().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrReact_Power().trim()) * mf)%></td>
                                                                <td align="right"><%=history.getStrFreq().trim().equals("") ? "" : df1.format(Double.parseDouble(history.getStrFreq().trim()))%></td>
                                                            </tr>

            <%
                                                        }
                                                    }
                                                }
                                            }
                } 
    
                else
                {    // INSTANTANEOUS complete data of a meter

                    System.out.println("Date Range Selected---------------------------------------------------->");
                    Map<Integer, Object> d2_data_map = new HashMap<Integer, Object>();
                    start = Integer.parseInt(request.getParameter("start"));
                    d2_data_map = new D2_DAO().getD2DataDayWise(con, meter_sl_no, sdate, start);
                    noOfIntervals = d2_data_map.size();
                    int srlNo = 1;
                    if (noOfIntervals > 0) 
                    {
                        count = noOfIntervals + 1;
                        int limit1 = limit;
                        if (count > 0 && start + limit - 1 > count) 
                        {
                            int diff = (start + limit - 1) - count;
                            limit1 = limit - (diff + 1);
                        }
                                            //System.out.println(" history size  :  "+count);
                                            //System.out.println(" history  g3 ind start : "+start);
                                            //System.out.println(" history  g3 ind limit : "+limit);
                                            //System.out.println(" history  g3 ind limit : "+limit1);
                        for (int ind = 0; ind < d2_data_map.size(); ind++) 
                        {
                                                //System.out.println(" history  g3 ind : "+ind);
                            D2_Data_Form history = new D2_Data_Form();
                            history = (D2_Data_Form) d2_data_map.get(ind);
                                                //System.out.println(" history  g3 : "+history.getStrG3());
                                                //System.out.println(" history PF : "+history.getStrBphPF());
                                                //System.out.println(" history PF  1: "+history.getStrBphPF());
                            if (history != null) 
                            {
                                if (aemf == 0)//without MF
                                {
                                    System.out.println("aemf in if = " + aemf);
%>
                                    <tr align="center">
                                        <%   System.out.println("  555 : ");%>
                                        <td><%=srlNo++%></td>
                                        <td ><%=history.getStrG3() == null ? "" : history.getStrG3()%></td>
                                        <td align="right" class="v"><%=history.getStrRphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrRphVol().trim())))%></td>
                                        <td align="right" class="v"><%=history.getStrYphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrYphVol().trim())))%></td>
                                        <td align="right" class="v"><%=history.getStrBphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrBphVol().trim())))%></td>
                                        <td align="right" class="kv"><%=history.getStrRphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrRphVol().trim())) / 1000)%></td>
                                        <td align="right" class="kv"><%=history.getStrYphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrYphVol().trim())) / 1000)%></td>
                                        <td align="right" class="kv"><%=history.getStrBphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrBphVol().trim())) / 1000)%></td>

                                        <td align="right"><%=history.getStrRphLineCurr().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrRphLineCurr().trim()))%></td>
                                        <td align="right"><%=history.getStrYphLineCurr().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrYphLineCurr().trim()))%></td>
                                        <td align="right"><%=history.getStrBphLineCurr().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrBphLineCurr().trim()))%></td>
                                        <td align="right" id="hidden_fields"><%=history.getStrRph_Act_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrRph_Act_Current().trim()))%></td>
                                        <td align="right" id="hidden_fields"><%=history.getStrYph_Act_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrYph_Act_Current().trim()))%></td>
                                        <td align="right" id="hidden_fields"><%=history.getStrBph_Act_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrBph_Act_Current().trim()))%></td>
                                        <td align="right" id="hidden_fields"><%=history.getStrRph_React_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrRph_React_Current().trim()))%></td>
                                        <td align="right" id="hidden_fields"><%=history.getStrYph_React_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrYph_React_Current().trim()))%></td>
                                        <td align="right" id="hidden_fields"><%=history.getStrBph_React_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrBph_React_Current().trim()))%></td>
                                        <td align="right"><%=history.getStrRphPF().trim().equals("0") ? "0.00" : history.getStrRphPF().trim()%></td>
                                        <td align="right"><%=history.getStrYphPF().trim().equals("0") ? "0.00" : history.getStrYphPF().trim()%></td>
                                        <td align="right"><%=history.getStrBphPF().trim().equals("0") ? "0.00" : history.getStrBphPF().trim()%></td>
                                        <td align="right"><%=history.getStrAvg_Power_Fact().trim().equals("") ? "" : history.getStrAvg_Power_Fact().trim()%></td>
                                        <td align="right"><%=history.getStrFund_Act_Power().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrFund_Act_Power().trim()))%></td>
                                        <td align="right"><%=history.getStrApp_Power().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrApp_Power().trim()))%></td>
                                        <td align="right"><%=history.getStrReact_Power().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrReact_Power().trim()))%></td>
                                        <td align="right"><%=history.getStrFreq().trim().equals("") ? "" : df1.format(Double.parseDouble(history.getStrFreq().trim()))%></td>
                                    </tr>
<%
                                } 
                                else 
                                {
                                    System.out.println("aemf in else = " + aemf);
                                    %>
                                    <tr align="center">
                                        <td><%=srlNo++%></td>
                                        <td ><%=history.getStrG3() == null ? "" : history.getStrG3()%></td>
                                        <td align="right" class="v"><%=history.getStrRphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrRphVol().trim()) * vmf))%></td>
                                        <td align="right" class="v"><%=history.getStrYphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrYphVol().trim()) * vmf))%></td>
                                        <td align="right" class="v"><%=history.getStrBphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrBphVol().trim()) * vmf))%></td>
                                        <td align="right" class="kv"><%=history.getStrRphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrRphVol().trim()) * vmf) / 1000)%></td>
                                        <td align="right" class="kv"><%=history.getStrYphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrYphVol().trim()) * vmf) / 1000)%></td>
                                        <td align="right" class="kv"><%=history.getStrBphVol().trim().equals("0") ? "0.00" : df1.format((Double.parseDouble(history.getStrBphVol().trim()) * vmf) / 1000)%></td>

                                        <td align="right"><%=history.getStrRphLineCurr().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrRphLineCurr().trim()) * cmf)%></td>
                                        <td align="right"><%=history.getStrYphLineCurr().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrYphLineCurr().trim()) * cmf)%></td>
                                        <td align="right"><%=history.getStrBphLineCurr().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrBphLineCurr().trim()) * cmf)%></td>
                                        <td align="right" id="hidden_fields"><%=history.getStrRph_Act_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrRph_Act_Current().trim()) * cmf)%></td>
                                        <td align="right" id="hidden_fields"><%=history.getStrYph_Act_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrYph_Act_Current().trim()) * cmf)%></td>
                                        <td align="right" id="hidden_fields"><%=history.getStrBph_Act_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrBph_Act_Current().trim()) * cmf)%></td>
                                        <td align="right" id="hidden_fields"><%=history.getStrRph_React_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrRph_React_Current().trim()) * cmf)%></td>
                                        <td align="right" id="hidden_fields"><%=history.getStrYph_React_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrYph_React_Current().trim()) * cmf)%></td>
                                        <td align="right" id="hidden_fields"><%=history.getStrBph_React_Current().trim().equals("NA") ? " " : df1.format(Double.parseDouble(history.getStrBph_React_Current().trim()) * cmf)%></td>
                                        <td align="right"><%=history.getStrRphPF().trim().equals("0") ? "0.00" : history.getStrRphPF().trim()%></td>
                                        <td align="right"><%=history.getStrYphPF().trim().equals("0") ? "0.00" : history.getStrYphPF().trim()%></td>
                                        <td align="right"><%=history.getStrBphPF().trim().equals("0") ? "0.00" : history.getStrBphPF().trim()%></td>
                                        <td align="right"><%=history.getStrAvg_Power_Fact().trim().equals("") ? "" : df1.format(Math.abs(Double.parseDouble(history.getStrAvg_Power_Fact().trim())))%></td>
                                        <td align="right"><%=history.getStrFund_Act_Power().trim().equals("0.00") ? "" : df1.format(Double.parseDouble(history.getStrFund_Act_Power().trim()) * mf)%></td>
                                        <td align="right"><%=history.getStrApp_Power().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrApp_Power().trim()) * mf)%></td>
                                        <td align="right"><%=history.getStrReact_Power().trim().equals("0") ? "0.00" : df1.format(Double.parseDouble(history.getStrReact_Power().trim()) * mf)%></td>
                                        <td align="right"><%=history.getStrFreq().trim().equals("") ? "" : df1.format(Double.parseDouble(history.getStrFreq().trim()))%></td>
                                    </tr>

                                    <%
                                }
                            }
                        }
                    }
                    else
                    {
%>
                                    <tr>
                                        <td colspan="20" style="text-align: center;font-weight: bold">
                                            Data Not Available
                                        </td>
                                    </tr>
<%                  }
                }
                out.println(stringToReturn);
            %>

        
    <!--<div id="note">
        <p style="font-variant: normal;text-transform: none;"><font color="red"><b>Note:</b>
            For <b>DLMS</b> meters active currents and reactive currents data can not be captured</font>.</p>
    </div>
    <!-- code for pagination -->
    <script type="text/javascript">
        setOfPagination();
    </script>
    
    <%
        System.out.println("<===== End =====>");
}// end of complete data
//START OF INSTANTANEOUS DATA
else
{
//     Insantaneous latest data of meter
        System.out.println("<== 10 Records Instant Block==>");
        // latest data block
        double ctr, ptr, Actr, Aptr;
        double vmf = 1.0, cmf = 1.0, mf = 1.0;
        if (aemf != 0) {
            pstmt2 = con.prepareStatement("select * from meter where meterid='"+meter_id+"'");
            rs2 = pstmt2.executeQuery();
            if (rs2.next()) {
                ctr = Double.parseDouble(rs2.getString("meter_ctr") == null ? "1.0" : rs2.getString("meter_ctr"));
                ptr = Double.parseDouble(rs2.getString("meter_ptr") == null ? "1.0" : rs2.getString("meter_ptr"));
                Actr = Double.parseDouble(rs2.getString("adopted_ctr") == null ? "1.0" : rs2.getString("adopted_ctr"));
                Aptr = Double.parseDouble(rs2.getString("adopted_ptr") == null ? "1.0" : rs2.getString("adopted_ptr"));
                mf = Double.parseDouble(rs2.getString("mf") == null ? "1.0" : rs2.getString("mf"));
                vmf = Double.parseDouble(df1.format(Aptr / ptr)) * 1;
                cmf = Double.parseDouble(df1.format(Actr / ctr));
                pmf = Double.parseDouble(df1.format(cmf / vmf));
            }
        }

        String sql = "";

                    System.out.println("meter_id in instant block  = '"+meter_id+"'");
            //pstmt = con.prepareStatement("select * from d2 where meterid=" + meter_id + " order by meter_time_stamp desc limit 10");
            sql = "select TIMESTAMPDIFF(SECOND,str_to_date(g2,'%d-%m-%Y %H:%i:%s'),log_time_stamp) timediff,g2,rph_voltage,yph_voltage,bph_voltage,rph_line_current,yph_line_current,bph_line_current,rph_active_current,yph_active_current,bph_active_current,rph_react_current,yph_react_current,bph_react_current,rph_power_factor,yph_power_factor,bph_power_factor,avg_power_factor,fund_act_power,apparent_power,react_power,frequency,g3 from d2_curr where meterid=" + meter_id + " order by str_to_date(g2,'%d-%m-%Y %H:%i:%s') desc limit 10";
            System.out.println("sql =======> "+sql);
            pstmt = con.prepareStatement(sql);
        
        rs = pstmt.executeQuery();
        String dataString = "";
        String dblRphVol = null;
        String dblYphVol = null;
        String dblBphVol = null;
        String dblRphLineCurr = null;
        String dblYphLineCurr = null;
        String dblBphLineCurr = null;
        String dblRphPF = null;
        String dblYphPF = null;
        String dblBphPF = null;
        String dblFund_Act_Power = null;
        String dblApp_Power = null;
        String dblReact_Power = null;
        String strRph_Act_Current = "";
        String strYph_Act_Current = "";
        String strBph_Act_Current = "";
        String strRph_React_Current = "";
        String strYph_React_Current = "";
        String strBph_React_Current = "";
        String strAvg_Power_Fact = "";
        String strFreq = "";
        String strG3 = "";
        // Double g7Value = (("").equals(meterIDG7Map.get(meter_id))||null==meterIDG7Map.get(meter_id))?null:Double.parseDouble(meterIDG7Map.get(meter_id));
        while (rs.next()) {
            strG3 = rs.getString("g2") == null ? "" : rs.getString("g2");
            dblRphVol = rs.getString("rph_voltage") == null ? "0.00" : Double.parseDouble(rs.getString("rph_voltage")) + "";
//            System.out.println("dblRphVol = "+dblRphVol);
            dblYphVol = rs.getString("yph_voltage") == null ? "0.00" : Double.parseDouble(rs.getString("yph_voltage")) + "";
//            System.out.println("dblYphVol = "+dblYphVol);
            dblBphVol = rs.getString("bph_voltage") == null ? "0.00" : Double.parseDouble(rs.getString("bph_voltage")) + "";
//            System.out.println("dblBphVol ===========================================>>>>> "+dblBphVol);
//            System.out.println("vmf = "+vmf);

            dblRphLineCurr = rs.getString("RPH_LINE_CURRENT") == null ? "0.00" : Double.parseDouble(rs.getString("RPH_LINE_CURRENT")) + "";
            dblYphLineCurr = rs.getString("YPH_LINE_CURRENT") == null ? "0.00" : Double.parseDouble(rs.getString("YPH_LINE_CURRENT")) + "";
            dblBphLineCurr = rs.getString("BPH_LINE_CURRENT") == null ? "0.00" : Double.parseDouble(rs.getString("BPH_LINE_CURRENT")) + "";

            dblRphPF = rs.getString("RPH_POWER_FACTOR") == null ? "0.00" : Double.parseDouble(rs.getString("RPH_POWER_FACTOR")) + "";
            dblYphPF = rs.getString("YPH_POWER_FACTOR") == null ? "0.00" : Double.parseDouble(rs.getString("YPH_POWER_FACTOR")) + "";
            dblBphPF = rs.getString("BPH_POWER_FACTOR") == null ? "0.00" : Double.parseDouble(rs.getString("BPH_POWER_FACTOR")) + "";
            dblFund_Act_Power = rs.getString("FUND_ACT_POWER") == null ? "0.00" : Double.parseDouble(rs.getString("FUND_ACT_POWER")) + "";
            dblApp_Power = rs.getString("APPARENT_POWER") == null ? "0.00" : Double.parseDouble(rs.getString("APPARENT_POWER")) + "";
            dblReact_Power = rs.getString("REACT_POWER") == null ? "0.00" : Double.parseDouble(rs.getString("REACT_POWER")) + "";

            strRph_Act_Current = rs.getString("RPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_ACTIVE_CURRENT")));
            strYph_Act_Current = rs.getString("YPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_ACTIVE_CURRENT")));
            strBph_Act_Current = rs.getString("BPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_ACTIVE_CURRENT")));
            strRph_React_Current = rs.getString("RPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_REACT_CURRENT")));
            strYph_React_Current = rs.getString("YPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_REACT_CURRENT")));
            strBph_React_Current = rs.getString("BPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_REACT_CURRENT")));
            strAvg_Power_Fact = rs.getString("AVG_POWER_FACTOR") == null ? "" : df1.format(Double.parseDouble(rs.getString("AVG_POWER_FACTOR")));
            strFreq = rs.getString("FREQUENCY") == null ? "" : df1.format(Double.parseDouble(rs.getString("FREQUENCY")));
    %>
<tr  align="center" >
    <td class="tdbg"><a href="javascript:getTotalInstant('<%=meter_sl_no %>','<%=strG3%>');" class="btn-sm  md-trigger m-b-5 m-r-2" data-modal="ctmodal-rpt"><%=strG3 == null ? "" : strG3%></a></td>
    <td class="tdbg" onclick='getPhasor("<%=meter_sl_no%>","<%=strG3%>","tr_<%=strG3.trim()%>")'><%=rs.getString("g3") == null ? "" : rs.getString("g3")%></td>
    <td align="right" class="v"><%=df1.format((Double.parseDouble(dblRphVol) * vmf))%></td>
    <td align="right" class="v"><%=df1.format((Double.parseDouble(dblYphVol) * vmf))%></td>
    <td align="right" class="v"><%=df1.format((Double.parseDouble(dblBphVol) * vmf))%></td>
<!--    <td align="right" class="kv"><%=df1.format((Double.parseDouble(dblRphVol) * vmf) / 1000)%></td>
    <td align="right" class="kv"><%=df1.format((Double.parseDouble(dblYphVol) * vmf) / 1000)%></td>
    <td align="right" class="kv"><%=df1.format((Double.parseDouble(dblBphVol) * vmf) / 1000)%></td>-->

    <td align="right"><%=df1.format(Double.parseDouble(dblRphLineCurr) * cmf)%></td>
    <td align="right"><%=df1.format(Double.parseDouble(dblYphLineCurr) * cmf)%></td>
    <td align="right"><%=df1.format(Double.parseDouble(dblBphLineCurr) * cmf)%></td>
<!--    <td align="right" id="hidden_fields"><%=strRph_Act_Current == "NA" ? " " : df1.format(Double.parseDouble(strRph_Act_Current) * cmf)%></td>
    <td align="right" id="hidden_fields"><%=strYph_Act_Current == "NA" ? " " : df1.format(Double.parseDouble(strYph_Act_Current) * cmf)%></td>
    <td align="right" id="hidden_fields"><%=strBph_Act_Current == "NA" ? " " : df1.format(Double.parseDouble(strBph_Act_Current) * cmf)%></td>
    <td align="right" id="hidden_fields"><%=strRph_React_Current == "NA" ? " " : df1.format(Double.parseDouble(strRph_React_Current) * cmf)%></td>
    <td align="right" id="hidden_fields"><%=strYph_React_Current == "NA" ? " " : df1.format(Double.parseDouble(strYph_React_Current) * cmf)%></td>
    <td align="right" id="hidden_fields"><%=strBph_React_Current == "NA" ? " " : df1.format(Double.parseDouble(strBph_React_Current) * cmf)%></td>-->
    <td align="right"><%=dblRphPF == null ? "" : dblRphPF%></td>
    <td align="right"><%=dblYphPF == null ? "" : dblYphPF%></td>
    <td align="right"><%=dblBphPF == null ? "" : dblBphPF%></td>
    <td  align="right"><%=strAvg_Power_Fact == null ? "" :(strAvg_Power_Fact==""?"": df1.format(Math.abs(Double.parseDouble(strAvg_Power_Fact))))%></td>
    <td  align="right"><%=dblFund_Act_Power == null ? "" : df1.format(Double.parseDouble(dblFund_Act_Power) * mf)%></td>
    <td align="right"><%=dblApp_Power == null ? "" : df1.format(Double.parseDouble(dblApp_Power) * mf)%></td>
    <td  align="right"><%=dblReact_Power == null ? "" : df1.format(Double.parseDouble(dblReact_Power) * mf)%></td>
    <td  align="right"><%=strFreq == null ? "" : df1.format(Double.parseDouble(strFreq))%></td>
</tr>
<tr   align="center" id="tr_<%=strG3.trim()%>" class="tr_phasor">
    
</tr>
<% }//while %>
</tbody>
</table>
</div>
<%  pstmt1 = con.prepareStatement("select * from meter where meterid='"+meter_id+"'");
    rs1 = pstmt1.executeQuery();
    while (rs1.next()) {
        //System.out.println("me_ct ========== " + rs1.getString("me_ct"));
%>


<div class="tableoptions bdrbtm" style="padding:2px;">
    <div class="inc"> Inst ctc ratio : <%=rs1.getString("meter_ctr") == null ? "1.0" : rs1.getString("meter_ctr")%></div>
    <div class="incpt">Inst pt ratio : <%=rs1.getString("meter_ptr") == null ? "1.0" : rs1.getString("meter_ptr")%></div>
    <div class="incpt">Adapted ct : <%=rs1.getString("adopted_ctr") == null ? "1.0" : rs1.getString("adopted_ctr")%></div>
    <div class="incpt">Adapted pt : <%=rs1.getString("adopted_ptr") == null ? "1.0" : rs1.getString("adopted_ptr")%></div>
    <div class="incpt">EMF : <%=rs1.getString("mf")%> </div>
</div>

<div id="note">
    <p style="font-variant: normal;text-transform: none;"><font color="red"><b>Note:</b>
        For <b>DLMS</b> meters active currents and reactive currents data can not be captured</font>.</p>
</div>
<%
            }
            stringToReturn = stringToReturn;
            out.println(stringToReturn);
    }
    } catch (Exception e) {
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

<!--<input type="button" id = "clear" value="Filter" onclick="clearall()" /><%--Button to clear the graphs--%>
        <input type="button" value="Click" onclick="implement()"/>-->
</body>
 