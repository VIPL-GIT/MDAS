package com.rtdas.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.ClientDao;
import com.rtdas.common.dao.HierarchyTitleDao;
import com.rtdas.common.models.*;
import com.rtdas.common.service.HierarchyTitleService;

@Service("titleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HierarchyTitleServiceImpl implements HierarchyTitleService {

	@Autowired
	private HierarchyTitleDao titleDao;
	@Autowired
	private ClientDao clientDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addTitle(HierarchyTitle title) {
		titleDao.addTitle(title);

	}

	@Override
	public void deleteTitle(HierarchyTitle title) {
		titleDao.deleteTitle(title);

	}

	@Override
	public HierarchyTitle getTitles(int titleId) {
		return titleDao.getTitles(titleId);
	}

	@Override
	public List<HierarchyTitle> listTitles(int clientId) {
		return titleDao.listTitles(clientId);
	}
	
	@Override
	public List<HierarchyTitle> listDeviceLocatedTitles(int clientId){
		return titleDao.listDeviceLocatedTitles(clientId);
	}
	@Override
	public List<Client> listClients() {
		return clientDao.listClients();
	}

}
