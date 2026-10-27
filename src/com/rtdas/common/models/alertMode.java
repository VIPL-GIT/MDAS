package com.rtdas.common.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alert_mode")
public class alertMode {

	@Id
	@GeneratedValue
	@Column
	    private int	alert_mode_Id;
	public int getAlert_mode_Id() {
		return alert_mode_Id;
	}
	public void setAlert_mode_Id(int alert_mode_Id) {
		this.alert_mode_Id = alert_mode_Id;
	}
	public String getAlert_mode_Type() {
		return alert_mode_Type;
	}
	public void setAlert_mode_Type(String alert_mode_Type) {
		this.alert_mode_Type = alert_mode_Type;
	}
	@Column
	    private String alert_mode_Type;
	
	
}
