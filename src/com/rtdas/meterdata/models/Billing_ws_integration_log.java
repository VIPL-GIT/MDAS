package com.rtdas.meterdata.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="billing_ws_integration_log")
public class Billing_ws_integration_log {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int	id;
	private String	binder_no;
	private String	token_no;
	private String	bill_cycle;
	private java.sql.Blob	req_data;
	private java.sql.Blob	master_data;
	private String	ack_send_status;
	private String	ack_response_status;
	private String	meter_serial_no;
	private String	kno;
	private java.sql.Blob	post_readings_data;
	private String	post_readings_response;
	private String	error_msg;
	private String	flag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBinder_no() {
		return binder_no;
	}
	public void setBinder_no(String binder_no) {
		this.binder_no = binder_no;
	}
	public String getToken_no() {
		return token_no;
	}
	public void setToken_no(String token_no) {
		this.token_no = token_no;
	}
	public String getBill_cycle() {
		return bill_cycle;
	}
	public void setBill_cycle(String bill_cycle) {
		this.bill_cycle = bill_cycle;
	}
	public java.sql.Blob getReq_data() {
		return req_data;
	}
	public void setReq_data(java.sql.Blob req_data) {
		this.req_data = req_data;
	}
	public java.sql.Blob getMaster_data() {
		return master_data;
	}
	public void setMaster_data(java.sql.Blob master_data) {
		this.master_data = master_data;
	}
	public String getAck_send_status() {
		return ack_send_status;
	}
	public void setAck_send_status(String ack_send_status) {
		this.ack_send_status = ack_send_status;
	}
	public String getAck_response_status() {
		return ack_response_status;
	}
	public void setAck_response_status(String ack_response_status) {
		this.ack_response_status = ack_response_status;
	}
	public String getMeter_serial_no() {
		return meter_serial_no;
	}
	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}
	public String getKno() {
		return kno;
	}
	public void setKno(String kno) {
		this.kno = kno;
	}
	public java.sql.Blob getPost_readings_data() {
		return post_readings_data;
	}
	public void setPost_readings_data(java.sql.Blob post_readings_data) {
		this.post_readings_data = post_readings_data;
	}
	public String getPost_readings_response() {
		return post_readings_response;
	}
	public void setPost_readings_response(String post_readings_response) {
		this.post_readings_response = post_readings_response;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	

}
