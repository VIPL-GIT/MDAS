package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="analog_param_device_map")
public class ParameterMap implements Serializable {
	
	private static final long serialVersionUID = 5462223600l;
	
	@Id
	@Column(name="parameter_map_id")
	private int parameter_map_id;
	
	@Column(name="parameter_id")
	private int parameter_id;
	
	@Column(name="device_id")
	private int device_id;

	public int getParameter_map_id() {
		return parameter_map_id;
	}

	public void setParameter_map_id(int parameter_map_id) {
		this.parameter_map_id = parameter_map_id;
	}

	public int getParameter_id() {
		return parameter_id;
	}

	public void setParameter_id(int parameter_id) {
		this.parameter_id = parameter_id;
	}

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}
	
	
	

}
