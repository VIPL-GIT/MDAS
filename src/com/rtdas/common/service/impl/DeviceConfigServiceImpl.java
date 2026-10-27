package com.rtdas.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.DeviceConfigDao;
import com.rtdas.common.models.MemoryMap;
import com.rtdas.common.service.DeviceConfigService;

@Service("deviceConfigService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DeviceConfigServiceImpl implements DeviceConfigService {

	@Autowired
	private DeviceConfigDao deviceConfigDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addDeviceConfig(MemoryMap memoryMap) {
		deviceConfigDao.addDeviceConfig(memoryMap);

	}

	@Override
	public void deleteDeviceConfig(int deviceId) {
		deviceConfigDao.deleteDeviceConfig(deviceId);

	}

	@Override
	public List<MemoryMap> listDeviceConfig(String clientId,int deviceId) {
		return deviceConfigDao.listDeviceConfig(clientId,deviceId);
	}

	@Override
	public MemoryMap getDeviceConfig(int memory_map_id) {
		return deviceConfigDao.getDeviceConfig(memory_map_id);
	}

	@Override
	public void deleteDeviceConfigBymemoryMapId(int mapid,int deviceId) {
		// TODO Auto-generated method stub
		deviceConfigDao.deleteDeviceConfigBymemoryMapId(mapid,deviceId);
	}

}
