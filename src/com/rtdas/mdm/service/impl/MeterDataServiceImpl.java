package com.rtdas.mdm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.rtdas.mdm.dao.MeterDataDao;

import com.rtdas.mdm.models.D2_curr;
import com.rtdas.mdm.models.D3_b3;
import com.rtdas.mdm.models.D3_b5;
import com.rtdas.mdm.models.D3_columns;
import com.rtdas.mdm.models.D4_columns;
import com.rtdas.mdm.models.D4_data;
import com.rtdas.mdm.models.D5_columns;
import com.rtdas.mdm.models.D5_data;
import com.rtdas.mdm.models.D5_data_all;
import com.rtdas.mdm.service.MeterDataService;
import com.rtdas.meterdata.beans.AutoResetEnergies;

@Service("MeterDataService")
@Transactional
public class MeterDataServiceImpl implements MeterDataService {
	@Autowired
	private MeterDataDao dao;

	@Override
	public D2_curr getLatestInstant(String meter,String date) {
		return dao.getLatestInstant(meter,date);
	}

	@Override
	public D3_columns getBillingHeaders(String billing_type,String meter) {
		return dao.getBillingHeaders(billing_type,meter);
	}

	@Override
	public D4_columns getLoadHeaders(String meter) {
		return dao.getLoadHeaders(meter);
	}

	@Override
	public D5_columns getEventsHeaders(String meter) {
		return dao.getEventsHeaders(meter);
	}

	@Override
	public List<D3_b3> getBillingEnergies(String meter,String date) {
		return dao.getBillingEnergies(meter,date);
	}

	@Override
	public List<D3_b5> getBillingMds(String meter,String date) {
		return dao.getBillingMds(meter,date);
	}

	@Override
	public List<D4_data> getLoadProfile(String meter,String date) {
		return dao.getLoadProfile(meter,date);
	}

	@Override
	public List<D5_data> getEvents(String meter,String date) {
		return dao.getEvents(meter,date);
	}

	@Override
	public List<AutoResetEnergies> getAutoResetEnergies(String meter) {
		 return dao.getAutoResetEnergies(meter);
	}

	@Override
	public List<D5_data_all> getAllEvents(String meter) {
		return dao.getAllEvents(meter);
	}

}
