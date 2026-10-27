<%@page import="prepaid.beans.RechargeBean"%>
<%@page import="prepaid.dao.PrepaidRecharge"%>

<%
try{
    String meter=request.getParameter("meter");
    
    
    System.out.println("Recharge done...."+meter);
    String exePath="D:\\Netbeans Apps\\BCS\\BCS_v1\\exe";
    String exeName="ZEN_BCS_ver.03_Connect.exe";
    String meterConfigFile=meter+".xml";
    
    PrepaidRecharge pr=new PrepaidRecharge();
    boolean isDone=pr.tamperReconnect(meter, exePath,exeName,meterConfigFile);
}catch(Exception e){
 e.printStackTrace();
}

%>