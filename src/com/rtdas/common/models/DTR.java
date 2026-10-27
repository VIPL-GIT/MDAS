package com.rtdas.common.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="dtr")
public class DTR {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int dtrid;
	private String dtrname;
	private String structure_code;
	private String capacity;	
	private int feederid;
	private String changed_by;
	private String changed_datetime;
	private java.sql.Timestamp log_timestamp;
	
	@Transient
	private String feeder_name;

	public int getDtrid() {
		return dtrid;
	}

	public void setDtrid(int dtrid) {
		this.dtrid = dtrid;
	}

	public String getDtrname() {
		return dtrname;
	}

	public void setDtrname(String dtrname) {
		this.dtrname = dtrname;
	}

	public String getStructure_code() {
		return structure_code;
	}

	public void setStructure_code(String structure_code) {
		this.structure_code = structure_code;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public int getFeederid() {
		return feederid;
	}

	public void setFeederid(int feederid) {
		this.feederid = feederid;
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

	public String getFeeder_name() {
		return feeder_name;
	}

	public void setFeeder_name(String feeder_name) {
		this.feeder_name = feeder_name;
	}
	
	

}

