<%@page import="utils.DBUtil"%>
<%@ page import="java.sql.*"%>

<%!
  PreparedStatement pstmt = null;
  ResultSet rs = null;
%>
<%
    try{
        String circle_id=request.getParameter("circle_id").trim();
        String data ="0_Select Division";
        Class.forName("com.mysql.jdbc.Driver");//kumar
        String connectionUrl = "jdbc:mysql://localhost:3306/rec9"; //kumar
        //Connection con = DriverManager.getConnection(connectionUrl,"root","verticross@123");//kumar
        Connection con=new DBUtil().getConnection();
        pstmt = con.prepareStatement("select * from division where circleid="+circle_id+" order by divisionname");
        rs = pstmt.executeQuery();
        while(rs.next())
            data = data +":"+rs.getString("divisionid")+"_"+rs.getString("divisionname");
     
        out.println(data);
    }catch(Exception e)
    {
       response.sendRedirect("errorPage.jsp?message="+e.getMessage());
        
    }
%>