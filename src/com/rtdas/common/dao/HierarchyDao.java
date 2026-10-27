package com.rtdas.common.dao;

import java.util.*;

import com.rtdas.common.models.*;


public interface HierarchyDao {

	public void addHierarchy(Hierarchy hierarchy);
	public void deleteHierarchy(Hierarchy hierarchy);
	public Hierarchy getHierarchy(int id);
	public Map<Integer,String> listHierarchyByClient(int clientId);
	public List<Hierarchy> getHierarchyByType(int typeId);
	public List<Hierarchy> getHierarchyByParentId(int parentId);
	public List<Object[]> listHierarchy(int level,int typeId,int clientId);
	public List<Object[]> listHierarchyById(int level,int id);
	public List<Object[]> listAllChildsByParentId(int level,int titlesize,int parentId);
}
