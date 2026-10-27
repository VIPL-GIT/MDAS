package com.rtdas.common.models;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client implements Serializable{
	
	private static final long serialVersionUID = 5462223600l;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CLIENT_ID")
	private int client_id;
	@Column(name="client_name")
	private String client_name;
	@Column(name="about_client")
	private String about_client;
	@Column(name="client_logo")
	private String client_logo;
	@Column(name="client_add1")
	private String client_add1;
	@Column(name="client_add2")
	private String client_add2;
	@Column(name="email")
	private String email;
	@Column(name="mobile")
	private String mobile;
	@Column(name="contact_person")
	private String contact_person;
	@Column(name="active_flg")
	private int active_flg;
	
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getAbout_client() {
		return about_client;
	}
	public void setAbout_client(String about_client) {
		this.about_client = about_client;
	}
	public String getClient_logo() {
		return client_logo;
	}
	public void setClient_logo(String client_logo) {
		this.client_logo = client_logo;
	}
	public String getClient_add1() {
		return client_add1;
	}
	public void setClient_add1(String client_add1) {
		this.client_add1 = client_add1;
	}
	public String getClient_add2() {
		return client_add2;
	}
	public void setClient_add2(String client_add2) {
		this.client_add2 = client_add2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContact_person() {
		return contact_person;
	}
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	public int getActive_flg() {
		return active_flg;
	}
	public void setActive_flg(int active_flg) {
		this.active_flg = active_flg;
	}
	

}
