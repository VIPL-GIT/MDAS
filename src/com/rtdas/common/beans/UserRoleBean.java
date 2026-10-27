package com.rtdas.common.beans;

import java.util.*;

public class UserRoleBean {

	 
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_title() {
		return role_title;
	}
	public void setRole_title(String role_title) {
		this.role_title = role_title;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public String getRole_desc() {
		return role_desc;
	}
	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}
	public int getParent_role_id() {
		return parent_role_id;
	}
	public void setParent_role_id(int parent_role_id) {
		this.parent_role_id = parent_role_id;
	}
	public int getActive_flg() {
		return active_flg;
	}
	public void setActive_flg(int active_flg) {
		this.active_flg = active_flg;
	}
	
	public String getAction_flgs() {
		return action_flgs;
	}
	public void setAction_flgs(String action_flgs) {
		this.action_flgs = action_flgs;
	}

	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getParent_role_name() {
		return parent_role_name;
	}
	public void setParent_role_name(String parent_role_name) {
		this.parent_role_name = parent_role_name;
	}

	public Integer getHierarchy_level() {
		return hierarchy_level;
	}
	public void setHierarchy_level(Integer hierarchy_level) {
		this.hierarchy_level = hierarchy_level;
	}

	private int role_id;
	private String role_title;
	private int client_id;
	private String role_desc;
	private int parent_role_id;
	private int active_flg;
	private String action_flgs;
	
	private String client_name;
	private String parent_role_name;
	private Integer hierarchy_level;
}
