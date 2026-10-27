package com.rtdas.hes.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hes_rf_gateway")
public class RFGateway {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int	gateway_id;
	private String	gateway_serial_number;
	private String	endnode_mac_address;
	private String	gateway_manufacturer;
	private String	created_by;
	private String	updated_by;
	private java.sql.Timestamp	log_timestamp;
	
	
	public int getGateway_id() {
		return gateway_id;
	}
	public void setGateway_id(int gateway_id) {
		this.gateway_id = gateway_id;
	}
	public String getGateway_serial_number() {
		return gateway_serial_number;
	}
	public void setGateway_serial_number(String gateway_serial_number) {
		this.gateway_serial_number = gateway_serial_number;
	}
	public String getEndnode_mac_address() {
		return endnode_mac_address;
	}
	public void setEndnode_mac_address(String endnode_mac_address) {
		this.endnode_mac_address = endnode_mac_address;
	}
	public String getGateway_manufacturer() {
		return gateway_manufacturer;
	}
	public void setGateway_manufacturer(String gateway_manufacturer) {
		this.gateway_manufacturer = gateway_manufacturer;
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
	public java.sql.Timestamp getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(java.sql.Timestamp log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	
	
}
