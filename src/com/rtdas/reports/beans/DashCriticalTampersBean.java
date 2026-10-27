package com.rtdas.reports.beans;

public class DashCriticalTampersBean {

	private String meter_serial_no;
	private String event_date;
	private String event_name;
	private String last_o_timestamp;
	private String last_r_timestamp;
	
	
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getMeter_serial_no() {
		return meter_serial_no;
	}
	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}
	public String getEvent_date() {
		return event_date;
	}
	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}
	public String getLast_o_timestamp() {
		return last_o_timestamp;
	}
	public void setLast_o_timestamp(String last_o_timestamp) {
		this.last_o_timestamp = last_o_timestamp;
	}
	public String getLast_r_timestamp() {
		return last_r_timestamp;
	}
	public void setLast_r_timestamp(String last_r_timestamp) {
		this.last_r_timestamp = last_r_timestamp;
	}
	
	
}
