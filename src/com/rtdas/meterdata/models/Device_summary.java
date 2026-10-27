package com.rtdas.meterdata.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="device_summary")
public class Device_summary {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String meter_serial_no;
	private String modem_serial_no;
	private String log_timestamp;
	private String discom_name;
	private String zonename;
	private String circlename;
	private String divisionname;
	private String subdivname;
	private String mandalname;
	private String sectionname;
	private String substationname;
	private String feedername;
	private String locationname;
	private String metermakename;
	private String status;
	private String issue;
	private String recordStatus;
	private String prog_meter_serial_no;
	private String mapping;
	private String installationpoint;
	private String imeino;
	private String simno;
	private String ipaddress;
	private String msisdnno;
	private String fv;
	private String signal_strength;
	private String comm_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMeter_serial_no() {
		return meter_serial_no;
	}
	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}
	public String getModem_serial_no() {
		return modem_serial_no;
	}
	public void setModem_serial_no(String modem_serial_no) {
		this.modem_serial_no = modem_serial_no;
	}
	public String getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(String log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	public String getDiscom_name() {
		return discom_name;
	}
	public void setDiscom_name(String discom_name) {
		this.discom_name = discom_name;
	}
	public String getZonename() {
		return zonename;
	}
	public void setZonename(String zonename) {
		this.zonename = zonename;
	}
	public String getCirclename() {
		return circlename;
	}
	public void setCirclename(String circlename) {
		this.circlename = circlename;
	}
	public String getDivisionname() {
		return divisionname;
	}
	public void setDivisionname(String divisionname) {
		this.divisionname = divisionname;
	}
	public String getSubdivname() {
		return subdivname;
	}
	public void setSubdivname(String subdivname) {
		this.subdivname = subdivname;
	}
	public String getMandalname() {
		return mandalname;
	}
	public void setMandalname(String mandalname) {
		this.mandalname = mandalname;
	}
	public String getSectionname() {
		return sectionname;
	}
	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}
	public String getSubstationname() {
		return substationname;
	}
	public void setSubstationname(String substationname) {
		this.substationname = substationname;
	}
	public String getFeedername() {
		return feedername;
	}
	public void setFeedername(String feedername) {
		this.feedername = feedername;
	}
	public String getLocationname() {
		return locationname;
	}
	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}
	public String getMetermakename() {
		return metermakename;
	}
	public void setMetermakename(String metermakename) {
		this.metermakename = metermakename;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getProg_meter_serial_no() {
		return prog_meter_serial_no;
	}
	public void setProg_meter_serial_no(String prog_meter_serial_no) {
		this.prog_meter_serial_no = prog_meter_serial_no;
	}
	public String getMapping() {
		return mapping;
	}
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
	public String getInstallationpoint() {
		return installationpoint;
	}
	public void setInstallationpoint(String installationpoint) {
		this.installationpoint = installationpoint;
	}
	public String getImeino() {
		return imeino;
	}
	public void setImeino(String imeino) {
		this.imeino = imeino;
	}
	public String getSimno() {
		return simno;
	}
	public void setSimno(String simno) {
		this.simno = simno;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getMsisdnno() {
		return msisdnno;
	}
	public void setMsisdnno(String msisdnno) {
		this.msisdnno = msisdnno;
	}
	public String getFv() {
		return fv;
	}
	public void setFv(String fv) {
		this.fv = fv;
	}
	public String getSignal_strength() {
		return signal_strength;
	}
	public void setSignal_strength(String signal_strength) {
		this.signal_strength = signal_strength;
	}
	public String getComm_date() {
		return comm_date;
	}
	public void setComm_date(String comm_date) {
		this.comm_date = comm_date;
	}
	
	

}
