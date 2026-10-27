package com.rtdas.reports.beans;

import java.util.List;
import java.util.Map;

public class DashMeterDataAnalysisBean {

	private Map<String,String> pf_lag_meters;
	private Map<String,String> pf_lead_meters;
	
	private Map<String,String> lowPf_meters;
	private Map<String,String> mdViolation_meters;
	
	
	
	public Map<String, String> getPf_lag_meters() {
		return pf_lag_meters;
	}
	public void setPf_lag_meters(Map<String, String> pf_lag_meters) {
		this.pf_lag_meters = pf_lag_meters;
	}
	public Map<String, String> getPf_lead_meters() {
		return pf_lead_meters;
	}
	public void setPf_lead_meters(Map<String, String> pf_lead_meters) {
		this.pf_lead_meters = pf_lead_meters;
	}
	public Map<String, String> getLowPf_meters() {
		return lowPf_meters;
	}
	public void setLowPf_meters(Map<String, String> lowPf_meters) {
		this.lowPf_meters = lowPf_meters;
	}
	public Map<String, String> getMdViolation_meters() {
		return mdViolation_meters;
	}
	public void setMdViolation_meters(Map<String, String> mdViolation_meters) {
		this.mdViolation_meters = mdViolation_meters;
	}
	
	
	
	
}
