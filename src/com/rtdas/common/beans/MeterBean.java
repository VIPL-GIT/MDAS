package com.rtdas.common.beans;

public class MeterBean {
    
private int meter_id;
	
	private String meter_sl_no;
	private String modem_sl_no;
	private String installation_date;
	private String meter_type;
	private String lattitude;
	private String longitude;
	private String location_id;
	private String kwh;
	private String kvah;
	private String me_ct;
	private String me_pt;
	private String ad_ct;
	private String ad_pt;	
	private String mf;
	private String install_point;
	private String metermake;
	private String meterlocation;
	private String changed_by;
	private String changed_datetime;
	private java.sql.Timestamp log_timestamp;
	
	
	
	
	public String getMeterlocation() {
		return meterlocation;
	}
	public void setMeterlocation(String meterlocation) {
		this.meterlocation = meterlocation;
	}
	public String getMf() {
		return mf;
	}
	public void setMf(String mf) {
		this.mf = mf;
	}
	public String getInstall_point() {
		return install_point;
	}
	public void setInstall_point(String install_point) {
		this.install_point = install_point;
	}
	public String getMetermake() {
		return metermake;
	}
	public void setMetermake(String metermake) {
		this.metermake = metermake;
	}
	private String location_name;
	
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public int getMeter_id() {
		return meter_id;
	}
	public void setMeter_id(int meter_id) {
		this.meter_id = meter_id;
	}
	public String getMeter_sl_no() {
		return meter_sl_no;
	}
	public void setMeter_sl_no(String meter_sl_no) {
		this.meter_sl_no = meter_sl_no;
	}
	public String getModem_sl_no() {
		return modem_sl_no;
	}
	public void setModem_sl_no(String modem_sl_no) {
		this.modem_sl_no = modem_sl_no;
	}
	public String getInstallation_date() {
		return installation_date;
	}
	public void setInstallation_date(String installation_date) {
		this.installation_date = installation_date;
	}
	public String getMeter_type() {
		return meter_type;
	}
	public void setMeter_type(String meter_type) {
		this.meter_type = meter_type;
	}
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public String getKwh() {
		return kwh;
	}
	public void setKwh(String kwh) {
		this.kwh = kwh;
	}
	public String getKvah() {
		return kvah;
	}
	public void setKvah(String kvah) {
		this.kvah = kvah;
	}
	public String getMe_ct() {
		return me_ct;
	}
	public void setMe_ct(String me_ct) {
		this.me_ct = me_ct;
	}
	public String getMe_pt() {
		return me_pt;
	}
	public void setMe_pt(String me_pt) {
		this.me_pt = me_pt;
	}
	public String getAd_ct() {
		return ad_ct;
	}
	public void setAd_ct(String ad_ct) {
		this.ad_ct = ad_ct;
	}
	public String getAd_pt() {
		return ad_pt;
	}
	public void setAd_pt(String ad_pt) {
		this.ad_pt = ad_pt;
	}
	public String getChanged_by() {
		return changed_by;
	}
	public void setChanged_by(String changed_by) {
		this.changed_by = changed_by;
	}
	public String getChanged_datetime() {
		return changed_datetime;
	}
	public void setChanged_datetime(String changed_datetime) {
		this.changed_datetime = changed_datetime;
	}
	public java.sql.Timestamp getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(java.sql.Timestamp log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	
	
	
}
