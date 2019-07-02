package com.wxsoft.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.business.dao.IDrugsForStoreDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Drugs;
import com.wxsoft.business.service.IDrugsForStoreService;

@Service("drugsForStoreService")
public class DrugsForStoreServiceImpl implements IDrugsForStoreService  {
	@Resource
	private IDrugsForStoreDao dao;
	
	
	public long findCount(Drugs drugs) {
		return dao.findCount(drugs);
	}
	public int getLastId(Drugs drugs){
		Integer lastId = dao.getLastId(drugs);
		return lastId==null?0:lastId;
	}
	public List<Drugs> findAll(PageHelper page,Drugs drugs) {
        List<Drugs> r = dao.findAll(page,drugs);
		return r;
	}
	/**
	 * 根据条件查询药品
	 */
	public List<Drugs> findBy(Drugs drugs) {
        List<Drugs> r = dao.findBy(drugs);
		return r;
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String add(Drugs drugs) {
		Drugs d = new Drugs();
		d.setBarcode(drugs.getBarcode());
		d.setDrugStoreShortName(drugs.getDrugStoreShortName());
		List<Drugs> l = dao.findBy(d);
		if(l!=null && l.size()>0){
			Drugs OlDd = l.get(0);
			throw new RuntimeException("已存在条码【"+OlDd.getBarcode()+"】得药品【"+OlDd.getDrugsname()+"】");
		}
		this.dao.insert(drugs);
		return "操作成功!";
	}
	
	public String modify(Drugs drugs) {
		this.dao.modify(drugs);
		return "操作成功!";
	}
	
	public String delete(Drugs drugs) {
		this.dao.delete(drugs);
		return "操作成功!";
	}
	
}
