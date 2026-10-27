package com.rtdas.common.service;
import java.util.List;

import com.rtdas.common.beans.ModuleSubModuleBean;
import com.rtdas.common.models.*;
public interface ClientService {
	
	public int addClient(Client client,String moduleIds);
	public void deleteClient(Client client);
	public Client getClient(int clientId);
	public String getClientImageUrl(int clientId);
	public List<Client> listClients();
	public List<ModuleSubModuleBean> listModules();
	public List getMappedModules(int clientId);

}
