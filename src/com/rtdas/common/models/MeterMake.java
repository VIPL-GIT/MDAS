package com.rtdas.common.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="metermake")
public class MeterMake {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int	METERMAKEID;
	private String	METERMAKENAME;
	private String	METER_SERIES;
	private String	METER_VERSION;
	private String	NO_OF_PARAMS;
	private String	PARAM_NAMES;
	private String	B4_TOD_PARAMS;
	private String	D4_PARAMS;
	private String	D3_PARAMS;
	private String	D6_PARAMS;
	private String	KAB0;
	public int getMETERMAKEID() {
		return METERMAKEID;
	}
	public void setMETERMAKEID(int mETERMAKEID) {
		METERMAKEID = mETERMAKEID;
	}
	public String getMETERMAKENAME() {
		return METERMAKENAME;
	}
	public void setMETERMAKENAME(String mETERMAKENAME) {
		METERMAKENAME = mETERMAKENAME;
	}
	public String getMETER_SERIES() {
		return METER_SERIES;
	}
	public void setMETER_SERIES(String mETER_SERIES) {
		METER_SERIES = mETER_SERIES;
	}
	public String getMETER_VERSION() {
		return METER_VERSION;
	}
	public void setMETER_VERSION(String mETER_VERSION) {
		METER_VERSION = mETER_VERSION;
	}
	public String getNO_OF_PARAMS() {
		return NO_OF_PARAMS;
	}
	public void setNO_OF_PARAMS(String nO_OF_PARAMS) {
		NO_OF_PARAMS = nO_OF_PARAMS;
	}
	public String getPARAM_NAMES() {
		return PARAM_NAMES;
	}
	public void setPARAM_NAMES(String pARAM_NAMES) {
		PARAM_NAMES = pARAM_NAMES;
	}
	public String getB4_TOD_PARAMS() {
		return B4_TOD_PARAMS;
	}
	public void setB4_TOD_PARAMS(String b4_TOD_PARAMS) {
		B4_TOD_PARAMS = b4_TOD_PARAMS;
	}
	public String getD4_PARAMS() {
		return D4_PARAMS;
	}
	public void setD4_PARAMS(String d4_PARAMS) {
		D4_PARAMS = d4_PARAMS;
	}
	public String getD3_PARAMS() {
		return D3_PARAMS;
	}
	public void setD3_PARAMS(String d3_PARAMS) {
		D3_PARAMS = d3_PARAMS;
	}
	public String getD6_PARAMS() {
		return D6_PARAMS;
	}
	public void setD6_PARAMS(String d6_PARAMS) {
		D6_PARAMS = d6_PARAMS;
	}
	public String getKAB0() {
		return KAB0;
	}
	public void setKAB0(String kAB0) {
		KAB0 = kAB0;
	}
	
	
	

}
