package com.rtdas.meterdata.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WSResponseDataBean {

	private List<WSMasterDataBean> mrmaster;
	private String status;
	private String token_no;
	private String sdo;
	private String binder;
	private String bm;
	private String by;
	private String totalRecords;
	
	@JsonProperty("MRMASTER")
	public List<WSMasterDataBean> getMrmaster() {
		return mrmaster;
	}
	public void setMrmaster(List<WSMasterDataBean> mrmaster) {
		this.mrmaster = mrmaster;
	}
	
	@JsonProperty("STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonProperty("TOKEN_NO")
	public String getToken_no() {
		return token_no;
	}
	public void setToken_no(String token_no) {
		this.token_no = token_no;
	}
	public String getSdo() {
		return sdo;
	}
	public void setSdo(String sdo) {
		this.sdo = sdo;
	}
	public String getBinder() {
		return binder;
	}
	public void setBinder(String binder) {
		this.binder = binder;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	
	
}
