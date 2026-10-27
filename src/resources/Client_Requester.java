package resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;

public class Client_Requester {

    String ip = null;
    int port;
    String cmd = null;
   // public static String response_data = "";

//    public Client_Requester(String ip, int port, String cmd) throws IOException {
//        this.ip = ip;
//        this.port = port;
//        this.cmd = cmd;
//    }

    public static void main(String[] args) throws IOException {
//        Client_Requester m = new Client_Requester("192.168.2.111", 30000, "Time");
//        m.client_Handle();
    }

    public void client_Handle() {
        try {
            Socket sock = new Socket(ip, port);
            Client_Request_Thread(sock, cmd);
            Client_Responses_Thread(sock, cmd);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void Client_Request_Thread(Socket sock, String cmd) {
        try {

            PrintWriter out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
            out.println(cmd);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String  Client_Responses_Thread(Socket sock, String cmd) {
        String response_data="";      
        int c = 0;
        try {
            InputStream is = sock.getInputStream();
            while (c != -1) {
                c = is.read();
                if (c != -1) {

                    System.out.print((char) c);
                    response_data = response_data + (char) c;
                    
                    if(response_data.contains(":$ND"))
                    	break;

                }
            }//while
            System.out.println("-----------");
            System.out.println("response--->" + response_data);

            try {
            } catch (NullPointerException ne) {
                //ne.printStackTrace();
                response_data=ne.getMessage();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            response_data=e.getMessage();
        }
        return response_data;
        
    }
}
