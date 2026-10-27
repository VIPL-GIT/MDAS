package com.rtdas.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.beans.ModuleSubModuleBean;
import com.rtdas.common.dao.ClientDao;
import com.rtdas.common.models.*;
import com.rtdas.common.service.ClientService;


@Service("clientService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientDao clientDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int addClient(Client client,String moduleIds) {
		return clientDao.addClient(client,moduleIds);

	}

	@Override
	public void deleteClient(Client client) {
		 clientDao.deleteClient(client);

	}

	@Override
	public Client getClient(int clientId) {
		return clientDao.getClient(clientId);

	}

	@Override
	public List<Client> listClients() {
		
		return clientDao.listClients();
	}
	@Override
	public List<ModuleSubModuleBean> listModules(){
		return clientDao.listModules();
	}
	@Override
	public List getMappedModules(int clientId){
		return clientDao.getMappedModules(clientId);
	}
	@Override
	public String getClientImageUrl(int clientId){
		return clientDao.getClientImageUrl(clientId);
	}

}
