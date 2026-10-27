package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="analog_device_type")
public class DeviceType implements Serializable {
	
	private static final long serialVersionUID = 5462223600l;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DEVICE_TYPE_ID", unique=true, nullable=false, precision=10)
	private int deviceTypeId;
	
	@Column(name="DEVICE_TYPE_NAME")
	private String deviceTypeName;
	
	@Column(name="CLIENT_ID")
	private int clientId;
	
	@Column(name="ACTIVE_FLG")
	private int activeFlg;

	public int getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(int deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
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
