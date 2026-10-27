<%@page import="utils.DBUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
try
{
    int ss_id = Integer.parseInt(request.getParameter("ss_id"));
    System.out.println("ss_id  --> "+ss_id);
    con = DBUtil.getConnection();
    pstmt = con.prepareStatement("SELECT * FROM feeder_dtls WHERE rtu_id IN (SELECT rtu_id FROM rtu WHERE ss_id=?)");
    pstmt.setInt(1, ss_id);
    rs = pstmt.executeQuery();
    out.println("0,Feeder;");
    while(rs.next())
    {
    	System.out.println("feeders list   "+rs.getString("feeder_id")+","+rs.getString("feeder_nm")+";");
        out.println(rs.getString("feeder_id")+","+rs.getString("feeder_nm")+";");
    }
}
catch(Exception e)
{
    e.printStackTrace();
}
finally
{
    if(con!=null)
        con.close();
}
%>