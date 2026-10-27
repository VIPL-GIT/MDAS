<%@page import="java.util.*" %>
<%
try{
	Map<String,Integer> map=(Map<String,Integer>)request.getAttribute("lf");
	String xvalues="",yvalues="";
	if(!map.isEmpty()){
		for(Map.Entry em:map.entrySet()){
			xvalues=xvalues+","+em.getKey().toString();
			yvalues=yvalues+","+em.getValue();
		}
		if(xvalues.contains(",")) xvalues=xvalues.substring(1);
		if(yvalues.contains(",")) yvalues=yvalues.substring(1);
		
		out.println(xvalues+";"+yvalues);
	}else{
		out.println("error@<p style='text-align:center;'>Data is not available...</p>");
	}
}catch(Exception e){
	e.printStackTrace();
}
%>