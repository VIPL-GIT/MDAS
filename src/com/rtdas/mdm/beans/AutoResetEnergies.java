package com.rtdas.mdm.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class AutoResetEnergies {
	
	public int recordid;
	public String meter_serial_no;
	public String billing_timestamp;
	public String KWH;
	public String KVAH;
	public String KVARH_Lag;
	public String KVARH_Lead;
	public String MDKW;
	public String MDKWOCCDATE;
	public String MDKVA;
	public String MDKVAOCCDATE;
	public String KWH_TOD_1;
	public String KWH_TOD_2;
	public String KWH_TOD_3;
	public String KWH_TOD_4;
	public String KWH_TOD_5;
	public String KWH_TOD_6;
	public String KWH_TOD_7;
	public String KWH_TOD_8;
	public String KVAH_TOD_1;
	public String KVAH_TOD_2;
	public String KVAH_TOD_3;
	public String KVAH_TOD_4;
	public String KVAH_TOD_5;
	public String KVAH_TOD_6;
	public String KVAH_TOD_7;
	public String KVAH_TOD_8;
	public String MDKW_TOD_1;
	public String MDKW_TOD_2;
	public String MDKW_TOD_3;
	public String MDKW_TOD_4;
	public String MDKW_TOD_5;
	public String MDKW_TOD_6;
	public String MDKW_TOD_7;
	public String MDKW_TOD_8;
	public String MDKW_TOD_1_OCCDATE;
	public String MDKW_TOD_2_OCCDATE;
	public String MDKW_TOD_3_OCCDATE;
	public String MDKW_TOD_4_OCCDATE;
	public String MDKW_TOD_5_OCCDATE;
	public String MDKW_TOD_6_OCCDATE;
	public String MDKW_TOD_7_OCCDATE;
	public String MDKW_TOD_8_OCCDATE;
	public String MDKVA_TOD_1;
	public String MDKVA_TOD_2;
	public String MDKVA_TOD_3;
	public String MDKVA_TOD_4;
	public String MDKVA_TOD_5;
	public String MDKVA_TOD_6;
	public String MDKVA_TOD_7;
	public String MDKVA_TOD_8;
	public String MDKVA_TOD_1_OCCDATE;
	public String MDKVA_TOD_2_OCCDATE;
	public String MDKVA_TOD_3_OCCDATE;
	public String MDKVA_TOD_4_OCCDATE;
	public String MDKVA_TOD_5_OCCDATE;
	public String MDKVA_TOD_6_OCCDATE;
	public String MDKVA_TOD_7_OCCDATE;
	public String MDKVA_TOD_8_OCCDATE;
	public String PF;
	public java.sql.Timestamp log_timestamp;
	public String mdmflag;
	
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public String getMeter_serial_no() {
		return meter_serial_no;
	}
	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}
	public String getBilling_timestamp() {
		return billing_timestamp;
	}
	public void setBilling_timestamp(String billing_timestamp) {
		this.billing_timestamp = billing_timestamp;
	}
	public String getKWH() {
		return KWH;
	}
	public void setKWH(String kWH) {
		KWH = kWH;
	}
	public String getKVAH() {
		return KVAH;
	}
	public void setKVAH(String kVAH) {
		KVAH = kVAH;
	}
	public String getKVARH_Lag() {
		return KVARH_Lag;
	}
	public void setKVARH_Lag(String kVARH_Lag) {
		KVARH_Lag = kVARH_Lag;
	}
	public String getKVARH_Lead() {
		return KVARH_Lead;
	}
	public void setKVARH_Lead(String kVARH_Lead) {
		KVARH_Lead = kVARH_Lead;
	}
	public String getMDKW() {
		return MDKW;
	}
	public void setMDKW(String mDKW) {
		MDKW = mDKW;
	}
	public String getMDKWOCCDATE() {
		return MDKWOCCDATE;
	}
	public void setMDKWOCCDATE(String mDKWOCCDATE) {
		MDKWOCCDATE = mDKWOCCDATE;
	}
	public String getMDKVA() {
		return MDKVA;
	}
	public void setMDKVA(String mDKVA) {
		MDKVA = mDKVA;
	}
	public String getMDKVAOCCDATE() {
		return MDKVAOCCDATE;
	}
	public void setMDKVAOCCDATE(String mDKVAOCCDATE) {
		MDKVAOCCDATE = mDKVAOCCDATE;
	}
	public String getKWH_TOD_1() {
		return KWH_TOD_1;
	}
	public void setKWH_TOD_1(String kWH_TOD_1) {
		KWH_TOD_1 = kWH_TOD_1;
	}
	public String getKWH_TOD_2() {
		return KWH_TOD_2;
	}
	public void setKWH_TOD_2(String kWH_TOD_2) {
		KWH_TOD_2 = kWH_TOD_2;
	}
	public String getKWH_TOD_3() {
		return KWH_TOD_3;
	}
	public void setKWH_TOD_3(String kWH_TOD_3) {
		KWH_TOD_3 = kWH_TOD_3;
	}
	public String getKWH_TOD_4() {
		return KWH_TOD_4;
	}
	public void setKWH_TOD_4(String kWH_TOD_4) {
		KWH_TOD_4 = kWH_TOD_4;
	}
	public String getKWH_TOD_5() {
		return KWH_TOD_5;
	}
	public void setKWH_TOD_5(String kWH_TOD_5) {
		KWH_TOD_5 = kWH_TOD_5;
	}
	public String getKWH_TOD_6() {
		return KWH_TOD_6;
	}
	public void setKWH_TOD_6(String kWH_TOD_6) {
		KWH_TOD_6 = kWH_TOD_6;
	}
	public String getKWH_TOD_7() {
		return KWH_TOD_7;
	}
	public void setKWH_TOD_7(String kWH_TOD_7) {
		KWH_TOD_7 = kWH_TOD_7;
	}
	public String getKWH_TOD_8() {
		return KWH_TOD_8;
	}
	public void setKWH_TOD_8(String kWH_TOD_8) {
		KWH_TOD_8 = kWH_TOD_8;
	}
	public String getKVAH_TOD_1() {
		return KVAH_TOD_1;
	}
	public void setKVAH_TOD_1(String kVAH_TOD_1) {
		KVAH_TOD_1 = kVAH_TOD_1;
	}
	public String getKVAH_TOD_2() {
		return KVAH_TOD_2;
	}
	public void setKVAH_TOD_2(String kVAH_TOD_2) {
		KVAH_TOD_2 = kVAH_TOD_2;
	}
	public String getKVAH_TOD_3() {
		return KVAH_TOD_3;
	}
	public void setKVAH_TOD_3(String kVAH_TOD_3) {
		KVAH_TOD_3 = kVAH_TOD_3;
	}
	public String getKVAH_TOD_4() {
		return KVAH_TOD_4;
	}
	public void setKVAH_TOD_4(String kVAH_TOD_4) {
		KVAH_TOD_4 = kVAH_TOD_4;
	}
	public String getKVAH_TOD_5() {
		return KVAH_TOD_5;
	}
	public void setKVAH_TOD_5(String kVAH_TOD_5) {
		KVAH_TOD_5 = kVAH_TOD_5;
	}
	public String getKVAH_TOD_6() {
		return KVAH_TOD_6;
	}
	public void setKVAH_TOD_6(String kVAH_TOD_6) {
		KVAH_TOD_6 = kVAH_TOD_6;
	}
	public String getKVAH_TOD_7() {
		return KVAH_TOD_7;
	}
	public void setKVAH_TOD_7(String kVAH_TOD_7) {
		KVAH_TOD_7 = kVAH_TOD_7;
	}
	public String getKVAH_TOD_8() {
		return KVAH_TOD_8;
	}
	public void setKVAH_TOD_8(String kVAH_TOD_8) {
		KVAH_TOD_8 = kVAH_TOD_8;
	}
	public String getMDKW_TOD_1() {
		return MDKW_TOD_1;
	}
	public void setMDKW_TOD_1(String mDKW_TOD_1) {
		MDKW_TOD_1 = mDKW_TOD_1;
	}
	public String getMDKW_TOD_2() {
		return MDKW_TOD_2;
	}
	public void setMDKW_TOD_2(String mDKW_TOD_2) {
		MDKW_TOD_2 = mDKW_TOD_2;
	}
	public String getMDKW_TOD_3() {
		return MDKW_TOD_3;
	}
	public void setMDKW_TOD_3(String mDKW_TOD_3) {
		MDKW_TOD_3 = mDKW_TOD_3;
	}
	public String getMDKW_TOD_4() {
		return MDKW_TOD_4;
	}
	public void setMDKW_TOD_4(String mDKW_TOD_4) {
		MDKW_TOD_4 = mDKW_TOD_4;
	}
	public String getMDKW_TOD_5() {
		return MDKW_TOD_5;
	}
	public void setMDKW_TOD_5(String mDKW_TOD_5) {
		MDKW_TOD_5 = mDKW_TOD_5;
	}
	public String getMDKW_TOD_6() {
		return MDKW_TOD_6;
	}
	public void setMDKW_TOD_6(String mDKW_TOD_6) {
		MDKW_TOD_6 = mDKW_TOD_6;
	}
	public String getMDKW_TOD_7() {
		return MDKW_TOD_7;
	}
	public void setMDKW_TOD_7(String mDKW_TOD_7) {
		MDKW_TOD_7 = mDKW_TOD_7;
	}
	public String getMDKW_TOD_8() {
		return MDKW_TOD_8;
	}
	public void setMDKW_TOD_8(String mDKW_TOD_8) {
		MDKW_TOD_8 = mDKW_TOD_8;
	}
	public String getMDKW_TOD_1_OCCDATE() {
		return MDKW_TOD_1_OCCDATE;
	}
	public void setMDKW_TOD_1_OCCDATE(String mDKW_TOD_1_OCCDATE) {
		MDKW_TOD_1_OCCDATE = mDKW_TOD_1_OCCDATE;
	}
	public String getMDKW_TOD_2_OCCDATE() {
		return MDKW_TOD_2_OCCDATE;
	}
	public void setMDKW_TOD_2_OCCDATE(String mDKW_TOD_2_OCCDATE) {
		MDKW_TOD_2_OCCDATE = mDKW_TOD_2_OCCDATE;
	}
	public String getMDKW_TOD_3_OCCDATE() {
		return MDKW_TOD_3_OCCDATE;
	}
	public void setMDKW_TOD_3_OCCDATE(String mDKW_TOD_3_OCCDATE) {
		MDKW_TOD_3_OCCDATE = mDKW_TOD_3_OCCDATE;
	}
	public String getMDKW_TOD_4_OCCDATE() {
		return MDKW_TOD_4_OCCDATE;
	}
	public void setMDKW_TOD_4_OCCDATE(String mDKW_TOD_4_OCCDATE) {
		MDKW_TOD_4_OCCDATE = mDKW_TOD_4_OCCDATE;
	}
	public String getMDKW_TOD_5_OCCDATE() {
		return MDKW_TOD_5_OCCDATE;
	}
	public void setMDKW_TOD_5_OCCDATE(String mDKW_TOD_5_OCCDATE) {
		MDKW_TOD_5_OCCDATE = mDKW_TOD_5_OCCDATE;
	}
	public String getMDKW_TOD_6_OCCDATE() {
		return MDKW_TOD_6_OCCDATE;
	}
	public void setMDKW_TOD_6_OCCDATE(String mDKW_TOD_6_OCCDATE) {
		MDKW_TOD_6_OCCDATE = mDKW_TOD_6_OCCDATE;
	}
	public String getMDKW_TOD_7_OCCDATE() {
		return MDKW_TOD_7_OCCDATE;
	}
	public void setMDKW_TOD_7_OCCDATE(String mDKW_TOD_7_OCCDATE) {
		MDKW_TOD_7_OCCDATE = mDKW_TOD_7_OCCDATE;
	}
	public String getMDKW_TOD_8_OCCDATE() {
		return MDKW_TOD_8_OCCDATE;
	}
	public void setMDKW_TOD_8_OCCDATE(String mDKW_TOD_8_OCCDATE) {
		MDKW_TOD_8_OCCDATE = mDKW_TOD_8_OCCDATE;
	}
	public String getMDKVA_TOD_1() {
		return MDKVA_TOD_1;
	}
	public void setMDKVA_TOD_1(String mDKVA_TOD_1) {
		MDKVA_TOD_1 = mDKVA_TOD_1;
	}
	public String getMDKVA_TOD_2() {
		return MDKVA_TOD_2;
	}
	public void setMDKVA_TOD_2(String mDKVA_TOD_2) {
		MDKVA_TOD_2 = mDKVA_TOD_2;
	}
	public String getMDKVA_TOD_3() {
		return MDKVA_TOD_3;
	}
	public void setMDKVA_TOD_3(String mDKVA_TOD_3) {
		MDKVA_TOD_3 = mDKVA_TOD_3;
	}
	public String getMDKVA_TOD_4() {
		return MDKVA_TOD_4;
	}
	public void setMDKVA_TOD_4(String mDKVA_TOD_4) {
		MDKVA_TOD_4 = mDKVA_TOD_4;
	}
	public String getMDKVA_TOD_5() {
		return MDKVA_TOD_5;
	}
	public void setMDKVA_TOD_5(String mDKVA_TOD_5) {
		MDKVA_TOD_5 = mDKVA_TOD_5;
	}
	public String getMDKVA_TOD_6() {
		return MDKVA_TOD_6;
	}
	public void setMDKVA_TOD_6(String mDKVA_TOD_6) {
		MDKVA_TOD_6 = mDKVA_TOD_6;
	}
	public String getMDKVA_TOD_7() {
		return MDKVA_TOD_7;
	}
	public void setMDKVA_TOD_7(String mDKVA_TOD_7) {
		MDKVA_TOD_7 = mDKVA_TOD_7;
	}
	public String getMDKVA_TOD_8() {
		return MDKVA_TOD_8;
	}
	public void setMDKVA_TOD_8(String mDKVA_TOD_8) {
		MDKVA_TOD_8 = mDKVA_TOD_8;
	}
	public String getMDKVA_TOD_1_OCCDATE() {
		return MDKVA_TOD_1_OCCDATE;
	}
	public void setMDKVA_TOD_1_OCCDATE(String mDKVA_TOD_1_OCCDATE) {
		MDKVA_TOD_1_OCCDATE = mDKVA_TOD_1_OCCDATE;
	}
	public String getMDKVA_TOD_2_OCCDATE() {
		return MDKVA_TOD_2_OCCDATE;
	}
	public void setMDKVA_TOD_2_OCCDATE(String mDKVA_TOD_2_OCCDATE) {
		MDKVA_TOD_2_OCCDATE = mDKVA_TOD_2_OCCDATE;
	}
	public String getMDKVA_TOD_3_OCCDATE() {
		return MDKVA_TOD_3_OCCDATE;
	}
	public void setMDKVA_TOD_3_OCCDATE(String mDKVA_TOD_3_OCCDATE) {
		MDKVA_TOD_3_OCCDATE = mDKVA_TOD_3_OCCDATE;
	}
	public String getMDKVA_TOD_4_OCCDATE() {
		return MDKVA_TOD_4_OCCDATE;
	}
	public void setMDKVA_TOD_4_OCCDATE(String mDKVA_TOD_4_OCCDATE) {
		MDKVA_TOD_4_OCCDATE = mDKVA_TOD_4_OCCDATE;
	}
	public String getMDKVA_TOD_5_OCCDATE() {
		return MDKVA_TOD_5_OCCDATE;
	}
	public void setMDKVA_TOD_5_OCCDATE(String mDKVA_TOD_5_OCCDATE) {
		MDKVA_TOD_5_OCCDATE = mDKVA_TOD_5_OCCDATE;
	}
	public String getMDKVA_TOD_6_OCCDATE() {
		return MDKVA_TOD_6_OCCDATE;
	}
	public void setMDKVA_TOD_6_OCCDATE(String mDKVA_TOD_6_OCCDATE) {
		MDKVA_TOD_6_OCCDATE = mDKVA_TOD_6_OCCDATE;
	}
	public String getMDKVA_TOD_7_OCCDATE() {
		return MDKVA_TOD_7_OCCDATE;
	}
	public void setMDKVA_TOD_7_OCCDATE(String mDKVA_TOD_7_OCCDATE) {
		MDKVA_TOD_7_OCCDATE = mDKVA_TOD_7_OCCDATE;
	}
	public String getMDKVA_TOD_8_OCCDATE() {
		return MDKVA_TOD_8_OCCDATE;
	}
	public void setMDKVA_TOD_8_OCCDATE(String mDKVA_TOD_8_OCCDATE) {
		MDKVA_TOD_8_OCCDATE = mDKVA_TOD_8_OCCDATE;
	}
	public String getPF() {
		return PF;
	}
	public void setPF(String pF) {
		PF = pF;
	}
	public java.sql.Timestamp getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(java.sql.Timestamp log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	public String getMdmflag() {
		return mdmflag;
	}
	public void setMdmflag(String mdmflag) {
		this.mdmflag = mdmflag;
	}

	
	
	
}
