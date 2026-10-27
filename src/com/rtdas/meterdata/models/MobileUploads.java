package com.rtdas.meterdata.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="analytics_mri_upload_summary")
public class MobileUploads {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int	recordid;
	private String	meter_serial_no;
	private String	month;
	private String	originalname;
	private String	renamedname;
	private String	filesize;
	private String	extension;
	private String	rtc;
	private String	source;
	private String	readings_in_mri_file;
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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getOriginalname() {
		return originalname;
	}
	public void setOriginalname(String originalname) {
		this.originalname = originalname;
	}
	public String getRenamedname() {
		return renamedname;
	}
	public void setRenamedname(String renamedname) {
		this.renamedname = renamedname;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getRtc() {
		return rtc;
	}
	public void setRtc(String rtc) {
		this.rtc = rtc;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getReadings_in_mri_file() {
		return readings_in_mri_file;
	}
	public void setReadings_in_mri_file(String readings_in_mri_file) {
		this.readings_in_mri_file = readings_in_mri_file;
	}

	
	
}
