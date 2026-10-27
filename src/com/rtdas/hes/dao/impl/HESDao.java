package com.rtdas.hes.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.hes.models.Projects;
import com.rtdas.hes.models.RFGateway;
import com.rtdas.hes.models.SmartDevices;

@Repository("hesDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HESDao {

	@Autowired
	private SessionFactory sf;
	
	public List<Projects> getSmartProjects(){
		List<Projects> list=null;
		list=sf.getCurrentSession().createQuery("from Projects").list();
		return list;
	}
	public Projects editSmartProjects(int projid){
		
		Projects proj=(Projects)sf.getCurrentSession().createQuery("from Projects where project_id="+projid).uniqueResult();
		return proj;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveSmartProjects(Projects proj){
		try{
		  sf.getCurrentSession().saveOrUpdate(proj);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void deleteSmartProjects(int projid){
		
		sf.getCurrentSession().createSQLQuery("delete from hes_manufacturer_security_keys where project_id="+projid).executeUpdate();
		
	}
//-------------------------------------------------------------------------------------------	
	public List<RFGateway> getRFGateway(){
		List<RFGateway> list=null;
		list=sf.getCurrentSession().createQuery("from RFGateway").list();
		return list;
	}
	public List<RFGateway> getMACAddress(int id){
		List<RFGateway> list=null;
		list=sf.getCurrentSession().createQuery("from RFGateway where gateway_serial_number='"+id+"'").list();
		return list;
	}
	public List<String> getDistinctRFGateway(){
		List<String> list=new ArrayList();
		List<RFGateway> rflist=sf.getCurrentSession().createQuery("from RFGateway").list();
		for(RFGateway rf:rflist){
			if(!list.contains(rf.getGateway_serial_number())){
				list.add(rf.getGateway_serial_number());
			}
		}
		return list;
	}
	public void deleteRF(int id){
		sf.getCurrentSession().createSQLQuery("delete from hes_rf_gateway where gateway_id="+id).executeUpdate();
	}
	public RFGateway getRF(int id){
		return (RFGateway)sf.getCurrentSession().createQuery("from RFGateway where gateway_id="+id).uniqueResult();
	}
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateRF(RFGateway rf){
		sf.getCurrentSession().saveOrUpdate(rf);
	}
//-------------------------------------------------------------------------------------------	
	public List<SmartDevices> getSmartDevices(){
		List<SmartDevices> list=null;
		list=sf.getCurrentSession().createQuery("from SmartDevices").list();
		
		List<Projects> projlist=sf.getCurrentSession().createQuery("from Projects").list();
		Map<String,String> projMap=new HashMap();
		for(Projects p:projlist){
			projMap.put(p.getProject_id()+"", p.getProject_name());
		}
		//System.out.println("-----------"+projMap);
		for(SmartDevices sd :list){
			if(projMap.containsKey(sd.getProject()))
				sd.setProject(projMap.get(sd.getProject()));
		}
		return list;
	}
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveSmartDevice(SmartDevices sd){
		try{
		sf.getCurrentSession().saveOrUpdate(sd);
//-----------------------------------------------------------------------------------------
		Connection con=sf.getCurrentSession().connection();
		String sql="insert into meter(meter_serial_no,ipaddress) values(?,?)";
		String sql1="select * from meter where meter_serial_no ='"+sd.getMeter_serial_no()+"'";
		int flag=0;
		ResultSet rs=con.prepareStatement(sql1).executeQuery();
		if(rs.next()) flag=1;
		if(flag==0){
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, sd.getMeter_serial_no());
			ps.setString(2, sd.getDestination_ipaddress());
			ps.executeUpdate();
		}
//-----------------------------------------------------------------------------------------		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void deleteSmartDevice(int id){
		sf.getCurrentSession().createSQLQuery("delete from hes_device_details where device_id="+id).executeUpdate();
	}
	public SmartDevices getSmartDevice(int id){
		return (SmartDevices)sf.getCurrentSession().createQuery("from SmartDevices where device_id="+id).uniqueResult();
	}
}
