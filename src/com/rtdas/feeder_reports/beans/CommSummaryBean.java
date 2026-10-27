package com.rtdas.feeder_reports.beans;

public class CommSummaryBean {

	private String hlevel;
	private String hlevel_id;
	private String hlevel_name;
	private String feeder;
	private String voltage;
	private String category;
	private String meterno;
	
//-------comm summary
	private String tot_points;
	private String tot_33kv;
	private String tot_11kv;
	private String comm_points;
	private String comm_percent;
	private String mcf;
	private String modem_fail;
	private String power_nw_fail;
	private String notComm;
	private String commissioning_to_be_done;
	private String iNc_to_be_done;
	private String not_comm_percent;
	
//----------feeder comm summary
	private String comm_status;
	private String comm_signal_strength;
	private String not_comm_last_date;
	
	
	
	public String getNotComm() {
		return notComm;
	}
	public void setNotComm(String notComm) {
		this.notComm = notComm;
	}
	public String getCommissioning_to_be_done() {
		return commissioning_to_be_done;
	}
	public void setCommissioning_to_be_done(String commissioning_to_be_done) {
		this.commissioning_to_be_done = commissioning_to_be_done;
	}
	public String getiNc_to_be_done() {
		return iNc_to_be_done;
	}
	public void setiNc_to_be_done(String iNc_to_be_done) {
		this.iNc_to_be_done = iNc_to_be_done;
	}
	public String getHlevel() {
		return hlevel;
	}
	public void setHlevel(String hlevel) {
		this.hlevel = hlevel;
	}
	public String getHlevel_id() {
		return hlevel_id;
	}
	public void setHlevel_id(String hlevel_id) {
		this.hlevel_id = hlevel_id;
	}
	public String getHlevel_name() {
		return hlevel_name;
	}
	public void setHlevel_name(String hlevel_name) {
		this.hlevel_name = hlevel_name;
	}
	public String getFeeder() {
		return feeder;
	}
	public void setFeeder(String feeder) {
		this.feeder = feeder;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMeterno() {
		return meterno;
	}
	public void setMeterno(String meterno) {
		this.meterno = meterno;
	}
	public String getTot_points() {
		return tot_points;
	}
	public void setTot_points(String tot_points) {
		this.tot_points = tot_points;
	}
	public String getTot_33kv() {
		return tot_33kv;
	}
	public void setTot_33kv(String tot_33kv) {
		this.tot_33kv = tot_33kv;
	}
	public String getTot_11kv() {
		return tot_11kv;
	}
	public void setTot_11kv(String tot_11kv) {
		this.tot_11kv = tot_11kv;
	}
	public String getComm_points() {
		return comm_points;
	}
	public void setComm_points(String comm_points) {
		this.comm_points = comm_points;
	}
	public String getComm_percent() {
		return comm_percent;
	}
	public void setComm_percent(String comm_percent) {
		this.comm_percent = comm_percent;
	}
	public String getMcf() {
		return mcf;
	}
	public void setMcf(String mcf) {
		this.mcf = mcf;
	}
	public String getModem_fail() {
		return modem_fail;
	}
	public void setModem_fail(String modem_fail) {
		this.modem_fail = modem_fail;
	}
	public String getPower_nw_fail() {
		return power_nw_fail;
	}
	public void setPower_nw_fail(String power_nw_fail) {
		this.power_nw_fail = power_nw_fail;
	}
	public String getNot_comm_percent() {
		return not_comm_percent;
	}
	public void setNot_comm_percent(String not_comm_percent) {
		this.not_comm_percent = not_comm_percent;
	}
	public String getComm_status() {
		return comm_status;
	}
	public void setComm_status(String comm_status) {
		this.comm_status = comm_status;
	}
	public String getComm_signal_strength() {
		return comm_signal_strength;
	}
	public void setComm_signal_strength(String comm_signal_strength) {
		this.comm_signal_strength = comm_signal_strength;
	}
	public String getNot_comm_last_date() {
		return not_comm_last_date;
	}
	public void setNot_comm_last_date(String not_comm_last_date) {
		this.not_comm_last_date = not_comm_last_date;
	}
	
	
	
}
