package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="modem")
public class Modem implements Serializable{
	
	private static final long serialVersionUID = 5462223600l;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="modem_id")
	private int modem_id;
	private String modem_serial_no;
	private String imei_no;
	private String sim_no;
	private String static_ip;
	private String mobile_no;
	private String vendor;
	private String delivery_date;
	private String fw_version;
	private String hw_version;
	private String changed_by;
	private String changed_datetime;
	private java.sql.Timestamp log_timestamp;
	
	
	
	public String getSim_no() {
		return sim_no;
	}
	public void setSim_no(String sim_no) {
		this.sim_no = sim_no;
	}
	public int getModem_id() {
		return modem_id;
	}
	public void setModem_id(int modem_id) {
		this.modem_id = modem_id;
	}
	public String getModem_serial_no() {
		return modem_serial_no;
	}
	public void setModem_serial_no(String modem_serial_no) {
		this.modem_serial_no = modem_serial_no;
	}
	public String getImei_no() {
		return imei_no;
	}
	public void setImei_no(String imei_no) {
		this.imei_no = imei_no;
	}
	public String getStatic_ip() {
		return static_ip;
	}
	public void setStatic_ip(String static_ip) {
		this.static_ip = static_ip;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}
	public String getFw_version() {
		return fw_version;
	}
	public void setFw_version(String fw_version) {
		this.fw_version = fw_version;
	}
	public String getHw_version() {
		return hw_version;
	}
	public void setHw_version(String hw_version) {
		this.hw_version = hw_version;
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
