<%@page import="utils.DBUtil"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="DataBeans.D2_Data_Form"%>
<%
    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
PreparedStatement pstmt = null;
ResultSet rs = null;
Connection con = null;
String month=request.getParameter("month");
String year = request.getParameter("year");
String meter_id = request.getParameter("meter_id");
String currMonth=sdf.format(new java.util.Date()).split("-")[1];
String currYear=sdf.format(new java.util.Date()).split("-")[2];
String meter_no=null;
con = new DBUtil().getConnection();
pstmt  = con.prepareStatement("select meter_serial_no from meter where meterid="+meter_id+";");
rs = pstmt.executeQuery();
while(rs.next())
{
    meter_no = rs.getString("meter_serial_no");
}
System.out.println("Meter no. is "+meter_no+" Month is "+month+ "Year is "+year);

int emf=Integer.parseInt(request.getParameter("EMF"));
%>
<head>
    <link rel="stylesheet" type="text/css" href="css/table.css">
</head>
<!-- <div class="tableoptions bdrbtm mart10" style="padding:2px;"> -->
<!--         <div class="complete" style="width:100%;text-align: center;"><b>Complete DATA</b>  -->
<!--         <p style="margin:0px !important;float:right;"> -->
<!--               <p style="margin:0px !important;float:right;"> -->
<!--             <button class="btn btn-sm" onclick="exportExcel('completedatatable','CompleteData.xls')">Export</button> -->
<!--             </p> -->
<!--             <img src="images/phasor.png" width="25px" height="25px" style="cursor: pointer;" ></img> -->
<!--         </p> -->
<!--         </div> -->
<!--         <div class="active_recvolt"> Show Active & Reactive Current <input name="check2" type="checkbox">  | Show voltagge in KV <input name="check2" type="checkbox"> </div> -->
<!--     </div>tableoptions -->
<div id="divcompletedatatable" class="table-responsive">
    <table width="100%" id="completedatatable" cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont">
        <thead>
            <tr>
                <!--<th>S.No</th>-->
                <th style="width:160px !important;"> Reading Date Time</th>
                <th style="width:160px !important;"> Server Date Time</th>
                <th>Rph V (volts)</th>
                <th>Yph V (volts)</th>
                <th>Bph V (volts)</th>
                <th>Rph I (amps)</th>
                <th>Yph I (amps)</th>
                <th>Bph I (amps)</th>
                <th>Rph Pf</th>
                <th>Yph Pf</th>
                <th>Bph Pf</th>
                <th>AVG Pf</th>
                <th>kw</th>
                <th>kva</th>
                <th>kvar</th>
                <th>Freq (Hz)</th>
            </tr>
        </thead>
        <tbody>
