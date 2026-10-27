package com.rtdas.mdm.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="analytics_other_events_summary")
public class Analytics_other_events_summary {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int recordid;
	private String meter_serial_no;
	private String event_type;
	@Transient
	private String event_name;
	
	private String o_timestamp;
	private String o_kwh;
	private String r_timestamp;
	private String r_kwh;
	private String duration;
	private java.sql.Timestamp log_timestamp;
	
	
	
	
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public String getMeter_serial_no() {
		return meter_serial_no;
	}
	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public String getO_timestamp() {
		return o_timestamp;
	}
	public void setO_timestamp(String o_timestamp) {
		this.o_timestamp = o_timestamp;
	}
	public String getO_kwh() {
		return o_kwh;
	}
	public void setO_kwh(String o_kwh) {
		this.o_kwh = o_kwh;
	}
	public String getR_timestamp() {
		return r_timestamp;
	}
	public void setR_timestamp(String r_timestamp) {
		this.r_timestamp = r_timestamp;
	}
	public String getR_kwh() {
		return r_kwh;
	}
	public void setR_kwh(String r_kwh) {
		this.r_kwh = r_kwh;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public java.sql.Timestamp getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(java.sql.Timestamp log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	
	
}
