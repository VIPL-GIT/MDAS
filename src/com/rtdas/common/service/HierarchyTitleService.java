package com.rtdas.common.service;

import java.util.List;

import com.rtdas.common.models.*;

public interface HierarchyTitleService {

	public void addTitle(HierarchyTitle title);
	public void deleteTitle(HierarchyTitle title);
	public HierarchyTitle getTitles(int titleId);
	public List<HierarchyTitle> listTitles(int clientId);
	public List<HierarchyTitle> listDeviceLocatedTitles(int clientId);
	public List<Client> listClients();
	
	
}
