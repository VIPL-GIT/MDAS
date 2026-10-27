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
  
  System.out.println("-----------------"+type);
  if(type.contains("Non-Read")){
      fileNameNew="NotCommunicating.xls";
      meters=(List)ses.getAttribute("notCommMeters");      
  }
  else if(type.contains("Never-Read")){
     fileNameNew="NeverCommunicating.xls";
      meters=(List)ses.getAttribute("neverCommMeters");      
  }
  else if(type.contains("unmappedRead")){
	     fileNameNew="Unmapped_read.xls";
	      meters=(List)ses.getAttribute("unmappedCommMeters");      
	  }
  else if(type.contains("Cable Issue")){
	     fileNameNew="cable_issue_meters.xls";
	      meters=(List)ses.getAttribute("mcfMeters");      
	  }

   else{
      fileNameNew="Communicating.xls";
      meters=(List)ses.getAttribute("commMeters");      
   }
  
 System.out.println(meterType+"----------------Total Meters --------------->"+meters.size()); 
  
  String tempmeter="";
  Map<String,String> ip_points=new HashMap(); 
  Map<String,String> meter_makes=new HashMap(); 
  if(meters!=null && !type.contains("unmappedRead"))
  {
     
         for(int i=0;i<meters.size();i++)
    {
      tempmeter=meters.get(i).toString();      
      totMeters=totMeters+"'"+meters.get(i)+"',";
    
    }
  totMeters=totMeters.substring(0, totMeters.length()-1);
 // System.out.println("totMeterstotMeters = "+totMeters);
  //--------------------getting Install points --------------------------
        rs=con.prepareStatement("select * from meterlocation").executeQuery();
  while(rs.next()){
	  ip_points.put(rs.getString("locationid"),rs.getString("locationname"));
  }
//--------------------getting meter makes --------------------------
  rs=con.prepareStatement("select * from metermake").executeQuery();
while(rs.next()){
meter_makes.put(rs.getString("metermakeid"),rs.getString("metermakename"));
}
  
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
	      totalString="Company"+dataSep+"SDO"+dataSep+"SS"+dataSep;
    	  totalString=totalString+"Meter Sl.No"+dataSep+"Installation Point"+dataSep+"Meter Make"+dataSep+"Meter Type"+dataSep+"Modem Sl.No"+dataSep+"Mobile No";
    	  totalString=totalString+dataSep+"Location Name"+dataSep+"Location Code"+dataSep+"Category";
    	  totalString=totalString+dataSep+"Last Comm Date Time";
    	  String sql="select * from total_masterdata where meter_sl_no in ("+totMeters+")";
    	  if(meterType.equals("0")){}
    	  else
    		  sql=sql+" and meter_location="+meterType;
    	  rs=con.prepareStatement(sql).executeQuery();
    	  while(rs.next()){
     totalData=totalData+rs.getString("company")+dataSep+rs.getString("sd")+dataSep+(rs.getString("ss")==null?"":rs.getString("ss"))+dataSep+
    				    rs.getString("meter_sl_no")+dataSep+
    				    (ip_points.containsKey(rs.getString("meter_location"))?ip_points.get(rs.getString("meter_location")):rs.getString("meter_location"))+
    				    dataSep+
    				    (meter_makes.containsKey(rs.getString("meter_make"))?meter_makes.get(rs.getString("meter_make")):rs.getString("meter_make"))
    				    +dataSep+rs.getString("meter_type")+dataSep+(rs.getString("modem_sl_no")==null?"":rs.getString("modem_sl_no"))+dataSep+(rs.getString("modem_mobile_no")==null?"":rs.getString("modem_mobile_no"))+dataSep+
    				    rs.getString("location_name")+dataSep+"'"+rs.getString("location_code")+dataSep+rs.getString("location_category");
    		  totalData=totalData+newLine;
    	  }
    	  
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
