/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author USER
 */
public class SendSMS {
    public String sendSMS(String mobile, String message) {
        String status = "";
        String url="",user="",pass="",send="";
        try {
        	//--------------loading credentials from file
        	Properties p=new Properties(); p.load(this.getClass().getResourceAsStream("database.properties"));
        	//p.load(new FileInputStream("database.properties"));
        	url=p.getProperty("sms_url");
        	user=p.getProperty("sms_user");
        	pass=p.getProperty("sms_pass");
        	send=p.getProperty("sms_send");
        //------------------------------------------------	
            int c;
            String responseString = "";
            StringBuilder urlSB = new StringBuilder();   
//            urlSB.append("http://smsfb.fastbucksindia.com/sendsms.aspx?");
//            urlSB.append("mobile=9964321395");
//            urlSB.append("&");
//            urlSB.append("pass=MPSLNT");
//            urlSB.append("&");
//            urlSB.append("senderid=LNTPPV");
//            urlSB.append("&");
//            urlSB.append("to="+mobile);
//            urlSB.append("&");
//            urlSB.append("msg="+URLEncoder.encode(message, "UTF-8"));
//----------------------------------------------------------------------------------------
            urlSB.append(url);
            urlSB.append("uname="+user);
            urlSB.append("&");
            urlSB.append("pass="+pass);
            urlSB.append("&");
            urlSB.append("send="+send);
            urlSB.append("&");
            urlSB.append("dest="+mobile);
            urlSB.append("&");
            urlSB.append("msg="+URLEncoder.encode(message, "UTF-8"));
                    
            URL hp = new URL(urlSB.toString());                        
            URLConnection hpCon = hp.openConnection();
            InputStream input = hpCon.getInputStream();
            int len = hpCon.getContentLength();
            if (len > 0) {
                int i = len;
                while (((c = input.read()) != -1)) {
                    
                    responseString = responseString +  (char)c;
                }
                input.close();
//                System.out.println("responseString = "+responseString);
                //out.println("SMS Sent Successfully");
            } else {
                System.out.println("No Content Available");
            }
            System.out.println("responseString = "+responseString);
            if (responseString.contains("SMS sent Successfully to Mobile")
|| responseString.contains("success") || responseString.length()!=0) {
                status = "Success";
            }else{
                status="Failed";
            }
            
            System.out.println(mobile + "\t" + message + "\t" + status);
        } catch (Exception e) {
            //e.getMessage();
            e.printStackTrace();
//        System.out.println(e.getMessage());
        }
        return status;
    }
    public void sendMail(String empName, String toMail,String msgtosend) throws Exception {
        String host = "smtp.gmail.com";
//         System.out.println("toMail -->"+toMail);
       
        // System.out.println(f.getAbsolutePath());

        // Get system properties
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
        
        Authenticator auth = new SMTPAuthenticator();
        System.out.println("after authentication----------------");
        Session session = Session.getDefaultInstance(props, auth);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("maruthikumar6@gmail.com"));

        InternetAddress[] toAddress = new InternetAddress[1];
        toAddress[0] = new InternetAddress(toMail);
        message.setRecipients(Message.RecipientType.TO, toAddress);

        message.setSubject("PAYSLIP FOR THE MONTH OF ");
        BodyPart messageBodyPart = new MimeBodyPart();
//        String bodyContent = "Dear " + empName + ", \n\nPlease find attached PAYSLIP for the month of " + month + " " + year + ". \n\nUse your Date of Birth in DDMMYY format as password\n\nFor payroll related queries if any, please write to payroll@amitech.in";
        String bodyContent =msgtosend;
       

        messageBodyPart.setText(bodyContent);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        
        message.setContent(multipart);
        try {
            Transport.send(message);
            //log.info("Mail has been send Successfully for :" + toMail);
            System.out.println("Mail has been send Successfully to   :"+toMail);
        } catch (SendFailedException sfe) {
            message.setRecipients(Message.RecipientType.TO, sfe.getValidUnsentAddresses());
            Transport.send(message);
            System.out.println("Mail Sending Failed to "+toMail+" Due to" + sfe);
            //log.info("Mail Sending Failed to "+toMail+" Due to" + sfe);
        } catch (Exception e) {
            System.out.println("Mail Sending Failed to "+toMail+" Due to" + e);
            //log.info("Mail Sending Failed to "+toMail+" Due to" + e);
        }
    }//sendingMail
    
    private static class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username = "maruthikumar6@gmail.com";
            String password = "03031985";
            return new PasswordAuthentication(username, password);
        }
    }//SMTPAuthenticator
    
    public static void main(String args[]){
    	try{
        SendSMS sms=new SendSMS();
        sms.sendSMS("9985821595", "Hi, You will get your F & F soon.. Thanks ,HR ");
        //sms.sendMail( "Maruthi", "maruthikumar@npulseinc.com");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
