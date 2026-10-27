package com.rtdas.mdm.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="d3")
public class D3 implements Serializable{
	private static final long serialVersionUID = 5462223600l;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cdfid")
	public int cdfid;
	@Column(name="meter_serial_no")
	public String meter_serial_no;
	@Column(name="d3_time_stamp")
	private java.sql.Timestamp d3_time_stamp;
	@Column(name="kwh_imp")
	public String kwh_imp;
	@Column(name="kwh_exp")
	public String kwh_exp;
	@Column(name="kvah_imp")
	public String kvah_imp;
	@Column(name="kvah_exp")
	public String kvah_exp;
	@Column(name="log_time_stamp")
	private java.sql.Timestamp log_time_stamp;
	public int getCdfid() {
		return cdfid;
	}
	public void setCdfid(int cdfid) {
		this.cdfid = cdfid;
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
	public String getKwh_imp() {
		return kwh_imp;
	}
	public void setKwh_imp(String kwh_imp) {
		this.kwh_imp = kwh_imp;
	}
	public String getKwh_exp() {
		return kwh_exp;
	}
	public void setKwh_exp(String kwh_exp) {
		this.kwh_exp = kwh_exp;
	}
	public String getKvah_imp() {
		return kvah_imp;
	}
	public void setKvah_imp(String kvah_imp) {
		this.kvah_imp = kvah_imp;
	}
	public String getKvah_exp() {
		return kvah_exp;
	}
	public void setKvah_exp(String kvah_exp) {
		this.kvah_exp = kvah_exp;
	}
	public java.sql.Timestamp getLog_time_stamp() {
		return log_time_stamp;
	}
	public void setLog_time_stamp(java.sql.Timestamp log_time_stamp) {
		this.log_time_stamp = log_time_stamp;
	}
	
	

}
