package com.rtdas.common.service;

import java.util.List;

import com.rtdas.common.models.InstallationPoints;
import com.rtdas.common.models.Meter;
import com.rtdas.common.models.MeterMake;
import com.rtdas.common.models.MeterType;

public interface MeterService {

	public void addMeter(Meter modem);
	public void deleteMeter(Meter modem);
	public Meter getMeter(int modemId);
	public Meter getMeterByslno(String meter);
	public List<Meter> listMeters(List<Integer> iedids);
	public List<Meter> listAdminMeters(List<Integer> iedids);
	public List<MeterType> listMeterTypes();
	
	public List<MeterMake> listMeterMakes();
	
	public List<Meter> listMetersByLocation(List<Integer> locationid,int type);
	public List<InstallationPoints> getInstallPoints();
	
	public List<Meter> getUnmappedMeters();
	
	public void changeMeter(Meter meter,String oldMeter);
	
}
