<%-- 
    Document   : getFeederPointsData.jsp
    Created on : 8 Apr, 2014, 9:57:47 AM
    Author     : Sreenu
--%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<% 
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    System.out.println("get feeders point jsp  -------------------------------");
    
    try{
        con = DBUtil.getConnection();
        String feeder_id = request.getParameter("feeder_id");
        String point_type = request.getParameter("point_type");
        System.out.println("point_type"+point_type); 
        
        String point_table_name = "analog_input_details";
        String sqlQuery = "";        
        sqlQuery = "select ai_srlno as point_srlno,description FROM analog_input_details WHERE feeder_id="+feeder_id;
        if("1".equals(point_type)){
            sqlQuery = "select di_srlno as point_srlno,description FROM digital_input_details WHERE feeder_id="+feeder_id;           
        }
        System.out.println("sqlQuery --> "+sqlQuery);
        String json = null ;
        Map<String, String> pointsMap = new LinkedHashMap<String, String>();        
        pstmt = con.prepareStatement(sqlQuery);
        rs = pstmt.executeQuery();
        while(rs.next()){
           pointsMap.put(rs.getString("point_srlno"),rs.getString("description"));
         //  System.out.println(rs.getString("point_srlno")+"  <--->  "+rs.getString("description"));
        }
        json= new Gson().toJson(pointsMap);
        System.out.println("json===>"+json);
        out.print(json);
    }catch(Exception ex){
        ex.printStackTrace();
    }finally{
      DBUtil.connectionClose(con, pstmt, rs);
    }   
 %>
