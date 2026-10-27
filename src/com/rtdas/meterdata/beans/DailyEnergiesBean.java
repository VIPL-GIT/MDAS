package com.rtdas.meterdata.beans;

public class DailyEnergiesBean {

	private String date;
	private String kwh;
	private String consumption;
	private String md_kw;
	private String md_kva;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getKwh() {
		return kwh;
	}
	public void setKwh(String kwh) {
		this.kwh = kwh;
	}
	public String getConsumption() {
		return consumption;
	}
	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}
	public String getMd_kw() {
		return md_kw;
	}
	public void setMd_kw(String md_kw) {
		this.md_kw = md_kw;
	}
	public String getMd_kva() {
		return md_kva;
	}
	public void setMd_kva(String md_kva) {
		this.md_kva = md_kva;
	}
	
	
}
