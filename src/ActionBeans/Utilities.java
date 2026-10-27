

package ActionBeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utilities
{
public String[] Divide_String_By_7E7EA(String str)
 {
      String divide_tamperData_by_7E7E[] = str.split("7E7EA");;
    try{
      String StringToWrite = "";
      for(int i=0;i<divide_tamperData_by_7E7E.length;i++)
      {
           if(i==0 || divide_tamperData_by_7E7E[i].startsWith("7E"))
              StringToWrite = divide_tamperData_by_7E7E[i] +"7E";
           else
              StringToWrite = "7EA"+divide_tamperData_by_7E7E[i]+"7E";
           divide_tamperData_by_7E7E[i] = StringToWrite;
//         System.out.println(divide_tamperData_by_7E7E[i]);
      }//for(int i)
     }catch(Exception e){
         e.printStackTrace();
     }
      return divide_tamperData_by_7E7E;
 }//Divide_String_By_7E7EA()
 public static String DISCOM_JUNK_TEST(String str)
 {
     String postFix = "GVCL";
     String result = "";
   try{
       if(str.contains("ÿ") || str.contains("~") || str.length()!=5)
       {
           if(str.charAt(0)=='D' || str.charAt(0)=='M' || str.charAt(0)=='P' || str.charAt(0)=='U')
           {
               result = str.charAt(0)+postFix;
           }
           else
           {
               result = "ABSURD_DISCOM_NAME";
           }
       }
       else{
           return str;
       }
   }catch(Exception e){
       e.printStackTrace();
   }
       return result;
 }//DISCOM_JUNK_TEST()

 public String[] Divide_String_By_7E(String str)
 {
      String divide_tamperData_by_7E7E[] = str.split("7E7E");;
    try{
      String StringToWrite = "";
      for(int i=0;i<divide_tamperData_by_7E7E.length;i++)
      {
           if(i==0 || divide_tamperData_by_7E7E[i].startsWith("7E"))
              StringToWrite = divide_tamperData_by_7E7E[i]+"7E";
           else
              StringToWrite = "7E"+divide_tamperData_by_7E7E[i]+"7E";
           divide_tamperData_by_7E7E[i] = StringToWrite;
//         System.out.println(divide_tamperData_by_7E7E[i]);
      }//for(int i)
     }catch(Exception e){
         e.printStackTrace();
     }
      return divide_tamperData_by_7E7E;
 }//Divide_String_By_7E()


    public static String check_Validity(String value)
    {
        String result = "";
      try{
          Map map = new HashMap();
          map.put("FFFFFFFF","4294967295");
          map.put("FFFFFC18","4294966296");
          map.put("FFFFFFE5","4294967269");
          map.put("FFFFFFEB","4294967275");

          long A=0;
          if(value.contains("FF"))
              A = Long.parseLong(map.get(value).toString());
          else
              A = Long.parseLong(Integer.parseInt(value,16)+"");

          long B = 2147483647l;
          if(A>B)
          {
             result = "-"+getCompliment(A)+"";
          }
          else
             result = Integer.parseInt(value,16)+"";

      }catch(Exception e)
      {
          e.printStackTrace();
      }
      return result;
    }//check_Validity()

    public static long getCompliment(long data)
    {
        String compl = "";
        String s = Long.toBinaryString(data);
        // //System.out.println("binary..."+s);
        //System.out.println();
        for (int k = 0; k < s.length(); k++)
        {
            if (s.charAt(k) == '0')
            {
                compl = compl + "1";
            } else
            {
                compl = compl + "0";
            }
        }
        long l = 1;
        long k = Long.parseLong(compl, 2);
        String m = Long.toBinaryString(k + l);
        //System.out.println();
        long finalNo = Integer.parseInt(m, 2);
        return finalNo;
    }//getCompliment()

  public static int count(final String string, final String substring)
  {
     int count = 0;
     int idx = 0;

     while((idx = string.indexOf(substring, idx)) != -1)
     {
        idx++;
        count++;
     }
     return count;
  }//count()

  public static String get_MIOSCODE_OF_OBISCODE(String OBIS_Code)
  {
     String Mios_Code = "";
     Map<String,String> map = new HashMap<String,String>();
     String status = "";
    try{
       map.put("1","1");
       map.put("2","1");
       map.put("3","2");
       map.put("4","2");
       map.put("5","3");
       map.put("6","3");
       map.put("7","33");
       map.put("8","33");
       map.put("9","58");
       map.put("10","58");
       map.put("11","14");
       map.put("12","14");
       map.put("51","4");
       map.put("52","4");
       map.put("53","5");
       map.put("54","5");
       map.put("55","6");
       map.put("56","6");
       map.put("57","24");
       map.put("58","24");
       map.put("59","25");
       map.put("60","25");
       map.put("61","26");
       map.put("62","26");
       map.put("63","7");
       map.put("64","7");
       map.put("65","19");
       map.put("66","19");
       map.put("67","11");
       map.put("68","11");
       map.put("101","13");
       map.put("102","13");
       map.put("201","27");
       map.put("202","27");
       map.put("203","28");
       map.put("204","28");
       map.put("205","12");
       map.put("206","12");
       map.put("251","60");
       map.put("2102","60");
       map.put("2106","60");
       map.put("2104","60");
       map.put("2105","60");
       map.put("2103","60");
       map.put("2101","60");

       Mios_Code = map.get(OBIS_Code).toString();
       
    }catch(Exception e){
        e.printStackTrace();
    }
    return Mios_Code;
  }//get_MIOSCODE_OF_OBISCODE()

  public static String get_Tamper_Status(String eventCode)
  {
      Map<String,String> map = new HashMap<String,String>();
      String status = "";
    try{
        map.put("1","0");
        map.put("2","1");
        map.put("3","0");
        map.put("4","1");
        map.put("5","0");
        map.put("6","1");
        map.put("7","0");
        map.put("8","1");
        map.put("9","0");
        map.put("10","1");
        map.put("11","0");
        map.put("12","1");
        map.put("101","0");
        map.put("102","1");
        map.put("51","0");
        map.put("52","1");
        map.put("53","0");
        map.put("54","1");
        map.put("55","0");
        map.put("56","1");
        map.put("57","0");
        map.put("58","1");
        map.put("59","0");
        map.put("60","1");
        map.put("61","0");
        map.put("62","1");
        map.put("63","0");
        map.put("64","1");
        map.put("65","0");
        map.put("66","1");
        map.put("67","0");
        map.put("68","1");
        map.put("201","0");
        map.put("202","1");
        map.put("203","0");
        map.put("204","1");
        map.put("205","0");
        map.put("206","1");
        map.put("206","1");
        map.put("2102","1");
        map.put("2106","0");
        map.put("2104","1");
        map.put("2105","0");
        map.put("2103","1");
        map.put("2101","0");


        status = map.get(eventCode).toString();
    }catch(Exception e){
        e.printStackTrace();
    }
      return status;
  }//get_Tamper_Status
 public static String getSCALAR(String value)
 {
    HashMap<String,String> map = new HashMap<String,String>();
   try{
        map.put("00","1");
        map.put("FB","0.00001");
        map.put("FC","0.0001");
        map.put("FD","0.001");
        map.put("FE","0.01");
        map.put("FF","0.1");

//        map.put("01","10");     //correct
        map.put("01","0.01");    //Made for HT Consumer
        map.put("02","100");
//      map.put("03","1000");   //correct
        map.put("03","1");      //Made for HT Consumer
        map.put("04","10000");
        map.put("05","100000");
        map.put("06","1000000");
        map.put(null,"0.01");
                        
   }catch(Exception e){
       e.printStackTrace();
   }
    return map.get(value).toString();
  }//getSCALAR()
	public static long getValue(long value)
	{
	  	 long returnedValue;
		if(value>126)
		{
		     returnedValue = getCompliment(value);
		     return -returnedValue;
		}
		else
		  return value;
       }//getValue()

    public static String Check_For_Valid_Date_OR_Not(String date)
    {
       String Final_Date = " ";
        try{
            String date_split[] = date.split(" ");
            String Only_Date = date_split[0];
            String Only_Time = date_split[1];

            String Only_Date_Split[] = Only_Date.split("-");

            //----------Fetching current year ------------
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String System_Date  = sdf.format(new Date());
//            System.out.println("Current Year -->"+System_Date.substring(System_Date.lastIndexOf("-")+1));
            //----------End of fetching th ecurrent year --------------
            if(date.contains("00-00-2000"))
                Final_Date = " ";
            else if(date.contains("00-00-0000"))
                Final_Date = " ";                            
            else if(Only_Date_Split[0].length()!=2 ||
                    Only_Date_Split[1].length()!=2 ||
                    Only_Date_Split[2].length()!=4
                   )
                Final_Date = " ";
            else
                Final_Date = date;

//            System.out.println(date+"   Only_Date_Split[1] --->"+(Integer.parseInt(Only_Date_Split[2])<=Integer.parseInt(System_Date.substring(System_Date.lastIndexOf("-")+1)) &&
//               Integer.parseInt(Only_Date_Split[1]) >=0 && Integer.parseInt(Only_Date_Split[1]) <=12 &&
//               Integer.parseInt(Only_Date_Split[0]) >=0 && Integer.parseInt(Only_Date_Split[0]) <=31
//                    )+"   sysyear -->"+System_Date.substring(System_Date.lastIndexOf("-")+1));
            if((Integer.parseInt(Only_Date_Split[2])<=Integer.parseInt(System_Date.substring(System_Date.lastIndexOf("-")+1)) &&
               Integer.parseInt(Only_Date_Split[1]) >=0 && Integer.parseInt(Only_Date_Split[1]) <=12 &&
               Integer.parseInt(Only_Date_Split[0]) >=0 && Integer.parseInt(Only_Date_Split[0]) <=31
                    ))
                Final_Date = date;
            else
                Final_Date = " ";

        }catch(Exception e){
            e.printStackTrace();
        }
        return Final_Date;
    }//Check_For_Valid_Date_OR_Not()
 public String add_60_Minutes(String str,String Details_for_Packet_Missing,String file_in_srcDir){
    String dateFormat = "";
    try{
          SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
          int day = Integer.parseInt(str.substring(0,2));
          int mon = Integer.parseInt(str.substring(3,5));
          int year = Integer.parseInt(str.substring(6,10));

          int hrs = Integer.parseInt(str.substring(11,13));
          int min = Integer.parseInt(str.substring(14,16));
          int sec = Integer.parseInt(str.substring(17));

		Calendar c = Calendar.getInstance();
		c.set(year,mon-1,day,hrs,min,sec);//Meter starting time 1988 JAN 1st 00:00:00
		c.add(Calendar.MINUTE,(int)60);
//		c.add(Calendar.MINUTE,(int)15);
		Date date1 = c.getTime();
		dateFormat=sdf.format(date1);
//                System.out.println(dateFormat);
    }catch(Exception e){
        // Logging into Exception
        e.printStackTrace();
    }
    return dateFormat;
  }//add_60_Minutes()
 public String substract_Minutes(String str,int noOfMinutes){
    String dateFormat = "";
    try{
          SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
          int day = Integer.parseInt(str.substring(0,2));
          int mon = Integer.parseInt(str.substring(3,5));
          int year = Integer.parseInt(str.substring(6,10));

          int hrs = Integer.parseInt(str.substring(11,13));
          int min = Integer.parseInt(str.substring(14,16));
          int sec = Integer.parseInt(str.substring(17));

		Calendar c = Calendar.getInstance();
		c.set(year,mon-1,day,hrs,min,sec);//Meter starting time 1988 JAN 1st 00:00:00
		c.add(Calendar.MINUTE,(int)-(noOfMinutes));
//		c.add(Calendar.MINUTE,(int)15);
		Date date1 = c.getTime();
		dateFormat=sdf.format(date1);
//                System.out.println(dateFormat);
    }catch(Exception e){
        // Logging into Exception
        e.printStackTrace();
    }
    return dateFormat;
  }//add_60_Minutes()
 public String add_30_Minutes(String str)
  {
    String dateFormat = "";
    try{
          SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
          int day = Integer.parseInt(str.substring(0,2));
          int mon = Integer.parseInt(str.substring(3,5));
          int year = Integer.parseInt(str.substring(6,10));

          int hrs = Integer.parseInt(str.substring(11,13));
          int min = Integer.parseInt(str.substring(14,16));
          int sec = Integer.parseInt(str.substring(17));

		Calendar c = Calendar.getInstance();
		c.set(year,mon-1,day,hrs,min,sec);//Meter starting time 1988 JAN 1st 00:00:00
		c.add(Calendar.MINUTE,(int)30);
//		c.add(Calendar.MINUTE,(int)15);
		Date date1 = c.getTime();
		dateFormat=sdf.format(date1);
//                System.out.println(dateFormat);
    }catch(Exception e){
        e.printStackTrace();
    }
    return dateFormat;
  }//add_30_Minutes()
  public String add_1_Day(String str)
  {
    String dateFormat = "";
    try{
          SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
          int day = Integer.parseInt(str.substring(0,2));
          int mon = Integer.parseInt(str.substring(3,5));
          int year = Integer.parseInt(str.substring(6,10));

          int hrs = Integer.parseInt(str.substring(11,13));
          int min = Integer.parseInt(str.substring(14,16));
          int sec = Integer.parseInt(str.substring(17));

		Calendar c = Calendar.getInstance();
		c.set(year,mon-1,day,hrs,min,sec);//Meter starting time 1988 JAN 1st 00:00:00
		c.add(Calendar.MINUTE,(int)1440);
		Date date1 = c.getTime();
		dateFormat=sdf.format(date1);
//                System.out.println(dateFormat);
    }catch(Exception e){
        e.printStackTrace();
    }
    return dateFormat;
  }//add_1_Day()
public void moveFile2(File source, File dest) throws IOException
    {
        InputStream in = null;
        OutputStream out = null;
        File filePath = null;
        String fileName = null;
        filePath = dest.getParentFile();//get FileParentPath
        fileName = dest.getName();//get FileName
//        System.out.println("filePath >"+filePath);
        String[] completeFilename = fileName.split("_");
        String date = "";
        if (completeFilename[4].length() > 6)
        {
            date = completeFilename[4].substring(0, 2)
                   + "-"
                   + completeFilename[4].substring(2, 4)
                   + "-"
                   + "20"
                   + completeFilename[4].substring(4, 6);//get DD/MM/YY from file name
        }
        else
        {

            DateFormat dt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dt1 = new Date();
            date = dt.format(dt1);
        }
        File directryStrct = new File(filePath + System.getProperty("file.separator") + completeFilename[3] + System.getProperty("file.separator") + date);

        if (!directryStrct.exists())
            directryStrct.mkdirs();

        try {
                in = new FileInputStream(source);
//                System.out.println("directryStrct = "+directryStrct);
                out = new FileOutputStream(directryStrct + System.getProperty("file.separator") + fileName);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0)
                {
                  out.write(buf, 0, len);
                }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }
    }//moveFile222

    public void deletefile(File file)
    {
//        File f1 = new File(file);
        boolean success = file.delete();
        if (!success)
        {
            System.out.println("File Deletion failed........!");
        }
        else
        {
        }
    }//deletefile()

    public String getMLSuccessStatus(String dates,String Modem_Details)
    {
      String finalMLDates_Success = "";
     try{
        String mlDates[] = new Utilities().getMLDates(Modem_Details);
//        String dates = "26-11-2012,02-12-2012,12-12-2012,30-11-2012,29-11-2012,";
        for(int row=0;row<mlDates.length;row++)
        {
            if(dates.contains(mlDates[row]))
            {
              finalMLDates_Success = finalMLDates_Success + mlDates[row] + ",1,";
            }
            else
            {
             finalMLDates_Success = finalMLDates_Success + mlDates[row] + ",4,";
            }
        }//for
//        System.out.println("finalMLDates_Success = "+finalMLDates_Success);
     }catch(Exception e){
         e.printStackTrace();
     }
      return finalMLDates_Success;
    }//GET ML SUCCESS STATUS
    public String[] getMLDates(String Modem_Details)
    {
//      String Modem_Details = "MCT27106,FW166,MGVCL,DLMS_SECURE,A20812,23-12-12,09:03:05,A07TMH,120121126,120121209,120121212,120121130,120121128,CRCSTR,32332F31322F313230393A30323A3235";
      String finalMLDates_CRCFail = "";
      int mlCount = 0;
      String ml_dates[] = null;
      try{
        String Modem_Details_Split[] = Modem_Details.split(",");
        if((Modem_Details_Split.length==15)||(Modem_Details_Split.length==16))
            mlCount = 5;
        else if(Modem_Details_Split.length==14)
            mlCount = 4;
        else if(Modem_Details_Split.length==13)
            mlCount = 3;
        else if(Modem_Details_Split.length==12)
            mlCount = 2;
        else if(Modem_Details_Split.length==11)
            mlCount = 1;

         ml_dates = new String[mlCount];
//        System.out.println("mlCount = "+ mlCount);
        System.arraycopy(Modem_Details_Split, 8, ml_dates, 0, mlCount);
          for(int row=0;row<ml_dates.length;row++)
          {
              ml_dates[row] = ml_dates[row].substring(1);
              ml_dates[row] = ml_dates[row].substring(6) + "-" + ml_dates[row].substring(4,6) + "-" + ml_dates[row].substring(0,4);
          }//for
//        System.out.println("finalMLDates_CRCFail = "+finalMLDates_CRCFail);
      }catch(Exception e){
          e.printStackTrace();
      }
      return ml_dates;
    } // GetML dates
    public String getConversionFailed_MLDates(String Modem_Details)
    {
//      String Modem_Details = "MCT27106,FW166,MGVCL,DLMS_SECURE,A20812,23-12-12,09:03:05,A07TMH,120121126,120121209,120121212,120121130,120121128,CRCSTR,32332F31322F313230393A30323A3235";
      String finalMLDates_ConversionFail = "";
      int mlCount = 0;
      String ml_dates[] = null;
      try{
        String Modem_Details_Split[] = Modem_Details.split(",");
        if((Modem_Details_Split.length==15)||(Modem_Details_Split.length==16))
            mlCount = 5;
        else if(Modem_Details_Split.length==14)
            mlCount = 4;
        else if(Modem_Details_Split.length==13)
            mlCount = 3;
        else if(Modem_Details_Split.length==12)
            mlCount = 2;
        else if(Modem_Details_Split.length==11)
            mlCount = 1;

         ml_dates = new String[mlCount];
//        System.out.println("mlCount = "+ mlCount);
        System.arraycopy(Modem_Details_Split, 8, ml_dates, 0, mlCount);
          for(int row=0;row<ml_dates.length;row++)
          {
              ml_dates[row] = ml_dates[row].substring(1);
              ml_dates[row] = ml_dates[row].substring(6) + "-" + ml_dates[row].substring(4,6) + "-" + ml_dates[row].substring(0,4);
              finalMLDates_ConversionFail = finalMLDates_ConversionFail + ml_dates[row] + ",3,";
          }//for
//        System.out.println("finalMLDates_CRCFail = "+finalMLDates_CRCFail);
      }catch(Exception e){
          e.printStackTrace();
      }
      return finalMLDates_ConversionFail;
    } // GetML dates
