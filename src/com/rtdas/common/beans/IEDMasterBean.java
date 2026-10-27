package com.rtdas.common.beans;

public class IEDMasterBean {

	private int ied_id;
	private String ied_sl_no;
	private String hw_version;
	private String fw_version;
	private int logger_id;

	//for logger info
	private String location_name;
	private String logger_sl_no;
	
//for IED info -----------------
	private String device_names;
	
	
	
	
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getLogger_sl_no() {
		return logger_sl_no;
	}
	public void setLogger_sl_no(String logger_sl_no) {
		this.logger_sl_no = logger_sl_no;
	}
	public String getDevice_names() {
		return device_names;
	}
	public void setDevice_names(String device_names) {
		this.device_names = device_names;
	}
	public int getIed_id() {
		return ied_id;
	}
	public void setIed_id(int ied_id) {
		this.ied_id = ied_id;
	}
	public String getIed_sl_no() {
		return ied_sl_no;
	}
	public void setIed_sl_no(String ied_sl_no) {
		this.ied_sl_no = ied_sl_no;
	}
	public String getHw_version() {
		return hw_version;
	}
	public void setHw_version(String hw_version) {
		this.hw_version = hw_version;
	}
	public String getFw_version() {
		return fw_version;
	}
	public void setFw_version(String fw_version) {
		this.fw_version = fw_version;
	}
	public int getLogger_id() {
		return logger_id;
	}
	public void setLogger_id(int logger_id) {
		this.logger_id = logger_id;
	}
	
	
	
	
	
}
