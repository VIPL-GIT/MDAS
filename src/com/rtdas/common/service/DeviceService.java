package com.rtdas.common.service;

import java.util.List;

import com.rtdas.common.models.Device;
import com.rtdas.common.models.DeviceMake;
import com.rtdas.common.models.DeviceType;

public interface DeviceService {

//---------------device type -------------------------------	
	void addDeviceType(DeviceType deviceType);
	void deleteDeviceType(int deviceTypeId);
	public List<Object[]> listDeviceType(String clientId);	
	public DeviceType getDeviceType(int deviceTypeId);

//-----------------device make -------------------------------
	void addDeviceMake(DeviceMake deviceMake);	
	void deleteDeviceMake(int deviceMakeId);
	public List<Object[]> listDeviceMake(String clientId);
	public DeviceMake getDeviceMake(int deviceMakeId);
	
//-----------------device -------------------------------------	
	void addDevice(Device device,String paramIds);
	void deleteDevice(int deviceId);
	public  List<Object[]> listDevice(String clientId);
	public Device getDevice(int deviceId);
	public List<Integer> getMappedParams(int deviceId);
	
	
	

}
