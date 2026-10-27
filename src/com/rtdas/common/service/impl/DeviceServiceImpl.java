package com.rtdas.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.DeviceDao;
import com.rtdas.common.models.Device;
import com.rtdas.common.models.DeviceMake;
import com.rtdas.common.models.DeviceType;
import com.rtdas.common.service.DeviceService;

@Service("deviceService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)

public class DeviceServiceImpl implements DeviceService {
	
	@Autowired
	private DeviceDao deviceDao;

//--------------------------------device type ---------------------------------------------------
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addDeviceType(DeviceType deviceType) {
		
		deviceDao.addDeviceType(deviceType);
	}
	@Override
	public void deleteDeviceType(int deviceTypeId) {
		
		deviceDao.deleteDeviceType(deviceTypeId);
		
	}
	@Override
	public DeviceType getDeviceType(int deviceTypeId) {
		
		return deviceDao.getDeviceType(deviceTypeId);
	}
	@Override
	public List<Object[]> listDeviceType(String clientId) {
		
		return deviceDao.listDeviceType(clientId);
	}

//------------------------------device make ---------------------------------------------------	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addDeviceMake(DeviceMake deviceMake) {
		
		deviceDao.addDeviceMake(deviceMake);
		
	}
	@Override
	public void deleteDeviceMake(int deviceMakeId) {
		
		deviceDao.deleteDeviceMake(deviceMakeId);
	}
	@Override
	public List<Object[]> listDeviceMake(String clientId) {
		
		return deviceDao.listDeviceMake(clientId);
	}
	@Override
	public DeviceMake getDeviceMake(int deviceMakeId) {
		
		return deviceDao.getDeviceMake(deviceMakeId);
	}

//----------------------------------device ---------------------------------------------------	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addDevice(Device device,String paramIds) {
		
		deviceDao.addDevice(device,paramIds);
		
	}
	@Override
	public void deleteDevice(int deviceId) {
		
		deviceDao.deleteDevice(deviceId);
		
	}
	@Override
	public Device getDevice(int deviceId) {
		
		return deviceDao.getDevice(deviceId);
	}
	@Override
	public List<Object[]> listDevice(String clientId) {
		
		return deviceDao.listDevice(clientId);
	}
	
	
	@Override
	public List<Integer> getMappedParams(int deviceId){
		return  deviceDao.getMappedParams(deviceId);
	}

}
