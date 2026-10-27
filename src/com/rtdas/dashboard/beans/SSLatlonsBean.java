package com.rtdas.dashboard.beans;

import java.util.List;

public class SSLatlonsBean {

	private String ss_name;
	private List<MeterLatLons> meters;
	
	public String getSs_name() {
		return ss_name;
	}
	public void setSs_name(String ss_name) {
		this.ss_name = ss_name;
	}
	public List<MeterLatLons> getMeters() {
		return meters;
	}
	public void setMeters(List<MeterLatLons> meters) {
		this.meters = meters;
	}
	
	
}
