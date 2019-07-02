package com.wxsoft.business.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.business.dao.IDrugsForStoreDao;
import com.wxsoft.business.dao.IGoodsForStoreDao;
import com.wxsoft.business.dao.IInstorageForStoreDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Drugs;
import com.wxsoft.business.pojo.Goods;
import com.wxsoft.business.pojo.Instorage;
import com.wxsoft.business.service.IInstorageForStoreService;

@Service("instorageForStoreService")
public class InstorageForStroreServiceImpl implements IInstorageForStoreService  {
	@Resource
	private IInstorageForStoreDao dao;
	@Resource
	private IGoodsForStoreDao goodsDao;
	@Resource
	private IDrugsForStoreDao drugsDao;
	
	public int getLastId(Instorage instorage){
		Integer lastId = dao.getLastId(instorage);
		return lastId==null?0:lastId;
	}
	public long findCount(Instorage instorage) {
		return dao.findCount(instorage);
	}
	
	public List<Instorage> findAll(PageHelper page,Instorage instorage) {
        List<Instorage> r = dao.findAll(page,instorage);
		return r;
	}
	/**
	 * 入库操作
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String add(Instorage instorage) {
		this.dao.insert(instorage);
		Goods gs = new Goods();
		gs.setBarcode(instorage.getBarcode());
		gs.setDrugStoreShortName(instorage.getDrugStoreShortName());
		List<Goods> gL = goodsDao.findBy(gs);
		if(gL==null || gL.size()==0){
			// 插入商品
			insertGoodsStock(instorage);
		}else{
			//更新商品数量
			updateGoodsStock(gL, instorage);
		}
		return "操作成功!";
	}
	/**
	 * 更新商品数量
	 * @param gL
	 * @param instorage
	 * @return
	 */
	public String updateGoodsStock(List<Goods> gL,Instorage instorage){
		Goods oldGs = gL.get(0);
		Goods newGs = new Goods();
		
		newGs.setId(oldGs.getId());
		int inCount = Integer.valueOf(instorage.getInquantity());
		int currentC = Integer.valueOf(oldGs.getStock());
		String newC = String.valueOf(inCount+currentC);
		newGs.setStock(newC);
		//更新商品数量
		goodsDao.modify(newGs);
		return "";
	}
	/**
	 * 插入商品
	 * @param gL
	 * @param instorage
	 * @return
	 */
	public String insertGoodsStock(Instorage instorage){
		Goods nGs = new Goods();
		
		nGs.setBarcode(instorage.getBarcode());
		nGs.setGoodsname(instorage.getDrugsname());
		nGs.setCommonname(instorage.getCommonname());
		
		int inCount = Integer.valueOf(instorage.getInquantity());
		String newC = String.valueOf(inCount);
		nGs.setStock(newC);
		
		nGs.setGeneratenum(instorage.getGeneratenum());
		nGs.setGeneratedate(instorage.getGeneratedate());
		nGs.setValidityperiod(instorage.getValidityperiod());
		nGs.setShelflife(instorage.getShelflife());
		nGs.setOperator(instorage.getInperson());
		nGs.setOperatedate(new Date());
		
		Drugs drugs = new Drugs();
		drugs.setBarcode(instorage.getBarcode());
		drugs.setDrugStoreShortName(instorage.getDrugStoreShortName());
		List<Drugs> dL = drugsDao.findBy(drugs);
		if(dL==null || dL.size()==0){
			throw new RuntimeException("未找到入库的药品【"+instorage.getCommonname()+"】");
		}
		Drugs oldD = dL.get(0);
		nGs.setType1(oldD.getType1());
		nGs.setType2(oldD.getType2());
		nGs.setType3(oldD.getType3());
		nGs.setDosageform(oldD.getDosageform());
		nGs.setSpecifications(oldD.getSpecifications());

		nGs.setDrugStoreShortName(instorage.getDrugStoreShortName());
		//插入商品
		goodsDao.insert(nGs);
		return "";
	}
	
	public String modify(Instorage instorage) {
		this.dao.modify(instorage);
		return "操作成功!";
	}
	
	public String delete(Instorage instorage) {
		this.dao.delete(instorage);
		return "操作成功!";
	}
	
}
