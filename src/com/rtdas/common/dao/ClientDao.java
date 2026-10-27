package com.rtdas.common.dao;

import java.util.List;

import com.rtdas.common.beans.ModuleSubModuleBean;
import com.rtdas.common.models.Client;

public interface ClientDao {
	
	public int addClient(Client client,String moduleIds);
	public void deleteClient(Client client);
	public Client getClient(int clientId);
	public String getClientImageUrl(int clientId);
	public List<Client> listClients();
	public List<ModuleSubModuleBean> listModules();
	public List getMappedModules(int clientId);
}
