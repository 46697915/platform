package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IOrderlogJzDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.OrderlogJz;
import com.wxsoft.business.service.IOrderlogJzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("orderlogJzService")
public class OrderlogJzServiceImpl implements IOrderlogJzService  {
    @Resource
    private IOrderlogJzDao dao;


    public long findCount(OrderlogJz orderlogJz) {
        return dao.findCount(orderlogJz);
    }

    public List<OrderlogJz> findAll(PageHelper page,OrderlogJz orderlogJz) {
        List<OrderlogJz> r = dao.findAll(page,orderlogJz);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(OrderlogJz orderlogJz) {
        this.dao.insert(orderlogJz);
        return "操作成功!";
    }

    public String modify(OrderlogJz orderlogJz) {
        this.dao.modify(orderlogJz);
        return "操作成功!";
    }

    public String delete(OrderlogJz orderlogJz) {
        this.dao.delete(orderlogJz);
        return "操作成功!";
    }

}
