package com.rtdas.common.beans;

public class ParameterMasterBean {
	
	private int parameterId;
	private String parameterName;
	private int clientId;
	private String clientName;
	private int activeFlg;
	
	public int getParameterId() {
		return parameterId;
	}
	public void setParameterId(int parameterId) {
		this.parameterId = parameterId;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
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

}
