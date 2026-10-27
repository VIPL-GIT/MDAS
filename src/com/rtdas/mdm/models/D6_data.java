package com.rtdas.mdm.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="d6_data")
public class D6_data {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int app_id;
	private int CDFID;
	private String SNAPSHOT_DATE;
	private String DATA_STRING;
	private String METER_SERIAL_NO;
	private String D6_TIME_STAMP;
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public int getCDFID() {
		return CDFID;
	}
	public void setCDFID(int cDFID) {
		CDFID = cDFID;
	}
	public String getSNAPSHOT_DATE() {
		return SNAPSHOT_DATE;
	}
	public void setSNAPSHOT_DATE(String sNAPSHOT_DATE) {
		SNAPSHOT_DATE = sNAPSHOT_DATE;
	}
	public String getDATA_STRING() {
		return DATA_STRING;
	}
	public void setDATA_STRING(String dATA_STRING) {
		DATA_STRING = dATA_STRING;
	}
	public String getMETER_SERIAL_NO() {
		return METER_SERIAL_NO;
	}
	public void setMETER_SERIAL_NO(String mETER_SERIAL_NO) {
		METER_SERIAL_NO = mETER_SERIAL_NO;
	}
	public String getD6_TIME_STAMP() {
		return D6_TIME_STAMP;
	}
	public void setD6_TIME_STAMP(String d6_TIME_STAMP) {
		D6_TIME_STAMP = d6_TIME_STAMP;
	}
	
	
}
