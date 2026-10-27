package utils;

import java.util.*;

public class Events {
	
	public Map<Integer,String> evetnsList(){
		Map<Integer,String> eventsMap=new HashMap();
		
		eventsMap.put(1,"R phase potential missing");
		eventsMap.put(6,"B phase CT reversed");
		eventsMap.put(7,"Load imbalance");
		eventsMap.put(8,"Load unbalance - R phase");
		eventsMap.put(14,"Unbalance voltage");
		eventsMap.put(15,"Unbalance voltage - R phase");
		eventsMap.put(16,"Unbalance voltage - Y phase");
		eventsMap.put(17,"Unbalance voltage - B phase");	
		eventsMap.put(18,"Invalid voltage inputs");
		eventsMap.put(19,"CT short");
		eventsMap.put(20,"CT short - R phase");
		eventsMap.put(21,"CT short - Y phase");
		eventsMap.put(22,"CT short - B phase");
		eventsMap.put(23,"CT open");
		eventsMap.put(28,"Neutral disturbance");
		eventsMap.put(29,"High neutral current");
		eventsMap.put(30,"R-Ph current missing");
		eventsMap.put(31,"Y-Ph current missing");
		eventsMap.put(32,"B-Ph current missing");
		eventsMap.put(33,"High voltage");
		eventsMap.put(34,"High voltage - R phase");
		eventsMap.put(35,"High voltage - Y phase");
		eventsMap.put(36,"High voltage - B phase");
		eventsMap.put(37,"Over load (current) - R phase");
		eventsMap.put(38,"Over load (current) - Y phase");
		eventsMap.put(39,"Over load (current) - B phase");
		eventsMap.put(40,"Low voltage - R phase");
		eventsMap.put(41,"Low voltage - Y phase");
		eventsMap.put(42,"Low voltage - B phase");
		eventsMap.put(43,"Low current - R phase");
		eventsMap.put(44,"Low current - Y phase");
		eventsMap.put(45,"Low current - B phase");
		eventsMap.put(46,"Power reversed - R phase");
		eventsMap.put(47,"Power reversed - Y phase");
		eventsMap.put(48,"Power reversed - B phase");
		eventsMap.put(49,"Power failed in the presence of magnet");
		eventsMap.put(51,"High total harmonics distortion current");
		eventsMap.put(52,"Low PF - R Phase");
		eventsMap.put(2,"Y phase potential missing");
		eventsMap.put(3,"B phase potential missing");
		eventsMap.put(4,"R phase CT reversed");
		eventsMap.put(5,"Y phase CT reversed");
		eventsMap.put(9,"Load unbalance - Y phase");
		eventsMap.put(10,"Load unbalance - B phase");
		eventsMap.put(11,"Overload(Current)");
		eventsMap.put(12,"Low power factor");
		eventsMap.put(13,"Power failed");
		eventsMap.put(24,"CT open - R phase");
		eventsMap.put(25,"CT open - Y phase");
		eventsMap.put(26,"CT open - B phase");
		eventsMap.put(27,"Magnet");
		eventsMap.put(50,"High second harmonics");
		eventsMap.put(53,"Low PF - Y Phase");
		eventsMap.put(54,"Low PF - B Phase");
		eventsMap.put(55,"Potential Missing");
		eventsMap.put(56,"Current Reversa");
		eventsMap.put(57,"Current Missing");
		eventsMap.put(58,"Low Voltage");
		eventsMap.put(59,"Low Current");
		eventsMap.put(60,"Meter Cover Open");
		eventsMap.put(61,"RTC Change");
		eventsMap.put(62,"Energy Corruption");
		eventsMap.put(63,"Gain Angle Corruption");
		eventsMap.put(64,"Power Reversal");
		eventsMap.put(65,"Abnormal Restart Detected");
		eventsMap.put(66,"Invalid Phase Association");
		eventsMap.put(67,"Over Load (Apparent Power)");
		eventsMap.put(68,"Under Load (Apparent Power)");
		eventsMap.put(69,"Short Term Over Load (Apparent Power)");
		eventsMap.put(70,"Supply Status-FULL");
		eventsMap.put(71,"Supply Status-Partial");
		eventsMap.put(302,"Relay Event");
		eventsMap.put(151,"RTC Change");
		eventsMap.put(251,"Cover Open");
		
		return eventsMap;
		
	}

}
