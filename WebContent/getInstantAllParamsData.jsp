
<%@page import="java.io.*"%>
<div class="table-responsive">
<table cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont" id="energydatatable">   
         <thead>
    <tr>
        <th>Parameter</th>
        <th>Value</th>
        <th>Units</th>
    </tr>
         </thead>
         
<%
    String meter=request.getParameter("meter_id");
    try{
    String filesPath="C:/Users/maruthi/Desktop/PSPCL/instant files";
    File f=new File(filesPath+"/"+meter+".txt");
    
    BufferedReader br=new BufferedReader(new FileReader(f));
    String str="";
    while((str=br.readLine())!=null){
        System.out.println("str----"+str);
        try{
        %>
        <tr>
            <%if(str.split(",")[0].contains("Demand")){ %>
            <td style="text-align: left;"><%=str.split(",")[0]%></td><td><%=str.split(",")[1]%><br><%=str.split(",")[2]%></td><td><%=str.split(",")[3]%></td>
            <%}else{%>
            <td style="text-align: left;"><%=str.split(",")[0]%></td><td><%=str.split(",")[1]%></td><td><%=str.split(",")[3]%></td>
            <%}%>
        </tr>
       <% }catch(Exception e){}
     
}
    
    }catch(Exception e){}
    %>
    </table>
</div>
