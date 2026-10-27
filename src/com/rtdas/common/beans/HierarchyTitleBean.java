package com.rtdas.common.beans;

public class HierarchyTitleBean {

	
	public int getHierarchy_type_id() {
		return hierarchy_type_id;
	}
	public void setHierarchy_type_id(int hierarchy_type_id) {
		this.hierarchy_type_id = hierarchy_type_id;
	}
	public String getHierarchy_title() {
		return hierarchy_title;
	}
	public void setHierarchy_title(String hierarchy_title) {
		this.hierarchy_title = hierarchy_title;
	}
	public String getTitle_desc() {
		return title_desc;
	}
	public void setTitle_desc(String title_desc) {
		this.title_desc = title_desc;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int getParent_title_id() {
		return parent_title_id;
	}
	public void setParent_title_id(int parent_title_id) {
		this.parent_title_id = parent_title_id;
	}
	public int getActive_flg() {
		return active_flg;
	}
	public void setActive_flg(int active_flg) {
		this.active_flg = active_flg;
	}
	
	public String getParent_title() {
		return parent_title;
	}
	public void setParent_title(String parent_title) {
		this.parent_title = parent_title;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public Integer getDevice_location() {
		return device_location;
	}
	public void setDevice_location(Integer device_location) {
		this.device_location = device_location;
	}

	private int hierarchy_type_id;
	private String hierarchy_title;
	private String title_desc;
	private int client_id;
	private int parent_title_id;
	private int active_flg;
	private String parent_title;
	private String client_name;
	private Integer device_location;
}
