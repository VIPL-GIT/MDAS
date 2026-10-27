<%@page import="utils.DBUtil"%>
<%@ page import="java.sql.*"%>

<%!
  PreparedStatement pstmt = null;
  ResultSet rs = null;
%>
<%
    try{
        String zone_id=request.getParameter("zone_id").trim();
        String data ="-1_Select Circle";
        Class.forName("com.mysql.jdbc.Driver");//kumar
        String connectionUrl = "jdbc:mysql://localhost:3306/rec9"; //kumar
        //Connection con = DriverManager.getConnection(connectionUrl,"root","verticross@123");//kumar
        Connection con=new DBUtil().getConnection();
        System.out.println("select * from circle where zoneid="+zone_id);
        pstmt = con.prepareStatement("select * from circle where zoneid="+zone_id);
        rs = pstmt.executeQuery();
        while(rs.next())
            data = data +":"+rs.getString("circleid")+"_"+rs.getString("circlename");
            
        out.println(data);
    }catch(Exception e)
    {
      response.sendRedirect("errorPage.jsp?message="+e.getMessage());
        
    }
%>