package com.wxsoft.business.service;

import java.util.List;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderdetail;

public interface IOrderdetailForStoreService {
	
	public long findCount(Orderdetail orderdetail);
	public int getLastId(Orderdetail orderdetail);
	
	public List<Orderdetail> findAll(PageHelper page, Orderdetail orderdetail);
	
	public String add(Orderdetail orderdetail);
	
	public String modify(Orderdetail orderdetail);
	
	public String delete(Orderdetail orderdetail);
	
	public long findMoneyCount(Orderdetail orderdetail);
}
