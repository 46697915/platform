package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IOrderdetailJzDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.OrderdetailJz;
import com.wxsoft.business.service.IOrderdetailJzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("orderdetailJzService")
public class OrderdetailJzServiceImpl implements IOrderdetailJzService  {
    @Resource
    private IOrderdetailJzDao dao;


    public long findCount(OrderdetailJz orderdetailJz) {
        return dao.findCount(orderdetailJz);
    }

    public List<OrderdetailJz> findAll(PageHelper page,OrderdetailJz orderdetailJz) {
        List<OrderdetailJz> r = dao.findAll(page,orderdetailJz);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(OrderdetailJz orderdetailJz) {
        this.dao.insert(orderdetailJz);
        return "操作成功!";
    }

    public String modify(OrderdetailJz orderdetailJz) {
        this.dao.modify(orderdetailJz);
        return "操作成功!";
    }

    public String delete(OrderdetailJz orderdetailJz) {
        this.dao.delete(orderdetailJz);
        return "操作成功!";
    }

}
