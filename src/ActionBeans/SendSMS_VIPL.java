/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActionBeans;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import com.google.gson.Gson;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author dell
 */
public class SendSMS_VIPL {

    public String sendSMS_VIPL(String mobile, String message) {

		Gson gson = new Gson();
		
        String status = "";

        try {

            int c;
            String responseString = "";
            StringBuilder urlSB = new StringBuilder();
            urlSB.append("http://sms.smsjosh.com/api/mt/SendSMS?");
            urlSB.append("user=verticross");
            urlSB.append("&");
            urlSB.append("password=Vipl@123");
            urlSB.append("&");
            urlSB.append("senderid=VTXIPL");//
            urlSB.append("&");
            urlSB.append("Channel=Trans");
            urlSB.append("&");
            urlSB.append("DCS=0");
            urlSB.append("&");
            urlSB.append("flashsms=0");
            urlSB.append("&");
            urlSB.append("number=91" + mobile);
            urlSB.append("&");
            urlSB.append("text=" + URLEncoder.encode(message, "UTF-8"));
            urlSB.append("&");
            urlSB.append("route=9");
           
            URL hp = new URL(urlSB.toString());
            URLConnection hpCon = hp.openConnection();
            InputStream input = hpCon.getInputStream();
            
            int len = hpCon.getContentLength();
            if (len > 0) {
                int i = len;
                while (((c = input.read()) != -1)) {
                    responseString = responseString + (char) c;
                }
                input.close();
            } else {
                System.out.println("No Content Available");
            }
            
            System.out.println("responseString = " + responseString);
            
            Response response = gson.fromJson(responseString, Response.class);

            System.out.println("response error code = "+response.getErrorCode());
            System.out.println("response error message = "+response.getErrorMessage());
           
            if(response.getErrorMessage().equals("Done")){
                status = response.getErrorMessage();
            	System.out.println(mobile + "\t" + message + "\t" + "Success"+ "\t" + response.getJobId());
            }else{
            status = response.getErrorMessage();
            System.out.println(mobile + "\t" + message + "\t" + "Failed");
            }
        } catch (Exception e) {
            //e.getMessage();
            e.printStackTrace();
//        System.out.println(e.getMessage());
        }
        return status;
    }
    
    public String sendSMS_VIPL_DB(String mobile, String message) {

		Gson gson = new Gson();
		
        String status = "";

        try {

            int c;
            String responseString = "";
            StringBuilder urlSB = new StringBuilder();
           // urlSB.append("http://183.82.123.227:8088/CS/sendSMS.jsp?");
            urlSB.append("http://183.82.123.227:8080/CS_New/sendIdeaSMS.jsp?");
            urlSB.append("mobile=" + mobile);
            urlSB.append("&");
            urlSB.append("message=" + URLEncoder.encode(message, "UTF-8"));
            
            URL hp = new URL(urlSB.toString());
            URLConnection hpCon = hp.openConnection();
            InputStream input = hpCon.getInputStream();
            
            int len = hpCon.getContentLength();
            if (len > 0) {
                int i = len;
                while (((c = input.read()) != -1)) {
                    responseString = responseString + (char) c;
                }
                input.close();
            } else {
                System.out.println("No Content Available");
            }
            
            System.out.println("responseString = " + responseString);
            
            Response response = gson.fromJson(responseString, Response.class);

            System.out.println("response error code = "+response.getErrorCode());
            System.out.println("response error message = "+response.getErrorMessage());
           
            if(response.getErrorMessage().equals("Done")){
                status = response.getErrorMessage();
            	System.out.println(mobile + "\t" + message + "\t" + "Success"+ "\t" + response.getJobId());
            }else{
            status = response.getErrorMessage();
            System.out.println(mobile + "\t" + message + "\t" + "Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }    
    
//-------------------------------------------------------------------------------------------------
   public String sendOD_TCPIP(String address, String message) {
      String status = "";
        try {            
            String responseString = "";
            Socket s=new Socket(address, 30000);
            OutputStream os=s.getOutputStream();
            os.write(message.getBytes());
            os.flush();
            
            status = "Done";
            System.out.println(address + "\t" + message + "\t" + "Success");
                        
        }catch(java.net.UnknownHostException uhex){
        	status="Unknown Host / Invalid IP";
        }
        catch (Exception e) {
            status = "failed";
            System.out.println(address + "\t" + message + "\t" + "Failed");
            e.printStackTrace();
        }
        return status;
    }    
    
   public String getFullString(String preStr,int length){
	   String finalStr=preStr;
	   try{
	        for(int x=0;x<=(length-finalStr.length());x++){
	          finalStr = finalStr + ",";
	        }//for
	        System.out.println(" finalStr = "+finalStr);
	    }catch(Exception e){
	      e.printStackTrace();
	     }
	      return finalStr;
	  }
    public static void main(String[] args) {

    	
        SendSMS_VIPL smsVIPL = new SendSMS_VIPL();
        
        String str = smsVIPL.sendSMS_VIPL("8121447021", "$123456DPCD");
        System.out.println("str = "+str);
    }
}
