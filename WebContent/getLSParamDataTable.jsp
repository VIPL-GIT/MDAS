<%@page import="java.util.*"%>
<%@page import="java.time.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="utils.DBUtil"%>
<%@page import="ActionBeans.Utilities"%>
<%@ page import="java.sql.*"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<head>
    <link rel="stylesheet" type="text/css" href="css/table.css">
    <link rel="stylesheet" type="text/css" href="css/pagination.css"> 
    <style>
        .kv{
            display:none;
        }
    </style>
</head>
<%!


public static Date addMinutesToDate(Date date, int minutes) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MINUTE, minutes);
    return calendar.getTime();
}

/* public static Date subtractMinutesToDate(Date date, int minutes) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MINUTE, minutes);
    return calendar.getTime();
} */

    String selectedDateStr = "";
    String selectedDateStr1 = "";
    String endlimit="";
    java.util.Date startDate;
    java.util.Date endDate;
    
    
%>
<%
    PreparedStatement pstmt = null;
    Statement stmt = null;
    Statement stmt1 = null;
    Statement stmt2 = null;
    ResultSet rs = null, rs1 = null, rs2=null;
    
    List<Date> dates = new ArrayList<Date>();
    Map<String,String> timeparammap=new HashMap<String,String>();//Creating HashMap 
    
    String lsdate = request.getParameter("lsdate");
    String ledate = request.getParameter("ledate");
    String meter_id = request.getParameter("meter_id").trim();
    String lsparam=request.getParameter("lsparam");
    System.out.println("meter_id -------------------------------> " + meter_id);
    System.out.println(lsdate+" "+ledate+" "+lsparam);%>
    
    <input type="hidden" id="param_meter" value="<%=meter_id%>">

   <%DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
    
    Date  startDate = (Date)formatter.parse(lsdate); 
    Date  endDate = (Date)formatter.parse(ledate);
    long interval = 24*1000 * 60 * 60; // 1 hour in millis
    long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
    long curTime = startDate.getTime();
    while (curTime <= endTime) {
        dates.add(new Date(curTime));
        curTime += interval;
    }
    
    
    
%>

<%
     String meter_serial_no=null;   
     int timedelay=0;
    Connection con =null;
        try{
    con = new DBUtil().getConnection();
    
    String meterquery="SELECT meter_serial_no FROM meter WHERE meterid LIKE '%"+meter_id+"%';";
    stmt2=con.createStatement();
    rs2=stmt2.executeQuery(meterquery);
    while(rs2.next()){
    	meter_serial_no=rs2.getString(1);
    }
    
   System.out.println("Query -->  SELECT max(time_delay) as timedelay FROM ls_data_stbl WHERE meter_serial_no LIKE '%"+meter_serial_no+"%'");
    
   String timedelayquery="SELECT max(time_delay) as timedelay FROM ls_data_stbl WHERE meter_serial_no LIKE '%"+meter_serial_no+"%'";
   stmt=con.createStatement();
   rs=stmt.executeQuery(timedelayquery);
  
   while(rs.next()){
	   timedelay=rs.getInt("timedelay");
	   System.out.println("timedelay "+timedelay);
   }

   if(timedelay>48){
	   timedelay=96;
   }else{
	   timedelay=48;
   } 
   
   System.out.println("timedelay  "+timedelay);
   
        }catch(Exception e){
        	 System.out.println("exception occured "+e.getMessage());
        }
%>

