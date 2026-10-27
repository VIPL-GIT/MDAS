/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prepaid.dao;

import prepaid.beans.PrepaidInstant;
import prepaid.beans.RechargeBean;
import utils.DBUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author maruthi
 */
public class PrepaidRecharge_bkp {

public boolean rechargeAmount(String meter,String amount,String exePath,String exeName,String meterconfigFile){
    boolean isdone=false;
    Connection con=null;
    String sql="",status="live";
    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    DecimalFormat fmt=new DecimalFormat("#0.0000");
    try{
        double to_kwh=0.0,pres_kwh=0.0;
        con=new DBUtil().getConnection();
        String units=getUnits(Double.parseDouble(amount));
        sql="select * from prepaid where meter_serial_no='"+meter+"'";
        ResultSet rs=con.prepareStatement(sql).executeQuery();
        while(rs.next()){
            pres_kwh=rs.getDouble("present_kwh");
            to_kwh=rs.getDouble("to_kwh");
            status=rs.getString("status");
        }
        if(!status.equalsIgnoreCase("live")){
            if(pres_kwh>=to_kwh+Double.parseDouble(units)){
               sql="update prepaid set to_kwh=?,recent_recharge_amount=?,recharge_timestamp=? where meter_serial_no=?";
            }
            else{
           String exestatus= executeExe(exePath,exeName,meterconfigFile);
           if(exestatus.equals("success"))
            sql="update prepaid set to_kwh=?,recent_recharge_amount=?,recharge_timestamp=?,status='LIVE',disconnected_timestamp=null,source_of_disconnect=null where meter_serial_no=?";
           else
               sql="update prepaid set to_kwh=?,recent_recharge_amount=?,recharge_timestamp=?,disconnected_timestamp=null,source_of_disconnect=null where meter_serial_no=?";
            }
        }else{            
         sql="update prepaid set to_kwh=?,recent_recharge_amount=?,recharge_timestamp=?,disconnected_timestamp=null,source_of_disconnect=null where meter_serial_no=?";
        }
        PreparedStatement ps=con.prepareStatement(sql);
        
        ps.setString(1, fmt.format(to_kwh+Double.parseDouble(units)));
        ps.setString(2, amount);
        ps.setString(3, sdf.format(new java.util.Date()));
        ps.setString(4, meter);
        ps.executeUpdate();
        
        
        isdone=true;
    }catch(Exception e){
      isdone=false;
      e.printStackTrace();
    }
    finally{
         try{
             if(con!=null) con.close();
         }catch(Exception e){}
     }
    return isdone;
} 
public boolean tamperReconnect(String meter,String exePath,String exeName,String meterconfigFile){
    boolean isdone=false;
    Connection con=null;
    String sql="",status="live";
    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    DecimalFormat fmt=new DecimalFormat("#0.0000");
    try{
        double to_kwh=0.0;
        con=new DBUtil().getConnection();
        
         String exestatus= executeExe(exePath,exeName,meterconfigFile);
           if(exestatus.equals("success")){          
           sql="update prepaid set status='LIVE',disconnected_timestamp=null,source_of_disconnect=null where meter_serial_no=?";
        
        PreparedStatement ps=con.prepareStatement(sql);        
        ps.setString(1, meter);
        ps.executeUpdate();
        isdone=true;
           }
    }catch(Exception e){
      isdone=false;
      e.printStackTrace();
    }
    finally{
         try{
             if(con!=null) con.close();
         }catch(Exception e){}
     }
    return isdone;
} 
public String executeExe(String exePath,String exeName,String meterConfigFile){
    String result="",line="",cmd="";
    Runtime r = Runtime.getRuntime();
    StringBuilder consoleContent = new StringBuilder();
    try{        
        
        cmd="cmd /c "+exeName+" "+meterConfigFile;
        System.out.println("------------>"+cmd);            
       Process  p=  r.exec(cmd, null,  new File(exePath));
         BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
         
         while((line = reader.readLine()) != null) {
            consoleContent.append(line);
          }
         if(consoleContent.toString().contains("LOAD CONNECTED")) result="success";
         else result="failed";
         
    }catch(Exception e){
      e.printStackTrace();
    }finally{
        System.out.println("exe result------->"+result);
        consoleContent.setLength(0);
    }
    return result;
}
 public List<RechargeBean> getrechargeHistory(){
     List<RechargeBean> list=new ArrayList();
     Connection con=null;
     try{
         
         con=new DBUtil().getConnection();
        String sql="select * from prepaid";
        ResultSet rs=con.prepareStatement(sql).executeQuery();
        while(rs.next()){
            RechargeBean rb=new RechargeBean();
            rb.setMeter_serial_no(rs.getString("meter_serial_no"));
            rb.setPresent_kwh(rs.getString("present_kwh"));
            rb.setUpdated_datetime(rs.getString("updated_datetime"));
            rb.setTo_kwh(rs.getString("to_kwh"));
            rb.setRecent_recharge_amount(rs.getString("recent_recharge_amount"));
            rb.setRecharge_timestamp(rs.getString("recharge_timestamp"));
            rb.setTampercount(rs.getString("tampercount"));
            rb.setDisconnected_timestamp(rs.getString("disconnected_timestamp"));
            rb.setStatus(rs.getString("status"));
            rb.setSource_of_disconnect(rs.getString("source_of_disconnect"));
            
            list.add(rb);
        }
     }catch(Exception e){}
     finally{
         try{
             if(con!=null) con.close();
         }catch(Exception e){}
     }
     
     return list;
     
 }   

public List<PrepaidInstant> getPrepaidInstant(){
    List<PrepaidInstant> list=new ArrayList();
    Connection con=null;
    try{
        con=new DBUtil().getConnection();
        String sql="select * from instant order by LOG_TIME_STAMP desc limit 20";
        ResultSet rs=con.prepareStatement(sql).executeQuery();
        while(rs.next()){
            PrepaidInstant pi=new PrepaidInstant();
            
            pi.setMeter_type(rs.getString("meter_type"));
            pi.setG1(rs.getString("G1"));
            pi.setG2(rs.getString("G2"));
            pi.setG3(rs.getString("G3"));
            pi.setOne_PH_VOLTAGE(rs.getString("1PH_VOLTAGE"));
            pi.setRPH_VOLTAGE(rs.getString("RPH_VOLTAGE"));
            pi.setYPH_VOLTAGE(rs.getString("YPH_VOLTAGE"));
            pi.setBPH_VOLTAGE(rs.getString("BPH_VOLTAGE"));
            pi.setRPH_LINE_CURRENT(rs.getString("RPH_LINE_CURRENT"));
            pi.setYPH_LINE_CURRENT(rs.getString("YPH_LINE_CURRENT"));
            pi.setBPH_LINE_CURRENT(rs.getString("BPH_LINE_CURRENT"));
            pi.setOne_PH_CURRENT(rs.getString("1PH_CURRENT"));
            pi.setNEUTRAL_CURRENT(rs.getString("NEUTRAL_CURRENT"));
            pi.setACT_POWER(rs.getString("ACT_POWER"));
            pi.setREACT_POWER(rs.getString("REACT_POWER"));
            pi.setAPPARENT_POWER(rs.getString("APPARENT_POWER"));
            pi.setRPH_POWER_FACTOR(rs.getString("RPH_POWER_FACTOR"));
            pi.setYPH_POWER_FACTOR(rs.getString("YPH_POWER_FACTOR"));
            pi.setBPH_POWER_FACTOR(rs.getString("BPH_POWER_FACTOR"));
            pi.setAVG_POWER_FACTOR(rs.getString("AVG_POWER_FACTOR"));
            pi.setFREQUENCY(rs.getString("FREQUENCY"));
            pi.setKWH(rs.getString("KWH"));
            pi.setKVAH(rs.getString("KVAH"));
            pi.setKVARH_LAG(rs.getString("KVARH_LAG"));
            pi.setKVARH_LEAD(rs.getString("KVARH_LEAD"));
            pi.setMDKW(rs.getString("MDKW"));
            pi.setMDKVA(rs.getString("MDKVA"));
            pi.setMDKWOCCDATE(rs.getString("MDKWOCCDATE"));
            pi.setMDKVAOCCDATE(rs.getString("MDKVAOCCDATE"));
            pi.setKWH_E(rs.getString("KWH_E"));
            pi.setKVAH_E(rs.getString("KVAH_E"));
            pi.setPOWER_ON_DURATION(rs.getString("POWER_ON_DURATION"));
            pi.setTAMPER_COUNT(rs.getString("TAMPER_COUNT"));
            pi.setBILLING_COUNT(rs.getString("BILLING_COUNT"));
            pi.setPROGRAMMING_COUNT(rs.getString("PROGRAMMING_COUNT"));
            pi.setDisc_con(rs.getString("disc_con"));
            pi.setLimiter(rs.getString("limiter"));
            pi.setMETER_TIME_STAMP(rs.getString("METER_TIME_STAMP"));

            list.add(pi);
            
        }
        
    }catch(Exception e){}
    finally{
         try{
             if(con!=null) con.close();
         }catch(Exception e){}
     }
    return list;
} 
 public String getUnits(double amount){

        double units = 0.0;
        double first50 = 400000.6, second50=400001.6;
        try{

            if(amount>(50*first50)){
                units = 50;
                amount = amount - (50*first50);
                double extraUnits = amount/second50;
                units = units + extraUnits;
            }else{
                double extraUnits = amount/first50;
                units = units + extraUnits;
            }
            return new DecimalFormat("#0.000").format(units);

        }catch(Exception e){
            e.printStackTrace();
        }

        return "";

    }
}
