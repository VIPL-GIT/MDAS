package com.rtdas.mdm.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="d2_curr")
public class D2_curr implements Serializable{
	private static final long serialVersionUID = 5462223600l;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cdfid")
	public int cdfid;
	@Column(name="G1")
	public String G1;
	@Column(name="G2")
	public String G2;
	@Column(name="G3")
	public String G3;
	@Column(name="RPH_VOLTAGE")
	public String RPH_VOLTAGE;
	@Column(name="YPH_VOLTAGE")
	public String YPH_VOLTAGE;
	@Column(name="BPH_VOLTAGE")
	public String BPH_VOLTAGE;
	@Column(name="RPH_LINE_CURRENT")
	public String RPH_LINE_CURRENT;
	@Column(name="YPH_LINE_CURRENT")
	public String YPH_LINE_CURRENT;
	@Column(name="BPH_LINE_CURRENT")
	public String BPH_LINE_CURRENT;
	@Column(name="RPH_ACTIVE_CURRENT")
	public String RPH_ACTIVE_CURRENT;
	@Column(name="YPH_ACTIVE_CURRENT")
	public String YPH_ACTIVE_CURRENT;
	@Column(name="BPH_ACTIVE_CURRENT")
	public String BPH_ACTIVE_CURRENT;
	@Column(name="RPH_REACT_CURRENT")
	public String RPH_REACT_CURRENT;
	@Column(name="YPH_REACT_CURRENT")
	public String YPH_REACT_CURRENT;
	@Column(name="BPH_REACT_CURRENT")
	public String BPH_REACT_CURRENT;
	@Column(name="FUND_ACT_POWER")
	public String FUND_ACT_POWER;
	@Column(name="REACT_POWER")
	public String REACT_POWER;
	@Column(name="APPARENT_POWER")
	public String APPARENT_POWER;
	@Column(name="RPH_TOTAL_ACT_POWER")
	public String RPH_TOTAL_ACT_POWER;
	@Column(name="RPH_REACT_POWER")
	public String RPH_REACT_POWER;
	@Column(name="YPH_TOTAL_ACT_POWER")
	public String YPH_TOTAL_ACT_POWER;
	@Column(name="YPH_REACT_POWER")
	public String YPH_REACT_POWER;
	@Column(name="BPH_TOTAL_ACT_POWER")
	public String BPH_TOTAL_ACT_POWER;
	@Column(name="BPH_REACT_POWER")
	public String BPH_REACT_POWER;
	@Column(name="D2_SYSTEM_TOTAL_ACT_POWER")
	public String D2_SYSTEM_TOTAL_ACT_POWER;
	@Column(name="D2_SYSTEM_TOTAL_REACT_POWER")
	public String D2_SYSTEM_TOTAL_REACT_POWER;
	@Column(name="D2_SYSTEM_APPARENT_POWER")
	public String D2_SYSTEM_APPARENT_POWER;
	@Column(name="RPH_POWER_FACTOR")
	public String RPH_POWER_FACTOR;
	@Column(name="YPH_POWER_FACTOR")
	public String YPH_POWER_FACTOR;
	@Column(name="BPH_POWER_FACTOR")
	public String BPH_POWER_FACTOR;
	@Column(name="AVG_POWER_FACTOR")
	public String AVG_POWER_FACTOR;
	@Column(name="R_Y_VOLTAGE_ANGLE")
	public String R_Y_VOLTAGE_ANGLE;
	@Column(name="Y_B_VOLTAGE_ANGLE")
	public String Y_B_VOLTAGE_ANGLE;
	@Column(name="B_R_VOLTAGE_ANGLE")
	public String B_R_VOLTAGE_ANGLE;
	@Column(name="VOLTAGE_PHASE_SEQUENCE")
	public String VOLTAGE_PHASE_SEQUENCE;
	@Column(name="FREQUENCY")
	public String FREQUENCY;
	@Column(name="METER_SERIAL_NO")
	public String METER_SERIAL_NO;
	@Column(name="METER_TIME_STAMP")
	public java.sql.Timestamp METER_TIME_STAMP;
	public int getCdfid() {
		return cdfid;
	}
	public void setCdfid(int cdfid) {
		this.cdfid = cdfid;
	}
	public String getG1() {
		return G1;
	}
	public void setG1(String g1) {
		G1 = g1;
	}
	public String getG2() {
		return G2;
	}
	public void setG2(String g2) {
		G2 = g2;
	}
	public String getG3() {
		return G3;
	}
	public void setG3(String g3) {
		G3 = g3;
	}
	public String getRPH_VOLTAGE() {
		return RPH_VOLTAGE;
	}
	public void setRPH_VOLTAGE(String rPH_VOLTAGE) {
		RPH_VOLTAGE = rPH_VOLTAGE;
	}
	public String getYPH_VOLTAGE() {
		return YPH_VOLTAGE;
	}
	public void setYPH_VOLTAGE(String yPH_VOLTAGE) {
		YPH_VOLTAGE = yPH_VOLTAGE;
	}
	public String getBPH_VOLTAGE() {
		return BPH_VOLTAGE;
	}
	public void setBPH_VOLTAGE(String bPH_VOLTAGE) {
		BPH_VOLTAGE = bPH_VOLTAGE;
	}
	public String getRPH_LINE_CURRENT() {
		return RPH_LINE_CURRENT;
	}
	public void setRPH_LINE_CURRENT(String rPH_LINE_CURRENT) {
		RPH_LINE_CURRENT = rPH_LINE_CURRENT;
	}
	public String getYPH_LINE_CURRENT() {
		return YPH_LINE_CURRENT;
	}
	public void setYPH_LINE_CURRENT(String yPH_LINE_CURRENT) {
		YPH_LINE_CURRENT = yPH_LINE_CURRENT;
	}
	public String getBPH_LINE_CURRENT() {
		return BPH_LINE_CURRENT;
	}
	public void setBPH_LINE_CURRENT(String bPH_LINE_CURRENT) {
		BPH_LINE_CURRENT = bPH_LINE_CURRENT;
	}
	public String getRPH_ACTIVE_CURRENT() {
		return RPH_ACTIVE_CURRENT;
	}
	public void setRPH_ACTIVE_CURRENT(String rPH_ACTIVE_CURRENT) {
		RPH_ACTIVE_CURRENT = rPH_ACTIVE_CURRENT;
	}
	public String getYPH_ACTIVE_CURRENT() {
		return YPH_ACTIVE_CURRENT;
	}
	public void setYPH_ACTIVE_CURRENT(String yPH_ACTIVE_CURRENT) {
		YPH_ACTIVE_CURRENT = yPH_ACTIVE_CURRENT;
	}
	public String getBPH_ACTIVE_CURRENT() {
		return BPH_ACTIVE_CURRENT;
	}
	public void setBPH_ACTIVE_CURRENT(String bPH_ACTIVE_CURRENT) {
		BPH_ACTIVE_CURRENT = bPH_ACTIVE_CURRENT;
	}
	public String getRPH_REACT_CURRENT() {
		return RPH_REACT_CURRENT;
	}
	public void setRPH_REACT_CURRENT(String rPH_REACT_CURRENT) {
		RPH_REACT_CURRENT = rPH_REACT_CURRENT;
	}
	public String getYPH_REACT_CURRENT() {
		return YPH_REACT_CURRENT;
	}
	public void setYPH_REACT_CURRENT(String yPH_REACT_CURRENT) {
		YPH_REACT_CURRENT = yPH_REACT_CURRENT;
	}
	public String getBPH_REACT_CURRENT() {
		return BPH_REACT_CURRENT;
	}
	public void setBPH_REACT_CURRENT(String bPH_REACT_CURRENT) {
		BPH_REACT_CURRENT = bPH_REACT_CURRENT;
	}
	public String getFUND_ACT_POWER() {
		return FUND_ACT_POWER;
	}
	public void setFUND_ACT_POWER(String fUND_ACT_POWER) {
		FUND_ACT_POWER = fUND_ACT_POWER;
	}
	public String getREACT_POWER() {
		return REACT_POWER;
	}
	public void setREACT_POWER(String rEACT_POWER) {
		REACT_POWER = rEACT_POWER;
	}
	public String getAPPARENT_POWER() {
		return APPARENT_POWER;
	}
	public void setAPPARENT_POWER(String aPPARENT_POWER) {
		APPARENT_POWER = aPPARENT_POWER;
	}
	public String getRPH_TOTAL_ACT_POWER() {
		return RPH_TOTAL_ACT_POWER;
	}
	public void setRPH_TOTAL_ACT_POWER(String rPH_TOTAL_ACT_POWER) {
		RPH_TOTAL_ACT_POWER = rPH_TOTAL_ACT_POWER;
	}
	public String getRPH_REACT_POWER() {
		return RPH_REACT_POWER;
	}
	public void setRPH_REACT_POWER(String rPH_REACT_POWER) {
		RPH_REACT_POWER = rPH_REACT_POWER;
	}
	public String getYPH_TOTAL_ACT_POWER() {
		return YPH_TOTAL_ACT_POWER;
	}
	public void setYPH_TOTAL_ACT_POWER(String yPH_TOTAL_ACT_POWER) {
		YPH_TOTAL_ACT_POWER = yPH_TOTAL_ACT_POWER;
	}
	public String getYPH_REACT_POWER() {
		return YPH_REACT_POWER;
	}
	public void setYPH_REACT_POWER(String yPH_REACT_POWER) {
		YPH_REACT_POWER = yPH_REACT_POWER;
	}
	public String getBPH_TOTAL_ACT_POWER() {
		return BPH_TOTAL_ACT_POWER;
	}
	public void setBPH_TOTAL_ACT_POWER(String bPH_TOTAL_ACT_POWER) {
		BPH_TOTAL_ACT_POWER = bPH_TOTAL_ACT_POWER;
	}
	public String getBPH_REACT_POWER() {
		return BPH_REACT_POWER;
	}
	public void setBPH_REACT_POWER(String bPH_REACT_POWER) {
		BPH_REACT_POWER = bPH_REACT_POWER;
	}
	public String getD2_SYSTEM_TOTAL_ACT_POWER() {
		return D2_SYSTEM_TOTAL_ACT_POWER;
	}
	public void setD2_SYSTEM_TOTAL_ACT_POWER(String d2_SYSTEM_TOTAL_ACT_POWER) {
		D2_SYSTEM_TOTAL_ACT_POWER = d2_SYSTEM_TOTAL_ACT_POWER;
	}
	public String getD2_SYSTEM_TOTAL_REACT_POWER() {
		return D2_SYSTEM_TOTAL_REACT_POWER;
	}
	public void setD2_SYSTEM_TOTAL_REACT_POWER(String d2_SYSTEM_TOTAL_REACT_POWER) {
		D2_SYSTEM_TOTAL_REACT_POWER = d2_SYSTEM_TOTAL_REACT_POWER;
	}
	public String getD2_SYSTEM_APPARENT_POWER() {
		return D2_SYSTEM_APPARENT_POWER;
	}
	public void setD2_SYSTEM_APPARENT_POWER(String d2_SYSTEM_APPARENT_POWER) {
		D2_SYSTEM_APPARENT_POWER = d2_SYSTEM_APPARENT_POWER;
	}
	public String getRPH_POWER_FACTOR() {
		return RPH_POWER_FACTOR;
	}
	public void setRPH_POWER_FACTOR(String rPH_POWER_FACTOR) {
		RPH_POWER_FACTOR = rPH_POWER_FACTOR;
	}
	public String getYPH_POWER_FACTOR() {
		return YPH_POWER_FACTOR;
	}
	public void setYPH_POWER_FACTOR(String yPH_POWER_FACTOR) {
		YPH_POWER_FACTOR = yPH_POWER_FACTOR;
	}
	public String getBPH_POWER_FACTOR() {
		return BPH_POWER_FACTOR;
	}
	public void setBPH_POWER_FACTOR(String bPH_POWER_FACTOR) {
		BPH_POWER_FACTOR = bPH_POWER_FACTOR;
	}
	public String getAVG_POWER_FACTOR() {
		return AVG_POWER_FACTOR;
	}
	public void setAVG_POWER_FACTOR(String aVG_POWER_FACTOR) {
		AVG_POWER_FACTOR = aVG_POWER_FACTOR;
	}
	public String getR_Y_VOLTAGE_ANGLE() {
		return R_Y_VOLTAGE_ANGLE;
	}
	public void setR_Y_VOLTAGE_ANGLE(String r_Y_VOLTAGE_ANGLE) {
		R_Y_VOLTAGE_ANGLE = r_Y_VOLTAGE_ANGLE;
	}
	public String getY_B_VOLTAGE_ANGLE() {
		return Y_B_VOLTAGE_ANGLE;
	}
	public void setY_B_VOLTAGE_ANGLE(String y_B_VOLTAGE_ANGLE) {
		Y_B_VOLTAGE_ANGLE = y_B_VOLTAGE_ANGLE;
	}
	public String getB_R_VOLTAGE_ANGLE() {
		return B_R_VOLTAGE_ANGLE;
	}
	public void setB_R_VOLTAGE_ANGLE(String b_R_VOLTAGE_ANGLE) {
		B_R_VOLTAGE_ANGLE = b_R_VOLTAGE_ANGLE;
	}
	public String getVOLTAGE_PHASE_SEQUENCE() {
		return VOLTAGE_PHASE_SEQUENCE;
	}
	public void setVOLTAGE_PHASE_SEQUENCE(String vOLTAGE_PHASE_SEQUENCE) {
		VOLTAGE_PHASE_SEQUENCE = vOLTAGE_PHASE_SEQUENCE;
	}
	public String getFREQUENCY() {
		return FREQUENCY;
	}
	public void setFREQUENCY(String fREQUENCY) {
		FREQUENCY = fREQUENCY;
	}
	public String getMETER_SERIAL_NO() {
		return METER_SERIAL_NO;
	}
	public void setMETER_SERIAL_NO(String mETER_SERIAL_NO) {
		METER_SERIAL_NO = mETER_SERIAL_NO;
	}
	public java.sql.Timestamp getMETER_TIME_STAMP() {
		return METER_TIME_STAMP;
	}
	public void setMETER_TIME_STAMP(java.sql.Timestamp mETER_TIME_STAMP) {
		METER_TIME_STAMP = mETER_TIME_STAMP;
	}
	
	
	
	


}
