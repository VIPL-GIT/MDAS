package com.rtdas.common.beans;

import org.springframework.web.multipart.MultipartFile;

public class ClientBean {

	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getAboutClient() {
		return aboutClient;
	}
	public void setAboutClient(String aboutClient) {
		this.aboutClient = aboutClient;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
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
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public int getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
 
	private int clientId; 
	private String clientName;
	private String aboutClient;
	private String add1;
	private String add2;
	private String email;
	private String mobile;
	private String contactPerson;
	private String submodules;
	private String clientImg;
	private MultipartFile client_img_file;
	
	public String getClientImg() {
		return clientImg;
	}
	public void setClientImg(String clientImg) {
		this.clientImg = clientImg;
	}
	public MultipartFile getClient_img_file() {
		return client_img_file;
	}
	public void setClient_img_file(MultipartFile client_img_file) {
		this.client_img_file = client_img_file;
	}
	public String getSubmodules() {
		return submodules;
	}
	public void setSubmodules(String submodules) {
		this.submodules = submodules;
	}

	private int activeStatus;
	
	
}
