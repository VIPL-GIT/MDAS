
<%
String meter=request.getParameter("meter");
System.out.println("meter -screen1-------------> "+meter);
%>
<input type="hidden" id="meter_energy" value="<%=meter%>">

<input type="radio" checked="checked" value="Multiple" onclick="getMultipleLoadDate(this.value);" name="graph"/> Multi Axis Graph
<input type="radio" value="tabular" onclick="getLoadPage1(this.value);" name="graph"> Tabular &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!-- <input type="radio" value="tabular" onclick="getLsParamData(this.value);" name="graph"> LS Params Data &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- <input type="radio" value="DayWise" onclick="getLoadDate(this.value);" name="graph"> Day Graphs      -->
<td>&nbsp;&nbsp; 
                    <input id="chbMF" type="checkbox"  onclick="getLoadsurvayData_MF(this.checked)" > Apply MF</input> 
                </td>