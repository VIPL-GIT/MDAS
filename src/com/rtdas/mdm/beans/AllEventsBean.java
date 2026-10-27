package com.rtdas.mdm.beans;

import java.util.List;

import com.rtdas.mdm.models.*;


public class AllEventsBean {
	

	public D5_columns header;
	public List<D5_data_all> data;
	public D5_columns getHeader() {
		return header;
	}
	public void setHeader(D5_columns header) {
		this.header = header;
	}
	public List<D5_data_all> getData() {
		return data;
	}
	public void setData(List<D5_data_all> data) {
		this.data = data;
	}
	
	
	
	

}
