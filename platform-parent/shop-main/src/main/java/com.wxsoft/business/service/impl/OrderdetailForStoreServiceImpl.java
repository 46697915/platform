package com.wxsoft.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.business.dao.IOrderdetailForStoreDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderdetail;
import com.wxsoft.business.service.IOrderdetailForStoreService;

@Service("orderdetailForStoreService")
public class OrderdetailForStoreServiceImpl implements IOrderdetailForStoreService  {
	@Resource
	private IOrderdetailForStoreDao dao;
	
	public int getLastId(Orderdetail orderdetail){
		Integer lastId = dao.getLastId(orderdetail);
		return lastId==null?0:lastId;
	}
	public long findCount(Orderdetail orderdetail) {
		return dao.findCount(orderdetail);
	}
	
	public List<Orderdetail> findAll(PageHelper page,Orderdetail orderdetail) {
        List<Orderdetail> r = dao.findAll(page,orderdetail);
		return r;
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String add(Orderdetail orderdetail) {
		this.dao.insert(orderdetail);
		return "操作成功!";
	}
	
	public String modify(Orderdetail orderdetail) {
		this.dao.modify(orderdetail);
		return "操作成功!";
	}
	
	public String delete(Orderdetail orderdetail) {
		this.dao.delete(orderdetail);
		return "操作成功!";
	}
	
	public long findMoneyCount(Orderdetail orderdetail){
		return this.dao.findMoneyCount(orderdetail);
	}
	
}
