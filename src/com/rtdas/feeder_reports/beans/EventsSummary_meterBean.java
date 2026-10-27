package com.rtdas.feeder_reports.beans;

public class EventsSummary_meterBean {

	private String month;
	private String event;
	private int event_no_with_restore;
	private int event_no_with_restore_duration;
	private int event_no_without_restore;
	private int event_no_without_restore_duration;
	
	private String with_duration;
	private String without_duration;
	
	
	
	public String getWith_duration() {
		return with_duration;
	}
	public void setWith_duration(String with_duration) {
		this.with_duration = with_duration;
	}
	public String getWithout_duration() {
		return without_duration;
	}
	public void setWithout_duration(String without_duration) {
		this.without_duration = without_duration;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getEvent_no_with_restore() {
		return event_no_with_restore;
	}
	public void setEvent_no_with_restore(int event_no_with_restore) {
		this.event_no_with_restore = event_no_with_restore;
	}
	public int getEvent_no_with_restore_duration() {
		return event_no_with_restore_duration;
	}
	public void setEvent_no_with_restore_duration(int event_no_with_restore_duration) {
		this.event_no_with_restore_duration = event_no_with_restore_duration;
	}
	public int getEvent_no_without_restore() {
		return event_no_without_restore;
	}
	public void setEvent_no_without_restore(int event_no_without_restore) {
		this.event_no_without_restore = event_no_without_restore;
	}
	public int getEvent_no_without_restore_duration() {
		return event_no_without_restore_duration;
	}
	public void setEvent_no_without_restore_duration(int event_no_without_restore_duration) {
		this.event_no_without_restore_duration = event_no_without_restore_duration;
	}
	
	
	
}
