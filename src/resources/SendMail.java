package resources;

import java.util.*;  

import javax.activation.*;  
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import utils.DBUtil;

public class SendMail {

	public String sendtomail(String user){
		 String result =null;
	try{
	   Connection con=DBUtil.getConnection();
       Statement stmt=con.createStatement();  
      
       String email=null;
       String new_password=null;
       ResultSet rs1=stmt.executeQuery("SELECT email,new_password FROM user where user_id like '"+user+"'"); 
       while(rs1.next()){
       email=rs1.getString("email");
       new_password=rs1.getString("new_password");
      }
       result = fromGmail(email,new_password); 
	}catch(Exception e){
		e.printStackTrace();
	}
	return result;
	}
	
	
	public String fromGmail(String email,String pwd){
		    
		    System.out.println(email+"  "+pwd);
		    String feedback="";
		    final String username = "mdas.verticross@gmail.com";
	        final String password = "7874567147";
	        
	        Properties prop = new Properties();
		    /*prop.put("mail.smtp.host", "outlook.office365.com");*/
		    prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	        
	 
	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("mdas.verticross@gmail.com"));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse(email)
	            );
	            message.setSubject("MDAS login password");
	            message.setText(" Your Password is "+pwd);
	            try {
	                Transport.send(message);
	                //log.info("Mail has been send Successfully for :" + toMail);
	                System.out.println("Mail has been send Successfully to   :"+email);
	                feedback="Mail has been send Successfully to :"+email;
	            } catch (SendFailedException sfe) {
	                message.setRecipients(Message.RecipientType.TO, sfe.getValidUnsentAddresses());
	                Transport.send(message);
	                System.out.println("Mail Sending Failed to "+email+" Due to" + sfe);
	                feedback="Mail Sending Failed to "+email+" Due to" + sfe;
	                //log.info("Mail Sending Failed to "+toMail+" Due to" + sfe);
	            } catch (Exception e) {
	                System.out.println("Mail Sending Failed to "+email+" Due to" + e);
	                feedback="Mail Sending Failed to "+email+" Due to" + e;
	                //log.info("Mail Sending Failed to "+toMail+" Due to" + e);
	            }

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	        return feedback;
	    }
		
	public static void main(String args[]){
		SendMail sms=new SendMail();
		sms.fromGmail("aavulashiva501@gmail.com","password");
	}
	
	}
	
	

	

