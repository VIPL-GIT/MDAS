package com.rtdas.common.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rtdas.common.beans.ModuleSubModuleBean;
import com.rtdas.common.dao.ClientDao;
import com.rtdas.common.models.Client;

@Repository("clientDao")
public class ClientDaoImpl implements ClientDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int addClient(Client client,String submoduleIds) {
		 
		 Session session=sessionFactory.getCurrentSession();
		 int id=0;
		try{
			
			if(client.getClient_id()==0){
				id=(int)session.save(client);
			}else{
				session.saveOrUpdate(client);
				id=client.getClient_id();
			}
			session.flush();
            session.clear();
			
			session.createSQLQuery("delete from client_module_map where client_id="+id).executeUpdate();
			
			List subIds=Arrays.asList(submoduleIds.split(","));
			for(int i=0;i<subIds.size();i++){
				session.createSQLQuery("insert into client_module_map(client_id,module_id) values("+id+","+subIds.get(i)+")").executeUpdate();
				session.flush();
	            session.clear();
			}
           
			
		}catch(Exception e){
			
			session.close();
			e.printStackTrace();
		}
           return id;
	}

	@Override
	public void deleteClient(Client client) {
		try{
		String sql="delete from Client where client_id="+client.getClient_id();
		Query q=sessionFactory.getCurrentSession().createQuery(sql);
		q.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public Client getClient(int clientId) {
		return (Client)sessionFactory.getCurrentSession().createQuery("from Client where client_id="+clientId).uniqueResult();

	}
	@Override
	public String getClientImageUrl(int clientId){
		Client client=(Client)sessionFactory.getCurrentSession().createQuery("from Client where client_id="+clientId).uniqueResult();
		return client.getClient_logo();
	}

	@Override
	public List<Client> listClients() {
		
		//return sessionFactory.getCurrentSession().createQuery("from Client").list();
		Query q=sessionFactory.getCurrentSession().createQuery("from Client");
		List<Client> list=q.list();
		return list;
	}
	@Override
	public List<ModuleSubModuleBean> listModules(){
		List<ModuleSubModuleBean> moduleList=new ArrayList();
		try{
		SQLQuery sq=sessionFactory.getCurrentSession().createSQLQuery("SELECT m.module_id,m.module_title,sm.submodule_id,sm.submodule_title,sm.url,m.module_img FROM module m,submodule sm WHERE m.module_id=sm.module_id ORDER BY m.display_order,sm.display_order");
		List<Object[]> result=sq.list(); 
		
		
		for(Object[] bean:result){
			ModuleSubModuleBean mb=new ModuleSubModuleBean();
			mb.setModule_id(Integer.parseInt(bean[0].toString()));
			mb.setModule_title(bean[1].toString());
			mb.setSubmodule_id(Integer.parseInt(bean[2].toString()));
			mb.setSubmodule_title(bean[3].toString());
			mb.setSubmodule_url(bean[4]!=null?bean[4].toString():"");
			mb.setModule_img(bean[5]!=null?bean[5].toString():"");
			moduleList.add(mb);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("moduleList  -->  "+moduleList);
		return moduleList;
		
	}
	public List getMappedModules(int clientId){
		System.out.println("clientId  -->  "+clientId);
		return sessionFactory.getCurrentSession().createSQLQuery("select module_id from client_module_map where client_id="+clientId).list();
	}

}
