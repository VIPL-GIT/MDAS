package com.rtdas.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.common.dao.HierarchyDao;
import com.rtdas.common.models.*;
import com.rtdas.common.service.HierarchyService;


@Service("hierarchyService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HierarchyServiceImpl implements HierarchyService {
 @Autowired
 private HierarchyDao hierarchyDao;
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addHierarchy(Hierarchy hierarchy) {
		hierarchyDao.addHierarchy(hierarchy);
	}

	@Override
	public void deleteHierarchy(Hierarchy hierarchy) {
		hierarchyDao.deleteHierarchy(hierarchy);;

	}

	@Override
	public Hierarchy getHierarchy(int id) {
		return hierarchyDao.getHierarchy(id);
	}
    @Override
    public List<Hierarchy> getHierarchyByType(int typeId){
    	return hierarchyDao.getHierarchyByType(typeId);
    }
    @Override
    public List<Hierarchy> getHierarchyByParentId(int parentId){
    	return hierarchyDao.getHierarchyByParentId(parentId);
    }
	@Override
	public List<Object[]> listHierarchy(int level,int typeId,int clientId) {
		return hierarchyDao.listHierarchy(level,typeId,clientId);
	}
	@Override
	public List<Object[]> listHierarchyById(int level,int id){
		return hierarchyDao.listHierarchyById(level, id);
	}
	
	@Override
	public List<Object[]> listAllChildsByParentId(int level,int titlesize,int parentId){
		return hierarchyDao.listAllChildsByParentId(level,titlesize,parentId);
	}
	@Override
	public Map<Integer,String> listHierarchyByClient(int clientId){
		return hierarchyDao.listHierarchyByClient(clientId);
	}
	

}
