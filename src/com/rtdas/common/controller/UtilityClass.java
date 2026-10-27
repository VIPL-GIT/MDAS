package com.rtdas.common.controller;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class UtilityClass {
	public String decryptPass(String pass){
		String decPass="";
		try{
		
        String key = "smart123smart123"; // 128 bit key
        // Create key and cipher
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        // encrypt the text
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        System.out.println("---before encryption---->"+pass);
        decPass = new String(cipher.doFinal(pass.getBytes()));
        System.out.println("---after cecryption---->"+decPass);
		}catch(Exception e){
			e.printStackTrace();
		}
		return decPass;
	}
	
	public String encryptPassword(String pwd){
		String pass="";
		try{
		
        String key = "smart123smart123"; // 128 bit key
        // Create key and cipher
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        // encrypt the text
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(pwd.getBytes());
        pass=new String(encrypted);
		}catch(Exception e){}
		return pass;
	}

	public String addZeros(int id){
		String genId="";
		if(id<10) genId="0000"+id;
		else if(id<100) genId="000"+id;
		else if(id<1000) genId="00"+id;
		else if(id<10000) genId="0"+id;
		return genId;
	}
	public String getPreviousDay(String date){ //date should be in yyyy-MM-dd formate
		String result=date;
		try 
        {
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        	
        	result=sdf.format(sdf.parse(date).getTime()-24*60*60*1000);
        	
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
		return result;
	}
	public String addoneDay(String date){ //date should be in yyyy-MM-dd formate
		String result=date;
		try 
        {
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");        	
        	result=sdf.format(sdf.parse(date).getTime()+24*60*60*1000);
        	
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
		return result;
	}
	public String getPreviousMonth(String mn){ //date should be in yyyy-MM formate
		String result=mn;
		try 
        {
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");  
        	Calendar cal = Calendar.getInstance();
        	cal.setTime(sdf.parse(mn+"-01"));
        	cal.add(Calendar.MONTH, -1);
        	
        	result=sdf.format(cal.getTime());
        	
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
		return result;
	}
	public String addOneMonth(String mn){ //date should be in yyyy-MM formate
		String result=mn;
		try 
        {
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");  
        	Calendar cal = Calendar.getInstance();
        	cal.setTime(sdf.parse(mn+"-01"));
        	cal.add(Calendar.MONTH, 1);
        	
        	result=sdf.format(cal.getTime());
        	
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
		return result;
	}
}
