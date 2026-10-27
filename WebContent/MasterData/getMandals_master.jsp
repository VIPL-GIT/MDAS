<%@page import="java.sql.*"%>
<%@page import="utils.DBUtil"%>

<%!
  PreparedStatement pstmt = null;
  ResultSet rs = null;
%>
<%
    try{
        String subdivid = request.getParameter("subdivid").toString();
        System.out.println("subdivisionname---->"+subdivid);
        //String data ="0_Select Town";
        String data="";
        Class.forName("com.mysql.jdbc.Driver");//kumar
        String connectionUrl = "jdbc:mysql://localhost:3306/rec9"; //kumar
        //Connection con = DriverManager.getConnection(connectionUrl,"root","verticross@123");//kumar
        Connection con=new DBUtil().getConnection();
        PreparedStatement pstmt = con.prepareStatement("select * from mandal where subdivid="+subdivid+" order by mandalname");
        rs = pstmt.executeQuery();
        while(rs.next())
            data = data +":"+rs.getString("mandalid")+"_"+rs.getString("mandalname");

       // data = data +":abc:def";
        out.println(data);
        System.out.println("data is "+data);
    }catch(Exception e)
    {
       response.sendRedirect("errorPage.jsp?message="+e.getMessage());
    }
%>