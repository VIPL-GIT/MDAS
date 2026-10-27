<form name="snapDates" >
    <table cellpadding="5" cellspacing="5">
    <tr id="line">
        <td colspan="6">
            <table>
                <tr>                    
                <td  colspan="2" > From Date:
                <input type="text" readonly="true" size="10" name="startingDate" id="dsDate" style="border:1px solid #666666;"/></td>
                <td style="padding-top:0px;padding-right:10px">
                <a href="javascript:show_calendar('snapDates.startingDate')"> <img src="images/cal1.gif" id="sdate"  width=24 height=22 border=0/></a></td>
                <td colspan="2">To Date:
                <input type="text" readonly="true" size="10" name="endingDate" id="deDate" style="border:1px solid #666666;"/></td>
<!--                 <td style="padding-top:1px;" > -->
<!--                 <a href="javascript:show_calendar('snapDates.endingDate')"> <img src="images/cal1.gif" id="edate"  width=24 height=22 border=0/></a> -->
<!--                 &nbsp; -->
<!--                  <input type="checkbox" name="chbMF" id="chbMF" />Apply MF  -->
<!--                 &nbsp; -->
<!--                 </td> -->
                <td > 
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="myButton" value="Download All Profiles" onclick="downloadAllProfilesData()" />
                </td>
                               
                </tr>
            </table>
       </td>
   </tr>
  
    </table>
</form>


