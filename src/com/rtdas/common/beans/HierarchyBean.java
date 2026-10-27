package com.rtdas.common.beans;

public class HierarchyBean {

	
	public int getHierarchy_id() {
		return hierarchy_id;
	}
	public void setHierarchy_id(int hierarchy_id) {
		this.hierarchy_id = hierarchy_id;
	}
	public String getHierarchy_name() {
		return hierarchy_name;
	}
	public void setHierarchy_name(String hierarchy_name) {
		this.hierarchy_name = hierarchy_name;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public String getHierarchy_desc() {
		return hierarchy_desc;
	}
	public void setHierarchy_desc(String hierarchy_desc) {
		this.hierarchy_desc = hierarchy_desc;
	}
	public int getHierarchy_type_id() {
		return hierarchy_type_id;
	}
	public void setHierarchy_type_id(int hierarchy_type_id) {
		this.hierarchy_type_id = hierarchy_type_id;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getActive_flg() {
		return active_flg;
	}
	public void setActive_flg(int active_flg) {
		this.active_flg = active_flg;
	}
	
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}


	private int hierarchy_id;
	private String hierarchy_name;
	private int client_id;
	private String hierarchy_desc;
	private int hierarchy_type_id;
	private int parent_id;
	private int active_flg;
	private String client_name;
	private String code;
	private String capacity;
}
