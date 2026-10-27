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
/* .control-label{ */
/* width:100%; */
/* text-align:left !important; */
/* } */
/* .col-md-2{ */
/* width:10% !important; */
/* } */
/* .form-control{ */
/* font-size:11px; */
/* padding :0px !important; */
/* height:20px !important; */
/* } */
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


<div class="row table-responsive" style="min-height:30px;padding-top: -10px">
<div class="com-md-12">
<div class="panel-body"> 
<div class="table-responsive">
     <table cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont" id="allinstant">   
         <thead>
            <tr class="customrow">
                <th>Region</th>
                <th>Circle</th>
                <th>Division</th>
                <th>Zone</th>
                <th>Substation</th>
                <th>PTR</th>
                <th>Feeder</th>
                <th>Category</th>
                <th>Group</th>
                <th>Meter Sl.No</th>
                <th > Reading Date Time</th>
                <th > Server Date Time</th>
                <th>RTC Status</th>
                <th>Rph V</th>
                <th>Yph V</th>
                <th>Bph V</th>
                <th>Rph I</th>
                <th>Yph I</th>
                <th>Bph I</th>
                <th>Rph Pf</th>
                <th>Yph Pf</th>
                <th>Bph Pf</th>
                <th>AVG Pf</th>
                <th>kw</th>
                <th>kva</th>
                <th>kvar</th>
                <th>Freq</th>
                <th>MF</th>
                <th>CT status</th>
                <th>CT missing phases</th>
                <th>PT status</th>
                <th>PT missing phases</th>
                <th>PT observation</th>
                <th>PF status</th>
                <th>Phase sequence</th>
                <th>Current sequence</th>
               
            </tr>
            </thead>
            <tbody>
   <%
   Connection con=null;
   try{
	   String levelStr=request.getParameter("level");
	   String levelID=request.getParameter("levelID");
	   System.out.println("--------------start time----------------"+new java.util.Date());
	   DecimalFormat fourDec=new DecimalFormat("#0.0000");
	   WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
	  
	   MeterService meterService = (MeterService) wac.getBean("meterService");
	   TotalMasterDAO tmdao=(TotalMasterDAO)wac.getBean("totalMasterDao");
	   HierarchyService hservice=(HierarchyService)wac.getBean("hierarchyService");
	   HierarchyTitleService titleService=(HierarchyTitleService)wac.getBean("titleService");
	   TotalMasterDAO masterdao=(TotalMasterDAO)wac.getBean("totalMasterDao");
	   
	   
       int clientId=Integer.parseInt(session.getAttribute("clientId").toString());
       List<TotalMasterdataBean> masterMap=new ArrayList();
       Map<String,TotalMasterdataBean> master=new HashMap();
       List<Integer> ids=new ArrayList();
       List<String> meters=new ArrayList();
		String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
		List<HierarchyTitle> titleList=titleService.listTitles(clientId);
		if(levelStr.equals("0")){
			int level=0;
			if(userHierarchyids!=null){
				Hierarchy userHierarchy=hservice.getHierarchy(Integer.parseInt(userHierarchyids[0]));
			 for(int i=0;i<titleList.size();i++){
				HierarchyTitle htitle=(HierarchyTitle)titleList.get(i);
				if(htitle.getHierarchy_type_id()==userHierarchy.getHierarchy_type_id()){
					level=i+1;	    				
			       }
			     }//for
			 }//if
			if (userHierarchyids!=null && level==1 ){    //for company level user
				
				for(String sid:userHierarchyid.split(",")){
					ids.add(Integer.parseInt(sid));
				}
				masterMap=masterdao.getTotalMasterDataBysdID(ids,level);
				
			}
		}else{
			int level=Integer.parseInt(levelStr.split("_")[1])-1;
			ids.add(Integer.parseInt(levelID));
			masterMap=masterdao.getTotalMasterDataBysdID(ids,level);
		}

	   for(TotalMasterdataBean tmb:masterMap){
		   meters.add(tmb.getMeter_sl_no());
		   master.put(tmb.getMeter_sl_no(),tmb);
	   }
	   
	   con=new DBUtil().getConnection();
	   SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	   SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	   
	   String presentDate=sdf.format(new java.util.Date());
	   String sql="select *,TIMESTAMPDIFF(MINUTE,STR_TO_DATE(adjusted_rtc,'%d-%m-%Y %H:%i:%s'),CURRENT_TIMESTAMP) as diff from feeder_live_status";
	   //if(!metersList.isEmpty()) sql=sql+" where meter_serial_no in ("+meterStr+")";
	   
	   System.out.println("--------------feeder live status----------------"+new java.util.Date());
	   ResultSet rs=con.prepareStatement(sql).executeQuery();
	   while(rs.next()){
		   if(rs.getInt("diff")<=30){
		  if(meters.contains(rs.getString("meter_serial_no"))){ 
		   
		  // if(new java.util.Date().getTime()-sdf1.parse(rs.getString("adjusted_rtc")).getTime()<=1*90*60*1000){
		   if(meters.contains(rs.getString("meter_serial_no"))){
		   double vmf=rs.getString("vmf")!=null?rs.getDouble("vmf"):1;
		   double imf=rs.getString("imf")!=null?rs.getDouble("imf"):1;
		   double mf=rs.getString("mf")!=null?rs.getDouble("mf"):1;
		   %>
		   <tr style="background-color: <%=rs.getString("meter_rtc").contains(presentDate)?"":"#f1ccbe"%>">
		    <%if(master.containsKey(rs.getString("meter_serial_no"))){
				TotalMasterdataBean tmb=master.get(rs.getString("meter_serial_no"));
				%>
				<td><%=tmb.getRegion().toUpperCase() %></td>
				<td><%=tmb.getCircle().toUpperCase() %></td>
				<td><%=tmb.getDivision().toUpperCase() %></td>
				<td><%=tmb.getSd().toUpperCase() %></td>
				<td><%=tmb.getSs().toUpperCase()%></td>
				<td><%=tmb.getPtr().toUpperCase() %></td>
				<td><%=tmb.getLocation_name().toUpperCase() %></td>
				<td><%=tmb.getLocation_category()==null?"":tmb.getLocation_category() %></td>
				<td><%=tmb.getLocation_group()==null?"":tmb.getLocation_group()%></td>
			<%}else{ %>
			
			<td></td><td></td><td></td><td></td><td></td>
			<td></td><td></td><td></td>
			<td></td>
				<%}%>
		    <td><%=rs.getString("meter_serial_no")%></td>
			<td><%=rs.getString("meter_rtc")%></td>
			<td><%=rs.getString("modem_rtc")%></td>
			<td><%=rs.getString("rtc_status")==null?"":rs.getString("rtc_status")%></td>
			<td><%=fourDec.format(rs.getDouble("vr")*vmf*1.732)%></td>
			<td><%=fourDec.format(rs.getDouble("vy")*vmf*1.732)%></td>
			<td><%=fourDec.format(rs.getDouble("vb")*vmf*1.732)%></td>
			<td><%=fourDec.format(rs.getDouble("lr")*imf)%></td>
			<td><%=fourDec.format(rs.getDouble("ly")*imf)%></td>
			<td><%=fourDec.format(rs.getDouble("lb")*imf)%></td>
			<td><%=rs.getString("pfr")%></td>
			<td><%=rs.getString("pfy")%></td>
			<td><%=rs.getString("pfb")%></td>
			<td><%=rs.getString("pfavg")%></td>
			<td><%=fourDec.format(rs.getDouble("kw")*mf)%></td>
			<td><%=fourDec.format(rs.getDouble("kva")*mf)%></td>
			<td><%=fourDec.format(rs.getDouble("kvar")*mf)%></td>
			<td><%=rs.getString("freq")%></td>
			<td><%=rs.getString("mf")%></td>
			<td><%=rs.getString("ct_status")%></td>
			<td><%=rs.getString("ct_missing_phases")%></td>
			<td><%=rs.getString("pt_status")%></td>
			<td><%=rs.getString("pt_missing_phases")%></td>
			<td><%=rs.getString("pt_observation")%></td>
			<td><%=rs.getString("pf_status")%></td>
			<td><%=rs.getString("phasesequence")==null?"":rs.getString("phasesequence")%></td>
			<td><%=rs.getString("currentsequence")==null?"":rs.getString("currentsequence")%></td>
			
			
		   </tr>
	   <%}
		  }
		  }//if
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
</div>

</div>
</div>
