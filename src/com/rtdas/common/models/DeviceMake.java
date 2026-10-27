package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="analog_device_make")
public class DeviceMake implements Serializable {
	
private static final long serialVersionUID = 5462223600l;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DEVICE_MAKE_ID", unique=true, nullable=false, precision=10)
	private int deviceMakeId;
	
	@Column(name="DEVICE_MAKE_NAME")
	private String deviceMakeName;
	
	@Column(name="CLIENT_ID")
	private int clientId;
	
	@Column(name="ACTIVE_FLG")
	private int activeFlg;

	public int getDeviceMakeId() {
		return deviceMakeId;
	}

	public void setDeviceMakeId(int deviceMakeId) {
		this.deviceMakeId = deviceMakeId;
	}

	public String getDeviceMakeName() {
		return deviceMakeName;
	}

	public void setDeviceMakeName(String deviceMakeName) {
		this.deviceMakeName = deviceMakeName;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getActiveFlg() {
		return activeFlg;
	}

	public void setActiveFlg(int activeFlg) {
		this.activeFlg = activeFlg;
	}
	
	

}
