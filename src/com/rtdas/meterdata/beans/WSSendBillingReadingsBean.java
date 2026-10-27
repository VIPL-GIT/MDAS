package com.rtdas.meterdata.beans;

import java.util.List;

public class WSSendBillingReadingsBean {

	private String apiKey;
	private String bym;
	private int totrecs;
	private String binder;
	private String status;
	private String sdo;
	private String token;
	private List<WSMeterReadingsBean> meterreadings;
	
	
	
	
	
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getBym() {
		return bym;
	}
	public void setBym(String bym) {
		this.bym = bym;
	}
	public int getTotrecs() {
		return totrecs;
	}
	public void setTotrecs(int totrecs) {
		this.totrecs = totrecs;
	}
	public String getBinder() {
		return binder;
	}
	public void setBinder(String binder) {
		this.binder = binder;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSdo() {
		return sdo;
	}
	public void setSdo(String sdo) {
		this.sdo = sdo;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<WSMeterReadingsBean> getMeterreadings() {
		return meterreadings;
	}
	public void setMeterreadings(List<WSMeterReadingsBean> meterreadings) {
		this.meterreadings = meterreadings;
	}
			
	
	
	
	
	
	
	
}
