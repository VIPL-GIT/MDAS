package com.rtdas.common.models;
import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class Users implements Serializable{
	
	private static final long serialVersionUID = 5462223600l;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SLNO", unique=true, nullable=false, precision=10)
	private int slno;
	
	@Column(name="USER_ID")
	private String userId;
	@Column(name="CLIENT_ID")
	private int clientId;
	@Column(name="USER_FULL_NAME")
	private String userFullName;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="MOBILE")
	private String mobileNumber;
	@Column(name="USER_IMAGE")
	private String userImage;
	@Column(name="ROLE_ID")
	private int rollId;
	@Column(name="ACTIVE_FLG")
	private int activeFlg;
	
	@Column(name="hierarchy")
	private String hierarchy;
	
	@Column(name="new_password")
	private String new_password;
	
	
	
	
	public String getNew_password() {
		return new_password;
	}
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
	public String getHierarchy() {
		return hierarchy;
	}
	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}
	public int getSlno() {
		return slno;
	}
	public void setSlno(int id) {
		this.slno = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserFullName() {
		return userFullName;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public int getRollId() {
		return rollId;
	}
	public void setRollId(int rollId) {
		this.rollId = rollId;
	}
	public int getActiveFlg() {
		return activeFlg;
	}
	public void setActiveFlg(Integer activeFlg) {
		this.activeFlg = activeFlg;
	}
	

}
