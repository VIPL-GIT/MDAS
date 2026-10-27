package com.rtdas.meterdata.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.rtdas.meterdata.beans.TotalMasterdataBean;

@Entity
@Table(name="billing_summary")
public class Billing_summary {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int 	recordid;
	private String	SLA;
	private String	meter_serial_no;
	private String	modem_serial_no;
	private String	imeino;
	private String	ipaddress;
	private String	d2;
	private String	d3;
	private String	d4;
	private String	d5;
	private String	d6;
	private String	recentAutoReset;
	private String	bill_at_least_once;
	private String	pres_month_billing;
	private String	cmd_availability;
	private String	lastDataAvailability;
	private String	metermake;
	private String	issue;
	private String	issue_scope;
	
	@Transient
	private TotalMasterdataBean master;
	
	public TotalMasterdataBean getMaster() {
		return master;
	}
	public void setMaster(TotalMasterdataBean master) {
		this.master = master;
	}
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public String getSLA() {
		return SLA;
	}
	public void setSLA(String sLA) {
		SLA = sLA;
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
	public String getImeino() {
		return imeino;
	}
	public void setImeino(String imeino) {
		this.imeino = imeino;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getD2() {
		return d2;
	}
	public void setD2(String d2) {
		this.d2 = d2;
	}
	public String getD3() {
		return d3;
	}
	public void setD3(String d3) {
		this.d3 = d3;
	}
	public String getD4() {
		return d4;
	}
	public void setD4(String d4) {
		this.d4 = d4;
	}
	public String getD5() {
		return d5;
	}
	public void setD5(String d5) {
		this.d5 = d5;
	}
	public String getD6() {
		return d6;
	}
	public void setD6(String d6) {
		this.d6 = d6;
	}
	public String getRecentAutoReset() {
		return recentAutoReset;
	}
	public void setRecentAutoReset(String recentAutoReset) {
		this.recentAutoReset = recentAutoReset;
	}
	public String getBill_at_least_once() {
		return bill_at_least_once;
	}
	public void setBill_at_least_once(String bill_at_least_once) {
		this.bill_at_least_once = bill_at_least_once;
	}
	public String getPres_month_billing() {
		return pres_month_billing;
	}
	public void setPres_month_billing(String pres_month_billing) {
		this.pres_month_billing = pres_month_billing;
	}
	public String getCmd_availability() {
		return cmd_availability;
	}
	public void setCmd_availability(String cmd_availability) {
		this.cmd_availability = cmd_availability;
	}
	public String getLastDataAvailability() {
		return lastDataAvailability;
	}
	public void setLastDataAvailability(String lastDataAvailability) {
		this.lastDataAvailability = lastDataAvailability;
	}
	public String getMetermake() {
		return metermake;
	}
	public void setMetermake(String metermake) {
		this.metermake = metermake;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getIssue_scope() {
		return issue_scope;
	}
	public void setIssue_scope(String issue_scope) {
		this.issue_scope = issue_scope;
	}
	
	
	

	
}
