package com.rtdas.mdm.beans;

import java.util.List;

import com.rtdas.mdm.models.D5_columns;
import com.rtdas.mdm.models.D5_data;




public class EventsBean {
	

	public D5_columns header;
	public List<D5_data> data;
	public D5_columns getHeader() {
		return header;
	}
	public void setHeader(D5_columns header) {
		this.header = header;
	}
	public List<D5_data> getData() {
		return data;
	}
	public void setData(List<D5_data> data) {
		this.data = data;
	}
	
	
	
	

}
