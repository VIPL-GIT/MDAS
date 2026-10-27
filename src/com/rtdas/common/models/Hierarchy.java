package com.rtdas.common.models;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hierarchy")
public class Hierarchy implements Serializable{
	private static final long serialVersionUID = 5462223600l;
	
    @Id
    @Column(name="hierarchy_id")
	private int hierarchy_id;
    @Column(name="hierarchy_name")
	private String hierarchy_name;
    @Column(name="client_id")
	private int client_id;
    @Column(name="hierarchy_desc")
	private String hierarchy_desc;
    @Column(name="hierarchy_type_id")
	private int hierarchy_type_id;
    @Column(name="parent_id")
	private int parent_id;
    @Column(name="active_flg")
    private int active_flg;
    
    private String code;
    private String capacity;
   
    
    
    
    
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
    
    
}
