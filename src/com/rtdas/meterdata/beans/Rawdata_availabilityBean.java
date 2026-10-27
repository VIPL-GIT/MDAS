package com.rtdas.meterdata.beans;

public class Rawdata_availabilityBean {

	private String isAvailable;
	private TotalMasterdataBean tmb;
	private String path;
	
	
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	public TotalMasterdataBean getTmb() {
		return tmb;
	}
	public void setTmb(TotalMasterdataBean tmb) {
		this.tmb = tmb;
	}
	
	
}
