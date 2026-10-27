<%@page import="utils.DBUtil"%>
<%@ page import="java.sql.*"%>

<%!
  PreparedStatement pstmt = null;
  ResultSet rs = null;
%>
<%
    try{
        String section_id=request.getParameter("section_id").trim();
        //String data ="0_Select SubStation";
        String data="";
                Class.forName("com.mysql.jdbc.Driver");//kumar
        String connectionUrl = "jdbc:mysql://localhost:3306/rec9"; //kumar
       // Connection con = DriverManager.getConnection(connectionUrl,"root","verticross@123");//kumar
        Connection con=new DBUtil().getConnection();
        pstmt = con.prepareStatement("select * from substation where section_id="+section_id);
        rs = pstmt.executeQuery();
        while(rs.next())
            data = data +":"+rs.getString("substationid")+"_"+rs.getString("substationname");
     
        out.println(data);
    }catch(Exception e)
    {
       response.sendRedirect("errorPage.jsp?message="+e.getMessage());
        
    }
%>