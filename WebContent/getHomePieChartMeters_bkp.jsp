<%@page import="java.text.SimpleDateFormat"%>

<%@ page import="utils.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<% 
  List meters=null;
  String totMeters="";
  Connection con=new DBUtil().getConnection();
  Connection datalog_con=null;
  PreparedStatement pstmt=null;
  ResultSet rs=null;
  String newLine = System.getProperty("line.separator");
  String dataSep = "\t";
  String fileNameNew = "";
  String totalString = "";
  String totalData = "",totalFeederData="",totalHtData="",totalLtData="",totalOAData="",totalDtrData="";
  String type=request.getParameter("type").trim();
  String meterType=request.getParameter("meterType").trim();
  HttpSession ses=request.getSession(false);
  if(type.contains("Non-Read")){
      fileNameNew="NotCommunicating.xls";
      meters=(List)ses.getAttribute("notCommMeters");      
  }
  else if(type.contains("Never-Read")){
     fileNameNew="NeverCommunicating.xls";
      meters=(List)ses.getAttribute("neverCommMeters");      
  }
//  
//  else if(type.contains("Un Mapped")){
//     fileNameNew="Un-mapped-comm-meters.xls";
//      meters=new HomePie().unMappedMeters;
//  }
   else{
      fileNameNew="Communicating.xls";
      meters=(List)ses.getAttribute("commMeters");      
   }
  
 System.out.println(type+"----------------Total Meters --------------->"+meters.size()); 
  
  String tempmeter="";
  Map<String,String> meterTimeStamp=new HashMap(); 
  Map<String,String> logTimeStamp=new HashMap(); 
  if(meters!=null && !type.contains("Un Mapped"))
  {
      //--------------------------------------------------
         for(int i=0;i<meters.size();i++)
    {
      tempmeter=meters.get(i).toString();      
      totMeters=totMeters+"'"+meters.get(i)+"',";
    
    }
  totMeters=totMeters.substring(0, totMeters.length()-1);
 // System.out.println("totMeterstotMeters = "+totMeters);
  //------------------getting log timestamp from comm_status --------------------------
  
 // System.out.println("==========="+logTimeStamp);
  
  //--------------getting modem details--------------------------------
    Map<String,String> modemMap=new HashMap();
    try{
   String sql="select * from modem";
    pstmt=con.prepareStatement(sql);
    rs=pstmt.executeQuery();
    while(rs.next()){
       modemMap.put(rs.getString("modem_serial_no"),(rs.getString("imei_no")==null?"":rs.getString("imei_no"))+","+(rs.getString("mobile_no")==null?" ":rs.getString("mobile_no"))+","+(rs.getString("static_ip")==null?" ":rs.getString("static_ip")));
    }
       }catch(Exception e){}
    //--------------getting alert details--------------------------------
    
    //--------------getting mcf details--------------------------------
    
    //--------------getting rawdata details--------------------------------
    
  //------------------getting last comm date and time details from meter_comm_track--------
     Map<String,String> lastCommMap=new HashMap();
    try{
    String sql="select * from meter_comm_track";
    pstmt=con.prepareStatement(sql);
    rs=pstmt.executeQuery();
    while(rs.next()){
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        java.util.Date cdate=sdf1.parse(rs.getString("d2_timestamp"));
        java.util.Date cdate=sdf1.parse(rs.getString("log_timestamp"));
       lastCommMap.put(rs.getString("meter_no"),sdf.format(cdate));
    }
       }catch(Exception e){ e.printStackTrace();}
  
  try{ 
      if(meterType.toUpperCase().equals("FEEDER") || meterType.toUpperCase().equals("ALL"))
   {
    	  totalString="Meter sl no"+dataSep+"Meter Type"+dataSep+"Modem Sl No"+dataSep+"Static IP"+dataSep+"IMEI No"+dataSep+"Mobile No";
    	  totalString=totalString+dataSep+"Last Comm Date Time";
    	  
    	  String sql="SELECT * FROM meter WHERE meterlocation=7 and meter_serial_no in ("+totMeters+")";
    	  rs=con.prepareStatement(sql).executeQuery();
    	  while(rs.next()){
    		  totalFeederData=totalFeederData+"'"+rs.getString("meter_serial_no")+dataSep+"Feeder"+dataSep+(rs.getString("modem_serial_no")==null?"":rs.getString("modem_serial_no"));
    		  String modem=rs.getString("modem_serial_no");
    		  if(modemMap.containsKey(rs.getString("modem_serial_no"))){
    			  
    			  totalFeederData=totalFeederData+dataSep+modemMap.get(modem).split(",")[2]+dataSep+modemMap.get(modem).split(",")[0]+dataSep+modemMap.get(modem).split(",")[1];
    		  }else{
    			  totalFeederData=totalFeederData+dataSep+" "+dataSep+" "+dataSep+" ";
    		  }
    		//------------last comm date
    		if(lastCommMap.containsKey(rs.getString("meter_serial_no"))){
    			totalFeederData=totalFeederData+dataSep+lastCommMap.get(rs.getString("meter_serial_no"));
    		}else{
    			totalFeederData=totalFeederData+dataSep+" ";
    		}
    		totalFeederData=totalFeederData+newLine;
    	  }//while
          
    
     } //FEEDER   
     if(meterType.toUpperCase().equals("HT")|| meterType.toUpperCase().equals("ALL"))
   {
    	 totalString="Meter sl no"+dataSep+"Meter Type"+dataSep+"Modem Sl No"+dataSep+"Static IP"+dataSep+"IMEI No"+dataSep+"Mobile No";
   	  totalString=totalString+dataSep+"Last Comm Date Time";
   	  
   	  String sql="SELECT * FROM meter WHERE meterlocation=8 and meter_serial_no in ("+totMeters+")";
   	  rs=con.prepareStatement(sql).executeQuery();
   	  while(rs.next()){
   		  totalHtData=totalHtData+"'"+rs.getString("meter_serial_no")+dataSep+"HT"+dataSep+(rs.getString("modem_serial_no")==null?"":rs.getString("modem_serial_no"));
   		  String modem=rs.getString("modem_serial_no");
   		  if(modemMap.containsKey(rs.getString("modem_serial_no"))){
   			  
   			totalHtData=totalHtData+dataSep+modemMap.get(modem).split(",")[2]+dataSep+modemMap.get(modem).split(",")[0]+dataSep+modemMap.get(modem).split(",")[1];
   		  }else{
   			totalHtData=totalHtData+dataSep+" "+dataSep+" "+dataSep+" ";
   		  }
   		//------------last comm date
   		if(lastCommMap.containsKey(rs.getString("meter_serial_no"))){
   			totalHtData=totalHtData+dataSep+lastCommMap.get(rs.getString("meter_serial_no"));
   		}else{
   			totalHtData=totalHtData+dataSep+" ";
   		}
   		totalHtData=totalHtData+newLine;
   	  }//while
      
     } //HT 
   if(meterType.toUpperCase().equals("LT")|| meterType.toUpperCase().equals("ALL"))
   {
	   totalString="Meter sl no"+dataSep+"Meter Type"+dataSep+"Modem Sl No"+dataSep+"Static IP"+dataSep+"IMEI No"+dataSep+"Mobile No";
	   	  totalString=totalString+dataSep+"Last Comm Date Time";
	   	  
	   	  String sql="SELECT * FROM meter WHERE meterlocation=9 and meter_serial_no in ("+totMeters+")";
	   	  rs=con.prepareStatement(sql).executeQuery();
	   	  while(rs.next()){
	   		  totalLtData=totalLtData+"'"+rs.getString("meter_serial_no")+dataSep+"LT"+dataSep+(rs.getString("modem_serial_no")==null?"":rs.getString("modem_serial_no"));
	   		  String modem=rs.getString("modem_seriall_no");
	   		  if(modemMap.containsKey(rs.getString("modem_seriall_no"))){
	   			  
	   			totalLtData=totalLtData+dataSep+modemMap.get(modem).split(",")[2]+dataSep+modemMap.get(modem).split(",")[0]+dataSep+modemMap.get(modem).split(",")[1];
	   		  }else{
	   			totalLtData=totalLtData+dataSep+" "+dataSep+" "+dataSep+" ";
	   		  }
	   		//------------last comm date
	   		if(lastCommMap.containsKey(rs.getString("meter_serial_no"))){
	   			totalLtData=totalLtData+dataSep+lastCommMap.get(rs.getString("meter_serial_no"));
	   		}else{
	   			totalLtData=totalLtData+dataSep+" ";
	   		}
	   		
	   		totalLtData=totalLtData+newLine;
	   	  }//while
    
      
     } //LT    
 if(meterType.toUpperCase().equals("DTR")|| meterType.toUpperCase().equals("ALL"))
{
	 totalString="Meter sl no"+dataSep+"Meter Type"+dataSep+"Modem Sl No"+dataSep+"Static IP"+dataSep+"IMEI No"+dataSep+"Mobile No";
  	  totalString=totalString+dataSep+"Last Comm Date Time";
  	  
  	  String sql="SELECT * FROM meter WHERE meterlocation=6 and meter_serial_no in ("+totMeters+")";
  	  rs=con.prepareStatement(sql).executeQuery();
  	  while(rs.next()){
  		  totalDtrData=totalDtrData+"'"+rs.getString("meter_serial_no")+dataSep+"DTR"+dataSep+(rs.getString("modem_serial_no")==null?"":rs.getString("modem_serial_no"));
  		  String modem=rs.getString("modem_serial_no");
  		  if(modemMap.containsKey(rs.getString("modem_serial_no"))){
  			  
  			totalDtrData=totalDtrData+dataSep+modemMap.get(modem).split(",")[2]+dataSep+modemMap.get(modem).split(",")[0]+dataSep+modemMap.get(modem).split(",")[1];
  		  }else{
  			totalDtrData=totalDtrData+dataSep+" "+dataSep+" "+dataSep+" ";
  		  }
  		//------------last comm date
  		if(lastCommMap.containsKey(rs.getString("meter_serial_no"))){
  			totalDtrData=totalDtrData+dataSep+lastCommMap.get(rs.getString("meter_serial_no"));
  		}else{
  			totalDtrData=totalDtrData+dataSep+" ";
  		}
  		
  		totalDtrData=totalDtrData+newLine;
  	  }//DTR
 
  } //DTR 
if(meterType.toUpperCase().equals("ALL"))
{
	totalString="Meter sl no"+dataSep+"Meter Type"+dataSep+"Modem Sl No"+dataSep+"Static IP"+dataSep+"IMEI No"+dataSep+"Mobile No";
	  totalString=totalString+dataSep+"Last Comm Date Time";
   
    
}//all      
      
      if(meterType.toUpperCase().equals("FEEDER"))
          totalData=totalFeederData;
      if(meterType.toUpperCase().equals("HT"))
          totalData=totalHtData;
      if(meterType.toUpperCase().equals("LT"))
          totalData=totalLtData;      
      if(meterType.toUpperCase().equals("DTR"))
          totalData=totalDtrData;
      
      if(meterType.toUpperCase().equals("ALL"))
          totalData=totalFeederData+totalHtData+totalLtData+totalOAData+totalDtrData;
      
       totalString = totalString + newLine + totalData;

System.out.println(" -------------");
//System.out.println(totalString);

        //response.setContentType("application/octet-stream"); //for csv format(.csv)
        response.setContentType("application/vnd.ms-excel");   //for xls format(.xls)

        response.setHeader("Content-Disposition", "attachment;filename=" + fileNameNew);
        ServletOutputStream op = response.getOutputStream();
        op.write(totalString.getBytes(), 0, totalString.length());
        op.flush();
        op.close();
           
  }catch(Exception e){
      e.printStackTrace();
  }
   }//if
  else{
      //block for un mapped communicating meters
      for(int i=0;i<meters.size();i++){
          totalData=totalData+"'"+meters.get(i)+newLine;
      }
      totalString="Meter Sl No"+newLine+totalData;
       //response.setContentType("application/octet-stream"); //for csv format(.csv)
        response.setContentType("application/vnd.ms-excel");   //for xls format(.xls)

        response.setHeader("Content-Disposition", "attachment;filename=" + fileNameNew);
        ServletOutputStream op = response.getOutputStream();
        op.write(totalString.getBytes(), 0, totalString.length());
        op.flush();
        op.close();
  }
%>
