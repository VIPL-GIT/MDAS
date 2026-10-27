<%@page import="utils.DBUtil"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<% 
   // System.out.println(" sld_view_1 page  ");

    String strsql = "1";
    int sid = Integer.parseInt(request.getParameter("s_id"));
   // System.out.println("sid  --> "+sid);
    String strPath = "";
    strsql = "1";
    String strError = "";
    Connection con = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    boolean blnvalue = false;
    ResultSet rs = null;
    strsql = "step 2";
    try {
       con = DBUtil.getConnection();
     //  System.out.println("con  --> "+con);
        strsql = "step 3";

        if (con == null) {
            blnvalue = true;
        }
        while (blnvalue) {
            Thread.sleep(1000);
            con = DBUtil.getConnection();
            if (con != null) {
                blnvalue = false;
            }
            strsql = "step 34 in while loop ";
           // System.out.println(" in while loop blnvalue  " + blnvalue);
        }
        //System.out.println("sid --> "+sid);
        pstmt = con.prepareStatement("select SLD_Path from sld_path where ss_id=?");
        pstmt.setInt(1, sid);
        rs = pstmt.executeQuery();
        strsql = pstmt.toString();
        if (rs.next()) {
            strPath = rs.getString("SLD_Path");
        }
    } catch (Exception e) {
        strError = "Step 7";
    } finally {
        if (con != null) {
            con.close();
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="js/jquery.min.js"></script>

        <link rel="stylesheet" href="css/sld.css" type="text/css"/>
        <script src="js/sld_js.js"></script>
        <style>
            body{
                width:auto !important;
                margin:0px;
                padding:0px;
            }
            #view_part div{
                font-size:12px;
            }
            #image {        
               border: 1px solid;  width:1200px;
            }   
        </style>
        <script>
            function fun1(img1){
                alert("image : "+img1);
            }
            function view_wind1()
            {
                var ssid = window.opener.ss_id;
                document.getElementById("ss_id").value='<%=sid%>';
                view_sld();
            }
        </script>
           <script language="JavaScript" type="text/javascript" src="/js/sld_js.js"></script>
    </head>
    <body onload="view_wind1()">
        <input type="hidden" id="ss_id" name="ss_id" value="<%=sid%>"/>  
        <div id="view_part" style="min-width:1300px;">
        </div>


        <div id="loading_div" >
            <div id="image">
                <img src=<%=strPath%> alt="" width="1200px" height="800px">
            </div>                                 
        </div>
        <script>
            var int = self.setInterval("view_sld()",5000);
        </script>
    <jsp:include page="model_window_For_FeederAIs.jsp" />
    <script>
        function m_window(x1,y1)
        {

        }
    </script>        


    </body>
</html>