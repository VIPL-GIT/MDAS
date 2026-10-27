package resources;
import java.util.*;

import org.springframework.core.annotation.Order;

import java.lang.reflect.*;

public class Utility {

	public Method[] sortMethods(Method[] methods){
		
		Arrays.sort(methods, new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                Order or1 = o1.getAnnotation(Order.class);
                Order or2 = o2.getAnnotation(Order.class);
                int result=0;
                // nulls last
                if (or1 != null && or2 != null) {
                	result= or1.value() - or2.value();
                } else
                if (or1 != null && or2 == null) {
                	result= -1;
                } else
                if (or1 == null && or2 != null) {
                	result= 1;
                }
                return result;
               // return o1.getName().compareTo(o2.getName());
//                if(o1.getName().startsWith("getParam") && o2.getName().startsWith("getParam") )
//                  return o1.getName().split("_")[1].compareTo(o2.getName().split("_")[1]);
//                else
//                	return o1.getName().compareTo(o2.getName());
            }
            
        });
		
		return methods;
	}
}
