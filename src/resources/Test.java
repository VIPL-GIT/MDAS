package resources;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
public class Test {
 
	 public void run() 
	    {
	        try 
	        {
	        	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	        	sdf.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
	        	System.out.println("current time-->"+sdf.format(new java.util.Date()));
	        }
	        catch(Exception e) 
	        {
	            e.printStackTrace();
	        }
	    }
	    public static void main(String[] args) 
	    {
	    	Test app = new Test();
	        app.run();
	    }
 
}
