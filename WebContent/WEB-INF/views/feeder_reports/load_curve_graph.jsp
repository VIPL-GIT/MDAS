<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import="com.rtdas.feeder_reports.beans.LoadCurveBean" %>


<%
String result="";
SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
DecimalFormat twodec=new DecimalFormat("#0.00");
String type=request.getAttribute("type").toString();

if(type.equalsIgnoreCase("meter")){
	Map<String,LoadCurveBean> map= (Map<String,LoadCurveBean>)request.getAttribute("vals");
	String dates="",ag="";
	for(Map.Entry em:map.entrySet()){
		double sum=0;
		LoadCurveBean lb=(LoadCurveBean)em.getValue();
		sum=Double.parseDouble(lb.getH1())+Double.parseDouble(lb.getH2())+Double.parseDouble(lb.getH3())+Double.parseDouble(lb.getH4())+Double.parseDouble(lb.getH5())+Double.parseDouble(lb.getH6())+Double.parseDouble(lb.getH7())+Double.parseDouble(lb.getH8())+Double.parseDouble(lb.getH9())+Double.parseDouble(lb.getH10())+Double.parseDouble(lb.getH11())+Double.parseDouble(lb.getH12())+Double.parseDouble(lb.getH13())+Double.parseDouble(lb.getH14())+Double.parseDouble(lb.getH15())+Double.parseDouble(lb.getH16())+Double.parseDouble(lb.getH17())+Double.parseDouble(lb.getH18())+Double.parseDouble(lb.getH19())+Double.parseDouble(lb.getH20())+Double.parseDouble(lb.getH21())+Double.parseDouble(lb.getH22())+Double.parseDouble(lb.getH23())+Double.parseDouble(lb.getH24());
		//result=result+"@"+(sdf.parse(em.getKey().toString()).getTime())+","+twodec.format(sum/1000);
		dates=dates+","+(sdf.parse(em.getKey().toString()).getTime());
		ag=ag+","+twodec.format(sum/1000);
	}
	if(!map.isEmpty()){
		dates=dates.substring(1);
		ag=ag.substring(1);		
	}
	System.out.println(dates+"@"+ag);
	out.println(dates+"@"+ag);
	
}else{
	System.out.println("Hierarchy got selected..........");
	Map<String,LoadCurveBean> map= (Map<String,LoadCurveBean>)request.getAttribute("vals");
	String dates="",ag="",nonag="";
	for(Map.Entry em:map.entrySet()){
		LoadCurveBean lb=(LoadCurveBean)em.getValue();
		
		dates=dates+","+(sdf.parse(em.getKey().toString()).getTime());
		ag=ag+","+lb.getAg();
		nonag=nonag+","+lb.getNonag();
	}
	if(!map.isEmpty()){
		dates=dates.substring(1);
		ag=ag.substring(1);
		nonag=nonag.substring(1);
	}
	System.out.println(dates+"@"+ag+"@"+nonag);
	out.println(dates+"@"+ag+"@"+nonag);
}
%>

