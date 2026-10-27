package com.rtdas.common.models;

import java.io.Serializable;
import java.sql.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alerts")
public class Alerts implements Serializable{
	
	private static final long serialVersionUID = 5462223600l;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int slno;
	private String siteid;
	private String traid;
	private String alert_type;
	private String alert_desc;
	private java.sql.Timestamp alert_datetime;
	public int getSlno() {
		return slno;
	}
	public void setSlno(int slno) {
		this.slno = slno;
	}
	public String getSiteid() {
		return siteid;
	}
	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}
	public String getTraid() {
		return traid;
	}
	public void setTraid(String traid) {
		this.traid = traid;
	}
	public String getAlert_type() {
		return alert_type;
	}
	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}
	public String getAlert_desc() {
		return alert_desc;
	}
	public void setAlert_desc(String alert_desc) {
		this.alert_desc = alert_desc;
	}
	public java.sql.Timestamp getAlert_datetime() {
		return alert_datetime;
	}
	public void setAlert_datetime(java.sql.Timestamp alert_datetime) {
		this.alert_datetime = alert_datetime;
	}
	
	
}
