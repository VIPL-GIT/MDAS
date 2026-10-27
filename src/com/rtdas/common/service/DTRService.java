package com.rtdas.common.service;



import java.util.List;

import com.rtdas.common.models.DTR;


public interface DTRService {
	
	public int addDTR(DTR dtr);
	public void deleteDTR(int dtrid);
	public DTR getDTR(int dtrId);	
	public List<DTR> listDTRs(String clientId);
	public List<DTR> listDTRsByfId(String fid);
	
	public String processRowInsertion(List<String> header,List<String> values,int clientId) throws Exception;

}
