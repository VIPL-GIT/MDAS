package com.rtdas.mdm.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="analytics_outage_summary")
public class Analytics_outage_summary {

	private static final long serialVersionUID = 5462223600l;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int recordid;
	private String meter_serial_no;
	private String o_timestamp;
	private String r_timestamp;
	private String duration;
	private java.sql.Timestamp log_timestamp;
	
	
	public java.sql.Timestamp getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(java.sql.Timestamp log_timestamp) {
		this.log_timestamp = log_timestamp;
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
	public String getO_timestamp() {
		return o_timestamp;
	}
	public void setO_timestamp(String o_timestamp) {
		this.o_timestamp = o_timestamp;
	}
	public String getR_timestamp() {
		return r_timestamp;
	}
	public void setR_timestamp(String r_timestamp) {
		this.r_timestamp = r_timestamp;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
}
