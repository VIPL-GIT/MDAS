package com.rtdas.common.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_alerts_config")
public class useralertsconfig {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column
	private int	user_alert_id;
public int getUser_alert_id() {
	return user_alert_id;
}
public void setUser_alert_id(int user_alert_id) {
	this.user_alert_id = user_alert_id;
}
@Column
    private String user_name;
@Column
    private String type_of_alert;
@Column
    private String mode_of_alert;
@Column
private int	is_enabled;
@Column
private String user_hierarchy_id;
@Column
private String alerts_hierarchy;
public int getIs_enabled() {
	return is_enabled;
}
public void setIs_enabled(int is_enabled) {
	this.is_enabled = is_enabled;
}
public String getUser_hierarchy_id() {
	return user_hierarchy_id;
}
public void setUser_hierarchy_id(String user_hierarchy_id) {
	this.user_hierarchy_id = user_hierarchy_id;
}
public String getAlerts_hierarchy() {
	return alerts_hierarchy;
}
public void setAlerts_hierarchy(String alerts_hierarchy) {
	this.alerts_hierarchy = alerts_hierarchy;
}




public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public String getType_of_alert() {
	return type_of_alert;
}
public void setType_of_alert(String type_of_alert) {
	this.type_of_alert = type_of_alert;
}
public String getMode_of_alert() {
	return mode_of_alert;
}
public void setMode_of_alert(String mode_of_alert) {
	this.mode_of_alert = mode_of_alert;
}
	

	
	
}
