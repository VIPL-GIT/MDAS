<%@page import="utils.DBUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.sql.*" %>
<%@page import="java.text.*" %>
<%@page import="java.util.*" %>
<link rel="stylesheet" href="css/table.css" type="text/css" />
<!-- <link rel="stylesheet" href="css/style.default.css" type="text/css" /> -->
<script src="js/month/jquery-ui.min.js"></script>
<link href="js/month/jquery-ui.css" rel="stylesheet" type="text/css" />

<%@page import="com.rtdas.common.service.*" %>
<%@page import="com.rtdas.common.models.*" %>
<%@page import="com.rtdas.reports.dao.*" %>
<%@page import="com.rtdas.meterdata.beans.*" %>
 <%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>        

<style>
#instantTable td{
padding:2px !important;
}
.control-label{
width:100%;
text-align:left !important;
}
.col-md-2{
width:10% !important;
}
.form-control{
font-size:11px;
padding :0px !important;
height:20px !important;
}
</style>
<style>
.myButton {
	-moz-box-shadow: inset 0px 1px 0px 0px #ffffff;
	-webkit-box-shadow: inset 0px 1px 0px 0px #ffffff;
	box-shadow: inset 0px 1px 0px 0px #ffffff;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f9f9f9
		), color-stop(1, #e9e9e9));
	background: -moz-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: -webkit-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: -o-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: -ms-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background: linear-gradient(to bottom, #f9f9f9 5%, #e9e9e9 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f9f9f9',
		endColorstr='#e9e9e9', GradientType=0);
	background-color: #f9f9f9;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	border: 1px solid #dcdcdc;
	display: inline-block;
	cursor: pointer;
	color: #666666;
	margin-left: 10px;
	font-size: 13px;
	font-weight: bold;
	padding: 3px 19px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #ffffff;
}

.myButton:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #e9e9e9
		), color-stop(1, #f9f9f9));
	background: -moz-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background: -webkit-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background: -o-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background: -ms-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background: linear-gradient(to bottom, #e9e9e9 5%, #f9f9f9 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#e9e9e9',
		endColorstr='#f9f9f9', GradientType=0);
	background-color: #e9e9e9;
}

.myButton:active {
	position: relative;
	top: 1px;
}

.table_download_csv_link, .table_download_xls_link {
	margin: 10px;
	display: inline-block;
	padding: 10px;
	background-color: #1ABC9C;
	border-bottom: 3px solid #16A085;
	text-align: center;
	line-height: 30px;
	border-radius: 5px;
	color: #fff;
}
#allinstant{
 white-space: nowrap;;
}
</style>


<div class="table-responsive">
     <table cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont" id="allinstant">   
         <thead>
            <tr class="customrow">
                <th>Region</th>
                <th>Circle</th>
                <th>Division</th>
                <th>Zone</th>
                <th>Substation</th>
<!--                 <th>PTR</th> -->
                <th>Feeder</th>
                <th>Category</th>
<!--                 <th>Group</th> -->
                <th>Meter Sl.No</th>
                <th > Reading Date Time</th>
                <th > Server Date Time</th>
                
               
            </tr>
            </thead>
            <tbody>
   <%
   Connection con=null;
   try{
	   
	   System.out.println("--------------start time----------------"+new java.util.Date());
	   DecimalFormat fourDec=new DecimalFormat("#0.0000");
	   WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
	  
	   MeterService meterService = (MeterService) wac.getBean("meterService");
	   TotalMasterDAO tmdao=(TotalMasterDAO)wac.getBean("totalMasterDao");
	   List<Integer> childIds=null;
	   
	   
	   String meterStr="";
	   List<Meter> metersList=new ArrayList();
	   Map<String,Integer> meter_ids=new HashMap();
	   Map<String,TotalMasterdataBean> masterMap=new HashMap();
	   try{
	   	childIds=(List<Integer>)session.getAttribute("childIds");
	   	//System.out.println("child ids in total instant ---------------------------->"+childIds);
	   	List<TotalMasterdataBean> masterList=tmdao.getTotalMasterData();
	   	
	   	if(!masterList.isEmpty()){
	   		for(TotalMasterdataBean tmb:masterList){
	   			if(tmb.getCategory()!=null && tmb.getCategory().equalsIgnoreCase("INDUSTRIAL"))
	   			  masterMap.put(tmb.getMeter_sl_no(),tmb);
	   		}
	   	}
	   	System.out.println("master map ---------------------"+masterMap.size());
	   	 metersList=meterService.listMetersByLocation(childIds, 0); //0 to get all the meters
	   	for(Meter mt:metersList){
	   		meterStr=meterStr+",'"+mt.getMeter_sl_no()+"'";
	   		meter_ids.put(mt.getMeter_sl_no(),mt.getMeter_id());
	   	}

         
         
	   	if(meterStr.length()>0) meterStr=meterStr.substring(1);
	   }catch(Exception e){
	   	response.sendRedirect("/login.epx");
	   }
	   
	   con=new DBUtil().getConnection();
	   SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	   String presentDate=sdf.format(new java.util.Date());
	   String sql="select * from feeder_live_status";
	   //if(!metersList.isEmpty()) sql=sql+" where meter_serial_no in ("+meterStr+")";
	   
	   System.out.println("--------------feeder live status----------------"+new java.util.Date());
	   ResultSet rs=con.prepareStatement(sql).executeQuery();
	   while(rs.next()){
		   if(masterMap.containsKey(rs.getString("meter_serial_no"))){
		   double vmf=rs.getString("vmf")!=null?rs.getDouble("vmf"):1;
		   double imf=rs.getString("imf")!=null?rs.getDouble("imf"):1;
		   double mf=rs.getString("mf")!=null?rs.getDouble("mf"):1;
		   %>
		   <tr style="background-color: <%=rs.getString("meter_rtc").contains(presentDate)?"":"#f1ccbe"%>">
		    <%if(masterMap.containsKey(rs.getString("meter_serial_no"))){
				TotalMasterdataBean tmb=masterMap.get(rs.getString("meter_serial_no"));
				%>
				<td><%=tmb.getRegion().toUpperCase() %></td>
				<td><%=tmb.getCircle().toUpperCase() %></td>
				<td><%=tmb.getDivision().toUpperCase() %></td>
				<td><%=tmb.getSd().toUpperCase() %></td>
				<td><%=tmb.getSs().toUpperCase()%></td>
<%-- 				<td><%=tmb.getPtr().toUpperCase() %></td> --%>
				<td>
				<a href="javascript:getFeederTrippingDetails('<%=rs.getString("meter_serial_no")%>')">
				<%=tmb.getLocation_name().toUpperCase() %>
				</a>
				</td>
				<td><%=tmb.getLocation_category()==null?"":tmb.getLocation_category() %></td>
<%-- 				<td><%=tmb.getLocation_group()==null?"":tmb.getLocation_group()%></td> --%>
			<%} %>
			
			
		    <td><a href="javascript:goToMeterDataReports('<%=meter_ids.get(rs.getString("meter_serial_no"))%>')"><%=rs.getString("meter_serial_no")%></a></td>
			<td><%=rs.getString("meter_rtc")%></td>
			<td><%=rs.getString("modem_rtc")%></td>
			
			
		   </tr>
	   <%}
   }//while
	   
	   System.out.println("--------------end time----------------"+new java.util.Date());
   }catch(Exception e){
	   e.printStackTrace();
   }finally{	
	   try{
		   if(con!=null) con.close();
	   }catch(Exception e){
		   
	   }
   }
   %>  
   </tbody>
   </table>
   </div>                                         

