package com.rtdas.vee.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vee_rules")
public class VEERules {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String rule_name;
	private String params;
	private String param_values;
	private String formula;
	private String is_editable;
	private String created_by;
	private String log_timestamp;
	
	
	public String getIs_editable() {
		return is_editable;
	}
	public void setIs_editable(String is_editable) {
		this.is_editable = is_editable;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRule_name() {
		return rule_name;
	}
	public void setRule_name(String rule_name) {
		this.rule_name = rule_name;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getParam_values() {
		return param_values;
	}
	public void setParam_values(String param_values) {
		this.param_values = param_values;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(String log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	
	
	

}
