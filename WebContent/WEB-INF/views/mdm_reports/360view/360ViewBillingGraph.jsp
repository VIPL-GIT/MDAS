<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.rtdas.mdm.beans.*" %>
<%@page import="com.rtdas.mdm.models.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%
String totalData="";
SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
try{
	BillingBean lbean=(BillingBean)request.getAttribute("result");
	String isError=(String)request.getAttribute("error");
	if(isError!=null){
		out.println("error:"+isError);
	}else{

String header=lbean.getB3_header().getBilling_columns_header();
header=header.substring(0, header.length()-1);
List<D3_b3> result=(List<D3_b3>)lbean.getEnergies();
System.out.println(result.size());
String time="",dataString="";

for(D3_b3 d3b3:result){
	try{
	String datetime=sdf.format(d3b3.getD3_time_stamp());
	//System.out.println("---------------"+datetime.split(" ")[1].substring(0, datetime.split(" ")[1].lastIndexOf(":")));
	time=time+","+datetime.split(" ")[1].substring(0, datetime.split(" ")[1].lastIndexOf(":"));	
	}catch(Exception e){
		e.printStackTrace();
	}
}
//System.out.println("---------------"+time);

for(int i=0;i<header.split(",").length;i++){
	String value=header.split(",")[i];	
	for(D3_b3 d3b3:result){
		
		value=value+","+d3b3.getData_string().split(",")[i].trim();	
		//System.out.println(value);
	}//for result
	
	dataString=dataString+";"+value;
			
}//for
dataString=dataString.substring(1);
		
//System.out.println("---------->"+dataString);
time="time"+time;

//System.out.println(time);
//System.out.println(header);
//System.out.println(time+";"+dataString);					
out.println(time+";"+dataString);

	}

}catch(Exception e){
	e.printStackTrace();
}

%>

  