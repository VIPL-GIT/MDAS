package com.rtdas.common.beans;

public class TariffBean {

	private int slNo;
	private String tariffName;
	private String applyTax;
	private String tax;
	private String startValue;
	private String endValue;
	private String units;
	private String rate;
	private String tariffType;
//	private String endValue1;
//	private String endValue2;
//	private String startValue1;
//	private String startValue2;

	public String getStartValue() {
		return startValue;
	}

	public void setStartValue(String startValue) {
		this.startValue = startValue;
	}

	public String getTariffType() {
		return tariffType;
	}

//	public String getEndValue1() {
//		return endValue1;
//	}
//
//	public void setEndValue1(String endValue1) {
//		this.endValue1 = endValue1;
//	}
//
//	public String getEndValue2() {
//		return endValue2;
//	}
//
//	public void setEndValue2(String endValue2) {
//		this.endValue2 = endValue2;
//	}
//
//	public String getStartValue1() {
//		return startValue1;
//	}
//
//	public void setStartValue1(String startValue1) {
//		this.startValue1 = startValue1;
//	}
//
//	public String getStartValue2() {
//		return startValue2;
//	}
//
//	public void setStartValue2(String startValue2) {
//		this.startValue2 = startValue2;
//	}

	public void setTariffType(String tariffType) {
		this.tariffType = tariffType;
	}

	public int getSlNo() {
		return slNo;
	}

	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}

	public String getTariffName() {
		return tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}

	public String getApplyTax() {
		return applyTax;
	}

	public void setApplyTax(String applyTax) {
		this.applyTax = applyTax;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getEndValue() {
		return endValue;
	}

	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

}
