package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.*;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.*;
import com.wxsoft.business.service.ITransferService;
import com.wxsoft.util.UserUtil;
import com.wxsoft.util.common.CodeUtil;
import com.wxsoft.util.common.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

@Service("transferService")
public class TransferServiceImpl implements ITransferService {
    @Resource
    private ITransferDao dao;

    @Resource
    private IInstorageDao istDao;
    @Resource
    private IInstorageJzDao istJzDao;

    @Resource
    private IStoreCheckDao stCkDao;
    @Resource
    private IStorecheckJzDao stCkJzDao;

    @Resource
    private IYbjsmxxxDao ybjsmxDao;
    @Resource
    private IYbjsmxxxJzDao ybjsmxJzDao;

    @Resource
    private IYbjszxxDao ybjszDao;
    @Resource
    private IYbjszxxJzDao ybjszJzDao;

    @Resource
    private IOrderdetailDao odDtlDao;
    @Resource
    private IOrderdetailJzDao odDtlJzDao;

    @Resource
    private IOrderlogDao odLogDao;
    @Resource
    private IOrderlogJzDao odLogJzDao;

    @Resource
    private IPaylogDao pLogDao;
    @Resource
    private IPaylogJzDao pLogJzDao;

    @Resource
    private IOrderDao odDao;
    @Resource
    private IOrderJzDao odJzDao;
    @Resource
    private IDrugsDao drugsDao;
    @Resource
    private IDrugsDelDao drugsDelDao;
    @Resource
    private IGoodsDao goodsDao;

    public long findCount(Transfer transfer) {
        return dao.findCount(transfer);
    }

    public List<Transfer> findAll(PageHelper page, Transfer transfer) {
        List<Transfer> r = dao.findAll(page, transfer);
        return r;
    }
    /**
     * 批量结转-为了处理旧数据，已经删除了的药品
     *
     * @Description:TODO
     * @参数： @return
     * @return String
     * @throws
     * @author: chenliang
     * @time:2018-10-28 下午7:00:44
     */
    public String batchAddForDel(Transfer transfer) {
        // 查询所有药品
        DrugsDel drugs = new DrugsDel();
        drugs.setDrugStoreShortName(transfer.getDrugStoreShortName());
        drugs.setType1("02"); // 药品类型 01诊疗类 02药品类
        List<DrugsDel> drugsL = drugsDelDao.findBy(drugs);

        for (DrugsDel d : drugsL) {
            // 盘点是否已经结转

            Transfer tQ = new Transfer();
            tQ.setBarcode(d.getBarcode());
            tQ.setJzrq(transfer.getJzrq());
            tQ.setDrugStoreShortName(transfer.getDrugStoreShortName());
            // 结转 前 判断
            long findCount = dao.findCount(tQ);
            if (findCount > 0) {
                continue;
            }
            // 如果没有结转则进行结转
            transfer.setBarcode(d.getBarcode());
            add(transfer);
        }

        return "操作成功";
    }

    /**
     * 批量结转
     *
     * @Description:TODO
     * @参数： @return
     * @return String
     * @throws
     * @author: chenliang
     * @time:2018-10-28 下午7:00:44
     */
    public String batchAdd(Transfer transfer) {
        // 查询所有药品
        Drugs drugs = new Drugs();
        drugs.setDrugStoreShortName(transfer.getDrugStoreShortName());
        drugs.setType1("02"); // 药品类型 01诊疗类 02药品类
        List<Drugs> drugsL = drugsDao.findBy(drugs);

        for (Drugs d : drugsL) {
            // 盘点是否已经结转

            Transfer tQ = new Transfer();
            tQ.setBarcode(d.getBarcode());
            tQ.setJzrq(transfer.getJzrq());
            tQ.setDrugStoreShortName(transfer.getDrugStoreShortName());
            // 结转 前 判断
            long findCount = dao.findCount(tQ);
            if (findCount > 0) {
                continue;
            }
            // 如果没有结转则进行结转
            transfer.setBarcode(d.getBarcode());
            add(transfer);
        }

        return "操作成功";
    }

