package com.rtdas.common.dao;

import java.util.List;

import com.rtdas.common.models.Meter;
import com.rtdas.common.models.MeterMake;
import com.rtdas.common.models.MeterType;
import com.rtdas.common.models.InstallationPoints;
public interface MeterDao {
	
	public void addMeter(Meter meter);
	public void deleteMeter(Meter meter);
	public Meter getMeter(int meterId);
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
