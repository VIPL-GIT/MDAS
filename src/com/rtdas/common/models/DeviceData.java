package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.core.annotation.Order;

@Entity
@Table(name="device_data")
public class DeviceData implements Serializable {
	
private static final long serialVersionUID = 5462223600l;

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="slno")
    private Integer  slno;
@Column(name="device_id")
	private String  device_id;
@Column(name="reading_datetime")
	private java.sql.Timestamp  reading_datetime;
@Order(value=1)
@Column(name="param_1")
	private String  param_1;
@Order(value=2)
@Column(name="param_2")
	private String  param_2;
@Order(value=3)
    @Column(name="param_3")
	private String  param_3;
@Order(value=4)
    @Column(name="param_4")
	private String  param_4;
@Order(value=5)
    @Column(name="param_5")
	private String  param_5;
@Order(value=6)
    @Column(name="param_6")
	private String  param_6;
@Order(value=7)
    @Column(name="param_7")
	private String  param_7;
@Order(value=8)
    @Column(name="param_8")
	private String  param_8;
@Order(value=9)
    @Column(name="param_9")
	private String  param_9;
@Order(value=10)
    @Column(name="param_10")
	private String  param_10;
@Order(value=11)
    @Column(name="param_11")
	private String  param_11;
@Order(value=12)
    @Column(name="param_12")
	private String  param_12;
@Order(value=13)
    @Column(name="param_13")
	private String  param_13;
@Order(value=14)
    @Column(name="param_14")
	private String  param_14;
@Order(value=15)
    @Column(name="param_15")
	private String  param_15;
@Order(value=16)
    @Column(name="param_16")
	private String  param_16;
@Order(value=17)
    @Column(name="param_17")
	private String  param_17;
@Order(value=18)
    @Column(name="param_18")
	private String  param_18;
@Order(value=19)
    @Column(name="param_19")
	private String  param_19;
@Order(value=20)
    @Column(name="param_20")
	private String  param_20;
@Order(value=21)
    @Column(name="param_21")
	private String  param_21;
@Order(value=22)
    @Column(name="param_22")
	private String  param_22;
@Order(value=23)
    @Column(name="param_23")
	private String  param_23;
@Order(value=24)
    @Column(name="param_24")    
	private String  param_24;
@Order(value=25)
    @Column(name="param_25")
	private String  param_25;
@Order(value=26)
    @Column(name="param_26")
	private String  param_26;
@Order(value=27)
    @Column(name="param_27")
	private String  param_27;
@Order(value=28)
    @Column(name="param_28")
	private String  param_28;
@Order(value=29)
    @Column(name="param_29")
	private String  param_29;
@Order(value=30)
    @Column(name="param_30")
	private String  param_30;
@Order(value=31)
    @Column(name="param_31")
	private String  param_31;
@Order(value=32)
    @Column(name="param_32")
	private String  param_32;
@Order(value=33)
    @Column(name="param_33")
	private String  param_33;
@Order(value=34)
    @Column(name="param_34")
	private String  param_34;
@Order(value=35)
    @Column(name="param_35")
	private String  param_35;
