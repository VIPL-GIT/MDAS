/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActionBeans;

import FormBeans.OutageBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author RamGopal
 */
public class OutageGenStatus {
    public Map<String,OutageBean> getDateWiseStatus(String discom){
        OutageBean obean = null;
        Map<String,OutageBean> outageMap = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            outageMap = new HashMap<String,OutageBean>();
            String query = "select meter_serial_no,modem_serial_no,substationname,feedername,alert_timestamp from masterdata "
                    + "where discom_id='"+discom+"' and poweroff='YES'";
            System.out.println("query 111 = "+query);
            con=new in.verticross.db.DBUtil().getConnection();            
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                obean = new OutageBean();
                obean.setMeterSrNo(rs.getString("meter_serial_no"));
                obean.setModemSrNo(rs.getString("modem_serial_no"));
                obean.setSSName(rs.getString("substationname"));
                obean.setFeederName(rs.getString("feedername"));
                obean.setAlertTimestamp(rs.getString("alert_timestamp"));
                outageMap.put(rs.getString("meter_serial_no"), obean);
            }//while
            con.close();
            if(outageMap.size()==0)
                return null;
        }catch(Exception e){
            e.printStackTrace();
        }
        return outageMap;
    }
}
