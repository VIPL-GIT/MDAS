
<%@page import="java.sql.*"%>

<%@page import="utils.DBUtil"%>

<%!
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  Connection con=null;
%>
<%
    try{
        String divisionname = request.getParameter("emp_id").toString();
        System.out.println("divisionname---->"+divisionname);
        String data ="Select Subdivision";
//        Class.forName("com.mysql.jdbc.Driver");//kumar
//        String connectionUrl = "jdbc:mysql://localhost:3306/rec9"; //kumar
//        Connection con = DriverManager.getConnection(connectionUrl,"root","verticross@123");//kumar
        con=new DBUtil().getConnection();
        PreparedStatement pstmt = con.prepareStatement("select * from subdivision where divisionid="+divisionname);
        rs = pstmt.executeQuery();
        while(rs.next())
            data = data +":"+rs.getString("subdivid")+"_"+rs.getString("subdivname");

       // data = data +":abc:def";
        out.println(data);
        System.out.println("data is "+data);
    }catch(Exception e)
    {
        response.sendRedirect("errorPage.jsp?message="+e.getMessage());
    }
%>