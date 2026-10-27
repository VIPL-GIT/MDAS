/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prepaid.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import prepaid.beans.PrepaidAlerts;
import prepaid.beans.PrepaidInstant;
import prepaid.beans.RechargeBean;
import prepaid.beans.SlabRatesBean;
import prepaid.beans.TransactionsBean;
import utils.DBUtil;

/**
 *
 * @author maruthi
 */
public class PrepaidRecharge {

public boolean rechargeAmount_modem(String meter,String amount){
    boolean isdone=false;
    Connection con=null;
    String sql="",status="live";
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    
    DecimalFormat fmt=new DecimalFormat("#0.0000");
    try{
        double to_kwh=0.0,pres_kwh=0.0;
        con=new DBUtil().getConnection();
        
        while(meter.startsWith("0")){
        	meter=meter.substring(1);
         }
        double prev_balance=0,total_balance=0,curr_balance=0,bal_afterRecharge=0;
        String readings_timestamp="";
        sql="select * from prepaid_statement where meter_serial_no='"+meter+"' order by recharge_timestamp desc limit 1";
        ResultSet rs=con.prepareStatement(sql).executeQuery();
        if(rs.next()){
        	prev_balance=rs.getDouble("total_amount");
        	readings_timestamp=rs.getString("readings_timestamp");
        }
        
        //total_balance=(prev_balance+Double.parseDouble(amount));
        
        sql="insert into prepaid_statement(meter_serial_no,readings_timestamp,sign,amount,total_amount) values(?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, meter);
        ps.setString(2, readings_timestamp);        
        ps.setString(3, "+");
        ps.setString(4, ""+prev_balance);
        ps.setString(5, ""+(prev_balance+Double.parseDouble(amount)));
        ps.executeUpdate();
        
         prev_balance=0;
         String curMonth=sdf.format(new java.util.Date());
         curMonth=curMonth.substring(0, curMonth.lastIndexOf("-"));
        sql="select * from prepaid_transactions_monthly where meter_serial_no='"+meter+"' and to_timestamp like '"+curMonth+"%'";
        rs=con.prepareStatement(sql).executeQuery();
        if(rs.next()){
        	prev_balance=rs.getDouble("previous_balance");
        }
        
        sql="update prepaid_transactions_monthly set previous_balance=? where meter_serial_no='"+meter+"' and to_timestamp like '"+curMonth+"%'";
        ps=con.prepareStatement(sql);
        ps.setString(1,""+(prev_balance+Double.parseDouble(amount)));
        ps.executeUpdate();
        
        total_balance=(prev_balance+Double.parseDouble(amount));
        sql="select * from smartmeter_instant where g1='"+meter+"' order by recordid desc";
        rs=con.prepareStatement(sql).executeQuery();
        if(rs.next()){
        	curr_balance=rs.getDouble("current_balance");
        }
        bal_afterRecharge=total_balance-curr_balance;
        
        sql="INSERT INTO sms_config(req_time_stamp,req_type,mobile_no,meter_id) values(?,?,?,?)";
        ps=con.prepareStatement(sql);
        ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
        if(bal_afterRecharge>0)
         ps.setString(2, "$123456PRE,0,010,"+total_balance);
        else
         ps.setString(2, "$123456PRE,1,010,"+total_balance);
        ps.setString(3, "9515678364");
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

public boolean rechargeAmount_smartmeter(String meter,String amount){
    boolean isdone=false;
    Connection con=null;
    String sql="",status="live";
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    
    DecimalFormat fmt=new DecimalFormat("#0.0000");
    try{
        double to_kwh=0.0,pres_kwh=0.0;
        con=new DBUtil().getConnection();
        
        while(meter.startsWith("0")){
        	meter=meter.substring(1);
         }
        double prev_balance=0,total_balance=0,curr_balance=0,bal_afterRecharge=0;
        String readings_timestamp="";
        sql="select * from prepaid_statement where meter_serial_no='"+meter+"' order by recharge_timestamp desc limit 1";
        ResultSet rs=con.prepareStatement(sql).executeQuery();
        if(rs.next()){
        	prev_balance=rs.getDouble("total_amount");
        	readings_timestamp=rs.getString("readings_timestamp");
        }
        
        //total_balance=(prev_balance+Double.parseDouble(amount));
        
        sql="insert into prepaid_statement(meter_serial_no,readings_timestamp,sign,amount,total_amount) values(?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, meter);
        ps.setString(2, readings_timestamp);        
        ps.setString(3, "+");
        ps.setString(4, ""+prev_balance);
        ps.setString(5, ""+(prev_balance+Double.parseDouble(amount)));
        ps.executeUpdate();
        
         prev_balance=0;
         String curMonth=sdf.format(new java.util.Date());
         curMonth=curMonth.substring(0, curMonth.lastIndexOf("-"));
        //sql="select * from prepaid_transactions_monthly where meter_serial_no='"+meter+"' and to_timestamp like '"+curMonth+"%'";
         sql="select * from prepaid_transactions_monthly where meter_serial_no='"+meter+"' order by recordid desc limit 1";
        rs=con.prepareStatement(sql).executeQuery();
        if(rs.next()){
        	prev_balance=rs.getDouble("previous_balance");
        }
        
        sql="update prepaid_transactions_monthly set previous_balance=? where meter_serial_no='"+meter+"' and to_timestamp like '"+curMonth+"%'";
        ps=con.prepareStatement(sql);
        ps.setString(1,""+(prev_balance+Double.parseDouble(amount)));
        ps.executeUpdate();
        
        total_balance=(prev_balance+Double.parseDouble(amount));
        sql="select * from smartmeter_instant where g1='"+meter+"' order by recordid desc";
        rs=con.prepareStatement(sql).executeQuery();
        if(rs.next()){
        	curr_balance=rs.getDouble("current_balance");
        }
        bal_afterRecharge=total_balance-curr_balance;
        
         sql="insert into smart_meter_od(meter_no,action_type,name,sample_format,ondemandflag) values(?,?,?,?,?)";
		 ps=con.prepareStatement(sql);
		ps.setString(1,meter);
		ps.setString(2,"Execute");
		ps.setString(3,(bal_afterRecharge>0?"connect":"disconnect"));
		ps.setString(4,"0F00");
		ps.setString(5,"1");
		
		if(ps.executeUpdate()>0)
			        
        
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

public List<PrepaidInstant> getPrepaidInstant(String meter,String sdate,String edate){
    List<PrepaidInstant> list=new ArrayList();
    Connection con=null;
    try{
        con=new DBUtil().getConnection();
        DecimalFormat twoDec=new DecimalFormat("#0.00");
        DecimalFormat fourDec=new DecimalFormat("#0.0000");
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
        
        sdate=sdf2.format(sdf1.parse(sdate));
        edate=sdf2.format(sdf1.parse(edate));
        
        System.out.println(meter+"\t"+sdate+"\t"+edate);
        
        String sql="select * from smartmeter_instant where g1='"+meter+"' and STR_TO_DATE(g2,'%d-%m-%Y')>=STR_TO_DATE('"+sdate+"','%d-%m-%Y')"
        		+ "and STR_TO_DATE(g2,'%d-%m-%Y')<=STR_TO_DATE('"+edate+"','%d-%m-%Y') order by log_time_stamp desc";
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
            pi.setMETER_TIME_STAMP(sdf.format(rs.getTimestamp("METER_TIME_STAMP")));
            
            pi.setBase_kwh(rs.getString("base_kwh")==null?"":fourDec.format(rs.getDouble("base_kwh")));
            pi.setConsumed_units_kwh(rs.getString("consumed_units_kwh")==null?"":fourDec.format(rs.getDouble("consumed_units_kwh")));
            
            pi.setCharge_for_consumed_units(rs.getString("charge_for_consumed_units")!=null?twoDec.format(rs.getDouble("charge_for_consumed_units")):"");
            pi.setPrevious_balance(rs.getString("previous_balance")!=null?twoDec.format(rs.getDouble("previous_balance")):"");
            pi.setCurrent_balance(rs.getString("current_balance")!=null?twoDec.format(rs.getDouble("current_balance")):"");
            
            

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
public List<PrepaidAlerts> getPrepaidAlerts(String meter,String sdate,String edate){
    List<PrepaidAlerts> list=new ArrayList();
    Connection con=null;
    try{
        con=new DBUtil().getConnection();
        DecimalFormat twoDec=new DecimalFormat("#0.00");
        DecimalFormat fourDec=new DecimalFormat("#0.0000");
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
        
        sdate=sdf2.format(sdf1.parse(sdate));
        edate=sdf2.format(sdf1.parse(edate));
        
        System.out.println(meter+"\t"+sdate+"\t"+edate);
        
        String sql="select * from smart_meter_alerts_64 where meter_serial_no='"+meter+"' and STR_TO_DATE(meter_rtc,'%d-%m-%Y')>=STR_TO_DATE('"+sdate+"','%d-%m-%Y')"
        		+ "and STR_TO_DATE(meter_rtc,'%d-%m-%Y')<=STR_TO_DATE('"+edate+"','%d-%m-%Y') order by log_timestamp desc";
        ResultSet rs=con.prepareStatement(sql).executeQuery();
        while(rs.next()){
        	PrepaidAlerts pi=new PrepaidAlerts();
            
            pi.setObis(rs.getString("obis"));
            pi.setMeter_rtc(rs.getString("meter_rtc"));
            pi.setMeter(meter);
            pi.setBit_string(rs.getString("bit_string"));
            

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
public Map<String,String> getConnectDisconnectHistory(String meter,String sdate,String edate){
	Map<String,String> map=new HashMap();
	Connection con=null;
	try{
		con=DBUtil.getConnection();
		String sql="select * from smart_meter_od where meter_no='"+meter+"' and STR_TO_DATE(log_time_stamp,'%Y-%m-%d')>=STR_TO_DATE('"+sdate+"','%Y-%m-%d')"
        		+ "and STR_TO_DATE(log_time_stamp,'%Y-%m-%d')<=STR_TO_DATE('"+edate+"','%Y-%m-%d') order by log_time_stamp desc";
		
		System.out.println("---------------------------------->"+sql);
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			map.put(rs.getString("log_time_stamp"), rs.getString("name"));
		}
			
	}catch(Exception e){
		e.printStackTrace();
	}finally{
        try{
            if(con!=null) con.close();
        }catch(Exception e){}
    }
	return map;
	
}
public List<SlabRatesBean> getSlabRates(){
	List<SlabRatesBean> list=new ArrayList();
	Connection con=null;
	try{
		con=new DBUtil().getConnection();
		String sql="select * from prepaid_slab_scripts order by recordid";
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			SlabRatesBean sb=new SlabRatesBean();
			sb.setCategory(rs.getString("category"));
			sb.setConnected_load(rs.getString("connected_load"));
			sb.setCustomer_charge(rs.getString("customer_charge"));
			sb.setElectricity_charge(rs.getString("electricity_charge"));
			sb.setEnergy_charge(rs.getString("energy_charge"));
			sb.setMax_units(rs.getString("max_units"));
			sb.setMin_charge(rs.getString("min_charge"));
			sb.setMin_units(rs.getString("min_units"));
			sb.setPhase(rs.getString("phase"));
			sb.setScriptid(rs.getString("scriptid"));
			sb.setTariff_script(rs.getString("tariff_script"));
			
			list.add(sb);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
        try{
            if(con!=null) con.close();
        }catch(Exception e){}
    }
	return list;
	
}
public List<TransactionsBean> getPrepaidTransactions(String meter,String month){
	List<TransactionsBean> list=new ArrayList();
	Connection con=null;
	try{
		con=DBUtil.getConnection();
		System.out.println("------------------->"+month);
		String sql="select * from prepaid_transactions_monthly where meter_serial_no='"+meter+"' and log_timestamp like '"+month+"%' order by log_timestamp desc";
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			TransactionsBean tb=new TransactionsBean();
			tb.setAverage_unit_price(rs.getString("average_unit_price"));
			tb.setCharge_for_units_consumed(rs.getString("charge_for_units_consumed"));
			tb.setFrom_readings(rs.getString("from_readings"));
			tb.setFrom_timestamp(rs.getString("from_timestamp"));
			tb.setLog_timestamp(rs.getString("log_timestamp"));
			tb.setMeter_serial_no(rs.getString("meter_serial_no"));
			tb.setPrevious_balance(rs.getString("previous_balance"));
			tb.setRevised_balance(rs.getString("revised_balance"));
			tb.setTo_readings(rs.getString("to_readings"));
			tb.setTo_timestamp(rs.getString("to_timestamp"));
			tb.setUnits_consumed(rs.getString("units_consumed"));
			
			list.add(tb);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
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