public String[] divide_rawdata_by_2_bytes(String str)
 {
    String data[] = new String[(str.length())/4];
   try{
        for(int row=0;row<((str.length())/4);row++)
        {
           if(row==(str.length()/4-1))
           {
            data[row] = str.substring(row*4);
           }
           else
           {
            data[row] = str.substring((row*4),(4+(row*4)));
           }
//            System.out.println("row = "+row+"\t\t"+data[row]);
        }//for
   }catch(Exception e){
       e.printStackTrace();
   }
    return data;
 }//divide_rawdata_by_2_bytes()
    public boolean isJunkData(String str)
    {
       boolean dataStatus = false;
        try{
//          String str = "505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050505050FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
          String first2Bytes = str.substring(0,4);
//            System.out.println("first2Bytes = "+first2Bytes);
          String data[] = new Utilities().divide_rawdata_by_2_bytes(str.substring(4));
         int repeatCount = 0;
         for(int row=0;row<data.length;row++)
         {
            if(data[row].equals(first2Bytes))
                repeatCount++;
            if(repeatCount==20)
            {
                return true;
            }
         }//for
        }catch(Exception e){
            e.printStackTrace();
        }
       return dataStatus;
    }// check Junk Data Status
public String add_Oneday(String str){
    String dateFormat = "";
    try{
          SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
          int day = Integer.parseInt(str.substring(0,2));
          int mon = Integer.parseInt(str.substring(3,5));
          int year = Integer.parseInt(str.substring(6,10));

          int hrs = Integer.parseInt(str.substring(11,13));
          int min = Integer.parseInt(str.substring(14,16));
          int sec = Integer.parseInt(str.substring(17));

		Calendar c = Calendar.getInstance();
		c.set(year,mon-1,day,hrs,min,sec);//Meter starting time 1988 JAN 1st 00:00:00
		c.add(Calendar.MINUTE,(int)+1440);
//		c.add(Calendar.MINUTE,(int)15);
		Date date1 = c.getTime();
		dateFormat=sdf.format(date1);
//                System.out.println(dateFormat);
    }catch(Exception e){
        // Logging into Exception
        e.printStackTrace();
    }
    return dateFormat;
  }

public String previous_Day(String str)
  {
    String dateFormat = "";
    try{
          SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
          int day = Integer.parseInt(str.substring(0,2));
          int mon = Integer.parseInt(str.substring(3,5));
          int year = Integer.parseInt(str.substring(6,10));

		Calendar c = Calendar.getInstance();
                Calendar c1 = Calendar.getInstance();
		c.set(year,mon-1,day);//Meter starting time 1988 JAN 1st 00:00:00
                c1.setTimeInMillis(c.getTimeInMillis()-(24*60*60*1000));
		Date date1 = c1.getTime();
		dateFormat=sdf.format(date1);
//                System.out.println(dateFormat);
    }catch(Exception e){
        e.printStackTrace();
    }
    return dateFormat;
  }//add_1_Day()

}//class
