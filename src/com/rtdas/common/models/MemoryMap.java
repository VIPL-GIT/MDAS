package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="analog_device_memory_map")
public class MemoryMap implements Serializable{
	
	private static final long serialVersionUID = 5462223600l;
	
	@Id
	@Column(name="memory_map_id")
	private int memory_map_id;
	@Column(name="device_id")
	private int device_id;
	@Column(name="client_id")
	private int client_id;
	@Column(name="parameter_id")
	private String parameter_id;
	@Column(name="description")
	private String description;
	@Column(name="mf")
	private float mf;
	@Column(name="register_address")
	private String register_address;
	@Column(name="register_size")
	private String register_size;
	@Column(name="default_add_flg")
	private int default_add_flg;
	@Column(name="energy_flg")
	private int energy_flg;
	@Column(name="function_code")
	private int function_code;
//	@Column(name="di_flg")
//	private Integer di_flg;
	
	
	
	public int getMemory_map_id() {
		return memory_map_id;
	}
	public String getParameter_id() {
		return parameter_id;
	}
	public void setParameter_id(String parameter_id) {
		this.parameter_id = parameter_id;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
