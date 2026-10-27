package com.rtdas.meterdata.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="total_masterdata")
public class TotalMasterdataBean {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int slno;
	private String region;
	private int region_id;
	private int region_pid;
	private String circle;
	private int circle_id;
	private int circle_pid;
	private String division;
	private int division_id;
	private int division_pid;
	private String sd;
	private int sd_id;
	private int sd_pid;
	private String ss;
	private int ss_id;
	private int ss_pid;
	private String ptr;
	private int ptr_id;
	private int ptr_pid;
	private String meter_sl_no;
	private String meter_make;
	private String meter_type;
	private String meter_location;
	private Double mf;
	private int location_id;
	private String modem_sl_no;
	private String modem_mobile_no;
	private String location_name;
	private String location_code;
	private String location_category;
	private String location_group;
	private String sanction_load;
	private String contract_demand;
	private String supply_voltage;
	private String category;
	
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPtr() {
		return ptr;
	}
	public void setPtr(String ptr) {
		this.ptr = ptr;
	}
	public int getPtr_id() {
		return ptr_id;
	}
	public void setPtr_id(int ptr_id) {
		this.ptr_id = ptr_id;
	}
	public int getPtr_pid() {
		return ptr_pid;
	}
	public void setPtr_pid(int ptr_pid) {
		this.ptr_pid = ptr_pid;
	}
	public int getSlno() {
		return slno;
	}
	public void setSlno(int slno) {
		this.slno = slno;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	public int getRegion_pid() {
		return region_pid;
	}
	public void setRegion_pid(int region_pid) {
		this.region_pid = region_pid;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public int getCircle_id() {
		return circle_id;
	}
	public void setCircle_id(int circle_id) {
		this.circle_id = circle_id;
	}
	public int getCircle_pid() {
		return circle_pid;
	}
	public void setCircle_pid(int circle_pid) {
		this.circle_pid = circle_pid;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public int getDivision_id() {
		return division_id;
	}
	public void setDivision_id(int division_id) {
		this.division_id = division_id;
	}
	public int getDivision_pid() {
		return division_pid;
	}
	public void setDivision_pid(int division_pid) {
		this.division_pid = division_pid;
	}
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	public int getSd_id() {
		return sd_id;
	}
	public void setSd_id(int sd_id) {
		this.sd_id = sd_id;
	}
	public int getSd_pid() {
		return sd_pid;
	}
	public void setSd_pid(int sd_pid) {
		this.sd_pid = sd_pid;
	}
	public String getSs() {
		return ss;
	}
	public void setSs(String ss) {
		this.ss = ss;
	}
	public int getSs_id() {
		return ss_id;
	}
	public void setSs_id(int ss_id) {
		this.ss_id = ss_id;
	}
	public int getSs_pid() {
		return ss_pid;
	}
	public void setSs_pid(int ss_pid) {
		this.ss_pid = ss_pid;
	}
	public String getMeter_sl_no() {
		return meter_sl_no;
	}
	public void setMeter_sl_no(String meter_sl_no) {
		this.meter_sl_no = meter_sl_no;
	}
	public String getMeter_make() {
		return meter_make;
	}
	public void setMeter_make(String meter_make) {
		this.meter_make = meter_make;
	}
	public String getMeter_type() {
		return meter_type;
	}
	public void setMeter_type(String meter_type) {
		this.meter_type = meter_type;
	}
	public String getMeter_location() {
		return meter_location;
	}
	public void setMeter_location(String meter_location) {
		this.meter_location = meter_location;
	}
	public Double getMf() {
		return mf;
	}
	public void setMf(Double mf) {
		this.mf = mf;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public String getModem_sl_no() {
		return modem_sl_no;
	}
	public void setModem_sl_no(String modem_sl_no) {
		this.modem_sl_no = modem_sl_no;
	}
	public String getModem_mobile_no() {
		return modem_mobile_no;
	}
	public void setModem_mobile_no(String modem_mobile_no) {
		this.modem_mobile_no = modem_mobile_no;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getLocation_code() {
		return location_code;
	}
	public void setLocation_code(String location_code) {
		this.location_code = location_code;
	}
	public String getLocation_category() {
		return location_category;
	}
	public void setLocation_category(String location_category) {
		this.location_category = location_category;
	}
	public String getLocation_group() {
		return location_group;
	}
	public void setLocation_group(String location_group) {
		this.location_group = location_group;
	}
	public String getSanction_load() {
		return sanction_load;
	}
	public void setSanction_load(String sanction_load) {
		this.sanction_load = sanction_load;
	}
	public String getContract_demand() {
		return contract_demand;
	}
	public void setContract_demand(String contract_demand) {
		this.contract_demand = contract_demand;
	}
	public String getSupply_voltage() {
		return supply_voltage;
	}
	public void setSupply_voltage(String supply_voltage) {
		this.supply_voltage = supply_voltage;
	}
	
	
	

}
