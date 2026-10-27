
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="utils.DBUtil"%>

<%!
  PreparedStatement pstmt = null;
  ResultSet rs = null;
%>
<%
    try{
       String feeder = request.getParameter("emp_id").toString();
        System.out.println("feeder id ---->"+feeder);
        //String data ="Select dtr";
        String data="";
          Connection con=new DBUtil().getConnection();
        PreparedStatement pstmt = con.prepareStatement("select dtrid,dtrname from dtr where feederId="+feeder+" order by dtrname");
        rs = pstmt.executeQuery();
        while(rs.next())
            data = data +":"+rs.getString("dtrid")+"_"+rs.getString("dtrname");

       // data = data +":abc:def";
        out.println(data);
        System.out.println("data is "+data);
    }catch(Exception e)
    {
        response.sendRedirect("errorPage.jsp?message="+e.getMessage());
    }
%>