package com.rtdas.feeder_reports.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class AnalyticsDemandForecasting {

	
	private int recordid;
	private String meter_serial_no;
	private String month;
	private String forcast_year_based_kw;
	private String forcast_month_based_kw;
	private String observation;
	private String log_timestamp;
	
	
	
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
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
	public String getForcast_year_based_kw() {
		return forcast_year_based_kw;
	}
	public void setForcast_year_based_kw(String forcast_year_based_kw) {
		this.forcast_year_based_kw = forcast_year_based_kw;
	}
	public String getForcast_month_based_kw() {
		return forcast_month_based_kw;
	}
	public void setForcast_month_based_kw(String forcast_month_based_kw) {
		this.forcast_month_based_kw = forcast_month_based_kw;
	}
	public String getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(String log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	
	
}
