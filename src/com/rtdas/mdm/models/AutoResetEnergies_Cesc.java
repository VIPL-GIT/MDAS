package com.rtdas.mdm.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="auto_reset_energies_cesc")
public class AutoResetEnergies_Cesc implements Serializable{
	
	private static final long serialVersionUID = 5462223600l;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int recordid;
	
	private String meter_serial_no;
	private String billing_timestamp;
	private String datetime;
	private String CONSUMERNO;


	private String KWH_I;
	private String KWH_E;
	private String KWH_ABS;
	private String KVAH_I;
	private String KVAH_E;
	private String KVAH_ABS;
	private String KVARH_Lag;
	private String KVARH_Lead;

	private String C1KWH_I;
	private String C2KWH_I;
	private String C3KWH_I;
	private String C4KWH_I;
	private String C5KWH_I;
	private String C6KWH_I;
	private String C7KWH_I;
	private String C8KWH_I;
	private String C1KWH_E;
	private String C2KWH_E;
	private String C3KWH_E;
	private String C4KWH_E;
	private String C5KWH_E;
	private String C6KWH_E;
	private String C7KWH_E;
	private String C8KWH_E;
	private String C1KWH_ABS;
	private String C2KWH_ABS;
	private String C3KWH_ABS;
	private String C4KWH_ABS;
	private String C5KWH_ABS;
	private String C6KWH_ABS;
	private String C7KWH_ABS;
	private String C8KWH_ABS;
	private String C1KVAH_I;
	private String C2KVAH_I;
	private String C3KVAH_I;
	private String C4KVAH_I;
	private String C5KVAH_I;
	private String C6KVAH_I;
	private String C7KVAH_I;
	private String C8KVAH_I;
	private String C1KVAH_E;
	private String C2KVAH_E;
	private String C3KVAH_E;
	private String C4KVAH_E;
	private String C5KVAH_E;
	private String C6KVAH_E;
	private String C7KVAH_E;
	private String C8KVAH_E;
	private String C1KVAH_ABS;
	private String C2KVAH_ABS;
	private String C3KVAH_ABS;
	private String C4KVAH_ABS;
	private String C5KVAH_ABS;
	private String C6KVAH_ABS;
	private String C7KVAH_ABS;
	private String C8KVAH_ABS;

	private String CMDKW_I;
	private String CMDKW_E;
	private String CMDKW_ABS;
	private String CMDKVA_I;
	private String CMDKVA_E;
	private String CMDKVA_ABS;

	private String MDKW_I;
	private String MDKW_E;
	private String MDKW_ABS;
	private String MDKVA_I;
	private String MDKVA_E;
	private String MDKVA_ABS;

	private String PF;

	private String CMDKW_I_CURRENT;
	private String CMDKW_E_CURRENT;
	private String CMDKW_ABS_CURRENT;
	private String CMDKVA_I_CURRENT;
	private String CMDKVA_E_CURRENT;
	private String CMDKVA_ABS_CURRENT;
	private java.sql.Timestamp log_timestamp;
	
