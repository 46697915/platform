package com.wxsoft.business.service;

import java.util.List;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Goods;

public interface IGoodsForStoreService {
	
	public long findCount(Goods goods);
	public int getLastId(Goods goods);
	public List<Goods> findAll(PageHelper page, Goods goods);
	public List<Goods> findBy(Goods goods);
	
	public String add(Goods goods);
	
	public String modify(Goods goods);
	
	public String delete(Goods goods);
}
