<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.rtdas.mdm.beans.*" %>
<%@page import="com.rtdas.mdm.models.*" %>
<%@page import="java.util.*" %>
<%
String totalData="";
try{
LoadBean lbean=(LoadBean)request.getAttribute("result");
String isError=(String)request.getAttribute("error");
if(isError!=null){
	out.println("error:<p class='error'>"+isError+"</p>");
}else{
String header=lbean.getHeader().getLoad_columns_header();
header=header.substring(0, header.length()-1);
List<D4_data> result=(List<D4_data>)lbean.getData();

String time="",dataString="";
for(D4_data d4:result){
	
	time=time+","+d4.getD4_date().split(" ")[1].trim().substring(0, d4.getD4_date().split(" ")[1].trim().lastIndexOf(":"));	
}

for(int i=0;i<header.split(",").length;i++){
	String value=header.split(",")[i];	
	for(D4_data d4:result){
		
		value=value+","+d4.getData_string().split(",")[i].trim();	
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

}//else
}catch(Exception e){
	e.printStackTrace();
}

%>

  