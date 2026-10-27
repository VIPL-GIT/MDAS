package com.rtdas.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.beans.IEDMasterBean;
import com.rtdas.common.dao.IEDDao;
import com.rtdas.common.models.IEDMaster;

@Repository("ieddao")
public class IEDDaoImpl implements IEDDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addIED(IEDMasterBean imb) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteIED(int ied_id) {
		sessionFactory.getCurrentSession().createQuery("delete from IEDMaster where ied_id="+ied_id).executeUpdate();
		
		sessionFactory.getCurrentSession().createSQLQuery("delete from ied_device_map where ied_id="+ied_id).executeUpdate();

	}

	@Override
	public IEDMaster getIED(int ied_id) {
		
		return (IEDMaster) sessionFactory.getCurrentSession().get(IEDMaster.class, ied_id);
	}

	@Override
	public List<IEDMaster> listIED(List<Integer> loggerIds) {
		Query q=null;
		List<IEDMaster> iedlist=null;
		try{
			String sql="from IEDMaster where logger_id in(:ids)";
			q=sessionFactory.getCurrentSession().createQuery(sql);
			q.setParameterList("ids", loggerIds);
			iedlist=q.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return iedlist;
	}
	
	@Override
	public Map<Integer,String> devicesMappedToIED(){
		
		Map<Integer,String> mappedMap=new HashMap<>();
		try{
			String sql="SELECT i.ied_id,makename,typename FROM "
					+ "(SELECT d.device_id id,dm.device_make_name makename,dt.device_type_name typename FROM "
					+ " device d,devicemake dm,devicetype dt WHERE d.device_make_id=dm.device_make_id AND d.device_type_id=dt.device_type_id) t,"
					+ "ied_device_map i WHERE t.id=i.device_id";
			List<Object[]> resultList=sessionFactory.getCurrentSession().createSQLQuery(sql).list();
			for(Object[] obj:resultList){
				int id=Integer.parseInt(obj[0].toString());
				String name=obj[1]+"-"+obj[2];
				if(mappedMap.containsKey(id)){
					String value=mappedMap.get(id)+","+name;
					mappedMap.put(id, value);
				}else{
				mappedMap.put(id,name);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return mappedMap;
		
	}

}
