package com.rtdas.mdm.beans;

import java.util.List;

import com.rtdas.mdm.models.*;


public class BillingBean {
	public D3_columns b3_header;
	public D3_columns b5_header;
	public List<D3_b3> energies;
	public List<D3_b5> mds;
	
	
	public D3_columns getB3_header() {
		return b3_header;
	}
	public void setB3_header(D3_columns b3_header) {
		this.b3_header = b3_header;
	}
	public D3_columns getB5_header() {
		return b5_header;
	}
	public void setB5_header(D3_columns b5_header) {
		this.b5_header = b5_header;
	}
	public List<D3_b3> getEnergies() {
		return energies;
	}
	public void setEnergies(List<D3_b3> energies) {
		this.energies = energies;
	}
	public List<D3_b5> getMds() {
		return mds;
	}
	public void setMds(List<D3_b5> mds) {
		this.mds = mds;
	}
	
	
	

}
