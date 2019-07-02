package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IKeyvalueDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Keyvalue;
import com.wxsoft.business.service.IKeyvalueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("keyvalueService")
public class KeyvalueServiceImpl implements IKeyvalueService  {
    @Resource
    private IKeyvalueDao dao;


    public long findCount(Keyvalue keyvalue) {
        return dao.findCount(keyvalue);
    }

    public List<Keyvalue> findAll(PageHelper page,Keyvalue keyvalue) {
        List<Keyvalue> r = dao.findAll(page,keyvalue);
        return r;
    }

    /**
     * 根据类型返回字段选项
     */
    public List<Keyvalue> findByType(String type){
        List<Keyvalue> r = dao.findByType(type);
        return r ;
    }


    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(Keyvalue keyvalue) {
        this.dao.insert(keyvalue);
        return "操作成功!";
    }

    public String modify(Keyvalue keyvalue) {
        this.dao.modify(keyvalue);
        return "操作成功!";
    }

    public String delete(int id) {
        this.dao.delete(id);
        return "操作成功!";
    }

}
