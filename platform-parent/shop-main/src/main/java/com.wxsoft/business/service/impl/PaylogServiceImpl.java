package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IPaylogDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Paylog;
import com.wxsoft.business.service.IPaylogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("paylogService")
public class PaylogServiceImpl implements IPaylogService  {
    @Resource
    private IPaylogDao dao;


    public long findCount(Paylog paylog) {
        return dao.findCount(paylog);
    }

    public List<Paylog> findAll(PageHelper page,Paylog paylog) {
        List<Paylog> r = dao.findAll(page,paylog);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(Paylog paylog) {
        this.dao.insert(paylog);
        return "操作成功!";
    }

    public String modify(Paylog paylog) {
        this.dao.modify(paylog);
        return "操作成功!";
    }

    public String delete(Paylog paylog) {
        this.dao.delete(paylog);
        return "操作成功!";
    }

}
