<%-- 
    Document   : sldImage
    Created on : Dec 11, 2012, 12:08:14 PM
    Author     : root
--%>

<%@page import="com.sun.xml.internal.ws.api.message.saaj.SAAJFactory"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
    String path = "";
    ArrayList Param_Names = new ArrayList();
    Connection con = null;
    Statement stmt = null;
    
    ResultSet rs = null;
    ResultSet rs1 = null;
    String paramStr = "";
    String paramVal = "";
    try {
    	
    	
    	
    	// System.out.println("  sldimage page --------------------------------------  ");
    	
        con = DBUtil.getConnection();
        stmt = con.createStatement();
        //stmt1 = con.createStatement();
        String ss_id = request.getParameter("ss_id");
        // System.out.println("ss_id  ---->  "+ss_id);
        rs = stmt.executeQuery("select * from sld_path where ss_id=" +ss_id);
        
        while (rs.next()) {
            path = rs.getString("SLD_Path");
          //  System.out.println("path1" + path);
        }

        rs = stmt.executeQuery("select * from sld_params");
        while (rs.next()) {
            // System.out.println("hai");
            System.out.println("hhhh" + rs.getString("Param_Name"));
            paramStr = paramStr + "," + rs.getString("Param_Name");

            //Param_Names.add(rs1.getString("Param_Name"));

        }
        String sqlQry = "SELECT Short_Name AS sName FROM sld_params ORDER BY param_id";
        PreparedStatement pstmt = con.prepareStatement(sqlQry);
        rs = pstmt.executeQuery();
        
        while (rs.next()) {
            
            paramVal = paramVal + "," + rs.getString("sName");
           
            
            //System.out.println("paramVal"+paramVal);
        }
    } catch (Exception e) {
        //System.out.println("msg" + e.getMessage());
    }finally{
        DBUtil.connectionClose(con, stmt, rs);
    }

   // System.out.println("path" + path);
   // System.out.println(path + "#" + paramStr+"?"+paramVal);
    out.println(path + "#" + paramStr+"?"+paramVal);

%>
