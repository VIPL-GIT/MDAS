<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>        
    <title>fine SCADA</title>
    <script type="text/javascript" src="js/jquery.min_1.js"></script>
    <script type="text/javascript" src="js/jqueryslidemenu.js"></script>     
    <!-- STYLESHEETS -->
    <!--<link rel="stylesheet" media="screen" href="css/reset.css" />-->
    <link rel="stylesheet" media="screen" href="css/grids.css" />
    <link rel="stylesheet" media="screen" href="css/style.css" />
    <!--<link rel="stylesheet" media="screen" href="css/jquery.uniform.css" />-->
    <!--<link rel="stylesheet" media="screen" href="css/light_blue_style.css" />-->

    <!-- jQplot CSS -->

    <!-- jQplot CSS END -->
    <script src="js/highcharts.js"></script>
    <style type = "text/css">
        #loading-container {position: absolute; top:50%; left:50%;}
        #loading-content {width:800px; text-align:center; margin-left: -400px; height:50px; margin-top:-25px; line-height: 50px;}
        #loading-content {font-family: "Helvetica", "Arial", sans-serif; font-size: 18px; color: black; text-shadow: 0px 1px 0px white; }
        #loading-graphic {margin-right: 0.2em; margin-bottom:-2px;}
        #loading {background-color: #eeeeee; height:100%; width:100%; overflow:hidden; position: absolute; left: 0; top: 0; z-index: 99999;}
    </style>
    </head>


    <%
    
        System.out.println(" view feeder insta data page ----------------------------------------------------------------  ");
        //out.println("1==>" + new Date());
       NumberFormat twoDecFormat = new DecimalFormat("#0.00");
        String strFeeder_id = request.getParameter("pFeeder_id").toString().trim();
        System.out.println(" strFeeder_id " + strFeeder_id);
        try{
        if (strFeeder_id.length() > 0 || strFeeder_id != null) {
            int feede_id = Integer.parseInt(strFeeder_id);
            Connection con = null;
            PreparedStatement pstmt = null;
            PreparedStatement pstmt1 = null;
            ResultSet rs = null; 
            ResultSet rs1 = null; 
                                
            con = DBUtil.getConnection();           
             //out.println("3==>" + new Date());
            //we should comment this after testing
               //     feede_id=169;
            //=================================
                    %>
                    <center>
       <div style="overflow:scroll;height:80%;width:100%;overflow:auto">                 
        <table class="uiTable" id="table_disp" border="0" cellpadding="0" cellspacing="0" width="50%">
            <thead><tr>
                    <th colspan="2"><b>Instantaneous Data </b></th>
                        
                </tr>
                    <tr>
                        <th align="left"><b>Description</b></th>
                        <th align="left"><b>Value</b></th>
                    </tr>
            </thead>
            <tbody>
                    <% try{
                   int type_id=0;
                   double input_min=0;
                   double input_max=0;
                   double output_min=0;
                   double output_max=0;
                   int ai_srlno = 0;//rtuid 
                   String strText="Hz"; //out.println("4==>"+feede_id); 
                   String sql = "SELECT address,ai_srlno,multi_factor,input_range_min,input_range_max,output_range_min,output_range_max,type_id,engg_range_min,engg_range_max,description FROM analog_input_details WHERE feeder_id =?  " ;
                   pstmt = con.prepareStatement(sql);
                   pstmt.setInt(1,feede_id);//out.println(feede_id+"5==>"+sql); 
                   double multi_fact = 1;
                   double qd_val = 0;
                   String val = "";
                   String valDesc = "";
                   String strDesc="";
                   rs = pstmt.executeQuery();
                   while (rs.next()) {
                     ai_srlno = rs.getInt("ai_srlno");
                     System.out.println(" ai_srlno  ==>  "+ai_srlno);
                     multi_fact = rs.getDouble("multi_factor");
                     type_id = rs.getInt("type_id");
                     input_max=rs.getDouble("input_range_max");
                     //System.out.println("input_max==>"+input_max);
                     input_min=rs.getDouble("input_range_min");
                     output_min=rs.getDouble("engg_range_min");
                     output_max=rs.getDouble("engg_range_max");
                    //System.out.println("output_max==>"+output_max);
                    //System.out.println("output_min==>"+output_min);
                     strDesc = rs.getString("description");
                     double val1=0;
                     pstmt1 = con.prepareStatement("SELECT ai_value,qd_value FROM rtu_ai_data WHERE ai_srlno=?  ORDER BY DATE DESC,TIME DESC LIMIT 1");
                     pstmt1.setInt(1, ai_srlno);
                    //pstmt.setInt(2, rtuid);
                    rs1 = pstmt1.executeQuery();
                    if (rs1.next()) {
                      if (type_id == 9 || type_id == 11) {
                           double in_diff = input_max - input_min;
                           double out_diff = output_max - output_min;
                           double per_point = out_diff / in_diff;//revers
                           if (out_diff == 0) {
                              per_point = 0;
                           }
                           //System.out.println("out_diff ==>"+out_diff); 
                          //System.out.println("in_diff ==>"+in_diff);                                                                                       
                          int ai_val = rs1.getInt("ai_value");
                         //System.out.println("ai_value ==>"+ai_val);
                          int intVal = ai_val;
                          val1 = ((intVal - input_min) * per_point) + output_min;//rever                                                                          
                          //System.out.println("val1==>"+val1);
                       }else{ 
                            //val1 = rs.getDouble("ai_value")*multi_fact; 
                            val1 = rs1.getDouble("ai_value"); 
                            if (val1 < 0) {
                                val1 = -(val1);
                            }
                       } 
                       qd_val = rs1.getDouble("qd_value");
                       if (qd_val != 0) {
                          val = "" + twoDecFormat.format(val1) +" "+strText+ "  (S)";
                       } else {
                          val = "" + twoDecFormat.format(val1) +" "+strText;
                       }
                      //System.out.println(" val  : "+val);
                    %>         
              <tr><td><%=strDesc%></td><td><%=val%></td></tr>                                        
           <%        }
                 }
              } catch (Exception ex) {
                      out.println(" Find Last before31 exception   "+ex.getMessage());
              }%>
         </tbody>
        </table></div>  </center> 
    <% } } catch (Exception e) {
                out.println(" Find Last exception   " + e.getMessage());
                //System.out.println(" Find Last exception   ");
                e.printStackTrace();
            } finally {
            } %>

</body>
</html>