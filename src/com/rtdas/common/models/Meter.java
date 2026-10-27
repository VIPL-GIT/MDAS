package com.rtdas.common.models;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="meter")
public class Meter implements Serializable{
	private static final long serialVersionUID = 5462223600l;
	@Id
	@Column(name="meterid")
	private int meter_id;
	@Column(name="meter_serial_no")
	private String meter_sl_no;
	@Column(name="modem_serial_no")
	private String modem_sl_no;
	private String installation_date;
	private String meterlocation;
	private String metermake;
	private String location_id;
	@Column(name="meter_ctr")
	private String me_ct;
	@Column(name="meter_ptr")
	private String me_pt;
	@Column(name="adopted_ctr")
	private String ad_ct;
	@Column(name="adopted_ptr")
	private String ad_pt;
	@Column(name="mf")
	private String mf;
	@Column(name="meter_type")
	private String meter_type;
	
	private String changed_by;
	private String changed_datetime;
	private java.sql.Timestamp log_timestamp;
	
	
	
	
	
	public String getMetermake() {
		return metermake;
	}
	public void setMetermake(String metermake) {
		this.metermake = metermake;
	}
	public String getMeter_type() {
		return meter_type;
	}
	public void setMeter_type(String meter_type) {
		this.meter_type = meter_type;
	}
	public String getMf() {
		return mf;
	}
	public void setMf(String mf) {
		this.mf = mf;
	}
	public int getMeter_id() {
		return meter_id;
	}
	public void setMeter_id(int meter_id) {
		this.meter_id = meter_id;
	}
	public String getMeter_sl_no() {
		return meter_sl_no;
	}
	public void setMeter_sl_no(String meter_sl_no) {
		this.meter_sl_no = meter_sl_no;
	}
	public String getModem_sl_no() {
		return modem_sl_no;
	}
	public void setModem_sl_no(String modem_sl_no) {
		this.modem_sl_no = modem_sl_no;
	}
	public String getInstallation_date() {
		return installation_date;
	}
	public void setInstallation_date(String installation_date) {
		this.installation_date = installation_date;
	}
	public String getMeterlocation() {
		return meterlocation;
	}
	public void setMeterlocation(String meterlocation) {
		this.meterlocation = meterlocation;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public String getMe_ct() {
		return me_ct;
	}
	public void setMe_ct(String me_ct) {
		this.me_ct = me_ct;
	}
	public String getMe_pt() {
		return me_pt;
	}
	public void setMe_pt(String me_pt) {
		this.me_pt = me_pt;
	}
	public String getAd_ct() {
		return ad_ct;
	}
	public void setAd_ct(String ad_ct) {
		this.ad_ct = ad_ct;
	}
	public String getAd_pt() {
		return ad_pt;
	}
	public void setAd_pt(String ad_pt) {
		this.ad_pt = ad_pt;
	}
	public String getChanged_by() {
		return changed_by;
	}
	public void setChanged_by(String changed_by) {
		this.changed_by = changed_by;
	}
	public String getChanged_datetime() {
		return changed_datetime;
	}
	public void setChanged_datetime(String changed_datetime) {
		this.changed_datetime = changed_datetime;
	}
	public java.sql.Timestamp getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(java.sql.Timestamp log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	 
}
