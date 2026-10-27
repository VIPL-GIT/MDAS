<%@page import="utils.DBUtil"%>
<%@page import="ActionBeans.SendSMS_VIPL"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="ActionBeans.Client_getMobileNo"%>
<%@page import="smartmeter.*" %>
<%@page import="com.vipl.main.*" %>
<%@page import="com.vipl.meter.objects.*" %>
<%@page import="com.vipl.encrypt.HexConverter" %>
<%@page import="org.apache.commons.lang.ArrayUtils" %>
<%@page import="java.sql.*"%>
<%@page import="java.time.*"%>

<%
String meter=request.getParameter("meter");
String activity=request.getParameter("activity");
String feature=request.getParameter("feature");
String params=request.getParameter("params");
String paramValues=request.getParameter("paramValues");


System.out.println(meter+"\t"+activity+"\t"+feature+"\t"+params+"\t"+paramValues);
if(Integer.parseInt(activity)==1){
	//params=""+Integer.parseInt(feature);
	//params=Integer.parseInt(feature)==1?"connect":"disconnect";
	params=feature;
}
if(Integer.parseInt(activity)==2){  //to send OD IP & CD
    System.out.println("in OD sending********************************");
	String command="";
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy HH:mm:ss"); 
    String uniqueId=sdf.format(new java.util.Date()).split(" ")[1];
    uniqueId=uniqueId.replaceAll(":","");    
   
         command = "$"+uniqueId + feature;
         Client_getMobileNo cgm = new Client_getMobileNo();
        String  mobileNo = cgm.getIPAddr(meter);
        
        try{
        	 String responseData = "Initiated";
        	 
        	System.out.println(meter+"\t"+mobileNo);
      /*   SendSMS_VIPL smsVIPL = new SendSMS_VIPL();
        responseData = smsVIPL.sendSMS_VIPL_DB(mobileNo.split(",")[0], command);
   //  tcp/ip
         responseData = "Initiated";
		  //smsVIPL.sendOD_TCPIP(mobileNo.split(",")[0], command);
          System.out.println(meter+"  responseData --> "+responseData); */
          
          //OnDemandDaoImpl mu=new OnDemandDaoImpl();
       try{                              
  	      Connection  con = utils.DBUtil.getConnection();
  	        System.out.println(con+"  connection ");
  	        
  	        Statement   stmt = con.createStatement();
//   	        String sql = "UPDATE meter SET ondemandflag = 1 WHERE meter_serial_no LIKE '"+meter+"%'";
//   	        System.out.println("query -->  "+sql);
//   	        int i = stmt.executeUpdate(sql); 
//   	        System.out.println("Result --> "+i);
  	      String sql="insert into smart_meter_od(meter_no,action_type,name,flag) values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,meter);
			ps.setString(2,"Ondemand");
			ps.setString(3,feature);			
			ps.setString(4,"1");
			
			if(ps.executeUpdate()>0)
				out.println("Initiated...");
			else
				out.println("Error...");
      	    
 }catch (Exception e) {
  			e.printStackTrace();
  		}   
     
  		
            
         /*  if (responseData.equals("Initiated")) {
        	 // mu.meterUpdate(meter);
             cgm.onDemandRequestToDB_VIPL(mobileNo.split(",")[2], mobileNo.split(",")[1],mobileNo.split(",")[0], command,uniqueId); 
          } */
          
         // out.println(responseData);
        }catch(Exception e){
        	e.printStackTrace();
        	out.println("Error...");
        }
	
}else{
	SmartMeterDao dao=new SmartMeterDao();
	SmartMeterSettings sms=dao.getSmartMeterSettings(meter);
	SmartMeter_US_settings smus=dao.getSmartMeter_US_settings(feature, params);
	System.out.println("----------------------------1");
	//----------------setting params ------------------------
		String text=smus.getData_type();
	
		if(Integer.parseInt(activity)==1){
			//paramValues=text+"00";
			paramValues="0F00";
			if(feature.equals("MD Reset"))
				paramValues="0001";
			//paramValues=smus.getSample_format();
			System.out.println("------------------------------------>>>>>>>>>>>>>>>"+paramValues);
		}
		else if(params.equals("Real Time Clock") || params.equals("ACTIVITY CALENDER (DateTime SET)")){
			String date=paramValues.split("T")[0];
			String time=paramValues.split("T")[1];
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
			LocalDate ld=LocalDate.of(Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1]), Integer.parseInt(date.split("-")[2]));
			
			paramValues=text+smus.getData_length()+
					dao.appendZeros(Long.toHexString(Long.parseLong(date.split("-")[0])),4)+  //year
					dao.appendZeros(Long.toHexString(Long.parseLong(date.split("-")[1])),2)+  //month
							dao.appendZeros(Long.toHexString(Long.parseLong(date.split("-")[2])),2)+ //date
					//"FF"+  //day of the week
					dao.appendZeros(""+ld.getDayOfWeek().getValue(),2)+ //day of the week
					dao.appendZeros(Long.toHexString(Long.parseLong(time.split(":")[0])),2)+
							dao.appendZeros(Long.toHexString(Long.parseLong(time.split(":")[1])),2)+
					"00FF014A00";
		}
		else if(params.equals("SINGLE ACTION SCHEDULE")){
			String date=paramValues.split("T")[0];
			String time=paramValues.split("T")[1];
			
			paramValues=text+smus.getData_length()+
					"02020904"+
					dao.appendZeros(Long.toHexString(Long.parseLong(time.split(":")[0])),2)+ //hh
					dao.appendZeros(Long.toHexString(Long.parseLong(time.split(":")[1])),2)+ //mins
					"FFFF"+
					"0905"+
					"FFFF"+					
					dao.appendZeros(Long.toHexString(Long.parseLong(date.split("-")[1])),2)+  //month
					dao.appendZeros(Long.toHexString(Long.parseLong(date.split("-")[2])),2)+ //date
					"FF"+
					
					"020209040000FFFF0905FFFF0201FF020209040000FFFF0905FFFF0301FF020209040000FFFF0905FFFF0401FF020209040000FFFF0905FFFF0501FF020209040000FFFF0905FFFF0601FF020209040000FFFF0905FFFF0701FF020209040000FFFF0905FFFF0801FF020209040000FFFF0905FFFF0901FF020209040000FFFF0905FFFF0A01FF020209040000FFFF0905FFFF0B01FF020209040000FFFF0905FFFF0C01FF";
		}
		
		else if(params.equals("ACTIVITY CALENDER (write)")){
			String tod1=paramValues.split(",")[0];
			String tod2=paramValues.split(",")[1];
			String tod3=paramValues.split(",")[2];
			String tod4=paramValues.split(",")[3];
			String tod5=paramValues.split(",")[4];
			String tod6=paramValues.split(",")[5];
			String tod7=paramValues.split(",")[6];
			String tod8=paramValues.split(",")[7];
			
			tod1=dao.appendZeros(Long.toHexString(Long.parseLong(tod1.split(":")[0])),2)+dao.appendZeros(Long.toHexString(Long.parseLong(tod1.split(":")[1])),2);
			tod2=dao.appendZeros(Long.toHexString(Long.parseLong(tod2.split(":")[0])),2)+dao.appendZeros(Long.toHexString(Long.parseLong(tod2.split(":")[1])),2);
			tod3=dao.appendZeros(Long.toHexString(Long.parseLong(tod3.split(":")[0])),2)+dao.appendZeros(Long.toHexString(Long.parseLong(tod3.split(":")[1])),2);
			tod4=dao.appendZeros(Long.toHexString(Long.parseLong(tod4.split(":")[0])),2)+dao.appendZeros(Long.toHexString(Long.parseLong(tod4.split(":")[1])),2);
			tod5=dao.appendZeros(Long.toHexString(Long.parseLong(tod5.split(":")[0])),2)+dao.appendZeros(Long.toHexString(Long.parseLong(tod5.split(":")[1])),2);
			tod6=dao.appendZeros(Long.toHexString(Long.parseLong(tod6.split(":")[0])),2)+dao.appendZeros(Long.toHexString(Long.parseLong(tod6.split(":")[1])),2);
			tod7=dao.appendZeros(Long.toHexString(Long.parseLong(tod7.split(":")[0])),2)+dao.appendZeros(Long.toHexString(Long.parseLong(tod7.split(":")[1])),2);
			tod8=dao.appendZeros(Long.toHexString(Long.parseLong(tod8.split(":")[0])),2)+dao.appendZeros(Long.toHexString(Long.parseLong(tod8.split(":")[1])),2);
			
			tod1=tod1.toUpperCase();
			tod2=tod2.toUpperCase();
			tod3=tod3.toUpperCase();
			tod4=tod4.toUpperCase();
			tod5=tod5.toUpperCase();
			tod6=tod6.toUpperCase();
			tod7=tod7.toUpperCase();
			tod8=tod8.toUpperCase();
			
			System.out.println(tod1+"\t"+tod2+"\t"+tod3);
			paramValues=text+
					"01020211010108"+
					"02030904"+tod1+"FFFF090600000A0064FF1200"+(tod1.equals("FFFF")?"FF":"01")+
					"02030904"+tod2+"FFFF090600000A0064FF1200"+(tod2.equals("FFFF")?"FF":"02")+
					"02030904"+tod3+"FFFF090600000A0064FF1200"+(tod3.equals("FFFF")?"FF":"03")+
					"02030904"+tod4+"FFFF090600000A0064FF1200"+(tod4.equals("FFFF")?"FF":"04")+
					"02030904"+tod5+"FFFF090600000A0064FF1200"+(tod5.equals("FFFF")?"FF":"05")+
					"02030904"+tod6+"FFFF090600000A0064FF1200"+(tod6.equals("FFFF")?"FF":"06")+
					"02030904"+tod7+"FFFF090600000A0064FF1200"+(tod7.equals("FFFF")?"FF":"07")+
					"02030904"+tod8+"FFFF090600000A0064FF1200"+(tod8.equals("FFFF")?"FF":"08");
		}
			else{		
			paramValues=Long.toHexString(Long.parseLong(paramValues));
			int len=Integer.parseInt(smus.getData_length(),16);
			len=len*2-paramValues.length();
			for(int i=0;i<len;i++){
				paramValues="0"+paramValues;
			}
			if(feature.equals("Daily Profile Integration Period")){ 
				//need to reverse the datasend incase of snapshot details
				 byte[] b=HexConverter.fromShortHexString(paramValues);
		            ArrayUtils.reverse(b);
		            paramValues=HexConverter.toShortHexString(b);
			}
		     	paramValues=text+paramValues;
			
		}
	System.out.println("------------------------------>"+paramValues);
	//MeterWriting_zen write=new MeterWriting_zen();
