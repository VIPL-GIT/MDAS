package com.rtdas.feeder_reports.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="analytics_energy_savings_single_phase")
public class EnergysavingsBean {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int recordid;
	private String meter_serial_no;
	private String d4_date;
	private String singlephase;
	private String threephase;
	private String powerfailure;
	private String avg_three_consumption;
	private String energy_savings;
	private java.sql.Timestamp log_timestamp;
	@Transient
	private String energy_utilised;
	
	@Transient
	private String supply_duration;
	
	
	
	
	
	
	
	public String getSupply_duration() {
		return supply_duration;
	}
	public void setSupply_duration(String supply_duration) {
		this.supply_duration = supply_duration;
	}
	public String getEnergy_utilised() {
		return energy_utilised;
	}
	public void setEnergy_utilised(String energy_utilised) {
		this.energy_utilised = energy_utilised;
	}
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
	public String getD4_date() {
		return d4_date;
	}
	public void setD4_date(String d4_date) {
		this.d4_date = d4_date;
	}
	public String getSinglephase() {
		return singlephase;
	}
	public void setSinglephase(String singlephase) {
		this.singlephase = singlephase;
	}
	public String getThreephase() {
		return threephase;
	}
	public void setThreephase(String threephase) {
		this.threephase = threephase;
	}
	public String getPowerfailure() {
		return powerfailure;
	}
	public void setPowerfailure(String powerfailure) {
		this.powerfailure = powerfailure;
	}
	public String getAvg_three_consumption() {
		return avg_three_consumption;
	}
	public void setAvg_three_consumption(String avg_three_consumption) {
		this.avg_three_consumption = avg_three_consumption;
	}
	public String getEnergy_savings() {
		return energy_savings;
	}
	public void setEnergy_savings(String energy_savings) {
		this.energy_savings = energy_savings;
	}
	
	
}
