package com.rtdas.mdm.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="d3_b3")
public class D3_b3 implements Serializable{
	
	private static final long serialVersionUID = 5462223600l;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="app_id")
	private int app_id;
	@Column(name="cdfid")
	private Integer cdfid;
	@Column(name="billing_day")
	private String billing_day;
	@Column(name="data_string")
	private String data_string;
	@Column(name="b11_b12_b13")
	private String b11_b12_b13;
	@Column(name="meter_serial_no")
	private String meter_serial_no;
	@Column(name="d3_time_stamp")
	private java.sql.Timestamp d3_time_stamp;
	@Column(name="log_time_stamp")
	private java.sql.Timestamp log_time_stamp;
	
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
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
	public String getB11_b12_b13() {
		return b11_b12_b13;
	}
	public void setB11_b12_b13(String b11_b12_b13) {
		this.b11_b12_b13 = b11_b12_b13;
	}
	public String getMeter_serial_no() {
		return meter_serial_no;
	}
	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}
	public java.sql.Timestamp getD3_time_stamp() {
		return d3_time_stamp;
	}
	public void setD3_time_stamp(java.sql.Timestamp d3_time_stamp) {
		this.d3_time_stamp = d3_time_stamp;
	}
	public java.sql.Timestamp getLog_time_stamp() {
		return log_time_stamp;
	}
	public void setLog_time_stamp(java.sql.Timestamp log_time_stamp) {
		this.log_time_stamp = log_time_stamp;
	}
	
	
}
