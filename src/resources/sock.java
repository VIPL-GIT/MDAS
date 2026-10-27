package resources;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class sock {
	
	private static final long serialVersionUID = 1L;
	private static Socket socket;
	
   
 
    public static String sendmessage(String command)
    {
    	String result="";
        try
        {
            String host = "132.148.131.31";
            int port = 8024;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
 
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
 
            //String number = "rdevCmd 5000 #SMT01Status#";
            //String number =  command;
            
            String sendMessage = command + "\n";
            bw.write(sendMessage);
            bw.flush();
            result="Message sent to the server : "+sendMessage;
            System.out.println("Message sent to the server : "+sendMessage);
 
            //Get the return message from the server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            result=result+"\nMessage received from the server : "+message;
            System.out.println("Message received from the server : " +message);
            socket.close();
        }
        catch (Exception exception) 
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }
}
