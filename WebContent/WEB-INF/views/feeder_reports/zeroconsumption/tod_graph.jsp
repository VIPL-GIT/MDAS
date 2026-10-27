<%@page import="java.util.*" %>
<%@page import="com.rtdas.feeder_reports.beans.TODConsumptionBean" %>
<%
try{
	Map<String,TODConsumptionBean> map=(Map<String,TODConsumptionBean>)request.getAttribute("zc");
	String xvalues="",tod1="",tod2="",tod3="",tod4="",tod5="",tod6="",tod7="",tod8="";
	if(!map.isEmpty()){
		for(Map.Entry em:map.entrySet()){
			TODConsumptionBean tb=(TODConsumptionBean)em.getValue();
			xvalues=xvalues+","+em.getKey().toString();
			tod1=tod1+","+tb.getTod1();
			tod2=tod2+","+tb.getTod2();
			tod3=tod3+","+tb.getTod3();
			tod4=tod4+","+tb.getTod4();
			tod5=tod5+","+tb.getTod5();
			tod6=tod6+","+tb.getTod6();
			tod7=tod7+","+tb.getTod7();
			tod8=tod8+","+tb.getTod8();
		}
		if(xvalues.contains(",")) xvalues=xvalues.substring(1);
		if(tod1.contains(",")) tod1=tod1.substring(1);
		if(tod2.contains(",")) tod2=tod2.substring(1);
		if(tod3.contains(",")) tod3=tod3.substring(1);
		if(tod4.contains(",")) tod4=tod4.substring(1);
		if(tod5.contains(",")) tod5=tod5.substring(1);
		if(tod6.contains(",")) tod6=tod6.substring(1);
		if(tod7.contains(",")) tod7=tod7.substring(1);
		if(tod8.contains(",")) tod8=tod8.substring(1);
		
		
		
		System.out.println(xvalues+";"+tod1+";"+tod2+";"+tod3+";"+tod4+";"+tod5+";"+tod6+";"+tod7+";"+tod8);
		out.println(xvalues+";"+tod1+";"+tod2+";"+tod3+";"+tod4+";"+tod5+";"+tod6+";"+tod7+";"+tod8);
	}else{
		out.println("error@<p style='text-align:center;'>Data is not available...</p>");
	}
}catch(Exception e){
	e.printStackTrace();
}
%>