/**	MeterWriting_GPRS_Avon write=new MeterWriting_GPRS_Avon();
	 MeterProperties mp=new MeterProperties();
     mp.setPassword(sms.getLls_secret());
     mp.setCipherkey(sms.getE_key());
     mp.setAuthKey(sms.getA_key());
     mp.setAuthMechanism(2);

     Write_US_props usp=new Write_US_props();
     usp.setAction_type(smus.getAction_type());
     usp.setIc(smus.getIc());
     usp.setAttribute(smus.getAttribute());
     usp.setObis(smus.getObis());
     usp.setSample_format(paramValues);
     usp.setComport(sms.getComport());
     usp.setMeterPort(sms.getPull_port());
     usp.setIpaddress(sms.getIpaddress());
     
	  String rx_res=write.execute(mp, usp);
	  
	  if(rx_res.contains(smus.getRx_response()))
		  out.println("Done");
	  else
		  out.println("Failed...");  */
		  
//-----------------storing into the db for smart meter OD temporarily--------------		  
	Connection con1=null;
		try{
			con1=utils.DBUtil.getConnection();
			String sql="insert into smart_meter_od(meter_no,action_type,name,sample_format,ondemandflag) values(?,?,?,?,?)";
			PreparedStatement ps=con1.prepareStatement(sql);
			ps.setString(1,sms.getMeter_serial_no());
			ps.setString(2,activity.equals("1")?"Execute":"Write");
			ps.setString(3,params);
			ps.setString(4,paramValues);
			ps.setString(5,"1");
			
			if(ps.executeUpdate()>0)
				out.println("Initiated...");
			else
				out.println("Error...");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(con1!=null) con1.close();
		}
	
}

%>