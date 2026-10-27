package com.rtdas.hes.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hes_device_details")
public class SmartDevices {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int	device_id;
	private String	meter_serial_no;
	private String	project;
	private String	manufacturer;
	private String	flag_id;
	private String	system_title;
	private String	meter_version;
	private String	year_of_manufacture;
	private String	meter_category;
	private String	meter_type;
	private String	ctr;
	private String	ptr;
	private String	netmeter;
	private String	ipaddress_type;
	private String	destination_ipaddress;
	private String	listening_port;
	private String	channel_type;
	private String	addressing_scheme;
	private String	baudrate;
	private String	parity;
	private String	communication_type;
	private String	gateway_serial_no;
	private String	mac_address;
	private String	created_by;
	private String	updated_by;
	private java.sql.Timestamp log_timestamp;
	
	
	
	public String getMeter_serial_no() {
		return meter_serial_no;
	}
	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}
	public int getDevice_id() {
		return device_id;
	}
	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getFlag_id() {
		return flag_id;
	}
	public void setFlag_id(String flag_id) {
		this.flag_id = flag_id;
	}
	public String getSystem_title() {
		return system_title;
	}
	public void setSystem_title(String system_title) {
		this.system_title = system_title;
	}
	public String getMeter_version() {
		return meter_version;
	}
	public void setMeter_version(String meter_version) {
		this.meter_version = meter_version;
	}
	public String getYear_of_manufacture() {
		return year_of_manufacture;
	}
	public void setYear_of_manufacture(String year_of_manufacture) {
		this.year_of_manufacture = year_of_manufacture;
	}
	public String getMeter_category() {
		return meter_category;
	}
	public void setMeter_category(String meter_category) {
		this.meter_category = meter_category;
	}
	public String getMeter_type() {
		return meter_type;
	}
	public void setMeter_type(String meter_type) {
		this.meter_type = meter_type;
	}
	public String getCtr() {
		return ctr;
	}
	public void setCtr(String ctr) {
		this.ctr = ctr;
	}
	public String getPtr() {
		return ptr;
	}
	public void setPtr(String ptr) {
		this.ptr = ptr;
	}
	public String getNetmeter() {
		return netmeter;
	}
	public void setNetmeter(String netmeter) {
		this.netmeter = netmeter;
	}
	public String getIpaddress_type() {
		return ipaddress_type;
	}
	public void setIpaddress_type(String ipaddress_type) {
		this.ipaddress_type = ipaddress_type;
	}
	public String getDestination_ipaddress() {
		return destination_ipaddress;
	}
	public void setDestination_ipaddress(String destination_ipaddress) {
		this.destination_ipaddress = destination_ipaddress;
	}
	public String getListening_port() {
		return listening_port;
	}
	public void setListening_port(String listening_port) {
		this.listening_port = listening_port;
	}
	public String getChannel_type() {
		return channel_type;
	}
	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}
	public String getAddressing_scheme() {
		return addressing_scheme;
	}
	public void setAddressing_scheme(String addressing_scheme) {
		this.addressing_scheme = addressing_scheme;
	}
	public String getBaudrate() {
		return baudrate;
	}
	public void setBaudrate(String baudrate) {
		this.baudrate = baudrate;
	}
	public String getParity() {
		return parity;
	}
	public void setParity(String parity) {
		this.parity = parity;
	}
	public String getCommunication_type() {
		return communication_type;
	}
	public void setCommunication_type(String communication_type) {
		this.communication_type = communication_type;
	}
	public String getGateway_serial_no() {
		return gateway_serial_no;
	}
	public void setGateway_serial_no(String gateway_serial_no) {
		this.gateway_serial_no = gateway_serial_no;
	}
	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
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
