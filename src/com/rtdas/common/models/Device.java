package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="analog_device_master")
public class Device implements Serializable {
	
private static final long serialVersionUID = 5462223600l;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DEVICE_ID", unique=true, nullable=false, precision=10)
	private int deviceId;
	
	@Column(name="DEVICE_TYPE_ID")
	private int deviceTypeId;
	
	@Column(name="DEVICE_MAKE_ID")
	private int deviceMakeId;
	
	@Column(name="SUPPORTED_PARAMS_COUNT")
	private int supportedParamsCount;
	
	@Column(name="SUPPORTED_BAUDRATES")
	private String supportedBaudrates;
	
	@Column(name="ACTIVE_FLG")
	private int activeFlg;

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public int getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(int deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public int getDeviceMakeId() {
		return deviceMakeId;
	}

	public void setDeviceMakeId(int deviceMakeId) {
		this.deviceMakeId = deviceMakeId;
	}

	public int getSupportedParamsCount() {
		return supportedParamsCount;
	}

	public void setSupportedParamsCount(int supportedParamsCount) {
		this.supportedParamsCount = supportedParamsCount;
	}
	public String getSupportedBaudrates() {
		return supportedBaudrates;
	}

	public void setSupportedBaudrates(String supportedBaudrates) {
		this.supportedBaudrates = supportedBaudrates;
	}

	public int getActiveFlg() {
		return activeFlg;
	}

	public void setActiveFlg(int activeFlg) {
		this.activeFlg = activeFlg;
	}
	
	

}
