<%@page import="java.util.*" %>
<%
String result="";
try{
	Map<String,Integer> map = (Map<String,Integer>)request.getAttribute("map");
	String names="",values="";
	for(Map.Entry em: map.entrySet()){
		if(!em.getKey().toString().equalsIgnoreCase("POWER FAILED")){
		  names=names+","+em.getKey().toString();
		  values=values+","+em.getValue().toString();
		}
	}
	if(!map.isEmpty()){
		names=names.substring(1);
		values=values.substring(1);
		
		result=names+";"+values;
	}
}catch(Exception e){
	
}
System.out.println(result);
out.println(result);
%>