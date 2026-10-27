package com.rtdas.common.beans;

import javax.persistence.Column;

public class DeviceConfigBean {
	
	private int memory_map_id;	
	private int device_id;	
	private int client_id;	
	private int parameter_id;	
	private String description;
	private float mf;	
	private String register_address;	
	private String register_size;	
	private int default_add_flg;	
	private int energy_flg;	
	private int function_code;
	
	private String parameter_name;
	private String client_name;
    private Integer di_flg;
	
	
	public Integer getDi_flg() {
		return di_flg;
	}
	public void setDi_flg(Integer di_flg) {
		this.di_flg = di_flg;
	}
	
	
	
	public String getParameter_name() {
		return parameter_name;
	}
	public void setParameter_name(String parameter_name) {
		this.parameter_name = parameter_name;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public int getMemory_map_id() {
		return memory_map_id;
	}
	public void setMemory_map_id(int memory_map_id) {
		this.memory_map_id = memory_map_id;
	}
	public int getDevice_id() {
		return device_id;
	}
	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int getParameter_id() {
		return parameter_id;
	}
	public void setParameter_id(int parameter_id) {
		this.parameter_id = parameter_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getMf() {
		return mf;
	}
	public void setMf(float mf) {
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
	
	
	
}
