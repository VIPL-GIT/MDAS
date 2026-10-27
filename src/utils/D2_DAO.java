package utils;



import DataBeans.D2_Data_Form;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import ActionBeans.Utilities;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import utils.DBUtil;

/**
 *
 * @author Praveen
 */
public class D2_DAO {

    public static Map getD2Data(Connection con, String meter_id, String sdate, String edate) throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DecimalFormat df1 = new DecimalFormat("#0.0");
        System.out.println("sdate is "+sdate+"end date is "+edate);
        if (sdate.trim().length() > 0 && edate.trim().length() > 0) {
            edate = new Utilities().add_Oneday(edate + " 00:00:00");
        }
        edate = edate.replace("-", "/");
        Map<Integer, Object> d2_data_map = new HashMap<Integer, Object>();
        try {

            int noOfInter = 0;
            double dblRphVol = 0;
            double dblYphVol = 0;
            double dblBphVol = 0;
            double dblRphLineCurr = 0;
            double dblYphLineCurr = 0;
            double dblBphLineCurr = 0;
            double dblRphPF = 0;
            double dblYphPF = 0;
            double dblBphPF = 0;
            double dblFund_Act_Power = 0;
            double dblApp_Power = 0;
            double dblReact_Power = 0;
            String strRph_Act_Current = "";
            String strYph_Act_Current = "";
            String strBph_Act_Current = "";
            String strRph_React_Current = "";
            String strYph_React_Current = "";
            String strBph_React_Current = "";
            String strAvg_Power_Fact = "";
            String strFreq = "";
            String strG3 = "";
            int tempCount = 0;
            int tempCount1 = 0;

            double dblPrevRphVol = 0;
            double dblPrevYphVol = 0;
            double dblPrevBphVol = 0;
            double dblPrevRphLineCurr = 0;
            double dblPrevYphLineCurr = 0;
            double dblPrevBphLineCurr = 0;
            double dblPrevRphPF = 0;
            double dblPrevYphPF = 0;
            double dblPrevBphPF = 0;
            double dblPrevFund_Act_Power = 0;
            double dblPrevApp_Power = 0;
            double dblPrevReact_Power = 0;
            String strPrevRph_Act_Current = "";
            String strPrevYph_Act_Current = "";
            String strPrevBph_Act_Current = "";
            String strPrevRph_React_Current = "";
            String strPrevYph_React_Current = "";
            String strPrevBph_React_Current = "";
            String strPrevAvg_Power_Fact = "";
            String strPrevFreq = "";
            String strPrevG3 = "";
            String sql = "";
     
            sql = "select * from d2 where cdfid in (select max(cdfid) from d2  D where  D.meterid="+meter_id+"";
            if (sdate.trim().length() > 0 && edate.trim().length() > 0) {
                sql = sql + " and convert(datetime,METER_TIME_STAMP,103) >= convert(datetime,'" + sdate + "',103) and convert(datetime,METER_TIME_STAMP,103)<= convert(datetime,'" + edate + "',103) ";
            }
            sql = sql + " group by g2) order by convert(datetime,g3,103) desc ";
            System.out.println(sql);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) 
            {
                strG3 = rs.getString("g3") == null ? "" : rs.getString("g3");
                dblRphVol = rs.getString("rph_voltage") == null ? 0 : Double.parseDouble(rs.getString("rph_voltage"));
                dblYphVol = rs.getString("yph_voltage") == null ? 0 : Double.parseDouble(rs.getString("yph_voltage"));
                dblBphVol = rs.getString("bph_voltage") == null ? 0 : Double.parseDouble(rs.getString("bph_voltage"));
                /*if(null!=g7Value){
                    //System.out.println(dblRphVol+","+dblYphVol+","+dblBphVol+","+g7Value);    
                    dblRphVol =  ((dblRphVol*g7Value)/100); 
                    dblYphVol =  ((dblYphVol*g7Value)/100); 
                    dblBphVol =  ((dblBphVol*g7Value)/100); 
                }*/
                dblRphLineCurr = rs.getString("RPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("RPH_LINE_CURRENT"));
                dblYphLineCurr = rs.getString("YPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("YPH_LINE_CURRENT"));
                dblBphLineCurr = rs.getString("BPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("BPH_LINE_CURRENT"));

                dblRphPF = rs.getString("RPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("RPH_POWER_FACTOR"));
                dblYphPF = rs.getString("YPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("YPH_POWER_FACTOR"));
                dblBphPF = rs.getString("BPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("BPH_POWER_FACTOR"));
                dblFund_Act_Power = rs.getString("FUND_ACT_POWER") == null ? 0 : Double.parseDouble(rs.getString("FUND_ACT_POWER"));
                dblApp_Power = rs.getString("APPARENT_POWER") == null ? 0 : Double.parseDouble(rs.getString("APPARENT_POWER"));
                dblReact_Power = rs.getString("REACT_POWER") == null ? 0 : Double.parseDouble(rs.getString("REACT_POWER"));

                strRph_Act_Current = rs.getString("RPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_ACTIVE_CURRENT")));
                strYph_Act_Current = rs.getString("YPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_ACTIVE_CURRENT")));
                strBph_Act_Current = rs.getString("BPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_ACTIVE_CURRENT")));
                strRph_React_Current = rs.getString("RPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_REACT_CURRENT")));
                strYph_React_Current = rs.getString("YPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_REACT_CURRENT")));
                strBph_React_Current = rs.getString("BPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_REACT_CURRENT")));
                strAvg_Power_Fact = rs.getString("AVG_POWER_FACTOR") == null ? "" : df1.format(Double.parseDouble(rs.getString("AVG_POWER_FACTOR")));
                strFreq = rs.getString("FREQUENCY") == null ? "" : df1.format(Double.parseDouble(rs.getString("FREQUENCY")));
                D2_Data_Form history = new D2_Data_Form();
                history.setStrG3(strG3);
                history.setStrRphVol(dblRphVol+"");
                history.setStrYphVol(dblYphVol + "");
                history.setStrBphVol(dblBphVol + "");
                history.setStrRphLineCurr(dblRphLineCurr + "");
                history.setStrYphLineCurr(dblYphLineCurr + "");
                history.setStrBphLineCurr(dblBphLineCurr + "");
                history.setStrRphPF(dblRphPF + "");
                history.setStrYphPF(dblYphPF + "");
                history.setStrBphPF(dblBphPF + "");
                history.setStrFund_Act_Power(dblFund_Act_Power + "");
                history.setStrApp_Power(dblApp_Power + "");
                history.setStrReact_Power(dblReact_Power + "");
                history.setStrRph_Act_Current(strRph_Act_Current);
                history.setStrYph_Act_Current(strYph_Act_Current);
                history.setStrBph_Act_Current(strBph_Act_Current);
                history.setStrRph_React_Current(strRph_React_Current);
                history.setStrYph_React_Current(strYph_React_Current);
                history.setStrBph_React_Current(strBph_React_Current);
                history.setStrAvg_Power_Fact(strAvg_Power_Fact);
                history.setStrFreq(strFreq);                    
                noOfInter = noOfInter + 1;                    
                tempCount1 = tempCount1 + 1;
                d2_data_map.put(noOfInter - 1, history);
                System.out.println(" tempCount1 : " + tempCount1);
                
            }
            if (noOfInter == 1 || tempCount1 == 1) {
                D2_Data_Form history = new D2_Data_Form();
                history.setStrG3(strPrevG3);
                history.setStrRphVol(dblPrevRphVol + "");
                history.setStrYphVol(dblPrevYphVol + "");
                history.setStrBphVol(dblPrevBphVol + "");
                history.setStrRphLineCurr(dblPrevRphLineCurr + "");
                history.setStrYphLineCurr(dblPrevYphLineCurr + "");
                history.setStrBphLineCurr(dblPrevBphLineCurr + "");
                history.setStrRphPF(dblPrevRphPF + "");
                history.setStrYphPF(dblPrevYphPF + "");
                history.setStrBphPF(dblPrevBphPF + "");
                history.setStrFund_Act_Power(dblPrevFund_Act_Power + "");
                history.setStrApp_Power(dblPrevApp_Power + "");
                history.setStrReact_Power(dblPrevReact_Power + "");
                history.setStrRph_Act_Current(strPrevRph_Act_Current);
                history.setStrYph_Act_Current(strPrevYph_Act_Current);
                history.setStrBph_Act_Current(strPrevBph_Act_Current);
                history.setStrRph_React_Current(strPrevRph_React_Current);
                history.setStrYph_React_Current(strPrevYph_React_Current);
                history.setStrBph_React_Current(strPrevBph_React_Current);
                history.setStrAvg_Power_Fact(strPrevAvg_Power_Fact);
                history.setStrFreq(strPrevFreq);
                d2_data_map.put(noOfInter - 1, history);
            }
            System.out.println(" tempCount : " + tempCount);
            System.out.println(" tempCount1 : " + tempCount1);
        } catch (Exception e) {
            System.out.println("Error in DAO : " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("in DAO : " + d2_data_map.size());
        return d2_data_map;
    }

    public static int getD2DataCount(Connection con, String meter_id, String sdate) throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DecimalFormat df1 = new DecimalFormat("#0.0");
        int noOfInter = 0;
        int flagCount = 0;
        String edate = sdate;
        if (sdate.trim().length() > 0 && edate.trim().length() > 0) {
            edate = new Utilities().add_Oneday(edate + " 00:00:00");
        }
        edate = edate.replace("-", "/");
        sdate = sdate.replace("-", "/");
        try {
            double dblRphVol = 0;
            double dblYphVol = 0;
            double dblBphVol = 0;
            double dblRphLineCurr = 0;
            double dblYphLineCurr = 0;
            double dblBphLineCurr = 0;
            double dblRphPF = 0;
            double dblYphPF = 0;
            double dblBphPF = 0;
            double dblFund_Act_Power = 0;
            double dblApp_Power = 0;
            double dblReact_Power = 0;
            String strRph_Act_Current = "";
            String strYph_Act_Current = "";
            String strBph_Act_Current = "";
            String strRph_React_Current = "";
            String strYph_React_Current = "";
            String strBph_React_Current = "";
            String strAvg_Power_Fact = "";
            String strFreq = "";
            String strG3 = "";

            double dblPrevRphVol = 0;
            double dblPrevYphVol = 0;
            double dblPrevBphVol = 0;
            double dblPrevRphLineCurr = 0;
            double dblPrevYphLineCurr = 0;
            double dblPrevBphLineCurr = 0;
            double dblPrevRphPF = 0;
            double dblPrevYphPF = 0;
            double dblPrevBphPF = 0;
            double dblPrevFund_Act_Power = 0;
            double dblPrevApp_Power = 0;
            double dblPrevReact_Power = 0;
            String strPrevRph_Act_Current = "";
            String strPrevYph_Act_Current = "";
            String strPrevBph_Act_Current = "";
            String strPrevRph_React_Current = "";
            String strPrevYph_React_Current = "";
            String strPrevBph_React_Current = "";
            String strPrevAvg_Power_Fact = "";
            String strPrevFreq = "";
            String strPrevG3 = "";
            String sql = "";
            sql = " select g3,rph_voltage,yph_voltage,bph_voltage,rph_line_current,yph_line_current,bph_line_current,rph_power_factor,yph_power_factor,bph_power_factor,avg_power_factor,FUND_ACT_POWER,APPARENT_POWER,REACT_POWER,FREQUENCY,RPH_ACTIVE_CURRENT,YPH_ACTIVE_CURRENT,BPH_ACTIVE_CURRENT,RPH_REACT_CURRENT,BPH_REACT_CURRENT,YPH_REACT_CURRENT from d2  D,meter M where D.G1 = '" + meter_id + "'  and D.meterid=M.METERID ";
            sql = sql + " and (((cast(RPH_LINE_CURRENT as float)+cast(YPH_LINE_CURRENT as float)+cast(BPH_LINE_CURRENT as float))/3)*M.ADOPTED_CTR/M.METER_CTR)<1000 ";
            if (sdate.trim().length() > 0 && edate.trim().length() > 0) {
                sql = sql + " and convert(datetime,METER_TIME_STAMP,103) >= convert(datetime,'" + sdate + "',103)  and convert(datetime,METER_TIME_STAMP,103)<= convert(datetime,'" + edate + "',103) ";
            }
            sql = sql + " order by convert(datetime,g3,103) desc ";
            System.out.println(sql);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                strG3 = rs.getString("g3") == null ? "" : rs.getString("g3");
                dblRphVol = rs.getString("rph_voltage") == null ? 0 : Double.parseDouble(rs.getString("rph_voltage"));
                dblYphVol = rs.getString("yph_voltage") == null ? 0 : Double.parseDouble(rs.getString("yph_voltage"));
                dblBphVol = rs.getString("bph_voltage") == null ? 0 : Double.parseDouble(rs.getString("bph_voltage"));
                dblRphLineCurr = rs.getString("RPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("RPH_LINE_CURRENT"));
                dblYphLineCurr = rs.getString("YPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("YPH_LINE_CURRENT"));
                dblBphLineCurr = rs.getString("BPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("BPH_LINE_CURRENT"));

                dblRphPF = rs.getString("RPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("RPH_POWER_FACTOR"));
                dblYphPF = rs.getString("YPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("YPH_POWER_FACTOR"));
                dblBphPF = rs.getString("BPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("BPH_POWER_FACTOR"));
                dblFund_Act_Power = rs.getString("FUND_ACT_POWER") == null ? 0 : Double.parseDouble(rs.getString("FUND_ACT_POWER"));
                dblApp_Power = rs.getString("APPARENT_POWER") == null ? 0 : Double.parseDouble(rs.getString("APPARENT_POWER"));
                dblReact_Power = rs.getString("REACT_POWER") == null ? 0 : Double.parseDouble(rs.getString("REACT_POWER"));

                strRph_Act_Current = rs.getString("RPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_ACTIVE_CURRENT")));
                strYph_Act_Current = rs.getString("YPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_ACTIVE_CURRENT")));
                strBph_Act_Current = rs.getString("BPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_ACTIVE_CURRENT")));
                strRph_React_Current = rs.getString("RPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_REACT_CURRENT")));
                strYph_React_Current = rs.getString("YPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_REACT_CURRENT")));
                strBph_React_Current = rs.getString("BPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_REACT_CURRENT")));
                strAvg_Power_Fact = rs.getString("AVG_POWER_FACTOR") == null ? "" : df1.format(Double.parseDouble(rs.getString("AVG_POWER_FACTOR")));
                strFreq = rs.getString("FREQUENCY") == null ? "" : df1.format(Double.parseDouble(rs.getString("FREQUENCY")));
                if (noOfInter != 0 && (dblRphVol != dblPrevRphVol || dblYphVol != dblPrevYphVol || dblBphVol != dblPrevBphVol || dblRphLineCurr != dblPrevRphLineCurr || dblYphLineCurr != dblPrevYphLineCurr || dblBphLineCurr != dblPrevBphLineCurr || dblRphPF != dblPrevRphPF || dblYphPF != dblPrevYphPF || dblBphPF != dblPrevBphPF || dblFund_Act_Power != dblPrevFund_Act_Power || dblApp_Power != dblPrevApp_Power || dblReact_Power != dblPrevReact_Power)) {
                    noOfInter = noOfInter + 1;

                    strPrevG3 = rs.getString("g3") == null ? "" : rs.getString("g3");
                    dblPrevRphVol = rs.getString("rph_voltage") == null ? 0 : Double.parseDouble(rs.getString("rph_voltage"));
                    dblPrevYphVol = rs.getString("yph_voltage") == null ? 0 : Double.parseDouble(rs.getString("yph_voltage"));
                    dblPrevBphVol = rs.getString("bph_voltage") == null ? 0 : Double.parseDouble(rs.getString("bph_voltage"));

                    dblPrevRphLineCurr = rs.getString("RPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("RPH_LINE_CURRENT"));
                    dblPrevYphLineCurr = rs.getString("YPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("YPH_LINE_CURRENT"));
                    dblPrevBphLineCurr = rs.getString("BPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("BPH_LINE_CURRENT"));

                    dblPrevRphPF = rs.getString("RPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("RPH_POWER_FACTOR"));
                    dblPrevYphPF = rs.getString("YPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("YPH_POWER_FACTOR"));
                    dblPrevBphPF = rs.getString("BPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("BPH_POWER_FACTOR"));
                    dblPrevFund_Act_Power = rs.getString("FUND_ACT_POWER") == null ? 0 : Double.parseDouble(rs.getString("FUND_ACT_POWER"));
                    dblPrevApp_Power = rs.getString("APPARENT_POWER") == null ? 0 : Double.parseDouble(rs.getString("APPARENT_POWER"));
                    dblPrevReact_Power = rs.getString("REACT_POWER") == null ? 0 : Double.parseDouble(rs.getString("REACT_POWER"));

                    strPrevRph_Act_Current = rs.getString("RPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_ACTIVE_CURRENT")));
                    strPrevYph_Act_Current = rs.getString("YPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_ACTIVE_CURRENT")));
                    strPrevBph_Act_Current = rs.getString("BPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_ACTIVE_CURRENT")));
                    strPrevRph_React_Current = rs.getString("RPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_REACT_CURRENT")));
                    strPrevYph_React_Current = rs.getString("YPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_REACT_CURRENT")));
                    strPrevBph_React_Current = rs.getString("BPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_REACT_CURRENT")));
                    strPrevAvg_Power_Fact = rs.getString("AVG_POWER_FACTOR") == null ? "" : df1.format(Double.parseDouble(rs.getString("AVG_POWER_FACTOR")));
                    strPrevFreq = rs.getString("FREQUENCY") == null ? "" : df1.format(Double.parseDouble(rs.getString("FREQUENCY")));
                    //noOfInter=noOfInter+1;        
                } else {
                    strPrevG3 = rs.getString("g3") == null ? "" : rs.getString("g3");
                    dblPrevRphVol = rs.getString("rph_voltage") == null ? 0 : Double.parseDouble(rs.getString("rph_voltage"));
                    dblPrevYphVol = rs.getString("yph_voltage") == null ? 0 : Double.parseDouble(rs.getString("yph_voltage"));
                    dblPrevBphVol = rs.getString("bph_voltage") == null ? 0 : Double.parseDouble(rs.getString("bph_voltage"));

                    dblPrevRphLineCurr = rs.getString("RPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("RPH_LINE_CURRENT"));
                    dblPrevYphLineCurr = rs.getString("YPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("YPH_LINE_CURRENT"));
                    dblPrevBphLineCurr = rs.getString("BPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("BPH_LINE_CURRENT"));

                    dblPrevRphPF = rs.getString("RPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("RPH_POWER_FACTOR"));
                    dblPrevYphPF = rs.getString("YPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("YPH_POWER_FACTOR"));
                    dblPrevBphPF = rs.getString("BPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("BPH_POWER_FACTOR"));
                    dblPrevFund_Act_Power = rs.getString("FUND_ACT_POWER") == null ? 0 : Double.parseDouble(rs.getString("FUND_ACT_POWER"));
                    dblPrevApp_Power = rs.getString("APPARENT_POWER") == null ? 0 : Double.parseDouble(rs.getString("APPARENT_POWER"));
                    dblPrevReact_Power = rs.getString("REACT_POWER") == null ? 0 : Double.parseDouble(rs.getString("REACT_POWER"));

                    strPrevRph_Act_Current = rs.getString("RPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_ACTIVE_CURRENT")));
                    strPrevYph_Act_Current = rs.getString("YPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_ACTIVE_CURRENT")));
                    strPrevBph_Act_Current = rs.getString("BPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_ACTIVE_CURRENT")));
                    strPrevRph_React_Current = rs.getString("RPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_REACT_CURRENT")));
                    strPrevYph_React_Current = rs.getString("YPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_REACT_CURRENT")));
                    strPrevBph_React_Current = rs.getString("BPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_REACT_CURRENT")));
                    strPrevAvg_Power_Fact = rs.getString("AVG_POWER_FACTOR") == null ? "" : df1.format(Double.parseDouble(rs.getString("AVG_POWER_FACTOR")));
                    strPrevFreq = rs.getString("FREQUENCY") == null ? "" : df1.format(Double.parseDouble(rs.getString("FREQUENCY")));
                    if (noOfInter == 0) {
                        noOfInter = noOfInter + 1;
                    }
                    flagCount = flagCount + 1;
                }
            }
            if (flagCount > 1) {
                noOfInter = noOfInter - 1;
            }
        } catch (Exception e) {
            System.out.println("Error in DAO Count : " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("in DAO Count: " + noOfInter);
        return noOfInter;
    }
    
    public static Map<String,Map<String,String>>  getAllMetersD2DataCount(Connection con, String sdate,String edate,List<String>meterList) throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DecimalFormat df1 = new DecimalFormat("#0.0");
        int noOfInter = 0;
        int flagCount = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Map<String,String>meterD2IntervalMap = new HashMap<String,String>();
        Map<String,Map<String,String>>dateMeterIntervalMap = new HashMap<String,Map<String,String>>();
        String metersStr = "";
        for (String meterSerialNo : meterList) {
            if (meterSerialNo == null || meterSerialNo == "null" || meterSerialNo.length() <= 0 || ("").equals(meterSerialNo)) {
                continue;
            }
            metersStr = metersStr + "'" + meterSerialNo.trim() + "',";
        }
        if(meterList.size()>0){
            metersStr = metersStr.substring(0, metersStr.length() - 1);
        }
        Date startDate = sdf.parse(sdate);
        Date endDate = sdf.parse(edate);
        //System.out.println("startDate.getTime()==>"+startDate.getTime());
        //System.out.println("endDate.getTime()==>"+endDate.getTime());
        
        long diff = (endDate.getTime()-startDate.getTime());
        long diffDays1 = diff / (24 * 60 * 60 * 1000);
        int diffDays = (int) diffDays1;
        //System.out.println("diffDays==>"+diffDays);
        try {
            for(int loop = 0;loop<=diffDays;loop++){
                meterD2IntervalMap = new HashMap<String,String>();
                if(loop!=0){
                    sdate = new Utilities().add_Oneday(sdate + " 00:00:00");
                }
                String sql = "";
                sql = "select tab1.meter_serial_no,count(*) as interval from (select distinct d.meter_serial_no,g2 from d2 d  ";
                if (sdate.trim().length() > 0) {
                    sql = sql + " where g3 like '"+sdate+"%'";
                }
                sql = sql +" and meter_serial_no in ("+metersStr+") group by d.meter_serial_no,g2)tab1 group by meter_serial_no";            
                //System.out.println(sql);
                pstmt = con.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    meterD2IntervalMap.put(rs.getString("meter_serial_no"),rs.getString("interval"));
                }
                dateMeterIntervalMap.put(sdate, meterD2IntervalMap);
            }
            //System.out.println("dateMeterIntervalMap==>"+dateMeterIntervalMap);
        } catch (Exception e) {
            System.out.println("Error in DAO Count : " + e.getMessage());
            e.printStackTrace();
        }
        return dateMeterIntervalMap;
    }
    public static Map<String, D2_Data_Form> getInstantDataFirstRecord(String[] meterSerialNoArr) throws SQLException {
        Map<String, D2_Data_Form> meterD2FormMap = new HashMap<String, D2_Data_Form>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DecimalFormat df1 = new DecimalFormat("#0.000");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        try {
                Date presentDate = new Date();
                String presentYear = sdf.format(presentDate);
                String metersStr = "";    
                for (String meterSerialNo : meterSerialNoArr) {
                    if (meterSerialNo == null || meterSerialNo == "null" || meterSerialNo.length() <= 0 || ("").equals(meterSerialNo)) {
                        continue;
                    }
                    metersStr = metersStr + "'" + meterSerialNo.trim() + "',";
                }
                if(metersStr.length()>0){
                    metersStr = metersStr.substring(0, metersStr.length() - 1);
                }
                con=new utils.DBUtil().getConnection();
                /*String sqlQuery = "select * from d2 where cdfid in (SELECT  max(cdfid) FROM d2 where "+ 
                                " convert(datetime,g3,103)<=convert(datetime,'20-12-2014',103) and meter_serial_no in ("+metersStr+") " +
                                " group by meter_serial_no)" +
                                " and meter_serial_no in ("+metersStr+") order by meter_time_stamp desc";
                */
                /*Map<String,String>meterIDG7Map = new HashMap<String,String>();
                String sql = "select m.meter_serial_no as meter_serial_no,max(g7) as g7 from d1,meter m,feeder f where d1.meter_serial_no=m.meter_serial_no and m.metermake=9 and f.classvoltage=33 and f.feederid=m.feederid  group by m.meter_serial_no";
                pstmt = con.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while(rs.next()){
                    meterIDG7Map.put(rs.getString("meter_serial_no"),"300");
                }*/                
                String sqlQuery = "select a.* from " +
                                    " d2 a inner join " +
                                    " (select max(convert(datetime,g3,103))as maxG3,meter_serial_no as meterNum from d2 where meter_serial_no in ("+metersStr+")   group by meter_serial_no) as b " +
                                    " on  convert(datetime,a.g3,103) = b.maxG3 " +
                                    " where meter_serial_no in ("+metersStr+") and  b.meterNum=a.meter_serial_no";
                pstmt = con.prepareStatement(sqlQuery);
                rs  = pstmt.executeQuery();
                D2_Data_Form form = null;
                //System.out.println("sqlQuery12==>"+sqlQuery);
                String meter_serial_no = "";
                String dblRphVol = "";
                String dblYphVol = "";
                String dblBphVol = "";
                
                String dblFund_Act_Power = "";
                String dblApp_Power = "";
                String dblReact_Power = "";                
                while(rs.next()){
                    form = new D2_Data_Form();
                    meter_serial_no = rs.getString("meter_serial_no").trim();
                    //Double g7Value = (("").equals(meterIDG7Map.get(meter_serial_no))||null==meterIDG7Map.get(meter_serial_no))?null:Double.parseDouble(meterIDG7Map.get(meter_serial_no));
                    System.out.println("adfafasdfas"+rs.getString("g3"));
                    form.setStrG3(rs.getString("g3"));
                    dblRphVol = rs.getString("rph_voltage") == null ? "0.00": Double.parseDouble(rs.getString("rph_voltage"))+"";
                    dblYphVol = rs.getString("yph_voltage") == null ? "0.00" : Double.parseDouble(rs.getString("yph_voltage"))+"";
                    dblBphVol = rs.getString("bph_voltage") == null ? "0.00": Double.parseDouble(rs.getString("bph_voltage"))+"";
                    /*if(null!=g7Value){
                        //System.out.println(dblRphVol+","+dblYphVol+","+dblBphVol+","+g7Value);    
                        dblRphVol =  ((Double.parseDouble(dblRphVol)*g7Value)/100)+""; 
                        dblYphVol =  ((Double.parseDouble(dblYphVol)*g7Value)/100)+""; 
                        dblBphVol =  ((Double.parseDouble(dblBphVol)*g7Value)/100)+""; 
                    }*/
                    form.setStrRphVol(dblRphVol);
                    form.setStrYphVol(dblYphVol);
                    form.setStrBphVol(dblBphVol);

                    form.setStrRphLineCurr(rs.getString("RPH_LINE_CURRENT"));
                    form.setStrYphLineCurr(rs.getString("YPH_LINE_CURRENT"));
                    form.setStrBphLineCurr(rs.getString("BPH_LINE_CURRENT"));

                    form.setStrRphPF(rs.getString("RPH_POWER_FACTOR"));
                    form.setStrYphPF(rs.getString("YPH_POWER_FACTOR"));
                    form.setStrBphPF(rs.getString("BPH_POWER_FACTOR"));
                    form.setStrFund_Act_Power(rs.getString("FUND_ACT_POWER"));
                    form.setStrApp_Power(rs.getString("APPARENT_POWER"));
                    form.setStrReact_Power(rs.getString("REACT_POWER"));

                    form.setStrRph_Act_Current(rs.getString("RPH_ACTIVE_CURRENT"));
                    form.setStrYph_Act_Current(rs.getString("YPH_ACTIVE_CURRENT"));
                    form.setStrBph_Act_Current(rs.getString("BPH_ACTIVE_CURRENT"));
                    form.setStrRph_React_Current(rs.getString("RPH_REACT_CURRENT"));
                    form.setStrYph_React_Current(rs.getString("YPH_REACT_CURRENT"));
                    form.setStrBph_React_Current(rs.getString("BPH_REACT_CURRENT"));
                    form.setStrAvg_Power_Fact(rs.getString("AVG_POWER_FACTOR"));
                    form.setStrFreq(rs.getString("FREQUENCY"));
                    
                    meterD2FormMap.put(rs.getString("meter_serial_no").trim(),form);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            if(null!=con){
                con.close();
            }
        }
        return meterD2FormMap;
    }
    
    /****
     * This Below Method get the Data per day wise
     * 
     * 
     * 
     */
    public static Map getD2DataDayWise(Connection con, String meter_id,String selectedDateStr,int start) throws Exception 
    {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DecimalFormat df1 = new DecimalFormat("#0.0");       
        Map<Integer, Object> d2_data_map = new HashMap<Integer, Object>();
        try {

            int noOfInter = 0;
            double dblRphVol = 0;
            double dblYphVol = 0;
            double dblBphVol = 0;
            double dblRphLineCurr = 0;
            double dblYphLineCurr = 0;
            double dblBphLineCurr = 0;
            double dblRphPF = 0;
            double dblYphPF = 0;
            double dblBphPF = 0;
            double dblFund_Act_Power = 0;
            double dblApp_Power = 0;
            double dblReact_Power = 0;
            String strRph_Act_Current = "";
            String strYph_Act_Current = "";
            String strBph_Act_Current = "";
            String strRph_React_Current = "";
            String strYph_React_Current = "";
            String strBph_React_Current = "";
            String strAvg_Power_Fact = "";
            String strFreq = "";
            String strG3 = "";
            int tempCount = 0;
            int tempCount1 = 0;

            double dblPrevRphVol = 0;
            double dblPrevYphVol = 0;
            double dblPrevBphVol = 0;
            double dblPrevRphLineCurr = 0;
            double dblPrevYphLineCurr = 0;
            double dblPrevBphLineCurr = 0;
            double dblPrevRphPF = 0;
            double dblPrevYphPF = 0;
            double dblPrevBphPF = 0;
            double dblPrevFund_Act_Power = 0;
            double dblPrevApp_Power = 0;
            double dblPrevReact_Power = 0;
            String strPrevRph_Act_Current = "";
            String strPrevYph_Act_Current = "";
            String strPrevBph_Act_Current = "";
            String strPrevRph_React_Current = "";
            String strPrevYph_React_Current = "";
            String strPrevBph_React_Current = "";
            String strPrevAvg_Power_Fact = "";
            String strPrevFreq = "";
            String strPrevG3 = "";
            String sql = "";
            System.out.println("start==>"+start);
            if(0!=start){
                start = start-1;
            }
           /* Double g7Value = null;
            sql = "select distinct(m.meterid) as meterid,g7 as g7 from d1,meter m,feeder f where d1.meter_serial_no=m.meter_serial_no and m.metermake=9 and f.feederid=m.feederid and f.classvoltage=33 and  m.meterid="+meter_id;
            System.out.println("sql==>"+sql);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
              g7Value = 300.0; 
            }*/
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            Date selectedDate = sdf.parse(selectedDateStr);
            selectedDate.setDate(selectedDate.getDate()+start);
            selectedDateStr = sdf1.format(selectedDate);
            sql = "select * from d2 where meter_serial_no='"+meter_id+"'";
            sql = sql + " and  g3 like '"+selectedDateStr+"%'";            
            sql = sql + " order by STR_TO_DATE(g3,'%d-%m-%Y %H:%i:%s')";
            System.out.println(sql);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) 
            {
                strG3 = rs.getString("g3") == null ? "" : rs.getString("g3");
                dblRphVol = rs.getString("rph_voltage") == null ? 0 : Double.parseDouble(rs.getString("rph_voltage"));
                dblYphVol = rs.getString("yph_voltage") == null ? 0 : Double.parseDouble(rs.getString("yph_voltage"));
                dblBphVol = rs.getString("bph_voltage") == null ? 0 : Double.parseDouble(rs.getString("bph_voltage"));

                dblRphLineCurr = rs.getString("RPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("RPH_LINE_CURRENT"));
                dblYphLineCurr = rs.getString("YPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("YPH_LINE_CURRENT"));
                dblBphLineCurr = rs.getString("BPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("BPH_LINE_CURRENT"));

                dblRphPF = rs.getString("RPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("RPH_POWER_FACTOR"));
                dblYphPF = rs.getString("YPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("YPH_POWER_FACTOR"));
                dblBphPF = rs.getString("BPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("BPH_POWER_FACTOR"));
                dblFund_Act_Power = rs.getString("FUND_ACT_POWER") == null ? 0 : Double.parseDouble(rs.getString("FUND_ACT_POWER"));
                dblApp_Power = rs.getString("APPARENT_POWER") == null ? 0 : Double.parseDouble(rs.getString("APPARENT_POWER"));
                dblReact_Power = rs.getString("REACT_POWER") == null ? 0 : Double.parseDouble(rs.getString("REACT_POWER"));

                strRph_Act_Current = rs.getString("RPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_ACTIVE_CURRENT")));
                strYph_Act_Current = rs.getString("YPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_ACTIVE_CURRENT")));
                strBph_Act_Current = rs.getString("BPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_ACTIVE_CURRENT")));
                strRph_React_Current = rs.getString("RPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_REACT_CURRENT")));
                strYph_React_Current = rs.getString("YPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_REACT_CURRENT")));
                strBph_React_Current = rs.getString("BPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_REACT_CURRENT")));
                strAvg_Power_Fact = rs.getString("AVG_POWER_FACTOR") == null ? "" : df1.format(Double.parseDouble(rs.getString("AVG_POWER_FACTOR")));
                strFreq = rs.getString("FREQUENCY") == null ? "" : df1.format(Double.parseDouble(rs.getString("FREQUENCY")));
                /*if(null!=g7Value){
                    //System.out.println(dblRphVol+","+dblYphVol+","+dblBphVol+","+g7Value); 
                    //System.out.println(dblFund_Act_Power+","+dblApp_Power+","+dblReact_Power+","+g7Value); 
                    dblRphVol =  ((dblRphVol*g7Value)/100); 
                    dblYphVol =  ((dblYphVol*g7Value)/100); 
                    dblBphVol =  ((dblBphVol*g7Value)/100); 
                    
                    //dblFund_Act_Power =  ((dblFund_Act_Power*g7Value)); 
                    //dblApp_Power =  ((dblApp_Power*g7Value)); 
                    //dblReact_Power =  ((dblReact_Power*g7Value)); 
                }*/                
                D2_Data_Form history = new D2_Data_Form();
                history.setStrG3(strG3);
                history.setStrRphVol(dblRphVol+"");
                history.setStrYphVol(dblYphVol + "");
                history.setStrBphVol(dblBphVol + "");
                history.setStrRphLineCurr(dblRphLineCurr + "");
                history.setStrYphLineCurr(dblYphLineCurr + "");
                history.setStrBphLineCurr(dblBphLineCurr + "");
                history.setStrRphPF(dblRphPF + "");
                history.setStrYphPF(dblYphPF + "");
                history.setStrBphPF(dblBphPF + "");
                history.setStrFund_Act_Power(dblFund_Act_Power + "");
                history.setStrApp_Power(dblApp_Power + "");
                history.setStrReact_Power(dblReact_Power + "");
                history.setStrRph_Act_Current(strRph_Act_Current);
                history.setStrYph_Act_Current(strYph_Act_Current);
                history.setStrBph_Act_Current(strBph_Act_Current);
                history.setStrRph_React_Current(strRph_React_Current);
                history.setStrYph_React_Current(strYph_React_Current);
                history.setStrBph_React_Current(strBph_React_Current);
                history.setStrAvg_Power_Fact(strAvg_Power_Fact);
                history.setStrFreq(strFreq);                    
                noOfInter = noOfInter + 1;                    
                tempCount1 = tempCount1 + 1;
                d2_data_map.put(noOfInter - 1, history);
                //System.out.println(" tempCount1 : " + tempCount1);
                System.out.println("strRph_Act_Current==>"+strRph_Act_Current);
            }           
            //System.out.println(" tempCount : " + tempCount);
            //System.out.println(" tempCount1 : " + tempCount1);
        } catch (Exception e) {
            System.out.println("Error in DAO : " + e.getMessage());
            e.printStackTrace();
        }
        //System.out.println("in DAO : " + d2_data_map.size());
        return d2_data_map;
    }
    
    public static Map getD2DataMonth(Connection con, String meter_id,String month,String year,int start) throws Exception 
    {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DecimalFormat df1 = new DecimalFormat("#0.0");       
        Map<Integer, Object> d2_data_map = new HashMap<Integer, Object>();
        String db="d2_"+month+year.substring(2);
        try {

            int noOfInter = 0;
            double dblRphVol = 0;
            double dblYphVol = 0;
            double dblBphVol = 0;
            double dblRphLineCurr = 0;
            double dblYphLineCurr = 0;
            double dblBphLineCurr = 0;
            double dblRphPF = 0;
            double dblYphPF = 0;
            double dblBphPF = 0;
            double dblFund_Act_Power = 0;
            double dblApp_Power = 0;
            double dblReact_Power = 0;
            String strRph_Act_Current = "";
            String strYph_Act_Current = "";
            String strBph_Act_Current = "";
            String strRph_React_Current = "";
            String strYph_React_Current = "";
            String strBph_React_Current = "";
            String strAvg_Power_Fact = "";
            String strFreq = "";
            String strG3 = "";
            int tempCount = 0;
            int tempCount1 = 0;

            double dblPrevRphVol = 0;
            double dblPrevYphVol = 0;
            double dblPrevBphVol = 0;
            double dblPrevRphLineCurr = 0;
            double dblPrevYphLineCurr = 0;
            double dblPrevBphLineCurr = 0;
            double dblPrevRphPF = 0;
            double dblPrevYphPF = 0;
            double dblPrevBphPF = 0;
            double dblPrevFund_Act_Power = 0;
            double dblPrevApp_Power = 0;
            double dblPrevReact_Power = 0;
            String strPrevRph_Act_Current = "";
            String strPrevYph_Act_Current = "";
            String strPrevBph_Act_Current = "";
            String strPrevRph_React_Current = "";
            String strPrevYph_React_Current = "";
            String strPrevBph_React_Current = "";
            String strPrevAvg_Power_Fact = "";
            String strPrevFreq = "";
            String strPrevG3 = "";
            String sql = "";
            System.out.println("start==>"+start);
            if(0!=start)
            {
                start = start-1;
            }
           /* Double g7Value = null;
            sql = "select distinct(m.meterid) as meterid,g7 as g7 from d1,meter m,feeder f where d1.meter_serial_no=m.meter_serial_no and m.metermake=9 and f.feederid=m.feederid and f.classvoltage=33 and  m.meterid="+meter_id;
            System.out.println("sql==>"+sql);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
              g7Value = 300.0; 
            }*/
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                       
            sql = "select * from "+db +"where meter_serial_no='"+meter_id+"'";
            sql = sql + " and  g3 like '"+month+"-"+year+"%'";            
            sql = sql + " order by STR_TO_DATE(g3,'%d-%m-%Y %H:%i:%s')";
            System.out.println(sql);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) 
            {
                strG3 = rs.getString("g3") == null ? "" : rs.getString("g3");
                dblRphVol = rs.getString("rph_voltage") == null ? 0 : Double.parseDouble(rs.getString("rph_voltage"));
                dblYphVol = rs.getString("yph_voltage") == null ? 0 : Double.parseDouble(rs.getString("yph_voltage"));
                dblBphVol = rs.getString("bph_voltage") == null ? 0 : Double.parseDouble(rs.getString("bph_voltage"));

                dblRphLineCurr = rs.getString("RPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("RPH_LINE_CURRENT"));
                dblYphLineCurr = rs.getString("YPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("YPH_LINE_CURRENT"));
                dblBphLineCurr = rs.getString("BPH_LINE_CURRENT") == null ? 0 : Double.parseDouble(rs.getString("BPH_LINE_CURRENT"));

                dblRphPF = rs.getString("RPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("RPH_POWER_FACTOR"));
                dblYphPF = rs.getString("YPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("YPH_POWER_FACTOR"));
                dblBphPF = rs.getString("BPH_POWER_FACTOR") == null ? 0 : Double.parseDouble(rs.getString("BPH_POWER_FACTOR"));
                dblFund_Act_Power = rs.getString("FUND_ACT_POWER") == null ? 0 : Double.parseDouble(rs.getString("FUND_ACT_POWER"));
                dblApp_Power = rs.getString("APPARENT_POWER") == null ? 0 : Double.parseDouble(rs.getString("APPARENT_POWER"));
                dblReact_Power = rs.getString("REACT_POWER") == null ? 0 : Double.parseDouble(rs.getString("REACT_POWER"));

                strRph_Act_Current = rs.getString("RPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_ACTIVE_CURRENT")));
                strYph_Act_Current = rs.getString("YPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_ACTIVE_CURRENT")));
                strBph_Act_Current = rs.getString("BPH_ACTIVE_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_ACTIVE_CURRENT")));
                strRph_React_Current = rs.getString("RPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("RPH_REACT_CURRENT")));
                strYph_React_Current = rs.getString("YPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("YPH_REACT_CURRENT")));
                strBph_React_Current = rs.getString("BPH_REACT_CURRENT") == null ? "NA" : df1.format(Double.parseDouble(rs.getString("BPH_REACT_CURRENT")));
                strAvg_Power_Fact = rs.getString("AVG_POWER_FACTOR") == null ? "" : df1.format(Double.parseDouble(rs.getString("AVG_POWER_FACTOR")));
                strFreq = rs.getString("FREQUENCY") == null ? "" : df1.format(Double.parseDouble(rs.getString("FREQUENCY")));
         
                D2_Data_Form history = new D2_Data_Form();
                history.setStrG3(strG3);
                history.setStrRphVol(dblRphVol+"");
                history.setStrYphVol(dblYphVol + "");
                history.setStrBphVol(dblBphVol + "");
                history.setStrRphLineCurr(dblRphLineCurr + "");
                history.setStrYphLineCurr(dblYphLineCurr + "");
                history.setStrBphLineCurr(dblBphLineCurr + "");
                history.setStrRphPF(dblRphPF + "");
                history.setStrYphPF(dblYphPF + "");
                history.setStrBphPF(dblBphPF + "");
                history.setStrFund_Act_Power(dblFund_Act_Power + "");
                history.setStrApp_Power(dblApp_Power + "");
                history.setStrReact_Power(dblReact_Power + "");
                history.setStrRph_Act_Current(strRph_Act_Current);
                history.setStrYph_Act_Current(strYph_Act_Current);
                history.setStrBph_Act_Current(strBph_Act_Current);
                history.setStrRph_React_Current(strRph_React_Current);
                history.setStrYph_React_Current(strYph_React_Current);
                history.setStrBph_React_Current(strBph_React_Current);
                history.setStrAvg_Power_Fact(strAvg_Power_Fact);
                history.setStrFreq(strFreq);                    
                noOfInter = noOfInter + 1;                    
                tempCount1 = tempCount1 + 1;
                d2_data_map.put(noOfInter - 1, history);
                //System.out.println(" tempCount1 : " + tempCount1);
                System.out.println("strRph_Act_Current==>"+strRph_Act_Current);
            }           
            //System.out.println(" tempCount : " + tempCount);
            //System.out.println(" tempCount1 : " + tempCount1);
        } catch (Exception e) {
            System.out.println("Error in DAO : " + e.getMessage());
            e.printStackTrace();
        }
        //System.out.println("in DAO : " + d2_data_map.size());
        return d2_data_map;
    }
    
}



