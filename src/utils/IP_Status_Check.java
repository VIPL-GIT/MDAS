/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author root
 */
public class IP_Status_Check 
{
    public int check_IP_Status(String ip_addr)
    {
        try
        {
            InetAddress inet = InetAddress.getByName(ip_addr);
            //System.out.println("Sending Ping Request to " + ip_addr);

            boolean status = inet.isReachable(5000); //Timeout = 5000 milli seconds

            if (status)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        catch (UnknownHostException e)
        {
            System.err.println("Host does not exists");
            return 0;
        }
        catch (IOException e)
        {
            System.err.println("Error in reaching the Host");
            return 0;
        }   
        //return 1;
    }
}
