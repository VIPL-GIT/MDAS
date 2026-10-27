package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="adjustments")
public class Adjustments implements Serializable{
	
	private static final long serialVersionUID = 5462223600l;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int slno;
	private String site_id;
	private String device_id;
	private String adjust_param;
	private String adjust_value;
	public int getSlno() {
		return slno;
	}
	public void setSlno(int slno) {
		this.slno = slno;
	}
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getAdjust_param() {
		return adjust_param;
	}
	public void setAdjust_param(String adjust_param) {
		this.adjust_param = adjust_param;
	}
	public String getAdjust_value() {
		return adjust_value;
	}
	public void setAdjust_value(String adjust_value) {
		this.adjust_value = adjust_value;
	}
	
	

}
