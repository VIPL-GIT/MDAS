package com.rtdas.feeder_reports.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ls_data_stbl_energy_savings")
public class EnergySavingsDetailsBean {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int recordid;
	private String d4_date;
	private String meter_serial_no;
	private String phase_supply;
	private String energy_utilised;
	private String energy_saved;
	private java.sql.Timestamp log_timestamp;
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public String getD4_date() {
		return d4_date;
	}
	public void setD4_date(String d4_date) {
		this.d4_date = d4_date;
	}
	public String getMeter_serial_no() {
		return meter_serial_no;
	}
	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}
	public String getPhase_supply() {
		return phase_supply;
	}
	public void setPhase_supply(String phase_supply) {
		this.phase_supply = phase_supply;
	}
	public String getEnergy_utilised() {
		return energy_utilised;
	}
	public void setEnergy_utilised(String energy_utilised) {
		this.energy_utilised = energy_utilised;
	}
	public String getEnergy_saved() {
		return energy_saved;
	}
	public void setEnergy_saved(String energy_saved) {
		this.energy_saved = energy_saved;
	}
	public java.sql.Timestamp getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(java.sql.Timestamp log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	
	
	
}
