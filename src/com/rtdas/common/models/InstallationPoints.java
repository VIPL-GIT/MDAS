package com.rtdas.common.models;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="meterlocation")
public class InstallationPoints implements Serializable{
	
	private static final long serialVersionUID = 5462223600l;
	
	@Id
	@Column(name="locationid")
	private int id;
	@Column(name="locationname")
	private String meter_type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMeter_type() {
		return meter_type;
	}
	public void setMeter_type(String meter_type) {
		this.meter_type = meter_type;
	}
	
}
