
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="utils.DBUtil"%>

<%!
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  Connection con=null;
%>
<%
    try{
        String divid = request.getParameter("divid").toString();
        String data ="0_Select Town";
        con=new DBUtil().getConnection();
        PreparedStatement pstmt = con.prepareStatement("select * from mandal where subdivid in(select subdivid from subdivision where divisionid="+divid+")");
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