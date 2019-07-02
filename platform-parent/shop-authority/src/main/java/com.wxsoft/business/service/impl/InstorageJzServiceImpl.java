package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IInstorageJzDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.InstorageJz;
import com.wxsoft.business.service.IInstorageJzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("instorageJzService")
public class InstorageJzServiceImpl implements IInstorageJzService  {
    @Resource
    private IInstorageJzDao dao;


    public long findCount(InstorageJz instorageJz) {
        return dao.findCount(instorageJz);
    }

    public List<InstorageJz> findAll(PageHelper page,InstorageJz instorageJz) {
        List<InstorageJz> r = dao.findAll(page,instorageJz);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(InstorageJz instorageJz) {
        this.dao.insert(instorageJz);
        return "操作成功!";
    }

    public String modify(InstorageJz instorageJz) {
        this.dao.modify(instorageJz);
        return "操作成功!";
    }

    public String delete(InstorageJz instorageJz) {
        this.dao.delete(instorageJz);
        return "操作成功!";
    }

}
