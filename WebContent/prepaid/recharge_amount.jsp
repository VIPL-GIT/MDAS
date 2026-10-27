<%@page import="prepaid.beans.RechargeBean"%>
<%@page import="prepaid.dao.PrepaidRecharge"%>

<%
try{
    String meter=request.getParameter("meter");
    String amount=request.getParameter("amount");
    
    
    PrepaidRecharge pr=new PrepaidRecharge();
    boolean isDone=pr.rechargeAmount_smartmeter(meter, amount);
    
    if(isDone) out.print("Recharge done successfully...");
    else out.print("Failed, Try again...");
}catch(Exception e){
 e.printStackTrace();
}

%>