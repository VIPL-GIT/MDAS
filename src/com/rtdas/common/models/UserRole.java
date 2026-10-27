package com.rtdas.common.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id")
	private int role_id;
	
	@Column(name="role_title")
	private String role_title;
	
	@Column(name="client_id")
	private int client_id;
	
	@Column(name="role_desc")
	private String role_desc;
	
	@Column(name="parent_role_id")
	private int parent_role_id;
	
	@Column(name="active_flg")
	private int active_flg;
	@Column(name="hierarchy_level")
	private Integer hierarchy_level;

	
	public Integer getHierarchy_level() {
		return hierarchy_level;
	}

	public void setHierarchy_level(Integer hierarchy_level) {
		this.hierarchy_level = hierarchy_level;
	}

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
	
	

}
