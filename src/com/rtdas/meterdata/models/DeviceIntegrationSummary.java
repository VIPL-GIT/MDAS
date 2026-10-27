package com.rtdas.meterdata.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="device_integration_summary")
public class DeviceIntegrationSummary {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int 	recordid;
	private String	meter_serial_no;
	private String	officeid;
	private String	binderid;
	private String	tokenno;
	private String	mdas_kno;
	private String	mdas_consumer_name;
	private String	mdas_consumer_address;
	private String	kno;
	private String	consumer_name;
	private String	consumer_address;
	private String	kwh_i;
	private String	kvah_i;
	private String	mdkva_i;
	private String	pf;
	private String	mf;
	private String	istatus;
	private String	metermake;
	private String	apikey;
	private String	billmonth;
	private String	last_comm;
	private String	modem_serial_no;
	private String	ipaddress;
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
	public String getOfficeid() {
		return officeid;
	}
	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}
	public String getBinderid() {
		return binderid;
	}
	public void setBinderid(String binderid) {
		this.binderid = binderid;
	}
	public String getTokenno() {
		return tokenno;
	}
	public void setTokenno(String tokenno) {
		this.tokenno = tokenno;
	}
	public String getMdas_kno() {
		return mdas_kno;
	}
	public void setMdas_kno(String mdas_kno) {
		this.mdas_kno = mdas_kno;
	}
	public String getMdas_consumer_name() {
		return mdas_consumer_name;
	}
	public void setMdas_consumer_name(String mdas_consumer_name) {
		this.mdas_consumer_name = mdas_consumer_name;
	}
	public String getMdas_consumer_address() {
		return mdas_consumer_address;
	}
	public void setMdas_consumer_address(String mdas_consumer_address) {
		this.mdas_consumer_address = mdas_consumer_address;
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
	public String getKwh_i() {
		return kwh_i;
	}
	public void setKwh_i(String kwh_i) {
		this.kwh_i = kwh_i;
	}
	public String getKvah_i() {
		return kvah_i;
	}
	public void setKvah_i(String kvah_i) {
		this.kvah_i = kvah_i;
	}
	public String getMdkva_i() {
		return mdkva_i;
	}
	public void setMdkva_i(String mdkva_i) {
		this.mdkva_i = mdkva_i;
	}
	public String getPf() {
		return pf;
	}
	public void setPf(String pf) {
		this.pf = pf;
	}
	public String getMf() {
		return mf;
	}
	public void setMf(String mf) {
		this.mf = mf;
	}
	public String getIstatus() {
		return istatus;
	}
	public void setIstatus(String istatus) {
		this.istatus = istatus;
	}
	public String getMetermake() {
		return metermake;
	}
	public void setMetermake(String metermake) {
		this.metermake = metermake;
	}
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	public String getBillmonth() {
		return billmonth;
	}
	public void setBillmonth(String billmonth) {
		this.billmonth = billmonth;
	}
	public String getLast_comm() {
		return last_comm;
	}
	public void setLast_comm(String last_comm) {
		this.last_comm = last_comm;
	}
	public String getModem_serial_no() {
		return modem_serial_no;
	}
	public void setModem_serial_no(String modem_serial_no) {
		this.modem_serial_no = modem_serial_no;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	
	

}
