package com.rtdas.common.dao;

import java.util.List;

import com.rtdas.common.models.HierarchyTitle;

public interface HierarchyTitleDao {

	public void addTitle(HierarchyTitle title);
	public void deleteTitle(HierarchyTitle title);
	public HierarchyTitle getTitles(int titleId);
	public List<HierarchyTitle> listTitles(int clientId);
	public List<HierarchyTitle> listDeviceLocatedTitles(int clientId);
}
