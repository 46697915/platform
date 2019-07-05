package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IDrugsDao;
import com.wxsoft.business.dao.IGoodsDao;
import com.wxsoft.business.dao.IInstorageDao;
import com.wxsoft.business.dao.IStoreCheckDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.*;
import com.wxsoft.business.service.IInstorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("instorageService")
public class InstorageServiceImpl implements IInstorageService  {
    @Resource
    private IInstorageDao dao;
    @Resource
    private IGoodsDao goodsDao;
    @Resource
    private IDrugsDao drugsDao;
    @Resource
    private IStoreCheckDao storeCheckDao;


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

        StoreCheck sc = new StoreCheck();
        sc.setDrugStoreShortName(instorage.getDrugStoreShortName());
        String maxCheckdate = storeCheckDao.findMaxCheckdate(sc);
        if(maxCheckdate == null){
            maxCheckdate = "" ;
        }
        if(maxCheckdate.compareTo(instorage.getIndate())>0){
            return "新增失败！最大盘点日期："+maxCheckdate+"；请在输入大于此日期的入库日期，或删除最大日期的盘点记录。";
        }

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
        double inCount = Double.parseDouble(instorage.getInquantity());
        double currentC = Double.parseDouble(oldGs.getStock());
        String newC = String.valueOf(inCount+currentC);
        newGs.setStock(newC);
        newGs.setDrugStoreShortName(instorage.getDrugStoreShortName());
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
        nGs.setUnits(oldD.getUnits());
        nGs.setDrugscode(oldD.getDrugscode());
        nGs.setCommonnamespell(oldD.getCommonnamespell());
        nGs.setCommonshotspell(oldD.getCommonshotspell());
        nGs.setPrice("0");  //初始化售价 0

        //插入商品
        nGs.setDrugStoreShortName(instorage.getDrugStoreShortName());
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

    /**
     * 入库汇总 信息
     */
    public List<InstorageSummary> instorageSummaryAll(PageHelper page,
                                                      Instorage instorage) {
        List<InstorageSummary> r = dao.instorageSummaryAll(page,instorage);
        return r;
    }

    /**
     * 入库汇总 药品数
     */
    public long instorageSummaryCount(Instorage instorage) {
        return dao.instorageSummaryCount(instorage);
    }

}
