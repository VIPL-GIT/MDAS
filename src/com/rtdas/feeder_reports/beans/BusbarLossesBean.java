package com.rtdas.feeder_reports.beans;

public class BusbarLossesBean {

	private String meter;
	private String initial_kwh;
	private String final_kwh;
	private String diff;
	private String diff_with_mf;
	
	public String getMeter() {
		return meter;
	}
	public void setMeter(String meter) {
		this.meter = meter;
	}
	public String getInitial_kwh() {
		return initial_kwh;
	}
	public void setInitial_kwh(String initial_kwh) {
		this.initial_kwh = initial_kwh;
	}
	public String getFinal_kwh() {
		return final_kwh;
	}
	public void setFinal_kwh(String final_kwh) {
		this.final_kwh = final_kwh;
	}
	public String getDiff() {
		return diff;
	}
	public void setDiff(String diff) {
		this.diff = diff;
	}
	public String getDiff_with_mf() {
		return diff_with_mf;
	}
	public void setDiff_with_mf(String diff_with_mf) {
		this.diff_with_mf = diff_with_mf;
	}
	
	
}