    /**
     * 开始结转
     */
    // @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Transactional
    public String add(Transfer transfer) {

        String tsfCode = CodeUtil.getCode("jz");
        transfer.setId(tsfCode);

        Transfer tQ = new Transfer();
        tQ.setBarcode(transfer.getBarcode());
        tQ.setJzrq(transfer.getJzrq());
        tQ.setDrugStoreShortName(transfer.getDrugStoreShortName());
        // 结转 前 判断
        long findCount = dao.findCount(tQ);
        if (findCount > 0) {
            return "此药品已经结转。";
        }

        // 根据 结转日期 获取最大的盘点记录
        StoreCheck sc = new StoreCheck();
        sc.setDrugStoreShortName(transfer.getDrugStoreShortName());
        sc.setBarcode(transfer.getBarcode());
        sc.setCrrcheckdate_end(transfer.getJzrq());
        List<StoreCheck> findMaxCheckdateByJZ = stCkDao
                .findMaxCheckdateByJZ(sc);
        if (findMaxCheckdateByJZ != null && findMaxCheckdateByJZ.size() == 1) {
            StoreCheck storeCheck = findMaxCheckdateByJZ.get(0);
            // 取最后的盘点结转库存
            transfer.setStock(storeCheck.getNewstore());
        } else {
            //如果结转库存为 null 则以goods表库存为准，
            Goods goods = new Goods();
            goods.setBarcode(transfer.getBarcode());
            goods.setDrugStoreShortName(transfer.getDrugStoreShortName());
            String stockBy = goodsDao.getStockBy(goods);
            if(stockBy != null && !"".equals(stockBy)){
                transfer.setStock(stockBy);
            }else{
                //如果 goods表中库存也是空，则库存设置为0（这种情况极少，除非药品不被使用）
                transfer.setStock("0");
            }

//			throw new RuntimeException("取最后结转记录错误！");
        }

        // 从结转之前保存库存信息（包含结转日期）
        jzCC(transfer);

        // 从结转之前保存盘点信息（包含结转日期）
        jzPD(transfer);

        // 从结转之前保存医保计算明细信息及主信息（包含结转日期）
        jzYBJSMX(transfer);

        // 从结转之前保存 销售 信息 ： 订单详细信息，订单日志信息，订单付款信息，订单信息（包含结转日期）
        jzXS(transfer);

        this.dao.insert(transfer);
        return "操作成功!";
    }

    /**
     * 结转库存信息
     *
     * @Description:TODO
     * @参数： @param transfer
     * @参数： @return
     * @return String
     * @throws
     * @author: chenliang
     * @time:2018-6-8 下午9:38:35
     */
    public String jzCC(Transfer transfer) {

        // 查询出 结转日期之前的数据
        Instorage ist = new Instorage();
        ist.setDrugStoreShortName(transfer.getDrugStoreShortName());
        ist.setBarcode_search(transfer.getBarcode());
        ist.setIndate_end(transfer.getJzrq());

        // 结转 后 初始入库 对象
        Instorage istNew = new Instorage();
        List<Instorage> r = istDao.findAll(new PageHelper(), ist);
        // 循环插入到结转记录表
        for (Instorage istOld : r) {
            InstorageJz instorageJz = new InstorageJz();
            BeanUtils.copyProperties(istOld, instorageJz);

            instorageJz.setTransfer_id(transfer.getId());
            instorageJz.setDrugStoreShortName(transfer.getDrugStoreShortName());

            int insert = istJzDao.insert(instorageJz);
            // 删除原来记录
            istOld.setDrugStoreShortName(transfer.getDrugStoreShortName());
            int delete = istDao.delete(istOld);
            if (insert != delete) {
                new RuntimeException("结转 库存 异常");
            }
            istNew = istOld;
        }
        //如果上年库存不为0则重新插入库存
        if(!"0.00".equals(transfer.getStock())
                && !"0".equals(transfer.getStock())
                && istNew.getBarcode()!=null )
        {
            // 将结转后剩余库存 结转 到 结转日期+1 天 的 初始入库中
            istNew.setDrugStoreShortName(transfer.getDrugStoreShortName());
            istNew.setInquantity(transfer.getStock());
            istNew.setIntype("000"); // 初始入库
            istNew.setSigntype("zcrk"); // 标记是否找票
            // 入库日期
            String nextDay = DateUtil.getNextDay(transfer.getJzrq());
            istNew.setIndate(nextDay);
            // 入库人 、 入库记录日期
            User user = UserUtil.getUser();
            istNew.setInperson(user.getUsername());
            istNew.setLoggingdate(DateUtil.currentDatetime());
            istDao.insert(istNew);
        }

        return "";
    }

    /**
     * 结转盘点信息
     *
     * @Description:TODO
     * @参数： @param transfer
     * @参数： @return
     * @return String
     * @throws
     * @author: chenliang
     * @time:2018-6-8 下午10:04:27
     */
    public String jzPD(Transfer transfer) {

        StoreCheck sc = new StoreCheck();
        sc.setDrugStoreShortName(transfer.getDrugStoreShortName());
        sc.setBarcode(transfer.getBarcode());
        sc.setCrrcheckdate_end(transfer.getJzrq());
        List<StoreCheck> findBy = stCkDao.findBy(sc);
        // 循环插入到结转记录表
        for (StoreCheck scOld : findBy) {
            StorecheckJz jz = new StorecheckJz();
            BeanUtils.copyProperties(scOld, jz);

            jz.setTransfer_id(transfer.getId());
            jz.setDrugStoreShortName(transfer.getDrugStoreShortName());

            int insert = stCkJzDao.insert(jz);
            // 删除原来记录
            scOld.setDrugStoreShortName(transfer.getDrugStoreShortName());
            int delete = stCkDao.delete(scOld);
            if (insert != delete) {
                new RuntimeException("结转 盘点信息 异常");
            }
        }

        return "";
    }

