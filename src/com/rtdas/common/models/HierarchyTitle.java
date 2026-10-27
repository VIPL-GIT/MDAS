package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hierarchy_master")
public class HierarchyTitle implements Serializable{
	private static final long serialVersionUID = 5462223600l;
	
    @Id
    @Column(name="hierarchy_type_id")
	private int hierarchy_type_id;
    @Column(name="hierarchy_title")
	private String hierarchy_title;
    @Column(name="title_desc")
	private String title_desc;
    @Column(name="client_id")
	private int client_id;
    @Column(name="parent_title_id")
	private int parent_title_id;
    @Column(name="active_flg", columnDefinition="int default '1'")
	private int active_flg;
    @Column(name="device_location")
    private Integer device_location;
    
    
    
	public Integer getDevice_location() {
		return device_location;
	}
	public void setDevice_location(Integer device_location) {
		this.device_location = device_location;
	}
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
    
    
}
