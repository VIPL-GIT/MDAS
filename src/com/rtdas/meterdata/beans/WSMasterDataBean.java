package com.rtdas.meterdata.beans;

public class WSMasterDataBean {

	private String token_no;
	private String kno;
	private String bm;
	private String by;
	private String sdo;
	private String binder_no;
	private String account_no;
	private String meter_no;
	private String connection_status;
	private String last_meter_sts_cd;
	private String meter_vector_type;
	private String meter_digits;
	private String prev_reading_date;
	private String prev_kwh;
	private String prev_kvah;
	private String prev_kva;
	private String pf;
	private String mf;
	private String name;
	private String address;
	private String category_id;
	private String tariff_code;
	private String last_bill_issue_date;
	private String avg_cnsp;
	private String time_stamp_crt;
	private String latitude;
	private String longitude;
	private String cdmnd;
	private String mobile;
	private String sancload;
	private String connload;
	private String ctptratio;
	private String mtrinstdt;
	private String supvolt;
	private String mtrvolt;
	private String make;
	
	//------------for net meters ------------------
		private String solarmeter;
		private String export_prev_kwh;
		private String export_prev_kvah;
		private String avg_cnsp_export;
		
		
		
		
	public String getSolarmeter() {
			return solarmeter;
		}
		public void setSolarmeter(String solarmeter) {
			this.solarmeter = solarmeter;
		}
		public String getExport_prev_kwh() {
			return export_prev_kwh;
		}
		public void setExport_prev_kwh(String export_prev_kwh) {
			this.export_prev_kwh = export_prev_kwh;
		}
		public String getExport_prev_kvah() {
			return export_prev_kvah;
		}
		public void setExport_prev_kvah(String export_prev_kvah) {
			this.export_prev_kvah = export_prev_kvah;
		}
		public String getAvg_cnsp_export() {
			return avg_cnsp_export;
		}
		public void setAvg_cnsp_export(String avg_cnsp_export) {
			this.avg_cnsp_export = avg_cnsp_export;
		}
	public String getToken_no() {
		return token_no;
	}
	public void setToken_no(String token_no) {
		this.token_no = token_no;
	}
	public String getKno() {
		return kno;
	}
	public void setKno(String kno) {
		this.kno = kno;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public String getSdo() {
		return sdo;
	}
	public void setSdo(String sdo) {
		this.sdo = sdo;
	}
	public String getBinder_no() {
		return binder_no;
	}
	public void setBinder_no(String binder_no) {
		this.binder_no = binder_no;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getMeter_no() {
		return meter_no;
	}
	public void setMeter_no(String meter_no) {
		this.meter_no = meter_no;
	}
	public String getConnection_status() {
		return connection_status;
	}
	public void setConnection_status(String connection_status) {
		this.connection_status = connection_status;
	}
	public String getLast_meter_sts_cd() {
		return last_meter_sts_cd;
	}
	public void setLast_meter_sts_cd(String last_meter_sts_cd) {
		this.last_meter_sts_cd = last_meter_sts_cd;
	}
	public String getMeter_vector_type() {
		return meter_vector_type;
	}
	public void setMeter_vector_type(String meter_vector_type) {
		this.meter_vector_type = meter_vector_type;
	}
	public String getMeter_digits() {
		return meter_digits;
	}
	public void setMeter_digits(String meter_digits) {
		this.meter_digits = meter_digits;
	}
	public String getPrev_reading_date() {
		return prev_reading_date;
	}
	public void setPrev_reading_date(String prev_reading_date) {
		this.prev_reading_date = prev_reading_date;
	}
	public String getPrev_kwh() {
		return prev_kwh;
	}
	public void setPrev_kwh(String prev_kwh) {
		this.prev_kwh = prev_kwh;
	}
	public String getPrev_kvah() {
		return prev_kvah;
	}
	public void setPrev_kvah(String prev_kvah) {
		this.prev_kvah = prev_kvah;
	}
	public String getPrev_kva() {
		return prev_kva;
	}
	public void setPrev_kva(String prev_kva) {
		this.prev_kva = prev_kva;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getTariff_code() {
		return tariff_code;
	}
	public void setTariff_code(String tariff_code) {
		this.tariff_code = tariff_code;
	}
	public String getLast_bill_issue_date() {
		return last_bill_issue_date;
	}
	public void setLast_bill_issue_date(String last_bill_issue_date) {
		this.last_bill_issue_date = last_bill_issue_date;
	}
	public String getAvg_cnsp() {
		return avg_cnsp;
	}
	public void setAvg_cnsp(String avg_cnsp) {
		this.avg_cnsp = avg_cnsp;
	}
	public String getTime_stamp_crt() {
		return time_stamp_crt;
	}
	public void setTime_stamp_crt(String time_stamp_crt) {
		this.time_stamp_crt = time_stamp_crt;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCdmnd() {
		return cdmnd;
	}
	public void setCdmnd(String cdmnd) {
		this.cdmnd = cdmnd;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSancload() {
		return sancload;
	}
	public void setSancload(String sancload) {
		this.sancload = sancload;
	}
	public String getConnload() {
		return connload;
	}
	public void setConnload(String connload) {
		this.connload = connload;
	}
	public String getCtptratio() {
		return ctptratio;
	}
	public void setCtptratio(String ctptratio) {
		this.ctptratio = ctptratio;
	}
	public String getMtrinstdt() {
		return mtrinstdt;
	}
	public void setMtrinstdt(String mtrinstdt) {
		this.mtrinstdt = mtrinstdt;
	}
	public String getSupvolt() {
		return supvolt;
	}
	public void setSupvolt(String supvolt) {
		this.supvolt = supvolt;
	}
	public String getMtrvolt() {
		return mtrvolt;
	}
	public void setMtrvolt(String mtrvolt) {
		this.mtrvolt = mtrvolt;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	
	
}
