package com.rtdas.common.dao;

import java.util.List;

import com.rtdas.common.models.Device;
import com.rtdas.common.models.DeviceMake;
import com.rtdas.common.models.DeviceType;

public interface DeviceDao {

//------------------------Device type -------------------------------	
	void   addDeviceType(DeviceType deviceType);
	public List<Object[]> listDeviceType(String clientId);
	void   deleteDeviceType(int deviceTypeId);
	public DeviceType getDeviceType(int deviceTypeId);
	
//------------------------Device make -------------------------------
	public List<Object[]> listDeviceMake(String clientId);	
	void addDeviceMake(DeviceMake deviceMake);
	void deleteDeviceMake(int deviceMakeId);	
	public DeviceMake getDeviceMake(int deviceMakeId);

	
//-----------------------Device --------------------------------------
	public List<Object[]> listDevice(String clientId);
	void addDevice(Device device,String paramIds);
	void deleteDevice(int deviceId);
	public Device getDevice(int deviceId);
	public List<Integer> getMappedParams(int deviceId);
	//void editDevice(Device device);

}
