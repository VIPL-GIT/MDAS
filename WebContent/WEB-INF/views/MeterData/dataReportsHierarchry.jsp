<%@page import="utils.DBUtil"%>
<%@page import="DAO.UsersDao"%>
<%@ page import="java.sql.*"%>



<script type="text/javascript" src="js/jquery-1.7.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="js/highcharts/highcharts.js"></script>
<script src="js/highcharts/highcharts-more.js"></script> 
<style>
.myButton {
	-moz-box-shadow:inset 0px 1px 0px 0px #ffffff;
	-webkit-box-shadow:inset 0px 1px 0px 0px #ffffff;
	box-shadow:inset 0px 1px 0px 0px #ffffff;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f9f9f9), color-stop(1, #e9e9e9));
	background:-moz-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background:-webkit-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background:-o-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background:-ms-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background:linear-gradient(to bottom, #f9f9f9 5%, #e9e9e9 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#f9f9f9', endColorstr='#e9e9e9',GradientType=0);
	background-color:#f9f9f9;
	-moz-border-radius:6px;
	-webkit-border-radius:6px;
	border-radius:6px;
	border:1px solid #dcdcdc;
	display:inline-block;
	cursor:pointer;
	color:#666666;
	
	font-size:13px;
	font-weight:bold;
	padding:5px 19px;
	text-decoration:none;
	text-shadow:0px 1px 0px #ffffff;
}
.myButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #e9e9e9), color-stop(1, #f9f9f9));
	background:-moz-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background:-webkit-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background:-o-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background:-ms-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background:linear-gradient(to bottom, #e9e9e9 5%, #f9f9f9 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#e9e9e9', endColorstr='#f9f9f9',GradientType=0);
	background-color:#e9e9e9;
}
.myButton:active {
	position:relative;
	top:1px;
}
.one_half{
    
    width:100% !important;
}
select:disabled{
       background: darkgrey;
       color: white;
    } 
</style>

<%
    // UserName variable is declared in menu.jsp
     //UserName = session.getAttribute("userid").toString();
       //System.out.println("==================================$ "+UserName+" ====================");
%>
<%
    // UserName = session.getAttribute("userid").toString();
       //System.out.println("==================================$ "+UserName+" ====================");
       
       String office="all";
       String officeTypeId="";
       try{
    	
    	/* 
    	
    	   office=session.getAttribute("officeID").toString();
           System.out.println("office --> "+office);
           officeTypeId=session.getAttribute("officeTypeId")!=null?session.getAttribute("officeTypeId").toString():"";
           System.out.println(office+"\t"+officeTypeId); */
    	   
           
    	   
       }catch(Exception e){
       e.printStackTrace();
       }
       
       
       String officeName="ALL",locTypeAccess="DTR,HT,LT,Feeder";
       try{
           officeName=new UsersDao().getOfficeName(office, officeTypeId.length()>0?Integer.parseInt(officeTypeId):0);
           locTypeAccess=session.getAttribute("locTypeAccess")!=null?session.getAttribute("locTypeAccess").toString():"";
       }catch(Exception e){}
%>
<script>
    
    $( document ).ready(function() {
        
        if(<%=office.equals("all")%>){
            newShowZones($("#discom").val());
        }
        if(<%=office.equals("ZO")%>){
            $('#discom').attr('disabled', 'disabled');
            $('#zone').append('<option value="<%=officeTypeId%>" selected="selected"><%=officeName%></option>');
            newShowCircles(<%=officeTypeId%>);         
            
       
        }
        if(<%=office.equals("CO")%>){
            $('#discom').attr('disabled', 'disabled');
            $('#zone').attr('disabled', 'disabled'); 
            $('#circleId').append('<option value="<%=officeTypeId%>" selected="selected"><%=officeName%></option>');
            newShowDivisions(<%=officeTypeId%>);          
            
      }
      if(<%=office.equals("DO")%>){
            $('#discom').attr('disabled', 'disabled');
            $('#zone').attr('disabled', 'disabled');
            $('#circleId').attr('disabled', 'disabled');
            $('#divisionId').append('<option value="<%=officeTypeId%>" selected="selected"><%=officeName%></option>');
                    
            newShowSubDivisions(<%=officeTypeId%>); 
      }
      if(<%=office.equals("SDO")%>){
            $('#discom').attr('disabled', 'disabled');
            $('#zone').attr('disabled', 'disabled');
            $('#circleId').attr('disabled', 'disabled');
            $('#divisionId').attr('disabled', 'disabled');
            $('#subdivisionId').append('<option value="<%=officeTypeId%>" selected="selected"><%=officeName%></option>');
                     
                      
      }
   });
        
        
</script>
<div class="vernav2 iconmenu smallleft">
    <div class="slecthirearcy">
        <div class="contenttitle2 metedatarepors">
            <h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hierarchy</h3>
        </div>
                    
        <form name="meterReportsForm" action="javascript:void(0)" class="meterreports">
            <div class="one_half">
            
                <label>Discom</label>
                <select name="discom" id ="discom" onChange="newShowZones(this.value)">
                    <!--<option value="0">Select Discom</option>-->   
                    <%
                    Connection con = new DBUtil().getConnection();
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    System.out.println("before select stmnt");
                    try {
                        pstmt = con.prepareStatement("select * from discom");
                        rs = pstmt.executeQuery();
                        
                        
                        while (rs.next()) {
                            System.out.println("Discom Data ====> "+rs.getString("discom_id")+"     "+rs.getString("discom_name"));
                %>
            
                    <option value="<%=rs.getString("discom_id")%>"><%=rs.getString("discom_name")%></option>            
            
                <% }
                    } catch (Exception e) {
                        con.close();
                        response.sendRedirect("../errorPage.jsp?message=" + e.getMessage());
                    }
                %>    
                </select>
           
                </div>
                        
            
           <div class="one_half">
            
                <label>Zone</label>
                    <select name="zone" id ="zone" onchange="newShowCircles(this.value)">
                       <option value="0">Select Zone</option>          
                    </select>         
           
            </div>	


            
            <div class="one_half">
            <div class="">
                <label>Circle</label>
                    <select name="circle" id ="circleId" onChange="newShowDivisions(this.value);">
                        <option value="0">Select Circle</option>
                    </select>
            </div>
                
                </div>

            
          <div class="one_half">
            <div class="">
                <label>Division</label>
                    <select name="division" id ="divisionId" onChange="newShowSubDivisions(this.value);">
                         <option value="0">Select Division</option>
                     </select>   
            </div>
           </div>	


            <div class="clearall"></div>
             
            <div class="one_half">
            <div class="">
                <label>SubDivision</label>
                    <select name="subdivisionname" id="subdivisionId" onchange="newShowMandals(this.value);">
                        <option value="-1">Select Subdivision</option>
                    </select>                                
            </div>
          </div>	

           <div class="one_half" style="display:none;">
            <div class="">
                <label>Town</label>
                    <select name="mandal" id="mandalId" onchange="newShowSections(this.value);">
                        <option value="-1">Select Town</option>
                    </select>
            </div>
               </div>

            <div class="clearall"></div>

           <div class="one_half" style="display:none;">
            <div class="">
                <label>Section </label>
                    <select name="section" id="sectionId" onchange="newShowSubStations(this.value)">
                        <option value="-1">Select Section</option>
                    </select>
            </div>	
           </div>

           <div class="one_half" style="display:none;">
            <div class="">
                <label>SubStation</label>
                    <select name="ss" id ="ssId" onChange="oldSss();">
                        <option value="0">Select Substation</option>
                    </select>
            </div>
           </div>
            
           <div class="clearall"></div>
           <div class="one_half">
            <div class="">
                <label>Category</label>
                    <select name="cat" id="cat" >                        
                        <!--<option value="DTR">DTR</option>-->
<!--                        <option value="HT">HT</option> 
                        <option value="LT">LT</option>
                        <option value="OA">Open Access</option>
                        <option value="FEEDER">Interface</option>-->
            <% if(locTypeAccess.contains("HT")) { %> <option value="HT">HT</option> <% } %>
            <% if(locTypeAccess.contains("LT")) { %> <option value="LT">LT</option> <% } %>
            <% if(locTypeAccess.contains("OA")) { %> <option value="OA">OA</option> <% } %>
            <% if(locTypeAccess.contains("Feeder")) { %> <option value="Feeder">Interface</option> <% } %>
                        
                    </select>
            </div>
           </div>
           
           <div class="one_half">
            <div class="">
                <label>Search By</label>
                    <select name="search" id="search" onchange="document.getElementById('searchText').value = ''" >
                        <option value="0">Select</option>
                        <option value="location">Location Name</option>
                        <option value="meter">Meter Sl No</option>
                        <option value="unmapped">Unmapped Meters</option>
                    </select>
            </div>
           </div>    
               
            
        </form>
    </div>
<div class="clearall"></div>
    <div class ="getmeters slecthirearcy">
    
        <form class ="mart10" >
            <input type="text" name="searchText" id="searchText" maxlength="" style="padding:4px 10px;width: 99%;" > 
            <center>
                
                <%--<input name="save" class="reset radius2" value="Get Meters" type="button" style="padding:4px 10px; margin-top:10px; background:#ddd; border:solid 1px #ccc; color:#2e2e2e;" 
                               onclick="javascript:searchForMeterss()">  --%>
                <input name="save"   value="Get Meters" type="button"  class="myButton"
                               onclick="javascript:searchForMeterss()"> 
                
            </center>
        </form>
            
        <div>    
            
            <select name="dtr" id="dtr" style="height:200px;width:212px;font-size:14px;font-family:verdana;" size="12" onChange="getReportsInstantData(this.value);" >
            </select>
            
        </div>
    </div>
<div id="dcu_loading" style="display: none">
    <table width="100%">
        <tr>
            <td align="center">
                <img src="images/loaders/139.gif" style="padding-top:150px;"/>
            </td>
        </tr>
    </table>
</div>
                
</div><!--leftmenu-->

