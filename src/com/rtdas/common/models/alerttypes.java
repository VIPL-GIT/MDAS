package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="alert_types")
public class alerttypes implements Serializable {
	
	@Id
	@GeneratedValue
	@Column
	private int alert_id;
	
	@Column
	private String alert_type;
	
	public int getAlert_id() {
		return alert_id;
	}
	
	public void setAlert_id(int alert_id) {
		this.alert_id = alert_id;
	}
	public String getAlert_type() {
		return alert_type;
	}
	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}
	
	
	
}
