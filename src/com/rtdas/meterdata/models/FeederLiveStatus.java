package com.rtdas.meterdata.models;

import javax.persistence.Entity;
import javax.persistence.Table;

public class FeederLiveStatus {

	private int	recordid;
	private String	meter_serial_no;
	private String	meter_rtc;
	private String	modem_rtc;
	private String	adjusted_rtc;
	private double	vr;
	private double	vy;
	private double	vb;
	private double	lr;
	private double	ly;
	private double	lb;
	private double	kw;
	private double	kva;
	private double	kvar;
	private double	pfr;
	private double	pfy;
	private double	pfb;
	private double	pfavg;
	private double	freq;
	private double	mf;
	
	private String	status;
	private String	ct_status;
	private String	ct_missing_phases;
	private String	pt_status;
	private String	pt_missing_phases;
	private String	pt_observation;
	private String	pf_status;
	private String	date;
	private String	phasesequence;
	private String	currentsequence;
	private String vmf;
	private String imf;
	
	
	
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
	public String getMeter_rtc() {
		return meter_rtc;
	}
	public void setMeter_rtc(String meter_rtc) {
		this.meter_rtc = meter_rtc;
	}
	public String getModem_rtc() {
		return modem_rtc;
	}
	public void setModem_rtc(String modem_rtc) {
		this.modem_rtc = modem_rtc;
	}
	public String getAdjusted_rtc() {
		return adjusted_rtc;
	}
	public void setAdjusted_rtc(String adjusted_rtc) {
		this.adjusted_rtc = adjusted_rtc;
	}
	public double getVr() {
		return vr;
	}
	public void setVr(double vr) {
		this.vr = vr;
	}
	public double getVy() {
		return vy;
	}
	public void setVy(double vy) {
		this.vy = vy;
	}
	public double getVb() {
		return vb;
	}
	public void setVb(double vb) {
		this.vb = vb;
	}
	public double getLr() {
		return lr;
	}
	public void setLr(double lr) {
		this.lr = lr;
	}
	public double getLy() {
		return ly;
	}
	public void setLy(double ly) {
		this.ly = ly;
	}
	public double getLb() {
		return lb;
	}
	public void setLb(double lb) {
		this.lb = lb;
	}
	public double getKw() {
		return kw;
	}
	public void setKw(double kw) {
		this.kw = kw;
	}
	public double getKva() {
		return kva;
	}
	public void setKva(double kva) {
		this.kva = kva;
	}
	public double getKvar() {
		return kvar;
	}
	public void setKvar(double kvar) {
		this.kvar = kvar;
	}
	public double getPfr() {
		return pfr;
	}
	public void setPfr(double pfr) {
		this.pfr = pfr;
	}
	public double getPfy() {
		return pfy;
	}
	public void setPfy(double pfy) {
		this.pfy = pfy;
	}
	public double getPfb() {
		return pfb;
	}
	public void setPfb(double pfb) {
		this.pfb = pfb;
	}
	public double getPfavg() {
		return pfavg;
	}
	public void setPfavg(double pfavg) {
		this.pfavg = pfavg;
	}
	public double getFreq() {
		return freq;
	}
	public void setFreq(double freq) {
		this.freq = freq;
	}
	public double getMf() {
		return mf;
	}
	public void setMf(double mf) {
		this.mf = mf;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCt_status() {
		return ct_status;
	}
	public void setCt_status(String ct_status) {
		this.ct_status = ct_status;
	}
	public String getCt_missing_phases() {
		return ct_missing_phases;
	}
	public void setCt_missing_phases(String ct_missing_phases) {
		this.ct_missing_phases = ct_missing_phases;
	}
	public String getPt_status() {
		return pt_status;
	}
	public void setPt_status(String pt_status) {
		this.pt_status = pt_status;
	}
	public String getPt_missing_phases() {
		return pt_missing_phases;
	}
	public void setPt_missing_phases(String pt_missing_phases) {
		this.pt_missing_phases = pt_missing_phases;
	}
	public String getPt_observation() {
		return pt_observation;
	}
	public void setPt_observation(String pt_observation) {
		this.pt_observation = pt_observation;
	}
	public String getPf_status() {
		return pf_status;
	}
	public void setPf_status(String pf_status) {
		this.pf_status = pf_status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPhasesequence() {
		return phasesequence;
	}
	public void setPhasesequence(String phasesequence) {
		this.phasesequence = phasesequence;
	}
	public String getCurrentsequence() {
		return currentsequence;
	}
	public void setCurrentsequence(String currentsequence) {
		this.currentsequence = currentsequence;
	}
	public String getVmf() {
		return vmf;
	}
	public void setVmf(String vmf) {
		this.vmf = vmf;
	}
	public String getImf() {
		return imf;
	}
	public void setImf(String imf) {
		this.imf = imf;
	}

	
}
