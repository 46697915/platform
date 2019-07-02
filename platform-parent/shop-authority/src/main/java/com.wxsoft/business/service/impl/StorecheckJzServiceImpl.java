package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IStorecheckJzDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.StorecheckJz;
import com.wxsoft.business.service.IStorecheckJzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("storecheckJzService")
public class StorecheckJzServiceImpl implements IStorecheckJzService  {
    @Resource
    private IStorecheckJzDao dao;


    public long findCount(StorecheckJz storecheckJz) {
        return dao.findCount(storecheckJz);
    }

    public List<StorecheckJz> findAll(PageHelper page,StorecheckJz storecheckJz) {
        List<StorecheckJz> r = dao.findAll(page,storecheckJz);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(StorecheckJz storecheckJz) {
        this.dao.insert(storecheckJz);
        return "操作成功!";
    }

    public String modify(StorecheckJz storecheckJz) {
        this.dao.modify(storecheckJz);
        return "操作成功!";
    }

    public String delete(StorecheckJz storecheckJz) {
        this.dao.delete(storecheckJz);
        return "操作成功!";
    }

}
