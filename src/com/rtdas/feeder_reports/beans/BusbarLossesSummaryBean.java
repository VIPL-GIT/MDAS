package com.rtdas.feeder_reports.beans;

public class BusbarLossesSummaryBean {

	private String hierarchy;
	private double all_11kv_cons;
	private double all_33kv_out_cons;
	private  double all_33kv_in_cons;
	private double loss;
	private double loss_ing_percent;
	public String getHierarchy() {
		return hierarchy;
	}
	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}
	public double getAll_11kv_cons() {
		return all_11kv_cons;
	}
	public void setAll_11kv_cons(double all_11kv_cons) {
		this.all_11kv_cons = all_11kv_cons;
	}
	public double getAll_33kv_out_cons() {
		return all_33kv_out_cons;
	}
	public void setAll_33kv_out_cons(double all_33kv_out_cons) {
		this.all_33kv_out_cons = all_33kv_out_cons;
	}
	public double getAll_33kv_in_cons() {
		return all_33kv_in_cons;
	}
	public void setAll_33kv_in_cons(double all_33kv_in_cons) {
		this.all_33kv_in_cons = all_33kv_in_cons;
	}
	public double getLoss() {
		return loss;
	}
	public void setLoss(double loss) {
		this.loss = loss;
	}
	public double getLoss_ing_percent() {
		return loss_ing_percent;
	}
	public void setLoss_ing_percent(double loss_ing_percent) {
		this.loss_ing_percent = loss_ing_percent;
	}
	
	
	
}
