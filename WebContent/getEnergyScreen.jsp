
<%
String meter=request.getParameter("meter");
System.out.println("meter --> "+meter);
%>
<input type="hidden" id="meter_energy" value="<%=meter%>">

<script type="text/javascript" src="js/date-picker.js"></script>
<form name="energyScreen" >
    <div class="button_style"><input type="radio" name="e_opt1" value="1" checked onclick="getEnergy(this.value)">Main Energy &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div class="button_style"><input type="radio" name="e_opt1" value="6" onclick="getEnergy(this.value)">Max Demand</div>    
    <!--<div class="button_style"><input type="radio" name="e_opt1" value="8" onclick="getEnergy(this.value)">Daily Readings</div>-->    
    <!--<div class="button_style"><input type="radio" name="e_opt1" value="7" onclick="getEnergy(this.value)">Auto Reset Readings</div>-->    
    <!--<div class="button_style"><input type="radio" name="e_opt1" value="9" onclick="getEnergy(this.value)">Consumption Report</div>-->
                              <input type="checkbox" id="energyMF"  style="padding-left: 3px; " /> Apply MF
                              <br>
                              
    <div id="energyDates" style="padding-top:9px;">      
        <form name="energygraphicalReports">
            <table>
                <tr>
                    <td id="egdate">
                        From Date:
                        <input type="text" readonly="true" size="10" name="startingDate" id="energyFromDate"/>
                        <a href="javascript:show_calendar('energyScreen.startingDate')"> <img src="images/cal1.gif" id="esdate"  width=24 height=22 border=0/></a>
                        &nbsp;
                    </td>
                    <td id="egdate">
                        To Date:
                        <input type="text" readonly="true" size="10" name="endingDate" id="energyToDate"/>
                        <a href="javascript:show_calendar('energyScreen.endingDate')"> <img src="images/cal1.gif" id="eedate"  width=24 height=22 border=0/></a>
                        &nbsp;
                    </td>
                    <td><input type="button" class="myButton" value="Get Energy Report" onclick="getEnergyReport(); "/></td>
                </tr>
            </table>
        </form> 
    </div>
</form>