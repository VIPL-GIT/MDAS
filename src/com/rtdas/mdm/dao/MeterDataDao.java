package com.rtdas.mdm.dao;

import java.util.List;

import com.rtdas.mdm.models.D2_curr;
import com.rtdas.mdm.models.D3_b3;
import com.rtdas.mdm.models.D3_b5;
import com.rtdas.mdm.models.D3_columns;
import com.rtdas.mdm.models.D4_columns;
import com.rtdas.mdm.models.D4_data;
import com.rtdas.mdm.models.D5_columns;
import com.rtdas.mdm.models.D5_data;
import com.rtdas.mdm.models.D5_data_all;
import com.rtdas.meterdata.beans.AutoResetEnergies;

public interface MeterDataDao {
	
	public D2_curr getLatestInstant(String meter,String date);
	public D3_columns getBillingHeaders(String billing_type,String meter);
	public D4_columns getLoadHeaders(String meter);
	public D5_columns getEventsHeaders(String meter);
	
	public List<D3_b3> getBillingEnergies(String meter,String date);
	public List<D3_b5> getBillingMds(String meter,String date);
	
	public List<D4_data> getLoadProfile(String meter,String date);
	public List<D5_data> getEvents(String meter,String date);
	public List<D5_data_all> getAllEvents(String meter);

	public List<AutoResetEnergies> getAutoResetEnergies(String meter);
}
