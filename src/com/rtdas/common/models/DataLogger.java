package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="data_logger")
public class DataLogger implements Serializable{
	
	private static final long serialVersionUID = 5462223600l;

	@Id
	@Column(name="logger_id")
	private int logger_id;
	@Column(name="logger_sl_no")
	private String logger_sl_no;
	@Column(name="hw_version")
	private String hw_version;
	@Column(name="fw_version")
	private String fw_version;
	@Column(name="mobile")
	private String mobile;
	@Column(name="delivery_date")
	private java.sql.Date delivery_date;
	@Column(name="location_id")
	private Integer location_id;
	@Column(name="latitude")
	private String lattitude;
	@Column(name="longitude")
	private String longitude;
	@Column(name="no_of_lights")
	private Integer no_of_lights;
	@Column(name="amp_per_light")
	private Float amp_per_light;
	
		
	
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
	public java.sql.Date getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(java.sql.Date delivery_date) {
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
	
	
	
	
	
}
