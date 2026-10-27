package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="analog_parameter")
public class ParameterMaster implements Serializable {
	
private static final long serialVersionUID = 5462223600l;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PARAMETER_ID", unique=true, nullable=false, precision=10)
	private int parameterId;
	
	@Column(name="PARAMETER_NAME")
	private String parameterName;
	
	@Column(name="CLIENT_ID")
	private int clientId;
	
	@Column(name="ACTIVE_FLG")
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

	public int getActiveFlg() {
		return activeFlg;
	}

	public void setActiveFlg(int activeFlg) {
		this.activeFlg = activeFlg;
	}
	
	
}
