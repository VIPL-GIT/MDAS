package com.rtdas.common.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="psl_consumer")
public class PSLConsumer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int consumer_id;
	private String consumer_name;
	private String consumer_code;
	private String contract_load;
	
	private String aggrement_start_date;
	private String aggrement_end_date;
	private String address;
	private String mobile;
	private String email_id;
	private String changed_by;
	private String changed_datetime;
	private int feeder_id;
	private java.sql.Timestamp log_timestamp;
	
	@Transient
	private String feeder_name;

	public int getConsumer_id() {
		return consumer_id;
	}

	public void setConsumer_id(int consumer_id) {
		this.consumer_id = consumer_id;
	}

	public String getConsumer_name() {
		return consumer_name;
	}

	public void setConsumer_name(String consumer_name) {
		this.consumer_name = consumer_name;
	}

	public String getConsumer_code() {
		return consumer_code;
	}

	public void setConsumer_code(String consumer_code) {
		this.consumer_code = consumer_code;
	}

	public String getContract_load() {
		return contract_load;
	}

	public void setContract_load(String contract_load) {
		this.contract_load = contract_load;
	}

	public String getAggrement_start_date() {
		return aggrement_start_date;
	}

	public void setAggrement_start_date(String aggrement_start_date) {
		this.aggrement_start_date = aggrement_start_date;
	}

	public String getAggrement_end_date() {
		return aggrement_end_date;
	}

	public void setAggrement_end_date(String aggrement_end_date) {
		this.aggrement_end_date = aggrement_end_date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
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

	public int getFeeder_id() {
		return feeder_id;
	}

	public void setFeeder_id(int feeder_id) {
		this.feeder_id = feeder_id;
	}

	public java.sql.Timestamp getLog_timestamp() {
		return log_timestamp;
	}

	public void setLog_timestamp(java.sql.Timestamp log_timestamp) {
		this.log_timestamp = log_timestamp;
	}

	public String getFeeder_name() {
		return feeder_name;
	}

	public void setFeeder_name(String feeder_name) {
		this.feeder_name = feeder_name;
	}
	
	

}
