<%@page import="ActionBeans.SendSMS_VIPL"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="ActionBeans.Client_getMobileNo"%>
<%@page import="java.sql.Connection,java.sql.ResultSet,java.sql.PreparedStatement,java.util.Map,java.util.HashMap,java.util.ArrayList"%>
<%@page import="java.util.Set,java.util.Iterator"%>
<%@page import="utils.DBUtil"%>
<%@page import="ActionBeans.Client_Requester" %>
<%@page import="ActionBeans.SMS_ModemApp" %>
<%@page import="java.io.*" %>
<%@page import="java.net.*" %>
<style>
    .thstyle{
        background-color: #8FA1B5; 
        color:#FFFFFF;
        font-size:14px;
        border-left:1px solid #FFFFFF;
        text-decoration:none;
        font-family: monospace;
        border-bottom: 1px solid #FFFFFF;
    }
    .trbg{
        background-color: #E8EBE2;
        border-bottom: 1px solid #FFFFFF;
    }
    .tdbg{
        border-bottom: 1px solid #FFFFFF;
        border-left: 1px solid #FFFFFF;
    }
    #district_11kv_load tr  td {
        background-color: #E8EBE2;
        border-bottom: 1px solid #FFFFFF;
        border-left: 1px solid #FFFFFF;
    }
</style> 
<%
    try {
        
    	System.out.println("smstomodem");
    	String selected = request.getParameter("meters");
        System.out.println("selected = "+selected);
        String requestToModem = request.getParameter("CTM");
        System.out.println("requestToModem = "+requestToModem);
        String requiredParams = request.getParameter("requiredParams");
        System.out.println("requiredParams = " + requiredParams);
        String mobileNo = "";
        String command = "";
        String a1, a2, a3, a4 = "";
        System.out.println("requestToModem = "+requestToModem);
        if (requestToModem.equals("EACB")) {
            requestToModem = "ACB1111";
        }else if (requestToModem.equals("DACB")) {
            requestToModem = "ACB0000";
        }else if (requestToModem.equals("PHIM")) {
            requestToModem = "PHI";
        }else if (requestToModem.equals("VOLTSET")) {
            requestToModem = "VLTG";
        }else if (requestToModem.equals("PFSET")) {
            requestToModem = "PF";
        }else if (requestToModem.equals("TEMPSET")) {
            requestToModem = "TMP";
        }else if (requestToModem.equals("POTA")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            requiredParams = sdf.format(new Date());
//        System.out.println(requiredParams);
        }
        else if (requestToModem.equals("FLGA")) {
//            System.out.println("requiredParams = " + requiredParams);
            String arr[] = requiredParams.split(",");
            a1 = arr[0];
            a2 = arr[1];
            a3 = arr[2] + "," + arr[3];

            while (a1.length() < 16) {
                a1 = a1 + ",";
            }
            while (a2.length() < 16) {
                a2 = a2 + ",";
            }
            while (a3.length() < 16) {
                a3 = a3 + ",";
            }
//            System.out.println("a1 = " + a1);
//            System.out.println("a2 = " + a2);
//            System.out.println("a3 = " + a3);
            requiredParams = a1 + a2 + a3 + "ABCDEFGHIJ1234567890";
        }
        else if(requestToModem.equals("FLGB")){
            while(requiredParams.length() <16){
                requiredParams = requiredParams + ","; 
            }
        }
        else if(requestToModem.equals("FLGI")){
            String arr[] = requiredParams.split(",");
            a1 = arr[0];
//            System.out.println("a1 = "+a1);
            a2 = arr[1];
//            System.out.println("a2 = "+a2);
            a3 = arr[2];
//            System.out.println("a3 = "+a3);
            a4 = arr[3];
//            System.out.println("a4 = "+a4);
            
            while(a3.length() < 6){
                a3 = a3 + ",";
            }
            requiredParams = a1+a2+a3+a4;
//            System.out.println("requiredParams = "+requiredParams);
        }
        System.out.println("Final requestToModem = "+requestToModem);
        System.out.println("requiredParamsrequiredParams = "+requiredParams);
        String responseData = "";
//        if (requiredParams.equalsIgnoreCase("NA") || requestToModem.equals("EACB")|| requestToModem.equals("DACB")) {
      SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy HH:mm:ss"); 
       String uniqueId=sdf.format(new java.util.Date()).split(" ")[1];
       uniqueId=uniqueId.replaceAll(":","");
       
      if (requiredParams.equalsIgnoreCase("NA")){
            command = "$"+uniqueId + requestToModem;
        } else {
            command = "$"+uniqueId + requestToModem + requiredParams;
        }
        System.out.println("selected command -------------> "+command);
%>
<table width="100%">
    <thead>
        <!--        <tr>
                    <th class="thstyle">Meter Serial No</th>
                    <th class="thstyle">Static IP</th>
                    <th class="thstyle">Status</th>
                </tr>-->
    </thead>
    <tbody>
        <%
            Client_getMobileNo cgm = new Client_getMobileNo();
            String modemSlno = "";

             //gets the mobile number 
               // mobileNo = cgm.getMobileNo(selected);
            //gets the IP address 
                mobileNo = cgm.getIPAddr(selected);    
                System.out.println("mobileNo ==== "+mobileNo);

          // through sms gateway
                SendSMS_VIPL smsVIPL = new SendSMS_VIPL();
               responseData = smsVIPL.sendSMS_VIPL(mobileNo.split(",")[0], command);
          //  tcp/ip
//                  responseData = smsVIPL.sendOD_TCPIP(mobileNo.split(",")[0], command);


                    if (responseData.equals("Done")) {
                    cgm.onDemandRequestToDB_VIPL(mobileNo.split(",")[2], mobileNo.split(",")[1],mobileNo.split(",")[0], command,uniqueId); 
                }

                if (responseData.equals("Done")) {
        %>
        <tr class="trbg">
            <td colspan="3" align="center"><%="<font color='green'>OD Request Sent</font>" + ""%></td>
        </tr>
        
        <%  
        } else {
        %>
        <tr class="trbg">
            <td colspan="3" align="center"><%="<font color='red'>OD Request Failed   ("+responseData+")</font>" + ""%></td>
        </tr>
        <%
                }
                //System.out.println("response--->" + responseData);
            } catch (Exception e) {
                out.println("<h4 color='red'>Error:</h4>" + e.getMessage());
            }
        %>
    </tbody>
</table>