@Order(value=36)
    @Column(name="param_36")
	private String  param_36;
    @Column(name="param_37")
	private String  param_37;
    @Column(name="param_38")
	private String  param_38;
    @Column(name="param_39")
	private String  param_39;
    @Column(name="param_40")
	private String  param_40;
    @Column(name="param_41")
	private String  param_41;
    @Column(name="param_42")
	private String  param_42;
    @Column(name="param_43")
	private String  param_43;
    @Column(name="param_44")
	private String  param_44;
    @Column(name="param_45")
	private String  param_45;
    @Column(name="param_46")
	private String  param_46;
    @Column(name="param_47")
	private String  param_47;
    @Column(name="param_48")
	private String  param_48;
    @Column(name="param_49")
	private String  param_49;
    @Column(name="param_50")
	private String  param_50;
    @Column(name="client_id")
	private String  client_id;
    
    @Column(name="log_timestamp")
	private java.sql.Timestamp  log_timestamp;
	public Integer getSlno() {
		return slno;
	}
	public void setSlno(Integer slno) {
		this.slno = slno;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public java.sql.Timestamp getReading_datetime() {
		return reading_datetime;
	}
	public void setReading_datetime(java.sql.Timestamp reading_datetime) {
		this.reading_datetime = reading_datetime;
	}
	public String getParam_1() {
		return param_1;
	}
	public void setParam_1(String param_1) {
		this.param_1 = param_1;
	}
	public String getParam_2() {
		return param_2;
	}
	public void setParam_2(String param_2) {
		this.param_2 = param_2;
	}
	public String getParam_3() {
		return param_3;
	}
	public void setParam_3(String param_3) {
		this.param_3 = param_3;
	}
	public String getParam_4() {
		return param_4;
	}
	public void setParam_4(String param_4) {
		this.param_4 = param_4;
	}
	public String getParam_5() {
		return param_5;
	}
	public void setParam_5(String param_5) {
		this.param_5 = param_5;
	}
	public String getParam_6() {
		return param_6;
	}
	public void setParam_6(String param_6) {
		this.param_6 = param_6;
	}
	public String getParam_7() {
		return param_7;
	}
	public void setParam_7(String param_7) {
		this.param_7 = param_7;
	}
	public String getParam_8() {
		return param_8;
	}
	public void setParam_8(String param_8) {
		this.param_8 = param_8;
	}
	public String getParam_9() {
		return param_9;
	}
	public void setParam_9(String param_9) {
		this.param_9 = param_9;
	}
	public String getParam_10() {
		return param_10;
	}
	public void setParam_10(String param_10) {
		this.param_10 = param_10;
	}
	public String getParam_11() {
		return param_11;
	}
	public void setParam_11(String param_11) {
		this.param_11 = param_11;
	}
	public String getParam_12() {
		return param_12;
	}
	public void setParam_12(String param_12) {
		this.param_12 = param_12;
	}
	public String getParam_13() {
		return param_13;
	}
	public void setParam_13(String param_13) {
		this.param_13 = param_13;
	}
	public String getParam_14() {
		return param_14;
	}
	public void setParam_14(String param_14) {
		this.param_14 = param_14;
	}
	public String getParam_15() {
		return param_15;
	}
	public void setParam_15(String param_15) {
		this.param_15 = param_15;
	}
	public String getParam_16() {
		return param_16;
	}
	public void setParam_16(String param_16) {
		this.param_16 = param_16;
	}
	public String getParam_17() {
		return param_17;
	}
	public void setParam_17(String param_17) {
		this.param_17 = param_17;
	}
	public String getParam_18() {
		return param_18;
	}
	public void setParam_18(String param_18) {
		this.param_18 = param_18;
	}
	public String getParam_19() {
		return param_19;
	}
	public void setParam_19(String param_19) {
		this.param_19 = param_19;
	}
	public String getParam_20() {
		return param_20;
	}
	public void setParam_20(String param_20) {
		this.param_20 = param_20;
	}
	public String getParam_21() {
		return param_21;
	}
	public void setParam_21(String param_21) {
		this.param_21 = param_21;
	}
	public String getParam_22() {
		return param_22;
	}
	public void setParam_22(String param_22) {
		this.param_22 = param_22;
	}
	public String getParam_23() {
		return param_23;
	}
	public void setParam_23(String param_23) {
		this.param_23 = param_23;
	}
	public String getParam_24() {
		return param_24;
	}
	public void setParam_24(String param_24) {
		this.param_24 = param_24;
	}
	public String getParam_25() {
		return param_25;
	}
	public void setParam_25(String param_25) {
		this.param_25 = param_25;
	}
	public String getParam_26() {
		return param_26;
	}
	public void setParam_26(String param_26) {
		this.param_26 = param_26;
	}
	public String getParam_27() {
		return param_27;
	}
	public void setParam_27(String param_27) {
		this.param_27 = param_27;
	}
	public String getParam_28() {
		return param_28;
	}
	public void setParam_28(String param_28) {
		this.param_28 = param_28;
	}
	public String getParam_29() {
		return param_29;
	}
	public void setParam_29(String param_29) {
		this.param_29 = param_29;
	}
	public String getParam_30() {
		return param_30;
	}
	public void setParam_30(String param_30) {
		this.param_30 = param_30;
	}
	public String getParam_31() {
		return param_31;
	}
	public void setParam_31(String param_31) {
		this.param_31 = param_31;
	}
	public String getParam_32() {
		return param_32;
	}
	public void setParam_32(String param_32) {
		this.param_32 = param_32;
	}
	public String getParam_33() {
		return param_33;
	}
	public void setParam_33(String param_33) {
		this.param_33 = param_33;
	}
	public String getParam_34() {
		return param_34;
	}
	public void setParam_34(String param_34) {
		this.param_34 = param_34;
	}
	public String getParam_35() {
		return param_35;
	}
	public void setParam_35(String param_35) {
		this.param_35 = param_35;
	}
	public String getParam_36() {
		return param_36;
	}
	public void setParam_36(String param_36) {
		this.param_36 = param_36;
	}
	public String getParam_37() {
		return param_37;
	}
	public void setParam_37(String param_37) {
		this.param_37 = param_37;
	}
	public String getParam_38() {
		return param_38;
	}
	public void setParam_38(String param_38) {
		this.param_38 = param_38;
	}
	public String getParam_39() {
		return param_39;
	}
	public void setParam_39(String param_39) {
		this.param_39 = param_39;
	}
	public String getParam_40() {
		return param_40;
	}
	public void setParam_40(String param_40) {
		this.param_40 = param_40;
	}
	public String getParam_41() {
		return param_41;
	}
	public void setParam_41(String param_41) {
		this.param_41 = param_41;
	}
	public String getParam_42() {
		return param_42;
	}
	public void setParam_42(String param_42) {
		this.param_42 = param_42;
	}
	public String getParam_43() {
		return param_43;
	}
	public void setParam_43(String param_43) {
		this.param_43 = param_43;
	}
	public String getParam_44() {
		return param_44;
	}
	public void setParam_44(String param_44) {
		this.param_44 = param_44;
	}
	public String getParam_45() {
		return param_45;
	}
	public void setParam_45(String param_45) {
		this.param_45 = param_45;
	}
	public String getParam_46() {
		return param_46;
	}
	public void setParam_46(String param_46) {
		this.param_46 = param_46;
	}
	public String getParam_47() {
		return param_47;
	}
	public void setParam_47(String param_47) {
		this.param_47 = param_47;
	}
	public String getParam_48() {
		return param_48;
	}
	public void setParam_48(String param_48) {
		this.param_48 = param_48;
	}
	public String getParam_49() {
		return param_49;
	}
	public void setParam_49(String param_49) {
		this.param_49 = param_49;
	}
	public String getParam_50() {
		return param_50;
	}
	public void setParam_50(String param_50) {
		this.param_50 = param_50;
	}
	public java.sql.Timestamp getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(java.sql.Timestamp log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

    
    
	
}
