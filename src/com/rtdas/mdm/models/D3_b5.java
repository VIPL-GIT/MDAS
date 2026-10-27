package com.rtdas.mdm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="d3_b5")
public class D3_b5 {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="app_id")
	private int app_id;
	private Integer cdfid;
	
	private String billing_day;
	
	private String data_string;
	
	private String occ_date;
	
	private String meter_serial_no;
	
	private String d3_time_stamp;
	
	private java.sql.Timestamp log_time_stamp;
	
	
	public Integer getCdfid() {
		return cdfid;
	}
	public void setCdfid(Integer cdfid) {
		this.cdfid = cdfid;
	}
	public String getBilling_day() {
		return billing_day;
	}
	public void setBilling_day(String billing_day) {
		this.billing_day = billing_day;
	}
	public String getData_string() {
		return data_string;
	}
	public void setData_string(String data_string) {
		this.data_string = data_string;
	}
	public String getOcc_date() {
		return occ_date;
	}
	public void setOcc_date(String occ_date) {
		this.occ_date = occ_date;
	}
	public String getMeter_serial_no() {
		return meter_serial_no;
	}
	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}
	public String getD3_time_stamp() {
		return d3_time_stamp;
	}
	public void setD3_time_stamp(String d3_time_stamp) {
		this.d3_time_stamp = d3_time_stamp;
	}
	public java.sql.Timestamp getLog_time_stamp() {
		return log_time_stamp;
	}
	public void setLog_time_stamp(java.sql.Timestamp log_time_stamp) {
		this.log_time_stamp = log_time_stamp;
	}
	
	
	

}
