/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ActionBeans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author RAMGOPALREDDY
 */
public class GenStatus {
    public Map<String,RDBean> getAlertsStatus(String date){
        Connection con = null;
        ResultSet rs = null;
        String query = "";
        Statement stmt = null;
        RDBean rdBean = null;
        Map<String,RDBean> dataBeansMap = new LinkedHashMap<String,RDBean>();
        try{
            con=new in.verticross.db.DBUtil().getConnection();
            //query = "select * from modem_alerts where modem_id like 'PVVNL%' order by log_time_stamp desc limit 10";
            query = "select * from modem_alerts  order by log_time_stamp desc limit 10";
            System.out.println("Alerts Query = "+query);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                rdBean = null;
                rdBean = new RDBean();
                rdBean.setMeterSrNo(rs.getString("meter_id"));
                rdBean.setModemSrNo(rs.getString("modem_id"));
                rdBean.setAlertType(rs.getString("alert_code"));
                rdBean.setAlertDescription(rs.getString("ALERT_DESC"));
                rdBean.setAlertTimestamp(rs.getString("ALERT_GENERATED_DATE"));
                rdBean.setLogTimestamp(rs.getString("LOG_TIME_STAMP"));
                dataBeansMap.put(rs.getString("modem_id")+"_"+rs.getString("LOG_TIME_STAMP"),rdBean);
            }//while
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return dataBeansMap;
    }
//    public static void main(String[] args) {
    public Map<String,RDBean> getDateWiseStatus(String date){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "";
        RDBean rdBean = null;
        Map<String,RDBean> dataBeansMap = new LinkedHashMap<String,RDBean>();
        try{
//            con = new DBManager1().getPostGresDBConnection();
            con=new in.verticross.db.DBUtil().getConnection();
//         String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());             
//         for(int x=0;x<7;x++){
             query = "SELECT * FROM comm_status where comm_date='"+date+"' order by meter_serial_no";
             System.out.println("query for date wise details = "+query);
             stmt = con.createStatement();
             rs = stmt.executeQuery(query);
           while(rs.next()){  
             rdBean = new RDBean();
//               System.out.println("rs.getString(\"meter_serial_no\") = "+rs.getString("meter_serial_no"));
             rdBean.setMeterSrNo(rs.getString("meter_serial_no"));
             rdBean.setCdCount(Integer.parseInt(rs.getString("cd")));
//               System.out.println("rs.getString(\"cd\") = "+rs.getString("cd"));
             rdBean.setIpCount(Integer.parseInt(rs.getString("ip")));
             rdBean.setMlCount(Integer.parseInt(rs.getString("ml")));
             rdBean.setNtCount(Integer.parseInt(rs.getString("nt")));
             rdBean.setOdCount(Integer.parseInt(rs.getString("od")));
             rdBean.setDdCount(Integer.parseInt(rs.getString("dd")));
             rdBean.setCrcFailCount(Integer.parseInt(rs.getString("crcfail")));
              // System.out.println("rs.getString(\"cdf\") = "+rs.getString("cdf"));
             if(rs.getString("cdf")==null)
              rdBean.setCdfCount(Integer.parseInt("0"));   
             else    
              rdBean.setCdfCount(Integer.parseInt(rs.getString("cdf")));
             
             rdBean.setCdfCounts(rs.getString("cdfcount"));
             rdBean.setMinSize(rs.getString("cdfmin"));
             rdBean.setMaxSize(rs.getString("cdfMax"));
             rdBean.setCiaShared(rs.getString("ciashared"));
             
             dataBeansMap.put(rs.getString("meter_serial_no"),rdBean);
           }
//             System.out.println(date+"\t"+recordsArrList.size());
//           date = new Utilities().substract_1_Day(date+" 00:00:00");            
//         }//for
           con.close();
           if(dataBeansMap.size()==0)
               return null;
        }catch(Exception e){
            e.printStackTrace();
        }
            return dataBeansMap;
    }
}
