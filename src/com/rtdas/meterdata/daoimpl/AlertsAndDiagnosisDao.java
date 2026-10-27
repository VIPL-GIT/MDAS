package com.rtdas.meterdata.daoimpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rtdas.meterdata.beans.*;

public class AlertsAndDiagnosisDao {

	
	public HashMap<Integer,String> getEvents(){
		Connection con = null;
	    Statement stmt=null;
	    ResultSet rs = null;
	    HashMap<Integer,String> events=new HashMap<Integer,String>();
	    try{                              
	        String query = "select * from events";
	        stmt = con.createStatement();
	        rs = stmt.executeQuery(query);
	        while(rs.next()){
	        	events.put(rs.getInt(1),rs.getString(2));
	        }
    	 }catch (Exception e) {
			e.printStackTrace();
		}
    	 return events;
	    }
	
	public ArrayList<Auto_reset_bean> getAutoResetBillingsUPCL_new(String selectedMonth,String type,String locTypeAccess,List<String> meters){
    Connection con = null;
    Statement stmt=null;
    ResultSet rs = null;
    Auto_reset_bean bb = null;
    ArrayList<Auto_reset_bean> arrList = new ArrayList<Auto_reset_bean>();
    HierarchyBean hBean = null;
    try{                              
        String query = "select * from auto_reset_energies_cesc where billing_timestamp like '"+selectedMonth+"%' order by meter_serial_no ";
        System.out.println("sql --> "+query);
        con = new utils.DBUtil().getConnection();
//        con = new DBUtil().getMysqlDBConnection();
        List<String> MeterListType = new ArrayList();
        if(!meters.isEmpty()) MeterListType.addAll(meters);
        else
          getHeirarchyListByType_new(con,type);
        
        System.out.println("masterMap = "+MeterListType.size());
        
       
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        while(rs.next()){
            if(MeterListType.contains(rs.getString("meter_serial_no"))){
                bb = new Auto_reset_bean();
                bb.setMeter_serial_no(rs.getString("meter_serial_no"));
            
                hBean = null;
//                hBean = (HierarchyBean)masterMap.get(rs.getString("meter_serial_no"));
//                bb.setDiscom(hBean.getDiscom());
//                bb.setCircle(hBean.getCircle());
//                bb.setDivision(hBean.getDivision());
//                bb.setSubdivision(hBean.getSubdivision());
//                bb.setTown(hBean.getTown());
//                bb.setSection(hBean.getSection());
//                bb.setConsumerName(hBean.getConsumerName());
                bb.setMeter_serial_no(rs.getString("meter_serial_no"));
//                bb.setMetertype(hBean.getMetertype());
                
                bb.setCONSUMERNO(rs.getString("CONSUMERNO"));
                bb.setDatetime(rs.getString("datetime"));
                bb.setC1KWH_I(rs.getString("C1KWH_I"));
                bb.setC2KWH_I(rs.getString("C2KWH_I"));
                bb.setC3KWH_I(rs.getString("C3KWH_I"));
                bb.setC4KWH_I(rs.getString("C4KWH_I"));
                bb.setC5KWH_I(rs.getString("C5KWH_I"));
                bb.setC6KWH_I(rs.getString("C6KWH_I"));
                bb.setC7KWH_I(rs.getString("C7KWH_I"));
                bb.setC8KWH_I(rs.getString("C8KWH_I"));
                bb.setC1KWH_E(rs.getString("C1KWH_E"));
                bb.setC2KWH_E(rs.getString("C2KWH_E"));
                bb.setC3KWH_E(rs.getString("C3KWH_E"));
                bb.setC4KWH_E(rs.getString("C4KWH_E"));
                bb.setC5KWH_E(rs.getString("C5KWH_E"));
                bb.setC6KWH_E(rs.getString("C6KWH_E"));
                bb.setC7KWH_E(rs.getString("C7KWH_E"));
                bb.setC8KWH_E(rs.getString("C8KWH_E"));
                bb.setC1KWH_ABS(rs.getString("C1KWH_ABS"));
                bb.setC2KWH_ABS(rs.getString("C2KWH_ABS"));
                bb.setC3KWH_ABS(rs.getString("C3KWH_ABS"));
                bb.setC4KWH_ABS(rs.getString("C4KWH_ABS"));
                bb.setC5KWH_ABS(rs.getString("C5KWH_ABS"));
                bb.setC6KWH_ABS(rs.getString("C6KWH_ABS"));
                bb.setC7KWH_ABS(rs.getString("C7KWH_ABS"));
                bb.setC8KWH_ABS(rs.getString("C8KWH_ABS"));
                bb.setC1KVAH_I(rs.getString("C1KVAH_I"));
                bb.setC2KVAH_I(rs.getString("C2KVAH_I"));
                bb.setC3KVAH_I(rs.getString("C3KVAH_I"));
                bb.setC4KVAH_I(rs.getString("C4KVAH_I"));
                bb.setC5KVAH_I(rs.getString("C5KVAH_I"));
                bb.setC6KVAH_I(rs.getString("C6KVAH_I"));
                bb.setC7KVAH_I(rs.getString("C7KVAH_I"));
                bb.setC8KVAH_I(rs.getString("C8KVAH_I"));
                bb.setC1KVAH_E(rs.getString("C1KVAH_E"));
                bb.setC2KVAH_E(rs.getString("C2KVAH_E"));
                bb.setC3KVAH_E(rs.getString("C3KVAH_E"));
                bb.setC4KVAH_E(rs.getString("C4KVAH_E"));
                bb.setC5KVAH_E(rs.getString("C5KVAH_E"));
                bb.setC6KVAH_E(rs.getString("C6KVAH_E"));
                bb.setC7KVAH_E(rs.getString("C7KVAH_E"));
                bb.setC8KVAH_E(rs.getString("C8KVAH_E"));
                bb.setC1KVAH_ABS(rs.getString("C1KVAH_ABS"));
                bb.setC2KVAH_ABS(rs.getString("C2KVAH_ABS"));
                bb.setC3KVAH_ABS(rs.getString("C3KVAH_ABS"));
                bb.setC4KVAH_ABS(rs.getString("C4KVAH_ABS"));
                bb.setC5KVAH_ABS(rs.getString("C5KVAH_ABS"));
                bb.setC6KVAH_ABS(rs.getString("C6KVAH_ABS"));
                bb.setC7KVAH_ABS(rs.getString("C7KVAH_ABS"));
                bb.setC8KVAH_ABS(rs.getString("C8KVAH_ABS"));
                bb.setCMDKW_I(rs.getString("CMDKW_I"));
                bb.setCMDKW_E(rs.getString("CMDKW_E"));
                bb.setCMDKW_ABS(rs.getString("CMDKW_ABS"));
                bb.setCMDKVA_I(rs.getString("CMDKVA_I"));
                bb.setCMDKVA_E(rs.getString("CMDKVA_E"));
                bb.setCMDKVA_ABS(rs.getString("CMDKVA_ABS"));
                bb.setMDKW_I(rs.getString("MDKW_I"));
                bb.setMDKW_E(rs.getString("MDKW_E"));
                bb.setMDKW_ABS(rs.getString("MDKW_ABS"));
                bb.setMDKVA_I(rs.getString("MDKVA_I"));
                bb.setMDKVA_E(rs.getString("MDKVA_E"));
                bb.setMDKVA_ABS(rs.getString("MDKVA_ABS"));
                bb.setKWH_I(rs.getString("KWH_I"));
                bb.setKWH_E(rs.getString("KWH_E"));
                bb.setKWH_ABS(rs.getString("KWH_ABS"));
                bb.setKVAH_I(rs.getString("KVAH_I"));
                bb.setKVAH_E(rs.getString("KVAH_E"));
                bb.setKVAH_ABS(rs.getString("KVAH_ABS"));
                bb.setPF(rs.getString("PF"));
                bb.setCMDKW_I_CURRENT(rs.getString("CMDKW_I_CURRENT"));
                bb.setCMDKW_E_CURRENT(rs.getString("CMDKW_E_CURRENT"));
                bb.setCMDKW_ABS_CURRENT(rs.getString("CMDKW_ABS_CURRENT"));
                bb.setCMDKVA_I_CURRENT(rs.getString("CMDKVA_I_CURRENT"));
                bb.setCMDKVA_E_CURRENT(rs.getString("CMDKVA_E_CURRENT"));
                bb.setCMDKVA_ABS_CURRENT(rs.getString("CMDKVA_ABS_CURRENT"));
            arrList.add(bb);
            }else{
                //System.out.println(rs.getString("meter_serial_no"));
            }
            }//while
        System.out.println("Total Records in ArrayList = "+arrList.size());
        con.close();
    }catch(Exception e){
        e.printStackTrace();
    }
    return arrList;
}

	
	public List<String> getHeirarchyListByType_new(Connection con,String type){
         
          List<String> meterlist=new ArrayList<String>();
          String query=null;
          if(Integer.parseInt(type)==0){
        	  query="select meter_serial_no from meter where meterlocation in (6,7,8,9)";
          }
          else{
          query="select meter_serial_no from meter where meterlocation='"+type+"'";
          }
          
          System.out.println("sql meter -- "+query);
          try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
            	meterlist.add(rs.getString(1));	 
            }
          }catch(Exception e){
        	  e.printStackTrace();
          }
              return meterlist;
    }
	
	
    public static List<ModemAlertsBean> getDiagList(String meter,String date1) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "";        
        List<ModemAlertsBean> DiagList = null;
        try {
            con = utils.DBUtil.getConnection();
            //System.out.println("con --> "+con);
            DiagList = new ArrayList<ModemAlertsBean>();
            sqlQuery = "SELECT * FROM device_alerts WHERE meter_serial_no=(select meter_serial_no from meter where meterid='"+meter+"') AND alert_timestamp LIKE '%"+date1+"%'";
            System.out.println("dsql "+sqlQuery);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            ModemAlertsBean bean = null;

            while (rs.next()) {
                bean = new ModemAlertsBean();
                bean.setMeter_serial_no(rs.getString("meter_serial_no"));
                bean.setModem_serial_no(rs.getString("modem_serial_no"));
                bean.setAlert_type(rs.getString("alert_type"));
                bean.setAlert_timestamp(rs.getString("alert_timestamp"));
                bean.setFv(rs.getString("fv"));
                bean.setMeter_make(rs.getString("meter_make"));
                bean.setImeino(rs.getString("imeino"));
                bean.setIpaddress(rs.getString("ipaddress"));              
             
              
                
                DiagList.add(bean);                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            utils.DBUtil.connectionClose(con, stmt, rs);
        }
        return DiagList;
    }
	

    
    
    
    public static List<ModemDiagnosisBean> getModemDiagnosisList(String meter,String date1) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "";        
        List<ModemDiagnosisBean> DiagList = null;
        try {
            con = utils.DBUtil.getConnection();
            System.out.println("con --> "+con);
            DiagList = new ArrayList<ModemDiagnosisBean>();
            sqlQuery = "SELECT * FROM device_diagnosis WHERE meter_serial_no=(select meter_serial_no from meter where meterid='"+meter+"') AND diag_timestamp LIKE '%"+date1+"%'";
            System.out.println("dsql "+sqlQuery);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            ModemDiagnosisBean bean = null;

            while (rs.next()) {
            	bean = new ModemDiagnosisBean();               
            	bean.setMeter_serial_no(rs.getString("meter_serial_no"));
            	bean.setModem_serial_no(rs.getString("modem_serial_no"));
    			bean.setDiag_timestamp(rs.getString("diag_timestamp"));
    			bean.setFv(rs.getString("fv"));
    			bean.setBattery(rs.getString("battery"));
    			bean.setGsm_signal(rs.getString("gsm_signal"));
    			bean.setSignal_quality_error(rs.getString("signal_quality_error"));
    			bean.setTemperature(rs.getString("temperature"));
    			bean.setSimno(rs.getString("simno"));
    			bean.setImeino(rs.getString("imeino"));
    			bean.setCablestatus(rs.getString("cablestatus"));
    			bean.setIpaddress(rs.getString("ipaddress"));
    			bean.setReadtime(rs.getString("readtime"));
    			bean.setTransmittime(rs.getString("transmittime"));
    			bean.setMetermake(rs.getString("metermake"));
 	

              
                DiagList.add(bean);                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            utils.DBUtil.connectionClose(con, stmt, rs);
        }
        return DiagList;
    }

    
    

    public static List<AutoResetEnergies> getEnergyList(String meter,String date1) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "";        
        String meter_serial_no="";
        List<AutoResetEnergies> DiagList = null;
        try {
            con = utils.DBUtil.getConnection();
            System.out.println("con --> "+con);
            DiagList = new ArrayList<AutoResetEnergies>();

            
           String Query = "SELECT * FROM meter WHERE meter_id='"+meter+"'";
            System.out.println("sql "+sqlQuery);
            stmt = con.createStatement();
            rs = stmt.executeQuery(Query);
            while (rs.next()) {
            	meter_serial_no=rs.getString("meter_sl_no");
            }
            
            
            sqlQuery = "SELECT * FROM auto_reset_energies WHERE meter_serial_no='"+meter_serial_no+"' AND billing_timestamp LIKE '%"+date1+"%'";
            System.out.println("sql "+sqlQuery);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            AutoResetEnergies bean = null;

            while (rs.next()) {
            	
            	bean = new AutoResetEnergies();  
                bean.setRecordid(rs.getInt("recordid"));
                bean.setMeter_serial_no(rs.getString("meter_serial_no"));
                bean.setBilling_timestamp(rs.getString("billing_timestamp"));
                bean.setKWH(rs.getString("KWH_I"));
                bean.setKVAH(rs.getString("kVAH_I"));
                bean.setKVARH_Lag(rs.getString("kVARH_Lag"));
                bean.setKVARH_Lead(rs.getString("kVARH_Lead"));
                bean.setPF(rs.getString("pF"));

              
                DiagList.add(bean);                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            utils.DBUtil.connectionClose(con, stmt, rs);
        }
        return DiagList;
    }

    public static List<ModemDiagnosisBean> getModemDiagnosisLatest() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "";        
        List<ModemDiagnosisBean> DiagList = null;
        try {
            con = utils.DBUtil.getConnection();
            
            String meterid="";
            String sqlmeters="select meter_serial_no from meter";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlmeters);
            while(rs.next()){
            	meterid="'"+rs.getString(1)+"',"+meterid;
                }
            meterid=meterid.substring(0,meterid.length() - 1);
            
            //System.out.println("meterid --> "+meterid);
            
            //System.out.println("con --> "+con);
            DiagList = new ArrayList<ModemDiagnosisBean>();
           // sqlQuery = "SELECT * FROM device_diagnosis_summary WHERE meter_serial_no in ("+meterid+")  order by diag_timestamp desc";
            sqlQuery = "SELECT * FROM device_diagnosis_summary order by diag_timestamp desc";
            //System.out.println("dsql "+sqlQuery);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            ModemDiagnosisBean bean = null;

            while (rs.next()) {
            	bean = new ModemDiagnosisBean();               
            	bean.setMeter_serial_no(rs.getString("meter_serial_no"));
            	bean.setModem_serial_no(rs.getString("modem_serial_no"));
    			bean.setDiag_timestamp(rs.getString("diag_timestamp"));
    			bean.setFv(rs.getString("fv"));
    			bean.setBattery(rs.getString("battery"));
    			bean.setGsm_signal(rs.getString("gsm_signal"));
    			bean.setSignal_quality_error(rs.getString("signal_quality_error"));
    			bean.setTemperature(rs.getString("temperature"));
    			bean.setSimno(rs.getString("simno"));
    			bean.setImeino(rs.getString("imeino"));
    			bean.setCablestatus(rs.getString("cablestatus"));
    			bean.setIpaddress(rs.getString("ipaddress"));
    			bean.setReadtime(rs.getString("readtime"));
    			bean.setTransmittime(rs.getString("transmittime"));
    			bean.setMetermake(rs.getString("metermake")); 	

              
                DiagList.add(bean);                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            utils.DBUtil.connectionClose(con, stmt, rs);
        }
        return DiagList;
    }




 public static List<ModemAlertsBean> getModemLatestAlerts() throws SQLException {
	        Connection con = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        String sqlQuery = "";        
	        List<ModemAlertsBean> DiagList = null;
	        try {
	            con = utils.DBUtil.getConnection();
	            String meterid="";
	            String sqlmeters="select meter_serial_no from meter";
	            stmt = con.createStatement();
	            rs = stmt.executeQuery(sqlmeters);
	            while(rs.next()){
	            	meterid="'"+rs.getString(1)+"',"+meterid;
	                }
	            meterid=meterid.substring(0,meterid.length() - 1);
	            
	            //System.out.println("meterid --> "+meterid);
	            DiagList = new ArrayList<ModemAlertsBean>();
	           // sqlQuery = "SELECT * FROM device_alerts_summary WHERE meter_serial_no in ("+meterid+") ORDER BY alert_timestamp DESC";
	            sqlQuery = "SELECT * FROM device_alerts_summary ORDER BY alert_timestamp DESC";
	            System.out.println("dsql "+sqlQuery);
	            stmt = con.createStatement();
	            rs = stmt.executeQuery(sqlQuery);
	            ModemAlertsBean bean = null;

	            while (rs.next()) {
	                bean = new ModemAlertsBean();
	               
	                bean.setMeter_serial_no(rs.getString("meter_serial_no"));
	                bean.setModem_serial_no(rs.getString("modem_serial_no"));
	                bean.setAlert_type(rs.getString("alert_type"));
	                bean.setAlert_timestamp(rs.getString("alert_timestamp"));
	                bean.setFv(rs.getString("fv"));
	                bean.setMeter_make(rs.getString("meter_make"));
	                bean.setImeino(rs.getString("imeino"));
	                bean.setIpaddress(rs.getString("ipaddress"));   
	              
	                
	                DiagList.add(bean);                
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            utils.DBUtil.connectionClose(con, stmt, rs);
	        }
	        return DiagList;
	    }


    
}
