package smartmeter;

import utils.DBUtil;
import java.sql.*;

public class SmartMeterDao {

	
	public SmartMeterSettings getSmartMeterSettings(String meter){
		SmartMeterSettings sms=new SmartMeterSettings();
		Connection con=null;
		try{
			con=DBUtil.getConnection();
			ResultSet rs=con.prepareStatement("select * from smart_meter_device_summary where meter_serial_no='"+meter+"'").executeQuery();
			if(rs.next()){
				sms.setMeter_serial_no(rs.getString("meter_serial_no"));
				sms.setLls_secret(rs.getString("lls_secret"));
				sms.setE_key(rs.getString("e_key"));
				sms.setA_key(rs.getString("a_key"));
				sms.setComport(rs.getString("comport"));
			}
			
		}catch(Exception e){
			
		}
		finally{
			try{
				if(con!=null) con.close();
			}catch(Exception e){}
		}
		
		return sms;
	}
	
	public SmartMeter_US_settings getSmartMeter_US_settings(String name,String attrName){
		SmartMeter_US_settings smus=new SmartMeter_US_settings();
		Connection con=null;
		try{
			con=DBUtil.getConnection();
			ResultSet rs=con.prepareStatement("select * from smart_meter_us_association_props where name='"+name+"' and attribute_name='"+attrName+"'").executeQuery();
			if(rs.next()){
				smus.setAction_type(rs.getString("action_type"));
				smus.setIc(rs.getString("ic"));
				smus.setObis(rs.getString("obis").replaceAll("\\.", ","));
				smus.setAttribute(rs.getString("attribute"));
				smus.setData_length(rs.getString("data_length"));
				smus.setData_type(rs.getString("data_type"));
				smus.setRx_response(rs.getString("rx_response"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(con!=null) con.close();
			}catch(Exception e){}
		}
		
		return smus;
	}
	
	public String appendZeros(String data,int len){
		
		try{
			len=len-data.length();
			for(int i=0;i<len;i++){
				data="0"+data;
			}
		}catch(Exception e){
			
		}
		
		return data;
	}
}
