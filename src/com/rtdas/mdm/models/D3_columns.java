package com.rtdas.mdm.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="d3_columns_header")
public class D3_columns implements Serializable{
	
	private static final long serialVersionUID = 5462223600l;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="app_id")
	private int app_id;
	@Column(name="meter_serial_no")
	private String meter_serial_no;
	@Column(name="billing_columns_header")
	private String billing_columns_header;
	@Column(name="billing_type")
	private String billing_type;
	
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public String getMeter_serial_no() {
		return meter_serial_no;
	}
	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}
	public String getBilling_columns_header() {
		return billing_columns_header;
	}
	public void setBilling_columns_header(String billing_columns_header) {
		this.billing_columns_header = billing_columns_header;
	}
	public String getBilling_type() {
		return billing_type;
	}
	public void setBilling_type(String billing_type) {
		this.billing_type = billing_type;
	}
	
	
	
}