    /**
     * 结转医保结算明细
     *
     * @Description:TODO
     * @参数： @param transfer
     * @参数： @return
     * @return String
     * @throws
     * @author: chenliang
     * @time:2018-6-8 下午10:27:54
     */
    public String jzYBJSMX(Transfer transfer) {

        Ybjsmxxx ybmx = new Ybjsmxxx();
        ybmx.setDrugStoreShortName(transfer.getDrugStoreShortName());
        ybmx.setBarcode(transfer.getBarcode());
        ybmx.setHjrq_end(transfer.getJzrq());
        List<Ybjsmxxx> findBy = ybjsmxDao.findAll(new PageHelper(), ybmx);
        // 保存 明细中的 医保结算明细 ，用于去 重复 医保结算号
        HashSet<String> ybjshSet = new HashSet<String>();
        // 循环插入到结转记录表
        for (Ybjsmxxx ybmxOld : findBy) {
            YbjsmxxxJz jz = new YbjsmxxxJz();
            BeanUtils.copyProperties(ybmxOld, jz);

            jz.setTransfer_id(transfer.getId());
            jz.setDrugStoreShortName(transfer.getDrugStoreShortName());

            ybjshSet.add(jz.getYbjsh());
            int insert = ybjsmxJzDao.insert(jz);
            // 删除原来记录
            ybmxOld.setDrugStoreShortName(transfer.getDrugStoreShortName());
            int delete = ybjsmxDao.delete(ybmxOld);

            if (insert != delete) {
                new RuntimeException("结转 医保结算明细 异常");
            }
        }
        jzYBJSZ(ybjshSet, transfer);

        return "";
    }

    /**
     * 结转医保结算主信息
     *
     * @Description:TODO
     * @参数： @param transfer
     * @参数： @return
     * @return String
     * @throws
     * @author: chenliang
     * @time:2018-6-8 下午10:28:26
     */
    public String jzYBJSZ(HashSet<String> ybjshSet, Transfer transfer) {

        for (String ybjsh : ybjshSet) {
            Ybjszxx ybz = new Ybjszxx();
            ybz.setDrugStoreShortName(transfer.getDrugStoreShortName());
            ybz.setYbjsbh(ybjsh);
            // ybz.setBarcode(ybmx.getBarcode());
            // ybz.setHjrq_end(ybmx.getJzrq());
            List<Ybjszxx> findBy = ybjszDao.findAll(new PageHelper(), ybz);
            // 循环插入到结转记录表
            for (Ybjszxx ybzOld : findBy) {
                YbjszxxJz jz = new YbjszxxJz();
                BeanUtils.copyProperties(ybzOld, jz);

                jz.setTransfer_id(transfer.getId());
                jz.setDrugStoreShortName(transfer.getDrugStoreShortName());

                int insert = ybjszJzDao.insert(jz);
                // 删除原来记录
                ybzOld.setDrugStoreShortName(transfer.getDrugStoreShortName());
                int delete = ybjszDao.delete(ybzOld);
                if (insert != delete) {
                    new RuntimeException("结转 医保结算主信息 异常");
                }
            }
        }
        return "";
    }

    /**
     * 结转销售信息
     *
     * @Description:TODO
     * @参数： @param transfer
     * @参数： @return
     * @return String
     * @throws
     * @author: chenliang
     * @time:2018-6-8 下午11:13:33
     */
    public String jzXS(Transfer transfer) {

        Orderdetail od = new Orderdetail();
        od.setDrugStoreShortName(transfer.getDrugStoreShortName());
        od.setBarcode(transfer.getBarcode());
        od.setCreatedate_end(transfer.getJzrq());
        List<Orderdetail> findBy = odDtlDao.findAll(new PageHelper(), od);
        // 保存 明细中的 订单明细 ，用于去 重复 医保结算号
        HashSet<String> orderDetCodeSet = new HashSet<String>();
        // 循环插入到结转记录表
        for (Orderdetail odOld : findBy) {
            OrderdetailJz jz = new OrderdetailJz();
            BeanUtils.copyProperties(odOld, jz);

            jz.setTransfer_id(transfer.getId());
            jz.setDrugStoreShortName(transfer.getDrugStoreShortName());

            orderDetCodeSet.add(jz.getOrdercode());

            int insert = odDtlJzDao.insert(jz);
            // 删除原来记录
            odOld.setDrugStoreShortName(transfer.getDrugStoreShortName());
            int delete = odDtlDao.delete(odOld);
            if (insert != delete) {
                new RuntimeException("结转 订单明细 异常");
            }
        }
        // 结转订单日志
        jzDDRZ(orderDetCodeSet, transfer);
        // 结转订单付款 日志
        jzFKRZ(orderDetCodeSet, transfer);
        // 结转订单
        jzDD(orderDetCodeSet, transfer);

        return "";
    }

