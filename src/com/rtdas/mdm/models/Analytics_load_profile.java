package com.rtdas.mdm.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="analytics_load_profile")
public class Analytics_load_profile {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int	recordid;
	private String	meter_serial_no;
	private String	d4_date;
	private String	vr;
	private String	vy;
	private String	vb;
	private String	lr;
	private String	ly;
	private String	lb;
	private String	kwh;
	private String	kvah;
	private String	kvarhlag;
	private String	kvarhlead;
	private java.sql.Timestamp	log_timestamp;
	
	
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public String getMeter_serial_no() {
		return meter_serial_no;
	}
	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}
	public String getD4_date() {
		return d4_date;
	}
	public void setD4_date(String d4_date) {
		this.d4_date = d4_date;
	}
	public String getVr() {
		return vr;
	}
	public void setVr(String vr) {
		this.vr = vr;
	}
	public String getVy() {
		return vy;
	}
	public void setVy(String vy) {
		this.vy = vy;
	}
	public String getVb() {
		return vb;
	}
	public void setVb(String vb) {
		this.vb = vb;
	}
	public String getLr() {
		return lr;
	}
	public void setLr(String lr) {
		this.lr = lr;
	}
	public String getLy() {
		return ly;
	}
	public void setLy(String ly) {
		this.ly = ly;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
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
	public String getKvarhlag() {
		return kvarhlag;
	}
	public void setKvarhlag(String kvarhlag) {
		this.kvarhlag = kvarhlag;
	}
	public String getKvarhlead() {
		return kvarhlead;
	}
	public void setKvarhlead(String kvarhlead) {
		this.kvarhlead = kvarhlead;
	}
	public java.sql.Timestamp getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(java.sql.Timestamp log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	
	
	

}
