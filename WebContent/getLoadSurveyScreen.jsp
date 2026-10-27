
<%
String meter_id=request.getParameter("meter1");
System.out.println("meter --> screen "+meter_id);
%>
<input type="hidden" id="meter_energy_2" value="<%=meter_id%>">

<form name="loadScreen" class="lodscreen">
    <table cellpadding="5" cellspacing="5">
    <tr id="line">
        <td colspan="6">
            <table>
                <tr>                    
                <td  colspan="2" > From Date:
                <input type="text" readonly="true" size="10" name="startingDate" id="loadsDate" /></td>
                <td style="padding-top:0px;padding-right:10px">
                <a href="javascript:show_calendar('loadScreen.startingDate')"> <img src="images/cal1.gif" id="sdate"  width=24 height=22 border=0/></a></td>
                <td colspan="2">To Date:
                <input type="text" readonly="true" size="10" name="endingDate" id="loadeDate" /></td>
                <td style="padding-top:1px;" >
                <a href="javascript:show_calendar('loadScreen.endingDate')"> <img src="images/cal1.gif" id="edate"  width=24 height=22 border=0/></a></td>
<!--                 <td>&nbsp;&nbsp;  -->
<!--                     <input id="chbMF" type="checkbox" checked="false" onclick="getLoadsurvayData_MF(this.checked)" > Apply MF</input>  -->
<!--                 </td> -->
                <td > 
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="myButton" value="Get LoadSurvey Report" onclick="getLoadsurvayDatas(this.value)" />
                </td>                
                </tr>
            </table>
       </td>
    </tr>
    <tr>
        <td colspan="7" style="height:1px;background-color:#ddd;" ></td>    
    </tr>  
    </table>
</form>


