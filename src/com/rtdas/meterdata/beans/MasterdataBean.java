package com.rtdas.meterdata.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="masterdata")
public class MasterdataBean {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	

}
