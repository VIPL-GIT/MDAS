package com.rtdas.common.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.dao.DeviceDao;
import com.rtdas.common.models.Device;
import com.rtdas.common.models.DeviceMake;
import com.rtdas.common.models.DeviceType;

@Repository("deviceDao")
public class DeviceDaoImpl implements DeviceDao {
	
	@Autowired
	private SessionFactory sessionFactory;

//-------------------------------------Device type -----------------------------------------------------	
	@Override
	public void addDeviceType(DeviceType deviceType) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(deviceType);
		
	}

	@Override
	public void deleteDeviceType(int deviceTypeId) {
		
		sessionFactory.getCurrentSession().createQuery("DELETE FROM DeviceType WHERE deviceTypeId = "+deviceTypeId).executeUpdate();
		
	}

	@Override
	public DeviceType getDeviceType(int deviceTypeId) {
		
		return (DeviceType) sessionFactory.getCurrentSession().get(DeviceType.class, deviceTypeId);
	}
	@Override
	public List<Object[]> listDeviceType(String clientId) {
		String sql="";
		if(clientId.equals("0"))
			sql="SELECT d.device_type_id,d.device_type_name,c.client_name FROM analog_device_type d,client c WHERE d.client_id=c.client_id ORDER BY client_name";
		else
			sql="SELECT d.device_type_id,d.device_type_name,c.client_name FROM analog_device_type d,client c WHERE d.client_id=c.client_id and d.client_id="+clientId+" ORDER BY client_name";
		Query q=sessionFactory.getCurrentSession().createSQLQuery(sql);
		
		return (List<Object[]>) q.list();
	}

	
//--------------------------------------device make -------------------------------------------	
	@Override
	public void addDeviceMake(DeviceMake deviceMake) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(deviceMake);
		
	}	

	@Override
	public void deleteDeviceMake(int deviceMakeId) {
		
		sessionFactory.getCurrentSession().createQuery("DELETE FROM DeviceMake WHERE deviceMakeId = "+deviceMakeId).executeUpdate();
		
	}

	@Override
	public DeviceMake getDeviceMake(int deviceMakeId) {
		
		return (DeviceMake) sessionFactory.getCurrentSession().get(DeviceMake.class, deviceMakeId);
	}
	@Override
	public List<Object[]> listDeviceMake(String clientId) {
		String sql="";
		if(clientId.equals("0"))
			sql="SELECT d.device_make_id,d.device_make_name,c.client_name FROM analog_device_make d,client c WHERE d.client_id=c.client_id ORDER BY client_name";
		else
			sql="SELECT d.device_make_id,d.device_make_name,c.client_name FROM analog_device_make d,client c"
					+ " WHERE d.client_id=c.client_id and d.client_id="+clientId+" ORDER BY client_name";
		
        Query q=sessionFactory.getCurrentSession().createSQLQuery(sql);		
		return (List<Object[]>) q.list();
		
	}

	
//----------------------------------------- Device ---------------------------------------------------------
	
	@Override
	public List<Object[]> listDevice(String clientId) {
		String sql="";
		if(clientId.equals("0"))
			sql="SELECT d.device_id,dt.device_type_name,dm.device_make_name,d.supported_params_count,d.supported_baudrates,d.active_flg FROM analog_device_type dt,analog_device_make dm,analog_device_master d WHERE d.device_type_id=dt.device_type_id AND d.device_make_id=dm.device_make_id ORDER BY device_id";
		else
			sql="SELECT d.device_id,dt.device_type_name,dm.device_make_name,d.supported_params_count,d.supported_baudrates,d.active_flg FROM analog_device_type dt,analog_device_make dm,analog_device_master d WHERE"
					+ " dt.client_id="+clientId+" and  d.device_type_id=dt.device_type_id AND d.device_make_id=dm.device_make_id ORDER BY device_id";
		
        Query q=sessionFactory.getCurrentSession().createSQLQuery(sql);		
		return (List<Object[]>) q.list();
		
		
	}

	@Override
	public void addDevice(Device device,String paramIds) {
		Session session=sessionFactory.getCurrentSession();
		try{
			int id;
			if(device.getDeviceId()==0){				
				id=(int)session.save(device);
			}else{
				session.saveOrUpdate(device);
				id=device.getDeviceId();
			}
			session.flush();
            session.clear();
            //deleting the existing mapping in device_parameter_map table
            session.createSQLQuery("delete from analog_param_device_map where device_id="+id).executeUpdate();
            // inserting the new data into device_parameter_map table
            String sql="insert into analog_param_device_map(parameter_id,device_id) values(?,?)";
            
            for(int i=0;i<paramIds.split(",").length;i++){
            	Query q=session.createSQLQuery(sql);
            	q.setString(0,paramIds.split(",")[i]);
            	q.setInteger(1,id);
            	q.executeUpdate();
				session.flush();
	            session.clear();
            }
            
            
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteDevice(int deviceId) {
		
		sessionFactory.getCurrentSession().createQuery("DELETE FROM Device WHERE deviceId = "+deviceId).executeUpdate();
		
		sessionFactory.getCurrentSession().createSQLQuery("delete FROM analog_param_device_map WHERE device_id = "+deviceId).executeUpdate();
		
	}

	@Override
	public Device getDevice(int deviceId) {
		
		return (Device) sessionFactory.getCurrentSession().get(Device.class, deviceId);
	}

	@Override
	public List<Integer> getMappedParams(int deviceId){
		List<Integer> params=null;
		try{
			//params=sessionFactory.getCurrentSession().createSQLQuery("select parameter_id from device_parameter_map where device_id="+deviceId).list();
			params=sessionFactory.getCurrentSession().createQuery("select parameter_id from ParameterMap where device_id="+deviceId).list();
		}catch(Exception e){
			
		}
		
		return params;
	}
}
