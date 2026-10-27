<%@page import="utils.DBUtil"%>
<%@ page import="java.sql.*"%>

<%!
  PreparedStatement pstmt = null;
  ResultSet rs = null;
%>
<%
    try{
        String division_id=request.getParameter("division_id").trim();
        String data ="0_Select Town";
        Connection con=new DBUtil().getConnection();
        pstmt = con.prepareStatement("select * from mandal where subdivid in(select subdivid from subdivision where divisionid="+division_id+") order by mandalname");
        rs = pstmt.executeQuery();
        while(rs.next())
            data = data +":"+rs.getString("mandalid")+"_"+rs.getString("mandalname");
     
        out.println(data);
    }catch(Exception e)
    {
       response.sendRedirect("errorPage.jsp?message="+e.getMessage());
        
    }
%>