/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

public class EncryptPassword {

    public String encrypt(String password) {
        String algorithm = "SHA-1";
        String encoding = "UTF-8";
        MessageDigest msgDigest = null;
        String hashValue = null;
        try {
            msgDigest = MessageDigest.getInstance(algorithm);
            msgDigest.update(password.getBytes(encoding));
            byte rawByte[] = msgDigest.digest();
            hashValue = (new BASE64Encoder()).encode(rawByte);
System.out.println("Encrypted value--->"+hashValue);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm Exists");
        } catch (UnsupportedEncodingException e) {
            System.out.println("The Encoding Is Not Supported");
        }//end catch
        return hashValue;
    }//end method
    public static void main(String args[]){
    	try{
    		 System.out.println(new EncryptPassword().encrypt("password"));
    	}catch(Exception e){
    		
    	}
    	
    }
}
