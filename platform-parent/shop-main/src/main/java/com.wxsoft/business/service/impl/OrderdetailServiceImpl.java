package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IOrderdetailDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderdetail;
import com.wxsoft.business.pojo.OrderdetailSummary;
import com.wxsoft.business.service.IOrderdetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("orderdetailService")
public class OrderdetailServiceImpl implements IOrderdetailService  {
    @Resource
    private IOrderdetailDao dao;


    public long findCount(Orderdetail orderdetail) {
        return dao.findCount(orderdetail);
    }

    public List<Orderdetail> findAll(PageHelper page,Orderdetail orderdetail) {
        List<Orderdetail> r = dao.findAll(page,orderdetail);
        return r;
    }
    public long salesSummaryCount(Orderdetail orderdetail){
        return dao.salesSummaryCount(orderdetail);
    }
    public List<OrderdetailSummary> salesSummaryAll(PageHelper page,Orderdetail orderdetail){
        List<OrderdetailSummary> r = dao.salesSummaryAll(page,orderdetail);
        return r;
    }

    public long salesAllSummaryCount(Orderdetail orderdetail){
        return dao.salesAllSummaryCount(orderdetail);
    }
    public List<OrderdetailSummary> salesAllSummaryAll(PageHelper page,Orderdetail orderdetail){
        List<OrderdetailSummary> r = dao.salesAllSummaryAll(page,orderdetail);
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
        return dao.findMoneyCount(orderdetail);
    }
}
