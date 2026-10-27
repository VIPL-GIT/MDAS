package com.rtdas.mdm.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="d5_data")
public class D5_data_all {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="app_id")
	private int app_id;
	
	private String cdfid;	
	private String event_type;	
	private String event_date;	
	private String event_status;	
	private String data_string;	
	private String meter_serial_no;	
	private java.sql.Timestamp d5_time_stamp;	
	private java.sql.Timestamp log_time_stamp;
	
	public String getCdfid() {
		return cdfid;
	}
	public void setCdfid(String cdfid) {
		this.cdfid = cdfid;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public String getEvent_date() {
		return event_date;
	}
	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}
	public String getEvent_status() {
		return event_status;
	}
	public void setEvent_status(String event_status) {
		this.event_status = event_status;
	}
	public String getData_string() {
		return data_string;
	}
	public void setData_string(String data_string) {
		this.data_string = data_string;
	}
	public String getMeter_serial_no() {
		return meter_serial_no;
	}
	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}
	public java.sql.Timestamp getD5_time_stamp() {
		return d5_time_stamp;
	}
	public void setD5_time_stamp(java.sql.Timestamp d5_time_stamp) {
		this.d5_time_stamp = d5_time_stamp;
	}
	public java.sql.Timestamp getLog_time_stamp() {
		return log_time_stamp;
	}
	public void setLog_time_stamp(java.sql.Timestamp log_time_stamp) {
		this.log_time_stamp = log_time_stamp;
	}
	
	

}
