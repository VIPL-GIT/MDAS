<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="utils.DBUtil"%>
<%@page import="utils.HomeBarGraph"%>
<%@page import="java.util.Date"%>
<%@ page import="java.sql.*"%>
<%
    /* String selectedLevel = request.getParameter("selectedLevel").trim();
    int selectedId = Integer.parseInt(request.getParameter("selectedId"));
    String selectedType = request.getParameter("selectedType").trim();
    String selectedDate = request.getParameter("selectedDate").trim();
    System.out.println("in bar graph "+selectedLevel + "----" + selectedId + "----" + selectedType + "----" + selectedDate); */    
    String  selectedDate = "";   
    List<Integer> childIds=null;
    try{
    	childIds=(List<Integer>)session.getAttribute("childIds");
    }catch(Exception e){
    	response.sendRedirect("/login.epx");
    }
    if (selectedDate.isEmpty()) {
        SimpleDateFormat formDate = new SimpleDateFormat("dd-MM-yyyy");
        selectedDate = formDate.format(new Date());
        //System.out.println("selectedDate in bargraph if ================ " + selectedDate);
    }else{
     selectedDate = selectedDate.substring(3,5) + "-" + selectedDate.substring(0,2) + "-" +selectedDate.substring(6,10) ;   
     //System.out.println("selectedDate in bargraph else ================ " + selectedDate);
    }
    
    HomeBarGraph hbg = new HomeBarGraph();
    // hbg.communicatingMeters("2017/06/14");
//    String sdate = selectedDate.substring(6, 10) + "/" + selectedDate.substring(3, 5) + "/" + selectedDate.substring(0, 2);
   // String sdate = selectedDate.substring(6, 10) + "-" + selectedDate.substring(3, 5) + "-" + selectedDate.substring(0, 2);
       String sdate=selectedDate;
    
    // Map<String,String> resultMap = hbg.getMappedMeters(selectedId+"");

     String comapny=hbg.getCompany(childIds);

     String communication_list = hbg.communicatingMeters(sdate,childIds,comapny);
     String Non_communication_list = hbg.nonCommunicatingMeters(sdate,childIds,comapny);
     String Never_communication_list = hbg.neverCommunicatingMeters(sdate,childIds,comapny);
     String mcf_list = hbg.mcfMeters(sdate,childIds,comapny);

     System.out.println("Bar ---------"+communication_list + ";" + Non_communication_list + ";" + Never_communication_list);
   
    out.println(communication_list + ";" + Non_communication_list + ";" + Never_communication_list+";"+mcf_list);
   
      
%>