<div id="divdatatable" class="table-responsive">
<table cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont" id="loaddatatable" >    
    <thead>  
    <%if(timedelay==96){ %>
 <tr >  
<th>LS_Param_Date</th>
<th>0:15</th>
<th>0:30</th>
<th>0:45</th>
<th>1:00</th>
<th>1:15</th>
<th>1:30</th>
<th>1:45</th>
<th>2:00</th>
<th>2:15</th>
<th>2:30</th>
<th>2:45</th>
<th>3:00</th>
<th>3:15</th>
<th>3:30</th>
<th>3:45</th>
<th>4:00</th>
<th>4:15</th>
<th>4:30</th>
<th>4:45</th>
<th>5:00</th>
<th>5:15</th>
<th>5:30</th>
<th>5:45</th>
<th>6:00</th>
<th>6:15</th>
<th>6:30</th>
<th>6:45</th>
<th>7:00</th>
<th>7:15</th>
<th>7:30</th>
<th>7:45</th>
<th>8:00</th>
<th>8:15</th>
<th>8:30</th>
<th>8:45</th>
<th>9:00</th>
<th>9:15</th>
<th>9:30</th>
<th>9:45</th>
<th>10:00</th>
<th>10:15</th>
<th>10:30</th>
<th>10:45</th>
<th>11:00</th>
<th>11:15</th>
<th>11:30</th>
<th>11:45</th>
<th>12:00</th>
<th>12:15</th>
<th>12:30</th>
<th>12:45</th>
<th>13:00</th>
<th>13:15</th>
<th>13:30</th>
<th>13:45</th>
<th>14:00</th>
<th>14:15</th>
<th>14:30</th>
<th>14:45</th>
<th>15:00</th>
<th>15:15</th>
<th>15:30</th>
<th>15:45</th>
<th>16:00</th>
<th>16:15</th>
<th>16:30</th>
<th>16:45</th>
<th>17:00</th>
<th>17:15</th>
<th>17:30</th>
<th>17:45</th>
<th>18:00</th>
<th>18:15</th>
<th>18:30</th>
<th>18:45</th>
<th>19:00</th>
<th>19:15</th>
<th>19:30</th>
<th>19:45</th>
<th>20:00</th>
<th>20:15</th>
<th>20:30</th>
<th>20:45</th>
<th>21:00</th>
<th>21:15</th>
<th>21:30</th>
<th>21:45</th>
<th>22:00</th>
<th>22:15</th>
<th>22:30</th>
<th>22:45</th>
<th>23:00</th>
<th>23:15</th>
<th>23:30</th>
<th>23:45</th>
<th>00:00</th>
</tr>
<%}else{ %>
<tr >  
<th>LS_Param_Date</th>
<th>0:30</th>
<th>1:00</th>
<th>1:30</th>
<th>2:00</th>
<th>2:30</th>
<th>3:00</th>
<th>3:30</th>
<th>4:00</th>
<th>4:30</th>
<th>5:00</th>
<th>5:30</th>
<th>6:00</th>
<th>6:30</th>
<th>7:00</th>
<th>7:30</th>
<th>8:00</th>
<th>8:30</th>
<th>9:00</th>
<th>9:30</th>
<th>10:00</th>
<th>10:30</th>
<th>11:00</th>
<th>11:30</th>
<th>12:00</th>
<th>12:30</th>
<th>13:00</th>
<th>13:30</th>
<th>14:00</th>
<th>14:30</th>
<th>15:00</th>
<th>15:30</th>
<th>16:00</th>
<th>16:30</th>
<th>17:00</th>
<th>17:30</th>
<th>18:00</th>
<th>18:30</th>
<th>19:00</th>
<th>19:30</th>
<th>20:00</th>
<th>20:30</th>
<th>21:00</th>
<th>21:30</th>
<th>22:00</th>
<th>22:30</th>
<th>23:00</th>
<th>23:30</th>
<th>00:00</th>
</tr>
<%}%>
    </thead>
    <tbody>
    <% 
    
    System.out.println("date size "+dates.size());
    for(int i=0;i<dates.size();i++){ 
    	
    	int count1=15;
        int count2=30;
        
    	Date lDate =(Date)dates.get(i);
    	String datetopass = formatter1.format(lDate); 
    	System.out.println("datetopass "+datetopass);
    	
    	stmt1=con.createStatement();
        System.out.println("SELECT "+lsparam+",d4_date FROM ls_data_stbl WHERE meter_serial_no LIKE '%"+meter_serial_no+"%' AND d4_date LIKE '%"+datetopass+"%'");
        rs1=stmt1.executeQuery("SELECT "+lsparam+",d4_date FROM ls_data_stbl WHERE meter_serial_no LIKE '%"+meter_serial_no+"%' AND d4_date LIKE '%"+datetopass+"%'");
     
        int lastcount=0;
        String str2=null;
		DateFormat df2=null;
      
		 while(rs1.next()){
          timeparammap.put(rs1.getString(2),rs1.getString(1));
        }
		 
		   rs1.last(); 
		   int size = rs1.getRow(); 
		   rs1.beforeFirst();
		 
		   
	    	 %>
		    <tr>
		    <td><%=datetopass%></td>
		    <% 
		   
		 if(size==0){
			 for(int j=0;j<96;j++){
		 %>
			 <td>-</td>
		 <%}}
		 else{
		 for(int k=0;k<timedelay;k++){
			 lastcount++;
			 
			  SimpleDateFormat formatter2=new SimpleDateFormat("dd-MM-yyyy"); 
			  Date date1=formatter2.parse(datetopass);  
			  System.out.println("date1 "+date1);
			  System.out.println("lastcount "+lastcount);
			  Date date2=null;
			  if(timedelay==96){
				  
				  if(lastcount==96){
					  System.out.println(date1+" lastcount 111 "+lastcount);
		              date2=addMinutesToDate(date1,1439);
		              count1=count1+14;
				  }else{
					  System.out.println("lastcount 222 "+lastcount);
					  date2=addMinutesToDate(date1,count1);
				       count1=count1+15;
				  }
			  }else{
				  if(lastcount==48){
				  System.out.println("lastcount 333 "+lastcount);
				  date2=addMinutesToDate(date1,1439);
		          count2=count2+29;
		         
				  }else{
					  System.out.println("lastcount 444 "+lastcount);
					  date2=addMinutesToDate(date1,count2);
			          count2=count2+30;
				  }
			  }
		     
			 
			  df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		      str2 = df2.format(date2);
		      System.out.println("str2 "+str2);
		      
                 try{
		          if(timeparammap.containsKey(str2)){
		          	System.out.println(str2+"  ---> "+timeparammap.get(str2));
		          %>
		          	 <td><%=timeparammap.get(str2)%></td>
		         <%}else{%>
		      	   
		         <td>-</td>
		         
		        <% }
		          }catch(Exception e){
		          System.out.println("Exx "+e.getMessage());} 
		 }	 
		 }%>
        </tr>
   <%}%>
    </tbody>
</table>
</div>
<input type="hidden" id="meter_energy_2" value="<%=meter_id%>">

     
      
         
      
      
      