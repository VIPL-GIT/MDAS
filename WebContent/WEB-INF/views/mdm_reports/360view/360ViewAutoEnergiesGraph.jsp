<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.rtdas.mdm.models.*" %>
<%@page import="com.rtdas.meterdata.beans.*" %>
<%@page import="java.util.*" %>
<%
String totalData="";
try{
	List<AutoResetEnergies> result=(List<AutoResetEnergies>)request.getAttribute("result");
	
	String isError=(String)request.getAttribute("error");
	if(isError!=null){
		out.println("error:<p class='error'>"+isError+"</p>");
	}else{
String header="KWH,KVAH,KVARH_Lag,KVARH_Lead,MDKW,MDKVA";


String time="",dataString="";
for(AutoResetEnergies auto:result){
	
	time=time+","+auto.getBilling_timestamp().split(" ")[0];	
}

for(int i=0;i<header.split(",").length;i++){
	String value=header.split(",")[i];	
		
		if(value.equals("KWH")){
			for(AutoResetEnergies auto:result){
			value=value+","+auto.getKWH();
			}
		}
	 if(value.equals("KVAH")){
		 for(AutoResetEnergies auto:result){
			value=value+","+auto.getKVAH();
		 }
	 }
		if(value.equals("KVARH_Lag")){
			for(AutoResetEnergies auto:result){
			value=value+","+auto.getKVARH_Lag();
			}
		}
	 if(value.equals("KVARH_Lead")){
		 for(AutoResetEnergies auto:result){
			value=value+","+auto.getKVARH_Lead();
		 }	 
		
	}
	 if(value.equals("MDKW")){
		 for(AutoResetEnergies auto:result){
			value=value+","+auto.getMDKW();
		 }	 
		
	}
	 if(value.equals("MDKVA")){
		 for(AutoResetEnergies auto:result){
			value=value+","+auto.getMDKVA();
		 }	 
		
	}
	
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

  