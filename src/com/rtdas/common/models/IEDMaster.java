package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ied_master")
public class IEDMaster implements Serializable{

	private static final long serialVersionUID = 5462223600l;
	
	@Id
	@Column(name="ied_id")
	private int ied_id;
	@Column(name="ied_sl_no")
	private String ied_sl_no;
	@Column(name="hw_version")
	private String hw_version;
	@Column(name="fw_version")
	private String fw_version;
	@Column(name="logger_id")
	private int logger_id;
	
	
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
