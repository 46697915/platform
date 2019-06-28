package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.*;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.*;
import com.wxsoft.business.service.IStoreCheckService;
import com.wxsoft.util.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("storeCheckService")
public class StoreCheckServiceImpl implements IStoreCheckService  {
    @Resource
    private IStoreCheckDao dao;

    @Autowired
    private IDrugsDao drugsDao;
    @Autowired
    private IGoodsDao goodsDao;
    @Autowired
    private IInstorageDao instorageDao;
    @Autowired
    private IOrderdetailDao orderDetailDao;
    @Autowired
    private IYbjsmxxxDao ybjsmxxxDao;

    public long findCount(StoreCheck storeCheck) {
        return dao.findCount(storeCheck);
    }

    public List<StoreCheck> findAll(PageHelper page,StoreCheck storeCheck) {
        List<StoreCheck> r = dao.findAll(page,storeCheck);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(StoreCheck storeCheck) {
        this.dao.insert(storeCheck);
        return "操作成功!";
    }

    public String modify(StoreCheck storeCheck) {
        this.dao.modify(storeCheck);
        return "操作成功!";
    }

    public String delete(StoreCheck storeCheck) {
        this.dao.delete(storeCheck);
        return "操作成功!";
    }

    /**
     * 更加当前盘点日期，删除盘点记录
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String deleteSCByDate(StoreCheck storeCheck) {
        //更新比删除盘点日期小的上个盘点日期的盘点记录，为1（当前盘点状态）
        this.dao.deleteSCByDate(storeCheck);
        this.dao.updateLastCheck(storeCheck);
        return "操作成功!";
    }

    /**
     * 定期盘点库存
     * @throws ParseException
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String check(StoreCheck storeCheck) throws ParseException {

        String maxCheckdate = dao.findMaxCheckdate(storeCheck);
        if(maxCheckdate == null){
            maxCheckdate = "" ;
        }
        if(maxCheckdate.compareTo(storeCheck.getCrrcheckdate())>0){
            return "最大盘点日期："+maxCheckdate+"；请在输入大于此日期的盘点日期。";
        }

        //查询商品信息（goods表）
        Goods goods = new Goods();
        goods.setDrugStoreShortName(storeCheck.getDrugStoreShortName());
        goods.setType1("02");	//只判断药品类
        //goods.setBarcode("86903373000953");	//测试用
        PageHelper page = new PageHelper();
        List l = goodsDao.findAll(page, goods);

        //循环goods信息插入storecheck表
        for(int i=0; i<l.size(); i++){
            Goods oldG = (Goods) l.get(i);
            StoreCheck scNew = new StoreCheck();
            scNew.setBarcode(oldG.getBarcode());
            //从药品表中取内部编码
            Drugs drugs = new Drugs();
            drugs.setBarcode(oldG.getBarcode());
            drugs.setDrugStoreShortName(storeCheck.getDrugStoreShortName());
            List<Drugs> drugsL = drugsDao.findBy(drugs);
            if(drugsL==null || drugsL.size()<=0){
                throw new RuntimeException("未找到药品："+oldG.getCommonname());
            }
            Drugs drugsNes =  drugsL.get(0);
            scNew.setDrugscode(drugsNes.getDrugscode());	//从药品表中取得内部编码

            scNew.setBxdygx(oldG.getBxdygx());
            scNew.setDrugsname(oldG.getGoodsname());
            scNew.setCommonname(oldG.getCommonname());
            scNew.setCurrstore(oldG.getStock());	//当前库存
            double cStock = Double.valueOf(oldG.getStock());
            double cPrice = Double.valueOf(oldG.getPrice()==null?"0":oldG.getPrice());
            scNew.setCurramount(cStock*cPrice+"");	//当前金额
            scNew.setCrrcheckdate(storeCheck.getCrrcheckdate());
            scNew.setCheckdate(DateUtil.currentDatetime());
            scNew.setChecker(storeCheck.getChecker());
            //查询上次盘点数据
            StoreCheck scSeach = new StoreCheck();
            scSeach.setBarcode(oldG.getBarcode());
//			scSeach.setBxdygx(oldG.getBxdygx());
//			scSeach.setDrugscode(oldG.getDrugscode());
            scSeach.setIslastcheck("1");//是否最后一次盘点
            scSeach.setDrugStoreShortName(storeCheck.getDrugStoreShortName());
            List scOldL = dao.findBy(scSeach);
            if(scOldL!=null && scOldL.size()>0){//如果存在上次盘点，则取出上次盘点相关数据
                StoreCheck scOld = (StoreCheck) scOldL.get(0);
                //如果某个药品的上次盘点日期，等于本次盘点日期，则退出循环
                SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                Date currCheckDate = sdf.parse(storeCheck.getCrrcheckdate()+" 00:00:00" );
                Date lastCheckDate = sdf.parse(scOld.getCrrcheckdate());
                if(!currCheckDate.after(lastCheckDate)){
                    continue;
                }
                scNew.setInitstore(scOld.getNewstore());
                scNew.setInitamount(scOld.getNewamount());
                scNew.setLastcheckdate(scOld.getCrrcheckdate());
                scNew.setLastcheckid(String.valueOf(scOld.getId()));
                //更新上次盘点数据
                StoreCheck scModify = new StoreCheck();
                scModify.setId(scOld.getId());
                scModify.setIslastcheck("0");//是否最后一次盘点
                scModify.setDrugStoreShortName(storeCheck.getDrugStoreShortName());
                dao.modify(scModify);
            }else{
                scNew.setInitstore("0");
                scNew.setInitamount("0");
                scNew.setLastcheckdate(null);
                scNew.setLastcheckid(null);
            }
            //更新盘点期间入库信息
            Instorage inSearch = new Instorage();
            inSearch.setBarcode(scNew.getBarcode());
//			System.out.println("getLastcheckdate:"+scNew.getLastcheckdate());
//			System.out.println("getBarcode:"+scNew.getBarcode());
            inSearch.setIndate_begin(getNextDate(scNew.getLastcheckdate()));
            inSearch.setIndate_end(scNew.getCrrcheckdate());
            inSearch.setDrugStoreShortName(storeCheck.getDrugStoreShortName());
            List sumInL = instorageDao.instorageSummaryByBarcode(inSearch);
            if(sumInL!=null && sumInL.size()>0){//如果盘点期间有入库，则更新数据，金额
                InstorageSummary inSum = (InstorageSummary) sumInL.get(0);
                scNew.setInstore(inSum.getTotal());
                scNew.setInstoreamount(inSum.getAmount()==null?"0":inSum.getAmount());
            }else{
                scNew.setInstore("0");
                scNew.setInstoreamount("0");
            }
            //更新盘点期间销售信息
            Orderdetail odSearch = new Orderdetail();
            odSearch.setBarcode(scNew.getBarcode());
            odSearch.setCreatedate_begin(getNextDate(scNew.getLastcheckdate()));
            odSearch.setCreatedate_end(scNew.getCrrcheckdate());
            odSearch.setDrugStoreShortName(storeCheck.getDrugStoreShortName());
            List odSumL = orderDetailDao.salesSummaryByBarcode(odSearch);
            if(odSumL!=null && odSumL.size()>0){//如果盘点期间有销售数据，则更新数据，金额
                OrderdetailSummary odSum = (OrderdetailSummary) odSumL.get(0);
                scNew.setSalecount(odSum.getTotal());
                scNew.setSaleamount(odSum.getAmount());
            }else{
                scNew.setSalecount("0");
                scNew.setSaleamount("0");
            }

            //更新盘点期间医保销售信息
//			if("86978997001249".equals(scNew.getDrugscode())){
//				System.out.println(scNew.getDrugscode());
//			}
            Ybjsmxxx ybmxSearch = new Ybjsmxxx();
            //20190408修改：按条形码查询医保销售数据，因更新库存时，已经将条形码更新进去
//			ybmxSearch.setYyxmbm(scNew.getDrugscode());
            ybmxSearch.setBarcode(scNew.getBarcode());
            ybmxSearch.setHjrq_begin(getNextDate(scNew.getLastcheckdate()));
            ybmxSearch.setHjrq_end(scNew.getCrrcheckdate());
            ybmxSearch.setDrugStoreShortName(storeCheck.getDrugStoreShortName());
            List ybmxSumL = ybjsmxxxDao.ybSalesSummaryByBarcode(ybmxSearch);
            if(ybmxSumL!=null && ybmxSumL.size()>0){//如果盘点期间有销售医保，则更新数据，金额
                YbjsmxxxSummary ybmxSum = (YbjsmxxxSummary) ybmxSumL.get(0);
                double oldSaleC = Double.valueOf(scNew.getSalecount());
                double oldSaleA = Double.valueOf(scNew.getSaleamount());
                double newSaleC = Double.valueOf(ybmxSum.getTotal());
                double newSaleA = Double.valueOf(ybmxSum.getAmount());
                scNew.setSalecount(oldSaleC+newSaleC+"");
                scNew.setSaleamount(oldSaleA+newSaleA+"");
            }
            //计算最新库存、和总金额
            double initStock = Double.valueOf(scNew.getInitstore());
            double inStock = Double.valueOf(scNew.getInstore());
            double saleCount = Double.valueOf(scNew.getSalecount());
            double initAmount = Double.valueOf(scNew.getInitamount());
            double inStoreAmount = Double.valueOf(scNew.getInstoreamount());
            double saleAmount = Double.valueOf(scNew.getSaleamount());
            scNew.setNewstore(initStock+inStock-saleCount+"");	//最新库存
            scNew.setNewamount(initAmount+inStoreAmount-saleAmount+"");	//最新金额

            //插入新的盘点数据
            scNew.setIslastcheck("1");//是否最后一次盘点
            scNew.setDrugStoreShortName(storeCheck.getDrugStoreShortName());
            dao.insert(scNew);
        }

        return "盘点成功。";
    }
    /**
     * 获取日期下一天，如果是 空 ，则返回null
     * @param date
     * @return
     */
    public String getNextDate(String date){
        if(date==null || "".equals(date)){
            return date ;
        }else{
            return DateUtil.getNextDay(date);
        }
    }
}