    /**
     * 结转订单日志
     *
     * @Description:TODO
     * @参数： @param ybjshSet
     * @参数： @param transfer
     * @参数： @return
     * @return String
     * @throws
     * @author: chenliang
     * @time:2018-6-8 下午11:29:30
     */
    public String jzDDRZ(HashSet<String> orderDetCodeSet, Transfer transfer) {

        for (String dCode : orderDetCodeSet) {
            Orderlog ol = new Orderlog();
            ol.setDrugStoreShortName(transfer.getDrugStoreShortName());
            ol.setOrdercode(dCode);
            List<Orderlog> findBy = odLogDao.findAll(new PageHelper(), ol);
            // 循环插入到结转记录表
            for (Orderlog olOld : findBy) {
                OrderlogJz jz = new OrderlogJz();
                BeanUtils.copyProperties(olOld, jz);

                jz.setTransfer_id(transfer.getId());
                jz.setDrugStoreShortName(transfer.getDrugStoreShortName());

                int insert = odLogJzDao.insert(jz);
                // 删除原来记录
                olOld.setDrugStoreShortName(transfer.getDrugStoreShortName());
                int delete = odLogDao.delete(olOld);
                if (insert != delete) {
                    new RuntimeException("结转 订单日志 异常");
                }
            }
        }
        return "";
    }

    /**
     * 结转订单付款 日志
     *
     * @Description:TODO
     * @参数： @param ybjshSet
     * @参数： @param transfer
     * @参数： @return
     * @return String
     * @throws
     * @author: chenliang
     * @time:2018-6-8 下午11:29:50
     */
    public String jzFKRZ(HashSet<String> orderDetCodeSet, Transfer transfer) {

        for (String dCode : orderDetCodeSet) {
            Paylog pl = new Paylog();
            pl.setDrugStoreShortName(transfer.getDrugStoreShortName());
            pl.setOrdercode(dCode);
            List<Paylog> findBy = pLogDao.findAll(new PageHelper(), pl);
            // 循环插入到结转记录表
            for (Paylog plOld : findBy) {
                PaylogJz jz = new PaylogJz();
                BeanUtils.copyProperties(plOld, jz);

                jz.setTransfer_id(transfer.getId());
                jz.setDrugStoreShortName(transfer.getDrugStoreShortName());

                int insert = pLogJzDao.insert(jz);
                // 删除原来记录
                plOld.setDrugStoreShortName(transfer.getDrugStoreShortName());
                int delete = pLogDao.delete(plOld);
                if (insert != delete) {
                    new RuntimeException("结转 订单付款日志 异常");
                }
            }
        }
        return "";
    }

    /**
     * 结转订单
     *
     * @Description:TODO
     * @参数： @param ybjshSet
     * @参数： @param transfer
     * @参数： @return
     * @return String
     * @throws
     * @author: chenliang
     * @time:2018-6-8 下午11:30:03
     */
    public String jzDD(HashSet<String> orderDetCodeSet, Transfer transfer) {

        for (String dCode : orderDetCodeSet) {
            Order o = new Order();
            o.setDrugStoreShortName(transfer.getDrugStoreShortName());
            o.setOrdercode(dCode);
            List<Order> findBy = odDao.findAll(new PageHelper(), o);
            // 循环插入到结转记录表
            for (Order oOld : findBy) {
                OrderJz jz = new OrderJz();
                BeanUtils.copyProperties(oOld, jz);

                jz.setTransfer_id(transfer.getId());
                jz.setDrugStoreShortName(transfer.getDrugStoreShortName());

                int insert = odJzDao.insert(jz);
                // 删除原来记录
                oOld.setDrugStoreShortName(transfer.getDrugStoreShortName());
                int delete = odDao.delete(oOld);
                if (insert != delete) {
                    new RuntimeException("结转 订单信息 异常");
                }
            }
        }
        return "";
    }

    public String modify(Transfer transfer) {
        this.dao.modify(transfer);
        return "操作成功!";
    }

    public String delete(Transfer transfer) {
        this.dao.delete(transfer);
        return "操作成功!";
    }

}
