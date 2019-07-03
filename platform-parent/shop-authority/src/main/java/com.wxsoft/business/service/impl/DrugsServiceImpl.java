package com.wxsoft.business.service.impl;

import com.wxsoft.annotation.DataFilter;
import com.wxsoft.business.dao.*;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.*;
import com.wxsoft.business.service.IDrugsDicService;
import com.wxsoft.business.service.IDrugsService;
import com.wxsoft.util.UserUtil;
import com.wxsoft.util.common.HanyuPinyinHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service("drugsService")
public class DrugsServiceImpl implements IDrugsService  {
    @Resource
    private IDrugsDao dao;

    @Resource
    private IDrugsDelDao delDao;

    @Autowired
    private IGoodsDao goodsDao;
    @Autowired
    private IInstorageDao instorageDao;
    @Autowired
    private IOrderdetailDao orderDetailDao;
    @Autowired
    private IYbjsmxxxDao ybjsmxxxDao;

    @Autowired
    private IDrugsDicService drugsDicService ;


    public long findCount(Drugs drugs) {
        return dao.findCount(drugs);
    }

    public List<Drugs> findAll(PageHelper page,Drugs drugs) {
        List<Drugs> r = dao.findAll(page,drugs);
        return r;
    }
    /**
     * 根据条件查询药品
     */
    @DataFilter(storeAlias ="storecode" )
    public List<Drugs> findBy(Drugs drugs) {
        List<Drugs> r = dao.findBy(drugs);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(Drugs drugs) throws IllegalAccessException, InvocationTargetException {

        drugs.setBarcode(drugs.getBarcode2());
        //新增前检查
        checkForAdd(drugs);

        if("".equals(drugs.getType1()) || drugs.getType1()==null){
            drugs.setType1("02");	//默认是药品
        }
        //转汉语拼音getFirstLettersUp
        if("".equals(drugs.getCommonnamespell())){
            if("".equals(drugs.getCommonname())){
                drugs.setCommonnamespell(HanyuPinyinHelper.getFullLettersLo(drugs.getDrugsname()));
            }else{
                drugs.setCommonnamespell(HanyuPinyinHelper.getFullLettersLo(drugs.getCommonname()));
            }
        }
        if("".equals(drugs.getCommonshotspell())){
            if("".equals(drugs.getCommonname())){
                drugs.setCommonshotspell(HanyuPinyinHelper.getFirstLettersUp(drugs.getDrugsname()));
            }else{
                drugs.setCommonshotspell(HanyuPinyinHelper.getFirstLettersUp(drugs.getCommonname()));
            }
        }

        //修改后保存到药品字典表
        DrugsDic drugsDic = new DrugsDic();
        drugsDic.setOperatedate(new Date());
        BeanUtils.copyProperties(drugsDic,drugs);
//		drugsDic.setBarcode(drugs.getBarcode2());
        drugsDicService.addOrModify(drugsDic);

        this.dao.insert(drugs);
        return "操作成功!";
    }
    /**
     * 保存前校验
     * @Description:TODO
     * @参数： @param drugs
     * @参数： @return
     * @return String
     * @throws
     * @author: chenliang
     * @time:2018-9-17 上午9:07:54
     */
    public String checkForAdd(Drugs drugs){
        Drugs drugsSearch = new Drugs();
        drugsSearch.setDrugStoreShortName(drugs.getDrugStoreShortName());
        drugsSearch.setBarcode(drugs.getBarcode());
        List<Drugs> l = dao.findBy(drugsSearch);
        if(l!=null && l.size()>0){
            Drugs OlDd = l.get(0);
            throw new RuntimeException("已存在条码【"+OlDd.getBarcode2()+"】的药品【"+OlDd.getDrugsname()+"】");
        }
        drugsSearch.setBarcode("");
        drugsSearch.setBarcode2(drugs.getBarcode2());
        l = dao.findBy(drugsSearch);
        if(l!=null && l.size()>0){
            Drugs OlDd = l.get(0);
            throw new RuntimeException("已存在条码【"+OlDd.getDrugscode()+"】的药品【"+OlDd.getDrugsname()+"】");
        }
        drugsSearch.setBarcode("");
        drugsSearch.setBarcode2("");
        drugsSearch.setDrugscode(drugs.getDrugscode());
        l = dao.findBy(drugsSearch);
        if(l!=null && l.size()>0){
            Drugs OlDd = l.get(0);
            //如果已存在内部编码，则修改原药品内部编码。因有些
            Drugs drugsUpdate = new Drugs();
            drugsUpdate.setDrugscode(OlDd.getDrugscode());
            drugsUpdate.setBarcode(OlDd.getBarcode());
            drugsUpdate.setId(OlDd.getId());
            dao.modify(drugsUpdate);
            throw new RuntimeException("已存在内部编码【"+OlDd.getDrugscode()+"】的药品【"+OlDd.getDrugsname()+"】");
        }

        return "";
    }
    /**
     * 修改药品信息
     *
     * 20180917因修改药品条形码需要，增加barcode2字段，
     * 原barcode做各个表之间关联使用，
     * 一般情况是与barcode2一致
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String modify(Drugs drugs) throws IllegalAccessException, InvocationTargetException {
        Drugs drugsSearch = new Drugs();
        drugsSearch.setDrugStoreShortName(drugs.getDrugStoreShortName());

        drugsSearch.setBarcode2(drugs.getBarcode2());
        List<Drugs> l = dao.findBy(drugsSearch);
        if(l!=null && l.size()>0){
            Drugs OlDd = l.get(0);
            if(drugs.getId()!=OlDd.getId()){
                throw new RuntimeException("已存在条码【"+OlDd.getDrugscode()+"】的药品【"+OlDd.getDrugsname()+"】");
            }
        }
        drugsSearch.setBarcode2("");
        drugsSearch.setDrugscode(drugs.getDrugscode());
        l = dao.findBy(drugsSearch);
        if(l!=null && l.size()>0 ){
            Drugs OlDd = l.get(0);
            if(drugs.getId()!=OlDd.getId()){
                throw new RuntimeException("已存在内部编码【"+OlDd.getDrugscode()+"】的药品【"+OlDd.getDrugsname()+"】");
            }
        }
        //转汉语拼音getFirstLettersUp
        if("".equals(drugs.getCommonnamespell())){
            if("".equals(drugs.getCommonname())){
                drugs.setCommonnamespell(HanyuPinyinHelper.getFullLettersLo(drugs.getDrugsname()));
            }else{
                drugs.setCommonnamespell(HanyuPinyinHelper.getFullLettersLo(drugs.getCommonname()));
            }
        }
        if("".equals(drugs.getCommonshotspell())){
            if("".equals(drugs.getCommonname())){
                drugs.setCommonshotspell(HanyuPinyinHelper.getFirstLettersUp(drugs.getDrugsname()));
            }else{
                drugs.setCommonshotspell(HanyuPinyinHelper.getFirstLettersUp(drugs.getCommonname()));
            }
        }
        drugs.setOperatedate(new Date());

        //修改后保存到药品字典表
        DrugsDic drugsDic = new DrugsDic();
        drugsDic.setOperatedate(new Date());
        BeanUtils.copyProperties(drugsDic,drugs);
        drugsDic.setBarcode(drugs.getBarcode2());
        drugsDicService.addOrModify(drugsDic);

        this.dao.modify(drugs);

        //修改关联表的名字、通用名字段
        this.modifyDrugsName(drugs);


        return "操作成功!";
    }
    /**
     * 修改相关表中 药品 的名字
     * @Description:TODO
     * @参数： @param drugs
     * @return void
     * @throws
     * @author: chenliang
     * @time:2019-3-12 下午9:33:24
     */
    public void modifyDrugsName(Drugs drugs){
        //修改商品表的名称
        Goods goods = new Goods();
        goods.setDrugStoreShortName(drugs.getDrugStoreShortName());
        goods.setBarcode(drugs.getBarcode2());
        goods.setGoodsname(drugs.getDrugsname());
        goods.setCommonname(drugs.getCommonname());
        goodsDao.modifyByBarcode(goods);

        //其他表 暂无

    }
    /**
     * 停用某个药
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String delete(Drugs drugs,HttpServletRequest request) {

        List<Drugs> l = dao.findBy(drugs);
        if(l!=null && l.size()>0){
            Drugs oldDrugs = l.get(0);

            //获得药品当前库存
            Goods goods = new Goods();
            goods.setDrugStoreShortName(drugs.getDrugStoreShortName());
            goods.setBarcode(oldDrugs.getBarcode());
            String stockStr = goodsDao.getStockBy(goods);
//			double stock = Double.valueOf(stockStr);
            double stock = 0;
            if(stockStr != null ){
                stock = Double.valueOf(stockStr);
            }


            //查找此药的入库信息
            Instorage inSearch = new Instorage();
            inSearch.setDrugStoreShortName(drugs.getDrugStoreShortName());
            inSearch.setBarcode(oldDrugs.getBarcode());
            List<InstorageSummary> sumInL = instorageDao.instorageSummaryByBarcode(inSearch);
            String inNumStr = "0";
            if(sumInL!=null && sumInL.size()>0){//如果盘点期间有入库，则更新数据，金额
                InstorageSummary inSum = (InstorageSummary) sumInL.get(0);
                inNumStr = inSum.getTotal();
            }
            double inNum = Double.valueOf(inNumStr);


            //销售信息
            Orderdetail odSearch = new Orderdetail();
            odSearch.setDrugStoreShortName(drugs.getDrugStoreShortName());
            odSearch.setBarcode(oldDrugs.getBarcode());
            List odSumL = orderDetailDao.salesSummaryByBarcode(odSearch);
            String saleNumStr = "0";
            if(odSumL!=null && odSumL.size()>0){//如果盘点期间有销售数据，则更新数据，金额
                OrderdetailSummary odSum = (OrderdetailSummary) odSumL.get(0);
                saleNumStr = odSum.getTotal();
            }
            double saleNum = Double.valueOf(saleNumStr);


            //更新盘点期间医保销售信息（根据 医院项目代码 计算）
            Ybjsmxxx ybmxSearch = new Ybjsmxxx();
            ybmxSearch.setDrugStoreShortName(drugs.getDrugStoreShortName());
            ybmxSearch.setYyxmbm(oldDrugs.getDrugscode());
            List ybmxSumL = ybjsmxxxDao.ybSalesSummaryByYyxmbm(ybmxSearch);
            String ybmxNumStr = "0";
            if(ybmxSumL!=null && ybmxSumL.size()>0){//如果盘点期间有销售医保，则更新数据，金额
                YbjsmxxxSummary ybmxSum = (YbjsmxxxSummary) ybmxSumL.get(0);
                ybmxNumStr = ybmxSum.getTotal();
            }
            double ybmxNum = Double.valueOf(ybmxNumStr);

            //更新盘点期间医保销售信息（根据 条形码 计算）
            Ybjsmxxx ybmxSearch2 = new Ybjsmxxx();
            ybmxSearch2.setDrugStoreShortName(drugs.getDrugStoreShortName());
            ybmxSearch2.setBarcode(oldDrugs.getBarcode());
            //只计算更新了库存的
            ybmxSearch2.setIsupdate("1");
            List ybmxSumL2 = ybjsmxxxDao.ybSalesSummaryByBarcode(ybmxSearch2);
            String ybmxNumStr2 = "0";
            if(ybmxSumL2!=null && ybmxSumL2.size()>0){//如果盘点期间有销售医保，则更新数据，金额
                YbjsmxxxSummary ybmxSum2 = (YbjsmxxxSummary) ybmxSumL2.get(0);
                ybmxNumStr2 = ybmxSum2.getTotal();
            }
            double ybmxNum2 = Double.valueOf(ybmxNumStr2);
            //如果 根据条形码 和内部编码 计算余额 相等 则 修改库存（临时用）
//			if(ybmxNum2 == ybmxNum && (inNum-saleNum-ybmxNum) != stock ){
//				Goods goodsDlt = new Goods();
//				goodsDlt.setDrugStoreShortName(drugs.getDrugStoreShortName());
//				goodsDlt.setBarcode(oldDrugs.getBarcode());
//				goodsDlt.setStock(ybmxNumStr2);
//				int modifyByBarcode = goodsDao.modifyByBarcode(goodsDlt);
//				if(modifyByBarcode!=1){
//					throw new RuntimeException("更新库存错误");
//				}
//			}

            //校验入库和销售，=当前库存，并且等于0；可以删除。
            if(inNum-saleNum-ybmxNum==stock && stock==0 ){
                DrugsDel drugsDel = new DrugsDel();
                drugsDel.setDrugStoreShortName(drugs.getDrugStoreShortName());
                drugsDel.setBarcode(oldDrugs.getBarcode());
                User user = UserUtil.getUser(request);
                drugsDel.setDeleter(user.getUsername());
                drugsDel.setDeletedate(new Date());
                this.delDao.insertDrugsDel(drugsDel);

                this.dao.delete(drugs);

                //必须 库存等于 0 删除库存
                goodsDao.deleteBy(goods);
            }else{
                throw new RuntimeException("校验入库("+inNum+")-销售("+(saleNum+ybmxNum)+"("+(saleNum+ybmxNum2)+"根据条形码计算（可能没有更新库存）)) 不等于 当前库存("+stock+") 或 当前库存("+stock+") 不等于 0，不能停用；请做入库操作。");
            }
        }

        return "操作成功!";
    }
    /**
     * 停用某个药品
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String stop(Drugs drugs,HttpServletRequest request) {

        List<Drugs> l = dao.findBy(drugs);
        if(l!=null && l.size()>0){
            Drugs oldD = l.get(0);
//			throw new RuntimeException("已存在内部编码【"+OlDd.getDrugscode()+"】的药品【"+OlDd.getDrugsname()+"】");
            DrugsDel drugsDel = new DrugsDel();
            drugsDel.setDrugStoreShortName(drugs.getDrugStoreShortName());
            drugsDel.setBarcode(oldD.getBarcode());
            User user = UserUtil.getUser(request);
            drugsDel.setDeleter(user.getUsername());
            drugsDel.setDeletedate(new Date());
            this.delDao.insertDrugsDel(drugsDel);
        }
        this.dao.delete(drugs);

        return "操作成功!";
    }










    /**
     * 更新某个药品 的 库存  后台使用
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String updateStore(Drugs drugs,HttpServletRequest request) {

        List<Drugs> l = dao.findBy(drugs);
        if(l!=null && l.size()>0){
            Drugs oldDrugs = l.get(0);

            //获得药品当前库存
            Goods goods = new Goods();
            goods.setDrugStoreShortName(drugs.getDrugStoreShortName());
            goods.setBarcode(oldDrugs.getBarcode());
            String stockStr = goodsDao.getStockBy(goods);
//			double stock = Double.valueOf(stockStr);
            double stock = 0;
            if(stockStr != null ){
                stock = Double.valueOf(stockStr);
            }


            //查找此药的入库信息
            Instorage inSearch = new Instorage();
            inSearch.setDrugStoreShortName(drugs.getDrugStoreShortName());
            inSearch.setBarcode(oldDrugs.getBarcode());
            List<InstorageSummary> sumInL = instorageDao.instorageSummaryByBarcode(inSearch);
            String inNumStr = "0";
            if(sumInL!=null && sumInL.size()>0){//如果盘点期间有入库，则更新数据，金额
                InstorageSummary inSum = (InstorageSummary) sumInL.get(0);
                inNumStr = inSum.getTotal();
            }
            double inNum = Double.valueOf(inNumStr);


            //销售信息
            Orderdetail odSearch = new Orderdetail();
            odSearch.setDrugStoreShortName(drugs.getDrugStoreShortName());
            odSearch.setBarcode(oldDrugs.getBarcode());
            List odSumL = orderDetailDao.salesSummaryByBarcode(odSearch);
            String saleNumStr = "0";
            if(odSumL!=null && odSumL.size()>0){//如果盘点期间有销售数据，则更新数据，金额
                OrderdetailSummary odSum = (OrderdetailSummary) odSumL.get(0);
                saleNumStr = odSum.getTotal();
            }
            double saleNum = Double.valueOf(saleNumStr);


            //更新盘点期间医保销售信息（根据 医院项目代码 计算）
            Ybjsmxxx ybmxSearch = new Ybjsmxxx();
            ybmxSearch.setDrugStoreShortName(drugs.getDrugStoreShortName());
            ybmxSearch.setYyxmbm(oldDrugs.getDrugscode());
            List ybmxSumL = ybjsmxxxDao.ybSalesSummaryByYyxmbm(ybmxSearch);
            String ybmxNumStr = "0";
            if(ybmxSumL!=null && ybmxSumL.size()>0){//如果盘点期间有销售医保，则更新数据，金额
                YbjsmxxxSummary ybmxSum = (YbjsmxxxSummary) ybmxSumL.get(0);
                ybmxNumStr = ybmxSum.getTotal();
            }
            double ybmxNum = Double.valueOf(ybmxNumStr);

            //更新盘点期间医保销售信息（根据 条形码 计算）
            Ybjsmxxx ybmxSearch2 = new Ybjsmxxx();
            ybmxSearch2.setDrugStoreShortName(drugs.getDrugStoreShortName());
            ybmxSearch2.setBarcode(oldDrugs.getBarcode());
            //只计算更新了库存的
            ybmxSearch2.setIsupdate("1");
            List ybmxSumL2 = ybjsmxxxDao.ybSalesSummaryByBarcode(ybmxSearch2);
            String ybmxNumStr2 = "0";
            if(ybmxSumL2!=null && ybmxSumL2.size()>0){//如果盘点期间有销售医保，则更新数据，金额
                YbjsmxxxSummary ybmxSum2 = (YbjsmxxxSummary) ybmxSumL2.get(0);
                ybmxNumStr2 = ybmxSum2.getTotal();
            }
            double ybmxNum2 = Double.valueOf(ybmxNumStr2);
            //如果 根据条形码   计算余额 相等 则 修改库存（正式用，根据条形码计算销售数量更新，2019年用）
//			if( (inNum-saleNum-ybmxNum2) != stock ){
//				Goods goodsDlt = new Goods();
//				goodsDlt.setDrugStoreShortName(drugs.getDrugStoreShortName());
//				goodsDlt.setBarcode(oldDrugs.getBarcode());
//				goodsDlt.setStock(String.valueOf(inNum-saleNum-ybmxNum2));
//				int modifyByBarcode = goodsDao.modifyByBarcode(goodsDlt);
//				if(modifyByBarcode!=1){
//					throw new RuntimeException("更新库存错误");
//				}
//			}
            //如果 根据条形码 和内部编码 计算余额 相等 则 修改库存（临时用）
            if(ybmxNum2 == ybmxNum && (inNum-saleNum-ybmxNum) != stock ){
                Goods goodsDlt = new Goods();
                goodsDlt.setDrugStoreShortName(drugs.getDrugStoreShortName());
                goodsDlt.setBarcode(oldDrugs.getBarcode());
                goodsDlt.setStock(String.valueOf(inNum-saleNum-ybmxNum));
                int modifyByBarcode = goodsDao.modifyByBarcode(goodsDlt);
                if(modifyByBarcode!=1){
                    throw new RuntimeException("更新库存错误");
                }
            }

        }

        return "操作成功!";
    }
}
