package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtilities {

	private static final String FORMAT = "%02d %02d:%02d:%02d";
	
	private static final String FORMAT_h = "%02d:%02d:%02d";
	
	private static final String FORMAT_d = "%02d:%02d:%02d";
	
	private static final String FORMAT_m = "%02d %02d:%02d";
	
	public  String parseTime(long milliseconds) {
		
		//System.out.println("--"+milliseconds);
		
	      return String.format(FORMAT,
	    		  TimeUnit.MILLISECONDS.toDays(milliseconds),
	              TimeUnit.MILLISECONDS.toHours(milliseconds) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds)),
	              TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds)),
	              TimeUnit.MILLISECONDS.toSeconds(milliseconds) - TimeUnit.MINUTES.toSeconds(
	              TimeUnit.MILLISECONDS.toMinutes(milliseconds)));
	   }
	
public  String parseTimeH(long milliseconds) {
		
		//System.out.println("--"+milliseconds);
		
	      return String.format(FORMAT_h,	    		 
	              TimeUnit.MILLISECONDS.toHours(milliseconds),
	              TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds)),
	              TimeUnit.MILLISECONDS.toSeconds(milliseconds) - TimeUnit.MINUTES.toSeconds(
	              TimeUnit.MILLISECONDS.toMinutes(milliseconds)));
	   }
	
public  String parseTimeD(long milliseconds) {
		
		//System.out.println("--"+milliseconds);
		
	      return String.format(FORMAT,
	    		  TimeUnit.MILLISECONDS.toDays(milliseconds),
	              TimeUnit.MILLISECONDS.toHours(milliseconds) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds)),
	              TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds)),
	              TimeUnit.MILLISECONDS.toSeconds(milliseconds) - TimeUnit.MINUTES.toSeconds(
	              TimeUnit.MILLISECONDS.toMinutes(milliseconds)));
	   }
public  String parseTime_m(long minutes) {
	
	//System.out.println("--"+milliseconds);
	
      return String.format(FORMAT_m,
    		  TimeUnit.MINUTES.toDays(minutes),
              TimeUnit.MINUTES.toHours(minutes) - TimeUnit.DAYS.toHours(TimeUnit.MINUTES.toDays(minutes)),
              TimeUnit.MINUTES.toMinutes(minutes) - TimeUnit.HOURS.toMinutes(TimeUnit.MINUTES.toHours(minutes)));
   }
public String substract_1_Day(String str){
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
		c.add(Calendar.MINUTE,(int)(-1440)*1);
               
		java.util.Date date1 = c.getTime();
		dateFormat=sdf.format(date1);
//                System.out.println(dateFormat);
    }catch(Exception e){
        e.printStackTrace();
    }
    return dateFormat;
  }

public String substract_30_mins(String str,int mins_interval){
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
		c.add(Calendar.MINUTE,(int)(-mins_interval)*1);
               
		java.util.Date date1 = c.getTime();
		dateFormat=sdf.format(date1);
//                System.out.println(dateFormat);
    }catch(Exception e){
        e.printStackTrace();
    }
    return dateFormat;
  }
	
public long getTimeDiff(String datetime){
	long diff=0;
	try{
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		diff=new java.util.Date().getTime()-sdf.parse(datetime).getTime();
		
	}catch(Exception e){
		
	}
	return diff;
}

public  String getLastDayOfTheMonth(String date) {
    String lastDayOfTheMonth = "";

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    try{
    java.util.Date dt= formatter.parse(date);
    Calendar calendar = Calendar.getInstance();  
    calendar.setTime(dt);  

    calendar.add(Calendar.MONTH, 1);  
    calendar.set(Calendar.DAY_OF_MONTH, 1);  
    calendar.add(Calendar.DATE, -1);  

    java.util.Date lastDay = calendar.getTime();  

    lastDayOfTheMonth = formatter.format(lastDay);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lastDayOfTheMonth;
}

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

    }catch(Exception e){
        e.printStackTrace();
    }
    return dateFormat;
  }

public  Calendar firstDayOfLastWeek(Calendar c,int week)
{
	try{
    c = (Calendar) c.clone();
    // last week
    c.add(Calendar.WEEK_OF_YEAR, week);
    // first day
    c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
	}catch(Exception e){
		e.printStackTrace();
	}
    return c;
}

public  Calendar lastDayOfLastWeek(Calendar c,int week)
{
    c = (Calendar) c.clone();
    c.add(Calendar.WEEK_OF_YEAR, week);
    // first day of this week
    c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
    // last day of previous week
    c.add(Calendar.DAY_OF_MONTH, -1);
    return c;
}


public static void main(String args[]){
		try{
			System.out.println(new DateUtilities().add_Oneday("01-02-2021 00:00:00"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