	private String record_type;
	private String office_code;
	private String token_no;
	private String bccd;
	private String kno;
	private String consumer_name;
	private String consumer_address;
	private String remarks;
	
	
	
	
	public String getRecord_type() {
		return record_type;
	}
	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}
	public String getOffice_code() {
		return office_code;
	}
	public void setOffice_code(String office_code) {
		this.office_code = office_code;
	}
	public String getToken_no() {
		return token_no;
	}
	public void setToken_no(String token_no) {
		this.token_no = token_no;
	}
	public String getBccd() {
		return bccd;
	}
	public void setBccd(String bccd) {
		this.bccd = bccd;
	}
	public String getKno() {
		return kno;
	}
	public void setKno(String kno) {
		this.kno = kno;
	}
	public String getConsumer_name() {
		return consumer_name;
	}
	public void setConsumer_name(String consumer_name) {
		this.consumer_name = consumer_name;
	}
	public String getConsumer_address() {
		return consumer_address;
	}
	public void setConsumer_address(String consumer_address) {
		this.consumer_address = consumer_address;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
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
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getCONSUMERNO() {
		return CONSUMERNO;
	}
	public void setCONSUMERNO(String cONSUMERNO) {
		CONSUMERNO = cONSUMERNO;
	}
	public String getKWH_I() {
		return KWH_I;
	}
	public void setKWH_I(String kWH_I) {
		KWH_I = kWH_I;
	}
	public String getKWH_E() {
		return KWH_E;
	}
	public void setKWH_E(String kWH_E) {
		KWH_E = kWH_E;
	}
	public String getKWH_ABS() {
		return KWH_ABS;
	}
	public void setKWH_ABS(String kWH_ABS) {
		KWH_ABS = kWH_ABS;
	}
	public String getKVAH_I() {
		return KVAH_I;
	}
	public void setKVAH_I(String kVAH_I) {
		KVAH_I = kVAH_I;
	}
	public String getKVAH_E() {
		return KVAH_E;
	}
	public void setKVAH_E(String kVAH_E) {
		KVAH_E = kVAH_E;
	}
	public String getKVAH_ABS() {
		return KVAH_ABS;
	}
	public void setKVAH_ABS(String kVAH_ABS) {
		KVAH_ABS = kVAH_ABS;
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
	public String getC1KWH_I() {
		return C1KWH_I;
	}
	public void setC1KWH_I(String c1kwh_I) {
		C1KWH_I = c1kwh_I;
	}
	public String getC2KWH_I() {
		return C2KWH_I;
	}
	public void setC2KWH_I(String c2kwh_I) {
		C2KWH_I = c2kwh_I;
	}
	public String getC3KWH_I() {
		return C3KWH_I;
	}
	public void setC3KWH_I(String c3kwh_I) {
		C3KWH_I = c3kwh_I;
	}
	public String getC4KWH_I() {
		return C4KWH_I;
	}
	public void setC4KWH_I(String c4kwh_I) {
		C4KWH_I = c4kwh_I;
	}
	public String getC5KWH_I() {
		return C5KWH_I;
	}
	public void setC5KWH_I(String c5kwh_I) {
		C5KWH_I = c5kwh_I;
	}
	public String getC6KWH_I() {
		return C6KWH_I;
	}
	public void setC6KWH_I(String c6kwh_I) {
		C6KWH_I = c6kwh_I;
	}
	public String getC7KWH_I() {
		return C7KWH_I;
	}
	public void setC7KWH_I(String c7kwh_I) {
		C7KWH_I = c7kwh_I;
	}
	public String getC8KWH_I() {
		return C8KWH_I;
	}
	public void setC8KWH_I(String c8kwh_I) {
		C8KWH_I = c8kwh_I;
	}
	public String getC1KWH_E() {
		return C1KWH_E;
	}
	public void setC1KWH_E(String c1kwh_E) {
		C1KWH_E = c1kwh_E;
	}
	public String getC2KWH_E() {
		return C2KWH_E;
	}
	public void setC2KWH_E(String c2kwh_E) {
		C2KWH_E = c2kwh_E;
	}
	public String getC3KWH_E() {
		return C3KWH_E;
	}
	public void setC3KWH_E(String c3kwh_E) {
		C3KWH_E = c3kwh_E;
	}
	public String getC4KWH_E() {
		return C4KWH_E;
	}
	public void setC4KWH_E(String c4kwh_E) {
		C4KWH_E = c4kwh_E;
	}
	public String getC5KWH_E() {
		return C5KWH_E;
	}
	public void setC5KWH_E(String c5kwh_E) {
		C5KWH_E = c5kwh_E;
	}
	public String getC6KWH_E() {
		return C6KWH_E;
	}
	public void setC6KWH_E(String c6kwh_E) {
		C6KWH_E = c6kwh_E;
	}
	public String getC7KWH_E() {
		return C7KWH_E;
	}
	public void setC7KWH_E(String c7kwh_E) {
		C7KWH_E = c7kwh_E;
	}
	public String getC8KWH_E() {
		return C8KWH_E;
	}
	public void setC8KWH_E(String c8kwh_E) {
		C8KWH_E = c8kwh_E;
	}
	public String getC1KWH_ABS() {
		return C1KWH_ABS;
	}
	public void setC1KWH_ABS(String c1kwh_ABS) {
		C1KWH_ABS = c1kwh_ABS;
	}
	public String getC2KWH_ABS() {
		return C2KWH_ABS;
	}
	public void setC2KWH_ABS(String c2kwh_ABS) {
		C2KWH_ABS = c2kwh_ABS;
	}
	public String getC3KWH_ABS() {
		return C3KWH_ABS;
	}
	public void setC3KWH_ABS(String c3kwh_ABS) {
		C3KWH_ABS = c3kwh_ABS;
	}
	public String getC4KWH_ABS() {
		return C4KWH_ABS;
	}
	public void setC4KWH_ABS(String c4kwh_ABS) {
		C4KWH_ABS = c4kwh_ABS;
	}
	public String getC5KWH_ABS() {
		return C5KWH_ABS;
	}
	public void setC5KWH_ABS(String c5kwh_ABS) {
		C5KWH_ABS = c5kwh_ABS;
	}
	public String getC6KWH_ABS() {
		return C6KWH_ABS;
	}
	public void setC6KWH_ABS(String c6kwh_ABS) {
		C6KWH_ABS = c6kwh_ABS;
	}
	public String getC7KWH_ABS() {
		return C7KWH_ABS;
	}
	public void setC7KWH_ABS(String c7kwh_ABS) {
		C7KWH_ABS = c7kwh_ABS;
	}
	public String getC8KWH_ABS() {
		return C8KWH_ABS;
	}
	public void setC8KWH_ABS(String c8kwh_ABS) {
		C8KWH_ABS = c8kwh_ABS;
	}
	public String getC1KVAH_I() {
		return C1KVAH_I;
	}
	public void setC1KVAH_I(String c1kvah_I) {
		C1KVAH_I = c1kvah_I;
	}
	public String getC2KVAH_I() {
		return C2KVAH_I;
	}
	public void setC2KVAH_I(String c2kvah_I) {
		C2KVAH_I = c2kvah_I;
	}
	public String getC3KVAH_I() {
		return C3KVAH_I;
	}
	public void setC3KVAH_I(String c3kvah_I) {
		C3KVAH_I = c3kvah_I;
	}
	public String getC4KVAH_I() {
		return C4KVAH_I;
	}
	public void setC4KVAH_I(String c4kvah_I) {
		C4KVAH_I = c4kvah_I;
	}
	public String getC5KVAH_I() {
		return C5KVAH_I;
	}
	public void setC5KVAH_I(String c5kvah_I) {
		C5KVAH_I = c5kvah_I;
	}
	public String getC6KVAH_I() {
		return C6KVAH_I;
	}
	public void setC6KVAH_I(String c6kvah_I) {
		C6KVAH_I = c6kvah_I;
	}
	public String getC7KVAH_I() {
		return C7KVAH_I;
	}
	public void setC7KVAH_I(String c7kvah_I) {
		C7KVAH_I = c7kvah_I;
	}
	public String getC8KVAH_I() {
		return C8KVAH_I;
	}
	public void setC8KVAH_I(String c8kvah_I) {
		C8KVAH_I = c8kvah_I;
	}
	public String getC1KVAH_E() {
		return C1KVAH_E;
	}
	public void setC1KVAH_E(String c1kvah_E) {
		C1KVAH_E = c1kvah_E;
	}
	public String getC2KVAH_E() {
		return C2KVAH_E;
	}
	public void setC2KVAH_E(String c2kvah_E) {
		C2KVAH_E = c2kvah_E;
	}
	public String getC3KVAH_E() {
		return C3KVAH_E;
	}
	public void setC3KVAH_E(String c3kvah_E) {
		C3KVAH_E = c3kvah_E;
	}
	public String getC4KVAH_E() {
		return C4KVAH_E;
	}
	public void setC4KVAH_E(String c4kvah_E) {
		C4KVAH_E = c4kvah_E;
	}
	public String getC5KVAH_E() {
		return C5KVAH_E;
	}
	public void setC5KVAH_E(String c5kvah_E) {
		C5KVAH_E = c5kvah_E;
	}
	public String getC6KVAH_E() {
		return C6KVAH_E;
	}
	public void setC6KVAH_E(String c6kvah_E) {
		C6KVAH_E = c6kvah_E;
	}
	public String getC7KVAH_E() {
		return C7KVAH_E;
	}
	public void setC7KVAH_E(String c7kvah_E) {
		C7KVAH_E = c7kvah_E;
	}
	public String getC8KVAH_E() {
		return C8KVAH_E;
	}
	public void setC8KVAH_E(String c8kvah_E) {
		C8KVAH_E = c8kvah_E;
	}
	public String getC1KVAH_ABS() {
		return C1KVAH_ABS;
	}
	public void setC1KVAH_ABS(String c1kvah_ABS) {
		C1KVAH_ABS = c1kvah_ABS;
	}
	public String getC2KVAH_ABS() {
		return C2KVAH_ABS;
	}
	public void setC2KVAH_ABS(String c2kvah_ABS) {
		C2KVAH_ABS = c2kvah_ABS;
	}
	public String getC3KVAH_ABS() {
		return C3KVAH_ABS;
	}
	public void setC3KVAH_ABS(String c3kvah_ABS) {
		C3KVAH_ABS = c3kvah_ABS;
	}
	public String getC4KVAH_ABS() {
		return C4KVAH_ABS;
	}
	public void setC4KVAH_ABS(String c4kvah_ABS) {
		C4KVAH_ABS = c4kvah_ABS;
	}
	public String getC5KVAH_ABS() {
		return C5KVAH_ABS;
	}
	public void setC5KVAH_ABS(String c5kvah_ABS) {
		C5KVAH_ABS = c5kvah_ABS;
	}
	public String getC6KVAH_ABS() {
		return C6KVAH_ABS;
	}
	public void setC6KVAH_ABS(String c6kvah_ABS) {
		C6KVAH_ABS = c6kvah_ABS;
	}
	public String getC7KVAH_ABS() {
		return C7KVAH_ABS;
	}
	public void setC7KVAH_ABS(String c7kvah_ABS) {
		C7KVAH_ABS = c7kvah_ABS;
	}
	public String getC8KVAH_ABS() {
		return C8KVAH_ABS;
	}
	public void setC8KVAH_ABS(String c8kvah_ABS) {
		C8KVAH_ABS = c8kvah_ABS;
	}
	public String getCMDKW_I() {
		return CMDKW_I;
	}
	public void setCMDKW_I(String cMDKW_I) {
		CMDKW_I = cMDKW_I;
	}
	public String getCMDKW_E() {
		return CMDKW_E;
	}
	public void setCMDKW_E(String cMDKW_E) {
		CMDKW_E = cMDKW_E;
	}
	public String getCMDKW_ABS() {
		return CMDKW_ABS;
	}
	public void setCMDKW_ABS(String cMDKW_ABS) {
		CMDKW_ABS = cMDKW_ABS;
	}
	public String getCMDKVA_I() {
		return CMDKVA_I;
	}
	public void setCMDKVA_I(String cMDKVA_I) {
		CMDKVA_I = cMDKVA_I;
	}
	public String getCMDKVA_E() {
		return CMDKVA_E;
	}
	public void setCMDKVA_E(String cMDKVA_E) {
		CMDKVA_E = cMDKVA_E;
	}
	public String getCMDKVA_ABS() {
		return CMDKVA_ABS;
	}
	public void setCMDKVA_ABS(String cMDKVA_ABS) {
		CMDKVA_ABS = cMDKVA_ABS;
	}
	public String getMDKW_I() {
		return MDKW_I;
	}
	public void setMDKW_I(String mDKW_I) {
		MDKW_I = mDKW_I;
	}
	public String getMDKW_E() {
		return MDKW_E;
	}
	public void setMDKW_E(String mDKW_E) {
		MDKW_E = mDKW_E;
	}
	public String getMDKW_ABS() {
		return MDKW_ABS;
	}
	public void setMDKW_ABS(String mDKW_ABS) {
		MDKW_ABS = mDKW_ABS;
	}
	public String getMDKVA_I() {
		return MDKVA_I;
	}
	public void setMDKVA_I(String mDKVA_I) {
		MDKVA_I = mDKVA_I;
	}
	public String getMDKVA_E() {
		return MDKVA_E;
	}
	public void setMDKVA_E(String mDKVA_E) {
		MDKVA_E = mDKVA_E;
	}
	public String getMDKVA_ABS() {
		return MDKVA_ABS;
	}
	public void setMDKVA_ABS(String mDKVA_ABS) {
		MDKVA_ABS = mDKVA_ABS;
	}
	public String getPF() {
		return PF;
	}
	public void setPF(String pF) {
		PF = pF;
	}
	public String getCMDKW_I_CURRENT() {
		return CMDKW_I_CURRENT;
	}
	public void setCMDKW_I_CURRENT(String cMDKW_I_CURRENT) {
		CMDKW_I_CURRENT = cMDKW_I_CURRENT;
	}
	public String getCMDKW_E_CURRENT() {
		return CMDKW_E_CURRENT;
	}
	public void setCMDKW_E_CURRENT(String cMDKW_E_CURRENT) {
		CMDKW_E_CURRENT = cMDKW_E_CURRENT;
	}
	public String getCMDKW_ABS_CURRENT() {
		return CMDKW_ABS_CURRENT;
	}
	public void setCMDKW_ABS_CURRENT(String cMDKW_ABS_CURRENT) {
		CMDKW_ABS_CURRENT = cMDKW_ABS_CURRENT;
	}
	public String getCMDKVA_I_CURRENT() {
		return CMDKVA_I_CURRENT;
	}
	public void setCMDKVA_I_CURRENT(String cMDKVA_I_CURRENT) {
		CMDKVA_I_CURRENT = cMDKVA_I_CURRENT;
	}
	public String getCMDKVA_E_CURRENT() {
		return CMDKVA_E_CURRENT;
	}
	public void setCMDKVA_E_CURRENT(String cMDKVA_E_CURRENT) {
		CMDKVA_E_CURRENT = cMDKVA_E_CURRENT;
	}
	public String getCMDKVA_ABS_CURRENT() {
		return CMDKVA_ABS_CURRENT;
	}
	public void setCMDKVA_ABS_CURRENT(String cMDKVA_ABS_CURRENT) {
		CMDKVA_ABS_CURRENT = cMDKVA_ABS_CURRENT;
	}
	public java.sql.Timestamp getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(java.sql.Timestamp log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	
	
	
	
}
