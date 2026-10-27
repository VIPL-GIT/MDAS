package com.rtdas.mdm.beans;

import com.rtdas.mdm.models.*;
import java.util.*;

public class LoadBean {
	
	public D4_columns header;
	public List<D4_data> data;
	public D4_columns getHeader() {
		return header;
	}
	public void setHeader(D4_columns header) {
		this.header = header;
	}
	public List<D4_data> getData() {
		return data;
	}
	public void setData(List<D4_data> data) {
		this.data = data;
	}
	
	

}
