/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActionBeans;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;

import utils.DBUtil;

public class Client_getMobileNo {

    public String getMobileNo(String getMeterId) {

        String meterId = getMeterId;
        String mobileNo = "";
        String meterSlno = "";
        String modemSlno = "";
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DBUtil().getConnection();	
            System.out.println("getMeterId = "+getMeterId);
//            pstmt = conn.prepareStatement("select mtr.METER_SERIAL_NO, mtr.MODEM_SERIAL_NO, mdm.MOBILE_NO, mdm.MODEM_ID from vtxmdas.meter mtr, vtxmdas.modem mdm where mtr.MODEM_SERIAL_NO = mdm.MODEM_SERIAL_NO and mtr.METERID =?");
            pstmt = conn.prepareStatement("select METER_SERIAL_NO,MODEM_SERIAL_NO,MOBILE_NO from meter where meterid =?");
            pstmt.setString(1, meterId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                meterSlno = rs.getString("METER_SERIAL_NO");
                System.out.println("meterSlno = " + meterSlno);
                modemSlno = rs.getString("MODEM_SERIAL_NO");
                mobileNo = rs.getString("MOBILE_NO");
//                System.out.println("mobileNo = " + mobileNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return mobileNo+";"+modemSlno;
    return mobileNo+","+modemSlno+","+meterSlno;
    }

     public String getIPAddr(String getMeterId) {

        String meterId = getMeterId;
        String ipaddress = "";
        String meterSlno = "";
        String modemSlno = "";
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DBUtil().getConnection();
           

            pstmt = conn.prepareStatement("select METER_SERIAL_NO,MODEM_SERIAL_NO,ipaddress from meter where meter_serial_no =?");
            pstmt.setString(1, meterId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                meterSlno = rs.getString("METER_SERIAL_NO");
                System.out.println("meterSlno = " + meterSlno);
                modemSlno = rs.getString("MODEM_SERIAL_NO");
                ipaddress = rs.getString("ipaddress");
//                System.out.println("mobileNo = " + mobileNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return mobileNo+";"+modemSlno;
    return ipaddress+","+modemSlno+","+meterSlno;
    }
    public void onDemandRequestToDB_VIPL(String meterSrNo, String modemSrNo,String mobileNo,String command,String reqid) {

        Connection con = null;
        PreparedStatement pstmt = null;
        DateFormat df = null;
//        Date dateTime = null;

        try {           

            con = new DBUtil().getConnection();

            String OracleDBQuery = "INSERT INTO sms_config(METER_ID,MODEM_SERIAL_NO,REQ_TYPE,REQ_TIME_STAMP,SMS_STATUS,mobile_no,req_id) VALUES(?,?,?,now(),?,?,?)";

            pstmt = con.prepareStatement(OracleDBQuery);

            pstmt.setString(1, meterSrNo);
            pstmt.setString(2, modemSrNo);
            pstmt.setString(3, command);            
            pstmt.setString(4, "SERVER");
            pstmt.setString(5, mobileNo);
            pstmt.setString(6, reqid);

            if (pstmt.executeUpdate() != 0) {
                System.out.println("Requested Command INSERTED SUCCESSFULLY.............!");
            }
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Client_getMobileNo cgm = new Client_getMobileNo();
        cgm.getMobileNo("XB470236");
    }
}
