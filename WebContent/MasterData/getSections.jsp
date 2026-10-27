<%@page import="java.sql.*"%>
<%@page import="utils.DBUtil"%>

<%!
    PreparedStatement pstmt = null;
    ResultSet rs = null;
%>
<%
    try {
        String mandalid = request.getParameter("mandalid").toString();
        //String data = "0_Select Section";
        String data="";
        Class.forName("com.mysql.jdbc.Driver");//kumar
        String connectionUrl = "jdbc:mysql://localhost:3306/rec9"; //kumar
        //Connection con = DriverManager.getConnection(connectionUrl,"root","verticross@123");//kumar
        Connection con = new DBUtil().getConnection();
        PreparedStatement pstmt = con.prepareStatement("select * from section where mandalid=" + mandalid + "");
        rs = pstmt.executeQuery();
        while (rs.next()) {
            data = data + ":" + rs.getString("section_id") + "_" + rs.getString("sectionname");
        }

        // data = data +":abc:def";
        out.println(data);
        System.out.println("data is " + data);
    } catch (Exception e) {
        response.sendRedirect("errorPage.jsp?message=" + e.getMessage());
    }
%>