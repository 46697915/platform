package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IPaylogJzDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.PaylogJz;
import com.wxsoft.business.service.IPaylogJzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("paylogJzService")
public class PaylogJzServiceImpl implements IPaylogJzService  {
    @Resource
    private IPaylogJzDao dao;


    public long findCount(PaylogJz paylogJz) {
        return dao.findCount(paylogJz);
    }

    public List<PaylogJz> findAll(PageHelper page,PaylogJz paylogJz) {
        List<PaylogJz> r = dao.findAll(page,paylogJz);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(PaylogJz paylogJz) {
        this.dao.insert(paylogJz);
        return "操作成功!";
    }

    public String modify(PaylogJz paylogJz) {
        this.dao.modify(paylogJz);
        return "操作成功!";
    }

    public String delete(PaylogJz paylogJz) {
        this.dao.delete(paylogJz);
        return "操作成功!";
    }

}
