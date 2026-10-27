package com.rtdas.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.MeterDao;
import com.rtdas.common.models.Meter;
import com.rtdas.common.models.MeterMake;
import com.rtdas.common.models.MeterType;
import com.rtdas.common.service.MeterService;
import com.rtdas.common.models.InstallationPoints;
@Service("meterService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MeterServiceImpl implements MeterService {

	@Autowired
	private MeterDao meterDao;
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addMeter(Meter meter) {
		meterDao.addMeter(meter);

	}

	@Override
	public List<InstallationPoints> getInstallPoints() {
		// TODO Auto-generated method stub
		return meterDao.getInstallPoints();
	}
	
	
	@Override
	public List<Meter> listMetersByLocation(List<Integer> locationid, int type) {
		// TODO Auto-generated method stub
		return meterDao.listMetersByLocation(locationid, type);
	}
	
	@Override
	public void deleteMeter(Meter meter) {
		meterDao.deleteMeter(meter);
	}

	@Override
	public Meter getMeter(int meterId) {
		return meterDao.getMeter(meterId);
	}
	
	@Override
	public Meter getMeterByslno(String meter){
		return meterDao.getMeterByslno(meter);
	}

	@Override
	public List<Meter> listMeters(List<Integer> iedids) {
		return meterDao.listMeters(iedids);
	}

	@Override
	public List<MeterType> listMeterTypes() {
		return meterDao.listMeterTypes();
	}

	@Override
	public List<Meter> listAdminMeters(List<Integer> iedids) {
		return meterDao.listAdminMeters(iedids);
	}

	@Override
	public List<MeterMake> listMeterMakes() {
		// TODO Auto-generated method stub
		return meterDao.listMeterMakes();
	}

	@Override
	public List<Meter> getUnmappedMeters() {
		// TODO Auto-generated method stub
		return meterDao.getUnmappedMeters();
	}

	@Override
	public void changeMeter(Meter meter, String oldMeter) {
		// TODO Auto-generated method stub
		meterDao.changeMeter(meter, oldMeter);
	}
	

}
