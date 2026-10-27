package com.rtdas.common.beans;

public class DeviceTypeBean {
	
	private int clientId;
	private String clientName;
	private String deviceTypeName;
	private int deviceTypeId;
	private String deviceMakeName;
	private int deviceMakeId;
	private int activeFlg;
	private int deviceId;
	private int supportedParamsCount;
	//private int supportedBaudrates;
	private String[] supportedBaudrates;
	private String supportedBaudrate;
	
	
	public String getSupportedBaudrate() {
		return supportedBaudrate;
	}
	public void setSupportedBaudrate(String supportedBaudrate) {
		this.supportedBaudrate = supportedBaudrate;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getDeviceMakeName() {
		return deviceMakeName;
	}
	public void setDeviceMakeName(String deviceMakeName) {
		this.deviceMakeName = deviceMakeName;
	}
	public int getDeviceMakeId() {
		return deviceMakeId;
	}
	public void setDeviceMakeId(int deviceMakeId) {
		this.deviceMakeId = deviceMakeId;
	}
		
	public String getDeviceTypeName() {
		return deviceTypeName;
	}
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}
	
	public int getDeviceTypeId() {
		return deviceTypeId;
	}
	public void setDeviceTypeId(int deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public int getActiveFlg() {
		return activeFlg;
	}
	public void setActiveFlg(int activeFlg) {
		this.activeFlg = activeFlg;
	}
	public int getSupportedParamsCount() {
		return supportedParamsCount;
	}
	public void setSupportedParamsCount(int supportedParamsCount) {
		this.supportedParamsCount = supportedParamsCount;
	}
	public String[] getSupportedBaudrates() {
		return supportedBaudrates;
	}
	public void setSupportedBaudrates(String[] supportedBaudrates) {
		this.supportedBaudrates = supportedBaudrates;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	
		
}
