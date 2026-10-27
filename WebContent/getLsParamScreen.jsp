
<%
String meter_id=request.getParameter("meter_id");
System.out.println("meter First page --> screen "+meter_id);
%>
<input type="hidden" id="param_meter" value="<%=meter_id%>">

<form name="LsParamScreen" class="lsparamscreen">
    <table cellpadding="5" cellspacing="5">
    <tr id="line">
        <td colspan="8">
            <table>
                <tr>                    
                <td  colspan="2" > From Date:
                <input type="text" readonly="true" size="10" name="startingDate11" id="paramsdate" /></td>
                <td style="padding-top:0px;padding-right:10px">
                <a href="javascript:show_calendar('LsParamScreen.startingDate11')"> <img src="images/cal1.gif" id="sdate"  width=24 height=22 border=0/></a></td>
                <td colspan="2">To Date:
                <input type="text" readonly="true" size="10" name="endingDate11" id="paramedate" /></td>
                <td style="padding-top:1px;" >
                <a href="javascript:show_calendar('LsParamScreen.endingDate11')"> <img src="images/cal1.gif" id="edate"  width=24 height=22 border=0/></a></td>
<!--                 <td>&nbsp;&nbsp;  -->
<!--                     <input id="chbMF" type="checkbox" checked="false" onclick="getLoadsurvayData_MF(this.checked)" > Apply MF</input>  -->
<!--                 </td> -->

 <td  colspan="2" >
 
 <label for="lsparams">LS Param:</label>

<select name="lsparam" id="lsparam">
 <option value="vr">vr</option>
<option value="vy">vy</option>
<option value="vb">vb</option>
<option value="lr">lr</option>
<option value="lr">ly</option>
<option value="lb">lb</option>
<option value="kwh_e">kwh exp</option>
<option value="kwh_i">kwh imp</option>
<option value="kvah_e">kvah exp</option>
<option value="kvah_i">kvah imp</option>
<option value="kvarh_lag">kvarh lag</option>
<option value="kvarh_lead">kvarh lead</option>
<option value="voltage_avg">voltage (avg)</option>
<option value="current_avg">current (avg)</option>
</select>
 </td>
<td > 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="myButton" value="Get Data" onclick="getLsParamDataTable(this.value)" />
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





