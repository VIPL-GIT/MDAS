<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
<%@page import="DAO.ReadPropertiesFile"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Properties"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="utils.DBUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%-- <%@page import="FormBean.sldBean"%> --%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="iec104.Socket_Serialization"%>
<%@page import="java.io.ObjectInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%-- <%@page import="utils.IP_Status_Check"%> --%>
<%@page import="java.net.URL,java.net.UnknownHostException,java.net.URL,java.net.Socket,java.net.InetSocketAddress,java.net.HttpURLConnection,java.io.IOException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String rightclick_flag = "0";
    try {
        //       rightclick_flag = "1";
        //       rightclick_flag = ReadPropertiesFile.getPropertyValue("rightclick_flag");              Note:  Need to get from properties file
        //       System.out.println("rightclick_flag==>" + rightclick_flag);
    } catch (Exception e) {
        System.out.println("Error:" + e.getMessage());
    }
%>
<html>
    <head>        
        
    </head>
    <%!
        public static boolean deserialzeRTUStatus(String rtuName, String path) {
            boolean socket_flg = false;
            try {
                //System.out.println("path==>"+path);
                FileInputStream fin = new FileInputStream(path + "iec104//" + rtuName + ".ser");
                ObjectInputStream ois = new ObjectInputStream(fin);
                Socket_Serialization SS = (Socket_Serialization) ois.readObject();
                socket_flg = SS.socket_flg;
                ois.close();
            } catch (Exception ex) {
                //ex.printStackTrace();
                //System.out.println("exception==>"+ex.getMessage());
            }
            return socket_flg;
        }        
        public static boolean isInternetReachable()
        {
            try {
                //make a URL to a known source
                URL url = new URL("http://www.google.com");
                //open a connection to that source
                HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();
                //trying to retrieve data from the source. If there
                //is no connection, this line will fail
                Object objData = urlConnect.getContent();
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
            return true;
        }
    %>

    <%
        //out.println("1==>" + new Date());
        Date CurrDate;
        final String NEW_FORMAT = "yyyy/MM/dd";
        final String OLD_FORMAT = "dd-MM-yyyy";
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        NumberFormat twoDecFormat = new DecimalFormat("#0.00");
        NumberFormat fourDecFormat = new DecimalFormat("#0.0000");
        String str2 = "";
        String strMapVal = "";
        String date1 = "";
        String time1 = "";
        String path = "";
        String ss_id = request.getParameter("ss_id").toString().trim();
        int rtuid = 0;
        if (ss_id.length() > 0 || ss_id != null) {
            int s_id = Integer.parseInt(ss_id);
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String strFeeder = "";
            Iterator iter = null;
            String rtuName = "";
            String image_id = "";
            try {   
                con = DBUtil.getConnection();
                if (con != null) {
                    pstmt = con.prepareStatement("select rtu_id,rtu_nm from rtu where ss_id=?");
                    pstmt.setInt(1, s_id);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        rtuid = rs.getInt("rtu_id");
                        rtuName = rs.getString("rtu_nm");
                }
                    String tableName = "";
                    if (rightclick_flag.equals("1")) {
                        tableName = "rtu_di_data";
                    } else {
                        tableName = "rtu_ai_data";
                    }

                    //pstmt = con.prepareStatement("SELECT date,time,rtu_id FROM rtu_di_data WHERE rtu_id=? order by date desc,time desc LIMIT 1");
                    pstmt = con.prepareStatement("SELECT date,time,rtu_id,concat(DATE_FORMAT(date,'%d/%m/%Y'),' ',time) as dateandtime FROM " + tableName + " WHERE rtu_id=? order by date desc,time desc LIMIT 1");
                    //pstmt.setInt(1, s_id);
                    System.out.println("rtuid  -->  "+rtuid);
                    pstmt.setInt(1, rtuid);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        System.out.println(" ififififififififif ");
                        /*date1 = rs.getString("recv_date");
                        CurrDate = dateFormat2.parse(rs.getString("recv_date"));*/                        
                        date1 = rs.getString("date");
                        CurrDate = dateFormat2.parse(rs.getString("date"));
                        date1 = dateFormat2.format(CurrDate);
                        dateFormat2.applyPattern(OLD_FORMAT);
                        date1 = dateFormat2.format(CurrDate);
                        date1 = "LAST UPDATED: " + date1;
                        //time1 = rs.getString("recv_time");
                        time1 = rs.getString("time");
                        //rtuid=rs.getInt("rtu_id");                        
                    }   
               
                    List<Integer> feeder_id_list = new LinkedList<Integer>();
                    List<Integer> x_axis_list = new LinkedList<Integer>();
                    List<Integer> y_axis_list = new LinkedList<Integer>();
                    List<Integer> is_di_pointType_list = new LinkedList<Integer>();
                    List<Integer> point_srlno_list = new LinkedList<Integer>();
                    pstmt = con.prepareStatement("select * from sld_designer where ss_id=?");
                     System.out.println(" ififififififififif  ---->    "+s_id);
                    pstmt.setInt(1, s_id);
                    rs = pstmt.executeQuery();
                    int loop = 0;
                    double qd_val = 0;
                    while (rs.next()) {
                        point_srlno_list.add(rs.getInt("point_srlno"));
                        feeder_id_list.add(rs.getInt("feeder_id"));
                        x_axis_list.add(rs.getInt("x_cord_value"));
                        y_axis_list.add(rs.getInt("y_cord_value"));
                        is_di_pointType_list.add(rs.getInt("is_di_pointType"));
                        loop++;
                    }
                    System.out.println("size -->  "+point_srlno_list.size());
                    //out.println("4==>" + new Date());
                    for (int loop2 = 0; loop2 < point_srlno_list.size(); loop2++) {
                        String val = "";
                        qd_val = 0;
                        int param_no = 0;
                        int ai_srlno = 0;//rtuid 
                        String strText="Hz";
                        int yAxixDecrement = 30;
                        int feederid = feeder_id_list.get(loop2);
                        int pointSrlno = point_srlno_list.get(loop2);
                        /* for AI Point Type */
                        if(2==is_di_pointType_list.get(loop2)){
                                pstmt = con.prepareStatement("SELECT  * from feeder_dtls where feeder_id= ? ");
                                pstmt.setInt(1,feederid);
                                rs = pstmt.executeQuery();
                                if (rs.next()) {
                                    val =rs.getString("feeder_nm");
                                    System.out.println(val+"   ---> val");
                                }                            
                        }else if(0==is_di_pointType_list.get(loop2)){ 
                            ai_srlno = 0;
                            try {  
                                    int type_id=0;
                                    double input_min=0;
                                    double input_max=0;
                                    double output_min=0;
                                    double output_max=0;                          
                                    pstmt = con.prepareStatement("SELECT address,ai_srlno,multi_factor,input_range_min,input_range_max,output_range_min,output_range_max,type_id,engg_range_min,engg_range_max,description FROM analog_input_details WHERE rtu_id =" + rtuid + "  and feeder_id =? and ai_srlno = ? ");
                                    
                                    System.out.println("After  size size size  -->  "+feederid+"  <---> "+pointSrlno+" <---> ");
                                    double multi_fact = 1;
                                    pstmt.setInt(1,feederid);
                                    pstmt.setInt(2, pointSrlno);
                                    rs = pstmt.executeQuery();
                                    if (rs.next()) {
                                        ai_srlno = rs.getInt("ai_srlno");
                                        multi_fact = rs.getDouble("multi_factor");
                                        type_id = rs.getInt("type_id");
                                        input_max=rs.getDouble("input_range_max");
                                        input_min=rs.getDouble("input_range_min");
                                        output_min=rs.getDouble("engg_range_min");
                                        output_max=rs.getDouble("engg_range_max");
                                        strText = rs.getString("description");
                                        
                                    System.out.println(ai_srlno+"  <--->  "+multi_fact+" <---> "+input_max+" <---> "+input_min+" <---> "+output_min+" <---> "+output_max+" <---> "+strText);    
                                    }
                                    double val1=0;
                                    pstmt = con.prepareStatement("SELECT ai_value,qd_value FROM rtu_ai_data WHERE ai_srlno=? and rtu_id=? ORDER BY DATE DESC,TIME DESC LIMIT 1");
                                    System.out.println(rtuid+"  ......  ai_srlno  ........  "+ai_srlno);
                                    System.out.println("type_id  ........  "+type_id);
                                    pstmt.setInt(1, ai_srlno);
                                    pstmt.setInt(2, rtuid);
                                    rs = pstmt.executeQuery();
                                    if (rs.next()) {
                                        if (type_id == 9 || type_id == 11) {
                                            double in_diff = input_max - input_min;
                                            double out_diff = output_max - output_min;
                                            double per_point = out_diff / in_diff;//revers
                                          
                                            
                                            if (out_diff == 0) {
                                                per_point = 0;
                                            }
                                            System.out.println("out_diff ==>"+out_diff); 
                                            System.out.println("in_diff ==>"+in_diff);                                                                                       
                                            int ai_val = rs.getInt("ai_value");
                                            System.out.println("ai_value ==>"+ai_val);
                                            int intVal = ai_val;
                                            
                                        //    val1 = ((intVal - input_min) * per_point) + output_min;//rever   
                                            
                                            val1 =((intVal - input_min) * per_point) + output_min;
                                            
                                            System.out.println("val1==>"+val1);
                                      // System.out.println("checking  -->  "+twoDecFormat.format(45.6777));     
                                        }else{
                                            
                                            val1 = rs.getDouble("ai_value")*multi_fact; 
                                            if (val1 < 0) {
                                                   val1 = -(val1);
                                            }
                                        } 
                                        if (qd_val != 0) {
                                            val = "" + twoDecFormat.format(val1) +" "+strText+ "  (S)";
                                            System.out.println("val  ifififi  ==>"+val);
                                        } else {
                                            val = "" + twoDecFormat.format(val1) +" "+strText;
                                            System.out.println("val  elseelse  ==>"+val);
                                        }
                                    }
                                } catch (Exception ex) {
                                    out.println(" Find Last before31 exception   "+ex.getMessage());
                                }                            

                        }else{
                             System.out.println(" else else else ");
                            val = "<img src='images/red_icong.png' />";
                            int di_srlno = 0;
                            int statVal = 0;
                            int cmd_id = 0;
                            String rtu_id = "";
                            String description = "";
                            pstmt = con.prepareStatement("SELECT description,di_srlno,type_id,rtu_id FROM digital_input_details WHERE feeder_id=? and di_srlno=? and rtu_id =? ");
                            pstmt.setString(1, feederid + "");
                            pstmt.setInt(2, pointSrlno);
                            pstmt.setInt(3, rtuid);
                            System.out.println(feederid+" ssssssssss "+pointSrlno+"  ttttttttt  "+rtuid);
                            rs = pstmt.executeQuery();
                            if (rs.next()) {
                                di_srlno = rs.getInt("di_srlno");
                                cmd_id = rs.getInt("type_id");
                                rtu_id = rs.getString("rtu_id");
                                description = rs.getString("description");
                                
                                System.out.println("Result -->   "+di_srlno+" <---> "+cmd_id+" <---> "+rtu_id+" <---> "+description);
                            }
                            int descLen = description.length();
                            System.out.println("length  --> "+descLen);
                            if(descLen>1){
                                yAxixDecrement = ((descLen/2)*10)+2;
                                 System.out.println("ifififif     yAxixDecrement  --> "+yAxixDecrement);
                            }else{
                               yAxixDecrement = 110; 
                                System.out.println("elseelse yAxixDecrement  --> "+yAxixDecrement);
                            }
                            //System.out.println("yAxixDecrement==>"+yAxixDecrement);
                            if (di_srlno != 0) {
                                pstmt = con.prepareStatement("SELECT di_value FROM rtu_di_data WHERE di_srlno=? ORDER BY DATE DESC,TIME DESC LIMIT 1");
                                pstmt.setInt(1, di_srlno);
                                 System.out.println("di_srlno  --> "+di_srlno);
                                rs = pstmt.executeQuery();
                                if (rs.next()) {
                                    statVal = rs.getInt("di_value");
                                     System.out.println("statVal  --> "+statVal);
                                     System.out.println("cmd_id  --> "+cmd_id);
                                    image_id = rtu_id + "_" + di_srlno + "_" + cmd_id + "_" + statVal;
                                    if (cmd_id == 1) {
					if (statVal == 0) {   // "OPEN";   OFF
                                                val = "<img src='images/red_icong.png' id='" + image_id + "' />";
                                        } else if (statVal == 1) {
                                            //"CLOSE";    ON
                                            val = "<img src='images/green_iconn.png' id='" + image_id + "' />";
                                        }

                                    } else {
					if (statVal == 0) {   //undefined
                                            val = "<img src='images/brownUndef_iconn.png' id='" + image_id + "' />";
                                        } else if (statVal == 1) {
                                            //"OPEN";   OFF
                                            val = "<img src='images/red_icong.png' id='" + image_id + "' />";
                                        } else if (statVal == 2) {
                                            //"CLOSE";   ON
                                            val = "<img src='images/green_iconn.png' id='" + image_id + "' />";
                                        } else if (statVal == 3) {
                                            //"Transient";
                                            val = "<img src='images/yellowTrans_iconn.png' id='" + image_id + "' />";
                                        } else {
                                            //"Wrong";
                                            val = "<img src='images/blueWrong_iconn.png' id='" + image_id + "' />";
                                        }
                                    }
                                } else {
                                    val = "<img src='images/green_iconn.png' id='" + image_id + "' />";
                                }
                                //System.out.println("End of di status");
                            }

                        }

    if (val.length() > 0) {        
        int xAxisVal = x_axis_list.get(loop2);
        int yaxisVal = y_axis_list.get(loop2);
        
        /*if(xAxisVal<300){
            xAxisVal = xAxisVal-50;
        }else if(xAxisVal>300 && xAxisVal<400){
            xAxisVal = xAxisVal-10;
        }else if(xAxisVal>500 && xAxisVal<1000){
            xAxisVal = xAxisVal+40;
        }else if(xAxisVal>1000){
            xAxisVal = xAxisVal-200;
        }
        if(yaxisVal>1000){
           yAxixDecrement = yAxixDecrement;
        }*/
       //System.out.println("xAxisVal==>"+xAxisVal);
       if(2!=is_di_pointType_list.get(loop2)){System.out.println("ifififif  xAxisVal==>"+xAxisVal+" ---->  "+val+"  yaxisval  --> "+y_axis_list.get(loop2)+" "+yAxixDecrement+" value  --> "+val);
    %>
        <div style="color:red;border:0px solid #666;position:absolute;top:<%=xAxisVal%>px;left:<%=((y_axis_list.get(loop2))-yAxixDecrement)%>px;">
            <%=val%>
        </div> 
        <div class="se">
            <ul id="contextmenu" style="left: 686px; top: 220px; display: none;">
                <li id="open_id" onclick="sendDOCommand(0)">OPEN</li>
                <li id="close_id" onclick="sendDOCommand(1)">CLOSE</li>

            </ul>
        </div>    
    <%
       } else {System.out.println("else -------------->  "); %>
        <div style="color:red;border:0px solid #666;position:absolute;top:<%=xAxisVal%>px;left:<%=((y_axis_list.get(loop2))-yAxixDecrement)%>px;">
            <b> <a href="#" onclick="getFeederLiveDet('<%=feederid%>')"><%=val%></a></b></br>
        </div>         
     <%  }}
    }
    %>
    <% if (rightclick_flag.equals("1")) {%>
    <div style="color:red;border:0px solid #666;position:absolute;top:5px;left:635px;font-size:13px;font-family:verdana;z-index:5;">
        <b> <%=date1 + " " + time1%></b>
    </div>
    <%} else {%>
    <div style="color:red;border:0px solid #666;position:absolute;top:5px;left:735px;font-size:13px;font-family:verdana;">
        <b> <%=date1 + " " + time1%></b>
    </div>
    <%}%>
    <div style="color:red;border:0px solid #666;position:absolute;top:20px;left:650px;font-size:13px;font-family:verdana;">
        <b> <%= "                                                                  "%></b>
    </div>

    <%
        String online_time = "";
        String offline_time = "";
        String strVal = "";
        String strText = "";
        String strTime = "";

        if (deserialzeRTUStatus(rtuName.toString(), application.getRealPath("/"))) {
            online_time = "ONLINE";
            strText = " <img src='images/green_iconn.png'>";
            strVal = "<font color=green>" + online_time + "</font>";
        } else {
            offline_time = "OFFLINE";
            strText = " <img src='images/red_icong.png'>";
            strVal = offline_time;
        }
      boolean isReachable = isInternetReachable();
      String network_status = "Internet Status :";
      if(isReachable){
          network_status = network_status+" <img src='images/green_iconn.png'>";
      }else{
          network_status = network_status+" <img src='images/red_icong.png'>";
      }
    %>
    
    <% if (rightclick_flag.equals("1")) {%>    
    <div style="color:red;border:0px solid #666;position:absolute;top:200px;left:928px;font-size:13px;font-family:verdana;">
        <b> <%=strVal%></br>Refresh Time Interval:10 Sec</b>
        <br/><br/><%=network_status%>
        
    </div>
    <%} else {%>    
    <div style="color:red;border:0px solid #666;position:absolute;top:200px;left:1208px;font-size:13px;font-family:verdana;">
        <b> <%=strVal%></b>
    </div>
    <%
        strVal = "Refresh Time Interval:10 Sec";
    %>
    <div style="color:red;border:0px solid #666;position:absolute;top:220px;left:1238px;font-size:13px;font-family:verdana;width:165px;">
        <b> <%=strVal%><br/><br/><%=network_status%></b>
    </div>    
    <% }%>
    <%
        //strVal="Refresh Time Interval:10 Sec";
    %>

    <!--<div style="color:red;border:0px solid #666;position:absolute;top:5px;left:375px;font-size:13px;font-family:verdana;">
        <b> <%=strText%></b>
    </div>-->
    <%} else {%>
    <div style="color:red;border:0px solid #666;position:absolute;top:20px;left:650px;font-size:13px;font-family:verdana;">
        <b> <%= " Connection not available at  : " + new Date()%></b>
    </div>

    <% }
            } catch (Exception e) {
                out.println(" Find Last exception   " + e.getMessage());
                //System.out.println(" Find Last exception   ");
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.close();
                }
            }
        }
    %>
    <div id="image"><!--<img src="<%=path%>" alt="" width="1200" >-->
    </div>
    <input type="hidden" name="rightclick_flag" id="rightclick_flag" value="<%=rightclick_flag%>"/>
    <input type="hidden" name="role_id" id="role_id" value="<%=session.getAttribute("role_id")%>"/> 
    <% if (rightclick_flag.equals("1")) {%>  
    <div style="border:0px solid #666;position:absolute;top:0px;left:958px;">
        <%} else {%>
        <div style="border:0px solid #666;position:absolute;top:0px;left:1208px;">
            <%}%>
            <table border="1">
                <tr>
                    <td>OPEN</td><td align="center" valign="middle"><img src="images/red_icong.png" alt=""></td>
                </tr>
                <tr>
                    <td>CLOSE</td><td align="center" valign="middle"><img src="images/green_iconn.png" alt=""></td>
                </tr>
                <tr>
                    <td>TRANSIENT</td><td><img src="images/yellowTrans_iconn.png" alt=""></td>
                </tr>
                <tr>
                    <td>WRONG</td><td align="center" valign="middle"><img src="images/blueWrong_iconn.png" alt=""></td>
                </tr>
                <tr>
                    <td>UNDEFINED</td><td align="center" valign="middle"><img src="images/brownUndef_iconn.png" alt=""></td>
                </tr>
                           
            </table>
        </div>
       <% if (rtuid==29) { %>     
       <div style="border:0px solid #666;position:absolute;top:520px;left:120px;">
            <img src='images/indiacation.png'>
           <!-- <img border="0" src="/images/indiacation.png" width="204" height="158">-->
        </div>            
       <%  }  %>
       <% if (rightclick_flag.equals("1")) {%>        
       <style type="text/css">
           body {
                width: 900px;
                padding: 20px;
                margin: 0 auto;
                font-family: Helvetica, Arial, sans-serif;
                text-align: center;
            }
            a { color: #3399ff; }
            .tight {
                padding: 0px;
                margin: 0px;
            }
            ul#contextmenu {
                background: #333;
                color: #fff;
                width: 100px;
                font-size: 12px;
                display: none;
                position: absolute;
                list-style: none;
                padding: 0px;
                margin: 0px;
            }

            ul#contextmenu li {
                margin: 2px;
                padding: 3px;
                background: #000;
                cursor: pointer;
            }
            ul#contextmenu li:hover {
                color: #f60;
            }

            #image 
            {        
                border: 1px solid; width:900px;
            }
        </style>
        <% } %>
</body>
</html>