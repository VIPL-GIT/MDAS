package com.rtdas.common.beans;

public class DataLoggerBean {

	private int logger_id;
	private String logger_sl_no;
	private String hw_version;
	private String fw_version;
	private String mobile;
	private String delivery_date;
	private Integer location_id;
	private String lattitude;
	private String longitude;
	private Integer no_of_lights;
	private Float amp_per_light;
	
	private String location_name;
	
	
	

	public Integer getNo_of_lights() {
		return no_of_lights;
	}

	public void setNo_of_lights(Integer no_of_lights) {
		this.no_of_lights = no_of_lights;
	}

	public Float getAmp_per_light() {
		return amp_per_light;
	}

	public void setAmp_per_light(Float amp_per_light) {
		this.amp_per_light = amp_per_light;
	}

	public int getLogger_id() {
		return logger_id;
	}

	public void setLogger_id(int logger_id) {
		this.logger_id = logger_id;
	}

	public String getLogger_sl_no() {
		return logger_sl_no;
	}

	public void setLogger_sl_no(String logger_sl_no) {
		this.logger_sl_no = logger_sl_no;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}

	public Integer getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Integer location_id) {
		this.location_id = location_id;
	}

	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	
	
	
	
}
