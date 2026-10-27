package com.rtdas.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.dao.DeviceConfigDao;
import com.rtdas.common.models.Device;
import com.rtdas.common.models.MemoryMap;

@Repository("deviceConfigDao")
public class DeviceConfigDaoImpl implements DeviceConfigDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addDeviceConfig(MemoryMap memoryMap) {
		sessionFactory.getCurrentSession().saveOrUpdate(memoryMap);
		
	}

	@Override
	public void deleteDeviceConfig(int deviceId) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM MemoryMap WHERE device_id = "+deviceId).executeUpdate();
		
	}

	@Override
	public List<MemoryMap> listDeviceConfig(String clientId,int deviceId) {
		List<MemoryMap> list=new ArrayList();
		try{
			list= sessionFactory.getCurrentSession().createQuery("from MemoryMap where device_id="+deviceId).list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public MemoryMap getDeviceConfig(int memory_map_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDeviceConfigBymemoryMapId(int mapid,int deviceId) {
		try{
			Session ses=sessionFactory.getCurrentSession();
			ses.createQuery("DELETE FROM MemoryMap WHERE memory_map_id = "+mapid).executeUpdate();
			ses.flush();
			//decreasing the params count by 1 after deleting
			Device device=(Device) ses.get(Device.class, deviceId);
			ses.flush();
			device.setSupportedParamsCount(device.getSupportedParamsCount()-1);
			ses.saveOrUpdate(device);
			ses.flush();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	
	

}
