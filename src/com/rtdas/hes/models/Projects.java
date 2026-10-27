package com.rtdas.hes.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hes_manufacturer_security_keys")
public class Projects {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int	project_id;
	private String	project_name;
	private String	meter_manufacturer;
	private String	flag_id;
	private String	lls;
	private String	hls;
	private String	global_key;
	private String	authentication_key;
	private String	master_key;
	private String	created_by;
	private String	updated_by;
	
	
	private java.sql.Timestamp	log_timestamp;
	
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getMeter_manufacturer() {
		return meter_manufacturer;
	}
	public void setMeter_manufacturer(String meter_manufacturer) {
		this.meter_manufacturer = meter_manufacturer;
	}
	public String getFlag_id() {
		return flag_id;
	}
	public void setFlag_id(String flag_id) {
		this.flag_id = flag_id;
	}
	public String getLls() {
		return lls;
	}
	public void setLls(String lls) {
		this.lls = lls;
	}
	public String getHls() {
		return hls;
	}
	public void setHls(String hls) {
		this.hls = hls;
	}
	public String getGlobal_key() {
		return global_key;
	}
	public void setGlobal_key(String global_key) {
		this.global_key = global_key;
	}
	public String getAuthentication_key() {
		return authentication_key;
	}
	public void setAuthentication_key(String authentication_key) {
		this.authentication_key = authentication_key;
	}
	public String getMaster_key() {
		return master_key;
	}
	public void setMaster_key(String master_key) {
		this.master_key = master_key;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	public java.sql.Timestamp getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(java.sql.Timestamp log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	
	
	

}
