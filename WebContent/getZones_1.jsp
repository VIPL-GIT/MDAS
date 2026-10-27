<%@page import="utils.DBUtil"%>
<%@ page import="java.sql.*"%>


<%!
  PreparedStatement pstmt = null;
  ResultSet rs = null;
%>
<%
    try{
        String discom = request.getParameter("discom_id").toString();
        System.out.println("discom---->"+discom);
        String data ="0_Select Zone";
        Class.forName("com.mysql.jdbc.Driver");//kumar
        String connectionUrl = "jdbc:mysql://localhost:3306/rec9"; //kumar
        //Connection con = DriverManager.getConnection(connectionUrl,"root","verticross@123");//kumar
        Connection con = new DBUtil().getConnection();
        PreparedStatement pstmt = con.prepareStatement("select * from zone where discom_id="+discom+"");
        rs = pstmt.executeQuery();
        while(rs.next()){
            data = data +":"+rs.getString("zoneid")+"_"+rs.getString("zonename");
            System.out.println("data is "+data);
            }
       // data = data +":abc:def";
        out.println(data);
        System.out.println("data is "+data);
    }catch(Exception e)
    {
        e.printStackTrace();
    }
%>