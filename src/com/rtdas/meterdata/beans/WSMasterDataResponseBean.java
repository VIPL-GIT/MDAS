package com.rtdas.meterdata.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WSMasterDataResponseBean {

	private WSResponseDataBean response;
	private String result;
	
	@JsonProperty("RESPONSE")
	public WSResponseDataBean getResponse() {
		return response;
	}
	public void setResponse(WSResponseDataBean response) {
		this.response = response;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	
	
	
	
}
