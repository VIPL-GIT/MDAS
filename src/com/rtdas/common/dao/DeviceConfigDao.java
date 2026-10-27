package com.rtdas.common.dao;

import java.util.List;

import com.rtdas.common.models.MemoryMap;

public interface DeviceConfigDao {

	void addDeviceConfig(MemoryMap memoryMap);
	void deleteDeviceConfig(int deviceId);
	void deleteDeviceConfigBymemoryMapId(int mapid,int deviceId);
	public  List<MemoryMap> listDeviceConfig(String clientId,int deviceId);
	public MemoryMap getDeviceConfig(int memory_map_id);
}
