package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {

	
	public static void main(String[] args)
    {
		Map<String,Integer> map=new HashMap();
		System.out.println("----------------"+map.get("k1"));
    }

    public  Calendar firstDayOfLastWeek(Calendar c,int week)
    {
        c = (Calendar) c.clone();
        // last week
        c.add(Calendar.WEEK_OF_YEAR, week);
        // first day
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
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
}
