package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IGoodsDao;
import com.wxsoft.business.dao.IOrderDao;
import com.wxsoft.business.dao.IOrderdetailDao;
import com.wxsoft.business.dao.IOrderlogDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.*;
import com.wxsoft.business.service.IOrderService;
import com.wxsoft.util.common.CodeUtil;
import com.wxsoft.util.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao dao;
    @Autowired
    private IOrderdetailDao orderDetailDao;
    @Autowired
    private IGoodsDao goodsDao;
    @Autowired
    private IOrderlogDao orderLogDao;

    public long findCount(Order order) {
        return dao.findCount(order);
    }

    public List<Order> findAll(PageHelper page, Order order) {
        List<Order> r = dao.findAll(page, order);
        return r;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(Order order) {
        this.dao.insert(order);
        return "操作成功!";
    }

    public String modify(Order order) {
        this.dao.modify(order);
        return "操作成功!";
    }

    public String delete(Order order) {
        this.dao.delete(order);
        return "操作成功!";
    }

    /**
     * 订单结算
     *
     * @param goodsIds
     * @param countsTemp
     * @param totalMoney
     * @param totalMoney2
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String charge(List<Map<String, String>> goodsDetl,
                         String totalMoney, String receiveTotalMoney,User user) {

        if(goodsDetl!=null && goodsDetl.size()>0){
            String orderCode = CodeUtil.getCode();
            double tolCount = 0;
            for (int i = 0; i < goodsDetl.size(); i++) {
                Map<String, String> orderM = goodsDetl.get(i);
                Goods good = new Goods();
                good.setId(Integer.parseInt(orderM.get("id")));
                good.setDrugStoreShortName(orderM.get("drugStoreShortName"));
                Goods goods = goodsDao.findById(good);
                //减库存
                this.subtractInventory(goods, orderM);
                //保存订单明细
                this.saveOrderDetail(orderCode, goods, orderM, i);

                double count = Double.parseDouble(orderM.get("count"));
                tolCount = tolCount+ count;
            }
            //保存订单
            saveOrder(orderCode, totalMoney, receiveTotalMoney, tolCount, user);
            //保存订单日志
            saveOrderLog(orderCode, user);
            return "收款成功！";
        }
        return "订单明细为空！";
    }
    /**
     * 减掉库存
     * @param goods
     * @param orderM
     * @return
     */
    public String subtractInventory(Goods goods,Map<String, String> orderM){
        Goods gs = new Goods();

        gs.setId(goods.getId());
        String stockStr = goods.getStock();
        if(stockStr==null || "".equals(stockStr)){
            stockStr = "0";
        }
        double stock = Double.parseDouble(stockStr);
        double count = Double.parseDouble(orderM.get("count"));
        if((stock-count)<0){
            throw new RuntimeException(goods.getCommonname()+"剩余库存"+stock+"-本次出售"+count+"="+(stock-count)+"不能销售。");
        }
        String newStock = String.valueOf(stock-count);
        gs.setStock(newStock);

        gs.setDrugStoreShortName(orderM.get("drugStoreShortName"));
        goodsDao.modify(gs);

        return "";
    }
    /**
     * 保存订单日志表
     * @param orderCode
     * @param user
     * @return
     */
    public String saveOrderLog(String orderCode,User user){
        Orderlog ol = new Orderlog();

        ol.setOperate(user.getUsername());
        ol.setOperatedate(DateUtil.currentDatetime());
        ol.setOrdercode(orderCode);
        ol.setOperator("新增订单");
        ol.setOperatetype("1");	//操作类型

        ol.setDrugStoreShortName(user.getDrugStoreShortName());
        orderLogDao.insert(ol);

        return "";
    }
    /**
     * 保存订单
     * @param orderCode
     * @param totalMoney
     * @param receiveTotalMoney
     * @param tolCount
     * @return
     */
    public String saveOrder(String orderCode,String totalMoney, String receiveTotalMoney,double tolCount,User user){
        Order o = new Order();

        o.setOrdercode(orderCode);
        o.setAssociator(user.getUsername());
        o.setPaytype("1");	//支付方式 (默认现金)
        o.setTransport("1");	//运输方式(默认现场购买)
        o.setDiscount("1");	//折扣
        o.setCreatedate(DateUtil.currentDatetime());
        o.setOrderstate("10");	//订单状态（送货完成）
        o.setPaystate("10");	//付款状态
        o.setRefundstae("0");	//退款状态
        o.setReceiptamount(receiveTotalMoney);	//收款金额
        o.setOderamount(totalMoney);	//订单总金额
        o.setGoodsamount(totalMoney);	//商品总金额
        o.setGoodstotal(String.valueOf(tolCount));	//商品总数量
        o.setIsmodify("0");	//是否修改过

        o.setDrugStoreShortName(user.getDrugStoreShortName());
        dao.insert(o);

        return "";
    }
    /**
     * 保存订单明细
     * @param orderCode
     * @param goods
     * @param orderM
     * @param serial
     */
    public String saveOrderDetail(String orderCode,Goods goods,Map<String, String> orderM,int serial){
        Orderdetail od = new Orderdetail();

        od.setOrdercode(orderCode);
        od.setDetailcode(serial+"");
        od.setBarcode(goods.getBarcode());
        od.setGoodsname(goods.getGoodsname());
        od.setCommonname(goods.getCommonname());
        od.setPrice(goods.getPrice());
        od.setAmount(orderM.get("count"));
        od.setCreatedate(DateUtil.currentDatetime());

        Double amonent = Double.parseDouble(goods.getPrice())
                * Double.parseDouble(orderM.get("count"));
        od.setMoney("" + amonent);
        //收款金额
        od.setGetmoney("" + amonent);
        od.setPaytype("1");	//支付方式 (默认现金)
        od.setUnits(goods.getUnits());	//购货单位

        od.setDrugStoreShortName(orderM.get("drugStoreShortName"));
        orderDetailDao.insert(od);

        return "";
    }

}
