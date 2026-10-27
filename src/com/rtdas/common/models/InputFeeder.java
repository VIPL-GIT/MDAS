package com.rtdas.common.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="input_feeder")
public class InputFeeder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int feederid;
	private String feedername;
	private String structure_code;
	private String voltage_level;	
	private Integer substationid;
	private String changed_by;
	private String changed_datetime;
	private java.sql.Timestamp log_timestamp;
	
	@Transient
	private String substation_name;

	public int getFeederid() {
		return feederid;
	}

	public void setFeederid(int feederid) {
		this.feederid = feederid;
	}

	public String getFeedername() {
		return feedername;
	}

	public void setFeedername(String feedername) {
		this.feedername = feedername;
	}

	public String getStructure_code() {
		return structure_code;
	}

	public void setStructure_code(String structure_code) {
		this.structure_code = structure_code;
	}

	public String getVoltage_level() {
		return voltage_level;
	}

	public void setVoltage_level(String voltage_level) {
		this.voltage_level = voltage_level;
	}

	public Integer getSubstationid() {
		return substationid;
	}

	public void setSubstationid(Integer substationid) {
		this.substationid = substationid;
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

	public String getSubstation_name() {
		return substation_name;
	}

	public void setSubstation_name(String substation_name) {
		this.substation_name = substation_name;
	}
	
	

}