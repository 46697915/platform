package com.wxsoft.business.service;

import java.util.List;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Drugs;

public interface IDrugsForStoreService {
	
	public long findCount(Drugs drugs);
	
	public List<Drugs> findAll(PageHelper page, Drugs drugs);
	public List<Drugs> findBy(Drugs drugs);
	
	public String add(Drugs drugs);
	public int getLastId(Drugs drugs);
	public String modify(Drugs drugs);
	
	public String delete(Drugs drugs);
}
