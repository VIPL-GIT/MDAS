<form name="logForm">
    <table>
        <tr id="dates" >
            <td> Select Date:
                <input type="text" readonly="true" size="10" name="startingDate" id="s1Date" style="font-size:11px;padding :0px !important;height:20px !important;width:62px !important;"/>
                <a href="javascript:show_calendar('logForm.startingDate')"> <img src="images/cal1.gif" id="sdate" height=25 border=0 style="margin-bottom:-6px"/></a>
            </td>
            <!-- <td>Ending Date:
                 <input type="text" readonly="true" size="10" name="endingDate" id="endingDate"/>
                 <a href="javascript:show_calendar('form1.endingDate')"> <img src="images/cal1.gif" id="edate"  width=24 height=22 border=0/></a>
             </td> -->
            <td colspan="4" id="metersPart" style="padding-left: 10px;">
                <input type="button" value="Get On-Demand Logs" onclick="getOnDemandReport()" />
            </td>
        </tr>
    </table>
</form>