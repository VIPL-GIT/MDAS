package com.rtdas.vee.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vee_summary_daily")
public class Vee_summary_daily {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int recordid;
	private String meter_serial_no;
	private String comm_date;
	private String rtc_check;
	private String spike_check;
	private String kvarh_check;
	private String sum_check;
	private String high_low_check;
	private String zero_check;
	private String negative_usage_check;
	private String consecutive_interval_check;
	private String tod_summation_check;
	private String inactive_measurement_check;
	private String time_tolerance_check;
	private String version;
	private String created_by;
	private String updated_by;
	private String log_timestamp;
	
	
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
	public String getComm_date() {
		return comm_date;
	}
	public void setComm_date(String comm_date) {
		this.comm_date = comm_date;
	}
	public String getRtc_check() {
		return rtc_check;
	}
	public void setRtc_check(String rtc_check) {
		this.rtc_check = rtc_check;
	}
	public String getSpike_check() {
		return spike_check;
	}
	public void setSpike_check(String spike_check) {
		this.spike_check = spike_check;
	}
	public String getKvarh_check() {
		return kvarh_check;
	}
	public void setKvarh_check(String kvarh_check) {
		this.kvarh_check = kvarh_check;
	}
	public String getSum_check() {
		return sum_check;
	}
	public void setSum_check(String sum_check) {
		this.sum_check = sum_check;
	}
	public String getHigh_low_check() {
		return high_low_check;
	}
	public void setHigh_low_check(String high_low_check) {
		this.high_low_check = high_low_check;
	}
	public String getZero_check() {
		return zero_check;
	}
	public void setZero_check(String zero_check) {
		this.zero_check = zero_check;
	}
	public String getNegative_usage_check() {
		return negative_usage_check;
	}
	public void setNegative_usage_check(String negative_usage_check) {
		this.negative_usage_check = negative_usage_check;
	}
	public String getConsecutive_interval_check() {
		return consecutive_interval_check;
	}
	public void setConsecutive_interval_check(String consecutive_interval_check) {
		this.consecutive_interval_check = consecutive_interval_check;
	}
	public String getTod_summation_check() {
		return tod_summation_check;
	}
	public void setTod_summation_check(String tod_summation_check) {
		this.tod_summation_check = tod_summation_check;
	}
	public String getInactive_measurement_check() {
		return inactive_measurement_check;
	}
	public void setInactive_measurement_check(String inactive_measurement_check) {
		this.inactive_measurement_check = inactive_measurement_check;
	}
	public String getTime_tolerance_check() {
		return time_tolerance_check;
	}
	public void setTime_tolerance_check(String time_tolerance_check) {
		this.time_tolerance_check = time_tolerance_check;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	public String getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(String log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	
	
}
