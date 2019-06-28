package com.wxsoft.business.service;

import java.util.List;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Instorage;

public interface IInstorageForStoreService {
	
	public long findCount(Instorage instorage);
	public int getLastId(Instorage instorage);
	public List<Instorage> findAll(PageHelper page, Instorage instorage);
	
	public String add(Instorage instorage);
	
	public String modify(Instorage instorage);
	
	public String delete(Instorage instorage);
}
