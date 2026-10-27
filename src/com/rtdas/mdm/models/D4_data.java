package com.rtdas.mdm.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="d4_data_curr")
public class D4_data {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="app_id")
	private int app_id;
	
	private String cdfid;
	
	private String d4_date;
	
	private Integer time_delay;
	
	private String data_string;
	
	private String meter_serial_no;
	
	private java.sql.Timestamp d4_time_stamp;
	
	private java.sql.Timestamp log_time_stamp;
	
	
	public String getCdfid() {
		return cdfid;
	}
	public void setCdfid(String cdfid) {
		this.cdfid = cdfid;
	}
	public String getD4_date() {
		return d4_date;
	}
	public void setD4_date(String d4_date) {
		this.d4_date = d4_date;
	}
	public Integer getTime_delay() {
		return time_delay;
	}
	public void setTime_delay(Integer time_delay) {
		this.time_delay = time_delay;
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
	public java.sql.Timestamp getD4_time_stamp() {
		return d4_time_stamp;
	}
	public void setD4_time_stamp(java.sql.Timestamp d4_time_stamp) {
		this.d4_time_stamp = d4_time_stamp;
	}
	public java.sql.Timestamp getLog_time_stamp() {
		return log_time_stamp;
	}
	public void setLog_time_stamp(java.sql.Timestamp log_time_stamp) {
		this.log_time_stamp = log_time_stamp;
	}
	
	
	
	

}
