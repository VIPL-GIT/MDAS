package com.rtdas.common.beans;

import java.util.List;

public class DeviceBean {

	private int clientId;
	private String clientName;	
	private int activeFlg;
	private int deviceId;
	private int supportedParamsCount;	
	private List<String> supportedBaudrates;
	private String supportedBaudrate;
	private String deviceTypeName;
	private int deviceTypeId;
	private String deviceMakeName;
	private int deviceMakeId;
	
//------------for parameter map ------------
	private Integer[] paramIds;
	private String paramsId;
	
//------------for memory map ------------------
	private Integer paramId;
	private String paramdesc;
	private String mf;
	private String register_address;
	private String register_size;
	private int default_add_flg;
	private int energy_flg;
	private int function_code;
	
	public String getParamsId() {
		return paramsId;
	}
	public void setParamsId(String paramsId) {
		this.paramsId = paramsId;
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
	public Integer[] getParamIds() {
		return paramIds;
	}
	public void setParamIds(Integer[] paramIds) {
		this.paramIds = paramIds;
	}
	public Integer getParamId() {
		return paramId;
	}
	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}
	public String getParamdesc() {
		return paramdesc;
	}
	public void setParamdesc(String paramdesc) {
		this.paramdesc = paramdesc;
	}
	public String getMf() {
		return mf;
	}
	public void setMf(String mf) {
		this.mf = mf;
	}
	public String getRegister_address() {
		return register_address;
	}
	public void setRegister_address(String register_address) {
		this.register_address = register_address;
	}
	public String getRegister_size() {
		return register_size;
	}
	public void setRegister_size(String register_size) {
		this.register_size = register_size;
	}
	public int getDefault_add_flg() {
		return default_add_flg;
	}
	public void setDefault_add_flg(int default_add_flg) {
		this.default_add_flg = default_add_flg;
	}
	public int getEnergy_flg() {
		return energy_flg;
	}
	public void setEnergy_flg(int energy_flg) {
		this.energy_flg = energy_flg;
	}
	public int getFunction_code() {
		return function_code;
	}
	public void setFunction_code(int function_code) {
		this.function_code = function_code;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
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
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public int getSupportedParamsCount() {
		return supportedParamsCount;
	}
	public void setSupportedParamsCount(int supportedParamsCount) {
		this.supportedParamsCount = supportedParamsCount;
	}
	public List<String> getSupportedBaudrates() {
		return supportedBaudrates;
	}
	public void setSupportedBaudrates(List<String> supportedBaudrates) {
		this.supportedBaudrates = supportedBaudrates;
	}
	public String getSupportedBaudrate() {
		return supportedBaudrate;
	}
	public void setSupportedBaudrate(String supportedBaudrate) {
		this.supportedBaudrate = supportedBaudrate;
	}
	
	
}
