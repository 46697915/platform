package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IOrderJzDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.OrderJz;
import com.wxsoft.business.service.IOrderJzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("orderJzService")
public class OrderJzServiceImpl implements IOrderJzService  {
    @Resource
    private IOrderJzDao dao;


    public long findCount(OrderJz orderJz) {
        return dao.findCount(orderJz);
    }

    public List<OrderJz> findAll(PageHelper page,OrderJz orderJz) {
        List<OrderJz> r = dao.findAll(page,orderJz);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(OrderJz orderJz) {
        this.dao.insert(orderJz);
        return "操作成功!";
    }

    public String modify(OrderJz orderJz) {
        this.dao.modify(orderJz);
        return "操作成功!";
    }

    public String delete(OrderJz orderJz) {
        this.dao.delete(orderJz);
        return "操作成功!";
    }

}
