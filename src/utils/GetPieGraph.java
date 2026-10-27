package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GetPieGraph {

	 public void getPieSummary(String discomId){
	        String selectedDate="20-04-2018";
//	        int selectedId=238;
	        int selectedId = Integer.parseInt(discomId);
	        String selectedLevel="discom";
	        String selectedType = "all";
	        MeterStatusBean msb = null;
	        try{
	           // List meters = new HomePie_maru().gettotalMeters(selectedId, selectedType, selectedLevel);
//	            msb = new HomePie_New().getResult(meters,selectedDate);
	            System.out.println("FINAL RESULT = "+msb.getCommMeters().size()+"\t"+msb.getNotCommMeters().size()+"\t"+msb.getNeverCommMeters().size());
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
	    public MeterStatusBean metersStatus(List totalMeters, String selectedDate){
	        Connection con = null;
	        List<String> communicatingMeterSerialNos=null;
	        List<String> powerOffMeterMeterSerialNos = null;
	        List<String> notCommunicatingMeterSerialNos = null;
	        List<String> NeverCommunicatingMeterSerialNos= null ;
	        List<String> mcfMeters=null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        MeterStatusBean msb=new MeterStatusBean();
	 List<String> communicatingMeters = new ArrayList<>(); //total comm meters on selected day in total meters
	        List<String> totalInstMeters = new ArrayList<>(); // total distinct meters in instant 
	        List<String> totalPowerOffMeters = new ArrayList<>();
//	        unMappedMeters=new ArrayList<String>();// total unmapped meters from meter table
	        String sql = "", sql1 = "";
	        int totalMeterCount=0;
	        int commCount = 0, notCommCount = 0, everCommCount = 0, cdfFailCount=0;
//	        int comFailCount=0, powerFailCount=0 ,onlyDiagCount=0,unKnownMetersCount=0;
	        try {
	            con = new DBUtil().getConnection();
	            System.out.println("Total meters===============>" + totalMeters.size());
	            SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	            String currentDate=sdf.format(new java.util.Date());
	            System.out.println("current date------->"+currentDate);
	            System.out.println("selectedDate date------->"+selectedDate);
	            // total communicating meters on selected date
	            if(selectedDate.equals(currentDate))
	             sql1 = "select * from meter_comm_track where str_to_date(date_format(log_timestamp,'%d-%m-%Y'),'%d-%m-%Y')= str_to_date('" + selectedDate + "','%d-%m-%Y')";
	            else{
	             sql1 = "select distinct(meter_no) from meter_comm_track_stats where str_to_date(date_format(meter_comm_date,'%d-%m-%Y'),'%d-%m-%Y')= str_to_date('" + selectedDate + "','%d-%m-%Y')";  
//	             sql1 = "select distinct(meter_no) from meter_comm_track_stats where str_to_date(date_format(log_timestamp,'%d-%m-%Y'),'%d-%m-%Y')= str_to_date('" + selectedDate + "','%d-%m-%Y')";   
	            }
	            System.out.println("==========>"+sql1);
	            ps = con.prepareStatement(sql1);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                communicatingMeters.add(rs.getString(1).trim());
	            }
	            ps.close();
	            rs.close();
	            System.out.println("today communicating meters in pie------>" + communicatingMeters.size());
	            //total instant meters
	            sql1 = "select meter_no from meter_comm_track";
	            ps = con.prepareStatement(sql1);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                totalInstMeters.add(rs.getString(1).trim());
	            }
	            ps.close();
	            rs.close();
	            System.out.println("total communicating meters------>" + totalInstMeters.size());
	            
	            //Power Off Meters
	            /*sql1 = "select meter_serial_no from masterdata where poweroff='YES'";
	            ps = con.prepareStatement(sql1);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                totalPowerOffMeters.add(rs.getString(1).trim());
	            }
	            ps.close();
	            rs.close();
	            System.out.println("total power off meters------>" + totalPowerOffMeters.size()); */
//	            //total unmapped meters
//	            sql1 = "select meter_serial_no from meter where dtrid is null and feederid is null and htconsumerid is null";
//	            ps = con.prepareStatement(sql1);
//	            rs = ps.executeQuery();
//	            while (rs.next()) {
//	                unMappedMeters.add(rs.getString(1));
//	            }
//	            ps.close();
//	            rs.close();
	             
	             communicatingMeterSerialNos= new ArrayList<String>();
	             powerOffMeterMeterSerialNos= new ArrayList<String>();
	             notCommunicatingMeterSerialNos= new ArrayList<String>();
	             NeverCommunicatingMeterSerialNos= new ArrayList<String>();
	             mcfMeters=new ArrayList<String>();
	             
//	             block for unmapped meters which are communicating in given date	
//	             unMappedMeters.retainAll(communicatingMeters);
//	             unKnownMetersCount=unMappedMeters.size();
//	             System.out.println("un knwoun meters===>"+unMappedMeters.size());
	             
//	               block for communicating and not communicating 

	               // Filtering Power Off Meter Serial Numbers.
	               powerOffMeterMeterSerialNos.addAll(totalMeters);
	               powerOffMeterMeterSerialNos.retainAll(totalPowerOffMeters);
	               totalMeters.removeAll(powerOffMeterMeterSerialNos);
	               
	               communicatingMeterSerialNos.addAll(totalMeters);
	               
	               //Communicating Meters
//	               communicatingMeters.retainAll(totalMeters);
	               communicatingMeterSerialNos.retainAll(communicatingMeters);
	               totalMeters.removeAll(communicatingMeterSerialNos);
	               commCount=communicatingMeterSerialNos.size();
	              // System.out.println("communicatingMeterSerialNos = "+communicatingMeterSerialNos);
	               
//---------------------------------getting mcf meters--------------------------------------------------------
	               //System.out.println("----------Total Meters------------"+totalMeters); 
	               mcfMeters=getMcfMeters(totalMeters);
	               totalMeters.removeAll(mcfMeters);
	               notCommunicatingMeterSerialNos.addAll(totalMeters);
	               
	                msb.setMcfMeters(mcfMeters);
//-----------------------------------------------------------------------------------------------------------	               
	            //block for Never communicating
	                
	               notCommunicatingMeterSerialNos.retainAll(totalInstMeters);
	               totalMeters.removeAll(notCommunicatingMeterSerialNos);
	              
	               NeverCommunicatingMeterSerialNos.addAll(totalMeters);
	               //System.out.println("notCommunicatingMeterSerialNos = "+notCommunicatingMeterSerialNos);
	               
	               notCommCount=notCommunicatingMeterSerialNos.size();
	               everCommCount=NeverCommunicatingMeterSerialNos.size();
	               
	               int powerOffCount = powerOffMeterMeterSerialNos.size();
	               totalMeterCount=commCount+notCommCount+everCommCount+powerOffCount;
	              // System.out.println("NeverCommunicatingMeterSerialNos = "+NeverCommunicatingMeterSerialNos);
	                msb.setCommMeters(communicatingMeterSerialNos);
	                msb.setNotCommMeters(notCommunicatingMeterSerialNos);
	                msb.setNeverCommMeters(NeverCommunicatingMeterSerialNos);
	                msb.setPowerOffMeters(powerOffMeterMeterSerialNos);
//-----------------------------setting unmapped communication meters----------------------------------
	                try{
	                	List<String> unmappedCommMeters=new ArrayList();
	                	sql1 = "select meter_no from meter_comm_track where meter_no in (select meter_serial_no from meter where location_id is null)"
	                			+ " and str_to_date(date_format(log_timestamp,'%d-%m-%Y'),'%d-%m-%Y')= str_to_date('" + selectedDate + "','%d-%m-%Y')";
	    	         System.out.println("un-mapped query   "+sql1);  
	                	ps = con.prepareStatement(sql1);
	    	            rs = ps.executeQuery();
	    	            while (rs.next()) {
	    	            	unmappedCommMeters.add(rs.getString(1).trim());
	    	            }
	    	            ps.close();
	    	            rs.close();
	    	            
	    	            msb.setUnmappedCommMeters(unmappedCommMeters);
	                }catch(Exception e){
	                	e.printStackTrace();
	                }
//----------------------------------------------------------------------------------------------------	                
//	            System.out.println("comm meters---->"+communicatingMeterSerialNos);
	            System.out.println("powerOffMeterMeterSerialNos = "+powerOffMeterMeterSerialNos);
	            System.out.println("communication count----" + commCount);
	            System.out.println("Not communicating count--->" + notCommCount);
	            System.out.println("Never communicating count----->" + everCommCount);
	            System.out.println("Power Off Meter Serial Numbers ----> "+powerOffCount);
	            //return totalMeterCount + "," + commCount + "," + notCommCount + "," + everCommCount;
	        } catch (Exception e) {
	            e.printStackTrace();
	           // return totalMeterCount+ "," + commCount + "," + notCommCount + "," + everCommCount;
	        }
	        return msb;
	    }
	    
	    public List<String> getOnlyCommmetersStatus( String selectedDate){
	        Connection con = null;
	        List<String> communicatingMeterSerialNos=null;
	       
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        MeterStatusBean msb=new MeterStatusBean();
	       List<String> communicatingMeters = new ArrayList<>(); //total comm meters on selected day in total meters
	        
	        String sql = "", sql1 = "";
	        
	        try {
	            con = new DBUtil().getConnection();
	            
	            SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	            String currentDate=sdf.format(new java.util.Date());
	            System.out.println("current date------->"+currentDate);
	            System.out.println("selectedDate date------->"+selectedDate);
	            // total communicating meters on selected date
	            if(selectedDate.equals(currentDate))
	             sql1 = "select * from meter_comm_track where str_to_date(date_format(log_timestamp,'%d-%m-%Y'),'%d-%m-%Y')= str_to_date('" + selectedDate + "','%d-%m-%Y')";
	            else{
	             sql1 = "select distinct(meter_no) from meter_comm_track_stats where str_to_date(date_format(meter_comm_date,'%d-%m-%Y'),'%d-%m-%Y')= str_to_date('" + selectedDate + "','%d-%m-%Y')";  
//	             sql1 = "select distinct(meter_no) from meter_comm_track_stats where str_to_date(date_format(log_timestamp,'%d-%m-%Y'),'%d-%m-%Y')= str_to_date('" + selectedDate + "','%d-%m-%Y')";   
	            }
	            System.out.println("==========>"+sql1);
	            ps = con.prepareStatement(sql1);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                communicatingMeters.add(rs.getString(1).trim());
	            }
	            ps.close();
	            rs.close();
	            System.out.println("today communicating meters in pie------>" + communicatingMeters.size());
	            
	           
	            
	               
	                
	        } catch (Exception e) {
	            e.printStackTrace();

	        }
	        return communicatingMeters;
	    }
	    
	    public List<String> getLast1hrCommmetersStatus( String selectedDate){
	        Connection con = null;
	        List<String> communicatingMeterSerialNos=null;
	       
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        MeterStatusBean msb=new MeterStatusBean();
	       List<String> communicatingMeters = new ArrayList<>(); //total comm meters on selected day in total meters
	        
	        String sql = "", sql1 = "";
	        
	        try {
	            con = new DBUtil().getConnection();
	            
	            SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	            String currentDate=sdf.format(new java.util.Date());
	            System.out.println("current date------->"+currentDate);
	            System.out.println("selectedDate date------->"+selectedDate);
	            // total communicating meters on selected date
	            if(selectedDate.equals(currentDate))
	             sql1 = "select *,TIMESTAMPDIFF(HOUR,log_timestamp,CURRENT_TIMESTAMP) AS diff from meter_comm_track where str_to_date(date_format(log_timestamp,'%d-%m-%Y'),'%d-%m-%Y')= str_to_date('" + selectedDate + "','%d-%m-%Y')";
	            else{
	             sql1 = "select distinct(meter_no) from meter_comm_track_stats where str_to_date(date_format(meter_comm_date,'%d-%m-%Y'),'%d-%m-%Y')= str_to_date('" + selectedDate + "','%d-%m-%Y')";  
//	             sql1 = "select distinct(meter_no) from meter_comm_track_stats where str_to_date(date_format(log_timestamp,'%d-%m-%Y'),'%d-%m-%Y')= str_to_date('" + selectedDate + "','%d-%m-%Y')";   
	            }
	            System.out.println("==========>"+sql1);
	            ps = con.prepareStatement(sql1);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	if(rs.getInt("diff")<=1)
	                  communicatingMeters.add(rs.getString(1).trim());
	            }
	            ps.close();
	            rs.close();
	            System.out.println("today communicating meters in pie------>" + communicatingMeters.size());
	            
	           
	            
	               
	                
	        } catch (Exception e) {
	            e.printStackTrace();

	        }
	        return communicatingMeters;
	    }
	    
	    public List<String> getLast1hrCommmetersStatus_new( String selectedDate){
	        Connection con = null;
	        List<String> communicatingMeterSerialNos=null;
	       
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        MeterStatusBean msb=new MeterStatusBean();
	       List<String> communicatingMeters = new ArrayList<>(); //total comm meters on selected day in total meters
	        
	        String sql = "", sql1 = "";
	        
	        try {
	            con = new DBUtil().getConnection();
	            
	            SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	            String currentDate=sdf.format(new java.util.Date());
	            System.out.println("current date------->"+currentDate);
	            System.out.println("selectedDate date------->"+selectedDate);
	            // total communicating meters on selected date
	            
	             sql1 = "select * from feeder_live_status where adjusted_rtc!='-'";
	            
	            ps = con.prepareStatement(sql1);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	if(new java.util.Date().getTime()-sdf.parse(rs.getString("adjusted_rtc")).getTime()<=1*30*60*1000)
	                  communicatingMeters.add(rs.getString("meter_serial_no").trim());
	            }
	            ps.close();
	            rs.close();
	            System.out.println("today communicating meters in pie------>" + communicatingMeters.size());
	            
	           
	            
	               
	                
	        } catch (Exception e) {
	            e.printStackTrace();

	        }
	        return communicatingMeters;
	    }   
	    
	    public List<String> getMcfMeters(List<String> meters){
	    	List<String> mcf=new ArrayList();
	    	Connection con=null;
	    	try{
	    		con=new DBUtil().getConnection();
	    		if(!meters.isEmpty()){
	    			String metersStr="";
	    			for(String mt:meters){
	    				metersStr=metersStr+"'"+mt+"',";
	    			}
	    			metersStr=metersStr.substring(0, metersStr.length()-1);
	    		String sql="select m.meter_serial_no from meter m,device_diagnosis_summary d where m.modem_serial_no=d.modem_serial_no and d.cablestatus='N' and m.meter_serial_no in ("+metersStr+")";
	    		ResultSet rs=con.prepareStatement(sql).executeQuery();
	    		while(rs.next()){
	    			mcf.add(rs.getString("meter_serial_no"));
	    		}
	    	 }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		try{
	    		if(con!=null) con.close();
	    		}catch(Exception e){}
	    	}
	    	
	    	return mcf;
	    }
	    public List<String> gettotalMeters(String selectedType) {
	        List<String> totalMeters = new ArrayList();
	        try{
	        	Connection con=new DBUtil().getConnection();
	        	String sql="select * from meter";
	        	if(selectedType.equalsIgnoreCase("0")){}
	        	else
	        		sql=sql+" where meterlocation="+selectedType;
	        	
	        	ResultSet rs=con.prepareStatement(sql).executeQuery();
	        	while(rs.next()){
	        		totalMeters.add(rs.getString("meter_serial_no"));
	        	}
	        }catch(Exception e){
	        	
	        }
	        return totalMeters;
	    }
}
