package com.rtdas.mdm.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="d5_columns_header")
public class D5_columns implements Serializable{
	
	private static final long serialVersionUID = 5462223600l;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="meter_serial_no")
	private String meter_serial_no;
	
	@Column(name="tamper_columns_header")
	private String tamper_columns_header;

	public String getMeter_serial_no() {
		return meter_serial_no;
	}

	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}

	public String getTamper_columns_header() {
		return tamper_columns_header;
	}

	public void setTamper_columns_header(String tamper_columns_header) {
		this.tamper_columns_header = tamper_columns_header;
	}
	
	
	
}
