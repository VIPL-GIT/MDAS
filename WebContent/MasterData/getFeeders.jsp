<%@page import="utils.DBUtil"%>
<%@ page import="java.sql.*"%>

<%!
  PreparedStatement pstmt = null;
  ResultSet rs = null;
%>
<%
    try{
        String substation_id=request.getParameter("substation_id").trim();
        //String data ="Select Feeder";
        String data="";
        Connection con=new DBUtil().getConnection();
        pstmt = con.prepareStatement("select * from feeder where substationid="+substation_id);
        rs = pstmt.executeQuery();
        while(rs.next())
            data = data +":"+rs.getString("feederid")+"_"+rs.getString("feedername");
     
        out.println(data);
    }catch(Exception e)
    {
        //e.printStackTrace();
      response.sendRedirect("errorPage.jsp?message="+e.getMessage());
        
    }
%>