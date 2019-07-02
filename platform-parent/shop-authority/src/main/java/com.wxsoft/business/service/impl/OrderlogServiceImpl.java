package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IOrderlogDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderlog;
import com.wxsoft.business.service.IOrderlogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("orderlogService")
public class OrderlogServiceImpl implements IOrderlogService  {
    @Resource
    private IOrderlogDao dao;


    public long findCount(Orderlog orderlog) {
        return dao.findCount(orderlog);
    }

    public List<Orderlog> findAll(PageHelper page,Orderlog orderlog) {
        List<Orderlog> r = dao.findAll(page,orderlog);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(Orderlog orderlog) {
        this.dao.insert(orderlog);
        return "操作成功!";
    }

    public String modify(Orderlog orderlog) {
        this.dao.modify(orderlog);
        return "操作成功!";
    }

    public String delete(Orderlog orderlog) {
        this.dao.delete(orderlog);
        return "操作成功!";
    }

}
