package com.wxsoft.business.service;

import java.util.List;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderlog;

public interface IOrderlogForStoreService {
	
	public long findCount(Orderlog orderlog);
	public int getLastId(Orderlog orderlog);
	
	public List<Orderlog> findAll(PageHelper page, Orderlog orderlog);
	
	public String add(Orderlog orderlog);
	
	public String modify(Orderlog orderlog);
	
	public String delete(Orderlog orderlog);
}
