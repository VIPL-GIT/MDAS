<%@page import="java.util.*" %>
<%

try{
	int totalCommCount=0,unmappedCommMeters=0;
	if(session.getAttribute("commMeters")!=null){
		List<String>  commMeters=(List)session.getAttribute("commMeters");
		totalCommCount=commMeters.size();
	}
	if(session.getAttribute("unmappedCommMeters")!=null){
		List<String>  unmcommMeters=(List)session.getAttribute("unmappedCommMeters");
		unmappedCommMeters=unmcommMeters.size();
	}
	
	out.print(totalCommCount+","+unmappedCommMeters);
	System.out.print(totalCommCount+"-------------------------------------------"+unmappedCommMeters);
}catch(Exception e){
	e.printStackTrace();
}

%>