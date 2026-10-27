package com.rtdas.common.beans;
public class ModemBean {

	private int modemId;
	public int getModemId() {
		return modemId;
	}
	public void setModemId(int modemId) {
		this.modemId = modemId;
	}
	public String getModemSlNo() {
		return modemSlNo;
	}
	public void setModemSlNo(String modemSlNo) {
		this.modemSlNo = modemSlNo;
	}
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}
	private String modemSlNo;
	private String delivery_date;
	private String sim_no;
	private String imei_no;
	private String ip;
	private String mobile;
	private String vendor;
	private String fw_version;
	private String hw_version;
	private String changed_by;
	private String changed_datetime;
	private java.sql.Timestamp log_timestamp;
	
	
	
	public String getImei_no() {
		return imei_no;
	}
	public void setImei_no(String imei_no) {
		this.imei_no = imei_no;
	}
	public String getSim_no() {
		return sim_no;
	}
	public void setSim_no(String sim_no) {
		this.sim_no = sim_no;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
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
	
	
	
	
}