<%
     try {
    DecimalFormat df1 = new DecimalFormat("#0.000");
    double ctr, ptr, Actr, Aptr;
        double vmf = 1.0, cmf = 1.0, mf = 1.0,pmf=1.0;
        if (emf != 0) {
            pstmt = con.prepareStatement("select * from meter where meterid=" + meter_id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ctr = Double.parseDouble(rs.getString("meter_ctr") == null ? "1.0" : rs.getString("meter_ctr"));
                ptr = Double.parseDouble(rs.getString("meter_ptr") == null ? "1.0" : rs.getString("meter_ptr"));
                Actr = Double.parseDouble(rs.getString("adopted_ctr") == null ? "1.0" : rs.getString("adopted_ctr"));
                Aptr = Double.parseDouble(rs.getString("adopted_ptr") == null ? "1.0" : rs.getString("adopted_ptr"));
                mf = Double.parseDouble(rs.getString("mf") == null ? "1.0" : rs.getString("mf"));
                vmf = Double.parseDouble(df1.format(Aptr / ptr)) * 1;
                cmf = Double.parseDouble(df1.format(Actr / ctr));
                pmf = Double.parseDouble(df1.format(cmf / vmf));
            }
        }
        
            
        String db="d2";
          if(!month.equals(currMonth) && !year.equals(currYear))
              db="d2_"+month+year.substring(2);
       

            
                        String dblRphVol = "";
                        String dblYphVol = "";
                        String dblBphVol = "";
                        String dblRphLineCurr = "";
                        String dblYphLineCurr = "";
                        String dblBphLineCurr = "";
                        String dblRphPF = "";
                        String dblYphPF = "";
                        String dblBphPF = "";
                        String dblFund_Act_Power = "";
                        String dblApp_Power = "";
                        String dblReact_Power = "";
                        String Avg_Power_Fact = "";
                        String strFreq="";

                        String strG3 = "";

                        String sql = "";
                        sql = "select * from "+db +" where meter_serial_no='"+meter_no+"'";
                        sql = sql + " and  g2 like '%"+month+"-"+year+"%'";            
                        sql = sql + " order by STR_TO_DATE(g2,'%d-%m-%Y %H:%i:%s') desc";
                        
//                        String sql1 = "";
//                        sql = "select * from d2 where meter_serial_no='"+meter_no+"'";
//                        sql = sql + " and  g3 like '"+month+"-"+year+"%'";            
//                        sql = sql + " order by STR_TO_DATE(g3,'%d-%m-%Y %H:%i:%s')";
                        
                        System.out.println(sql);
                        con = new DBUtil().getConnection();
                        pstmt = con.prepareStatement(sql);
                        rs = pstmt.executeQuery();
                        int sno=1;
                        while (rs.next()) 
                        {

                            strG3 = rs.getString("g2") == null ? "" : rs.getString("g2"); //date time

                            dblRphVol = rs.getString("rph_voltage") == null ? "" : df1.format(Double.parseDouble(rs.getString("rph_voltage"))); 
                            dblYphVol = rs.getString("yph_voltage") == null ? "" : df1.format(Double.parseDouble(rs.getString("yph_voltage")));
                            dblBphVol = rs.getString("bph_voltage") == null ? "" : df1.format(Double.parseDouble(rs.getString("bph_voltage")));

                            dblRphLineCurr = rs.getString("RPH_LINE_CURRENT") == null ? "" : df1.format(Double.parseDouble(rs.getString("RPH_LINE_CURRENT")));
                            dblYphLineCurr = rs.getString("YPH_LINE_CURRENT") == null ? "" : df1.format(Double.parseDouble(rs.getString("YPH_LINE_CURRENT")));
                            dblBphLineCurr = rs.getString("BPH_LINE_CURRENT") == null ? "" : df1.format(Double.parseDouble(rs.getString("BPH_LINE_CURRENT")));

                            dblRphPF = rs.getString("RPH_POWER_FACTOR") == null ? "" : df1.format(Double.parseDouble(rs.getString("RPH_POWER_FACTOR")));
                            dblYphPF = rs.getString("YPH_POWER_FACTOR") == null ? "" : df1.format(Double.parseDouble(rs.getString("YPH_POWER_FACTOR")));
                            dblBphPF = rs.getString("BPH_POWER_FACTOR") == null ? "" : df1.format(Double.parseDouble(rs.getString("BPH_POWER_FACTOR")));
                            Avg_Power_Fact = rs.getString("AVG_POWER_FACTOR") == null ? "" : df1.format(Double.parseDouble(rs.getString("AVG_POWER_FACTOR")));

                            dblFund_Act_Power = rs.getString("FUND_ACT_POWER") == null ? "" : df1.format(Double.parseDouble(rs.getString("FUND_ACT_POWER")));
                            dblReact_Power = rs.getString("REACT_POWER") == null ? "" : df1.format(Double.parseDouble(rs.getString("REACT_POWER")));
                            dblApp_Power = rs.getString("APPARENT_POWER") == null ? "" : df1.format(Double.parseDouble(rs.getString("APPARENT_POWER")));

                            strFreq = rs.getString("FREQUENCY").trim() == null ? "" : df1.format(Double.parseDouble(rs.getString("FREQUENCY").trim()));

                            
                            //PRINTING THE TABLE
%>
                            <tr>
    <td class="tdbg">
    <%=strG3 == null ? "" : strG3%></td>
    <td class="tdbg"><%=rs.getString("g3") == null ? "" : rs.getString("g3")%></td>
    <td align="right" class="v"><%=df1.format((Double.parseDouble(dblRphVol) * vmf))%></td>
    <td align="right" class="v"><%=df1.format((Double.parseDouble(dblYphVol) * vmf))%></td>
    <td align="right" class="v"><%=df1.format((Double.parseDouble(dblBphVol) * vmf))%></td>
    

    <td align="right"><%=df1.format(Double.parseDouble(dblRphLineCurr) * cmf)%></td>
    <td align="right"><%=df1.format(Double.parseDouble(dblYphLineCurr) * cmf)%></td>
    <td align="right"><%=df1.format(Double.parseDouble(dblBphLineCurr) * cmf)%></td>
    
    <td align="right"><%=dblRphPF == null ? "" : dblRphPF%></td>
    <td align="right"><%=dblYphPF == null ? "" : dblYphPF%></td>
    <td align="right"><%=dblBphPF == null ? "" : dblBphPF%></td>
    <td  align="right"><%=Avg_Power_Fact == null ? "" :(Avg_Power_Fact==""?"": df1.format(Math.abs(Double.parseDouble(Avg_Power_Fact))))%></td>
    <td  align="right"><%=dblFund_Act_Power == null ? "" : df1.format(Double.parseDouble(dblFund_Act_Power) * mf)%></td>
    <td align="right"><%=dblApp_Power == null ? "" : df1.format(Double.parseDouble(dblApp_Power) * mf)%></td>
    <td  align="right"><%=dblReact_Power == null ? "" : df1.format(Double.parseDouble(dblReact_Power) * mf)%></td>
    <td  align="right"><%=strFreq == null ? "" : df1.format(Double.parseDouble(strFreq))%></td>

                            </tr>
<%
                            sno++;
                        }           

                    } 
                    catch (Exception e) 
                    {
                        //System.out.println("Error in DAO : " + e.getMessage());
                        e.printStackTrace();
                    }
%>
            
            
        </tbody>
        
    </table>
</div>  